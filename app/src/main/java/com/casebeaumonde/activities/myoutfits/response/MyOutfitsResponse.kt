package com.casebeaumonde.activities.myoutfits.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class MyOutfitsResponse {
    @SerializedName("code")
    @Expose
    var code: String? = null

    @SerializedName("data")
    @Expose
    var data: Data? = null

    inner class Data {
        @SerializedName("outfits")
        @Expose
        var outfits: List<Outfit>? = null

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