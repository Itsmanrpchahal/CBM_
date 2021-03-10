package com.casebeaumonde.activities.myoutfitsdetail.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class MyOutfitsDetailResponse {
    @SerializedName("code")
    @Expose
    var code: String? = null

    @SerializedName("data")
    @Expose
    var data: Data? = null

    inner class Data {
        @SerializedName("outfit")
        @Expose
        var outfit: Outfit? = null

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

            @SerializedName("items")
            @Expose
            var items: List<Item>? = null

            @SerializedName("creator")
            @Expose
            var creator: Creator_? = null

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
                var hearts: List<Any>? = null



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

                    @SerializedName("avatar")
                    @Expose
                    var avatar: String? = null

                    @SerializedName("role")
                    @Expose
                    var role: String? = null
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
            }
        }
    }
}
