package com.casebeaumonde.activities.questionaries.describeyourself.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.casebeaumonde.R
import com.casebeaumonde.activities.ClosetItm.adapter.ClosetsItemAdapter
import com.casebeaumonde.activities.questionaries.selectStores.SelectStores
import kotlinx.android.synthetic.main.describeyourself_layout.view.*

class DescribeYourselfAdapter(var context: Context) :
    RecyclerView.Adapter<DescribeYourselfAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var v = LayoutInflater.from(parent.context).inflate(R.layout.describeyourself_layout, parent, false)

        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.button.setOnClickListener {
            context.startActivity(Intent(context,SelectStores::class.java))
        }
    }

    override fun getItemCount(): Int {
       return 30
    }

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        private lateinit var button : Button
        fun bindItems()
        {
            button = itemView.findViewById(R.id.button)
        }
    }
}