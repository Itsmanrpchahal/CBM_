package com.casebeaumonde.activities.b_questionaries

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import com.casebeaumonde.R
import com.casebeaumonde.activities.b_questionaries.selectbrands.B_SelectBrands
import com.casebeaumonde.activities.questionaries.TellAboutYourSelf
import com.casebeaumonde.constants.BaseClass
import com.casebeaumonde.constants.Constants

class B_GetStartedActivity : BaseClass() {

    private lateinit var b_getstarted: Button
    private lateinit var close: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_b__get_started)

        findIDs()

        listeners()
    }

    private fun listeners() {
        b_getstarted.setOnClickListener {
            startActivity(Intent(this, B_SelectBrands::class.java))
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
        b_getstarted = findViewById(R.id.b_getstarted)
        close = findViewById(R.id.close)
    }
}