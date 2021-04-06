package com.casebeaumonde.fragments.shop

import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.casebeaumonde.Controller.Controller
import com.casebeaumonde.R
import com.casebeaumonde.activities.ShopItems.ShopItemsActivity
import com.casebeaumonde.constants.BaseFrag
import com.casebeaumonde.constants.Constants
import com.casebeaumonde.fragments.shop.IF.GetShopID_IF
import com.casebeaumonde.fragments.shop.adapter.ShopAdapter
import com.casebeaumonde.fragments.shop.response.ShopResponse
import com.casebeaumonde.utilities.Utility
import kotlinx.android.synthetic.main.fragment_shop.*
import retrofit2.Response

class Shop : BaseFrag(),Controller.ShopAPI,GetShopID_IF {

    private lateinit var utility: Utility
    private lateinit var pd: ProgressDialog
    private lateinit var controller: Controller
    public lateinit var shop_recycler :RecyclerView
    lateinit var shop_images :ArrayList<String>
    lateinit var shops : ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_shop, container, false)

        getshopidIf = this
        findIds(view)
        return view;
    }

    private fun findIds(view: View) {
        shop_recycler = view.findViewById(R.id.shop_recycler)

        controller = Controller()
        controller.Controller(this)

        utility = Utility()
        pd = ProgressDialog(context)
        pd!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        pd!!.window!!.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        pd!!.isIndeterminate = true
        pd!!.setCancelable(false)

        if (utility.isConnectingToInternet(context)) {
            pd.show()
            pd.setContentView(R.layout.loading)
            controller.Shop(
                "Bearer " + getStringVal(Constants.TOKEN)
            )
        } else {
            utility!!.relative_snackbar(
                parent_shop!!,
                getString(R.string.nointernet),
                getString(R.string.close_up)
            )
        }
    }

    override fun onShopSuccess(success: Response<ShopResponse>) {
        pd.dismiss()
        if (success.isSuccessful)
        {

            shop_recycler.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            val adapter = ShopAdapter (
                context!!,success
            )
            shop_recycler.adapter = adapter
            adapter.notifyDataSetChanged()
        } else {

        }
    }

    override fun error(error: String) {
        pd.dismiss()
        utility!!.relative_snackbar(
            parent_shop!!,
            error,
            getString(R.string.close_up)
        )
    }

    companion object{
        var getshopidIf : GetShopID_IF? =null
    }

    override fun getID(id: String?) {
        startActivity(Intent(context,ShopItemsActivity::class.java).putExtra("shopID",id.toString()))

    }
}