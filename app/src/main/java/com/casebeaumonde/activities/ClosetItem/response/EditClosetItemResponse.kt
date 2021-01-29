package com.casebeaumonde.activities.ClosetItem.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class EditClosetItemResponse {

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
        @SerializedName("closet_item")
        @Expose
        var closetItem: ClosetItem? = null

        inner class ClosetItem {
            @SerializedName("id")
            @Expose
            var id: Int? = null

            @SerializedName("parent_id")
            @Expose
            var parentId: Int? = null

            @SerializedName("creator_id")
            @Expose
            var creatorId: Int? = null

            @SerializedName("fashionable_type")
            @Expose
            var fashionableType: String? = null

            @SerializedName("fashionable_id")
            @Expose
            var fashionableId: Int? = null

            @SerializedName("title")
            @Expose
            var title: String? = null

            @SerializedName("description")
            @Expose
            var description: String? = null

            @SerializedName("category_id")
            @Expose
            var categoryId: String? = null

            @SerializedName("size")
            @Expose
            var size: Int? = null

            @SerializedName("color")
            @Expose
            var color: Int? = null

            @SerializedName("brand")
            @Expose
            var brand: Int? = null

            @SerializedName("price")
            @Expose
            var price: String? = null

            @SerializedName("picture")
            @Expose
            var picture: String? = null

            @SerializedName("external_url")
            @Expose
            var externalUrl: Any? = null

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
        }
    }
}