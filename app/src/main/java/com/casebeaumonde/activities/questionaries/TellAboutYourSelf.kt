package com.casebeaumonde.activities.questionaries

import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.*
import androidx.appcompat.widget.AppCompatSpinner
import com.casebeaumonde.Controller.Controller
import com.casebeaumonde.R
import com.casebeaumonde.activities.questionaries.reponse.QuestionariesDataResponse
import com.casebeaumonde.constants.BaseClass
import com.casebeaumonde.constants.Constants
import com.casebeaumonde.utilities.Utility
import kotlinx.android.synthetic.main.activity_tell_about_your_self.*
import retrofit2.Response

class TellAboutYourSelf : BaseClass() , Controller.QuestionariesAPI {

    private lateinit var continue_bt : LinearLayout
    private lateinit var back : ImageButton
    private lateinit var state_spinner : AppCompatSpinner
    private lateinit var country_spinner : AppCompatSpinner
    private lateinit var month_spinner : AppCompatSpinner
    private lateinit var day_spinner : AppCompatSpinner
    private lateinit var year_spinner : AppCompatSpinner
    private lateinit var astrological_spinner : AppCompatSpinner
    private lateinit var pd : ProgressDialog
    private lateinit var utility: Utility
    private lateinit var controller: Controller
    private lateinit var countries : ArrayList<String>
    private lateinit var astrologicalsign : ArrayList<String>
    private lateinit var state : ArrayList<QuestionariesDataResponse.Customer.Country.State>
    private lateinit var stateName : ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tell_about_your_self)
        utility = Utility()
        controller = Controller()
        controller.Controller(this)
        pd = ProgressDialog(this)
        pd!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        pd!!.window!!.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        pd!!.isIndeterminate = true
        pd!!.setCancelable(false)
        if (utility.isConnectingToInternet(this))
        {
            pd.show()
            pd.setContentView(R.layout.loading)
             controller.Questionaries("Bearer "+getStringVal(Constants.TOKEN))
        } else {

            utility.relative_snackbar(
                parent_tellus!!,
                "No Internet Connectivity",
                getString(R.string.close_up)
            )
        }
        findIds()
        listeners()
    }

    private fun listeners() {
        back.setOnClickListener { onBackPressed() }
        continue_bt.setOnClickListener{ startActivity(Intent(this,ColorPickerScreen::class.java))}

        setMonthSpinner()
        setDaySpinner()
        setYearSpinner()

    }

    private fun setAstrologicalSpinner(astrologicalsign:ArrayList<String>) {
        val languages = resources.getStringArray(R.array.Year)
        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_dropdown_item, astrologicalsign
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

    private fun setCountrySpinner(countries: ArrayList<String>) {
        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_dropdown_item, countries
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

    private fun setStateSpinner(stateName : ArrayList<String>) {
        val languages = resources.getStringArray(R.array.Team)
        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_dropdown_item, stateName
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

    override fun onQuestionariesSuccess(questionaries: Response<QuestionariesDataResponse>) {
        pd.dismiss()
        if (questionaries.isSuccessful)
        {
            countries = ArrayList()
            for (i in 0 until questionaries.body()?.data?.customer?.country?.size!!) {
                val title = questionaries.body()?.data?.customer?.country?.get(i)
                title?.name?.let { countries.add(it) }
            }
            setCountrySpinner(countries)

            astrologicalsign = ArrayList()
            for (i in 0 until questionaries.body()?.data?.customer!!.astrologicalSign?.size!!) {
                val title = questionaries.body()?.data?.customer?.astrologicalSign?.get(i)
                astrologicalsign.add(title.toString())
            }
            setAstrologicalSpinner(astrologicalsign)

            state = ArrayList()
            stateName = ArrayList()
            for (i in 0 until questionaries.body()?.data?.customer?.country?.size!!) {
                val title = questionaries.body()?.data?.customer?.country?.get(i)
                title?.name?.let { countries.add(it) }
                for (j in 0 until countries.size) {
                    val title = questionaries.body()?.data?.customer?.country?.get(i)?.state?.get(j)?.name
                    stateName.add(title.toString())

                    setStateSpinner(stateName)
                }

            }

        } else {
            utility.relative_snackbar(
                parent_tellus!!,
                questionaries.message(),
                getString(R.string.close_up)
            )
        }
    }

    override fun error(error: String?) {
        pd.dismiss()
        utility.relative_snackbar(
            parent_tellus!!,
            error,
            getString(R.string.close_up)
        )
    }
}