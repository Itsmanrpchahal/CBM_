package com.casebeaumonde.Retrofit

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class WebAPI {
    var BASE_URL = "http://dev.casabeaumonde.com/"
    var mInstance: WebAPI? = null
    var retrofit: Retrofit? = null
    var apiInterface: ApiInterface? = null
    fun WebAPI() {
        val gson = GsonBuilder()
            .setLenient()
            .create()
        val okHttpClient = OkHttpClient.Builder()
            .connectTimeout(180, TimeUnit.SECONDS)
            .writeTimeout(180, TimeUnit.SECONDS)
            .readTimeout(180, TimeUnit.SECONDS)
            .build()
        retrofit = Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()
        apiInterface = retrofit?.create(ApiInterface::class.java)
    }


    @Synchronized
    fun getInstance(): WebAPI? {
        if (mInstance == null) {
            mInstance = com.casebeaumonde.Retrofit.WebAPI()
        }
        return mInstance
    }

    fun getApi(): ApiInterface? {
        return retrofit!!.create(ApiInterface::class.java)
    }
}