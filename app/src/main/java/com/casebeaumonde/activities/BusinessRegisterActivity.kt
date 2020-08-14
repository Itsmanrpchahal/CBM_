package com.casebeaumonde.activities

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.*
import androidx.appcompat.widget.AppCompatSpinner
import com.casebeaumonde.R
import com.casebeaumonde.activities.login.LoginActivity
import com.github.dhaval2404.imagepicker.ImagePicker

class BusinessRegisterActivity : AppCompatActivity() {

    private lateinit var business_team_spinner: AppCompatSpinner
    private lateinit var spinnertitle : TextView
    private lateinit var register_forbusiness :TextView
    private lateinit var bregister_companyname: EditText
    private lateinit var bregister_firstname:EditText
    private lateinit var bregister_lastname : EditText
    private lateinit var bregister_email : EditText
    private lateinit var bregister_password : EditText
    private lateinit var bregister_cpassword : EditText
    private lateinit var bregister_phone : EditText
    private lateinit var business_agreecheck : CheckBox
    private lateinit var bregister_upload :Button
    private lateinit var buploadfilename : TextView
    private lateinit var bregister_agreecheck : CheckBox
    private lateinit var bregister_regiter_bt : Button
    private lateinit var register_login : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_business_register)

        findIds()
        setSpinnerData()
        listeners()


    }

    private fun listeners() {
        register_login.setOnClickListener {
            startActivity(Intent(this,
                LoginActivity::class.java))
            finish()
        }

        register_forbusiness.setOnClickListener {
            startActivity(Intent(this,RegisterActivity::class.java))
            finish()
        }

        bregister_upload.setOnClickListener {
            pictureSelectionDialog()
        }


    }

    private fun setSpinnerData() {
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
                if (position!=0)
                {
                    spinnertitle.setText(languages[position])
                }

            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // write code to perform some action
            }
        }
    }

    private fun findIds() {
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
}