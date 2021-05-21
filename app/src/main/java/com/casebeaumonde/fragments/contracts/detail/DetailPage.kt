package com.casebeaumonde.fragments.contracts.detail

import android.app.Dialog
import android.app.ProgressDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.*
import com.bumptech.glide.Glide
import com.casebeaumonde.Controller.Controller
import com.casebeaumonde.R
import com.casebeaumonde.activities.EventsInvitations.response.AcceptDeclineInvitationResponse
import com.casebeaumonde.activities.myContracts.tabs.Contract.response.ContractListResponse
import com.casebeaumonde.activities.myContracts.tabs.Contract.response.SendClaimResponse
import com.casebeaumonde.activities.myContracts.tabs.WorkInvitation.response.WorkInvitationResponse
import com.casebeaumonde.constants.BaseClass
import com.casebeaumonde.constants.Constants
import com.casebeaumonde.fragments.contracts.offers.response.OfferListResponse
import com.casebeaumonde.fragments.contracts.offers.response.SetOfferDecisionResponse
import com.casebeaumonde.utilities.Utility
import com.casebeaumonde.utilities.Utils
import kotlinx.android.synthetic.main.activity_detail_page.*
import kotlinx.android.synthetic.main.fragment_my_contracts.*
import kotlinx.android.synthetic.main.fragment_work_inviation.*
import kotlinx.android.synthetic.main.fragment_work_inviation.parent_workinvitation
import org.w3c.dom.Text
import retrofit2.Response

class DetailPage : BaseClass(), Controller.WorkInvitationAPI,
    Controller.AcceptDeclineInvitationAPI, Controller.OfferListAPI, Controller.SetOfferDecisionAPI,
    Controller.ContractListAPI,Controller.SendClaimAPI {

    private lateinit var utility: Utility
    private lateinit var pd: ProgressDialog
    private lateinit var controller: Controller
    private lateinit var back: ImageButton
    private lateinit var id: String
    private lateinit var from: String
    private lateinit var inviationimage: ImageView
    private lateinit var invitation_title: TextView
    private lateinit var invitation_date: TextView
    private lateinit var detail: TextView
    private lateinit var accept_bt: Button
    private lateinit var decline_bt: Button
    private lateinit var setjobasopened: Button
    private lateinit var open_claimbt: Button
    private lateinit var id_c: String
    private lateinit var title: TextView
    private lateinit var contractnumber: TextView
    private lateinit var giggenratedthiscontract: TextView
    private lateinit var rateagreed: TextView
    private lateinit var typeofrate: TextView
    private lateinit var hours: TextView
    private lateinit var amount: TextView
    private lateinit var sendClaim:Dialog
    private lateinit var setJobDialog:Dialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_page)
        controller = Controller()
        controller.Controller(this, this, this, this, this,this)
        findIDs()
        listeners()

    }

    private fun listeners() {
        back.setOnClickListener { onBackPressed() }



        accept_bt.setOnClickListener {
            if (utility.isConnectingToInternet(this)) {
                pd.show()
                pd.setContentView(R.layout.loading)

                if (from.equals("invitation") || from.equals("invitation_R")) {
                    controller.AcceptDeclineInvitation(
                        "Bearer " + getStringVal(Constants.TOKEN),
                        id_c,
                        "1"
                    )
                } else if (from.equals("sendoffer") || from.equals("recieveoffer")) {

                    controller.SetOfferDecision(
                        "Bearer " + getStringVal(Constants.TOKEN),
                        id_c,
                        "1"
                    )
                }

            } else {
                utility.relative_snackbar(
                    parent_detail!!,
                    "No Internet Connectivity",
                    getString(R.string.close_up)
                )
            }
        }

        decline_bt.setOnClickListener {
            if (utility.isConnectingToInternet(this)) {
                pd.show()
                pd.setContentView(R.layout.loading)
                if (from.equals("invitation") || from.equals("invitation_R")) {
                    controller.AcceptDeclineInvitation(
                        "Bearer " + getStringVal(Constants.TOKEN),
                        id_c,
                        "0"
                    )

                } else if (from.equals("sendoffer") || from.equals("recieveoffer")) {
                    controller.SetOfferDecision(
                        "Bearer " + getStringVal(Constants.TOKEN),
                        id_c,
                        "0"
                    )
                }

            } else {
                utility.relative_snackbar(
                    parent_detail!!,
                    "No Internet Connectivity",
                    getString(R.string.close_up)
                )
            }
        }
    }

    private fun findIDs() {
        id = intent.getStringExtra("id").toString()
        from = intent.getStringExtra("from").toString()

        utility = Utility()
        pd = ProgressDialog(this)
        pd!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        pd!!.window!!.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        pd!!.isIndeterminate = true
        pd!!.setCancelable(false)

        back = findViewById(R.id.back)
        inviationimage = findViewById(R.id.inviationimage)
        invitation_title = findViewById(R.id.invitation_title)
        invitation_date = findViewById(R.id.invitation_date)
        detail = findViewById(R.id.detail)
        accept_bt = findViewById(R.id.accept_bt)
        decline_bt = findViewById(R.id.decline_bt)
        title = findViewById(R.id.title)
        contractnumber = findViewById(R.id.contractnumber)
        giggenratedthiscontract = findViewById(R.id.giggenratedthiscontract)
        rateagreed = findViewById(R.id.rateagreed)
        typeofrate = findViewById(R.id.typeofrate)
        hours = findViewById(R.id.hours)
        amount = findViewById(R.id.amount)
        setjobasopened = findViewById(R.id.setjobasopened)
        open_claimbt = findViewById(R.id.open_claimbt)

        if (from.equals("invitation") || from.equals("invitation_R")) {
            title.setText("Manage Work Invitation")
        } else if (from.equals("sendoffer") || from.equals("recieveoffer")) {
            title.setText("Manage Offers")
        } else if (from.equals("contractor") || from.equals("customer"))
        {
            title.setText("Manage Contracts")
        }

        if (utility.isConnectingToInternet(this)) {
            pd.show()
            pd.setContentView(R.layout.loading)
            if (from.equals("invitation") || from.equals("invitation_R")) {
                controller.WorkInvitation(
                    "Bearer " + getStringVal(Constants.TOKEN),
                    getStringVal(Constants.USERID)
                )
            } else if (from.equals("sendoffer") || from.equals("recieveoffer")) {
                controller.OfferList("Bearer " + getStringVal(Constants.TOKEN))
            }else if (from.equals("contractor") || from.equals("customer"))
            {
               controller.ContractList("Bearer "+getStringVal(Constants.TOKEN))
            }


        } else {
            utility.relative_snackbar(
                parent_detail!!,
                "No Internet Connectivity",
                getString(R.string.close_up)
            )
        }
    }

    override fun onWorkInviationSuccess(workInvitation: Response<WorkInvitationResponse>) {
        pd.dismiss()
        if (workInvitation.isSuccessful) {
            if (workInvitation.body()?.getCode().equals("200")) {
                if (from.equals("invitation")) {
                    id_c = workInvitation.body()
                        ?.getData()?.user?.sentInvitations?.get(id.toInt())?.id.toString()
                    Glide.with(this).load(
                        Constants.BASE_IMAGE_URL + workInvitation.body()
                            ?.getData()?.user?.sentInvitations?.get(id.toInt())?.designer?.avatar
                    ).placeholder(R.drawable.login_banner1).into(inviationimage)
                    invitation_title.setText(
                        workInvitation.body()
                            ?.getData()?.user?.sentInvitations?.get(id.toInt())?.description
                    )
                    invitation_date.setText(
                        "Sent On:" +
                                Utils.changeDateTimeToDateTime(
                                    workInvitation.body()
                                        ?.getData()?.user?.sentInvitations?.get(id.toInt())?.createdAt
                                )
                    )
                    detail.setText(
                        "Status:" +
                                workInvitation.body()
                                    ?.getData()?.user?.sentInvitations?.get(id.toInt())?.status?.substring(
                                        0,
                                        1
                                    )?.toUpperCase() + workInvitation.body()
                            ?.getData()?.user?.sentInvitations?.get(id.toInt())?.status?.substring(1)
                    )


                } else {
                    id_c = workInvitation.body()
                        ?.getData()?.user?.receivedInvitations?.get(id.toInt())?.id.toString()
                    Glide.with(this).load(
                        Constants.BASE_IMAGE_URL + workInvitation.body()
                            ?.getData()?.user?.receivedInvitations?.get(id.toInt())?.user?.avatar
                    ).placeholder(R.drawable.login_banner1).into(inviationimage)
                    invitation_title.setText(
                        workInvitation.body()
                            ?.getData()?.user?.receivedInvitations?.get(id.toInt())?.description
                    )
                    invitation_date.setText(
                        "Sent On:" +
                                Utils.changeDateTimeToDateTime(
                                    workInvitation.body()
                                        ?.getData()?.user?.receivedInvitations?.get(id.toInt())?.createdAt
                                )
                    )
                    detail.setText(
                        "Status:" +
                                workInvitation.body()
                                    ?.getData()?.user?.receivedInvitations?.get(id.toInt())?.status?.substring(
                                        0,
                                        1
                                    )?.toUpperCase() + workInvitation.body()
                            ?.getData()?.user?.receivedInvitations?.get(id.toInt())?.status?.substring(
                                1
                            )
                    )

                    if (workInvitation.body()
                            ?.getData()?.user?.receivedInvitations?.get(id.toInt())?.status.equals(
                                "accepted"
                            )
                    ) {
                        decline_bt.visibility = View.VISIBLE
                        accept_bt.visibility = View.GONE
                    } else if (workInvitation.body()
                            ?.getData()?.user?.receivedInvitations?.get(id.toInt())?.status.equals(
                                "declined"
                            )
                    ) {
                        accept_bt.visibility = View.VISIBLE
                        decline_bt.visibility = View.GONE
                    } else {
                        accept_bt.visibility = View.VISIBLE
                        decline_bt.visibility = View.VISIBLE
                    }

                }

            } else {
                utility.relative_snackbar(
                    parent_detail!!,
                    workInvitation.message(),
                    getString(R.string.close_up)
                )
            }
        } else {
            utility.relative_snackbar(
                parent_detail!!,
                workInvitation.message(),
                getString(R.string.close_up)
            )
        }
    }

    override fun onAcceptDeclineInvitationSuccess(success: Response<AcceptDeclineInvitationResponse>) {
        controller.WorkInvitation(
            "Bearer " + getStringVal(Constants.TOKEN),
            getStringVal(Constants.USERID)
        )
    }

    override fun onOfferListSuccess(offerlist: Response<OfferListResponse>) {
        pd.dismiss()
        if (offerlist.isSuccessful) {
            if (offerlist.body()?.getCode().equals("200")) {
                if (from.equals("sendoffer"))
                {
                    id_c = offerlist.body()
                        ?.getData()?.user?.sentOffers?.get(id.toInt())?.id.toString()
                    Glide.with(this).load(
                        Constants.BASE_IMAGE_URL + offerlist.body()
                            ?.getData()?.user?.sentOffers?.get(id.toInt())?.designer?.avatar
                    ).placeholder(R.drawable.login_banner1).into(inviationimage)
                invitation_title.setText(
                    offerlist.body()
                        ?.getData()?.user?.sentOffers?.get(id.toInt())?.gig?.title
                )
                    invitation_date.setText(
                        "Sent On:" +
                                Utils.changeDateTimeToDateTime(
                                    offerlist.body()
                                        ?.getData()?.user?.sentOffers?.get(id.toInt())?.createdAt
                                )
                    )
                    detail.setText(
                        "Status:" +
                                offerlist.body()
                                    ?.getData()?.user?.sentOffers?.get(id.toInt())?.status?.substring(
                                        0,
                                        1
                                    )?.toUpperCase() + offerlist.body()
                            ?.getData()?.user?.sentOffers?.get(id.toInt())?.status?.substring(1)
                    )
                } else {
                    id_c = offerlist.body()
                        ?.getData()?.user?.receivedOffers?.get(id.toInt())?.id.toString()
                    Glide.with(this).load(
                        Constants.BASE_IMAGE_URL + offerlist.body()
                            ?.getData()?.user?.receivedOffers?.get(id.toInt())?.designer?.avatar
                    ).placeholder(R.drawable.login_banner1).into(inviationimage)
                invitation_title.setText(
                    offerlist.body()
                        ?.getData()?.user?.receivedOffers?.get(id.toInt())?.gig?.title
                )
                    invitation_date.setText(
                        "Sent On:" +
                                Utils.changeDateTimeToDateTime(
                                    offerlist.body()
                                        ?.getData()?.user?.receivedOffers?.get(id.toInt())?.createdAt
                                )
                    )
                    detail.setText(
                        "Status:" +
                                offerlist.body()
                                    ?.getData()?.user?.receivedOffers?.get(id.toInt())?.status?.substring(
                                        0,
                                        1
                                    )?.toUpperCase() + offerlist.body()
                            ?.getData()?.user?.receivedOffers?.get(id.toInt())?.status?.substring(1)
                    )
                    if (from.equals("recieveoffer")) {
                        if (offerlist.body()
                                ?.getData()?.user?.receivedOffers?.get(id.toInt())?.status.equals("accepted")
                        ) {
                            decline_bt.visibility = View.VISIBLE
                            accept_bt.visibility = View.GONE
                        } else if (offerlist.body()
                                ?.getData()?.user?.receivedOffers?.get(id.toInt())?.status.equals("declined")
                        ) {
                            accept_bt.visibility = View.VISIBLE
                            decline_bt.visibility = View.GONE
                        } else {
                            accept_bt.visibility = View.VISIBLE
                            decline_bt.visibility = View.VISIBLE
                        }
                    }
                }



            } else {
                utility.relative_snackbar(
                    parent_detail!!,
                    offerlist.message(),
                    getString(R.string.close_up)
                )
            }
        } else {
            utility.relative_snackbar(
                parent_detail!!,
                offerlist.message(),
                getString(R.string.close_up)
            )
        }
    }

    override fun onSetOfferSuccess(setOffer: Response<SetOfferDecisionResponse>) {
        controller.OfferList("Bearer " + getStringVal(Constants.TOKEN))
    }

    override fun onContractListSuccess(contractlist: Response<ContractListResponse>) {
        pd.dismiss()
        if (contractlist.isSuccessful) {
            if (contractlist.body()?.getCode().equals("200")) {
                if (from.equals("contractor")) {
                    id_c = contractlist.body()
                        ?.getData()?.user?.contractsAsContractor?.get(id.toInt())?.id.toString()
                    Glide.with(this).load(
                        Constants.BASE_IMAGE_URL + contractlist.body()
                            ?.getData()?.user?.contractsAsContractor?.get(id.toInt())?.contractor?.avatar
                    ).placeholder(R.drawable.login_banner1).into(inviationimage)
                    invitation_title.setText(
                        contractlist.body()
                            ?.getData()?.user?.contractsAsContractor?.get(id.toInt())?.gig?.description
                    )
                    invitation_date.setText(
                        "Sent On:" +
                                Utils.changeDateTimeToDateTime(
                                    contractlist.body()
                                        ?.getData()?.user?.contractsAsContractor?.get(id.toInt())?.createdAt
                                )
                    )
                    detail.setText(
                        "Status:" +
                                contractlist.body()
                                    ?.getData()?.user?.contractsAsContractor?.get(id.toInt())?.status?.substring(
                                        0,
                                        1
                                    )?.toUpperCase() + contractlist.body()
                            ?.getData()?.user?.contractsAsContractor?.get(id.toInt())?.status?.substring(1)
                    )

                    contractnumber.visibility = View.VISIBLE
                    giggenratedthiscontract.visibility = View.VISIBLE
                    rateagreed.visibility = View.VISIBLE
                    typeofrate.visibility = View.VISIBLE
                    hours.visibility = View.VISIBLE
                    amount.visibility = View.VISIBLE

                    contractnumber.setText("Contract Number:"+contractlist.body()?.getData()?.user?.contractsAsContractor?.get(id.toInt())?.contractor?.contract_number)
                    giggenratedthiscontract.setText("Gig that generated for this contract:"+contractlist.body()?.getData()?.user?.contractsAsContractor?.get(id.toInt())?.customer?.firstname)
                    rateagreed.setText("Rate agreed:"+contractlist.body()?.getData()?.user?.contractsAsContractor?.get(id.toInt())?.rate)
                    typeofrate.setText("Type of rate agreed:"+contractlist.body()?.getData()?.user?.contractsAsContractor?.get(id.toInt())?.rateType)
                    hours.setText("Quantity of hours agreed:"+contractlist.body()?.getData()?.user?.contractsAsContractor?.get(id.toInt())?.hours)
                    amount.setText("Total amount agreed:"+contractlist.body()?.getData()?.user?.contractsAsContractor?.get(id.toInt())?.totalAmount)

                    if (contractlist?.body()?.getData()?.user?.contractsAsContractor?.get(id.toInt())?.status.equals("funds_release_requested")||contractlist?.body()?.getData()?.user?.contractsAsContractor?.get(id.toInt())?.status.equals("opened"))
                    {
                        open_claimbt.visibility = View.VISIBLE
                        setjobasopened.visibility = View.VISIBLE
                    }

                    open_claimbt.setOnClickListener {
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
                        var closecliam: Button
                        var issue_et: EditText
                        contract_id = sendClaim.findViewById(R.id.contract_id)
                        sendclaim = sendClaim.findViewById(R.id.sendclaim)
                        closecliam = sendClaim.findViewById(R.id.closecliam)
                        issue_et = sendClaim.findViewById(R.id.issue_et)

                        contract_id.setText("Contract#:" + contractlist?.body()?.getData()?.user?.contractsAsContractor?.get(id?.toInt()!!)?.contractNumber)

                        closecliam.setOnClickListener {
                            sendClaim.dismiss()
                        }
                        sendclaim.setOnClickListener {
                            when {
                                issue_et.text.isEmpty() -> {
                                    issue_et.requestFocus()
                                    issue_et.error = " Enter Issue "
                                }
                                else -> {
                                    pd.show()
                                    controller.SendClaim(
                                        "Bearer " + getStringVal(Constants.TOKEN),
                                        contractlist.body()?.getData()?.user?.contractsAsContractor?.get(id.toInt())?.contractor?.id.toString(), issue_et.text.toString()
                                    )
                                }
                            }
                        }

                        sendClaim.show()
                    }

                    setjobasopened.setOnClickListener {
                        setJobDialog = Dialog(this!!)
                        setJobDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
                        setJobDialog.setContentView(R.layout.unblockuser_popup)
                        val window = setJobDialog.window
                        window?.setLayout(
                            WindowManager.LayoutParams.MATCH_PARENT,
                            WindowManager.LayoutParams.WRAP_CONTENT
                        )

                        var title:TextView
                        var subtitle : TextView
                        var cancel : Button
                        var go : Button
                        title = setJobDialog.findViewById(R.id.title)
                        subtitle = setJobDialog.findViewById(R.id.subtitle)
                        cancel = setJobDialog.findViewById(R.id.cancel)
                        go = setJobDialog.findViewById(R.id.go)

                        title.text =  "Notify job as opened"
                        subtitle.text ="This will notify a job as opened again.Note that if the customer has already taken action on your previous notification of completed job,you must contact us and the client in order to revert this action."

                        go.setOnClickListener {

                        }
                        cancel.setOnClickListener { setJobDialog.dismiss() }
                        setJobDialog.show()
                    }

                } else {
                    id_c = contractlist.body()
                        ?.getData()?.user?.contractsAsCustomer?.get(id.toInt())?.id.toString()
                    Glide.with(this).load(
                        Constants.BASE_IMAGE_URL + contractlist.body()
                            ?.getData()?.user?.contractsAsCustomer?.get(id.toInt())?.contractor?.avatar
                    ).placeholder(R.drawable.login_banner1).into(inviationimage)
                    invitation_title.setText(
                        contractlist.body()
                            ?.getData()?.user?.contractsAsCustomer?.get(id.toInt())?.gig?.description
                    )
                    invitation_date.setText(
                        "Sent On:" +
                                Utils.changeDateTimeToDateTime(
                                    contractlist.body()
                                        ?.getData()?.user?.contractsAsCustomer?.get(id.toInt())?.createdAt
                                )
                    )
                    detail.setText(
                        "Status:" +
                                contractlist.body()
                                    ?.getData()?.user?.contractsAsCustomer?.get(id.toInt())?.status?.substring(
                                        0,
                                        1
                                    )?.toUpperCase() + contractlist.body()
                            ?.getData()?.user?.contractsAsCustomer?.get(id.toInt())?.status?.substring(
                                1
                            )
                    )

                    contractnumber.visibility = View.VISIBLE
                    giggenratedthiscontract.visibility = View.VISIBLE
                    rateagreed.visibility = View.VISIBLE
                    typeofrate.visibility = View.VISIBLE
                    hours.visibility = View.VISIBLE
                    amount.visibility = View.VISIBLE

                    contractnumber.setText("Contract Number:"+contractlist.body()?.getData()?.user?.contractsAsCustomer?.get(id.toInt())?.contractor?.contract_number)
                    giggenratedthiscontract.setText("Gig that generated for this contract:"+contractlist.body()?.getData()?.user?.contractsAsCustomer?.get(id.toInt())?.customer?.firstname)
                    rateagreed.setText("Rate agreed:"+contractlist.body()?.getData()?.user?.contractsAsCustomer?.get(id.toInt())?.rate)
                    typeofrate.setText("Type of rate agreed:"+contractlist.body()?.getData()?.user?.contractsAsCustomer?.get(id.toInt())?.rateType)
                    hours.setText("Quantity of hours agreed:"+contractlist.body()?.getData()?.user?.contractsAsCustomer?.get(id.toInt())?.hours)
                    amount.setText("Total amount agreed:"+contractlist.body()?.getData()?.user?.contractsAsContractor?.get(id.toInt())?.totalAmount)

                    if (contractlist.body()
                            ?.getData()?.user?.contractsAsCustomer?.get(id.toInt())?.status.equals(
                                "accepted"
                            )
                    ) {
                        decline_bt.visibility = View.VISIBLE
                        accept_bt.visibility = View.GONE
                    } else if (contractlist.body()
                            ?.getData()?.user?.contractsAsCustomer?.get(id.toInt())?.status.equals(
                                "declined"
                            )
                    ) {
                        accept_bt.visibility = View.VISIBLE
                        decline_bt.visibility = View.GONE
                    } else {
                        accept_bt.visibility = View.VISIBLE
                        decline_bt.visibility = View.VISIBLE
                    }

                }

            } else {
                utility.relative_snackbar(
                    parent_detail!!,
                    contractlist.message(),
                    getString(R.string.close_up)
                )
            }
        } else {
            utility.relative_snackbar(
                parent_detail!!,
                contractlist.message(),
                getString(R.string.close_up)
            )
        }
    }

    override fun onSendClaimSuccess(sendclaim: Response<SendClaimResponse>) {
        pd.dismiss()
        if (sendclaim.isSuccessful) {

            sendClaim.dismiss()
            controller.ContractList("Bearer "+getStringVal(Constants.TOKEN))
            utility.relative_snackbar(
                parent_detail!!,
                sendclaim.body()?.getMessage(),
                getString(R.string.close_up)
            )
        } else {
            utility.relative_snackbar(
                parent_contract!!,
                sendclaim.message(),
                getString(R.string.close_up)
            )
        }
     }


    override fun error(error: String?) {
        pd.dismiss()
        utility.relative_snackbar(
            parent_detail!!,
            error,
            getString(R.string.close_up)
        )
    }
}