package com.casebeaumonde.activities.b_questionaries.fashionevents

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import com.casebeaumonde.R
import com.casebeaumonde.activities.b_questionaries.b_basicInfo1.B_BasicInfoActivity1

class B_FashionEventsActivity : AppCompatActivity() {
    private lateinit var continue_bt : LinearLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_b__fashion_events)

        findIds()
        listeners()
    }

    private fun listeners() {
        continue_bt.setOnClickListener {
            startActivity(Intent(this, B_BasicInfoActivity1::class.java))
        }
    }

    private fun findIds() {
        continue_bt = findViewById(R.id.continue_bt)
    }
}