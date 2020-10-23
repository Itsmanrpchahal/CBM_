package com.casebeaumonde.activities.myContracts.tabs.Contract

import android.app.Dialog
import android.app.ProgressDialog
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
import com.casebeaumonde.activities.myContracts.tabs.Contract.adapter.ContractCustomerAdapter
import com.casebeaumonde.activities.myContracts.tabs.Contract.response.ContractListResponse
import com.casebeaumonde.constants.BaseFrag
import com.casebeaumonde.constants.Constants
import com.casebeaumonde.utilities.Utility
import kotlinx.android.synthetic.main.fragment_my_contracts.*
import kotlinx.android.synthetic.main.fragment_offers.*
import retrofit2.Response

class MyContractsFrag : BaseFrag(),Controller.ContractListAPI {

    private lateinit var utility: Utility
    private lateinit var pd: ProgressDialog
    private lateinit var controller: Controller
    private lateinit var contract_recieved: Button
    private lateinit var contract_sent: Button
    private lateinit var contracts_as_customer_recycler: RecyclerView
    private lateinit var contracts_as_contractor_recycler: RecyclerView
    private lateinit var contract: ArrayList<ContractListResponse.Data.User.ContractsAsContractor>
    private lateinit var customer: ArrayList<ContractListResponse.Data.User.ContractsAsCustomer>
    lateinit var type: String
    private lateinit var contractDialog: Dialog

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view : View
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_my_contracts, container, false)
        controller = Controller()
        controller.Controller(this)
        findIds(view)
        lisenters()
        if (utility.isConnectingToInternet(context)) {
            pd.show()
            pd.setContentView(R.layout.loading)
            controller.ContractList(
                "Bearer " + getStringVal(Constants.TOKEN)
            )

        } else {
            utility.relative_snackbar(
                parent_contract!!,
                "No Internet Connectivity",
                getString(R.string.close_up)
            )
        }
        return view
    }

    private fun findIds(view: View?) {
        contract_recieved = view!!.findViewById(R.id.contract_recieved)
        contract_sent = view.findViewById(R.id.contract_sent)
        contracts_as_customer_recycler = view.findViewById(R.id.contracts_as_customer_recycler)
        contracts_as_contractor_recycler = view.findViewById(R.id.contracts_as_contractor_recycler)
        utility = Utility()
        pd = ProgressDialog(context)
        pd!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        pd!!.window!!.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        pd!!.isIndeterminate = true
        pd!!.setCancelable(false)
    }

    private fun lisenters() {
        contract_recieved.setOnClickListener {
            contract_recieved.setBackgroundColor(Color.WHITE)
            contract_recieved.setTextColor(Color.BLACK)
            contract_sent.setBackgroundColor(Color.BLACK)
            contract_sent.setTextColor(Color.WHITE)
            type = "sent"
            contracts_as_customer_recycler.visibility = View.GONE
            contracts_as_contractor_recycler.visibility = View.VISIBLE
            //setFullData(sendOffer, type)
        }

        contract_sent.setOnClickListener {
            contract_recieved.setBackgroundColor(Color.BLACK)
            contract_recieved.setTextColor(Color.WHITE)
            contract_sent.setBackgroundColor(Color.WHITE)
            contract_sent.setTextColor(Color.BLACK)
            type = "recieve"
            contracts_as_customer_recycler.visibility = View.VISIBLE
            contracts_as_contractor_recycler.visibility = View.GONE
            //setRecieveData(recieveOffer, type)

        }
    }

    override fun onContractListSuccess(contractlist: Response<ContractListResponse>) {
        pd.dismiss()
        customer = ArrayList()
        customer =
            contractlist.body()?.data?.user?.contractsAsCustomer as ArrayList<ContractListResponse.Data.User.ContractsAsCustomer>

        if (contractlist.body()?.data?.user?.role.equals("customer")) {
            type = "customer"
            //worksentinvitations_recycler.visibility = View.VISIBLE
            setFullData(customer, "customer")
            contracts_as_customer_recycler.visibility = View.VISIBLE

        } else {
            contract_recieved.visibility = View.VISIBLE
            contract_sent.visibility = View.VISIBLE
            contract_recieved.setBackgroundColor(Color.WHITE)
            contract_recieved.setTextColor(Color.BLACK)
            type = "recieve"
            //setRecieveData(recieveOffer, type)

        }

        utility.relative_snackbar(
            parent_contract!!,
            ""+contractlist.body()?.code,
            getString(R.string.close_up)
        )
    }

    private fun setFullData(contractlist: ArrayList<ContractListResponse.Data.User.ContractsAsCustomer>, type: String) {
        contracts_as_customer_recycler.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        val adapter =
            ContractCustomerAdapter(
                context!!,
                contractlist
            )
        contracts_as_customer_recycler.adapter = adapter
    }

    override fun error(error: String?) {
        pd.dismiss()
        utility.relative_snackbar(
            parent_contract!!,
            error,
            getString(R.string.close_up)
        )
    }


}