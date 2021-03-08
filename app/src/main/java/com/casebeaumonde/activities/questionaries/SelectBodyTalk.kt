package com.casebeaumonde.activities.questionaries

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.casebeaumonde.R
import com.casebeaumonde.activities.questionaries.uploadBodyType.UploadBodyTypeImage

class SelectBodyTalk : AppCompatActivity() {

    private lateinit var continue_bt : LinearLayout
    private lateinit var back : ImageButton
    private lateinit var check1 : CheckBox
    private lateinit var check2 : CheckBox
    private lateinit var check3 : CheckBox
    private lateinit var check4 : CheckBox
    private lateinit var check5 : CheckBox
    private lateinit var body1 : ImageView
    private lateinit var body2 : ImageView
    private lateinit var body3 : ImageView
    private lateinit var body4 : ImageView
    private lateinit var body5 : ImageView
    private lateinit var bodytype_et : EditText

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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_body_talk)

        findIds()
        listeners()
    }

    private fun listeners() {
        back.setOnClickListener { onBackPressed() }
        continue_bt.setOnClickListener {
            when {
                bodytype_et.text.isEmpty() -> {
                    bodytype_et.requestFocus()
                    bodytype_et.error = "Enter Body type"
                }

                bodyType.equals("") -> {
                    Toast.makeText(this,"Select body type",Toast.LENGTH_SHORT).show()
                } else -> {
                startActivity( Intent(this, UploadBodyTypeImage::class.java).
                putExtra("name", name)
                    .putExtra("city", city).putExtra("state", state)
                    .putExtra("country", country).putExtra("mobile", mobile)
                    .putExtra("age", age).putExtra("month", month).putExtra("date", date)
                    .putExtra("year", year).putExtra("astrologicalSign", astrologicalSign)
                    .putStringArrayListExtra("colorscode", colorscode)
                    .putExtra("bodyType",bodyType)
                    .putExtra("bodyType_text",bodytype_et.text.toString()))
                }
            }
        }

        body1.setOnClickListener {
            check1.visibility = View.VISIBLE
            check2.visibility = View.GONE
            check3.visibility = View.GONE
            check4.visibility = View.GONE
            check5.visibility = View.GONE
            bodyType = "Triangle"
        }

        body2.setOnClickListener {
            check1.visibility = View.GONE
            check2.visibility = View.VISIBLE
            check3.visibility = View.GONE
            check4.visibility = View.GONE
            check5.visibility = View.GONE
            bodyType = "Inverted Triangle"
        }

        body3.setOnClickListener {
            check1.visibility = View.GONE
            check2.visibility = View.GONE
            check3.visibility = View.VISIBLE
            check4.visibility = View.GONE
            check5.visibility = View.GONE
            bodyType = "Circle"
        }

        body4.setOnClickListener {
            check1.visibility = View.GONE
            check2.visibility = View.GONE
            check3.visibility = View.GONE
            check4.visibility = View.VISIBLE
            check5.visibility = View.GONE

            bodyType = "Rectangle"
        }

        body5.setOnClickListener {
            check1.visibility = View.GONE
            check2.visibility = View.GONE
            check3.visibility = View.GONE
            check4.visibility = View.GONE
            check5.visibility = View.VISIBLE

            bodyType ="Two Triangle"
        }
    }

    private fun findIds() {
        back = findViewById(R.id.back)
        continue_bt = findViewById(R.id.continue_bt)
        bodytype_et = findViewById(R.id.bodytype_et)

        check1 = findViewById(R.id.check1)
        check2 = findViewById(R.id.check2)
        check3 = findViewById(R.id.check3)
        check4 = findViewById(R.id.check4)
        check5 = findViewById(R.id.check5)

        body1 = findViewById(R.id.body1)
        body2 = findViewById(R.id.body2)
        body3 = findViewById(R.id.body3)
        body4 = findViewById(R.id.body4)
        body5 = findViewById(R.id.body5)


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
    }
}