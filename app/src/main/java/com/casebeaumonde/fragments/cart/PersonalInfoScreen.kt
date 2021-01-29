package com.casebeaumonde.fragments.cart

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import com.casebeaumonde.R

class PersonalInfoScreen : AppCompatActivity() {

    private lateinit var back : ImageButton
    private lateinit var next : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_personal_info_screen)

        findIds()
        listeners()
    }

    private fun listeners() {
        back.setOnClickListener { onBackPressed() }
        next.setOnClickListener { startActivity(Intent(this,PaymentScreen::class.java)) }
    }

    private fun findIds() {
        back = findViewById(R.id.back)
        next = findViewById(R.id.next)
    }
}