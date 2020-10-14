package com.casebeaumonde.fragments.pricing

import android.app.ProgressDialog
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
import com.casebeaumonde.constants.BaseFrag
import com.casebeaumonde.constants.Constants
import com.casebeaumonde.fragments.pricing.adapter.BussinessPricingAdapter
import com.casebeaumonde.fragments.pricing.adapter.CustomerPricingAdapter
import com.casebeaumonde.fragments.pricing.response.PricingResponse
import com.casebeaumonde.utilities.Utility
import kotlinx.android.synthetic.main.fragment_live_events.*
import kotlinx.android.synthetic.main.fragment_pricing.*
import retrofit2.Response

class Pricing : BaseFrag(), Controller.PricingAPI {

    private lateinit var utility: Utility
    private lateinit var pd: ProgressDialog
    private lateinit var controller: Controller
    private lateinit var priciing_recyclerview: RecyclerView
    private lateinit var customerPricing: ArrayList<PricingResponse.Data.CustomerPlan>
    private lateinit var bussinessPricing: ArrayList<PricingResponse.Data.BusinessPlan>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View
        view = inflater.inflate(R.layout.fragment_pricing, container, false)
        findIDs(view)
        controller = Controller()
        controller.Controller(this)
        if (utility.isConnectingToInternet(context)) {
            pd.show()
            pd.setContentView(R.layout.loading)
            controller.Pricing("Bearer " + getStringVal(Constants.TOKEN))
        } else {
            utility!!.relative_snackbar(
                parent_liveevents!!,
                getString(R.string.nointernet),
                getString(R.string.close_up)
            )
        }


        return view
    }

    private fun findIDs(view: View?) {
        priciing_recyclerview = view?.findViewById(R.id.priciing_recyclerview)!!
        utility = Utility()
        pd = ProgressDialog(context)
        pd!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        pd!!.window!!.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        pd!!.isIndeterminate = true
        pd!!.setCancelable(false)
    }

    override fun onPricingSuccess(pricing: Response<PricingResponse>) {
        pd.dismiss()
        if (getStringVal(Constants.BUSSINESSSUBSSCRIPTION).equals("1")) {
            bussinessPricing =
                pricing.body()?.data?.businessPlans as ArrayList<PricingResponse.Data.BusinessPlan>
            priciing_recyclerview.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            val adapter = BussinessPricingAdapter(
                context!!, bussinessPricing
            )
            priciing_recyclerview.adapter = adapter
        }

        if (getStringVal(Constants.CUSTOMERSUBSCRIPTION).equals("1")) {
            customerPricing =
                pricing.body()?.data?.customerPlans as ArrayList<PricingResponse.Data.CustomerPlan>
            priciing_recyclerview.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            val adapter = CustomerPricingAdapter(
                context!!, customerPricing
            )
            priciing_recyclerview.adapter = adapter
        }

    }

    override fun error(error: String?) {
        pd.dismiss()
        utility!!.relative_snackbar(
            parent_pricing!!,
            error,
            getString(R.string.close_up)
        )
    }
}