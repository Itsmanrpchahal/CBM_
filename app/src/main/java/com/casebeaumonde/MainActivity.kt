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
import com.casebeaumonde.activities.b_questionaries.B_GetStartedActivity
import com.casebeaumonde.activities.login.loginResponse.LogoutResponse
import com.casebeaumonde.activities.notifications.Notifications
import com.casebeaumonde.activities.notifications.response.NotificationsResponse
import com.casebeaumonde.activities.questionaries.GetStartedActivity
import com.casebeaumonde.constants.BaseClass
import com.casebeaumonde.constants.Constants
import com.casebeaumonde.fragments.cart.Cart
import com.casebeaumonde.fragments.profile.profileResponse.UserProfileResponse
import com.casebeaumonde.utilities.Utility
import com.google.android.material.appbar.AppBarLayout
import com.shreyaspatil.material.navigationview.MaterialNavigationView
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.changeplandialog.view.*
import retrofit2.Call
import retrofit2.Response
import ru.nikartm.support.ImageBadgeView


class MainActivity : BaseClass(), Controller.NotificationAPI, Controller.UserProfileAPI {
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navView: MaterialNavigationView
    private lateinit var opennavigation: ImageView
    private lateinit var frameLayout: NavController
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var toolbar_cart: ImageBadgeView
    private lateinit var utility: Utility
    private lateinit var toolbar: Toolbar
    private lateinit var pd: ProgressDialog
    lateinit var manager: FragmentManager
    private lateinit var logoutDialog: Dialog
    private lateinit var toolbar_username: TextView
    var status: Int = 0
    private lateinit var toolbar_notifiction: ImageBadgeView
    private lateinit var userImage: ImageView
    lateinit var controller: Controller
    lateinit var toolbarrelative: RelativeLayout
    lateinit var appbarmain: AppBarLayout

    //test
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        manager = getSupportFragmentManager()

        findIds()
        listeners()

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
                    R.id.nav_contractor,
                    R.id.nav_chat,
                    R.id.nav_home,
                    R.id.nav_about,
                    R.id.nav_contactUs,
                    R.id.nav_products
                ), drawerLayout
            )

            if (getStringVal(Constants.USER_ROLE).equals("customer")) {
                navView.getMenu().findItem(R.id.nav_contractor).isVisible = false
            }
            navView.getMenu().findItem(R.id.nav_logout).setOnMenuItemClickListener({ menuItem ->
                logoutDialog()
                true
            })



            // Toast.makeText(this,""+getStringVal(Constants.USER_ROLE),Toast.LENGTH_SHORT).show()
            if (getStringVal(Constants.USER_ROLE).equals("customer") && getStringVal(Constants.QUESTIONARIES_STATUS).equals(
                    "0"
                )
            ) {
                startActivity(Intent(this, GetStartedActivity::class.java))
            } else {
                if (getStringVal(Constants.QUESTIONARIES_STATUS).equals("0")) {
                    startActivity(Intent(this, B_GetStartedActivity::class.java))
                }
                // startActivity(Intent(this, Biz_Questionaries1::class.java))

            }
        }

        setupActionBarWithNavController(frameLayout, appBarConfiguration)
        navView.setupWithNavController(frameLayout)


        //navView.setNavigationItemSelectedListener(this)
        // Show ItemStyle
        println("ItemStyle=${navView.checkedItem}")

    }

    override fun onResume() {
        super.onResume()
    }


    private fun listeners() {
        toolbar_notifiction.setOnClickListener {
            startActivity(Intent(this, Notifications::class.java))
        }

        toolbar_cart.setOnClickListener {
            manager.popBackStack()
            manager.beginTransaction().replace(R.id.nav_host_fragment, Cart()).addToBackStack(null)
                .commit()

        }
    }

    private fun logout() {
        //hideKeyboard()
        if (utility!!.isConnectingToInternet(this)) {
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
                                    this@MainActivity,
                                    MainActivity::class.java
                                ).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                                    .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                                    .setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)

                            )
                            finish()
                            //finish()
                            overridePendingTransition(0, 0)
                            overridePendingTransition(0, 0)

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
        toolbar_cart = findViewById(R.id.toolbar_cart)

        val ha = Handler()
        ha.postDelayed(object : Runnable {
            override fun run() {
                //call function
                controller.setUserProfileAPI(
                    "Bearer " + getStringVal(Constants.TOKEN),
                    getStringVal(Constants.USERID)
                )

                controller.setNotificationAPI(
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
        pd.show()
        pd.setContentView(R.layout.loading)

        controller.setUserProfileAPI(
            "Bearer " + getStringVal(Constants.TOKEN),
            getStringVal(Constants.USERID)
        )
    }

//    override fun onCreateOptionsMenu(menu: Menu): Boolean {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        if (!getStringVal(Constants.TOKEN).equals("")) {
//            navView.setItemStyle(MaterialNavigationView.ITEM_STYLE_DEFAULT)
//            menuInflater.inflate(R.menu.menu, menu)
//        }
//
//        return true
//    }

//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//
//        when (item.itemId) {
//            R.id.logout -> {
//
//              logoutDialog()
//
//            }
//        }
//        return false
//    }

    fun logoutDialog() {
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
            if (userProfileResponse.body()?.getCode().equals("200")) {


                toolbar_username.text =
                    userProfileResponse.body()
                        ?.getData()?.user?.firstname + " " + userProfileResponse.body()
                        ?.getData()?.user?.lastname
                try {
                    Glide.with(this@MainActivity).asBitmap()
                        .skipMemoryCache(true)
                        .load(
                            userProfileResponse.body()!!
                                .getData()!!.filePath + userProfileResponse.body()!!
                                .getData()!!.user!!.avatar
                        )
                        .placeholder(R.drawable.login_banner1).into(userImage)
                } catch (e: Exception) {

                }

            }
        }
    }

    override fun onSucess(notificationsResponseResponse: Response<NotificationsResponse>) {

        if (notificationsResponseResponse.isSuccessful) {
            if( notificationsResponseResponse.body()?.getData()?.notification?.size!!>=1)
            {

            }

            toolbar_notifiction.setBadgeValue(
                notificationsResponseResponse.body()?.getData()?.notification?.size!!
            )
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