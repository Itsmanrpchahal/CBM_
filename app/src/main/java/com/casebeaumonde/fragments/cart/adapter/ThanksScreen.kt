package com.casebeaumonde.fragments.cart.adapter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import com.casebeaumonde.R

class ThanksScreen : AppCompatActivity() {

    private lateinit var back : ImageButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_thanks_screen)

        findIds()
        listeners()
    }

    private fun listeners() {
        back.setOnClickListener { onBackPressed() }
    }

    private fun findIds() {
        back = findViewById(R.id.back)
    }
}