package com.casebeaumonde.activities.addItemtoCLoset

import android.app.Activity
import android.app.Dialog
import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.*
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.casebeaumonde.Controller.Controller
import com.casebeaumonde.R
import com.casebeaumonde.activities.ClosetItem.response.ClosetsItemsResponse
import com.casebeaumonde.activities.ClosetItem.response.EditClosetItemResponse
import com.casebeaumonde.constants.BaseClass
import com.casebeaumonde.constants.Constants
import com.casebeaumonde.activities.addItemtoCLoset.response.AddClosetItemResponse
import com.casebeaumonde.utilities.Utility
import com.github.dhaval2404.imagepicker.ImagePicker
import kotlinx.android.synthetic.main.activity_add_item_to_closet.*
import kotlinx.android.synthetic.main.activity_closets_items.*
import okhttp3.MultipartBody
import retrofit2.Response
import java.io.File

class AddItemToCloset : BaseClass(), Controller.AddClosetItemListAPI, Controller.AddClosetItemAPI,
    Controller.ClosetItemsAPI, Controller.EditClosetItemAPI {

    //ToDo: Edit at 370 Line
    private lateinit var utility: Utility
    private lateinit var pd: ProgressDialog
    private lateinit var controller: Controller
    private lateinit var closetCreateListItems: ArrayList<AddClosetItemResponse.Data>
    private lateinit var userClosets: ArrayList<ClosetsItemsResponse.Data.AllCloset>
    private lateinit var categorties: ArrayList<AddClosetItemResponse.Data.Category>
    private lateinit var brands: ArrayList<AddClosetItemResponse.Data.Brand>
    private lateinit var size: ArrayList<AddClosetItemResponse.Data.Size>
    private lateinit var color: ArrayList<AddClosetItemResponse.Data.Color>
    private lateinit var closetItems: ArrayList<ClosetsItemsResponse.Data.Closet>
    private lateinit var cateName: ArrayList<String>
    private lateinit var brandName: ArrayList<String>
    private lateinit var Size: ArrayList<String>
    private lateinit var Colors: ArrayList<String>
    private lateinit var list: ArrayList<String>
    private lateinit var listID: ArrayList<String>
    private var cateID = ""
    private var categoryName = ""
    private var brandID = ""
    private var colorID = ""
    private var sizeID = ""
    private var closetItemID = ""
    private var path: String = ""
    private var edit: String = ""
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_item_to_closet)
        findIds()
        controller = Controller()
        controller.Controller(this, this, this, this)
        controller.AddClosetItemList("Bearer " + getStringVal(Constants.TOKEN))

        edit = intent.getStringExtra("edit").toString()
        pd.show()
        pd.setContentView(R.layout.loading)
        listeners()
        controller.ClosetItems(
            "Bearer " + getStringVal(Constants.TOKEN),
            intent.getStringExtra("closetID")
        )
    }

    private fun listeners() {
        additemclosets__upload.setOnClickListener {
            pictureSelectionDialog()
        }

        aditemtocloset_cancel.setOnClickListener {
            onBackPressed()
        }

        backon_additemstocloset.setOnClickListener {
            onBackPressed()
        }

        if (edit.equals("1")) {
            texttitle.setText("Update")
            aditemtocloset_add.setText("Update")
        }
        aditemtocloset_add.setOnClickListener {
            addItems(intent.getStringExtra("closetID"), edit)
        }

        additemclosets_categoryspinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View, position: Int, id: Long
            ) {
                // additemclosets_category.setText(parent.selectedItem.toString())
                cateID = categorties.get(closetItemID.toInt()).id.toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {

            }

        }
    }

    private fun findIds() {
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
                parent_additemtocloset!!,
                ImagePicker.getError(data),
                getString(R.string.close_up)
            )
        } else {
            utility!!.relative_snackbar(
                parent_additemtocloset,
                "Task Cancelled",
                getString(R.string.close_up)
            )
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

            aditemtocloset_price.text.isEmpty() -> {
                aditemtocloset_price.setError("Enter Price")
                aditemtocloset_price.requestFocus()
            }
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
                        closetItemID
                    )
                } else {
                    controller.AddClosetItems(
                        "Bearer " + getStringVal(Constants.TOKEN),
                        part,
                        aditemtocloset_title.text.toString(),
                        aditemtocloset_decs.text.toString(),
                        closetId.toString(),
                        cateID,
                        sizeID,
                        colorID,
                        brandID,
                        aditemtocloset_price.text.toString()
                    )
                }
            }

        }
    }

    override fun onAddClosetItemListSuccess(addClosetItemList: Response<AddClosetItemResponse>) {
        if (addClosetItemList.isSuccessful) {
            categorties = ArrayList()
            cateName = ArrayList()
            categorties =
                addClosetItemList.body()
                    ?.getData()?.categories as ArrayList<AddClosetItemResponse.Data.Category>

            for (i in 0 until categorties.size) {
                val cat = categorties.get(i)
                cateName.add(cat.name.toString())
            }


            // catePos = categorties.get(intent.getStringExtra("closetItemID").toInt()).id.toString()


            brandName = ArrayList()
            brands =
                addClosetItemList.body()
                    ?.getData()?.brands as ArrayList<AddClosetItemResponse.Data.Brand>

            for (i in 0 until brands.size) {
                val brand = brands.get(i)
                brandName.add(brand.name.toString())
            }

            Colors = ArrayList()

            color =
                addClosetItemList.body()
                    ?.getData()?.colors as ArrayList<AddClosetItemResponse.Data.Color>
            for (i in 0 until color.size) {
                val colors = color.get(i)
                Colors.add(colors.name.toString())
            }


            Size = ArrayList()
            size =
                addClosetItemList.body()
                    ?.getData()?.sizes as ArrayList<AddClosetItemResponse.Data.Size>

            for (i in 0 until size.size) {
                val SIZE = size.get(i)
                Size.add(SIZE.name.toString())
            }

            setFetchList()
        } else {
            utility!!.relative_snackbar(
                parent_additemtocloset!!,
                addClosetItemList.message(),
                getString(R.string.close_up)
            )
        }
    }

    override fun onAddClosetItemSuccess(addclosetItem: Response<AddClosetItemResponse>) {
        pd.dismiss()
        if (addclosetItem.isSuccessful) {
            utility!!.relative_snackbar(
                parent_additemtocloset!!,
                "Successfully Added",
                getString(R.string.close_up)
            )
            onBackPressed()
        } else {
            utility!!.relative_snackbar(
                parent_additemtocloset!!,
                addclosetItem.message(),
                getString(R.string.close_up)
            )
        }
    }

    override fun onClosetItemSuccess(closetItemsResponse: Response<ClosetsItemsResponse>) {
        pd.dismiss()
        if (closetItemsResponse.isSuccessful) {
            if (edit.equals("1")) {


                closetItemID = closetItemsResponse.body()?.getData()?.closet?.items?.get(
                    intent.getStringExtra("closetItemID").toInt()
                )?.categoryId.toString()


                val arr = categorties
                val item = closetItemID;



                aditemtocloset_title.setText(
                    closetItemsResponse.body()?.getData()?.closet?.items?.get(
                        intent.getStringExtra("closetItemID").toInt()
                    )?.title
                )
                aditemtocloset_decs.setText(
                    closetItemsResponse.body()?.getData()?.closet?.items?.get(
                        intent.getStringExtra("closetItemID").toInt()
                    )?.description
                )
                aditemtocloset_price.setText(
                    closetItemsResponse.body()?.getData()?.closet?.items?.get(
                        intent.getStringExtra("closetItemID").toInt()
                    )?.price.toString()
                )

                cateID = closetItemsResponse.body()?.getData()?.closet?.items?.get(
                    intent.getStringExtra("closetItemID").toInt()
                )?.category?.id.toString()!!

                categoryName = closetItemsResponse.body()?.getData()?.closet?.items?.get(
                    intent.getStringExtra("closetItemID").toInt()
                )?.category?.name!!

                Glide.with(this).asBitmap().load(
                    Constants.BASE_IMAGE_URL + closetItemsResponse.body()
                        ?.getData()?.closet?.items?.get(
                            intent.getStringExtra("closetItemID").toInt()
                        )?.picture
                )
                    .into(object : CustomTarget<Bitmap?>() {
                        override fun onResourceReady(
                            resource: Bitmap,
                            transition: com.bumptech.glide.request.transition.Transition<in Bitmap?>?
                        ) {
                            Log.d("image", "" + resource)
                            additemclosets__uploadfilename.text = resource.toString()
                            bitMap = resource
                            part = Utility.sendImageFileToserver(filesDir, bitMap, "image")
                        }

                        override fun onLoadCleared(placeholder: Drawable?) {
                            Log.d("loadcleared", "" + placeholder)
                        }
                    })
            }

        } else {
            utility!!.relative_snackbar(
                parent_closetsItems!!,
                closetItemsResponse.message(),
                getString(R.string.close_up)
            )
        }
    }


    override fun onEditClosetItemSuccess(editClosetItem: Response<EditClosetItemResponse>) {
        pd.dismiss()
        onBackPressed()
    }

    override fun error(error: String?) {
        utility!!.relative_snackbar(
            parent_additemtocloset!!,
            error,
            getString(R.string.close_up)
        )
    }

    private fun setFetchList() {
        //additemclosets_category.setText(closetResponse.get(closetItemID.toInt()).category.name)
        //ToDo: Set Categories in Spinner
        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_dropdown_item, cateName
        )
        additemclosets_categoryspinner.adapter = adapter
        additemclosets_categoryspinner.setSelection(cateID.toInt())


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

                    colorID = color.get(position).name.toString()
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }

            }
    }
}