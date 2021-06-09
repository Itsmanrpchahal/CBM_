package com.casebeaumonde.activities.myGigs

import android.app.Dialog
import android.app.ProgressDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.*
import androidx.appcompat.widget.AppCompatSpinner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.casebeaumonde.Controller.Controller
import com.casebeaumonde.R
import com.casebeaumonde.activities.myGigs.IF.DetailGigID_IF
import com.casebeaumonde.activities.myGigs.IF.GetDeleteGigId_IF
import com.casebeaumonde.activities.myGigs.IF.GetEditGigID_IF
import com.casebeaumonde.activities.myGigs.IF.getGigId
import com.casebeaumonde.activities.myGigs.adapter.MyGigsAdapter
import com.casebeaumonde.activities.myGigs.response.AddGigResponse
import com.casebeaumonde.activities.myGigs.response.MyGigsResponse
import com.casebeaumonde.constants.BaseClass
import com.casebeaumonde.constants.Constants
import com.casebeaumonde.fragments.HireExpert.response.SendInvitationResponse
import com.casebeaumonde.utilities.Utility
import kotlinx.android.synthetic.main.activity_my_closets.*
import kotlinx.android.synthetic.main.activity_my_gigs.*
import kotlinx.android.synthetic.main.add_gig.*
import retrofit2.Response

class MyGigs : BaseClass(), Controller.GetUserGigsAPI, Controller.SendInvitationAPI, getGigId,Controller.AddGigAPI,
    GetEditGigID_IF,Controller.UpdateGigAPI, GetDeleteGigId_IF, DetailGigID_IF {

    lateinit var mygigis_back: ImageView
    lateinit var mygigigs_rv: RecyclerView
    private lateinit var utility: Utility
    private lateinit var pd: ProgressDialog
    lateinit var controller: Controller
    lateinit var role: String
    private lateinit var userID: String
    lateinit var addgig: ImageButton
    private lateinit var response :ArrayList<MyGigsResponse.Data.User.Gig>
    private lateinit var sendInvitationDialog: Dialog
    private lateinit var username :String
    private lateinit var addgig_status_spinner:AppCompatSpinner
    private lateinit var spinnertitle:  TextView
    private  var status : String ="active"
    private lateinit var  dialog:Dialog;
    private lateinit var deleteDialog: Dialog
    private lateinit var detailDialog: Dialog


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_gigs)
        controller = Controller()
        controller.Controller(this, this,this,this)
        userID = intent.getStringExtra("userID")!!
        getGigId = this
        geteditgigidIf = this
        getdeletegigidIf = this
        detailgigId =this
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

    private fun setSpinnerData(s: String, status: String?) {

//        business_team_spinner.prompt = "Select"
        val languages = resources.getStringArray(R.array.Status1)
        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_dropdown_item, languages
        )
        addgig_status_spinner.adapter = adapter
        addgig_status_spinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View, position: Int, id: Long
            ) {

              //  addgig_status_spinner.setSelection(position)
                if (!s.equals("edit"))
                {

                }else {

                }

                spinnertitle.setText(languages[position])
                this@MyGigs.status = languages[position]


             var   userType = languages[position]
                Log.d("userType",""+userType+"   "+position)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // write code to perform some action
            }
        }
    }

    companion object {
        var getGigId: getGigId? = null
        var geteditgigidIf : GetEditGigID_IF? = null
        var getdeletegigidIf : GetDeleteGigId_IF? = null
        var detailgigId : DetailGigID_IF?=null
    }

    private fun listeners() {
        mygigis_back.setOnClickListener { onBackPressed() }

        addgig.setOnClickListener {
             addGigDialog("","","")
        }
    }

    private fun addGigDialog(s:String,id:String,pos: String) {
        dialog = Dialog(this!!)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.add_gig)
        val window = dialog.window
        window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
        var addgig_title : EditText
        var addgig_hours : EditText
        var addgig_rate : EditText
        var addgig_description : EditText
        var addgid_hourly_RB : RadioButton
        var fixed_RB : RadioButton
        var save_bt : Button
        var cancel_bt : Button
        var rateType = "hourly"
        addgig_title = dialog.findViewById(R.id.addgig_title)
        addgig_hours = dialog.findViewById(R.id.addgig_hours)
        spinnertitle = dialog.findViewById(R.id.spinnertitle)
        addgig_rate = dialog.findViewById(R.id.addgig_rate)
        addgid_hourly_RB = dialog.findViewById(R.id.addgid_hourly_RB)
        fixed_RB = dialog.findViewById(R.id.fixed_RB)
        addgig_description = dialog.findViewById(R.id.addgig_description)
        addgig_status_spinner = dialog.findViewById(R.id.addgig_status_spinner)




        addgid_hourly_RB.setOnClickListener {
            addgid_hourly_RB.isChecked = true
            rateType = "hourly"
        }

        fixed_RB.setOnClickListener {
            fixed_RB.isChecked = true
            rateType = "fixed"
        }

        if (s.equals("edit"))
        {
            addgig_title.setText(response.get(pos.toInt()).title.toString())
            addgig_hours.setText(response.get(pos.toInt()).hours.toString())
            addgig_rate.setText(response.get(pos.toInt()).rate.toString())
            addgig_description.setText(response.get(pos.toInt()).description)
            if (response.get(pos.toInt()).rateType?.equals("hourly")!!)
            {
                addgid_hourly_RB.isChecked = true
                rateType = "hourly"
            }else {
                fixed_RB.isChecked = true
                rateType = "fixed"
            }
            setSpinnerData("edit",response.get(pos.toInt()).status?.toLowerCase())
            if (status.equals("active"))
            {
                spinnertitle.setText(status)
                this@MyGigs.status = "active"
                addgig_status_spinner.setSelection(0)
            } else if (status.equals("inactive"))
            {
                spinnertitle.setText(status)
                this@MyGigs.status = "inactive"
                addgig_status_spinner.setSelection(1)
            }
        }else {
            addgid_hourly_RB.isChecked = true
            rateType ="hourly"
            setSpinnerData("","")
        }

        dialog.cancel_bt.setOnClickListener {
            dialog.dismiss()
        }

        dialog.save_bt.setOnClickListener {
            when {
                addgig_title.text.isEmpty()-> {
                    addgig_title.requestFocus()
                    addgig_title.setError("Enter gig title")
                }

                addgig_hours.text.isEmpty() -> {
                    addgig_hours.requestFocus()
                    addgig_hours.setError("Enter hours")
                }

                addgig_rate.text.isEmpty()  -> {
                    addgig_rate.requestFocus()
                    addgig_rate.setError("Enter rate")
                }

                addgig_description.text.isEmpty() -> {
                    addgig_description.requestFocus()
                    addgig_description.setError("Enter description")
                } else -> {
                if (utility.isConnectingToInternet(this)) {
                    pd.show()
                    pd.setContentView(R.layout.loading)
                    if (s.equals("edit"))
                    {
                        controller.UpdatEgig(
                            "Bearer " + getStringVal(Constants.TOKEN),
                            id,
                            addgig_title.text.toString(),
                            rateType,
                            addgig_hours.text.toString(),
                            addgig_rate.text.toString(),
                            status,
                            addgig_description.text.toString()
                        )
                    }else {
                        controller.AddGig(
                            "Bearer " + getStringVal(Constants.TOKEN),
                            addgig_title.text.toString(),
                            rateType,
                            addgig_hours.text.toString(),
                            addgig_rate.text.toString(),
                            status,
                            addgig_description.text.toString()
                        )
                    }
                } else {
                    utility!!.relative_snackbar(
                        parent_gigs!!,
                        getString(R.string.nointernet),
                        getString(R.string.close_up)
                    )
                }
            }
            }


        }
        dialog.show()
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


        addgig = findViewById(R.id.add_gig)
        role = intent.getStringExtra("role")!!
        if (userID != getStringVal(Constants.USERID)) {
            add_gig.visibility = View.GONE
        }
    }

    override fun onMyGigsSuccess(myGigsResponse: Response<MyGigsResponse>) {
        pd.dismiss()
        if (myGigsResponse.isSuccessful) {

            response = myGigsResponse.body()?.getData()?.user?.gigs as ArrayList<MyGigsResponse.Data.User.Gig>
            username = myGigsResponse.body()?.getData()?.user?.firstname.toString()
                //add layout manager
            mygigigs_rv.layoutManager =
                LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
            val adapter = MyGigsAdapter(
                this, (myGigsResponse.body()?.getData()?.user?.gigs as ArrayList<MyGigsResponse.Data.User.Gig>)!!, userID,
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

    override fun onAddGigSuccess(success: Response<AddGigResponse>) {

        if (success.isSuccessful)
        {
            dialog.dismiss()
            MygigsCall()
        } else {
            pd.dismiss()
            utility!!.relative_snackbar(
                parent_gigs,
                success.message(),
                getString(R.string.close_up)
            )
        }
    }

    override fun onUpdateGigSuccess(success: Response<AddGigResponse>) {
        if (success.isSuccessful)
        {
            dialog.dismiss()
            MygigsCall()
        } else {
            pd.dismiss()
            utility!!.relative_snackbar(
                parent_gigs,
                success.message(),
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

    override fun getEditgigID(id: String, pos: String) {
        addGigDialog("edit",id,pos)
    }

    override fun getDeleteID_IF(id: String, pos: String) {
        deleteDialog = Dialog(this)
        deleteDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        deleteDialog.setCancelable(false)
        deleteDialog.setContentView(R.layout.logout_dialog)
        val window = deleteDialog.window
        window?.setLayout(
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )

        val no: Button
        val yes: Button
        val logout_tv: TextView

        no = deleteDialog.findViewById(R.id.logout_no)
        yes = deleteDialog.findViewById(R.id.logout_yes)
        logout_tv = deleteDialog.findViewById(R.id.logout_tv)
        logout_tv.setText("ARE YOU SURE YOU WANT TO DELETE?")

        no.setOnClickListener {
            deleteDialog.dismiss()
        }

        yes.setOnClickListener {
//            if (utility.isConnectingToInternet(this)) {
//
//                if (utility.isConnectingToInternet(this)) {
//                    pd.show()
//                    pd.setContentView(R.layout.loading)
//                    controller.DeleteCloset(
//                        "Bearer " + getStringVal(Constants.TOKEN),
//                        pos.toString()
//                    )
//                } else {
//                    utility!!.relative_snackbar(
//                        parent_myclosets,
//                        R.string.nointernet.toString(),
//                        getString(R.string.close_up)
//                    )
//                }
//            } else {
//                utility.relative_snackbar(
//                    parent_myclosets!!,
//                    "No Internet Connectivity",
//                    getString(R.string.close_up)
//                )
//            }
        }

        deleteDialog.show()
    }

    override fun gigDetailID(id: String, pos: String) {
        detailDialog = Dialog(this)
        detailDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        detailDialog.setContentView(R.layout.gigdetaildialog)
        val window = detailDialog.window
        window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )

        var title : TextView
        var description : TextView
        var hours : TextView
        var rate : TextView
        var rate_type : TextView
        var status : TextView
        var close: Button

        title = detailDialog.findViewById(R.id.title)
        description = detailDialog.findViewById(R.id.description)
        hours = detailDialog.findViewById(R.id.hours)
        rate = detailDialog.findViewById(R.id.rate)
        rate_type = detailDialog.findViewById(R.id.rate_type)
        status = detailDialog.findViewById(R.id.status)
        close = detailDialog.findViewById(R.id.close)

        title.text ="Title: "+response.get(pos.toInt()).title
        description.text ="Description: "+response.get(pos.toInt()).description
        hours.text ="Hours: "+response.get(pos.toInt()).hours
        rate.text ="Rate: "+response.get(pos.toInt()).rate
        rate_type.text ="Rate Type: "+response.get(pos.toInt()).rateType
        status.text ="Status: "+response.get(pos.toInt()).status

        close.setOnClickListener {
            detailDialog.dismiss()
        }
        detailDialog.show()
    }
}