package com.casebeaumonde.activities.paymentScreen

import android.app.DatePickerDialog
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.casebeaumonde.R
import java.text.SimpleDateFormat
import java.util.*

class CardDetailScreen : AppCompatActivity() {

    private lateinit var plan_carddate : EditText
    private lateinit var plan_name : TextView
    private lateinit var plan_price : TextView
    private lateinit var plan_cardholdername : EditText
    private lateinit var plan_cardnumber : EditText
    private lateinit var plan_cvc : EditText
    private lateinit var plan_billingzipcode : EditText
    private lateinit var plan_verfybt : Button
    private lateinit var plan_cancel : Button
    private lateinit var back_carddetail : ImageButton
    val c = Calendar.getInstance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card_detail_screen)

        findIds()
        listeners()
    }

    private fun listeners() {
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)
        // create an OnDateSetListener
        val dateSetListener = object : DatePickerDialog.OnDateSetListener {
            override fun onDateSet(view: DatePicker, year: Int, monthOfYear: Int,
                                   dayOfMonth: Int) {
                c.set(Calendar.YEAR, year)
                c.set(Calendar.MONTH, monthOfYear)
                c.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                updateDateInView()
            }
        }


        // when you click on the button, show DatePickerDialog that is set with OnDateSetListener
        plan_carddate.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                DatePickerDialog(this@CardDetailScreen,R.style.DialogTheme,
                    dateSetListener,
                    // set DatePickerDialog to point to today's date when it loads up
                    c.get(Calendar.YEAR),
                    c.get(Calendar.MONTH),
                    c.get(Calendar.DAY_OF_MONTH)).show()
            }
        })

        back_carddetail.setOnClickListener {
            onBackPressed()
        }
    }

    private fun updateDateInView() {
        val myFormat = "MM/dd/yyyy" // mention the format you need
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        plan_carddate.setText(sdf.format(c.time))
    }

    private fun findIds() {
        plan_carddate = findViewById(R.id.plan_carddate)
        plan_name = findViewById(R.id.plan_name)
        plan_price = findViewById(R.id.plan_price)
        plan_cardholdername = findViewById(R.id.plan_cardholdername)
        plan_cardnumber = findViewById(R.id.plan_cardnumber)
        plan_billingzipcode = findViewById(R.id.plan_billingzipcode)
        plan_verfybt = findViewById(R.id.plan_verfybt)
        plan_cancel= findViewById(R.id.plan_cancel)
        back_carddetail = findViewById(R.id.back_carddetail)
    }
}