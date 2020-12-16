package com.casebeaumonde.activities.myContracts.tabs.Contract.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.casebeaumonde.R
import com.casebeaumonde.activities.myContracts.tabs.Contract.response.ContractListResponse
import com.casebeaumonde.constants.Constants
import com.casebeaumonde.utilities.Utils
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.custom_workinvitation.view.*

class ContractorAdapter(
    val context: Context,
    val data: ArrayList<ContractListResponse.Data.User.ContractsAsContractor>
) :
    RecyclerView.Adapter<ContractorAdapter.ViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ContractorAdapter.ViewHolder {
        var v = LayoutInflater.from(parent.context)
            .inflate(R.layout.custom_workinvitation, parent, false)

        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ContractorAdapter.ViewHolder, position: Int) {
        val res = data
        Glide.with(context).load(
            Constants.BASE_IMAGE_URL + data.get(position).contractor.avatar
        ).placeholder(R.drawable.login_banner).into(holder.itemView.inviationimage)

        holder.itemView.invitation_title.setText(
            "Contract "+ data.get(position).status +" for gig with title: '" + data.get(
                position
            ).gig.title + "' and " + data.get(position).customer.firstname + " " + data.get(position).customer.lastname
        )

        holder.itemView.invitation_date.setText(Utils.changeDateTimeToDate(data.get(position).createdAt))
        holder.itemView.invitation_status.setText("Status:" + data.get(position).status)

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
        fun bindItems() {
            inviationimage = itemView.findViewById(R.id.inviationimage)
            invitation_title = itemView.findViewById(R.id.invitation_title)
            invitation_date = itemView.findViewById(R.id.invitation_date)
            invitation_status = itemView.findViewById(R.id.invitation_status)
            makeanofferbt = itemView.findViewById(R.id.makeanofferbt)
            deleteoffer = itemView.findViewById(R.id.deleteoffer)
        }

    }

}