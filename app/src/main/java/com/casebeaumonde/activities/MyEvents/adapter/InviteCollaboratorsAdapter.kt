package com.casebeaumonde.activities.MyEvents.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.casebeaumonde.R
import com.casebeaumonde.activities.MyEvents.Response.InviteCollaboratorsResponse
import com.casebeaumonde.activities.MyEvents.Response.InviteCustomersResponse
import com.casebeaumonde.constants.Constants
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.custom_inviteuser.view.*

class InviteCollaboratorsAdapter(var context: Context, var users : MutableList<InviteCollaboratorsResponse.Data.InvitedCollaborator>):
    RecyclerView.Adapter<InviteCollaboratorsAdapter.ViewHoilder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): InviteCollaboratorsAdapter.ViewHoilder {
        var v = LayoutInflater.from(parent.context).inflate(R.layout.custom_inviteuser, parent, false)
        //  selected = ArrayList()
        return ViewHoilder(v)
    }



    override fun onBindViewHolder(holder: InviteCollaboratorsAdapter.ViewHoilder, position: Int) {
        Glide.with(context).load(Constants.BASE_IMAGE_URL+users.get(position).avatar).placeholder(R.drawable.login_banner1).into(holder.itemView.userimage)
        holder.itemView.username.setText(users.get(position).firstname+" "+users.get(position).lastname)
        if (users.get(position).invitedStatus.equals("0"))
        {
            holder.itemView.invitebt.setText("Invite")
        } else {
            holder.itemView.invitebt.setText("Invited")
            holder.itemView.invitebt.isEnabled = false
        }
    }

    override fun getItemCount(): Int {

        return users.size
    }

    class ViewHoilder(itemView: View) :RecyclerView.ViewHolder(itemView) {
        fun bindItems()
        {
            var userimage : CircleImageView
            var username : TextView
            var invitebt : Button

            userimage = itemView.findViewById(R.id.userimage)
            username = itemView.findViewById(R.id.username)
            invitebt = itemView.findViewById(R.id.invitebt)
        }
    }
}