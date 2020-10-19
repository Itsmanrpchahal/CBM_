package com.casebeaumonde.fragments.profile

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
import android.view.*
import android.widget.*
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.casebeaumonde.Controller.Controller
import com.casebeaumonde.R
import com.casebeaumonde.Retrofit.WebAPI
import com.casebeaumonde.UpdateProfilePicResponse
import com.casebeaumonde.activities.myGigs.MyGigs
import com.casebeaumonde.activities.myWall.MyWall
import com.casebeaumonde.activities.myclosets.MyClosets
import com.casebeaumonde.activities.notifications.Notifications
import com.casebeaumonde.constants.BaseFrag
import com.casebeaumonde.constants.Constants
import com.casebeaumonde.fragments.pricing.Pricing
import com.casebeaumonde.fragments.profile.IF.GetUserID
import com.casebeaumonde.fragments.profile.adapter.FollowerAdapter
import com.casebeaumonde.fragments.profile.adapter.FollowingAdapter
import com.casebeaumonde.fragments.profile.profileResponse.EditProfileResponse
import com.casebeaumonde.fragments.profile.profileResponse.UserProfileResponse
import com.casebeaumonde.utilities.Utility
import com.github.dhaval2404.imagepicker.ImagePicker
import com.google.android.material.tabs.TabLayout
import com.google.gson.JsonObject
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.fragment_profile.*
import okhttp3.MultipartBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

class Profile : BaseFrag(), Controller.UserProfileAPI, Controller.UpdateAvatarAPI,
    Controller.UpdateProfileAPI, TabLayout.OnTabSelectedListener, GetUserID {

    private lateinit var controller: Controller
    private lateinit var utility: Utility
    private lateinit var pd: ProgressDialog
    private lateinit var profile_username: TextView
    private lateinit var profile_mygigs: Button
    private lateinit var profile_profilePic: CircleImageView
    private lateinit var profile_followerscount: TextView
    private lateinit var profile_followingcount: TextView
    private lateinit var profile_followerslayout: LinearLayout
    private lateinit var profile_followinglayout: LinearLayout
    private lateinit var profile_changetv: ImageView
    private lateinit var part: MultipartBody.Part
    private lateinit var bitMap: Bitmap
    private lateinit var profile_changepassword: Button
    private lateinit var profile_edit_profile: Button
    private lateinit var profile_chooseyourplan : Button
    private lateinit var followbt: Button
    private var path: String = ""
    private lateinit var changePasswordDialog: Dialog
    private lateinit var dialog: Dialog
    private lateinit var role: String
    private var tabLayout: TabLayout? = null
    private lateinit var username: String
    private lateinit var userID: String
    private lateinit var followfollowingDialog: Dialog
    private lateinit var fragmentActivity: FragmentActivity
    private lateinit var businessSubscription: String
    private lateinit var customerSubscription: String
    private lateinit var followers: ArrayList<UserProfileResponse.Data.User.Follower>
    private lateinit var following: ArrayList<UserProfileResponse.Data.User.Following>
    lateinit var manager: FragmentManager
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

        manager = fragmentManager!!
        controller = Controller()
        controller.Controller(this, this, this)
        getUserID = this
        findIds(view)
        listeners()

        controller.setUserProfileAPI(
            "Bearer " + getStringVal(Constants.TOKEN),
            getStringVal(Constants.USERID)
        )
        userID = getStringVal(Constants.USERID).toString()

        return view
    }

    private fun listeners() {
        profile_changetv.setOnClickListener {
            pictureSelectionDialog()
        }

        profile_edit_profile.setOnClickListener {
            editprofile_Dialog(view)
        }

        profile_changepassword.setOnClickListener {

            changePassworddialog()
        }

        profile_mygigs.setOnClickListener {
            startActivity(Intent(context, MyGigs::class.java).putExtra("role", role).putExtra("userID",userID))
        }

        profile_followerslayout.setOnClickListener {
            openFollowersDialog("followers")
        }

        profile_followinglayout.setOnClickListener {
            openFollowersDialog("following")
        }

        profile_chooseyourplan.setOnClickListener {
            val transaction = manager.beginTransaction()
                transaction.replace(R.id.nav_host_fragment, Pricing())
                transaction.commit()
           // startActivity(Intent(context,Pricing::class.java))
        }

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
        val changepassword_changebt: Button
        val changepassword_closebt: Button

        editprofile_firstname = dialog.findViewById(R.id.editprofile_firstname)
        editprofile_lastname = dialog.findViewById(R.id.editprofile_lastname)
        editprofile_email = dialog.findViewById(R.id.editprofile_email)
        editprofile_phone = dialog.findViewById(R.id.editprofile_phone)
        editprofile_about = dialog.findViewById(R.id.editprofile_about)
        changepassword_changebt = dialog.findViewById(R.id.changepassword_changebt)
        changepassword_closebt = dialog.findViewById(R.id.changepassword_closebt)

        editprofile_firstname.setText(getStringVal(Constants.FIRSTNAME))
        editprofile_lastname.setText(getStringVal(Constants.LASTNAME))
        editprofile_email.setText(getStringVal(Constants.EMAIL))
        editprofile_phone.setText(getStringVal(Constants.PHONE))
        editprofile_about.setText(getStringVal(Constants.ABOUT))

        changepassword_closebt.setOnClickListener {
            dialog.dismiss()
        }

        changepassword_changebt.setOnClickListener {
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
                            editprofile_phone.text.toString(),
                            editprofile_about.text.toString(),
                            getStringVal(Constants.USERID).toString()
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
        profile_profilePic = view.findViewById(R.id.profile_profilePic)
        profile_followerscount = view.findViewById(R.id.profile_followerscount)
        profile_followingcount = view.findViewById(R.id.profile_followingcount)
        profile_followerslayout = view.findViewById(R.id.profile_followerslayout)
        profile_followinglayout = view.findViewById(R.id.profile_followinglayout)
        profile_changetv = view.findViewById(R.id.profile_changetv)
        profile_edit_profile = view.findViewById(R.id.profile_edit_profile)
        profile_changepassword = view.findViewById(R.id.profile_changepassword)
        profile_mygigs = view.findViewById(R.id.profile_mygigs)
        profile_chooseyourplan = view.findViewById(R.id.profile_chooseyourplan)
        tabLayout = view.findViewById(R.id.tabLayout)
        pd.show()
        pd.setContentView(R.layout.loading)
        //userID = getStringVal(Constants.USERID).toString()
    }


    override fun onResume() {
        super.onResume()
    }

    companion object {
        var getUserID: GetUserID? = null
    }

    override fun getUserID(id: String?) {

        userID = id.toString()
        startActivity(Intent(context,ViewProfile::class.java).putExtra("userID",userID))
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
            bitMap = MediaStore.Images.Media.getBitmap(context?.contentResolver, fileUri)
            part = Utility.sendImageFileToserver(context?.filesDir, bitMap, "image")

            updateAvatar(
                part,
                "Bearer " + getStringVal(Constants.TOKEN),
                getStringVal(Constants.USERID)
            )
        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            utility!!.relative_snackbar(
                parent_main!!,
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
        Log.d("userprofilerespose", "" + userProfileResponse.body()?.data)

        profile_username.text =
            userProfileResponse.body()?.data?.user?.firstname + " " + userProfileResponse.body()?.data?.user?.lastname
        Glide.with(context!!)
            .load(userProfileResponse.body()?.data?.filePath + userProfileResponse.body()?.data?.user?.avatar)
            .placeholder(R.drawable.login_banner).into(profile_profilePic)
        profile_followerscount.text =
            userProfileResponse.body()?.data?.user?.followers?.size.toString()
        profile_followingcount.text =
            userProfileResponse.body()?.data?.user?.following?.size.toString()
        username = userProfileResponse.body()?.data?.user?.firstname!!
        setStringVal(Constants.FIRSTNAME, userProfileResponse.body()?.data?.user?.firstname)
        setStringVal(Constants.LASTNAME, userProfileResponse.body()?.data?.user?.lastname)
        setStringVal(Constants.EMAIL, userProfileResponse.body()?.data?.user?.email)
        setStringVal(Constants.PHONE, userProfileResponse.body()?.data?.user?.phone)
        setStringVal(
            Constants.ABOUT,
            userProfileResponse.body()?.data?.user?.profile?.aboutMe.toString()
        )

        followers =
            userProfileResponse.body()?.data?.user?.followers as ArrayList<UserProfileResponse.Data.User.Follower>
        following =
            userProfileResponse.body()?.data?.user?.following as ArrayList<UserProfileResponse.Data.User.Following>

        role = userProfileResponse.body()?.data?.user?.role.toString()

        if (userProfileResponse.body()?.data?.user?.customerSubscription !=null)
        {
            setStringVal(Constants.CUSTOMERSUBSCRIPTION,"1")
        }

        if (userProfileResponse.body()?.data?.user?.businessSubscription !=null)
        {
            setStringVal(Constants.BUSSINESSSUBSSCRIPTION,"1")
        }
        if (userProfileResponse.body()?.data?.user?.customerSubscription != null || userProfileResponse.body()?.data?.user?.businessSubscription != null) {
            profile_mygigs.visibility = View.VISIBLE


            tabLayout!!.addTab(tabLayout!!.newTab().setText("My Wall"))
            tabLayout!!.addTab(tabLayout!!.newTab().setText("My gigs"))
            tabLayout!!.addTab(tabLayout!!.newTab().setText("Notifications"))
            tabLayout!!.addTab(tabLayout!!.newTab().setText("Work Invitations"))
            tabLayout!!.addTab(tabLayout!!.newTab().setText("Offers"))
            tabLayout!!.addTab(tabLayout!!.newTab().setText("Contracts"))
            tabLayout!!.addTab(tabLayout!!.newTab().setText("My Closets"))
            tabLayout!!.addTab(tabLayout!!.newTab().setText("My Events"))
            tabLayout!!.addTab(tabLayout!!.newTab().setText("Events Invitations"))
            tabLayout!!.tabGravity = TabLayout.GRAVITY_FILL
            tabLayout!!.setOnTabSelectedListener(this)
        } else {
            profile_mygigs.visibility = View.GONE

            tabLayout!!.addTab(tabLayout!!.newTab().setText("My Wall"))
            tabLayout!!.addTab(tabLayout!!.newTab().setText("Notifications"))
            tabLayout!!.addTab(tabLayout!!.newTab().setText("My Closets"))
            tabLayout!!.tabGravity = TabLayout.GRAVITY_FILL
            tabLayout!!.setOnTabSelectedListener(this)
        }
    }

    override fun onUpdateAvatarResponse(updateAvatarResponse: Response<UpdateProfilePicResponse>) {
        pd.dismiss()
        if (updateAvatarResponse.isSuccessful) {
            if (updateAvatarResponse.body()?.code == 200) {
                profile_profilePic.setImageBitmap(bitMap)
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
            setStringVal(Constants.FIRSTNAME, updateProfileResponse.body()?.data?.user?.firstname)
            setStringVal(Constants.LASTNAME, updateProfileResponse.body()?.data?.user?.lastname)
            setStringVal(Constants.EMAIL, updateProfileResponse.body()?.data?.user?.email)
            setStringVal(Constants.PHONE, updateProfileResponse.body()?.data?.user?.phone)
            setStringVal(
                Constants.ABOUT,
                updateProfileResponse.body()?.data?.user?.profile?.aboutMe
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

    }

    override fun error(error: String?) {
        pd.dismiss()
        utility!!.relative_snackbar(
            parent_profile,
            error,
            getString(R.string.close_up)
        )
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


            val changePassCall = WebAPI.getInstance().api.changePasswordCall(
                token, userId, old_password, new_pass, new_cpass
            )
            changePassCall.enqueue(object : Callback<JsonObject> {
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

    override fun onTabSelected(p0: TabLayout.Tab?) {
        when {
            p0?.text?.equals("My Closets")!! -> {
                startActivity(Intent(context, MyClosets::class.java).putExtra("userID", userID))
            }

            p0?.text?.equals("My gigs")!! -> {
                startActivity(Intent(context, MyGigs::class.java).putExtra("role", role).putExtra("userID",userID))
            }

            p0?.text?.equals("My Wall")!! -> {
                startActivity(Intent(context, MyWall::class.java).putExtra("userID", userID))
            }

            p0?.text?.equals("Notifications")!! -> {
                startActivity(Intent(context, Notifications::class.java).putExtra("userID", userID))
            }
        }
    }

    override fun onTabUnselected(p0: TabLayout.Tab?) {
        // Toast.makeText(context,"HERE "+p0,Toast.LENGTH_SHORT).show()
    }

    override fun onTabReselected(p0: TabLayout.Tab?) {
        when {
            p0?.text?.equals("My Closets")!! -> {
                startActivity(Intent(context, MyClosets::class.java).putExtra("userID", userID))
            }

            p0?.text?.equals("My gigs")!! -> {
                startActivity(Intent(context, MyGigs::class.java).putExtra("role", role).putExtra("userID",userID))
            }

            p0?.text?.equals("My Wall")!! -> {
                startActivity(Intent(context, MyWall::class.java).putExtra("userID", userID))
            }

            p0?.text?.equals("Notifications")!! -> {
                startActivity(Intent(context, Notifications::class.java).putExtra("userID", userID))
            }
        }
    }


}