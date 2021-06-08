package com.casebeaumonde.fragments.productManagement.addproduct

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Dialog
import android.app.ProgressDialog
import android.content.ContentResolver
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.casebeaumonde.Controller.Controller
import com.casebeaumonde.R
import com.casebeaumonde.activities.myclosets.response.FetchListResponse
import com.casebeaumonde.activities.questionaries.uploadBodyType.adapter.AddImagesAdapter
import com.casebeaumonde.constants.BaseClass
import com.casebeaumonde.constants.Constants
import com.casebeaumonde.fragments.productManagement.IF.getImagesIF
import com.casebeaumonde.fragments.productManagement.addproduct.reponse.AddProductResponse
import com.casebeaumonde.fragments.productManagement.addproduct.reponse.ProductDetailResponse
import com.casebeaumonde.fragments.productManagement.addproduct.reponse.UpdateProductResponse
import com.casebeaumonde.fragments.productManagement.response.ProductListResponse
import com.casebeaumonde.utilities.Utility
import com.github.dhaval2404.imagepicker.ImagePicker
import kotlinx.android.synthetic.main.activity_add_new_product.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import java.io.*
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLConnection


class AddNewProduct : BaseClass(),Controller.FetchListAPI ,
    Controller.AddProductAPI,Controller.ProductListAPI,
    Controller.ProductDetailAPI,getImagesIF,Controller.UpdateProductAPI{

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
    private lateinit var images_recyclers: RecyclerView
    private var path: String = ""
    private lateinit var categories : ArrayList<FetchListResponse.Data.Category>
    private lateinit var category : ArrayList<String>
    private lateinit var brands : ArrayList<FetchListResponse.Data.Brand>
    private lateinit var brandsname :ArrayList<String>
    private  var catPos : Int = 0
    private  var brandPos : Int = 0
    private lateinit var saveproductbt : Button
    private lateinit var productname:EditText
    private lateinit var shortdescription:EditText
    private lateinit var description:EditText
    private lateinit var regularprice:EditText
    private lateinit var sellprice:EditText
    private lateinit var stockquantity:EditText
    var partlist : ArrayList<MultipartBody.Part> = ArrayList()
    var image_uris : ArrayList<String> = ArrayList()
    private lateinit var imageData :ArrayList<String>
    private lateinit var images: ArrayList<String?>
    private lateinit var dialog:Dialog
    private lateinit var adapter:AddImagesAdapter
    //current position/index of selected images
    private var position = 0
    private  var cateID:String=""
    private  var brandID : String=""
    private  var productType:String="1"
    private lateinit var type:String
    private lateinit var id:String
    private lateinit var pos:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new_product)
        images = ArrayList()
        partlist = ArrayList()
        imageData = ArrayList()
        type = intent.getStringExtra("type").toString()
        if (type.equals("edit"))
        {
            id = intent.getStringExtra("id").toString()
            pos = intent.getStringExtra("pos").toString()
        }
        getImagesIF = this
        findIds()
        lisenters()
    }

    fun moveItem(oldPos: Int, newPos: Int) {
        val image: String = images.get(oldPos)!!
        images.removeAt(oldPos)
        images.add(newPos, image)
        // adapter.notifyItemMoved(oldPos, newPos)
    }

    fun deleteItem(position: Int) {
        images.removeAt(position)
        // part.remove(position)
        // adapter.notifyItemRemoved(position)
    }


    @SuppressLint("ResourceAsColor")
    private fun lisenters() {
        backon_additemstocloset.setOnClickListener {
            onBackPressed()
        }

        simpleproduct.setOnClickListener {
            simpleproduct.setBackgroundResource(R.drawable.whiteborder)
            variableproduct.setBackgroundResource(R.drawable.greyborder)
            productType = "1"

        }

        variableproduct.setOnClickListener {
            simpleproduct.setBackgroundResource(R.drawable.greyborder)
            variableproduct.setBackgroundResource(R.drawable.whiteborder)
            productType ="2"
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
                    if (images.size==0)
                    {
                        utility!!.relative_snackbar(
                            addnewproduct,
                            "Upload Image",
                            getString(R.string.close_up)
                        )
                    }else {

                        Log.d("testProduct",productname.text.toString()+"\n"+
                                shortdescription.text.toString()+"\n"+
                                description.text.toString()+"\n"+
//                        brandID+"\n"+
//                        cateID+"\n"+
                        partlist.toString()+"\n"+
                        productType+"\n"+
                        regularprice.text.toString()+"\n"+
                        sellprice.text.toString()+"\n"+
                        stockquantity.text.toString())

                        pd.show()
                        if (type.equals("edit"))
                        {
                            controller.UpdateProduct("Bearer "+getStringVal(Constants.TOKEN),
                                id,productname.text.toString(),
                                shortdescription.text.toString(),
                                description.text.toString(),
                                cateID,
                                brandID,
                                productType,
                                regularprice.text.toString(),
                                sellprice.text.toString(),
                                stockquantity.text.toString(),
                                partlist,"put")
                        }
                        else {
                            controller.AddProduct("Bearer "+getStringVal(Constants.TOKEN),
                                productname.text.toString(),
                                shortdescription.text.toString(),
                                description.text.toString(),
                                cateID,
                                brandID,
                                productType,
                                regularprice.text.toString(),
                                sellprice.text.toString(),
                                stockquantity.text.toString(),
                                partlist)
                        }

                    }
                }
            }
        }
    }

    private fun findIds() {
        controller = Controller()
        controller.Controller(this ,this,this,this,this)
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
        images_recyclers = findViewById(R.id.images_recyclers)


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
         dialog = Dialog(this!!)
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
        //partlist = ArrayList()
        if (requestCode == 121) {

//            if (resultCode == Activity.RESULT_OK) {

//                if (data!!.clipData != null) {
                    //picked multiple images
                    //get number of picked images
//                    val count = data?.clipData!!.itemCount
//                    for (i in 0 until count) {
//                        val imageUri = data.clipData!!.getItemAt(i).uri
//                        //add image to list
//
//                        // Toast.makeText(this,""+bitmap,Toast.LENGTH_SHORT).show()
//                        images!!.add(imageUri.toString())
//                        Log.d("IMAGES", "" + imageUri)
//                        uploadfilename.text = imageUri.toString()
//                        dialog.dismiss()
//                        setAdapterData(images)
//                    }
//                    bitMap = MediaStore.Images.Media.getBitmap(this.contentResolver, data.data)
//                    partlist.add(sendImageFileToserver(bitMap)!!)
//                    imageData.add(data.data.toString())
//
//                    Log.d("bitmap", "" + part)
//                    //set first image from list to image switcher
//                    position = 0;
//                }
//                else {
//                    //picked single image
//                    val imageUri = data?.data
//                    //set image to image switcher
//                    bitMap = MediaStore.Images.Media.getBitmap(this.contentResolver, data?.data)
//                    partlist.add(sendImageFileToserver(bitMap)!!)
//                    Log.d("bitmap1", "" + partlist)
//                    Log.d("IMAGES", "" + imageUri)
//                    uploadfilename.text = imageUri.toString()
//                    images.add(imageUri.toString())
//                    imageData.add(data?.data.toString())
//                    dialog.dismiss()
//                    setAdapterData(images)
//                    position = 0;
//                }

            }

//        }
//        else
            if (resultCode == Activity.RESULT_OK) {
            //Image Uri will not be null for RESULT_OK
            val fileUri = data?.data

            //You can get File object from intent
            var file: File? = ImagePicker.getFile(data)
            //You can also get File Path from intent
            val filePath: String? = ImagePicker.getFilePath(data)
            images.add(filePath)

            bitMap = MediaStore.Images.Media.getBitmap(this.contentResolver, data?.data)

            partlist.add(sendImageFileToserver(bitMap)!!)
            imageData.add(data?.data.toString())
            Log.d("part1", "" + partlist)

            setAdapterData(images)
//             path = filePath!!
//              bitMap = MediaStore.Images.Media.getBitmap(context?.contentResolver, fileUri)
//              part = sendImageFileToserver(context?.filesDir, bitMap, "image")

        }
        else if (resultCode == ImagePicker.RESULT_ERROR) {
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

    private fun setAdapterData(images: ArrayList<String?>) {
        images_recyclers.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        adapter = AddImagesAdapter(this, images,type)
        images_recyclers.adapter = adapter
    }

    @Throws(FileNotFoundException::class, IOException::class)
    fun getBitmap(cr: ContentResolver, url: Uri?): Bitmap {
        val input = cr.openInputStream(url!!)
        val bitmap = BitmapFactory.decodeStream(input)
        input!!.close()
        return bitmap
    }

    @Throws(IOException::class)
    fun sendImageFileToserver(bitMap: Bitmap): MultipartBody.Part? {
        val filesDir = applicationContext.filesDir
        val file = File(filesDir, "image" + ".png" + System.currentTimeMillis())
        val bos = ByteArrayOutputStream()
        bitMap.compress(Bitmap.CompressFormat.PNG, 50, bos)
        val bitmapdata: ByteArray = bos.toByteArray()
        val fos = FileOutputStream(file)
        fos.write(bitmapdata)
        fos.flush()
        fos.close()
        val requestFile: RequestBody =
            RequestBody.create("multipart/form-data".toMediaTypeOrNull(), saveBitmapToFile(file)!!)
        return MultipartBody.Part.createFormData(
            "product_images[]",
            saveBitmapToFile(file)?.getName(),
            requestFile
        )
    }

    fun saveBitmapToFile(file: File): File? {
        return try {

            // BitmapFactory options to downsize the image
            val o = BitmapFactory.Options()
            o.inJustDecodeBounds = true
            o.inSampleSize = 6
            // factor of downsizing the image
            var inputStream = FileInputStream(file)
            //Bitmap selectedBitmap = null;
            BitmapFactory.decodeStream(inputStream, null, o)
            inputStream.close()

            // The new size we want to scale to
            val REQUIRED_SIZE = 50

            // Find the correct scale value. It should be the power of 2.
            var scale = 1
            while (o.outWidth / scale / 2 >= REQUIRED_SIZE &&
                o.outHeight / scale / 2 >= REQUIRED_SIZE
            ) {
                scale *= 2
            }
            val o2 = BitmapFactory.Options()
            o2.inSampleSize = scale
            inputStream = FileInputStream(file)
            val selectedBitmap = BitmapFactory.decodeStream(inputStream, null, o2)
            inputStream.close()

            // here i override the original image file
            file.createNewFile()
            val outputStream = FileOutputStream(file)
            selectedBitmap!!.compress(Bitmap.CompressFormat.JPEG, 50, outputStream)
            file
        } catch (e: Exception) {
            null
        }
    }

    fun loadBitmap(url: String?): Bitmap? {
        var bm: Bitmap? = null
        var `is`: InputStream? = null
        var bis: BufferedInputStream? = null
        try {
            val conn: URLConnection = URL(url).openConnection()
            conn.connect()
            `is` = conn.getInputStream()
            bis = BufferedInputStream(`is`, 8192)
            bm = BitmapFactory.decodeStream(bis)
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        } finally {
            if (bis != null) {
                try {
                    bis.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
            if (`is` != null) {
                try {
                    `is`.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        }
        return bm
    }


    override fun onFetchListSuccess(fetchList: Response<FetchListResponse>) {
        if (type.equals("edit"))
        {
            pd.show()
            controller.ProductDetail("Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6ImZkOTQ2N2I5YTVkOTc2NjEwMzFjNzlkYjQ3NmY1YWQyNWFjZGYxNjZjODBjNjExMTE1ZTk1NDAyMTJkZThjYzVjY2IxYmJkNjhmZjllMWRhIn0.eyJhdWQiOiIxIiwianRpIjoiZmQ5NDY3YjlhNWQ5NzY2MTAzMWM3OWRiNDc2ZjVhZDI1YWNkZjE2NmM4MGM2MTExMTVlOTU0MDIxMmRlOGNjNWNjYjFiYmQ2OGZmOWUxZGEiLCJpYXQiOjE2MjI3ODE0MzUsIm5iZiI6MTYyMjc4MTQzNSwiZXhwIjoxNjU0MzE3NDM1LCJzdWIiOiIxMTEiLCJzY29wZXMiOltdfQ.e9RrUdPPTXJ9amldSLut1DUhEDXGK9TDmwSv9gqJM-BDveob_aLqH_cW3p0j1JxRUsHhbND1U5QXf22qeTDWe74DLQLEXVqdFTlA9G9AdOR5DjOem3LxQnb7yrGUz8JNPn5ntruUB_WyNQs6BGYJ1REC6ADRN4E1W4ZLJbNotbvaC41MQJ526UWFVB825MeMQoGSKo-DVHtShfqKXzMKA6tYz7T4wdy_1yBJWmnYlAyAY-qAgdIDvpKvByEJ2A0XStqiht3ptRsnJXKcQmlV0yieU25siQz1XPLXlebfr4OjiYZAaQSv7MQNtRNwc32gWvmNpZpnIgtu_n01mtJuXn8cYSSx66r3TTCP1plFaLL30iXAz2x-NRcAcn37ekSBmsNc_EZNSQypWVWsgAoDA-2Um5VT-IJvCsQUglzT-QThNBXFtBcBWzVLD2mLEpFSryq3sK5jT7Klx71ehY6MekPOmJJr6w4kznEsD0Nmq923UsZ_0eJvvXdxL5wjZYjuK1xkX1QClsukE2XHMPL0RrUoabyglzMKLmdZioGHtV2SlNOUYob0I3mTPhR8vj-riRFEKBAB1aXfWq9B7BAQ7i4mptNz8qA4VcEBq2y3FSxH0xkbT2kQf7QBKiLVWz-wvTKP5ucDpLp1DqAZKz4Yrvjo5enaraGHtpvJuqVBZu8",id)
        }else {
            pd.dismiss()
        }
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

                if (categories.size > 0) {

                    for (i in categories.indices) {
                        val catpos = categories[i]

                        if (catpos.id.toString().equals(cateID)) {
                            catPos = i
                        }
                    }
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
                        Log.d("catID2","HERE"+catPos)
                        if (catPos == 0) {
                            //additemclosets_color.setText(color.get(position).name)
                            //additemclosets_Colorpinner.setSelection(colorPos)
                            cateID = categories.get(position).name.toString()
                            Log.d("catID",cateID+"   "+catPos)
                        } else {

                            //additemclosets_color.setText(color.get(colorPos).name)
                            categoryspinner.setSelection(catPos)
                            cateID = categories.get(catPos).name.toString()
                            catPos = position
                            Log.d("catID1",cateID+"   "+catPos)
                            //Toast.makeText(this@AddItemToEvent,""+position.toString(),Toast.LENGTH_SHORT).show()

                        }
                        //cateID = categories.get(position).id.toString()
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

                if (brands.size > 0) {

                    for (i in brands.indices) {
                        val catpos = brands[i]

                        if (catpos.id.toString().equals(brandID)) {
                            brandPos = i
                        }
                    }
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
                        if (brandPos == 0) {
                            //additemclosets_color.setText(color.get(position).name)
                            //additemclosets_Colorpinner.setSelection(colorPos)
                            brandID = brands.get(position).name.toString()
                        } else {

                            //additemclosets_color.setText(color.get(colorPos).name)
                            categoryspinner.setSelection(catPos)
                            brandID = brands.get(brandPos).name.toString()
                            brandPos = position
                            //Toast.makeText(this@AddItemToEvent,""+position.toString(),Toast.LENGTH_SHORT).show()

                        }
                            // brandID = brands.get(position).id.toString()
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

    override fun onAddProductSuccess(success: Response<AddProductResponse>) {
        pd.dismiss()
        if (success.isSuccessful)
        {
            if (success.body()?.code?.equals("200")!!)
            {
                onBackPressed()
                utility!!.relative_snackbar(
                    addnewproduct,
                    success.body()?.message,
                    getString(R.string.close_up)
                )
            } else {
                utility!!.relative_snackbar(
                    addnewproduct,
                    success.message(),
                    getString(R.string.close_up)
                )
            }

        } else {
            utility!!.relative_snackbar(
                addnewproduct,
                success.message(),
                getString(R.string.close_up)
            )
        }

    }

    override fun onProductListSuccess(success: Response<ProductListResponse>) {
        pd.dismiss()
        if (success.isSuccessful)
        {
            if (success.body()?.code?.equals("200")!!)
            {

            } else {
                utility!!.relative_snackbar(
                    addnewproduct,
                    success.message(),
                    getString(R.string.close_up)
                )
            }
        } else {
            utility!!.relative_snackbar(
                addnewproduct,
                success.message(),
                getString(R.string.close_up)
            )
        }
    }

    override fun onProductDetailSuccess(success: Response<ProductDetailResponse>) {
        pd.dismiss()
        if (success.isSuccessful)
        {
            if (success.body()?.code?.equals("200")!!)
            {
                productname.setText(success.body()?.data?.products?.productName?.toString())
                shortdescription.setText(success.body()?.data?.products?.shortDescription?.toString())
                description.setText(success.body()?.data?.products?.description?.toString())
                regularprice.setText(success.body()?.data?.products?.regularPrice.toString())
                sellprice.setText(success.body()?.data?.products?.sellPrice.toString())
                stockquantity.setText(success.body()?.data?.products?.stockQuantity.toString())

                cateID = success.body()?.data?.products?.categoryId.toString()
                brandID = success.body()?.data?.products?.brandId.toString()



                if (success.body()?.data?.products?.productType==1)
                {
                    simpleproduct.setBackgroundResource(R.drawable.whiteborder)
                    variableproduct.setBackgroundResource(R.drawable.greyborder)
                    productType = "1"
                } else {
                    simpleproduct.setBackgroundResource(R.drawable.greyborder)
                    variableproduct.setBackgroundResource(R.drawable.whiteborder)
                    productType ="2"
                }
                images.clear()
                for (i in 0 until success.body()!!.data?.products?.productImages?.size!!) {
                    images.add(
                        success.body()!!.data?.products?.productImages?.get(i)?.image
                    )
                    setAdapterData(images)
                    Log.d("images",""+images)
                    for (i in 0 until images.size)
                    {
                        //getBitmapFromURL(images.get(i))
                       // partlist.add(getBitmapFromURL(images.get(i)))
                    }
                    //partlist.add(sendImageFileToserver(bitMap)!!)
                    //imageData.add(data.data.toString())
                   // Log.d("IMAGE", image_uris.get(0))
                }

            } else {
                utility!!.relative_snackbar(
                    addnewproduct,
                    success.message(),
                    getString(R.string.close_up)
                )
            }
        } else {
            utility!!.relative_snackbar(
                addnewproduct,
                success.message(),
                getString(R.string.close_up)
            )
        }
    }

    fun getBitmapFromURL(src: String?): Bitmap? {
        return try {
            val url = URL(src)
            val connection: HttpURLConnection = url.openConnection() as HttpURLConnection
            connection.setDoInput(true)
            connection.connect()
            val input: InputStream = connection.getInputStream()
            BitmapFactory.decodeStream(input)
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }
    }

    override fun onUpdateProductSuccess(success: Response<UpdateProductResponse>) {
        pd.dismiss()
        onBackPressed()
        if (success.isSuccessful)
        {
            if (success.body()?.code?.equals("200")!!)
            {
                onBackPressed()
            } else {
                utility!!.relative_snackbar(
                    addnewproduct,
                    success.message(),
                    getString(R.string.close_up)
                )
            }

        } else {
            utility!!.relative_snackbar(
                addnewproduct,
                success.message(),
                getString(R.string.close_up)
            )
        }
        Log.d("success",""+success.body()?.code)
    }

    override fun error(error: String?) {
        pd.dismiss()
        if (type.equals("edit"))
        {
            pd.show()
            controller.ProductDetail("Bearer "+getStringVal(Constants.TOKEN),id)
        }else {
            pd.dismiss()
        }
        utility!!.relative_snackbar(
            addnewproduct,
            error,
            getString(R.string.close_up)
        )
    }

    companion object {
        var getImagesIF:getImagesIF?=null
    }


    override fun getImages(part: ArrayList<MultipartBody.Part>) {
        //partlist.addAll(part)
        Log.d("part",""+partlist.size)
    }
}