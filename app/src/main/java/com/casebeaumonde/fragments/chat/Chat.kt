package com.casebeaumonde.fragments.chat

import android.app.ProgressDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.casebeaumonde.Controller.Controller
import com.casebeaumonde.R
import com.casebeaumonde.constants.BaseFrag
import com.casebeaumonde.constants.Constants
import com.casebeaumonde.fragments.allClosets.adapter.AllClosetsAdapter
import com.casebeaumonde.fragments.chat.adapter.AllChatUsersAdapter
import com.casebeaumonde.utilities.Utility
import com.pusher.client.Pusher
import com.pusher.client.PusherOptions
import com.pusher.client.connection.ConnectionEventListener
import com.pusher.client.connection.ConnectionState
import com.pusher.client.connection.ConnectionStateChange
import kotlinx.android.synthetic.main.fragment_chat.*
import kotlinx.android.synthetic.main.fragment_closets.*
import retrofit2.Response

class Chat : BaseFrag() ,Controller.GetChatUsersAPI {

    private lateinit var chatUsersRecycler : RecyclerView
    private lateinit var utility: Utility
    private lateinit var pd: ProgressDialog
    private lateinit var controller: Controller
    private lateinit var response : ArrayList<GetChatUsers.Data.User>
    private lateinit var filterData : ArrayList<GetChatUsers.Data.User>
    private lateinit var search_ET: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View
        view = inflater.inflate(R.layout.fragment_chat, container, false)

        findIds(view)
        controller = Controller()
        controller.Controller(this)
        if (utility.isConnectingToInternet(context)) {
            pd.show()
            pd.setContentView(R.layout.loading)
            controller.GetChatUsers("Bearer " + getStringVal(Constants.TOKEN))
        } else {
            utility!!.relative_snackbar(
                parent_chat!!,
                getString(R.string.nointernet),
                getString(R.string.close_up)
            )
        }

        listeners(view)

        return view
    }

    private fun listeners(view: View?) {
        search_ET!!.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (count > 0) {
                    searchByTitle(s.toString())
                } else {
                    setFullData(response)
                }
            }

            override fun afterTextChanged(s: Editable) {
                if (s.isNotEmpty()) {

                    searchByTitle(s.toString())
                } else {
                    allchat_recycler.visibility = View.VISIBLE
                    setFullData(response)
                }
            }

        })
    }

    private fun findIds(view: View?) {
        utility = Utility()
        pd = ProgressDialog(context)
        pd!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        pd!!.window!!.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        pd!!.isIndeterminate = true
        pd!!.setCancelable(false)
        chatUsersRecycler = view?.findViewById(R.id.allchat_recycler)!!
        search_ET = view.findViewById(R.id.search_ET)
    }

    override fun onGetChatUsersSuccess(chatUsers: Response<GetChatUsers>) {
        pd.dismiss()
        response = chatUsers.body()?.data?.users as ArrayList<GetChatUsers.Data.User>
        setFullData(response)
    }

    private fun setFullData(response: ArrayList<GetChatUsers.Data.User>) {
        chatUsersRecycler.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        val adapter = AllChatUsersAdapter(context!!, response!!
        )
        chatUsersRecycler.adapter = adapter
    }

    private fun searchByTitle(s: String?) {
        filterData = ArrayList()
        if (response.size > 0) {
            for (i in response!!.indices) {
                val Model = response!![i]
                if (Model.firstname!!.toLowerCase().contains(s!!.toLowerCase())|| Model.lastname!!.toLowerCase().contains(s!!.toLowerCase()))
                    filterData!!.add(Model)

                if (filterData.size > 0) {
                    allchat_recycler.layoutManager =
                        LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                    val adapter = AllChatUsersAdapter(context!!, filterData!!)
                    allchat_recycler.adapter = adapter
                }
            }

            if (filterData.size == 0) {
                allchat_recycler.visibility = View.GONE
            }
        }
    }

    override fun error(error: String?) {
        pd.dismiss()
        utility!!.relative_snackbar(
            parent_chat!!,
            error,
            getString(R.string.close_up)
        )
    }
}