package com.casebeaumonde.activities.b_questionaries.selectbrands

import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.EditText
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.casebeaumonde.Controller.Controller
import com.casebeaumonde.R
import com.casebeaumonde.activities.b_questionaries.selectbrands.IF.B_SelectedBrand_IF
import com.casebeaumonde.activities.b_questionaries.selectbrands.adapter.B_DataAdapter
import com.casebeaumonde.activities.b_questionaries.tellusmore.B_Tell_US_MORE
import com.casebeaumonde.activities.questionaries.describeyourself.DescribeYourself
import com.casebeaumonde.activities.questionaries.reponse.QuestionariesDataResponse
import com.casebeaumonde.constants.BaseClass
import com.casebeaumonde.constants.Constants
import com.casebeaumonde.constants.Data
import com.casebeaumonde.utilities.Utility
import fastscroll.app.fastscrollalphabetindex.AlphabetIndexFastScrollRecyclerView
import kotlinx.android.synthetic.main.activity_select_brands.*
import retrofit2.Response

class B_SelectBrands : BaseClass() , B_SelectedBrand_IF, Controller.QuestionariesAPI{

    private lateinit var dataList: ArrayList<Data>
    private lateinit var recyclerView: AlphabetIndexFastScrollRecyclerView
    private lateinit var selectedBrands: ArrayList<String>
    private lateinit var continue_bt: LinearLayout
    private lateinit var back: ImageButton
    private lateinit var brands :ArrayList<String>
    private lateinit var brandsID : ArrayList<String>
    private lateinit var pojo : ArrayList<Data>
    private lateinit var pd : ProgressDialog
    private lateinit var controller: Controller
    private lateinit var search_ET: EditText
    private lateinit var utility: Utility
    private lateinit var brandsResponse : ArrayList<QuestionariesDataResponse.Data.Customer.Brand>
    private lateinit var filterBrand : ArrayList<QuestionariesDataResponse.Data.Customer.Brand>
    private lateinit var height_et : EditText
    private var name: String = ""
    private var city: String = ""
    private var state: String = ""
    private var country: String = ""
    private var mobile: String = ""
    private var age: String = ""
    private var month: String = ""
    private var date: String = ""
    private var year: String = ""
    private var astrologicalSign: String = ""
    private var bodyType : String = ""
    private lateinit var colorscode : ArrayList<String>
    private var bodyType_text : String = ""
    private var height : String =""
    private var eycolor : String =""
    private var haircolor : String =""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_b__select_brands)

        dataList = java.util.ArrayList()
        selectedBrands = ArrayList()
        findIds()

        bB_SelectedBrand_IF = this

        listeners()
    }


    private fun listeners() {
        continue_bt.setOnClickListener {

            if (selectedBrands.size==0)
            {
                Toast.makeText(this,"Select atleast one brand", Toast.LENGTH_SHORT).show()
            } else {
                startActivity(
                    Intent(
                        this,
                        B_Tell_US_MORE::class.java
                    ).putExtra("brandID",selectedBrands)
                )
            }

        }

        back.setOnClickListener {
            onBackPressed()
        }

        search_ET!!.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (count > 0) {
                    searchByTitle(s.toString())
                } else {
                    setFullData(brandsResponse)
                }
            }

            override fun afterTextChanged(s: Editable) {
                if (s.isNotEmpty()) {

                    searchByTitle(s.toString())
                } else {
                    recyclerView.visibility = View.VISIBLE
                    setFullData(filterBrand)
                }
            }

        })
    }

    private fun setFullData(closets: ArrayList<QuestionariesDataResponse.Data.Customer.Brand>) {
        brandsResponse =
            closets
        recyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = GridLayoutManager(this, 2)
        val adapter =
            B_DataAdapter(
                closets,
                this
            )
        recyclerView.adapter = adapter
    }

    fun searchByTitle(s: String) {

        filterBrand = ArrayList()
        //filterBrand = ArrayList()
        if (brandsResponse.size > 0) {
            for (i in brandsResponse!!.indices) {
                val closetModel = brandsResponse!![i]
                if (closetModel.name!!.toLowerCase().contains(s.toLowerCase()))
                    filterBrand!!.add(closetModel)

                if (brandsResponse.size > 0) {
                    recyclerView.layoutManager =
                        LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
                    //closets =  response
                    recyclerView.layoutManager = GridLayoutManager(this, 2)
                    val adapter =
                        B_DataAdapter(
                            filterBrand,
                            this
                        )
                    recyclerView.adapter = adapter
                }
            }
            if (brandsResponse.size == 0) {
                recyclerView.visibility = View.GONE
            }
        }
    }

    private fun findIds() {
        recyclerView = findViewById(R.id.recyclerView)
        continue_bt = findViewById(R.id.continue_bt)
        back = findViewById(R.id.back)
        search_ET = findViewById(R.id.search_ET)

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
                parent_brands!!,
                "No Internet Connectivity",
                getString(R.string.close_up)
            )
        }
    }


    companion object {
        lateinit var bB_SelectedBrand_IF: B_SelectedBrand_IF
    }

    override fun getID(id: String?, s: String?) {
        if (s.equals("0")) {
            if (selectedBrands.contains(id)) {
                selectedBrands.remove(id)
            }
        } else {
            selectedBrands.add(id.toString())
            Log.d("selectBrands", "" + selectedBrands)
        }
    }

    override fun onQuestionariesSuccess(questionaries: Response<QuestionariesDataResponse>) {
        pd.dismiss()
        if (questionaries.isSuccessful)
        {
            brandsResponse = questionaries.body()?.data?.customer?.brand as ArrayList<QuestionariesDataResponse.Data.Customer.Brand>
            setFullData(brandsResponse)
        } else {
            utility.relative_snackbar(
                parent_brands!!,
                questionaries.message(),
                getString(R.string.close_up)
            )
        }
    }

    override fun error(error: String?) {
        pd.dismiss()
        utility.relative_snackbar(
            parent_brands!!,
            error,
            getString(R.string.close_up)
        )
    }
}