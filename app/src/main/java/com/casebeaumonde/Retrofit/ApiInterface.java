package com.casebeaumonde.Retrofit;

import com.casebeaumonde.activities.login.loginResponse.LoginResponse;
import com.casebeaumonde.activities.login.loginResponse.LogoutResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

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
}
