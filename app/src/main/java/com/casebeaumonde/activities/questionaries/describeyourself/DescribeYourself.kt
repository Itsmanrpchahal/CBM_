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
            startActivity(
                Intent(
                    this,
                    SelectStores::class.java
                )
            )
            finish()
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