package com.casebeaumonde.fragments.productManagement.adapter

import android.content.Context
import android.content.Intent
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.casebeaumonde.R
import com.casebeaumonde.fragments.productManagement.Products
import com.casebeaumonde.fragments.productManagement.productdetail.ProductDetailScreen
import com.casebeaumonde.fragments.productManagement.response.ProductListResponse
import kotlinx.android.synthetic.main.customproduct.view.*

class ProductListAdapter (var context: Context,
                          var products: MutableList<ProductListResponse.Data.Products.Datum>):
    RecyclerView.Adapter<ProductListAdapter.ViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProductListAdapter.ViewHolder {
        var v = LayoutInflater.from(parent.context).inflate(R.layout.customproduct, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ProductListAdapter.ViewHolder, position: Int) {
        if (products.get(position.toInt()).productImages.size>=1)
        {
            Glide.with(context).load(products.get(position).productImages.get(0).image).placeholder(R.drawable.login_banner1).into(holder.itemView.closetItemImage)
        }
         holder.itemView.name.text = products.get(position).productName
        holder.itemView.title.text = products.get(position).shortDescription
        holder.itemView.actualprice.text = "$"+products.get(position).regularPrice.toString()
        holder.itemView.actualprice.setPaintFlags(holder.itemView.actualprice.getPaintFlags() or Paint.STRIKE_THRU_TEXT_FLAG)
        holder.itemView.newprice.text = "$"+products.get(position).sellPrice.toString()
        if (products.get(position).status==1)
        {
            holder.itemView.unpublish.setText("Unpublish")
        } else {
            holder.itemView.unpublish.setText("Publish")
        }

        holder.itemView.unpublish.setOnClickListener {
            Products.unPublishIF?.unPublishID(products.get(position).id.toString())
        }

        holder.itemView.setOnClickListener {
            context.startActivity(Intent(context,ProductDetailScreen::class.java).putExtra("position",position.toString()))
        }

        holder.itemView.edit.setOnClickListener {
            Products.editProductIF?.getEditProductID(products.get(position).id.toString(),position.toString(),"edit")
        }
    }

    override fun getItemCount(): Int {
        return products.size
    }

    class ViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bindItems(){
            var closetItemImage : ImageView
            var name : TextView
            var title : TextView
            var actualprice : TextView
            var newprice : TextView
            var unpublish : Button
            var edit : Button

            closetItemImage = itemView.findViewById(R.id.closetItemImage)
            name = itemView.findViewById(R.id.name)
            title = itemView.findViewById(R.id.title)
            actualprice = itemView.findViewById(R.id.actualprice)
            newprice = itemView.findViewById(R.id.newprice)
            unpublish = itemView.findViewById(R.id.unpublish)
            edit = itemView.findViewById(R.id.edit)
        }
    }

}