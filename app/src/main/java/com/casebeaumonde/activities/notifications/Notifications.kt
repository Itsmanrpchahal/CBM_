package com.casebeaumonde.activities.notifications

import android.app.ProgressDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.ImageButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.casebeaumonde.Controller.Controller
import com.casebeaumonde.R
import com.casebeaumonde.constants.BaseClass
import com.casebeaumonde.constants.Constants
import com.casebeaumonde.activities.notifications.IF.NotificationIF
import com.casebeaumonde.activities.notifications.adpater.NotificationAdapter
import com.casebeaumonde.activities.notifications.response.NotificationsResponse
import com.casebeaumonde.activities.notifications.response.RemoveNotificationResponse
import com.casebeaumonde.utilities.Utility
import kotlinx.android.synthetic.main.activity_notifications.*
import retrofit2.Response
import java.util.*
import kotlin.collections.ArrayList


class Notifications : BaseClass(), Controller.NotificationAPI,NotificationIF ,Controller.RemoveNotificationAPI{

    private lateinit var notification_recyler: RecyclerView
    private lateinit var notification_back: ImageButton
    private var notifiactionlist = ArrayList<String>()
    private lateinit var utility: Utility
    private lateinit var pd: ProgressDialog
    lateinit var controller: Controller
    private var timer: Timer? = null
    private var notifications: List<NotificationsResponse>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notifications)
        controller = Controller()
        controller.Controller(this,this)
        notificationIF = this
        findIds()
        listeners()
        NotificationCall()
    }

    private fun NotificationCall() {
        if (utility.isConnectingToInternet(this)) {
            pd.show()
            pd.setContentView(R.layout.loading)

            val handler = Handler()
            timer = Timer()
            val doTask: TimerTask = object : TimerTask() {
                override fun run() {
                    handler.post {
                        try {
                            Log.d("refresh", "done")
                            runOnUiThread {

                            }
                        } catch (e: Exception) {

                        }
                    }
                }
            }
            timer!!.schedule(doTask, 0, 500)
            notificationCall()


        }
    }

    private fun notificationCall() {
        if (utility.isConnectingToInternet(this)) {
            pd.show()
            pd.setContentView(R.layout.loading)
            controller.setNotificationAPI(
                "Bearer " + getStringVal(Constants.TOKEN),
                getStringVal(Constants.USERID)
            )
        }else{
            utility!!.relative_snackbar(parent_notifications!!, getString(R.string.nointernet), getString(R.string.close_up))
        }
    }

    private fun listeners() {
        notification_back.setOnClickListener {
            onBackPressed()
        }
    }

    private fun findIds() {
        utility = Utility()
        pd = ProgressDialog(this)
        pd!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        pd!!.window!!.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        pd!!.isIndeterminate = true
        pd!!.setCancelable(false)
        notification_recyler = findViewById(R.id.notification_recyler)
        notification_back = findViewById(R.id.notification_back)
    }

    override fun onSucess(notificationsResponseResponse: Response<NotificationsResponse>) {
        pd.dismiss()
        //add layout manager
        notification_recyler.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        val adapter = NotificationAdapter(this ,notificationsResponseResponse.body()?.data?.notification!!)
        notification_recyler.adapter = adapter
    }

    override fun onRemoveNotification(removeNotification: Response<RemoveNotificationResponse>) {
        if (removeNotification.isSuccessful)
        {
            notificationCall()
        }
    }


    override fun error(error: String?) {
        pd.dismiss()
        utility!!.relative_snackbar(parent_notifications!!, error, getString(R.string.close_up))
    }

    companion object{
        var notificationIF : NotificationIF? = null
    }

    override fun getID(id: String?) {
        hideKeyboard()
        if (utility.isConnectingToInternet(this)) {
            pd.show()
            pd.setContentView(R.layout.loading)
            Log.d("notifyID",id)
            controller.RemoveNotification("Bearer "+getStringVal(Constants.TOKEN),id)
        }else{
            utility!!.relative_snackbar(parent_notifications!!, getString(R.string.nointernet), getString(R.string.close_up))
        }
    }

    private fun hideKeyboard() {
        try {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
        }catch (e : java.lang.Exception)
        {

        }

    }
}




