package com.casebeaumonde.fragments.productManagement.productdetail

import android.app.ProgressDialog
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.casebeaumonde.Controller.Controller
import com.casebeaumonde.R
import com.casebeaumonde.constants.BaseClass
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
            controller.ProductList("Bearer " + "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6IjYwNzBjM2I1M2MzZjQzZDQyYmQ2MjA5MjlhNWY5YWIyYjA0OTFmNzJlMTMyZmVlNzgzZmI2ZTE1NWFjNjhlNTEyNWMxOGRhMGRlZjVjMGE3In0.eyJhdWQiOiIxIiwianRpIjoiNjA3MGMzYjUzYzNmNDNkNDJiZDYyMDkyOWE1ZjlhYjJiMDQ5MWY3MmUxMzJmZWU3ODNmYjZlMTU1YWM2OGU1MTI1YzE4ZGEwZGVmNWMwYTciLCJpYXQiOjE2MjI2MjI1MjAsIm5iZiI6MTYyMjYyMjUyMCwiZXhwIjoxNjU0MTU4NTIwLCJzdWIiOiI0Iiwic2NvcGVzIjpbXX0.M2Csyr5DOWL_rZi8Cabf5IV6_DFghI8eTRhip1wVAWw8pWf20-GeJrS6mAPjte0nvcdTpMItHlk3vSQE9aWbs_J-v26jKBeVswqJOlAC9ouK55KDHagv1iMcF6arOknVXwwp3DjechX59M_jhXNyzKGJh5QJidhXlBZgcorkCz-javQ1cDxTcvsXyXmvw3KLXfzkUPPdnLVwfRStwnPC_cx1SoeDLY0ii2ysBrWLz8AgLrlvRWfzM42ZNYnptK9HpWSNQRYSvyQQEWSk9lAkEkpA9QBTFFzegBuBPflBYBteanuMkMJ9UOb4j46hRybLUjJ2l5LBvoefg6_a7Ttc__vwsJhi9CLYiFaMpvSw-vclgfJ6LtKWtDMOItZyNh8d1RrcQZZB__Zx6KFvrrWhO9didyQoo6sh9LSLiU3Lp-fjMIDaWSXduJj1I4KFMMiLqdhvvkwlFOoFety4vedWtZo4VlG0Q87LdBU9W9sHZWOWrZFC6Fr4cUmZkQpHiB2GV8mb0J4ITpsRCe03xNNYgay5EtB6cBTbdarbOT9Pezc6jetPhmuQpxDaPUdLQRc9RnDBfS2XkL0375B4P2OB4aA-VhefJNnhFNtzvUkr0FaG3gv891Pj_h8CNkHHNGVu9uBAqHt1r_lpc5H_7lL3CObN7CyuryQ7GmhrSc-zL60")
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
                products.addAll(success.body()?.data?.products?.data!!)
                Glide.with(this).load(products.get(position.toInt()).productImages.get(0).image).placeholder(R.drawable.login_banner1).into(closetItemImage)
                name.text ="Title :"+ products.get(position.toInt()).productName
                title.text ="Short Description :"+ products.get(position.toInt()).shortDescription
                actualprice.text ="$"+products.get(position.toInt()).regularPrice.toString()
                actualprice.setPaintFlags(actualprice.getPaintFlags() or Paint.STRIKE_THRU_TEXT_FLAG)
                newprice.text ="$"+products.get(position.toInt()).sellPrice.toString()
                sdecs.visibility = View.GONE
                decs.text ="Description :"+ products.get(position.toInt()).description
               // stockquantity.text ="Stock Quantity :"+ products.get(position.toInt()).stock_quantity.toString()
                category.text ="Category :"+products.get(position.toInt()).category.name
                brand.text = "Brand :"+products.get(position.toInt()).brand.name
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

    override fun error(error: String?) {
        pd.dismiss()
        utility!!.relative_snackbar(
            productdetail!!,
           error,
            getString(R.string.close_up)
        )
    }
}