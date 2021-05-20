package com.casebeaumonde.fragments.contracts.offers.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.casebeaumonde.R
import com.casebeaumonde.activities.openchat.SendChat
import com.casebeaumonde.fragments.contracts.offers.OffersFrag
import com.casebeaumonde.fragments.contracts.offers.response.OfferListResponse
import com.casebeaumonde.constants.Constants
import com.casebeaumonde.fragments.contracts.Offers_Activity
import com.casebeaumonde.fragments.contracts.detail.DetailPage
import com.casebeaumonde.utilities.Utils
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.custom_workinvitation.view.*

class SendOffersAdapter(
    val context: Context,
    val data: MutableList<OfferListResponse.Data.User.SentOffer>
) :
    RecyclerView.Adapter<SendOffersAdapter.ViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SendOffersAdapter.ViewHolder {
        var v = LayoutInflater.from(parent.context)
            .inflate(R.layout.custom_workinvitation, parent, false)

        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: SendOffersAdapter.ViewHolder, position: Int) {
        val res = data
        Glide.with(context).load(
            Constants.BASE_IMAGE_URL + data.get(
                position
            ).designer?.avatar
        ).placeholder(R.drawable.login_banner1).into(holder.itemView.inviationimage)
        holder.itemView.invitation_title.setText("Offer for gig : " + data.get(position).gig?.title)
        holder.itemView.invitation_date.setText(Utils.changeDateTimeToDateTime(data.get(position).createdAt))
        holder.itemView.invitation_status.setText(
            data.get(position).status?.substring(0, 1)
                ?.toUpperCase() + data.get(position).status?.substring(1)
        )

        holder.itemView.setOnClickListener {
            OffersFrag.getOfferID_IF?.getID(
                data.get(position).gig?.id.toString(),
                position.toString(), "send", data.get(position).id.toString()
            )
        }

        if (data.get(position).status.equals("pending")) {
            holder.itemView.deleteoffer.visibility = View.VISIBLE
        }

        holder.itemView.openchat.setOnClickListener {
            context.startActivity(
                Intent(context, SendChat::class.java).putExtra(
                    "id", data.get(
                        position
                    ).designer?.id.toString()
                ).putExtra(
                    "chatname", data.get(
                        position
                    ).designer?.firstname + " " + data.get(
                        position
                    ).designer?.lastname.toString()
                )
            )
        }

        holder.itemView.setOnClickListener {
            context.startActivity(Intent(context, DetailPage::class.java).putExtra("id",position.toString()).putExtra("from","sendoffer"))
            //  Toast.makeText(context,""+data.get(position).description,Toast.LENGTH_SHORT).show()
        }

        holder.itemView.deleteoffer.setOnClickListener {
            Offers_Activity.getOfferDeleteID_IF?.getOfferDeleteID( data.get(
                position
            ).user?.id.toString())
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        lateinit var inviationimage: CircleImageView
        lateinit var invitation_title: TextView
        lateinit var invitation_date: TextView
        lateinit var invitation_status: TextView
        lateinit var makeanofferbt: Button
        lateinit var deleteoffer: ImageButton
        lateinit var openchat: Button
        fun bindItems() {
            inviationimage = itemView.findViewById(R.id.inviationimage)
            invitation_title = itemView.findViewById(R.id.invitation_title)
            invitation_date = itemView.findViewById(R.id.invitation_date)
            invitation_status = itemView.findViewById(R.id.invitation_status)
            makeanofferbt = itemView.findViewById(R.id.makeanofferbt)
            deleteoffer = itemView.findViewById(R.id.deleteoffer)
            openchat = itemView.findViewById(R.id.openchat)
        }
    }

}