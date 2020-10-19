package com.casebeaumonde.activities.ClosetItem

import android.annotation.SuppressLint
import android.app.Dialog
import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
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
import com.casebeaumonde.activities.ClosetItem.IF.ClosetItemID_IF
import com.casebeaumonde.activities.ClosetItem.IF.SelectedCloset_ID
import com.casebeaumonde.activities.ClosetItem.response.AddToFavClosetItemResponse
import com.casebeaumonde.activities.ClosetItem.response.ClosetsItemsResponse
import com.casebeaumonde.activities.ClosetItem.response.DeleteClosetItemResponse
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
import retrofit2.Response

class ClosetsItems : BaseClass(), Controller.ClosetItemsAPI, ClosetItemID_IF, ViewClosetID_IF,
    SelectedCloset_ID,
    Controller.AddTofavClosetItemAPI, Controller.DeleteClosetItemAPI,
    Controller.AdDItemToAnotherClosetAPI, Controller.MoveItemAPI, Controller.DuplicateItemAPI,
    Controller.FetchListAPI, Controller.OutFItAPI {

    private lateinit var utility: Utility
    private lateinit var pd: ProgressDialog
    private lateinit var controller: Controller
    private lateinit var closetID: String
    private lateinit var closetsItems_recycler: RecyclerView
    private lateinit var closetitems_back: ImageButton
    private lateinit var closetiems_add: ImageButton
    private lateinit var hearlist: ArrayList<ClosetsItemsResponse.Data.Closet.Item.Heart>
    private lateinit var closetResponse: ArrayList<ClosetsItemsResponse.Data.Closet.Item>
    private lateinit var dialog: Dialog
    private lateinit var logoutDialog: Dialog
    private lateinit var Viewdialog: Dialog
    private lateinit var userID: String
    private lateinit var userClosets: ArrayList<ClosetsItemsResponse.Data.AllCloset>
    private lateinit var list: ArrayList<String>
    private lateinit var listID: ArrayList<String>
    private lateinit var closet_showselectbt: Button
    private lateinit var closet_deselectitembt: Button
    private lateinit var closet_selectitembt: Button
    private lateinit var closet_moveitembt: Button
    private lateinit var closet_duplicateitembt: Button
    private lateinit var closet_makeoutfitbt: Button
    private lateinit var moveItemDialog: Dialog
    private lateinit var checkedClosetIDs: ArrayList<String>
    private lateinit var selectedItems: ArrayList<String>
    private lateinit var listData: ArrayList<String>
    private lateinit var outFitRes: ArrayList<FetchListResponse.Data.Outfit>
    private lateinit var outFitTitle: ArrayList<String>
    private lateinit var outFitID: ArrayList<String>

    var select: Int = 0
    var selectAll: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_closets_items)
        findIds()
        checkedClosetIDs = ArrayList()
        selectedItems = ArrayList()
        controller = Controller()
        controller.Controller(this, this, this, this, this, this, this, this)
        closetID = intent?.getStringExtra(Constants.CLOSETID).toString()
        controller.FetchList("Bearer " + getStringVal(Constants.TOKEN))
        closetitemidIf = this
        viewclosetidIf = this
        selectedclosetId = this
        userID = intent.getStringExtra("userID")
        listeners()
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
        var checkbox: CheckBox
        var addoutfit : CheckBox
        listview = moveItemDialog.findViewById(R.id.listview)
        outfitlist = moveItemDialog.findViewById(R.id.outfitlist)
        moveitem = moveItemDialog.findViewById(R.id.moveitem)
        cancelitem = moveItemDialog.findViewById(R.id.cancelitem)
        checkbox = moveItemDialog.findViewById(R.id.checkbox)
        addnewclosetet = moveItemDialog.findViewById(R.id.addnewclosetet)
        addoutfit = moveItemDialog.findViewById(R.id.addoutfit)
        list = ArrayList()
        listID = ArrayList()
        outFitTitle = ArrayList()
        outFitID = ArrayList()

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
            list.add(title.title)
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
            outFitTitle.add(title.title)
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
    }

    //ToDo: Get  Closet All Items
    override fun onClosetItemSuccess(closetItemsResponse: Response<ClosetsItemsResponse>) {
        pd.dismiss()
        if (closetItemsResponse.isSuccessful) {
            //userClosets = closetItemsResponse.body()?.data?.closet as ArrayList<ClosetsItemsResponse.Data.Closet>
            userClosets = ArrayList()
            selectAll = 0
            userClosets.addAll(closetItemsResponse.body()?.data?.allClosets!!)
            setFullData(closetItemsResponse.body()?.data?.closet?.items, select, selectAll)
            listData = ArrayList()
            checkedClosetIDs = ArrayList()
            listData.clear()
            checkedClosetIDs.clear()
            if (closetItemsResponse.body()?.data?.closet?.userId.toString()
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
                deleteClosetItemResponse.body()?.message,
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
            utility!!.relative_snackbar(
                parent_closetsItems!!,
                addItemToAnotherCloset.body()?.messsage,
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
            utility!!.relative_snackbar(
                parent_closetsItems!!,
                moveItem.body()?.message,
                getString(R.string.close_up)
            )
        } else {
            utility!!.relative_snackbar(
                parent_closetsItems!!,
                moveItem.body()?.message,
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
            utility!!.relative_snackbar(
                parent_closetsItems!!,
                duplicateItem.body()?.message,
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
            outFitRes = ArrayList()
            outFitRes.addAll(fetchList.body()?.data?.outfits!!)
        } else {
            utility!!.relative_snackbar(
                parent_closetsItems!!,
                fetchList.message(),
                getString(R.string.close_up)
            )
        }
    }

    //ToDo: Outfit
    override fun onOutfitSuccess(outfit: Response<OutFitResponse>) {
        pd.dismiss()
        moveItemDialog.dismiss()
        setClosetAPI()
        if (outfit.isSuccessful) {
            utility!!.relative_snackbar(
                parent_closetsItems!!,
                outfit.body()?.message,
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


    override fun error(error: String?) {
        pd.dismiss()
        //dialog.dismiss()
        utility!!.relative_snackbar(parent_closetsItems!!, error, getString(R.string.close_up))
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
        viewitem_color.text = "Color :" + closetResponse.get(id).color.name
        if (closetResponse.get(id).hearts.size > 0) {
            viewitem_favcount.text = closetResponse.get(id).hearts.size.toString()
        }

        viewitem_size.text = "Size :" + closetResponse.get(id).size.name
        viewitem_price.text = "Price :" + closetResponse.get(id).price
        viewitem_category.text = "Category :" + closetResponse.get(id).category.name

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

        searchUserHeart(closetResponse.get(id).hearts, viewitem_checkbox)

        setSpinnerData(
            userClosets,
            itemview_spinner,
            closetResponse.get(id).id,
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
            list.add(title.title)
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