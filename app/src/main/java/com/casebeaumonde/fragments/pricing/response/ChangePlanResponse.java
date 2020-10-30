package com.casebeaumonde.fragments.pricing.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ChangePlanResponse {

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

        @SerializedName("message")
        @Expose
        private String message;
        @SerializedName("subscription")
        @Expose
        private Subscription subscription;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public Subscription getSubscription() {
            return subscription;
        }

        public void setSubscription(Subscription subscription) {
            this.subscription = subscription;
        }

        public class Subscription {

            @SerializedName("user_id")
            @Expose
            private Integer userId;
            @SerializedName("customer_plan_id")
            @Expose
            private Integer customerPlanId;
            @SerializedName("prev_customer_plan_id")
            @Expose
            private Integer prevCustomerPlanId;
            @SerializedName("active_at")
            @Expose
            private String activeAt;
            @SerializedName("monthly_price")
            @Expose
            private String  monthlyPrice;
            @SerializedName("annual_charge")
            @Expose
            private String annualCharge;
            @SerializedName("yearly_saving_percent")
            @Expose
            private String yearlySavingPercent;
            @SerializedName("amount")
            @Expose
            private String amount;
            @SerializedName("paid_annual_charge")
            @Expose
            private Boolean paidAnnualCharge;
            @SerializedName("expires_at")
            @Expose
            private String expiresAt;
            @SerializedName("trial_started_at")
            @Expose
            private String trialStartedAt;
            @SerializedName("trial_end_at")
            @Expose
            private String trialEndAt;
            @SerializedName("status")
            @Expose
            private String status;
            @SerializedName("next_payment_date")
            @Expose
            private String nextPaymentDate;
            @SerializedName("billing_day")
            @Expose
            private String billingDay;
            @SerializedName("updated_at")
            @Expose
            private String updatedAt;
            @SerializedName("created_at")
            @Expose
            private String createdAt;
            @SerializedName("id")
            @Expose
            private Integer id;
            @SerializedName("plan")
            @Expose
            private Plan plan;

            public Integer getUserId() {
                return userId;
            }

            public void setUserId(Integer userId) {
                this.userId = userId;
            }

            public Integer getCustomerPlanId() {
                return customerPlanId;
            }

            public void setCustomerPlanId(Integer customerPlanId) {
                this.customerPlanId = customerPlanId;
            }

            public Integer getPrevCustomerPlanId() {
                return prevCustomerPlanId;
            }

            public void setPrevCustomerPlanId(Integer prevCustomerPlanId) {
                this.prevCustomerPlanId = prevCustomerPlanId;
            }

            public String getActiveAt() {
                return activeAt;
            }

            public void setActiveAt(String activeAt) {
                this.activeAt = activeAt;
            }

            public String getMonthlyPrice() {
                return monthlyPrice;
            }

            public void setMonthlyPrice(String monthlyPrice) {
                this.monthlyPrice = monthlyPrice;
            }

            public Object getAnnualCharge() {
                return annualCharge;
            }

            public void setAnnualCharge(String annualCharge) {
                this.annualCharge = annualCharge;
            }

            public String getYearlySavingPercent() {
                return yearlySavingPercent;
            }

            public void setYearlySavingPercent(String yearlySavingPercent) {
                this.yearlySavingPercent = yearlySavingPercent;
            }

            public String getAmount() {
                return amount;
            }

            public void setAmount(String amount) {
                this.amount = amount;
            }

            public Boolean getPaidAnnualCharge() {
                return paidAnnualCharge;
            }

            public void setPaidAnnualCharge(Boolean paidAnnualCharge) {
                this.paidAnnualCharge = paidAnnualCharge;
            }

            public String getExpiresAt() {
                return expiresAt;
            }

            public void setExpiresAt(String expiresAt) {
                this.expiresAt = expiresAt;
            }

            public String getTrialStartedAt() {
                return trialStartedAt;
            }

            public void setTrialStartedAt(String trialStartedAt) {
                this.trialStartedAt = trialStartedAt;
            }

            public String getTrialEndAt() {
                return trialEndAt;
            }

            public void setTrialEndAt(String trialEndAt) {
                this.trialEndAt = trialEndAt;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getNextPaymentDate() {
                return nextPaymentDate;
            }

            public void setNextPaymentDate(String nextPaymentDate) {
                this.nextPaymentDate = nextPaymentDate;
            }

            public String getBillingDay() {
                return billingDay;
            }

            public void setBillingDay(String billingDay) {
                this.billingDay = billingDay;
            }

            public String getUpdatedAt() {
                return updatedAt;
            }

            public void setUpdatedAt(String updatedAt) {
                this.updatedAt = updatedAt;
            }

            public String getCreatedAt() {
                return createdAt;
            }

            public void setCreatedAt(String createdAt) {
                this.createdAt = createdAt;
            }

            public Integer getId() {
                return id;
            }

            public void setId(Integer id) {
                this.id = id;
            }

            public Plan getPlan() {
                return plan;
            }

            public void setPlan(Plan plan) {
                this.plan = plan;
            }

            public class Plan {

                @SerializedName("id")
                @Expose
                private Integer id;
                @SerializedName("name")
                @Expose
                private String name;
                @SerializedName("description")
                @Expose
                private String description;
                @SerializedName("monthly_price")
                @Expose
                private String monthlyPrice;
                @SerializedName("yearly_price")
                @Expose
                private String yearlyPrice;
                @SerializedName("yearly_saving_percent")
                @Expose
                private String yearlySavingPercent;
                @SerializedName("benefits")
                @Expose
                private Object benefits;
                @SerializedName("trial_days")
                @Expose
                private String trialDays;
                @SerializedName("interval")
                @Expose
                private String interval;
                @SerializedName("interval_count")
                @Expose
                private String intervalCount;
                @SerializedName("slug")
                @Expose
                private String slug;
                @SerializedName("match_profile_with_users")
                @Expose
                private Integer matchProfileWithUsers;
                @SerializedName("customer_ratings")
                @Expose
                private Integer customerRatings;
                @SerializedName("chat_support")
                @Expose
                private Integer chatSupport;
                @SerializedName("phone_support")
                @Expose
                private Integer phoneSupport;
                @SerializedName("designated_account_manager")
                @Expose
                private Integer designatedAccountManager;
                @SerializedName("number_of_closets")
                @Expose
                private Object numberOfClosets;
                @SerializedName("stylist_collaboration")
                @Expose
                private Integer stylistCollaboration;
                @SerializedName("allowed_storage_items")
                @Expose
                private Integer allowedStorageItems;
                @SerializedName("private_closets")
                @Expose
                private Integer privateClosets;
                @SerializedName("paired_with_stylists")
                @Expose
                private Integer pairedWithStylists;
                @SerializedName("styling_packages")
                @Expose
                private Integer stylingPackages;
                @SerializedName("invitations")
                @Expose
                private Integer invitations;
                @SerializedName("status")
                @Expose
                private String status;
                @SerializedName("created_at")
                @Expose
                private String createdAt;
                @SerializedName("updated_at")
                @Expose
                private String updatedAt;
                @SerializedName("annual_charge")
                @Expose
                private Object annualCharge;

                public Integer getId() {
                    return id;
                }

                public void setId(Integer id) {
                    this.id = id;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getDescription() {
                    return description;
                }

                public void setDescription(String description) {
                    this.description = description;
                }

                public String getMonthlyPrice() {
                    return monthlyPrice;
                }

                public void setMonthlyPrice(String monthlyPrice) {
                    this.monthlyPrice = monthlyPrice;
                }

                public String getYearlyPrice() {
                    return yearlyPrice;
                }

                public void setYearlyPrice(String yearlyPrice) {
                    this.yearlyPrice = yearlyPrice;
                }

                public String getYearlySavingPercent() {
                    return yearlySavingPercent;
                }

                public void setYearlySavingPercent(String yearlySavingPercent) {
                    this.yearlySavingPercent = yearlySavingPercent;
                }

                public Object getBenefits() {
                    return benefits;
                }

                public void setBenefits(Object benefits) {
                    this.benefits = benefits;
                }

                public String getTrialDays() {
                    return trialDays;
                }

                public void setTrialDays(String trialDays) {
                    this.trialDays = trialDays;
                }

                public String getInterval() {
                    return interval;
                }

                public void setInterval(String interval) {
                    this.interval = interval;
                }

                public String getIntervalCount() {
                    return intervalCount;
                }

                public void setIntervalCount(String intervalCount) {
                    this.intervalCount = intervalCount;
                }

                public String getSlug() {
                    return slug;
                }

                public void setSlug(String slug) {
                    this.slug = slug;
                }

                public Integer getMatchProfileWithUsers() {
                    return matchProfileWithUsers;
                }

                public void setMatchProfileWithUsers(Integer matchProfileWithUsers) {
                    this.matchProfileWithUsers = matchProfileWithUsers;
                }

                public Integer getCustomerRatings() {
                    return customerRatings;
                }

                public void setCustomerRatings(Integer customerRatings) {
                    this.customerRatings = customerRatings;
                }

                public Integer getChatSupport() {
                    return chatSupport;
                }

                public void setChatSupport(Integer chatSupport) {
                    this.chatSupport = chatSupport;
                }

                public Integer getPhoneSupport() {
                    return phoneSupport;
                }

                public void setPhoneSupport(Integer phoneSupport) {
                    this.phoneSupport = phoneSupport;
                }

                public Integer getDesignatedAccountManager() {
                    return designatedAccountManager;
                }

                public void setDesignatedAccountManager(Integer designatedAccountManager) {
                    this.designatedAccountManager = designatedAccountManager;
                }

                public Object getNumberOfClosets() {
                    return numberOfClosets;
                }

                public void setNumberOfClosets(Object numberOfClosets) {
                    this.numberOfClosets = numberOfClosets;
                }

                public Integer getStylistCollaboration() {
                    return stylistCollaboration;
                }

                public void setStylistCollaboration(Integer stylistCollaboration) {
                    this.stylistCollaboration = stylistCollaboration;
                }

                public Integer getAllowedStorageItems() {
                    return allowedStorageItems;
                }

                public void setAllowedStorageItems(Integer allowedStorageItems) {
                    this.allowedStorageItems = allowedStorageItems;
                }

                public Integer getPrivateClosets() {
                    return privateClosets;
                }

                public void setPrivateClosets(Integer privateClosets) {
                    this.privateClosets = privateClosets;
                }

                public Integer getPairedWithStylists() {
                    return pairedWithStylists;
                }

                public void setPairedWithStylists(Integer pairedWithStylists) {
                    this.pairedWithStylists = pairedWithStylists;
                }

                public Integer getStylingPackages() {
                    return stylingPackages;
                }

                public void setStylingPackages(Integer stylingPackages) {
                    this.stylingPackages = stylingPackages;
                }

                public Integer getInvitations() {
                    return invitations;
                }

                public void setInvitations(Integer invitations) {
                    this.invitations = invitations;
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

                public Object getAnnualCharge() {
                    return annualCharge;
                }

                public void setAnnualCharge(Object annualCharge) {
                    this.annualCharge = annualCharge;
                }

            }
        }
    }
}