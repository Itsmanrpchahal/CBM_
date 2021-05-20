package com.casebeaumonde.fragments.contracts.detail

import android.app.ProgressDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.casebeaumonde.Controller.Controller
import com.casebeaumonde.R
import com.casebeaumonde.activities.EventsInvitations.response.AcceptDeclineInvitationResponse
import com.casebeaumonde.activities.myContracts.tabs.WorkInvitation.response.WorkInvitationResponse
import com.casebeaumonde.constants.BaseClass
import com.casebeaumonde.constants.Constants
import com.casebeaumonde.fragments.contracts.offers.response.OfferListResponse
import com.casebeaumonde.utilities.Utility
import com.casebeaumonde.utilities.Utils
import kotlinx.android.synthetic.main.activity_detail_page.*
import kotlinx.android.synthetic.main.fragment_work_inviation.*
import kotlinx.android.synthetic.main.fragment_work_inviation.parent_workinvitation
import retrofit2.Response

class DetailPage : BaseClass(), Controller.WorkInvitationAPI,
    Controller.AcceptDeclineInvitationAPI,Controller.OfferListAPI {

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
    private lateinit var id_c:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_page)
        controller = Controller()
        controller.Controller(this, this,this)
        findIDs()
        listeners()

    }

    private fun listeners() {
        back.setOnClickListener { onBackPressed() }

        accept_bt.setOnClickListener {
            if (utility.isConnectingToInternet(this)) {
                pd.show()
                pd.setContentView(R.layout.loading)

                if (from.equals("invitation")||from.equals("invitation_R"))
                {
                    controller.AcceptDeclineInvitation(
                        "Bearer " + getStringVal(Constants.TOKEN),
                        id_c,
                        "1"
                    )
                } else {

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

                controller.AcceptDeclineInvitation(
                    "Bearer " + getStringVal(Constants.TOKEN),
                    id_c,
                    "0"
                )

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

        if (utility.isConnectingToInternet(this)) {
            pd.show()
            pd.setContentView(R.layout.loading)
            if (from.equals("invitation")||from.equals("invitation_R")) {
                controller.WorkInvitation(
                    "Bearer " + getStringVal(Constants.TOKEN),
                    getStringVal(Constants.USERID)
                )
            } else if (from.equals("sendoffer") || from.equals("recieveoffer"))
            {
                controller.OfferList("Bearer "+getStringVal(Constants.TOKEN))
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
                id_c = workInvitation.body()?.getData()?.user?.receivedInvitations?.get(id.toInt())?.id.toString()
                Glide.with(this).load(
                    Constants.BASE_IMAGE_URL + workInvitation.body()
                        ?.getData()?.user?.receivedInvitations?.get(id.toInt())?.user?.avatar
                ).placeholder(R.drawable.login_banner1).into(inviationimage)
                invitation_title.setText(
                    workInvitation.body()
                        ?.getData()?.user?.receivedInvitations?.get(id.toInt())?.description
                )
                invitation_date.setText("Sent On:"+
                    Utils.changeDateTimeToDateTime(
                        workInvitation.body()
                            ?.getData()?.user?.receivedInvitations?.get(id.toInt())?.createdAt
                    )
                )
                detail.setText("Status:"+
                    workInvitation.body()
                        ?.getData()?.user?.receivedInvitations?.get(id.toInt())?.status?.substring(
                        0,
                        1
                    )?.toUpperCase() + workInvitation.body()
                        ?.getData()?.user?.receivedInvitations?.get(id.toInt())?.status?.substring(1)
                )
                if(!from.equals("invitation_R"))
                {
                    if (workInvitation.body()
                            ?.getData()?.user?.receivedInvitations?.get(id.toInt())?.status.equals("accepted")
                    ) {
                        decline_bt.visibility = View.VISIBLE
                        accept_bt.visibility = View.GONE
                    } else if (workInvitation.body()
                            ?.getData()?.user?.receivedInvitations?.get(id.toInt())?.status.equals("declined")
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
                id_c = offerlist.body()?.getData()?.user?.receivedOffers?.get(id.toInt())?.id.toString()
                Glide.with(this).load(
                    Constants.BASE_IMAGE_URL + offerlist.body()
                        ?.getData()?.user?.receivedOffers?.get(id.toInt())?.user?.avatar
                ).placeholder(R.drawable.login_banner1).into(inviationimage)
//                invitation_title.setText(
//                    offerlist.body()
//                        ?.getData()?.user?.receivedOffers?.get(id.toInt())?.designer
//                )
                invitation_date.setText("Sent On:"+
                        Utils.changeDateTimeToDateTime(
                            offerlist.body()
                                ?.getData()?.user?.receivedOffers?.get(id.toInt())?.createdAt
                        )
                )
                detail.setText("Status:"+
                        offerlist.body()
                            ?.getData()?.user?.receivedOffers?.get(id.toInt())?.status?.substring(
                                0,
                                1
                            )?.toUpperCase() + offerlist.body()
                    ?.getData()?.user?.receivedOffers?.get(id.toInt())?.status?.substring(1)
                )
                if(from.equals("recieveoffer"))
                {
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

    override fun error(error: String?) {
        utility.relative_snackbar(
            parent_detail!!,
            error,
            getString(R.string.close_up)
        )
    }
}