package com.casebeaumonde.fragments.cart

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.RelativeLayout
import com.casebeaumonde.R

class PersonalInfoScreen : AppCompatActivity() {

    private lateinit var back : ImageButton
    private lateinit var next : Button
    private lateinit var checkbox : CheckBox
    private lateinit var address : RelativeLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.personalinfo)

        findIds()
        listeners()
    }

    private fun listeners() {
        back.setOnClickListener { onBackPressed() }
        next.setOnClickListener { startActivity(Intent(this,PaymentScreen::class.java)) }
        checkbox.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked)
            {
                address.visibility = View.GONE
            } else {
                address.visibility = View.VISIBLE
            }
        }
    }

    private fun findIds() {
        back = findViewById(R.id.back)
        next = findViewById(R.id.next)
        checkbox = findViewById(R.id.checkbox)
        address = findViewById(R.id.address)
    }
}