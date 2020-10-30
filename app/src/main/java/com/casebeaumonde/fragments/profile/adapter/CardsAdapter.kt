package com.casebeaumonde.fragments.profile.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.casebeaumonde.R
import com.casebeaumonde.fragments.profile.Profile
import com.casebeaumonde.fragments.profile.profileResponse.PaymentMethodResponse
import kotlinx.android.synthetic.main.custom_mycards.view.*

class CardsAdapter(
    val context: Context,
    val cards: MutableList<PaymentMethodResponse.Data.PaymentProfile>
) :
    RecyclerView.Adapter<CardsAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardsAdapter.ViewHolder {
        val v =
            LayoutInflater.from(parent.context).inflate(R.layout.custom_mycards, parent, false)
        return ViewHolder(v)
    }

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: CardsAdapter.ViewHolder, position: Int) {

        holder.itemView.cardnumber.setText(cards.get(position).brand+" ending in ****"+cards.get(position).lastNumbers)

        if (cards.get(position).default==1)
        {
            holder.itemView.setas_default_card.setText("Default")
            holder.itemView.setas_default_card.setTextColor(Color.parseColor("#136C16"))
        }else {
            holder.itemView.setas_default_card.setTextColor(Color.parseColor("#000000"))
        }


        holder.itemView.remove_card.setOnClickListener {
            Profile.getCardID?.getCardID(cards.get(position).id.toString())
        }
    }

    override fun getItemCount(): Int {
        return cards.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        lateinit var card_image : ImageView
        lateinit var cardnumber : TextView
        lateinit var setas_default_card : Button
        lateinit var remove_card : Button
        fun bindItems()
        {
            card_image = itemView.findViewById(R.id.card_image)
            cardnumber = itemView.findViewById(R.id.cardnumber)
            setas_default_card = itemView.findViewById(R.id.setas_default_card)
            remove_card = itemView.findViewById(R.id.remove_card)
        }
    }
}