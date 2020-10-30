package com.casebeaumonde

import android.app.Dialog
import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.Handler
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.appcompat.widget.Toolbar
import androidx.core.view.get
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
import com.casebeaumonde.activities.OnBoardScreen
import com.casebeaumonde.activities.login.LoginActivity
import com.casebeaumonde.activities.login.loginResponse.LogoutResponse
import com.casebeaumonde.constants.BaseClass
import com.casebeaumonde.constants.Constants
import com.casebeaumonde.fragments.profile.profileResponse.UserProfileResponse
import com.casebeaumonde.activities.notifications.Notifications
import com.casebeaumonde.activities.notifications.response.NotificationsResponse
import com.casebeaumonde.utilities.Utility
import com.google.android.material.appbar.AppBarLayout
import com.shreyaspatil.material.navigationview.MaterialNavigationView
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.changeplandialog.view.*
import retrofit2.Call
import retrofit2.Response
import ru.nikartm.support.ImageBadgeView
import java.lang.Exception

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
    private lateinit var logoutDialog: Dialog
    private lateinit var toolbar_username: TextView
    var status: Int = 0
    private lateinit var toolbar_notifiction: ImageBadgeView
    private lateinit var userImage: CircleImageView
    lateinit var controller: Controller
    lateinit var toolbarrelative: RelativeLayout
    lateinit var appbarmain: AppBarLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        manager = getSupportFragmentManager()

        findIds()


        if (getStringVal(Constants.TOKEN).equals("")) {
            navView.inflateMenu(R.menu.activity_main_drawer1)
            navView.visibility = View.GONE
            appbarmain.visibility = View.GONE
            appBarConfiguration = AppBarConfiguration(
                setOf(
                    R.id.nav_home,
                    R.id.nav_pricing,
                    R.id.nav_shop,
                    R.id.nav_about,
                    R.id.nav_contactUs
                ), drawerLayout
            )
            toolbarrelative.visibility = View.GONE
            // setupActionBarWithNavController(frameLayout, appBarConfiguration)
            // navView.setupWithNavController(frameLayout)

        } else {
            navView.inflateMenu(R.menu.activity_main_drawer)
            appBarConfiguration = AppBarConfiguration(
                setOf(
                    R.id.nav_profile,
                    R.id.nav_users,
                    R.id.nav_pricing,
                    R.id.nav_designers,
                    R.id.nav_hireanexpert,
                    R.id.nav_shop,
                    R.id.nav_cart,
                    R.id.nav_closets,
                    R.id.nav_liveevents,
                    R.id.nav_chat,
                    R.id.nav_home,
                    R.id.nav_about,
                    R.id.nav_contactUs
                ), drawerLayout
            )
        }

        setupActionBarWithNavController(frameLayout, appBarConfiguration)
        navView.setupWithNavController(frameLayout)


        //navView.setNavigationItemSelectedListener(this)
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
                            startActivity(
                                Intent(
                                    this@MainActivity,
                                    LoginActivity::class.java
                                ).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                            )
                            finish()
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

    private fun findIds() {
        controller = Controller()
        controller.Controller(this, this)

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
        toolbarrelative = findViewById(R.id.toolbarrelative)
        appbarmain = findViewById(R.id.appbarmain)

        val ha = Handler()
        ha.postDelayed(object : Runnable {
            override fun run() {
                //call function
                controller.setUserProfileAPI(
                    "Bearer " + getStringVal(Constants.TOKEN),
                    getStringVal(Constants.USERID)
                )
                ha.postDelayed(this, 10000)
            }
        }, 10000)

        controller.setNotificationAPI(
            "Bearer " + getStringVal(Constants.TOKEN),
            getStringVal(Constants.USERID)
        )

        controller.setUserProfileAPI(
            "Bearer " + getStringVal(Constants.TOKEN),
            getStringVal(Constants.USERID)
        )
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        if (!getStringVal(Constants.TOKEN).equals("")) {
            navView.setItemStyle(MaterialNavigationView.ITEM_STYLE_DEFAULT)
            menuInflater.inflate(R.menu.menu, menu)
        }

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
        }
        return false
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)

        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }


    private fun hideKeyboard() {
        val view = this.currentFocus
        if (view != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }


    override fun onPrfileSucess(userProfileResponse: Response<UserProfileResponse>) {
        pd.dismiss()
        if (userProfileResponse.isSuccessful) {
            if (userProfileResponse.body()?.code.equals("200")) {
                toolbar_username.text =
                    userProfileResponse.body()?.data?.user?.firstname + " " + userProfileResponse.body()?.data?.user?.lastname
                try {
                    Glide.with(this@MainActivity)
                        .load(userProfileResponse.body()!!.data!!.filePath + userProfileResponse.body()!!.data!!.user!!.avatar)
                        .placeholder(R.drawable.login_banner).into(userImage)
                } catch (e: Exception) {

                }

            }
        }
    }

    override fun onSucess(notificationsResponseResponse: Response<NotificationsResponse>) {

        if (notificationsResponseResponse.isSuccessful) {
            toolbar_notifiction.setBadgeValue(notificationsResponseResponse.body()?.data?.notification?.size!!)
            toolbar_notifiction.setBadgeTextColor(resources.getColor(R.color.colorBlue))
        }
    }

    override fun error(error: String?) {
        pd.dismiss()
    }

//    override fun onNavigationItemSelected(p0: MenuItem): Boolean {
//        menuItem = p0
//        when (p0.itemId) {
//            R.id.nav_home -> {
//                val transaction = manager.beginTransaction()
//                transaction.replace(R.id.nav_host_fragment, HomeFragment())
//                transaction.commit()
//                drawerLayout.closeDrawers()
//                status = 0
//            }
//
//            R.id.nav_users -> {
//                val transaction = manager.beginTransaction()
//                transaction.replace(R.id.nav_host_fragment, Users())
//                transaction.commit()
//                drawerLayout.closeDrawers()
//                status = 1
//            }
//        }
//        return true
//    }

}