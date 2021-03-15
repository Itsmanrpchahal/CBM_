package com.casebeaumonde.activities.biz_questionaries

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.LinearLayout
import com.casebeaumonde.R
import com.casebeaumonde.constants.BaseClass

class Biz_Questionaries2 : BaseClass() {

    private lateinit var biz2_continue_bt : LinearLayout
    private lateinit var back : ImageButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_biz__questionaries2)

        findIDs()
        listeners()
    }

    private fun listeners() {
        biz2_continue_bt.setOnClickListener {
            startActivity(Intent(this,Biz_Questionaries3::class.java))
        }

        back.setOnClickListener {
            onBackPressed()
        }
    }

    private fun findIDs() {
        biz2_continue_bt = findViewById(R.id.biz2_continue_bt)
        back = findViewById(R.id.back)
    }
}