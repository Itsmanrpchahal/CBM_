package com.casebeaumonde.activities.myContracts.tabs.WorkInvitation.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.casebeaumonde.R
import com.casebeaumonde.activities.myContracts.tabs.WorkInvitation.response.WorkInvitationResponse
import com.casebeaumonde.activities.openchat.SendChat
import com.casebeaumonde.constants.Constants
import com.casebeaumonde.fragments.contracts.detail.DetailPage
import com.casebeaumonde.utilities.Utils
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.custom_workinvitation.view.*

class WorkRecieveInvitationAdapter (var context: Context, var data: MutableList<WorkInvitationResponse.Data.User.ReceivedInvitation>) :
    RecyclerView.Adapter<WorkRecieveInvitationAdapter.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): WorkRecieveInvitationAdapter.ViewHolder {
        var v = LayoutInflater.from(parent.context)
            .inflate(R.layout.custom_workinvitation, parent, false)

        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: WorkRecieveInvitationAdapter.ViewHolder, position: Int) {
        val res = data
        Glide.with(context).load(
            Constants.BASE_IMAGE_URL+data.get(
            position).user?.avatar).placeholder(R.drawable.login_banner1).into(holder.itemView.inviationimage)
        holder.itemView.invitation_title.setText(data.get(position).description)
        holder.itemView.invitation_date.setText(Utils.changeDateTimeToDateTime(data.get(position).createdAt))
        holder.itemView.invitation_status.setText(data.get(position).status?.substring(0,1)?.toUpperCase()+data.get(position).status?.substring(1))

//        if (data.get(position).status.equals("accepted"))
//        {
//            holder.itemView.makeanofferbt.visibility = View.VISIBLE
//        }else{
//            //holder.itemView.deleteoffer.visibility = View.VISIBLE
//        }
        holder.itemView.deleteoffer.visibility = View.GONE
        holder.itemView.setOnClickListener {
            context.startActivity(Intent(context,DetailPage::class.java).putExtra("id",position.toString()).putExtra("from","invitation"))
          //  Toast.makeText(context,""+data.get(position).description,Toast.LENGTH_SHORT).show()
        }

        holder.itemView.openchat.setOnClickListener {
            context.startActivity(
                Intent(context, SendChat::class.java).putExtra(
                    "id", data.get(
                        position
                    ).user?.id.toString()
                ).putExtra(
                    "chatname", data.get(
                        position
                    ).designer?.firstname + " " + data.get(
                        position
                    ).designer?.lastname.toString()
                )
            )
        }

    }

    override fun getItemCount(): Int {
        return data.size
    }

    class ViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {

        lateinit var inviationimage : ImageView
        lateinit var invitation_title : TextView
        lateinit var invitation_date : TextView
        lateinit var invitation_status : TextView
        lateinit var makeanofferbt: Button
        lateinit var deleteoffer: ImageButton
        lateinit var openchat : Button
        fun bindItems()
        {
            inviationimage = itemView.findViewById(R.id.inviationimage)
            invitation_title = itemView.findViewById(R.id.invitation_title)
            invitation_date = itemView.findViewById(R.id.invitation_date)
            invitation_status = itemView.findViewById(R.id.invitation_status)
            makeanofferbt = itemView.findViewById(R.id.makeanofferbt)
            deleteoffer = itemView.findViewById(R.id.deleteoffer)
            deleteoffer.visibility = View.GONE
            openchat = itemView.findViewById(R.id.openchat)
        }
    }
}