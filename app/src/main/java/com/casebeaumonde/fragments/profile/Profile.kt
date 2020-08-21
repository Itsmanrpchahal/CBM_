package com.casebeaumonde.fragments.profile

import android.app.Activity
import android.app.Dialog
import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.provider.MediaStore
import android.view.*
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.casebeaumonde.Controller.Controller
import com.casebeaumonde.R
import com.casebeaumonde.UpdateProfilePicResponse
import com.casebeaumonde.constants.BaseFrag
import com.casebeaumonde.constants.Constants
import com.casebeaumonde.fragments.profile.profileResponse.UserProfileResponse
import com.casebeaumonde.utilities.Utility
import com.github.dhaval2404.imagepicker.ImagePicker
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.fragment_profile.*
import okhttp3.MultipartBody
import retrofit2.Response
import java.io.File

class Profile : BaseFrag(), Controller.UserProfileAPI, Controller.UpdateAvatarAPI {

    private lateinit var controller: Controller
    private lateinit var utility: Utility
    private lateinit var pd: ProgressDialog
    private lateinit var profile_username: TextView
    private lateinit var profile_profilePic: ImageView
    private lateinit var profile_followerscount: TextView
    private lateinit var profile_followingcount: TextView
    private lateinit var profile_changetv: TextView
    private lateinit var part: MultipartBody.Part
    private lateinit var bitMap: Bitmap
    private var path: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_profile, container, false)
        controller = Controller()
        controller.Controller(this, this)
        findIds(view)
        listeners()

        return view
    }

    private fun listeners() {
        profile_changetv.setOnClickListener {
            pictureSelectionDialog()
        }
    }

    private fun findIds(view: View) {
        utility = Utility()
        pd = ProgressDialog(context)
        pd!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        pd!!.window!!.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        pd!!.isIndeterminate = true
        pd!!.setCancelable(false)
        profile_username = view.findViewById(R.id.profile_username)
        profile_profilePic = view.findViewById(R.id.profile_profilePic)
        profile_followerscount = view.findViewById(R.id.profile_followerscount)
        profile_followingcount = view.findViewById(R.id.profile_followingcount)
        profile_changetv = view.findViewById(R.id.profile_changetv)

        pd.show()
        pd.setContentView(R.layout.loading)
    }

    override fun onResume() {
        super.onResume()
        controller.setUserProfileAPI(
            "Bearer " + getStringVal(Constants.TOKEN),
            getStringVal(Constants.USERID)
        )
    }

    private fun pictureSelectionDialog() {

        val camera: LinearLayout
        val gallery: LinearLayout
        val dialog = Dialog(context!!)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.imagepicker)
        camera = dialog.findViewById(R.id.linear_camera) as LinearLayout
        gallery = dialog.findViewById(R.id.linear_gallery) as LinearLayout

        camera.setOnClickListener {
            ImagePicker.with(this)
                .cameraOnly()
                .crop()         //User can only capture image using Camera
                .start()
            dialog.dismiss()
        }

        gallery.setOnClickListener {
            ImagePicker.with(this)
                .galleryOnly()
                .crop()     //User can only select image from Gallery
                .start()    //Default Request Code is ImagePicker.REQUEST_CODE
            dialog.dismiss()
        }
        dialog.show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            //Image Uri will not be null for RESULT_OK
            val fileUri = data?.data

            //You can get File object from intent
            var file: File? = ImagePicker.getFile(data)
            //You can also get File Path from intent
            val filePath: String? = ImagePicker.getFilePath(data)

            path = filePath!!
            bitMap = MediaStore.Images.Media.getBitmap(context?.contentResolver, fileUri)
            part = Utility.sendImageFileToserver(context?.filesDir, bitMap)

            updateAvatar(part, "Bearer " + getStringVal(Constants.TOKEN),getStringVal(Constants.USERID))
        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            utility!!.relative_snackbar(
                parent_main!!,
                ImagePicker.getError(data),
                getString(R.string.close_up)
            )
        } else {
            utility!!.relative_snackbar(
                parent_main,
                "Task Cancelled",
                getString(R.string.close_up)
            )
        }
    }

    private fun updateAvatar(part: MultipartBody.Part, token: String, userId :String?) {
        if (utility.isConnectingToInternet(context)) {
            pd.show()
            pd.setContentView(R.layout.loading)
            controller.setUpdateAvatar(part,token,userId)

        }else{
            utility!!.relative_snackbar(
                parent_profile,
                R.string.nointernet.toString(),
                getString(R.string.close_up)
            )
        }
    }

    override fun onPrfileSucess(userProfileResponse: Response<UserProfileResponse>) {
        pd.dismiss()
        profile_username.text =
            userProfileResponse.body()?.data?.user?.firstname + " " + userProfileResponse.body()?.data?.user?.lastname
        Glide.with(context!!)
            .load(userProfileResponse.body()?.data?.filePath + userProfileResponse.body()?.data?.user?.avatar)
            .placeholder(R.drawable.login_banner).into(profile_profilePic)
        profile_followerscount.text =
            userProfileResponse.body()?.data?.user?.followers?.size.toString()
        profile_followingcount.text =
            userProfileResponse.body()?.data?.user?.following?.size.toString()
    }

    override fun onUpdateAvatarResponse(updateAvatarResponse: Response<UpdateProfilePicResponse>) {
        pd.dismiss()
        if (updateAvatarResponse.isSuccessful)
        {
            if (updateAvatarResponse.body()?.code==200)
            {
                profile_profilePic.setImageBitmap(bitMap)
            }else{
                utility!!.relative_snackbar(
                    parent_profile,
                    updateAvatarResponse.message(),
                    getString(R.string.close_up)
                )
            }

        }else{
            utility!!.relative_snackbar(
                parent_profile,
                updateAvatarResponse.message(),
                getString(R.string.close_up)
            )
        }
    }

    override fun error(error: String?) {
        utility!!.relative_snackbar(
            parent_profile,
            error,
            getString(R.string.close_up)
        )
    }
}