package com.casebeaumonde.fragments.pricing.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.casebeaumonde.R
import com.casebeaumonde.fragments.pricing.response.PricingResponse
import kotlinx.android.synthetic.main.customplanfeature.view.*

class BussinessPlanFeaturesAdapter(var context: Context,
                                   val plansFeatures: List<PricingResponse.Data.BusinessPlan>?,
                                   val from:String,
                                   val token:String,
                                   val pos : Int): RecyclerView.Adapter<BussinessPlanFeaturesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.customplanfeature, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if ((position % 2) == 0)
        {
            holder.itemView.plantext.setBackgroundColor(Color.GRAY)
            holder.itemView.plantext.text = plansFeatures?.get(pos)?.planFeatures?.get(position)?.feature
        } else {
            holder.itemView.plantext.text = plansFeatures?.get(pos)?.planFeatures?.get(position)?.feature
        }
    }

    override fun getItemCount(): Int {
        return plansFeatures?.get(pos)?.planFeatures?.size!!
    }

    class ViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){
        fun bind() {
            val plantext : TextView

            plantext = itemView.findViewById(R.id.plantext)
        }
    }


}