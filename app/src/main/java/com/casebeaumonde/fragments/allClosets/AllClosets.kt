package com.casebeaumonde.fragments.allClosets

import android.app.Activity
import android.app.ProgressDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.EditText
import androidx.core.app.ActivityCompat.recreate
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.casebeaumonde.Controller.Controller
import com.casebeaumonde.R
import com.casebeaumonde.activities.ClosetItem.IF.ClosetItemID_IF
import com.casebeaumonde.activities.ClosetItem.response.AddToFavClosetItemResponse
import com.casebeaumonde.constants.BaseFrag
import com.casebeaumonde.constants.Constants
import com.casebeaumonde.fragments.allClosets.adapter.AllClosetsAdapter
import com.casebeaumonde.fragments.allClosets.response.AllClosetsResponse
import com.casebeaumonde.utilities.Utility
import kotlinx.android.synthetic.main.activity_closets_items.*
import kotlinx.android.synthetic.main.fragment_closets.*
import retrofit2.Response

class AllClosets : BaseFrag(),Controller.AllClosetsAPI,ClosetItemID_IF ,Controller.AddTofavClosetItemAPI{

    private lateinit var allclosets_recycler : RecyclerView
    private lateinit var utility: Utility
    private lateinit var pd: ProgressDialog
    private lateinit var controller: Controller
    private lateinit var search_ET: EditText
    private lateinit var response : ArrayList<AllClosetsResponse.Data.Closet>
    private lateinit var filterData : ArrayList<AllClosetsResponse.Data.Closet>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view : View
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_closets, container, false)
        findIds(view)
        closetitemidIf = this
        controller = Controller()
        controller.Controller(this,this)
        if (utility.isConnectingToInternet(context)) {
            pd.show()
            pd.setContentView(R.layout.loading)
            controller.AllClosets("Bearer " + getStringVal(Constants.TOKEN))
        } else {
            utility!!.relative_snackbar(
                parent_allClosets!!,
                getString(R.string.nointernet),
                getString(R.string.close_up)
            )
        }

        listeners()

        return view
    }

    companion object {
        var closetitemidIf : ClosetItemID_IF? = null
    }

    private fun findIds(view: View) {
        utility = Utility()
        pd = ProgressDialog(context)
        pd!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        pd!!.window!!.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        pd!!.isIndeterminate = true
        pd!!.setCancelable(false)
        allclosets_recycler = view.findViewById(R.id.allclosets_recycler)
        search_ET = view.findViewById(R.id.search_ET)
    }

    private fun listeners() {
        search_ET!!.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (count > 0) {
                    searchByTitle(s.toString())
                } else {
                    setFullData(response)
                }
            }

            override fun afterTextChanged(s: Editable) {
                if (s.isNotEmpty()) {

                    searchByTitle(s.toString())
                } else {
                    allclosets_recycler.visibility = View.VISIBLE
                    setFullData(response)
                }
            }

        })
    }

    private fun searchByTitle(s: String?) {
        filterData = ArrayList()
        if (response.size > 0) {
            for (i in response!!.indices) {
                val closetModel = response!![i]
                if (closetModel.title!!.toLowerCase().contains(s!!.toLowerCase()))
                    filterData!!.add(closetModel)

                if (filterData.size > 0) {
                    allclosets_recycler.layoutManager =
                        LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                    val adapter = AllClosetsAdapter(context!!, filterData!!,
                        getStringVal(Constants.USERID).toString()
                    )
                    allclosets_recycler.adapter = adapter
                }
            }

            if (filterData.size == 0) {
                allclosets_recycler.visibility = View.GONE
            }
        }
    }

    override fun onAllEventsSuccess(allClosetsResponse: Response<AllClosetsResponse>) {
       pd.dismiss()
        if (allClosetsResponse.isSuccessful)
        {

            response = allClosetsResponse.body()?.data?.closet as ArrayList<AllClosetsResponse.Data.Closet>

            setFullData(response)
        }else{
            utility!!.relative_snackbar(
                parent_allClosets!!,
                allClosetsResponse.message(),
                getString(R.string.close_up)
            )
        }
    }

    private fun setFullData(response: ArrayList<AllClosetsResponse.Data.Closet>) {
        allclosets_recycler.visibility = View.VISIBLE
        //response = closets
        allclosets_recycler.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        val adapter = AllClosetsAdapter(context!!, response!!,
            getStringVal(Constants.USERID).toString()
        )
        allclosets_recycler.adapter = adapter
    }

    override fun onAddToFavClosetItemSuccess(addToFavClosetItemResponse: Response<AddToFavClosetItemResponse>) {
        if (addToFavClosetItemResponse.isSuccessful)
        {
            pd.dismiss()
        }else{
            pd.dismiss()
            recreate(context as Activity)
            utility!!.relative_snackbar(parent_closetsItems!!, addToFavClosetItemResponse.message(), getString(R.string.close_up))
        }
    }

    override fun error(error: String?) {
      pd.dismiss()
        utility!!.relative_snackbar(
            parent_allClosets!!,
            error,
            getString(R.string.close_up)
        )
    }

    override fun getClosetID(id: String?) {
        if (utility.isConnectingToInternet(context)) {
            pd.show()
            pd.setContentView(R.layout.loading)
            controller.AddToFavClosetItem("Bearer "+getStringVal(Constants.TOKEN),id,"closet")

        }else {
            utility.relative_snackbar(parent_allClosets!!, "No Internet Connectivity", getString(R.string.close_up))
        }
    }

}