package com.casebeaumonde.Controller

import com.casebeaumonde.Retrofit.WebAPI
import com.casebeaumonde.UpdateProfilePicResponse
import com.casebeaumonde.activities.ClosetItem.response.*
import com.casebeaumonde.activities.EventsInvitations.response.UserInvitationsResponse
import com.casebeaumonde.activities.eventDetail.response.AddItemToAnotherCloset
import com.casebeaumonde.activities.eventDetail.response.EventDetailResponse
import com.casebeaumonde.activities.myGigs.response.MyGigsResponse
import com.casebeaumonde.fragments.designers.Response.DesignersResponse
import com.casebeaumonde.fragments.users.Response.UsersResponse
import com.casebeaumonde.activities.notifications.response.NotificationsResponse
import com.casebeaumonde.activities.notifications.response.RemoveNotificationResponse
import com.casebeaumonde.fragments.allClosets.response.CreateClosetResponse
import com.casebeaumonde.fragments.HireExpert.response.HireAnExpertResponse
import com.casebeaumonde.fragments.HireExpert.response.SendInvitationResponse
import com.casebeaumonde.fragments.Live_Events.response.FavLiveEventResponse
import com.casebeaumonde.fragments.Live_Events.response.LiveEventsResponse
import com.casebeaumonde.activities.addItemtoCLoset.response.AddClosetItemResponse
import com.casebeaumonde.activities.login.loginResponse.ForgotPassworResponse
import com.casebeaumonde.activities.myContracts.tabs.Contract.response.ContractListResponse
import com.casebeaumonde.activities.myContracts.tabs.Contract.response.SendClaimResponse
import com.casebeaumonde.activities.myContracts.tabs.WorkInvitation.response.MakeOfferResponse
import com.casebeaumonde.activities.myContracts.tabs.offers.response.OfferListResponse
import com.casebeaumonde.activities.myContracts.tabs.WorkInvitation.response.WorkInvitationResponse
import com.casebeaumonde.activities.myContracts.tabs.offers.response.SetOfferDecisionResponse
import com.casebeaumonde.activities.myclosets.response.*
import com.casebeaumonde.activities.openchat.response.BlockResponse
import com.casebeaumonde.activities.openchat.response.GetChatResponse
import com.casebeaumonde.activities.openchat.response.SendChatResponse
import com.casebeaumonde.activities.paymentScreen.response.PaymentProfileResponse
import com.casebeaumonde.activities.paymentScreen.response.SubscribePlanResponse
import com.casebeaumonde.fragments.allClosets.response.AllClosetsResponse
import com.casebeaumonde.fragments.cart.reponse.CartItemsResponse
import com.casebeaumonde.fragments.chat.GetChatUsers
import com.casebeaumonde.fragments.pricing.response.ChangePlanResponse
import com.casebeaumonde.fragments.pricing.response.PricingResponse
import com.casebeaumonde.fragments.profile.profileResponse.*
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.collections.HashMap


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
    var allClosetAPI: AllClosetsAPI? = null
    var addItemToAnotherClosetAPI: AdDItemToAnotherClosetAPI? = null
    var favLiveEventAPI: FavLiveEventAPI? = null
    var addClosetItemListAPI: AddClosetItemListAPI? = null
    var addClosetItemsAPI: AddClosetItemAPI? = null
    var editClosetItemAPI: EditClosetItemAPI? = null
    var cartItemAPI: CartItemAPI? = null
    var myWallAPI: MyWallAPI? = null
    var pricingAPI: PricingAPI? = null
    var moveItemAPI: MoveItemAPI? = null
    var followUnFollowAPI: FollowUnFollowAPI? = null
    var duplicateItemAPI: DuplicateItemAPI? = null
    var fetchListAPI: FetchListAPI? = null
    var outFItAPI: OutFItAPI? = null
    var paymentProfileAPI: PaymentProfileAPI? = null
    var workInvitationAPI: WorkInvitationAPI? = null
    var makeOfferAPI: MakeOfferAPI? = null
    var offerListAPI: OfferListAPI? = null
    var contractListAPI: ContractListAPI? = null
    var outfitFilterAPI: OutfitFilterAPI? = null
    var sendClaimAPI: SendClaimAPI? = null
    var setOfferDesicionAPI: SetOfferDecisionAPI? = null
    var paymentMethodAPI: PaymentMethodAPI? = null
    var deleteCardAPI: DeleteCardAPI? = null
    var fOrgotPasswordAPI: FOrgotPasswordAPI? = null
    var userInviatationsAPI: UserInviatationsAPI? = null
    var changePlanAPI: ChangePlanAPI? = null
    var subscribePlanAPI: SubscribePlanAPI? = null
    var cancelPlanAPI : CancelPlanAPI? = null
    var addPaymentMethodAPI : AddPaymentMethodAPI? = null
    var filterClosetItemsAPI : FilterClosetItemsAPI? = null
    var getChatUsersAPI : GetChatUsersAPI? = null
    var getCHatAPI : GetChatAPI? = null
    var senduserchatAPI : SendUserChatAPI? = null
    var blockUserAPI : BlockUserAPI? = null


    fun Controller(fOrgotPassword: FOrgotPasswordAPI) {
        fOrgotPasswordAPI = fOrgotPassword
        webAPI = WebAPI()
    }

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
        updateProfile: UpdateProfileAPI,
        paymentMethod: PaymentMethodAPI,
        deleteCard: DeleteCardAPI,
        cancelPlan : CancelPlanAPI,
        addMethod : AddPaymentMethodAPI
    ) {
        userProfileAPI = userProfile
        updateAvatarAPI = updateAvatar
        updateProfileAPI = updateProfile
        paymentMethodAPI = paymentMethod
        deleteCardAPI = deleteCard
        cancelPlanAPI = cancelPlan
        addPaymentMethodAPI = addMethod
        webAPI = WebAPI()
    }

    fun Controller(myWall: MyWallAPI) {
        myWallAPI = myWall
        webAPI = WebAPI()
    }

    fun Controller(userProfile: UserProfileAPI, followUnFollow: FollowUnFollowAPI) {
        userProfileAPI = userProfile
        followUnFollowAPI = followUnFollow
        webAPI = WebAPI()
    }

    fun Controller(getUserGigs: GetUserGigsAPI, sendInvitation: SendInvitationAPI) {
        getUserGigsAPI = getUserGigs
        sendInvitationAPI = sendInvitation
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
        deleteClosetItem: DeleteClosetItemAPI,
        addItemToAnotherCloset: AdDItemToAnotherClosetAPI,
        moveClosetItem: MoveItemAPI,
        duplicateItem: DuplicateItemAPI,
        fetchList: FetchListAPI,
        outfit: OutFItAPI,
        outfitFilter: OutfitFilterAPI,
        filterClosetItem : FilterClosetItemsAPI
    ) {
        closetItemsAPI = closetItems
        addTofavClosetItemAPI = addTofavClosetItem
        deleteClosetItemAPI = deleteClosetItem
        addItemToAnotherClosetAPI = addItemToAnotherCloset
        moveItemAPI = moveClosetItem
        duplicateItemAPI = duplicateItem
        fetchListAPI = fetchList
        outFItAPI = outfit
        outfitFilterAPI = outfitFilter
        filterClosetItemsAPI = filterClosetItem
        webAPI = WebAPI()
    }

    fun Controller(
        liveEventsAP: LiveEventsAPI,
        favLiveEvent: FavLiveEventAPI
    ) {
        liveEventsAPI = liveEventsAP
        favLiveEventAPI = favLiveEvent

        webAPI = WebAPI()
    }


    fun Controller(hireAnExpert: HireAnExpertAPI, sendInvitation: SendInvitationAPI) {
        hireAnExpertAPI = hireAnExpert
        sendInvitationAPI = sendInvitation
        webAPI = WebAPI()
    }


    fun Controller(
        eventsDetail: EventsDetailAPI,
        addTofavClosetItem: AddTofavClosetItemAPI,
        addItemToAnotherCloset: AdDItemToAnotherClosetAPI
    ) {
        eventsDetailAPI = eventsDetail
        addTofavClosetItemAPI = addTofavClosetItem
        addItemToAnotherClosetAPI = addItemToAnotherCloset
        webAPI = WebAPI()
    }

    fun Controller(allClosets: AllClosetsAPI, addTofavClosetItem: AddTofavClosetItemAPI) {
        allClosetAPI = allClosets
        addTofavClosetItemAPI = addTofavClosetItem
        webAPI = WebAPI()
    }

    fun Controller(
        addClosetItemList: AddClosetItemListAPI,
        addclosetItem: AddClosetItemAPI,
        closetItems: ClosetItemsAPI,
        editClosetItem: EditClosetItemAPI
    ) {
        addClosetItemListAPI = addClosetItemList
        addClosetItemsAPI = addclosetItem
        closetItemsAPI = closetItems
        editClosetItemAPI = editClosetItem
        webAPI = WebAPI()
    }

    fun Controller(cartItem: CartItemAPI) {
        cartItemAPI = cartItem
        webAPI = WebAPI()
    }

    fun Controller(pricing: PricingAPI, changeplan: ChangePlanAPI,userProfile : UserProfileAPI) {
        pricingAPI = pricing
        changePlanAPI = changeplan
        userProfileAPI = userProfile
        webAPI = WebAPI()
    }

    fun Controller(pricing: PricingAPI) {
        pricingAPI = pricing
        webAPI = WebAPI()
    }

    fun Controller(paymentProfile: PaymentProfileAPI) {
        paymentProfileAPI = paymentProfile
        webAPI = WebAPI()
    }

    fun Controller(workInvitation: WorkInvitationAPI, makeOffer: MakeOfferAPI) {
        workInvitationAPI = workInvitation
        makeOfferAPI = makeOffer
        webAPI = WebAPI()
    }

    fun Controller(offerlist: OfferListAPI, setOffer: SetOfferDecisionAPI) {
        offerListAPI = offerlist
        setOfferDesicionAPI = setOffer
        webAPI = WebAPI()
    }

    fun Controller(contractlist: ContractListAPI, sendClaim: SendClaimAPI) {
        contractListAPI = contractlist
        sendClaimAPI = sendClaim
        webAPI = WebAPI()
    }

    fun Controller(userInvitation: UserInviatationsAPI) {
        userInviatationsAPI = userInvitation
        webAPI = WebAPI()
    }

    fun Controller(subscribePlan: SubscribePlanAPI) {
        subscribePlanAPI = subscribePlan
        webAPI = WebAPI()
    }

    fun Controller(getChatUsers: GetChatUsersAPI){
        getChatUsersAPI = getChatUsers
        webAPI = WebAPI()
    }

    fun Controller(getChat : GetChatAPI,senduserchat: SendUserChatAPI,blockUser: BlockUserAPI){
        getCHatAPI = getChat
        senduserchatAPI = senduserchat
        blockUserAPI = blockUser
        webAPI = WebAPI()
    }

    fun Controller(userProfile: UserProfileAPI)
    {
        userProfileAPI = userProfile
        webAPI = WebAPI()
    }

    fun setForgotPassword(email: String) {
        webAPI?.api?.forgotPassword(email)?.enqueue(object : Callback<ForgotPassworResponse> {
            override fun onResponse(
                call: Call<ForgotPassworResponse>,
                response: Response<ForgotPassworResponse>
            ) {
                val forgot = response
                fOrgotPasswordAPI?.onForgotPasswordSuccess(forgot)
            }

            override fun onFailure(call: Call<ForgotPassworResponse>, t: Throwable) {
                fOrgotPasswordAPI?.error(t.message)
            }

        })
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

    fun setMyWall(token: String?, userId: String?) {
        webAPI?.api?.myWall(token, userId)?.enqueue(object : Callback<MyWallResponse> {
            override fun onResponse(
                call: Call<MyWallResponse>,
                response: Response<MyWallResponse>
            ) {
                val myWallResponse = response
                myWallAPI?.onMyWallSuccess(myWallResponse)
            }

            override fun onFailure(call: Call<MyWallResponse>, t: Throwable) {
                myWallAPI?.error(t.message)
            }

        })
    }

    fun setUpDateProfile(
        token: String,
        firstname: String,
        lastname: String,
        email: String,
        paypal_email : String,
        phone: String,
        about: String,
        userID: String,
        chat_invitation:String
    ) {
        webAPI?.api?.editProfile(token, firstname, lastname, email,paypal_email, phone, about, userID,chat_invitation)
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

    fun EventDetail(token: String?, id: String?) {
        webAPI?.api?.eventDetail(token, id)?.enqueue(object : Callback<EventDetailResponse> {
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


    fun AllClosets(token: String?) {
        webAPI?.api?.allClosets(token)?.enqueue(object : Callback<AllClosetsResponse> {
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

    fun AddItemToAnotherCloset(token: String?, itemId: String?, closetId: String?, type: String?) {
        webAPI?.api?.addItemToAnotherCloset(token, itemId, closetId, type)
            ?.enqueue(object : Callback<AddItemToAnotherCloset> {
                override fun onResponse(
                    call: Call<AddItemToAnotherCloset>,
                    response: Response<AddItemToAnotherCloset>
                ) {
                    val addToCloset = response
                    addItemToAnotherClosetAPI?.onAddItemToAnotherClosetSuccess(addToCloset)
                }

                override fun onFailure(call: Call<AddItemToAnotherCloset>, t: Throwable) {
                    addItemToAnotherClosetAPI?.error(t.message)
                }

            })
    }

    fun favLiveEvent(token: String?, id: String?, type: String?) {
        webAPI?.api?.favLiveEventResponse(token, id, type)
            ?.enqueue(object : Callback<FavLiveEventResponse> {
                override fun onResponse(
                    call: Call<FavLiveEventResponse>,
                    response: Response<FavLiveEventResponse>
                ) {
                    val favLiveEvent = response
                    favLiveEventAPI?.onFavLiveEventSuccess(favLiveEvent)
                }

                override fun onFailure(call: Call<FavLiveEventResponse>, t: Throwable) {
                    favLiveEventAPI?.error(t.message)
                }

            })
    }

    fun AddClosetItemList(token: String?) {
        webAPI?.api?.addClosetItemLists(token)?.enqueue(object : Callback<AddClosetItemResponse> {
            override fun onResponse(
                call: Call<AddClosetItemResponse>,
                response: Response<AddClosetItemResponse>
            ) {
                val closetLists = response
                addClosetItemListAPI?.onAddClosetItemListSuccess(closetLists)
            }

            override fun onFailure(call: Call<AddClosetItemResponse>, t: Throwable) {
                addClosetItemListAPI?.error(t.message)
            }
        })
    }

    fun AddClosetItems(
        token: String?,
        picture: MultipartBody.Part,
        title: String,
        description: String,
        closet_id: String,
        category_id: String,
        size: String,
        color: String,
        brand: String,
        price: String
    ) {
        webAPI?.api?.addClosetItem(
            token,
            picture,
            title,
            description,
            closet_id,
            category_id,
            size,
            color,
            brand,
            price.toDouble()
        )?.enqueue(object : Callback<AddClosetItemResponse> {
            override fun onResponse(
                call: Call<AddClosetItemResponse>,
                response: Response<AddClosetItemResponse>
            ) {
                val addclosetItem = response
                addClosetItemsAPI?.onAddClosetItemSuccess(addclosetItem)
            }

            override fun onFailure(call: Call<AddClosetItemResponse>, t: Throwable) {
                addTofavClosetItemAPI?.error(t.message)
            }
        })
    }

    fun EditClosetItem(
        token: String?,
        picture: MultipartBody.Part,
        title: String?,
        decripition: String?,
        closet_id: String,
        category_id: String,
        size: String,
        color: String,
        brand: String,
        price: String,
        id: String?
    ) {
        webAPI?.api?.editClosetItem(
            token,
            picture,
            title,
            decripition,
            closet_id,
            category_id,
            size,
            color,
            brand,
            price.toDouble(),
            id
        )?.enqueue(object : Callback<EditClosetItemResponse> {
            override fun onResponse(
                call: Call<EditClosetItemResponse>,
                response: Response<EditClosetItemResponse>
            ) {
                val editClosetItem = response
                editClosetItemAPI?.onEditClosetItemSuccess(editClosetItem)
            }

            override fun onFailure(call: Call<EditClosetItemResponse>, t: Throwable) {
                editClosetItemAPI?.error(t.message)
            }

        })
    }

    fun CartItems(token: String?) {
        webAPI?.api?.cartItem(token)?.enqueue(object : Callback<CartItemsResponse> {
            override fun onResponse(
                call: Call<CartItemsResponse>,
                response: Response<CartItemsResponse>
            ) {
                val cart = response
                cartItemAPI?.onCartItemSuccess(cart)
            }

            override fun onFailure(call: Call<CartItemsResponse>, t: Throwable) {
                cartItemAPI?.error(t.message)
            }

        })
    }

    fun Pricing(token: String?) {
        webAPI?.api?.pricing(token)?.enqueue(object : Callback<PricingResponse> {
            override fun onResponse(
                call: Call<PricingResponse>,
                response: Response<PricingResponse>
            ) {
                val pricing = response
                pricingAPI?.onPricingSuccess(pricing)
            }

            override fun onFailure(call: Call<PricingResponse>, t: Throwable) {
                pricingAPI?.error(t.message)
            }

        })
    }

    fun MoveItem(token: String?, items: String, closetId: String, name: String) {
        webAPI?.api?.moveItem(token, items, closetId, name)
            ?.enqueue(object : Callback<MoveClosetItems> {
                override fun onResponse(
                    call: Call<MoveClosetItems>,
                    response: Response<MoveClosetItems>
                ) {
                    val moveitems = response
                    moveItemAPI?.onMoveItemSuccess(moveitems)
                }

                override fun onFailure(call: Call<MoveClosetItems>, t: Throwable) {
                    moveItemAPI?.error(
                        t
                            .message
                    )
                }

            })
    }

    fun DuplicateItem(token: String?, items: String, closetId: String, name: String) {
        webAPI?.api?.duplicateItem(token, items, closetId, name)
            ?.enqueue(object : Callback<DuplicateItemResponse> {
                override fun onResponse(
                    call: Call<DuplicateItemResponse>,
                    response: Response<DuplicateItemResponse>
                ) {
                    val duplicate = response
                    duplicateItemAPI?.onDuplicateItemSuccess(duplicate)
                }

                override fun onFailure(call: Call<DuplicateItemResponse>, t: Throwable) {
                    duplicateItemAPI?.error(t.message)
                }

            })
    }

    fun FollowUnFollow(token: String?, userId: String?) {
        webAPI?.api?.followUnFollow(token, userId)
            ?.enqueue(object : Callback<FollowUnFollowResponse> {
                override fun onResponse(
                    call: Call<FollowUnFollowResponse>,
                    response: Response<FollowUnFollowResponse>
                ) {
                    val followUnfollow = response
                    followUnFollowAPI?.onFollowUnfollowSuccess(followUnfollow)
                }

                override fun onFailure(call: Call<FollowUnFollowResponse>, t: Throwable) {
                    followUnFollowAPI?.error(t.message)
                }
            })
    }

    fun FetchList(token: String?) {
        webAPI?.api?.fetchList(token)?.enqueue(object : Callback<FetchListResponse> {
            override fun onResponse(
                call: Call<FetchListResponse>,
                response: Response<FetchListResponse>
            ) {
                val fetchList = response
                fetchListAPI?.onFetchListSuccess(fetchList)
            }

            override fun onFailure(call: Call<FetchListResponse>, t: Throwable) {
                fetchListAPI?.error(t.message)
            }

        })
    }

    fun OutFIt(token: String?, items: String?, outfitid: String?, name: String?) {
        webAPI?.api?.outfitItem(token, items, outfitid, name)
            ?.enqueue(object : Callback<OutFitResponse> {
                override fun onResponse(
                    call: Call<OutFitResponse>,
                    response: Response<OutFitResponse>
                ) {
                    val outfit = response
                    outFItAPI?.onOutfitSuccess(outfit)
                }

                override fun onFailure(call: Call<OutFitResponse>, t: Throwable) {
                    outFItAPI?.error(t.message)
                }

            })
    }

    fun PaymentProfile(token: String?) {
        webAPI?.api?.paymentProfile(token)?.enqueue(object : Callback<PaymentProfileResponse> {
            override fun onResponse(
                call: Call<PaymentProfileResponse>,
                response: Response<PaymentProfileResponse>
            ) {
                val paymentprofile = response
                paymentProfileAPI?.onPaymentProfileSuccess(paymentprofile)
            }

            override fun onFailure(call: Call<PaymentProfileResponse>, t: Throwable) {
                paymentProfileAPI?.error(t.message)
            }

        })
    }

    fun WorkInvitation(token: String?, id: String?) {
        webAPI?.api?.workinvitations(token, id)?.enqueue(object : Callback<WorkInvitationResponse> {
            override fun onResponse(
                call: Call<WorkInvitationResponse>,
                response: Response<WorkInvitationResponse>
            ) {
                val workinvitation = response
                workInvitationAPI?.onWorkInviationSuccess(workinvitation)
            }

            override fun onFailure(call: Call<WorkInvitationResponse>, t: Throwable) {
                workInvitationAPI?.error(t.message)
            }
        })
    }

    fun MakeOffer(
        token: String?,
        designerId: String,
        gig_id: String?,
        rate_type: String,
        comments: String?,
        rate: String
    ) {
        webAPI?.api?.makeOffer(token, designerId, gig_id, rate_type, comments, rate)
            ?.enqueue(object : Callback<MakeOfferResponse> {
                override fun onResponse(
                    call: Call<MakeOfferResponse>,
                    response: Response<MakeOfferResponse>
                ) {
                    val makeoffer = response
                    makeOfferAPI?.onMakeOfferSuccess(makeoffer)
                }

                override fun onFailure(call: Call<MakeOfferResponse>, t: Throwable) {
                    makeOfferAPI?.error(
                        t
                            .message
                    )
                }

            })
    }

    fun OfferList(token: String?) {
        webAPI?.api?.offerList(token)?.enqueue(object : Callback<OfferListResponse> {
            override fun onResponse(
                call: Call<OfferListResponse>,
                response: Response<OfferListResponse>
            ) {
                val offerlist = response
                offerListAPI?.onOfferListSuccess(offerlist)
            }

            override fun onFailure(call: Call<OfferListResponse>, t: Throwable) {
                offerListAPI?.error(t.message)
            }

        })
    }

    fun ContractList(token: String?) {
        webAPI?.api?.contractList(token)?.enqueue(object : Callback<ContractListResponse> {
            override fun onResponse(
                call: Call<ContractListResponse>,
                response: Response<ContractListResponse>
            ) {
                val contractlist = response
                contractListAPI?.onContractListSuccess(contractlist)
            }

            override fun onFailure(call: Call<ContractListResponse>, t: Throwable) {
                contractListAPI?.error(t.message)
            }

        })
    }

    fun OutFitFilter(token: String?, outfitid: String?, closetID: String?) {
        webAPI?.api?.outfitfilter(token, outfitid, closetID)
            ?.enqueue(object : Callback<OutfitFilterResponse> {
                override fun onResponse(
                    call: Call<OutfitFilterResponse>,
                    response: Response<OutfitFilterResponse>
                ) {
                    val outfileFilter = response
                    outfitFilterAPI?.onOutfitFilterSuccess(outfileFilter)
                }

                override fun onFailure(call: Call<OutfitFilterResponse>, t: Throwable) {
                    outfitFilterAPI?.error(t.message)
                }

            })
    }

    fun SendClaim(token: String?, contract_id: String, description: String) {
        webAPI?.api?.sendClaim(token, contract_id, description)
            ?.enqueue(object : Callback<SendClaimResponse> {
                override fun onResponse(
                    call: Call<SendClaimResponse>,
                    response: Response<SendClaimResponse>
                ) {
                    val sendClaim = response
                    sendClaimAPI?.onSendClaimSuccess(sendClaim)
                }

                override fun onFailure(call: Call<SendClaimResponse>, t: Throwable) {
                    sendInvitationAPI?.error(
                        t
                            .message
                    )
                }

            })
    }

    fun SetOfferDecision(token: String?, id: String?, action: String) {
        webAPI?.api?.setOfferdecision(token, id, action)
            ?.enqueue(object : Callback<SetOfferDecisionResponse> {
                override fun onResponse(
                    call: Call<SetOfferDecisionResponse>,
                    response: Response<SetOfferDecisionResponse>
                ) {
                    val setOffer = response
                    setOfferDesicionAPI?.onSetOfferSuccess(setOffer)
                }

                override fun onFailure(call: Call<SetOfferDecisionResponse>, t: Throwable) {
                    setOfferDesicionAPI?.error(t.message)
                }

            })
    }

    fun SetPaymentMethod(token: String?) {
        webAPI?.api?.paymentMethod(token)?.enqueue(object : Callback<PaymentMethodResponse> {
            override fun onResponse(
                call: Call<PaymentMethodResponse>,
                response: Response<PaymentMethodResponse>
            ) {
                val paymentmethod = response
                paymentMethodAPI?.onPaymentSuccess(paymentmethod)
            }

            override fun onFailure(call: Call<PaymentMethodResponse>, t: Throwable) {
                paymentMethodAPI?.error(
                    t
                        .message
                )
            }

        })
    }


    fun DeletePaymentCard(token: String?, cardID: String) {
        webAPI?.api?.deleteCard(token, cardID)
            ?.enqueue(object : Callback<DeletePaymentMethodResponse> {
                override fun onResponse(
                    call: Call<DeletePaymentMethodResponse>,
                    response: Response<DeletePaymentMethodResponse>
                ) {
                    val deleteCard = response
                    deleteCardAPI?.onDeleteCardSuccess(deleteCard)
                }

                override fun onFailure(call: Call<DeletePaymentMethodResponse>, t: Throwable) {
                    deleteCardAPI?.error(t.message!!)
                }

            })
    }

    fun UserInvitation(token: String?, userId: String?) {
        webAPI?.api?.userInvitations(token, userId)
            ?.enqueue(object : Callback<UserInvitationsResponse> {
                override fun onResponse(
                    call: Call<UserInvitationsResponse>,
                    response: Response<UserInvitationsResponse>
                ) {
                    val userInvitation = response
                    userInviatationsAPI?.onUserInvitationSuccess(userInvitation)
                }

                override fun onFailure(call: Call<UserInvitationsResponse>, t: Throwable) {
                    userInviatationsAPI?.error(t.message)
                }

            })
    }

    fun ChangePlan(token: String?, id: String?, type: String?, plantype: String) {
        webAPI?.api?.changePlan(token, id, type, plantype)
            ?.enqueue(object : Callback<ChangePlanResponse> {
                override fun onResponse(
                    call: Call<ChangePlanResponse>,
                    response: Response<ChangePlanResponse>
                ) {
                    val changeplan = response
                    changePlanAPI?.onChangePlanSuccess(changeplan)
                }

                override fun onFailure(call: Call<ChangePlanResponse>, t: Throwable) {
                    changePlanAPI?.error(t.message)
                }

            })
    }

    fun SubscribePlan(
        token: String?,
        brand: String,
        charge_type: String,
        exp_date: String,
        last_4: String,
        name: String,
        payment_method_type: String,
        plan_id: String,
        stripetoken: String,
        type: String
    ) {
        webAPI?.api?.subscribePlan(
            token,
            brand,
            charge_type,
            exp_date,
            last_4,
            name,
            payment_method_type,
            plan_id,
            stripetoken,
            type
        )?.enqueue(object : Callback<SubscribePlanResponse> {
            override fun onResponse(
                call: Call<SubscribePlanResponse>,
                response: Response<SubscribePlanResponse>
            ) {
                val subscribeplan = response
                subscribePlanAPI?.onSubscribeSuccess(subscribeplan)
            }

            override fun onFailure(call: Call<SubscribePlanResponse>, t: Throwable) {
                subscribePlanAPI?.error(t.message)
            }

        })
    }

    fun CancelPlan (token: String?,id: String?,type: String?)
    {
        webAPI?.api?.cancelPlan(token, id, type)?.enqueue(object : Callback<CancelPlanResponse>
        {
            override fun onResponse(
                call: Call<CancelPlanResponse>,
                response: Response<CancelPlanResponse>
            ) {
               val cancelPlan = response
                cancelPlanAPI?.onCancelPlan(cancelPlan)
            }

            override fun onFailure(call: Call<CancelPlanResponse>, t: Throwable) {
                cancelPlanAPI?.error(t.message)
            }

        })
    }

    fun AddPaymentMethod(token: String?,brand: String,payment_method_type: String,stripetoken: String)
    {
        webAPI?.api?.addpaymentMethod(token, brand, payment_method_type, stripetoken)?.enqueue(object : Callback<CancelPlanResponse>
        {
            override fun onResponse(
                call: Call<CancelPlanResponse>,
                response: Response<CancelPlanResponse>
            ) {
                val addpaymentmethod = response
                addPaymentMethodAPI?.onAddPaymentSuccess(addpaymentmethod)
            }

            override fun onFailure(call: Call<CancelPlanResponse>, t: Throwable) {
                addPaymentMethodAPI?.error(t.message)
            }

        })
    }

    fun FilterCloseItems(token: String?,closet_id: String, hashMap: HashMap<String,String>)
    {
        webAPI?.api?.filterCLosetItems(token,closet_id,hashMap)?.enqueue(object :Callback<List<FilterResponse>>
        {
            override fun onResponse(
                call: Call<List<FilterResponse>>,
                response: Response<List<FilterResponse>>
            ) {
                val filtercloset = response
                filterClosetItemsAPI?.onFilterClosetItemSuccess(filtercloset)
            }

            override fun onFailure(call: Call<List<FilterResponse>>, t: Throwable) {
               filterClosetItemsAPI?.error(t.message)
            }

        })
    }

    fun GetChatUsers(token: String?)
    {
        webAPI?.api?.getChatUsers(token)?.enqueue(object : Callback<GetChatUsers>
        {
            override fun onResponse(call: Call<GetChatUsers>, response: Response<GetChatUsers>) {
                val getchatusers = response
                getChatUsersAPI?.onGetChatUsersSuccess(getchatusers)
            }

            override fun onFailure(call: Call<GetChatUsers>, t: Throwable) {
                getChatUsersAPI?.error(t.message)
            }

        })
    }

    fun GetChat(token: String?,id: String?)
    {
        webAPI?.api?.getCHat(token, id)?.enqueue(object :Callback<GetChatResponse>
        {
            override fun onResponse(
                call: Call<GetChatResponse>,
                response: Response<GetChatResponse>
            ) {
                val getChat = response
                getCHatAPI?.onGetChatSuccess(getChat)
            }

            override fun onFailure(call: Call<GetChatResponse>, t: Throwable) {
                getCHatAPI?.error(t.message)
            }

        })
    }

    fun SendChat(token: String?,id: String?,message:String)
    {
        webAPI?.api?.sendChat(token,id,message)?.enqueue(object :Callback<SendChatResponse>
        {
            override fun onResponse(
                call: Call<SendChatResponse>,
                response: Response<SendChatResponse>
            ) {
                val sendChat = response
                senduserchatAPI?.onGetUserChatSuccess(sendChat)
            }

            override fun onFailure(call: Call<SendChatResponse>, t: Throwable) {
               senduserchatAPI?.error(t.message)
            }

        })
    }

    fun BlockUser(token: String?,id: String?)
    {
        webAPI?.api?.blockUser(token, id)?.enqueue(object : Callback<BlockResponse>
        {
            override fun onResponse(call: Call<BlockResponse>, response: Response<BlockResponse>) {
                val blockUser = response
                blockUserAPI?.onBlockUserSuccess(blockUser)
            }

            override fun onFailure(call: Call<BlockResponse>, t: Throwable) {
                blockUserAPI?.error(t.message)
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
        fun onAllEventsSuccess(allClosetsResponse: Response<AllClosetsResponse>)
        fun error(error: String?)
    }

    interface AdDItemToAnotherClosetAPI {
        fun onAddItemToAnotherClosetSuccess(addItemToAnotherCloset: Response<AddItemToAnotherCloset>)
        fun error(error: String?)
    }

    interface FavLiveEventAPI {
        fun onFavLiveEventSuccess(favLiveEvent: Response<FavLiveEventResponse>)
        fun error(error: String?)
    }

    interface AddClosetItemListAPI {
        fun onAddClosetItemListSuccess(addClosetItemList: Response<AddClosetItemResponse>)
        fun error(error: String?)
    }

    interface AddClosetItemAPI {
        fun onAddClosetItemSuccess(addclosetItem: Response<AddClosetItemResponse>)
        fun error(error: String?)
    }

    interface EditClosetItemAPI {
        fun onEditClosetItemSuccess(editClosetItem: Response<EditClosetItemResponse>)
        fun error(error: String?)
    }

    interface CartItemAPI {
        fun onCartItemSuccess(cartitem: Response<CartItemsResponse>)
        fun error(error: String?)
    }

    interface MyWallAPI {
        fun onMyWallSuccess(myWall: Response<MyWallResponse>)
        fun error(error: String?)
    }

    interface PricingAPI {
        fun onPricingSuccess(pricing: Response<PricingResponse>)
        fun error(error: String?)
    }

    interface MoveItemAPI {
        fun onMoveItemSuccess(moveItem: Response<MoveClosetItems>)
        fun error(error: String?)
    }

    interface FollowUnFollowAPI {
        fun onFollowUnfollowSuccess(followUnfollow: Response<FollowUnFollowResponse>)
        fun error(error: String?)
    }

    interface DuplicateItemAPI {
        fun onDuplicateItemSuccess(duplicateItem: Response<DuplicateItemResponse>)
        fun error(error: String?)
    }

    interface FetchListAPI {
        fun onFetchListSuccess(fetchList: Response<FetchListResponse>)
        fun error(error: String?)
    }

    interface OutFItAPI {
        fun onOutfitSuccess(outfit: Response<OutFitResponse>)
        fun error(error: String?)
    }

    interface PaymentProfileAPI {
        fun onPaymentProfileSuccess(paymentProfile: Response<PaymentProfileResponse>)
        fun error(error: String?)
    }

    interface WorkInvitationAPI {
        fun onWorkInviationSuccess(workInvitation: Response<WorkInvitationResponse>)
        fun error(error: String?)
    }

    interface MakeOfferAPI {
        fun onMakeOfferSuccess(makeOffer: Response<MakeOfferResponse>)
        fun error(error: String?)
    }

    interface OfferListAPI {
        fun onOfferListSuccess(offerlist: Response<OfferListResponse>)
        fun error(error: String?)
    }

    interface ContractListAPI {
        fun onContractListSuccess(contractlist: Response<ContractListResponse>)
        fun error(error: String?)
    }

    interface OutfitFilterAPI {
        fun onOutfitFilterSuccess(outfiFIlter: Response<OutfitFilterResponse>)
        fun error(error: String?)
    }

    interface SendClaimAPI {
        fun onSendClaimSuccess(sendClaim: Response<SendClaimResponse>)
        fun error(error: String?)
    }

    interface SetOfferDecisionAPI {
        fun onSetOfferSuccess(setOffer: Response<SetOfferDecisionResponse>)
        fun error(error: String?)
    }

    interface PaymentMethodAPI {
        fun onPaymentSuccess(paymentMethod: Response<PaymentMethodResponse>)
        fun error(error: String?)
    }

    interface DeleteCardAPI {
        fun onDeleteCardSuccess(deleteCard: Response<DeletePaymentMethodResponse>)
        fun error(error: String?)
    }

    interface FOrgotPasswordAPI {
        fun onForgotPasswordSuccess(forgotPassword: Response<ForgotPassworResponse>)
        fun error(error: String?)
    }

    interface UserInviatationsAPI {
        fun onUserInvitationSuccess(userInvitations: Response<UserInvitationsResponse>)
        fun error(error: String?)
    }

    interface ChangePlanAPI {
        fun onChangePlanSuccess(changePlan: Response<ChangePlanResponse>)
        fun error(error: String?)
    }

    interface SubscribePlanAPI {
        fun onSubscribeSuccess(subscribe: Response<SubscribePlanResponse>)
        fun error(error: String?)
    }

    interface CancelPlanAPI {
        fun onCancelPlan(cancelPlan : Response<CancelPlanResponse>)
        fun error(error: String?)
    }

    interface AddPaymentMethodAPI {
        fun onAddPaymentSuccess(addPayment : Response<CancelPlanResponse>)
        fun error(error: String?)
    }

    interface FilterClosetItemsAPI {
        fun onFilterClosetItemSuccess(filtersucces : Response<List<FilterResponse>>)
        fun error(error: String?)
    }

    interface GetChatUsersAPI {
        fun onGetChatUsersSuccess(chatUsers : Response<GetChatUsers>)
        fun error(error: String?)
    }

    interface GetChatAPI {
        fun onGetChatSuccess(getCHat : Response<GetChatResponse>)
        fun error(error: String?)
    }

    interface SendUserChatAPI {
        fun onGetUserChatSuccess(senduserchat : Response<SendChatResponse>)
        fun error(error: String?)
    }

    interface BlockUserAPI {
        fun onBlockUserSuccess(blockUser : Response<BlockResponse>)
        fun error(error: String?)
    }
}
