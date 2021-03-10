package com.casebeaumonde.activities.myoutfits.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class NewOutfitResponse {
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
            @SerializedName("creator_id")
            @Expose
            var creatorId: Int? = null

            @SerializedName("title")
            @Expose
            var title: String? = null

            @SerializedName("description")
            @Expose
            var description: String? = null

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