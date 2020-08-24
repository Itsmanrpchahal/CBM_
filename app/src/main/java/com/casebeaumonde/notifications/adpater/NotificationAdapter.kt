package com.casebeaumonde.notifications.adpater

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.casebeaumonde.R
import com.casebeaumonde.constants.Constants
import com.casebeaumonde.notifications.response.NotificationsResponse
import com.casebeaumonde.utilities.Utils
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.notificationlayoiut.view.*

class NotificationAdapter(val context : Context,val notifications: MutableList<NotificationsResponse.Data.Notification>): RecyclerView.Adapter<NotificationAdapter.ViewHolder>()
{
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NotificationAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.notificationlayoiut,parent,false)

        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return notifications!!.size
    }

    override fun onBindViewHolder(holder: NotificationAdapter.ViewHolder, position: Int) {
        val notificationModel = notifications?.get(position)
        holder.itemView.notification_text.text = notificationModel.message
        holder.itemView.notification_time.text =
            Utils.changeDateTimeToDate(notificationModel.createdAt).toString()
        Glide.with(context).load(Constants.BASE_IMAGE_URL+""+notificationModel.data.user.avatar).placeholder(R.drawable.login_banner).into(holder.itemView.notification_image)
    }

    class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView) {

        fun bindItems(notificationsResponse: NotificationsResponse)
        {
            var  notification_text : TextView
            var  notification_time : TextView
            var notification_image : CircleImageView
            notification_text = itemView.findViewById(R.id.notification_text)
            notification_time = itemView.findViewById(R.id.notification_time)
            notification_image = itemView.findViewById(R.id.notification_image)
        }

    }

}