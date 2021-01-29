package com.casebeaumonde

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class UpdateProfilePicResponse {

    @SerializedName("code")
    @Expose
    private var code: Int? = null

    @SerializedName("data")
    @Expose
    private var data: Data? = null

    fun getCode(): Int? {
        return code
    }

    fun setCode(code: Int?) {
        this.code = code
    }

    fun getData(): Data? {
        return data
    }

    fun setData(data: Data?) {
        this.data = data
    }

    class Data {
        @SerializedName("image")
        @Expose
        var image: String? = null
    }
}