package com.casebeaumonde.fragments.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.FragmentManager
import com.casebeaumonde.R
import com.casebeaumonde.activities.login.LoginActivity
import com.casebeaumonde.activities.register.userRegister.RegisterActivity
import com.casebeaumonde.activities.register.userRegister.RegisterTypeScreen
import com.casebeaumonde.constants.BaseFrag
import com.casebeaumonde.constants.Constants
import com.toptoche.searchablespinnerlibrary.SearchableSpinner
import kotlinx.android.synthetic.main.cart_custom.*

class HomeFragment : BaseFrag() {

    private lateinit var add : Button
    private lateinit var spinner : SearchableSpinner
    private lateinit var data : ArrayList<String>
    private lateinit var onboard_loginBt : Button
    private lateinit var onboard_registerBt : Button
    private lateinit var onboard_pricing : TextView
    private lateinit var onboard_contact : TextView
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

        findIds(view)
        listeners()

        return view
    }

    private fun listeners() {
        onboard_loginBt.setOnClickListener {
            context?.startActivity(Intent(context,LoginActivity::class.java))
        }

        onboard_registerBt.setOnClickListener {
//            context?.startActivity(Intent(context,RegisterActivity::class.java))
            context?.startActivity(Intent(context,RegisterTypeScreen::class.java))
        }

        onboard_pricing.setOnClickListener {
//            val bundle = Bundle()
//            bundle.putString(Constants.FROM,"logout")
//            val pricing = Pricing()
//            pricing.arguments = bundle
//            val transaction = manager.beginTransaction()
//                transaction.replace(R.id.nav_host_fragment, pricing)
//                transaction.commit()
            startActivity(Intent(context,RegisterTypeScreen::class.java))
        }
    }

    private fun findIds(view: View) {
        onboard_loginBt = view.findViewById(R.id.onboard_loginBt)
        onboard_registerBt = view.findViewById(R.id.onboard_registerBt)
        onboard_pricing = view.findViewById(R.id.onboard_pricing)
        onboard_contact = view.findViewById(R.id.onboard_contact)

        if (getStringVal(Constants.TOKEN).equals(""))
        {
            onboard_pricing.visibility = View.GONE
            onboard_contact.visibility = View.GONE
        }

        if (!getStringVal(Constants.TOKEN).equals(""))
        {
            onboard_loginBt.visibility = View.INVISIBLE
            onboard_registerBt.visibility = View.INVISIBLE
            onboard_pricing.visibility = View.GONE
            onboard_contact.visibility = View.GONE
        }
    }


}