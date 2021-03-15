package com.casebeaumonde.activities.biz_questionaries

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.LinearLayout
import com.casebeaumonde.R

class Biz_Questionaries3 : AppCompatActivity() {
    private lateinit var biz3_continue_bt : LinearLayout
    private lateinit var back : ImageButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_biz__questionaries3)

        findIds()
        lisenters()
    }

    private fun lisenters() {
        biz3_continue_bt.setOnClickListener {
            startActivity(Intent(this,Biz_Questionaries4::class.java))
        }

        back.setOnClickListener {
            onBackPressed()
        }
    }

    private fun findIds() {
        biz3_continue_bt = findViewById(R.id.biz3_continue_bt)
        back = findViewById(R.id.back)
    }
}