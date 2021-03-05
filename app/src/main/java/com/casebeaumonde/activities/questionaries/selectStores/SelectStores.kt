package com.casebeaumonde.activities.questionaries.selectStores

import android.app.ProgressDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.casebeaumonde.Controller.Controller
import com.casebeaumonde.R
import com.casebeaumonde.activities.questionaries.describeyourself.adapter.DescribeYourselfAdapter
import com.casebeaumonde.activities.questionaries.reponse.QuestionariesDataResponse
import com.casebeaumonde.activities.questionaries.selectbrands.adapter.DataAdapter
import com.casebeaumonde.constants.BaseClass
import com.casebeaumonde.constants.Constants
import com.casebeaumonde.constants.Data
import com.casebeaumonde.utilities.Utility
import fastscroll.app.fastscrollalphabetindex.AlphabetIndexFastScrollRecyclerView
import kotlinx.android.synthetic.main.activity_describe_yourself.*
import kotlinx.android.synthetic.main.activity_select_brands.*
import kotlinx.android.synthetic.main.activity_select_brands.parent_brands
import kotlinx.android.synthetic.main.activity_select_stores.*
import retrofit2.Response
import java.util.*
import kotlin.collections.ArrayList

class SelectStores : BaseClass() ,SelectedStores_IF, Controller.QuestionariesAPI{
    private lateinit var dataList : ArrayList<Data>
    private lateinit var recyclerView : AlphabetIndexFastScrollRecyclerView
    private lateinit var pd : ProgressDialog
    private lateinit var controller: Controller
    private lateinit var utility: Utility
    private lateinit var search_ET: EditText
    private lateinit var back : ImageButton
    private lateinit var stores : ArrayList<String>
    private lateinit var filterStores : ArrayList<String>
    private lateinit var selectedStores : ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_stores)

        dataList = java.util.ArrayList()
        selectedStores = ArrayList()
        findIds()
        selectedstoresIf = this
        listeners()
        Collections.sort(dataList, Data.titleNameComparator)
        recyclerView.layoutManager = GridLayoutManager(this,2)
//        val adapter =
//            DataAdapter(
//                dataList,
//                this
//            )
//        recyclerView.adapter = adapter
//        recyclerView.setIndexTextSize(12)
//        recyclerView.setIndexBarTextColor("#FFFFFF")
//        recyclerView.setIndexBarColor("#cdced2")
//        recyclerView.setIndexbarHighLateTextColor("#FF4081")
//        recyclerView.setIndexBarHighLateTextVisibility(true)
//        recyclerView.setIndexBarTransparentValue(1.0.toFloat())
    }

    private fun listeners() {
        search_ET!!.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (count > 0) {
                    searchByTitle(s.toString())
                } else {
                    setFullData(stores)
                }
            }

            override fun afterTextChanged(s: Editable) {
                if (s.isNotEmpty()) {

                    searchByTitle(s.toString())
                } else {
                    recyclerView.visibility = View.VISIBLE
                    setFullData(filterStores)
                }
            }

        })

        back.setOnClickListener {
            onBackPressed()
        }
    }

    companion object {
        lateinit var selectedstoresIf: SelectedStores_IF
    }

    private fun setFullData(closets: ArrayList<String>) {
        recyclerView.layoutManager = GridLayoutManager(this,3)
        //user_recyler.addItemDecoration(GridItemDecoration(10, 2))
        val adapter = DataAdapter1(closets,this)
        recyclerView.adapter = adapter
    }

    fun searchByTitle(s: String) {

        filterStores = ArrayList()
        //filterBrand = ArrayList()
        if (stores.size > 0) {
            for (i in stores!!.indices) {
                val closetModel = stores!![i]
                if (closetModel!!.toLowerCase().contains(s.toLowerCase()))
                    filterStores!!.add(closetModel)

                if (stores.size > 0) {
                    recyclerView.layoutManager =
                        LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
                    //closets =  response
                    recyclerView.layoutManager = GridLayoutManager(this, 2)
                    val adapter =
                        DataAdapter1(
                            filterStores,
                            this
                        )
                    recyclerView.adapter = adapter
                }
            }
            if (stores.size == 0) {
                recyclerView.visibility = View.GONE
            }
        }
    }

    private fun findIds() {
        recyclerView = findViewById(R.id.recyclerView)
        search_ET = findViewById(R.id.search_ET)
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
                parent_store!!,
                "No Internet Connectivity",
                getString(R.string.close_up)
            )
        }
    }



    override fun onQuestionariesSuccess(questionaries: Response<QuestionariesDataResponse>) {
        pd.dismiss()
        stores = ArrayList()
        if (questionaries.isSuccessful)
        {
            for (i in 0 until questionaries.body()?.data?.customer?.stores?.size!!) {
                val title = questionaries.body()?.data?.customer?.stores?.get(i)
                stores.add(title.toString())
            }
            setFullData(stores)
        } else {
            utility.relative_snackbar(
                parent_store!!,
                questionaries.message(),
                getString(R.string.close_up)
            )
        }
    }

    override fun error(error: String?) {
        pd.dismiss()
        utility.relative_snackbar(
            parent_store!!,
            error,
            getString(R.string.close_up)
        )
    }

    override fun getID(id: String?, s: String?) {
        if (s.equals("0")) {
            if (selectedStores.contains(id)) {
                selectedStores.remove(id)
            }
        } else {
            selectedStores.add(id.toString())
            Log.d("selectStores", "" + selectedStores)
        }
    }
}