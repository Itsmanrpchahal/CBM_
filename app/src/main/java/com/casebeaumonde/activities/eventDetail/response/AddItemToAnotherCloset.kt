package com.casebeaumonde.activities.eventDetail.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class AddItemToAnotherCloset {

    @SerializedName("code")
    @Expose
    private var code: String? = null

    @SerializedName("messsage")
    @Expose
    private var messsage: String? = null

    fun getCode(): String? {
        return code
    }

    fun setCode(code: String?) {
        this.code = code
    }

    fun getMesssage(): String? {
        return messsage
    }

    fun setMesssage(messsage: String?) {
        this.messsage = messsage
    }

}