package com.casebeaumonde.fragments.cart

import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.casebeaumonde.Controller.Controller
import com.casebeaumonde.R
import com.casebeaumonde.activities.ShopItems.response.AddtoCartResponse
import com.casebeaumonde.constants.BaseFrag
import com.casebeaumonde.constants.Constants
import com.casebeaumonde.fragments.cart.IF.AddtoCartIF
import com.casebeaumonde.fragments.cart.IF.RemoveCartItemIF
import com.casebeaumonde.fragments.cart.adapter.CartAdapter
import com.casebeaumonde.fragments.cart.reponse.GetCartItemsResponse
import com.casebeaumonde.fragments.cart.reponse.RemoveItemCartResponse
import com.casebeaumonde.utilities.Utility
import kotlinx.android.synthetic.main.fragment_cart.*
import retrofit2.Response

class Cart : BaseFrag(),
//    Controller.CartItemAPI,
    Controller.GetCartItemsAPI,
    RemoveCartItemIF,
    Controller.RemoveItemCartAPI,
    AddtoCartIF, Controller.AddtoCartAPI {

    private lateinit var cartitems_recycler: RecyclerView
    private lateinit var utility: Utility
    private lateinit var pd: ProgressDialog
    private lateinit var checkout_bt: Button
    private lateinit var controller: Controller
    private lateinit var response: ArrayList<GetCartItemsResponse.CartItems>
    private var quantity1: String? = ""

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
        controller.Controller(this, this, this)
        findIds(view)

        if (utility.isConnectingToInternet(context)) {
            pd.show()
            pd.setContentView(R.layout.loading)
            controller.GetCartItems("Bearer " + getStringVal(Constants.TOKEN))
        } else {
            utility!!.relative_snackbar(
                parent_cart!!,
                getString(R.string.nointernet),
                getString(R.string.close_up)
            )
        }

        lisenters()

        return view
    }

    private fun lisenters() {
        checkout_bt.setOnClickListener {
            startActivity(Intent(context, PersonalInfoScreen::class.java))
        }
    }

    private fun findIds(view: View) {

        removeCartItemIF = this
        addtoCartIF = this
        utility = Utility()
        pd = ProgressDialog(context)
        pd!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        pd!!.window!!.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        pd!!.isIndeterminate = true
        pd!!.setCancelable(false)
        cartitems_recycler = view.findViewById(R.id.cartitems_recycler)
        checkout_bt = view.findViewById(R.id.checkout_bt)
    }

//    override fun onCartItemSuccess(cartitem: Response<CartItemsResponse>) {
//
//    }

    companion object {
        var removeCartItemIF: RemoveCartItemIF? = null
        var addtoCartIF: AddtoCartIF? = null
    }

    private fun setFullData(response: ArrayList<GetCartItemsResponse.CartItems>) {
        cartitems_recycler.visibility = View.VISIBLE
        //response = closets
        cartitems_recycler.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        val adapter = CartAdapter(context!!, response)
        cartitems_recycler.adapter = adapter
    }

    override fun onGetCartItemsSuccess(success: Response<GetCartItemsResponse>) {
        pd.dismiss()

        if (success.isSuccessful) {
//            response =
//                success.body()?.cartItems
//            response.addAll(success.body()?.)
            setFullData(response)
        } else {
            utility!!.relative_snackbar(
                parent_cart!!,
                success.message(),
                getString(R.string.close_up)
            )
        }
    }


    override fun error(error: String?) {
        pd.dismiss()
        utility!!.relative_snackbar(
            parent_cart!!,
            error,
            getString(R.string.close_up)
        )
    }

    override fun getID(pos: String?, id: String?) {
        if (utility.isConnectingToInternet(context)) {
            controller.RemoveCartItem("Bearer " + getStringVal(Constants.TOKEN), id.toString())
            pd.show()
            pd.setContentView(R.layout.loading)
        } else {
            utility!!.relative_snackbar(
                parent_cart!!,
                getString(R.string.nointernet),
                getString(R.string.close_up)
            )
        }


    }

    override fun onRemoveCartSuccess(success: Response<RemoveItemCartResponse>) {

        if (success.isSuccessful) {
            if (success.body()?.code.equals("200")) {
                controller.CartItems("Bearer " + getStringVal(Constants.TOKEN))
                utility!!.relative_snackbar(
                    parent_cart!!,
                    success.body()?.message,
                    getString(R.string.close_up)
                )
            } else {
                pd.dismiss()
                utility!!.relative_snackbar(
                    parent_cart!!,
                    success.body()?.message,
                    getString(R.string.close_up)
                )
            }
        } else {
            pd.dismiss()
            utility!!.relative_snackbar(
                parent_cart!!,
                success.message(),
                getString(R.string.close_up)
            )
        }


    }

    override fun getCartQuantity(quantity: String?, id: String?) {
        quantity1 = quantity
        if (utility.isConnectingToInternet(context)) {
            pd.show()
            pd.setContentView(R.layout.loading)
            controller.AddtoCart("Bearer " + getStringVal(Constants.TOKEN), id, quantity!!)
        } else {
            pd.dismiss()
            utility!!.relative_snackbar(
                parent_cart!!,
                getString(R.string.nointernet),
                getString(R.string.close_up)
            )
        }
    }

    override fun onAddtoCartSuccess(success: Response<AddtoCartResponse>) {

        if (success.isSuccessful) {
            if (utility.isConnectingToInternet(context)) {
                pd.show()
                pd.setContentView(R.layout.loading)
                controller.CartItems("Bearer " + getStringVal(Constants.TOKEN))
                utility!!.relative_snackbar(
                    parent_cart!!,
                   success.body()?.message,
                    getString(R.string.close_up)
                )
            } else {
                utility!!.relative_snackbar(
                    parent_cart!!,
                    getString(R.string.nointernet),
                    getString(R.string.close_up)
                )
            }
        } else {
            pd.dismiss()
            utility!!.relative_snackbar(
                parent_cart!!,
                success.body()?.message,
                getString(R.string.close_up)
            )
        }
    }
}