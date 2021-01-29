package com.casebeaumonde.activities.notifications.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class RemoveNotificationResponse {

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
        @SerializedName("message")
        @Expose
        var message: String? = null

        @SerializedName("notification")
        @Expose
        var notification: Notification? = null

        inner class Notification
    }
}