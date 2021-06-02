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
import com.casebeaumonde.fragments.productManagement.IF.DeleteProductIF
import com.casebeaumonde.fragments.productManagement.IF.UnPublishIF
import com.casebeaumonde.fragments.productManagement.adapter.ProductListAdapter
import com.casebeaumonde.fragments.productManagement.addproduct.AddNewProduct
import com.casebeaumonde.fragments.productManagement.response.ProductListResponse
import com.casebeaumonde.fragments.productManagement.response.ProductPublishUnPublishResponse
import com.casebeaumonde.utilities.Utility
import kotlinx.android.synthetic.main.fragment_products.*
import retrofit2.Response

class Products : BaseFrag(), Controller.ProductListAPI ,DeleteProductIF,UnPublishIF,Controller.ProductPublishUnPublishAPI{

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
        if (utility.isConnectingToInternet(context)) {
            pd.show()
            pd.setContentView(R.layout.loading)
            controller.ProductList("Bearer " + "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6IjYwNzBjM2I1M2MzZjQzZDQyYmQ2MjA5MjlhNWY5YWIyYjA0OTFmNzJlMTMyZmVlNzgzZmI2ZTE1NWFjNjhlNTEyNWMxOGRhMGRlZjVjMGE3In0.eyJhdWQiOiIxIiwianRpIjoiNjA3MGMzYjUzYzNmNDNkNDJiZDYyMDkyOWE1ZjlhYjJiMDQ5MWY3MmUxMzJmZWU3ODNmYjZlMTU1YWM2OGU1MTI1YzE4ZGEwZGVmNWMwYTciLCJpYXQiOjE2MjI2MjI1MjAsIm5iZiI6MTYyMjYyMjUyMCwiZXhwIjoxNjU0MTU4NTIwLCJzdWIiOiI0Iiwic2NvcGVzIjpbXX0.M2Csyr5DOWL_rZi8Cabf5IV6_DFghI8eTRhip1wVAWw8pWf20-GeJrS6mAPjte0nvcdTpMItHlk3vSQE9aWbs_J-v26jKBeVswqJOlAC9ouK55KDHagv1iMcF6arOknVXwwp3DjechX59M_jhXNyzKGJh5QJidhXlBZgcorkCz-javQ1cDxTcvsXyXmvw3KLXfzkUPPdnLVwfRStwnPC_cx1SoeDLY0ii2ysBrWLz8AgLrlvRWfzM42ZNYnptK9HpWSNQRYSvyQQEWSk9lAkEkpA9QBTFFzegBuBPflBYBteanuMkMJ9UOb4j46hRybLUjJ2l5LBvoefg6_a7Ttc__vwsJhi9CLYiFaMpvSw-vclgfJ6LtKWtDMOItZyNh8d1RrcQZZB__Zx6KFvrrWhO9didyQoo6sh9LSLiU3Lp-fjMIDaWSXduJj1I4KFMMiLqdhvvkwlFOoFety4vedWtZo4VlG0Q87LdBU9W9sHZWOWrZFC6Fr4cUmZkQpHiB2GV8mb0J4ITpsRCe03xNNYgay5EtB6cBTbdarbOT9Pezc6jetPhmuQpxDaPUdLQRc9RnDBfS2XkL0375B4P2OB4aA-VhefJNnhFNtzvUkr0FaG3gv891Pj_h8CNkHHNGVu9uBAqHt1r_lpc5H_7lL3CObN7CyuryQ7GmhrSc-zL60")
        } else {
            utility!!.relative_snackbar(
                productscreen!!,
                getString(R.string.nointernet),
                getString(R.string.close_up)
            )
        }

        lisenters()
        return view
    }

    private fun lisenters() {
        addnewproduct.setOnClickListener {
            startActivity(Intent(context, AddNewProduct::class.java))
        }
    }

    private fun findIds(view: View?) {

        deleteProductIF = this
        unPublishIF = this
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
                 controller.ProductList("Bearer " + "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6IjYwNzBjM2I1M2MzZjQzZDQyYmQ2MjA5MjlhNWY5YWIyYjA0OTFmNzJlMTMyZmVlNzgzZmI2ZTE1NWFjNjhlNTEyNWMxOGRhMGRlZjVjMGE3In0.eyJhdWQiOiIxIiwianRpIjoiNjA3MGMzYjUzYzNmNDNkNDJiZDYyMDkyOWE1ZjlhYjJiMDQ5MWY3MmUxMzJmZWU3ODNmYjZlMTU1YWM2OGU1MTI1YzE4ZGEwZGVmNWMwYTciLCJpYXQiOjE2MjI2MjI1MjAsIm5iZiI6MTYyMjYyMjUyMCwiZXhwIjoxNjU0MTU4NTIwLCJzdWIiOiI0Iiwic2NvcGVzIjpbXX0.M2Csyr5DOWL_rZi8Cabf5IV6_DFghI8eTRhip1wVAWw8pWf20-GeJrS6mAPjte0nvcdTpMItHlk3vSQE9aWbs_J-v26jKBeVswqJOlAC9ouK55KDHagv1iMcF6arOknVXwwp3DjechX59M_jhXNyzKGJh5QJidhXlBZgcorkCz-javQ1cDxTcvsXyXmvw3KLXfzkUPPdnLVwfRStwnPC_cx1SoeDLY0ii2ysBrWLz8AgLrlvRWfzM42ZNYnptK9HpWSNQRYSvyQQEWSk9lAkEkpA9QBTFFzegBuBPflBYBteanuMkMJ9UOb4j46hRybLUjJ2l5LBvoefg6_a7Ttc__vwsJhi9CLYiFaMpvSw-vclgfJ6LtKWtDMOItZyNh8d1RrcQZZB__Zx6KFvrrWhO9didyQoo6sh9LSLiU3Lp-fjMIDaWSXduJj1I4KFMMiLqdhvvkwlFOoFety4vedWtZo4VlG0Q87LdBU9W9sHZWOWrZFC6Fr4cUmZkQpHiB2GV8mb0J4ITpsRCe03xNNYgay5EtB6cBTbdarbOT9Pezc6jetPhmuQpxDaPUdLQRc9RnDBfS2XkL0375B4P2OB4aA-VhefJNnhFNtzvUkr0FaG3gv891Pj_h8CNkHHNGVu9uBAqHt1r_lpc5H_7lL3CObN7CyuryQ7GmhrSc-zL60")

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
    }

    override fun deleteProductID(id: String) {


    }

    override fun unPublishID(id: String) {
        Log.d("here",id)
        if (utility.isConnectingToInternet(context)) {
            pd.show()
            pd.setContentView(R.layout.loading)
            controller.ProductPublishUnPublish("Bearer " + "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6IjYwNzBjM2I1M2MzZjQzZDQyYmQ2MjA5MjlhNWY5YWIyYjA0OTFmNzJlMTMyZmVlNzgzZmI2ZTE1NWFjNjhlNTEyNWMxOGRhMGRlZjVjMGE3In0.eyJhdWQiOiIxIiwianRpIjoiNjA3MGMzYjUzYzNmNDNkNDJiZDYyMDkyOWE1ZjlhYjJiMDQ5MWY3MmUxMzJmZWU3ODNmYjZlMTU1YWM2OGU1MTI1YzE4ZGEwZGVmNWMwYTciLCJpYXQiOjE2MjI2MjI1MjAsIm5iZiI6MTYyMjYyMjUyMCwiZXhwIjoxNjU0MTU4NTIwLCJzdWIiOiI0Iiwic2NvcGVzIjpbXX0.M2Csyr5DOWL_rZi8Cabf5IV6_DFghI8eTRhip1wVAWw8pWf20-GeJrS6mAPjte0nvcdTpMItHlk3vSQE9aWbs_J-v26jKBeVswqJOlAC9ouK55KDHagv1iMcF6arOknVXwwp3DjechX59M_jhXNyzKGJh5QJidhXlBZgcorkCz-javQ1cDxTcvsXyXmvw3KLXfzkUPPdnLVwfRStwnPC_cx1SoeDLY0ii2ysBrWLz8AgLrlvRWfzM42ZNYnptK9HpWSNQRYSvyQQEWSk9lAkEkpA9QBTFFzegBuBPflBYBteanuMkMJ9UOb4j46hRybLUjJ2l5LBvoefg6_a7Ttc__vwsJhi9CLYiFaMpvSw-vclgfJ6LtKWtDMOItZyNh8d1RrcQZZB__Zx6KFvrrWhO9didyQoo6sh9LSLiU3Lp-fjMIDaWSXduJj1I4KFMMiLqdhvvkwlFOoFety4vedWtZo4VlG0Q87LdBU9W9sHZWOWrZFC6Fr4cUmZkQpHiB2GV8mb0J4ITpsRCe03xNNYgay5EtB6cBTbdarbOT9Pezc6jetPhmuQpxDaPUdLQRc9RnDBfS2XkL0375B4P2OB4aA-VhefJNnhFNtzvUkr0FaG3gv891Pj_h8CNkHHNGVu9uBAqHt1r_lpc5H_7lL3CObN7CyuryQ7GmhrSc-zL60",id)
        } else {
            utility!!.relative_snackbar(
                productscreen!!,
                getString(R.string.nointernet),
                getString(R.string.close_up)
            )
        }
    }
}