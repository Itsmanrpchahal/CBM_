package com.casebeaumonde.Retrofit;

import com.casebeaumonde.UpdateProfilePicResponse;
import com.casebeaumonde.activities.login.loginResponse.LoginResponse;
import com.casebeaumonde.activities.login.loginResponse.LogoutResponse;
import com.casebeaumonde.activities.myGigs.response.MyGigsResponse;
import com.casebeaumonde.activities.userRegister.userRegisterResponse.UserRegisterResponse;
import com.casebeaumonde.fragments.profile.profileResponse.EditProfileResponse;
import com.casebeaumonde.fragments.profile.profileResponse.UserProfileResponse;
import com.casebeaumonde.notifications.response.NotificationsResponse;
import com.casebeaumonde.notifications.response.RemoveNotificationResponse;
import com.google.gson.JsonObject;

import java.util.ArrayList;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
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
            @Field("phone") String phone,
            @Field("about_me") String about_me,
            @Field("id") String id
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

}
