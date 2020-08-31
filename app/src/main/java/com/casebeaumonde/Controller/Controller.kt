package com.casebeaumonde.Controller

import com.casebeaumonde.Retrofit.WebAPI
import com.casebeaumonde.UpdateProfilePicResponse
import com.casebeaumonde.activities.myGigs.response.MyGigsResponse
import com.casebeaumonde.fragments.designers.Response.DesignersResponse
import com.casebeaumonde.fragments.profile.profileResponse.EditProfileResponse
import com.casebeaumonde.fragments.profile.profileResponse.UserProfileResponse
import com.casebeaumonde.fragments.users.Response.UsersResponse
import com.casebeaumonde.notifications.response.NotificationsResponse
import com.casebeaumonde.notifications.response.RemoveNotificationResponse
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


public class Controller {

    var webAPI: WebAPI? = null
    var notificationAPI: NotificationAPI? = null
    var userProfileAPI: UserProfileAPI? = null
    var updateAvatarAPI: UpdateAvatarAPI? = null
    var updateProfileAPI: UpdateProfileAPI? = null
    var removeNotificationAPI: RemoveNotificationAPI? = null
    var getUserGigsAPI: GetUserGigsAPI? = null
    var usersAPI: UsersAPI? = null
    var designersAPI: DesignersAPI? = null


    fun Controller(notification: NotificationAPI, userProfile: UserProfileAPI) {
        notificationAPI = notification
        userProfileAPI = userProfile
        webAPI = WebAPI()
    }

    fun Controller(notification: NotificationAPI, removeNotification: RemoveNotificationAPI) {
        notificationAPI = notification
        removeNotificationAPI = removeNotification
        webAPI = WebAPI()
    }

    fun Controller(
        userProfile: UserProfileAPI,
        updateAvatar: UpdateAvatarAPI,
        updateProfile: UpdateProfileAPI
    ) {
        userProfileAPI = userProfile
        updateAvatarAPI = updateAvatar
        updateProfileAPI = updateProfile
        webAPI = WebAPI()
    }

    fun Controller(getUserGigs: GetUserGigsAPI) {
        getUserGigsAPI = getUserGigs
        webAPI = WebAPI()
    }

    fun Controller(users: UsersAPI)
    {
        usersAPI = users
        webAPI = WebAPI()
    }

    fun Controller(designers: DesignersAPI)
    {
        designersAPI = designers
        webAPI = WebAPI()
    }


    fun setNotificationAPI(token: String?, userId: String?) {
        webAPI?.api?.notificationCall(token, userId)
            ?.enqueue(object : Callback<NotificationsResponse> {

                override fun onFailure(call: Call<NotificationsResponse>, t: Throwable) {
                    notificationAPI?.error(t.message)
                }

                override fun onResponse(
                    call: Call<NotificationsResponse>,
                    response: Response<NotificationsResponse>
                ) {
                    val notificationsResponseResponse = response
                    notificationAPI?.onSucess(notificationsResponseResponse)
                }

            })
    }

    fun setUserProfileAPI(token: String?, userId: String?) {
        webAPI?.api?.userProfileResponse(token, userId)
            ?.enqueue(object : Callback<UserProfileResponse> {
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

    fun setUpdateAvatar(part: MultipartBody.Part, token: String?, userId: String?) {
        webAPI?.api?.updateAvatar(token, userId, part)
            ?.enqueue(object : Callback<UpdateProfilePicResponse> {
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

    fun setUpDateProfile(
        token: String,
        firstname: String,
        lastname: String,
        email: String,
        phone: String,
        about: String,
        userID: String
    ) {
        webAPI?.api?.editProfile(token, firstname, lastname, email, phone, about, userID)
            ?.enqueue(object : Callback<EditProfileResponse> {
                override fun onResponse(
                    call: Call<EditProfileResponse>,
                    response: Response<EditProfileResponse>
                ) {
                    val updateProfile = response
                    updateProfileAPI?.onUpdateProfileSuccess(updateProfile)
                }

                override fun onFailure(call: Call<EditProfileResponse>, t: Throwable) {
                    updateProfileAPI?.error(t.message)
                }
            })
    }

    fun RemoveNotification(token: String?, notID: String?) {
        webAPI?.api?.removeNotification(token, notID)
            ?.enqueue(object : Callback<RemoveNotificationResponse> {
                override fun onResponse(
                    call: Call<RemoveNotificationResponse>,
                    response: Response<RemoveNotificationResponse>
                ) {
                    val removeNotificationResponse = response
                    removeNotificationAPI?.onRemoveNotification(removeNotificationResponse)
                }

                override fun onFailure(call: Call<RemoveNotificationResponse>, t: Throwable) {
                    removeNotificationAPI?.error(t.message)
                }

            })
    }

    fun GetUserGigs(token: String?, userId: String?) {
        webAPI?.api?.usergigs(token, userId)?.enqueue(object : Callback<MyGigsResponse> {
            override fun onResponse(
                call: Call<MyGigsResponse>,
                response: Response<MyGigsResponse>
            ) {
                val usergigsResponse = response
                getUserGigsAPI?.onMyGigsSuccess(usergigsResponse)
            }

            override fun onFailure(call: Call<MyGigsResponse>, t: Throwable) {
                getUserGigsAPI?.error(t.message)
            }

        })
    }

    fun GetUsers(token: String?)
    {
        webAPI?.api?.users(token)?.enqueue(object : Callback<UsersResponse>{
            override fun onResponse(call: Call<UsersResponse>, response: Response<UsersResponse>) {
                val usersResponse = response
                usersAPI?.onUsersSuccess(usersResponse)
            }

            override fun onFailure(call: Call<UsersResponse>, t: Throwable) {
                usersAPI?.error(t.message)
            }

        })
    }

    fun GetDesigners(token: String?)
    {
        webAPI?.api?.designers(token)?.enqueue(object : Callback<DesignersResponse>{
            override fun onResponse(
                call: Call<DesignersResponse>,
                response: Response<DesignersResponse>
            ) {
                val designersResponse = response
                designersAPI?.onDesignersSuccess(designersResponse)
            }

            override fun onFailure(call: Call<DesignersResponse>, t: Throwable) {
                designersAPI?.error(t.message)
            }

        })

    }


    interface NotificationAPI {
        fun onSucess(notificationsResponseResponse: Response<NotificationsResponse>)
        fun error(error: String?)
    }


    interface UserProfileAPI {
        fun onPrfileSucess(userProfileResponse: Response<UserProfileResponse>)
        fun error(error: String?)
    }

    interface UpdateAvatarAPI {
        fun onUpdateAvatarResponse(updateAvatarResponse: Response<UpdateProfilePicResponse>)
        fun error(error: String?)
    }

    interface UpdateProfileAPI {
        fun onUpdateProfileSuccess(updateProfileResponse: Response<EditProfileResponse>)
        fun error(error: String?)
    }

    interface RemoveNotificationAPI {
        fun onRemoveNotification(removeNotification: Response<RemoveNotificationResponse>)
        fun error(error: String?)
    }

    interface GetUserGigsAPI {
        fun onMyGigsSuccess(myGigsResponse: Response<MyGigsResponse>)
        fun error(error: String?)
    }

    interface UsersAPI {
        fun onUsersSuccess(usersResponse: Response<UsersResponse>)
        fun error(error: String?)
    }

    interface DesignersAPI {
        fun onDesignersSuccess(designerResponse:Response<DesignersResponse>)
        fun error(error: String?)
    }
}
