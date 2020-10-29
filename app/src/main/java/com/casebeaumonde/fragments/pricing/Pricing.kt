package com.casebeaumonde.fragments.pricing

import android.app.Dialog
import android.app.ProgressDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import android.widget.Button
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.casebeaumonde.Controller.Controller
import com.casebeaumonde.R
import com.casebeaumonde.constants.BaseFrag
import com.casebeaumonde.constants.Constants
import com.casebeaumonde.fragments.pricing.IF.GetPriceID_IF
import com.casebeaumonde.fragments.pricing.adapter.BussinessPricingAdapter
import com.casebeaumonde.fragments.pricing.adapter.CustomerPricingAdapter
import com.casebeaumonde.fragments.pricing.response.ChangePlanResponse
import com.casebeaumonde.fragments.pricing.response.PricingResponse
import com.casebeaumonde.fragments.profile.Profile
import com.casebeaumonde.utilities.Utility
import kotlinx.android.synthetic.main.fragment_live_events.*
import kotlinx.android.synthetic.main.fragment_pricing.*
import org.jetbrains.annotations.NotNull
import org.jetbrains.annotations.Nullable
import retrofit2.Response

class Pricing : BaseFrag(), Controller.PricingAPI ,GetPriceID_IF,Controller.ChangePlanAPI{

    private lateinit var utility: Utility
    private lateinit var pd: ProgressDialog
    private lateinit var controller: Controller
    private lateinit var priciing_recyclerview: RecyclerView
    private lateinit var customerPricing: ArrayList<PricingResponse.Data.CustomerPlan>
    private lateinit var bussinessPricing: ArrayList<PricingResponse.Data.BusinessPlan>
    private lateinit var changePlanDialog: Dialog
    var from : String? = ""
    var planname : String? =""
    lateinit var manager: FragmentManager
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
        manager = fragmentManager!!
        getpriceidIf = this
        controller = Controller()
        from = arguments?.getString(Constants.FROM).toString()
        planname = arguments?.getString(Constants.PLANNAME).toString()

        controller.Controller(this,this)
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

    companion object {
        var getpriceidIf : GetPriceID_IF ? =null
    }

    override fun onPricingSuccess(pricing: Response<PricingResponse>) {
        pd.dismiss()
        if (getStringVal(Constants.USER_ROLE).equals("1")) {

        }

        if (getStringVal(Constants.USER_ROLE).equals("customer")) {
            customerPricing =
                pricing.body()?.data?.customerPlans as ArrayList<PricingResponse.Data.CustomerPlan>

            priciing_recyclerview.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            val adapter = CustomerPricingAdapter(
                context!!, customerPricing, from.toString(), planname.toString()
            )
            priciing_recyclerview.adapter = adapter
            adapter.notifyDataSetChanged()

        } else {
            bussinessPricing =
                pricing.body()?.data?.businessPlans as ArrayList<PricingResponse.Data.BusinessPlan>
            priciing_recyclerview.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            val adapter = BussinessPricingAdapter(
                context!!, bussinessPricing
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

    override fun getID(
        id: String?,
        s: String
    ) {
        changePlanDialog = Dialog(context!!)
        changePlanDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        changePlanDialog.setCancelable(false)
        changePlanDialog.setContentView(R.layout.changeplandialog)
        val window = changePlanDialog.window
        window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )

        var cancelbt : Button
        var changebt : Button
        cancelbt =  changePlanDialog.findViewById(R.id.cancelbt)
        changebt = changePlanDialog.findViewById(R.id.changebt)

        cancelbt.setOnClickListener {
            changePlanDialog.dismiss()
        }

        changebt.setOnClickListener {
            if (utility.isConnectingToInternet(context)) {
                pd.show()
                pd.setContentView(R.layout.loading)
                controller.ChangePlan("Bearer "+getStringVal(Constants.TOKEN),id,s,"monthly")
            } else {
                utility!!.relative_snackbar(
                    parent_liveevents!!,
                    getString(R.string.nointernet),
                    getString(R.string.close_up)
                )
            }

        }

        changePlanDialog.show()
    }

    override fun onChangePlanSuccess(changePlan: Response<ChangePlanResponse>) {
        pd.dismiss()
        if (changePlan.isSuccessful)
        {
            changePlanDialog.dismiss()
            val transaction = manager.beginTransaction()
            transaction.replace(R.id.nav_host_fragment, Profile())
            transaction.commit()
            utility!!.relative_snackbar(
                parent_pricing!!,
                changePlan.body()?.data?.message,
                getString(R.string.close_up)
            )
        }else {
            utility!!.relative_snackbar(
                parent_pricing!!,
                changePlan.message(),
                getString(R.string.close_up)
            )
        }
    }
}