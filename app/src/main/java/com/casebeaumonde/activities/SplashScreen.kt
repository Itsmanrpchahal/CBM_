package com.casebeaumonde.activities

import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.casebeaumonde.MainActivity
import com.casebeaumonde.R
import com.casebeaumonde.activities.login.LoginActivity
import com.casebeaumonde.constants.BaseClass
import com.casebeaumonde.constants.Constants
import com.livinglifetechway.quickpermissions_kotlin.runWithPermissions
import com.livinglifetechway.quickpermissions_kotlin.util.QuickPermissionsOptions
import com.livinglifetechway.quickpermissions_kotlin.util.QuickPermissionsRequest
import pl.droidsonroids.gif.GifDrawable
import pl.droidsonroids.gif.GifImageView


class SplashScreen : BaseClass() {
    private lateinit var gif : GifImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        gif = findViewById(R.id.gif)
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window: Window = window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.setStatusBarColor(Color.BLACK)
        }
        methodRequiresPermission()
    }

    private fun methodRequiresPermission() = runWithPermissions(
        android.Manifest.permission.READ_EXTERNAL_STORAGE,
        android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
        android.Manifest.permission.CAMERA,
        android.Manifest.permission.ACCESS_FINE_LOCATION
    ){
        Glide.with(this).load(R.drawable.splashgif).into(gif)
        Handler().postDelayed({

            val userID = getStringVal(Constants.TOKEN)
//            if (!userID.equals(""))
//            {
//                startActivity(Intent(this,
//                    MainActivity::class.java))
//                finish()
//            }else{
//                startActivity(Intent(this,
//                    OnBoardScreen::class.java))
//                finish()
//            }

            startActivity(Intent(this,
                MainActivity::class.java))
            finish()

        },4000)
    }

    private val quickPermissionsOptions = QuickPermissionsOptions(
        rationaleMessage = "Custom  rational messsage",
        permanentlyDeniedMessage = "Custom permanently denied message",
        rationaleMethod = { rationalCallback(it)}
    )

    private fun rationalCallback(request: QuickPermissionsRequest)
    {
        AlertDialog.Builder(this)
            .setTitle("Permission Denied")
            .setMessage("This is the custom rational dialog. Please allow us to procees "+ "asking for permissions again, or cancel to end the permission flow.")
            .setPositiveButton("Go Ahead"){_,_ -> request.proceed()}
            .setNegativeButton("Cancel"){_,_ -> request.cancel()}
            .setCancelable(false)
            .show()
    }
}