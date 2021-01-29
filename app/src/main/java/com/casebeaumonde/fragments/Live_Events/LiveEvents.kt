package com.casebeaumonde.fragments.Live_Events

import android.app.ProgressDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.*
import androidx.appcompat.widget.AppCompatSpinner
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.casebeaumonde.Controller.Controller
import com.casebeaumonde.R
import com.casebeaumonde.activities.myclosets.adapter.MyClosetsAdapter
import com.casebeaumonde.activities.myclosets.response.MyClosetsResponse
import com.casebeaumonde.constants.BaseFrag
import com.casebeaumonde.constants.Constants
import com.casebeaumonde.constants.GridItemDecoration
import com.casebeaumonde.fragments.HireExpert.IF.HireExpertIF
import com.casebeaumonde.fragments.Live_Events.IF.LiveEventIF
import com.casebeaumonde.fragments.Live_Events.adapter.LiveEventsAdapter
import com.casebeaumonde.fragments.Live_Events.response.FavLiveEventResponse
import com.casebeaumonde.fragments.Live_Events.response.LiveEventsResponse
import com.casebeaumonde.fragments.designers.adapter.DesignerAdapter
import com.casebeaumonde.utilities.Utility
import kotlinx.android.synthetic.main.fragment_designers.*
import kotlinx.android.synthetic.main.fragment_live_events.*
import retrofit2.Response
import kotlin.properties.Delegates

class LiveEvents : BaseFrag(), Controller.LiveEventsAPI ,LiveEventIF,Controller.FavLiveEventAPI{

    private lateinit var utility: Utility
    private lateinit var pd: ProgressDialog
    private lateinit var live_event_spinner: AppCompatSpinner
    private lateinit var controller: Controller
    private lateinit var live_events_recycler: RecyclerView
    private lateinit var spinnertitle: TextView
    private lateinit var response: ArrayList<LiveEventsResponse.Event>
    private lateinit var filterdata: ArrayList<LiveEventsResponse.Event>
    private lateinit var search_ET: EditText
    private lateinit var type: String
    private var count : Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_live_events, container, false)
        findIds(view)
        liveEventIF = this
        controller = Controller()
        controller.Controller(this,this)

        if (utility.isConnectingToInternet(context)) {
            pd.show()
            pd.setContentView(R.layout.loading)
            controller.LiveEvents("Bearer " + getStringVal(Constants.TOKEN))
        } else {
            utility!!.relative_snackbar(
                parent_liveevents!!,
                getString(R.string.nointernet),
                getString(R.string.close_up)
            )
        }

        setSpinnerData()
        listeners()

        return view
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
                    live_events_recycler.visibility = View.VISIBLE
                    setFullData(response)
                }
            }

        })
    }

    private fun setSpinnerData() {
        val search = resources.getStringArray(R.array.SearchEvent)
        val adapter = ArrayAdapter(
            context!!,
            android.R.layout.simple_spinner_dropdown_item, search
        )
        live_event_spinner.adapter = adapter
        live_event_spinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View, position: Int, id: Long
            ) {
               pd.show()
                if (!!search[position].equals("All"))
                {
                    controller.LiveEvents("Bearer " + getStringVal(Constants.TOKEN))
                }
                else{
                    controller.LiveEvents("Bearer " + getStringVal(Constants.TOKEN))
                    live_events_recycler.visibility = View.VISIBLE
                }
                type = search[position]
                spinnertitle.setText(search[position])
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // write code to perform some action
            }
        }
    }

    private fun searchbyType(s: String?) {
        filterdata = ArrayList()
        if (response.size > 0) {
            for (i in response!!.indices) {
                val closetModel = response!![i]
                if (closetModel.type!!.toLowerCase().contains(s!!.toLowerCase()))
                    filterdata!!.add(closetModel)

                if (filterdata.size > 0) {
                    live_events_recycler.layoutManager =
                        LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                    val adapter = LiveEventsAdapter(context!!, filterdata!!,
                        getStringVal(Constants.USERID).toString()
                    )
                    live_events_recycler.adapter = adapter
                }
            }

            if (filterdata.size == 0) {
                live_events_recycler.visibility = View.GONE
            }
        }

    }

    fun searchByTitle(s: String) {

        filterdata = ArrayList()
        if (response.size > 0) {
            for (i in response!!.indices) {
                val closetModel = response!![i]
                if (closetModel.title!!.toLowerCase().contains(s.toLowerCase()))
                    filterdata!!.add(closetModel)

                if (filterdata.size > 0) {
                    live_events_recycler.layoutManager =
                        LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                    val adapter = LiveEventsAdapter(context!!, filterdata!!,
                        getStringVal(Constants.USERID).toString()
                    )
                    live_events_recycler.adapter = adapter
                }
            }
            if (filterdata.size == 0) {
                live_events_recycler.visibility = View.GONE
            }
        }
    }

    private fun findIds(view: View?) {
        live_events_recycler = view?.findViewById(R.id.live_events_recycler)!!
        live_event_spinner = view?.findViewById(R.id.live_event_spinner)!!
        spinnertitle = view?.findViewById(R.id.live_spinnertitle)!!
        search_ET = view?.findViewById(R.id.search_ET)
        utility = Utility()
        pd = ProgressDialog(context)
        pd!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        pd!!.window!!.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        pd!!.isIndeterminate = true
        pd!!.setCancelable(false)
    }

    override fun onLiveEventSuccess(liveEventsResponse: Response<LiveEventsResponse>) {
        pd.dismiss()
        if (liveEventsResponse.isSuccessful) {
            response =
                liveEventsResponse.body()?.getData()?.events as ArrayList<LiveEventsResponse.Event>
            if (!type.equals("All"))
            {
                searchbyType(type)
            }else{
                setFullData(response)
            }

        } else {
            utility!!.relative_snackbar(
                parent_liveevents!!,
                liveEventsResponse.message(),
                getString(R.string.close_up)
            )
        }
    }

    companion object {
        var liveEventIF: LiveEventIF? = null
    }

    private fun setFullData(closets: ArrayList<LiveEventsResponse.Event>) {

        live_events_recycler.visibility = View.VISIBLE
        //response = closets
        live_events_recycler.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        val adapter = LiveEventsAdapter(context!!, closets!!,
            getStringVal(Constants.USERID).toString()
        )
        live_events_recycler.adapter = adapter
    }

    override fun onFavLiveEventSuccess(favLiveEvent: Response<FavLiveEventResponse>) {
       pd.dismiss()
        if (favLiveEvent.isSuccessful)
        {
            utility!!.relative_snackbar(
            parent_liveevents!!,
            favLiveEvent.body()?.getMessage(),
            getString(R.string.close_up)
        )

            controller.LiveEvents("Bearer " + getStringVal(Constants.TOKEN))

        }else{
            utility!!.relative_snackbar(
                parent_liveevents!!,
                favLiveEvent.message(),
                getString(R.string.close_up)
            )
        }
    }

    override fun error(error: String?) {
        pd.dismiss()
        utility!!.relative_snackbar(
            parent_liveevents!!,
            error,
            getString(R.string.close_up)
        )
    }

    override fun getID(id: String?,coount : Int) {
        pd.show()
        count = coount

     controller.favLiveEvent("Bearer "+getStringVal(Constants.TOKEN),id,"event")
    }
}