package com.casebeaumonde.activities.openchat

import android.app.ProgressDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.WindowManager
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.casebeaumonde.Controller.Controller
import com.casebeaumonde.R
import com.casebeaumonde.activities.openchat.adapter.GetChatAdapter
import com.casebeaumonde.activities.openchat.response.BlockResponse
import com.casebeaumonde.activities.openchat.response.GetChatResponse
import com.casebeaumonde.activities.openchat.response.SendChatResponse
import com.casebeaumonde.constants.BaseClass
import com.casebeaumonde.constants.Constants
import com.casebeaumonde.utilities.Utility
import com.google.gson.Gson
import com.pusher.client.Pusher
import com.pusher.client.PusherOptions
import com.pusher.client.channel.Channel
import com.pusher.client.channel.ChannelEventListener
import com.pusher.client.channel.PrivateChannelEventListener
import com.pusher.client.channel.SubscriptionEventListener
import com.pusher.client.connection.ConnectionEventListener
import com.pusher.client.connection.ConnectionState
import com.pusher.client.connection.ConnectionStateChange
import com.pusher.client.util.HttpAuthorizer
import kotlinx.android.synthetic.main.activity_send_chat.*
import org.json.JSONObject
import retrofit2.Response
import java.util.*


class SendChat : BaseClass(), Controller.SendUserChatAPI, Controller.GetChatAPI,
    Controller.BlockUserAPI {

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
    private lateinit var blockbt: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_send_chat)

        findIds()
        controller = Controller()
        controller.Controller(this, this, this)
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
//        setPusher()
        setupPusher()
        listeners()
    }

    private fun setPusher() {
        val options = PusherOptions()
        options.setCluster("us2")

        val pusher = Pusher("27d208f3a07f7bb15e7e", options)
        val channel: Channel = pusher.subscribe("chat")
        val eventListener =
            SubscriptionEventListener { channel, event, data ->
                runOnUiThread {
                    println("Received event with data: $data")
                    val gson = Gson()

                }
            }

        channel.bind("client-NewMessage", eventListener);

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

        //  Toast.makeText(this, "" + channel.name, Toast.LENGTH_SHORT).show()

//        channel.bind("client-NewMessage") { channelName, eventName, data ->
//            val jsonObject = JSONObject(data)
//            Toast.makeText(this, "" + jsonObject.toString(), Toast.LENGTH_SHORT).show()
//        }

// Disconnect from the service
        pusher.disconnect()

// Reconnect, with all channel subscriptions and event bindings automatically recreated
        pusher.connect()

    }

    private fun subscribeToChannel() {
        val authorizer = HttpAuthorizer("http://10.0.2.2:5000/pusher/auth/private")
        val options = PusherOptions().setAuthorizer(authorizer)
        options.setCluster("us2")

        val pusher = Pusher("27d208f3a07f7bb15e7e", options)
        pusher.connect()


        pusher.subscribePrivate("client-NewMessage", object : PrivateChannelEventListener {
            override fun onEvent(channelName: String?, eventName: String?, data: String?) {

                val jsonObject = JSONObject(data)
            }

            override fun onAuthenticationFailure(p0: String?, p1: java.lang.Exception?) {
                Log.e("ChatRoom", p1!!.localizedMessage)
            }

            override fun onSubscriptionSucceeded(p0: String?) {
                Log.i("ChatRoom", "Successful subscription")
            }

        }, "client-NewMessage")

    }

    private fun setupPusher1() {
        val options = PusherOptions()
        options.setCluster("us2")

        val pusher = Pusher("27d208f3a07f7bb15e7e", options)
        val channel = pusher.subscribe("chat")

        channel.bind("client-NewMessage") { channelName, eventName, data ->
            val jsonObject = JSONObject(data)
            Toast.makeText(this, "" + jsonObject, Toast.LENGTH_SHORT).show()
        }

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

        pusher.subscribe("Chat", object : ChannelEventListener {
            override fun onEvent(channelName: String?, eventName: String?, data: String?) {
                Log.d(
                    "PUSH",
                    "onEvent: data " + channelName.toString() + " user id " + data.toString()
                )
                Log.d("test","TEST")
            }

            override fun onSubscriptionSucceeded(channelName: String?) {
                Log.d("PUSH", "onSubscriptionSucceeded: " + channelName)
            }
        }, "client-NewMessage")

        pusher.disconnect()
        pusher.connect()

    }

    private fun listeners() {

        back.setOnClickListener { onBackPressed() }

        sendmesg_bt.setOnClickListener {
            if (!sendmesg_et.text.toString().equals("")) {
                if (utility.isConnectingToInternet(this)) {
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

        blockbt.setOnClickListener {
            if (utility.isConnectingToInternet(this)) {
                controller.BlockUser(
                    "Bearer " + getStringVal(Constants.TOKEN),
                    id
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
        blockbt = findViewById(R.id.blockbt)
    }

    override fun onGetChatSuccess(getCHat: Response<GetChatResponse>) {
        pd.dismiss()
        chatdata = getCHat.body()?.data?.messages as ArrayList<GetChatResponse.Data.Message>
        setFullData(chatdata)
        if (getCHat.body()?.data?.blocked == 0) {
            blockbt.setText("Block")
        } else {
            blockbt.setText("UnBlock")
        }
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

    override fun onBlockUserSuccess(blockUser: Response<BlockResponse>) {
        pd.dismiss()
        if (blockUser.isSuccessful) {
            if (blockUser.body()?.message.equals("User Blocked successfully")) {
                blockbt.setText("UnBlock")
            } else {
                blockbt.setText("Block")
            }
            utility!!.relative_snackbar(
                parent_sendchat!!,
                blockUser.body()?.message,
                getString(R.string.close_up)
            )

        }

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
