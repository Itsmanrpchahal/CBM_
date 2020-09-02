package com.casebeaumonde.activities.myGigs

import android.app.Dialog
import android.app.ProgressDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.ImageButton
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.casebeaumonde.Controller.Controller
import com.casebeaumonde.R
import com.casebeaumonde.activities.myGigs.adapter.MyGigsAdapter
import com.casebeaumonde.activities.myGigs.response.MyGigsResponse
import com.casebeaumonde.constants.BaseClass
import com.casebeaumonde.constants.Constants
import com.casebeaumonde.utilities.Utility
import kotlinx.android.synthetic.main.activity_my_gigs.*
import retrofit2.Response

class MyGigs : BaseClass(), Controller.GetUserGigsAPI {

    public lateinit var mygigis_back: ImageView
    lateinit var mygigigs_rv: RecyclerView
    private lateinit var utility: Utility
    private lateinit var pd: ProgressDialog
    lateinit var controller: Controller
    lateinit var role: String
    lateinit var add_gig: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_gigs)
        controller = Controller()
        controller.Controller(this)
        findIds()
        MygigsCall()
        listeners()
    }

    private fun MygigsCall() {
        if (utility.isConnectingToInternet(this)) {
            pd.show()
            pd.setContentView(R.layout.loading)
            controller.GetUserGigs(
                "Bearer " + getStringVal(Constants.TOKEN),
                getStringVal(Constants.USERID)
            )
        } else {
            utility!!.relative_snackbar(
                parent_gigs!!,
                getString(R.string.nointernet),
                getString(R.string.close_up)
            )
        }
    }

    private fun listeners() {
        mygigis_back.setOnClickListener { onBackPressed() }

        add_gig.setOnClickListener {
            val dialog = Dialog(this!!)
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog.setContentView(R.layout.add_gig)
            val window = dialog.window
            window?.setLayout(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT
            )
            dialog.show()
        }
    }

    private fun findIds() {
        utility = Utility()
        pd = ProgressDialog(this)
        pd!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        pd!!.window!!.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        pd!!.isIndeterminate = true
        pd!!.setCancelable(false)
        mygigis_back = findViewById(R.id.mygigis_back)
        mygigigs_rv = findViewById(R.id.mygigigs_rv)
        add_gig = findViewById(R.id.add_gig)
        role = intent.getStringExtra("role")
    }

    override fun onMyGigsSuccess(myGigsResponse: Response<MyGigsResponse>) {
        pd.dismiss()
        if (myGigsResponse.isSuccessful) {

            //add layout manager
            mygigigs_rv.layoutManager =
                LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
            val adapter = MyGigsAdapter(this, myGigsResponse.body()?.data?.user?.gigs!!)
            mygigigs_rv.adapter = adapter

        } else {
            utility!!.relative_snackbar(
                parent_gigs!!,
                myGigsResponse.message(),
                getString(R.string.close_up)
            )
        }
    }

    override fun error(error: String?) {
        pd.dismiss()
        utility!!.relative_snackbar(parent_gigs!!, error, getString(R.string.close_up))
    }
}