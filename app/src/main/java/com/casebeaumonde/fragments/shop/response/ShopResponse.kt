package com.casebeaumonde.fragments.shop.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ShopResponse {
    @SerializedName("code")
    @Expose
    var code: String? = null

    @SerializedName("data")
    @Expose
    var data: List<Datum>? = null

    inner class Datum {
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

        @SerializedName("chat_invitation")
        @Expose
        var chatInvitation: String? = null

        @SerializedName("avatar")
        @Expose
        var avatar: Any? = null

        @SerializedName("email_verified_at")
        @Expose
        var emailVerifiedAt: String? = null

        @SerializedName("paypal_email")
        @Expose
        var paypalEmail: Any? = null

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
