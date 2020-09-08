package com.casebeaumonde.fragments.Live_Events

import android.app.ProgressDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
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
import com.casebeaumonde.fragments.Live_Events.adapter.LiveEventsAdapter
import com.casebeaumonde.fragments.Live_Events.response.LiveEventsResponse
import com.casebeaumonde.fragments.designers.adapter.DesignerAdapter
import com.casebeaumonde.utilities.Utility
import kotlinx.android.synthetic.main.fragment_designers.*
import kotlinx.android.synthetic.main.fragment_live_events.*
import retrofit2.Response

class LiveEvents : BaseFrag() ,Controller.LiveEventsAPI{

    private lateinit var utility: Utility
    private lateinit var pd: ProgressDialog
    private lateinit var controller: Controller
    private lateinit var live_events_recycler : RecyclerView
    private lateinit var response: ArrayList<LiveEventsResponse.Event>
    private lateinit var creator : ArrayList<LiveEventsResponse.Data>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_live_events, container, false)
        findIds(view)
        controller = Controller()
        controller.Controller(this)

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

        return view
    }

    private fun findIds(view: View?) {
        live_events_recycler = view?.findViewById(R.id.live_events_recycler)!!
        utility = Utility()
        pd = ProgressDialog(context)
        pd!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        pd!!.window!!.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        pd!!.isIndeterminate = true
        pd!!.setCancelable(false)
    }

    override fun onLiveEventSuccess(liveEventsResponse: Response<LiveEventsResponse>) {
       pd.dismiss()
        if (liveEventsResponse.isSuccessful)
        {
            setFullData(liveEventsResponse.body()?.data?.events as ArrayList<LiveEventsResponse.Event>)
        }else{
            utility!!.relative_snackbar(
                parent_liveevents!!,
                liveEventsResponse.message(),
                getString(R.string.close_up)
            )
        }
    }

    private fun setFullData(closets: ArrayList<LiveEventsResponse.Event>) {

        response =
            closets
        live_events_recycler.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        val adapter = LiveEventsAdapter(context!!, closets!!)
        live_events_recycler.adapter = adapter
    }

    override fun error(error: String?) {
       pd.dismiss()
        utility!!.relative_snackbar(
            parent_liveevents!!,
            error,
            getString(R.string.close_up)
        )
    }
}