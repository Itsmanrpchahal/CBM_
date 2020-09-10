package com.casebeaumonde.fragments.Live_Events.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.casebeaumonde.R
import com.casebeaumonde.activities.eventDetail.EventDetailScreen
import com.casebeaumonde.constants.Constants
import com.casebeaumonde.fragments.Live_Events.response.LiveEventsResponse
import com.casebeaumonde.fragments.allClosets.response.AllClosetsResponse
import com.casebeaumonde.utilities.Utils
import kotlinx.android.synthetic.main.closetsitems.view.*
import kotlinx.android.synthetic.main.createclosets.view.*
import org.w3c.dom.Text

class LiveEventsAdapter(val context : Context,val data: MutableList<LiveEventsResponse.Event>,var userID:String) : RecyclerView.Adapter<LiveEventsAdapter.ViewHolder>()
{
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): LiveEventsAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.closetsitems,parent,false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: LiveEventsAdapter.ViewHolder, position: Int) {
        val  list = data.get(position)
        Glide.with(context).load(Constants.BASE_IMAGE_URL+list.image).placeholder(R.drawable.login_banner).into(holder.itemView.closetItemImage)
        holder.itemView.closetitem_name.text = list.title
        holder.itemView.closetitem_uploadby.text = "Created by: "+data.get(position).creator.firstname +" at "+Utils.changeDateTimeToDateTime(list.createdAt)

        holder.itemView.setOnClickListener {
            context.startActivity(Intent(context,EventDetailScreen::class.java).putExtra(Constants.EVENTID,list.id.toString()))
        }

        if (list.hearts.size>0)
        {
            holder.itemView.closetitem_favcount.text = list.hearts.size.toString()
        }


        searchUserHeart(list,holder.itemView.closetitem_favorite)
    }

    private fun searchUserHeart(list: LiveEventsResponse.Event, closetitem_favorite: CheckBox) {
        if (list.hearts.size>0)
        {
            for (i in list.hearts!!.indices)
            {
                val heart = list.hearts!![i]
                if (heart.userId.toString().equals(userID))
                {
                    closetitem_favorite.isChecked = true
                }
            }
        }
    }


    override fun getItemCount(): Int {
        return data.size
    }

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        fun bindItems()
        {
            val closetItemImage : ImageView
            val closetitem_name : TextView
            val closetitem_uploadby : TextView
            val closetitem_favorite : CheckBox
            val closetitem_favcount : TextView

            closetItemImage = itemView.findViewById(R.id.closetItemImage)
            closetitem_name = itemView.findViewById(R.id.closetitem_name)
            closetitem_uploadby = itemView.findViewById(R.id.closetitem_uploadby)
            closetitem_favorite = itemView.findViewById(R.id.closetitem_favorite)
            closetitem_favcount = itemView.findViewById(R.id.closetitem_favcount)
        }
    }
}