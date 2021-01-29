package com.casebeaumonde.fragments.allClosets.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class CreateClosetResponse {

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
        @SerializedName("closet")
        @Expose
        var closet: Closet? = null

        @SerializedName("file_path")
        @Expose
        var filePath: String? = null
    }

    class Closet {
        @SerializedName("title")
        @Expose
        var title: String? = null

        @SerializedName("user_id")
        @Expose
        var userId: Int? = null

        @SerializedName("status")
        @Expose
        var status: String? = null

        @SerializedName("creator_id")
        @Expose
        var creatorId: Int? = null

        @SerializedName("description")
        @Expose
        var description: String? = null

        @SerializedName("visibility")
        @Expose
        var visibility: String? = null

        @SerializedName("image")
        @Expose
        var image: String? = null

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