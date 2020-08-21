package com.casebeaumonde.Controller

import com.casebeaumonde.Retrofit.WebAPI
import com.casebeaumonde.UpdateProfilePicResponse
import com.casebeaumonde.fragments.profile.profileResponse.UserProfileResponse
import com.casebeaumonde.notifications.response.NotificationsResponse
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


public class Controller {

    var webAPI: WebAPI? = null
    var notificationAPI: NotificationAPI? = null
    var userProfileAPI : UserProfileAPI? = null
    var updateAvatarAPI : UpdateAvatarAPI? = null


    fun Controller(notification: NotificationAPI,userProfile: UserProfileAPI) {
        notificationAPI = notification
        userProfileAPI = userProfile
        webAPI = WebAPI()
    }
    fun Controller(notification: NotificationAPI)
    {
        notificationAPI = notification
        webAPI = WebAPI()
    }

    fun Controller(userProfile: UserProfileAPI,updateAvatar: UpdateAvatarAPI){
        userProfileAPI = userProfile
        updateAvatarAPI = updateAvatar
        webAPI = WebAPI()
    }


    fun setNotificationAPI(token: String?, userId: String?) {
        webAPI?.api?.notificationCall(token, userId)
            ?.enqueue(object : Callback<NotificationsResponse> {

                override fun onResponse(
                    call: Call<NotificationsResponse>,
                    response: Response<NotificationsResponse>
                ) {
                    val notificationsResponseResponse = response
                    notificationAPI?.onSucess(notificationsResponseResponse)
                }

                override fun onFailure(call: Call<NotificationsResponse>, t: Throwable) {
                    notificationAPI?.error(t.message)
                }

            })
    }

    fun setUserProfileAPI(token: String?,userId: String?)
    {
        webAPI?.api?.userProfileResponse(token,userId)
            ?.enqueue(object : Callback<UserProfileResponse>
            {
                override fun onResponse(
                    call: Call<UserProfileResponse>,
                    response: Response<UserProfileResponse>
                ) {
                    val userProfileResponse = response
                    userProfileAPI?.onPrfileSucess(userProfileResponse)
                }

                override fun onFailure(call: Call<UserProfileResponse>, t: Throwable) {
                   userProfileAPI?.error(t.message)
                }

            })
    }

    fun setUpdateAvatar(part: MultipartBody.Part,token: String?,userId: String?)
    {
        webAPI?.api?.updateAvatar(token,userId,part)?.enqueue(object :Callback<UpdateProfilePicResponse>
        {
            override fun onResponse(
                call: Call<UpdateProfilePicResponse>,
                response: Response<UpdateProfilePicResponse>
            ) {
                val updateProfileAvatar = response
                updateAvatarAPI?.onUpdateAvatarResponse(updateProfileAvatar)
            }

            override fun onFailure(call: Call<UpdateProfilePicResponse>, t: Throwable) {
               updateAvatarAPI?.error(t.message)
            }

        })
    }


    interface NotificationAPI {
        fun onSucess(notificationsResponseResponse: Response<NotificationsResponse>)
        fun error(error: String?)
    }


    interface UserProfileAPI{
        fun onPrfileSucess(userProfileResponse: Response<UserProfileResponse>)
        fun error(error: String?)
    }

    interface UpdateAvatarAPI{
        fun onUpdateAvatarResponse(updateAvatarResponse: Response<UpdateProfilePicResponse>)
        fun error(error: String?)
    }
}
