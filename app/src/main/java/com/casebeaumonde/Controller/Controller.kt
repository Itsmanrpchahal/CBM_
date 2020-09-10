package com.casebeaumonde.Controller

import com.casebeaumonde.Retrofit.WebAPI
import com.casebeaumonde.UpdateProfilePicResponse
import com.casebeaumonde.activities.ClosetItem.response.AddToFavClosetItemResponse
import com.casebeaumonde.activities.ClosetItem.response.ClosetsItemsResponse
import com.casebeaumonde.activities.ClosetItem.response.DeleteClosetItemResponse
import com.casebeaumonde.activities.eventDetail.response.EventDetailResponse
import com.casebeaumonde.activities.myGigs.response.MyGigsResponse
import com.casebeaumonde.activities.myclosets.response.DeleteClosetResponse
import com.casebeaumonde.activities.myclosets.response.MyClosetsResponse
import com.casebeaumonde.activities.myclosets.response.UpdateClosetsResponse
import com.casebeaumonde.fragments.designers.Response.DesignersResponse
import com.casebeaumonde.fragments.profile.profileResponse.EditProfileResponse
import com.casebeaumonde.fragments.profile.profileResponse.UserProfileResponse
import com.casebeaumonde.fragments.users.Response.UsersResponse
import com.casebeaumonde.activities.notifications.response.NotificationsResponse
import com.casebeaumonde.activities.notifications.response.RemoveNotificationResponse
import com.casebeaumonde.createClosets.CreateClosetResponse
import com.casebeaumonde.fragments.HireExpert.response.HireAnExpertResponse
import com.casebeaumonde.fragments.HireExpert.response.SendInvitationResponse
import com.casebeaumonde.fragments.Live_Events.response.LiveEventsResponse
import com.casebeaumonde.fragments.allClosets.AllClosets
import com.casebeaumonde.fragments.allClosets.response.AllClosetsResponse
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
    var myClosetsAPI: MyClosetsAPI? = null
    var createClosetAPI: CreateClosetAPI? = null
    var closetItemsAPI: ClosetItemsAPI? = null
    var addTofavClosetItemAPI: AddTofavClosetItemAPI? = null
    var updateClosetAPI: UpdateClosetAPI? = null
    var deleteClosetAPI: DeleteClosetAPI? = null
    var deleteClosetItemAPI: DeleteClosetItemAPI? = null
    var hireAnExpertAPI: HireAnExpertAPI? = null
    var sendInvitationAPI: SendInvitationAPI? = null
    var liveEventsAPI: LiveEventsAPI? = null
    var eventsDetailAPI: EventsDetailAPI? = null
    var allClosetAPI : AllClosetsAPI? = null


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

    fun Controller(userProfile: UserProfileAPI) {
        userProfileAPI = userProfile
        webAPI = WebAPI()
    }

    fun Controller(getUserGigs: GetUserGigsAPI) {
        getUserGigsAPI = getUserGigs
        webAPI = WebAPI()
    }

    fun Controller(users: UsersAPI) {
        usersAPI = users
        webAPI = WebAPI()
    }

    fun Controller(designers: DesignersAPI) {
        designersAPI = designers
        webAPI = WebAPI()
    }

    fun Controller(
        myClosets: MyClosetsAPI,
        createCloset: CreateClosetAPI,
        updateCloset: UpdateClosetAPI,
        deleteCloset: DeleteClosetAPI
    ) {
        myClosetsAPI = myClosets
        createClosetAPI = createCloset
        updateClosetAPI = updateCloset
        deleteClosetAPI = deleteCloset
        webAPI = WebAPI()
    }

    fun Controller(
        closetItems: ClosetItemsAPI,
        addTofavClosetItem: AddTofavClosetItemAPI,
        deleteClosetItem: DeleteClosetItemAPI
    ) {
        closetItemsAPI = closetItems
        addTofavClosetItemAPI = addTofavClosetItem
        deleteClosetItemAPI = deleteClosetItem
        webAPI = WebAPI()
    }

    fun Controller(
        liveEventsAP: LiveEventsAPI
    ) {
        liveEventsAPI = liveEventsAP
        webAPI = WebAPI()
    }


    fun Controller(hireAnExpert: HireAnExpertAPI, sendInvitation: SendInvitationAPI) {
        hireAnExpertAPI = hireAnExpert
        sendInvitationAPI = sendInvitation
        webAPI = WebAPI()
    }


    fun Controller(eventsDetail: EventsDetailAPI,addTofavClosetItem: AddTofavClosetItemAPI)
    {
        eventsDetailAPI = eventsDetail
        addTofavClosetItemAPI = addTofavClosetItem
        webAPI = WebAPI()
    }

    fun Controller(allClosets: AllClosetsAPI,addTofavClosetItem: AddTofavClosetItemAPI)
    {
        allClosetAPI = allClosets
        addTofavClosetItemAPI = addTofavClosetItem
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

    fun GetUsers(token: String?) {
        webAPI?.api?.users(token)?.enqueue(object : Callback<UsersResponse> {
            override fun onResponse(call: Call<UsersResponse>, response: Response<UsersResponse>) {
                val usersResponse = response
                usersAPI?.onUsersSuccess(usersResponse)
            }

            override fun onFailure(call: Call<UsersResponse>, t: Throwable) {
                usersAPI?.error(t.message)
            }

        })
    }

    fun GetDesigners(token: String?) {
        webAPI?.api?.designers(token)?.enqueue(object : Callback<DesignersResponse> {
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

    fun GetMyClosets(token: String?, userId: String?) {
        webAPI?.api?.myClosets(token, userId)?.enqueue(object : Callback<MyClosetsResponse> {
            override fun onResponse(
                call: Call<MyClosetsResponse>,
                response: Response<MyClosetsResponse>
            ) {
                val myclosetsResponse = response
                myClosetsAPI?.onMyClosetsSuccess(myclosetsResponse)
            }

            override fun onFailure(call: Call<MyClosetsResponse>, t: Throwable) {
                myClosetsAPI?.error(t.message)
            }

        })
    }


    fun CreateCloset(
        token: String?,
        title: String?,
        visibility: String?,
        part: MultipartBody.Part,
        decs: String?
    ) {
        webAPI?.api?.createCloset(token, title, visibility, part, decs)
            ?.enqueue(object : Callback<CreateClosetResponse> {
                override fun onResponse(
                    call: Call<CreateClosetResponse>,
                    response: Response<CreateClosetResponse>
                ) {
                    val createClosetResponse = response
                    createClosetAPI?.onCreateClosetSuccess(createClosetResponse)
                }

                override fun onFailure(call: Call<CreateClosetResponse>, t: Throwable) {
                    createClosetAPI?.error(t.message)
                }

            })
    }

    fun ClosetItems(token: String?, closetID: String?) {
        webAPI?.api?.closetsItems(token, closetID)
            ?.enqueue(object : Callback<ClosetsItemsResponse> {
                override fun onResponse(
                    call: Call<ClosetsItemsResponse>,
                    response: Response<ClosetsItemsResponse>
                ) {
                    val closetItems = response
                    closetItemsAPI?.onClosetItemSuccess(closetItems)
                }

                override fun onFailure(call: Call<ClosetsItemsResponse>, t: Throwable) {
                    closetItemsAPI?.error(t.message)
                }

            })
    }

    fun AddToFavClosetItem(token: String?, closetItemId: String?, type: String?) {
        webAPI?.api?.addToFavClosetItem(token, closetItemId, type)
            ?.enqueue(object : Callback<AddToFavClosetItemResponse> {
                override fun onResponse(
                    call: Call<AddToFavClosetItemResponse>,
                    response: Response<AddToFavClosetItemResponse>
                ) {
                    val addtofavclosetItem = response
                    addTofavClosetItemAPI?.onAddToFavClosetItemSuccess(addtofavclosetItem)
                }

                override fun onFailure(call: Call<AddToFavClosetItemResponse>, t: Throwable) {
                    addTofavClosetItemAPI?.error(t.message)
                }

            })
    }

    fun UpdateCloset(
        token: String?,
        id: String?,
        title: String?,
        visibility: String?,
        image: MultipartBody.Part,
        description: String?
    ) {
        webAPI?.api?.closetUpdate(token, id, title, visibility, image, description)
            ?.enqueue(object : Callback<UpdateClosetsResponse> {
                override fun onResponse(
                    call: Call<UpdateClosetsResponse>,
                    response: Response<UpdateClosetsResponse>
                ) {
                    val updateCloset = response
                    updateClosetAPI?.onUpdateClosetSuccess(updateCloset)
                }

                override fun onFailure(call: Call<UpdateClosetsResponse>, t: Throwable) {
                    updateClosetAPI?.error(t.message)
                }

            })
    }

    fun DeleteCloset(token: String?, id: String?) {
        webAPI?.api?.deleteCloset(token, id)?.enqueue(object : Callback<DeleteClosetResponse> {
            override fun onResponse(
                call: Call<DeleteClosetResponse>,
                response: Response<DeleteClosetResponse>
            ) {
                val deleteCloset = response
                deleteClosetAPI?.onDeleteClosetSuccess(deleteCloset)
            }

            override fun onFailure(call: Call<DeleteClosetResponse>, t: Throwable) {
                deleteClosetAPI?.error(t.message)
            }

        })
    }

    fun DeleteClosetItem(token: String?, id: String?) {
        webAPI?.api?.deleteClosetItem(token, id)
            ?.enqueue(object : Callback<DeleteClosetItemResponse> {
                override fun onResponse(
                    call: Call<DeleteClosetItemResponse>,
                    response: Response<DeleteClosetItemResponse>
                ) {
                    val deleteClosetItem = response
                    deleteClosetItemAPI?.onDeleteClosetItemSuccess(deleteClosetItem)
                }

                override fun onFailure(call: Call<DeleteClosetItemResponse>, t: Throwable) {
                    deleteClosetItemAPI?.error(t.message)
                }

            })
    }

    fun HireAnExpert(token: String?) {
        webAPI?.api?.hireanExpert(token)?.enqueue(object : Callback<HireAnExpertResponse> {
            override fun onResponse(
                call: Call<HireAnExpertResponse>,
                response: Response<HireAnExpertResponse>
            ) {
                val hireAnExpert = response
                hireAnExpertAPI?.onHireErxpertSuccess(hireAnExpert)
            }

            override fun onFailure(call: Call<HireAnExpertResponse>, t: Throwable) {
                hireAnExpertAPI?.error(t.message)
            }
        })
    }


    fun SendInvitation(
        token: String?,
        desigenerID: String,
        decripition: String?,
        gig_id: String?,
        budget: String?
    ) {
        webAPI?.api?.sendInviation(token, desigenerID, decripition, gig_id, budget)
            ?.enqueue(object : Callback<SendInvitationResponse> {
                override fun onResponse(
                    call: Call<SendInvitationResponse>,
                    response: Response<SendInvitationResponse>
                ) {
                    val sendInvitation = response
                    sendInvitationAPI?.onSendInvitationSuccess(sendInvitation)
                }

                override fun onFailure(call: Call<SendInvitationResponse>, t: Throwable) {
                    sendInvitationAPI?.error(t.message)
                }

            })
    }

    fun LiveEvents(token: String?) {
        webAPI?.api?.liveEvents(token)?.enqueue(object : Callback<LiveEventsResponse> {
            override fun onResponse(
                call: Call<LiveEventsResponse>,
                response: Response<LiveEventsResponse>
            ) {
                val liveEvents = response
                liveEventsAPI?.onLiveEventSuccess(liveEvents)
            }

            override fun onFailure(call: Call<LiveEventsResponse>, t: Throwable) {
                liveEventsAPI?.error(t.message)
            }

        })
    }

    fun EventDetail(token: String?,id: String?)
    {
        webAPI?.api?.eventDetail(token,id)?.enqueue(object : Callback<EventDetailResponse>
        {
            override fun onResponse(
                call: Call<EventDetailResponse>,
                response: Response<EventDetailResponse>
            ) {
                val eventDetail = response
                eventsDetailAPI?.onEventDetailSuccess(eventDetail)
            }

            override fun onFailure(call: Call<EventDetailResponse>, t: Throwable) {
               eventsDetailAPI?.error(t.message)
            }

        })
    }


    fun AllClosets(token: String?)
    {
        webAPI?.api?.allClosets(token)?.enqueue(object : Callback<AllClosetsResponse>
        {
            override fun onResponse(
                call: Call<AllClosetsResponse>,
                response: Response<AllClosetsResponse>
            ) {
                val allClosets = response
                allClosetAPI?.onAllEventsSuccess(allClosets)
            }

            override fun onFailure(call: Call<AllClosetsResponse>, t: Throwable) {
                allClosetAPI?.error(t.message)
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
        fun onDesignersSuccess(designerResponse: Response<DesignersResponse>)
        fun error(error: String?)
    }

    interface MyClosetsAPI {
        fun onMyClosetsSuccess(myClosetsResponse: Response<MyClosetsResponse>)
        fun error(error: String?)
    }

    interface CreateClosetAPI {
        fun onCreateClosetSuccess(createClosetResponse: Response<CreateClosetResponse>)
        fun error(error: String?)
    }

    interface ClosetItemsAPI {
        fun onClosetItemSuccess(closetItemsResponse: Response<ClosetsItemsResponse>)
        fun error(error: String?)
    }

    interface AddTofavClosetItemAPI {
        fun onAddToFavClosetItemSuccess(addToFavClosetItemResponse: Response<AddToFavClosetItemResponse>)
        fun error(error: String?)
    }

    interface UpdateClosetAPI {
        fun onUpdateClosetSuccess(updateClosetsResponse: Response<UpdateClosetsResponse>)
        fun error(error: String?)
    }

    interface DeleteClosetAPI {
        fun onDeleteClosetSuccess(deleteClosetResponse: Response<DeleteClosetResponse>)
        fun error(error: String?)
    }

    interface DeleteClosetItemAPI {
        fun onDeleteClosetItemSuccess(deleteClosetItemResponse: Response<DeleteClosetItemResponse>)
        fun error(error: String?)
    }

    interface HireAnExpertAPI {
        fun onHireErxpertSuccess(hireAnExpertResponse: Response<HireAnExpertResponse>)
        fun error(error: String?)
    }

    interface SendInvitationAPI {
        fun onSendInvitationSuccess(sendInvitationResponse: Response<SendInvitationResponse>)
        fun error(error: String?)
    }

    interface LiveEventsAPI {
        fun onLiveEventSuccess(liveEventsResponse: Response<LiveEventsResponse>)
        fun error(error: String?)
    }

    interface EventsDetailAPI {
        fun onEventDetailSuccess(eventDetailResponse: Response<EventDetailResponse>)
        fun error(error: String?)
    }

    interface AllClosetsAPI {
        fun onAllEventsSuccess (allClosetsResponse: Response<AllClosetsResponse>)
        fun error(error : String?)
    }
}
