package com.casebeaumonde.activities.b_questionaries.like_CBM

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.casebeaumonde.R
import com.casebeaumonde.activities.b_questionaries.fashionevents.B_FashionEventAdapter
import com.casebeaumonde.constants.BaseClass

class Like_CBM : BaseClass() {


    private lateinit var continue_bt : LinearLayout
    private lateinit var recylerview : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_like__c_b_m)

        findIds()
        listeners()
        setData()
    }

    private fun setData() {
        recylerview.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        val adapter = LikeCBM_Adapter(this)
        recylerview.adapter = adapter
    }

    private fun listeners() {
        continue_bt.setOnClickListener {
            startActivity(Intent(this, Like_CBM::class.java))
        }
    }

    private fun findIds() {
        continue_bt = findViewById(R.id.cbmcontinue_bt)
        recylerview = findViewById(R.id.recylerview)
    }
}