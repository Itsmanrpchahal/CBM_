package com.casebeaumonde.Retrofit;

import com.casebeaumonde.UpdateProfilePicResponse;
import com.casebeaumonde.activities.ClosetItem.response.AddToFavClosetItemResponse;
import com.casebeaumonde.activities.ClosetItem.response.ClosetsItemsResponse;
import com.casebeaumonde.activities.ClosetItem.response.DeleteClosetItemResponse;
import com.casebeaumonde.activities.ClosetItem.response.EditClosetItemResponse;
import com.casebeaumonde.activities.ClosetItem.response.FilterResponse;
import com.casebeaumonde.activities.ClosetItem.response.OutfitFilterResponse;
import com.casebeaumonde.activities.EventsInvitations.response.UserInvitationsResponse;
import com.casebeaumonde.activities.eventDetail.response.AddItemToAnotherCloset;
import com.casebeaumonde.activities.eventDetail.response.EventDetailResponse;
import com.casebeaumonde.activities.login.loginResponse.ForgotPassworResponse;
import com.casebeaumonde.activities.login.loginResponse.LoginResponse;
import com.casebeaumonde.activities.login.loginResponse.LogoutResponse;
import com.casebeaumonde.activities.myContracts.tabs.Contract.response.ContractListResponse;
import com.casebeaumonde.activities.myContracts.tabs.Contract.response.SendClaimResponse;
import com.casebeaumonde.activities.myContracts.tabs.WorkInvitation.response.MakeOfferResponse;
import com.casebeaumonde.activities.myContracts.tabs.offers.response.OfferListResponse;
import com.casebeaumonde.activities.myContracts.tabs.WorkInvitation.response.WorkInvitationResponse;
import com.casebeaumonde.activities.myContracts.tabs.offers.response.SetOfferDecisionResponse;
import com.casebeaumonde.activities.myGigs.response.MyGigsResponse;
import com.casebeaumonde.activities.myclosets.response.DeleteClosetResponse;
import com.casebeaumonde.activities.myclosets.response.DuplicateItemResponse;
import com.casebeaumonde.activities.myclosets.response.FetchListResponse;
import com.casebeaumonde.activities.myclosets.response.MoveClosetItems;
import com.casebeaumonde.activities.myclosets.response.MyClosetsResponse;
import com.casebeaumonde.activities.myclosets.response.OutFitResponse;
import com.casebeaumonde.activities.myclosets.response.UpdateClosetsResponse;
import com.casebeaumonde.activities.openchat.response.BlockResponse;
import com.casebeaumonde.activities.openchat.response.GetChatResponse;
import com.casebeaumonde.activities.openchat.response.SendChatResponse;
import com.casebeaumonde.activities.paymentScreen.response.PaymentProfileResponse;
import com.casebeaumonde.activities.paymentScreen.response.SubscribePlanResponse;
import com.casebeaumonde.activities.userRegister.userRegisterResponse.UserRegisterResponse;
import com.casebeaumonde.fragments.allClosets.response.CreateClosetResponse;
import com.casebeaumonde.fragments.HireExpert.response.HireAnExpertResponse;
import com.casebeaumonde.fragments.HireExpert.response.SendInvitationResponse;
import com.casebeaumonde.fragments.Live_Events.response.FavLiveEventResponse;
import com.casebeaumonde.fragments.Live_Events.response.LiveEventsResponse;
import com.casebeaumonde.activities.addItemtoCLoset.response.AddClosetItemResponse;
import com.casebeaumonde.fragments.allClosets.response.AllClosetsResponse;
import com.casebeaumonde.fragments.cart.reponse.CartItemsResponse;
import com.casebeaumonde.fragments.chat.GetChatUsers;
import com.casebeaumonde.fragments.designers.Response.DesignersResponse;
import com.casebeaumonde.fragments.pricing.response.ChangePlanResponse;
import com.casebeaumonde.fragments.pricing.response.PricingResponse;
import com.casebeaumonde.fragments.profile.profileResponse.CancelPlanResponse;
import com.casebeaumonde.fragments.profile.profileResponse.DeletePaymentMethodResponse;
import com.casebeaumonde.fragments.profile.profileResponse.EditProfileResponse;
import com.casebeaumonde.fragments.profile.profileResponse.FollowUnFollowResponse;
import com.casebeaumonde.fragments.profile.profileResponse.MyWallResponse;
import com.casebeaumonde.fragments.profile.profileResponse.PaymentMethodResponse;
import com.casebeaumonde.fragments.profile.profileResponse.UserProfileResponse;
import com.casebeaumonde.fragments.users.Response.UsersResponse;
import com.casebeaumonde.activities.notifications.response.NotificationsResponse;
import com.casebeaumonde.activities.notifications.response.RemoveNotificationResponse;
import com.google.gson.JsonObject;

import java.util.HashMap;
import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {

    @FormUrlEncoded
    @POST("api/v1/login")
    Call<LoginResponse> loginCall(
            @Field("email") String email,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("api/v1/forgot-password")
    Call<ForgotPassworResponse> forgotPassword(
            @Field("email") String email
    );

    @GET("api/v1/logout")
    Call<LogoutResponse> logoutCall(
            @Header("Authorization") String token
    );

    @Multipart
    @POST("api/v1/register")
    Call<UserRegisterResponse> userRegisterCall(
            @Query("firstname") String firstname,
            @Query("lastname") String lastname,
            @Query("email") String email,
            @Query("password") String password,
            @Query("password_confirmation") String password_confirmation,
            @Query("phone") String phone,
            @Query("terms") String terms,
            @Query("about_me") String about_me,
            @Part MultipartBody.Part image,
            @Query("role") String role
    );

    @FormUrlEncoded
    @POST("api/v1/changePassword")
    Call<JsonObject> changePasswordCall(
            @Header("Authorization") String token,
            @Field("id") String id,
            @Field("old_password") String old_password,
            @Field("password") String password,
            @Field("password_confirmation") String password_confirmation
    );


    @GET("api/v1/userNotification/{input}")
    Call<NotificationsResponse> notificationCall(
            @Header("Authorization") String token,
            @Path("input") String userid
    );


    @GET("api/v1/userProfile/{input}")
    Call<UserProfileResponse> userProfileResponse(
            @Header("Authorization") String token,
            @Path("input") String userid
    );

    @Multipart
    @POST("api/v1/changeAvatar")
    Call<UpdateProfilePicResponse> updateAvatar(
            @Header("Authorization") String token,
            @Query("user_id") String user_id,
            @Part MultipartBody.Part image
    );

    @FormUrlEncoded
    @POST("api/v1/updateProfile")
    Call<EditProfileResponse> editProfile(
            @Header("Authorization") String token,
            @Field("firstname") String firstname,
            @Field("lastname") String lastname,
            @Field("email") String email,
            @Field("paypal_email") String paypal_email,
            @Field("phone") String phone,
            @Field("about_me") String about_me,
            @Field("id") String id,
            @Field("chat_invitation") String chat_invitation
    );

    @DELETE("api/v1/removeNotification/{input}")
    Call<RemoveNotificationResponse> removeNotification(
            @Header("Authorization") String token,
            @Path("input") String notId
    );

    @GET("api/v1/userGigs/{input}")
    Call<MyGigsResponse> usergigs(
            @Header("Authorization") String token,
            @Path("input") String userid
    );

    @GET("api/v1/myWall/{input}")
    Call<MyWallResponse> myWall(
            @Header("Authorization") String token,
            @Path("input") String userid
    );

    @GET("api/v1/users")
    Call<UsersResponse> users(
            @Header("Authorization") String token
    );

    @GET("api/v1/designers")
    Call<DesignersResponse> designers(
            @Header("Authorization") String token
    );

    @GET("api/v1/userClosets/{input}")
    Call<MyClosetsResponse> myClosets(
            @Header("Authorization") String token,
            @Path("input") String userId
    );

    @Multipart
    @POST("api/v1/createCloset")
    Call<CreateClosetResponse> createCloset(
            @Header("Authorization") String token,
            @Query("title") String title,
            @Query("visibility") String visibility,
            @Part MultipartBody.Part image,
            @Query("description") String description
    );

    @GET("api/v1/closet/{input}")
    Call<ClosetsItemsResponse> closetsItems(
            @Header("Authorization") String token,
            @Path("input") String closetId
    );


    @FormUrlEncoded
    @POST("api/v1/heartItem")
    Call<AddToFavClosetItemResponse> addToFavClosetItem(
            @Header("Authorization") String token,
            @Field("id") String id,
            @Field("type") String type
    );

    @Multipart
    @POST("api/v1/updateCloset")
    Call<UpdateClosetsResponse> closetUpdate(
            @Header("Authorization") String token,
            @Query("id") String id,
            @Query("title") String title,
            @Query("visibility") String visibility,
            @Part MultipartBody.Part image,
            @Query("description") String description
    );

    @DELETE("api/v1/deleteCloset/{input}")
    Call<DeleteClosetResponse> deleteCloset(
            @Header("Authorization") String token,
            @Path("input") String id
    );

    @DELETE("api/v1/removeItem/{input}")
    Call<DeleteClosetItemResponse> deleteClosetItem(
            @Header("Authorization") String token,
            @Path("input") String id
    );


    @GET("api/v1/hireAnExpert")
    Call<HireAnExpertResponse> hireanExpert(
            @Header("Authorization") String token
    );

    @FormUrlEncoded
    @POST("api/v1/sendInvitation")
    Call<SendInvitationResponse> sendInviation(
            @Header("Authorization") String token,
            @Field("designer_id") String designer_id,
            @Field("description") String description,
            @Field("gig_id") String gig_id,
            @Field("budget") String budget
    );

    @GET("api/v1/liveEvents")
    Call<LiveEventsResponse> liveEvents(
            @Header("Authorization") String token
    );

    @GET("api/v1/eventDetail/{input}")
    Call<EventDetailResponse> eventDetail(
            @Header("Authorization") String token,
            @Path("input") String id
    );

    @GET("api/v1/getAllCloset")
    Call<AllClosetsResponse> allClosets(
            @Header("Authorization") String token
    );

    @FormUrlEncoded
    @POST("api/v1/addItemToCloset")
    Call<AddItemToAnotherCloset> addItemToAnotherCloset(
            @Header("Authorization") String token,
            @Field("itemid") String itemid,
            @Field("closetid") String closetid,
            @Field("type") String type
    );

    @FormUrlEncoded
    @POST("api/v1/heartEvent")
    Call<FavLiveEventResponse> favLiveEventResponse(
            @Header("Authorization") String token,
            @Field("id") String id,
            @Field("type") String type
    );

    @GET("api/v1/fetchList")
    Call<AddClosetItemResponse> addClosetItemLists(
            @Header("Authorization") String token
    );

    @Multipart
    @POST("api/v1/addClosetItem")
    Call<AddClosetItemResponse> addClosetItem(
            @Header("Authorization") String token,
            @Part MultipartBody.Part picture,
            @Query("title") String title,
            @Query("description") String description,
            @Query("closet_id") String closet_id,
            @Query("category_id") String category_id,
            @Query("size") String size,
            @Query("color") String color,
            @Query("brand") String brand,
            @Query("price") Double price
    );

    @Multipart
    @POST("api/v1/updateClosetItem")
    Call<EditClosetItemResponse> editClosetItem(
            @Header("Authorization") String token,
            @Part MultipartBody.Part picture,
            @Query("title") String title,
            @Query("description") String description,
            @Query("closet_id") String closet_id,
            @Query("category_id") String category_id,
            @Query("size") String size,
            @Query("color") String color,
            @Query("brand") String brand,
            @Query("price") Double price,
            @Query("id") String id
    );


    @GET("api/v1/cartItem")
    Call<CartItemsResponse> cartItem(
            @Header("Authorization") String token
    );

    @GET("api/v1/userPlans")
    Call<PricingResponse> pricing(
            @Header("Authorization") String token
    );

    @FormUrlEncoded
    @POST("api/v1/move-closet-item")
    Call<MoveClosetItems> moveItem(
            @Header("Authorization") String token,
            @Field("items") String items,
            @Field("closetid") String closetid,
            @Field("name") String name

    );

    @GET("api/v1/follow-user/{input}")
    Call<FollowUnFollowResponse> followUnFollow(
            @Header("Authorization") String token,
            @Path("input") String id
    );

    @FormUrlEncoded
    @POST("api/v1/duplicate-item-closet")
    Call<DuplicateItemResponse> duplicateItem(
            @Header("Authorization") String token,
            @Field("items") String items,
            @Field("closetid") String closetid,
            @Field("name") String name
    );

    @FormUrlEncoded
    @POST("api/v1/outfit-item")
    Call<OutFitResponse> outfitItem(
            @Header("Authorization") String token,
            @Field("items") String items,
            @Field("outfitid") String outfitid,
            @Field("name") String name
    );

    @GET("api/v1/fetchList")
    Call<FetchListResponse> fetchList(
            @Header("Authorization") String token
    );

    @GET("api/v1/payment-profile")
    Call<PaymentProfileResponse> paymentProfile(
            @Header("Authorization") String token
    );

    @GET("api/v1/userInvitation/{input}")
    Call<WorkInvitationResponse> workinvitations(
            @Header("Authorization") String token,
            @Path("input") String id
    );

    @FormUrlEncoded
    @POST("api/v1/sendOffer")
    Call<MakeOfferResponse> makeOffer(
            @Header("Authorization") String token,
            @Field("designer_id") String designer_id,
            @Field("gig_id") String gig_id,
            @Field("rate_type") String rate_type,
            @Field("comments") String comments,
            @Field("rate") String rate
    );

    @GET("api/v1/user-offers")
    Call<OfferListResponse> offerList(
            @Header("Authorization") String token
    );

    @GET("api/v1/user-contracts")
    Call<ContractListResponse> contractList(
            @Header("Authorization") String token
    );


    @FormUrlEncoded
    @POST("api/v1/fetch-outfit-item")
    Call<OutfitFilterResponse> outfitfilter(
            @Header("Authorization") String token,
            @Field("outfitid") String outfitid,
            @Field("closetid") String closetid
    );

    @FormUrlEncoded
    @POST("api/v1/open-claim")
    Call<SendClaimResponse> sendClaim(
            @Header("Authorization") String token,
            @Field("contract_id") String contract_id,
            @Field("description") String description
    );

    @GET("api/v1/set-offer-decision/{input}/{input1}")
    Call<SetOfferDecisionResponse> setOfferdecision(
            @Header("Authorization") String token,
            @Path("input") String id,
            @Path("input1") String action
    );

    @GET("api/v1/payment-methods")
    Call<PaymentMethodResponse> paymentMethod(
            @Header("Authorization") String token
    );


    @DELETE("api/v1/remove-payment-method/{input}")
    Call<DeletePaymentMethodResponse> deleteCard(
            @Header("Authorization") String token,
            @Path("input") String cardID
    );

    @GET("api/v1/userEventInvitations/{input}")
    Call<UserInvitationsResponse> userInvitations(
            @Header("Authorization") String token,
            @Path("input") String userID
    );

    @GET("api/v1/change-subscription/{input}/{input1}/{input2}")
    Call<ChangePlanResponse> changePlan(
            @Header("Authorization") String token,
            @Path("input") String id,
            @Path("input1") String type,
            @Path("input2") String charge_type
    );

    @FormUrlEncoded
    @POST("api/v1/store-subscription")
    Call<SubscribePlanResponse> subscribePlan(
            @Header("Authorization") String token,
            @Field("brand") String brand,
            @Field("charge_type") String charge_type,
            @Field("exp_date") String exp_date,
            @Field("last_4") String last_4,
            @Field("name") String name,
            @Field("payment_method_type") String payment_method_type,
            @Field("plan_id") String plan_id,
            @Field("token") String Stripetoken,
            @Field("type") String type
    );

    @GET("api/v1/cancel-plan/{input}/{input1}")
    Call<CancelPlanResponse> cancelPlan(
            @Header("Authorization") String token,
            @Path("input") String id,
            @Path("input1") String type

    );

    @FormUrlEncoded
    @POST("api/v1/add-payment-method")
    Call<CancelPlanResponse> addpaymentMethod(
            @Header("Authorization") String token,
            @Field("brand") String brand,
            @Field("payment_method_type") String payment_method_type,
            @Field("token") String stripetoken
            );

    @FormUrlEncoded
    @POST("api/v1/filter-closet")
    Call<List<FilterResponse>> filterCLosetItems(
            @Header("Authorization") String token,
            @Field("closetid") String closetid,
            @FieldMap HashMap<String,String> map
    );

    @GET("api/v1/getChatUsers/{id}")
    Call<GetChatUsers> getChatUsers(
            @Header("Authorization") String token
    );

    @GET("api/v1/getMessageFor/{input}")
    Call<GetChatResponse> getCHat(
            @Header("Authorization") String token,
            @Path("input") String id
    );


    @FormUrlEncoded
    @POST("api/v1/addMessage")
    Call<SendChatResponse> sendChat(
            @Header("Authorization") String token,
            @Field("to_id") String to_id,
            @Field("message") String message
    );

    @GET("api/v1/blockUser/{input}")
    Call<BlockResponse> blockUser(
            @Header("Authorization") String token,
            @Path("input") String id
    );
}
