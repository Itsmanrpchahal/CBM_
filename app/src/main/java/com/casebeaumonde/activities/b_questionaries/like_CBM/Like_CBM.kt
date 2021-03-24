package com.casebeaumonde.activities.b_questionaries.like_CBM

import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.WindowManager
import android.widget.ImageButton
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.casebeaumonde.Controller.Controller
import com.casebeaumonde.R
import com.casebeaumonde.activities.b_questionaries.b_colorPicker.B_ColorPicker
import com.casebeaumonde.activities.questionaries.reponse.QuestionariesDataResponse
import com.casebeaumonde.constants.BaseClass
import com.casebeaumonde.constants.Constants
import com.casebeaumonde.utilities.Utility
import kotlinx.android.synthetic.main.activity_select_brands.*
import kotlinx.android.synthetic.main.activity_select_stores.*
import retrofit2.Response

class Like_CBM : BaseClass()  , Controller.QuestionariesAPI {


    private lateinit var continue_bt : LinearLayout
    private lateinit var recylerview : RecyclerView
    private lateinit var back : ImageButton
    private lateinit var utility: Utility
    private lateinit var pd : ProgressDialog
    private lateinit var controller: Controller
    private lateinit var likeCBM : ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_like__c_b_m)

        findIds()
        listeners()

    }

    private fun setData(likeCBM: ArrayList<String>) {
        recylerview.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        val adapter = LikeCBM_Adapter(this)
        recylerview.adapter = adapter
    }

    private fun listeners() {
        continue_bt.setOnClickListener {
            startActivity(Intent(this, B_ColorPicker::class.java))
        }

        back.setOnClickListener {
            onBackPressed()
        }
    }

    private fun findIds() {
        continue_bt = findViewById(R.id.cbmcontinue_bt)
        recylerview = findViewById(R.id.recylerview)
        back = findViewById(R.id.back)

        pd = ProgressDialog(this)
        pd!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        pd!!.window!!.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        pd!!.isIndeterminate = true
        pd!!.setCancelable(false)
        utility = Utility()
        controller = Controller()
        controller.Controller(this)

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
                val title = questionaries.body()?.data?.customer?.fashionEvents!!.get(i)
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
}