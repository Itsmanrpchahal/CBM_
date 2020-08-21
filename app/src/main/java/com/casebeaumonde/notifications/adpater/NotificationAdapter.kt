package com.casebeaumonde.notifications.adpater

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.casebeaumonde.R
import com.casebeaumonde.fragments.users.adapter.UsersAdapter

class NotificationAdapter (val notifications:ArrayList<String>): RecyclerView.Adapter<NotificationAdapter.ViewHolder>()
{

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NotificationAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.notificationlayoiut,parent,false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return 14
    }

    override fun onBindViewHolder(holder: NotificationAdapter.ViewHolder, position: Int) {

    }

    class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView) {

    }

}