package com.casebeaumonde.activities.questionaries.selectbrands

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
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.casebeaumonde.Controller.Controller
import com.casebeaumonde.MainActivity
import com.casebeaumonde.R
import com.casebeaumonde.activities.ClosetItem.IF.SelectedCloset_ID
import com.casebeaumonde.activities.myclosets.adapter.MyClosetsAdapter
import com.casebeaumonde.activities.myclosets.response.MyClosetsResponse
import com.casebeaumonde.activities.questionaries.describeyourself.DescribeYourself
import com.casebeaumonde.activities.questionaries.reponse.QuestionariesDataResponse
import com.casebeaumonde.activities.questionaries.selectbrands.IF.SelectedBrand_IF
import com.casebeaumonde.activities.questionaries.selectbrands.adapter.DataAdapter
import com.casebeaumonde.constants.BaseClass
import com.casebeaumonde.constants.Constants
import com.casebeaumonde.constants.Data
import com.casebeaumonde.utilities.Utility
import com.stripe.okhttp3.MultipartBody
import fastscroll.app.fastscrollalphabetindex.AlphabetIndexFastScrollRecyclerView
import kotlinx.android.synthetic.main.activity_select_brands.*
import kotlinx.android.synthetic.main.activity_upload_body_type_image.*
import kotlinx.android.synthetic.main.activity_upload_body_type_image.parent_uploadbody
import retrofit2.Response
import java.util.*
import kotlin.collections.ArrayList


class SelectBrands : BaseClass(), SelectedBrand_IF , Controller.QuestionariesAPI {

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
    private lateinit var images : ArrayList<String>
    private lateinit var imageData : ArrayList<String>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_brands)
        dataList = java.util.ArrayList()
        selectedBrands = ArrayList()
        findIds()
       // Collections.sort(brands, QuestionariesDataResponse.Brand)

//        recyclerView.setIndexTextSize(12)
//        recyclerView.setIndexBarTextColor("#FFFFFF")
//        recyclerView.setIndexBarColor("#cdced2")
//        recyclerView.setIndexbarHighLateTextColor("#FFFFFF")
//        recyclerView.setIndexBarHighLateTextVisibility(true)
//        recyclerView.setIndexBarTransparentValue(1.0.toFloat())
       //adapter.notifyDataSetChanged()

        selectedbrandIf = this

        listeners()
    }

    private fun listeners() {
        continue_bt.setOnClickListener {

            if (selectedBrands.size==0)
            {
                Toast.makeText(this,"Select atleast one brand",Toast.LENGTH_SHORT).show()
            } else {
                startActivity(
                    Intent(
                        this,
                        DescribeYourself::class.java
                    ).
                    putExtra("name", name)
                        .putExtra("city", city).putExtra("state", state)
                        .putExtra("country", country).putExtra("mobile", mobile)
                        .putExtra("age", age).putExtra("month", month).putExtra("date", date)
                        .putExtra("year", year).putExtra("astrologicalSign", astrologicalSign)
                        .putStringArrayListExtra("colorscode", colorscode)
                        .putExtra("bodyType",bodyType)
                        .putExtra("bodyType_text",bodyType_text)
                        .putStringArrayListExtra("images",images).
                        putExtra("height",height).
                        putExtra("eycolor",eycolor).
                        putExtra("haircolor",haircolor).
                        putStringArrayListExtra("brandsID",selectedBrands).
                            putExtra("imageData",imageData)
                )
                finish()
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
            DataAdapter(
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
                        DataAdapter(
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
        images = intent.getStringArrayListExtra("images")!!
        eycolor = intent.getStringExtra("eycolor").toString()
        haircolor = intent.getStringExtra("haircolor").toString()

        name = intent.getStringExtra("name").toString()
        city = intent.getStringExtra("city").toString()
        state = intent.getStringExtra("state").toString()
        country = intent.getStringExtra("country").toString()
        mobile = intent.getStringExtra("mobile").toString()
        age = intent.getStringExtra("age").toString()
        month = intent.getStringExtra("month").toString()
        date = intent.getStringExtra("date").toString()
        year = intent.getStringExtra("year").toString()
        astrologicalSign = intent.getStringExtra("astrologicalSign").toString()
        colorscode = intent.getStringArrayListExtra("colorscode")!!
        bodyType = intent.getStringExtra("bodyType").toString()
        bodyType_text = intent.getStringExtra("bodyType_text").toString()
        height = intent.getStringExtra("height").toString()
        eycolor = intent.getStringExtra("eycolor").toString()
        haircolor = intent.getStringExtra("haircolor").toString()
        imageData = intent.getStringArrayListExtra("imageData")!!
        Log.d("partsize",""+imageData)
    }


    companion object {
        lateinit var selectedbrandIf: SelectedBrand_IF
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