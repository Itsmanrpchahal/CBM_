package com.casebeaumonde.activities.myContracts.tabs.Contract.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ContractListResponse {

    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("data")
    @Expose
    private Data data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public class Data {

        @SerializedName("user")
        @Expose
        private User user;

        public User getUser() {
            return user;
        }

        public void setUser(User user) {
            this.user = user;
        }

        public class User {

            @SerializedName("id")
            @Expose
            private Integer id;
            @SerializedName("firstname")
            @Expose
            private String firstname;
            @SerializedName("lastname")
            @Expose
            private String lastname;
            @SerializedName("fullname")
            @Expose
            private Object fullname;
            @SerializedName("phone")
            @Expose
            private String phone;
            @SerializedName("email")
            @Expose
            private String email;
            @SerializedName("company")
            @Expose
            private Object company;
            @SerializedName("status")
            @Expose
            private String status;
            @SerializedName("affiliate_id")
            @Expose
            private Integer affiliateId;
            @SerializedName("percent")
            @Expose
            private String percent;
            @SerializedName("pending_balance")
            @Expose
            private String pendingBalance;
            @SerializedName("available_balance")
            @Expose
            private String availableBalance;
            @SerializedName("allow_hire")
            @Expose
            private Integer allowHire;
            @SerializedName("avatar")
            @Expose
            private String avatar;
            @SerializedName("email_verified_at")
            @Expose
            private String emailVerifiedAt;
            @SerializedName("created_at")
            @Expose
            private String createdAt;
            @SerializedName("updated_at")
            @Expose
            private String updatedAt;
            @SerializedName("deleted_at")
            @Expose
            private Object deletedAt;
            @SerializedName("contracts_as_customer")
            @Expose
            private List<ContractsAsCustomer> contractsAsCustomer = null;
            @SerializedName("contracts_as_contractor")
            @Expose
            private List<ContractsAsContractor> contractsAsContractor = null;
            @SerializedName("role")
            @Expose
            private String role;

            public Integer getId() {
                return id;
            }

            public void setId(Integer id) {
                this.id = id;
            }

            public String getFirstname() {
                return firstname;
            }

            public void setFirstname(String firstname) {
                this.firstname = firstname;
            }

            public String getLastname() {
                return lastname;
            }

            public void setLastname(String lastname) {
                this.lastname = lastname;
            }

            public Object getFullname() {
                return fullname;
            }

            public void setFullname(Object fullname) {
                this.fullname = fullname;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public String getEmail() {
                return email;
            }

            public void setEmail(String email) {
                this.email = email;
            }

            public Object getCompany() {
                return company;
            }

            public void setCompany(Object company) {
                this.company = company;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public Integer getAffiliateId() {
                return affiliateId;
            }

            public void setAffiliateId(Integer affiliateId) {
                this.affiliateId = affiliateId;
            }

            public String getPercent() {
                return percent;
            }

            public void setPercent(String percent) {
                this.percent = percent;
            }

            public String getPendingBalance() {
                return pendingBalance;
            }

            public void setPendingBalance(String pendingBalance) {
                this.pendingBalance = pendingBalance;
            }

            public String getAvailableBalance() {
                return availableBalance;
            }

            public void setAvailableBalance(String availableBalance) {
                this.availableBalance = availableBalance;
            }

            public Integer getAllowHire() {
                return allowHire;
            }

            public void setAllowHire(Integer allowHire) {
                this.allowHire = allowHire;
            }

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }

            public String getEmailVerifiedAt() {
                return emailVerifiedAt;
            }

            public void setEmailVerifiedAt(String emailVerifiedAt) {
                this.emailVerifiedAt = emailVerifiedAt;
            }

            public String getCreatedAt() {
                return createdAt;
            }

            public void setCreatedAt(String createdAt) {
                this.createdAt = createdAt;
            }

            public String getUpdatedAt() {
                return updatedAt;
            }

            public void setUpdatedAt(String updatedAt) {
                this.updatedAt = updatedAt;
            }

            public Object getDeletedAt() {
                return deletedAt;
            }

            public void setDeletedAt(Object deletedAt) {
                this.deletedAt = deletedAt;
            }

            public List<ContractsAsCustomer> getContractsAsCustomer() {
                return contractsAsCustomer;
            }

            public void setContractsAsCustomer(List<ContractsAsCustomer> contractsAsCustomer) {
                this.contractsAsCustomer = contractsAsCustomer;
            }

            public List<ContractsAsContractor> getContractsAsContractor() {
                return contractsAsContractor;
            }

            public void setContractsAsContractor(List<ContractsAsContractor> contractsAsContractor) {
                this.contractsAsContractor = contractsAsContractor;
            }

            public String getRole() {
                return role;
            }

            public void setRole(String role) {
                this.role = role;
            }

            public class ContractsAsCustomer {

                @SerializedName("id")
                @Expose
                private Integer id;
                @SerializedName("contract_number")
                @Expose
                private String contractNumber;
                @SerializedName("customer_id")
                @Expose
                private Integer customerId;
                @SerializedName("contractor_id")
                @Expose
                private Integer contractorId;
                @SerializedName("gig_id")
                @Expose
                private Integer gigId;
                @SerializedName("customer_fullname")
                @Expose
                private String customerFullname;
                @SerializedName("contractor_fullname")
                @Expose
                private String contractorFullname;
                @SerializedName("hours")
                @Expose
                private Integer hours;
                @SerializedName("rate")
                @Expose
                private Integer rate;
                @SerializedName("rate_type")
                @Expose
                private String rateType;
                @SerializedName("total_amount")
                @Expose
                private Integer totalAmount;
                @SerializedName("customer_signature")
                @Expose
                private Object customerSignature;
                @SerializedName("contractor_signature")
                @Expose
                private Object contractorSignature;
                @SerializedName("opened_at")
                @Expose
                private String openedAt;
                @SerializedName("finished_at")
                @Expose
                private Object finishedAt;
                @SerializedName("amount_paid_contractor")
                @Expose
                private Object amountPaidContractor;
                @SerializedName("amount_paid_cbm")
                @Expose
                private Object amountPaidCbm;
                @SerializedName("total_used_days")
                @Expose
                private Object totalUsedDays;
                @SerializedName("comments")
                @Expose
                private Object comments;
                @SerializedName("status")
                @Expose
                private String status;
                @SerializedName("hash")
                @Expose
                private String hash;
                @SerializedName("retries_to_open")
                @Expose
                private Integer retriesToOpen;
                @SerializedName("created_at")
                @Expose
                private String createdAt;
                @SerializedName("updated_at")
                @Expose
                private String updatedAt;
                @SerializedName("customer")
                @Expose
                private Customer customer;
                @SerializedName("contractor")
                @Expose
                private Contractor contractor;
                @SerializedName("gig")
                @Expose
                private Gig gig;

                public Integer getId() {
                    return id;
                }

                public void setId(Integer id) {
                    this.id = id;
                }

                public String getContractNumber() {
                    return contractNumber;
                }

                public void setContractNumber(String contractNumber) {
                    this.contractNumber = contractNumber;
                }

                public Integer getCustomerId() {
                    return customerId;
                }

                public void setCustomerId(Integer customerId) {
                    this.customerId = customerId;
                }

                public Integer getContractorId() {
                    return contractorId;
                }

                public void setContractorId(Integer contractorId) {
                    this.contractorId = contractorId;
                }

                public Integer getGigId() {
                    return gigId;
                }

                public void setGigId(Integer gigId) {
                    this.gigId = gigId;
                }

                public String getCustomerFullname() {
                    return customerFullname;
                }

                public void setCustomerFullname(String customerFullname) {
                    this.customerFullname = customerFullname;
                }

                public String getContractorFullname() {
                    return contractorFullname;
                }

                public void setContractorFullname(String contractorFullname) {
                    this.contractorFullname = contractorFullname;
                }

                public Integer getHours() {
                    return hours;
                }

                public void setHours(Integer hours) {
                    this.hours = hours;
                }

                public Integer getRate() {
                    return rate;
                }

                public void setRate(Integer rate) {
                    this.rate = rate;
                }

                public String getRateType() {
                    return rateType;
                }

                public void setRateType(String rateType) {
                    this.rateType = rateType;
                }

                public Integer getTotalAmount() {
                    return totalAmount;
                }

                public void setTotalAmount(Integer totalAmount) {
                    this.totalAmount = totalAmount;
                }

                public Object getCustomerSignature() {
                    return customerSignature;
                }

                public void setCustomerSignature(Object customerSignature) {
                    this.customerSignature = customerSignature;
                }

                public Object getContractorSignature() {
                    return contractorSignature;
                }

                public void setContractorSignature(Object contractorSignature) {
                    this.contractorSignature = contractorSignature;
                }

                public String getOpenedAt() {
                    return openedAt;
                }

                public void setOpenedAt(String openedAt) {
                    this.openedAt = openedAt;
                }

                public Object getFinishedAt() {
                    return finishedAt;
                }

                public void setFinishedAt(Object finishedAt) {
                    this.finishedAt = finishedAt;
                }

                public Object getAmountPaidContractor() {
                    return amountPaidContractor;
                }

                public void setAmountPaidContractor(Object amountPaidContractor) {
                    this.amountPaidContractor = amountPaidContractor;
                }

                public Object getAmountPaidCbm() {
                    return amountPaidCbm;
                }

                public void setAmountPaidCbm(Object amountPaidCbm) {
                    this.amountPaidCbm = amountPaidCbm;
                }

                public Object getTotalUsedDays() {
                    return totalUsedDays;
                }

                public void setTotalUsedDays(Object totalUsedDays) {
                    this.totalUsedDays = totalUsedDays;
                }

                public Object getComments() {
                    return comments;
                }

                public void setComments(Object comments) {
                    this.comments = comments;
                }

                public String getStatus() {
                    return status;
                }

                public void setStatus(String status) {
                    this.status = status;
                }

                public String getHash() {
                    return hash;
                }

                public void setHash(String hash) {
                    this.hash = hash;
                }

                public Integer getRetriesToOpen() {
                    return retriesToOpen;
                }

                public void setRetriesToOpen(Integer retriesToOpen) {
                    this.retriesToOpen = retriesToOpen;
                }

                public String getCreatedAt() {
                    return createdAt;
                }

                public void setCreatedAt(String createdAt) {
                    this.createdAt = createdAt;
                }

                public String getUpdatedAt() {
                    return updatedAt;
                }

                public void setUpdatedAt(String updatedAt) {
                    this.updatedAt = updatedAt;
                }

                public Customer getCustomer() {
                    return customer;
                }

                public void setCustomer(Customer customer) {
                    this.customer = customer;
                }

                public Contractor getContractor() {
                    return contractor;
                }

                public void setContractor(Contractor contractor) {
                    this.contractor = contractor;
                }

                public Gig getGig() {
                    return gig;
                }

                public void setGig(Gig gig) {
                    this.gig = gig;
                }

                public class Customer {

                    @SerializedName("id")
                    @Expose
                    private Integer id;
                    @SerializedName("firstname")
                    @Expose
                    private String firstname;
                    @SerializedName("lastname")
                    @Expose
                    private String lastname;
                    @SerializedName("fullname")
                    @Expose
                    private String fullname;
                    @SerializedName("phone")
                    @Expose
                    private String phone;
                    @SerializedName("email")
                    @Expose
                    private String email;
                    @SerializedName("company")
                    @Expose
                    private Object company;
                    @SerializedName("status")
                    @Expose
                    private String status;
                    @SerializedName("affiliate_id")
                    @Expose
                    private Integer affiliateId;
                    @SerializedName("percent")
                    @Expose
                    private String percent;
                    @SerializedName("pending_balance")
                    @Expose
                    private String pendingBalance;
                    @SerializedName("available_balance")
                    @Expose
                    private String availableBalance;
                    @SerializedName("allow_hire")
                    @Expose
                    private Integer allowHire;
                    @SerializedName("avatar")
                    @Expose
                    private String avatar;
                    @SerializedName("email_verified_at")
                    @Expose
                    private String emailVerifiedAt;
                    @SerializedName("created_at")
                    @Expose
                    private String createdAt;
                    @SerializedName("updated_at")
                    @Expose
                    private String updatedAt;
                    @SerializedName("deleted_at")
                    @Expose
                    private Object deletedAt;
                    @SerializedName("role")
                    @Expose
                    private String role;

                    public Integer getId() {
                        return id;
                    }

                    public void setId(Integer id) {
                        this.id = id;
                    }

                    public String getFirstname() {
                        return firstname;
                    }

                    public void setFirstname(String firstname) {
                        this.firstname = firstname;
                    }

                    public String getLastname() {
                        return lastname;
                    }

                    public void setLastname(String lastname) {
                        this.lastname = lastname;
                    }

                    public String getFullname() {
                        return fullname;
                    }

                    public void setFullname(String fullname) {
                        this.fullname = fullname;
                    }

                    public String getPhone() {
                        return phone;
                    }

                    public void setPhone(String phone) {
                        this.phone = phone;
                    }

                    public String getEmail() {
                        return email;
                    }

                    public void setEmail(String email) {
                        this.email = email;
                    }

                    public Object getCompany() {
                        return company;
                    }

                    public void setCompany(Object company) {
                        this.company = company;
                    }

                    public String getStatus() {
                        return status;
                    }

                    public void setStatus(String status) {
                        this.status = status;
                    }

                    public Integer getAffiliateId() {
                        return affiliateId;
                    }

                    public void setAffiliateId(Integer affiliateId) {
                        this.affiliateId = affiliateId;
                    }

                    public String getPercent() {
                        return percent;
                    }

                    public void setPercent(String percent) {
                        this.percent = percent;
                    }

                    public String getPendingBalance() {
                        return pendingBalance;
                    }

                    public void setPendingBalance(String pendingBalance) {
                        this.pendingBalance = pendingBalance;
                    }

                    public String getAvailableBalance() {
                        return availableBalance;
                    }

                    public void setAvailableBalance(String availableBalance) {
                        this.availableBalance = availableBalance;
                    }

                    public Integer getAllowHire() {
                        return allowHire;
                    }

                    public void setAllowHire(Integer allowHire) {
                        this.allowHire = allowHire;
                    }

                    public String getAvatar() {
                        return avatar;
                    }

                    public void setAvatar(String avatar) {
                        this.avatar = avatar;
                    }

                    public String getEmailVerifiedAt() {
                        return emailVerifiedAt;
                    }

                    public void setEmailVerifiedAt(String emailVerifiedAt) {
                        this.emailVerifiedAt = emailVerifiedAt;
                    }

                    public String getCreatedAt() {
                        return createdAt;
                    }

                    public void setCreatedAt(String createdAt) {
                        this.createdAt = createdAt;
                    }

                    public String getUpdatedAt() {
                        return updatedAt;
                    }

                    public void setUpdatedAt(String updatedAt) {
                        this.updatedAt = updatedAt;
                    }

                    public Object getDeletedAt() {
                        return deletedAt;
                    }

                    public void setDeletedAt(Object deletedAt) {
                        this.deletedAt = deletedAt;
                    }

                    public String getRole() {
                        return role;
                    }

                    public void setRole(String role) {
                        this.role = role;
                    }

                }

                public class Contractor {

                    @SerializedName("id")
                    @Expose
                    private Integer id;
                    @SerializedName("firstname")
                    @Expose
                    private String firstname;
                    @SerializedName("lastname")
                    @Expose
                    private String lastname;
                    @SerializedName("fullname")
                    @Expose
                    private Object fullname;
                    @SerializedName("phone")
                    @Expose
                    private String phone;
                    @SerializedName("email")
                    @Expose
                    private String email;
                    @SerializedName("company")
                    @Expose
                    private Object company;
                    @SerializedName("status")
                    @Expose
                    private String status;
                    @SerializedName("affiliate_id")
                    @Expose
                    private Integer affiliateId;
                    @SerializedName("percent")
                    @Expose
                    private String percent;
                    @SerializedName("pending_balance")
                    @Expose
                    private String pendingBalance;
                    @SerializedName("available_balance")
                    @Expose
                    private String availableBalance;
                    @SerializedName("allow_hire")
                    @Expose
                    private Integer allowHire;
                    @SerializedName("avatar")
                    @Expose
                    private String avatar;
                    @SerializedName("email_verified_at")
                    @Expose
                    private String emailVerifiedAt;
                    @SerializedName("created_at")
                    @Expose
                    private String createdAt;
                    @SerializedName("updated_at")
                    @Expose
                    private String updatedAt;
                    @SerializedName("deleted_at")
                    @Expose
                    private Object deletedAt;
                    @SerializedName("role")
                    @Expose
                    private String role;

                    public Integer getId() {
                        return id;
                    }

                    public void setId(Integer id) {
                        this.id = id;
                    }

                    public String getFirstname() {
                        return firstname;
                    }

                    public void setFirstname(String firstname) {
                        this.firstname = firstname;
                    }

                    public String getLastname() {
                        return lastname;
                    }

                    public void setLastname(String lastname) {
                        this.lastname = lastname;
                    }

                    public Object getFullname() {
                        return fullname;
                    }

                    public void setFullname(Object fullname) {
                        this.fullname = fullname;
                    }

                    public String getPhone() {
                        return phone;
                    }

                    public void setPhone(String phone) {
                        this.phone = phone;
                    }

                    public String getEmail() {
                        return email;
                    }

                    public void setEmail(String email) {
                        this.email = email;
                    }

                    public Object getCompany() {
                        return company;
                    }

                    public void setCompany(Object company) {
                        this.company = company;
                    }

                    public String getStatus() {
                        return status;
                    }

                    public void setStatus(String status) {
                        this.status = status;
                    }

                    public Integer getAffiliateId() {
                        return affiliateId;
                    }

                    public void setAffiliateId(Integer affiliateId) {
                        this.affiliateId = affiliateId;
                    }

                    public String getPercent() {
                        return percent;
                    }

                    public void setPercent(String percent) {
                        this.percent = percent;
                    }

                    public String getPendingBalance() {
                        return pendingBalance;
                    }

                    public void setPendingBalance(String pendingBalance) {
                        this.pendingBalance = pendingBalance;
                    }

                    public String getAvailableBalance() {
                        return availableBalance;
                    }

                    public void setAvailableBalance(String availableBalance) {
                        this.availableBalance = availableBalance;
                    }

                    public Integer getAllowHire() {
                        return allowHire;
                    }

                    public void setAllowHire(Integer allowHire) {
                        this.allowHire = allowHire;
                    }

                    public String getAvatar() {
                        return avatar;
                    }

                    public void setAvatar(String avatar) {
                        this.avatar = avatar;
                    }

                    public String getEmailVerifiedAt() {
                        return emailVerifiedAt;
                    }

                    public void setEmailVerifiedAt(String emailVerifiedAt) {
                        this.emailVerifiedAt = emailVerifiedAt;
                    }

                    public String getCreatedAt() {
                        return createdAt;
                    }

                    public void setCreatedAt(String createdAt) {
                        this.createdAt = createdAt;
                    }

                    public String getUpdatedAt() {
                        return updatedAt;
                    }

                    public void setUpdatedAt(String updatedAt) {
                        this.updatedAt = updatedAt;
                    }

                    public Object getDeletedAt() {
                        return deletedAt;
                    }

                    public void setDeletedAt(Object deletedAt) {
                        this.deletedAt = deletedAt;
                    }

                    public String getRole() {
                        return role;
                    }

                    public void setRole(String role) {
                        this.role = role;
                    }

                }

                public class Gig {

                    @SerializedName("id")
                    @Expose
                    private Integer id;
                    @SerializedName("user_id")
                    @Expose
                    private Integer userId;
                    @SerializedName("title")
                    @Expose
                    private String title;
                    @SerializedName("description")
                    @Expose
                    private String description;
                    @SerializedName("hours")
                    @Expose
                    private String hours;
                    @SerializedName("rate")
                    @Expose
                    private Integer rate;
                    @SerializedName("rate_type")
                    @Expose
                    private String rateType;
                    @SerializedName("status")
                    @Expose
                    private String status;
                    @SerializedName("created_at")
                    @Expose
                    private String createdAt;
                    @SerializedName("updated_at")
                    @Expose
                    private String updatedAt;

                    public Integer getId() {
                        return id;
                    }

                    public void setId(Integer id) {
                        this.id = id;
                    }

                    public Integer getUserId() {
                        return userId;
                    }

                    public void setUserId(Integer userId) {
                        this.userId = userId;
                    }

                    public String getTitle() {
                        return title;
                    }

                    public void setTitle(String title) {
                        this.title = title;
                    }

                    public String getDescription() {
                        return description;
                    }

                    public void setDescription(String description) {
                        this.description = description;
                    }

                    public String getHours() {
                        return hours;
                    }

                    public void setHours(String hours) {
                        this.hours = hours;
                    }

                    public Integer getRate() {
                        return rate;
                    }

                    public void setRate(Integer rate) {
                        this.rate = rate;
                    }

                    public String getRateType() {
                        return rateType;
                    }

                    public void setRateType(String rateType) {
                        this.rateType = rateType;
                    }

                    public String getStatus() {
                        return status;
                    }

                    public void setStatus(String status) {
                        this.status = status;
                    }

                    public String getCreatedAt() {
                        return createdAt;
                    }

                    public void setCreatedAt(String createdAt) {
                        this.createdAt = createdAt;
                    }

                    public String getUpdatedAt() {
                        return updatedAt;
                    }

                    public void setUpdatedAt(String updatedAt) {
                        this.updatedAt = updatedAt;
                    }

                }

            }

            public class ContractsAsContractor {

                @SerializedName("id")
                @Expose
                private Integer id;
                @SerializedName("contract_number")
                @Expose
                private String contractNumber;
                @SerializedName("customer_id")
                @Expose
                private Integer customerId;
                @SerializedName("contractor_id")
                @Expose
                private Integer contractorId;
                @SerializedName("gig_id")
                @Expose
                private Integer gigId;
                @SerializedName("customer_fullname")
                @Expose
                private String customerFullname;
                @SerializedName("contractor_fullname")
                @Expose
                private String contractorFullname;
                @SerializedName("hours")
                @Expose
                private Integer hours;
                @SerializedName("rate")
                @Expose
                private Integer rate;
                @SerializedName("rate_type")
                @Expose
                private String rateType;
                @SerializedName("total_amount")
                @Expose
                private Integer totalAmount;
                @SerializedName("customer_signature")
                @Expose
                private Object customerSignature;
                @SerializedName("contractor_signature")
                @Expose
                private Object contractorSignature;
                @SerializedName("opened_at")
                @Expose
                private String openedAt;
                @SerializedName("finished_at")
                @Expose
                private Object finishedAt;
                @SerializedName("amount_paid_contractor")
                @Expose
                private Object amountPaidContractor;
                @SerializedName("amount_paid_cbm")
                @Expose
                private Object amountPaidCbm;
                @SerializedName("total_used_days")
                @Expose
                private Object totalUsedDays;
                @SerializedName("comments")
                @Expose
                private Object comments;
                @SerializedName("status")
                @Expose
                private String status;
                @SerializedName("hash")
                @Expose
                private String hash;
                @SerializedName("retries_to_open")
                @Expose
                private Integer retriesToOpen;
                @SerializedName("created_at")
                @Expose
                private String createdAt;
                @SerializedName("updated_at")
                @Expose
                private String updatedAt;
                @SerializedName("customer")
                @Expose
                private Customer_ customer;
                @SerializedName("contractor")
                @Expose
                private Contractor_ contractor;
                @SerializedName("gig")
                @Expose
                private Gig_ gig;

                public Integer getId() {
                    return id;
                }

                public void setId(Integer id) {
                    this.id = id;
                }

                public String getContractNumber() {
                    return contractNumber;
                }

                public void setContractNumber(String contractNumber) {
                    this.contractNumber = contractNumber;
                }

                public Integer getCustomerId() {
                    return customerId;
                }

                public void setCustomerId(Integer customerId) {
                    this.customerId = customerId;
                }

                public Integer getContractorId() {
                    return contractorId;
                }

                public void setContractorId(Integer contractorId) {
                    this.contractorId = contractorId;
                }

                public Integer getGigId() {
                    return gigId;
                }

                public void setGigId(Integer gigId) {
                    this.gigId = gigId;
                }

                public String getCustomerFullname() {
                    return customerFullname;
                }

                public void setCustomerFullname(String customerFullname) {
                    this.customerFullname = customerFullname;
                }

                public String getContractorFullname() {
                    return contractorFullname;
                }

                public void setContractorFullname(String contractorFullname) {
                    this.contractorFullname = contractorFullname;
                }

                public Integer getHours() {
                    return hours;
                }

                public void setHours(Integer hours) {
                    this.hours = hours;
                }

                public Integer getRate() {
                    return rate;
                }

                public void setRate(Integer rate) {
                    this.rate = rate;
                }

                public String getRateType() {
                    return rateType;
                }

                public void setRateType(String rateType) {
                    this.rateType = rateType;
                }

                public Integer getTotalAmount() {
                    return totalAmount;
                }

                public void setTotalAmount(Integer totalAmount) {
                    this.totalAmount = totalAmount;
                }

                public Object getCustomerSignature() {
                    return customerSignature;
                }

                public void setCustomerSignature(Object customerSignature) {
                    this.customerSignature = customerSignature;
                }

                public Object getContractorSignature() {
                    return contractorSignature;
                }

                public void setContractorSignature(Object contractorSignature) {
                    this.contractorSignature = contractorSignature;
                }

                public String getOpenedAt() {
                    return openedAt;
                }

                public void setOpenedAt(String openedAt) {
                    this.openedAt = openedAt;
                }

                public Object getFinishedAt() {
                    return finishedAt;
                }

                public void setFinishedAt(Object finishedAt) {
                    this.finishedAt = finishedAt;
                }

                public Object getAmountPaidContractor() {
                    return amountPaidContractor;
                }

                public void setAmountPaidContractor(Object amountPaidContractor) {
                    this.amountPaidContractor = amountPaidContractor;
                }

                public Object getAmountPaidCbm() {
                    return amountPaidCbm;
                }

                public void setAmountPaidCbm(Object amountPaidCbm) {
                    this.amountPaidCbm = amountPaidCbm;
                }

                public Object getTotalUsedDays() {
                    return totalUsedDays;
                }

                public void setTotalUsedDays(Object totalUsedDays) {
                    this.totalUsedDays = totalUsedDays;
                }

                public Object getComments() {
                    return comments;
                }

                public void setComments(Object comments) {
                    this.comments = comments;
                }

                public String getStatus() {
                    return status;
                }

                public void setStatus(String status) {
                    this.status = status;
                }

                public String getHash() {
                    return hash;
                }

                public void setHash(String hash) {
                    this.hash = hash;
                }

                public Integer getRetriesToOpen() {
                    return retriesToOpen;
                }

                public void setRetriesToOpen(Integer retriesToOpen) {
                    this.retriesToOpen = retriesToOpen;
                }

                public String getCreatedAt() {
                    return createdAt;
                }

                public void setCreatedAt(String createdAt) {
                    this.createdAt = createdAt;
                }

                public String getUpdatedAt() {
                    return updatedAt;
                }

                public void setUpdatedAt(String updatedAt) {
                    this.updatedAt = updatedAt;
                }

                public Customer_ getCustomer() {
                    return customer;
                }

                public void setCustomer(Customer_ customer) {
                    this.customer = customer;
                }

                public Contractor_ getContractor() {
                    return contractor;
                }

                public void setContractor(Contractor_ contractor) {
                    this.contractor = contractor;
                }

                public Gig_ getGig() {
                    return gig;
                }

                public void setGig(Gig_ gig) {
                    this.gig = gig;
                }

                public class Customer_ {

                    @SerializedName("id")
                    @Expose
                    private Integer id;
                    @SerializedName("firstname")
                    @Expose
                    private String firstname;
                    @SerializedName("lastname")
                    @Expose
                    private String lastname;
                    @SerializedName("fullname")
                    @Expose
                    private String fullname;
                    @SerializedName("phone")
                    @Expose
                    private String phone;
                    @SerializedName("email")
                    @Expose
                    private String email;
                    @SerializedName("company")
                    @Expose
                    private Object company;
                    @SerializedName("status")
                    @Expose
                    private String status;
                    @SerializedName("affiliate_id")
                    @Expose
                    private Integer affiliateId;
                    @SerializedName("percent")
                    @Expose
                    private String percent;
                    @SerializedName("pending_balance")
                    @Expose
                    private String pendingBalance;
                    @SerializedName("available_balance")
                    @Expose
                    private String availableBalance;
                    @SerializedName("allow_hire")
                    @Expose
                    private Integer allowHire;
                    @SerializedName("avatar")
                    @Expose
                    private String avatar;
                    @SerializedName("email_verified_at")
                    @Expose
                    private String emailVerifiedAt;
                    @SerializedName("created_at")
                    @Expose
                    private String createdAt;
                    @SerializedName("updated_at")
                    @Expose
                    private String updatedAt;
                    @SerializedName("deleted_at")
                    @Expose
                    private Object deletedAt;
                    @SerializedName("role")
                    @Expose
                    private String role;

                    public Integer getId() {
                        return id;
                    }

                    public void setId(Integer id) {
                        this.id = id;
                    }

                    public String getFirstname() {
                        return firstname;
                    }

                    public void setFirstname(String firstname) {
                        this.firstname = firstname;
                    }

                    public String getLastname() {
                        return lastname;
                    }

                    public void setLastname(String lastname) {
                        this.lastname = lastname;
                    }

                    public String getFullname() {
                        return fullname;
                    }

                    public void setFullname(String fullname) {
                        this.fullname = fullname;
                    }

                    public String getPhone() {
                        return phone;
                    }

                    public void setPhone(String phone) {
                        this.phone = phone;
                    }

                    public String getEmail() {
                        return email;
                    }

                    public void setEmail(String email) {
                        this.email = email;
                    }

                    public Object getCompany() {
                        return company;
                    }

                    public void setCompany(Object company) {
                        this.company = company;
                    }

                    public String getStatus() {
                        return status;
                    }

                    public void setStatus(String status) {
                        this.status = status;
                    }

                    public Integer getAffiliateId() {
                        return affiliateId;
                    }

                    public void setAffiliateId(Integer affiliateId) {
                        this.affiliateId = affiliateId;
                    }

                    public String getPercent() {
                        return percent;
                    }

                    public void setPercent(String percent) {
                        this.percent = percent;
                    }

                    public String getPendingBalance() {
                        return pendingBalance;
                    }

                    public void setPendingBalance(String pendingBalance) {
                        this.pendingBalance = pendingBalance;
                    }

                    public String getAvailableBalance() {
                        return availableBalance;
                    }

                    public void setAvailableBalance(String availableBalance) {
                        this.availableBalance = availableBalance;
                    }

                    public Integer getAllowHire() {
                        return allowHire;
                    }

                    public void setAllowHire(Integer allowHire) {
                        this.allowHire = allowHire;
                    }

                    public String getAvatar() {
                        return avatar;
                    }

                    public void setAvatar(String avatar) {
                        this.avatar = avatar;
                    }

                    public String getEmailVerifiedAt() {
                        return emailVerifiedAt;
                    }

                    public void setEmailVerifiedAt(String emailVerifiedAt) {
                        this.emailVerifiedAt = emailVerifiedAt;
                    }

                    public String getCreatedAt() {
                        return createdAt;
                    }

                    public void setCreatedAt(String createdAt) {
                        this.createdAt = createdAt;
                    }

                    public String getUpdatedAt() {
                        return updatedAt;
                    }

                    public void setUpdatedAt(String updatedAt) {
                        this.updatedAt = updatedAt;
                    }

                    public Object getDeletedAt() {
                        return deletedAt;
                    }

                    public void setDeletedAt(Object deletedAt) {
                        this.deletedAt = deletedAt;
                    }

                    public String getRole() {
                        return role;
                    }

                    public void setRole(String role) {
                        this.role = role;
                    }

                }

                public class Contractor_ {

                    @SerializedName("id")
                    @Expose
                    private Integer id;
                    @SerializedName("firstname")
                    @Expose
                    private String firstname;
                    @SerializedName("lastname")
                    @Expose
                    private String lastname;
                    @SerializedName("fullname")
                    @Expose
                    private Object fullname;
                    @SerializedName("phone")
                    @Expose
                    private String phone;
                    @SerializedName("email")
                    @Expose
                    private String email;
                    @SerializedName("company")
                    @Expose
                    private Object company;
                    @SerializedName("status")
                    @Expose
                    private String status;
                    @SerializedName("affiliate_id")
                    @Expose
                    private Integer affiliateId;
                    @SerializedName("percent")
                    @Expose
                    private String percent;
                    @SerializedName("pending_balance")
                    @Expose
                    private String pendingBalance;
                    @SerializedName("available_balance")
                    @Expose
                    private String availableBalance;
                    @SerializedName("allow_hire")
                    @Expose
                    private Integer allowHire;
                    @SerializedName("avatar")
                    @Expose
                    private String avatar;
                    @SerializedName("email_verified_at")
                    @Expose
                    private String emailVerifiedAt;
                    @SerializedName("created_at")
                    @Expose
                    private String createdAt;
                    @SerializedName("updated_at")
                    @Expose
                    private String updatedAt;
                    @SerializedName("deleted_at")
                    @Expose
                    private Object deletedAt;
                    @SerializedName("role")
                    @Expose
                    private String role;

                    public Integer getId() {
                        return id;
                    }

                    public void setId(Integer id) {
                        this.id = id;
                    }

                    public String getFirstname() {
                        return firstname;
                    }

                    public void setFirstname(String firstname) {
                        this.firstname = firstname;
                    }

                    public String getLastname() {
                        return lastname;
                    }

                    public void setLastname(String lastname) {
                        this.lastname = lastname;
                    }

                    public Object getFullname() {
                        return fullname;
                    }

                    public void setFullname(Object fullname) {
                        this.fullname = fullname;
                    }

                    public String getPhone() {
                        return phone;
                    }

                    public void setPhone(String phone) {
                        this.phone = phone;
                    }

                    public String getEmail() {
                        return email;
                    }

                    public void setEmail(String email) {
                        this.email = email;
                    }

                    public Object getCompany() {
                        return company;
                    }

                    public void setCompany(Object company) {
                        this.company = company;
                    }

                    public String getStatus() {
                        return status;
                    }

                    public void setStatus(String status) {
                        this.status = status;
                    }

                    public Integer getAffiliateId() {
                        return affiliateId;
                    }

                    public void setAffiliateId(Integer affiliateId) {
                        this.affiliateId = affiliateId;
                    }

                    public String getPercent() {
                        return percent;
                    }

                    public void setPercent(String percent) {
                        this.percent = percent;
                    }

                    public String getPendingBalance() {
                        return pendingBalance;
                    }

                    public void setPendingBalance(String pendingBalance) {
                        this.pendingBalance = pendingBalance;
                    }

                    public String getAvailableBalance() {
                        return availableBalance;
                    }

                    public void setAvailableBalance(String availableBalance) {
                        this.availableBalance = availableBalance;
                    }

                    public Integer getAllowHire() {
                        return allowHire;
                    }

                    public void setAllowHire(Integer allowHire) {
                        this.allowHire = allowHire;
                    }

                    public String getAvatar() {
                        return avatar;
                    }

                    public void setAvatar(String avatar) {
                        this.avatar = avatar;
                    }

                    public String getEmailVerifiedAt() {
                        return emailVerifiedAt;
                    }

                    public void setEmailVerifiedAt(String emailVerifiedAt) {
                        this.emailVerifiedAt = emailVerifiedAt;
                    }

                    public String getCreatedAt() {
                        return createdAt;
                    }

                    public void setCreatedAt(String createdAt) {
                        this.createdAt = createdAt;
                    }

                    public String getUpdatedAt() {
                        return updatedAt;
                    }

                    public void setUpdatedAt(String updatedAt) {
                        this.updatedAt = updatedAt;
                    }

                    public Object getDeletedAt() {
                        return deletedAt;
                    }

                    public void setDeletedAt(Object deletedAt) {
                        this.deletedAt = deletedAt;
                    }

                    public String getRole() {
                        return role;
                    }

                    public void setRole(String role) {
                        this.role = role;
                    }

                }

                public class Gig_ {

                    @SerializedName("id")
                    @Expose
                    private Integer id;
                    @SerializedName("user_id")
                    @Expose
                    private Integer userId;
                    @SerializedName("title")
                    @Expose
                    private String title;
                    @SerializedName("description")
                    @Expose
                    private String description;
                    @SerializedName("hours")
                    @Expose
                    private String hours;
                    @SerializedName("rate")
                    @Expose
                    private Integer rate;
                    @SerializedName("rate_type")
                    @Expose
                    private String rateType;
                    @SerializedName("status")
                    @Expose
                    private String status;
                    @SerializedName("created_at")
                    @Expose
                    private String createdAt;
                    @SerializedName("updated_at")
                    @Expose
                    private String updatedAt;

                    public Integer getId() {
                        return id;
                    }

                    public void setId(Integer id) {
                        this.id = id;
                    }

                    public Integer getUserId() {
                        return userId;
                    }

                    public void setUserId(Integer userId) {
                        this.userId = userId;
                    }

                    public String getTitle() {
                        return title;
                    }

                    public void setTitle(String title) {
                        this.title = title;
                    }

                    public String getDescription() {
                        return description;
                    }

                    public void setDescription(String description) {
                        this.description = description;
                    }

                    public String getHours() {
                        return hours;
                    }

                    public void setHours(String hours) {
                        this.hours = hours;
                    }

                    public Integer getRate() {
                        return rate;
                    }

                    public void setRate(Integer rate) {
                        this.rate = rate;
                    }

                    public String getRateType() {
                        return rateType;
                    }

                    public void setRateType(String rateType) {
                        this.rateType = rateType;
                    }

                    public String getStatus() {
                        return status;
                    }

                    public void setStatus(String status) {
                        this.status = status;
                    }

                    public String getCreatedAt() {
                        return createdAt;
                    }

                    public void setCreatedAt(String createdAt) {
                        this.createdAt = createdAt;
                    }

                    public String getUpdatedAt() {
                        return updatedAt;
                    }

                    public void setUpdatedAt(String updatedAt) {
                        this.updatedAt = updatedAt;
                    }

                }

            }
        }
    }
}