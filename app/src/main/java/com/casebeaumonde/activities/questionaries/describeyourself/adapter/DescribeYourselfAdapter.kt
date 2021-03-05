package com.casebeaumonde.activities.questionaries.describeyourself.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.CompoundButton
import androidx.recyclerview.widget.RecyclerView
import com.casebeaumonde.R
import com.casebeaumonde.activities.questionaries.describeyourself.DescribeYourself
import kotlinx.android.synthetic.main.describeyourself_layout.view.*

class DescribeYourselfAdapter(var context: Context, var characterstic: ArrayList<String>) :
    RecyclerView.Adapter<DescribeYourselfAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var v = LayoutInflater.from(parent.context).inflate(
            R.layout.describeyourself_layout,
            parent,
            false
        )

        return ViewHolder(v)
    }

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        holder.itemView.setOnClickListener {
//            context.startActivity(Intent(context,SelectStores::class.java))
//        }

        holder.itemView.selectbt.setText(characterstic.get(position))

        holder.itemView.selectbt.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { buttonView: CompoundButton?, isChecked: Boolean ->
            if (isChecked) {
                DescribeYourself.selectyourselfIf?.getID(characterstic.get(position), "1");
                holder.itemView.selectbt.setTextColor(Color.BLACK)
                holder.itemView.selectbt.setBackgroundColor(Color.WHITE)
            } else {
                DescribeYourself.selectyourselfIf?.getID(characterstic.get(position), "0");
                holder.itemView.selectbt.setTextColor(Color.WHITE)
                holder.itemView.selectbt.setBackgroundColor(Color.LTGRAY)
            }
        })
    }
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }


    override fun getItemCount(): Int {
       return characterstic.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        fun bindItems()
        {
             var selectbt : CheckBox
            selectbt = itemView.findViewById(R.id.selectbt)
        }
    }
}