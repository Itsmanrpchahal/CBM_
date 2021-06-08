package com.casebeaumonde.activities.ShopItems

import android.annotation.SuppressLint
import android.app.Dialog
import android.app.ProgressDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.*
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.casebeaumonde.Controller.Controller
import com.casebeaumonde.R
import com.casebeaumonde.activities.ShopItems.IF.GetShopItemID
import com.casebeaumonde.activities.ShopItems.adapter.ShopItemsAdapter
import com.casebeaumonde.activities.ShopItems.response.AddtoCartResponse
import com.casebeaumonde.activities.ShopItems.response.ShopFilterItemsResponse
import com.casebeaumonde.activities.ShopItems.response.ShopItemsLIKEResponse
import com.casebeaumonde.activities.ShopItems.response.ShopItemsResponse
import com.casebeaumonde.constants.BaseClass
import com.casebeaumonde.constants.Constants
import com.casebeaumonde.utilities.Utility
import kotlinx.android.synthetic.main.activity_shop_items.*
import retrofit2.Response

class ShopItemsActivity : BaseClass() ,Controller.ShopItemsAPI,GetShopItemID,Controller.ShopItemsLikeAPI,Controller.AddtoCartAPI,Controller.SearchShopItemAPI{
    lateinit var shopID : String
    lateinit var retailer_spinner:Spinner
    lateinit var designer_spinner : Spinner
    lateinit var category_spinner : Spinner
    lateinit var price_spinner : Spinner
    lateinit var shop_items_recyler : RecyclerView
    private lateinit var dialog: Dialog
    private lateinit var closetitems_back :ImageButton

    private lateinit var reatilerName: ArrayList<String>
    private lateinit var designerName:ArrayList<String>
    private lateinit var categoryName : ArrayList<String>
    private lateinit var price :ArrayList<String>
    private lateinit var items : ArrayList<ShopItemsResponse.Data.Item>
    private lateinit var filterItems : ArrayList<ShopFilterItemsResponse.Datum>

    private lateinit var utility: Utility
    private lateinit var pd: ProgressDialog
    private lateinit var controller: Controller

    lateinit var image : ImageView
    lateinit var titletv : TextView
    lateinit var descriptiontv : TextView
    lateinit var pricetv : TextView
    lateinit var categorytv : TextView
    lateinit var sizetv : TextView
    lateinit var colortv : TextView
    lateinit var brandtv : TextView
    lateinit var shopitem_favorite : CheckBox
    lateinit var count : TextView
    lateinit var addtocart : Button
    lateinit var shopid : String
    lateinit var reset: Button

     var catID : String =""
     var retailerID :String=""
     var prices :String =""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop_items)

        findIds()
        closetitems_back.setOnClickListener { onBackPressed() }
        reset.setOnClickListener {
            retailer_spinner.setSelection(0)
            category_spinner.setSelection(0)
            price_spinner.setSelection(0)

            catID = ""
            retailerID = ""
            prices =""
            controller.ShopItems("Bearer "+getStringVal(Constants.TOKEN),"1")
            pd.show()
            pd.setContentView(R.layout.loading)

        }
    }

    private fun findIds() {
        shopID = intent.getStringExtra("shopID").toString()

        retailer_spinner = findViewById(R.id.retailer_spinner)
        designer_spinner = findViewById(R.id.designer_spinner)
        category_spinner = findViewById(R.id.category_spinner)
        price_spinner = findViewById(R.id.price_spinner)
        shop_items_recyler = findViewById(R.id.shop_items_recyler)
        closetitems_back = findViewById(R.id.closetitems_back)
        reset = findViewById(R.id.reset)

        price = ArrayList()
        price.add("Any price")
        price.add("1-100")
        price.add("100-200")
        price.add("200-300")
        price.add("300-400")
        price.add("400-above")

        controller = Controller()
        controller.Controller(this,this,this,this)
        utility = Utility()
        pd = ProgressDialog(this)
        pd!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        pd!!.window!!.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        pd!!.isIndeterminate = true
        pd!!.setCancelable(false)

        controller.ShopItems("Bearer "+getStringVal(Constants.TOKEN),"1")
        pd.show()
        pd.setContentView(R.layout.loading)

        getShopItemID = this
    }

    override fun onShopItemsSuccess(success: Response<ShopItemsResponse>) {
        pd.dismiss()
        if (success.isSuccessful)
        {
            if (success.body()?.code.equals("200"))
            {
                reatilerName = ArrayList()
                reatilerName.add("All Retailer")
                for (i in success.body()?.data?.retailers?.indices!!)

                {
                    reatilerName.add(success.body()?.data?.retailers?.get(i)?.firstname.toString()+" "+success.body()?.data?.retailers?.get(i)?.lastname)
                }



                //ToDo: Set Retailer in Spinner
                val adapter = ArrayAdapter(
                    this,
                    android.R.layout.simple_spinner_dropdown_item, reatilerName
                )
                retailer_spinner.adapter = adapter
                retailer_spinner.onItemSelectedListener = object :AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        parent: AdapterView<*>?,
                        view: View?,
                        position: Int,
                        id: Long
                    ) {
                        if (position!=0)
                        {
                            retailerID = success.body()?.data?.retailers!!.get(position-1).id.toString()
                            controller.SearchShopItems("Bearer "+getStringVal(Constants.TOKEN),retailerID,catID,prices)
                            pd.show()
                            pd.setContentView(R.layout.loading)
                        }
                    }

                    override fun onNothingSelected(parent: AdapterView<*>?) {

                    }

                }

                categoryName = ArrayList()
                categoryName.add("All Category")
                for (i in success?.body()?.data?.categories?.indices!!)
                {
                    categoryName.add(success.body()?.data?.categories?.get(i)?.name.toString())
                }

                //ToDo: Set Category in Spinner
                val adapter1 = ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,categoryName)
                category_spinner.adapter = adapter1
                category_spinner.onItemSelectedListener = object :AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        parent: AdapterView<*>?,
                        view: View?,
                        position: Int,
                        id: Long
                    ) {
                        if (position!=0)
                        {
                            catID = success.body()?.data?.categories!!.get(position-1).id.toString()
                            controller.SearchShopItems("Bearer "+getStringVal(Constants.TOKEN),retailerID,catID,prices)
                            pd.show()
                            pd.setContentView(R.layout.loading)
                        }
                    }

                    override fun onNothingSelected(parent: AdapterView<*>?) {

                    }

                }

                //ToDo: Set Price in Spinner
                val adapter2 = ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,price)
                price_spinner.adapter = adapter2
                price_spinner.onItemSelectedListener = object :AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        parent: AdapterView<*>?,
                        view: View?,
                        position: Int,
                        id: Long
                    ) {
                        if (position!=0)
                        {
                            pd.show()
                            pd.setContentView(R.layout.loading)

                            if (position==5)
                            {
                                prices = "400-10000000"
                                controller.SearchShopItems("Bearer "+getStringVal(Constants.TOKEN),retailerID,catID,prices)
                            } else {
                                prices = price.get(position).toString()
                                controller.SearchShopItems("Bearer "+getStringVal(Constants.TOKEN),retailerID,catID,prices)
                            }


                        }

                    }

                    override fun onNothingSelected(parent: AdapterView<*>?) {

                    }

                }


                items = ArrayList()
                filterItems = ArrayList()
                filterItems.clear()
                items.addAll(success.body()?.data?.items!!)
                shop_items_recyler.layoutManager =
                    GridLayoutManager(this, 2)
                val itemsadapter = ShopItemsAdapter(
                    this, items,filterItems,"all"
                )
                shop_items_recyler.adapter = itemsadapter
            } else {
                utility!!.relative_snackbar(
                    parent_shopitems!!,
                    success.message(),
                    getString(R.string.close_up)
                )
            }
        } else {
            utility!!.relative_snackbar(
                parent_shopitems!!,
                success.message(),
                getString(R.string.close_up)
            )
        }
    }

    companion object {
        var getShopItemID:GetShopItemID? = null
    }

    override fun onSearchShopItemSuccess(success: Response<ShopFilterItemsResponse>) {
        pd.dismiss()
        if (success.isSuccessful)
        {
            if (success.body()?.code.equals("200"))
            {
                filterItems = ArrayList()
                for (i in success.body()?.data?.indices!!)
                {

                }
                filterItems.addAll(success.body()?.data!!)
                shop_items_recyler.layoutManager =
                    GridLayoutManager(this, 2)
                val itemsadapter = ShopItemsAdapter(
                    this, items,filterItems,"filter"
                )
                shop_items_recyler.adapter = itemsadapter

            } else {
                pd.dismiss()
                utility!!.relative_snackbar(
                    parent_shopitems!!,
                    success.message(),
                    getString(R.string.close_up)
                )
            }
        } else {
            pd.dismiss()
            utility!!.relative_snackbar(
                parent_shopitems!!,
                success.message(),
                getString(R.string.close_up)
            )
        }
    }


    override fun error(error: String?) {
       pd.dismiss()
        utility!!.relative_snackbar(
            parent_shopitems!!,
            error,
            getString(R.string.close_up)
        )
    }

    @SuppressLint("CheckResult")
    override fun getID(pos: String?, id: String?,type:String) {
        dialog = Dialog(this!!)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.viewshopitem)
        val window = dialog.window
        window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT)

        shopid = id.toString()


        image = dialog.findViewById(R.id.image)
        titletv = dialog.findViewById(R.id.titletv)
        descriptiontv = dialog.findViewById(R.id.descriptiontv)
        pricetv = dialog.findViewById(R.id.pricetv)
        categorytv = dialog.findViewById(R.id.categorytv)
        sizetv = dialog.findViewById(R.id.sizetv)
        colortv = dialog.findViewById(R.id.colortv)
        brandtv = dialog.findViewById(R.id.brandtv)
        shopitem_favorite = dialog.findViewById(R.id.shopitem_favorite)
        count = dialog.findViewById(R.id.count)
        addtocart = dialog.findViewById(R.id.addtocart)

        if (type.equals("filter"))
        {
            Glide.with(this).load(Constants.BASE_IMAGE_URL+filterItems.get(pos!!.toInt()).image).placeholder(R.drawable.login_banner1).into(image)
            titletv.text = "Title: "+filterItems.get(pos!!.toInt()).name
            descriptiontv.text = "Description: "+filterItems.get(pos!!.toInt()).description
            pricetv.text = "Price: $"+filterItems.get(pos!!.toInt()).price
            categorytv.text = "Category: "+filterItems.get(pos!!.toInt()).categoryId.toString()
            sizetv.text = "Size: "+filterItems.get(pos!!.toInt()).size.toString()
            colortv.text = "Color: "+filterItems.get(pos!!.toInt()).color.toString()
            brandtv.text = "Brand: "+filterItems.get(pos!!.toInt()).brand.toString()
            count.text = filterItems.get(pos!!.toInt()).likes.size.toString()

            shopitem_favorite.setOnClickListener {
                controller.ShopItemsLike("Bearer "+getStringVal(Constants.TOKEN),id)
            }


            searchUserHeart(items.get(pos!!.toInt()),shopitem_favorite)

            addtoCart(addtocart,id)
        } else {
            Glide.with(this).load(Constants.BASE_IMAGE_URL+items.get(pos!!.toInt()).image).placeholder(R.drawable.login_banner1).into(image)
            titletv.text = "Title: "+items.get(pos!!.toInt()).name
            descriptiontv.text = "Description: "+items.get(pos!!.toInt()).description
            pricetv.text = "Price: $"+items.get(pos!!.toInt()).price
            categorytv.text = "Category: "+items.get(pos!!.toInt()).categoryId.toString()
            sizetv.text = "Size: "+items.get(pos!!.toInt()).size.toString()
            colortv.text = "Color: "+items.get(pos!!.toInt()).color.toString()
            brandtv.text = "Brand: "+items.get(pos!!.toInt()).brand.toString()
            count.text = items.get(pos!!.toInt()).likes.size.toString()

            shopitem_favorite.setOnClickListener {
                controller.ShopItemsLike("Bearer "+getStringVal(Constants.TOKEN),id)
            }


            searchUserHeart(items.get(pos!!.toInt()),shopitem_favorite)

            addtoCart(addtocart,id)
        }


        dialog.show()
    }

    private fun addtoCart(addtocart: Button?, id: String?) {
    addtocart?.setOnClickListener {
        controller.AddtoCart("Bearer "+getStringVal(Constants.TOKEN),id,"1")
        pd.show()
        pd.setContentView(R.layout.loading)
    }
    }

    override fun onAddtoCartSuccess(success: Response<AddtoCartResponse>) {
        pd.dismiss()
        if (success.isSuccessful)
        {
            if (success.body()?.code?.equals("200")!!)
            {


                dialog.dismiss()
                utility!!.relative_snackbar(
                    parent_shopitems!!,
                    success.body()?.message,
                    getString(R.string.close_up)
                )
            } else {
                utility!!.relative_snackbar(
                    parent_shopitems!!,
                    success.message(),
                    getString(R.string.close_up)
                )
            }
        }else {
            utility!!.relative_snackbar(
                parent_shopitems!!,
                success.message(),
                getString(R.string.close_up)
            )
        }
    }

    fun searchUserHeart(
        items: ShopItemsResponse.Data.Item,
        closetitemFavorite: CheckBox
    )
    {
        if (items.likes!!.size>0)
        {
            for (i in items.likes!!.indices)
            {
                val heart = items.likes!![i]
                if (heart.userId.toString().equals(getStringVal(Constants.USERID)))
                {
                    closetitemFavorite.isChecked = true
                }
            }
        }
    }

    override fun onShopItemLikeSuccess(success: Response<ShopItemsLIKEResponse>) {
        pd.dismiss()
        if (success.isSuccessful)
        {
            if (success.body()?.code.equals("200"))
            {
                filterItems = ArrayList()
                filterItems.clear()
                shop_items_recyler.layoutManager =
                    GridLayoutManager(this, 2)
                val itemsadapter = ShopItemsAdapter(
                    this, items,filterItems,"all"
                )
                shop_items_recyler.adapter = itemsadapter
            } else {
                utility!!.relative_snackbar(
                    parent_shopitems!!,
                    success.message(),
                    getString(R.string.close_up)
                )
            }
        }else {
            utility!!.relative_snackbar(
                parent_shopitems!!,
                success.message(),
                getString(R.string.close_up)
            )
        }
    }

}