package com.casebeaumonde.activities

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.widget.*
import com.casebeaumonde.R
import com.casebeaumonde.activities.login.LoginActivity
import com.github.dhaval2404.imagepicker.ImagePicker
import java.io.File

class RegisterActivity : AppCompatActivity() {

    private lateinit var register_forbusiness : TextView
    private lateinit var register_firstname : EditText
    private lateinit var register_lastname : EditText
    private lateinit var register_email : EditText
    private lateinit var register_password : EditText
    private lateinit var register_cpassword : EditText
    private lateinit var register_phone : EditText
    private lateinit var register_aboutme : EditText
    private lateinit var register_upload : Button
    private lateinit var agreecheck :CheckBox
    private lateinit var register_regiter_bt : Button
    private lateinit var register_login : TextView
    private  var outputUri : Uri? = null
    private var profilePicChoosed: Boolean? = false
    val notC = "0"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        findids()
        listerners()
    }



    private fun findids() {
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
        register_login = findViewById(R.id.register_login)
    }

    private fun listerners() {
      register_login.setOnClickListener {
          startActivity(Intent(this,
              LoginActivity::class.java))
          finish()
      }

        register_upload.setOnClickListener {

            pictureSelectionDialog()
        }


        register_forbusiness.setOnClickListener {
            startActivity(Intent(this,BusinessRegisterActivity::class.java))
            finish()
        }
    }

    private fun pictureSelectionDialog() {

        val camera : LinearLayout
        val gallery : LinearLayout
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
                .start()	//Default Request Code is ImagePicker.REQUEST_CODE
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
            val file: File? = ImagePicker.getFile(data)
            //You can also get File Path from intent
            val filePath: String? = ImagePicker.getFilePath(data)
            Toast.makeText(this,""+filePath,Toast.LENGTH_LONG).show()
        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            Toast.makeText(this, ImagePicker.getError(data), Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Task Cancelled", Toast.LENGTH_SHORT).show()
        }
    }
}