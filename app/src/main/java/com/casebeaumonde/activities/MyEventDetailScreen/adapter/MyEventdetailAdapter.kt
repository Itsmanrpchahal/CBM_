package com.casebeaumonde.activities.MyEventDetailScreen.adapter

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
import com.casebeaumonde.activities.MyEventDetailScreen.EventDetailResponse
import com.casebeaumonde.activities.MyEventDetailScreen.MyEventDetailScreen
import com.casebeaumonde.activities.eventDetail.adapter.EventDetailAdapter
import com.casebeaumonde.constants.Constants
import com.casebeaumonde.utilities.Utils
import kotlinx.android.synthetic.main.closetsitems.view.*
import kotlinx.android.synthetic.main.custommyevent.view.*
import kotlinx.android.synthetic.main.custommyevent.view.closetItemImage
import kotlinx.android.synthetic.main.custommyevent.view.closet_title
import kotlinx.android.synthetic.main.custommyevent.view.closetitem_favcount
import kotlinx.android.synthetic.main.custommyevent.view.closetitem_name

class MyEventdetailAdapter(var context: Context,var eventItems :MutableList<EventDetailResponse.Data.Events.Item>,var userID:String):
    RecyclerView.Adapter<MyEventdetailAdapter.ViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyEventdetailAdapter.ViewHolder {
        var v = LayoutInflater.from(parent.context).inflate(R.layout.custommyevent, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: MyEventdetailAdapter.ViewHolder, position: Int) {
        holder.itemView.closetitem_name.setText(eventItems.get(position).title)
        holder.itemView.closet_title.setText("Creator by: "+eventItems.get(position).creator.firstname+" "+eventItems.get(position).creator.lastname+" "+Utils.changeDateTimeToDateTime(eventItems.get(position).createdAt))
        Glide.with(context).load(Constants.BASE_IMAGE_URL+eventItems.get(position).picture).placeholder(R.drawable.login_banner1).into(holder.itemView.closetItemImage)

        if (eventItems.get(position).hearts!!.size>0)
        {
            holder.itemView.closetitem_favcount.text = eventItems.get(position).hearts!!.size.toString()
        }
        if (eventItems.get(position).hearts!!.size>0)
        {
            for (i in eventItems.get(position).hearts!!.indices)
            {
                val heart = eventItems.get(position).hearts!![i]
                if (heart.userId.toString().equals(userID))
                {
                    holder.itemView.event_fav.isChecked = true
                }
            }
        }

        holder.itemView.setOnClickListener {
            MyEventDetailScreen.eventidIf?.getClosetID(position.toString())
        }
    }

    override fun getItemCount(): Int {
        return eventItems.size
    }

    class ViewHolder(itemView:View) :RecyclerView.ViewHolder(itemView){

        fun bindItems()
        {
           var closetItemImage : ImageView
           var closetitem_name : TextView
           var closet_title:TextView
           var event_fav : CheckBox
           var closetitem_favcount:TextView

            closetItemImage = itemView.findViewById(R.id.closetItemImage)
            closetitem_name = itemView.findViewById(R.id.closetitem_name)
            closet_title = itemView.findViewById(R.id.closet_title)
            event_fav = itemView.findViewById(R.id.event_fav)
            closetitem_favcount = itemView.findViewById(R.id.closetitem_favcount)
        }
    }
}