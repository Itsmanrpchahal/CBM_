package com.casebeaumonde.fragments.designers.Response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class DesignersResponse {
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
        @SerializedName("users")
        @Expose
        var users: List<User>? = null

        inner class User {
            //            @SerializedName("unread_notifications")
            //            @Expose
            //            private List<UnreadNotification> unreadNotifications = null;
            @SerializedName("id")
            @Expose
            var id: Int? = null

            @SerializedName("firstname")
            @Expose
            var firstname: String? = null

            @SerializedName("lastname")
            @Expose
            var lastname: String? = null

            @SerializedName("fullname")
            @Expose
            var fullname: String? = null

            @SerializedName("phone")
            @Expose
            var phone: String? = null

            @SerializedName("email")
            @Expose
            var email: String? = null

            @SerializedName("company")
            @Expose
            var company: String? = null

            @SerializedName("status")
            @Expose
            var status: String? = null

            @SerializedName("affiliate_id")
            @Expose
            var affiliateId: Int? = null

            @SerializedName("percent")
            @Expose
            var percent: String? = null

            @SerializedName("pending_balance")
            @Expose
            var pendingBalance: String? = null

            @SerializedName("available_balance")
            @Expose
            var availableBalance: String? = null

            @SerializedName("allow_hire")
            @Expose
            var allowHire: Int? = null

            @SerializedName("avatar")
            @Expose
            var avatar: String? = null

            @SerializedName("email_verified_at")
            @Expose
            var emailVerifiedAt: String? = null

            @SerializedName("created_at")
            @Expose
            var createdAt: String? = null

            @SerializedName("updated_at")
            @Expose
            var updatedAt: String? = null

            @SerializedName("deleted_at")
            @Expose
            var deletedAt: Any? = null

            //            public List<UnreadNotification> getUnreadNotifications() {
            @SerializedName("role")
            @Expose
            var role: String? = null
            //                return unreadNotifications;
            //            }
            //
            //            public void setUnreadNotifications(List<UnreadNotification> unreadNotifications) {
            //                this.unreadNotifications = unreadNotifications;
            //            }
        }

        inner class User_ {
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
            var phone: Any? = null

            @SerializedName("avatar")
            @Expose
            var avatar: Any? = null

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
            var fullname: Any? = null

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