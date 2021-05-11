package com.casebeaumonde.activities.MyEventDetailScreen

import android.app.Dialog
import android.app.ProgressDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.casebeaumonde.Controller.Controller
import com.casebeaumonde.R
import com.casebeaumonde.activities.MyEventDetailScreen.IF.EventID_IF
import com.casebeaumonde.activities.MyEventDetailScreen.adapter.MyEventdetailAdapter
import com.casebeaumonde.activities.MyEvents.adapter.MyEventsAdapter
import com.casebeaumonde.activities.eventDetail.EventDetailScreen
import com.casebeaumonde.constants.BaseClass
import com.casebeaumonde.constants.Constants
import com.casebeaumonde.utilities.Utility
import kotlinx.android.synthetic.main.activity_event_detail_screen.*
import kotlinx.android.synthetic.main.activity_event_detail_screen.parent_eventdetail
import kotlinx.android.synthetic.main.activity_my_event_detail_screen.*
import retrofit2.Response

class MyEventDetailScreen : BaseClass() ,Controller.MyEventsDetailAPI,EventID_IF{

    private lateinit var eventdetail_back: ImageButton
    private lateinit var eventdetail_eventname: TextView
    private lateinit var eventdetails_items: RecyclerView
    private lateinit var utility: Utility
    private lateinit var pd: ProgressDialog
    private lateinit var id: String
    private lateinit var controller: Controller
    private lateinit var eventItems : ArrayList<EventDetailResponse.Data.Events.Item>
    private lateinit var Viewdialog : Dialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_event_detail_screen)

        findIDs()
        listeners()
        controller.Controller(this)


        if (utility.isConnectingToInternet(this)) {
            pd.show()
            pd.setContentView(R.layout.loading)
            controller.MyEventDetail("Bearer " + getStringVal(Constants.TOKEN), id)
            //setViewAnalyticsAPI(id,"event_item");
        } else {
            utility!!.relative_snackbar(
                parent_myeventdetailscreen!!,
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
//        EventDetailScreen.eventID_IF = this
//        EventDetailScreen.closetitemidIf = this
        utility = Utility()
        pd = ProgressDialog(this)
        pd!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        pd!!.window!!.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        pd!!.isIndeterminate = true
        pd!!.setCancelable(false)
        controller = Controller()
        id = intent.getStringExtra(Constants.EVENTID)!!

        eventidIf = this

    }

    override fun onMyEventDetailSuccess(success: Response<EventDetailResponse>) {
       pd.dismiss()

        if (success.isSuccessful)
        {
            if (success.body()?.code.equals("200"))
            {
                eventItems = ArrayList()
                for (i in success.body()?.data?.events?.items?.indices!!)
                {
                   eventItems.add(success.body()?.data?.events?.items?.get(i)!!)
                }
                eventdetails_items.layoutManager =
                    LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
                //closets =  response
                val adapter = MyEventdetailAdapter(this, eventItems,
                    getStringVal(Constants.USERID)!!
                )
                eventdetails_items.adapter = adapter
            } else {
                utility!!.relative_snackbar(
                    parent_myeventdetailscreen!!,
                    success.message(),
                    getString(R.string.close_up)
                )
            }

        } else {
            utility!!.relative_snackbar(
                parent_myeventdetailscreen!!,
                success.message(),
                getString(R.string.close_up)
            )
        }

    }

    override fun error(error: String?) {
       pd.dismiss()
        utility!!.relative_snackbar(
            parent_myeventdetailscreen!!,
            error,
            getString(R.string.close_up)
        )
    }

    companion object {
        var eventidIf : EventID_IF? = null
    }

    override fun getClosetID(id: String?) {
      //  ViewEvent(id?.toInt())
    }

    fun ViewEvent(id: Int?) {
        Viewdialog = Dialog(this!!)
        Viewdialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        Viewdialog.setContentView(R.layout.viewclosetitem)
        val window = Viewdialog.window
        window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT)

        var viewitem_image: ImageView
        var viewitem_title : TextView
        var viewitem_color : TextView
        var viewitem_size : TextView
        var viewitem_price : TextView
        var  viewitem_category : TextView
        var viewitem_favcount : TextView
        var itemview_removebt : Button
        var itemview_editbt : Button
        var itemview_closebt : Button
        var viewitem_checkbox : CheckBox
        var itemview_spinner : Spinner
        var itemview_pinnertitle : TextView
        var itemview_addtoclosetbt : Button

        viewitem_image = Viewdialog.findViewById(R.id.viewitem_image)
        viewitem_title = Viewdialog.findViewById(R.id.viewitem_title)
        viewitem_color = Viewdialog.findViewById(R.id.viewitem_color)
        viewitem_size = Viewdialog.findViewById(R.id.viewitem_size)
        viewitem_price = Viewdialog.findViewById(R.id.viewitem_price)
        viewitem_category = Viewdialog.findViewById(R.id.viewitem_category)
        viewitem_favcount = Viewdialog.findViewById(R.id.viewitem_favcount)
        viewitem_checkbox = Viewdialog.findViewById(R.id.viewitem_checkbox)
        itemview_removebt = Viewdialog.findViewById(R.id.itemview_removebt)
        itemview_spinner = Viewdialog.findViewById(R.id.itemview_spinner)
        itemview_closebt = Viewdialog.findViewById(R.id.itemview_closebt)
        itemview_editbt = Viewdialog.findViewById(R.id.itemview_editbt)
        itemview_pinnertitle = Viewdialog.findViewById(R.id.itemview_pinnertitle)
        itemview_addtoclosetbt = Viewdialog.findViewById(R.id.itemview_addtoclosetbt)

        itemview_editbt.visibility = View.GONE
        itemview_removebt.visibility = View.GONE

        Glide.with(this).load(Constants.BASE_IMAGE_URL+eventItems.get(id!!).picture).placeholder(R.drawable.login_banner1).into(viewitem_image)
//        viewitem_title.text = "Title :"+response.get(id).title
//        viewitem_color.text = "Color :"+response.get(id).color?.name
//        if (response.get(id).hearts!!.size>0)
//        {
//            viewitem_favcount.text = response.get(id).hearts!!.size.toString()
//        }
//
//        itemview_closebt.setOnClickListener {
//            Viewdialog.dismiss()
//        }
//
//
//        viewitem_size.text = "Size :"+response.get(id).size?.name
//        viewitem_price.text = "Price :"+response.get(id).price
//        viewitem_category.text = "Category :"+response.get(id).category?.name
//
//        setSpinnerData(userClosets,itemview_spinner,itemview_pinnertitle,
//            response.get(id).id!!,itemview_addtoclosetbt)
//
//        searchUserHeart(response.get(id).hearts as MutableList<com.casebeaumonde.activities.eventDetail.response.EventDetailResponse.Data.Events.Heart>,viewitem_checkbox)
        Viewdialog.show()
    }

}