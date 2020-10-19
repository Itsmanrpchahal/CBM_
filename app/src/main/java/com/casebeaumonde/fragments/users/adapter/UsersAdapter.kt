package com.casebeaumonde.fragments.users.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.casebeaumonde.R
import com.casebeaumonde.fragments.users.Response.UsersResponse
import com.casebeaumonde.activities.notifications.response.NotificationsResponse
import com.casebeaumonde.fragments.profile.ViewProfile
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.customuser.view.*

class UsersAdapter (val context : Context, val userList:MutableList<UsersResponse.Data.User>,var filepath : String): RecyclerView.Adapter<UsersAdapter.ViewHolder>()
{

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.customuser,parent,false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
       return userList.size
    }

    override fun onBindViewHolder(holder: UsersAdapter.ViewHolder, position: Int) {
        val users = userList?.get(position)
        holder.itemView.userName.text = users.firstname+" "+users.lastname
        Glide.with(context).load(filepath+users.avatar).placeholder(R.drawable.login_banner).into(holder.itemView.userImage)

        holder.itemView.setOnClickListener {
            context.startActivity(Intent(context,ViewProfile::class.java).putExtra("userID",users.id.toString()))
        }
    }
    class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView) {

        fun bindItems(notificationsResponse: NotificationsResponse)
        {
            var userImage : CircleImageView
            var userName : TextView

            userImage = itemView.findViewById(R.id.userImage)
            userName = itemView.findViewById(R.id.userName)
        }
    }

}