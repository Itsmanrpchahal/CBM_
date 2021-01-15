package com.casebeaumonde.activities.questionaries.describeyourself.adapter

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.casebeaumonde.R
import com.casebeaumonde.activities.ClosetItm.adapter.ClosetsItemAdapter
import com.casebeaumonde.activities.questionaries.describeyourself.DescribeYourself
import com.casebeaumonde.activities.questionaries.selectStores.SelectStores
import kotlinx.android.synthetic.main.describeyourself_layout.view.*

class DescribeYourselfAdapter(var context: Context) :
    RecyclerView.Adapter<DescribeYourselfAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var v = LayoutInflater.from(parent.context).inflate(R.layout.describeyourself_layout, parent, false)

        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        holder.itemView.setOnClickListener {
//            context.startActivity(Intent(context,SelectStores::class.java))
//        }

        holder.itemView.selectbt.setOnCheckedChangeListener { buttonView, isChecked ->

//            if (isChecked)
//            {
//                DescribeYourself.selectyourselfIf?.getID(position.toString(),"1");
//                holder.itemView.selectbt.setTextColor(Color.BLACK)
//                Toast.makeText(context,""+position,Toast.LENGTH_SHORT).show()
//            }  else {
//                DescribeYourself.selectyourselfIf?.getID(position.toString(),"0");
//                holder.itemView.selectbt.setTextColor(Color.WHITE)
//            }
            context.startActivity(Intent(context,SelectStores::class.java))
        }
    }

    override fun getItemCount(): Int {
       return 30
    }

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        fun bindItems()
        {
             var selectbt : CheckBox
            selectbt = itemView.findViewById(R.id.selectbt)
        }
    }
}