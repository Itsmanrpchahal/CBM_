package com.casebeaumonde.activities.b_questionaries.tellusmore

import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.*
import androidx.appcompat.widget.AppCompatSpinner
import com.casebeaumonde.Controller.Controller
import com.casebeaumonde.R
import com.casebeaumonde.activities.b_questionaries.b_basicInfo.B_BasicInfoActivity
import com.casebeaumonde.activities.questionaries.reponse.QuestionariesDataResponse
import com.casebeaumonde.constants.BaseClass
import com.casebeaumonde.constants.Constants
import com.casebeaumonde.utilities.Utility
import kotlinx.android.synthetic.main.activity_b__tell__u_s__m_o_r_e.*
import kotlinx.android.synthetic.main.activity_tell_about_your_self.*
import kotlinx.android.synthetic.main.activity_tell_about_your_self.parent_tellus
import retrofit2.Response
import java.util.*
import kotlin.collections.ArrayList

class B_Tell_US_MORE :  BaseClass() , Controller.QuestionariesAPI {


    private  var selected_state = "-Select State-"
    private var selected_country ="-Select Country-"
    private lateinit var state_spinner : AppCompatSpinner
    private lateinit var country_spinner : AppCompatSpinner
    private lateinit var pd : ProgressDialog
    private lateinit var utility: Utility
    private lateinit var controller: Controller
    private lateinit var state : ArrayList<QuestionariesDataResponse.Data.Customer.Country.State>
    private lateinit var stateName : ArrayList<String>
    private var currentYear : Int = 0
    private lateinit var back : ImageButton
    private lateinit var countries : ArrayList<String>
    private lateinit var brandID : ArrayList<String>
    private lateinit var continue_bt : LinearLayout
    private lateinit var b_address_et : EditText
    private lateinit var b_addressline2_et : EditText
    private lateinit var b_city_et : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_b__tell__u_s__m_o_r_e)

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
                b_tell_us_more!!,
                "No Internet Connectivity",
                getString(R.string.close_up)
            )
        }

        currentYear = Calendar.getInstance().get(Calendar.YEAR);


        findIds()
        listeners()
    }

    private fun listeners() {
        back.setOnClickListener { onBackPressed() }
        continue_bt.setOnClickListener {
            when {
                b_address_et.text.isEmpty() -> {
                    b_address_et.requestFocus()
                    b_address_et.error ="Enter Address"
                }

                b_addressline2_et.text.isEmpty() -> {
                    b_addressline2_et.requestFocus()
                    b_addressline2_et.error = "Enter Addressline 2"
                }

                b_city_et.text.isEmpty() -> {
                    b_city_et.requestFocus()
                    b_city_et.error = "Enter City"
                }

                selected_state.toString().equals("-Select State-") -> {
                    utility.relative_snackbar(
                        b_tell_us_more!!,
                        "Select State",
                        getString(R.string.close_up)
                    )
                }

                selected_country.toString().equals("-Select Country-") -> {
                    utility.relative_snackbar(
                        b_tell_us_more!!,
                        "Select Country",
                        getString(R.string.close_up)
                    )
                }
                else -> {
                    startActivity(Intent(this,B_BasicInfoActivity::class.java).
                    putExtra("b_address",b_address_et.text.toString()).
                    putExtra("b_address1",b_addressline2_et.text.toString()).
                    putExtra("b_city",b_city_et.text.toString()).
                    putExtra("state",selected_state).
                    putExtra("country",selected_country).
                    putExtra("brandID",brandID)) }
                }
            }


    }

    private fun findIds() {
        //continue_bt = findViewById(R.id.continue_bt)
        back = findViewById(R.id.back)
        state_spinner = findViewById(R.id.state_spinner)
        country_spinner = findViewById(R.id.country_spinner)
        continue_bt = findViewById(R.id.continue_bt)
        b_address_et = findViewById(R.id.b_address_et)
        b_addressline2_et = findViewById(R.id.b_addressline2_et)
        b_city_et = findViewById(R.id.b_city_et)

        brandID = intent.getStringArrayListExtra("brandID")!!
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
                    selected_country = parent.selectedItem.toString()
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
                    selected_state = parent.selectedItem.toString()
                }
                //  userType = languages[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // write code to perform some action
            }
        }
    }

    override fun onQuestionariesSuccess(questionaries: Response<QuestionariesDataResponse>) {
        pd.dismiss()
        if (questionaries.isSuccessful)
        {
            countries = ArrayList()
            countries.add("-Select Country-")
            for (i in 0 until questionaries.body()?.data?.customer?.country?.size!!) {
                val title = questionaries.body()?.data?.customer?.country?.get(i)
                title?.name?.let { countries.add(it) }
            }
            setCountrySpinner(countries)



            state = ArrayList()
            stateName = ArrayList()
            stateName.add("-Select State-")
            for (i in 0 until questionaries.body()?.data?.customer!!.country?.get(0)?.state?.size!!) {
                val statename = questionaries.body()?.data?.customer!!.country?.get(0)?.state?.get(i)?.name
                stateName.add(statename.toString())
            }
            setStateSpinner(stateName)

        } else {
            utility.relative_snackbar(
                b_tell_us_more!!,
                questionaries.message(),
                getString(R.string.close_up)
            )
        }
    }

    override fun error(error: String?) {
        pd.dismiss()
        utility.relative_snackbar(
            b_tell_us_more!!,
            error,
            getString(R.string.close_up)
        )
    }
}