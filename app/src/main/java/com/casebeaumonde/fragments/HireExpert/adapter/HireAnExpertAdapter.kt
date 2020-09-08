package com.casebeaumonde.fragments.HireExpert.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.casebeaumonde.R
import com.casebeaumonde.constants.Constants
import com.casebeaumonde.fragments.HireExpert.HireAnExpertFragment
import com.casebeaumonde.fragments.HireExpert.response.HireAnExpertResponse
import com.casebeaumonde.fragments.designers.adapter.DesignerAdapter
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.custom_hireanexpert.view.*

class HireAnExpertAdapter(val context: Context,var list: MutableList<HireAnExpertResponse.Data.Gig>) :
    RecyclerView.Adapter<HireAnExpertAdapter.ViewHolder>()
{

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HireAnExpertAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.custom_hireanexpert,parent,false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: HireAnExpertAdapter.ViewHolder, position: Int) {
        val listData = list.get(position)

        Glide.with(context).load(Constants.BASE_IMAGE_URL+listData.user.avatar).placeholder(R.drawable.login_banner).into(holder.itemView.hireanexpert_image)
        holder.itemView.hireanexpert_name.text = listData.title
        holder.itemView.hireanexpert_description.text = listData.description
        holder.itemView.hireanexpert_hours.text = "Hours :"+listData.hours
        holder.itemView.hireanexpert_rate.text = "Rate :"+listData.rate
        holder.itemView.hireanexpert_ratetype.text = "Rate type :"+listData.rateType
        holder.itemView.hireanexpert_sendInvitation.setOnClickListener {
            HireAnExpertFragment.hireExpertIF?.getID(position)
        }
    }

    override fun getItemCount(): Int {
      return list.size
    }


    class ViewHolder(itemView : View): RecyclerView.ViewHolder(itemView) {

        fun BindItems() {
            val hireanexpert_image : CircleImageView
            val hireanexpert_name : TextView
            val hireanexpert_description : TextView
            val hireanexpert_hours : TextView
            val hireanexpert_rate : TextView
            val hireanexpert_ratetype : TextView
            val hireanexpert_sendInvitation : Button
        }
    }

}