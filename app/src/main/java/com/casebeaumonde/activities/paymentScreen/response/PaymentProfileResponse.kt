package com.casebeaumonde.activities.paymentScreen.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class PaymentProfileResponse {
    @SerializedName("code")
    @Expose
    private var code: String? = null

    @SerializedName("data")
    @Expose
    private var data: List<Datum?>? = null

    fun getCode(): String? {
        return code
    }

    fun setCode(code: String?) {
        this.code = code
    }

    fun getData(): List<Datum?>? {
        return data
    }

    fun setData(data: List<Datum?>?) {
        this.data = data
    }

    class Datum {
        @SerializedName("id")
        @Expose
        var id: Int? = null

        @SerializedName("payable_type")
        @Expose
        var payableType: String? = null

        @SerializedName("payable_id")
        @Expose
        var payableId: Int? = null

        @SerializedName("type")
        @Expose
        var type: String? = null

        @SerializedName("user_id")
        @Expose
        var userId: String? = null

        @SerializedName("customer_email")
        @Expose
        var customerEmail: Any? = null

        @SerializedName("last_4")
        @Expose
        var last4: String? = null

        @SerializedName("card_brand")
        @Expose
        var cardBrand: String? = null

        @SerializedName("operation")
        @Expose
        var operation: String? = null

        @SerializedName("amount")
        @Expose
        var amount: String? = null

        @SerializedName("fee")
        @Expose
        var fee: Any? = null

        @SerializedName("refund_amount")
        @Expose
        var refundAmount: String? = null

        @SerializedName("account_type")
        @Expose
        var accountType: Any? = null

        @SerializedName("account_id")
        @Expose
        var accountId: Any? = null

        @SerializedName("description")
        @Expose
        var description: Any? = null

        @SerializedName("transaction_id")
        @Expose
        var transactionId: String? = null

        @SerializedName("ref_transaction_id")
        @Expose
        var refTransactionId: Any? = null

        @SerializedName("auth_code")
        @Expose
        var authCode: Any? = null

        @SerializedName("transaction_status")
        @Expose
        var transactionStatus: String? = null

        @SerializedName("provider")
        @Expose
        var provider: String? = null

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