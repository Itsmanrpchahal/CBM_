package com.casebeaumonde.activities.b_questionaries.b_basicInfo1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.casebeaumonde.R
import com.casebeaumonde.activities.b_questionaries.fashionevents.B_FashionEventsActivity
import com.casebeaumonde.constants.BaseClass

class B_BasicInfoActivity1 : BaseClass() {
    private lateinit var continue_bt : LinearLayout
    private lateinit var spendclothing_spinner : Spinner
    private lateinit var driveyou_spinner : Spinner
    private lateinit var back : ImageButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_b__basic_info1)

        findIds()
        listeners()
        setSpinnerData()
        setspendSpinnerData()
    }

    private fun listeners() {
        continue_bt.setOnClickListener {
            startActivity(Intent(this, B_FashionEventsActivity::class.java))
        }

        back.setOnClickListener {
            onBackPressed()
        }
    }

    private fun findIds() {
        continue_bt = findViewById(R.id.continue_bt)
        back = findViewById(R.id.back)
        spendclothing_spinner = findViewById(R.id.spendclothing_spinner)
        driveyou_spinner = findViewById(R.id.driveyou_spinner)
    }

    private fun setSpinnerData() {

//        business_team_spinner.prompt = "Select"
        val languages = resources.getStringArray(R.array.Team)
        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_dropdown_item, languages
        )
        spendclothing_spinner.adapter = adapter
        spendclothing_spinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View, position: Int, id: Long
            ) {
                if (position != 0) {
                   // spinnertitle.setText(languages[position])
                }
               // userType = languages[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // write code to perform some action
            }
        }
    }

    private fun setspendSpinnerData() {

//        business_team_spinner.prompt = "Select"
        val languages = resources.getStringArray(R.array.Team)
        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_dropdown_item, languages
        )
        driveyou_spinner.adapter = adapter
        driveyou_spinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View, position: Int, id: Long
            ) {
                if (position != 0) {
                    // spinnertitle.setText(languages[position])
                }
                // userType = languages[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // write code to perform some action
            }
        }
    }
}