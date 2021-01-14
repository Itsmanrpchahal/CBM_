package com.casebeaumonde.activities.questionaries.describeyourself

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.casebeaumonde.R
import com.casebeaumonde.activities.questionaries.describeyourself.IF.SelectYourself_IF
import com.casebeaumonde.activities.questionaries.describeyourself.adapter.DescribeYourselfAdapter
import com.casebeaumonde.fragments.users.adapter.UsersAdapter

class DescribeYourself : AppCompatActivity(), SelectYourself_IF {

    private lateinit var recylerview : RecyclerView
    private lateinit var  back : ImageButton
    private lateinit var selectyourselfIf: SelectYourself_IF
    private lateinit var selectIds : ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_describe_yourself)

        findIds()
        selectIds = ArrayList()
        recylerview.layoutManager = GridLayoutManager(this,3)
        //user_recyler.addItemDecoration(GridItemDecoration(10, 2))
        val adapter = DescribeYourselfAdapter(this)
        recylerview.adapter = adapter
        selectyourselfIf = this
    }

    private fun findIds() {
        recylerview = findViewById(R.id.recylerview)
        back = findViewById(R.id.back)
    }

    companion object {
         var selectyourselfIf: SelectYourself_IF?=null
    }

    override fun getID(id: String?, s: String?) {
        if (s.equals("0")) {
            if (selectIds.contains(id)) {
                selectIds.remove(id)
            }
        } else {
            selectIds.add(id.toString())
            Log.d("yourself",""+selectIds)
        }
    }
}