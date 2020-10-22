package com.casebeaumonde.activities.myContracts.tabs.offers

import android.app.ProgressDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.casebeaumonde.Controller.Controller
import com.casebeaumonde.R
import com.casebeaumonde.activities.myContracts.tabs.WorkInvitation.adapter.WorkRecieveInvitationAdapter
import com.casebeaumonde.activities.myContracts.tabs.WorkInvitation.adapter.WorkSendInvitationAdapter
import com.casebeaumonde.activities.myContracts.tabs.offers.response.OfferListResponse
import com.casebeaumonde.activities.myContracts.tabs.WorkInvitation.response.WorkInvitationResponse
import com.casebeaumonde.activities.myContracts.tabs.offers.adapter.RecieveOffersAdapter
import com.casebeaumonde.activities.myContracts.tabs.offers.adapter.SendOffersAdapter
import com.casebeaumonde.constants.BaseFrag
import com.casebeaumonde.constants.Constants
import com.casebeaumonde.utilities.Utility
import kotlinx.android.synthetic.main.fragment_offers.*
import kotlinx.android.synthetic.main.fragment_work_inviation.*
import retrofit2.Response

class OffersFrag : BaseFrag(), Controller.OfferListAPI {

    private lateinit var utility: Utility
    private lateinit var pd: ProgressDialog
    private lateinit var controller: Controller
    private lateinit var offer_recieved: Button
    private lateinit var offer_sent: Button
    private lateinit var offer_recycler: RecyclerView
    private lateinit var offersentinvitations_recycler: RecyclerView
    private lateinit var sendOffer: ArrayList<OfferListResponse.Data.User.SentOffer>
    private lateinit var recieveOffer: ArrayList<OfferListResponse.Data.User.ReceivedOffer>
    lateinit var type: String
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view: View

        view = inflater.inflate(R.layout.fragment_offers, container, false)

        findIds(view)
        controller = Controller()
        controller.Controller(this)
        lisenters()
        if (utility.isConnectingToInternet(context)) {
            pd.show()
            pd.setContentView(R.layout.loading)
            controller.OfferList(
                "Bearer " + getStringVal(Constants.TOKEN)
            )

        } else {
            utility.relative_snackbar(
                parent_offers!!,
                "No Internet Connectivity",
                getString(R.string.close_up)
            )
        }

        return view
    }

    private fun lisenters() {
        offer_sent.setOnClickListener {
            offer_sent.setBackgroundColor(Color.WHITE)
            offer_sent.setTextColor(Color.BLACK)
            offer_recieved.setBackgroundColor(Color.BLACK)
            offer_recieved.setTextColor(Color.WHITE)
            type = "sent"
            offer_recycler.visibility = View.GONE
            offersentinvitations_recycler.visibility = View.VISIBLE
            setFullData(sendOffer, type)
        }

        offer_recieved.setOnClickListener {
            offer_sent.setBackgroundColor(Color.BLACK)
            offer_sent.setTextColor(Color.WHITE)
            offer_recieved.setBackgroundColor(Color.WHITE)
            offer_recieved.setTextColor(Color.BLACK)
            type = "recieve"
            offer_recycler.visibility = View.VISIBLE
            offersentinvitations_recycler.visibility = View.GONE
            setRecieveData(recieveOffer, type)

        }
    }

    private fun findIds(view: View?) {
        offer_recieved = view!!.findViewById(R.id.offer_recieved)
        offer_sent = view.findViewById(R.id.offer_sent)
        offer_recycler = view.findViewById(R.id.offer_recycler)
        offersentinvitations_recycler = view.findViewById(R.id.offersentinvitations_recycler)
        utility = Utility()
        pd = ProgressDialog(context)
        pd!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        pd!!.window!!.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        pd!!.isIndeterminate = true
        pd!!.setCancelable(false)
    }

    override fun onOfferListSuccess(offerlist: Response<OfferListResponse>) {
        pd.dismiss()

        sendOffer = ArrayList()
        sendOffer =
            offerlist.body()?.data?.user?.sentOffers as ArrayList<OfferListResponse.Data.User.SentOffer>

        //setFullData(sendOffer, "sent")
        recieveOffer = ArrayList()
        recieveOffer =
            offerlist.body()?.data?.user?.receivedOffers as ArrayList<OfferListResponse.Data.User.ReceivedOffer>
        setRecieveData(recieveOffer, "recieve")


        if (offerlist.body()?.data?.user?.role.equals("customer")) {
            type = "sent"
            //worksentinvitations_recycler.visibility = View.VISIBLE
            setFullData(sendOffer, "sent")
            offersentinvitations_recycler.visibility = View.VISIBLE

        } else {
            offer_recieved.visibility = View.VISIBLE
            offer_sent.visibility = View.VISIBLE
            offer_recieved.setBackgroundColor(Color.WHITE)
            offer_recieved.setTextColor(Color.BLACK)
            type = "recieve"
            setRecieveData(recieveOffer, type)

        }
    }

    private fun setRecieveData(
        reciveoffer: ArrayList<OfferListResponse.Data.User.ReceivedOffer>,
        s: String
    ) {
        offer_recycler.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        val adapter =
            RecieveOffersAdapter(
                context!!,
                reciveoffer
            )

        offer_recycler.adapter = adapter
    }

    private fun setFullData(
        sentInvitations: MutableList<OfferListResponse.Data.User.SentOffer>,
        type: String
    ) {

        //if (getStringVal(Constants.USER_ROLE).equals("customer") || type.equals("sent")) {

        offersentinvitations_recycler.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        val adapter =
            SendOffersAdapter(
                context!!,
                sentInvitations
            )
        offersentinvitations_recycler.adapter = adapter
        // }
    }

    override fun error(error: String?) {
        pd.dismiss()
        utility!!.relative_snackbar(
            parent_workinvitation!!,
            error,
            getString(R.string.close_up)
        )
    }

}