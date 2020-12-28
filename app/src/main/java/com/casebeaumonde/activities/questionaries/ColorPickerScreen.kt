package com.casebeaumonde.activities.questionaries

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.RelativeLayout
import com.casebeaumonde.R

class ColorPickerScreen : AppCompatActivity() {
    private lateinit var continue_bt : LinearLayout
    private lateinit var back : ImageButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_color_picker_screen)

        findIDs()
        listeners()
    }

    private fun listeners() {
        continue_bt.setOnClickListener { startActivity(Intent(this,SelectBodyTalk::class.java) )}
        back.setOnClickListener{onBackPressed()}
    }

    private fun findIDs() {
        continue_bt = findViewById(R.id.continue_bt)
        back = findViewById(R.id.back)
    }
}