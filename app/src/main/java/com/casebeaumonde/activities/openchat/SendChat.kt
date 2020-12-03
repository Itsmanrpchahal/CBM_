package com.casebeaumonde.activities.openchat

import android.app.ProgressDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.casebeaumonde.Controller.Controller
import com.casebeaumonde.R
import com.casebeaumonde.activities.openchat.adapter.GetChatAdapter
import com.casebeaumonde.activities.openchat.response.GetChatResponse
import com.casebeaumonde.activities.openchat.response.SendChatResponse
import com.casebeaumonde.constants.BaseClass
import com.casebeaumonde.constants.Constants
import com.casebeaumonde.utilities.Utility
import com.pusher.client.Pusher
import com.pusher.client.PusherOptions
import com.pusher.client.channel.Channel
import com.pusher.client.channel.ChannelEventListener
import com.pusher.client.channel.PusherEvent
import com.pusher.client.channel.SubscriptionEventListener
import com.pusher.client.connection.ConnectionEventListener
import com.pusher.client.connection.ConnectionState
import com.pusher.client.connection.ConnectionStateChange
import kotlinx.android.synthetic.main.activity_send_chat.*
import retrofit2.Response
import java.util.*


class SendChat : BaseClass(), Controller.SendUserChatAPI, Controller.GetChatAPI {

    private lateinit var back: ImageButton
    private lateinit var chatname: TextView
    private lateinit var chat_recycler: RecyclerView
    private lateinit var sendmesg_et: EditText
    private lateinit var sendmesg_bt: ImageButton
    private lateinit var utility: Utility
    private lateinit var pd: ProgressDialog
    private lateinit var controller: Controller
    private lateinit var id: String
    private lateinit var name: String
    private lateinit var chatdata: ArrayList<GetChatResponse.Data.Message>
    private lateinit var pusher: Pusher

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_send_chat)

        findIds()
        controller = Controller()
        controller.Controller(this, this)
        id = intent?.getStringExtra("id").toString()
        name = intent?.getStringExtra("chatname").toString()
        chatname.setText(name)
        if (utility.isConnectingToInternet(this)) {
            pd.show()
            pd.setContentView(R.layout.loading)
            controller.GetChat("Bearer " + getStringVal(Constants.TOKEN), id)
        } else {
            utility!!.relative_snackbar(
                parent_sendchat!!,
                getString(R.string.nointernet),
                getString(R.string.close_up)
            )
        }
        setPusher()
//             setupPusher()
        listeners()
    }

    private fun setPusher() {
        val options = PusherOptions()
        options.setCluster("us2")

        val pusher = Pusher("27d208f3a07f7bb15e7e", options)
        pusher.connect(object : ConnectionEventListener {
            override fun onConnectionStateChange(change: ConnectionStateChange) {
                println(
                    "State changed to " + change.currentState +
                            " from " + change.previousState
                )
            }

            override fun onError(message: String, code: String, e: java.lang.Exception) {
                println("There was a problem connecting!")
            }
        }, ConnectionState.ALL)

        // Subscribe to a channel
        // Subscribe to a channel
        val channel: Channel = pusher.subscribe("chat")
        Toast.makeText(this, "" + channel.name, Toast.LENGTH_SHORT).show()

        // Bind to listen for events called "my-event" sent to "my-channel"
        channel.bind("my-event") { event ->
            Log.i("Pusher", "Received event with data: ${event.eventName}")

        }

// Disconnect from the service
        pusher.disconnect()

// Reconnect, with all channel subscriptions and event bindings automatically recreated
        pusher.connect()
    }

    private fun setupPusher() {
        val options = PusherOptions()
        options.setCluster("us2");

        pusher = Pusher("27d208f3a07f7bb15e7e", options)

        pusher.connect(object : ConnectionEventListener {
            override fun onConnectionStateChange(change: ConnectionStateChange) {
                Log.i(
                    "Pusher",
                    "State changed from ${change.previousState} to ${change.currentState}"
                )

//                val pusher = com.pusher.rest.Pusher(
//                    "1112339",
//                    "27d208f3a07f7bb15e7e",
//                    "7a06f986f6da3b8d6f5d"
//                )
//                pusher.setCluster("us2")

//                pusher.trigger(
//                    "chat",
//                    "my-event",
//                    ""
//                )
            }

            override fun onError(
                message: String,
                code: String,
                e: Exception
            ) {
                Log.i(
                    "Pusher",
                    "There was a problem connecting! code ($code), message ($message), exception($e)"
                )
            }
        }, ConnectionState.ALL)

//        var channel = pusher.subscribe("chat")
//        Toast.makeText(this, "" + channel.toString(), Toast.LENGTH_SHORT).show()

        pusher.subscribe("chat").bind("event-name", object : ChannelEventListener {
            override fun onSubscriptionSucceeded(channelName: String) {
                Log.d("PUSH", "onSubscriptionSucceeded: ")
            }

            override fun onEvent(event: PusherEvent) {
                Log.d("PUSH", "onEvent: data " + event.data + " user id " + event.userId)
            }
        })

        // Disconnect from the service
        pusher.disconnect();
        // Reconnect, with all channel subscriptions and event bindings automatically recreated
        pusher.connect()
    }

    private fun listeners() {

        back.setOnClickListener { onBackPressed() }

        sendmesg_bt.setOnClickListener {
            if (!sendmesg_et.text.toString().equals("")) {
                if (utility.isConnectingToInternet(this)) {
//                    pd.show()
//                    pd.setContentView(R.layout.loading)
                    controller.SendChat(
                        "Bearer " + getStringVal(Constants.TOKEN),
                        id,
                        sendmesg_et.text.toString()
                    )
                } else {
                    utility!!.relative_snackbar(
                        parent_sendchat!!,
                        getString(R.string.nointernet),
                        getString(R.string.close_up)
                    )
                }
            }
        }
    }

    private fun findIds() {
        utility = Utility()
        pd = ProgressDialog(this)
        pd!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        pd!!.window!!.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        pd!!.isIndeterminate = true
        pd!!.setCancelable(false)
        back = findViewById(R.id.back)
        chatname = findViewById(R.id.chatname)
        chat_recycler = findViewById(R.id.chat_recycler)
        sendmesg_et = findViewById(R.id.sendmesg_et)
        sendmesg_bt = findViewById(R.id.sendmesg_bt)
    }

    override fun onGetChatSuccess(getCHat: Response<GetChatResponse>) {
        pd.dismiss()
        chatdata = getCHat.body()?.data?.messages as ArrayList<GetChatResponse.Data.Message>
        setFullData(chatdata)
    }

    private fun setFullData(chatdata: ArrayList<GetChatResponse.Data.Message>) {

        var layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        chat_recycler.layoutManager = layoutManager

        val adapter = GetChatAdapter(
            this!!, id, chatdata
        )
        chat_recycler.adapter = adapter
        chat_recycler.scrollToPosition(chatdata.size - 1)
    }

    override fun onGetUserChatSuccess(senduserchat: Response<SendChatResponse>) {
        pd.dismiss()
        sendmesg_et.setText("")

        controller.GetChat("Bearer " + getStringVal(Constants.TOKEN), id)

    }

    override fun error(error: String?) {
        pd.dismiss()
        utility!!.relative_snackbar(
            parent_sendchat!!,
            error,
            getString(R.string.close_up)
        )
    }
}
