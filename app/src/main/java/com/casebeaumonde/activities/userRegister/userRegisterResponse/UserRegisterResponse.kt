package com.casebeaumonde.activities.userRegister.userRegisterResponse

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


class UserRegisterResponse {
    @SerializedName("code")
    @Expose
    var code: Int? = null

    @SerializedName("message")
    @Expose
    var message: String? = null

}