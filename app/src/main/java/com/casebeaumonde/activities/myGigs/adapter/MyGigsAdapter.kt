package com.casebeaumonde.activities.myGigs.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.casebeaumonde.R
import com.casebeaumonde.activities.myGigs.MyGigs
import com.casebeaumonde.activities.myGigs.response.MyGigsResponse
import kotlinx.android.synthetic.main.custom_mygigs.view.*

class MyGigsAdapter(
    var context: Context, val mygigs: MutableList<MyGigsResponse.Data.User.Gig>, var userID: String,
    var loginuserID: String
) : RecyclerView.Adapter<MyGigsAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyGigsAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.custom_mygigs, parent, false)

        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: MyGigsAdapter.ViewHolder, position: Int) {
        val gigs = mygigs.get(position)
        holder.itemView.gig_name_tv.text = gigs.title
        holder.itemView.gig_detail_tv.text = gigs.description
        holder.itemView.gig_hour_tv.text = "Hour: " + gigs.hours
        holder.itemView.gig_rate_tv.text = "Rate: " + gigs.rate
        holder.itemView.gig_ratetype_tv.text = "Rate type: " + gigs.rateType
        holder.itemView.gig_status_tv.text = "Status: " + gigs.status
        if (userID != loginuserID) {
            holder.itemView.gig_edit_bt.visibility = View.GONE
            holder.itemView.gig_delete_bt.visibility = View.GONE
            holder.itemView.gig_sendInvitation.visibility = View.VISIBLE
        }

        holder.itemView.gig_sendInvitation.setOnClickListener {
            MyGigs.getGigId?.getGigID(gigs.id.toString(),position)
        }
    }

    override fun getItemCount(): Int {
        return mygigs.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(myGigsResponse: MyGigsResponse) {
            var gig_name_tv: TextView
            var gig_detail_tv: TextView
            var gig_hour_tv: TextView
            var gig_ratetype_tv: TextView
            var gig_rate_tv: TextView
            var gig_status_tv: TextView
            var gig_edit_bt: Button
            var gig_delete_bt: Button
            var gig_sendInvitation : Button


            gig_name_tv = itemView.findViewById(R.id.gig_name_tv)
            gig_detail_tv = itemView.findViewById(R.id.gig_detail_tv)
            gig_hour_tv = itemView.findViewById(R.id.gig_hour_tv)
            gig_ratetype_tv = itemView.findViewById(R.id.gig_ratetype_tv)
            gig_rate_tv = itemView.findViewById(R.id.gig_rate_tv)
            gig_status_tv = itemView.findViewById(R.id.gig_status_tv)
            gig_edit_bt = itemView.findViewById(R.id.gig_edit_bt)
            gig_delete_bt = itemView.findViewById(R.id.gig_delete_bt)
            gig_sendInvitation = itemView.findViewById(R.id.gig_sendInvitation)
        }
    }
}