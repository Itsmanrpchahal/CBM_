package com.casebeaumonde.activities.openchat

import android.app.ProgressDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.wifi.p2p.WifiP2pManager
import android.os.Bundle
import android.util.Log
import android.util.Log.println
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
import com.pusher.client.Pusher
import com.pusher.client.PusherOptions
import com.pusher.client.channel.Channel
import com.pusher.client.channel.ChannelEventListener
import com.pusher.client.channel.SubscriptionEventListener
import com.pusher.client.connection.ConnectionEventListener
import com.pusher.client.connection.ConnectionState
import com.pusher.client.connection.ConnectionStateChange
import kotlinx.android.synthetic.main.activity_send_chat.*
import org.apache.http.client.methods.RequestBuilder.put
import retrofit2.Response
import java.sql.DriverManager.println
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
        setupPusher()
        listeners()
    }

    private fun setupPusher() {
        val options = PusherOptions()
        options.setCluster("us2")

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
                    "onEvent: data " + data
                )
                Log.d("test", "TESTnbb")
            }

            override fun onSubscriptionSucceeded(channelName: String?) {
                Log.d("PUSH", "onSubscriptionSucceeded: " + channelName)
            }
        }, "NewMessage")

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
