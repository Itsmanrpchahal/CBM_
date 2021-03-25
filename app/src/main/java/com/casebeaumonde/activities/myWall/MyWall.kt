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
import com.casebeaumonde.fragments.profile.profileResponse.MyWallResponse
import com.casebeaumonde.fragments.profile.profileResponse.UserProfileResponse
import com.casebeaumonde.utilities.Utility
import kotlinx.android.synthetic.main.fragment_my_wall.*
import retrofit2.Response

class MyWall : BaseClass(),Controller.MyWallAPI  {
    private lateinit var myWall_Recycler : RecyclerView
    private lateinit var controller: Controller
    private lateinit var utility: Utility
    private lateinit var myWall_back : ImageButton
    private lateinit var pd: ProgressDialog
    private lateinit var userID : String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_wall)
        userID = intent.getStringExtra("userID").toString()
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
        controller.setMyWall(
            "Bearer " + getStringVal(Constants.TOKEN),
            userID
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

    override fun onMyWallSuccess(myWall: Response<MyWallResponse>) {
        pd.dismiss()
        if (myWall.isSuccessful)
        {
            myWall_Recycler.layoutManager = LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL,false)
            val  adapter = MyWallAdapter(this,
                (myWall.body()?.getData()?.fashionables as ArrayList<MyWallResponse.Data.Fashionable>?)!!,
                myWall.body()?.getData()?.filePath.toString(),userID
            )
            myWall_Recycler.adapter = adapter
        }else{
            utility!!.relative_snackbar(
                parent_myWall,
                myWall.message(),
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