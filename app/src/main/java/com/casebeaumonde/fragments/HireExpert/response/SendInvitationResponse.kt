package com.casebeaumonde.fragments.HireExpert.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class SendInvitationResponse {

    @SerializedName("code")
    @Expose
    private var code: String? = null

    fun getCode(): String? {
        return code
    }

    fun setCode(code: String?) {
        this.code = code
    }

}