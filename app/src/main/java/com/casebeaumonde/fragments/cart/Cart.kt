package com.casebeaumonde.fragments.cart

import android.app.ProgressDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.casebeaumonde.Controller.Controller
import com.casebeaumonde.R
import com.casebeaumonde.constants.BaseFrag
import com.casebeaumonde.constants.Constants
import com.casebeaumonde.fragments.cart.adapter.CartAdapter
import com.casebeaumonde.fragments.cart.reponse.CartItemsResponse
import com.casebeaumonde.utilities.Utility
import kotlinx.android.synthetic.main.fragment_cart.*
import retrofit2.Response

class Cart : BaseFrag(), Controller.CartItemAPI {

    private lateinit var cartitems_recycler: RecyclerView
    private lateinit var utility: Utility
    private lateinit var pd: ProgressDialog
    private lateinit var controller: Controller
    private lateinit var response: ArrayList<CartItemsResponse.Data.CartItem>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View
        view = inflater.inflate(R.layout.fragment_cart, container, false)
        controller = Controller()
        controller.Controller(this)
        findIds(view)

        if (utility.isConnectingToInternet(context)) {
            pd.show()
            pd.setContentView(R.layout.loading)
            controller.CartItems("Bearer " + getStringVal(Constants.TOKEN))
        } else {
            utility!!.relative_snackbar(
                parent_cart!!,
                getString(R.string.nointernet),
                getString(R.string.close_up)
            )
        }

        return view
    }

    private fun findIds(view: View) {
        utility = Utility()
        pd = ProgressDialog(context)
        pd!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        pd!!.window!!.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        pd!!.isIndeterminate = true
        pd!!.setCancelable(false)
        cartitems_recycler = view.findViewById(R.id.cartitems_recycler)
    }

    override fun onCartItemSuccess(cartitem: Response<CartItemsResponse>) {
        pd.dismiss()

        if (cartitem.isSuccessful) {

            response = cartitem.body()?.getData()?.cartItems as ArrayList<CartItemsResponse.Data.CartItem>
            setFullData(response)
        } else {
            utility!!.relative_snackbar(
                parent_cart!!,
                cartitem.message(),
                getString(R.string.close_up)
            )
        }
    }

    private fun setFullData(response: ArrayList<CartItemsResponse.Data.CartItem>) {
        cartitems_recycler.visibility = View.VISIBLE
        //response = closets
        cartitems_recycler.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        val adapter = CartAdapter(context!!, response)
        cartitems_recycler.adapter = adapter
    }

    override fun error(error: String?) {
        pd.dismiss()
        utility!!.relative_snackbar(
            parent_cart!!,
            error,
            getString(R.string.close_up)
        )
    }
}