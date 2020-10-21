package com.casebeaumonde.activities.paymentScreen.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.casebeaumonde.R
import com.casebeaumonde.activities.myGigs.adapter.MyGigsAdapter
import com.casebeaumonde.activities.paymentScreen.response.PaymentProfileResponse
import com.casebeaumonde.utilities.Utils
import kotlinx.android.synthetic.main.custom_payment.view.*

class PaymentCardsAdapter(
    var context: Context,
    val cards: MutableList<PaymentProfileResponse.Data.PaymentProfile>
) :
    RecyclerView.Adapter<PaymentCardsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PaymentCardsAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.custom_payment, parent, false)

        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: PaymentCardsAdapter.ViewHolder, position: Int) {
        val cardProfile = cards.get(position)
        holder.itemView.payment_type.setText("Type : "+cardProfile.brand)
        holder.itemView.payment_Details.setText("Details : Visa ending in ****"+cardProfile.lastNumbers)
        holder.itemView.payment_amountpaid.setText("Amount paid : $")
        holder.itemView.payment_patmentdate.setText("Payment date : "+Utils.changeDateTimeToDate(cardProfile.createdAt))
    }

    override fun getItemCount(): Int {
        return cards.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private lateinit var payment_type: TextView
        private lateinit var payment_Details: TextView
        private lateinit var payment_amountpaid: TextView
        private lateinit var payment_paidfor: TextView
        private lateinit var payment_patmentdate: TextView
        private lateinit var payment_transaction: TextView
        private lateinit var payment_status: TextView
        fun bindItems() {
            payment_type = itemView.findViewById(R.id.payment_type)
            payment_Details = itemView.findViewById(R.id.payment_Details)
            payment_amountpaid = itemView.findViewById(R.id.payment_amountpaid)
            payment_paidfor = itemView.findViewById(R.id.payment_paidfor)
            payment_patmentdate = itemView.findViewById(R.id.payment_patmentdate)
            payment_transaction = itemView.findViewById(R.id.payment_transaction)
            payment_status = itemView.findViewById(R.id.payment_status)
        }
    }

}