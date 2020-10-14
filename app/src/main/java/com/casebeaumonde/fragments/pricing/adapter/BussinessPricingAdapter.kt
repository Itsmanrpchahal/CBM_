package com.casebeaumonde.fragments.pricing.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.casebeaumonde.R
import com.casebeaumonde.activities.paymentScreen.CardDetailScreen
import com.casebeaumonde.fragments.pricing.response.PricingResponse
import kotlinx.android.synthetic.main.customprice.view.*

class BussinessPricingAdapter (val context: Context, val pricing: ArrayList<PricingResponse.Data.BusinessPlan>) : RecyclerView.Adapter<BussinessPricingAdapter.ViewHolder>()
{


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.customprice,parent,false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.levelplan.setText(pricing.get(position).name)
        holder.itemView.planrate.setText(pricing.get(position).monthlyPrice)
        holder.itemView.subscribebt.setOnClickListener {
            context.startActivity(Intent(context, CardDetailScreen::class.java).putExtra("planname",pricing.get(position).name))
        }
    }

    override fun getItemCount(): Int {
        return pricing.size
    }

    class ViewHolder (itemView : View): RecyclerView.ViewHolder(itemView){


        fun bind()
        {
            val levelplan : TextView
            val planrate : TextView
            val subscribebt : Button
            levelplan = itemView.findViewById(R.id.levelplan)
            planrate = itemView.findViewById(R.id.planrate)
            subscribebt = itemView.findViewById(R.id.subscribebt)
        }
    }
}