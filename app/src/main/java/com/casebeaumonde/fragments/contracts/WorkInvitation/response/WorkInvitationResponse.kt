package com.casebeaumonde.activities.myContracts.tabs.WorkInvitation.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class WorkInvitationResponse {
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
        @SerializedName("user")
        @Expose
        var user: User? = null

        @SerializedName("current_following")
        @Expose
        var currentFollowing: Boolean? = null

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

            @SerializedName("received_invitations")
            @Expose
            var receivedInvitations: List<ReceivedInvitation>? = null

            @SerializedName("sent_invitations")
            @Expose
            var sentInvitations: List<SentInvitation>? = null

            @SerializedName("role")
            @Expose
            var role: String? = null

            @SerializedName("unread_notifications")
            @Expose
            var unreadNotifications: List<Any>? = null

            @SerializedName("followers")
            @Expose
            var followers: List<Follower>? = null

            inner class ReceivedInvitation {
                @SerializedName("id")
                @Expose
                var id: Int? = null

                @SerializedName("user_id")
                @Expose
                var userId: Int? = null

                @SerializedName("designer_id")
                @Expose
                var designerId: Int? = null

                @SerializedName("gig_id")
                @Expose
                var gigId: Int? = null

                @SerializedName("description")
                @Expose
                var description: String? = null

                @SerializedName("budget")
                @Expose
                var budget: Int? = null

                @SerializedName("status")
                @Expose
                var status: String? = null

                @SerializedName("created_at")
                @Expose
                var createdAt: String? = null

                @SerializedName("updated_at")
                @Expose
                var updatedAt: String? = null

                @SerializedName("gig")
                @Expose
                var gig: Gig? = null

                @SerializedName("designer")
                @Expose
                var designer: Designer? = null

                @SerializedName("user")
                @Expose
                var user: User_? = null

                inner class Gig {
                    @SerializedName("id")
                    @Expose
                    var id: Int? = null

                    @SerializedName("user_id")
                    @Expose
                    var userId: Int? = null

                    @SerializedName("title")
                    @Expose
                    var title: String? = null

                    @SerializedName("description")
                    @Expose
                    var description: String? = null

                    @SerializedName("hours")
                    @Expose
                    var hours: String? = null

                    @SerializedName("rate")
                    @Expose
                    var rate: Int? = null

                    @SerializedName("rate_type")
                    @Expose
                    var rateType: String? = null

                    @SerializedName("status")
                    @Expose
                    var status: String? = null

                    @SerializedName("created_at")
                    @Expose
                    var createdAt: String? = null

                    @SerializedName("updated_at")
                    @Expose
                    var updatedAt: String? = null
                }

                inner class Designer {
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

                inner class User_ {
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
            }

            inner class SentInvitation {
                @SerializedName("id")
                @Expose
                var id: Int? = null

                @SerializedName("user_id")
                @Expose
                var userId: Int? = null

                @SerializedName("designer_id")
                @Expose
                var designerId: Int? = null

                @SerializedName("gig_id")
                @Expose
                var gigId: Int? = null

                @SerializedName("description")
                @Expose
                var description: String? = null

                @SerializedName("budget")
                @Expose
                var budget: Int? = null

                @SerializedName("status")
                @Expose
                var status: String? = null

                @SerializedName("created_at")
                @Expose
                var createdAt: String? = null

                @SerializedName("updated_at")
                @Expose
                var updatedAt: String? = null

                @SerializedName("user")
                @Expose
                var user: User__? = null

                @SerializedName("designer")
                @Expose
                var designer: Designer_? = null

                @SerializedName("gig")
                @Expose
                var gig: Gig_? = null

                inner class User__ {
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

                    @SerializedName("unread_notifications")
                    @Expose
                    var unreadNotifications: List<Any>? = null
                }

                inner class Designer_ {
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

                inner class Gig_ {
                    @SerializedName("id")
                    @Expose
                    var id: Int? = null

                    @SerializedName("user_id")
                    @Expose
                    var userId: Int? = null

                    @SerializedName("title")
                    @Expose
                    var title: String? = null

                    @SerializedName("description")
                    @Expose
                    var description: String? = null

                    @SerializedName("hours")
                    @Expose
                    var hours: String? = null

                    @SerializedName("rate")
                    @Expose
                    var rate: Int? = null

                    @SerializedName("rate_type")
                    @Expose
                    var rateType: String? = null

                    @SerializedName("status")
                    @Expose
                    var status: String? = null

                    @SerializedName("created_at")
                    @Expose
                    var createdAt: String? = null

                    @SerializedName("updated_at")
                    @Expose
                    var updatedAt: String? = null
                }
            }

            inner class Follower {
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

                @SerializedName("pivot")
                @Expose
                var pivot: Pivot? = null

                @SerializedName("unread_notifications")
                @Expose
                var unreadNotifications: List<Any>? = null

                inner class Pivot {
                    @SerializedName("leader_id")
                    @Expose
                    var leaderId: Int? = null

                    @SerializedName("follower_id")
                    @Expose
                    var followerId: Int? = null

                    @SerializedName("created_at")
                    @Expose
                    var createdAt: String? = null

                    @SerializedName("updated_at")
                    @Expose
                    var updatedAt: String? = null
                }
            }
        }
    }
}