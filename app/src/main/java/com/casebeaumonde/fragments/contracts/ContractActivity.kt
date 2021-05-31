package com.casebeaumonde.fragments.contracts

import android.app.Dialog
import android.app.ProgressDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.casebeaumonde.Controller.Controller
import com.casebeaumonde.R
import com.casebeaumonde.activities.myContracts.tabs.Contract.IF.GetContractID_IF
import com.casebeaumonde.activities.myContracts.tabs.Contract.MyContractsFrag
import com.casebeaumonde.activities.myContracts.tabs.Contract.adapter.ContractCustomerAdapter
import com.casebeaumonde.activities.myContracts.tabs.Contract.adapter.ContractorAdapter
import com.casebeaumonde.activities.myContracts.tabs.Contract.response.ContractListResponse
import com.casebeaumonde.activities.myContracts.tabs.Contract.response.SendClaimResponse
import com.casebeaumonde.constants.BaseClass
import com.casebeaumonde.constants.Constants
import com.casebeaumonde.utilities.Utility
import com.casebeaumonde.utilities.Utils
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.activity_contract.*
import kotlinx.android.synthetic.main.fragment_my_contracts.*
import kotlinx.android.synthetic.main.fragment_my_contracts.parent_contract
import retrofit2.Response

class ContractActivity : BaseClass(), Controller.ContractListAPI, Controller.SendClaimAPI,
    GetContractID_IF {
    private lateinit var utility: Utility
    private lateinit var pd: ProgressDialog
    private lateinit var controller: Controller
    private lateinit var contract_customer: Button
    private lateinit var contract_contractor: Button
    private lateinit var contracts_as_customer_recycler: RecyclerView
    private lateinit var contracts_as_contractor_recycler: RecyclerView
    private lateinit var contract: ArrayList<ContractListResponse.Data.User.ContractsAsContractor>
    private lateinit var customer: ArrayList<ContractListResponse.Data.User.ContractsAsCustomer>
    lateinit var type: String
    private lateinit var contractDialog: Dialog
    private lateinit var sendClaim: Dialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contract)

        controller = Controller()
        controller.Controller(this, this)
        MyContractsFrag.getcontractidIf = this
        findIds()
        lisenters()
        if (utility.isConnectingToInternet(this)) {
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
    }

    private fun findIds() {
        contract_customer = findViewById(R.id.contract_customer)
        contract_contractor = findViewById(R.id.contract_contractor)
        contracts_as_customer_recycler = findViewById(R.id.contracts_as_customer_recycler)
        contracts_as_contractor_recycler = findViewById(R.id.contracts_as_contractor_recycler)
        utility = Utility()
        pd = ProgressDialog(this)
        pd!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        pd!!.window!!.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        pd!!.isIndeterminate = true
        pd!!.setCancelable(false)
    }

    companion object {
        var getcontractidIf: GetContractID_IF? = null
    }

    private fun lisenters() {
        contract_customer.setOnClickListener {
            contract_customer.setBackgroundColor(Color.WHITE)
            contract_customer.setTextColor(Color.BLACK)
            contract_contractor.setBackgroundColor(Color.BLACK)
            contract_contractor.setTextColor(Color.WHITE)
            type = "sent"
            contracts_as_customer_recycler.visibility = View.VISIBLE
            contracts_as_contractor_recycler.visibility = View.GONE
            //setFullData(sendOffer, type)
        }

        contract_contractor.setOnClickListener {
            contract_customer.setBackgroundColor(Color.BLACK)
            contract_customer.setTextColor(Color.WHITE)
            contract_contractor.setBackgroundColor(Color.WHITE)
            contract_contractor.setTextColor(Color.BLACK)
            type = "type"
            contracts_as_customer_recycler.visibility = View.GONE
            contracts_as_contractor_recycler.visibility = View.VISIBLE
            //setRecieveData(recieveOffer, type)

        }

        back.setOnClickListener {
            onBackPressed()
        }
    }

    override fun onContractListSuccess(contractlist: Response<ContractListResponse>) {
        pd.dismiss()
        customer = ArrayList()
        if (contractlist.body()?.getData()?.user?.contractsAsCustomer?.size!! > 0)
        {
            customer =
                contractlist.body()?.getData()?.user?.contractsAsCustomer as ArrayList<ContractListResponse.Data.User.ContractsAsCustomer>
        }

        contract = contractlist.body()?.getData()?.user?.contractsAsContractor as ArrayList<ContractListResponse.Data.User.ContractsAsContractor>

        if (contractlist.body()?.getData()?.user?.role.equals("customer")) {
            type = "customer"
            //worksentinvitations_recycler.visibility = View.VISIBLE
            setFullData(customer, "customer")
            contracts_as_customer_recycler.visibility = View.VISIBLE
            contract_customer.visibility = View.GONE
            contract_contractor.visibility = View.GONE

        } else {
            contract_customer.visibility = View.VISIBLE
            contract_contractor.visibility = View.VISIBLE
            contract_contractor.setBackgroundColor(Color.WHITE)
            contract_contractor.setTextColor(Color.BLACK)
            type = "bussiness"
            contracts_as_contractor_recycler.visibility = View.VISIBLE

            setContractData(contract,"bussiness")
        }

    }

    private fun setContractData(contract: ArrayList<ContractListResponse.Data.User.ContractsAsContractor>, s: String) {
        contracts_as_contractor_recycler.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        val adapter =
            ContractorAdapter(
                this!!,
                contract
            )
        contracts_as_contractor_recycler.adapter = adapter
    }

    private fun setFullData(
        contractlist: ArrayList<ContractListResponse.Data.User.ContractsAsCustomer>,
        type: String
    ) {
        contracts_as_customer_recycler.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        val adapter =
            ContractCustomerAdapter(
                this!!,
                contractlist
            )
        contracts_as_customer_recycler.adapter = adapter
    }


    override fun error(error: String?) {
        pd.dismiss()
        utility.relative_snackbar(
            parent_contractA!!,
            error,
            getString(R.string.close_up)
        )
    }

    override fun getID(id: String?, pos: String?) {
        contractDialog = Dialog(this!!)
        contractDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        contractDialog.setContentView(R.layout.viewcontract)
        val window = contractDialog.window
        window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )

        var contract_date: TextView
        var contractuser_image: CircleImageView
        var contractparter_image: CircleImageView
        var contractuser_name: TextView
        var contractparter_name: TextView
        var contract_number: TextView
        var gig_that_genrated_contract: TextView
        var rate_agreed: TextView
        var type_rate: TextView
        var quantity_hours: TextView
        var contract_totalamount: TextView
        var contract_status: TextView
        var open_claim: Button
        var approvefund_claim : Button
        var declienfund_claim : Button
        var close: Button

        contract_date = contractDialog.findViewById(R.id.contract_date)
        contractuser_image = contractDialog.findViewById(R.id.contractuser_image)
        contractparter_image = contractDialog.findViewById(R.id.contractparter_image)
        contractuser_name = contractDialog.findViewById(R.id.contractuser_name)
        contractparter_name = contractDialog.findViewById(R.id.contractparter_name)
        contract_number = contractDialog.findViewById(R.id.contract_number)
        gig_that_genrated_contract = contractDialog.findViewById(R.id.gig_that_genrated_contract)
        rate_agreed = contractDialog.findViewById(R.id.rate_agreed)
        type_rate = contractDialog.findViewById(R.id.type_rate)
        quantity_hours = contractDialog.findViewById(R.id.quantity_hours)
        contract_totalamount = contractDialog.findViewById(R.id.contract_totalamount)
        contract_status = contractDialog.findViewById(R.id.contract_status)
        open_claim = contractDialog.findViewById(R.id.open_claim)
        approvefund_claim = contractDialog.findViewById(R.id.approvefund_claim)
        declienfund_claim = contractDialog.findViewById(R.id.declienfund_claim)
        close = contractDialog.findViewById(R.id.close)

        contract_date.setText("Opened on: " + Utils.changeDateTimeToDateTime(customer.get(pos?.toInt()!!).openedAt))
        Glide.with(this!!)
            .load(Constants.BASE_IMAGE_URL + customer.get(pos.toInt()).customer?.avatar)
            .placeholder(R.drawable.login_banner1).into(contractuser_image)
        Glide.with(this!!)
            .load(Constants.BASE_IMAGE_URL + customer.get(pos.toInt()).contractor?.avatar)
            .placeholder(R.drawable.login_banner1).into(contractparter_image)
        contractuser_name.setText(customer.get(pos.toInt()).customerFullname + "\n" + "Client")
        contractparter_name.setText(customer.get(pos.toInt()).contractorFullname + "\n" + "Designer")
        contract_number.setText("Contract number: " + customer.get(pos.toInt()).contractNumber)
        gig_that_genrated_contract.setText("gig_that_genrated_contract: " + customer.get(pos.toInt()).gig?.title)
        rate_agreed.setText("Rate agreed: $" + customer.get(pos.toInt()).rate)
        type_rate.setText("Type of rate agreed: " + customer.get(pos.toInt()).rateType)
        quantity_hours.setText("Quantity of hours agreed: " + customer.get(pos.toInt()).hours)
        contract_totalamount.setText("Total amount agreed: $" + customer.get(pos.toInt()).totalAmount)
        contract_status.setText("Status: " + customer.get(pos.toInt()).status?.replace("_"," "))

        if (!customer.get(pos.toInt()).status.equals("claim")) {
            open_claim.visibility = View.VISIBLE
        }

        if (customer.get(pos.toInt()).status.equals("funds_release_requested"))
        {
            approvefund_claim.visibility = View.VISIBLE
            declienfund_claim.visibility = View.VISIBLE

            approvefund_claim.setText(customer.get(pos.toInt()).status?.replace("_"," "))
        }

        open_claim.setOnClickListener {

            SendClaim(id, pos)
        }

        close.setOnClickListener { contractDialog.dismiss() }

        contractDialog.show()

    }

    private fun SendClaim(id: String?, pos: String?) {
        sendClaim = Dialog(this!!)
        sendClaim.requestWindowFeature(Window.FEATURE_NO_TITLE)
        sendClaim.setContentView(R.layout.opencliam)
        val window = sendClaim.window
        window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )

        var contract_id: TextView
        var sendclaim: Button
        var issue_et: EditText
        contract_id = sendClaim.findViewById(R.id.contract_id)
        sendclaim = sendClaim.findViewById(R.id.sendclaim)
        issue_et = sendClaim.findViewById(R.id.issue_et)

        contract_id.setText("Contract#:" + customer.get(pos?.toInt()!!).contractNumber)

        sendclaim.setOnClickListener {
            when {
                issue_et.text.isEmpty() -> {
                    issue_et.requestFocus()
                    issue_et.error = " Enter Issue "
                }
                else -> {
                    pd.show()
//                    controller.SendClaim(
//                        "Bearer " + getStringVal(Constants.TOKEN),
//                        id.toString(), issue_et.text.toString()
//                    )
                }
            }
        }



        sendClaim.show()
    }


    override fun onSendClaimSuccess(sendclaim: Response<SendClaimResponse>) {
        pd.dismiss()
        if (sendclaim.isSuccessful) {

            sendClaim.dismiss()
            contractDialog.dismiss()

            utility.relative_snackbar(
                parent_contractA!!,
                sendclaim.body()?.getMessage(),
                getString(R.string.close_up)
            )
        } else {
            utility.relative_snackbar(
                parent_contractA!!,
                sendclaim.message(),
                getString(R.string.close_up)
            )
        }
    }
}