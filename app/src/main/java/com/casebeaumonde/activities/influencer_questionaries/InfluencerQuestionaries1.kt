package com.casebeaumonde.activities.influencer_questionaries

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.LinearLayout
import com.casebeaumonde.R

class InfluencerQuestionaries1 : AppCompatActivity() {

    private lateinit var influ_continue_bt : LinearLayout
    private lateinit var back : ImageButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_influencer_questionaries1)

        findIds()
        listeners()
    }

    private fun listeners() {
        influ_continue_bt.setOnClickListener { startActivity(Intent()) }

        back.setOnClickListener { onBackPressed() }
    }

    private fun findIds() {
        influ_continue_bt = findViewById(R.id.influ_continue_bt)
        back = findViewById(R.id.back)
    }
}