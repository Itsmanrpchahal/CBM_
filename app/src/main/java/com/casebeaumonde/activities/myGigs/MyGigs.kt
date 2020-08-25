package com.casebeaumonde.activities.myGigs

import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.casebeaumonde.Controller.Controller
import com.casebeaumonde.R
import com.casebeaumonde.activities.myGigs.adapter.MyGigsAdapter
import com.casebeaumonde.activities.myGigs.response.MyGigsResponse
import com.casebeaumonde.constants.BaseClass
import com.casebeaumonde.constants.Constants
import com.casebeaumonde.notifications.adpater.NotificationAdapter
import com.casebeaumonde.utilities.Utility
import kotlinx.android.synthetic.main.activity_my_gigs.*
import kotlinx.android.synthetic.main.activity_notifications.*
import retrofit2.Response

class MyGigs : BaseClass(),Controller.GetUserGigsAPI {

    public lateinit var mygigis_back : ImageView
    lateinit var mygigigs_rv : RecyclerView
    private lateinit var utility: Utility
    private lateinit var pd: ProgressDialog
    lateinit var controller: Controller
    lateinit var role:String

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
            controller.GetUserGigs("Bearer "+getStringVal(Constants.TOKEN),getStringVal(Constants.USERID))
        }else{
            utility!!.relative_snackbar(parent_gigs!!, getString(R.string.nointernet), getString(R.string.close_up))
        }
    }

    private fun listeners() {
        mygigis_back.setOnClickListener { onBackPressed() }
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
        role = intent.getStringExtra("role")
    }

    override fun onMyGigsSuccess(myGigsResponse: Response<MyGigsResponse>) {
        pd.dismiss()
        if (myGigsResponse.isSuccessful)
       {
           if (role.equals("customer"))
           {
               //add layout manager
               mygigigs_rv.layoutManager =
                   LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
               val adapter = MyGigsAdapter(this,myGigsResponse.body()?.data?.user?.gigs!!)
               mygigigs_rv.adapter = adapter
           }else{
               //add layout manager
               mygigigs_rv.layoutManager =
                   LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
               val adapter = MyGigsAdapter(this,myGigsResponse.body()?.data?.user?.gigs!!)
               mygigigs_rv.adapter = adapter
           }

       }else{
            utility!!.relative_snackbar(parent_gigs!!, myGigsResponse.message(), getString(R.string.close_up))
       }
    }

    override fun error(error: String?) {
        pd.dismiss()
        utility!!.relative_snackbar(parent_gigs!!, error, getString(R.string.close_up))
    }
}