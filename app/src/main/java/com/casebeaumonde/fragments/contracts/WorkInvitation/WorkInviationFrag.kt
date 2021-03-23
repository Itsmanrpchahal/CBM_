package com.casebeaumonde.activities.myContracts.tabs.WorkInvitation

import android.app.Dialog
import android.app.ProgressDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.casebeaumonde.Controller.Controller
import com.casebeaumonde.R
import com.casebeaumonde.activities.myContracts.tabs.WorkInvitation.adapter.WorkRecieveInvitationAdapter
import com.casebeaumonde.activities.myContracts.tabs.WorkInvitation.adapter.WorkSendInvitationAdapter
import com.casebeaumonde.activities.myContracts.tabs.WorkInvitation.response.MakeOfferResponse
import com.casebeaumonde.activities.myContracts.tabs.WorkInvitation.response.WorkInvitationResponse
import com.casebeaumonde.constants.BaseFrag
import com.casebeaumonde.constants.Constants
import com.casebeaumonde.fragments.contracts.WorkInvitation.IF.GetInviID_IF
import com.casebeaumonde.utilities.Utility
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.fragment_work_inviation.*
import retrofit2.Response

class WorkInviationFrag : BaseFrag(), Controller.WorkInvitationAPI, GetInviID_IF,
    Controller.MakeOfferAPI {

    private lateinit var utility: Utility
    private lateinit var pd: ProgressDialog
    private lateinit var controller: Controller
    private lateinit var workinvitation_recieved: Button
    private lateinit var workinvitation_sent: Button
    private lateinit var workinvitations_recycler: RecyclerView
    private lateinit var worksentinvitations_recycler: RecyclerView
    lateinit var type: String
    private lateinit var sendIvitations: ArrayList<WorkInvitationResponse.Data.User.SentInvitation>
    private lateinit var recieveIvitations: ArrayList<WorkInvitationResponse.Data.User.ReceivedInvitation>
    private lateinit var makeofferDialog: Dialog

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_work_inviation, container, false)

        controller = Controller()
        controller.Controller(this, this)
        getinviidIf = this
        findIds(view)

        if (utility.isConnectingToInternet(context)) {
            pd.show()
            pd.setContentView(R.layout.loading)
            controller.WorkInvitation (
                "Bearer " + getStringVal(Constants.TOKEN),
                getStringVal(Constants.USERID)
            )

        } else {
            utility.relative_snackbar(
                parent_workinvitation!!,
                "No Internet Connectivity",
                getString(R.string.close_up)
            )
        }

        listeners()
        return view
    }

    private fun listeners() {
        workinvitation_sent.setOnClickListener {
            workinvitation_sent.setBackgroundColor(Color.WHITE)
            workinvitation_sent.setTextColor(Color.BLACK)
            workinvitation_recieved.setBackgroundColor(Color.BLACK)
            workinvitation_recieved.setTextColor(Color.WHITE)
            type = "sent"
            workinvitations_recycler.visibility = View.GONE
            worksentinvitations_recycler.visibility = View.VISIBLE
            //workinvitation_sent.visibility = View.VISIBLE

            setFullData(sendIvitations, type)
        }

        workinvitation_recieved.setOnClickListener {
            workinvitation_sent.setBackgroundColor(Color.BLACK)
            workinvitation_sent.setTextColor(Color.WHITE)
            workinvitation_recieved.setBackgroundColor(Color.WHITE)
            workinvitation_recieved.setTextColor(Color.BLACK)
            type = "recieve"
            workinvitations_recycler.visibility = View.VISIBLE
            worksentinvitations_recycler.visibility = View.GONE
            setRecieveData(recieveIvitations, type)

        }
    }

    private fun findIds(view: View?) {
        workinvitation_recieved = view!!.findViewById(R.id.workinvitation_recieved)
        workinvitation_sent = view.findViewById(R.id.workinvitation_sent)
        workinvitations_recycler = view.findViewById(R.id.workinvitations_recycler)
        worksentinvitations_recycler = view.findViewById(R.id.worksentinvitations_recycler)
        utility = Utility()
        pd = ProgressDialog(context)
        pd!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        pd!!.window!!.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        pd!!.isIndeterminate = true
        pd!!.setCancelable(false)
    }

    override fun onWorkInviationSuccess(workInvitation: Response<WorkInvitationResponse>) {
        pd.dismiss()
        if (workInvitation.isSuccessful)
        {
            //ToDo: SendInvitations
            sendIvitations = ArrayList()
            sendIvitations =
                workInvitation.body()?.getData()?.user?.sentInvitations as ArrayList<WorkInvitationResponse.Data.User.SentInvitation>

            setFullData(sendIvitations, "sent")

            //ToDo: Recieve Invitations
            recieveIvitations = ArrayList()
            recieveIvitations =
                workInvitation.body()?.getData()?.user?.receivedInvitations as ArrayList<WorkInvitationResponse.Data.User.ReceivedInvitation>
            setRecieveData(recieveIvitations, "recieve")


            if (getStringVal(Constants.USER_ROLE).equals("customer")) {
                type = "sent"
                worksentinvitations_recycler.visibility = View.VISIBLE
                setFullData(sendIvitations, "sent")
            } else {
                workinvitation_recieved.visibility = View.VISIBLE
                workinvitation_sent.visibility = View.VISIBLE

                workinvitation_recieved.setBackgroundColor(Color.WHITE)
                workinvitation_recieved.setTextColor(Color.BLACK)
                type = "recieve"


            }
        }else{
            utility!!.relative_snackbar(
                parent_workinvitation!!,
                workInvitation.message(),
                getString(R.string.close_up)
            )
        }



    }

    private fun setRecieveData(
        recieveIvitations: ArrayList<WorkInvitationResponse.Data.User.ReceivedInvitation>,
        s: String
    ) {
        workinvitations_recycler.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        val adapter =
            WorkRecieveInvitationAdapter(
                context!!,
                recieveIvitations
            )

        workinvitations_recycler.adapter = adapter
    }

    private fun setFullData(
        sentInvitations: MutableList<WorkInvitationResponse.Data.User.SentInvitation>,
        type: String
    ) {

        //if (getStringVal(Constants.USER_ROLE).equals("customer") || type.equals("sent")) {

        worksentinvitations_recycler.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        val adapter =
            WorkSendInvitationAdapter(
                context!!,
                sentInvitations
            )
        worksentinvitations_recycler.adapter = adapter
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

    companion object {
        var getinviidIf: GetInviID_IF? = null
    }

    override fun getID(designerID: String?, gigID: String, position: Int) {
        makeOfferDialog(designerID, gigID, position)
    }

    private fun makeOfferDialog(designerID: String?, gigID: String, position: Int) {
        makeofferDialog = Dialog(context!!)
        makeofferDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        makeofferDialog.setContentView(R.layout.makeanoffer)
        val window = makeofferDialog.window
        window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )

        var gigtitletv1: TextView
        var offer_image: CircleImageView
        var gigdecstv1: TextView
        var gigrate_spinner: Spinner
        var rateet: EditText
        var commetet: EditText
        var sendoffer: Button
        var currentoffer : TextView
        var rate_title: TextView
        var close: Button
        var rateType: String = ""

        gigtitletv1 = makeofferDialog.findViewById(R.id.gigtitletv1)
        offer_image = makeofferDialog.findViewById(R.id.offer_image)
        gigdecstv1 = makeofferDialog.findViewById(R.id.gigdecstv1)
        rateet = makeofferDialog.findViewById(R.id.rateet)
        gigrate_spinner = makeofferDialog.findViewById(R.id.gigrate_spinner)
        commetet = makeofferDialog.findViewById(R.id.commetet)
        sendoffer = makeofferDialog.findViewById(R.id.sendoffer)
        currentoffer = makeofferDialog.findViewById(R.id.currentoffer)
        close = makeofferDialog.findViewById(R.id.close)
        rate_title = makeofferDialog.findViewById(R.id.rate_title)

        Glide.with(context!!).load(
            Constants.BASE_IMAGE_URL + sendIvitations.get(
                position
            ).designer?.avatar
        ).placeholder(R.drawable.login_banner).into(offer_image)
        gigtitletv1.setText(sendIvitations.get(position).description)
        gigdecstv1.setText(sendIvitations.get(position).gig?.description)
        rateet.setText(sendIvitations.get(position).gig?.rate.toString())
        currentoffer.setText("Currently offering: $"+ sendIvitations.get(position).gig?.rate.toString() +  " per hour")




        val ratetype = resources.getStringArray(R.array.Rate)
        val adapter = ArrayAdapter(
            context!!,
            android.R.layout.simple_spinner_dropdown_item, ratetype
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        gigrate_spinner.adapter = adapter
        gigrate_spinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View, position: Int, id: Long
            ) {

                rate_title.setText(ratetype[position])
                rateType = ratetype[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // write code to perform some action
            }
        }

        sendoffer.setOnClickListener {
            when {
                rateet.text.isEmpty() -> {
                    rateet.requestFocus()
                    rateet.error = "Add Rate"
                }

                commetet.text.isEmpty() -> {
                    commetet.requestFocus()
                    commetet.error = "Add Comment"
                }
                else -> {
                    sendOffer(
                        designerID,
                        gigID,
                        rateType,
                        rateet.text.toString(),
                        commetet.text.toString()
                    )
                }
            }
        }

        close.setOnClickListener { makeofferDialog.dismiss() }
        makeofferDialog.show()
    }

    private fun sendOffer(
        designerID: String?,
        gigID: String,
        rateType: String,
        rate: String,
        comment: String
    ) {

        if (utility.isConnectingToInternet(context)) {
            pd.show()
            pd.setContentView(R.layout.loading)
            controller.MakeOffer(
                "Bearer " + getStringVal(Constants.TOKEN),
                designerID.toString(),
                gigID,
                rateType.toLowerCase(),
                comment.toLowerCase(),
                rate
            )

        } else {
            utility.relative_snackbar(
                parent_workinvitation!!,
                "No Internet Connectivity",
                getString(R.string.close_up)
            )
        }
    }


    override fun onMakeOfferSuccess(makeOffer: Response<MakeOfferResponse>) {
        pd.dismiss()
        makeofferDialog.dismiss()
        utility!!.relative_snackbar(
            parent_workinvitation!!,
            makeOffer.body()?.getMessage(),
            getString(R.string.close_up)
        )
    }
}