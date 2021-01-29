package com.casebeaumonde.fragments.pricing.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class PricingResponse {
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
        @SerializedName("customer_plans")
        @Expose
        var customerPlans: List<CustomerPlan>? = null

        @SerializedName("business_plans")
        @Expose
        var businessPlans: List<BusinessPlan>? = null

        @SerializedName("business_roles")
        @Expose
        var businessRoles: List<BusinessRole>? = null

        @SerializedName("customer_roles")
        @Expose
        var customerRoles: List<CustomerRole>? = null

        inner class CustomerPlan {
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
            var monthlyPrice: Double? = null

            @SerializedName("yearly_price")
            @Expose
            var yearlyPrice: Double? = null

            @SerializedName("yearly_saving_percent")
            @Expose
            var yearlySavingPercent: Int? = null

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

            @SerializedName("plan_features")
            @Expose
            var planFeatures: List<PlanFeature>? = null
        }

        inner class BusinessPlan {
            @SerializedName("id")
            @Expose
            var id: Int? = null

            @SerializedName("name")
            @Expose
            var name: String? = null

            @SerializedName("description")
            @Expose
            var description: String? = null

            @SerializedName("slug")
            @Expose
            var slug: String? = null

            @SerializedName("commission_percentage")
            @Expose
            var commissionPercentage: Int? = null

            @SerializedName("interval")
            @Expose
            var interval: String? = null

            @SerializedName("interval_count")
            @Expose
            var intervalCount: Int? = null

            @SerializedName("monthly_price")
            @Expose
            var monthlyPrice: Int? = null

            @SerializedName("number_of_users")
            @Expose
            var numberOfUsers: Int? = null

            @SerializedName("yearly_price")
            @Expose
            var yearlyPrice: Int? = null

            @SerializedName("annual_charge")
            @Expose
            var annualCharge: String? = null

            @SerializedName("yearly_saving_percent")
            @Expose
            var yearlySavingPercent: Int? = null

            @SerializedName("trial_days")
            @Expose
            var trialDays: String? = null

            @SerializedName("benefits")
            @Expose
            var benefits: String? = null

            @SerializedName("allowed_sale_packages")
            @Expose
            var allowedSalePackages: Int? = null

            @SerializedName("match_profile_with_users_interval")
            @Expose
            var matchProfileWithUsersInterval: String? = null

            @SerializedName("match_profile_with_users_interval_count")
            @Expose
            var matchProfileWithUsersIntervalCount: Int? = null

            @SerializedName("exclusive_affiliate_programs")
            @Expose
            var exclusiveAffiliatePrograms: Int? = null

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

            @SerializedName("featured_on_cbm")
            @Expose
            var featuredOnCbm: String? = null

            @SerializedName("weekly_picks_featured")
            @Expose
            var weeklyPicksFeatured: Int? = null

            @SerializedName("preferred_placement_search")
            @Expose
            var preferredPlacementSearch: Int? = null

            @SerializedName("suggest_closets")
            @Expose
            var suggestClosets: Int? = null

            @SerializedName("cbm_style_hours_interval")
            @Expose
            var cbmStyleHoursInterval: String? = null

            @SerializedName("cbm_style_hours_interval_count")
            @Expose
            var cbmStyleHoursIntervalCount: Int? = null

            @SerializedName("affiliate_marketing_help")
            @Expose
            var affiliateMarketingHelp: Int? = null

            @SerializedName("commission_on_affiliate")
            @Expose
            var commissionOnAffiliate: Any? = null

            @SerializedName("export_data")
            @Expose
            var exportData: Int? = null

            @SerializedName("subscription_report")
            @Expose
            var subscriptionReport: Int? = null

            @SerializedName("mrr_report")
            @Expose
            var mrrReport: Int? = null

            @SerializedName("create_events_for_sale")
            @Expose
            var createEventsForSale: Int? = null

            @SerializedName("create_live_store_events")
            @Expose
            var createLiveStoreEvents: Int? = null

            @SerializedName("event_partneship")
            @Expose
            var eventPartneship: Int? = null

            @SerializedName("created_benefits")
            @Expose
            var createdBenefits: Any? = null

            @SerializedName("status")
            @Expose
            var status: String? = null

            @SerializedName("created_at")
            @Expose
            var createdAt: String? = null

            @SerializedName("updated_at")
            @Expose
            var updatedAt: String? = null

            @SerializedName("plan_features")
            @Expose
            var planFeatures: List<PlanFeature_>? = null

            inner class PlanFeature_ {
                @SerializedName("id")
                @Expose
                var id: Int? = null

                @SerializedName("featureable_id")
                @Expose
                var featureableId: Int? = null

                @SerializedName("featureable_type")
                @Expose
                var featureableType: String? = null

                @SerializedName("feature")
                @Expose
                var feature: String? = null

                @SerializedName("status")
                @Expose
                var status: String? = null

                @SerializedName("created_at")
                @Expose
                var createdAt: String? = null

                @SerializedName("updated_at")
                @Expose
                var updatedAt: String? = null
            }
        }

        inner class BusinessRole {
            @SerializedName("id")
            @Expose
            var id: Int? = null

            @SerializedName("name")
            @Expose
            var name: String? = null

            @SerializedName("guard_name")
            @Expose
            var guardName: String? = null

            @SerializedName("url")
            @Expose
            var url: String? = null

            @SerializedName("created_at")
            @Expose
            var createdAt: String? = null

            @SerializedName("updated_at")
            @Expose
            var updatedAt: String? = null
        }

        inner class CustomerRole {
            @SerializedName("id")
            @Expose
            var id: Int? = null

            @SerializedName("name")
            @Expose
            var name: String? = null

            @SerializedName("guard_name")
            @Expose
            var guardName: String? = null

            @SerializedName("url")
            @Expose
            var url: String? = null

            @SerializedName("created_at")
            @Expose
            var createdAt: String? = null

            @SerializedName("updated_at")
            @Expose
            var updatedAt: String? = null
        }

        inner class PlanFeature {
            @SerializedName("id")
            @Expose
            var id: Int? = null

            @SerializedName("featureable_id")
            @Expose
            var featureableId: Int? = null

            @SerializedName("featureable_type")
            @Expose
            var featureableType: String? = null

            @SerializedName("feature")
            @Expose
            var feature: String? = null

            @SerializedName("status")
            @Expose
            var status: String? = null

            @SerializedName("created_at")
            @Expose
            var createdAt: String? = null

            @SerializedName("updated_at")
            @Expose
            var updatedAt: String? = null
        }
    }
}