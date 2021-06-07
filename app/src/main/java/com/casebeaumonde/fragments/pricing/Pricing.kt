package com.casebeaumonde.fragments.pricing

import android.app.Dialog
import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.*
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.casebeaumonde.Controller.Controller
import com.casebeaumonde.R
import com.casebeaumonde.activities.paymentScreen.CardDetailScreen
import com.casebeaumonde.constants.BaseFrag
import com.casebeaumonde.constants.Constants
import com.casebeaumonde.fragments.pricing.IF.GetPriceID_IF
import com.casebeaumonde.fragments.pricing.adapter.BussinessPlanFeaturesAdapter
import com.casebeaumonde.fragments.pricing.adapter.CustomerPlanFeaturesAdapter
import com.casebeaumonde.fragments.pricing.response.ChangePlanResponse
import com.casebeaumonde.fragments.pricing.response.PricingResponse
import com.casebeaumonde.fragments.profile.profileResponse.UserProfileResponse
import com.casebeaumonde.utilities.Utility
import kotlinx.android.synthetic.main.fragment_pricing.*
import retrofit2.Response

class Pricing : BaseFrag(), Controller.PricingAPI, GetPriceID_IF, Controller.ChangePlanAPI,
    Controller.UserProfileAPI {

    private lateinit var utility: Utility
    private lateinit var pd: ProgressDialog
    private lateinit var controller: Controller
    private lateinit var priciing_recyclerview: RecyclerView
    private lateinit var customerPricing: ArrayList<PricingResponse.Data.CustomerPlan>
    private lateinit var bussinessPricing: ArrayList<PricingResponse.Data.BusinessPlan>
    private lateinit var customerFeatures: ArrayList<PricingResponse.Data.CustomerPlan>
    private lateinit var changePlanDialog: Dialog
    private lateinit var plansname: Spinner
    private lateinit var plansnames: ArrayList<String>
    private lateinit var pricefeature_recyler: RecyclerView
    private lateinit var level_tv: TextView
    private lateinit var switchplan: Switch
    private lateinit var freepaid_tv: TextView
    private var pos: Int = 0
    private var planType: String = "Monthly"
    private lateinit var selectthisplan: Button
    private lateinit var startone: ImageView
    private lateinit var starttwo: ImageView
    private lateinit var startthree: ImageView
    private lateinit var startfour: ImageView

    var from: String? = ""
    var planname: String? = ""
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
        view = inflater.inflate(R.layout.price_layout, container, false)
        findIDs(view)
        manager = fragmentManager!!
        getpriceidIf = this
        controller = Controller()

        from = arguments?.getString(Constants.FROM).toString()
        // planname = arguments?.getString(Constants.PLANNAME).toString()

        controller.Controller(this, this, this)

        listeners()



        return view
    }

    private fun listeners() {
        if (getStringVal(Constants.USER_ROLE).equals("customer")) {
            switchplan.setOnCheckedChangeListener { buttonView, isChecked ->
                if (isChecked) {
                    freepaid_tv.text = "$" + customerPricing.get(pos).yearlyPrice.toString()
                    planType = "Yearly"
                } else {
                    freepaid_tv.text = "$" + customerPricing.get(pos).monthlyPrice.toString()
                    planType = "Monthly"
                }
            }
        } else {
            switchplan.setOnCheckedChangeListener { buttonView, isChecked ->
                if (isChecked) {
                    freepaid_tv.text = "$" + bussinessPricing.get(pos).yearlyPrice.toString()
                    planType = "Yearly"
                } else {
                    freepaid_tv.text = "$" + bussinessPricing.get(pos).monthlyPrice.toString()
                    planType = "Monthly"
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()

        if (utility.isConnectingToInternet(context)) {
            pd.show()
            pd.setContentView(R.layout.loading)
            controller.setUserProfileAPI(
                "Bearer " + getStringVal(Constants.TOKEN),
                getStringVal(Constants.USERID)
            )
        } else {
            utility!!.relative_snackbar(
                parent_pricing11!!,
                getString(R.string.nointernet),
                getString(R.string.close_up)
            )
        }
    }

    private fun findIDs(view: View?) {
        plansname = view?.findViewById(R.id.plansname)!!
        pricefeature_recyler = view?.findViewById(R.id.pricefeature_recyler)
        level_tv = view?.findViewById(R.id.level_tv)
        switchplan = view?.findViewById(R.id.switchplan)
        freepaid_tv = view?.findViewById(R.id.freepaid_tv)
        selectthisplan = view?.findViewById(R.id.selectthisplan)
        startone = view?.findViewById(R.id.startone)
        starttwo = view?.findViewById(R.id.starttwo)
        startthree = view?.findViewById(R.id.startthree)
        startfour = view?.findViewById(R.id.startfour)
        // priciing_recyclerview = view?.findViewById(R.id.priciing_recyclerview)!!
        utility = Utility()
        pd = ProgressDialog(context)
        pd!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        pd!!.window!!.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        pd!!.isIndeterminate = true
        pd!!.setCancelable(false)
    }

    companion object {
        var getpriceidIf: GetPriceID_IF? = null
    }

    override fun onPricingSuccess(pricing: Response<PricingResponse>) {
        pd.dismiss()
        if (pricing.isSuccessful) {
            if (getStringVal(Constants.USER_ROLE).equals("1")) {

            }

            if (getStringVal(Constants.USER_ROLE).equals("customer")) {
                customerPricing =
                    pricing.body()
                        ?.getData()?.customerPlans as ArrayList<PricingResponse.Data.CustomerPlan>

                plansnames = ArrayList()
                for (i in customerPricing.indices) {
                    plansnames.add(customerPricing.get(i).name.toString())
                }


                val adapter = context?.let {
                    ArrayAdapter(
                        it,
                        android.R.layout.simple_spinner_dropdown_item, plansnames
                    )
                }


                plansname.adapter = adapter
                plansname.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        parent: AdapterView<*>?,
                        view: View?,
                        position: Int,
                        id: Long
                    ) {
                        pos = position
                        if (position == 1) {
                            starttwo.visibility = View.VISIBLE
                            starttwo.visibility = View.VISIBLE
                            startthree.visibility = View.GONE
                            startfour.visibility = View.GONE
                        } else if (position == 2) {
                            starttwo.visibility = View.VISIBLE
                            startthree.visibility = View.VISIBLE
                            startfour.visibility = View.GONE
                        } else if (position == 3) {
                            starttwo.visibility = View.VISIBLE
                            startthree.visibility = View.VISIBLE
                            startfour.visibility = View.VISIBLE
                        } else if (position == 0) {
                            starttwo.visibility = View.VISIBLE
                            starttwo.visibility = View.GONE
                            startthree.visibility = View.GONE
                            startfour.visibility = View.GONE
                        }
                        pricefeature_recyler.layoutManager =
                            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                        val adapter1 = CustomerPlanFeaturesAdapter(
                            context!!, pricing.body()?.getData()?.customerPlans, from.toString(),
                            getStringVal(Constants.TOKEN)!!, pos
                        )
                        pricefeature_recyler.adapter = adapter1
                        adapter1.notifyDataSetChanged()


                        level_tv.text = pricing.body()?.getData()?.customerPlans?.get(pos)?.name
                        if (planType.equals("Monthly")) {
                            freepaid_tv.text =
                                "$" + customerPricing.get(pos).monthlyPrice.toString()
                        } else {
                            freepaid_tv.text = "$" + customerPricing.get(pos).yearlyPrice.toString()
                        }





                        if (from.equals("changeplan")) {
                            if ((pricing.body()
                                    ?.getData()?.customerPlans as ArrayList<PricingResponse.Data.CustomerPlan>).get(
                                    pos
                                ).name.equals(planname)
                            ) {

                                selectthisplan.setText("SUBSCRIBED")
//            val param = holder.itemView.getLayoutParams()
//            param.height = 0;
//            param.width = 0;
//            holder.itemView.setLayoutParams(param);

                            } else {
                                selectthisplan.setText("CHANGE PLAN")
                            }


                        } else {
                            if ((pricing.body()
                                    ?.getData()?.customerPlans as ArrayList<PricingResponse.Data.CustomerPlan>).get(
                                    pos
                                ).name.equals(planname)
                            ) {

                                selectthisplan.setText("SUBSCRIBED")
//            val param = holder.itemView.getLayoutParams()
//            param.height = 0;
//            param.width = 0;
//            holder.itemView.setLayoutParams(param);

                            } else {

                                //ToDo: Change THis plan from suscribed
                             //   selectthisplan.setText("CHANGE THIS PLAN")
                                selectthisplan.setText("Select This Plan")
                            }
                        }

                        if (selectthisplan.text.equals("SUBSCRIBED")) {
                            selectthisplan.isEnabled = false

                        } else {
                            selectthisplan.isEnabled = true
                        }
                    }

                    override fun onNothingSelected(parent: AdapterView<*>?) {

                    }

                }

                selectthisplan.setOnClickListener {
                    if (selectthisplan.text.equals("CHANGE PLAN")) {


                        changePlanDialog = Dialog(context!!)
                        changePlanDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
                        changePlanDialog.setCancelable(false)
                        changePlanDialog.setContentView(R.layout.changeplandialog)
                        val window = changePlanDialog.window
                        window?.setLayout(
                            WindowManager.LayoutParams.MATCH_PARENT,
                            WindowManager.LayoutParams.WRAP_CONTENT
                        )

                        var cancelbt: Button
                        var changebt: Button
                        cancelbt = changePlanDialog.findViewById(R.id.cancelbt)
                        changebt = changePlanDialog.findViewById(R.id.changebt)

                        cancelbt.setOnClickListener {
                            changePlanDialog.dismiss()
                        }

                        changebt.setOnClickListener {
                            if (utility.isConnectingToInternet(context)) {
                                pd.show()
                                pd.setContentView(R.layout.loading)
                                controller.ChangePlan(
                                    "Bearer " + getStringVal(Constants.TOKEN),
                                    pricing.body()
                                        ?.getData()?.customerPlans?.get(pos)?.id.toString(),
                                    "customer",
                                    "monthly"
                                )
                            } else {
                                utility!!.relative_snackbar(
                                    parent_pricing11!!,
                                    getString(R.string.nointernet),
                                    getString(R.string.close_up)
                                )
                            }
                        }

                        changePlanDialog.show()
                    } else if (selectthisplan.text.equals("SUBSCRIBED")) {
                        selectthisplan.isEnabled = false
                    } else {
                        startActivity(

                            Intent(context, CardDetailScreen::class.java).putExtra(
                                "planname", pricing.body()?.getData()?.customerPlans?.get(pos)?.name
                            ).putExtra(
                                "planprice",
                                pricing.body()
                                    ?.getData()?.customerPlans?.get(pos)?.monthlyPrice.toString()
                            )
                                .putExtra(
                                    "planID",
                                    pricing.body()
                                        ?.getData()?.customerPlans?.get(pos)?.id.toString()
                                )
                                .putExtra("plantype", "customer")
                        )
                        //Toast.makeText(context,""+pricing.body()?.getData()?.customerPlans?.get(pos)?.id.toString(),Toast.LENGTH_SHORT).show()
                    }
                }


            } else {
                bussinessPricing =
                    pricing.body()
                        ?.getData()?.businessPlans as ArrayList<PricingResponse.Data.BusinessPlan>


                plansnames = ArrayList()
                for (i in bussinessPricing.indices) {
                    plansnames.add(bussinessPricing.get(i).name.toString())
                }

                val adapter = context?.let {
                    ArrayAdapter(
                        it,
                        android.R.layout.simple_spinner_dropdown_item, plansnames
                    )
                }

                plansname.adapter = adapter
                plansname.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        parent: AdapterView<*>?,
                        view: View?,
                        position: Int,
                        id: Long
                    ) {
                        pos = position
                        if (position == 1) {
                            starttwo.visibility = View.VISIBLE
                            starttwo.visibility = View.VISIBLE
                            startthree.visibility = View.GONE
                            startfour.visibility = View.GONE
                        } else if (position == 2) {
                            starttwo.visibility = View.VISIBLE
                            startthree.visibility = View.VISIBLE
                            startfour.visibility = View.GONE
                        } else if (position == 3) {
                            starttwo.visibility = View.VISIBLE
                            startthree.visibility = View.VISIBLE
                            startfour.visibility = View.VISIBLE
                        } else if (position == 0) {
                            starttwo.visibility = View.VISIBLE
                            starttwo.visibility = View.GONE
                            startthree.visibility = View.GONE
                            startfour.visibility = View.GONE
                        }

                        pricefeature_recyler.layoutManager =
                            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                        val adapter1 = BussinessPlanFeaturesAdapter(
                            context!!, pricing.body()?.getData()?.businessPlans, from.toString(),
                            getStringVal(Constants.TOKEN)!!, pos
                        )
                        pricefeature_recyler.adapter = adapter1
                        adapter1.notifyDataSetChanged()


                        level_tv.text = pricing.body()?.getData()?.businessPlans?.get(pos)?.name
                        if (planType.equals("Monthly")) {
                            freepaid_tv.text =
                                "$" + bussinessPricing.get(pos).monthlyPrice.toString()
                        } else {
                            freepaid_tv.text =
                                "$" + bussinessPricing.get(pos).yearlyPrice.toString()
                        }


                        if (from.equals("changeplan")) {
                            if ((pricing.body()
                                    ?.getData()?.businessPlans as ArrayList<PricingResponse.Data.BusinessPlan>).get(
                                    pos
                                ).name.equals(planname)
                            ) {

                            selectthisplan.setText("SUBSCRIBED")
//            val param = holder.itemView.getLayoutParams()
//            param.height = 0;
//            param.width = 0;
//            holder.itemView.setLayoutParams(param);

                            } else {
                                selectthisplan.setText("CHANGE PLAN")
                            }


                        } else {
                            if ((pricing.body()
                                    ?.getData()?.businessPlans as ArrayList<PricingResponse.Data.BusinessPlan>).get(
                                    pos
                                ).name.equals(planname)
                            ) {

                                selectthisplan.setText("SUBSCRIBED")
//            val param = holder.itemView.getLayoutParams()
//            param.height = 0;
//            param.width = 0;
//            holder.itemView.setLayoutParams(param);

                            } else {

                                //ToDo: Change THis plan from suscribed
                                //selectthisplan.setText("CHANGE THIS PLAN")
                                selectthisplan.setText("Select This Plan")
                            }
                        }

                        if (selectthisplan.text.equals("SUBSCRIBED")) {
                            selectthisplan.isEnabled = false

                        } else {
                            selectthisplan.isEnabled = true
                        }
                    }

                    override fun onNothingSelected(parent: AdapterView<*>?) {

                    }

                }

                selectthisplan.setOnClickListener {
                    if (selectthisplan.text.equals("CHANGE PLAN")) {


                        changePlanDialog = Dialog(context!!)
                        changePlanDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
                        changePlanDialog.setCancelable(false)
                        changePlanDialog.setContentView(R.layout.changeplandialog)
                        val window = changePlanDialog.window
                        window?.setLayout(
                            WindowManager.LayoutParams.MATCH_PARENT,
                            WindowManager.LayoutParams.WRAP_CONTENT
                        )

                        var cancelbt: Button
                        var changebt: Button
                        cancelbt = changePlanDialog.findViewById(R.id.cancelbt)
                        changebt = changePlanDialog.findViewById(R.id.changebt)

                        cancelbt.setOnClickListener {
                            changePlanDialog.dismiss()
                        }

                        changebt.setOnClickListener {
                            if (utility.isConnectingToInternet(context)) {
                                pd.show()
                                pd.setContentView(R.layout.loading)
                                if (getStringVal(Constants.USER_ROLE).equals("customer"))
                                {
                                    controller.ChangePlan(
                                        "Bearer " + getStringVal(Constants.TOKEN),
                                        pricing.body()
                                            ?.getData()?.customerPlans?.get(pos)?.id.toString(),
                                        getStringVal(Constants.USER_ROLE),
                                        "monthly"
                                    )
                                }else {
                                    controller.ChangePlan(
                                        "Bearer " + getStringVal(Constants.TOKEN),
                                        pricing.body()
                                            ?.getData()?.businessPlans?.get(pos)?.id.toString(),
                                        getStringVal(Constants.USER_ROLE),
                                        "monthly"
                                    )
                                }

                            } else {
                                utility!!.relative_snackbar(
                                    parent_pricing11!!,
                                    getString(R.string.nointernet),
                                    getString(R.string.close_up)
                                )
                            }
                        }

                        changePlanDialog.show()
                    } else if (selectthisplan.text.equals("SUBSCRIBED")) {
                        selectthisplan.isEnabled = false
                    } else {
                        startActivity(

                            Intent(context, CardDetailScreen::class.java).putExtra(
                                "planname", pricing.body()?.getData()?.businessPlans?.get(pos)?.name
                            ).putExtra(
                                "planprice",
                                pricing.body()
                                    ?.getData()?.businessPlans?.get(pos)?.monthlyPrice.toString()
                            )
                                .putExtra(
                                    "planID",
                                    pricing.body()
                                        ?.getData()?.businessPlans?.get(pos)?.id.toString()
                                )
                                .putExtra("plantype", "customer")
                        )
                        //Toast.makeText(context,""+pricing.body()?.getData()?.customerPlans?.get(pos)?.id.toString(),Toast.LENGTH_SHORT).show()
                    }
                }
            }
        } else {
            utility!!.relative_snackbar(
                parent_pricing11!!,
                pricing.message(),
                getString(R.string.close_up)
            )
        }

    }

    override fun onPrfileSucess(userProfileResponse: Response<UserProfileResponse>) {
        pd.dismiss()
        if (userProfileResponse.isSuccessful) {
            Log.d("userprofilerespose", "" + userProfileResponse.body()?.getData())
            planname =
                userProfileResponse.body()
                    ?.getData()?.user?.customerSubscription?.plan?.name.toString()
            controller.Pricing("Bearer " + getStringVal(Constants.TOKEN))
        } else {
            utility!!.relative_snackbar(
                parent_pricing11!!,
                userProfileResponse.message(),
                getString(R.string.close_up)
            )
        }
    }

    override fun error(error: String?) {
        pd.dismiss()
        utility!!.relative_snackbar(
            parent_pricing11!!,
            error,
            getString(R.string.close_up)
        )
    }

    override fun getID(
        id: String?,
        s: String
    ) {

    }

    override fun onChangePlanSuccess(changePlan: Response<ChangePlanResponse>) {
        pd.dismiss()
        if (changePlan.isSuccessful) {
            changePlanDialog.dismiss()

            if (utility.isConnectingToInternet(context)) {
                pd.show()
                pd.setContentView(R.layout.loading)
                controller.setUserProfileAPI(
                    "Bearer " + getStringVal(Constants.TOKEN),
                    getStringVal(Constants.USERID)
                )
            } else {
                utility!!.relative_snackbar(
                    parent_pricing11!!,
                    getString(R.string.nointernet),
                    getString(R.string.close_up)
                )
            }
            utility!!.relative_snackbar(
                parent_pricing11!!,
                changePlan.body()?.getData()?.message,
                getString(R.string.close_up)
            )
        } else {
            utility!!.relative_snackbar(
                parent_pricing11!!,
                changePlan.message(),
                getString(R.string.close_up)
            )
        }
    }
}