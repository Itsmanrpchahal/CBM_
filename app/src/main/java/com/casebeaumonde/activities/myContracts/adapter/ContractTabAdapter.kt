package com.casebeaumonde.activities.myContracts.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.casebeaumonde.activities.myContracts.tabs.MyContractsFrag
import com.casebeaumonde.activities.myContracts.tabs.offers.OffersFrag
import com.casebeaumonde.activities.myContracts.tabs.WorkInvitation.WorkInviationFrag

class ContractTabAdapter(
    var context: Context,
    fm: FragmentManager,
    var tabs: Int
) : FragmentPagerAdapter(fm)
{
    override fun getCount(): Int {
        return tabs
    }

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                MyContractsFrag()
            }
            1 -> {
                OffersFrag()
            }
            2 -> {
                WorkInviationFrag()
            }
            else -> getItem(position)
        }
    }

}