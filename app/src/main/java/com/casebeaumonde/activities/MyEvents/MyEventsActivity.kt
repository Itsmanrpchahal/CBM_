package com.casebeaumonde.activities.MyEvents

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.app.DatePickerDialog
import android.app.Dialog
import android.app.ProgressDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.icu.text.SimpleDateFormat
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.casebeaumonde.Controller.Controller
import com.casebeaumonde.R
import com.casebeaumonde.activities.MyEventDetailScreen.IF.EventID_IF
import com.casebeaumonde.activities.MyEvents.IF.GetCollaboratorID_IF
import com.casebeaumonde.activities.MyEvents.IF.GetCustomerID_IF
import com.casebeaumonde.activities.MyEvents.IF.GetEventID_ForCreateItem
import com.casebeaumonde.activities.MyEvents.IF.GetEvent_ID
import com.casebeaumonde.activities.MyEvents.Response.*
import com.casebeaumonde.activities.MyEvents.adapter.InviteCollaboratorsAdapter
import com.casebeaumonde.activities.MyEvents.adapter.InviteUserAdapter
import com.casebeaumonde.activities.MyEvents.adapter.MyEventsAdapter
import com.casebeaumonde.activities.addItemtoEvent.AddItemToEvent
import com.casebeaumonde.activities.myclosets.adapter.MyClosetsAdapter
import com.casebeaumonde.constants.BaseClass
import com.casebeaumonde.constants.Constants
import com.casebeaumonde.utilities.Utility
import com.github.dhaval2404.imagepicker.ImagePicker
import com.livinglifetechway.quickpermissions_kotlin.runWithPermissions
import kotlinx.android.synthetic.main.activity_add_item_to_closet.*
import kotlinx.android.synthetic.main.activity_my_closets.*
import kotlinx.android.synthetic.main.activity_my_events.*
import kotlinx.android.synthetic.main.changeplandialog.view.*
import okhttp3.MultipartBody
import retrofit2.Response
import java.io.File
import java.util.*
import kotlin.collections.ArrayList

class MyEventsActivity : BaseClass(),
    Controller.MyEventsAPI,
    EventID_IF,
    Controller.FilterEventFilterAPI,
    GetEvent_ID,
    Controller.InviteCustomerAPI,
    Controller.InviteCollaboratesAPI,
    Controller.SendInviteAPI,
    GetCollaboratorID_IF,
    GetCustomerID_IF,
    Controller.InviteCustomer1API,
    Controller.CreateEventAPI,
GetEventID_ForCreateItem{

    private lateinit var utility: Utility
    private lateinit var pd: ProgressDialog
    private lateinit var controller: Controller
    private lateinit var event_recycler: RecyclerView
    private lateinit var myclosets_back: ImageButton
    private lateinit var myEvents: ArrayList<MyEventsResponse.Data.Events.Datum>
    private lateinit var response: ArrayList<MyEventsResponse.Data.Events.Datum>
    private lateinit var filterEvents: ArrayList<MyEventsResponse.Data.Events.Datum>
    private lateinit var filterevents: ArrayList<FilterEventResponse.Data.Event.Datum>
    private lateinit var spinner_status: Spinner
    private lateinit var spinner_type: Spinner
    private lateinit var status_title: TextView
    private lateinit var type_title: TextView
    private lateinit var create_event: ImageButton
    private lateinit var createeventDialog: Dialog
    private lateinit var aditemtocloset_title: EditText
    private lateinit var public_bt: RadioButton
    private lateinit var private_bt: RadioButton
    private lateinit var particular_checkbox: CheckBox
    private lateinit var users_spinner: Spinner
    private lateinit var users_title: TextView
    private lateinit var spinner_layout: RelativeLayout
    private lateinit var startdate: EditText
    private lateinit var enddate: EditText
    private lateinit var descrition: EditText
    private lateinit var status_layout: RelativeLayout
    private lateinit var status_spinner: Spinner
    private lateinit var status_addtitle: TextView
    private lateinit var additemclosets__upload1: Button
    private lateinit var additemclosets__uploadfilename: TextView
    private lateinit var addevent_add: Button
    private lateinit var aditemtocloset_cancel: Button
    private lateinit var search_ET: EditText
    private lateinit var part: MultipartBody.Part
    lateinit var bitMap: Bitmap
    private var path: String = ""
    private var statusname: String = "All Status"
    private var typename: String = "All types"
    private lateinit var inviteCustomerDialog: Dialog
    private lateinit var inviteCollaborateDialog: Dialog
    private lateinit var users: ArrayList<InviteCustomersResponse.Data.InvitedCustomer>
    private lateinit var usersname: ArrayList<String>
    private lateinit var filterusers: ArrayList<InviteCustomersResponse.Data.InvitedCustomer>
    private lateinit var users_recycler: RecyclerView
    private lateinit var collaborators: ArrayList<InviteCollaboratorsResponse.Data.InvitedCollaborator>
    private lateinit var filtercollaborators: ArrayList<InviteCollaboratorsResponse.Data.InvitedCollaborator>
    private lateinit var eventID: String
    private  var particularUserID: String =""
    val c = Calendar.getInstance()
    private lateinit var datetime: String
    private lateinit var particularuserIDonEventCreate: String
    private var eventType: String = "public"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_events)

        findIDs()
        eventId_if = this
        eventId = this
        getCollaborateID = this
        getcustomeridIf = this
        geteventidForcreateitem = this
        listeners()

        controller = Controller()
        controller.Controller(this, this, this, this, this, this,this)

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
                status_title.setText(status[position])

                statusname = status[position]

                if (position != 0) {
                    if (utility.isConnectingToInternet(this@MyEventsActivity)) {
                        pd.show()
                        pd.setContentView(R.layout.loading)

                        controller.FilterEvent(
                            "Bearer " + getStringVal(Constants.TOKEN),
                            statusname,
                            typename
                        )

                    } else {
                        utility!!.relative_snackbar(
                            parent_myevents!!,
                            getString(R.string.nointernet),
                            getString(R.string.close_up)
                        )
                    }
                } else {
                    if (utility.isConnectingToInternet(this@MyEventsActivity)) {
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
                typename = type[position]
//                }
                //rateType = outFitTitle[position]e
                if (position != 0) {
                    if (utility.isConnectingToInternet(this@MyEventsActivity)) {
                        pd.show()
                        pd.setContentView(R.layout.loading)

                        controller.FilterEvent(
                            "Bearer " + getStringVal(Constants.TOKEN),
                            statusname,
                            typename
                        )

                    } else {
                        utility!!.relative_snackbar(
                            parent_myevents!!,
                            getString(R.string.nointernet),
                            getString(R.string.close_up)
                        )
                    }

                } else {
                    if (utility.isConnectingToInternet(this@MyEventsActivity)) {
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

            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // write code to perform some action
            }
        }

        create_event.setOnClickListener {
            //startActivity(Intent(this,AddNewEventActivity::class.java))
            createEventDialog()
        }

        search_ET!!.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (count > 0) {
                    searchByTitle(s.toString())
                } else {
                    event_recycler.layoutManager =
                        LinearLayoutManager(
                            this@MyEventsActivity,
                            LinearLayoutManager.VERTICAL,
                            false
                        )
                    //closets =  response
                    val adapter =
                        MyEventsAdapter(this@MyEventsActivity, myEvents, filterevents, "all")
                    event_recycler.adapter = adapter
                }
            }

            override fun afterTextChanged(s: Editable) {
                if (s.isNotEmpty()) {

                    searchByTitle(s.toString())
                } else {
                    event_recycler.visibility = View.VISIBLE
                    event_recycler.layoutManager =
                        LinearLayoutManager(
                            this@MyEventsActivity,
                            LinearLayoutManager.VERTICAL,
                            false
                        )
                    //closets =  response
                    val adapter =
                        MyEventsAdapter(this@MyEventsActivity, myEvents, filterevents, "all")
                    event_recycler.adapter = adapter
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

    fun searchByTitle(s: String) {
        response = ArrayList()
        if (myEvents.size > 0) {
            for (i in myEvents!!.indices) {
                val closetModel = myEvents!![i]
                if (closetModel.title!!.toLowerCase().contains(s.toLowerCase()))
                    response!!.add(closetModel)

                if (response.size > 0) {
                    event_recycler.layoutManager =
                        LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
                    //closets =  response
                    event_recycler.layoutManager =
                        LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
                    //closets =  response
                    val adapter = MyEventsAdapter(this, response, filterevents, "all")
                    event_recycler.adapter = adapter
                }
            }
            if (response.size == 0) {
                event_recycler.visibility = View.GONE
            }
        }
    }

    private fun createEventDialog() {
        createeventDialog = Dialog(this!!)
        createeventDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        createeventDialog.setContentView(R.layout.custom_addevent)
        val window = createeventDialog.window
        window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )

        aditemtocloset_title = createeventDialog.findViewById(R.id.aditemtocloset_title)
        public_bt = createeventDialog.findViewById(R.id.public_bt)
        private_bt = createeventDialog.findViewById(R.id.private_bt)
        particular_checkbox = createeventDialog.findViewById(R.id.particular_checkbox)
        users_spinner = createeventDialog.findViewById(R.id.users_spinner)
        users_title = createeventDialog.findViewById(R.id.users_title)
        spinner_layout = createeventDialog.findViewById(R.id.spinner_layout)
        startdate = createeventDialog.findViewById(R.id.startdate)
        enddate = createeventDialog.findViewById(R.id.enddate)
        descrition = createeventDialog.findViewById(R.id.descrition)
        status_layout = createeventDialog.findViewById(R.id.status_layout)
        status_spinner = createeventDialog.findViewById(R.id.status_spinner)
        status_addtitle = createeventDialog.findViewById(R.id.status_addtitle)
        additemclosets__upload1 = createeventDialog.findViewById(R.id.additemclosets__upload1)
        additemclosets__uploadfilename =
            createeventDialog.findViewById(R.id.additemclosets__uploadfilename)
        addevent_add = createeventDialog.findViewById(R.id.addevent_add)
        aditemtocloset_cancel = createeventDialog.findViewById(R.id.aditemtocloset_cancel)

        public_bt.isChecked = true

        var dateSetListener = object : DatePickerDialog.OnDateSetListener {
            @RequiresApi(Build.VERSION_CODES.N)
            override fun onDateSet(
                view: DatePicker, year: Int, monthOfYear: Int,
                dayOfMonth: Int
            ) {
                c.set(Calendar.YEAR, year)
                c.set(Calendar.MONTH, monthOfYear)
                c.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                updateDateInView()
            }
        }

        var dateSetListener1 = object : DatePickerDialog.OnDateSetListener {
            @RequiresApi(Build.VERSION_CODES.N)
            override fun onDateSet(
                view: DatePicker, year: Int, monthOfYear: Int,
                dayOfMonth: Int
            ) {
                c.set(Calendar.YEAR, year)
                c.set(Calendar.MONTH, monthOfYear)
                c.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                updateDateInView1()
            }
        }

        val type = resources.getStringArray(R.array.Status1)
        val typeAdapter = ArrayAdapter(
            this!!,
            android.R.layout.simple_spinner_dropdown_item, type
        )
        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        status_spinner.adapter = typeAdapter
        status_spinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View, position: Int, id: Long
            ) {
                status_addtitle.setText(type[position])
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // write code to perform some action
            }
        }

        if (utility.isConnectingToInternet(this)) {
            pd.show()
            pd.setContentView(R.layout.loading)
            controller.InviteCustomer1("Bearer " + getStringVal(Constants.TOKEN), "00000")

        } else {
            utility!!.relative_snackbar(
                parent_myevents!!,
                getString(R.string.nointernet),
                getString(R.string.close_up)
            )
        }


        additemclosets__upload1.setOnClickListener {
            if ((ContextCompat.checkSelfPermission(
                    this!!,
                    Manifest.permission.CAMERA
                ) != PackageManager.PERMISSION_GRANTED) && (ContextCompat.checkSelfPermission(
                    this!!,
                    Manifest.permission.READ_EXTERNAL_STORAGE
                ) != PackageManager.PERMISSION_GRANTED) && (ContextCompat.checkSelfPermission(
                    this!!,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                ) != PackageManager.PERMISSION_GRANTED)
            ) {
                methodRequiresPermissions()
            } else {
                pictureSelectionDialog()
            }
        }

        particular_checkbox.setOnCheckedChangeListener { buttonView, isChecked ->

            if (buttonView.isChecked) {
                spinner_layout.visibility = View.VISIBLE
            } else {
                spinner_layout.visibility = View.GONE
            }
        }

        startdate.setOnClickListener {
            val datePickerDialog = DatePickerDialog(
                this,
                R.style.DialogTheme,
                dateSetListener,
                c.get(Calendar.YEAR),
                c.get(Calendar.MONTH),
                c.get(Calendar.DAY_OF_MONTH)
            )
            datePickerDialog.datePicker.minDate = System.currentTimeMillis() - 1000
            datePickerDialog.show()
        }

        enddate.setOnClickListener {
            val datePickerDialog1 = DatePickerDialog(
                this,
                R.style.DialogTheme,
                dateSetListener1,
                c.get(Calendar.YEAR),
                c.get(Calendar.MONTH),
                c.get(Calendar.DAY_OF_MONTH)
            )
            datePickerDialog1.datePicker.minDate = System.currentTimeMillis() - 1000
            datePickerDialog1.show()
        }

        if (public_bt.isChecked) {
            private_bt.isChecked = false
            eventType = "public"
        } else if (private_bt.isChecked) {
            public_bt.isChecked = true
            eventType = "private"
        }



        addevent_add.setOnClickListener {

            when {
                aditemtocloset_title.text.isEmpty() -> {
                    aditemtocloset_title.requestFocus()
                    aditemtocloset_title.error = "Enter title"

                }

                startdate.text.isEmpty() -> {
                    startdate.requestFocus()
                    startdate.error = "Enter Start Date"
                }

                enddate.text.isEmpty() -> {
                    enddate.requestFocus()
                    enddate.error = "Enter End Date"
                }


                descrition.text.isEmpty() -> {
                    descrition.requestFocus()
                    descrition.error = "Enter Description"
                }

                additemclosets__uploadfilename.text.isEmpty() -> {
                    additemclosets__uploadfilename.requestFocus()
                    additemclosets__uploadfilename.error = "Upload  Image"
                }
                else -> {
                    if (utility.isConnectingToInternet(this)) {
                        pd.show()
                        pd.setContentView(R.layout.loading)
                        controller.CreateEvent("Bearer " + getStringVal(Constants.TOKEN),aditemtocloset_title.text.toString(),
                            status_addtitle.text.toString(),descrition.text.toString(),startdate.text.toString(),enddate.text.toString(),eventType,
                        part,particularUserID)


                    } else {
                        utility!!.relative_snackbar(
                            parent_myevents!!,
                            getString(R.string.nointernet),
                            getString(R.string.close_up)
                        )
                    }

                    Log.d(
                        "createEvent", aditemtocloset_title.text.toString() + "\n" +
                                eventType + "\n" + particularUserID + "\n" + startdate.text.toString() + "\n" + enddate.text.toString() + descrition.text.toString() +
                                status_addtitle.text.toString()
                    )
                }

            }

        }


        aditemtocloset_cancel.setOnClickListener {
            createeventDialog.dismiss()
        }

        createeventDialog.show()
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun updateDateInView() {
        val myFormat = "dd/MM/yyyy" // mention the format you need
        val format1 = "MM/dd/yyyy HH:MM:ss"
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        val sdf1 = SimpleDateFormat(format1, Locale.UK)
        datetime = sdf1.format(c.time)
        Log.d("TIME", "" + datetime)
        startdate.setText(datetime)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun updateDateInView1() {
        val myFormat = "dd/MM/yyyy" // mention the format you need
        val format1 = "MM/dd/yyyy HH:MM:ss"
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        val sdf1 = SimpleDateFormat(format1, Locale.UK)
        datetime = sdf1.format(c.time)
        Log.d("TIME", "" + datetime)
        enddate.setText(datetime)
    }

    @SuppressLint("MissingPermission", "HardwareIds")
    private fun methodRequiresPermissions() = runWithPermissions(
        Manifest.permission.READ_PHONE_STATE,
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.CAMERA

    ) {
        pictureSelectionDialog()
    }

    private fun pictureSelectionDialog() {
        val camera: LinearLayout
        val gallery: LinearLayout
        val dialog = Dialog(this!!)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.imagepicker)
        camera = dialog.findViewById(R.id.linear_camera) as LinearLayout
        gallery = dialog.findViewById(R.id.linear_gallery) as LinearLayout

        camera.setOnClickListener {
            ImagePicker.with(this)
                .cameraOnly()
                .crop()         //User can only capture image using Camera
                .start()
            dialog.dismiss()
        }

        gallery.setOnClickListener {
            ImagePicker.with(this)
                .galleryOnly()
                .crop()     //User can only select image from Gallery
                .start()    //Default Request Code is ImagePicker.REQUEST_CODE
            dialog.dismiss()
        }
        dialog.show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            //Image Uri will not be null for RESULT_OK
            val fileUri = data?.data

            //You can get File object from intent
            var file: File? = ImagePicker.getFile(data)
            //You can also get File Path from intent
            val filePath: String? = ImagePicker.getFilePath(data)

            path = filePath!!
            additemclosets__uploadfilename.text = filePath
            bitMap = MediaStore.Images.Media.getBitmap(contentResolver, fileUri)
            part = Utility.sendImageFileToserver(filesDir, bitMap, "image")

//            Glide.with(this).load(bitMap).placeholder(R.drawable.login_banner1)
//                .into(createcloset_imagerperview)

        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            utility!!.relative_snackbar(
                parent_myevents!!,
                ImagePicker.getError(data),
                getString(R.string.close_up)
            )
        } else {
            utility!!.relative_snackbar(
                parent_myevents,
                "Task Cancelled",
                getString(R.string.close_up)
            )
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
        search_ET = findViewById(R.id.search_ET)

    }

    override fun onMyEventsSuccess(success: Response<MyEventsResponse>) {
        pd.dismiss()
        if (success.isSuccessful) {
            if (success.body()?.code.equals("200")) {

                myEvents = ArrayList()
                filterevents = ArrayList()
                response = myEvents
                for (i in success.body()?.data?.events?.data?.indices!!) {
                    myEvents.add(success.body()?.data?.events?.data!!.get(i))
                }
                //  myEvents.addAll(success.body()!!.data?.events!!)
                event_recycler.layoutManager =
                    LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
                //closets =  response
                val adapter = MyEventsAdapter(this, response, filterevents, "all")
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

    override fun onFilterEventSuccess(success: Response<FilterEventResponse>) {
        pd.dismiss()
        if (success.isSuccessful) {
            if (success.body()?.code.equals("200")) {
                myEvents = ArrayList()
                filterevents = ArrayList()
                for (i in success.body()?.data?.event?.data?.indices!!) {
                    filterevents.add(success.body()?.data?.event?.data!!.get(i))
                }
                //  myEvents.addAll(success.body()!!.data?.events!!)
                event_recycler.layoutManager =
                    LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
                //closets =  response
                val adapter = MyEventsAdapter(this, myEvents, filterevents, "filter")
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

    override fun onCreateEventAPI(success: Response<CreateEventResponse>) {
       // pd.dismiss()
        createeventDialog.dismiss()
        controller.MyEvents("Bearer " + getStringVal(Constants.TOKEN))
        if (success.isSuccessful)
        {
            utility!!.relative_snackbar(
                parent_myevents!!,
                success.body()?.code,
                getString(R.string.close_up)
            )
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
        var eventId_if: EventID_IF? = null
        var eventId: GetEvent_ID? = null
        var getCollaborateID: GetCollaboratorID_IF? = null
        var getcustomeridIf: GetCustomerID_IF? = null
        var geteventidForcreateitem : GetEventID_ForCreateItem? = null
    }

    override fun getClosetID(id: String?) {

    }

    override fun getEVentID(id: String?, type: String) {
        if (type.equals("customer")) {
            inviteCustomerDialog(id, type)
        } else {
            inviteCollaborateDialog(id, type)
        }
    }

    private fun inviteCollaborateDialog(id: String?, type: String) {
        inviteCollaborateDialog = Dialog(this!!)
        inviteCollaborateDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        inviteCollaborateDialog.setContentView(R.layout.invitecustomerpopup)
        val window = inviteCollaborateDialog.window
        window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )

        eventID = id.toString()

        var search_ET: EditText
        var cancelcustomer: Button
        var typetv: TextView
        search_ET = inviteCollaborateDialog.findViewById(R.id.search_ET)
        users_recycler = inviteCollaborateDialog.findViewById(R.id.users_recycler)
        cancelcustomer = inviteCollaborateDialog.findViewById(R.id.cancelcustomer)
        typetv = inviteCollaborateDialog.findViewById(R.id.typetv)
        typetv.setText("Invite collaborators to this event")

        cancelcustomer.setOnClickListener {
            inviteCollaborateDialog.dismiss()
        }

        if (utility.isConnectingToInternet(this)) {
            pd.show()
            pd.setContentView(R.layout.loading)
            controller.InviteCollaborates("Bearer " + getStringVal(Constants.TOKEN), id!!)


        } else {
            utility!!.relative_snackbar(
                parent_myevents!!,
                getString(R.string.nointernet),
                getString(R.string.close_up)
            )
        }

        search_ET!!.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (count > 0) {
                    searchByTitle2(s.toString())
                } else {
                    users_recycler.layoutManager =
                        LinearLayoutManager(
                            this@MyEventsActivity,
                            LinearLayoutManager.VERTICAL,
                            false
                        )
                    //closets =  response
                    val adapter = InviteCollaboratorsAdapter(this@MyEventsActivity, collaborators)
                    users_recycler.adapter = adapter
                }
            }

            override fun afterTextChanged(s: Editable) {
                if (s.isNotEmpty()) {

                    searchByTitle2(s.toString())
                } else {
                    users_recycler.visibility = View.VISIBLE
                    users_recycler.layoutManager =
                        LinearLayoutManager(
                            this@MyEventsActivity,
                            LinearLayoutManager.VERTICAL,
                            false
                        )
                    //closets =  response
                    val adapter = InviteCollaboratorsAdapter(this@MyEventsActivity, collaborators)
                    users_recycler.adapter = adapter
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

        inviteCollaborateDialog.show()
    }

    private fun searchByTitle2(toString: String) {
        filtercollaborators = ArrayList()
        if (collaborators.size > 0) {
            for (i in collaborators!!.indices) {
                val closetModel = collaborators!![i]
                if (closetModel.firstname!!.toLowerCase().contains(toString.toLowerCase()))
                    filtercollaborators!!.add(closetModel)

                if (filtercollaborators.size > 0) {
                    users_recycler.layoutManager =
                        LinearLayoutManager(
                            this@MyEventsActivity,
                            LinearLayoutManager.VERTICAL,
                            false
                        )
                    //closets =  response
                    val adapter =
                        InviteCollaboratorsAdapter(this@MyEventsActivity, filtercollaborators)
                    users_recycler.adapter = adapter
                }
            }
            if (filtercollaborators.size == 0) {
                users_recycler.visibility = View.GONE
            }
        }
    }

    override fun onInviteCustomer1Success(success: Response<InviteCustomersResponse>) {
        pd.dismiss()
        if (success.isSuccessful) {
            if (success.body()?.code.equals("200")) {
                usersname = ArrayList()
                users = ArrayList()
                // usersname.add("Select User")
                for (i in success.body()?.data?.invitedCustomers?.indices!!) {
                    users.add(success.body()?.data?.invitedCustomers?.get(i)!!)
                    usersname.add(
                        success.body()?.data?.invitedCustomers!!.get(i).firstname + " " + success.body()?.data?.invitedCustomers!!.get(
                            i
                        ).lastname
                    )
                }
                val statusadapter = ArrayAdapter(
                    this!!,
                    android.R.layout.simple_spinner_dropdown_item, usersname
                )
                statusadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                users_spinner.adapter = statusadapter
                users_spinner.onItemSelectedListener = object :
                    AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        parent: AdapterView<*>,
                        view: View, position: Int, id: Long
                    ) {
                        users_title.setText(usersname[position])
                        particularUserID = users.get(position).id.toString()
                    }

                    override fun onNothingSelected(parent: AdapterView<*>) {
                        // write code to perform some action
                    }
                }

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

    override fun onInviteCustomerSuccess(success: Response<InviteCustomersResponse>) {
        pd.dismiss()
        if (success.isSuccessful) {
            if (success.body()?.code.equals("200")) {
                users = ArrayList()

                for (i in success.body()?.data?.invitedCustomers?.indices!!) {
                    users.add(success.body()?.data?.invitedCustomers!!.get(i))
                }
                //  myEvents.addAll(success.body()!!.data?.events!!)
                users_recycler.layoutManager =
                    LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
                //closets =  response
                val adapter = InviteUserAdapter(this, users)
                users_recycler.adapter = adapter

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

    override fun onInviteCollaborateSuccess(success: Response<InviteCollaboratorsResponse>) {
        pd.dismiss()
        if (success.isSuccessful) {
            if (success.body()?.code.equals("200")) {
                collaborators = ArrayList()

                for (i in success.body()?.data?.invitedCollaborators?.indices!!) {
                    collaborators.add(success.body()?.data?.invitedCollaborators!!.get(i))
                }
                //  myEvents.addAll(success.body()!!.data?.events!!)
                users_recycler.layoutManager =
                    LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
                //closets =  response
                val adapter = InviteCollaboratorsAdapter(this, collaborators)
                users_recycler.adapter = adapter

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

    private fun inviteCustomerDialog(id: String?, type: String) {
        inviteCollaborateDialog = Dialog(this!!)
        inviteCollaborateDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        inviteCollaborateDialog.setContentView(R.layout.invitecustomerpopup)
        val window = inviteCollaborateDialog.window
        window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )

        eventID = id.toString()
        var search_ET: EditText
        var cancelcustomer: Button
        search_ET = inviteCollaborateDialog.findViewById(R.id.search_ET)
        users_recycler = inviteCollaborateDialog.findViewById(R.id.users_recycler)
        cancelcustomer = inviteCollaborateDialog.findViewById(R.id.cancelcustomer)

        cancelcustomer.setOnClickListener {
            inviteCollaborateDialog.dismiss()
        }

        if (utility.isConnectingToInternet(this)) {
            pd.show()
            pd.setContentView(R.layout.loading)
            controller.InviteCustomer("Bearer " + getStringVal(Constants.TOKEN), id!!)


        } else {
            utility!!.relative_snackbar(
                parent_myevents!!,
                getString(R.string.nointernet),
                getString(R.string.close_up)
            )
        }

        search_ET!!.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (count > 0) {
                    searchByTitle1(s.toString())
                } else {
                    users_recycler.layoutManager =
                        LinearLayoutManager(
                            this@MyEventsActivity,
                            LinearLayoutManager.VERTICAL,
                            false
                        )
                    //closets =  response
                    val adapter = InviteUserAdapter(this@MyEventsActivity, users)
                    users_recycler.adapter = adapter
                }
            }

            override fun afterTextChanged(s: Editable) {
                if (s.isNotEmpty()) {

                    searchByTitle1(s.toString())
                } else {
                    users_recycler.visibility = View.VISIBLE
                    users_recycler.layoutManager =
                        LinearLayoutManager(
                            this@MyEventsActivity,
                            LinearLayoutManager.VERTICAL,
                            false
                        )
                    //closets =  response
                    val adapter = InviteUserAdapter(this@MyEventsActivity, users)
                    users_recycler.adapter = adapter
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

        inviteCollaborateDialog.show()

    }

    private fun searchByTitle1(toString: String) {
        filterusers = ArrayList()
        if (users.size > 0) {
            for (i in users!!.indices) {
                val closetModel = users!![i]
                if (closetModel.firstname!!.toLowerCase().contains(toString.toLowerCase()))
                    filterusers!!.add(closetModel)

                if (filterusers.size > 0) {
                    users_recycler.layoutManager =
                        LinearLayoutManager(
                            this@MyEventsActivity,
                            LinearLayoutManager.VERTICAL,
                            false
                        )
                    //closets =  response
                    val adapter = InviteUserAdapter(this@MyEventsActivity, filterusers)
                    users_recycler.adapter = adapter
                }
            }
            if (filterusers.size == 0) {
                users_recycler.visibility = View.GONE
            }
        }
    }


    override fun getCollaborrateID(id: String) {
        //  pd.dismiss()
        if (utility.isConnectingToInternet(this)) {
            pd.show()
            pd.setContentView(R.layout.loading)
            controller.SendInvite(
                "Bearer " + getStringVal(Constants.TOKEN), eventID,
                id, "collaborator"
            )


        } else {
            utility!!.relative_snackbar(
                parent_myevents!!,
                getString(R.string.nointernet),
                getString(R.string.close_up)
            )
        }

        // Toast.makeText(this,""+id+"    "+eventID,Toast.LENGTH_SHORT).show()
    }

    override fun onSendInviteSuccess(success: Response<SendInviteResponse>) {
        pd.dismiss()
        inviteCollaborateDialog.dismiss()
        if (success.isSuccessful) {

            utility!!.relative_snackbar(
                parent_myevents!!,
                success.body()?.message,
                getString(R.string.close_up)
            )

        } else {
            utility!!.relative_snackbar(
                parent_myevents!!,
                success.body()?.message,
                getString(R.string.close_up)
            )
        }


    }

    override fun getCustomerID(id: String) {
        if (utility.isConnectingToInternet(this)) {
            pd.show()
            pd.setContentView(R.layout.loading)
            controller.SendInvite(
                "Bearer " + getStringVal(Constants.TOKEN), eventID,
                id, "customer"
            )


        } else {
            utility!!.relative_snackbar(
                parent_myevents!!,
                getString(R.string.nointernet),
                getString(R.string.close_up)
            )
        }
    }

    override fun getEventID_FOrCreateItem(id: String) {
        startActivity(Intent(this,AddItemToEvent::class.java).putExtra("eventID",id).putExtra("edit", "0"))
       // Toast.makeText(this,""+id,Toast.LENGTH_SHORT).show()
    }
}