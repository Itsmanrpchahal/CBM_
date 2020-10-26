package com.casebeaumonde.fragments.pricing.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.casebeaumonde.R
import com.casebeaumonde.activities.paymentScreen.CardDetailScreen
import com.casebeaumonde.fragments.pricing.response.PricingResponse
import kotlinx.android.synthetic.main.customprice.view.*

class CustomerPricingAdapter(val context: Context, val pricing: ArrayList<PricingResponse.Data.CustomerPlan>) : RecyclerView.Adapter<CustomerPricingAdapter.ViewHolder>()
{


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.customprice,parent,false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.levelplan.setText(pricing.get(position).name)
        holder.itemView.planrate.setText(pricing.get(position).monthlyPrice.toString())
        holder.itemView.subscribebt.setOnClickListener {
            context.startActivity(Intent(context,CardDetailScreen::class.java).putExtra("planname",pricing.get(position).name).putExtra("planprice",pricing.get(position).monthlyPrice.toString()))
        }

        holder.itemView.ortv.visibility = View.GONE
        holder.itemView.save_percent.visibility = View.GONE
        holder.itemView.trail_text.visibility = View.GONE
        holder.itemView.save_percent.visibility = View.GONE
       holder.itemView.year_plan.visibility = View.GONE
        //holder.itemView.trail_text.setText(pricing.get(position).trialDays.toString()+" days trail")
        //holder.itemView.save_percent.setText( " (save"+pricing.get(position).yearlySavingPercent.toString()+"%)")
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
            val planrate_yearly : TextView
            val trail_text : TextView
            val save_percent: TextView
            val ortv : TextView
            val year_plan : LinearLayout

            levelplan = itemView.findViewById(R.id.levelplan)
            planrate = itemView.findViewById(R.id.planrate)
            subscribebt = itemView.findViewById(R.id.subscribebt)
            planrate_yearly = itemView.findViewById(R.id.planrate_yearly)
            trail_text = itemView.findViewById(R.id.trail_text)
            save_percent = itemView.findViewById(R.id.save_percent)
            ortv = itemView.findViewById(R.id.ortv)
            year_plan = itemView.findViewById(R.id.year_plan)

        }
    }
}