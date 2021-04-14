package com.casebeaumonde.fragments.allClosets.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.casebeaumonde.R
import com.casebeaumonde.activities.ClosetItem.ClosetsItems
import com.casebeaumonde.constants.Constants
import com.casebeaumonde.fragments.allClosets.AllClosets
import com.casebeaumonde.fragments.allClosets.response.AllClosetsResponse
import com.casebeaumonde.utilities.Utils
import kotlinx.android.synthetic.main.closetsitems.view.*

class AllClosetsAdapter (var context: Context,var list: MutableList<AllClosetsResponse.Data.Closet>,val userID : String):
    RecyclerView.Adapter<AllClosetsAdapter.ViewHolder>()
{

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AllClosetsAdapter.ViewHolder {
        var v = LayoutInflater.from(parent.context).inflate(R.layout.closetsitems,parent,false)

        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: AllClosetsAdapter.ViewHolder, position: Int) {
        val listData = list.get(position)

        Glide.with(context).load(Constants.BASE_IMAGE_URL+listData.image).placeholder(R.drawable.login_banner1).into(holder.itemView.closetItemImage)
        holder.itemView.closetitem_name.text = listData.title
        holder.itemView.closetitem_uploadby.text = "Created by "+listData.user?.firstname+" at "+ Utils.changeDateTimeToDateTime(listData.createdAt)

        if (listData?.hearts!!.size != 0)
        {
            holder.itemView.closetitem_favcount.text = listData?.hearts!!.size.toString()
        }

        holder.itemView.setOnClickListener {
            context.startActivity(Intent(context, ClosetsItems::class.java)
                .putExtra(Constants.CLOSETID,""+listData.id).putExtra("userID",listData.creatorId.toString()))
        }

        holder.itemView.closetitem_favorite.setOnClickListener {
            AllClosets.closetitemidIf!!.getClosetID(listData.id.toString())
        }

        holder.itemView.hainger.visibility = View.GONE

        searchUserHeart(listData,holder.itemView.closetitem_favorite)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun searchUserHeart(
        closetsItems: AllClosetsResponse.Data.Closet,
        closetitemFavorite: CheckBox
    )
    {
        if (closetsItems.hearts!!.size>0)
        {
            for (i in closetsItems.hearts!!.indices)
            {
                val heart = closetsItems.hearts!![i]
                if (heart.userId.toString().equals(userID))
                {
                    closetitemFavorite.isChecked = true
                }
            }
        }
    }

    class ViewHolder(itemView : View):RecyclerView.ViewHolder(itemView) {
        fun bindItems()
        {
            val closetItemImage : ImageView
            val closetitem_name : TextView
            val closetitem_uploadby : TextView
            val hainger : ImageButton

            closetItemImage = itemView.findViewById(R.id.closetItemImage)
            closetitem_name = itemView.findViewById(R.id.closetitem_name)
            closetitem_uploadby = itemView.findViewById(R.id.closetitem_uploadby)
            hainger = itemView.findViewById(R.id.hainger)

        }
    }
}

