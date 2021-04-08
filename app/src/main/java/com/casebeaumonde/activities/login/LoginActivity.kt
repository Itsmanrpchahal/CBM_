package com.casebeaumonde.activities.login

import android.app.Dialog
import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.view.Window
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.*
import com.casebeaumonde.Controller.Controller
import com.casebeaumonde.MainActivity
import com.casebeaumonde.R
import com.casebeaumonde.Retrofit.WebAPI
import com.casebeaumonde.activities.forgotPassword.ForgotActivity
import com.casebeaumonde.activities.login.loginResponse.ForgotPassworResponse
import com.casebeaumonde.activities.login.loginResponse.LoginResponse
import com.casebeaumonde.activities.register.userRegister.RegisterActivity
import com.casebeaumonde.constants.BaseClass
import com.casebeaumonde.constants.Constants
import com.casebeaumonde.utilities.Utility
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.app_bar_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : BaseClass(), Controller.FOrgotPasswordAPI {

    private lateinit var login_Email_ET: EditText
    private lateinit var login_Password_ET: EditText
    private lateinit var rememberme_checkbox: CheckBox
    private lateinit var login_LoginBT: Button
    private lateinit var login_forgot_TV: TextView
    private lateinit var login_resgiter_TV: TextView
    private lateinit var back: ImageButton
    private lateinit var utility: Utility
    private lateinit var pd: ProgressDialog
    private lateinit var dialog: Dialog
    lateinit var controller: Controller
    private lateinit var see_password: CheckBox
    private lateinit var from: String
    private var remember: String = "0"
//Test
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        controller = Controller()
        controller.Controller(this)
        utility = Utility()
        pd = ProgressDialog(this)
        pd!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        pd!!.window!!.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        pd!!.isIndeterminate = true
        pd!!.setCancelable(false)
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

        back = findViewById(R.id.back)
        login_Email_ET = findViewById(R.id.login_Email_ET)
        login_Password_ET = findViewById(R.id.login_Password_ET)
        login_LoginBT = findViewById(R.id.login_LoginBT)
        login_forgot_TV = findViewById(R.id.login_forgot_TV)
        login_resgiter_TV = findViewById(R.id.login_resgiter_TV)
        rememberme_checkbox = findViewById(R.id.rememberme_checkbox)
        see_password = findViewById(R.id.see_password)
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

        login_forgot_TV.setOnClickListener {
//            forgotPassword()
            startActivity(Intent(this, ForgotActivity::class.java))
        }

        back.setOnClickListener { onBackPressed() }

        rememberme_checkbox.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                remember = "1"
            } else {
                remember = "0"
            }
        }

        see_password.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked)
            {
                login_Password_ET.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                login_Password_ET.setSelection(login_Password_ET.text.length)
            } else {
                login_Password_ET.inputType =
                    InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                login_Password_ET.setSelection(login_Password_ET.text.length)
            }
        }
    }

    private fun forgotPassword() {
        dialog = Dialog(this!!)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.forgotpasswordlayout)

        val forgot_email: EditText
        val forgot_submitbt: Button
        forgot_email = dialog.findViewById(R.id.forgot_email)
        forgot_submitbt = dialog.findViewById(R.id.forgot_submitbt)

        forgot_submitbt.setOnClickListener {
            when {
                forgot_email.text.isEmpty() -> {
                    forgot_email.requestFocus()
                    forgot_email.error = "Enter email"
                }
                else -> {
                    hideKeyboard()
                    if (utility.isConnectingToInternet(this)) {
                        pd.show()
                        pd.setContentView(R.layout.loading)
                        controller.setForgotPassword(forgot_email.text.toString())
                    } else {
                        utility.relative_snackbar(
                            parent_main!!,
                            "No Internet Connectivity",
                            getString(R.string.close_up)
                        )
                    }
                }
            }
        }

        dialog.show()
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

            val loginCall = WebAPI.apiInterface?.loginCall(username, password)
            loginCall?.enqueue(object : Callback<LoginResponse> {
                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    pd.dismiss()
                    utility!!.relative_snackbar(
                        parent_login!!,
                        t.localizedMessage,
                        getString(R.string.close_up)
                    )
                }

                override fun onResponse(
                    call: Call<LoginResponse>,
                    response: Response<LoginResponse>
                ) {
                    pd.dismiss()
                    if (response.isSuccessful) {
                        val responsedata = response.body().toString()
                        Log.d("TEST", "" + responsedata)
                        //val data = response.body()?.data?.user_id
                        if (response.body()?.data?.token != null) {


                            setStringVal(
                                Constants.USERID,
                                response.body()?.data?.user?.id.toString()
                            )
                            setStringVal(Constants.TOKEN, response.body()?.data?.token)
                            setStringVal(Constants.REMEMBERME, remember)
                            setStringVal(
                                Constants.USER_ROLE,
                                response.body()?.data?.user?.role?.toString()
                            )
                            setStringVal(
                                Constants.QUESTIONARIES_STATUS,
                                response.body()?.data?.questionnaire.toString()
                            )

                            startActivity(
                                Intent(
                                    this@LoginActivity,
                                    MainActivity::class.java
                                ).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                                    .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                            )

                            finish()
                        } else {
                            utility!!.relative_snackbar(
                                parent_login!!,
                                "Invalid Username or Password",
                                getString(R.string.close_up)
                            )
                        }
                    } else {
                        utility!!.relative_snackbar(
                            parent_login!!,
                            response.message(),
                            getString(R.string.close_up)
                        )
                    }
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

    private fun hideKeyboard() {
        try {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
        } catch (e: Exception) {

        }

    }

    override fun onForgotPasswordSuccess(forgotPassword: Response<ForgotPassworResponse>) {
        pd.dismiss()
        if (forgotPassword.isSuccessful) {
            dialog.dismiss()
            utility!!.relative_snackbar(
                parent_login!!,
                forgotPassword.body()?.getMessage(),
                getString(R.string.close_up)
            )
        } else {
            utility!!.relative_snackbar(
                parent_login!!,
                forgotPassword.message(),
                getString(R.string.close_up)
            )
        }
    }

    override fun error(error: String?) {
        pd.dismiss()
        utility!!.relative_snackbar(
            parent_login!!,
            error,
            getString(R.string.close_up)
        )
    }
}
