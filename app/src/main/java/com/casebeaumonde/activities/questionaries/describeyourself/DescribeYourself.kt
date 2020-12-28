package com.casebeaumonde.activities.questionaries.describeyourself

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.casebeaumonde.R
import com.casebeaumonde.activities.questionaries.describeyourself.adapter.DescribeYourselfAdapter
import com.casebeaumonde.fragments.users.adapter.UsersAdapter

class DescribeYourself : AppCompatActivity() {

    private lateinit var recylerview : RecyclerView
    private lateinit var  back : ImageButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_describe_yourself)

        findIds()

        recylerview.layoutManager = GridLayoutManager(this,3)
        //user_recyler.addItemDecoration(GridItemDecoration(10, 2))
        val adapter = DescribeYourselfAdapter(this)
        recylerview.adapter = adapter
    }

    private fun findIds() {
        recylerview = findViewById(R.id.recylerview)
        back = findViewById(R.id.back)
    }
}