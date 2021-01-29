package com.casebeaumonde.activities.eventDetail.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class EventDetailResponse {

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
        var events: Events? = null

        @SerializedName("closet")
        @Expose
        var closet: List<Closet>? = null

        inner class Events {
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

            @SerializedName("items")
            @Expose
            var items: List<Item>? = null

            @SerializedName("creator")
            @Expose
            var creator: Creator_? = null

            @SerializedName("followers")
            @Expose
            var followers: List<Any>? = null

            @SerializedName("user")
            @Expose
            var user: Any? = null

            inner class Item {
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
                var categoryId: Int? = null

                @SerializedName("size")
                @Expose
                var size: Size? = null

                @SerializedName("color")
                @Expose
                var color: Color? = null

                @SerializedName("brand")
                @Expose
                var brand: Brand? = null

                @SerializedName("price")
                @Expose
                var price: Int? = null

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

                @SerializedName("category")
                @Expose
                var category: Category? = null

                @SerializedName("hearts")
                @Expose
                var hearts: List<Heart>? = null
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

                @SerializedName("unread_notifications")
                @Expose
                var unreadNotifications: List<Any>? = null
            }

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

            inner class Heart {
                @SerializedName("id")
                @Expose
                var id: Int? = null

                @SerializedName("user_id")
                @Expose
                var userId: Int? = null

                @SerializedName("heartable_type")
                @Expose
                var heartableType: String? = null

                @SerializedName("heartable_id")
                @Expose
                var heartableId: Int? = null

                @SerializedName("created_at")
                @Expose
                var createdAt: String? = null

                @SerializedName("updated_at")
                @Expose
                var updatedAt: String? = null
            }
        }

        inner class Closet {
            @SerializedName("id")
            @Expose
            var id: Int? = null

            @SerializedName("title")
            @Expose
            var title: String? = null

            @SerializedName("creator")
            @Expose
            var creator: Any? = null
        }

        inner class Creator_ {
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

            @SerializedName("avatar")
            @Expose
            var avatar: String? = null

            @SerializedName("role")
            @Expose
            var role: String? = null

            @SerializedName("unread_notifications")
            @Expose
            var unreadNotifications: List<Any>? = null
        }
    }
}