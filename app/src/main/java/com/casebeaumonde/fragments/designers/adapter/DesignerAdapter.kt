package com.casebeaumonde.fragments.designers.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.casebeaumonde.R

class DesignerAdapter (designerList: ArrayList<String>) : RecyclerView.Adapter<DesignerAdapter.ViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DesignerAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.customuser,parent,false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
       return 14
    }

    override fun onBindViewHolder(holder: DesignerAdapter.ViewHolder, position: Int) {

    }

    class ViewHolder (itemView: View): RecyclerView.ViewHolder(itemView){

    }
}
