package com.casebeaumonde.`interface`

import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.*

interface ApiHelper {

    @FormUrlEncoded
    @POST(".")
    fun login(@Field("method") Login:String,@Field("email") email:String,@Field("password") password:String) : Call<JsonObject>

    @FormUrlEncoded
    @POST(".")
    fun logout(@Field("method") Logout:String,@Field("Authorization") token:String)
}