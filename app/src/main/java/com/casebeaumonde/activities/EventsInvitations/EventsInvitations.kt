package com.casebeaumonde.activities.EventsInvitations

import android.app.ProgressDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.widget.ImageButton
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.casebeaumonde.Controller.Controller
import com.casebeaumonde.R
import com.casebeaumonde.activities.EventsInvitations.adapter.UserInvitationsAdapter
import com.casebeaumonde.activities.EventsInvitations.response.UserInvitationsResponse
import com.casebeaumonde.constants.BaseClass
import com.casebeaumonde.constants.Constants
import com.casebeaumonde.utilities.Utility
import kotlinx.android.synthetic.main.activity_events_invitations.*
import retrofit2.Response
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class EventsInvitations : BaseClass(),Controller.UserInviatationsAPI {

    private lateinit var back : ImageButton
    private lateinit var controller: Controller
    private lateinit var utility: Utility
    private lateinit var pd: ProgressDialog
    private lateinit var userID : String
    private lateinit var events_recyler : RecyclerView
    private lateinit var events : ArrayList<UserInvitationsResponse.Data.Event>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_events_invitations)
        controller = Controller()
        controller.Controller(this)
        userID = intent.getStringExtra("userID")!!
        findIDs()

        if (utility.isConnectingToInternet(this)) {
            pd.show()
            pd.setContentView(R.layout.loading)
            controller.UserInvitation("Bearer " + getStringVal(Constants.TOKEN), userID)
        } else {
            utility.relative_snackbar(
                parent_events!!,
                "No Internet Connectivity",
                getString(R.string.close_up)
            )
        }

        listeners()
    }

    private fun listeners() {
        back.setOnClickListener { onBackPressed() }
    }

    private fun findIDs() {
        utility = Utility()
        pd = ProgressDialog(this)
        pd!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        pd!!.window!!.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        pd!!.isIndeterminate = true
        pd!!.setCancelable(false)
        back = findViewById(R.id.back)
        events_recyler = findViewById(R.id.events_recyler)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onUserInvitationSuccess(userInvitations: Response<UserInvitationsResponse>) {
       pd.dismiss()
       
        events = userInvitations.body()?.getData()?.events!! as ArrayList<UserInvitationsResponse.Data.Event>

        val c = Calendar.getInstance()

        val formatter1: DateFormat = SimpleDateFormat("dd/MM/yyyy HH:mm")
        var date1: Date? = null
        try {
            date1 = formatter1.parse(formatter1.format(c.time))
            Log.d("TodayDate", java.lang.String.valueOf(date1.time))
            val  formattedDate = java.lang.String.valueOf(date1.time)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        setFullData(events,java.lang.String.valueOf(date1?.time))



    }


    private fun setFullData(events: ArrayList<UserInvitationsResponse.Data.Event>, valueOf: String) {

        events_recyler.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        val adapter = UserInvitationsAdapter(
            this, events,valueOf
        )
        events_recyler.adapter = adapter

    }

    override fun error(error: String?) {
        pd.dismiss()
        utility.relative_snackbar(
            parent_events!!,
            error,
            getString(R.string.close_up)
        )
    }
}