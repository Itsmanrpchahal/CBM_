package com.casebeaumonde.fragments.contracts

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.casebeaumonde.R
import com.casebeaumonde.activities.myGigs.MyGigs
import com.casebeaumonde.constants.BaseFrag
import com.casebeaumonde.constants.Constants


class ContractsFB : BaseFrag() {

    lateinit var view_gigs : LinearLayout
    lateinit var work_invitations : LinearLayout
    lateinit var newofferstoreview : LinearLayout
    lateinit var invite_collaborate : LinearLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_contracts_f_b, container, false)

        findIds(view)
        listeners()

        return  view
    }

    private fun listeners() {
        work_invitations.setOnClickListener {
            startActivity(Intent(context,WorkInvitation_Activity::class.java))
        }

        view_gigs.setOnClickListener {
            startActivity(Intent(context,MyGigs::class.java).putExtra("userID",getStringVal(Constants.USERID)).putExtra("role",getStringVal(Constants.USER_ROLE)))
        }

        newofferstoreview.setOnClickListener {
            startActivity(Intent(context,Offers_Activity::class.java))
        }
    }

    private fun findIds(view: View) {
        view_gigs = view.findViewById(R.id.view_gigs)
        work_invitations = view.findViewById(R.id.work_invitations)
        newofferstoreview = view.findViewById(R.id.newofferstoreview)
        invite_collaborate = view.findViewById(R.id.invite_collaborate)
    }
}