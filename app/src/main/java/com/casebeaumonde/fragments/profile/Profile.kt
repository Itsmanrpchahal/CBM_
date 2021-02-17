package com.casebeaumonde.fragments.profile

import android.app.Activity
import android.app.DatePickerDialog
import android.app.Dialog
import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.*
import android.widget.*
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.casebeaumonde.Controller.Controller
import com.casebeaumonde.MainActivity
import com.casebeaumonde.R
import com.casebeaumonde.Retrofit.WebAPI
import com.casebeaumonde.UpdateProfilePicResponse
import com.casebeaumonde.activities.EventsInvitations.EventsInvitations
import com.casebeaumonde.activities.login.loginResponse.LogoutResponse
import com.casebeaumonde.activities.myContracts.MyContracts
import com.casebeaumonde.activities.myGigs.MyGigs
import com.casebeaumonde.activities.myWall.MyWall
import com.casebeaumonde.activities.myclosets.MyClosets
import com.casebeaumonde.activities.notifications.Notifications
import com.casebeaumonde.activities.paymenthistory.PaymentHistory
import com.casebeaumonde.activities.paymentscreen_b.PaymentScreenBussiness
import com.casebeaumonde.constants.BaseFrag
import com.casebeaumonde.constants.Constants
import com.casebeaumonde.fragments.pricing.Pricing
import com.casebeaumonde.fragments.profile.IF.GetCardID
import com.casebeaumonde.fragments.profile.IF.GetUserID
import com.casebeaumonde.fragments.profile.adapter.CardsAdapter
import com.casebeaumonde.fragments.profile.adapter.FollowerAdapter
import com.casebeaumonde.fragments.profile.adapter.FollowingAdapter
import com.casebeaumonde.fragments.profile.profileResponse.*
import com.casebeaumonde.utilities.Utility
import com.github.dhaval2404.imagepicker.ImagePicker
import com.google.android.material.tabs.TabLayout
import com.google.gson.JsonObject
import com.stripe.android.Stripe
import com.stripe.android.TokenCallback
import com.stripe.android.model.Card
import com.stripe.android.model.Token
import kotlinx.android.synthetic.main.activity_card_detail_screen.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.editprofilelayout.*
import kotlinx.android.synthetic.main.fragment_profile.*
import okhttp3.MultipartBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class Profile : BaseFrag(), Controller.UserProfileAPI, Controller.UpdateAvatarAPI,
    Controller.UpdateProfileAPI, GetUserID, Controller.PaymentMethodAPI, GetCardID,
    Controller.DeleteCardAPI, Controller.CancelPlanAPI, Controller.AddPaymentMethodAPI {

    private lateinit var controller: Controller
    private lateinit var utility: Utility
    private lateinit var pd: ProgressDialog
    private lateinit var profile_username: TextView
    private lateinit var user_decs: TextView
    private lateinit var profile_mygigs: Button
    private lateinit var profile_profilePic: ImageView
    private lateinit var profile_followerscount: TextView
    private lateinit var profile_followingcount: TextView
    private lateinit var editptofile_paypaltext: TextView
    private lateinit var editprofile_paypal_email: EditText
    private lateinit var profile_followerslayout: LinearLayout
    private lateinit var profile_followinglayout: LinearLayout
    private lateinit var profile_changetv: ImageView
    private lateinit var part: MultipartBody.Part
    private lateinit var bitMap: Bitmap
    private lateinit var editprofile_image: ImageView
    private lateinit var profile_changepassword: Button
    private lateinit var profile_edit_profile: Button
    private lateinit var profile_chooseyourplan: Button
    private lateinit var profile_logout: Button
    private lateinit var profile_mypaymentmethods: Button
    private lateinit var followbt: Button
    private var path: String = ""
    private lateinit var changePasswordDialog: Dialog
    private lateinit var dialog: Dialog
    private lateinit var viewplanDialog: Dialog
    private lateinit var mypaymentdialog: Dialog
    private lateinit var addPaymentMethod: Dialog
    private lateinit var role: String
    private lateinit var username: String
    private lateinit var userID: String
    private lateinit var followfollowingDialog: Dialog
    private lateinit var cancelPlanDialog: Dialog
    private lateinit var fragmentActivity: FragmentActivity
    private lateinit var businessSubscription: String
    private lateinit var customerSubscription: String
    lateinit var payment_method_recycler: RecyclerView
    lateinit var close_paymentdialog: Button
    private lateinit var followers: ArrayList<UserProfileResponse.Data.User.Follower>
    private lateinit var following: ArrayList<UserProfileResponse.Data.User.Following>
    private lateinit var cards: ArrayList<PaymentMethodResponse.Data.PaymentProfile>
    private lateinit var plan: String
    lateinit var manager: FragmentManager
    lateinit var plan_cardholdername: EditText
    lateinit var plan_cardnumber: EditText
    lateinit var plan_cvc: EditText
    lateinit var plan_billingzipcode: EditText
    lateinit var plan_carddate: EditText
    lateinit var plan_verfybt: Button
    lateinit var plan_cancel: Button
    lateinit var radio_alluser: RadioButton
    lateinit var radio_userifollow: RadioButton
    lateinit var radio_noone: RadioButton
    lateinit var radiogroup: RadioGroup
    private lateinit var cardholdername: String
    private lateinit var cardnumber: String
    private lateinit var cardexpDateyear: String
    private lateinit var cardcvc: String
    private lateinit var cardbilligcode: String
    private lateinit var mesgFrom: String
    private lateinit var logoutDialog: Dialog
    private lateinit var profile_mywall: Button
    private lateinit var profile_mycontracts: Button
    private lateinit var profile_myclosets: Button
    private lateinit var profile_myevents: Button
    private lateinit var profile_eventInvitation: Button
    val c = Calendar.getInstance()
    private var MONTH: Int = 0
    private var YEAR: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_profile, container, false)
        findIds(view)
        manager = fragmentManager!!
        controller = Controller()
        controller.Controller(this, this, this, this, this, this, this)
        getUserID = this
        getCardID = this

        listeners()

        if (utility.isConnectingToInternet(context)) {
            pd.show()
            pd.setContentView(R.layout.loading)

            controller.setUserProfileAPI(
                "Bearer " + getStringVal(Constants.TOKEN),
                getStringVal(Constants.USERID)
            )

        } else {
            utility.relative_snackbar(
                parent_profile!!,
                "No Internet Connectivity",
                getString(R.string.close_up)
            )
        }


        userID = getStringVal(Constants.USERID).toString()

        return view
    }

    private fun listeners() {
        profile_changetv.setOnClickListener {

        }

        profile_edit_profile.setOnClickListener {
            editprofile_Dialog(view)
        }

        profile_changepassword.setOnClickListener {

            changePassworddialog()
        }

        profile_mygigs.setOnClickListener {
            startActivity(
                Intent(context, MyGigs::class.java).putExtra("role", role)
                    .putExtra("userID", userID)
            )
        }

        profile_followerslayout.setOnClickListener {
            openFollowersDialog("followers")
        }

        profile_followinglayout.setOnClickListener {
            openFollowersDialog("following")
        }

        profile_chooseyourplan.setOnClickListener {

            if (profile_chooseyourplan.text.toString().equals("My Subscriptions")) {
                ViewPlanDialog()
            } else {

                val bundle = Bundle()
                bundle.putString(Constants.FROM, "pricing")


                val transaction = manager.beginTransaction()
                val priceFrag = Pricing()
                priceFrag.arguments = bundle
                transaction.replace(R.id.nav_host_fragment, priceFrag)
                transaction.addToBackStack(null)
                transaction.commit()
            }
        }

        profile_logout.setOnClickListener {
            logoutDialog()
        }

        profile_mywall.setOnClickListener {
            startActivity(Intent(context, MyWall::class.java).putExtra("userID", userID))
        }

        profile_mycontracts.setOnClickListener {
            startActivity(Intent(context, MyContracts::class.java).putExtra("userID", userID))
        }

        profile_myclosets.setOnClickListener {
            startActivity(Intent(context, MyClosets::class.java).putExtra("userID", userID))
        }

        profile_myevents.setOnClickListener {

        }

        profile_eventInvitation.setOnClickListener {
            startActivity(Intent(context, EventsInvitations::class.java).putExtra("userID", userID))
        }

    }

    fun logoutDialog() {
        logoutDialog = Dialog(context!!)
        logoutDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        logoutDialog.setCancelable(false)
        logoutDialog.setContentView(R.layout.logout_dialog)
        val window = logoutDialog.window
        window?.setLayout(
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )

        val no: Button
        val yes: Button

        no = logoutDialog.findViewById(R.id.logout_no)
        yes = logoutDialog.findViewById(R.id.logout_yes)

        no.setOnClickListener {
            logoutDialog.dismiss()
        }

        yes.setOnClickListener {
            logout()
        }

        logoutDialog.show()
    }

    private fun logout() {
        //hideKeyboard()
        if (utility!!.isConnectingToInternet(context)) {
            logoutDialog.dismiss()
            pd.show()
            pd.setContentView(R.layout.loading)

            val logoutCall =
                WebAPI.apiInterface?.logoutCall("Bearer " + getStringVal(Constants.TOKEN))
            logoutCall?.enqueue(object : retrofit2.Callback<LogoutResponse> {
                override fun onResponse(
                    call: Call<LogoutResponse>,
                    response: Response<LogoutResponse>
                ) {
                    pd.dismiss()
                    if (response.isSuccessful) {
                        val code = response.body()?.getCode()
                        if (code == 200) {
                            startActivity(
                                Intent(
                                    context,
                                    MainActivity::class.java
                                ).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                                    .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                                    .setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)

                            )
                            activity?.finish()
                            //finish()
                            activity?.overridePendingTransition(0, 0)
                            activity?.overridePendingTransition(0, 0)

                            clearStringVal(Constants.TOKEN)
                            clearStringVal(Constants.USERID)
                        }
                    }
                }

                override fun onFailure(call: Call<LogoutResponse>, t: Throwable) {
                    pd.dismiss()
                    utility!!.relative_snackbar(
                        parent_main!!,
                        t.localizedMessage,
                        getString(R.string.close_up)
                    )
                }

            })
        } else {
            utility.relative_snackbar(
                parent_main!!,
                "No Internet Connectivity",
                getString(R.string.close_up)
            )
        }
    }


    private fun ViewPlanDialog() {
        viewplanDialog = Dialog(context!!)
        viewplanDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)

        viewplanDialog.setContentView(R.layout.manageplandialog)
        val window = viewplanDialog.window
        window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )

        val planname: TextView
        val cancelplan: Button
        val viewpayments: Button
        val changeplan: Button
        val close: Button
        planname = viewplanDialog.findViewById(R.id.planname)
        cancelplan = viewplanDialog.findViewById(R.id.cancelplan)
        viewpayments = viewplanDialog.findViewById(R.id.viewpayments)
        changeplan = viewplanDialog.findViewById(R.id.changeplan)
        close = viewplanDialog.findViewById(R.id.close)

        planname.setText(plan)
        viewpayments.setOnClickListener {
            startActivity(
                Intent(
                    context,
                    PaymentHistory::class.java
                )
            )
        }
        close.setOnClickListener { viewplanDialog.dismiss() }
        changeplan.setOnClickListener {
            viewplanDialog.dismiss()
            val bundle = Bundle()
            bundle.putString(Constants.FROM, "changeplan")
            bundle.putString(Constants.PLANNAME, plan)


            val transaction = manager.beginTransaction()
            val priceFrag = Pricing()
            priceFrag.arguments = bundle
            transaction.replace(R.id.nav_host_fragment, priceFrag)
            transaction.addToBackStack(null)
            transaction.commit()
        }

        cancelplan.setOnClickListener {
            cancelPlanDialog = Dialog(context!!)
            cancelPlanDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)

            cancelPlanDialog.setContentView(R.layout.cancelplandialog)
            val window = cancelPlanDialog.window
            window?.setLayout(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT
            )

            var keepsubscrition: Button
            var cancelsubscrition: Button

            keepsubscrition = cancelPlanDialog.findViewById(R.id.keepsubscrition)
            cancelsubscrition = cancelPlanDialog.findViewById(R.id.cancelsubscrition)

            keepsubscrition.setOnClickListener { cancelPlanDialog.dismiss() }
            cancelsubscrition.setOnClickListener {
                pd.show()

                if (utility.isConnectingToInternet(context)) {
                    pd.show()
                    pd.setContentView(R.layout.loading)

                    controller.CancelPlan(
                        "Bearer " + getStringVal(Constants.TOKEN),
                        getStringVal(Constants.SUBSCRIPTION_ID),
                        getStringVal(Constants.USER_ROLE)
                    )

                } else {
                    utility.relative_snackbar(
                        parent_profile!!,
                        "No Internet Connectivity",
                        getString(R.string.close_up)
                    )
                }
            }

            cancelPlanDialog.show()
        }
        viewplanDialog.show()

    }

    private fun openFollowersDialog(s: String) {
        followfollowingDialog = Dialog(context!!)
        followfollowingDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        followfollowingDialog.setCancelable(true)
        followfollowingDialog.setContentView(R.layout.followerfollowinglayout)
        val window = followfollowingDialog.window
        window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )

        val followrecyclerview: RecyclerView
        val followcancelbt: Button
        val followtext: TextView
        followrecyclerview = followfollowingDialog.findViewById(R.id.followrecyclerview)
        followcancelbt = followfollowingDialog.findViewById(R.id.followcancelbt)
        followtext = followfollowingDialog.findViewById(R.id.followtext)
        if (s.equals("followers")) {
            followtext.setText("Followers Users List")
            followrecyclerview.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            val adapter = FollowerAdapter(context!!, followers)
            followrecyclerview.adapter = adapter
        } else {
            followtext.setText("Following Users List")
            followrecyclerview.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            val adapter = FollowingAdapter(context!!, following)
            followrecyclerview.adapter = adapter
        }


        followcancelbt.setOnClickListener {
            followfollowingDialog.dismiss()
        }

        followfollowingDialog.show()
    }

    private fun changePassworddialog() {
        changePasswordDialog = Dialog(context!!)
        changePasswordDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        changePasswordDialog.setCancelable(false)
        changePasswordDialog.setContentView(R.layout.changepassword)
        val window = changePasswordDialog.window
        window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )

        val changepassword_closebt: Button
        val changepassword_changebt: Button
        val changepass_current: EditText
        val changepassword_newPassword: EditText
        val changepassword_CnewPassword: EditText

        changepassword_closebt =
            changePasswordDialog.findViewById(R.id.changepassword_closebt)
        changepassword_changebt =
            changePasswordDialog.findViewById(R.id.changepassword_changebt)
        changepass_current = changePasswordDialog.findViewById(R.id.changepass_current)
        changepassword_newPassword =
            changePasswordDialog.findViewById(R.id.changepassword_newPassword)
        changepassword_CnewPassword =
            changePasswordDialog.findViewById(R.id.changepassword_CnewPassword)

        changepassword_closebt.setOnClickListener {
            changePasswordDialog.dismiss()
        }

        changepassword_changebt.setOnClickListener {
            when {
                changepass_current.text.isEmpty() -> {
                    changepass_current.requestFocus()
                    changepass_current.error = getString(R.string.entercurrentpassword)
                }

                changepassword_newPassword.text.isEmpty() -> {
                    changepassword_newPassword.requestFocus()
                    changepassword_newPassword.error = getString(R.string.enternewpassword)
                }

                changepassword_CnewPassword.text.isEmpty() -> {
                    changepassword_CnewPassword.requestFocus()
                    changepassword_CnewPassword.error = getString(R.string.confirmpassword)
                }

                !changepassword_newPassword.text.toString()
                    .equals(changepassword_CnewPassword.text.toString()) -> {
                    changepassword_CnewPassword.requestFocus()
                    changepassword_CnewPassword.error = getString(R.string.passwordnotmatch)

                }

                changepassword_newPassword.text.toString().length < 8 && !Utility.isValidPassword(
                    changepassword_newPassword.text.toString()
                ) -> {
                    changepassword_newPassword.requestFocus()
                    changepassword_newPassword.error = getString(R.string.strongpass)
                }
                else -> {
                    changePassword(
                        changePasswordDialog,
                        changepassword_newPassword,
                        "Bearer " + getStringVal(Constants.TOKEN),
                        getStringVal(Constants.USERID),
                        changepass_current.text.toString(),
                        changepassword_newPassword.text.toString(),
                        changepassword_CnewPassword.text.toString(),
                        changepass_current
                    )
                }
            }
        }
        changePasswordDialog.show()
    }

    private fun editprofile_Dialog(view: View?) {
        dialog = Dialog(context!!)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)

        dialog.setContentView(R.layout.editprofilelayout)
        val window = dialog.window
        window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )

        val editprofile_firstname: EditText
        val editprofile_lastname: EditText
        val editprofile_email: EditText
        val editprofile_phone: EditText
        val editprofile_about: EditText
        val update_profile: Button
        val changepassword_closebt: Button
        val update_userimage: Button
        val editprofile_fburl: EditText
        val editprofile_instaurl: EditText
        val editprofile_twitterurl: EditText

        editprofile_firstname = dialog.findViewById(R.id.editprofile_firstname)
        editprofile_lastname = dialog.findViewById(R.id.editprofile_lastname)
        editprofile_email = dialog.findViewById(R.id.editprofile_email)
        editprofile_phone = dialog.findViewById(R.id.editprofile_phone)
        editprofile_about = dialog.findViewById(R.id.editprofile_about)
        update_profile = dialog.findViewById(R.id.update_profile)
        changepassword_closebt = dialog.findViewById(R.id.changepassword_closebt)
        editprofile_paypal_email = dialog.findViewById(R.id.editprofile_paypal_email)
        editprofile_image = dialog.findViewById(R.id.editprofile_image)
        update_userimage = dialog.findViewById(R.id.update_userimage)
        radio_noone = dialog.findViewById(R.id.radio_noone)
        radio_userifollow = dialog.findViewById(R.id.radio_userifollow)
        radio_alluser = dialog.findViewById(R.id.radio_alluser)
        radiogroup = dialog.findViewById(R.id.radiogroup)
        editprofile_fburl = dialog.findViewById(R.id.editprofile_fburl)
        editprofile_instaurl = dialog.findViewById(R.id.editprofile_instaurl)
        editprofile_twitterurl = dialog.findViewById(R.id.editprofile_twitterurl)


        if (!getStringVal(Constants.USER_ROLE).equals("customer")) {
            editprofile_paypal_email.visibility = View.VISIBLE
            editprofile_fburl.visibility = View.VISIBLE
            editprofile_instaurl.visibility = View.VISIBLE
            editprofile_twitterurl.visibility = View.VISIBLE

        } else {
            radiogroup.visibility = View.VISIBLE
            if (getStringVal(Constants.CHAT).equals("1")) {
                radio_alluser.isChecked = true
                mesgFrom = "1"
            } else if (getStringVal(Constants.CHAT).equals("2")) {
                radio_userifollow.isChecked = true
                mesgFrom = "2"
            } else if (getStringVal(Constants.CHAT).equals("3")) {
                radio_noone.isChecked = true
                mesgFrom = "3"
            }
        }

        editprofile_firstname.setText(getStringVal(Constants.FIRSTNAME))
        editprofile_lastname.setText(getStringVal(Constants.LASTNAME))
        editprofile_email.setText(getStringVal(Constants.EMAIL))
        editprofile_phone.setText(getStringVal(Constants.PHONE))
        editprofile_about.setText(getStringVal(Constants.ABOUT))
        if (getStringVal(Constants.USER_ROLE).equals("customer")) {
            editprofile_paypal_email.setText("")
        } else {
            editprofile_paypal_email.setText(getStringVal(Constants.PAYPALID))
        }


        changepassword_closebt.setOnClickListener {
            dialog.dismiss()
        }


        radiogroup.setOnCheckedChangeListener { group, checkedId ->

            when {
                radio_alluser.isChecked -> {
                    mesgFrom = "1"
                }

                radio_userifollow.isChecked -> {
                    mesgFrom = "2"
                }

                radio_noone.isChecked -> {
                    mesgFrom = "3"
                }
            }
        }

        update_profile.setOnClickListener {
            when {
                editprofile_firstname.text.isEmpty() -> {
                    editprofile_firstname.requestFocus()
                    editprofile_firstname.error = getString(R.string.enterfirstname)
                }

                editprofile_lastname.text.isEmpty() -> {
                    editprofile_lastname.requestFocus()
                    editprofile_lastname.error = getString(R.string.enterlastname)
                }

                editprofile_email.text.isEmpty() -> {
                    editprofile_email.requestFocus()
                    editprofile_email.error = getString(R.string.enterlastname)
                }

                editprofile_phone.text.isEmpty() -> {
                    editprofile_email.requestFocus()
                    editprofile_email.error = getString(R.string.enteremail)
                }

                editprofile_about.text.isEmpty() -> {
                    editprofile_about.requestFocus()
                    editprofile_about.error = getString(R.string.enterabout)
                }

                else -> {
                    if (utility.isConnectingToInternet(context)) {
                        pd.show()
                        pd.setContentView(R.layout.loading)
                        controller.setUpDateProfile(
                            "Bearer " + getStringVal(Constants.TOKEN),
                            editprofile_firstname.text.toString(),
                            editprofile_lastname.text.toString(),
                            editprofile_email.text.toString(),
                            editprofile_paypal_email.text.toString(),
                            editprofile_phone.text.toString(),
                            editprofile_about.text.toString(),
                            getStringVal(Constants.USERID).toString(),
                            mesgFrom
                        )
                    } else {
                        utility!!.relative_snackbar(
                            parent_main,
                            getString(R.string.nointernet),
                            getString(R.string.close_up)
                        )
                    }
                }
            }
        }

        update_userimage.setOnClickListener {
            pictureSelectionDialog()
        }



        dialog.show()
    }

    private fun findIds(view: View) {
        utility = Utility()
        pd = ProgressDialog(context)
        pd!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        pd!!.window!!.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        pd!!.isIndeterminate = true
        pd!!.setCancelable(false)
        profile_username = view.findViewById(R.id.profile_username)
        user_decs = view.findViewById(R.id.user_decs)
        profile_profilePic = view.findViewById(R.id.profile_profilePic)
        profile_followerscount = view.findViewById(R.id.profile_followerscount)
        profile_followingcount = view.findViewById(R.id.profile_followingcount)
        profile_followerslayout = view.findViewById(R.id.profile_followerslayout)
        profile_followinglayout = view.findViewById(R.id.profile_followinglayout)
        profile_mypaymentmethods = view.findViewById(R.id.profile_mypaymentmethods)
        profile_changetv = view.findViewById(R.id.profile_changetv)
        profile_edit_profile = view.findViewById(R.id.profile_edit_profile)
        profile_changepassword = view.findViewById(R.id.profile_changepassword)
        profile_mygigs = view.findViewById(R.id.profile_mygigs)
        profile_chooseyourplan = view.findViewById(R.id.profile_chooseyourplan)
        profile_logout = view.findViewById(R.id.profile_logout)
        profile_mywall = view.findViewById(R.id.profile_mywall)
        profile_mycontracts = view.findViewById(R.id.profile_mycontracts)
        profile_myclosets = view.findViewById(R.id.profile_myclosets)
        profile_myevents = view.findViewById(R.id.profile_myevents)
        profile_eventInvitation = view.findViewById(R.id.profile_eventInvitation)
        pd.show()
        pd.setContentView(R.layout.loading)

        //userID = getStringVal(Constants.USERID).toString()
    }


    override fun onResume() {
        super.onResume()
    }

    companion object {
        var getUserID: GetUserID? = null
        var getCardID: GetCardID? = null
    }

    override fun getUserID(id: String?) {

        userID = id.toString()
        startActivity(Intent(context, ViewProfile::class.java).putExtra("userID", userID))
    }

    private fun pictureSelectionDialog() {

        val camera: LinearLayout
        val gallery: LinearLayout
        val dialog = Dialog(context!!)
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
                .galleryOnly().compress(60)
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
            bitMap = MediaStore.Images.Media.getBitmap(context?.contentResolver, fileUri)
            part = Utility.sendImageFileToserver(context?.filesDir, bitMap, "image")

            updateAvatar(
                part,
                "Bearer " + getStringVal(Constants.TOKEN),
                getStringVal(Constants.USERID)
            )
        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            utility!!.relative_snackbar(
                parent_profile!!,
                ImagePicker.getError(data),
                getString(R.string.close_up)
            )

        } else {
            utility!!.relative_snackbar(
                parent_profile,
                "Task Cancelled",
                getString(R.string.close_up)
            )
        }
    }

    private fun updateAvatar(part: MultipartBody.Part, token: String, userId: String?) {
        if (utility.isConnectingToInternet(context)) {
            pd.show()
            pd.setContentView(R.layout.loading)
            controller.setUpdateAvatar(part, token, userId)

        } else {
            utility!!.relative_snackbar(
                parent_profile,
                R.string.nointernet.toString(),
                getString(R.string.close_up)
            )
        }
    }

    override fun onPrfileSucess(userProfileResponse: Response<UserProfileResponse>) {
        pd.dismiss()
        if (userProfileResponse.isSuccessful) {
            Log.d("userprofilerespose", "" + userProfileResponse.body()?.getData())
            plan = userProfileResponse.body()
                ?.getData()?.user?.customerSubscription?.plan?.name.toString()
            profile_username.text =
                userProfileResponse.body()
                    ?.getData()?.user?.firstname + " " + userProfileResponse.body()
                    ?.getData()?.user?.lastname

            Glide.with(context!!)
                .load(
                    userProfileResponse.body()?.getData()?.filePath + userProfileResponse.body()
                        ?.getData()?.user?.avatar?.toString()
                )
                .placeholder(R.drawable.login_banner).into(profile_profilePic)
            profile_followerscount.text =
                userProfileResponse.body()?.getData()?.user?.followers?.size.toString()
            profile_followingcount.text =
                userProfileResponse.body()?.getData()?.user?.following?.size.toString()
            username = userProfileResponse.body()?.getData()?.user?.firstname!!
            setStringVal(
                Constants.FIRSTNAME,
                userProfileResponse.body()?.getData()?.user?.firstname
            )
            setStringVal(Constants.LASTNAME, userProfileResponse.body()?.getData()?.user?.lastname)
            setStringVal(Constants.EMAIL, userProfileResponse.body()?.getData()?.user?.email)
            setStringVal(Constants.PHONE, userProfileResponse.body()?.getData()?.user?.phone)
            setStringVal(
                Constants.PAYPALID,
                userProfileResponse.body()?.getData()?.user?.paypal_email.toString()
            )
            setStringVal(
                Constants.SUBSCRIPTION_ID,
                userProfileResponse.body()?.getData()?.user?.customerSubscription?.id.toString()
            )
            setStringVal(
                Constants.CHAT,

                userProfileResponse.body()?.getData()?.user?.chat_invitation.toString()

            )
            setStringVal(
                Constants.ABOUT,
                userProfileResponse.body()?.getData()?.user?.profile?.aboutMe.toString()
            )

            followers =
                userProfileResponse.body()
                    ?.getData()?.user?.followers as ArrayList<UserProfileResponse.Data.User.Follower>
            following =
                userProfileResponse.body()
                    ?.getData()?.user?.following as ArrayList<UserProfileResponse.Data.User.Following>

            role = userProfileResponse.body()?.getData()?.user?.role.toString()
            setStringVal(
                Constants.USER_ROLE,
                userProfileResponse.body()?.getData()?.user?.role.toString()
            )


            if (userProfileResponse.body()?.getData()?.user?.customerSubscription != null) {
                setStringVal(Constants.CUSTOMERSUBSCRIPTION, "1")
            }

            if (userProfileResponse.body()?.getData()?.user?.businessSubscription != null) {
                setStringVal(Constants.BUSSINESSSUBSSCRIPTION, "1")
            }
            if (userProfileResponse.body()
                    ?.getData()?.user?.customerSubscription != null || userProfileResponse.body()
                    ?.getData()?.user?.businessSubscription != null
            ) {
                profile_mygigs.visibility = View.GONE

                profile_chooseyourplan.setText("My Subscriptions")
                profile_mypaymentmethods.visibility = View.VISIBLE

                profile_mywall.visibility = View.VISIBLE
                profile_mycontracts.visibility = View.VISIBLE
                profile_myevents.visibility = View.VISIBLE
                profile_eventInvitation.visibility = View.VISIBLE
                profile_myclosets.visibility = View.VISIBLE
            } else {
                profile_mygigs.visibility = View.GONE
                profile_mywall.visibility = View.VISIBLE
                profile_myclosets.visibility = View.VISIBLE
            }

            profile_mypaymentmethods.setOnClickListener {
                startActivity(Intent(context, PaymentScreenBussiness::class.java))
            }
//            profile_mypaymentmethods.setOnClickListener {
//                mypaymentdialog = Dialog(context!!)
//                mypaymentdialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
//
//                mypaymentdialog.setContentView(R.layout.managepaymentmethid)
//                val window = mypaymentdialog.window
//                window?.setLayout(
//                    WindowManager.LayoutParams.MATCH_PARENT,
//                    WindowManager.LayoutParams.WRAP_CONTENT
//                )
//
//                pd.show()
//                controller.SetPaymentMethod("Bearer "+getStringVal(Constants.TOKEN))
//                val addpaymentmethod : Button
//                payment_method_recycler = mypaymentdialog.findViewById(R.id.payment_method_recycler)
//                close_paymentdialog = mypaymentdialog.findViewById(R.id.close_paymentdialog)
//                addpaymentmethod = mypaymentdialog.findViewById(R.id.addpaymentmethod)
//
//                close_paymentdialog.setOnClickListener { mypaymentdialog.dismiss() }
//
//                addpaymentmethod.setOnClickListener {
//                    addPaymentMethodDialog()
//                }
//
//                mypaymentdialog.show()
//            }
        } else {
            utility!!.relative_snackbar(
                parent_profile,
                userProfileResponse.message(),
                getString(R.string.close_up)
            )
        }

    }

    private fun addPaymentMethodDialog() {
        addPaymentMethod = Dialog(context!!)
        addPaymentMethod.requestWindowFeature(Window.FEATURE_NO_TITLE)

        addPaymentMethod.setContentView(R.layout.addpaymentmethod)
        val window = addPaymentMethod.window
        window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )



        plan_carddate = addPaymentMethod.findViewById(R.id.plan_carddate)
        plan_cardholdername = addPaymentMethod.findViewById(R.id.plan_cardholdername)
        plan_cardnumber = addPaymentMethod.findViewById(R.id.plan_cardnumber)
        plan_billingzipcode = addPaymentMethod.findViewById(R.id.plan_billingzipcode)
        plan_verfybt = addPaymentMethod.findViewById(R.id.plan_verfybt)
        plan_cvc = addPaymentMethod.findViewById(R.id.plan_cvc)
        plan_cancel = addPaymentMethod.findViewById(R.id.plan_cancel)

        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)
        // create an OnDateSetListener
        val dateSetListener = object : DatePickerDialog.OnDateSetListener {
            override fun onDateSet(
                view: DatePicker, year: Int, monthOfYear: Int,
                dayOfMonth: Int
            ) {
                c.set(Calendar.YEAR, year)
                c.set(Calendar.MONTH, monthOfYear)
                c.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                MONTH = monthOfYear
                YEAR = year

                updateDateInView()
            }
        }


        // when you click on the button, show DatePickerDialog that is set with OnDateSetListener
        plan_carddate.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                DatePickerDialog(
                    context!!, R.style.DialogTheme,
                    dateSetListener,
                    // set DatePickerDialog to point to today's date when it loads up
                    c.get(Calendar.YEAR),
                    c.get(Calendar.MONTH),
                    c.get(Calendar.DAY_OF_MONTH)
                ).show()
            }
        })

        plan_verfybt.setOnClickListener {
            //val card = CardUtils.isValidCardNumber("4242424242424242")
            checkValidations()
        }

        plan_cancel.setOnClickListener { addPaymentMethod.dismiss() }

        addPaymentMethod.show()
    }

    private fun checkValidations() {
        when {
            plan_cardholdername.text.isEmpty() -> {
                plan_cardholdername.requestFocus()
                plan_cardholdername.error = "Enter name on card"
            }

            plan_cardnumber.text.isEmpty() -> {
                plan_cardnumber.requestFocus()
                plan_cardnumber.error = "Enter card number"
            }

            plan_carddate.text.isEmpty() -> {
                plan_carddate.requestFocus()
                plan_carddate.error = "Enter expiration date"
            }

            plan_cvc.text.isEmpty() -> {
                plan_cvc.requestFocus()
                plan_cvc.error = "Enter card verification code"
            }

            plan_billingzipcode.text.isEmpty() -> {
                plan_billingzipcode.requestFocus()
                plan_billingzipcode.error = "Enter billing zip code"
            }
            else -> {
                cardholdername = plan_cardholdername.text.toString()
                cardnumber = plan_cardnumber.text.toString()
                cardexpDateyear = plan_carddate.text.toString()
                cardcvc = plan_cvc.text.toString()
                cardbilligcode = plan_billingzipcode.text.toString()
                val dateyear = cardexpDateyear.split("/").toTypedArray()
                Log.d("dateyear", "" + dateyear)
                pd.show()
                pd.setContentView(R.layout.loading)

                val card2 = Card(cardnumber, MONTH, YEAR, cardcvc)
                val stripe1 = Stripe(context!!, Constants.STRIPEKEY)
                stripe1.createToken(card2, object : TokenCallback {
                    override fun onSuccess(token: Token?) {
                        pd.show()
                        controller.AddPaymentMethod(
                            "Bearer " + getStringVal(Constants.TOKEN),
                            card2.brand,
                            "customer",
                            token?.id!!
                        )
                        Log.d("testCard", "" + token)
                    }

                    override fun onError(error: Exception?) {
                        utility!!.relative_snackbar(
                            parent_cardscreen!!,
                            error?.message,
                            getString(R.string.close_up)
                        )
                    }
                })
            }
        }
    }

    private fun updateDateInView() {
        val myFormat = "MM/yyyy" // mention the format you need
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        plan_carddate.setText(sdf.format(c.time))
    }

    override fun onUpdateAvatarResponse(updateAvatarResponse: Response<UpdateProfilePicResponse>) {
        pd.dismiss()
        if (updateAvatarResponse.isSuccessful) {
            if (updateAvatarResponse.body()?.getCode() == 200) {
                editprofile_image.setImageBitmap(bitMap)

            } else {

                utility!!.relative_snackbar(
                    parent_profile,
                    updateAvatarResponse.message(),
                    getString(R.string.close_up)
                )
            }

        } else {

            utility!!.relative_snackbar(
                parent_profile,
                updateAvatarResponse.message(),
                getString(R.string.close_up)
            )
        }
    }

    override fun onUpdateProfileSuccess(updateProfileResponse: Response<EditProfileResponse>) {
        pd.dismiss()
        dialog.dismiss()
        controller.setUserProfileAPI(
            "Bearer " + getStringVal(Constants.TOKEN),
            getStringVal(Constants.USERID)
        )
        if (updateProfileResponse.isSuccessful) {
            if (updateProfileResponse.body()?.getCode().equals("200")) {
                setStringVal(
                    Constants.FIRSTNAME,
                    updateProfileResponse.body()?.getData()?.user?.firstname
                )
                setStringVal(
                    Constants.LASTNAME,
                    updateProfileResponse.body()?.getData()?.user?.lastname
                )
                setStringVal(Constants.EMAIL, updateProfileResponse.body()?.getData()?.user?.email)
                setStringVal(Constants.PHONE, updateProfileResponse.body()?.getData()?.user?.phone)
                setStringVal(
                    Constants.PAYPALID,
                    updateProfileResponse.body()?.getData()?.user?.paypal_email.toString()
                )
                setStringVal(
                    Constants.ABOUT,
                    updateProfileResponse.body()?.getData()?.user?.profile?.aboutMe
                )
                utility!!.relative_snackbar(
                    parent_profile,
                    "Profile Updated",
                    getString(R.string.close_up)
                )
            } else {
                utility!!.relative_snackbar(
                    parent_profile,
                    updateProfileResponse.message(),
                    getString(R.string.close_up)
                )
            }


        } else {
            utility!!.relative_snackbar(
                parent_profile,
                updateProfileResponse.message(),
                getString(R.string.close_up)
            )
        }
    }


    override fun onPaymentSuccess(paymentMethod: Response<PaymentMethodResponse>) {
        pd.dismiss()
        if (paymentMethod.isSuccessful) {
            cards = paymentMethod.body()
                ?.getData()?.paymentProfiles as ArrayList<PaymentMethodResponse.Data.PaymentProfile>
            payment_method_recycler.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            val adapter = CardsAdapter(context!!, cards)
            payment_method_recycler.adapter = adapter
        } else {
            utility!!.relative_snackbar(
                parent_profile,
                paymentMethod.message(),
                getString(R.string.close_up)
            )
        }
    }

    override fun onCancelPlan(cancelPlan: Response<CancelPlanResponse>) {

        if (cancelPlan.isSuccessful) {
            pd.dismiss()
            cancelPlanDialog.dismiss()
            viewplanDialog.dismiss()
            val transaction = manager.beginTransaction()
            transaction.replace(R.id.nav_host_fragment, Profile())
            transaction.addToBackStack(null)
            transaction.commit()
        } else {
            pd.dismiss()
            utility!!.relative_snackbar(
                parent_profile,
                cancelPlan.message(),
                getString(R.string.close_up)
            )

        }
    }

    override fun onAddPaymentSuccess(addPayment: Response<CancelPlanResponse>) {
        pd.dismiss()
        addPaymentMethod.dismiss()
        mypaymentdialog.dismiss()
        utility!!.relative_snackbar(
            parent_profile,
            "Card added successfully",
            getString(R.string.close_up)
        )
    }

    override fun error(error: String?) {
        pd.dismiss()
        utility!!.relative_snackbar(
            parent_profile,
            error,
            getString(R.string.close_up)
        )
        Log.d("testing", "" + error)
    }

    private fun changePassword(
        dialog: Dialog,
        changeNewPass: EditText,
        token: String?,
        userId: String?,
        old_password: String,
        new_pass: String,
        new_cpass: String,
        changepassCurrent: EditText
    ) {

        if (utility.isConnectingToInternet(context)) {
            pd.show()
            pd.setContentView(R.layout.loading)


            val changePassCall = WebAPI.apiInterface?.changePasswordCall(
                token, userId, old_password, new_pass, new_cpass
            )
            changePassCall?.enqueue(object : Callback<JsonObject> {
                override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {

                    pd.dismiss()
                    if (response.isSuccessful) {
                        val responsedata = response.body().toString()
                        val jsonObject = JSONObject(responsedata)
                        try {
                            if (jsonObject.get("code").equals("200")) {
                                pd.dismiss()
                                changePasswordDialog.dismiss()
                                utility.relative_snackbar(
                                    parent_profile!!,
                                    jsonObject.get("message").toString(),
                                    getString(R.string.close_up)
                                )
                            } else if (jsonObject.get("code").equals("401")) {

                                val message = jsonObject.get("message").toString()
                                if (message.equals("Invalid current password.")) {
                                    changepassCurrent.requestFocus()
                                    changepassCurrent.error = message.toString()
                                } else {
                                    val messageArray = jsonObject.getJSONObject("message")
                                    val passValid = messageArray.getJSONArray("password").get(0)
                                    changeNewPass.requestFocus()
                                    changeNewPass.error = passValid.toString()
                                }
                            }

                        } catch (e: Exception) {
                            e.message
                        }
                    } else {
                        utility.relative_snackbar(
                            parent_profile!!,
                            response.message(),
                            getString(R.string.close_up)
                        )
                    }
                }

                override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                    dialog.dismiss()
                    pd.dismiss()
                    utility.relative_snackbar(
                        parent_profile!!,
                        t.toString(),
                        getString(R.string.close_up)
                    )
                }
            })
        }
    }


    override fun getCardID(id: String?) {
        pd.show()
        controller.DeletePaymentCard("Bearer " + getStringVal(Constants.TOKEN), id.toString())
    }

    override fun onDeleteCardSuccess(deleteCard: Response<DeletePaymentMethodResponse>) {
        pd.dismiss()
        if (deleteCard.isSuccessful) {
            controller.SetPaymentMethod("Bearer " + getStringVal(Constants.TOKEN))
        } else {
            utility.relative_snackbar(
                parent_profile!!,
                deleteCard.message(),
                getString(R.string.close_up)
            )
        }

    }

}