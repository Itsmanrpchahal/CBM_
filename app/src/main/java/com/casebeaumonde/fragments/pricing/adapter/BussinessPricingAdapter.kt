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
import com.casebeaumonde.fragments.pricing.Pricing
import com.casebeaumonde.fragments.pricing.response.PricingResponse
import kotlinx.android.synthetic.main.customprice.view.*
import org.w3c.dom.Text

class BussinessPricingAdapter (val context: Context, val pricing: ArrayList<PricingResponse.Data.BusinessPlan>) : RecyclerView.Adapter<BussinessPricingAdapter.ViewHolder>()
{


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.customprice,parent,false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.levelplan.setText(pricing.get(position).name)
        holder.itemView.planrate.setText(pricing.get(position).monthlyPrice.toString())
        holder.itemView.subscribebt.setOnClickListener {
            context.startActivity(Intent(context, CardDetailScreen::class.java).putExtra("planname",pricing.get(position).name).putExtra("planprice",pricing.get(position).monthlyPrice.toString()))
        }
        holder.itemView.planrate_yearly.setText(pricing.get(position).annualCharge.toString())
        holder.itemView.trail_text.setText(pricing.get(position).trialDays.toString()+" days trail")
        holder.itemView.save_percent.setText(" (save"+pricing.get(position).yearlySavingPercent.toString()+"%)")

        if (holder.itemView.subscribebt.text.equals("Subscribed")) {
            holder.itemView.subscribebt.isEnabled = false

        }

        holder.itemView.subscribebt.setOnClickListener {

            if (holder.itemView.subscribebt.text.equals("Change Plan")) {

                Pricing.getpriceidIf?.getID(pricing.get(position).id.toString(), "customer")
            }
            else  {
                context.startActivity(

                    Intent(context, CardDetailScreen::class.java).putExtra(
                        "planname", pricing.get(
                            position
                        ).name
                    ).putExtra("planprice", pricing.get(position).monthlyPrice.toString())
                        .putExtra("planID",pricing.get(position).id.toString())
                        .putExtra("plantype","customer")
                )
            }
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
            val planrate_yearly : TextView
            val trail_text : TextView
            val save_percent: TextView

            levelplan = itemView.findViewById(R.id.levelplan)
            planrate = itemView.findViewById(R.id.planrate)
            subscribebt = itemView.findViewById(R.id.subscribebt)
            planrate_yearly = itemView.findViewById(R.id.planrate_yearly)
            trail_text = itemView.findViewById(R.id.trail_text)
            save_percent = itemView.findViewById(R.id.save_percent)
        }
    }
}