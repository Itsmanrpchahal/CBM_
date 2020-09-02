package com.casebeaumonde.fragments.designers.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.casebeaumonde.R
import com.casebeaumonde.constants.Constants
import com.casebeaumonde.fragments.designers.Response.DesignersResponse
import com.casebeaumonde.activities.notifications.response.NotificationsResponse
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.customuser.view.*

class DesignerAdapter (val context : Context,var designers : MutableList<DesignersResponse.Data.User>) : RecyclerView.Adapter<DesignerAdapter.ViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DesignerAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.customuser,parent,false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
       return designers.size
    }

    override fun onBindViewHolder(holder: DesignerAdapter.ViewHolder, position: Int) {
        val designers = designers?.get(position)
        holder.itemView.userName.text = designers.firstname+" "+designers.lastname
        Glide.with(context).load(Constants.BASE_IMAGE_URL+designers.avatar).placeholder(R.drawable.login_banner).into(holder.itemView.userImage)
    }

    class ViewHolder (itemView: View): RecyclerView.ViewHolder(itemView){
        fun bindItems(notificationsResponse: NotificationsResponse)
        {
            var userImage : CircleImageView
            var userName : TextView

            userImage = itemView.findViewById(R.id.userImage)
            userName = itemView.findViewById(R.id.userName)
        }
    }
}
