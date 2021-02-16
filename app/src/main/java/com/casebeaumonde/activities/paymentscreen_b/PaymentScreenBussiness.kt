package com.casebeaumonde.activities.paymentscreen_b

import android.os.Bundle
import android.widget.ImageButton
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import com.casebeaumonde.R

class PaymentScreenBussiness : AppCompatActivity() {

    lateinit var back : ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment_screen_bussiness)

        findIds()
        listeners()
    }

    private fun listeners() {
        back.setOnClickListener {
            onBackPressed()
        }
    }

    private fun findIds() {
        back = findViewById(R.id.back)
    }
}