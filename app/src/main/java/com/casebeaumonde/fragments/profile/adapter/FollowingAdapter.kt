package com.casebeaumonde.fragments.profile.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.casebeaumonde.R
import com.casebeaumonde.constants.Constants
import com.casebeaumonde.fragments.profile.Profile
import com.casebeaumonde.fragments.profile.profileResponse.UserProfileResponse
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.custom_followtab.view.*

class FollowingAdapter (
    val context: Context,
    val followers: MutableList<UserProfileResponse.Data.User.Following>
) :
    RecyclerView.Adapter<FollowingAdapter.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FollowingAdapter.ViewHolder {
        val v =
            LayoutInflater.from(parent.context).inflate(R.layout.custom_followtab, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: FollowingAdapter.ViewHolder, position: Int) {
        val list = followers.get(position)
        holder.itemView.username.setText(list.firstname + " " + list.lastname)
        Glide.with(context).load(Constants.BASE_IMAGE_URL + list.avatar)
            .placeholder(R.drawable.login_banner).into(holder.itemView.userimage)

        holder.itemView.viewprofilebt.setOnClickListener {
            Profile.getUserID?.getUserID(list.id.toString())
        }
    }

    override fun getItemCount(): Int {
        return followers.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems() {
            val userimage: CircleImageView
            val username: TextView
            val viewprofilebt : Button
            userimage = itemView.findViewById(R.id.userimage)
            username = itemView.findViewById(R.id.username)
            viewprofilebt = itemView.findViewById(R.id.viewprofilebt)
        }
    }
}