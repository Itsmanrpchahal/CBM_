package com.casebeaumonde.fragments.pricing.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ChangePlanResponse {

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
        @SerializedName("message")
        @Expose
        var message: String? = null

        @SerializedName("subscription")
        @Expose
        var subscription: Subscription? = null

        inner class Subscription {
            @SerializedName("user_id")
            @Expose
            var userId: Int? = null

            @SerializedName("customer_plan_id")
            @Expose
            var customerPlanId: Int? = null

            @SerializedName("prev_customer_plan_id")
            @Expose
            var prevCustomerPlanId: Int? = null

            @SerializedName("active_at")
            @Expose
            var activeAt: String? = null

            @SerializedName("monthly_price")
            @Expose
            var monthlyPrice: String? = null

            @SerializedName("annual_charge")
            @Expose
            private var annualCharge: String? = null

            @SerializedName("yearly_saving_percent")
            @Expose
            var yearlySavingPercent: String? = null

            @SerializedName("amount")
            @Expose
            var amount: String? = null

            @SerializedName("paid_annual_charge")
            @Expose
            var paidAnnualCharge: Boolean? = null

            @SerializedName("expires_at")
            @Expose
            var expiresAt: String? = null

            @SerializedName("trial_started_at")
            @Expose
            var trialStartedAt: String? = null

            @SerializedName("trial_end_at")
            @Expose
            var trialEndAt: String? = null

            @SerializedName("status")
            @Expose
            var status: String? = null

            @SerializedName("next_payment_date")
            @Expose
            var nextPaymentDate: String? = null

            @SerializedName("billing_day")
            @Expose
            var billingDay: String? = null

            @SerializedName("updated_at")
            @Expose
            var updatedAt: String? = null

            @SerializedName("created_at")
            @Expose
            var createdAt: String? = null

            @SerializedName("id")
            @Expose
            var id: Int? = null

            @SerializedName("plan")
            @Expose
            var plan: Plan? = null

            fun getAnnualCharge(): Any? {
                return annualCharge
            }

            fun setAnnualCharge(annualCharge: String?) {
                this.annualCharge = annualCharge
            }

            inner class Plan {
                @SerializedName("id")
                @Expose
                var id: Int? = null

                @SerializedName("name")
                @Expose
                var name: String? = null

                @SerializedName("description")
                @Expose
                var description: String? = null

                @SerializedName("monthly_price")
                @Expose
                var monthlyPrice: String? = null

                @SerializedName("yearly_price")
                @Expose
                var yearlyPrice: String? = null

                @SerializedName("yearly_saving_percent")
                @Expose
                var yearlySavingPercent: String? = null

                @SerializedName("benefits")
                @Expose
                var benefits: Any? = null

                @SerializedName("trial_days")
                @Expose
                var trialDays: String? = null

                @SerializedName("interval")
                @Expose
                var interval: String? = null

                @SerializedName("interval_count")
                @Expose
                var intervalCount: String? = null

                @SerializedName("slug")
                @Expose
                var slug: String? = null

                @SerializedName("match_profile_with_users")
                @Expose
                var matchProfileWithUsers: Int? = null

                @SerializedName("customer_ratings")
                @Expose
                var customerRatings: Int? = null

                @SerializedName("chat_support")
                @Expose
                var chatSupport: Int? = null

                @SerializedName("phone_support")
                @Expose
                var phoneSupport: Int? = null

                @SerializedName("designated_account_manager")
                @Expose
                var designatedAccountManager: Int? = null

                @SerializedName("number_of_closets")
                @Expose
                var numberOfClosets: Any? = null

                @SerializedName("stylist_collaboration")
                @Expose
                var stylistCollaboration: Int? = null

                @SerializedName("allowed_storage_items")
                @Expose
                var allowedStorageItems: Int? = null

                @SerializedName("private_closets")
                @Expose
                var privateClosets: Int? = null

                @SerializedName("paired_with_stylists")
                @Expose
                var pairedWithStylists: Int? = null

                @SerializedName("styling_packages")
                @Expose
                var stylingPackages: Int? = null

                @SerializedName("invitations")
                @Expose
                var invitations: Int? = null

                @SerializedName("status")
                @Expose
                var status: String? = null

                @SerializedName("created_at")
                @Expose
                var createdAt: String? = null

                @SerializedName("updated_at")
                @Expose
                var updatedAt: String? = null

                @SerializedName("annual_charge")
                @Expose
                var annualCharge: Any? = null
            }
        }
    }
}