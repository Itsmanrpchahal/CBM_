package com.casebeaumonde.activities.openchat.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.casebeaumonde.R
import com.casebeaumonde.activities.openchat.response.GetChatResponse
import kotlinx.android.synthetic.main.content_send_chat.view.*

class GetChatAdapter(
    var context: Context,
    var userId: String,
    var list: MutableList<GetChatResponse.Data.Message>
):
    RecyclerView.Adapter<GetChatAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GetChatAdapter.ViewHolder {
        var v = LayoutInflater.from(parent.context).inflate(
            R.layout.content_send_chat,
            parent,
            false
        )
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: GetChatAdapter.ViewHolder, position: Int) {
       var  listdata = list.get(position)
        if (!userId.toString().equals(listdata.from.toString()))
        {
            holder.itemView.sendmesg.visibility = View.VISIBLE
            holder.itemView.sendmesg.text = listdata.content
        } else {
            holder.itemView.recievemesg.visibility = View.VISIBLE
            holder.itemView.recievemesg.text = listdata.content
        }
    }

    override fun getItemCount(): Int {
       return list.size
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {

        fun bindItems()
        {
            var recievemesg : TextView
            var sendmesg : TextView

            recievemesg = itemView.findViewById(R.id.recievemesg)
            sendmesg = itemView.findViewById(R.id.sendmesg)
        }
    }
}