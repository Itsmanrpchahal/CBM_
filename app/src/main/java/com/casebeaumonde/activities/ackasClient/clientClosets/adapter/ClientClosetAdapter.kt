package com.casebeaumonde.activities.ackasClient.clientClosets.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.casebeaumonde.R
import com.casebeaumonde.activities.ackasClient.clientClosetsItems.ClientClosetItems
import com.casebeaumonde.activities.myclosets.response.MyClosetsResponse
import com.casebeaumonde.constants.Constants
import com.casebeaumonde.utilities.Utils
import kotlinx.android.synthetic.main.closet_layout.view.*
import java.lang.Exception

class ClientClosetAdapter(var context: Context,
                          var closetsList: MutableList<MyClosetsResponse.Data.Closet>,
                          var userID : String,
                          var loginuserID : String) :
    RecyclerView.Adapter<ClientClosetAdapter.ViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ClientClosetAdapter.ViewHolder {

        var v = LayoutInflater.from(parent.context).inflate(R.layout.closet_layout, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ClientClosetAdapter.ViewHolder, position: Int) {
        val closets = closetsList.get(position)
        try {
            Glide.with(context).load(Constants.BASE_IMAGE_URL + closets.image)
                .placeholder(R.drawable.login_banner1)
                .into(holder.itemView.closet_banner)
        } catch (e: Exception) {

        }

        holder.itemView.closet_username.text = closets.title
        holder.itemView.closet_descripition.text = closets.description
        holder.itemView.closet_visibilty.text = "Visibility: " + closets.visibility
        holder.itemView.closet_customer.text =
            "Customer: " + closets.creator?.firstname + " " + closets.creator?.lastname
        holder.itemView.closet_createdat.text =
            "Created at: " + Utils.changeDateTimeToDateTime(closets.createdAt)

        holder.itemView.closetitem_favorite.visibility = View.GONE
        holder.itemView.closets_duplicate.visibility = View.GONE

        holder.itemView.closet_go_to_closets.setOnClickListener {
            context.startActivity(
                Intent(
                    context,
                    ClientClosetItems::class.java
                ).putExtra(Constants.CLOSETID, "" + closets.id).putExtra("userID",userID)
            )
        }

//        if (userID != loginuserID)
//        {
            holder.itemView.closets_edititem.visibility = View.GONE
            holder.itemView.closets_additem.visibility = View.GONE
            holder.itemView.closets_delete.visibility = View.GONE
            holder.itemView.closets_duplicate.visibility = View.GONE
        holder.itemView.hainger.visibility = View.GONE
//        }

//        holder.itemView.closets_edititem.setOnClickListener {
//            MyClosets.closetitemidIf!!.getClosetID(position.toString())
//        }
//
//        holder.itemView.closets_delete.setOnClickListener {
//            MyClosets.deleteClosetID!!.deleteClosetID(closets.id.toString())
//        }
//
//        holder.itemView.closets_additem.setOnClickListener {
//            context.startActivity(Intent(context, AddItemToCloset::class.java).putExtra("closetID",closets.id.toString()).putExtra("edit","0").putExtra("closetItemID",""))
//        }

    }

    override fun getItemCount(): Int {
        return closetsList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItems(myClosets: MyClosetsResponse) {
            var closet_banner: ImageView
            var closet_username: TextView
            var closet_go_to_closets: TextView
            var closet_descripition: TextView
            var closet_visibilty: TextView
            var closet_customer: TextView
            var closet_status: TextView
            var closet_createdat: TextView
            var closets_additem: Button
            var closets_edititem: Button
            var closets_delete: Button
            var hainger : ImageButton
            var closetitem_favorite : CheckBox
            var closets_duplicate: Button

            closet_banner = itemView.findViewById(R.id.closet_banner)
            closet_username = itemView.findViewById(R.id.closet_username)
            closet_go_to_closets = itemView.findViewById(R.id.closet_go_to_closets)
            closet_descripition = itemView.findViewById(R.id.closet_descripition)
            closet_visibilty = itemView.findViewById(R.id.closet_visibilty)
            closet_customer = itemView.findViewById(R.id.closet_customer)
            closet_status = itemView.findViewById(R.id.closet_status)
            closet_createdat = itemView.findViewById(R.id.closet_createdat)
            closets_additem = itemView.findViewById(R.id.closets_additem)
            closets_edititem = itemView.findViewById(R.id.closets_edititem)
            closets_delete = itemView.findViewById(R.id.closets_delete)
            hainger = itemView.findViewById(R.id.hainger)
            closetitem_favorite = itemView.findViewById(R.id.closetitem_favorite)
            closets_duplicate = itemView.findViewById(R.id.closets_duplicate)
        }
    }
}