package com.casebeaumonde.activities.myContracts.tabs.offers.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class OfferListResponse {

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
            @SerializedName("received_offers")
            @Expose
            private List<ReceivedOffer> receivedOffers = null;
            @SerializedName("sent_offers")
            @Expose
            private List<SentOffer> sentOffers = null;
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

            public List<ReceivedOffer> getReceivedOffers() {
                return receivedOffers;
            }

            public void setReceivedOffers(List<ReceivedOffer> receivedOffers) {
                this.receivedOffers = receivedOffers;
            }

            public List<SentOffer> getSentOffers() {
                return sentOffers;
            }

            public void setSentOffers(List<SentOffer> sentOffers) {
                this.sentOffers = sentOffers;
            }

            public String getRole() {
                return role;
            }

            public void setRole(String role) {
                this.role = role;
            }

            public class ReceivedOffer {

                @SerializedName("id")
                @Expose
                private Integer id;
                @SerializedName("user_id")
                @Expose
                private Integer userId;
                @SerializedName("designer_id")
                @Expose
                private Integer designerId;
                @SerializedName("gig_id")
                @Expose
                private Integer gigId;
                @SerializedName("rate_type")
                @Expose
                private String rateType;
                @SerializedName("rate")
                @Expose
                private String rate;
                @SerializedName("comments")
                @Expose
                private Object comments;
                @SerializedName("status")
                @Expose
                private String status;
                @SerializedName("created_at")
                @Expose
                private String createdAt;
                @SerializedName("updated_at")
                @Expose
                private String updatedAt;
                @SerializedName("user")
                @Expose
                private User_ user;
                @SerializedName("designer")
                @Expose
                private Designer designer;
                @SerializedName("gig")
                @Expose
                private Gig gig;

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

                public Integer getDesignerId() {
                    return designerId;
                }

                public void setDesignerId(Integer designerId) {
                    this.designerId = designerId;
                }

                public Integer getGigId() {
                    return gigId;
                }

                public void setGigId(Integer gigId) {
                    this.gigId = gigId;
                }

                public String getRateType() {
                    return rateType;
                }

                public void setRateType(String rateType) {
                    this.rateType = rateType;
                }

                public String getRate() {
                    return rate;
                }

                public void setRate(String rate) {
                    this.rate = rate;
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

                public User_ getUser() {
                    return user;
                }

                public void setUser(User_ user) {
                    this.user = user;
                }

                public Designer getDesigner() {
                    return designer;
                }

                public void setDesigner(Designer designer) {
                    this.designer = designer;
                }

                public Gig getGig() {
                    return gig;
                }

                public void setGig(Gig gig) {
                    this.gig = gig;
                }

                public class User_ {

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

                public class Designer {

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

            public class SentOffer {

                @SerializedName("id")
                @Expose
                private Integer id;
                @SerializedName("user_id")
                @Expose
                private Integer userId;
                @SerializedName("designer_id")
                @Expose
                private Integer designerId;
                @SerializedName("gig_id")
                @Expose
                private Integer gigId;
                @SerializedName("rate_type")
                @Expose
                private String rateType;
                @SerializedName("rate")
                @Expose
                private String rate;
                @SerializedName("comments")
                @Expose
                private Object comments;
                @SerializedName("status")
                @Expose
                private String status;
                @SerializedName("created_at")
                @Expose
                private String createdAt;
                @SerializedName("updated_at")
                @Expose
                private String updatedAt;
                @SerializedName("user")
                @Expose
                private User_ user;
                @SerializedName("designer")
                @Expose
                private Designer designer;
                @SerializedName("gig")
                @Expose
                private Gig gig;

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

                public Integer getDesignerId() {
                    return designerId;
                }

                public void setDesignerId(Integer designerId) {
                    this.designerId = designerId;
                }

                public Integer getGigId() {
                    return gigId;
                }

                public void setGigId(Integer gigId) {
                    this.gigId = gigId;
                }

                public String getRateType() {
                    return rateType;
                }

                public void setRateType(String rateType) {
                    this.rateType = rateType;
                }

                public String getRate() {
                    return rate;
                }

                public void setRate(String rate) {
                    this.rate = rate;
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

                public User_ getUser() {
                    return user;
                }

                public void setUser(User_ user) {
                    this.user = user;
                }

                public Designer getDesigner() {
                    return designer;
                }

                public void setDesigner(Designer designer) {
                    this.designer = designer;
                }

                public Gig getGig() {
                    return gig;
                }

                public void setGig(Gig gig) {
                    this.gig = gig;
                }

                public class User_ {

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

                public class Designer {

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
        }
    }
}