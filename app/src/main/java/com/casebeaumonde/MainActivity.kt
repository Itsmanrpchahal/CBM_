package com.casebeaumonde

import android.app.Dialog
import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.Editable
import android.text.Layout
import android.view.*
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.FragmentManager
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
import com.casebeaumonde.utilities.Utility
import com.shreyaspatil.material.navigationview.MaterialNavigationView
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.app_bar_main.*
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
    private lateinit var changePasswordDialog : Dialog
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
                R.id.nav_contactUs
            ), drawerLayout
        )

        setupActionBarWithNavController(frameLayout, appBarConfiguration)
        navView.setupWithNavController(frameLayout)


        // Show ItemStyle
        println("ItemStyle=${navView.checkedItem}")

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
                    utility!!.relative_snackbar(parent_main!!, t.localizedMessage, getString(R.string.close_up))
                }

            })
        }else{
            utility.relative_snackbar(parent_main!!, "No Internet Connectivity", getString(R.string.close_up))
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
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId)
        {
            R.id.logout -> {
                logout()
            }

            R.id.changepassword_ ->
            {
                changePasswordDialog = Dialog(this)
                changePasswordDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
                changePasswordDialog.setCancelable(false)
                changePasswordDialog.setContentView(R.layout.changepassword)
                val window = changePasswordDialog.window
                window?.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);

                val changepassword_closebt : Button
                val changepassword_changebt : Button
                val changepass_current : EditText
                val changepassword_newPassword : EditText
                val changepassword_CnewPassword : EditText

                changepassword_closebt = changePasswordDialog.findViewById(R.id.changepassword_closebt)
                changepassword_changebt = changePasswordDialog.findViewById(R.id.changepassword_changebt)
                changepass_current = changePasswordDialog.findViewById(R.id.changepass_current)
                changepassword_newPassword = changePasswordDialog.findViewById(R.id.changepassword_newPassword)
                changepassword_CnewPassword = changePasswordDialog.findViewById(R.id.changepassword_CnewPassword)

                changepassword_closebt.setOnClickListener {
                    changePasswordDialog.dismiss()
                }

                changepassword_changebt.setOnClickListener {
                    when{
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

                        !changepassword_newPassword.text.toString().equals(changepassword_CnewPassword.text.toString()) -> {
                            changepassword_CnewPassword.requestFocus()
                            changepassword_CnewPassword.error = getString(R.string.passwordnotmatch)

                        }

                        changepassword_newPassword.text.toString().length<8 &&!Utility.isValidPassword(changepassword_newPassword.text.toString())->
                        {
                            changepassword_newPassword.requestFocus()
                            changepassword_newPassword.error = getString(R.string.strongpass)
                        }
                        else -> {

                        }


                    }
                }


                changePasswordDialog.show()
            }
        }
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