package com.casebeaumonde.activities.eventDetail.adapter

import android.content.Context
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
import com.casebeaumonde.activities.eventDetail.response.EventDetailResponse
import com.casebeaumonde.constants.Constants
import kotlinx.android.synthetic.main.closetsitems.view.*

class EventDetailAdapter(
    val context: Context,
    val list: MutableList<EventDetailResponse.Data.Events.Item>,
    val userId : String
) :
    RecyclerView.Adapter<EventDetailAdapter.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): EventDetailAdapter.ViewHolder {
        var v = LayoutInflater.from(parent.context).inflate(R.layout.closetsitems, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: EventDetailAdapter.ViewHolder, position: Int) {
       val listdata = list.get(position)

        Glide.with(context).load(Constants.BASE_IMAGE_URL+listdata.picture).placeholder(R.drawable.login_banner1).into(holder.itemView.closetItemImage)
        holder.itemView.closetitem_name.text = listdata.title
        holder.itemView.closetitem_uploadby.text = "Uploaded by :"+listdata.creator?.firstname
        if (listdata.hearts!!.size>0)
        {
            holder.itemView.closetitem_favcount.text = listdata.hearts!!.size.toString()
        }

        if (listdata.hearts!!.size>0)
        {
            for (i in listdata.hearts!!.indices)
            {
                val heart = listdata.hearts!![i]
                if (heart.userId.toString().equals(userId))
                {
                    holder.itemView.closetitem_favorite.isChecked = true
                }
            }
        }

        holder.itemView.closetitem_favorite.setOnClickListener {
            EventDetailScreen.closetitemidIf!!.getClosetID(listdata.id.toString())
        }

        holder.itemView.setOnClickListener {
            EventDetailScreen.eventID_IF!!.getEventID(position)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private lateinit var closetItemImage: ImageView
        private lateinit var closetitem_name: TextView
        private lateinit var closetitem_uploadby: TextView
        private lateinit var closetitem_favorite: CheckBox
        private lateinit var closetitem_favcount: TextView
        fun bindItems() {
            closetItemImage = itemView.findViewById(R.id.closetItemImage)
            closetitem_name = itemView.findViewById(R.id.closetitem_name)
            closetitem_uploadby = itemView.findViewById(R.id.closetitem_uploadby)
            closetitem_favorite = itemView.findViewById(R.id.closetitem_favorite)
            closetitem_favcount = itemView.findViewById(R.id.closetitem_favcount)
        }

    }

}