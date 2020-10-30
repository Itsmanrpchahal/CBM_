package com.casebeaumonde.activities.paymentScreen

import android.app.DatePickerDialog
import android.app.ProgressDialog
import android.content.pm.PackageManager
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.casebeaumonde.Controller.Controller
import com.casebeaumonde.R
import com.casebeaumonde.activities.paymentScreen.response.SubscribePlanResponse
import com.casebeaumonde.constants.BaseClass
import com.casebeaumonde.constants.Constants
import com.casebeaumonde.utilities.Utility
import com.stripe.android.PaymentConfiguration
import com.stripe.android.Stripe
import com.stripe.android.TokenCallback
import com.stripe.android.model.Card
import com.stripe.android.model.Token
import kotlinx.android.synthetic.main.activity_card_detail_screen.*
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*
import javax.security.auth.callback.Callback

class CardDetailScreen : BaseClass(), Controller.SubscribePlanAPI {


    private lateinit var utility: Utility
    private lateinit var pd: ProgressDialog
    private lateinit var controller: Controller
    private lateinit var plan_carddate: EditText
    private lateinit var plan_name: TextView
    private lateinit var plan_price: TextView
    private lateinit var plan_cardholdername: EditText
    private lateinit var plan_cardnumber: EditText
    private lateinit var plan_cvc: EditText
    private lateinit var plan_billingzipcode: EditText
    private lateinit var plan_verfybt: Button
    private lateinit var plan_cancel: Button
    private lateinit var back_carddetail: ImageButton
    private val PUBLISHABLE_KEY = "pk_test_OTNta0F2CKTUpYDDM7igKdml"
    private lateinit var planname: String
    private lateinit var planprice: String
    private lateinit var planID: String
    private lateinit var plantype: String
    private lateinit var cardholdername: String
    private lateinit var cardnumber: String
    private lateinit var cardexpDateyear: String
    private lateinit var cardcvc: String
    private lateinit var cardbilligcode: String
    private var MONTH: Int = 0
    private var YEAR: Int = 0
    var token: Token? = null
    val c = Calendar.getInstance()
    lateinit var stripe: Stripe


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card_detail_screen)
        planname = intent.getStringExtra("planname")!!
        planprice = intent.getStringExtra("planprice")!!
        planID = intent.getStringExtra("planID")!!
        plantype = intent.getStringExtra("plantype")!!
        controller = Controller()
        controller.Controller(this)
        findIds()
        plan_name.setText(planname)
        plan_price.setText("$ " + planprice + "/month")
        listeners()
    }

    private fun listeners() {
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
                    this@CardDetailScreen, R.style.DialogTheme,
                    dateSetListener,
                    // set DatePickerDialog to point to today's date when it loads up
                    c.get(Calendar.YEAR),
                    c.get(Calendar.MONTH),
                    c.get(Calendar.DAY_OF_MONTH)
                ).show()
            }
        })

        back_carddetail.setOnClickListener {
            onBackPressed()
        }

        plan_verfybt.setOnClickListener {
            //val card = CardUtils.isValidCardNumber("4242424242424242")
            checkValidations()
        }
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
                val stripe1 = Stripe(this, "pk_test_OTNta0F2CKTUpYDDM7igKdml")
                stripe1.createToken(card2, object : TokenCallback {
                    override fun onSuccess(token: Token?) {

                        Log.d("token", "" + token?.type + "  ")
                        Log.d(
                            "card",
                            "" + card2.brand + "  " + MONTH + "/" + YEAR + " " + card2.last4 + "  " + cardholdername + " " + planID + "  " + plantype
                        )

                        controller.SubscribePlan(
                            "Bearer " + getStringVal(Constants.TOKEN),
                            card2.brand,
                            "monthly",
                            MONTH.toString() + "/" + YEAR,
                            card2.last4,
                            cardholdername,
                            "card",
                            planID.toString(),
                            token.toString(),
                            "customer"
                        )
                    }

                    override fun onError(error: Exception?) {
                        Toast.makeText(this@CardDetailScreen, "Error" + error, Toast.LENGTH_SHORT)
                            .show()
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

    private fun findIds() {
        utility = Utility()
        pd = ProgressDialog(this)
        pd!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        pd!!.window!!.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        pd!!.isIndeterminate = true
        pd!!.setCancelable(false)
        plan_carddate = findViewById(R.id.plan_carddate)
        plan_name = findViewById(R.id.plan_name)
        plan_price = findViewById(R.id.plan_price)
        plan_cardholdername = findViewById(R.id.plan_cardholdername)
        plan_cardnumber = findViewById(R.id.plan_cardnumber)
        plan_billingzipcode = findViewById(R.id.plan_billingzipcode)
        plan_verfybt = findViewById(R.id.plan_verfybt)
        plan_cvc = findViewById(R.id.plan_cvc)
        plan_cancel = findViewById(R.id.plan_cancel)
        back_carddetail = findViewById(R.id.back_carddetail)

        if (ContextCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            val permissions = arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION)
            // REQUEST_CODE_LOCATION should be defined on your app level
            ActivityCompat.requestPermissions(this, permissions, 101)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        if (requestCode == 101 && grantResults.isNotEmpty()
            && grantResults[0] != PackageManager.PERMISSION_GRANTED
        ) {
            throw RuntimeException("Location services are required in order to " + "connect to a reader.")
        }
    }

    override fun onSubscribeSuccess(subscribe: Response<SubscribePlanResponse>) {
        pd.dismiss()
        utility!!.relative_snackbar(
            parent_cardscreen!!,
            subscribe.body()?.code,
            getString(R.string.close_up)
        )
    }

    override fun error(error: String?) {
        pd.dismiss()
        utility!!.relative_snackbar(
            parent_cardscreen!!,
            error,
            getString(R.string.close_up)
        )
    }
}