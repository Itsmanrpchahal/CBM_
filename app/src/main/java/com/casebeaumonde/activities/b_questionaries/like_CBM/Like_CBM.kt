package com.casebeaumonde.activities.b_questionaries.like_CBM

import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.casebeaumonde.Controller.Controller
import com.casebeaumonde.R
import com.casebeaumonde.activities.b_questionaries.b_colorPicker.B_ColorPicker
import com.casebeaumonde.activities.b_questionaries.like_CBM.IF.SelectLike_IF
import com.casebeaumonde.activities.questionaries.reponse.QuestionariesDataResponse
import com.casebeaumonde.constants.BaseClass
import com.casebeaumonde.constants.Constants
import com.casebeaumonde.utilities.Utility
import kotlinx.android.synthetic.main.activity_select_brands.*
import kotlinx.android.synthetic.main.activity_select_stores.*
import retrofit2.Response

class Like_CBM : BaseClass(),SelectLike_IF  , Controller.QuestionariesAPI {


    private lateinit var continue_bt : LinearLayout
    private lateinit var recylerview : RecyclerView
    private lateinit var back : ImageButton
    private lateinit var utility: Utility
    private lateinit var pd : ProgressDialog
    private lateinit var controller: Controller
    private lateinit var likeCBM : ArrayList<String>
    private lateinit var selectedlikeCBM :ArrayList<String>

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
    private lateinit var elusivefashion:String
    private lateinit var fashionsense:String
    private lateinit var personalshopper : String
    private lateinit var yourexp : String
    private lateinit var meaningful : String
    private lateinit var spend:String
    private lateinit var impulseshopper: String
    private lateinit var lovetoshop: String
    private lateinit var drivetoshop: String
    private lateinit var selectedfashion : ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_like__c_b_m)

        findIds()
        listeners()

    }

    private fun setData(likeCBM: ArrayList<String>) {
        recylerview.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        val adapter = LikeCBM_Adapter(this,likeCBM)
        recylerview.adapter = adapter
    }

    private fun listeners() {
        continue_bt.setOnClickListener {
            if (selectedlikeCBM.size == 0)
            {
                Toast.makeText(this,"Select atleast one",Toast.LENGTH_SHORT).show()
            } else {
                startActivity(Intent(this, B_ColorPicker::class.java).
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
                putExtra("elusivefashion",elusivefashion).
                putExtra("fashionsense",fashionsense).
                putExtra("personalshopper",personalshopper).
                putExtra("yourexp",yourexp).
                putExtra("meaningful",meaningful).
                putExtra("spend",spend).
                putExtra("impulseshopper",impulseshopper).
                putExtra("lovetoshop",lovetoshop).
                putExtra("drivetoshop",drivetoshop).
                putExtra("fashionevents",selectedfashion).
                putExtra("likecbm",selectedlikeCBM))
            }

        }

        back.setOnClickListener {
            onBackPressed()
        }
    }

    companion object {
        var selectlikeIf :SelectLike_IF ? = null
    }

    private fun findIds() {
        continue_bt = findViewById(R.id.cbmcontinue_bt)
        recylerview = findViewById(R.id.recylerview)
        back = findViewById(R.id.back)

        selectedlikeCBM = ArrayList()
        selectlikeIf = this

        pd = ProgressDialog(this)
        pd!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        pd!!.window!!.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        pd!!.isIndeterminate = true
        pd!!.setCancelable(false)
        utility = Utility()
        controller = Controller()
        controller.Controller(this)

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
        elusivefashion = intent.getStringExtra("elusivefashion").toString()
        fashionsense = intent.getStringExtra("fashionsense").toString()
        personalshopper = intent.getStringExtra("personalshopper").toString()
        yourexp = intent.getStringExtra("yourexp").toString()
        meaningful = intent.getStringExtra("meaningful").toString()
        spend = intent.getStringExtra("spend").toString()
        impulseshopper = intent.getStringExtra("impulseshopper").toString()
        lovetoshop = intent.getStringExtra("lovetoshop").toString()
        drivetoshop = intent.getStringExtra("drivetoshop").toString()
        selectedfashion = intent.getStringArrayListExtra("fashionevents")!!

        if (utility.isConnectingToInternet(this))
        {
            pd.show()
            pd.setContentView(R.layout.loading)
            controller.Questionaries("Bearer "+getStringVal(Constants.TOKEN))
        } else {
            utility.relative_snackbar(
                parent_store!!,
                "No Internet Connectivity",
                getString(R.string.close_up)
            )
        }
    }

    override fun onQuestionariesSuccess(questionaries: Response<QuestionariesDataResponse>) {
        pd.dismiss()
        if (questionaries.isSuccessful)
        {
            likeCBM = ArrayList()
            for (i in 0 until questionaries.body()?.data?.customer?.fashionEvents?.size!!) {
                val title = questionaries.body()?.data?.customer?.likeCbm!!.get(i)
                likeCBM.add(title.toString())
            }

            setData(likeCBM)

//            brandsResponse = questionaries.body()?.data?.customer?.brand as ArrayList<QuestionariesDataResponse.Brand>
//            setFullData(brandsResponse)
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

    override fun getID(id: String?, s: String?) {
        if (s.equals("0")) {
            if (selectedlikeCBM.contains(id)) {
                selectedlikeCBM.remove(id)
            }
        } else {
            selectedlikeCBM.add(id.toString())
            Log.d("selectLike", "" + selectedlikeCBM)
        }
    }
}