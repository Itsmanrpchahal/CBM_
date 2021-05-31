package com.casebeaumonde.activities.ackasClient.clientClosetsItems.adapter

import android.app.Dialog
import android.content.Context
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
import com.casebeaumonde.activities.ClosetItem.IF.ClosetItemID_IF
import com.casebeaumonde.activities.ClosetItem.response.ClosetsItemsResponse
import com.casebeaumonde.activities.ClosetItm.adapter.ClosetsItemAdapter
import com.casebeaumonde.activities.ackasClient.clientClosetsItems.ClientClosetItems
import com.casebeaumonde.constants.Constants
import kotlinx.android.synthetic.main.closetsitems.view.*

class ClientClosetItemsAdapter(var context: Context,
                               var closetsItem: MutableList<ClosetsItemsResponse.Data.Closet.Item>,
                               var userid: String,
                               var select: Int,
                               var selectAll : Int) :
    RecyclerView.Adapter<ClientClosetItemsAdapter.ViewHolder>() {

    lateinit var ClosetItemID_IF : ClosetItemID_IF
    lateinit var hearlist : ArrayList<ClosetsItemsResponse.Data.Closet.Item.Heart>
    lateinit var dialog : Dialog
    var selected: ArrayList<String> = ArrayList<String>()
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ClientClosetItemsAdapter.ViewHolder {
        var v = LayoutInflater.from(parent.context).inflate(R.layout.closetsitems, parent, false)
        selected = ArrayList()
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ClientClosetItemsAdapter.ViewHolder, position: Int) {
        var closetsItems = closetsItem.get(position)
        Glide.with(context).load(Constants.BASE_IMAGE_URL + closetsItems?.picture).placeholder(R.drawable.login_banner1).into(
            holder.itemView.closetItemImage
        )

        holder.itemView.closetitem_name.text = closetsItems?.title
        holder.itemView.closetitem_uploadby.text = closetsItems?.creator?.firstname

        if (closetsItems?.hearts?.size != 0)
        {
            holder.itemView.closetitem_favcount.text = closetsItems?.hearts?.size.toString()
        }

        holder.itemView.closetitem_favorite.setOnClickListener {
            ClientClosetItems.closetitemidIf!!.getClosetID(closetsItems.id.toString())
        }
        searchUserHeart(closetsItems, holder.itemView.closetitem_favorite)

        holder.itemView.hainger.visibility = View.GONE
        holder.itemView.setOnClickListener {
            ClientClosetItems.viewclosetidIf!!.getID(position)
        }

        if (select==0)
        {
            holder.itemView.select_checkbox.visibility = View.GONE
        } else{
            holder.itemView.select_checkbox.visibility = View.VISIBLE
        }

        if (selectAll==1)
        {
            holder.itemView.select_checkbox.isChecked = true
        }else{
            holder.itemView.select_checkbox.isChecked = false
        }


        holder.itemView.select_checkbox.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked)
            {
                ClientClosetItems.selectedclosetId?.getID(closetsItems.id.toString(), "1")
            }  else {
                ClientClosetItems.selectedclosetId?.getID(closetsItems.id.toString(), "0")
            }
        }
    }

    fun searchUserHeart(
        closetsItems: ClosetsItemsResponse.Data.Closet.Item,
        closetitemFavorite: CheckBox
    )
    {
        if (closetsItems.hearts!!.size>0)
        {
            for (i in closetsItems.hearts!!.indices)
            {
                val heart = closetsItems.hearts!![i]
                if (heart.userId.toString().equals(userid))
                {
                    closetitemFavorite.isChecked = true
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return closetsItem.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItems(closetsItems: ClosetsItemsResponse)
        {
            var closetItemImage : ImageView
            var closetitem_name : TextView
            var closetitem_uploadby : TextView
            var closetitem_favorite : CheckBox
            var select_checkbox : CheckBox
            var closetitem_favcount : TextView
            var hainger : ImageButton

            closetItemImage = itemView.findViewById(R.id.closetItemImage)
            closetitem_name = itemView.findViewById(R.id.closetitem_name)
            closetitem_uploadby = itemView.findViewById(R.id.closetitem_uploadby)
            closetitem_favorite = itemView.findViewById(R.id.closetitem_favorite)
            closetitem_favcount = itemView.findViewById(R.id.closetitem_favcount)
            select_checkbox = itemView.findViewById(R.id.select_checkbox)
            hainger = itemView.findViewById(R.id.hainger)
        }
    }
}