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
import com.casebeaumonde.activities.myContracts.tabs.Contract.MyContractsFrag
import com.casebeaumonde.activities.myContracts.tabs.Contract.response.ContractListResponse
import com.casebeaumonde.constants.Constants
import com.casebeaumonde.utilities.Utils
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.custom_workinvitation.view.*

class ContractCustomerAdapter (val context: Context,val data : MutableList<ContractListResponse.Data.User.ContractsAsCustomer>):
    RecyclerView.Adapter<ContractCustomerAdapter.ViewHolder>() {



    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ContractCustomerAdapter.ViewHolder {
        var v = LayoutInflater.from(parent.context)
            .inflate(R.layout.custom_workinvitation, parent, false)

        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ContractCustomerAdapter.ViewHolder, position: Int) {
        Glide.with(context).load(
            Constants.BASE_IMAGE_URL + data.get(position).customer.avatar
        ).placeholder(R.drawable.login_banner).into(holder.itemView.inviationimage)
         holder.itemView.invitation_title.setText("Contract opened for gig with title:"+data.get(position).customer.firstname+" between you and "+data.get(position).contractor.firstname)
        holder.itemView.invitation_date.setText(Utils.changeDateTimeToDateTime(data.get(position).createdAt))
        holder.itemView.invitation_status.setText("Status:"+data.get(position).status.replace("_"," "))

        holder.itemView.setOnClickListener {
            MyContractsFrag.getcontractidIf?.getID(
                data.get(position).customerId.toString(),
                position.toString()
            )
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    class ViewHolder(itemView : View):RecyclerView.ViewHolder(itemView) {
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