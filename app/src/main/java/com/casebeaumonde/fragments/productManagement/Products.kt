package com.casebeaumonde.fragments.productManagement

import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.casebeaumonde.Controller.Controller
import com.casebeaumonde.R
import com.casebeaumonde.constants.BaseFrag
import com.casebeaumonde.constants.Constants
import com.casebeaumonde.fragments.productManagement.IF.DeleteProductIF
import com.casebeaumonde.fragments.productManagement.IF.EditProductIF
import com.casebeaumonde.fragments.productManagement.IF.UnPublishIF
import com.casebeaumonde.fragments.productManagement.adapter.ProductListAdapter
import com.casebeaumonde.fragments.productManagement.addproduct.AddNewProduct
import com.casebeaumonde.fragments.productManagement.response.ProductListResponse
import com.casebeaumonde.fragments.productManagement.response.ProductPublishUnPublishResponse
import com.casebeaumonde.utilities.Utility
import kotlinx.android.synthetic.main.fragment_products.*
import retrofit2.Response

class Products : BaseFrag(), Controller.ProductListAPI ,DeleteProductIF,UnPublishIF,Controller.ProductPublishUnPublishAPI,EditProductIF{

    private lateinit var utility: Utility
    private lateinit var pd: ProgressDialog
    private lateinit var controller: Controller
    private lateinit var productsrecycler: RecyclerView
    private lateinit var importproducts: Button
    private lateinit var addnewproduct: Button
    private lateinit var products: ArrayList<ProductListResponse.Data.Products.Datum>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view: View
        view = inflater.inflate(R.layout.fragment_products, container, false)

        findIds(view)
        controller = Controller()
        controller.Controller(this,this)


        lisenters()
        return view
    }

    override fun onResume() {
        super.onResume()
        if (utility.isConnectingToInternet(context)) {
            pd.show()
            pd.setContentView(R.layout.loading)
            controller.ProductList("Bearer " + getStringVal(Constants.TOKEN))
        } else {
            utility!!.relative_snackbar(
                productscreen!!,
                getString(R.string.nointernet),
                getString(R.string.close_up)
            )
        }
    }

    private fun lisenters() {
        addnewproduct.setOnClickListener {
            startActivity(Intent(context, AddNewProduct::class.java).putExtra("type","add"))
        }
    }

    private fun findIds(view: View?) {

        deleteProductIF = this
        unPublishIF = this
        editProductIF = this
        utility = Utility()
        pd = ProgressDialog(context)
        pd!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        pd!!.window!!.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        pd!!.isIndeterminate = true
        pd!!.setCancelable(false)

        importproducts = view!!.findViewById(R.id.importproducts)
        addnewproduct = view.findViewById(R.id.addnewproduct)
        productsrecycler = view.findViewById(R.id.productsrecycler)

    }

    override fun onProductListSuccess(success: Response<ProductListResponse>) {
        pd.dismiss()
        if (success.isSuccessful) {
            if (success.body()?.code?.equals("200")!!) {
                products = ArrayList()
                products.addAll(success.body()?.data?.products?.data!!)
                productsrecycler.layoutManager =
                    LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                //closets =  response
                val adapter = ProductListAdapter(
                    context!!, products
                )
                productsrecycler.adapter = adapter
            } else {
                utility!!.relative_snackbar(
                    productscreen!!,
                    success.message(),
                    getString(R.string.close_up)
                )
            }
        } else {
            utility!!.relative_snackbar(
                productscreen!!,
                success.message(),
                getString(R.string.close_up)
            )
        }
    }

    override fun onProductPulishUnPublishSuccess(success: Response<ProductPublishUnPublishResponse>) {

        if (success.isSuccessful)
        {
             if (success.body()?.code?.equals("200")!!)
             {
                 controller.ProductList("Bearer " + getStringVal(Constants.TOKEN))

                 utility!!.relative_snackbar(
                     productscreen!!,
                     success.body()?.message,
                     getString(R.string.close_up)
                 )
             }else {
                 pd.dismiss()
                 utility!!.relative_snackbar(
                     productscreen!!,
                     success.message(),
                     getString(R.string.close_up)
                 )
             }
        }else {
            pd.dismiss()
            utility!!.relative_snackbar(
                productscreen!!,
                success.message(),
                getString(R.string.close_up)
            )
        }
    }

    override fun error(error: String?) {
        pd.dismiss()
        utility!!.relative_snackbar(
            productscreen!!,
            error,
            getString(R.string.close_up)
        )
    }

    companion object {
        var deleteProductIF : DeleteProductIF? = null
        var unPublishIF : UnPublishIF? = null
        var editProductIF : EditProductIF? = null
    }

    override fun deleteProductID(id: String) {


    }

    override fun unPublishID(id: String) {
        Log.d("here",id)
        if (utility.isConnectingToInternet(context)) {
            pd.show()
            pd.setContentView(R.layout.loading)
            controller.ProductPublishUnPublish("Bearer " + getStringVal(Constants.TOKEN),id)
        } else {
            utility!!.relative_snackbar(
                productscreen!!,
                getString(R.string.nointernet),
                getString(R.string.close_up)
            )
        }
    }

    override fun getEditProductID(id: String, pos: String,s:String) {
        startActivity(Intent(context,AddNewProduct::class.java).putExtra("id",id).putExtra("pos",pos).putExtra("type",s))
    }
}