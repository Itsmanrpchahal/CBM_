package com.casebeaumonde.activities.myWall

import android.app.ProgressDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.WindowManager
import android.widget.ImageButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.casebeaumonde.Controller.Controller
import com.casebeaumonde.R
import com.casebeaumonde.constants.BaseClass
import com.casebeaumonde.constants.Constants
import com.casebeaumonde.activities.myWall.adapter.MyWallAdapter
import com.casebeaumonde.fragments.profile.profileResponse.UserProfileResponse
import com.casebeaumonde.utilities.Utility
import kotlinx.android.synthetic.main.fragment_my_wall.*
import retrofit2.Response

class MyWall : BaseClass(),Controller.UserProfileAPI  {
    private lateinit var myWall_Recycler : RecyclerView
    private lateinit var controller: Controller
    private lateinit var utility: Utility
    private lateinit var myWall_back : ImageButton
    private lateinit var pd: ProgressDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_wall)

        controller = Controller()
        controller.Controller(this)
        findIds()
        listerners()
    }

    private fun listerners() {
        myWall_back.setOnClickListener {
            onBackPressed()
        }
    }

    override fun onResume() {
        super.onResume()
        controller.setUserProfileAPI(
            "Bearer " + getStringVal(Constants.TOKEN),
            getStringVal(Constants.USERID)
        )
    }

    private fun findIds() {
        utility = Utility()
        pd = ProgressDialog(this)
        pd!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        pd!!.window!!.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        pd!!.isIndeterminate = true
        pd!!.setCancelable(false)
        myWall_Recycler = findViewById(R.id.myWall_Recycler)
        pd.show()
        pd.setContentView(R.layout.loading)
        myWall_back = findViewById(R.id.myWall_back)
    }

    override fun onPrfileSucess(userProfileResponse: Response<UserProfileResponse>) {
        pd.dismiss()
        if (userProfileResponse.isSuccessful)
        {
            myWall_Recycler.layoutManager = LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL,false)
            val  adapter = MyWallAdapter(this,
                (userProfileResponse.body()?.data?.fashionables as ArrayList<UserProfileResponse.Data.Fashionable>?)!!,
                userProfileResponse.body()?.data?.filePath.toString()
            )
            myWall_Recycler.adapter = adapter
        }else{
            utility!!.relative_snackbar(
                parent_myWall,
                userProfileResponse.message(),
                getString(R.string.close_up)
            )
        }
    }

    override fun error(error: String?) {
        pd.dismiss()
        utility!!.relative_snackbar(
            parent_myWall,
            error,
            getString(R.string.close_up)
        )
    }

}