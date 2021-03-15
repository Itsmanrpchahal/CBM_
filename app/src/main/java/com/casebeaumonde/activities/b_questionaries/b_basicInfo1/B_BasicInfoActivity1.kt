package com.casebeaumonde.activities.b_questionaries.b_basicInfo1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import com.casebeaumonde.R
import com.casebeaumonde.activities.b_questionaries.fashionevents.B_FashionEventsActivity
import com.casebeaumonde.constants.BaseClass

class B_BasicInfoActivity1 : BaseClass() {
    private lateinit var continue_bt : LinearLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_b__basic_info1)

        findIds()
        listeners()
    }

    private fun listeners() {
        continue_bt.setOnClickListener {
            startActivity(Intent(this, B_FashionEventsActivity::class.java))
        }

    }

    private fun findIds() {
        continue_bt = findViewById(R.id.continue_bt)

    }
}