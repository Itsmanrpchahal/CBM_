package com.casebeaumonde.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.casebeaumonde.R
import com.casebeaumonde.activities.login.LoginActivity
import com.casebeaumonde.activities.userRegister.RegisterActivity

class OnBoardScreen : AppCompatActivity() {

    private lateinit var onboard_registerBt : Button
    private lateinit var onboard_pricing : TextView
    private lateinit var onboard_loginBt : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_board_screen)

        findIds()
        listeners()
    }

    private fun listeners() {
        onboard_loginBt.setOnClickListener {
            startActivity(Intent(this,LoginActivity::class.java))
        }

        onboard_registerBt.setOnClickListener {
            //startActivity(Intent(this,RegisterActivity::class.java))
        }
        onboard_pricing.setOnClickListener {
           // startActivity(Intent(this,LoginActivity::class.java))
        }
    }

    private fun findIds() {
        onboard_loginBt = findViewById(R.id.onboard_loginBt)
        onboard_registerBt = findViewById(R.id.onboard_registerBt)
        onboard_pricing = findViewById(R.id.onboard_pricing)
    }
}