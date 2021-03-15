package com.casebeaumonde.activities.b_questionaries.like_CBM

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.CompoundButton
import android.widget.RelativeLayout
import androidx.recyclerview.widget.RecyclerView
import com.casebeaumonde.R
import com.casebeaumonde.activities.b_questionaries.fashionevents.B_FashionEventAdapter
import kotlinx.android.synthetic.main.b_fashionevents.view.*

class LikeCBM_Adapter(var context: Context): RecyclerView.Adapter<LikeCBM_Adapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var v = LayoutInflater.from(parent.context).inflate(R.layout.b_fashionevents, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.fashioneventtecb.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { buttonView: CompoundButton?, isChecked: Boolean ->
            if (isChecked) {
                //  DescribeYourself.selectyourselfIf?.getID(characterstic.get(position), "1");
                holder.itemView.fashioneventtecb.setTextColor(Color.BLACK)
                holder.itemView.fashioneventtecb.setBackgroundColor(Color.WHITE)
            } else {
                // DescribeYourself.selectyourselfIf?.getID(characterstic.get(position), "0");
                holder.itemView.fashioneventtecb.setTextColor(Color.WHITE)
                holder.itemView.fashioneventtecb.setBackgroundColor(Color.LTGRAY)
            }
        })
    }

    override fun getItemCount(): Int {
       return 12
    }

    class ViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView) {
        fun bindItems()
        {
            var layout1 : RelativeLayout
            var fashioneventtecb : CheckBox

            layout1 = itemView.findViewById(R.id.layout1)
            fashioneventtecb = itemView.findViewById(R.id.fashioneventtecb)
        }
    }
}