package com.casebeaumonde.fragments.chat.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.casebeaumonde.R
import com.casebeaumonde.activities.openchat.SendChat
import com.casebeaumonde.constants.Constants
import com.casebeaumonde.fragments.chat.GetChatUsers
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.custom_chatuser.view.*

class AllChatUsersAdapter (var context: Context, var list: MutableList<GetChatUsers.Data.User>) :
    RecyclerView.Adapter<AllChatUsersAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllChatUsersAdapter.ViewHolder {
        var v = LayoutInflater.from(parent.context).inflate(R.layout.custom_chatuser,parent,false)

        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: AllChatUsersAdapter.ViewHolder, position: Int) {
        val listData = list.get(position)

        Glide.with(context).load(Constants.BASE_IMAGE_URL+listData.avatar).placeholder(R.drawable.login_banner).into(holder.itemView.chatuser_image)
        holder.itemView.chatuser_name.text = listData.firstname+" "+listData.lastname

        holder.itemView.setOnClickListener {
            context.startActivity(Intent(context, SendChat::class.java).putExtra("id",listData.id.toString()).putExtra("chatname",listData.firstname+" "+listData.lastname))
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ViewHolder (itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bindItems()
        {
            var chatuser_image : CircleImageView
            var chatuser_name : TextView

            chatuser_image = itemView.findViewById(R.id.chatuser_image)
            chatuser_name = itemView.findViewById(R.id.chatuser_name)
        }
    }
}