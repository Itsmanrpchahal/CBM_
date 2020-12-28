package com.casebeaumonde.activities.questionaries

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.RelativeLayout
import com.casebeaumonde.R

class SelectBodyTalk : AppCompatActivity() {

    private lateinit var continue_bt : LinearLayout
    private lateinit var back : ImageButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_body_talk)

        findIds()
        listeners()
    }

    private fun listeners() {
        back.setOnClickListener { onBackPressed() }
        continue_bt.setOnClickListener {startActivity( Intent(this,UploadBodyTypeImage::class.java) )}
    }

    private fun findIds() {
        back = findViewById(R.id.back)
        continue_bt = findViewById(R.id.continue_bt)
    }
}