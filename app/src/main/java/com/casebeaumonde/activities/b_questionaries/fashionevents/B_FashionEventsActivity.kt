package com.casebeaumonde.activities.b_questionaries.fashionevents

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.casebeaumonde.R
import com.casebeaumonde.activities.b_questionaries.b_basicInfo1.B_BasicInfoActivity1
import com.casebeaumonde.activities.b_questionaries.like_CBM.Like_CBM
import com.casebeaumonde.activities.myclosets.adapter.MyClosetsAdapter
import com.casebeaumonde.constants.BaseClass
import com.casebeaumonde.constants.Constants

class B_FashionEventsActivity : BaseClass() {
    private lateinit var continue_bt : LinearLayout
    private lateinit var recylerview : RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_b__fashion_events)

        findIds()
        listeners()
        setData()
    }

    private fun setData() {
        recylerview.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        val adapter = B_FashionEventAdapter(this)
        recylerview.adapter = adapter
    }

    private fun listeners() {
        continue_bt.setOnClickListener {
            startActivity(Intent(this, Like_CBM::class.java))
        }
    }

    private fun findIds() {
        continue_bt = findViewById(R.id.continue1_bt)
        recylerview = findViewById(R.id.recylerview)
    }
}