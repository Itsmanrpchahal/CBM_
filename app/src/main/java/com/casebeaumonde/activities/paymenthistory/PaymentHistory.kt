package com.casebeaumonde.activities.paymenthistory

import android.app.ProgressDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.casebeaumonde.Controller.Controller
import com.casebeaumonde.R
import com.casebeaumonde.activities.myGigs.adapter.MyGigsAdapter
import com.casebeaumonde.activities.paymentScreen.adapter.PaymentCardsAdapter
import com.casebeaumonde.activities.paymentScreen.response.PaymentProfileResponse
import com.casebeaumonde.constants.BaseClass
import com.casebeaumonde.constants.Constants
import com.casebeaumonde.utilities.Utility
import kotlinx.android.synthetic.main.activity_payment_history.*
import kotlinx.android.synthetic.main.app_bar_main.*
import retrofit2.Response

class PaymentHistory : BaseClass(), Controller.PaymentProfileAPI {
    private lateinit var back: ImageButton
    private lateinit var successpayments_recycler: RecyclerView

    private lateinit var controller: Controller
    private lateinit var utility: Utility
    private lateinit var pd: ProgressDialog


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment_history)

        findIds()
        controller = Controller()
        controller.Controller(this)
        if (utility.isConnectingToInternet(this)) {
            pd.show()
            pd.setContentView(R.layout.loading)
            controller.PaymentProfile("Bearer " + getStringVal(Constants.TOKEN))
        } else {
            utility!!.relative_snackbar(
                parent_paymentprofile,
                getString(R.string.nointernet),
                getString(R.string.close_up)
            )
        }

        listeners()
    }

    private fun listeners() {
        back.setOnClickListener { onBackPressed() }
    }

    private fun findIds() {
        utility = Utility()
        pd = ProgressDialog(this)
        pd!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        pd!!.window!!.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        pd!!.isIndeterminate = true
        pd!!.setCancelable(false)
        back = findViewById(R.id.back)
        successpayments_recycler = findViewById(R.id.successpayments_recycler)

    }

    override fun onPaymentProfileSuccess(paymentProfile: Response<PaymentProfileResponse>) {
        pd.dismiss()
        if (paymentProfile.isSuccessful) {

            successpayments_recycler.layoutManager =
                LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
            val adapter = paymentProfile.body()?.data?.let {
                PaymentCardsAdapter(
                    this, it
                )
            }
            successpayments_recycler.adapter = adapter
            // payment_type.setText("Type : "+paymentProfile.body().data.paymentProfiles.)
        } else {
            utility!!.relative_snackbar(
                parent_paymentprofile,
                paymentProfile.message(),
                getString(R.string.close_up)
            )
        }
    }

    override fun error(error: String?) {
        pd.dismiss()
        utility!!.relative_snackbar(
            parent_paymentprofile,
            error,
            getString(R.string.close_up)
        )
    }
}