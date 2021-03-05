package com.casebeaumonde.activities.questionaries.uploadBodyType

import android.app.Activity
import android.app.Dialog
import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.view.WindowManager
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatSpinner
import androidx.recyclerview.widget.ItemTouchHelper
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
import retrofit2.Response
import java.io.File


class UploadBodyTypeImage : BaseClass() , Controller.QuestionariesAPI {

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

    //store uris of picked images
    private lateinit var images: ArrayList<String?>
    private lateinit var adapter: AddImagesAdapter
    private lateinit var eyeColor : ArrayList<String>
    private lateinit var hairColor : ArrayList<String>
    private lateinit var brands : ArrayList<String>
    private lateinit var brandsID : ArrayList<String>
    private lateinit var data : ArrayList<Data>
    public  lateinit var brand : ArrayList<QuestionariesDataResponse.Brand>

    //current position/index of selected images
    private var position = 0

    //request code to pick image(s)
    private val PICK_IMAGES_CODE = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upload_body_type_image)

        //init list
        images = ArrayList()

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

            startActivity( Intent(this, SelectBrands::class.java)) }
        upload_bt.setOnClickListener { pictureSelectionDialog() }
    }

    private fun setHairColorSpinner(hairColor : ArrayList<String>) {
        val languages = resources.getStringArray(R.array.EyeColor)
        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_dropdown_item, hairColor
        )
        hair_spinner.adapter = adapter
        /*hair_spinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View, position: Int, id: Long
            ) {
                if (position != 0) {
                    // spinnertitle.setText(languages[position])
                }
                //  userType = languages[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // write code to perform some action
            }
        }*/
    }

    private fun setEyecolorSpinner(eyeColor : ArrayList<String>) {
        val languages = resources.getStringArray(R.array.EyeColor)
        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_dropdown_item, eyeColor
        )
        eye_color_spinner.adapter = adapter
//        eye_color_spinner.onItemSelectedListener = object :
//            AdapterView.OnItemSelectedListener {
//            override fun onItemSelected(
//                parent: AdapterView<*>,
//                view: View, position: Int, id: Long
//            ) {
//                /*if (position != 0) {
//                    // spinnertitle.setText(languages[position])
//                }*/
//                //  userType = languages[position]
//            }
//
//            override fun onNothingSelected(parent: AdapterView<*>) {
//                // write code to perform some action
//            }
//        }
    }

    private fun findIds() {
        eye_color_spinner = findViewById(R.id.eye_color_spinner)
        hair_spinner = findViewById(R.id.hair_spinner)
        continue_bt = findViewById(R.id.continue_bt)
        back = findViewById(R.id.back)
        upload_bt = findViewById(R.id.upload_bt)
        images_recyclers = findViewById(R.id.images_recyclers)
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
            controller.Questionaries("Bearer "+getStringVal(Constants.TOKEN))
        } else {
            utility.relative_snackbar(
                parent_uploadbody!!,
                "No Internet Connectivity",
                getString(R.string.close_up)
            )
        }
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
                        images!!.add(imageUri.toString())
                        Log.d("IMAGES", "" + imageUri)
                        dialog.dismiss()
                        setAdapterData(images)
                    }
                    //set first image from list to image switcher
                    position = 0;
                } else {
                    //picked single image
                    val imageUri = data.data
                    //set image to image switcher
                    images!!.add(imageUri.toString())
                    Log.d("IMAGES", "" + imageUri)
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
            setAdapterData(images)
            // path = filePath!!
            //  bitMap = MediaStore.Images.Media.getBitmap(context?.contentResolver, fileUri)
            //  part = Utility.sendImageFileToserver(context?.filesDir, bitMap, "image")

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
}