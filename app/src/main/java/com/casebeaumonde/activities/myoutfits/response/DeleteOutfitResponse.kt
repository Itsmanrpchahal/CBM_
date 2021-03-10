package com.casebeaumonde.activities.myoutfits.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class DeleteOutfitResponse {
    @SerializedName("code")
    @Expose
    var code: String? = null

    @SerializedName("message")
    @Expose
    var message: String? = null
}
