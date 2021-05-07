package com.casebeaumonde.activities.MyEvents

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import androidx.recyclerview.widget.RecyclerView
import com.casebeaumonde.Controller.Controller
import com.casebeaumonde.R
import com.casebeaumonde.constants.BaseClass
import com.casebeaumonde.utilities.Utility

class MyEventsActivity : BaseClass() {

    private lateinit var utility: Utility
    private lateinit var pd: ProgressDialog
    private lateinit var controller: Controller
    private lateinit var event_recycler: RecyclerView
    private lateinit var myclosets_back: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_events)

        findIDs()
        listeners()
    }

    private fun listeners() {
        myclosets_back.setOnClickListener {
            onBackPressed()
        }
    }

    private fun findIDs() {
        myclosets_back = findViewById(R.id.myclosets_back)
    }
}