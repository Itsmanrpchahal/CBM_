package com.casebeaumonde.fragments.profile.profileResponse

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class PaymentMethodResponse {

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
        @SerializedName("id")
        @Expose
        var id: Int? = null

        @SerializedName("user_id")
        @Expose
        var userId: Int? = null

        @SerializedName("payment_processor")
        @Expose
        var paymentProcessor: String? = null

        @SerializedName("customer_profile_id")
        @Expose
        var customerProfileId: String? = null

        @SerializedName("created_at")
        @Expose
        var createdAt: String? = null

        @SerializedName("updated_at")
        @Expose
        var updatedAt: String? = null

        @SerializedName("deleted_at")
        @Expose
        var deletedAt: Any? = null

        @SerializedName("payment_profiles")
        @Expose
        var paymentProfiles: List<PaymentProfile>? = null

        inner class PaymentProfile {
            @SerializedName("id")
            @Expose
            var id: Int? = null

            @SerializedName("customer_profile_id")
            @Expose
            var customerProfileId: Int? = null

            @SerializedName("payment_profile_id")
            @Expose
            var paymentProfileId: String? = null

            @SerializedName("payment_method_type")
            @Expose
            var paymentMethodType: String? = null

            @SerializedName("last_numbers")
            @Expose
            var lastNumbers: String? = null

            @SerializedName("expiration_date")
            @Expose
            var expirationDate: String? = null

            @SerializedName("brand")
            @Expose
            var brand: String? = null

            @SerializedName("default")
            @Expose
            var default: Int? = null

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
}