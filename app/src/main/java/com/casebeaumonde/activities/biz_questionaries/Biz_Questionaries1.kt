package com.casebeaumonde.activities.biz_questionaries

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.LinearLayout
import com.casebeaumonde.R

class Biz_Questionaries1 : AppCompatActivity() {

    private lateinit var biz1_continue_bt : LinearLayout
    private lateinit var back : ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_biz__questionaries1)

        findIds()
        listeners()
    }

    private fun listeners() {
        biz1_continue_bt.setOnClickListener {
            startActivity(Intent(this,Biz_Questionaries2::class.java))
        }
        back.setOnClickListener { onBackPressed() }
    }

    private fun findIds() {
        biz1_continue_bt = findViewById(R.id.biz1_continue_bt)
        back = findViewById(R.id.back)
    }
}