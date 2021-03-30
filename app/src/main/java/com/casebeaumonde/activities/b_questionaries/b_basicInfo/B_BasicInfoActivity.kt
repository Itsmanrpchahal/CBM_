package com.casebeaumonde.activities.b_questionaries.b_basicInfo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.ImageButton
import android.widget.LinearLayout
import com.casebeaumonde.R
import com.casebeaumonde.activities.b_questionaries.b_basicInfo1.B_BasicInfoActivity1
import com.casebeaumonde.constants.BaseClass

class B_BasicInfoActivity : BaseClass() {

    private lateinit var continue_bt: LinearLayout
    private lateinit var back: ImageButton
    private lateinit var b_address: String
    private lateinit var b_address1: String
    private lateinit var b_city: String
    private lateinit var state: String
    private lateinit var country: String
    private lateinit var brandID: ArrayList<String>
    private lateinit var basic_et1: EditText
    private lateinit var basic_et2: EditText
    private lateinit var basic_et3: EditText
    private lateinit var basic_et4: EditText
    private lateinit var basic_et5: EditText
    private lateinit var basic_et6: EditText
    private lateinit var basic_et7: EditText
    private lateinit var basic_et8: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_b__basic_info)

        findIds()
        listeners()
    }

    private fun listeners() {
        continue_bt.setOnClickListener {
            when {
                basic_et1.text.isEmpty() -> {
                    basic_et1.requestFocus()
                    basic_et1.error = "Required"
                }
                basic_et2.text.isEmpty() -> {
                    basic_et2.requestFocus()
                    basic_et2.error = "Required"
                }

                basic_et3.text.isEmpty() -> {
                    basic_et3.requestFocus()
                    basic_et3.error = "Required"
                }

                basic_et4.text.isEmpty() -> {
                    basic_et4.requestFocus()
                    basic_et4.error = "Required"
                }

                basic_et5.text.isEmpty() -> {
                    basic_et5.requestFocus()
                    basic_et5.error = "Required"
                }

                basic_et6.text.isEmpty() -> {
                    basic_et6.requestFocus()
                    basic_et6.error = "Required"
                }

                basic_et7.text.isEmpty() -> {
                    basic_et7.requestFocus()
                    basic_et7.error = "Required"
                }

                basic_et8.text.isEmpty() -> {
                    basic_et8.requestFocus()
                    basic_et8.error = "Required"
                }
                else -> {
                    startActivity(Intent(this, B_BasicInfoActivity1::class.java).
                    putExtra("b_address",b_address).
                    putExtra("b_address1",b_address1).
                    putExtra("b_city",b_city).
                    putExtra("state",state).
                    putExtra("country",country).
                    putExtra("brandID",brandID).
                    putExtra("basic1",basic_et1.text.toString()).
                    putExtra("basic2",basic_et2.text.toString()).
                    putExtra("basic3",basic_et3.text.toString()).
                    putExtra("basic4",basic_et4.text.toString()).
                    putExtra("basic5",basic_et5.text.toString()).
                    putExtra("basic6",basic_et6.text.toString()).
                    putExtra("basic7",basic_et7.text.toString()).
                    putExtra("basic8",basic_et8.text.toString()))
                }
            }

        }

        back.setOnClickListener {
            onBackPressed()
        }
    }

    private fun findIds() {
        b_address = intent.getStringExtra("b_address").toString()
        b_address1 = intent.getStringExtra("b_address1").toString()
        b_city = intent.getStringExtra("b_city").toString()
        state = intent.getStringExtra("state").toString()
        country = intent.getStringExtra("country").toString()
        brandID = intent.getStringArrayListExtra("brandID")!!

        Log.d(
            "b_basic",
            b_address + "    " + b_address1 + "  " + b_city + "  " + state + "   " + country + "   " + brandID
        )
        continue_bt = findViewById(R.id.continue_bt)
        back = findViewById(R.id.back)
        basic_et1 = findViewById(R.id.basic_et1)
        basic_et2 = findViewById(R.id.basic_et2)
        basic_et3 = findViewById(R.id.basic_et3)
        basic_et4 = findViewById(R.id.basic_et4)
        basic_et5 = findViewById(R.id.basic_et5)
        basic_et6 = findViewById(R.id.basic_et6)
        basic_et7 = findViewById(R.id.basic_et7)
        basic_et8 = findViewById(R.id.basic_et8)
    }
}