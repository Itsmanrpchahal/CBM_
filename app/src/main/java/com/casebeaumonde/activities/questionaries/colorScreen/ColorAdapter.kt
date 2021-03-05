package com.casebeaumonde.activities.questionaries.colorScreen

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
import kotlinx.android.synthetic.main.custom_colors.view.*

class ColorAdapter(var context: Context, var colors: Array<String>) :
    RecyclerView.Adapter<ColorAdapter.ViewHolder>() {
    private var selectedPosition = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColorAdapter.ViewHolder {
        var v = LayoutInflater.from(parent.context).inflate(R.layout.custom_colors, parent, false)

        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ColorAdapter.ViewHolder, position: Int) {


//        if (selectedPosition == position) {
//            holder.itemView.isSelected = true
//            holder.itemView.colortv.setBackgroundColor(Color.parseColor("#FFFFFF" ))
//        } else {
//            holder.itemView.isSelected = false
//            holder.itemView.layout.setBackgroundResource(R.drawable.grey_border1)
//        }

//        holder.itemView.colortv.setOnClickListener {
//
//            if (selectedPosition >= 0) {
//                notifyItemChanged(selectedPosition)
//                selectedPosition = holder.adapterPosition
//                notifyItemChanged(selectedPosition)
//            }
//        }
        holder.itemView.colortv.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { buttonView: CompoundButton?, isChecked: Boolean ->
            if (isChecked) {
                ColorPickerScreen.selectcolorIf?.getID(colors.get(position), "1");

            } else {
                ColorPickerScreen.selectcolorIf?.getID(colors.get(position), "0");

            }
        })

        holder.itemView.colortv.setBackgroundColor(Color.parseColor("#" + colors.get(position)))
    }

    override fun getItemCount(): Int {
        return colors.size
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems() {
            val colortv: CheckBox
            val layout: RelativeLayout


            layout = itemView.findViewById(R.id.layout)
            colortv = itemView.findViewById(R.id.colortv)

        }
    }
}