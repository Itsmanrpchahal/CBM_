package com.casebeaumonde.activities.questionaries

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.casebeaumonde.R

class GetStartedActivity : AppCompatActivity() {

    private lateinit var getstarted : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get_started)

        findIDs()
        listeners()
    }

    private fun listeners() {
        getstarted.setOnClickListener {
            startActivity(Intent(this,TellAboutYourSelf::class.java))
        }
    }

    private fun findIDs() {
        getstarted = findViewById(R.id.getstarted)
    }
}