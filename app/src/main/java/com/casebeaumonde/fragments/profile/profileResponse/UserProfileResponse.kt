package com.casebeaumonde.fragments.profile.profileResponse

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class UserProfileResponse {

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
        @SerializedName("current_following")
        @Expose
        var currentFollowing: Boolean? = null

        @SerializedName("fashionables")
        @Expose
        var fashionables: List<Fashionable>? = null

        @SerializedName("user")
        @Expose
        var user: User? = null

        @SerializedName("file_path")
        @Expose
        var filePath: String? = null

        inner class Fashionable {
            @SerializedName("id")
            @Expose
            var id: Int? = null

            @SerializedName("user_id")
            @Expose
            var userId: Int? = null

            @SerializedName("creator_id")
            @Expose
            var creatorId: Int? = null

            @SerializedName("title")
            @Expose
            var title: String? = null

            @SerializedName("description")
            @Expose
            var description: String? = null

            @SerializedName("status")
            @Expose
            var status: String? = null

            @SerializedName("visibility")
            @Expose
            var visibility: String? = null

            @SerializedName("def")
            @Expose
            var def: Int? = null

            @SerializedName("image")
            @Expose
            var image: String? = null

            @SerializedName("created_at")
            @Expose
            var createdAt: String? = null

            @SerializedName("updated_at")
            @Expose
            var updatedAt: String? = null

            @SerializedName("type")
            @Expose
            var type: String? = null

            @SerializedName("creator")
            @Expose
            var creator: Creator? = null

            inner class Creator {
                @SerializedName("id")
                @Expose
                var id: Int? = null

                @SerializedName("firstname")
                @Expose
                var firstname: String? = null

                @SerializedName("lastname")
                @Expose
                var lastname: String? = null

                @SerializedName("fullname")
                @Expose
                var fullname: String? = null

                @SerializedName("phone")
                @Expose
                var phone: String? = null

                @SerializedName("email")
                @Expose
                var email: String? = null

                @SerializedName("company")
                @Expose
                var company: String? = null

                @SerializedName("status")
                @Expose
                var status: String? = null

                @SerializedName("affiliate_id")
                @Expose
                var affiliateId: Int? = null

                @SerializedName("percent")
                @Expose
                var percent: String? = null

                @SerializedName("pending_balance")
                @Expose
                var pendingBalance: String? = null

                @SerializedName("available_balance")
                @Expose
                var availableBalance: String? = null

                @SerializedName("allow_hire")
                @Expose
                var allowHire: Int? = null

                @SerializedName("avatar")
                @Expose
                var avatar: String? = null

                @SerializedName("email_verified_at")
                @Expose
                var emailVerifiedAt: String? = null

                @SerializedName("created_at")
                @Expose
                var createdAt: String? = null

                @SerializedName("updated_at")
                @Expose
                var updatedAt: String? = null

                @SerializedName("deleted_at")
                @Expose
                var deletedAt: Any? = null

                @SerializedName("role")
                @Expose
                var role: String? = null

                @SerializedName("unread_notifications")
                @Expose
                var unreadNotifications: List<Any>? = null
            }
        }

        inner class User {
            @SerializedName("id")
            @Expose
            var id: Int? = null

            @SerializedName("firstname")
            @Expose
            var firstname: String? = null

            @SerializedName("lastname")
            @Expose
            var lastname: String? = null

            @SerializedName("fullname")
            @Expose
            var fullname: String? = null

            @SerializedName("phone")
            @Expose
            var phone: String? = null

            @SerializedName("email")
            @Expose
            var email: String? = null

            @SerializedName("company")
            @Expose
            var company: String? = null

            @SerializedName("status")
            @Expose
            var status: String? = null

            @SerializedName("paypal_email")
            @Expose
            var paypal_email: String? = null

            @SerializedName("affiliate_id")
            @Expose
            var affiliateId: Int? = null

            @SerializedName("percent")
            @Expose
            var percent: String? = null

            @SerializedName("pending_balance")
            @Expose
            var pendingBalance: String? = null

            @SerializedName("available_balance")
            @Expose
            var availableBalance: String? = null

            @SerializedName("allow_hire")
            @Expose
            var allowHire: Int? = null

            @SerializedName("avatar")
            @Expose
            var avatar: String? = null

            @SerializedName("email_verified_at")
            @Expose
            var emailVerifiedAt: String? = null

            @SerializedName("created_at")
            @Expose
            var createdAt: String? = null

            @SerializedName("updated_at")
            @Expose
            var updatedAt: String? = null

            @SerializedName("deleted_at")
            @Expose
            var deletedAt: Any? = null

            @SerializedName("role")
            @Expose
            var role: String? = null

            @SerializedName("unread_notifications")
            @Expose
            var unreadNotifications: List<Any>? = null

            @SerializedName("closets")
            @Expose
            var closets: List<Closet>? = null

            @SerializedName("followers")
            @Expose
            var followers: List<Follower>? = null

            @SerializedName("following")
            @Expose
            var following: List<Following>? = null

            @SerializedName("events")
            @Expose
            var events: List<Any>? = null

            @SerializedName("items")
            @Expose
            var items: List<Any>? = null

            @SerializedName("profile")
            @Expose
            var profile: Profile? = null

            @SerializedName("customer_profile")
            @Expose
            var customerProfile: Any? = null

            @SerializedName("roles")
            @Expose
            var roles: List<Role>? = null

            @SerializedName("customer_subscription")
            @Expose
            var customerSubscription: CustomerSubscription? = null

            @SerializedName("business_subscription")
            @Expose
            var businessSubscription: Any? = null

            inner class Profile {
                @SerializedName("id")
                @Expose
                var id: Int? = null

                @SerializedName("user_id")
                @Expose
                var userId: Int? = null

                @SerializedName("about_me")
                @Expose
                var aboutMe: Any? = null

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

            inner class CustomerSubscription {
                @SerializedName("id")
                @Expose
                var id: Int? = null

                @SerializedName("user_id")
                @Expose
                var userId: Int? = null

                @SerializedName("customer_plan_id")
                @Expose
                var customerPlanId: Int? = null

                @SerializedName("amount")
                @Expose
                var amount: Double? = null

                @SerializedName("currency")
                @Expose
                var currency: String? = null

                @SerializedName("active_at")
                @Expose
                var activeAt: String? = null

                @SerializedName("inactive_at")
                @Expose
                var inactiveAt: String? = null

                @SerializedName("monthly_price")
                @Expose
                var monthlyPrice: Double? = null

                @SerializedName("prev_customer_plan_id")
                @Expose
                var prevCustomerPlanId: Int? = null

                @SerializedName("next_customer_plan_id")
                @Expose
                var nextCustomerPlanId: Int? = null

                @SerializedName("annual_charge")
                @Expose
                var annualCharge: Double? = null

                @SerializedName("yearly_saving_percent")
                @Expose
                var yearlySavingPercent: Any? = null

                @SerializedName("paid_annual_charge")
                @Expose
                var paidAnnualCharge: Int? = null

                @SerializedName("trial_started_at")
                @Expose
                var trialStartedAt: Any? = null

                @SerializedName("trial_end_at")
                @Expose
                var trialEndAt: Any? = null

                @SerializedName("billing_day")
                @Expose
                var billingDay: Int? = null

                @SerializedName("next_payment_date")
                @Expose
                var nextPaymentDate: String? = null

                @SerializedName("last_payment_date")
                @Expose
                var lastPaymentDate: Any? = null

                @SerializedName("expires_at")
                @Expose
                var expiresAt: Any? = null

                @SerializedName("data")
                @Expose
                var data: Any? = null

                @SerializedName("yearly_price")
                @Expose
                var yearlyPrice: Any? = null

                @SerializedName("status")
                @Expose
                var status: String? = null

                @SerializedName("created_at")
                @Expose
                var createdAt: String? = null

                @SerializedName("updated_at")
                @Expose
                var updatedAt: String? = null

                @SerializedName("deleted_at")
                @Expose
                var deletedAt: Any? = null

                @SerializedName("plan")
                @Expose
                var plan: Plan? = null

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
                }
            }

            inner class Closet {
                @SerializedName("id")
                @Expose
                var id: Int? = null

                @SerializedName("user_id")
                @Expose
                var userId: Int? = null

                @SerializedName("creator_id")
                @Expose
                var creatorId: Int? = null

                @SerializedName("title")
                @Expose
                var title: String? = null

                @SerializedName("description")
                @Expose
                var description: String? = null

                @SerializedName("status")
                @Expose
                var status: String? = null

                @SerializedName("visibility")
                @Expose
                var visibility: String? = null

                @SerializedName("def")
                @Expose
                var def: Int? = null

                @SerializedName("image")
                @Expose
                var image: String? = null

                @SerializedName("created_at")
                @Expose
                var createdAt: String? = null

                @SerializedName("updated_at")
                @Expose
                var updatedAt: String? = null

                @SerializedName("creator")
                @Expose
                var creator: Creator? = null
            }

            inner class Follower {
                @SerializedName("id")
                @Expose
                var id: Int? = null

                @SerializedName("firstname")
                @Expose
                var firstname: String? = null

                @SerializedName("lastname")
                @Expose
                var lastname: String? = null

                @SerializedName("fullname")
                @Expose
                var fullname: String? = null

                @SerializedName("phone")
                @Expose
                var phone: String? = null

                @SerializedName("email")
                @Expose
                var email: String? = null

                @SerializedName("company")
                @Expose
                var company: Any? = null

                @SerializedName("status")
                @Expose
                var status: String? = null

                @SerializedName("affiliate_id")
                @Expose
                var affiliateId: Int? = null

                @SerializedName("percent")
                @Expose
                var percent: String? = null

                @SerializedName("pending_balance")
                @Expose
                var pendingBalance: String? = null

                @SerializedName("available_balance")
                @Expose
                var availableBalance: String? = null

                @SerializedName("allow_hire")
                @Expose
                var allowHire: Int? = null

                @SerializedName("avatar")
                @Expose
                var avatar: String? = null

                @SerializedName("email_verified_at")
                @Expose
                var emailVerifiedAt: String? = null

                @SerializedName("created_at")
                @Expose
                var createdAt: String? = null

                @SerializedName("updated_at")
                @Expose
                var updatedAt: String? = null

                @SerializedName("deleted_at")
                @Expose
                var deletedAt: Any? = null

                @SerializedName("role")
                @Expose
                var role: String? = null

                @SerializedName("pivot")
                @Expose
                var pivot: Pivot? = null

                @SerializedName("unread_notifications")
                @Expose
                var unreadNotifications: List<Any>? = null

                inner class Pivot {
                    @SerializedName("leader_id")
                    @Expose
                    var leaderId: Int? = null

                    @SerializedName("follower_id")
                    @Expose
                    var followerId: Int? = null

                    @SerializedName("created_at")
                    @Expose
                    var createdAt: String? = null

                    @SerializedName("updated_at")
                    @Expose
                    var updatedAt: String? = null
                }
            }

            inner class Following {
                @SerializedName("id")
                @Expose
                var id: Int? = null

                @SerializedName("firstname")
                @Expose
                var firstname: String? = null

                @SerializedName("lastname")
                @Expose
                var lastname: String? = null

                @SerializedName("fullname")
                @Expose
                var fullname: String? = null

                @SerializedName("phone")
                @Expose
                var phone: String? = null

                @SerializedName("email")
                @Expose
                var email: String? = null

                @SerializedName("company")
                @Expose
                var company: Any? = null

                @SerializedName("status")
                @Expose
                var status: String? = null

                @SerializedName("affiliate_id")
                @Expose
                var affiliateId: Int? = null

                @SerializedName("percent")
                @Expose
                var percent: String? = null

                @SerializedName("pending_balance")
                @Expose
                var pendingBalance: String? = null

                @SerializedName("available_balance")
                @Expose
                var availableBalance: String? = null

                @SerializedName("allow_hire")
                @Expose
                var allowHire: Int? = null

                @SerializedName("avatar")
                @Expose
                var avatar: String? = null

                @SerializedName("email_verified_at")
                @Expose
                var emailVerifiedAt: String? = null

                @SerializedName("created_at")
                @Expose
                var createdAt: String? = null

                @SerializedName("updated_at")
                @Expose
                var updatedAt: String? = null

                @SerializedName("deleted_at")
                @Expose
                var deletedAt: Any? = null

                @SerializedName("role")
                @Expose
                var role: String? = null

                @SerializedName("pivot")
                @Expose
                var pivot: Follower.Pivot? = null

                @SerializedName("unread_notifications")
                @Expose
                var unreadNotifications: List<Any>? = null

                inner class Pivot {
                    @SerializedName("leader_id")
                    @Expose
                    var leaderId: Int? = null

                    @SerializedName("follower_id")
                    @Expose
                    var followerId: Int? = null

                    @SerializedName("created_at")
                    @Expose
                    var createdAt: String? = null

                    @SerializedName("updated_at")
                    @Expose
                    var updatedAt: String? = null
                }
            }

            inner class Role {
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

                @SerializedName("pivot")
                @Expose
                var pivot: Pivot_? = null

                inner class Pivot_ {
                    @SerializedName("model_id")
                    @Expose
                    var modelId: Int? = null

                    @SerializedName("role_id")
                    @Expose
                    var roleId: Int? = null

                    @SerializedName("model_type")
                    @Expose
                    var modelType: String? = null
                }
            }

            inner class Creator {
                @SerializedName("id")
                @Expose
                var id: Int? = null

                @SerializedName("firstname")
                @Expose
                var firstname: String? = null

                @SerializedName("lastname")
                @Expose
                var lastname: String? = null

                @SerializedName("fullname")
                @Expose
                var fullname: String? = null

                @SerializedName("phone")
                @Expose
                var phone: String? = null

                @SerializedName("email")
                @Expose
                var email: String? = null

                @SerializedName("company")
                @Expose
                var company: String? = null

                @SerializedName("status")
                @Expose
                var status: String? = null

                @SerializedName("affiliate_id")
                @Expose
                var affiliateId: Int? = null

                @SerializedName("percent")
                @Expose
                var percent: String? = null

                @SerializedName("pending_balance")
                @Expose
                var pendingBalance: String? = null

                @SerializedName("available_balance")
                @Expose
                var availableBalance: String? = null

                @SerializedName("allow_hire")
                @Expose
                var allowHire: Int? = null

                @SerializedName("avatar")
                @Expose
                var avatar: String? = null

                @SerializedName("email_verified_at")
                @Expose
                var emailVerifiedAt: String? = null

                @SerializedName("created_at")
                @Expose
                var createdAt: String? = null

                @SerializedName("updated_at")
                @Expose
                var updatedAt: String? = null

                @SerializedName("deleted_at")
                @Expose
                var deletedAt: Any? = null

                @SerializedName("role")
                @Expose
                var role: String? = null

                @SerializedName("unread_notifications")
                @Expose
                var unreadNotifications: List<Any>? = null
            }
        }
    }
}