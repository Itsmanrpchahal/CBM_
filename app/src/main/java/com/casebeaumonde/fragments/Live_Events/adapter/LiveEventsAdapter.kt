package com.casebeaumonde.fragments.Live_Events.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.casebeaumonde.R
import com.casebeaumonde.constants.Constants
import com.casebeaumonde.fragments.Live_Events.response.LiveEventsResponse
import com.casebeaumonde.fragments.designers.adapter.DesignerAdapter
import com.casebeaumonde.utilities.Utils
import kotlinx.android.synthetic.main.closetsitems.view.*

class LiveEventsAdapter(val context : Context,val data: MutableList<LiveEventsResponse.Event>) : RecyclerView.Adapter<LiveEventsAdapter.ViewHolder>()
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

            closetItemImage = itemView.findViewById(R.id.closetItemImage)
            closetitem_name = itemView.findViewById(R.id.closetitem_name)
            closetitem_uploadby = itemView.findViewById(R.id.closetitem_uploadby)
        }
    }
}