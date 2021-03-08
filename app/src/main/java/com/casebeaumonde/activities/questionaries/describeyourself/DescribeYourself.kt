package com.casebeaumonde.activities.questionaries.describeyourself

import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.casebeaumonde.Controller.Controller
import com.casebeaumonde.R
import com.casebeaumonde.activities.questionaries.describeyourself.IF.SelectYourself_IF
import com.casebeaumonde.activities.questionaries.describeyourself.adapter.DescribeYourselfAdapter
import com.casebeaumonde.activities.questionaries.reponse.QuestionariesDataResponse
import com.casebeaumonde.activities.questionaries.selectStores.SelectStores
import com.casebeaumonde.activities.questionaries.selectbrands.adapter.DataAdapter
import com.casebeaumonde.constants.BaseClass
import com.casebeaumonde.constants.Constants
import com.casebeaumonde.fragments.users.adapter.UsersAdapter
import com.casebeaumonde.utilities.Utility
import kotlinx.android.synthetic.main.activity_describe_yourself.*
import kotlinx.android.synthetic.main.activity_select_brands.*
import kotlinx.android.synthetic.main.activity_select_brands.parent_brands
import retrofit2.Response

class DescribeYourself : BaseClass(), SelectYourself_IF , Controller.QuestionariesAPI  {

    private lateinit var recylerview : RecyclerView
    private lateinit var  back : ImageButton
    private lateinit var selectIds : ArrayList<String>
    private lateinit var pd : ProgressDialog
    private lateinit var continue_bt: LinearLayout
    private lateinit var controller: Controller
    private lateinit var utility: Utility
    private lateinit var characterstic : ArrayList<String>
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
    private lateinit var brandsID : ArrayList<String>
    private lateinit var images : ArrayList<String>
    private lateinit var imageData : ArrayList<String>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_describe_yourself)

        findIds()
        listeners()
        selectIds = ArrayList()

        selectyourselfIf = this
    }

    private fun listeners() {
        continue_bt.setOnClickListener {
            if (selectIds.size==0)
            {
                Toast.makeText(this,"Select atleast on characterstics",Toast.LENGTH_SHORT).show()
            } else {
                startActivity(
                    Intent(
                        this,
                        SelectStores::class.java
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
                        putStringArrayListExtra("brandsID",brandsID).
                        putStringArrayListExtra("yourself",selectIds).
                            putStringArrayListExtra("imageData",imageData)
                )
            }

        }
        back.setOnClickListener {
            onBackPressed()
        }

    }

    private fun findIds() {
        continue_bt = findViewById(R.id.continue_bt)
        recylerview = findViewById(R.id.recylerview)
        back = findViewById(R.id.back)
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
                parent_describeyourself!!,
                "No Internet Connectivity",
                getString(R.string.close_up)
            )
        }
brandsID = ArrayList()
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
        brandsID = intent.getStringArrayListExtra("brandsID")!!
        images = intent.getStringArrayListExtra("images")!!
        imageData = intent.getStringArrayListExtra("imageData")!!
    }

    private fun setFullData(characterStic : ArrayList<String>) {

        recylerview.layoutManager = GridLayoutManager(this,3)
        //user_recyler.addItemDecoration(GridItemDecoration(10, 2))
        val adapter = DescribeYourselfAdapter(this,characterStic)
        recylerview.adapter = adapter
    }

    companion object {
        lateinit var selectyourselfIf: SelectYourself_IF
    }

    override fun getID(id: String?, s: String?) {
        if (s.equals("0")) {
            if (selectIds.contains(id)) {
                selectIds.remove(id)
            }
        } else {
            selectIds.add(id.toString())
            Log.d("yourself",""+selectIds)
        }
    }

    override fun onQuestionariesSuccess(questionaries: Response<QuestionariesDataResponse>) {
        pd.dismiss()
        characterstic = ArrayList()
        if (questionaries.isSuccessful)
        {
            for (i in 0 until questionaries.body()?.data?.customer?.characterstic?.size!!) {
                val title = questionaries.body()?.data?.customer?.characterstic?.get(i)
                characterstic.add(title.toString())
            }
            setFullData(characterstic)
        } else {
            utility.relative_snackbar(
                parent_describeyourself!!,
                questionaries.message(),
                getString(R.string.close_up)
            )
        }
    }

    override fun error(error: String?) {
        pd.dismiss()
        utility.relative_snackbar(
            parent_describeyourself!!,
            error,
            getString(R.string.close_up)
        )
    }
}