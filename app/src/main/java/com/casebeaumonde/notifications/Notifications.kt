package com.casebeaumonde.notifications

import android.app.ProgressDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.WindowManager
import android.widget.ImageButton
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.casebeaumonde.Controller.Controller
import com.casebeaumonde.R
import com.casebeaumonde.constants.BaseClass
import com.casebeaumonde.constants.Constants
import com.casebeaumonde.notifications.adpater.NotificationAdapter
import com.casebeaumonde.notifications.response.NotificationsResponse
import com.casebeaumonde.utilities.Utility
import com.google.gson.JsonObject
import org.json.JSONObject
import retrofit2.Response
import java.util.*
import kotlin.collections.ArrayList


class Notifications : BaseClass(), Controller.NotificationAPI {

    private lateinit var notification_recyler: RecyclerView
    private lateinit var notification_back: ImageButton
    private val notifiactionlist = ArrayList<String>()
    private lateinit var utility: Utility
    private lateinit var pd: ProgressDialog
    lateinit var controller: Controller
    private var timer: Timer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notifications)
        controller = Controller()
        controller.Controller(this)
        findIds()
        setAdapter()
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
            controller.setNotificationAPI(
                "Bearer " + getStringVal(Constants.TOKEN),
                getStringVal(Constants.USERID)
            )


        }
    }

    private fun listeners() {
        notification_back.setOnClickListener {
            onBackPressed()
        }
    }

    private fun setAdapter() {
        utility = Utility()
        pd = ProgressDialog(this)
        pd!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        pd!!.window!!.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        pd!!.isIndeterminate = true
        pd!!.setCancelable(false)

        //add layout manager
        notification_recyler.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        val adapter = NotificationAdapter(notifiactionlist)
        notification_recyler.adapter = adapter
    }

    private fun findIds() {
        notification_recyler = findViewById(R.id.notification_recyler)
        notification_back = findViewById(R.id.notification_back)
    }

    override fun onSucess(notificationsResponseResponse: Response<NotificationsResponse>) {
        pd.dismiss()
        Log.d("responseNotify",""+ notificationsResponseResponse.body()!!.data.notification.get(0).notifiableId)
    }

    override fun error(error: String?) {
        pd.dismiss()
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
    }
}