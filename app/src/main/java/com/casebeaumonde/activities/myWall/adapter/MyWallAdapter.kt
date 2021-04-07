package com.casebeaumonde.activities.myWall.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.casebeaumonde.R
import com.casebeaumonde.activities.ClosetItem.ClosetsItems
import com.casebeaumonde.constants.Constants
import com.casebeaumonde.fragments.profile.profileResponse.MyWallResponse
import com.casebeaumonde.utilities.Utils
import kotlinx.android.synthetic.main.custom_mywall.view.*

class MyWallAdapter(
    val context: Context,
    val myWallList: ArrayList<MyWallResponse.Data.Fashionable>,
    var filePath: String,
    var userID : String
) :
    RecyclerView.Adapter<MyWallAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyWallAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.custom_mywall, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: MyWallAdapter.ViewHolder, position: Int) {
        val fashionable = myWallList?.get(position)

        Glide.with(context).load(Constants.BASE_IMAGE_URL + fashionable.image)
            .placeholder(R.drawable.login_banner1).into(holder.itemView.myWall_image)
        holder.itemView.myWall_closetname.text = fashionable.title
        holder.itemView.myWall_Decs.text = fashionable.description
        holder.itemView.myWall_morenext.text = fashionable.creator?.firstname+" "+fashionable.creator?.lastname
        holder.itemView.myWall_lastupdate.text = "Last update at: " +Utils.changeDateTimeToDateTime(fashionable.updatedAt)

        holder.itemView.setOnClickListener {
            context.startActivity(Intent(context, ClosetsItems::class.java).putExtra(Constants.CLOSETID,fashionable.id.toString()).putExtra("userID",userID))

        }
    }

    override fun getItemCount(): Int {
        return myWallList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems() {
            var myWall_closetname: TextView
            var myWall_image: ImageView
            var myWall_morenext: TextView
            var myWall_Decs: TextView
            var myWall_lastupdate: TextView

            myWall_closetname = itemView.findViewById(R.id.myWall_closetname)
            myWall_image = itemView.findViewById(R.id.myWall_image)
            myWall_morenext = itemView.findViewById(R.id.myWall_morenext)
            myWall_Decs = itemView.findViewById(R.id.myWall_Decs)
            myWall_lastupdate = itemView.findViewById(R.id.myWall_lastupdate)
        }
    }
}