package com.casebeaumonde.activities.businessRegister

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.app.Dialog
import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Patterns
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.appcompat.widget.AppCompatSpinner
import androidx.core.content.ContextCompat
import com.casebeaumonde.R
import com.casebeaumonde.activities.login.LoginActivity
import com.casebeaumonde.activities.register.userRegister.RegisterActivity
import com.casebeaumonde.utilities.Utility
import com.github.dhaval2404.imagepicker.ImagePicker
import com.livinglifetechway.quickpermissions_kotlin.runWithPermissions
import kotlinx.android.synthetic.main.activity_business_register.*
import kotlinx.android.synthetic.main.activity_register.*
import okhttp3.MultipartBody
import java.io.File

class BusinessRegisterActivity : AppCompatActivity() {

    private lateinit var business_team_spinner: AppCompatSpinner
    private lateinit var spinnertitle: TextView
    private lateinit var register_forbusiness: TextView
    private lateinit var bregister_companyname: EditText
    private lateinit var bregister_firstname: EditText
    private lateinit var bregister_lastname: EditText
    private lateinit var bregister_email: EditText
    private lateinit var bregister_password: EditText
    private lateinit var bregister_cpassword: EditText
    private lateinit var bregister_phone: EditText
    private lateinit var business_agreecheck: CheckBox
    private lateinit var bregister_upload: Button
    private lateinit var buploadfilename: TextView
    private lateinit var bregister_agreecheck: CheckBox
    private lateinit var bregister_regiter_bt: Button
    private lateinit var register_login: TextView
    private lateinit var utility: Utility
    private lateinit var userType: String
    private var path: String = ""
    private var checked: String = "0"
    private lateinit var pd: ProgressDialog
    private lateinit var part: MultipartBody.Part
    private lateinit var bitMap: Bitmap
    var c: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_business_register)

        findIds()
        setSpinnerData()
        listeners()


    }

    private fun listeners() {
        register_login.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    LoginActivity::class.java
                )
            )
            finish()
        }

        register_forbusiness.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    RegisterActivity::class.java
                )
            )
            finish()
        }

        bregister_upload.setOnClickListener {
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

        bregister_regiter_bt.setOnClickListener {
            checkValidations()
        }


        bregister_agreecheck.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                checked = "1"
            } else {
                checked = "0"
            }
        }

    }

    private fun checkValidations() {
        when {
            userType.equals("-Select Team-") -> {
                utility!!.relative_snackbar(
                    bregister_parent!!,
                    "Select team",
                    getString(R.string.close_up)
                )
            }

            bregister_companyname.text.isEmpty() -> {
                bregister_companyname.requestFocus()
                bregister_companyname.error = getString(R.string.entercomapanyname)
            }

            bregister_firstname.text.isEmpty() -> {
                bregister_firstname.requestFocus()
                bregister_firstname.error = getString(R.string.enterfirstname)
            }

            bregister_lastname.text.isEmpty() -> {
                bregister_lastname.requestFocus()
                bregister_lastname.error = getString(R.string.enterlastname)
            }

            bregister_email.text.isEmpty() -> {
                bregister_email.requestFocus()
                bregister_email.error = getString(R.string.enteremail)
            }

            !Patterns.EMAIL_ADDRESS.matcher(bregister_email.text).matches() -> {
                bregister_email.requestFocus()
                bregister_email.error = "Enter Valid Email"
            }

            bregister_password.text.isEmpty() -> {
                bregister_password.requestFocus()
                bregister_password.error = getString(R.string.enterpassword)
            }

            bregister_cpassword.text.isEmpty() -> {
                bregister_cpassword.requestFocus()
                bregister_cpassword.error = getString(R.string.confirmpassword)
            }

            bregister_phone.text.isEmpty() -> {
                bregister_phone.requestFocus()
                bregister_phone.error = getString(R.string.enterphone)
            }

            buploadfilename.text.equals("") -> {
                utility!!.relative_snackbar(
                    bregister_parent!!,
                    "Upload Image",
                    getString(R.string.close_up)
                )
            }

            !bregister_agreecheck.isChecked -> {
                utility!!.relative_snackbar(
                    bregister_parent!!,
                    "Accept terms and condition",
                    getString(R.string.close_up)
                )
            }

            !bregister_password.text.toString().equals(bregister_cpassword.text.toString()) -> {
                register_cpassword.requestFocus()
                register_cpassword.error = getString(R.string.passwordnotmatch)

            }
            else -> {
                if (checked.equals("1"))
                {
                    c = "terms"
                }
            }
        }
    }

    private fun setSpinnerData() {

//        business_team_spinner.prompt = "Select"
        val languages = resources.getStringArray(R.array.Team)
        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_dropdown_item, languages
        )
        business_team_spinner.adapter = adapter
        business_team_spinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View, position: Int, id: Long
            ) {
                if (position != 0) {
                    spinnertitle.setText(languages[position])
                }
                userType = languages[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // write code to perform some action
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
        business_team_spinner = findViewById(R.id.business_team_spinner)
        spinnertitle = findViewById(R.id.spinnertitle)
        register_forbusiness = findViewById(R.id.register_forbusiness)
        bregister_companyname = findViewById(R.id.bregister_companyname)
        bregister_firstname = findViewById(R.id.bregister_firstname)
        bregister_lastname = findViewById(R.id.bregister_lastname)
        bregister_email = findViewById(R.id.bregister_email)
        bregister_password = findViewById(R.id.bregister_password)
        bregister_cpassword = findViewById(R.id.bregister_cpassword)
        bregister_phone = findViewById(R.id.bregister_phone)
        business_agreecheck = findViewById(R.id.business_agreecheck)
        bregister_upload = findViewById(R.id.bregister_upload)
        buploadfilename = findViewById(R.id.buploadfilename)
        bregister_agreecheck = findViewById(R.id.bregister_agreecheck)
        bregister_regiter_bt = findViewById(R.id.bregister_regiter_bt)
        register_login = findViewById(R.id.register_login)
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            //Image Uri will not be null for RESULT_OK
            val fileUri = data?.data

            //You can get File object from intent
            var file: File? = ImagePicker.getFile(data)
            //You can also get File Path from intent
            val filePath: String? = ImagePicker.getFilePath(data)
            uploadfilename.text = filePath
            path = filePath!!
            bitMap = MediaStore.Images.Media.getBitmap(contentResolver, fileUri)
            part = Utility.sendImageFileToserver(filesDir,bitMap,"image")
        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            utility!!.relative_snackbar(
                bregister_parent!!,
                ImagePicker.getError(data),
                getString(R.string.close_up)
            )
        } else {
            utility!!.relative_snackbar(
                bregister_parent,
                "Task Cancelled",
                getString(R.string.close_up)
            )
        }
    }

    private fun hideKeyboard() {
        val imm = this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
    }
}