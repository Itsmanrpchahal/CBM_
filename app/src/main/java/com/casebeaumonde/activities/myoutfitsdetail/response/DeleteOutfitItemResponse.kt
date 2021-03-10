package com.casebeaumonde.activities.myoutfitsdetail.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class DeleteOutfitItemResponse {
    @SerializedName("code")
    @Expose
    var code: String? = null

    @SerializedName("message")
    @Expose
    var message: String? = null
}
