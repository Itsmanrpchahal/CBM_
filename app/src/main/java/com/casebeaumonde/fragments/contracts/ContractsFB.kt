package com.casebeaumonde.fragments.contracts

import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import com.casebeaumonde.Controller.Controller
import com.casebeaumonde.R
import com.casebeaumonde.activities.myGigs.MyGigs
import com.casebeaumonde.constants.BaseFrag
import com.casebeaumonde.constants.Constants
import com.casebeaumonde.fragments.HireExpert.HireAnExpertFragment
import com.casebeaumonde.fragments.pricing.Pricing
import com.casebeaumonde.utilities.Utility
import org.w3c.dom.Text
import retrofit2.Response


class ContractsFB : BaseFrag() ,Controller.ContractCountAPI{

    lateinit var view_gigs : LinearLayout
    lateinit var work_invitations : LinearLayout
    lateinit var newofferstoreview : LinearLayout
    lateinit var invite_collaborate : LinearLayout
    lateinit var contracts: LinearLayout
    private lateinit var utility: Utility
    private lateinit var pd: ProgressDialog
    lateinit var controller: Controller
    private lateinit var gigcount: TextView
    private lateinit var workinvitationcount: TextView
    private lateinit var newoffercount: TextView
    private lateinit var invitecollaboratecount:TextView
    private lateinit var active: TextView
    private lateinit var complete: TextView
    private lateinit var contractscount: TextView
    private lateinit var manager:FragmentManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_contracts_f_b, container, false)
        manager = fragmentManager!!
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

        contracts.setOnClickListener {
            startActivity(Intent(context,ContractActivity::class.java))
        }
        val bundle = Bundle()
        bundle.putString(Constants.FROM, "")


        invite_collaborate.setOnClickListener {
            val transaction = manager.beginTransaction()
//            val priceFrag = Pricing()
//            priceFrag.arguments = bundle
            transaction.replace(R.id.nav_host_fragment, HireAnExpertFragment())
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }

    private fun findIds(view: View) {
        view_gigs = view.findViewById(R.id.view_gigs)
        work_invitations = view.findViewById(R.id.work_invitations)
        newofferstoreview = view.findViewById(R.id.newofferstoreview)
        invite_collaborate = view.findViewById(R.id.invite_collaborate)
        gigcount = view.findViewById(R.id.gigcount)
        workinvitationcount = view.findViewById(R.id.workinvitationcount)
        newoffercount = view.findViewById(R.id.newoffercount)
        invitecollaboratecount = view.findViewById(R.id.invitecollaboratecount)
        active = view.findViewById(R.id.active)
        complete = view.findViewById(R.id.complete)
        contractscount = view.findViewById(R.id.contractscount)
        contracts = view.findViewById(R.id.contracts)

        controller = Controller()
        controller.Controller(this)

        utility = Utility()
        pd = ProgressDialog(context)
        pd!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        pd!!.window!!.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        pd!!.isIndeterminate = true
        pd!!.setCancelable(false)
        controller.ContractCount("Bearer "+getStringVal(Constants.TOKEN))
    }

    override fun onContractCount(success: Response<ContractCountResponse>) {
        pd.dismiss()
        if (success.isSuccessful)
        {
            if (success.body()?.code.equals("200"))
            {
                gigcount.text = success.body()?.data?.gigs.toString()
                workinvitationcount.text = success.body()?.data?.workInvitation.toString()
                newoffercount.text = success.body()?.data?.receivedOffers.toString()
                invitecollaboratecount.text = success?.body()?.data?.invitesToColaborate.toString()
                active.text = success?.body()?.data?.activeContract.toString()
                complete.text = success?.body()?.data?.completedContract.toString()
                contractscount.text = success?.body()?.data?.completedContract.toString()
            }else {
                Toast.makeText(context,""+success.code(),Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(context,""+success.code(),Toast.LENGTH_SHORT).show()
        }
    }

    override fun error(error: String?) {
        pd.dismiss()
        Toast.makeText(context,""+error,Toast.LENGTH_SHORT).show()
    }
}