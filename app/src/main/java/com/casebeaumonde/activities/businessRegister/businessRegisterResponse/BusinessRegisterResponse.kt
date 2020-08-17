package com.casebeaumonde.activities.businessRegister.businessRegisterResponse

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class BusinessRegisterResponse {
    @SerializedName("code")
    @Expose
    var code: Int? = null

    @SerializedName("message")
    @Expose
    var message: String? = null
}