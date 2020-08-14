package com.casebeaumonde.fragments.users.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import com.casebeaumonde.R

class UsersAdapter (val userList:ArrayList<String>): RecyclerView.Adapter<UsersAdapter.ViewHolder>()
{

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.customuser,parent,false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
       return 14
    }

    override fun onBindViewHolder(holder: UsersAdapter.ViewHolder, position: Int) {

    }
    class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView) {

    }

}