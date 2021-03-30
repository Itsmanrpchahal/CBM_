package com.casebeaumonde.activities.b_questionaries.fashionevents

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.CompoundButton
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.casebeaumonde.R
import com.casebeaumonde.activities.myclosets.adapter.MyClosetsAdapter
import com.casebeaumonde.activities.questionaries.describeyourself.DescribeYourself
import kotlinx.android.synthetic.main.b_fashionevents.view.*
import kotlinx.android.synthetic.main.describeyourself_layout.view.*
import kotlinx.android.synthetic.main.describeyourself_layout.view.selectbt

class B_FashionEventAdapter(var context: Context,var fashionEvents : ArrayList<String>) :
    RecyclerView.Adapter<B_FashionEventAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var v = LayoutInflater.from(parent.context).inflate(R.layout.b_fashionevents, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.fashioneventtecb.setText(fashionEvents.get(position).toString())
        holder.itemView.fashioneventtecb.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { buttonView: CompoundButton?, isChecked: Boolean ->

            if (isChecked) {
              //  DescribeYourself.selectyourselfIf?.getID(characterstic.get(position), "1");
                holder.itemView.fashioneventtecb.setTextColor(Color.BLACK)
                holder.itemView.fashioneventtecb.setBackgroundColor(Color.WHITE)
                B_FashionEventsActivity.selectedfashionIf?.getID(fashionEvents.get(position),"1")
            } else {
               // DescribeYourself.selectyourselfIf?.getID(characterstic.get(position), "0");
                holder.itemView.fashioneventtecb.setTextColor(Color.WHITE)
                holder.itemView.fashioneventtecb.setBackgroundColor(Color.LTGRAY)
                B_FashionEventsActivity.selectedfashionIf?.getID(fashionEvents.get(position),"0")
            }


        })
    }

    override fun getItemCount(): Int {
       return fashionEvents.size
    }

    class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView) {
        fun bindItems()
        {
            var layout1 : RelativeLayout
            var fashioneventtecb : CheckBox

            layout1 = itemView.findViewById(R.id.layout1)
            fashioneventtecb = itemView.findViewById(R.id.fashioneventtecb)
        }

    }
}