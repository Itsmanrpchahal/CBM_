package com.casebeaumonde.activities.b_questionaries.b_basicInfo1

import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.*
import com.casebeaumonde.Controller.Controller
import com.casebeaumonde.R
import com.casebeaumonde.activities.b_questionaries.fashionevents.B_FashionEventsActivity
import com.casebeaumonde.activities.questionaries.reponse.QuestionariesDataResponse
import com.casebeaumonde.constants.BaseClass
import com.casebeaumonde.constants.Constants
import com.casebeaumonde.utilities.Utility
import kotlinx.android.synthetic.main.activity_b__basic_info1.*
import kotlinx.android.synthetic.main.activity_select_brands.*
import retrofit2.Response

class B_BasicInfoActivity1 : BaseClass(), Controller.QuestionariesAPI {
    private lateinit var continue_bt: LinearLayout
    private lateinit var spendclothing_spinner: Spinner
    private lateinit var driveyou_spinner: Spinner
    private lateinit var utility: Utility
    private lateinit var back: ImageButton
    private lateinit var pd: ProgressDialog
    private lateinit var controller: Controller
    private lateinit var drivetoshop: ArrayList<String>
    private lateinit var elusivefashion: EditText
    private lateinit var fashionsense: EditText
    private lateinit var yes_checkbox: CheckBox
    private lateinit var no_checkbox: CheckBox
    private var personalShopper: String ="YES"
    private var impulseShopper: String ="YES"
    private var lovetoShop: String ="YES"
    private lateinit var spendMoney :String
    private lateinit var driveToShop :String
    private lateinit var yes1_checkboxshopper: CheckBox
    private lateinit var no1_checkboxshopper: CheckBox
    private lateinit var experience: EditText
    private lateinit var experienceMeaningflu: EditText
    private lateinit var yes2_checkboxlovetoshop: CheckBox
    private lateinit var no2_checkboxlovetoshop: CheckBox

    private lateinit var b_address: String
    private lateinit var b_address1: String
    private lateinit var b_city: String
    private lateinit var state: String
    private lateinit var country: String
    private lateinit var brandID: ArrayList<String>
    private lateinit var basic1: String
    private lateinit var basic2: String
    private lateinit var basic3: String
    private lateinit var basic4: String
    private lateinit var basic5: String
    private lateinit var basic6: String
    private lateinit var basic7: String
    private lateinit var basic8: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_b__basic_info1)

        findIds()
        listeners()
        setSpinnerData()
    }

    private fun listeners() {
        continue_bt.setOnClickListener {
            when {
                elusivefashion.text.isEmpty() -> {
                    elusivefashion.requestFocus()
                    elusivefashion.error = "Required"
                }

                fashionsense.text.isEmpty() -> {
                    fashionsense.requestFocus()
                    fashionsense.error = "Required"
                }

                experienceMeaningflu.text.isEmpty() -> {
                    experienceMeaningflu.requestFocus()
                    experienceMeaningflu.error = "Required"
                }

                experience.text.isEmpty() && personalShopper.toString().equals("YES") -> {
                 experience.requestFocus()
                 experience.error ="Required"
                }
                else -> {
                    startActivity(Intent(this, B_FashionEventsActivity::class.java).
                    putExtra("b_address",b_address).
                    putExtra("b_address1",b_address1).
                    putExtra("b_city",b_city).
                    putExtra("state",state).
                    putExtra("country",country).
                    putExtra("brandID",brandID).
                    putExtra("basic1",basic1).
                    putExtra("basic2",basic2).
                    putExtra("basic3",basic3).
                    putExtra("basic4",basic4).
                    putExtra("basic5",basic5).
                    putExtra("basic6",basic6).
                    putExtra("basic7",basic7).
                    putExtra("basic8",basic8).
                    putExtra("elusivefashion",elusivefashion.text.toString()).
                    putExtra("fashionsense",fashionsense.text.toString()).
                    putExtra("personalshopper",personalShopper).
                    putExtra("yourexp",experience.text.toString()).
                    putExtra("meaningful",experienceMeaningflu.text.toString()).
                    putExtra("spend",spendMoney).
                    putExtra("impulseshopper",impulseShopper).
                    putExtra("lovetoshop",lovetoShop).
                    putExtra("drivetoshop",driveToShop))
                }
            }

        }

        back.setOnClickListener {
            onBackPressed()
        }

        yes_checkbox.setOnCheckedChangeListener { buttonView, isChecked ->
            if (buttonView.isChecked) {
                personalShopper = "YES"
                no_checkbox.isChecked = false
            }
        }

        no_checkbox.setOnCheckedChangeListener { buttonView, isChecked ->
            if (buttonView.isChecked) {
                personalShopper = "NO"
                yes_checkbox.isChecked = false
            }
        }

        yes1_checkboxshopper.setOnCheckedChangeListener { buttonView, isChecked ->
            if (buttonView.isChecked) {
                impulseShopper = "YES"
                no1_checkboxshopper.isChecked = false
            }
        }

        no1_checkboxshopper.setOnCheckedChangeListener { buttonView, isChecked ->
            if (buttonView.isChecked) {
                impulseShopper = "NO"
                yes1_checkboxshopper.isChecked = false
            }
        }

        yes2_checkboxlovetoshop.setOnCheckedChangeListener { buttonView, isChecked ->
            if (buttonView.isChecked) {
                lovetoShop = "YES"
                no2_checkboxlovetoshop.isChecked = false
            }
        }


        no2_checkboxlovetoshop.setOnCheckedChangeListener { buttonView, isChecked ->
            if (buttonView.isChecked) {
                lovetoShop = "NO"
                yes2_checkboxlovetoshop.isChecked = false
            }
        }

    }

    private fun findIds() {
        continue_bt = findViewById(R.id.continue_bt)
        back = findViewById(R.id.back)
        spendclothing_spinner = findViewById(R.id.spendclothing_spinner)
        driveyou_spinner = findViewById(R.id.driveyou_spinner)
        elusivefashion = findViewById(R.id.basic1_et1)
        fashionsense = findViewById(R.id.basic1_et2)
        yes_checkbox = findViewById(R.id.yes_checkbox)
        no_checkbox = findViewById(R.id.no_checkbox)
        yes1_checkboxshopper = findViewById(R.id.yes1_checkboxshopper)
        no1_checkboxshopper = findViewById(R.id.no1_checkboxshopper)
        experience = findViewById(R.id.basic1_et3)
        experienceMeaningflu = findViewById(R.id.basic1_et4)
        yes2_checkboxlovetoshop = findViewById(R.id.yes2_checkboxlovetoshop)
        no2_checkboxlovetoshop = findViewById(R.id.no2_checkboxlovetoshop)

        utility = Utility()
        controller = Controller()
        controller.Controller(this)
        pd = ProgressDialog(this)

        pd!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        pd!!.window!!.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        pd!!.isIndeterminate = true
        pd!!.setCancelable(false)


        b_address = intent.getStringExtra("b_address").toString()
        b_address1 = intent.getStringExtra("b_address1").toString()
        b_city = intent.getStringExtra("b_city").toString()
        state = intent.getStringExtra("state").toString()
        country = intent.getStringExtra("country").toString()
        brandID = intent.getStringArrayListExtra("brandID")!!
        basic1 = intent.getStringExtra("basic1").toString()
        basic2 = intent.getStringExtra("basic2").toString()
        basic3 = intent.getStringExtra("basic3").toString()
        basic4 = intent.getStringExtra("basic4").toString()
        basic5 = intent.getStringExtra("basic5").toString()
        basic6 = intent.getStringExtra("basic6").toString()
        basic7 = intent.getStringExtra("basic7").toString()
        basic8 = intent.getStringExtra("basic8").toString()

        if (utility.isConnectingToInternet(this)) {
            pd.show()
            pd.setContentView(R.layout.loading)
            controller.Questionaries("Bearer " + getStringVal(Constants.TOKEN))
        } else {
            utility.relative_snackbar(
                parent_b_baisic1!!,
                "No Internet Connectivity",
                getString(R.string.close_up)
            )
        }
    }

    private fun setSpinnerData() {

//        business_team_spinner.prompt = "Select"
        val languages = resources.getStringArray(R.array.Spend)
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
                spendMoney = parent.selectedItem.toString()
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

    private fun setdrivestoshopSpinnerData(drivetoshop: ArrayList<String>) {

        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_dropdown_item, drivetoshop
        )
        driveyou_spinner.adapter = adapter
        driveyou_spinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View, position: Int, id: Long
            ) {
                driveToShop = parent.selectedItem.toString()
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

    override fun onQuestionariesSuccess(questionaries: Response<QuestionariesDataResponse>) {
        pd.dismiss()
        if (questionaries.isSuccessful) {
            drivetoshop = ArrayList()
            drivetoshop = questionaries.body()?.data?.customer?.drivesToShop as ArrayList<String>

//            brandsResponse = questionaries.body()?.data?.customer?.brand as ArrayList<QuestionariesDataResponse.Data.Customer.Brand>
//            setFullData(brandsResponse)
            setdrivestoshopSpinnerData(drivetoshop)
        } else {
            utility.relative_snackbar(
                parent_b_baisic1!!,
                questionaries.message(),
                getString(R.string.close_up)
            )
        }
    }

    override fun error(error: String?) {
        pd.dismiss()
        utility.relative_snackbar(
            parent_b_baisic1!!,
            error,
            getString(R.string.close_up)
        )
    }

}