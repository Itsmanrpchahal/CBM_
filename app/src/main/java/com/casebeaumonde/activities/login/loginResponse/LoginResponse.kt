package com.casebeaumonde.activities.login.loginResponse

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class LoginResponse {

    @SerializedName("code")
    @Expose
    private var code: Int? = null

    @SerializedName("data")
    @Expose
    private var data: Data? = null

    fun getCode(): Int? {
        return code
    }

    fun setCode(code: Int?) {
        this.code = code
    }

    fun getData(): Data? {
        return data
    }

    fun setData(data: Data?) {
        this.data = data
    }

    class Data {
        @SerializedName("token_type")
        @Expose
        var tokenType: String? = null

        @SerializedName("token")
        @Expose
        var token: String? = null

        @SerializedName("user_id")
        @Expose
        var userId: Int? = null

    }
}