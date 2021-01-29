package com.casebeaumonde.activities.eventDetail

import android.app.Dialog
import android.app.ProgressDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
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
import com.casebeaumonde.Retrofit.WebAPI
import com.casebeaumonde.activities.ClosetItem.IF.ClosetItemID_IF
import com.casebeaumonde.activities.ClosetItem.response.AddToFavClosetItemResponse
import com.casebeaumonde.activities.eventDetail.IF.EventID_IF
import com.casebeaumonde.activities.eventDetail.adapter.EventDetailAdapter
import com.casebeaumonde.activities.eventDetail.response.AddItemToAnotherCloset
import com.casebeaumonde.activities.eventDetail.response.EventDetailResponse
import com.casebeaumonde.constants.BaseClass
import com.casebeaumonde.constants.Constants
import com.casebeaumonde.utilities.Utility
import kotlinx.android.synthetic.main.activity_closets_items.*
import kotlinx.android.synthetic.main.activity_event_detail_screen.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EventDetailScreen : BaseClass(), Controller.EventsDetailAPI ,ClosetItemID_IF,EventID_IF,Controller.AddTofavClosetItemAPI,Controller.AdDItemToAnotherClosetAPI{

    private lateinit var eventdetail_back: ImageButton
    private lateinit var eventdetail_eventname: TextView
    private lateinit var eventdetails_items: RecyclerView
    private lateinit var utility: Utility
    private lateinit var pd: ProgressDialog
    private lateinit var id: String
    private lateinit var controller: Controller
    private lateinit var response: ArrayList<EventDetailResponse.Data.Events.Item>
    private lateinit var filterData: ArrayList<EventDetailResponse.Data.Events.Item>
    private lateinit var userClosets : ArrayList<EventDetailResponse.Data.Closet>
    private lateinit var Viewdialog : Dialog
    private lateinit var list : ArrayList<String>
    private lateinit var listID : ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_detail_screen)

        findIDs()
        listeners()
        controller.Controller(this,this,this)

        if (utility.isConnectingToInternet(this)) {
            pd.show()
            pd.setContentView(R.layout.loading)
            controller.EventDetail("Bearer " + getStringVal(Constants.TOKEN), id)
            setViewAnalyticsAPI(id,"event_item");
        } else {
            utility!!.relative_snackbar(
                parent_eventdetail!!,
                getString(R.string.nointernet),
                getString(R.string.close_up)
            )
        }
    }

    private fun setViewAnalyticsAPI(id: String, s: String) {

        val viewAnalytics = WebAPI.apiInterface?.viewAnalytics("Bearer "+getStringVal(Constants.TOKEN),id,s)
        viewAnalytics?.enqueue(object : Callback<ResponseBody>
        {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {

            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {

            }

        })

    }

    private fun listeners() {
        eventdetail_back.setOnClickListener { onBackPressed() }
    }

    private fun findIDs() {
        eventdetail_back = findViewById(R.id.eventdetail_back)
        eventdetail_eventname = findViewById(R.id.eventdetail_eventname)
        eventdetails_items = findViewById(R.id.eventdetails_items)
        eventID_IF = this
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
        userClosets = eventDetailResponse.body()?.getData()?.closet as ArrayList<EventDetailResponse.Data.Closet>
        response =
            eventDetailResponse.body()?.getData()?.events?.items as ArrayList<EventDetailResponse.Data.Events.Item>
        setFullData(response)
    }

    companion object{
        var closetitemidIf : ClosetItemID_IF? = null
        var eventID_IF : EventID_IF? = null
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

    override fun getEventID(id: Int?) {
       ViewEvent(id)
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

        Glide.with(this).load(Constants.BASE_IMAGE_URL+response.get(id!!).picture).placeholder(R.drawable.login_banner).into(viewitem_image)
        viewitem_title.text = "Title :"+response.get(id).title
        viewitem_color.text = "Color :"+response.get(id).color?.name
        if (response.get(id).hearts!!.size>0)
        {
            viewitem_favcount.text = response.get(id).hearts!!.size.toString()
        }

        itemview_closebt.setOnClickListener {
            Viewdialog.dismiss()
        }


        viewitem_size.text = "Size :"+response.get(id).size?.name
        viewitem_price.text = "Price :"+response.get(id).price
        viewitem_category.text = "Category :"+response.get(id).category?.name

        setSpinnerData(userClosets,itemview_spinner,itemview_pinnertitle,
            response.get(id).id!!,itemview_addtoclosetbt)

        searchUserHeart(response.get(id).hearts as MutableList<EventDetailResponse.Data.Events.Heart>,viewitem_checkbox)
        Viewdialog.show()
    }

    private fun setSpinnerData(
        userClosets: java.util.ArrayList<EventDetailResponse.Data.Closet>,
        itemview_spinner: Spinner,
        spinnertitle: TextView,
        id: Int,
        itemview_addtoclosetbt: Button
    ) {
         list = ArrayList()
        listID = ArrayList()
        var closetId : String = ""
        for (i in 0 until userClosets.size)
        {

            val title = userClosets.get(i)
            list.add(title.title.toString())
            listID.add(title.id.toString())
        }

        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_dropdown_item, list
        )

        itemview_spinner.adapter = adapter
        itemview_spinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View, position: Int, id: Long
            ) {

                    spinnertitle.text = list.get(position)
                closetId = listID.get(position)


            }

            override fun onNothingSelected(parent: AdapterView<*>) {
               spinnertitle.text = list.get(0)
            }
        }

        itemview_addtoclosetbt.setOnClickListener {
            pd.show()
            controller.AddItemToAnotherCloset("Bearer "+getStringVal(Constants.TOKEN),
                id.toString(),closetId,"event_item")
        }
    }

    fun searchUserHeart(
        closetsItems: MutableList<EventDetailResponse.Data.Events.Heart>,
        closetitemFavorite: CheckBox
    )
    {
        if (closetsItems.size>0)
        {
            for (i in closetsItems!!.indices)
            {
                val heart = closetsItems!![i]
                if (heart.userId.toString().equals(getStringVal(Constants.USERID)))
                {
                    closetitemFavorite.isChecked = true
                }
            }
        }
    }

    override fun onAddItemToAnotherClosetSuccess(addItemToAnotherCloset: Response<AddItemToAnotherCloset>) {
        pd.dismiss()
        Viewdialog.dismiss()
        if (addItemToAnotherCloset.isSuccessful)
        {
            utility.relative_snackbar(parent_eventdetail!!, addItemToAnotherCloset.body()?.getMesssage(), getString(R.string.close_up))
        }else {
            utility.relative_snackbar(parent_eventdetail!!, addItemToAnotherCloset.message(), getString(R.string.close_up))
        }
    }
}


