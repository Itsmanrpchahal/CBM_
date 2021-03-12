package com.casebeaumonde.activities.myoutfits.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.casebeaumonde.R
import com.casebeaumonde.activities.ClosetItem.ClosetsItems
import com.casebeaumonde.activities.myclosets.adapter.MyClosetsAdapter
import com.casebeaumonde.activities.myclosets.response.MyClosetsResponse
import com.casebeaumonde.activities.myoutfits.MyOutfits
import com.casebeaumonde.activities.myoutfits.response.MyOutfitsResponse
import com.casebeaumonde.activities.myoutfitsdetail.MyOutfitsItems
import com.casebeaumonde.constants.Constants
import com.casebeaumonde.utilities.Utils
import kotlinx.android.synthetic.main.closet_layout.view.*
import java.lang.Exception

class MOutfitsAdapter(var context: Context,
var myOutfits : MutableList<MyOutfitsResponse.Data.Outfit>,
var  userID:String,
var loginuserID :String) : RecyclerView.Adapter<MOutfitsAdapter.ViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var v = LayoutInflater.from(parent.context).inflate(R.layout.closet_layout, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val myOutfits = myOutfits.get(position)
        try {
            Glide.with(context).load(Constants.BASE_IMAGE_URL + myOutfits.image)
                .placeholder(R.drawable.login_banner)
                .into(holder.itemView.closet_banner)
        } catch (e: Exception) {

        }

        holder.itemView.closet_go_to_closets.text = "GO TO OUTFIT"
        holder.itemView.closet_username.text = myOutfits.title
        holder.itemView.closet_descripition.text = myOutfits.description.toString()
        holder.itemView.closet_visibilty.visibility  = View.GONE
        holder.itemView.closet_status.text = myOutfits.status
        holder.itemView.closet_customer.text =
            "Customer: " + myOutfits.creator?.firstname + " " + myOutfits.creator?.lastname
        holder.itemView.closet_createdat.text =
            "Created at: " + Utils.changeDateTimeToDateTime(myOutfits.createdAt)
        holder.itemView.haingerllayout.visibility = View.GONE

        holder.itemView.closet_go_to_closets.setOnClickListener {
            context.startActivity(
                Intent(
                    context,
                    MyOutfitsItems::class.java
                ).putExtra(Constants.OUTFITID, "" + myOutfits.id).putExtra("userID",userID).putExtra("creatorID",myOutfits.creatorId)
            )
        }

        holder.itemView.closets_duplicate.visibility = View.GONE

        if (userID != loginuserID)
        {
            holder.itemView.closets_edititem.visibility = View.GONE
            holder.itemView.closets_additem.visibility = View.GONE
            holder.itemView.closets_delete.visibility = View.GONE
            holder.itemView.closets_duplicate.visibility = View.GONE
        }


            holder.itemView.closets_edititem.setOnClickListener {
                MyOutfits.outfitidIf?.getClosetID(myOutfits.id.toString(), position.toString())
            }

        holder.itemView.closets_delete.setOnClickListener {
            MyOutfits.deleteidIf?.getID(position.toString(),myOutfits.id.toString())
        }
    }

    override fun getItemCount(): Int {
       return myOutfits.size
    }

    class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView) {

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
            var haingerllayout : RelativeLayout

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
            haingerllayout = itemView.findViewById(R.id.haingerllayout)
        }
    }
}