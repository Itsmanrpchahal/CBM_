package com.casebeaumonde.fragments.productManagement.productdetail

import android.app.ProgressDialog
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.Html
import android.view.View
import android.view.WindowManager
import android.widget.*
import androidx.viewpager.widget.ViewPager
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.casebeaumonde.Controller.Controller
import com.casebeaumonde.R
import com.casebeaumonde.constants.BaseClass
import com.casebeaumonde.constants.Constants
import com.casebeaumonde.fragments.productManagement.response.ProductListResponse
import com.casebeaumonde.utilities.Utility
import kotlinx.android.synthetic.main.activity_product_detail_screen.*
import retrofit2.Response


class ProductDetailScreen : BaseClass(),Controller.ProductListAPI {
    private lateinit var position:String
    private lateinit var closetitems_back: ImageButton
   private lateinit var closetItemImage : ImageView
    private lateinit var name : TextView
    private lateinit var title : TextView
    private lateinit var actualprice : TextView
    private lateinit var newprice : TextView
    private lateinit var sdecs:TextView
    private lateinit var decs : TextView
    private lateinit var stockquantity : TextView
    private lateinit var category : TextView
    private lateinit var brand : TextView
    private lateinit var unpublish : Button
    private lateinit var utility: Utility
    private lateinit var pd: ProgressDialog
    private lateinit var controller: Controller
    private lateinit var products: ArrayList<ProductListResponse.Data.Products.Datum>
    var mViewPager: ViewPager? = null
    private lateinit var image : ArrayList<ProductListResponse.Data.Products.Datum.ProductImage>
    var layout_dot: LinearLayout? = null
    lateinit var dot: ArrayList<TextView>

    // Creating Object of ViewPagerAdapter
    private lateinit var mViewPagerAdapter: ViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail_screen)
        position = intent.getStringExtra("position").toString()
        findIds()
        listeners()
        controller = Controller()
        controller.Controller(this)
        if (utility.isConnectingToInternet(this)) {
            pd.show()
            pd.setContentView(R.layout.loading)
            controller.ProductList("Bearer " + getStringVal(Constants.TOKEN))
        } else {
            utility!!.relative_snackbar(
                productdetail!!,
                getString(R.string.nointernet),
                getString(R.string.close_up)
            )
        }

    }

    private fun listeners() {
        closetitems_back.setOnClickListener {
            onBackPressed()
        }
    }

    private fun findIds() {
        mViewPager = findViewById<View>(R.id.viewPagerMain) as ViewPager
        closetitems_back = findViewById(R.id.closetitems_back)
        closetItemImage = findViewById(R.id.closetItemImage)
        name = findViewById(R.id.name)
        title = findViewById(R.id.title)
        actualprice = findViewById(R.id.actualprice)
        newprice = findViewById(R.id.newprice)
        unpublish = findViewById(R.id.unpublish)
        sdecs = findViewById(R.id.sdecs)
        category = findViewById(R.id.category)
        brand = findViewById(R.id.brand)
        decs = findViewById(R.id.decs)
        stockquantity = findViewById(R.id.stockquantity1)
        layout_dot = findViewById(R.id.layout_dot);
        utility = Utility()
        pd = ProgressDialog(this)
        pd!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        pd!!.window!!.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        pd!!.isIndeterminate = true
        pd!!.setCancelable(false)
    }

    override fun onProductListSuccess(success: Response<ProductListResponse>) {
        pd.dismiss()
        if (success.isSuccessful) {
            if (success.body()?.code?.equals("200")!!) {
                products = ArrayList()

                image = ArrayList()
                image.addAll(success.body()?.data?.products?.data?.get(position.toInt())?.productImages!!)
                products.addAll(success.body()?.data?.products?.data!!)
               // Glide.with(this).load(products.get(position.toInt()).productImages.get(0).image).placeholder(R.drawable.login_banner1).into(closetItemImage)
                name.text ="Title :"+ products.get(position.toInt()).productName
                title.text ="Short Description :"+ products.get(position.toInt()).shortDescription
                actualprice.text ="$"+products.get(position.toInt()).regularPrice.toString()
                actualprice.setPaintFlags(actualprice.getPaintFlags() or Paint.STRIKE_THRU_TEXT_FLAG)
                newprice.text ="$"+products.get(position.toInt()).sellPrice.toString()
                sdecs.visibility = View.GONE
                decs.text ="Description :"+ products.get(position.toInt()).description
               // stockquantity.text ="Stock Quantity :"+ products.get(position.toInt()).stock_quantity.toString()
                category.text ="Category :"+products.get(position.toInt()).category.name
                if (products.get(position.toInt()).brand!=null)
                {
                    brand.text = "Brand :"+products.get(position.toInt()).brand.name
                }


                // Initializing the ViewPager Object

                // Initializing the ViewPager Object


                // Initializing the ViewPagerAdapter

                // Initializing the ViewPagerAdapter
                mViewPagerAdapter = ViewPagerAdapter(this, image)

                // Adding the Adapter to the ViewPager

                // Adding the Adapter to the ViewPager
                mViewPager!!.adapter = mViewPagerAdapter
                //addDot(0);
                // whenever the page changes

                // whenever the page changes
                mViewPager!!.addOnPageChangeListener(object : OnPageChangeListener {
                    override fun onPageScrolled(i: Int, v: Float, i1: Int) {}
                    override fun onPageSelected(i: Int) {
                      //  addDot(i)
                    }

                    override fun onPageScrollStateChanged(i: Int) {}
                })
            } else {
                utility!!.relative_snackbar(
                    productdetail!!,
                    success.message(),
                    getString(R.string.close_up)
                )
            }
        } else {
            pd.dismiss()
            utility!!.relative_snackbar(
                productdetail!!,
                success.message(),
                getString(R.string.close_up)
            )
        }
    }

    fun addDot(page_position: Int) {
        dot = ArrayList()
         dot[image.size]
        layout_dot!!.removeAllViews()
        for (i in 0 until image.size) {
            dot[i] = TextView(this)
            dot[i].text = Html.fromHtml("&#9673;")
            dot[i].setTextSize(35F)
            dot[i].setTextColor(resources.getColor(R.color.colorWhite))
            layout_dot!!.addView(dot[i])
        }
        //active dot
        dot[page_position].setTextColor(resources.getColor(R.color.colorgray))
    }

    override fun error(error: String?) {
        pd.dismiss()
        utility!!.relative_snackbar(
            productdetail!!,
           error,
            getString(R.string.close_up)
        )
    }
}