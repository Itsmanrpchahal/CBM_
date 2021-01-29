package com.casebeaumonde.activities.ClosetItem.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class AddItemToClosetResponse {

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
            @SerializedName("creator_id")
            @Expose
            var creatorId: Int? = null

            @SerializedName("title")
            @Expose
            var title: String? = null

            @SerializedName("category_id")
            @Expose
            var categoryId: String? = null

            @SerializedName("brand")
            @Expose
            var brand: Int? = null

            @SerializedName("color")
            @Expose
            var color: Int? = null

            @SerializedName("size")
            @Expose
            var size: Int? = null

            @SerializedName("status")
            @Expose
            var status: String? = null

            @SerializedName("description")
            @Expose
            var description: String? = null

            @SerializedName("picture")
            @Expose
            var picture: String? = null

            @SerializedName("fashionable_id")
            @Expose
            var fashionableId: Int? = null

            @SerializedName("fashionable_type")
            @Expose
            var fashionableType: String? = null

            @SerializedName("updated_at")
            @Expose
            var updatedAt: String? = null

            @SerializedName("created_at")
            @Expose
            var createdAt: String? = null

            @SerializedName("id")
            @Expose
            var id: Int? = null
        }
    }
}