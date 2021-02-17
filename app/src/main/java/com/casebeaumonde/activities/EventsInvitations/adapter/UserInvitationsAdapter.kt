package com.casebeaumonde.activities.EventsInvitations.adapter

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.casebeaumonde.R
import com.casebeaumonde.activities.EventsInvitations.response.UserInvitationsResponse
import com.casebeaumonde.constants.Constants
import com.casebeaumonde.utilities.Utils
import kotlinx.android.synthetic.main.custom_userevents.view.*
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.util.*

class UserInvitationsAdapter (val context: Context,val events : MutableList<UserInvitationsResponse.Data.Event>, val todaymilliseconds:String):
    RecyclerView.Adapter<UserInvitationsAdapter.ViewHolder>()
{


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): UserInvitationsAdapter.ViewHolder {
        var v = LayoutInflater.from(parent.context).inflate(R.layout.custom_userevents, parent, false)
        return ViewHolder(v)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: UserInvitationsAdapter.ViewHolder, position: Int) {

        Glide.with(context).load(Constants.BASE_IMAGE_URL+events.get(position)?.event?.image.toString()!!).placeholder(R.drawable.login_banner).into(holder.itemView.event_imageView)
        holder.itemView.event_sentby.setText("Sent by: "+events.get(position).event?.creator?.company)
        holder.itemView.event_sentat.setText("Sent at: "+ Utils.changeDateTimeToDateTime(events.get(position).createdAt))
        holder.itemView.event_name.setText(events.get(position).event?.title)
        holder.itemView.event_decs.setText(events.get(position).description)
        holder.itemView.event_startdate.setText("Start: "+Utils.changeDateTimeToDateTime(events.get(position).createdAt))
        holder.itemView.event_enddate.setText("End: "+Utils.changeDateTimeToDateTime(events.get(position).expiresAt))
        holder.itemView.event_status.setText("Status: "+events.get(position).status)


        val date = events.get(position).expiresAt
        val formatter: DateTimeFormatter =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH)
        val localDate: LocalDateTime = LocalDateTime.parse(date, formatter)
        val timeInMillisecondsExpire: Long = localDate.atOffset(ZoneOffset.UTC).toInstant().toEpochMilli()

        if (todaymilliseconds > timeInMillisecondsExpire.toString())
        {
          //  holder.itemView.visibility = View.GONE
        }

        holder.itemView.setOnClickListener {
           // Toast.makeText(context,""+events.get(position).event?.title,Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int {
       return events.size
    }

    class ViewHolder(itemView : View):RecyclerView.ViewHolder(itemView) {

        fun bindItems()
        {
            val event_imageView : ImageView
            val event_sentby : TextView
            val event_sentat : TextView
            val event_name : TextView
            val event_decs : TextView
            val event_startdate : TextView
            val event_enddate : TextView
            val event_status : TextView
            val details_bt: Button
            val accept_bt : Button
            val decline_bt : Button

            event_imageView = itemView.findViewById(R.id.event_imageView)
            event_sentby = itemView.findViewById(R.id.event_sentby)
            event_sentat = itemView.findViewById(R.id.event_sentat)
            event_name = itemView.findViewById(R.id.event_name)
            event_decs = itemView.findViewById(R.id.event_decs)
            event_startdate = itemView.findViewById(R.id.event_startdate)
            event_enddate = itemView.findViewById(R.id.event_enddate)
            event_status=  itemView.findViewById(R.id.event_status)
            details_bt = itemView.findViewById(R.id.details_bt)
            decline_bt = itemView.findViewById(R.id.decline_bt)
        }
    }
}