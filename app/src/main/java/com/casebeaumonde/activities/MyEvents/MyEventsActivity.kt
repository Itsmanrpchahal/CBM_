package com.casebeaumonde.activities.MyEvents

import android.app.Activity
import android.app.Dialog
import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.casebeaumonde.Controller.Controller
import com.casebeaumonde.R
import com.casebeaumonde.activities.MyEventDetailScreen.IF.EventID_IF
import com.casebeaumonde.activities.MyEvents.Response.MyEventsResponse
import com.casebeaumonde.activities.MyEvents.adapter.MyEventsAdapter
import com.casebeaumonde.activities.addnewevent.AddNewEventActivity
import com.casebeaumonde.constants.BaseClass
import com.casebeaumonde.constants.Constants
import com.casebeaumonde.utilities.Utility
import com.github.dhaval2404.imagepicker.ImagePicker
import kotlinx.android.synthetic.main.activity_add_item_to_closet.*
import kotlinx.android.synthetic.main.activity_add_item_to_closet.parent_additemtocloset
import kotlinx.android.synthetic.main.activity_add_new_event.*
import kotlinx.android.synthetic.main.activity_my_events.*
import okhttp3.MultipartBody
import retrofit2.Response
import java.io.File

class MyEventsActivity : BaseClass(), Controller.MyEventsAPI,EventID_IF {

    private lateinit var utility: Utility
    private lateinit var pd: ProgressDialog
    private lateinit var controller: Controller
    private lateinit var event_recycler: RecyclerView
    private lateinit var myclosets_back: ImageButton
    private lateinit var myEvents : ArrayList<MyEventsResponse.Data.Events.Datum>
    private lateinit var spinner_status: Spinner
    private lateinit var spinner_type: Spinner
    private lateinit var status_title: TextView
    private lateinit var type_title: TextView
    private lateinit var create_event: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_events)

        findIDs()
        eventId_if = this
        listeners()

        controller = Controller()
        controller.Controller(this)

        if (utility.isConnectingToInternet(this)) {
            pd.show()
            pd.setContentView(R.layout.loading)
            controller.MyEvents("Bearer " + getStringVal(Constants.TOKEN))


        } else {
            utility!!.relative_snackbar(
                parent_myevents!!,
                getString(R.string.nointernet),
                getString(R.string.close_up)
            )
        }


    }

    private fun listeners() {
        myclosets_back.setOnClickListener {
            onBackPressed()
        }

        val status = resources.getStringArray(R.array.Status)
        val statusadapter = ArrayAdapter(
            this!!,
            android.R.layout.simple_spinner_dropdown_item, status
        )
        statusadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner_status.adapter = statusadapter
        spinner_status.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View, position: Int, id: Long
            ) {
//                if (position != 0) {
                    status_title.setText(status[position])
//                }

                //rateType = outFitTitle[position]e
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // write code to perform some action
            }
        }

        val type = resources.getStringArray(R.array.Types)
        val typeAdapter = ArrayAdapter(
            this!!,
            android.R.layout.simple_spinner_dropdown_item, type
        )
        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner_type.adapter = typeAdapter
        spinner_type.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View, position: Int, id: Long
            ) {

//                if (position != 0) {
                    type_title.setText(type[position])
//                }
                //rateType = outFitTitle[position]e
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // write code to perform some action
            }
        }

        create_event.setOnClickListener {
            startActivity(Intent(this,AddNewEventActivity::class.java))
        }


    }


    private fun findIDs() {
        utility = Utility()
        pd = ProgressDialog(this)
        pd!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        pd!!.window!!.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        pd!!.isIndeterminate = true
        pd!!.setCancelable(false)

        myclosets_back = findViewById(R.id.myclosets_back)
        event_recycler = findViewById(R.id.event_recyler)
        spinner_status = findViewById(R.id.spinner_status)
        spinner_type = findViewById(R.id.spinner_type)
        status_title = findViewById(R.id.status_title)
        type_title = findViewById(R.id.type_title)
        create_event = findViewById(R.id.create_event)

    }

    override fun onMyEventsSuccess(success: Response<MyEventsResponse>) {
        pd.dismiss()
        if (success.isSuccessful) {
            if (success.body()?.code.equals("200")) {

                myEvents = ArrayList()
                for (i in success.body()?.data?.events?.data?.indices!!)
                {
                    myEvents.add(success.body()?.data?.events?.data!!.get(i))
                }
              //  myEvents.addAll(success.body()!!.data?.events!!)
                event_recycler.layoutManager =
                    LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
                //closets =  response
                val adapter = MyEventsAdapter(this, myEvents)
                event_recycler.adapter = adapter
            } else {
                utility!!.relative_snackbar(
                    parent_myevents!!,
                    success.message(),
                    getString(R.string.close_up)
                )
            }
        } else {
            utility!!.relative_snackbar(
                parent_myevents!!,
                success.message(),
                getString(R.string.close_up)
            )
        }

    }

    override fun error(error: String?) {
        pd.dismiss()
        utility!!.relative_snackbar(
            parent_myevents!!,
            error,
            getString(R.string.close_up)
        )
    }

    companion object {
        var eventId_if : EventID_IF? = null
    }

    override fun getClosetID(id: String?) {

    }
}