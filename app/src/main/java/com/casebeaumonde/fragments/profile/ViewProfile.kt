package com.casebeaumonde.fragments.profile

import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.widget.*
import com.bumptech.glide.Glide
import com.casebeaumonde.Controller.Controller
import com.casebeaumonde.R
import com.casebeaumonde.activities.myGigs.MyGigs
import com.casebeaumonde.activities.myWall.MyWall
import com.casebeaumonde.activities.myclosets.MyClosets
import com.casebeaumonde.activities.openchat.SendChat
import com.casebeaumonde.constants.BaseClass
import com.casebeaumonde.constants.Constants
import com.casebeaumonde.fragments.profile.profileResponse.FollowUnFollowResponse
import com.casebeaumonde.fragments.profile.profileResponse.UserProfileResponse
import com.casebeaumonde.utilities.Utility
import kotlinx.android.synthetic.main.activity_view_profile.*
import retrofit2.Response

class ViewProfile : BaseClass(), Controller.UserProfileAPI, Controller.FollowUnFollowAPI {

    private lateinit var controller: Controller
    private lateinit var utility: Utility
    private lateinit var pd: ProgressDialog
    private lateinit var userID: String
    private lateinit var viewprofile_back: ImageButton
    private lateinit var viewprofile__username: TextView
    private lateinit var viewprofile_usernamefull: TextView
    private lateinit var viewprofile_followerscount: TextView
    private lateinit var viewprofile__followingcount: TextView
    private lateinit var viewprofile_profilePic: ImageView
    private lateinit var viewprofile__mygigs: Button
    private lateinit var viewprofile_followbt: Button
    private lateinit var viewprofile__mywall: Button
    private lateinit var viewprofile__mycloset: Button
    private lateinit var viewprofile__startchat : Button
    private lateinit var role: String
    private lateinit var id :String
    private lateinit var name : String
    private lateinit var user_decs : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_profile)
        userID = intent.getStringExtra("userID").toString()
        controller = Controller()
        controller.Controller(this, this)
        findIds()
        controller.setUserProfileAPI(
            "Bearer " + getStringVal(Constants.TOKEN),
            userID
        )

        listeners()
    }

    private fun listeners() {
        viewprofile_back.setOnClickListener {
            onBackPressed()
        }

        viewprofile_followbt.setOnClickListener {
            pd.show()
            pd.setContentView(R.layout.loading)
            controller.FollowUnFollow("Bearer " + getStringVal(Constants.TOKEN), userID)
        }

        viewprofile__mygigs.setOnClickListener {
            startActivity(
                Intent(this, MyGigs::class.java).putExtra("role", role).putExtra("userID", userID)
            )
        }

        viewprofile__mywall.setOnClickListener {
            startActivity(Intent(this, MyWall::class.java).putExtra("userID", userID))
        }

        viewprofile__mycloset.setOnClickListener {
            startActivity(Intent(this, MyClosets::class.java).putExtra("userID", userID))
        }

        viewprofile__startchat.setOnClickListener {
            startActivity(Intent(this, SendChat::class.java).putExtra("id",id).putExtra("chatname",name))
        }
    }

    private fun findIds() {
        utility = Utility()
        pd = ProgressDialog(this)
        pd!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        pd!!.window!!.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        pd!!.isIndeterminate = true
        pd!!.setCancelable(false)
        pd.show()
        pd.setContentView(R.layout.loading)
        viewprofile_back = findViewById(R.id.viewprofile_back)
        viewprofile__username = findViewById(R.id.viewprofile__username)
        viewprofile_profilePic = findViewById(R.id.viewprofile_profilePic)
        viewprofile_usernamefull = findViewById(R.id.viewprofile_usernamefull)
        viewprofile_followerscount = findViewById(R.id.viewprofile_followerscount)
        viewprofile__followingcount = findViewById(R.id.viewprofile__followingcount)
        viewprofile__mygigs = findViewById(R.id.viewprofile__mygigs)
        viewprofile_followbt = findViewById(R.id.viewprofile_followbt)
        viewprofile__mywall = findViewById(R.id.viewprofile__mywall)
        viewprofile__mycloset = findViewById(R.id.viewprofile__mycloset)
        viewprofile__startchat = findViewById(R.id.viewprofile__startchat)
        user_decs = findViewById(R.id.user_decs)
    }

    override fun onPrfileSucess(userProfileResponse: Response<UserProfileResponse>) {
        pd.dismiss()
        if (userProfileResponse.isSuccessful) {
            if (userProfileResponse.body()?.getData()?.currentFollowing == true) {
                viewprofile_followbt.setText("UnFollow")
            } else {
                viewprofile_followbt.setText("Follow")
            }

            id = userProfileResponse.body()?.getData()?.user?.id.toString()
            name = userProfileResponse.body()?.getData()?.user?.firstname.toString()+" "+userProfileResponse.body()?.getData()?.user?.lastname.toString()
            viewprofile__username.setText(
                userProfileResponse.body()?.getData()?.user?.firstname + "'s Profile"
            )
            viewprofile_usernamefull.setText(
                userProfileResponse.body()
                    ?.getData()?.user?.firstname + " " + userProfileResponse.body()
                    ?.getData()?.user?.lastname
            )
            user_decs.text = userProfileResponse?.body()?.getData()?.user?.profile?.aboutMe.toString()
            viewprofile_followerscount.text =
                userProfileResponse.body()?.getData()?.user?.followers?.size.toString()
            viewprofile__followingcount.text =
                userProfileResponse.body()?.getData()?.user?.following?.size.toString()
            Glide.with(this).load(
                Constants.BASE_IMAGE_URL + userProfileResponse.body()?.getData()?.user?.avatar
            ).placeholder(R.drawable.login_banner1).into(viewprofile_profilePic)

            role = userProfileResponse.body()?.getData()?.user?.role.toString()


        } else {
            utility!!.relative_snackbar(
                parent_viewProfile,
                userProfileResponse.message(),
                getString(R.string.close_up)
            )
        }

    }

    override fun onFollowUnfollowSuccess(followUnfollow: Response<FollowUnFollowResponse>) {
        pd.dismiss()
        if (followUnfollow.isSuccessful) {
            if (followUnfollow.body()?.getMessage().equals("Now you are following Roberto")) {
                viewprofile_followbt.setText("Unfollow")
            } else {
                viewprofile_followbt.setText("Follow")
            }
            controller.setUserProfileAPI(
                "Bearer " + getStringVal(Constants.TOKEN),
                userID
            )


            utility!!.relative_snackbar(
                parent_viewProfile,
                followUnfollow.body()?.getMessage(),
                getString(R.string.close_up)
            )
        } else {
            utility!!.relative_snackbar(
                parent_viewProfile,
                followUnfollow.body()?.getMessage(),
                getString(R.string.close_up)
            )
        }
    }

    override fun error(error: String?) {
        pd.dismiss()
        utility!!.relative_snackbar(
            parent_viewProfile,
            error,
            getString(R.string.close_up)
        )
        Log.d("message", "" + error)
    }
}