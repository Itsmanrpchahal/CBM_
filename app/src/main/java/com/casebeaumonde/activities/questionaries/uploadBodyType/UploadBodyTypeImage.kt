package com.casebeaumonde.activities.questionaries.uploadBodyType

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
import androidx.appcompat.widget.AppCompatSpinner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.casebeaumonde.Controller.Controller
import com.casebeaumonde.R
import com.casebeaumonde.activities.questionaries.reponse.QuestionariesDataResponse
import com.casebeaumonde.activities.questionaries.selectbrands.SelectBrands
import com.casebeaumonde.activities.questionaries.uploadBodyType.adapter.AddImagesAdapter
import com.casebeaumonde.constants.BaseClass
import com.casebeaumonde.constants.Constants
import com.casebeaumonde.constants.Data
import com.casebeaumonde.utilities.Utility
import com.github.dhaval2404.imagepicker.ImagePicker
import kotlinx.android.synthetic.main.activity_tell_about_your_self.*
import kotlinx.android.synthetic.main.activity_upload_body_type_image.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import java.io.*
import java.net.URL
import java.net.URLConnection


class UploadBodyTypeImage : BaseClass()  , Controller.QuestionariesAPI {

    private lateinit var eye_color_spinner: AppCompatSpinner
    private lateinit var hair_spinner: AppCompatSpinner
    private lateinit var back: ImageButton
    private lateinit var upload_bt: Button
    private lateinit var continue_bt: LinearLayout
    private lateinit var utility: Utility
    private lateinit var images_recyclers: RecyclerView
    lateinit var dialog: Dialog
    private lateinit var pd : ProgressDialog
    private lateinit var controller: Controller
    private lateinit var height_et : EditText

    //store uris of picked images
    private lateinit var images: ArrayList<String?>
    private lateinit var adapter: AddImagesAdapter
    private lateinit var eyeColor : ArrayList<String>
    private lateinit var hairColor : ArrayList<String>
    private lateinit var brands : ArrayList<String>
    private lateinit var brandsID : ArrayList<String>
    private lateinit var data : ArrayList<Data>
    public  lateinit var brand : ArrayList<QuestionariesDataResponse.Brand>

    private var name: String = ""
    private var city: String = ""
    private var state: String = ""
    private var country: String = ""
    private var mobile: String = ""
    private var age: String = ""
    private var month: String = ""
    private var date: String = ""
    private var year: String = ""
    private var astrologicalSign: String = ""
    private var bodyType : String = ""
    private lateinit var colorscode : ArrayList<String>
    private var bodyType_text : String = ""
    private var selectedEyecolor : String ="-Select Eye Colour-"
    private var selectedhairColor : String = "-Select Hair Colour-"

     var part : ArrayList<MultipartBody.Part> = ArrayList()
    private lateinit var imageData :ArrayList<String>
    private lateinit var bitMap: Bitmap


    //current position/index of selected images
    private var position = 0

    //request code to pick image(s)
    private val PICK_IMAGES_CODE = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upload_body_type_image)

        //init list
        images = ArrayList()
        part = ArrayList()
        imageData = ArrayList()
        findIds()
        listeners()


//        val helper = ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
//            ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT,
//            ItemTouchHelper.DOWN or ItemTouchHelper.UP
//        ) {
//            override fun onMove(
//                recyclerView: RecyclerView,
//                dragged: RecyclerView.ViewHolder,
//                target: RecyclerView.ViewHolder
//            ): Boolean {
//                moveItem(dragged.adapterPosition, target.adapterPosition)
//                return true
//            }
//
//            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, i: Int) {
//                deleteItem(viewHolder.adapterPosition)
//            }
//        })
//
//        helper.attachToRecyclerView(images_recyclers)

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

    private fun listeners() {
        back.setOnClickListener { onBackPressed() }
        continue_bt.setOnClickListener {

            if (images.size == 0)
            {
                Toast.makeText(this, "Upload body type images", Toast.LENGTH_SHORT).show()
            } else if (selectedEyecolor.equals("-Select Eye Colour-"))
            {
                Toast.makeText(this, "Select Eye Colour", Toast.LENGTH_SHORT).show()
            } else if (selectedhairColor.equals("-Select Hair Colour-"))
            {
                Toast.makeText(this, "Select Hair Colour", Toast.LENGTH_SHORT).show()
            } else if (height_et.text.isEmpty())
            {
                height_et.requestFocus()
                height_et.setError("Enter Height")
            }
            else {
                startActivity(
                    Intent(this, SelectBrands::class.java).putExtra("name", name)
                        .putExtra("city", city).putExtra("state", state)
                        .putExtra("country", country).putExtra("mobile", mobile)
                        .putExtra("age", age).putExtra("month", month).putExtra("date", date)
                        .putExtra("year", year).putExtra("astrologicalSign", astrologicalSign)
                        .putStringArrayListExtra("colorscode", colorscode)
                        .putExtra("bodyType", bodyType)
                        .putExtra("bodyType_text", bodyType_text)
                        .putStringArrayListExtra("images", images)
                        .putExtra("height", height_et.text.toString())
                        .putExtra("eycolor", selectedEyecolor)
                        .putExtra("haircolor", selectedhairColor)
                        .putStringArrayListExtra("imageData",imageData)
                ) }
            }

        upload_bt.setOnClickListener {
            pictureSelectionDialog()
        }
    }

    private fun setHairColorSpinner(hairColor: ArrayList<String>) {
        val languages = resources.getStringArray(R.array.EyeColor)
        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_dropdown_item, hairColor
        )
        hair_spinner.adapter = adapter
        hair_spinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View, position: Int, id: Long
            ) {
                if (position != 0) {
                    selectedhairColor = parent.selectedItem.toString()
                }
                //  userType = languages[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // write code to perform some action
            }
        }
    }

    private fun setEyecolorSpinner(eyeColor: ArrayList<String>) {
        val languages = resources.getStringArray(R.array.EyeColor)
        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_dropdown_item, eyeColor
        )
        eye_color_spinner.adapter = adapter
        eye_color_spinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View, position: Int, id: Long
            ) {
                if (position != 0) {
                   selectedEyecolor = parent.selectedItem.toString()
                }
                //  userType = languages[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // write code to perform some action
            }
        }
    }

    private fun findIds() {
        eye_color_spinner = findViewById(R.id.eye_color_spinner)
        hair_spinner = findViewById(R.id.hair_spinner)
        continue_bt = findViewById(R.id.continue_bt)
        back = findViewById(R.id.back)
        upload_bt = findViewById(R.id.upload_bt)
        images_recyclers = findViewById(R.id.images_recyclers)
        height_et = findViewById(R.id.height_et)
        utility = Utility()
        controller = Controller()
        controller.Controller(this)
        pd = ProgressDialog(this)
        pd!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        pd!!.window!!.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        pd!!.isIndeterminate = true
        pd!!.setCancelable(false)
        if (utility.isConnectingToInternet(this))
        {
            pd.show()
            pd.setContentView(R.layout.loading)
            controller.Questionaries("Bearer " + getStringVal(Constants.TOKEN))
        } else {
            utility.relative_snackbar(
                parent_uploadbody!!,
                "No Internet Connectivity",
                getString(R.string.close_up)
            )
        }

        name = intent.getStringExtra("name").toString()
        city = intent.getStringExtra("city").toString()
        state = intent.getStringExtra("state").toString()
        country = intent.getStringExtra("country").toString()
        mobile = intent.getStringExtra("mobile").toString()
        age = intent.getStringExtra("age").toString()
        month = intent.getStringExtra("month").toString()
        date = intent.getStringExtra("date").toString()
        year = intent.getStringExtra("year").toString()
        astrologicalSign = intent.getStringExtra("astrologicalSign").toString()
        colorscode = intent.getStringArrayListExtra("colorscode")!!
        bodyType = intent.getStringExtra("bodyType").toString()
        bodyType_text = intent.getStringExtra("bodyType_text").toString()


    }

    private fun pickImagesIntent() {
        val intent = Intent()
        intent.type = "image/*"
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "Select Image(s)"), PICK_IMAGES_CODE)
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
            pickImagesIntent()
        }
        dialog.show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == PICK_IMAGES_CODE) {

            if (resultCode == Activity.RESULT_OK) {

                if (data!!.clipData != null) {
                    //picked multiple images
                    //get number of picked images
                    val count = data.clipData!!.itemCount
                    for (i in 0 until count) {
                        val imageUri = data.clipData!!.getItemAt(i).uri
                        //add image to list

                       // Toast.makeText(this,""+bitmap,Toast.LENGTH_SHORT).show()
                        images!!.add(imageUri.toString())
                        Log.d("IMAGES", "" + imageUri)
                        dialog.dismiss()
                        setAdapterData(images)
                    }
                    bitMap = MediaStore.Images.Media.getBitmap(this.contentResolver, data.data)
                    part.add(sendImageFileToserver(bitMap)!!)
                    imageData.add(data.data.toString())

                    Log.d("bitmap", "" + part)
                    //set first image from list to image switcher
                    position = 0;
                } else {
                    //picked single image
                    val imageUri = data.data
                    //set image to image switcher
                    bitMap = MediaStore.Images.Media.getBitmap(this.contentResolver, data.data)
                    part.add(sendImageFileToserver(bitMap)!!)
                    Log.d("bitmap", "" + part)
                    Log.d("IMAGES", "" + imageUri)
                    imageData.add(data.data.toString())
                    dialog.dismiss()
                    setAdapterData(images)
                    position = 0;
                }

            }

        } else if (resultCode == Activity.RESULT_OK) {
            //Image Uri will not be null for RESULT_OK
            val fileUri = data?.data

            //You can get File object from intent
            var file: File? = ImagePicker.getFile(data)
            //You can also get File Path from intent
            val filePath: String? = ImagePicker.getFilePath(data)
            images.add(filePath)

            bitMap = MediaStore.Images.Media.getBitmap(this.contentResolver, data?.data)

            part.add(sendImageFileToserver(bitMap)!!)
            imageData.add(data?.data.toString())
            Log.d("part", "" + part)

            setAdapterData(images)
//             path = filePath!!
//              bitMap = MediaStore.Images.Media.getBitmap(context?.contentResolver, fileUri)
//              part = sendImageFileToserver(context?.filesDir, bitMap, "image")

        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            utility!!.relative_snackbar(
                parent_uploadbody!!,
                ImagePicker.getError(data),
                getString(R.string.close_up)
            )
        } else {
            utility!!.relative_snackbar(
                parent_uploadbody,
                "Task Cancelled",
                getString(R.string.close_up)
            )
        }
    }


    @Throws(FileNotFoundException::class, IOException::class)
    fun getBitmap(cr: ContentResolver, url: Uri?): Bitmap {
        val input = cr.openInputStream(url!!)
        val bitmap = BitmapFactory.decodeStream(input)
        input!!.close()
        return bitmap
    }

    private fun setAdapterData(images: ArrayList<String?>) {
        images_recyclers.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        adapter = AddImagesAdapter(this, images)
        images_recyclers.adapter = adapter
    }

    override fun onQuestionariesSuccess(questionaries: Response<QuestionariesDataResponse>) {
        pd.dismiss()
        if (questionaries.isSuccessful)
        {
            eyeColor = ArrayList()
            eyeColor.add("-Select Eye Colour-")
            for (i in 0 until questionaries.body()?.data?.customer?.eyeColor?.size!!) {
                val title = questionaries.body()?.data?.customer?.eyeColor?.get(i)
                eyeColor.add(title.toString())
            }
            setEyecolorSpinner(eyeColor)

            hairColor = ArrayList()
            hairColor.add("-Select Hair Colour-")
            for (i in 0 until questionaries.body()?.data?.customer?.hairColor?.size!!) {
                val title = questionaries.body()?.data?.customer?.hairColor?.get(i)
                hairColor.add(title.toString())
            }
            setHairColorSpinner(hairColor)


            brands = ArrayList()
            brandsID = ArrayList()
            brand = ArrayList()
            data = ArrayList()
            for (i in 0 until questionaries.body()?.data?.customer!!.brand?.size!!) {
                val title =  questionaries.body()?.data?.customer!!.brand?.get(i)
                brands.add(title!!.name.toString())
                brandsID.add(title!!.id.toString())
                val brand = Data()
                brand.title = brands.get(i)
                brand.id = brandsID.get(i)
                data.add(brand)
            }

        } else {
            utility!!.relative_snackbar(
                parent_uploadbody,
                questionaries.message(),
                getString(R.string.close_up)
            )
        }
    }

    override fun error(error: String?) {
        pd.dismiss()
        utility!!.relative_snackbar(
            parent_uploadbody,
            error,
            getString(R.string.close_up)
        )
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
            "photo",
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

}