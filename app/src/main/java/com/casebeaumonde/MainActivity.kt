package com.casebeaumonde

import android.app.Activity
import android.app.Dialog
import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.Handler
import android.provider.MediaStore
import android.view.Menu
import android.view.MenuItem
import android.view.Window
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.bumptech.glide.Glide
import com.casebeaumonde.Controller.Controller
import com.casebeaumonde.Retrofit.WebAPI
import com.casebeaumonde.activities.login.LoginActivity
import com.casebeaumonde.activities.login.loginResponse.LogoutResponse
import com.casebeaumonde.constants.BaseClass
import com.casebeaumonde.constants.Constants
import com.casebeaumonde.fragments.profile.profileResponse.UserProfileResponse
import com.casebeaumonde.notifications.Notifications
import com.casebeaumonde.notifications.response.NotificationsResponse
import com.casebeaumonde.utilities.Utility
import com.github.dhaval2404.imagepicker.ImagePicker
import com.google.gson.JsonObject
import com.shreyaspatil.material.navigationview.MaterialNavigationView
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.app_bar_main.*
import okhttp3.MultipartBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.nikartm.support.ImageBadgeView
import java.io.File

class MainActivity : BaseClass(), Controller.NotificationAPI, Controller.UserProfileAPI {
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navView: MaterialNavigationView
    private lateinit var opennavigation: ImageView
    private lateinit var frameLayout: NavController
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var cartbadge: ImageBadgeView
    private lateinit var utility: Utility
    private lateinit var pd: ProgressDialog
    lateinit var manager: FragmentManager
    private lateinit var changePasswordDialog: Dialog
    private lateinit var logoutDialog: Dialog
    private lateinit var toolbar_username: TextView
    var status: Int = 0
    private lateinit var toolbar_notifiction: ImageBadgeView
    private lateinit var userImage: CircleImageView
    lateinit var controller: Controller

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        manager = getSupportFragmentManager()

        findIds()

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home,
                R.id.nav_users,
                R.id.nav_pricing,
                R.id.nav_designers,
                R.id.nav_hireanexpert,
                R.id.nav_shop,
                R.id.nav_cart,
                R.id.nav_closets,
                R.id.nav_liveevents,
                R.id.nav_chat,
                R.id.nav_profile,
                R.id.nav_about,
                R.id.nav_contactUs
            ), drawerLayout
        )

        setupActionBarWithNavController(frameLayout, appBarConfiguration)
        navView.setupWithNavController(frameLayout)


        // Show ItemStyle
        println("ItemStyle=${navView.checkedItem}")

        listeners()

    }


    private fun listeners() {
        toolbar_notifiction.setOnClickListener {
            startActivity(Intent(this, Notifications::class.java))
        }

    }

    private fun logout() {
        //hideKeyboard()
        if (utility!!.isConnectingToInternet(this)) {
            pd.show()
            pd.setContentView(R.layout.loading)

            val logoutCall =
                WebAPI.getInstance().api.logoutCall("Bearer " + getStringVal(Constants.TOKEN))
            logoutCall.enqueue(object : retrofit2.Callback<LogoutResponse> {
                override fun onResponse(
                    call: Call<LogoutResponse>,
                    response: Response<LogoutResponse>
                ) {

                    pd.dismiss()
                    if (response.isSuccessful) {
                        val code = response.body()?.getCode()
                        if (code == 200) {
                            startActivity(Intent(this@MainActivity, LoginActivity::class.java))
                            finish()
                            clearStringVal(Constants.TOKEN)
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

    private fun findIds() {
        controller = Controller()
        controller.Controller(this, this)
        controller.setUserProfileAPI(
            "Bearer " + getStringVal(Constants.TOKEN),
            getStringVal(Constants.USERID)
        )


        val ha = Handler()
        ha.postDelayed(object : Runnable {
            override fun run() {
                //call function
                controller.setNotificationAPI(
                    "Bearer " + getStringVal(Constants.TOKEN),
                    getStringVal(Constants.USERID)
                )

                ha.postDelayed(this, 5000)
            }
        }, 10000)

        controller.setUserProfileAPI(
            "Bearer " + getStringVal(Constants.TOKEN),
            getStringVal(Constants.USERID)
        )

        utility = Utility()
        pd = ProgressDialog(this)
        pd!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        pd!!.window!!.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        pd!!.isIndeterminate = true
        pd!!.setCancelable(false)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(true)

        drawerLayout = findViewById(R.id.drawer_layout)
        navView = findViewById(R.id.nav_view)
        frameLayout = findNavController(R.id.nav_host_fragment)
        toolbar_username = findViewById(R.id.toolbar_username)
        toolbar_notifiction = findViewById(R.id.toolbar_notifiction)
        userImage = findViewById(R.id.userImage)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        navView.setItemStyle(MaterialNavigationView.ITEM_STYLE_DEFAULT)
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.logout -> {

                logoutDialog = Dialog(this)
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

            R.id.changepassword_ -> {
                changePasswordDialog = Dialog(this)
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
                            hideKeyboard()
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
        }
        return false
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

        if (utility.isConnectingToInternet(this)) {
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
                                utility!!.relative_snackbar(
                                    parent_main!!,
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
                    }
                }

                override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                    dialog.dismiss()
                    pd.dismiss()
                    utility!!.relative_snackbar(
                        parent_main!!,
                        t.toString(),
                        getString(R.string.close_up)
                    )
                }

            })
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)

        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

    private fun hideKeyboard() {
        val view = this.currentFocus
        if (view != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    override fun onSucess(notificationsResponseResponse: Response<NotificationsResponse>) {
        toolbar_notifiction.setBadgeValue(notificationsResponseResponse.body()!!.data.notification.size)
        toolbar_notifiction.setBadgeTextColor(R.color.colorBlue)
    }

    override fun onPrfileSucess(userProfileResponse: Response<UserProfileResponse>) {
        pd.dismiss()
        if (userProfileResponse.isSuccessful) {
            if (userProfileResponse.body()?.code.equals("200")) {
                toolbar_username.text =
                    userProfileResponse.body()?.data?.user?.firstname + " " + userProfileResponse.body()?.data?.user?.lastname
                Glide.with(this).load(userProfileResponse.body()?.data?.filePath+userProfileResponse.body()?.data?.user?.avatar).placeholder(R.drawable.login_banner).into(userImage)
            }
        }
    }

    override fun error(error: String?) {
        pd.dismiss()
    }
}