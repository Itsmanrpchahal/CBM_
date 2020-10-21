package com.casebeaumonde.activities.myContracts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import androidx.viewpager.widget.ViewPager
import com.casebeaumonde.R
import com.casebeaumonde.activities.myContracts.adapter.ContractTabAdapter
import com.google.android.material.tabs.TabLayout

class MyContracts : AppCompatActivity() {
    lateinit var tabLayout: TabLayout
    lateinit var viewPager: ViewPager
    lateinit var back : ImageButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_contracts)

        findIds()
        listeners()
    }

    private fun listeners() {
        back.setOnClickListener { onBackPressed() }
    }

    private fun findIds() {
        tabLayout = findViewById(R.id.tabLayout)
        viewPager = findViewById(R.id.viewPager)
        back = findViewById(R.id.back)
        tabLayout.addTab(tabLayout.newTab().setText("Contracts"))
        tabLayout.addTab(tabLayout.newTab().setText("Offers"))
        tabLayout.addTab(tabLayout.newTab().setText("Work Invitations"))
        tabLayout.tabGravity = TabLayout.GRAVITY_FILL
        val adapter = ContractTabAdapter(this, supportFragmentManager,
            tabLayout.tabCount)
        viewPager.adapter = adapter
        viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager.currentItem = tab.position
            }
            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
    }
}