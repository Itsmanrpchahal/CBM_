package com.casebeaumonde

import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.WindowManager
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.core.view.get
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.casebeaumonde.Retrofit.WebAPI
import com.casebeaumonde.activities.login.LoginActivity
import com.casebeaumonde.activities.login.loginResponse.LogoutResponse
import com.casebeaumonde.constants.BaseClass
import com.casebeaumonde.constants.Constants
import com.casebeaumonde.fragments.HomeFragment
import com.casebeaumonde.fragments.users.Users
import com.casebeaumonde.utilities.Utility
import com.shreyaspatil.material.navigationview.MaterialNavigationView
import retrofit2.Call
import retrofit2.Response
import ru.nikartm.support.ImageBadgeView

class MainActivity : BaseClass() {
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navView: MaterialNavigationView
    private lateinit var opennavigation: ImageView
    private lateinit var frameLayout: NavController
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var cartbadge: ImageBadgeView
    private lateinit var utility: Utility
    private lateinit var pd: ProgressDialog
    lateinit var manager: FragmentManager
    var status: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        manager = getSupportFragmentManager();

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
                R.id.nav_contactUs,
                R.id.nav_Logout
            ), drawerLayout
        )


        navView.setNavigationItemSelectedListener { menuItem ->
            when(menuItem.itemId){
                R.id.nav_Logout ->
                {
                    logout()
                }
            }
            drawerLayout.closeDrawer(GravityCompat.START)
            true

        }


        setupActionBarWithNavController(frameLayout, appBarConfiguration)
        navView.setupWithNavController(frameLayout)




        // Show ItemStyle
        println("ItemStyle=${navView.checkedItem}")

    }

    private fun logout() {
        //hideKeyboard()
        if (utility.isConnectingToInternet(this)) {
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
                    Toast.makeText(this@MainActivity, "" + t.message, Toast.LENGTH_LONG).show()
                }

            })
        }
    }

    private fun findIds() {
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
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        navView.setItemStyle(MaterialNavigationView.ITEM_STYLE_DEFAULT)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return false
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)

        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onBackPressed() {


        super.onBackPressed()


    }
}