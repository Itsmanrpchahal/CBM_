package com.casebeaumonde.activities.questionaries

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ImageButton
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatSpinner
import com.casebeaumonde.R
import com.casebeaumonde.activities.questionaries.selectbrands.SelectBrands

class UploadBodyTypeImage : AppCompatActivity() {

    private lateinit var eye_color_spinner : AppCompatSpinner
    private lateinit var hair_spinner : AppCompatSpinner
    private lateinit var back : ImageButton
    private lateinit var continue_bt : LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upload_body_type_image)

        findIds()
        setEyecolorSpinner()
        setHairColorSpinner()
        listeners()
    }

    private fun listeners() {
        back.setOnClickListener { onBackPressed() }
        continue_bt.setOnClickListener { startActivity(Intent(this, SelectBrands::class.java)) }
    }

    private fun setHairColorSpinner() {
        val languages = resources.getStringArray(R.array.EyeColor)
        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_dropdown_item, languages
        )
        hair_spinner.adapter = adapter
        hair_spinner.onItemSelectedListener = object :
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
        }
    }

    private fun setEyecolorSpinner() {
        val languages = resources.getStringArray(R.array.EyeColor)
        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_dropdown_item, languages
        )
        eye_color_spinner.adapter = adapter
        eye_color_spinner.onItemSelectedListener = object :
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
        }
    }

    private fun findIds() {
        eye_color_spinner = findViewById(R.id.eye_color_spinner)
        hair_spinner = findViewById(R.id.hair_spinner)
        continue_bt = findViewById(R.id.continue_bt)
        back = findViewById(R.id.back)
    }
}