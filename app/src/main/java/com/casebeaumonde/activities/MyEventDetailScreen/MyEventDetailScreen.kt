package com.casebeaumonde.activities.MyEventDetailScreen

import android.app.Dialog
import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
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
import com.casebeaumonde.activities.MyEventDetailScreen.response.ChangeEventStatusResponse
import com.casebeaumonde.activities.MyEventDetailScreen.response.EventDetailResponse
import com.casebeaumonde.activities.MyEventDetailScreen.response.RemoveEventResponse
import com.casebeaumonde.activities.ShopItems.response.AddtoCartResponse
import com.casebeaumonde.activities.addItemtoEvent.AddItemToEvent
import com.casebeaumonde.activities.eventDetail.IF.GetEventForFav_IF
import com.casebeaumonde.activities.eventDetail.response.FavEventItemResponse
import com.casebeaumonde.activities.myclosets.response.FetchListResponse
import com.casebeaumonde.constants.BaseClass
import com.casebeaumonde.constants.Constants
import com.casebeaumonde.utilities.Utility
import kotlinx.android.synthetic.main.activity_closets_items.*
import kotlinx.android.synthetic.main.activity_my_event_detail_screen.*
import kotlinx.android.synthetic.main.activity_shop_items.*
import retrofit2.Response

class MyEventDetailScreen : BaseClass(), Controller.MyEventsDetailAPI, EventID_IF,
    Controller.ChangeEventStatusAPI, Controller.FetchListAPI, GetEventForFav_IF,
    Controller.FavEventAPI,
    Controller.RemoveEventItemAPI, Controller.AddtoCartAPI {

    private lateinit var eventdetail_back: ImageButton
    private lateinit var eventdetail_eventname: TextView
    private lateinit var eventdetails_items: RecyclerView
    private lateinit var utility: Utility
    private lateinit var pd: ProgressDialog
    private lateinit var id: String
    private lateinit var controller: Controller
    private lateinit var eventItems: ArrayList<EventDetailResponse.Data.Events.Item>
    private lateinit var Viewdialog: Dialog
    private lateinit var active_toogle: ToggleButton
    private lateinit var category_spinner: Spinner
    private lateinit var brand_spinner: Spinner
    private lateinit var color_spinner: Spinner
    private lateinit var size_spinner: Spinner
    private lateinit var price_spinner: Spinner
    private lateinit var category_title: TextView
    private lateinit var brand_title: TextView
    private lateinit var color_title: TextView
    private lateinit var size_title: TextView
    private lateinit var price_title: TextView
    private lateinit var eventID: String
    private lateinit var categoryTitle: ArrayList<String>
    private lateinit var categoryID: ArrayList<String>
    private lateinit var brandTitle: ArrayList<String>
    private lateinit var brandID: ArrayList<String>
    private lateinit var colorTitle: ArrayList<String>
    private lateinit var colorID: ArrayList<String>
    private lateinit var sizeTitle: ArrayList<String>
    private lateinit var sizeID: ArrayList<String>
    private lateinit var priceTitle: ArrayList<String>
    private lateinit var hashMap: HashMap<String, String>
    private lateinit var outFitTitle: ArrayList<String>
    private lateinit var outFitID: ArrayList<String>
    private lateinit var eventItemID: String
    private lateinit var addevent: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_event_detail_screen)

        findIDs()
        listeners()
        controller.Controller(this, this, this, this, this, this)

    }

    override fun onResume() {
        super.onResume()
        if (utility.isConnectingToInternet(this)) {
            pd.show()
            pd.setContentView(R.layout.loading)
            controller.MyEventDetail("Bearer " + getStringVal(Constants.TOKEN), id)
            controller.FetchList("Bearer " + getStringVal(Constants.TOKEN))
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

        active_toogle.setOnClickListener {
            if (utility.isConnectingToInternet(this)) {
                pd.show()
                pd.setContentView(R.layout.loading)
                controller.ChangeEventStatus("Bearer " + getStringVal(Constants.TOKEN), eventID)
                //setViewAnalyticsAPI(id,"event_item");
            } else {
                utility!!.relative_snackbar(
                    parent_myeventdetailscreen!!,
                    getString(R.string.nointernet),
                    getString(R.string.close_up)
                )
            }
        }

        addevent.setOnClickListener {
            startActivity(
                Intent(this, AddItemToEvent::class.java).putExtra(
                    "eventID",
                    id
                )
            )
        }
    }

    private fun findIDs() {
        hashMap = HashMap()
        eventdetail_back = findViewById(R.id.eventdetail_back)
        eventdetail_eventname = findViewById(R.id.eventdetail_eventname)
        eventdetails_items = findViewById(R.id.eventdetails_items)
        active_toogle = findViewById(R.id.active_toogle)
        addevent = findViewById(R.id.addevent)
        category_spinner = findViewById(R.id.category_spinner)
        brand_spinner = findViewById(R.id.brand_spinner)
        color_spinner = findViewById(R.id.color_spinner)
        size_spinner = findViewById(R.id.size_spinner)
        price_spinner = findViewById(R.id.price_spinner)
        category_title = findViewById(R.id.category_title)
        brand_title = findViewById(R.id.brand_title)
        color_title = findViewById(R.id.color_title)
        size_title = findViewById(R.id.size_title)
        price_title = findViewById(R.id.price_title)
//        EventDetailScreen.eventID_IF = this
//        EventDetailScreen.closetitemidIf = this

        geteventforfavIf = this

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

        if (success.isSuccessful) {
            if (success.body()?.code.equals("200")) {
                eventItems = ArrayList()
                eventID = success?.body()?.data?.events?.id.toString()
                for (i in success.body()?.data?.events?.items?.indices!!) {
                    eventItems.add(success.body()?.data?.events?.items?.get(i)!!)
                }
                eventdetails_items.layoutManager =
                    LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
                //closets =  response
                val adapter = MyEventdetailAdapter(
                    this, eventItems,
                    getStringVal(Constants.USERID)!!
                )
                eventdetails_items.adapter = adapter

                if (success.body()?.data?.events?.status.equals("active")) {
                    active_toogle.isChecked = true
                } else if (success.body()?.data?.events?.status.equals("inactive")) {
                    active_toogle.isChecked = false
                }
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

    override fun onChangeEventSuccess(success: Response<ChangeEventStatusResponse>) {
        pd.dismiss()
        if (success.isSuccessful) {
            if (success.body()?.code?.equals("200")!!) {
                if (success.body()?.data?.event?.status?.equals("active")!!) {
                    active_toogle.isChecked = true
                } else {
                    active_toogle.isChecked = false
                }
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

    override fun onFetchListSuccess(fetchList: Response<FetchListResponse>) {
        pd.dismiss()
        if (fetchList.isSuccessful) {
            outFitTitle = ArrayList()
            outFitID = ArrayList()
//            outFitRes = ArrayList()
//            outFitRes.addAll(fetchList.body()?.getData()?.outfits!!)


            //ToDo:---------------------------- Get Category------------------------------------
            categoryTitle = ArrayList()
            categoryID = ArrayList()
            categoryTitle.add("Select Category")
            categoryID.add("000")
            //ToDo: Get Categories
            for (i in 0 until fetchList.body()?.getData()?.categories?.size!!) {
                val title = fetchList.body()?.getData()?.categories?.get(i)
                categoryTitle.add(title?.name!!)
                categoryID.add(title?.id.toString())
            }


            val categoryadapter = ArrayAdapter(
                this!!,
                android.R.layout.simple_spinner_dropdown_item, categoryTitle
            )
            categoryadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            category_spinner.adapter = categoryadapter
            category_spinner.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View, position: Int, id: Long
                ) {
                    category_title.setText(categoryTitle[position])
                    val pos = categoryID[position]


                    if (!categoryTitle[position].equals("Select Category")) {
                        // pd.show()
                        hashMap.put("category_id", categoryID[position])
                        Log.d("hashmap", "" + hashMap)
//                        controller.FilterCloseItems(
//                            "Bearer " + getStringVal(Constants.TOKEN),
//                            closetID,
//                            hashMap
//                        )
                        //searchByOutFit(categoryTitle[position], categoryID[position])
                    } else {
                        hashMap.remove("category_id")
                        if (hashMap.size == 0) {
                            // setClosetAPI()
                        }
//                        controller.FilterCloseItems(
//                            "Bearer " + getStringVal(Constants.TOKEN),
//                            closetID,
//                            hashMap
//                        )
                    }

                    //rateType = outFitTitle[position]e
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }

            //ToDo:---------------------------- Get brand------------------------------------
            brandTitle = ArrayList()
            brandID = ArrayList()
            brandTitle.add("Select Brand")
            brandID.add("000")
            //ToDo: Get Categories
            for (i in 0 until fetchList.body()?.getData()?.brands?.size!!) {
                val title = fetchList.body()?.getData()?.brands?.get(i)
                brandTitle.add(title?.name!!)
                brandID.add(title?.id.toString())
            }


            val brandadapter = ArrayAdapter(
                this!!,
                android.R.layout.simple_spinner_dropdown_item, brandTitle
            )
            brandadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            brand_spinner.adapter = brandadapter
            brand_spinner.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View, position: Int, id: Long
                ) {
                    brand_title.setText(brandTitle[position])
                    val pos = brandID[position]

                    if (!brandTitle[position].equals("Select Brand")) {
                        // pd.show()
                        hashMap.put("brand", brandTitle[position])
                        Log.d("hashmap", "" + hashMap)
//                        controller.FilterCloseItems(
//                            "Bearer " + getStringVal(Constants.TOKEN),
//                            closetID,
//                            hashMap
//                        )
                        //searchByOutFit(categoryTitle[position], categoryID[position])
                    } else {
                        hashMap.remove("brand")
//                        if (hashMap.size == 0) {
//                            setClosetAPI()
//                        }
//                        controller.FilterCloseItems(
//                            "Bearer " + getStringVal(Constants.TOKEN),
//                            closetID,
//                            hashMap
//                        )
                    }

                    //rateType = outFitTitle[position]e
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }

            //ToDo:---------------------------- Get Color------------------------------------
            colorTitle = ArrayList()
            colorID = ArrayList()
            colorTitle.add("Select Color")
            colorID.add("000")
            //ToDo: Get Color
            for (i in 0 until fetchList.body()?.getData()?.colors?.size!!) {
                val title = fetchList.body()?.getData()?.colors?.get(i)
                colorTitle.add(title?.name!!)
                colorID.add(title?.id.toString())
            }


            val coloradapter = ArrayAdapter(
                this!!,
                android.R.layout.simple_spinner_dropdown_item, colorTitle
            )
            brandadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            color_spinner.adapter = coloradapter
            color_spinner.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View, position: Int, id: Long
                ) {
                    color_title.setText(colorTitle[position])
                    val pos = colorID[position]

                    if (!colorTitle[position].equals("Select Color")) {
                        //  pd.show()
                        hashMap.put("color", colorID[position])
                        Log.d("hashmap", "" + hashMap)
//                        controller.FilterCloseItems(
//                            "Bearer " + getStringVal(Constants.TOKEN),
//                            closetID,
//                            hashMap
//                        )
                        //searchByOutFit(categoryTitle[position], categoryID[position])
                    } else {
                        hashMap.remove("color")
//                        if (hashMap.size == 0) {
//                            setClosetAPI()
//                        }
//                        controller.FilterCloseItems(
//                            "Bearer " + getStringVal(Constants.TOKEN),
//                            closetID,
//                            hashMap
//                        )
                    }

                    //rateType = outFitTitle[position]e
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }

            //ToDo:---------------------------- Get Size------------------------------------
            sizeTitle = ArrayList()
            sizeID = ArrayList()
            sizeTitle.add("Select Size")
            sizeID.add("000")
            //ToDo: Get Color
            for (i in 0 until fetchList.body()?.getData()?.sizes?.size!!) {
                val title = fetchList.body()?.getData()?.sizes?.get(i)
                sizeTitle.add(title?.name!!)
                sizeID.add(title?.id.toString())
            }


            val sizeadapter = ArrayAdapter(
                this!!,
                android.R.layout.simple_spinner_dropdown_item, sizeTitle
            )
            sizeadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            size_spinner.adapter = sizeadapter
            size_spinner.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View, position: Int, id: Long
                ) {
                    size_title.setText(sizeTitle[position])
                    val pos = sizeID[position]

                    if (!sizeTitle[position].equals("Select Size")) {
                        //   pd.show()
                        hashMap.put("size", sizeTitle[position])
                        Log.d("hashmap", "" + hashMap)
//                        controller.FilterCloseItems(
//                            "Bearer " + getStringVal(Constants.TOKEN),
//                            closetID,
//                            hashMap
//                        )
                        //searchByOutFit(categoryTitle[position], categoryID[position])
                    } else {
                        hashMap.remove("size")
//                        if (hashMap.size == 0) {
//                            setClosetAPI()
//                        }
//                        controller.FilterCloseItems(
//                            "Bearer " + getStringVal(Constants.TOKEN),
//                            closetID,
//                            hashMap
//                        )
                    }

                    //rateType = outFitTitle[position]e
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }


            //ToDo:---------------------------- Get Price------------------------------------
            priceTitle = ArrayList()
            priceTitle.add("Select Price")

            //ToDo: Get Color

            val prices = resources.getStringArray(R.array.Price)

            for (i in 0 until prices?.size!!) {
                val title = prices?.get(i)
                priceTitle.add(title)
            }

            val priceadapter = ArrayAdapter(
                this!!,
                android.R.layout.simple_spinner_dropdown_item, priceTitle
            )
            priceadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            price_spinner.adapter = priceadapter
            price_spinner.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View, position: Int, id: Long
                ) {
                    price_title.setText(priceTitle[position])

                    if (!priceTitle[position].equals("Select Price")) {
                        // pd.show()
                        val priice = priceTitle[position]

                        hashMap.put("price", priice.replace("$", ""))
                        Log.d("hashmap", "" + hashMap)
//                        controller.FilterCloseItems(
//                            "Bearer " + getStringVal(Constants.TOKEN),
//                            closetID,
//                            hashMap
//                        )
                        //searchByOutFit(categoryTitle[position], categoryID[position])
                    } else {
                        hashMap.remove("price")
//                        if (hashMap.size == 0) {
//                            setClosetAPI()
//                        }
//                        controller.FilterCloseItems(
//                            "Bearer " + getStringVal(Constants.TOKEN),
//                            closetID,
//                            hashMap
//                        )

                        Log.d("hashmap", "" + hashMap)
                    }

                    //rateType = outFitTitle[position]e
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }


        } else {
            utility!!.relative_snackbar(
                parent_closetsItems!!,
                fetchList.message(),
                getString(R.string.close_up)
            )
        }
    }

    override fun onRemoveEventItemSuccess(success: Response<RemoveEventResponse>) {
        if (utility.isConnectingToInternet(this)) {
            pd.show()
            pd.setContentView(R.layout.loading)
            controller.MyEventDetail("Bearer " + getStringVal(Constants.TOKEN), id)
            controller.FetchList("Bearer " + getStringVal(Constants.TOKEN))
            //setViewAnalyticsAPI(id,"event_item");
        } else {
            utility!!.relative_snackbar(
                parent_myeventdetailscreen!!,
                getString(R.string.nointernet),
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
        var eventidIf: EventID_IF? = null
        var geteventforfavIf: GetEventForFav_IF? = null

    }

    override fun getClosetID(id: String?) {
        ViewEvent(id?.toInt())
    }

    fun ViewEvent(id: Int?) {
        Viewdialog = Dialog(this!!)
        Viewdialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        Viewdialog.setContentView(R.layout.viewclosetitem)
        val window = Viewdialog.window
        window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
        eventItemID = id.toString()
        var viewitem_image: ImageView
        var viewitem_title: TextView
        var viewitem_color: TextView
        var viewitem_size: TextView
        var viewitem_price: TextView
        var viewitem_category: TextView
        var viewitem_favcount: TextView
        var itemview_removebt: Button
        var itemview_editbt: Button
        var itemview_copy: Button
        var itemview_move: Button
        var itemview_closebt: Button
        var viewitem_checkbox: CheckBox
        var itemview_spinner: Spinner
        var itemview_pinnertitle: TextView
        var itemview_addtoclosetbt: Button
        var closetlayout: RelativeLayout
        var itemview_addtocart: Button

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
        itemview_move = Viewdialog.findViewById(R.id.itemview_move)
        itemview_pinnertitle = Viewdialog.findViewById(R.id.itemview_pinnertitle)
        itemview_addtoclosetbt = Viewdialog.findViewById(R.id.itemview_addtoclosetbt)
        closetlayout = Viewdialog.findViewById(R.id.closetlayout)
        itemview_copy = Viewdialog.findViewById(R.id.itemview_copy)
        itemview_addtocart = Viewdialog.findViewById(R.id.itemview_addtocart)

        closetlayout.visibility = View.GONE
        itemview_move.visibility = View.GONE
        itemview_copy.visibility = View.GONE
        itemview_addtocart.visibility = View.VISIBLE

        Glide.with(this).load(Constants.BASE_IMAGE_URL + eventItems.get(id!!).picture)
            .placeholder(R.drawable.login_banner1).into(viewitem_image)
        viewitem_title.text = "Title :" + eventItems.get(id).title
        viewitem_color.text = "Color :" + eventItems.get(id).color?.name
        if (eventItems.get(id).hearts!!.size > 0) {
            viewitem_favcount.text = eventItems.get(id).hearts!!.size.toString()
        }

        itemview_closebt.setOnClickListener {
            Viewdialog.dismiss()
        }


        itemview_editbt.setOnClickListener {
            if (utility.isConnectingToInternet(this)) {
                Viewdialog.dismiss()
                startActivity(
                    Intent(this, AddItemToEvent::class.java).putExtra("eventID", eventID)
                        .putExtra("edit", "1").putExtra("closetItemID", eventItemID)
                )
            } else {
                utility!!.relative_snackbar(
                    parent_myeventdetailscreen!!,
                    getString(R.string.nointernet),
                    getString(R.string.close_up)
                )
            }
        }

        itemview_removebt.setOnClickListener {
            Viewdialog.dismiss()
            if (utility.isConnectingToInternet(this)) {
                pd.show()
                pd.setContentView(R.layout.loading)
                controller.RemoveEventItem(
                    "Bearer " + getStringVal(Constants.TOKEN),
                    eventItems.get(id.toInt()).id.toString()
                )
            } else {
                utility!!.relative_snackbar(
                    parent_myeventdetailscreen!!,
                    getString(R.string.nointernet),
                    getString(R.string.close_up)
                )
            }
        }

        itemview_addtocart.setOnClickListener {
            controller.AddtoCart(
                "Bearer " + getStringVal(Constants.TOKEN),
                eventItems.get(id.toInt()).id.toString(), "1"
            )
            pd.show()
            pd.setContentView(R.layout.loading)
        }

        viewitem_size.text = "Size :" + eventItems.get(id).size?.name
        viewitem_price.text = "Price :" + eventItems.get(id).price
        viewitem_category.text = "Category :" + eventItems.get(id).category?.name

//        setSpinnerData(userClosets,itemview_spinner,itemview_pinnertitle,
//            response.get(id).id!!,itemview_addtoclosetbt)
//
//        searchUserHeart(response.get(id).hearts as MutableList<com.casebeaumonde.activities.eventDetail.response.EventDetailResponse.Data.Events.Heart>,viewitem_checkbox)
        viewitem_checkbox.setOnClickListener {
            //  Toast.makeText(this,"HERE",Toast.LENGTH_SHORT).show()
        }
        Viewdialog.show()
    }

    override fun onAddtoCartSuccess(success: Response<AddtoCartResponse>) {
        pd.dismiss()
        if (success.isSuccessful) {
            if (success.body()?.code?.equals("200")!!) {


                Viewdialog.dismiss()
                utility!!.relative_snackbar(
                    parent_myeventdetailscreen!!,
                    success.body()?.message,
                    getString(R.string.close_up)
                )
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

    override fun getEventIDForFav(id: String) {
        //Toast.makeText(this,""+id,Toast.LENGTH_SHORT).show()
        if (utility.isConnectingToInternet(this)) {
            pd.show()
            pd.setContentView(R.layout.loading)
            controller.FavEvent("Bearer " + getStringVal(Constants.TOKEN), id, "event_item")

        } else {
            utility!!.relative_snackbar(
                parent_myeventdetailscreen!!,
                getString(R.string.nointernet),
                getString(R.string.close_up)
            )
        }
    }


    override fun onFavEventSuccess(success: Response<FavEventItemResponse>) {
        pd.dismiss()
        if (success.isSuccessful) {
            utility!!.relative_snackbar(
                parent_myeventdetailscreen!!,
                success.body()?.message,
                getString(R.string.close_up)
            )
        } else {
            utility!!.relative_snackbar(
                parent_myeventdetailscreen!!,
                success.message(),
                getString(R.string.close_up)
            )
        }
    }


}