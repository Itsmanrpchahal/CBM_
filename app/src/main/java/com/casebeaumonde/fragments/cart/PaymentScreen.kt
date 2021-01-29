package com.casebeaumonde.fragments.cart

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import com.casebeaumonde.R
import com.casebeaumonde.fragments.cart.adapter.ThanksScreen

class PaymentScreen : AppCompatActivity() {

    private lateinit var back : ImageButton
    private lateinit var pay : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment_screen)

        findIds()
        listeners()
    }

    private fun listeners() {
        back.setOnClickListener { onBackPressed() }
        pay.setOnClickListener { startActivity(Intent(this,ThanksScreen::class.java)) }
    }

    private fun findIds() {
        back = findViewById(R.id.back)
        pay = findViewById(R.id.pay)
    }
}