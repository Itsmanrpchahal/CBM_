package com.casebeaumonde.activities.questionaries.selectbrands

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.casebeaumonde.R
import com.casebeaumonde.activities.ClosetItem.IF.SelectedCloset_ID
import com.casebeaumonde.activities.questionaries.selectbrands.IF.SelectedBrand_IF
import com.casebeaumonde.activities.questionaries.selectbrands.adapter.DataAdapter
import com.casebeaumonde.constants.Data
import fastscroll.app.fastscrollalphabetindex.AlphabetIndexFastScrollRecyclerView
import java.util.*
import kotlin.collections.ArrayList


class SelectBrands : AppCompatActivity(),SelectedBrand_IF{

    private lateinit var dataList : ArrayList<Data>
    private lateinit var recyclerView : AlphabetIndexFastScrollRecyclerView
    private lateinit var selectedBrands : ArrayList<String>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_brands)
        dataList = java.util.ArrayList()
        selectedBrands = ArrayList()
        findIds()
        initData()
        Collections.sort(dataList, Data.titleNameComparator)
        recyclerView.layoutManager = GridLayoutManager(this,2)
        val adapter =
            DataAdapter(
                dataList,
                this
            )
        recyclerView.adapter = adapter
        recyclerView.setIndexTextSize(12)
        recyclerView.setIndexBarTextColor("#000000")
        recyclerView.setIndexBarColor("#cdced2")
        recyclerView.setIndexbarHighLateTextColor("#FF4081")
        recyclerView.setIndexBarHighLateTextVisibility(true)
        recyclerView.setIndexBarTransparentValue(1.0.toFloat())
        adapter.notifyDataSetChanged()

        selectedbrandIf = this
    }

    private fun findIds() {
        recyclerView = findViewById(R.id.recyclerView)
    }

    private fun initData() {
        dataList.add(Data("Apple"))
        dataList.add(Data("Ant"))
        dataList.add(Data("Bpple"))
        dataList.add(Data("BDnt"))
        dataList.add(Data("Cpple"))
        dataList.add(Data("Cnt"))
        dataList.add(Data("Dpple"))
        dataList.add(Data("DDnt"))
        dataList.add(Data("Epple"))
        dataList.add(Data("Ent"))
        dataList.add(Data("Fpple"))
        dataList.add(Data("FDnt"))
        dataList.add(Data("Gpple"))
        dataList.add(Data("Gnt"))
        dataList.add(Data("Hpple"))
        dataList.add(Data("HDnt"))
        dataList.add(Data("Ipple"))
        dataList.add(Data("int"))
        dataList.add(Data("Jpple"))
        dataList.add(Data("JDnt"))
        dataList.add(Data("Kpple"))
        dataList.add(Data("Knt"))
        dataList.add(Data("Lpple"))
        dataList.add(Data("LDnt"))
        dataList.add(Data("Mpple"))
        dataList.add(Data("Mnt"))
        dataList.add(Data("Npple"))
        dataList.add(Data("NDnt"))
        dataList.add(Data("Opple"))
        dataList.add(Data("Ont"))
        dataList.add(Data("Ppple"))
        dataList.add(Data("PDnt"))
        dataList.add(Data("Qpple"))
        dataList.add(Data("Qnt"))
        dataList.add(Data("Rpple"))
        dataList.add(Data("RDnt"))
        dataList.add(Data("Spple"))
        dataList.add(Data("Snt"))
        dataList.add(Data("Tpple"))
        dataList.add(Data("TDnt"))
        dataList.add(Data("Upple"))
        dataList.add(Data("Unt"))
        dataList.add(Data("Vpple"))
        dataList.add(Data("VDnt"))
        dataList.add(Data("Wpple"))
        dataList.add(Data("Wnt"))
        dataList.add(Data("Xpple"))
        dataList.add(Data("XDnt"))
        dataList.add(Data("Ypple"))
        dataList.add(Data("Ynt"))
        dataList.add(Data("Zpple"))
        dataList.add(Data("ZDnt"))
        dataList.add(Data("kpple"))
        dataList.add(Data("ant"))
        dataList.add(Data("ppple"))
        dataList.add(Data("iDnt"))
        dataList.add(Data("fpple"))
        dataList.add(Data("rnt"))
        dataList.add(Data("apple"))
        dataList.add(Data("nDnt"))
        dataList.add(Data("tpple"))
        dataList.add(Data("int"))
        dataList.add(Data("cpple"))
        dataList.add(Data("lDnt"))
    }

    companion object{
        lateinit var selectedbrandIf: SelectedBrand_IF

    }

    override fun getID(id: String?, s: String?) {
        if (s.equals("0")) {
            if (selectedBrands.contains(id)) {
                selectedBrands.remove(id)
            }
        } else {
            selectedBrands.add(id.toString())
            Log.d("selectBrands",""+selectedBrands)
        }
    }

}