package com.casebeaumonde.activities.ClosetItem

import android.annotation.SuppressLint
import android.app.Dialog
import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.util.SparseBooleanArray
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
import com.casebeaumonde.activities.ClosetItem.IF.SelectedCloset_ID
import com.casebeaumonde.activities.ClosetItem.adapter.FilterAdapter
import com.casebeaumonde.activities.ClosetItem.adapter.FilterOutFitItems
import com.casebeaumonde.activities.ClosetItem.response.*
import com.casebeaumonde.activities.ClosetItm.adapter.ClosetsItemAdapter
import com.casebeaumonde.activities.addItemtoCLoset.AddItemToCloset
import com.casebeaumonde.activities.eventDetail.response.AddItemToAnotherCloset
import com.casebeaumonde.activities.myclosets.IF.ViewClosetID_IF
import com.casebeaumonde.activities.myclosets.response.DuplicateItemResponse
import com.casebeaumonde.activities.myclosets.response.FetchListResponse
import com.casebeaumonde.activities.myclosets.response.MoveClosetItems
import com.casebeaumonde.activities.myclosets.response.OutFitResponse
import com.casebeaumonde.constants.BaseClass
import com.casebeaumonde.constants.Constants
import com.casebeaumonde.utilities.Utility
import kotlinx.android.synthetic.main.activity_closets_items.*
import kotlinx.android.synthetic.main.activity_event_detail_screen.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ClosetsItems : BaseClass(),
    Controller.ClosetItemsAPI,
    ClosetItemID_IF,
    ViewClosetID_IF,
    SelectedCloset_ID,
    Controller.AddTofavClosetItemAPI,
    Controller.DeleteClosetItemAPI,
    Controller.AdDItemToAnotherClosetAPI,
    Controller.MoveItemAPI,
    Controller.DuplicateItemAPI,
    Controller.FetchListAPI,
    Controller.OutFItAPI,
    Controller.OutfitFilterAPI,
    Controller.FilterClosetItemsAPI {

    private lateinit var utility: Utility
    private lateinit var pd: ProgressDialog
    private lateinit var controller: Controller
    private lateinit var closetID: String
    private lateinit var closetsItems_recycler: RecyclerView
    private lateinit var closetitems_back: ImageButton
    private lateinit var closetiems_add: ImageButton
    private lateinit var hearlist: ArrayList<ClosetsItemsResponse.Data.Closet.Item.Heart>
    private lateinit var closetResponse: ArrayList<ClosetsItemsResponse.Data.Closet.Item>
    private lateinit var closetResponsefilter: ArrayList<ClosetsItemsResponse.Data.Closet.Item>
    private lateinit var outfitResponse: ArrayList<OutfitFilterResponse.Data.ClosetItem>
    private lateinit var filterOutfitResponse: ArrayList<OutfitFilterResponse.Data.ClosetItem>
    private lateinit var dialog: Dialog
    private lateinit var logoutDialog: Dialog
    private lateinit var Viewdialog: Dialog
    private lateinit var userID: String
    private lateinit var userClosets: ArrayList<ClosetsItemsResponse.Data.AllCloset>
    private lateinit var search_ET: EditText
    private lateinit var list: ArrayList<String>
    private lateinit var listID: ArrayList<String>
    private lateinit var closet_showselectbt: Button
    private lateinit var closet_deselectitembt: Button
    private lateinit var closet_selectitembt: Button
    private lateinit var closet_moveitembt: Button
    private lateinit var closet_duplicateitembt: Button
    private lateinit var closet_makeoutfitbt: Button
    private lateinit var moveItemDialog: Dialog
    private lateinit var outfit_spinner: Spinner
    private lateinit var outfit_title: TextView
    private lateinit var checkedClosetIDs: ArrayList<String>
    private lateinit var selectedItems: ArrayList<String>
    private lateinit var listData: ArrayList<String>
    private lateinit var outFitRes: ArrayList<FetchListResponse.Data.Outfit>
    private lateinit var outFitTitle: ArrayList<String>
    private lateinit var outFitID: ArrayList<String>
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

    var select: Int = 0
    var selectAll: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_closets_items)
        findIds()
        hashMap = HashMap()
        checkedClosetIDs = ArrayList()
        selectedItems = ArrayList()
        controller = Controller()
        controller.Controller(this, this, this, this, this, this, this, this, this, this)
        closetID = intent?.getStringExtra(Constants.CLOSETID).toString()
        if (utility.isConnectingToInternet(this)) {
            pd.show()
            pd.setContentView(R.layout.loading)
            controller.FetchList("Bearer " + getStringVal(Constants.TOKEN))
            controller.EventDetail("Bearer " + getStringVal(Constants.TOKEN), closetID)
            setViewAnalyticsAPI(closetID, "closet_item");
        } else {
            utility!!.relative_snackbar(
                parent_eventdetail!!,
                getString(R.string.nointernet),
                getString(R.string.close_up)
            )
        }

        closetitemidIf = this
        viewclosetidIf = this
        selectedclosetId = this
        userID = intent.getStringExtra("userID")
        listeners()
    }

    private fun setViewAnalyticsAPI(closetID: String, s: String) {
        val viewAnalytics = WebAPI.getInstance().api.viewAnalytics(
            "Bearer " + getStringVal(Constants.TOKEN),
            closetID,
            s
        )
        viewAnalytics.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {

            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {

            }

        })
    }

    override fun onResume() {
        super.onResume()
        setClosetAPI()
    }

    private fun listeners() {


        closetitems_back.setOnClickListener {
            onBackPressed()
        }

        closetiems_add.setOnClickListener {
            startActivity(
                Intent(this, AddItemToCloset::class.java).putExtra("closetID", closetID)
                    .putExtra("edit", "0").putExtra("closetItemID", "")
            )
            // addItemToCloset(closetItemID)
        }

        closet_showselectbt.setOnClickListener {
            closet_selectitembt.visibility = View.VISIBLE
            closet_deselectitembt.visibility = View.VISIBLE
            closet_showselectbt.visibility = View.GONE
            select = 1
            setFullData(closetResponse, select, selectAll)

        }

        closet_deselectitembt.setOnClickListener {
            closet_deselectitembt.visibility = View.GONE
            closet_selectitembt.visibility = View.GONE
            closet_showselectbt.visibility = View.VISIBLE
            select = 0
            selectAll = 0
            setFullData(closetResponse, select, selectAll)
            checkedClosetIDs.clear()
            closet_moveitembt.visibility = View.GONE
            closet_duplicateitembt.visibility = View.GONE
            closet_makeoutfitbt.visibility = View.GONE
        }

        closet_selectitembt.setOnClickListener {
            checkedClosetIDs.clear()
            for (i in closetResponse.indices) {
                checkedClosetIDs.add(closetResponse.get(i).id.toString())
            }
            selectAll = 1
            setFullData(closetResponse, select, selectAll)
            Log.d("test", "" + checkedClosetIDs)
            if (checkedClosetIDs.size > 0) {
                closet_moveitembt.visibility = View.VISIBLE
                closet_duplicateitembt.visibility = View.VISIBLE
                closet_makeoutfitbt.visibility = View.VISIBLE
            } else {
                closet_moveitembt.visibility = View.GONE
                closet_duplicateitembt.visibility = View.GONE
                closet_makeoutfitbt.visibility = View.GONE
            }
        }

        closet_moveitembt.setOnClickListener {
            if (checkedClosetIDs.size > 0) {
                showMoveDialog("move")
            }

        }

        closet_duplicateitembt.setOnClickListener {
            if (checkedClosetIDs.size > 0) {
                showMoveDialog("duplicate")
            }
        }

        closet_makeoutfitbt.setOnClickListener {
            if (checkedClosetIDs.size > 0) {
                showMoveDialog("outfit")
            }
        }

        search_ET.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (count > 0) {
                    searchByTitle(s.toString())
                } else {
                    setFullData(closetResponse, select, selectAll)
                }
            }

            override fun afterTextChanged(s: Editable?) {
                if (s!!.isNotEmpty()) {

                    searchByTitle(s.toString())
                } else {
                    closetsItems_recycler.visibility = View.VISIBLE
                    setFullData(closetResponse, select, selectAll)
                }
            }

        })

    }

    private fun searchByTitle(toString: String) {
        closetResponsefilter = ArrayList()
        if (closetResponse.size > 0) {
            for (i in closetResponse!!.indices) {
                val closetModel = closetResponse!![i]
                if (closetModel.title!!.toLowerCase().contains(toString.toLowerCase()))
                    closetResponsefilter!!.add(closetModel)

                if (closetResponsefilter.size > 0) {
                    closetsItems_recycler.layoutManager =
                        LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
                    //closets =  response
                    val adapter = ClosetsItemAdapter(
                        this, closetResponsefilter!!, userID, select, selectAll
                    )
                    closetsItems_recycler.adapter = adapter
                }
            }
            if (closetResponsefilter.size == 0) {
                closetsItems_recycler.visibility = View.GONE
            }
        }
    }


    //ToDO: MoveClosetiTem dialog
    private fun showMoveDialog(s: String) {
        moveItemDialog = Dialog(this)
        moveItemDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        moveItemDialog.setContentView(R.layout.customspinner)
        val window = moveItemDialog.window
        window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )


        var listview: ListView
        var outfitlist: ListView
        var moveitem: Button
        var cancelitem: Button
        var addnewclosetet: EditText
        var spinnertitle : TextView
        var checkbox: CheckBox
        var addoutfit: CheckBox
        listview = moveItemDialog.findViewById(R.id.listview)
        outfitlist = moveItemDialog.findViewById(R.id.outfitlist)
        moveitem = moveItemDialog.findViewById(R.id.moveitem)
        cancelitem = moveItemDialog.findViewById(R.id.cancelitem)
        checkbox = moveItemDialog.findViewById(R.id.checkbox)
        addnewclosetet = moveItemDialog.findViewById(R.id.addnewclosetet)
        addoutfit = moveItemDialog.findViewById(R.id.addoutfit)
        spinnertitle = moveItemDialog.findViewById(R.id.spinnertitle)
        list = ArrayList()
        listID = ArrayList()
        outFitTitle = ArrayList()
        outFitID = ArrayList()

        spinnertitle.text = s
        moveitem.text = s

        if (s.equals("move") || s.equals("duplicate")) {
            listview.visibility = View.VISIBLE
            outfitlist.visibility = View.GONE
            checkbox.visibility = View.VISIBLE
            addoutfit.visibility = View.GONE
        } else {
            listview.visibility = View.GONE
            outfitlist.visibility = View.VISIBLE
            addoutfit.visibility = View.VISIBLE
            checkbox.visibility = View.GONE
        }


        //ToDo : Get USer closets for move and duplicate
        for (i in 0 until userClosets.size) {
            val title = userClosets.get(i)
            list.add(title.title.toString())
            listID.add(title.id.toString())
        }
        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_multiple_choice, list
        )

        listview.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        listview.setAdapter(adapter);


        //ToDo: Get Outfits
        for (i in 0 until outFitRes.size) {
            val title = outFitRes.get(i)
            outFitTitle.add(title.title.toString())
            outFitID.add(title.id.toString())
        }

        val outfitadapter =
            ArrayAdapter(this, android.R.layout.simple_list_item_multiple_choice, outFitTitle)

        outfitlist.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE)
        outfitlist.setAdapter(outfitadapter)

        moveitem.setOnClickListener {
            if (s.equals("move") || s.equals("duplicate")) {
                listData = ArrayList()
                val sbArray: SparseBooleanArray = listview.getCheckedItemPositions()
                for (i in 0 until sbArray.size()) {

                    val key = sbArray.keyAt(i)

                    if (sbArray[key]) listData.add(listID.get(key))
                }
            } else {
                listData = ArrayList()
                val sbArray: SparseBooleanArray = outfitlist.getCheckedItemPositions()
                for (i in 0 until sbArray.size()) {

                    val key = sbArray.keyAt(i)

                    if (sbArray[key]) listData.add(outFitID.get(key))
                }
            }

            pd.show()
            pd.setContentView(R.layout.loading)

            val newclosetname = addnewclosetet.text.toString()
            if (s.equals("move")) {

                controller.MoveItem(
                    "Bearer " + getStringVal(Constants.TOKEN),
                    checkedClosetIDs.toString(),
                    listData.toString(),
                    newclosetname
                )


            } else if (s.equals("duplicate")) {

                controller.DuplicateItem(
                    "Bearer " + getStringVal(Constants.TOKEN),
                    checkedClosetIDs.toString(),
                    listData.toString(),
                    newclosetname
                )

            } else if (s.equals("outfit")) {
                controller.OutFIt(
                    "Bearer " + getStringVal(Constants.TOKEN),
                    checkedClosetIDs.toString(),
                    listData.toString(),
                    newclosetname
                )
            }

            Log.d("listtag", "" + listData + "    " + checkedClosetIDs)

        }


        checkbox.setOnCheckedChangeListener { buttonView, isChecked ->

            if (isChecked) {
                addnewclosetet.visibility = View.VISIBLE
                listview.visibility = View.GONE
                listData = ArrayList()
                listData.clear()
            } else {
                addnewclosetet.visibility = View.GONE
                listview.visibility = View.VISIBLE
            }
        }

        addoutfit.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                addnewclosetet.visibility = View.VISIBLE
                outfitlist.visibility = View.GONE
                listData = ArrayList()
                listData.clear()
            } else {
                addnewclosetet.visibility = View.GONE
                outfitlist.visibility = View.VISIBLE
            }

        }

        cancelitem.setOnClickListener { moveItemDialog.dismiss() }


        moveItemDialog.show()
    }

    private fun setClosetAPI() {
        if (utility.isConnectingToInternet(this)) {
            pd.show()
            pd.setContentView(R.layout.loading)
            controller.ClosetItems("Bearer " + getStringVal(Constants.TOKEN), closetID)

        } else {
            utility.relative_snackbar(
                parent_closetsItems!!,
                "No Internet Connectivity",
                getString(R.string.close_up)
            )
        }

        if (utility.isConnectingToInternet(this)) {
            pd.show()
            pd.setContentView(R.layout.loading)
            controller.AddClosetItemList("Bearer " + getStringVal(Constants.TOKEN))

        } else {
            utility.relative_snackbar(
                parent_closetsItems!!,
                "No Internet Connectivity",
                getString(R.string.close_up)
            )
        }
    }

    companion object {
        var closetitemidIf: ClosetItemID_IF? = null
        var viewclosetidIf: ViewClosetID_IF? = null
        var selectedclosetId: SelectedCloset_ID? = null
    }


    private fun findIds() {
        utility = Utility()
        pd = ProgressDialog(this)
        pd!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        pd!!.window!!.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        pd!!.isIndeterminate = true
        pd!!.setCancelable(false)
        closetsItems_recycler = findViewById(R.id.closetsItems_recycler)
        closetitems_back = findViewById(R.id.closetitems_back)
        closetiems_add = findViewById(R.id.closetiems_add)
        closet_showselectbt = findViewById(R.id.closet_showselectbt)
        closet_deselectitembt = findViewById(R.id.closet_deselectitembt)
        closet_selectitembt = findViewById(R.id.closet_selectitembt)
        closet_moveitembt = findViewById(R.id.closet_moveitembt)
        closet_duplicateitembt = findViewById(R.id.closet_duplicateitembt)
        closet_makeoutfitbt = findViewById(R.id.closet_makeoutfitbt)
        search_ET = findViewById(R.id.search_ET)
        outfit_spinner = findViewById(R.id.outfit_spinner)
        outfit_title = findViewById(R.id.outfit_title)
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
    }

    //ToDo: Get  Closet All Items
    override fun onClosetItemSuccess(closetItemsResponse: Response<ClosetsItemsResponse>) {
        pd.dismiss()
        if (closetItemsResponse.isSuccessful) {
            //userClosets = closetItemsResponse.body()?.data?.closet as ArrayList<ClosetsItemsResponse.Data.Closet>
            userClosets = ArrayList()
            selectAll = 0
            userClosets.addAll(closetItemsResponse.body()?.getData()?.allClosets!!)
            setFullData(closetItemsResponse.body()?.getData()?.closet?.items, select, selectAll)
            listData = ArrayList()
            checkedClosetIDs = ArrayList()
            listData.clear()
            checkedClosetIDs.clear()
            if (closetItemsResponse.body()?.getData()?.closet?.userId.toString()
                    .equals(getStringVal(Constants.USERID))
            ) {
                closetiems_add.visibility = View.VISIBLE
            }


        } else {
            utility!!.relative_snackbar(
                parent_closetsItems!!,
                closetItemsResponse.message(),
                getString(R.string.close_up)
            )
        }
    }


    //ToDo : Set Data into Adapter
    private fun setFullData(
        items: List<ClosetsItemsResponse.Data.Closet.Item>?,
        select: Int,
        selectAll: Int
    ) {
        closetsItems_recycler.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        val adapter = getStringVal(Constants.USERID)?.let {
            ClosetsItemAdapter(
                this, (items as MutableList<ClosetsItemsResponse.Data.Closet.Item>?)!!,
                it, select, selectAll
            )
        }
        closetResponse = ArrayList()
        closetResponse.addAll(items!!)
        closetsItems_recycler.adapter = adapter
    }


    //ToDo : Set Fav closet Item
    override fun onAddToFavClosetItemSuccess(addToFavClosetItemResponse: Response<AddToFavClosetItemResponse>) {
        if (addToFavClosetItemResponse.isSuccessful) {
            pd.dismiss()
        } else {
            pd.dismiss()
            recreate()
            utility!!.relative_snackbar(
                parent_closetsItems!!,
                addToFavClosetItemResponse.message(),
                getString(R.string.close_up)
            )
        }
    }

    //ToDo : Set Delete closet item
    override fun onDeleteClosetItemSuccess(deleteClosetItemResponse: Response<DeleteClosetItemResponse>) {
        pd.dismiss()
        if (deleteClosetItemResponse.isSuccessful) {
            setClosetAPI()
            utility!!.relative_snackbar(
                parent_closetsItems!!,
                deleteClosetItemResponse.body()?.getMessage(),
                getString(R.string.close_up)
            )
        } else {
            utility!!.relative_snackbar(
                parent_closetsItems!!,
                deleteClosetItemResponse.message(),
                getString(R.string.close_up)
            )
        }
    }

    //ToDo: Add closet item to another closet
    override fun onAddItemToAnotherClosetSuccess(addItemToAnotherCloset: Response<AddItemToAnotherCloset>) {
        pd.dismiss()
        Viewdialog.dismiss()
        if (addItemToAnotherCloset.isSuccessful) {
            controller.FetchList("Bearer " + getStringVal(Constants.TOKEN))
            utility!!.relative_snackbar(
                parent_closetsItems!!,
                addItemToAnotherCloset.body()?.getMesssage(),
                getString(R.string.close_up)
            )
            setClosetAPI()
        } else {
            utility!!.relative_snackbar(
                parent_closetsItems!!,
                addItemToAnotherCloset.message(),
                getString(R.string.close_up)
            )
        }
    }

    //ToDo: Move Closet Items
    override fun onMoveItemSuccess(moveItem: Response<MoveClosetItems>) {
        pd.dismiss()

        moveItemDialog.dismiss()
        setClosetAPI()
        if (moveItem.isSuccessful) {
            controller.FetchList("Bearer " + getStringVal(Constants.TOKEN))
            utility!!.relative_snackbar(
                parent_closetsItems!!,
                moveItem.body()?.getMessage(),
                getString(R.string.close_up)
            )
        } else {
            utility!!.relative_snackbar(
                parent_closetsItems!!,
                moveItem.body()?.getMessage(),
                getString(R.string.close_up)
            )
        }
    }

    //ToDo: Duplicate Item
    override fun onDuplicateItemSuccess(duplicateItem: Response<DuplicateItemResponse>) {
        pd.dismiss()
        moveItemDialog.dismiss()
        setClosetAPI()
        if (duplicateItem.isSuccessful) {
            controller.FetchList("Bearer " + getStringVal(Constants.TOKEN))
            utility!!.relative_snackbar(
                parent_closetsItems!!,
                duplicateItem.body()?.getMessage(),
                getString(R.string.close_up)
            )
        } else {
            utility!!.relative_snackbar(
                parent_closetsItems!!,
                duplicateItem.message(),
                getString(R.string.close_up)
            )
        }
    }

    //ToDo: Fetch List
    override fun onFetchListSuccess(fetchList: Response<FetchListResponse>) {
        pd.dismiss()
        if (fetchList.isSuccessful) {
            outFitTitle = ArrayList()
            outFitID = ArrayList()
            outFitRes = ArrayList()
            outFitRes.addAll(fetchList.body()?.getData()?.outfits!!)

            //ToDo: Get Outfits
            for (i in 0 until outFitRes.size) {
                val title = outFitRes.get(i)
                outFitTitle.add(title.title.toString())
                outFitID.add(title.id.toString())
            }

            outFitTitle.add("Select Outfit")
            outFitID.add("000")

            outFitTitle.reverse()
            outFitID.reverse()

            val adapter = ArrayAdapter(
                this!!,
                android.R.layout.simple_spinner_dropdown_item, outFitTitle
            )
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            outfit_spinner.adapter = adapter
            outfit_spinner.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View, position: Int, id: Long
                ) {
                    outfit_title.setText(outFitTitle[position])
                    val pos = outFitID[position]


                    if (!outFitTitle[position].equals("Select Outfit")) {
                        pd.show()
                        searchByOutFit(outFitTitle[position], outFitID[position])
                    } else {
                        pd.show()
                        controller.ClosetItems("Bearer " + getStringVal(Constants.TOKEN), closetID)
                    }

                    //rateType = outFitTitle[position]e
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }

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
                        pd.show()
                        hashMap.put("category_id", categoryID[position])
                        Log.d("hashmap", "" + hashMap)
                        controller.FilterCloseItems(
                            "Bearer " + getStringVal(Constants.TOKEN),
                            closetID,
                            hashMap
                        )
                        //searchByOutFit(categoryTitle[position], categoryID[position])
                    } else {
                        hashMap.remove("category_id")
                        if (hashMap.size == 0) {
                            setClosetAPI()
                        }
                        controller.FilterCloseItems(
                            "Bearer " + getStringVal(Constants.TOKEN),
                            closetID,
                            hashMap
                        )
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
                        pd.show()
                        hashMap.put("brand", brandTitle[position])
                        Log.d("hashmap", "" + hashMap)
                        controller.FilterCloseItems(
                            "Bearer " + getStringVal(Constants.TOKEN),
                            closetID,
                            hashMap
                        )
                        //searchByOutFit(categoryTitle[position], categoryID[position])
                    } else {
                        hashMap.remove("brand")
                        if (hashMap.size == 0) {
                            setClosetAPI()
                        }
                        controller.FilterCloseItems(
                            "Bearer " + getStringVal(Constants.TOKEN),
                            closetID,
                            hashMap
                        )
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
                        pd.show()
                        hashMap.put("color", colorTitle[position])
                        Log.d("hashmap", "" + hashMap)
                        controller.FilterCloseItems(
                            "Bearer " + getStringVal(Constants.TOKEN),
                            closetID,
                            hashMap
                        )
                        //searchByOutFit(categoryTitle[position], categoryID[position])
                    } else {
                        hashMap.remove("color")
                        if (hashMap.size == 0) {
                            setClosetAPI()
                        }
                        controller.FilterCloseItems(
                            "Bearer " + getStringVal(Constants.TOKEN),
                            closetID,
                            hashMap
                        )
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
                        pd.show()
                        hashMap.put("size", sizeTitle[position])
                        Log.d("hashmap", "" + hashMap)
                        controller.FilterCloseItems(
                            "Bearer " + getStringVal(Constants.TOKEN),
                            closetID,
                            hashMap
                        )
                        //searchByOutFit(categoryTitle[position], categoryID[position])
                    } else {
                        hashMap.remove("size")
                        if (hashMap.size == 0) {
                            setClosetAPI()
                        }
                        controller.FilterCloseItems(
                            "Bearer " + getStringVal(Constants.TOKEN),
                            closetID,
                            hashMap
                        )
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
                        pd.show()
                        val priice = priceTitle[position]

                        hashMap.put("price", priice.replace("$", ""))
                        Log.d("hashmap", "" + hashMap)
                        controller.FilterCloseItems(
                            "Bearer " + getStringVal(Constants.TOKEN),
                            closetID,
                            hashMap
                        )
                        //searchByOutFit(categoryTitle[position], categoryID[position])
                    } else {
                        hashMap.remove("price")
                        if (hashMap.size == 0) {
                            setClosetAPI()
                        }
                        controller.FilterCloseItems(
                            "Bearer " + getStringVal(Constants.TOKEN),
                            closetID,
                            hashMap
                        )

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

    private fun searchByOutFit(title: String, id: String) {
        pd.show()
        // searchByTitle(title)
        controller.OutFitFilter("Bearer " + getStringVal(Constants.TOKEN), id, closetID)
    }

    //ToDo: Outfit
    override fun onOutfitSuccess(outfit: Response<OutFitResponse>) {
        pd.dismiss()
        moveItemDialog.dismiss()
        setClosetAPI()
        if (outfit.isSuccessful) {
            utility!!.relative_snackbar(
                parent_closetsItems!!,
                outfit.body()?.getMessage(),
                getString(R.string.close_up)
            )
        } else {
            utility!!.relative_snackbar(
                parent_closetsItems!!,
                outfit.message(),
                getString(R.string.close_up)
            )
        }
    }

    override fun onOutfitFilterSuccess(outfiFIlter: Response<OutfitFilterResponse>) {

        pd.dismiss()
        if (outfiFIlter.isSuccessful) {
            closetsItems_recycler.layoutManager =
                LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
            val adapter = getStringVal(Constants.USERID)?.let {
                FilterOutFitItems(
                    this, (outfiFIlter.body()?.getData()?.closetItem as MutableList<OutfitFilterResponse.Data.ClosetItem>?)!!,
                    it, select, selectAll
                )
            }
            outfitResponse = ArrayList()
            outfitResponse.addAll(outfiFIlter.body()?.getData()?.closetItem!!)
            closetsItems_recycler.adapter = adapter
        } else {
            utility!!.relative_snackbar(
                parent_closetsItems!!,
                outfiFIlter.message(),
                getString(R.string.close_up)
            )
        }
    }

    override fun onFilterClosetItemSuccess(filtersucces: Response<List<FilterResponse>>) {
        pd.dismiss()

        if (filtersucces.isSuccessful) {
            closetsItems_recycler.layoutManager =
                LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
            val adapter = getStringVal(Constants.USERID)?.let {
                getStringVal(Constants.USERID)?.let { it1 ->
                    FilterAdapter(
                        this, filtersucces,
                        it, select, selectAll
                    )
                }
            }
            outfitResponse = ArrayList()
            //  outfitResponse.addAll(filtersucces)
            closetsItems_recycler.adapter = adapter
        } else {
            utility!!.relative_snackbar(
                parent_closetsItems!!,
                filtersucces.message(),
                getString(R.string.close_up)
            )
        }
    }


    override fun error(error: String?) {
        pd.dismiss()
        //dialog.dismiss()
        Log.d("error", "" + error)
        //utility!!.relative_snackbar(parent_closetsItems!!, error, getString(R.string.close_up))
    }

    override fun getClosetID(id: String?) {
        if (utility.isConnectingToInternet(this)) {
            pd.show()
            pd.setContentView(R.layout.loading)
            controller.AddToFavClosetItem(
                "Bearer " + getStringVal(Constants.TOKEN),
                id,
                "closet_item"
            )

        } else {
            utility!!.relative_snackbar(
                parent_closetsItems!!,
                "No Internet Connectivity",
                getString(R.string.close_up)
            )
        }
    }

    override fun getID(id: Int?) {
        ViewClosetItem(id)
    }


    //ToDo: View Closet Item
    @SuppressLint("CheckResult")
    private fun ViewClosetItem(id: Int?) {
        Viewdialog = Dialog(this!!)
        Viewdialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        Viewdialog.setContentView(R.layout.viewclosetitem)
        Viewdialog.getWindow()?.setBackgroundDrawable(ColorDrawable(android.graphics.Color.TRANSPARENT))
        val window = Viewdialog.window
        window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )

        var viewitem_image: ImageView
        var viewitem_title: TextView
        var viewitem_color: TextView
        var viewitem_size: TextView
        var viewitem_price: TextView
        var viewitem_category: TextView
        var viewitem_favcount: TextView
        var itemview_removebt: Button
        var viewitem_checkbox: CheckBox
        var itemview_spinner: Spinner
        var itemview_editbt: Button
        var itemview_closebt: Button
        var itemview_addtoclosetbt: Button
        var itemview_pinnertitle: TextView

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
        itemview_editbt = Viewdialog.findViewById(R.id.itemview_editbt)
        itemview_closebt = Viewdialog.findViewById(R.id.itemview_closebt)
        itemview_addtoclosetbt = Viewdialog.findViewById(R.id.itemview_addtoclosetbt)
        itemview_pinnertitle = Viewdialog.findViewById(R.id.itemview_pinnertitle)

        if (userID != getStringVal(Constants.USERID)) {
            itemview_removebt.visibility = View.GONE
            itemview_editbt.visibility = View.GONE
        }

        Glide.with(this).load(Constants.BASE_IMAGE_URL + closetResponse.get(id!!).picture)
            .placeholder(R.drawable.login_banner).into(viewitem_image)
        viewitem_title.text = "Title :" + closetResponse.get(id).title
        viewitem_color.text = "Color :" + closetResponse.get(id).color?.name
        if (closetResponse.get(id).hearts!!.size > 0) {
            viewitem_favcount.text = closetResponse.get(id).hearts!!.size.toString()
        }

        viewitem_size.text = "Size :" + closetResponse.get(id).size?.name
        viewitem_price.text = "Price :" + closetResponse.get(id).price
        viewitem_category.text = "Category :" + closetResponse.get(id).category?.name

        itemview_removebt.setOnClickListener {
            if (utility.isConnectingToInternet(this)) {

                DeleteDialog(closetResponse.get(id).id.toString())
            } else {
                utility.relative_snackbar(
                    parent_closetsItems!!,
                    "No Internet Connectivity",
                    getString(R.string.close_up)
                )
            }
        }


        itemview_editbt.setOnClickListener {
            Viewdialog.dismiss()
            startActivity(
                Intent(this, AddItemToCloset::class.java).putExtra("closetID", closetID)
                    .putExtra("edit", "1").putExtra("closetItemID", id.toString())
            )
            //addItemToCloset(closetItemID)
        }

        itemview_closebt.setOnClickListener {
            Viewdialog.dismiss()
        }

        searchUserHeart(closetResponse.get(id).hearts as MutableList<ClosetsItemsResponse.Data.Closet.Item.Heart>, viewitem_checkbox)

        setSpinnerData(
            userClosets,
            itemview_spinner,
            closetResponse.get(id).id!!,
            itemview_addtoclosetbt,
            itemview_pinnertitle
        )
        Viewdialog.show()
    }

    private fun setSpinnerData(
        userClosets: ArrayList<ClosetsItemsResponse.Data.AllCloset>,
        itemviewSpinner: Spinner,
        id: Int,
        itemviewAddtoclosetbt: Button,
        spinnertitle: TextView
    ) {
        list = ArrayList()
        listID = ArrayList()
        var closetId: String = ""
        for (i in 0 until userClosets.size) {

            val title = userClosets.get(i)
            list.add(title.title!!)
            listID.add(title.id.toString())
        }

        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_dropdown_item, list
        )

        itemviewSpinner.adapter = adapter
        itemviewSpinner.onItemSelectedListener = object :
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

        itemviewAddtoclosetbt.setOnClickListener {
            pd.show()
            controller.AddItemToAnotherCloset(
                "Bearer " + getStringVal(Constants.TOKEN),
                id.toString(), closetId, "closet_item"
            )
        }
    }

    private fun DeleteDialog(id: String?) {
        logoutDialog = Dialog(this)
        logoutDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        logoutDialog.setCancelable(false)
        logoutDialog.setContentView(R.layout.logout_dialog)
        val window = logoutDialog.window
        window?.setLayout(
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )

        val no: Button
        val yes: Button
        val text: TextView

        no = logoutDialog.findViewById(R.id.logout_no)
        yes = logoutDialog.findViewById(R.id.logout_yes)
        text = logoutDialog.findViewById(R.id.logout_tv)
        text.text = "Are you sure you want to delete it?"

        no.setOnClickListener {
            logoutDialog.dismiss()
        }

        yes.setOnClickListener {
            pd.show()
            pd.setContentView(R.layout.loading)
            logoutDialog.dismiss()
            Viewdialog.dismiss()
            controller.DeleteClosetItem(
                "Bearer " + getStringVal(Constants.TOKEN),
                id
            )
        }
        logoutDialog.show()
    }

    fun searchUserHeart(
        closetsItems: MutableList<ClosetsItemsResponse.Data.Closet.Item.Heart>,
        closetitemFavorite: CheckBox
    ) {
        if (closetsItems.size > 0) {
            for (i in closetsItems!!.indices) {
                val heart = closetsItems!![i]
                if (heart.userId.toString().equals(getStringVal(Constants.USERID))) {
                    closetitemFavorite.isChecked = true
                }
            }
        }
    }

    override fun getID(
        id: String?,
        s: String
    ) {
        if (s.equals("0")) {
            if (checkedClosetIDs.contains(id)) {
                checkedClosetIDs.remove(id)
            }
        } else {
            checkedClosetIDs.add(id.toString())
        }

        if (checkedClosetIDs.size > 0) {
            closet_moveitembt.visibility = View.VISIBLE
            closet_duplicateitembt.visibility = View.VISIBLE
            closet_makeoutfitbt.visibility = View.VISIBLE
        } else {
            closet_moveitembt.visibility = View.GONE
            closet_duplicateitembt.visibility = View.GONE
            closet_makeoutfitbt.visibility = View.GONE
        }

        Log.d("test", "" + checkedClosetIDs)
    }
}