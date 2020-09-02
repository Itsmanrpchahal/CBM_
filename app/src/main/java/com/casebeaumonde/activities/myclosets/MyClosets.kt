package com.casebeaumonde.activities.myclosets

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.app.Dialog
import android.app.ProgressDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.Window
import android.view.WindowManager
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.casebeaumonde.Controller.Controller
import com.casebeaumonde.R
import com.casebeaumonde.activities.myclosets.adapter.MyClosetsAdapter
import com.casebeaumonde.activities.myclosets.response.MyClosetsResponse
import com.casebeaumonde.constants.BaseClass
import com.casebeaumonde.constants.Constants
import com.casebeaumonde.createClosets.CreateClosetResponse
import com.casebeaumonde.utilities.Utility
import com.github.dhaval2404.imagepicker.ImagePicker
import com.livinglifetechway.quickpermissions_kotlin.runWithPermissions
import kotlinx.android.synthetic.main.activity_my_closets.*
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.createclosets.*
import kotlinx.android.synthetic.main.fragment_profile.*
import okhttp3.MultipartBody
import retrofit2.Response
import java.io.File

class MyClosets : BaseClass(), Controller.MyClosetsAPI, Controller.CreateClosetAPI {

    private lateinit var myclosets_back: ImageButton
    private lateinit var controller: Controller
    private lateinit var utility: Utility
    private lateinit var pd: ProgressDialog
    private lateinit var create_closets: ImageButton
    private lateinit var closets_recyler: RecyclerView
    private lateinit var part: MultipartBody.Part
    private lateinit var bitMap: Bitmap
    private var path: String = ""
    private lateinit var createcloset_title: EditText
    private lateinit var createcloset_checkbox: CheckBox
    private lateinit var createcloset_description: EditText
    private lateinit var createcloset_upload: Button
    private lateinit var createcloset_uploadfilename: TextView
    private lateinit var createcloset_imagerperview: ImageView
    private lateinit var createcloset_savebt: Button
    private lateinit var createcloset_cancelbt: Button
    private lateinit var dialog: Dialog
    private var checked: String = "0"
    var c: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_closets)
        findIds()
        controller = Controller()
        controller.Controller(this, this)

        lsiteners()

    }

    private fun lsiteners() {
        myclosets_back.setOnClickListener {
            onBackPressed()
        }

        create_closets.setOnClickListener {
            dialog = Dialog(this!!)
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog.setContentView(R.layout.createclosets)
            val window = dialog.window
            window?.setLayout(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT
            )
            dialog.show()

            createcloset_title = dialog.findViewById(R.id.createcloset_title)
            createcloset_checkbox = dialog.findViewById(R.id.createcloset_checkbox)
            createcloset_description = dialog.findViewById(R.id.createcloset_description)
            createcloset_upload = dialog.findViewById(R.id.createcloset_upload)
            createcloset_uploadfilename = dialog.findViewById(R.id.createcloset_uploadfilename)
            createcloset_imagerperview = dialog.findViewById(R.id.createcloset_imagerperview)
            createcloset_savebt = dialog.findViewById(R.id.createcloset_savebt)
            createcloset_cancelbt = dialog.findViewById(R.id.createcloset_cancelbt)

            createcloset_upload.setOnClickListener {
                if ((ContextCompat.checkSelfPermission(
                        this!!,
                        Manifest.permission.CAMERA
                    ) != PackageManager.PERMISSION_GRANTED) && (ContextCompat.checkSelfPermission(
                        this!!,
                        Manifest.permission.READ_EXTERNAL_STORAGE
                    ) != PackageManager.PERMISSION_GRANTED) && (ContextCompat.checkSelfPermission(
                        this!!,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                    ) != PackageManager.PERMISSION_GRANTED)
                ) {
                    methodRequiresPermissions()
                } else {
                    pictureSelectionDialog()
                }
            }

            createcloset_cancelbt.setOnClickListener {
                dialog.dismiss()
            }

            createcloset_savebt.setOnClickListener {

                checkValidations()
            }

        }
    }

    private fun checkValidations() {


        createcloset_checkbox.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                checked = "1"
            } else {
                checked = "0"
            }

        }

        when {
            createcloset_title.text.isEmpty() -> {
                createcloset_title.requestFocus()
                createcloset_title.error = "Closet Title"
            }

            createcloset_description.text.isEmpty() -> {
                createcloset_description.requestFocus()
                createcloset_description.error = "Closet Description"
            }

            createcloset_uploadfilename.text.equals("") -> {
                utility!!.relative_snackbar(
                    parent_myclosets!!,
                    "Upload Image",
                    getString(R.string.close_up)
                )
            } else -> {
                if (checked.equals("1")) {
                    c = "public"
                } else {
                    c = "private"
                }

                if (utility.isConnectingToInternet(this)) {
                    pd.show()
                    pd.setContentView(R.layout.loading)
                    createClosetAPI(
                        createcloset_title.text.toString(),
                        c,
                        createcloset_description.text.toString(),
                        part
                    )
                } else {
                    utility!!.relative_snackbar(
                        parent_myclosets,
                        R.string.nointernet.toString(),
                        getString(R.string.close_up)
                    )
                }


            }
        }


    }

    private fun createClosetAPI(title: String, c: String, decs: String, part: MultipartBody.Part) {

        controller.CreateCloset("Bearer " + getStringVal(Constants.TOKEN), title, c, part, decs)

    }

    override fun onResume() {
        if (utility.isConnectingToInternet(this)) {
            pd.show()
            pd.setContentView(R.layout.loading)
            controller.GetMyClosets(
                "Bearer " + getStringVal(Constants.TOKEN),
                getStringVal(Constants.USERID)
            )
        } else {
            utility!!.relative_snackbar(
                parent_myclosets,
                R.string.nointernet.toString(),
                getString(R.string.close_up)
            )
        }

        super.onResume()
    }

    private fun findIds() {
        utility = Utility()
        pd = ProgressDialog(this)
        pd!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        pd!!.window!!.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        pd!!.isIndeterminate = true
        pd!!.setCancelable(false)
        myclosets_back = findViewById(R.id.myclosets_back)
        create_closets = findViewById(R.id.create_closets)
        closets_recyler = findViewById(R.id.closets_recyler)
    }

    override fun onMyClosetsSuccess(myClosetsResponse: Response<MyClosetsResponse>) {
        pd.dismiss()

        if (myClosetsResponse.isSuccessful) {
            closets_recyler.layoutManager =
                LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
            val adapter = MyClosetsAdapter(this, myClosetsResponse.body()?.data?.closet!!)
            closets_recyler.adapter = adapter
        } else {
            utility!!.relative_snackbar(
                parent_myclosets,
                myClosetsResponse.message(),
                getString(R.string.close_up)
            )
        }
    }

    override fun onCreateClosetSuccess(createClosetResponse: Response<CreateClosetResponse>) {

        pd.dismiss()
        if (createClosetResponse.isSuccessful)
        {
            dialog.dismiss()
            controller.GetMyClosets(
                "Bearer " + getStringVal(Constants.TOKEN),
                getStringVal(Constants.USERID)
            )
            utility!!.relative_snackbar(
                parent_myclosets,
                createClosetResponse.message(),
                getString(R.string.close_up)
            )
        }
    }

    override fun error(error: String?) {
        pd.dismiss()
        utility!!.relative_snackbar(
            parent_myclosets,
            error,
            getString(R.string.close_up)
        )
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
            createcloset_uploadfilename.text = filePath
            bitMap = MediaStore.Images.Media.getBitmap(contentResolver, fileUri)
            part = Utility.sendImageFileToserver(filesDir, bitMap)

            Glide.with(this).load(bitMap).placeholder(R.drawable.login_banner)
                .into(createcloset_imagerperview)


        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            utility!!.relative_snackbar(
                parent_main!!,
                ImagePicker.getError(data),
                getString(R.string.close_up)
            )
        } else {
            utility!!.relative_snackbar(
                parent_main,
                "Task Cancelled",
                getString(R.string.close_up)
            )
        }
    }

    @SuppressLint("MissingPermission", "HardwareIds")
    private fun methodRequiresPermissions() = runWithPermissions(
        Manifest.permission.READ_PHONE_STATE,
        Manifest.permission.RECORD_AUDIO,
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.CAMERA

    ) {
        pictureSelectionDialog()

    }

}