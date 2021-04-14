package com.casebeaumonde.activities.ShopItems.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.casebeaumonde.R
import com.casebeaumonde.activities.ShopItems.ShopItemsActivity
import com.casebeaumonde.activities.ShopItems.response.ShopFilterItemsResponse
import com.casebeaumonde.activities.ShopItems.response.ShopItemsResponse
import com.casebeaumonde.constants.Constants
import kotlinx.android.synthetic.main.customuser.view.*

class ShopItemsAdapter(var context: Context,var items: ArrayList<ShopItemsResponse.Data.Item>,var filteritems : ArrayList<ShopFilterItemsResponse.Datum>,var type:String): RecyclerView.Adapter<ShopItemsAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var v = LayoutInflater.from(parent.context).inflate(R.layout.customuser, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        if (type.equals("filter"))
        {
            if (filteritems.size>0)
            {
                Glide.with(context).load(Constants.BASE_IMAGE_URL+filteritems.get(position).image).placeholder(R.drawable.login_banner1).into(holder.itemView.userImage)
                holder.itemView.userName.setOnClickListener {
                    ShopItemsActivity.getShopItemID?.getID(position.toString(), filteritems.get(position).id.toString(),"filter")
                }
            }

        }else {
            if (items.size>0)
            {
                Glide.with(context).load(Constants.BASE_IMAGE_URL+items.get(position).image).placeholder(R.drawable.login_banner1).into(holder.itemView.userImage)
                holder.itemView.userName.setOnClickListener {
                    ShopItemsActivity.getShopItemID?.getID(position.toString(), items.get(position).id.toString(),"all")
                }
            }

        }

        holder.itemView.userName.text = "Description"




    }




    override fun getItemCount(): Int {
        if (type.equals("filter"))
        {

            return filteritems.size
        } else {
            return items.size
        }
    }

    class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView) {

        lateinit var userImage : ImageView
        lateinit var userName : TextView
        fun bindItems()
        {
            userImage = itemView.findViewById(R.id.userImage)
            userName = itemView.findViewById(R.id.userName)
        }
    }
}