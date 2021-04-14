package com.casebeaumonde.activities.myoutfitsdetail

import android.app.Activity
import android.app.Dialog
import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.*
import com.casebeaumonde.Controller.Controller
import com.casebeaumonde.R
import com.casebeaumonde.activities.addItemtoCLoset.response.AddClosetItemResponse
import com.casebeaumonde.activities.myoutfitsdetail.response.AddOutfitItemResponse
import com.casebeaumonde.constants.BaseClass
import com.casebeaumonde.constants.Constants
import com.casebeaumonde.utilities.Utility
import com.github.dhaval2404.imagepicker.ImagePicker
import kotlinx.android.synthetic.main.activity_add_item_to_closet.*
import kotlinx.android.synthetic.main.activity_add_item_to_closet.parent_additemtocloset
import kotlinx.android.synthetic.main.activity_add_new_outfit_item.*
import okhttp3.MultipartBody
import retrofit2.Response
import java.io.File

class AddNewOutfitItem : BaseClass(), Controller.AddClosetItemListAPI,Controller.AddOutfitItemAPI {
    private lateinit var utility: Utility
    private lateinit var pd: ProgressDialog
    private lateinit var controller: Controller
    private lateinit var part: MultipartBody.Part
    private lateinit var bitMap: Bitmap
    private lateinit var aditemtocloset_title: EditText
    private lateinit var aditemtocloset_decs: EditText
    private lateinit var additemclosets_categoryspinner: Spinner
    private lateinit var additemclosets__upload: Button
    private lateinit var additemclosets__uploadfilename: TextView
    private lateinit var additemclosets_Sizespinner: Spinner
    private lateinit var additemclosets_Colorpinner: Spinner
    private lateinit var additemclosets_Brandpinner: Spinner
    private lateinit var aditemtocloset_price: EditText

    private lateinit var aditemtocloset_add: Button
    private lateinit var aditemtocloset_cancel: Button
    private lateinit var additemclosets_category: TextView
    private lateinit var additemclosets_brands: TextView
    private lateinit var additemclosets_size: TextView
    private lateinit var additemclosets_color: TextView
    private lateinit var texttitle: TextView
    private lateinit var backon_additemstocloset: ImageButton
    private lateinit var categorties: ArrayList<AddClosetItemResponse.Data.Category>
    private lateinit var cateName : ArrayList<String>
    private  var cateID : String =""

    private var brandID = ""
    private var brandPos: Int = 0
    private lateinit var brands :ArrayList<AddClosetItemResponse.Data.Brand>
    private lateinit var brandName : ArrayList<String>


    private var colorID = ""
    private var colorPos = 0
    private lateinit var color: ArrayList<AddClosetItemResponse.Data.Color>
    private lateinit var Colors : ArrayList<String>


    private var sizePos: Int = 0
    private var sizeID : String =""
    private lateinit var Size: ArrayList<String>
    private lateinit var size: ArrayList<AddClosetItemResponse.Data.Size>

    private var path: String = ""
    private var outfitID : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new_outfit_item)
        outfitID = intent.getStringExtra("outfitID").toString()
        findIds()
        listeners()
    }

    private fun listeners() {

        backon_additemstocloset.setOnClickListener {
            onBackPressed()
        }

        additemclosets__upload.setOnClickListener {
            pictureSelectionDialog()
        }

        aditemtocloset_add.setOnClickListener {
            addItems(intent.getStringExtra("closetID"), "0")
        }

        additemclosets_categoryspinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View, position: Int, id: Long
            ) {
                // additemclosets_category.setText(parent.selectedItem.toString())
                cateID = categorties.get(position).id.toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {

            }

        }
    }

    private fun addItems(closetId: String?, edit: String) {

        when {
            aditemtocloset_title.text.isEmpty() -> {
                aditemtocloset_title.setError("Enter Title")
                aditemtocloset_title.requestFocus()
            }

            aditemtocloset_decs.text.isEmpty() -> {
                aditemtocloset_decs.setError("Enter Description")
                aditemtocloset_decs.requestFocus()
            }

            additemclosets__uploadfilename.text.isEmpty() -> {
                additemclosets__uploadfilename.setError("Upload Image")
                additemclosets__uploadfilename.requestFocus()
            }

//            aditemtocloset_price.text.isEmpty() -> {
//                aditemtocloset_price.setError("Enter Price")
//                aditemtocloset_price.requestFocus()
//            }
            else -> {
                // if (aditemtocloset_add.equals("Add")) {
                pd.show()
                pd.setContentView(R.layout.loading)
                if (edit.equals("1")) {
                    controller.EditClosetItem(
                        "Bearer " + getStringVal(Constants.TOKEN),
                        part,
                        aditemtocloset_title.text.toString(),
                        aditemtocloset_decs.text.toString(),
                        closetId.toString(),
                        cateID,
                        sizeID,
                        colorID,
                        brandID,
                        aditemtocloset_price.text.toString(),
                        outfitID
                    )
                } else {
                    controller.AddOutfitItem(
                        "Bearer " + getStringVal(Constants.TOKEN),
                        part,
                        aditemtocloset_title.text.toString(),
                        aditemtocloset_decs.text.toString(),
                        outfitID.toString(),
                        cateID,
                        sizeID,
                        colorID,
                        brandID,
                        aditemtocloset_price.text.toString(),
                        getStringVal(Constants.USERID).toString()
                    )
                }
            }

        }
    }

    private fun findIds() {
        controller = Controller()
        controller.Controller(this ,this     )

        utility = Utility()
        pd = ProgressDialog(this)
        pd!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        pd!!.window!!.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        pd!!.isIndeterminate = true
        pd!!.setCancelable(false)

        texttitle = findViewById(R.id.texttitle)
        aditemtocloset_title = findViewById(R.id.aditemtocloset_title)
        aditemtocloset_decs = findViewById(R.id.aditemtocloset_decs)
        additemclosets_categoryspinner = findViewById(R.id.additemclosets_categoryspinner)
        additemclosets__upload = findViewById(R.id.additemclosets__upload)
        additemclosets__uploadfilename = findViewById(R.id.additemclosets__uploadfilename)
        additemclosets_Sizespinner = findViewById(R.id.additemclosets_Sizespinner)
        additemclosets_Colorpinner = findViewById(R.id.additemclosets_Colorpinner)
        additemclosets_Brandpinner = findViewById(R.id.additemclosets_Brandpinner)
        aditemtocloset_price = findViewById(R.id.aditemtocloset_price)
        aditemtocloset_cancel = findViewById(R.id.aditemtocloset_cancel)
        aditemtocloset_add = findViewById(R.id.aditemtocloset_add)
        additemclosets_brands = findViewById(R.id.additemclosets_brands)
        additemclosets_size = findViewById(R.id.additemclosets_size)
        additemclosets_color = findViewById(R.id.additemclosets_color)
        backon_additemstocloset = findViewById(R.id.backon_additemstocloset)


        controller.AddClosetItemList("Bearer " + getStringVal(Constants.TOKEN))
        pd.show()
        pd.setContentView(R.layout.loading)
    }

    private fun pictureSelectionDialog() {
        val camera: LinearLayout
        val gallery: LinearLayout
        val dialog = Dialog(this!!)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.imagepicker)
        camera = dialog.findViewById(R.id.linear_camera) as LinearLayout
        gallery = dialog.findViewById(R.id.linear_gallery) as LinearLayout

        camera.setOnClickListener {
            ImagePicker.with(this)
                .cameraOnly()
                .crop()         //User can only capture image using Camera
                .start()
            dialog.dismiss()
        }

        gallery.setOnClickListener {
            ImagePicker.with(this)
                .galleryOnly()
                .crop()     //User can only select image from Gallery
                .start()    //Default Request Code is ImagePicker.REQUEST_CODE
            dialog.dismiss()
        }
        dialog.show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            //Image Uri will not be null for RESULT_OK
            val fileUri = data?.data

            //You can get File object from intent
            var file: File? = ImagePicker.getFile(data)
            //You can also get File Path from intent
            val filePath: String? = ImagePicker.getFilePath(data)

            path = filePath!!
            bitMap = MediaStore.Images.Media.getBitmap(contentResolver, fileUri)
            part = Utility.sendImageFileToserver(filesDir, bitMap, "picture")
            additemclosets__uploadfilename.text = path.toString()

        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            utility!!.relative_snackbar(
                parent_addoutfititem!!,
                ImagePicker.getError(data),
                getString(R.string.close_up)
            )
        } else {
            utility!!.relative_snackbar(
                parent_addoutfititem,
                "Task Cancelled",
                getString(R.string.close_up)
            )
        }
    }

    override fun onAddClosetItemListSuccess(addClosetItemList: Response<AddClosetItemResponse>) {
        pd.dismiss()
       if (addClosetItemList.isSuccessful)

       {
            if (addClosetItemList.body()?.getCode().equals("200"))
            {
                categorties = ArrayList()
                cateName = ArrayList()
                categorties =
                    addClosetItemList.body()
                        ?.getData()?.categories as ArrayList<AddClosetItemResponse.Data.Category>

                for (i in 0 until categorties.size) {
                    val cat = categorties.get(i)
                    cateName.add(cat.name.toString())
                }


                brandName = ArrayList()
                brands =
                    addClosetItemList.body()
                        ?.getData()?.brands as ArrayList<AddClosetItemResponse.Data.Brand>

                for (i in 0 until brands.size) {
                    val brand = brands.get(i)
                    brandName.add(brand.name.toString())
                }

                if (brands.size > 0) {

                    for (i in brands.indices) {
                        val catpos = brands[i]

                        if (catpos.id.toString().equals(brandID)) {
                            brandPos = i
                        }
                    }
                }

                Colors = ArrayList()
                color =
                    addClosetItemList.body()
                        ?.getData()?.colors as ArrayList<AddClosetItemResponse.Data.Color>
                for (i in 0 until color.size) {
                    val colors = color.get(i)
                    Colors.add(colors.name.toString())
                }
                if (color.size > 0) {

                    for (i in color.indices) {
                        val catpos = color[i]

                        if (catpos.id.toString().equals(colorID)) {
                            colorPos = i

                        }
                    }
                }

                Size = ArrayList()
                size =
                    addClosetItemList.body()
                        ?.getData()?.sizes as ArrayList<AddClosetItemResponse.Data.Size>

                for (i in 0 until size.size) {
                    val SIZE = size.get(i)
                    Size.add(SIZE.name.toString())
                }


                if (size.size > 0) {

                    for (i in size.indices) {
                        val catpos = size[i]
                        // Toast.makeText(this,""+sizePos+" =>   "+sizeID,Toast.LENGTH_LONG).show()
                        if (catpos.id.toString().equals(sizeID)) {
                            sizePos = i

                        }
                    }
                }

                setFetchList()
            }
       } else {

       }


    }
    private fun setFetchList() {
        //additemclosets_category.setText(closetResponse.get(closetItemID.toInt()).category.name)
        //ToDo: Set Categories in Spinner
        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_dropdown_item, cateName
        )
        additemclosets_categoryspinner.adapter = adapter
        if (!cateID.equals("")) {
            additemclosets_categoryspinner.setSelection(cateID.toInt())
        }


        //ToDo: Set Brands in Spinner
        val adapter1 = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, brandName)
        additemclosets_Brandpinner.adapter = adapter1
        additemclosets_Brandpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    additemclosets_brands.setText(brands.get(position).name)

                    brandID = brands.get(position).id.toString()

                   // additemclosets_Brandpinner.setSelection(brandPos)
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }

            }

        //ToDo : Set Size in Spinner
        val adapter2 = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, Size)
        additemclosets_Sizespinner.adapter = adapter2
        additemclosets_Sizespinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {

                    sizeID = size.get(position).id.toString()
                    additemclosets_size.setText(size.get(position).name)

                  //  additemclosets_Sizespinner.setSelection(sizePos)

                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }

            }

        //ToDo : Set Color in  Spinner
        val adapter3 = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, Colors)
        additemclosets_Colorpinner.adapter = adapter3
        additemclosets_Colorpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    additemclosets_color.setText(color.get(position).name)
                    //additemclosets_Colorpinner.setSelection(colorPos)
                    colorID = color.get(position).name.toString()
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }

            }
    }

    override fun onaddOutfitItemSuccess(success: Response<AddOutfitItemResponse>) {
pd.dismiss()
        if (success.isSuccessful)
            {
                if (success.body()?.getCode().equals("200"))
                {
                   finish()
                } else {
                    utility!!.relative_snackbar(
                        parent_addoutfititem,
                        success.message(),
                        getString(R.string.close_up)
                    )
                }
            } else {
            pd.dismiss()
            utility!!.relative_snackbar(
                parent_addoutfititem,
                success.message(),
                getString(R.string.close_up)
            )
            }
    }


    override fun error(error: String?) {
        pd.dismiss()
        utility!!.relative_snackbar(
            parent_addoutfititem,
            error,
            getString(R.string.close_up)
        )
    }
}