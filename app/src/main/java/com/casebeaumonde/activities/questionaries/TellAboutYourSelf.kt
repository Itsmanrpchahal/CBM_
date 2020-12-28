package com.casebeaumonde.activities.questionaries

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.widget.AppCompatSpinner
import com.casebeaumonde.R

class TellAboutYourSelf : AppCompatActivity() {

    private lateinit var continue_bt : LinearLayout
    private lateinit var back : ImageButton
    private lateinit var state_spinner : AppCompatSpinner
    private lateinit var country_spinner : AppCompatSpinner
    private lateinit var month_spinner : AppCompatSpinner
    private lateinit var day_spinner : AppCompatSpinner
    private lateinit var year_spinner : AppCompatSpinner
    private lateinit var astrological_spinner : AppCompatSpinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tell_about_your_self)

        findIds()
        listeners()
    }

    private fun listeners() {
        back.setOnClickListener { onBackPressed() }
        continue_bt.setOnClickListener{ startActivity(Intent(this,ColorPickerScreen::class.java))}
        setStateSpinner()
        setCountrySpinner()
        setMonthSpinner()
        setDaySpinner()
        setYearSpinner()
        setAstrologicalSpinner()
    }

    private fun setAstrologicalSpinner() {
        val languages = resources.getStringArray(R.array.Year)
        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_dropdown_item, languages
        )
        astrological_spinner.adapter = adapter
        astrological_spinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View, position: Int, id: Long
            ) {
                if (position != 0) {
                    // spinnertitle.setText(languages[position])
                }
                //  userType = languages[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // write code to perform some action
            }
        }
    }

    private fun setYearSpinner() {
        val languages = resources.getStringArray(R.array.Year)
        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_dropdown_item, languages
        )
        year_spinner.adapter = adapter
        year_spinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View, position: Int, id: Long
            ) {
                if (position != 0) {
                    // spinnertitle.setText(languages[position])
                }
                //  userType = languages[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // write code to perform some action
            }
        }
    }

    private fun setDaySpinner() {
        val languages = resources.getStringArray(R.array.Day)
        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_dropdown_item, languages
        )
        day_spinner.adapter = adapter
        day_spinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View, position: Int, id: Long
            ) {
                if (position != 0) {
                    // spinnertitle.setText(languages[position])
                }
                //  userType = languages[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // write code to perform some action
            }
        }
    }

    private fun setMonthSpinner() {
        val languages = resources.getStringArray(R.array.Month)
        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_dropdown_item, languages
        )
        month_spinner.adapter = adapter
        month_spinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View, position: Int, id: Long
            ) {
                if (position != 0) {
                    // spinnertitle.setText(languages[position])
                }
                //  userType = languages[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // write code to perform some action
            }
        }
    }

    private fun setCountrySpinner() {
        val languages = resources.getStringArray(R.array.Countrt)
        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_dropdown_item, languages
        )
        country_spinner.adapter = adapter
        country_spinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View, position: Int, id: Long
            ) {
                if (position != 0) {
                    // spinnertitle.setText(languages[position])
                }
                //  userType = languages[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // write code to perform some action
            }
        }
    }

    private fun setStateSpinner() {
        val languages = resources.getStringArray(R.array.Team)
        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_dropdown_item, languages
        )
        state_spinner.adapter = adapter
        state_spinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View, position: Int, id: Long
            ) {
                if (position != 0) {
                   // spinnertitle.setText(languages[position])
                }
              //  userType = languages[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // write code to perform some action
            }
        }
    }

    private fun findIds() {
        continue_bt = findViewById(R.id.continue_bt)
        back = findViewById(R.id.back)
        state_spinner = findViewById(R.id.state_spinner)
        country_spinner = findViewById(R.id.country_spinner)
        month_spinner = findViewById(R.id.month_spinner)
        day_spinner = findViewById(R.id.day_spinner)
        year_spinner = findViewById(R.id.year_spinner)
        astrological_spinner = findViewById(R.id.astrological_spinner)

    }
}