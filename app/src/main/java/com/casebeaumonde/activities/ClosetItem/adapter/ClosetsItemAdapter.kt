package com.casebeaumonde.activities.ClosetItm.adapter

import android.app.Dialog
import android.content.Context
import android.view.*
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.casebeaumonde.R
import com.casebeaumonde.activities.ClosetItem.ClosetsItems
import com.casebeaumonde.activities.ClosetItem.IF.ClosetItemID_IF
import com.casebeaumonde.activities.ClosetItem.response.ClosetsItemsResponse
import com.casebeaumonde.constants.Constants
import kotlinx.android.synthetic.main.closetsitems.view.*

class ClosetsItemAdapter(var context: Context, var closetsItems: MutableList<ClosetsItemsResponse.Data.Closet.Item>,var userid:String):
    RecyclerView.Adapter<ClosetsItemAdapter.ViewHolder>() {

    lateinit var ClosetItemID_IF : ClosetItemID_IF
    lateinit var hearlist : ArrayList<ClosetsItemsResponse.Data.Closet.Item.Heart>
    lateinit var dialog : Dialog

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ClosetsItemAdapter.ViewHolder {
        var v = LayoutInflater.from(parent.context).inflate(R.layout.closetsitems,parent,false)
        return ViewHolder(v)
    }


    override fun onBindViewHolder(holder: ClosetsItemAdapter.ViewHolder, position: Int) {
        var closetsItems = closetsItems.get(position)
        Glide.with(context).load(Constants.BASE_IMAGE_URL+closetsItems?.picture).placeholder(R.drawable.login_banner).into(holder.itemView.closetItemImage)
        holder.itemView.closetitem_name.text = closetsItems?.title
        holder.itemView.closetitem_uploadby.text = closetsItems?.creator?.firstname

        if (closetsItems?.hearts.size != 0)
        {
            holder.itemView.closetitem_favcount.text = closetsItems?.hearts.size.toString()
        }

        holder.itemView.closetitem_favorite.setOnClickListener {
            ClosetsItems.closetitemidIf!!.getClosetID(closetsItems.id.toString())
        }
        searchUserHeart(closetsItems,holder.itemView.closetitem_favorite)

        holder.itemView.setOnClickListener {
            ClosetsItems.viewclosetidIf!!.getID(position)
        }


    }

    fun searchUserHeart(
        closetsItems: ClosetsItemsResponse.Data.Closet.Item,
        closetitemFavorite: CheckBox
    )
    {
        if (closetsItems.hearts.size>0)
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
       return closetsItems.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(closetsItems : ClosetsItemsResponse)
        {
            var closetItemImage : ImageView
            var closetitem_name : TextView
            var closetitem_uploadby : TextView
            var closetitem_favorite : CheckBox
            var closetitem_favcount : TextView

            closetItemImage = itemView.findViewById(R.id.closetItemImage)
            closetitem_name = itemView.findViewById(R.id.closetitem_name)
            closetitem_uploadby = itemView.findViewById(R.id.closetitem_uploadby)
            closetitem_favorite = itemView.findViewById(R.id.closetitem_favorite)
            closetitem_favcount = itemView.findViewById(R.id.closetitem_favcount)
        }
    }
}