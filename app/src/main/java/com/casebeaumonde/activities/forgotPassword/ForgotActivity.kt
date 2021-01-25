package com.casebeaumonde.activities.forgotPassword

import android.app.Dialog
import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import com.casebeaumonde.Controller.Controller
import com.casebeaumonde.R
import com.casebeaumonde.activities.login.loginResponse.ForgotPassworResponse
import com.casebeaumonde.activities.register.userRegister.RegisterTypeScreen
import com.casebeaumonde.constants.BaseClass
import com.casebeaumonde.utilities.Utility
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.app_bar_main.*
import retrofit2.Response
import java.lang.Exception

class ForgotActivity : BaseClass(),Controller.FOrgotPasswordAPI {
    private lateinit var utility : Utility
    private lateinit var pd : ProgressDialog
    lateinit var controller: Controller
    private lateinit var dialog: Dialog
    private lateinit var reset_password : Button
    private lateinit var forgot_email_et: EditText
    private lateinit var forgot_signup : TextView
    private lateinit var back: ImageButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot)

        findIds()
        listeners()
    }

    private fun listeners() {
        reset_password.setOnClickListener {
            when {
                forgot_email_et.text.isEmpty() -> {
                    forgot_email_et.requestFocus()
                    forgot_email_et.setError("Enter email")
                } else -> {
                hideKeyboard()
                if (utility.isConnectingToInternet(this)) {
                    pd.show()
                    pd.setContentView(R.layout.loading)
                    controller.setForgotPassword(forgot_email_et.text.toString())
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

        back.setOnClickListener { onBackPressed() }

        forgot_signup.setOnClickListener {
            startActivity(Intent(this,RegisterTypeScreen::class.java).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK))
            finish()
        }
    }

    private fun findIds() {
        utility = Utility()
        pd = ProgressDialog(this)
        pd!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        pd!!.window!!.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        pd!!.isIndeterminate = true
        pd!!.setCancelable(false)
        controller = Controller()
        controller.Controller(this)
        reset_password = findViewById(R.id.reset_password)
        forgot_email_et = findViewById(R.id.forgot_email_et)
        back = findViewById(R.id.back)
        forgot_signup = findViewById(R.id.forgot_signup)
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
        if (forgotPassword.isSuccessful)
        {
            dialog.dismiss()
            utility!!.relative_snackbar(
                parent_register!!,
                forgotPassword.body()?.message,
                getString(R.string.close_up)
            )
        }else {
            utility!!.relative_snackbar(
                parent_register!!,
                forgotPassword.message(),
                getString(R.string.close_up)
            )
        }
    }

    override fun error(error: String?) {
        pd.dismiss()
        utility!!.relative_snackbar(
            parent_register!!,
            error,
            getString(R.string.close_up)
        )
    }
}