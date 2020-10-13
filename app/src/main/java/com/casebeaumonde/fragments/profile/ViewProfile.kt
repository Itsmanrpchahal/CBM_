package com.casebeaumonde.fragments.profile

import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.casebeaumonde.Controller.Controller
import com.casebeaumonde.R
import com.casebeaumonde.activities.myGigs.MyGigs
import com.casebeaumonde.activities.myWall.MyWall
import com.casebeaumonde.activities.myclosets.MyClosets
import com.casebeaumonde.activities.notifications.Notifications
import com.casebeaumonde.constants.BaseClass
import com.casebeaumonde.constants.Constants
import com.casebeaumonde.fragments.profile.profileResponse.UserProfileResponse
import com.casebeaumonde.utilities.Utility
import com.google.android.material.tabs.TabLayout
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.activity_view_profile.*
import kotlinx.android.synthetic.main.fragment_my_wall.*
import retrofit2.Response

class ViewProfile : BaseClass(), Controller.UserProfileAPI,TabLayout.OnTabSelectedListener {

    private lateinit var controller: Controller
    private lateinit var utility: Utility
    private lateinit var pd: ProgressDialog
    private lateinit var userID: String
    private lateinit var viewprofile_back : ImageButton
    private lateinit var viewprofile__username : TextView
    private lateinit var viewprofile_usernamefull : TextView
    private lateinit var viewprofile_followerscount : TextView
    private lateinit var viewprofile__followingcount : TextView
    private lateinit var viewprofile_tabLayout : TabLayout
    private lateinit var viewprofile_profilePic : CircleImageView
    private lateinit var viewprofile__mygigs : Button
    private lateinit var role : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_profile)
        userID = intent.getStringExtra("userID").toString()
        controller = Controller()
        controller.Controller(this)
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

        viewprofile__mygigs.setOnClickListener {
            startActivity(Intent(this, MyGigs::class.java).putExtra("role", role).putExtra("userID",userID))
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
        viewprofile_followerscount=  findViewById(R.id.viewprofile_followerscount)
        viewprofile__followingcount = findViewById(R.id.viewprofile__followingcount)
        viewprofile_tabLayout = findViewById(R.id.viewprofile_tabLayout)
        viewprofile__mygigs = findViewById(R.id.viewprofile__mygigs)
    }

    override fun onPrfileSucess(userProfileResponse: Response<UserProfileResponse>) {
        pd.dismiss()
        if (userProfileResponse.isSuccessful)
        {
            viewprofile__username.setText(userProfileResponse.body()?.data?.user?.firstname+"'s Profile")
            viewprofile_usernamefull.setText(userProfileResponse.body()?.data?.user?.firstname+" "+userProfileResponse.body()?.data?.user?.lastname)
            viewprofile_followerscount.text = userProfileResponse.body()?.data?.user?.followers?.size.toString()
            viewprofile__followingcount.text = userProfileResponse.body()?.data?.user?.following?.size.toString()
            Glide.with(this).load(Constants.BASE_IMAGE_URL+userProfileResponse.body()?.data?.user?.avatar).placeholder(R.drawable.login_banner).into(viewprofile_profilePic)

            role = userProfileResponse.body()?.data?.user?.role.toString()

                viewprofile_tabLayout!!.addTab(viewprofile_tabLayout!!.newTab().setText("My Wall"))
                viewprofile_tabLayout!!.addTab(viewprofile_tabLayout!!.newTab().setText("My gigs"))
                viewprofile_tabLayout!!.addTab(viewprofile_tabLayout!!.newTab().setText("My Closets"))
                viewprofile_tabLayout!!.tabGravity = TabLayout.GRAVITY_FILL
                viewprofile_tabLayout!!.setOnTabSelectedListener(this)

        }else{
            utility!!.relative_snackbar(
                parent_viewProfile,
                userProfileResponse.message(),
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
    }

    override fun onTabSelected(p0: TabLayout.Tab?) {
        when {
            p0?.text?.equals("My Closets")!! -> {
                startActivity(Intent(this, MyClosets::class.java).putExtra("userID", userID))
            }

            p0?.text?.equals("My Wall")!! -> {
                startActivity(Intent(this, MyWall::class.java).putExtra("userID", userID))
            }

            p0?.text?.equals("My gigs")!! -> {
                startActivity(Intent(this, MyGigs::class.java).putExtra("role", role).putExtra("userID",userID))
            }

        }
    }

    override fun onTabUnselected(p0: TabLayout.Tab?) {

    }

    override fun onTabReselected(p0: TabLayout.Tab?) {
        when {
            p0?.text?.equals("My Closets")!! -> {
                startActivity(Intent(this, MyClosets::class.java).putExtra("userID", userID))
            }

            p0?.text?.equals("My Wall")!! -> {
                startActivity(Intent(this, MyWall::class.java).putExtra("userID", userID))
            }

            p0?.text?.equals("My gigs")!! -> {
                startActivity(Intent(this, MyGigs::class.java).putExtra("role", role).putExtra("userID",userID))
            }
        }
    }
}