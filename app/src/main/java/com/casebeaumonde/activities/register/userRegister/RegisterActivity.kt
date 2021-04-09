package com.casebeaumonde.activities.register.userRegister

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
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.text.InputType
import android.util.Log
import android.util.Patterns
import android.view.Window
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import com.casebeaumonde.R
import com.casebeaumonde.Retrofit.WebAPI
import com.casebeaumonde.activities.businessRegister.BusinessRegisterActivity
import com.casebeaumonde.activities.forgotPassword.ForgotActivity
import com.casebeaumonde.activities.login.LoginActivity
import com.casebeaumonde.activities.register.userRegister.userRegisterResponse.UserRegisterResponse
import com.casebeaumonde.constants.BaseClass
import com.casebeaumonde.utilities.Utility
import com.github.dhaval2404.imagepicker.ImagePicker
import com.livinglifetechway.quickpermissions_kotlin.runWithPermissions
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.app_bar_main.*
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

class RegisterActivity : BaseClass() {

    private lateinit var register_forbusiness: TextView
    private lateinit var forgotpassword : TextView
    private lateinit var donthaveaccount : TextView
    private lateinit var back : ImageButton
    private lateinit var register_firstname: EditText
    private lateinit var register_lastname: EditText
    private lateinit var register_email: EditText
    private lateinit var register_password: EditText
    private lateinit var register_cpassword: EditText
    private lateinit var register_phone: EditText
    private lateinit var register_aboutme: EditText

    private lateinit var register_upload: Button
    private lateinit var agreecheck: CheckBox
    private lateinit var register_regiter_bt: Button
    private lateinit var register_login: TextView
    private lateinit var utility: Utility
    private var path: String = ""
    private var checked: String = "0"
    private lateinit var pd: ProgressDialog
    private lateinit var part : MultipartBody.Part
    private lateinit var bitMap: Bitmap
    private lateinit var  dialog: Dialog
    var c:String = ""
    private lateinit var see_password: CheckBox
    private lateinit var see_password1: CheckBox


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        findids()
        listerners()
    }


    private fun findids() {

        utility = Utility()
        pd = ProgressDialog(this)
        pd!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        pd!!.window!!.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        pd!!.isIndeterminate = true
        pd!!.setCancelable(false)
        register_forbusiness = findViewById(R.id.register_forbusiness)
        register_firstname = findViewById(R.id.register_firstname)
        register_lastname = findViewById(R.id.register_lastname)
        register_email = findViewById(R.id.register_email)
        register_password = findViewById(R.id.register_password)
        register_cpassword = findViewById(R.id.register_cpassword)
        register_phone = findViewById(R.id.register_phone)
        register_aboutme = findViewById(R.id.register_aboutme)
        register_upload = findViewById(R.id.register_upload)
        agreecheck = findViewById(R.id.agreecheck)
        register_regiter_bt = findViewById(R.id.register_regiter_bt)
        back = findViewById(R.id.back)
        donthaveaccount = findViewById(R.id.donthaveaccount)
        forgotpassword = findViewById(R.id.forgotpassword)
        see_password = findViewById(R.id.see_password)
        see_password1 = findViewById(R.id.see_password1)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun listerners() {
//        register_login.setOnClickListener {
//            startActivity(
//                Intent(
//                    this,
//                    LoginActivity::class.java
//                )
//            )
//            finish()
//        }

        register_upload.setOnClickListener {

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


        register_forbusiness.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    BusinessRegisterActivity::class.java
                )
            )
            finish()
        }

        register_regiter_bt.setOnClickListener {
            checkValidations()
        }

        agreecheck.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                checked = "1"
            } else {
                checked = "0"
            }
        }

        back.setOnClickListener { onBackPressed() }

        donthaveaccount.setOnClickListener { startActivity(Intent(this,RegisterTypeScreen::class.java)) }

        forgotpassword.setOnClickListener {
            startActivity(Intent(this,ForgotActivity::class.java))
        }

        see_password.setOnCheckedChangeListener { buttonView, isChecked ->
            if (see_password.text.length>1)
            {
                if (isChecked)
                {
                    register_password.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                    register_password.setSelection(register_password.text.length)
                } else {
                    register_password.inputType =
                        InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                    register_password.setSelection(register_password.text.length)
                }
                register_password.setTypeface(resources.getFont(R.font.opensans_regular));
            }

        }

        see_password1.setOnCheckedChangeListener { buttonView, isChecked ->
            if (see_password1.text.length>1)
            {
                if (isChecked)
                {
                    register_cpassword.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                    register_cpassword.setSelection(register_cpassword.text.length)
                } else {
                    register_cpassword.inputType =
                        InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                    register_cpassword.setSelection(register_cpassword.text.length)
                }
                register_cpassword.setTypeface(resources.getFont(R.font.opensans_regular));
            }

        }
    }


    private fun checkValidations() {
        when {
            register_firstname.text.isEmpty() -> {
                register_firstname.requestFocus()
                register_firstname.error = getString(R.string.enterfirstname)
            }

            register_lastname.text.isEmpty() -> {
                register_lastname.requestFocus()
                register_lastname.error = getString(R.string.enterlastname)
            }

            register_email.text.isEmpty() -> {
                register_email.requestFocus()
                register_email.error = getString(R.string.enteremail)
            }

            !Patterns.EMAIL_ADDRESS.matcher(register_email.text).matches() -> {
                register_email.requestFocus()
                register_email.error = "Enter Valid Email"
            }

            register_password.text.toString().length<8 &&!Utility.isValidPassword(register_password.text.toString())->
            {
                register_password.requestFocus()
                register_password.error = getString(R.string.strongpass)
            }

            register_password.text.isEmpty() -> {
                register_password.requestFocus()
                register_password.error = getString(R.string.enterpassword)
            }

            register_cpassword.text.isEmpty() -> {
                register_cpassword.requestFocus()
                register_cpassword.error = getString(R.string.enterconfirmpassword)
            }


            register_phone.text.isEmpty() -> {
                register_phone.requestFocus()
                register_phone.error = getString(R.string.enterphone)
            }

            register_aboutme.text.isEmpty() -> {
                register_aboutme.requestFocus()
                register_aboutme.error = getString(R.string.aboutme)
            }


            uploadfilename.text.equals("") -> {
                utility!!.relative_snackbar(
                    parent_register!!,
                    "Upload Image",
                    getString(R.string.close_up)
                )
            }

            !agreecheck.isChecked -> {
                utility!!.relative_snackbar(
                    parent_register!!,
                    "Accept terms and condition",
                    getString(R.string.close_up)
                )
            }

            !register_password.text.toString().equals(register_cpassword.text.toString()) -> {
                register_cpassword.requestFocus()
                register_cpassword.error = getString(R.string.passwordnotmatch)

            }
            else -> {
                if (checked.equals("1"))
                {
                    c = "terms"
                }
//                val strg = register_firstname.text.split(" ").toTypedArray()
//                if (strg.size==1)
//                {
//                    userRegister(
//                        register_firstname.text.toString(),
//                       "",
//                        register_email.text.toString(),
//                        register_password.text.toString(),
//                        register_cpassword.text.toString(),
//                        register_phone.text.toString(),
//                        register_aboutme.text.toString(),
//                        part,
//                        c
//                    )
//                } else if (strg.size >=2)
//                {
                    userRegister(
                        register_firstname.text.toString(),
                        register_lastname.text.toString(),
                        register_email.text.toString(),
                        register_password.text.toString(),
                        register_cpassword.text.toString(),
                        register_phone.text.toString(),
                        register_aboutme.text.toString(),
                        part,
                        c
                    )
//                }


            }
        }
    }

    private fun userRegister(
        firstname: String?,
        lastname: String?,
        email: String?,
        password: String?,
        cpassword: String?,
        phone: String?,
        about: String?,
        part: MultipartBody.Part,
        c: String
    ) {
        //hideKeyboard()
        if (utility.isConnectingToInternet(this)) {
            pd.show()
            pd.setContentView(R.layout.loading)


            val userRegisterCall = WebAPI.apiInterface?.userRegisterCall(
                firstname,
                lastname,
                email,
                password,
                cpassword,
                phone,
                c,
                about,
                part,
                "customer",""
            )
            userRegisterCall?.enqueue(object : Callback<UserRegisterResponse> {

                override fun onResponse(
                    call: Call<UserRegisterResponse>,
                    response: Response<UserRegisterResponse>
                ) {
                    pd.dismiss()
                    Log.d("registerresponse", "" + response.body())

                    if(response.isSuccessful)
                    {
                        if(response.body()?.code.equals("200"))
                        {
                            register_firstname.setText("")
                            register_email.setText("")
                            register_password.setText("")
                            register_cpassword.setText("")
                            register_phone.setText("")
                            register_aboutme.setText("")
                            uploadfilename.setText("")
                            bitMap.recycle()
                            startActivity(Intent(this@RegisterActivity,LoginActivity::class.java).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK))
                            finish()
                            utility!!.relative_snackbar(
                                parent_register!!,
                                response.body()?.getMessage(),
                                getString(R.string.close_up)
                            )
                        }else {
                            utility!!.relative_snackbar(
                                parent_register!!,
                                response.body()?.getMessage(),
                                getString(R.string.close_up)
                            )
                        }
                    } else
                    {
                        utility!!.relative_snackbar(
                            parent_register!!,
                            response.message(),
                            getString(R.string.close_up)
                        )
                    }

                }

                override fun onFailure(call: Call<UserRegisterResponse>, t: Throwable) {
                    pd.dismiss()
                    utility!!.relative_snackbar(
                        parent_register!!,
                        "Error "+t.message,
                        getString(R.string.close_up)
                    )
                }
            })
        } else {
            pd.dismiss()
            utility.relative_snackbar(
                parent_register!!,
                getString(R.string.nointernet),
                getString(R.string.close_up)
            )
        }
    }

    private fun hideKeyboard() {
        val imm = this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
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
            uploadfilename.text = filePath
            path = filePath!!
            bitMap = MediaStore.Images.Media.getBitmap(contentResolver,fileUri)
            part = Utility.sendImageFileToserver(filesDir,bitMap,"image")
        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            utility!!.relative_snackbar(
                parent_register!!,
                ImagePicker.getError(data),
                getString(R.string.close_up)
            )
        } else {
            utility!!.relative_snackbar(
                parent_register,
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