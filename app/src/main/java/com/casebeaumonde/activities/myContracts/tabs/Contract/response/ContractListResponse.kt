package com.casebeaumonde.activities.myContracts.tabs.Contract.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ContractListResponse {
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
        @SerializedName("user")
        @Expose
        var user: User? = null

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
            var fullname: Any? = null

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

            @SerializedName("contracts_as_customer")
            @Expose
            var contractsAsCustomer: List<ContractsAsCustomer>? = null

            @SerializedName("contracts_as_contractor")
            @Expose
            var contractsAsContractor: List<ContractsAsContractor>? = null

            @SerializedName("role")
            @Expose
            var role: String? = null

            inner class ContractsAsCustomer {
                @SerializedName("id")
                @Expose
                var id: Int? = null

                @SerializedName("contract_number")
                @Expose
                var contractNumber: String? = null

                @SerializedName("customer_id")
                @Expose
                var customerId: Int? = null

                @SerializedName("contractor_id")
                @Expose
                var contractorId: Int? = null

                @SerializedName("gig_id")
                @Expose
                var gigId: Int? = null

                @SerializedName("customer_fullname")
                @Expose
                var customerFullname: String? = null

                @SerializedName("contractor_fullname")
                @Expose
                var contractorFullname: String? = null

                @SerializedName("hours")
                @Expose
                var hours: Int? = null

                @SerializedName("rate")
                @Expose
                var rate: Int? = null

                @SerializedName("rate_type")
                @Expose
                var rateType: String? = null

                @SerializedName("total_amount")
                @Expose
                var totalAmount: Int? = null

                @SerializedName("customer_signature")
                @Expose
                var customerSignature: Any? = null

                @SerializedName("contractor_signature")
                @Expose
                var contractorSignature: Any? = null

                @SerializedName("opened_at")
                @Expose
                var openedAt: String? = null

                @SerializedName("finished_at")
                @Expose
                var finishedAt: Any? = null

                @SerializedName("amount_paid_contractor")
                @Expose
                var amountPaidContractor: Any? = null

                @SerializedName("amount_paid_cbm")
                @Expose
                var amountPaidCbm: Any? = null

                @SerializedName("total_used_days")
                @Expose
                var totalUsedDays: Any? = null

                @SerializedName("comments")
                @Expose
                var comments: Any? = null

                @SerializedName("status")
                @Expose
                var status: String? = null

                @SerializedName("hash")
                @Expose
                var hash: String? = null

                @SerializedName("retries_to_open")
                @Expose
                var retriesToOpen: Int? = null

                @SerializedName("created_at")
                @Expose
                var createdAt: String? = null

                @SerializedName("updated_at")
                @Expose
                var updatedAt: String? = null

                @SerializedName("customer")
                @Expose
                var customer: Customer? = null

                @SerializedName("contractor")
                @Expose
                var contractor: Contractor? = null

                @SerializedName("gig")
                @Expose
                var gig: Gig? = null

                inner class Customer {
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
                }

                inner class Contractor {
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
                    var fullname: Any? = null

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
                }

                inner class Gig {
                    @SerializedName("id")
                    @Expose
                    var id: Int? = null

                    @SerializedName("user_id")
                    @Expose
                    var userId: Int? = null

                    @SerializedName("title")
                    @Expose
                    var title: String? = null

                    @SerializedName("description")
                    @Expose
                    var description: String? = null

                    @SerializedName("hours")
                    @Expose
                    var hours: String? = null

                    @SerializedName("rate")
                    @Expose
                    var rate: Int? = null

                    @SerializedName("rate_type")
                    @Expose
                    var rateType: String? = null

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

            inner class ContractsAsContractor {
                @SerializedName("id")
                @Expose
                var id: Int? = null

                @SerializedName("contract_number")
                @Expose
                var contractNumber: String? = null

                @SerializedName("customer_id")
                @Expose
                var customerId: Int? = null

                @SerializedName("contractor_id")
                @Expose
                var contractorId: Int? = null

                @SerializedName("gig_id")
                @Expose
                var gigId: Int? = null

                @SerializedName("customer_fullname")
                @Expose
                var customerFullname: String? = null

                @SerializedName("contractor_fullname")
                @Expose
                var contractorFullname: String? = null

                @SerializedName("hours")
                @Expose
                var hours: Int? = null

                @SerializedName("rate")
                @Expose
                var rate: Int? = null

                @SerializedName("rate_type")
                @Expose
                var rateType: String? = null

                @SerializedName("total_amount")
                @Expose
                var totalAmount: Int? = null

                @SerializedName("customer_signature")
                @Expose
                var customerSignature: Any? = null

                @SerializedName("contractor_signature")
                @Expose
                var contractorSignature: Any? = null

                @SerializedName("opened_at")
                @Expose
                var openedAt: String? = null

                @SerializedName("finished_at")
                @Expose
                var finishedAt: Any? = null

                @SerializedName("amount_paid_contractor")
                @Expose
                var amountPaidContractor: Any? = null

                @SerializedName("amount_paid_cbm")
                @Expose
                var amountPaidCbm: Any? = null

                @SerializedName("total_used_days")
                @Expose
                var totalUsedDays: Any? = null

                @SerializedName("comments")
                @Expose
                var comments: Any? = null

                @SerializedName("status")
                @Expose
                var status: String? = null

                @SerializedName("hash")
                @Expose
                var hash: String? = null

                @SerializedName("retries_to_open")
                @Expose
                var retriesToOpen: Int? = null

                @SerializedName("created_at")
                @Expose
                var createdAt: String? = null

                @SerializedName("updated_at")
                @Expose
                var updatedAt: String? = null

                @SerializedName("customer")
                @Expose
                var customer: Customer_? = null

                @SerializedName("contractor")
                @Expose
                var contractor: Contractor_? = null

                @SerializedName("gig")
                @Expose
                var gig: Gig_? = null

                inner class Customer_ {
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
                }

                inner class Contractor_ {
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
                    var fullname: Any? = null

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
                }

                inner class Gig_ {
                    @SerializedName("id")
                    @Expose
                    var id: Int? = null

                    @SerializedName("user_id")
                    @Expose
                    var userId: Int? = null

                    @SerializedName("title")
                    @Expose
                    var title: String? = null

                    @SerializedName("description")
                    @Expose
                    var description: String? = null

                    @SerializedName("hours")
                    @Expose
                    var hours: String? = null

                    @SerializedName("rate")
                    @Expose
                    var rate: Int? = null

                    @SerializedName("rate_type")
                    @Expose
                    var rateType: String? = null

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
    }
}