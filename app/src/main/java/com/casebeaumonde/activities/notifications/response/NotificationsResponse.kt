package com.casebeaumonde.activities.notifications.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class NotificationsResponse {
    @SerializedName("code")
    @Expose
    private var code: String? = null

    @SerializedName("data")
    @Expose
    private var data: Data? = null

    fun getCode(): String? {
        return code
    }

    fun setCode(code: String?) {
        this.code = code
    }

    fun getData(): Data? {
        return data
    }

    fun setData(data: Data?) {
        this.data = data
    }

    class Data {
        @SerializedName("notification")
        @Expose
        var notification: List<Notification>? = null

        inner class Notification {
            @SerializedName("id")
            @Expose
            var id: String? = null

            @SerializedName("type")
            @Expose
            var type: String? = null

            @SerializedName("notifiable_type")
            @Expose
            var notifiableType: String? = null

            @SerializedName("notifiable_id")
            @Expose
            var notifiableId: Int? = null

            @SerializedName("data")
            @Expose
            var data: Data_? = null

            @SerializedName("read_at")
            @Expose
            var readAt: String? = null

            @SerializedName("opened_at")
            @Expose
            var openedAt: Any? = null

            @SerializedName("created_at")
            @Expose
            var createdAt: String? = null

            @SerializedName("updated_at")
            @Expose
            var updatedAt: String? = null

            @SerializedName("message")
            @Expose
            var message: String? = null

            inner class Data_ {
                @SerializedName("type")
                @Expose
                var type: String? = null

                @SerializedName("user")
                @Expose
                var user: User? = null

                @SerializedName("created_at")
                @Expose
                var createdAt: String? = null

                @SerializedName("open_in_chat")
                @Expose
                var openInChat: String? = null

                inner class User {
                    @SerializedName("id")
                    @Expose
                    var id: Int? = null

                    @SerializedName("role")
                    @Expose
                    var role: String? = null

                    @SerializedName("email")
                    @Expose
                    var email: String? = null

                    @SerializedName("phone")
                    @Expose
                    var phone: String? = null

                    @SerializedName("avatar")
                    @Expose
                    var avatar: String? = null

                    @SerializedName("status")
                    @Expose
                    var status: String? = null

                    @SerializedName("company")
                    @Expose
                    var company: Any? = null

                    @SerializedName("percent")
                    @Expose
                    var percent: String? = null

                    @SerializedName("fullname")
                    @Expose
                    var fullname: String? = null

                    @SerializedName("lastname")
                    @Expose
                    var lastname: String? = null

                    @SerializedName("firstname")
                    @Expose
                    var firstname: String? = null

                    @SerializedName("allow_hire")
                    @Expose
                    var allowHire: Int? = null

                    @SerializedName("created_at")
                    @Expose
                    var createdAt: String? = null

                    @SerializedName("deleted_at")
                    @Expose
                    var deletedAt: Any? = null

                    @SerializedName("updated_at")
                    @Expose
                    var updatedAt: String? = null

                    @SerializedName("affiliate_id")
                    @Expose
                    var affiliateId: Int? = null

                    @SerializedName("pending_balance")
                    @Expose
                    var pendingBalance: String? = null

                    @SerializedName("available_balance")
                    @Expose
                    var availableBalance: String? = null

                    @SerializedName("email_verified_at")
                    @Expose
                    var emailVerifiedAt: String? = null

                    @SerializedName("unread_notifications")
                    @Expose
                    var unreadNotifications: List<Any>? = null
                }
            }
        }
    }
}