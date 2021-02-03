package com.casebeaumonde.fragments.cart.adapter

import android.content.Context
import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.casebeaumonde.R
import com.casebeaumonde.constants.Constants
import com.casebeaumonde.fragments.cart.reponse.CartItemsResponse
import kotlinx.android.synthetic.main.cart_custom.view.*

class CartAdapter(val context: Context, var cartsItems: ArrayList<CartItemsResponse.Data.CartItem>):
    RecyclerView.Adapter<CartAdapter.ViewHolder>() {

     var count =0


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.cart_custom,parent,false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: CartAdapter.ViewHolder, position: Int) {
        val cartlist = cartsItems.get(position)
        Glide.with(context).load(Constants.BASE_IMAGE_URL+cartlist.image).placeholder(R.drawable.login_banner).into(holder.itemView.cartimage)
        holder.itemView.itemdata.setText("Name :"+cartlist.name)
        holder.itemView.pricetv.setText("$"+cartlist.price)

        holder.itemView.plus.setOnClickListener {
            count++
            holder.itemView.count_tv.text = count.toString()
        }


        holder.itemView.minus.setOnClickListener {

            if (count.toInt()>1)
            {
                count--
                holder.itemView.count_tv.text = count.toString()
            }

        }

    }

    override fun getItemCount(): Int {
       return cartsItems.size
    }

    class ViewHolder (itemView : View) : RecyclerView.ViewHolder(itemView) {
        fun bindfun()
        {
            var cartimage : ImageView
            var itemdata : TextView
            var pricetv : TextView
            var plus : ImageButton
            var minus : ImageButton
            var count_tv : TextView

            cartimage = itemView.findViewById(R.id.cartimage)
            itemdata = itemView.findViewById(R.id.itemdata)
            pricetv = itemView.findViewById(R.id.pricetv)
            plus = itemView.findViewById(R.id.plus)
            minus = itemView.findViewById(R.id.minus)
            count_tv = itemView.findViewById(R.id.count_tv)
        }
    }
}