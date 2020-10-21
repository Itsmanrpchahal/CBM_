package com.casebeaumonde.activities.myContracts.tabs

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.casebeaumonde.R
import com.casebeaumonde.constants.BaseFrag
import com.casebeaumonde.constants.Constants

class WorkInviationFrag : BaseFrag() {

    private lateinit var workinvitation_recieved : Button
    private lateinit var workinvitation_sent : Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view : View
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_work_inviation, container, false)

        findIds(view)

        if (!getStringVal(Constants.USER_ROLE).equals("customer"))
        {
            workinvitation_recieved.visibility = View.VISIBLE
            workinvitation_sent.visibility = View.VISIBLE
        }
        return view
    }

    private fun findIds(view: View?) {
        workinvitation_recieved = view!!.findViewById(R.id.workinvitation_recieved)
        workinvitation_sent = view.findViewById(R.id.workinvitation_sent)
    }

}