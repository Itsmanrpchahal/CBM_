package com.casebeaumonde.fragments.productManagement.addproduct

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Dialog
import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.*
import com.casebeaumonde.Controller.Controller
import com.casebeaumonde.R
import com.casebeaumonde.activities.myclosets.response.FetchListResponse
import com.casebeaumonde.constants.BaseClass
import com.casebeaumonde.constants.Constants
import com.casebeaumonde.utilities.Utility
import com.github.dhaval2404.imagepicker.ImagePicker
import kotlinx.android.synthetic.main.activity_add_new_product.*
import okhttp3.MultipartBody
import retrofit2.Response
import java.io.File

class AddNewProduct : BaseClass(),Controller.FetchListAPI {


    private lateinit var utility: Utility
    private lateinit var pd: ProgressDialog
    private lateinit var controller: Controller
    private lateinit var part: MultipartBody.Part
    private lateinit var bitMap: Bitmap
    private lateinit var backon_additemstocloset: ImageButton
    private lateinit var simpleproduct : Button
    private lateinit var variableproduct : Button
    private lateinit var upload: Button
    private lateinit var uploadfilename: TextView
    private var path: String = ""
    private lateinit var categories : ArrayList<FetchListResponse.Data.Category>
    private lateinit var category : ArrayList<String>
    private lateinit var brands : ArrayList<FetchListResponse.Data.Brand>
    private lateinit var brandsname :ArrayList<String>
    private  var catPos : Int = 0
    private lateinit var saveproductbt : Button
    private lateinit var productname:EditText
    private lateinit var shortdescription:EditText
    private lateinit var description:EditText
    private lateinit var regularprice:EditText
    private lateinit var sellprice:EditText
    private lateinit var stockquantity:EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new_product)

        findIds()
        lisenters()
    }

    @SuppressLint("ResourceAsColor")
    private fun lisenters() {
        backon_additemstocloset.setOnClickListener {
            onBackPressed()
        }

        simpleproduct.setOnClickListener {
            simpleproduct.setBackgroundResource(R.drawable.whiteborder)
            variableproduct.setBackgroundResource(R.drawable.greyborder)

        }

        variableproduct.setOnClickListener {
            simpleproduct.setBackgroundResource(R.drawable.greyborder)
            variableproduct.setBackgroundResource(R.drawable.whiteborder)

        }

        upload.setOnClickListener {
            pictureSelectionDialog()
        }

        saveproductbt.setOnClickListener {
            when {
                productname.text.isEmpty() -> {
                    productname.requestFocus()
                    productname.setError("Enter Product name")
                }

                shortdescription.text.isEmpty() -> {
                    shortdescription.requestFocus()
                    shortdescription.setError("Enter Short Description")
                }

                description.text.isEmpty() -> {
                    description.requestFocus()
                    description.setError("Enter Description")
                }

                uploadfilename.text.isEmpty() -> {
                    uploadfilename.requestFocus()
                    uploadfilename.setError("Upload Image")
                }

                regularprice.text.isEmpty() -> {
                    regularprice.requestFocus()
                    regularprice.setError("Enter Regular Price")
                }

                sellprice.text.isEmpty() -> {
                    sellprice.requestFocus()
                    sellprice.setError("Enter Sell Price")
                }

                stockquantity.text.isEmpty() -> {
                    stockquantity.requestFocus()
                    stockquantity.setError("Enter Stock Quantity")
                }

                else -> {

                }
            }
        }
    }

    private fun findIds() {
        controller = Controller()
        controller.Controller(this )
        backon_additemstocloset = findViewById(R.id.backon_additemstocloset)
        simpleproduct = findViewById(R.id.simpleproduct)
        variableproduct = findViewById(R.id.variableproduct)
        upload = findViewById(R.id.upload)
        uploadfilename = findViewById(R.id.uploadfilename)
        saveproductbt = findViewById(R.id.saveproductbt)
        productname = findViewById(R.id.productname)
        shortdescription = findViewById(R.id.shortdescription)
        description = findViewById(R.id.description)
        regularprice = findViewById(R.id.regularprice)
        sellprice = findViewById(R.id.sellprice)
        stockquantity = findViewById(R.id.stockquantity)


        utility = Utility()
        pd = ProgressDialog(this)
        pd!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        pd!!.window!!.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        pd!!.isIndeterminate = true
        pd!!.setCancelable(false)

        controller.FetchList("Bearer " + getStringVal(Constants.TOKEN))
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
            uploadfilename.text = path.toString()

        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            utility!!.relative_snackbar(
                addnewproduct!!,
                ImagePicker.getError(data),
                getString(R.string.close_up)
            )
        } else {
            utility!!.relative_snackbar(
                addnewproduct,
                "Task Cancelled",
                getString(R.string.close_up)
            )
        }
    }

    override fun onFetchListSuccess(fetchList: Response<FetchListResponse>) {
        pd.dismiss()
        if (fetchList.isSuccessful)
        {
            if (fetchList.body()?.getCode()?.equals("200")!!)
            {

                categories = ArrayList()
                category = ArrayList()
                categories =
                    fetchList.body()
                        ?.getData()?.categories as ArrayList<FetchListResponse.Data.Category>


                for (i in 0 until categories.size) {
                    val cat = categories.get(i)
                    category.add(cat.name.toString())
                }


              val adapter = ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item, category)
                categoryspinner.adapter = adapter
                categoryspinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        parent: AdapterView<*>?,
                        view: View?,
                        position: Int,
                        id: Long
                    ) {

                    }

                    override fun onNothingSelected(parent: AdapterView<*>?) {

                    }

                }

                brandsname = ArrayList()
                brands =
                    fetchList.body()
                        ?.getData()?.brands as ArrayList<FetchListResponse.Data.Brand>

                for (i in 0 until brands.size) {
                    val brand = brands.get(i)
                    brandsname.add(brand.name.toString())
                }

                val adapter1 = ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item, brandsname)
                brandspinner.adapter = adapter1
                brandspinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        parent: AdapterView<*>?,
                        view: View?,
                        position: Int,
                        id: Long
                    ) {

                    }

                    override fun onNothingSelected(parent: AdapterView<*>?) {

                    }

                }


            } else {
                utility!!.relative_snackbar(
                    addnewproduct,
                    fetchList.message(),
                    getString(R.string.close_up)
                )
            }
        } else {
            utility!!.relative_snackbar(
                addnewproduct,
                fetchList.message(),
                getString(R.string.close_up)
            )
        }
    }

    override fun error(error: String?) {
        pd.dismiss()
        utility!!.relative_snackbar(
            addnewproduct,
            error,
            getString(R.string.close_up)
        )
    }
}