package com.casebeaumonde.activities.myGigs

import android.app.Dialog
import android.app.ProgressDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.casebeaumonde.Controller.Controller
import com.casebeaumonde.R
import com.casebeaumonde.activities.myGigs.IF.getGigId
import com.casebeaumonde.activities.myGigs.adapter.MyGigsAdapter
import com.casebeaumonde.activities.myGigs.response.MyGigsResponse
import com.casebeaumonde.constants.BaseClass
import com.casebeaumonde.constants.Constants
import com.casebeaumonde.fragments.HireExpert.response.HireAnExpertResponse
import com.casebeaumonde.fragments.HireExpert.response.SendInvitationResponse
import com.casebeaumonde.utilities.Utility
import kotlinx.android.synthetic.main.activity_my_gigs.*
import retrofit2.Response

class MyGigs : BaseClass(), Controller.GetUserGigsAPI, Controller.SendInvitationAPI, getGigId {

    lateinit var mygigis_back: ImageView
    lateinit var mygigigs_rv: RecyclerView
    private lateinit var utility: Utility
    private lateinit var pd: ProgressDialog
    lateinit var controller: Controller
    lateinit var role: String
    private lateinit var userID: String
    lateinit var add_gig: ImageButton
    private lateinit var response :ArrayList<MyGigsResponse.Data.User.Gig>
    private lateinit var sendInvitationDialog: Dialog
    private lateinit var username :String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_gigs)
        controller = Controller()
        controller.Controller(this, this)
        userID = intent.getStringExtra("userID")
        getGigId = this
        findIds()
        MygigsCall()
        listeners()
    }

    private fun MygigsCall() {
        if (utility.isConnectingToInternet(this)) {
            pd.show()
            pd.setContentView(R.layout.loading)
            controller.GetUserGigs(
                "Bearer " + getStringVal(Constants.TOKEN),
                userID
            )
        } else {
            utility!!.relative_snackbar(
                parent_gigs!!,
                getString(R.string.nointernet),
                getString(R.string.close_up)
            )
        }
    }

    companion object {
        var getGigId: getGigId? = null
    }

    private fun listeners() {
        mygigis_back.setOnClickListener { onBackPressed() }

        add_gig.setOnClickListener {
            val dialog = Dialog(this!!)
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog.setContentView(R.layout.add_gig)
            val window = dialog.window
            window?.setLayout(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT
            )
            dialog.show()
        }
    }

    private fun findIds() {
        utility = Utility()
        pd = ProgressDialog(this)
        pd!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        pd!!.window!!.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        pd!!.isIndeterminate = true
        pd!!.setCancelable(false)
        mygigis_back = findViewById(R.id.mygigis_back)
        mygigigs_rv = findViewById(R.id.mygigigs_rv)
        add_gig = findViewById(R.id.add_gig)
        role = intent.getStringExtra("role")
        if (userID != getStringVal(Constants.USERID)) {
            add_gig.visibility = View.GONE
        }
    }

    override fun onMyGigsSuccess(myGigsResponse: Response<MyGigsResponse>) {
        pd.dismiss()
        if (myGigsResponse.isSuccessful) {

            response = myGigsResponse.body()?.data?.user?.gigs as ArrayList<MyGigsResponse.Data.User.Gig>
            username = myGigsResponse.body()?.data?.user?.firstname.toString()
                //add layout manager
            mygigigs_rv.layoutManager =
                LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
            val adapter = MyGigsAdapter(
                this, myGigsResponse.body()?.data?.user?.gigs!!, userID,
                getStringVal(Constants.USERID).toString()
            )
            mygigigs_rv.adapter = adapter

        } else {
            utility!!.relative_snackbar(
                parent_gigs!!,
                myGigsResponse.message(),
                getString(R.string.close_up)
            )
        }
    }

    override fun onSendInvitationSuccess(sendInvitationResponse: Response<SendInvitationResponse>) {
        pd.dismiss()
        sendInvitationDialog.dismiss()
        if (sendInvitationResponse.isSuccessful) {

            utility!!.relative_snackbar(
                parent_gigs,
                "Invitation Sent",
                getString(R.string.close_up)
            )

        }
    }

    override fun error(error: String?) {
        pd.dismiss()
        utility!!.relative_snackbar(parent_gigs!!, error, getString(R.string.close_up))
    }

    override fun getGigID(id: String, position: Int) {
        sendInvitationDialog = Dialog(this)
        sendInvitationDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        sendInvitationDialog.setContentView(R.layout.sendinvitaion)
        val window = sendInvitationDialog.window
        window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
        sendInvitationDialog.show()
        val sendinviationtv: TextView
        val sendinviation_messageet: EditText
        val sendinviation_budget: EditText
        val sendinviation_sendInvitation: Button
        val sendinviation_cancel: Button
        sendinviationtv = sendInvitationDialog.findViewById(R.id.sendinviationtv)
        sendinviation_messageet = sendInvitationDialog.findViewById(R.id.sendinviation_messageet)
        sendinviation_budget = sendInvitationDialog.findViewById(R.id.sendinviation_budget)
        sendinviation_sendInvitation =
            sendInvitationDialog.findViewById(R.id.sendinviation_sendInvitation)
        sendinviation_cancel = sendInvitationDialog.findViewById(R.id.sendinviation_cancel)
        sendinviationtv.text = "Send invitation to " + username


        sendinviation_cancel.setOnClickListener {
            sendInvitationDialog.dismiss()
        }

        sendinviation_sendInvitation.setOnClickListener {
            when {
                sendinviation_messageet.text.isEmpty() -> {
                    sendinviation_messageet.error = "Enter Message"
                    sendinviation_messageet.requestFocus()
                }

                sendinviation_budget.text.isEmpty() -> {
                    sendinviation_budget.error = "Enter Budget"
                    sendinviation_budget.requestFocus()
                }
                else -> {
                    var des = sendinviation_messageet.text.toString()
                    var budget = sendinviation_budget.text.toString()
                    pd.show()
                    controller.SendInvitation(
                        "Bearer " + getStringVal(Constants.TOKEN),
                        userID,
                        des,
                        response.get(position).id.toString(),
                        budget
                    )
                }

            }
        }
    }
}