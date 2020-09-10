package com.casebeaumonde.activities.eventDetail

import android.app.ProgressDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.WindowManager
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.casebeaumonde.Controller.Controller
import com.casebeaumonde.R
import com.casebeaumonde.activities.ClosetItem.IF.ClosetItemID_IF
import com.casebeaumonde.activities.ClosetItem.response.AddToFavClosetItemResponse
import com.casebeaumonde.activities.eventDetail.adapter.EventDetailAdapter
import com.casebeaumonde.activities.eventDetail.response.EventDetailResponse
import com.casebeaumonde.activities.myclosets.IF.ViewClosetID_IF
import com.casebeaumonde.constants.BaseClass
import com.casebeaumonde.constants.Constants
import com.casebeaumonde.fragments.Live_Events.adapter.LiveEventsAdapter
import com.casebeaumonde.utilities.Utility
import kotlinx.android.synthetic.main.activity_closets_items.*
import kotlinx.android.synthetic.main.activity_event_detail_screen.*
import retrofit2.Response

class EventDetailScreen : BaseClass(), Controller.EventsDetailAPI ,ClosetItemID_IF,Controller.AddTofavClosetItemAPI{

    private lateinit var eventdetail_back: ImageButton
    private lateinit var eventdetail_eventname: TextView
    private lateinit var eventdetails_items: RecyclerView
    private lateinit var utility: Utility
    private lateinit var pd: ProgressDialog
    private lateinit var id: String
    private lateinit var controller: Controller
    private lateinit var response: ArrayList<EventDetailResponse.Data.Events.Item>
    private lateinit var filterData: ArrayList<EventDetailResponse.Data.Events.Item>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_detail_screen)

        findIDs()
        listeners()
        controller.Controller(this,this)

        if (utility.isConnectingToInternet(this)) {
            pd.show()
            pd.setContentView(R.layout.loading)
            controller.EventDetail("Bearer " + getStringVal(Constants.TOKEN), id)
        } else {
            utility!!.relative_snackbar(
                parent_eventdetail!!,
                getString(R.string.nointernet),
                getString(R.string.close_up)
            )
        }
    }

    private fun listeners() {
        eventdetail_back.setOnClickListener { onBackPressed() }
    }

    private fun findIDs() {
        eventdetail_back = findViewById(R.id.eventdetail_back)
        eventdetail_eventname = findViewById(R.id.eventdetail_eventname)
        eventdetails_items = findViewById(R.id.eventdetails_items)
        closetitemidIf = this
        utility = Utility()
        pd = ProgressDialog(this)
        pd!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        pd!!.window!!.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        pd!!.isIndeterminate = true
        pd!!.setCancelable(false)
        controller = Controller()
        id = intent.getStringExtra(Constants.EVENTID)

    }

    override fun onEventDetailSuccess(eventDetailResponse: Response<EventDetailResponse>) {
        pd.dismiss()
        response =
            eventDetailResponse.body()?.data?.events?.items as ArrayList<EventDetailResponse.Data.Events.Item>
        setFullData(response)
    }

    companion object{
        var closetitemidIf : ClosetItemID_IF? = null
    }

    override fun onAddToFavClosetItemSuccess(addToFavClosetItemResponse: Response<AddToFavClosetItemResponse>) {
        if (addToFavClosetItemResponse.isSuccessful)
        {
            pd.dismiss()
        }else {
            pd.dismiss()
            recreate()
            utility!!.relative_snackbar(parent_closetsItems!!, addToFavClosetItemResponse.message(), getString(R.string.close_up))
        }
    }

    override fun error(error: String?) {
        pd.dismiss()
        utility!!.relative_snackbar(
            parent_eventdetail!!,
            error,
            getString(R.string.close_up)
        )
    }

    fun setFullData(response: ArrayList<EventDetailResponse.Data.Events.Item>) {
        eventdetails_items.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        val adapter = EventDetailAdapter(this!!, response!!, getStringVal(Constants.USERID)!!)
        eventdetails_items.adapter = adapter
    }

    override fun getClosetID(id: String?) {
        if (utility.isConnectingToInternet(this)) {
            pd.show()
            pd.setContentView(R.layout.loading)
            controller.AddToFavClosetItem("Bearer "+getStringVal(Constants.TOKEN),id,"closet_item")

        }else {
            utility.relative_snackbar(parent_eventdetail!!, "No Internet Connectivity", getString(R.string.close_up))
        }
    }
}