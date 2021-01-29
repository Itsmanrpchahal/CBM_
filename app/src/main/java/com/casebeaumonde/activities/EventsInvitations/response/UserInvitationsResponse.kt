package com.casebeaumonde.activities.EventsInvitations.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class UserInvitationsResponse {
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
        @SerializedName("events")
        @Expose
        var events: List<Event>? = null

        inner class Event {
            @SerializedName("id")
            @Expose
            var id: Int? = null

            @SerializedName("event_id")
            @Expose
            var eventId: Int? = null

            @SerializedName("user_id")
            @Expose
            var userId: Int? = null

            @SerializedName("user_type")
            @Expose
            var userType: String? = null

            @SerializedName("description")
            @Expose
            var description: String? = null

            @SerializedName("status")
            @Expose
            var status: String? = null

            @SerializedName("expires_at")
            @Expose
            var expiresAt: String? = null

            @SerializedName("created_at")
            @Expose
            var createdAt: String? = null

            @SerializedName("updated_at")
            @Expose
            var updatedAt: String? = null

            @SerializedName("deleted_at")
            @Expose
            var deletedAt: Any? = null

            @SerializedName("user")
            @Expose
            var user: User? = null

            @SerializedName("event")
            @Expose
            var event: Event_? = null

            inner class User {
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
                var fullname: Any? = null

                @SerializedName("phone")
                @Expose
                var phone: String? = null

                @SerializedName("email")
                @Expose
                var email: String? = null

                @SerializedName("company")
                @Expose
                var company: Any? = null

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

                @SerializedName("role")
                @Expose
                var role: String? = null
            }

            inner class Event_ {
                @SerializedName("id")
                @Expose
                var id: Int? = null

                @SerializedName("creator_id")
                @Expose
                var creatorId: Int? = null

                @SerializedName("user_id")
                @Expose
                var userId: Any? = null

                @SerializedName("title")
                @Expose
                var title: String? = null

                @SerializedName("description")
                @Expose
                var description: String? = null

                @SerializedName("image")
                @Expose
                var image: String? = null

                @SerializedName("from")
                @Expose
                var from: String? = null

                @SerializedName("to")
                @Expose
                var to: String? = null

                @SerializedName("type")
                @Expose
                var type: String? = null

                @SerializedName("status")
                @Expose
                var status: String? = null

                @SerializedName("created_at")
                @Expose
                var createdAt: String? = null

                @SerializedName("updated_at")
                @Expose
                var updatedAt: String? = null

                @SerializedName("deleted_at")
                @Expose
                var deletedAt: Any? = null

                @SerializedName("creator")
                @Expose
                var creator: Creator? = null

                @SerializedName("user")
                @Expose
                var user: Any? = null

                inner class Creator {
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

                    @SerializedName("role")
                    @Expose
                    var role: String? = null
                }
            }
        }
    }
}