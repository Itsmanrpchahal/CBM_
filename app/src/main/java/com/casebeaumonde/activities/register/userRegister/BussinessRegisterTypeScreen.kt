package com.casebeaumonde.activities.register.userRegister

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.cardview.widget.CardView
import com.casebeaumonde.R
import com.casebeaumonde.activities.businessRegister.BusinessRegisterActivity

class BussinessRegisterTypeScreen : AppCompatActivity() {

    private lateinit var back : ImageButton
    private lateinit var personalcard : CardView
    private lateinit var busissnesscard : CardView
    private lateinit var textRetail : TextView
    private lateinit var textInfluencer : TextView
    private lateinit var continue_bt : LinearLayout
    var type : String ="Personal"


    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bussiness_register_type_screen)

        findIds()
        listeners()
    }

    @RequiresApi(Build.VERSION_CODES.M)
    @SuppressLint("ResourceAsColor")
    private fun listeners() {
        busissnesscard.setCardBackgroundColor(getColor(R.color.colorBlack))
        personalcard.setCardBackgroundColor(getColor(R.color.colorWhite))
        textRetail.setTextColor(getColor(R.color.colorBlack))
        textInfluencer.setTextColor(getColor(R.color.colorWhite))

        personalcard.setOnClickListener {
            personalcard.setCardBackgroundColor(getColor(R.color.colorWhite))
            busissnesscard.setCardBackgroundColor(getColor(R.color.colorBlack))
            textRetail.setTextColor(getColor(R.color.colorBlack))
            textRetail.setCompoundDrawablesRelativeWithIntrinsicBounds(0,R.drawable.storebb,0,0)
            textInfluencer.setCompoundDrawablesRelativeWithIntrinsicBounds(0,R.drawable.ladyw,0,0)
            textInfluencer.setTextColor(getColor(R.color.colorWhite))
            type = "Personal"
        }

        busissnesscard.setOnClickListener {
            personalcard.setCardBackgroundColor(getColor(R.color.colorBlack))
            busissnesscard.setCardBackgroundColor(getColor(R.color.colorWhite))
            textRetail.setTextColor(getColor(R.color.colorWhite))
            textInfluencer.setTextColor(getColor(R.color.colorBlack))
            textRetail.setCompoundDrawablesRelativeWithIntrinsicBounds(0,R.drawable.storeb,0,0)
            textInfluencer.setCompoundDrawablesRelativeWithIntrinsicBounds(0,R.drawable.ladyb,0,0)
            type = "Bussiness"
        }

        back.setOnClickListener { onBackPressed() }

        continue_bt.setOnClickListener {
            if(type.equals("Personal"))
            {
                startActivity(Intent(this,BusinessRegisterActivity::class.java))
            } else {
                startActivity(Intent(this,BusinessRegisterActivity::class.java))
            }


        }
    }

    private fun findIds() {
        back = findViewById(R.id.back)
        personalcard = findViewById(R.id.personalcard)
        busissnesscard = findViewById(R.id.busissnesscard)
        textRetail = findViewById(R.id.textRetail)
        textInfluencer = findViewById(R.id.textInfluencer)
        continue_bt = findViewById(R.id.continue_bt)
    }
}