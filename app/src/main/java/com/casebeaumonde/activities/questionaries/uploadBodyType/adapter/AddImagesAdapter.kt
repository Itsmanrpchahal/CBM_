package com.casebeaumonde.activities.questionaries.uploadBodyType.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.casebeaumonde.R
import kotlinx.android.synthetic.main.customphoto.view.*

class AddImagesAdapter(var context: Context, var list: ArrayList<String?>) : RecyclerView.Adapter<AddImagesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddImagesAdapter.ViewHolder {
        var v = LayoutInflater.from(parent.context).inflate(R.layout.customphoto, parent, false)

        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: AddImagesAdapter.ViewHolder, position: Int) {
       Glide.with(context).load(list.get(position)).placeholder(R.drawable.login_banner).into(holder.itemView.add_image)

        holder.itemView.delete_image.setOnClickListener {
            val position: Int = holder.getAdapterPosition()
            list.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position,list.size)
        }
    }

    override fun getItemCount(): Int {
       return list.size
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
    lateinit var add_image : ImageView
    lateinit var delete_image : ImageView
        fun bindItems()
        {
            add_image = itemView.findViewById(R.id.add_image)
            delete_image = itemView.findViewById(R.id.delete_image)
        }
    }
}