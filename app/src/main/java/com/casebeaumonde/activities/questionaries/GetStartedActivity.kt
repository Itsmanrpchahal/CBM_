package com.casebeaumonde.activities.questionaries

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import com.casebeaumonde.MainActivity
import com.casebeaumonde.R
import com.casebeaumonde.constants.BaseClass
import com.casebeaumonde.constants.Constants

class GetStartedActivity : BaseClass() {

    private lateinit var getstarted: Button
    private lateinit var close: ImageButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get_started)

        findIDs()
        listeners()
    }

    private fun listeners() {
        getstarted.setOnClickListener {
            startActivity(Intent(this, TellAboutYourSelf::class.java))
        }

        close.setOnClickListener {
//            startActivity(
//                Intent(
//                    this,
//                    MainActivity::class.java
//                ).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
//                    .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
//            )
            setStringVal(Constants.CLOSE, "1")
            finish()
        }
    }

    private fun findIDs() {
        getstarted = findViewById(R.id.getstarted)
        close = findViewById(R.id.close)
    }
}