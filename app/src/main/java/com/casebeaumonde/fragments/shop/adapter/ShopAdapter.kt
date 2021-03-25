package com.casebeaumonde.fragments.shop.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.casebeaumonde.R
import com.casebeaumonde.constants.Constants
import com.casebeaumonde.fragments.shop.Shop
import com.casebeaumonde.fragments.shop.response.ShopResponse
import kotlinx.android.synthetic.main.custom_shop.view.*
import retrofit2.Response

class ShopAdapter(var context: Context, var shops: Response<ShopResponse>): RecyclerView.Adapter<ShopAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.custom_shop,parent,false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(context).load(Constants.BASE_IMAGE_URL+shops.body()?.data?.get(position)?.avatar).placeholder(R.drawable.login_banner).into(holder.itemView.shopimage)

        holder.itemView.setOnClickListener {
            Shop.getshopidIf?.getID(shops.body()?.data?.get(position)?.id.toString())
        }
    }

    override fun getItemCount(): Int {
        return shops.body()?.data?.size!!
    }


    class ViewHolder (itemView:View):RecyclerView.ViewHolder(itemView){

        fun bind(){
            var shopimage : ImageView
            shopimage = itemView.findViewById(R.id.shopimage)
        }
    }
}