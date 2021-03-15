package com.casebeaumonde.activities.biz_questionaries

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import com.casebeaumonde.R

class Biz_Questionaries4 : AppCompatActivity() {
    private lateinit var back : ImageButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_biz__questionaries4)

        findIds()
        lisenters()
    }

    private fun lisenters() {
        back.setOnClickListener {
            onBackPressed()
        }
    }

    private fun findIds() {
        back = findViewById(R.id.back)
    }
}