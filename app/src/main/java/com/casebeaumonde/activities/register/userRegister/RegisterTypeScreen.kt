package com.casebeaumonde.activities.register.userRegister

import android.annotation.SuppressLint
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.cardview.widget.CardView
import com.casebeaumonde.R

class RegisterTypeScreen : AppCompatActivity() {

    private lateinit var retailercard : CardView
    private lateinit var influencercard : CardView
    private lateinit var textRetail : TextView
    private lateinit var textInfluencer : TextView

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_type_screen)

        findIds()
        listeners()
    }

    @RequiresApi(Build.VERSION_CODES.M)
    @SuppressLint("ResourceAsColor")
    private fun listeners() {
        influencercard.setCardBackgroundColor(getColor(R.color.colorBlack))
        retailercard.setCardBackgroundColor(getColor(R.color.colorWhite))
        textRetail.setTextColor(getColor(R.color.colorBlack))
        textInfluencer.setTextColor(getColor(R.color.colorWhite))

         retailercard.setOnClickListener {
            retailercard.setCardBackgroundColor(getColor(R.color.colorWhite))
            influencercard.setCardBackgroundColor(getColor(R.color.colorBlack))
             textRetail.setTextColor(getColor(R.color.colorBlack))
             textInfluencer.setTextColor(getColor(R.color.colorWhite))

        }

        influencercard.setOnClickListener {
            retailercard.setCardBackgroundColor(getColor(R.color.colorBlack))
            influencercard.setCardBackgroundColor(getColor(R.color.colorWhite))
            textRetail.setTextColor(getColor(R.color.colorWhite))
            textInfluencer.setTextColor(getColor(R.color.colorBlack))
        }


    }

    private fun findIds() {
        retailercard = findViewById(R.id.retailercard)
        influencercard = findViewById(R.id.influencercard)
        textRetail = findViewById(R.id.textRetail)
        textInfluencer = findViewById(R.id.textInfluencer)
    }
}