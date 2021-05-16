package com.casebeaumonde.activities.MyEvents.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.casebeaumonde.R
import com.casebeaumonde.activities.MyEventDetailScreen.MyEventDetailScreen
import com.casebeaumonde.activities.MyEvents.MyEventsActivity
import com.casebeaumonde.activities.MyEvents.Response.FilterEventResponse
import com.casebeaumonde.activities.MyEvents.Response.MyEventsResponse
import com.casebeaumonde.activities.eventDetail.EventDetailScreen
import com.casebeaumonde.constants.Constants
import com.casebeaumonde.utilities.Utils
import kotlinx.android.synthetic.main.custom_myeventsview.view.*
import org.w3c.dom.Text

class MyEventsAdapter(
    var context: Context, var events: MutableList<MyEventsResponse.Data.Events.Datum>,
    var filterevents: MutableList<FilterEventResponse.Data.Event.Datum>, var type: String
) : RecyclerView.Adapter<MyEventsAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyEventsAdapter.ViewHolder {
        var v =
            LayoutInflater.from(parent.context).inflate(R.layout.custom_myeventsview, parent, false)
        //  selected = ArrayList()
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: MyEventsAdapter.ViewHolder, position: Int) {

        if (type.equals("all")) {
            holder.itemView.closet_username.setText(events.get(position).title)
            holder.itemView.closet_descripition.setText(events.get(position).description)
            holder.itemView.eventstart.setText(
                "From: " + Utils.changeDateTimeToDateTime(
                    events.get(
                        position
                    ).from
                )
            )
            holder.itemView.eventend.setText(
                "To: " + Utils.changeDateTimeToDateTime(
                    events.get(
                        position
                    ).to
                )
            )
            holder.itemView.event_type.setText("Type: " + events.get(position).type)
            holder.itemView.event_createat.setText(
                "Created at:" + Utils.changeDateTimeToDateTime(
                    events.get(position).createdAt
                )
            )
            Glide.with(context).load(Constants.BASE_IMAGE_URL + events.get(position).image)
                .placeholder(R.drawable.login_banner1).into(holder.itemView.closet_banner)

            if (events.get(position).type.equals("private")) {
                holder.itemView.invite_customerbt.visibility = View.GONE
            } else {
                holder.itemView.invite_customerbt.visibility = View.VISIBLE
            }
        } else {
            holder.itemView.closet_username.setText(filterevents.get(position).title)
            holder.itemView.closet_descripition.setText(filterevents.get(position).description)
            holder.itemView.eventstart.setText(
                "From: " + Utils.changeDateTimeToDateTime(
                    filterevents.get(position).from
                )
            )
            holder.itemView.eventend.setText(
                "To: " + Utils.changeDateTimeToDateTime(
                    filterevents.get(
                        position
                    ).to
                )
            )
            holder.itemView.event_type.setText("Type: " + filterevents.get(position).type)
            holder.itemView.event_createat.setText(
                "Created at:" + Utils.changeDateTimeToDateTime(
                    filterevents.get(position).createdAt
                )
            )
            Glide.with(context).load(Constants.BASE_IMAGE_URL + filterevents.get(position).image)
                .placeholder(R.drawable.login_banner1).into(holder.itemView.closet_banner)

            if (filterevents.get(position).type.equals("private")) {
                holder.itemView.invite_customerbt.visibility = View.GONE
            } else {
                holder.itemView.invite_customerbt.visibility = View.VISIBLE
            }
        }


        holder.itemView.invite_customerbt.setOnClickListener {
            if (type.equals("all")) {
                MyEventsActivity.eventId?.getEVentID(events.get(position).id.toString(), "customer")
            } else {
                MyEventsActivity.eventId?.getEVentID(
                    filterevents.get(position).id.toString(),
                    "customer"
                )
            }

        }

        holder.itemView.invite_collaboratebt.setOnClickListener {
            if (type.equals("all")) {
                MyEventsActivity.eventId?.getEVentID(events.get(position).id.toString(), "colloborate")
            } else {
                MyEventsActivity.eventId?.getEVentID(
                    filterevents.get(position).id.toString(),
                    "colloborate"
                )
            }
           // MyEventsActivity.eventId?.getEVentID(position.toString(), "colloborate")
        }

        holder.itemView.closet_go_to_closets.setOnClickListener {
            context.startActivity(
                Intent(context, MyEventDetailScreen::class.java).putExtra(
                    Constants.EVENTID,
                    events.get(position).id.toString()
                )
            )
        }

        holder.itemView.closets_additem.setOnClickListener {
            MyEventsActivity.geteventidForcreateitem?.getEventID_FOrCreateItem(events.get(position).id.toString())
        }
    }

    override fun getItemCount(): Int {
        return events.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems() {
            var closet_banner: ImageView
            var closet_username: TextView
            var eventstart: TextView
            var eventend: TextView
            var event_type: TextView
            var event_createat: TextView
            var closet_go_to_closets: TextView
            var invite_customerbt: Button
            var invite_collaboratebt: Button
            var closets_additem : TextView

            closet_banner = itemView.findViewById(R.id.closet_banner)
            closet_username = itemView.findViewById(R.id.closet_username)
            eventstart = itemView.findViewById(R.id.eventstart)
            eventend = itemView.findViewById(R.id.eventend)
            event_type = itemView.findViewById(R.id.event_type)
            event_createat = itemView.findViewById(R.id.event_createat)
            closet_go_to_closets = itemView.findViewById(R.id.closet_go_to_closets)
            invite_customerbt = itemView.findViewById(R.id.invite_customerbt)
            invite_collaboratebt = itemView.findViewById(R.id.invite_collaboratebt)
            closets_additem = itemView.findViewById(R.id.closets_additem)
        }
    }
}