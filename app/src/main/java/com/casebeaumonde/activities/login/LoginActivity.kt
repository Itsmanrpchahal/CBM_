package com.casebeaumonde.activities.login

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.casebeaumonde.MainActivity
import com.casebeaumonde.R
import com.casebeaumonde.Retrofit.WebAPI
import com.casebeaumonde.activities.RegisterActivity
import com.casebeaumonde.activities.login.loginResponse.LoginResponse
import com.casebeaumonde.constants.BaseClass
import com.casebeaumonde.constants.Constants
import com.casebeaumonde.utilities.Utility
import com.google.android.material.snackbar.Snackbar
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.app_bar_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.math.log

class LoginActivity : BaseClass() {

    private lateinit var login_Email_ET: EditText
    private lateinit var login_Password_ET: EditText
    private lateinit var login_LoginBT: Button
    private lateinit var login_forgot_TV: TextView
    private lateinit var login_resgiter_TV: TextView
    private lateinit var utility: Utility
    private lateinit var pd: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        findIds()
        lisenters()
    }

    private fun findIds() {
        utility = Utility()
        pd = ProgressDialog(this)
        pd!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        pd!!.window!!.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        pd!!.isIndeterminate = true
        pd!!.setCancelable(false)

        login_Email_ET = findViewById(R.id.login_Email_ET)
        login_Password_ET = findViewById(R.id.login_Password_ET)
        login_LoginBT = findViewById(R.id.login_LoginBT)
        login_forgot_TV = findViewById(R.id.login_forgot_TV)
        login_resgiter_TV = findViewById(R.id.login_resgiter_TV)
    }

    private fun lisenters() {
        login_resgiter_TV.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    RegisterActivity::class.java
                )
            )
            finish()
        }

        login_LoginBT.setOnClickListener {
            checkValidations()
        }
    }

    private fun checkValidations() {
        when {
            login_Email_ET.text.isEmpty() -> {
                login_Email_ET.requestFocus()
                login_Email_ET.error = getString(R.string.enteremail)
            }

            login_Password_ET.text.isEmpty() -> {
                login_Password_ET.requestFocus()
                login_Password_ET.error = getString(R.string.enterpassword)
            }

            login_Password_ET.text.length < 6 -> {
                login_Password_ET.requestFocus()
                login_Password_ET.error = getString(R.string.passwordmesg)
            }
            else -> loginApiCall(login_Email_ET.text.toString(), login_Password_ET.text.toString())
        }
    }

    private fun loginApiCall(username: String, password: String) {
        hideKeyboard()
        if (utility.isConnectingToInternet(this)) {
            pd.show()
            pd.setContentView(R.layout.loading)

            val loginCall = WebAPI.getInstance().api.loginCall(username,password)
            loginCall.enqueue(object :Callback<LoginResponse>
            {
                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    pd.dismiss()
                    utility!!.relative_snackbar(parent_login!!, t.localizedMessage, getString(R.string.close_up))
                     }
                override fun onResponse(
                    call: Call<LoginResponse>,
                    response: Response<LoginResponse>
                ) {
                    pd.dismiss()
                    if (response.isSuccessful){
                        val responsedata = response.body().toString()
                        Log.d("TEST",""+responsedata)
                        val data = response.body()?.getData()?.userId
                        if (response.body()?.getData()?.token != null )
                        {
                            setStringVal(Constants.USERID, response.body()?.getData()?.userId.toString())
                            setStringVal(Constants.TOKEN,response.body()?.getData()?.token)
                            startActivity(Intent(this@LoginActivity,MainActivity::class.java))
                            finish()
                        }else{
                            utility!!.relative_snackbar(parent_login!!, "Invalid Username or Password", getString(R.string.close_up))
                        }
                    }else{
                        utility!!.relative_snackbar(parent_login!!, response.message(), getString(R.string.close_up))
                    }
                }
            })

        }else{
            utility.relative_snackbar(parent_main!!, "No Internet Connectivity", getString(R.string.close_up))
        }
    }

    private fun hideKeyboard() {
        val imm = this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
    }


}
