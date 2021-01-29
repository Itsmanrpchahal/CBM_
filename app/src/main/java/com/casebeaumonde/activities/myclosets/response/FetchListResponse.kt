package com.casebeaumonde.activities.myclosets.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class FetchListResponse {

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
        @SerializedName("categories")
        @Expose
        var categories: List<Category>? = null

        @SerializedName("brands")
        @Expose
        var brands: List<Brand>? = null

        @SerializedName("sizes")
        @Expose
        var sizes: List<Size>? = null

        @SerializedName("colors")
        @Expose
        var colors: List<Color>? = null

        @SerializedName("outfits")
        @Expose
        var outfits: List<Outfit>? = null

        inner class Category {
            @SerializedName("id")
            @Expose
            var id: Int? = null

            @SerializedName("parent_id")
            @Expose
            var parentId: Int? = null

            @SerializedName("name")
            @Expose
            var name: String? = null

            @SerializedName("description")
            @Expose
            var description: String? = null

            @SerializedName("active")
            @Expose
            var active: Int? = null

            @SerializedName("created_at")
            @Expose
            var createdAt: String? = null

            @SerializedName("updated_at")
            @Expose
            var updatedAt: String? = null

            @SerializedName("deleted_at")
            @Expose
            var deletedAt: Any? = null
        }

        inner class Brand {
            @SerializedName("id")
            @Expose
            var id: Int? = null

            @SerializedName("user_id")
            @Expose
            var userId: Any? = null

            @SerializedName("name")
            @Expose
            var name: String? = null

            @SerializedName("description")
            @Expose
            var description: Any? = null

            @SerializedName("status")
            @Expose
            var status: String? = null

            @SerializedName("logo")
            @Expose
            var logo: Any? = null

            @SerializedName("created_at")
            @Expose
            var createdAt: String? = null

            @SerializedName("updated_at")
            @Expose
            var updatedAt: String? = null
        }

        inner class Size {
            @SerializedName("id")
            @Expose
            var id: Int? = null

            @SerializedName("name")
            @Expose
            var name: String? = null

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

        inner class Color {
            @SerializedName("id")
            @Expose
            var id: Int? = null

            @SerializedName("user_id")
            @Expose
            var userId: Any? = null

            @SerializedName("name")
            @Expose
            var name: String? = null

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

        inner class Outfit {
            @SerializedName("id")
            @Expose
            var id: Int? = null

            @SerializedName("creator_id")
            @Expose
            var creatorId: Int? = null

            @SerializedName("title")
            @Expose
            var title: String? = null

            @SerializedName("description")
            @Expose
            var description: Any? = null

            @SerializedName("status")
            @Expose
            var status: String? = null

            @SerializedName("image")
            @Expose
            var image: Any? = null

            @SerializedName("created_at")
            @Expose
            var createdAt: String? = null

            @SerializedName("updated_at")
            @Expose
            var updatedAt: String? = null

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
            }
        }
    }
}