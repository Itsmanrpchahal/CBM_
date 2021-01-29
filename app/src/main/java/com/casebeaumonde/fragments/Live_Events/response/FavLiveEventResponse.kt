package com.casebeaumonde.fragments.Live_Events.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class FavLiveEventResponse {

    @SerializedName("code")
    @Expose
    private var code: String? = null

    @SerializedName("message")
    @Expose
    private var message: String? = null

    fun getCode(): String? {
        return code
    }

    fun setCode(code: String?) {
        this.code = code
    }

    fun getMessage(): String? {
        return message
    }

    fun setMessage(message: String?) {
        this.message = message
    }

}