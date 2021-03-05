package com.casebeaumonde.activities.questionaries

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_body_talk)

        findIds()
        listeners()
    }

    private fun listeners() {
        back.setOnClickListener { onBackPressed() }
        continue_bt.setOnClickListener {startActivity( Intent(this, UploadBodyTypeImage::class.java) )}

        body1.setOnClickListener {
            check1.visibility = View.VISIBLE
            check2.visibility = View.GONE
            check3.visibility = View.GONE
            check4.visibility = View.GONE
            check5.visibility = View.GONE
        }

        body2.setOnClickListener {
            check1.visibility = View.GONE
            check2.visibility = View.VISIBLE
            check3.visibility = View.GONE
            check4.visibility = View.GONE
            check5.visibility = View.GONE
        }

        body3.setOnClickListener {
            check1.visibility = View.GONE
            check2.visibility = View.GONE
            check3.visibility = View.VISIBLE
            check4.visibility = View.GONE
            check5.visibility = View.GONE
        }

        body4.setOnClickListener {
            check1.visibility = View.GONE
            check2.visibility = View.GONE
            check3.visibility = View.GONE
            check4.visibility = View.VISIBLE
            check5.visibility = View.GONE
        }

        body5.setOnClickListener {
            check1.visibility = View.GONE
            check2.visibility = View.GONE
            check3.visibility = View.GONE
            check4.visibility = View.GONE
            check5.visibility = View.VISIBLE
        }
    }

    private fun findIds() {
        back = findViewById(R.id.back)
        continue_bt = findViewById(R.id.continue_bt)

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
    }
}