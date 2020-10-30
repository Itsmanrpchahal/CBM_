package com.casebeaumonde.fragments.home

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.FragmentManager
import com.casebeaumonde.R
import com.casebeaumonde.activities.login.LoginActivity
import com.casebeaumonde.activities.userRegister.RegisterActivity
import com.casebeaumonde.constants.BaseFrag
import com.casebeaumonde.constants.Constants
import com.toptoche.searchablespinnerlibrary.SearchableSpinner

class HomeFragment : BaseFrag() {

    private lateinit var add : Button
    private lateinit var spinner : SearchableSpinner
    private lateinit var data : ArrayList<String>
    private lateinit var onboard_loginBt : Button
    private lateinit var onboard_registerBt : Button
    private lateinit var onboard_pricing : TextView
    private lateinit var manager : FragmentManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_home, container, false)
        manager = fragmentManager!!
        findIds(view)
        listeners()

        return view
    }

    private fun listeners() {
        onboard_loginBt.setOnClickListener {
            context?.startActivity(Intent(context,LoginActivity::class.java))
        }

        onboard_registerBt.setOnClickListener {
            context?.startActivity(Intent(context,RegisterActivity::class.java))
        }

        onboard_pricing.setOnClickListener {

        }
    }

    private fun findIds(view: View) {
        onboard_loginBt = view.findViewById(R.id.onboard_loginBt)
        onboard_registerBt = view.findViewById(R.id.onboard_registerBt)
        onboard_pricing = view.findViewById(R.id.onboard_pricing)

        if (!getStringVal(Constants.TOKEN).equals(""))
        {
            onboard_loginBt.visibility = View.GONE
            onboard_registerBt.visibility = View.GONE
        }
    }


}