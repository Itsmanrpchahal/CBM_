package com.casebeaumonde.activities.ackasClient.clientClosetsItems.adapter

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.casebeaumonde.R
import com.casebeaumonde.activities.ClosetItem.ClosetsItems
import com.casebeaumonde.activities.ClosetItem.response.FilterResponse
import com.casebeaumonde.activities.ackasClient.clientClosetsItems.ClientClosetItems
import com.casebeaumonde.constants.Constants
import kotlinx.android.synthetic.main.closetsitems.view.*
import retrofit2.Response

class ClientFilterAdapter (
    val context: Context,
    val filterData: Response<FilterResponse>,
    var userid: String,
    var select: Int,
    var selectAll: Int
        ):RecyclerView.Adapter<ClientFilterAdapter.ViewHolder>() {
    lateinit var hearlist : ArrayList<FilterResponse>
    lateinit var dialog : Dialog
    var selected: ArrayList<String> = ArrayList<String>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ClientFilterAdapter.ViewHolder {
        var v = LayoutInflater.from(parent.context).inflate(R.layout.closetsitems, parent, false)
        selected = ArrayList()
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ClientFilterAdapter.ViewHolder, position: Int) {
        Glide.with(context).load(Constants.BASE_IMAGE_URL + filterData?.body()?.data?.closet?.get(position)?.picture).placeholder(R.drawable.login_banner1).into(
            holder.itemView.closetItemImage
        )
        holder.itemView.closetitem_name.text = filterData?.body()?.data?.closet?.get(position)?.title
        holder.itemView.closetitem_uploadby.text = filterData?.body()?.data?.closet?.get(position)?.creator?.firstname+" "+filterData?.body()?.data?.closet?.get(position)?.creator?.lastname

        if (filterData?.body()?.data?.closet?.get(position)?.hearts?.size != 0)
        {
            holder.itemView.closetitem_favcount.text = filterData?.body()?.data?.closet?.get(position)?.hearts?.size.toString()
        }

        holder.itemView.hainger.visibility = View.GONE
//        holder.itemView.closetitem_favorite.setOnClickListener {
//            ClosetsItems.closetitemidIf!!.getClosetID(filterData?.body()?.data?.closet?.get(position)?.id.toString())
//        }
//         searchUserHeart(filterData.body()?.data?.closet, holder.itemView.closetitem_favorite,position)

        holder.itemView.setOnClickListener {
            ClientClosetItems.viewclosetidIf!!.getID(position)
        }

        if (select==0)
        {
            holder.itemView.select_checkbox.visibility = View.GONE
        }else{
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
                ClientClosetItems.selectedclosetId?.getID(filterData?.body()?.data?.closet?.get(position)?.id.toString(), "1")
            }  else {
                ClientClosetItems.selectedclosetId?.getID(filterData?.body()?.data?.closet?.get(position)?.id.toString(), "0")
            }
        }
    }

    fun searchUserHeart(
        closetsItems: List<FilterResponse.Data.Closet>?,
        closetitemFavorite: CheckBox,
        position: Int
    )
    {
        if (closetsItems?.get(position)?.hearts!!.size>0)
        {
            for (i in closetsItems?.get(position)?.hearts!!.indices)
            {
                val heart = closetsItems?.get(position)?.hearts!![i]
                if (heart.userId.toString().equals(userid))
                {
                    closetitemFavorite.isChecked = true
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return filterData.body()?.data?.closet?.size!!
    }

    class ViewHolder (itemView : View):RecyclerView.ViewHolder(itemView){
        fun bindItems()
        {
            var closetItemImage : ImageView
            var closetitem_name : TextView
            var closetitem_uploadby : TextView
            var closetitem_favorite : CheckBox
            var select_checkbox : CheckBox
            var closetitem_favcount : TextView

            closetItemImage = itemView.findViewById(R.id.closetItemImage)
            closetitem_name = itemView.findViewById(R.id.closetitem_name)
            closetitem_uploadby = itemView.findViewById(R.id.closetitem_uploadby)
            closetitem_favorite = itemView.findViewById(R.id.closetitem_favorite)
            closetitem_favcount = itemView.findViewById(R.id.closetitem_favcount)
            select_checkbox = itemView.findViewById(R.id.select_checkbox)
        }
    }
}
