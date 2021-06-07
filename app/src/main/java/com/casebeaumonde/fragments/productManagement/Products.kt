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
            controller.ProductList("Bearer " + "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6ImZkOTQ2N2I5YTVkOTc2NjEwMzFjNzlkYjQ3NmY1YWQyNWFjZGYxNjZjODBjNjExMTE1ZTk1NDAyMTJkZThjYzVjY2IxYmJkNjhmZjllMWRhIn0.eyJhdWQiOiIxIiwianRpIjoiZmQ5NDY3YjlhNWQ5NzY2MTAzMWM3OWRiNDc2ZjVhZDI1YWNkZjE2NmM4MGM2MTExMTVlOTU0MDIxMmRlOGNjNWNjYjFiYmQ2OGZmOWUxZGEiLCJpYXQiOjE2MjI3ODE0MzUsIm5iZiI6MTYyMjc4MTQzNSwiZXhwIjoxNjU0MzE3NDM1LCJzdWIiOiIxMTEiLCJzY29wZXMiOltdfQ.e9RrUdPPTXJ9amldSLut1DUhEDXGK9TDmwSv9gqJM-BDveob_aLqH_cW3p0j1JxRUsHhbND1U5QXf22qeTDWe74DLQLEXVqdFTlA9G9AdOR5DjOem3LxQnb7yrGUz8JNPn5ntruUB_WyNQs6BGYJ1REC6ADRN4E1W4ZLJbNotbvaC41MQJ526UWFVB825MeMQoGSKo-DVHtShfqKXzMKA6tYz7T4wdy_1yBJWmnYlAyAY-qAgdIDvpKvByEJ2A0XStqiht3ptRsnJXKcQmlV0yieU25siQz1XPLXlebfr4OjiYZAaQSv7MQNtRNwc32gWvmNpZpnIgtu_n01mtJuXn8cYSSx66r3TTCP1plFaLL30iXAz2x-NRcAcn37ekSBmsNc_EZNSQypWVWsgAoDA-2Um5VT-IJvCsQUglzT-QThNBXFtBcBWzVLD2mLEpFSryq3sK5jT7Klx71ehY6MekPOmJJr6w4kznEsD0Nmq923UsZ_0eJvvXdxL5wjZYjuK1xkX1QClsukE2XHMPL0RrUoabyglzMKLmdZioGHtV2SlNOUYob0I3mTPhR8vj-riRFEKBAB1aXfWq9B7BAQ7i4mptNz8qA4VcEBq2y3FSxH0xkbT2kQf7QBKiLVWz-wvTKP5ucDpLp1DqAZKz4Yrvjo5enaraGHtpvJuqVBZu8")
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
        var editProductIF : EditProductIF? = null
    }

    override fun deleteProductID(id: String) {


    }

    override fun unPublishID(id: String) {
        Log.d("here",id)
        if (utility.isConnectingToInternet(context)) {
            pd.show()
            pd.setContentView(R.layout.loading)
            controller.ProductPublishUnPublish("Bearer " + "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6ImE2Y2JkYzgwZTZhYjZlYWYxNGNlNmI3ODk0MTNjZjNjOGI1YzNkZTUwNmFhYjViZWIzNzI1Nzk0NDY0ZGY0ODZjNzYwZTY3N2ZjNzIyNGE5In0.eyJhdWQiOiIxIiwianRpIjoiYTZjYmRjODBlNmFiNmVhZjE0Y2U2Yjc4OTQxM2NmM2M4YjVjM2RlNTA2YWFiNWJlYjM3MjU3OTQ0NjRkZjQ4NmM3NjBlNjc3ZmM3MjI0YTkiLCJpYXQiOjE2MjMwMzkxMTcsIm5iZiI6MTYyMzAzOTExNywiZXhwIjoxNjU0NTc1MTE3LCJzdWIiOiIxMTEiLCJzY29wZXMiOltdfQ.xUWHZC_4LLw7vb6uTikPFAJ4K4u8KZllxaUn7f8Nc6r_u7c8ACjhHnk_2KAdISoOYgAbCKLxXz3WZGVxsgQCYazze63uZWvSq3R-31uHEb0Gs5vxR8lXr6jU_8YBacYjnRM0si9ahQ_K5Wq65kNIcU2-xtvp8r688ANmwMUSb7SaxPooVwHD_5xcftPYTqH615SIif_1JAL0PZMUnMFRG2BxWMjT1cNsqeVY2GOIti8Fh6OVFHMFhoUG61LAWS53V8BADaypQwue0hReX7e6Na20Ex-846kgSNYkC3G_B1ewwEdHH0EBGPYuz83PtzJzzfmj0tHfEUSEv2pj9ESUt8bGERPvs9TkqCSZweBQWRb65nn8ThgGfoa5qWlvSYlidYmTomFbd6CtS134KGnFTlxipzSRDnt3y2dAtQk8DlQ2Z9wIKMoIJUK59yGrQX9E77TtJpjzmg3rtIgVocFVsrqIb0Z94JNJvnMYFqJpHhYcrLZ8aOFtWxTdg0mobagGxRH_yjDjoEc_Nbqgg2bTRe2-O2vUujmATOxbwQfcZECF0rPWa23H92j0t5l8zXeFJuKApfT6YcwtVAMW3WGbQpy9aPfRQubmF-0GkDxLjhgnyBgWiXkl-blp2oxTbyhvJs9aIsdYquqy0PZQmbheDFaKluSlaOwRyF8dUhCcmps",id)
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