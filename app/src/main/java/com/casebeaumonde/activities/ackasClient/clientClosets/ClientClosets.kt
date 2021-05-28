package com.casebeaumonde.activities.ackasClient.clientClosets

import android.app.Dialog
import android.app.ProgressDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.casebeaumonde.Controller.Controller
import com.casebeaumonde.R
import com.casebeaumonde.activities.ackasClient.clientClosets.adapter.ClientClosetAdapter
import com.casebeaumonde.activities.myclosets.response.MyClosetsResponse
import com.casebeaumonde.constants.BaseClass
import com.casebeaumonde.constants.Constants
import com.casebeaumonde.utilities.Utility
import kotlinx.android.synthetic.main.activity_client_closets.*
import retrofit2.Response

class ClientClosets : BaseClass(), Controller.MyClosetsAPI {

    private lateinit var controller: Controller
    private lateinit var utility: Utility
    private lateinit var pd: ProgressDialog
    private lateinit var client_closets_recyler: RecyclerView
    private lateinit var search_ET : EditText
    private lateinit var response: ArrayList<MyClosetsResponse.Data.Closet>
    private lateinit var closets: ArrayList<MyClosetsResponse.Data.Closet>
    private lateinit var userID: String
    private lateinit var exitDialog : Dialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_client_closets)
        findIds()
        controller = Controller()
        controller.Controller(this)
        userID = intent.getStringExtra("userID")!!
        if (utility.isConnectingToInternet(this)) {
            pd.show()
            pd.setContentView(R.layout.loading)
            controller.GetMyClosets(
                "Bearer " + getStringVal(Constants.TOKEN),
                userID
            )
        } else {
            utility!!.relative_snackbar(
                parent_clientclosets,
                R.string.nointernet.toString(),
                getString(R.string.close_up)
            )
        }

        listeners()
    }

    override fun onBackPressed() {
        exitDialog = Dialog(this)
        exitDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        exitDialog.setCancelable(false)
        exitDialog.setContentView(R.layout.logout_dialog)
        val window = exitDialog.window
        window?.setLayout(
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )

        var logout_tv = exitDialog.findViewById<TextView>(R.id.logout_tv)
        var logout_yes = exitDialog.findViewById<Button>(R.id.logout_yes)
        var logout_no = exitDialog.findViewById<Button>(R.id.logout_no)
        logout_tv.text = "Are you sure to return back to normal user"
        logout_yes.setOnClickListener {
            super.onBackPressed()
        }

        logout_no.setOnClickListener {
            exitDialog.dismiss()
        }
        exitDialog.show()
    }

    private fun listeners() {
        search_ET!!.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (count > 0) {
                    searchByTitle(s.toString())
                } else {
                    setFullData(closets)
                }
            }

            override fun afterTextChanged(s: Editable) {
                if (s.isNotEmpty()) {

                    searchByTitle(s.toString())
                } else {
                    client_closets_recyler.visibility = View.VISIBLE
                    setFullData(closets)
                }
            }

        })

        search_ET.setOnKeyListener(object : View.OnKeyListener {
            override fun onKey(v: View?, keyCode: Int, event: KeyEvent?): Boolean {
                //You can identify which key pressed buy checking keyCode value with KeyEvent.KEYCODE_
                if (keyCode == KeyEvent.KEYCODE_DEL) {
                    //this is for backspace
                    search_ET.setText("")
                }
                return false
            }
        })
    }

    private fun findIds() {
        utility = Utility()
        pd = ProgressDialog(this)
        pd!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        pd!!.window!!.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        pd!!.isIndeterminate = true
        pd!!.setCancelable(false)
        client_closets_recyler = findViewById(R.id.client_closets_recyler)
        search_ET = findViewById(R.id.search_ET)
    }

    fun searchByTitle(s: String) {
        response = ArrayList()
        if (closets.size > 0) {
            for (i in closets!!.indices) {
                val closetModel = closets!![i]
                if (closetModel.title!!.toLowerCase().contains(s.toLowerCase()))
                    response!!.add(closetModel)

                if (response.size > 0) {
                    client_closets_recyler.layoutManager =
                        LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
                    //closets =  response
                    val adapter = ClientClosetAdapter(
                        this, response!!, userID,
                        getStringVal(Constants.USERID).toString()
                    )
                    client_closets_recyler.adapter = adapter
                }
            }
            if (response.size == 0) {
                client_closets_recyler.visibility = View.GONE
            }
        }
    }

    private fun setFullData(closets: ArrayList<MyClosetsResponse.Data.Closet>) {
        response =
            closets
        client_closets_recyler.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        val adapter = ClientClosetAdapter(
            this, closets!!, userID,
            getStringVal(Constants.USERID).toString()
        )
        client_closets_recyler.adapter = adapter
    }

    override fun onResume() {

        super.onResume()
    }

    override fun onMyClosetsSuccess(myClosetsResponse: Response<MyClosetsResponse>) {
        pd.dismiss()

        if (myClosetsResponse.isSuccessful) {
            closets =
                myClosetsResponse.body()
                    ?.getData()?.closet as ArrayList<MyClosetsResponse.Data.Closet>
            setFullData(closets)
        } else {
            utility!!.relative_snackbar(
                parent_clientclosets,
                myClosetsResponse.message(),
                getString(R.string.close_up)
            )
        }
    }

    override fun error(error: String?) {
        pd.dismiss()
        utility!!.relative_snackbar(
            parent_clientclosets,
            error,
            getString(R.string.close_up)
        )
    }

}