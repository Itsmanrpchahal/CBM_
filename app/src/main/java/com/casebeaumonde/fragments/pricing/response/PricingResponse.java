package com.casebeaumonde.fragments.pricing.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PricingResponse {

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

        @SerializedName("customer_plans")
        @Expose
        private List<CustomerPlan> customerPlans = null;
        @SerializedName("business_plans")
        @Expose
        private List<BusinessPlan> businessPlans = null;
        @SerializedName("business_roles")
        @Expose
        private List<BusinessRole> businessRoles = null;
        @SerializedName("customer_roles")
        @Expose
        private List<CustomerRole> customerRoles = null;

        public List<CustomerPlan> getCustomerPlans() {
            return customerPlans;
        }

        public void setCustomerPlans(List<CustomerPlan> customerPlans) {
            this.customerPlans = customerPlans;
        }

        public List<BusinessPlan> getBusinessPlans() {
            return businessPlans;
        }

        public void setBusinessPlans(List<BusinessPlan> businessPlans) {
            this.businessPlans = businessPlans;
        }

        public List<BusinessRole> getBusinessRoles() {
            return businessRoles;
        }

        public void setBusinessRoles(List<BusinessRole> businessRoles) {
            this.businessRoles = businessRoles;
        }

        public List<CustomerRole> getCustomerRoles() {
            return customerRoles;
        }

        public void setCustomerRoles(List<CustomerRole> customerRoles) {
            this.customerRoles = customerRoles;
        }

        public class CustomerPlan {

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
            @SerializedName("plan_features")
            @Expose
            private List<PlanFeature> planFeatures = null;

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

            public List<PlanFeature> getPlanFeatures() {
                return planFeatures;
            }

            public void setPlanFeatures(List<PlanFeature> planFeatures) {
                this.planFeatures = planFeatures;
            }

            public class PlanFeature {

                @SerializedName("id")
                @Expose
                private Integer id;
                @SerializedName("featureable_id")
                @Expose
                private Integer featureableId;
                @SerializedName("featureable_type")
                @Expose
                private String featureableType;
                @SerializedName("feature")
                @Expose
                private String feature;
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

                public Integer getFeatureableId() {
                    return featureableId;
                }

                public void setFeatureableId(Integer featureableId) {
                    this.featureableId = featureableId;
                }

                public String getFeatureableType() {
                    return featureableType;
                }

                public void setFeatureableType(String featureableType) {
                    this.featureableType = featureableType;
                }

                public String getFeature() {
                    return feature;
                }

                public void setFeature(String feature) {
                    this.feature = feature;
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


        public class BusinessPlan {

            @SerializedName("id")
            @Expose
            private Integer id;
            @SerializedName("name")
            @Expose
            private String name;
            @SerializedName("description")
            @Expose
            private String description;
            @SerializedName("slug")
            @Expose
            private String slug;
            @SerializedName("commission_percentage")
            @Expose
            private Integer commissionPercentage;
            @SerializedName("interval")
            @Expose
            private String interval;
            @SerializedName("interval_count")
            @Expose
            private Integer intervalCount;
            @SerializedName("monthly_price")
            @Expose
            private String monthlyPrice;
            @SerializedName("number_of_users")
            @Expose
            private String numberOfUsers;
            @SerializedName("yearly_price")
            @Expose
            private String yearlyPrice;
            @SerializedName("annual_charge")
            @Expose
            private String annualCharge;
            @SerializedName("yearly_saving_percent")
            @Expose
            private String yearlySavingPercent;
            @SerializedName("trial_days")
            @Expose
            private String trialDays;
            @SerializedName("benefits")
            @Expose
            private String benefits;
            @SerializedName("allowed_sale_packages")
            @Expose
            private Integer allowedSalePackages;
            @SerializedName("match_profile_with_users_interval")
            @Expose
            private String matchProfileWithUsersInterval;
            @SerializedName("match_profile_with_users_interval_count")
            @Expose
            private Integer matchProfileWithUsersIntervalCount;
            @SerializedName("exclusive_affiliate_programs")
            @Expose
            private Integer exclusiveAffiliatePrograms;
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
            @SerializedName("featured_on_cbm")
            @Expose
            private String featuredOnCbm;
            @SerializedName("weekly_picks_featured")
            @Expose
            private Integer weeklyPicksFeatured;
            @SerializedName("preferred_placement_search")
            @Expose
            private Integer preferredPlacementSearch;
            @SerializedName("suggest_closets")
            @Expose
            private Integer suggestClosets;
            @SerializedName("cbm_style_hours_interval")
            @Expose
            private String cbmStyleHoursInterval;
            @SerializedName("cbm_style_hours_interval_count")
            @Expose
            private Integer cbmStyleHoursIntervalCount;
            @SerializedName("affiliate_marketing_help")
            @Expose
            private Integer affiliateMarketingHelp;
            @SerializedName("commission_on_affiliate")
            @Expose
            private Object commissionOnAffiliate;
            @SerializedName("export_data")
            @Expose
            private Integer exportData;
            @SerializedName("subscription_report")
            @Expose
            private Integer subscriptionReport;
            @SerializedName("mrr_report")
            @Expose
            private Integer mrrReport;
            @SerializedName("create_events_for_sale")
            @Expose
            private Integer createEventsForSale;
            @SerializedName("create_live_store_events")
            @Expose
            private Integer createLiveStoreEvents;
            @SerializedName("event_partneship")
            @Expose
            private Integer eventPartneship;
            @SerializedName("created_benefits")
            @Expose
            private Object createdBenefits;
            @SerializedName("status")
            @Expose
            private String status;
            @SerializedName("created_at")
            @Expose
            private String createdAt;
            @SerializedName("updated_at")
            @Expose
            private String updatedAt;
            @SerializedName("plan_features")
            @Expose
            private List<PlanFeature_> planFeatures = null;

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

            public String getSlug() {
                return slug;
            }

            public void setSlug(String slug) {
                this.slug = slug;
            }

            public Integer getCommissionPercentage() {
                return commissionPercentage;
            }

            public void setCommissionPercentage(Integer commissionPercentage) {
                this.commissionPercentage = commissionPercentage;
            }

            public String getInterval() {
                return interval;
            }

            public void setInterval(String interval) {
                this.interval = interval;
            }

            public Integer getIntervalCount() {
                return intervalCount;
            }

            public void setIntervalCount(Integer intervalCount) {
                this.intervalCount = intervalCount;
            }

            public String getMonthlyPrice() {
                return monthlyPrice;
            }

            public void setMonthlyPrice(String monthlyPrice) {
                this.monthlyPrice = monthlyPrice;
            }

            public String getNumberOfUsers() {
                return numberOfUsers;
            }

            public void setNumberOfUsers(String numberOfUsers) {
                this.numberOfUsers = numberOfUsers;
            }

            public String getYearlyPrice() {
                return yearlyPrice;
            }

            public void setYearlyPrice(String yearlyPrice) {
                this.yearlyPrice = yearlyPrice;
            }

            public String getAnnualCharge() {
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

            public String getTrialDays() {
                return trialDays;
            }

            public void setTrialDays(String trialDays) {
                this.trialDays = trialDays;
            }

            public String getBenefits() {
                return benefits;
            }

            public void setBenefits(String benefits) {
                this.benefits = benefits;
            }

            public Integer getAllowedSalePackages() {
                return allowedSalePackages;
            }

            public void setAllowedSalePackages(Integer allowedSalePackages) {
                this.allowedSalePackages = allowedSalePackages;
            }

            public String getMatchProfileWithUsersInterval() {
                return matchProfileWithUsersInterval;
            }

            public void setMatchProfileWithUsersInterval(String matchProfileWithUsersInterval) {
                this.matchProfileWithUsersInterval = matchProfileWithUsersInterval;
            }

            public Integer getMatchProfileWithUsersIntervalCount() {
                return matchProfileWithUsersIntervalCount;
            }

            public void setMatchProfileWithUsersIntervalCount(Integer matchProfileWithUsersIntervalCount) {
                this.matchProfileWithUsersIntervalCount = matchProfileWithUsersIntervalCount;
            }

            public Integer getExclusiveAffiliatePrograms() {
                return exclusiveAffiliatePrograms;
            }

            public void setExclusiveAffiliatePrograms(Integer exclusiveAffiliatePrograms) {
                this.exclusiveAffiliatePrograms = exclusiveAffiliatePrograms;
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

            public String getFeaturedOnCbm() {
                return featuredOnCbm;
            }

            public void setFeaturedOnCbm(String featuredOnCbm) {
                this.featuredOnCbm = featuredOnCbm;
            }

            public Integer getWeeklyPicksFeatured() {
                return weeklyPicksFeatured;
            }

            public void setWeeklyPicksFeatured(Integer weeklyPicksFeatured) {
                this.weeklyPicksFeatured = weeklyPicksFeatured;
            }

            public Integer getPreferredPlacementSearch() {
                return preferredPlacementSearch;
            }

            public void setPreferredPlacementSearch(Integer preferredPlacementSearch) {
                this.preferredPlacementSearch = preferredPlacementSearch;
            }

            public Integer getSuggestClosets() {
                return suggestClosets;
            }

            public void setSuggestClosets(Integer suggestClosets) {
                this.suggestClosets = suggestClosets;
            }

            public String getCbmStyleHoursInterval() {
                return cbmStyleHoursInterval;
            }

            public void setCbmStyleHoursInterval(String cbmStyleHoursInterval) {
                this.cbmStyleHoursInterval = cbmStyleHoursInterval;
            }

            public Integer getCbmStyleHoursIntervalCount() {
                return cbmStyleHoursIntervalCount;
            }

            public void setCbmStyleHoursIntervalCount(Integer cbmStyleHoursIntervalCount) {
                this.cbmStyleHoursIntervalCount = cbmStyleHoursIntervalCount;
            }

            public Integer getAffiliateMarketingHelp() {
                return affiliateMarketingHelp;
            }

            public void setAffiliateMarketingHelp(Integer affiliateMarketingHelp) {
                this.affiliateMarketingHelp = affiliateMarketingHelp;
            }

            public Object getCommissionOnAffiliate() {
                return commissionOnAffiliate;
            }

            public void setCommissionOnAffiliate(Object commissionOnAffiliate) {
                this.commissionOnAffiliate = commissionOnAffiliate;
            }

            public Integer getExportData() {
                return exportData;
            }

            public void setExportData(Integer exportData) {
                this.exportData = exportData;
            }

            public Integer getSubscriptionReport() {
                return subscriptionReport;
            }

            public void setSubscriptionReport(Integer subscriptionReport) {
                this.subscriptionReport = subscriptionReport;
            }

            public Integer getMrrReport() {
                return mrrReport;
            }

            public void setMrrReport(Integer mrrReport) {
                this.mrrReport = mrrReport;
            }

            public Integer getCreateEventsForSale() {
                return createEventsForSale;
            }

            public void setCreateEventsForSale(Integer createEventsForSale) {
                this.createEventsForSale = createEventsForSale;
            }

            public Integer getCreateLiveStoreEvents() {
                return createLiveStoreEvents;
            }

            public void setCreateLiveStoreEvents(Integer createLiveStoreEvents) {
                this.createLiveStoreEvents = createLiveStoreEvents;
            }

            public Integer getEventPartneship() {
                return eventPartneship;
            }

            public void setEventPartneship(Integer eventPartneship) {
                this.eventPartneship = eventPartneship;
            }

            public Object getCreatedBenefits() {
                return createdBenefits;
            }

            public void setCreatedBenefits(Object createdBenefits) {
                this.createdBenefits = createdBenefits;
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

            public List<PlanFeature_> getPlanFeatures() {
                return planFeatures;
            }

            public void setPlanFeatures(List<PlanFeature_> planFeatures) {
                this.planFeatures = planFeatures;
            }

            public class PlanFeature_ {

                @SerializedName("id")
                @Expose
                private Integer id;
                @SerializedName("featureable_id")
                @Expose
                private Integer featureableId;
                @SerializedName("featureable_type")
                @Expose
                private String featureableType;
                @SerializedName("feature")
                @Expose
                private String feature;
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

                public Integer getFeatureableId() {
                    return featureableId;
                }

                public void setFeatureableId(Integer featureableId) {
                    this.featureableId = featureableId;
                }

                public String getFeatureableType() {
                    return featureableType;
                }

                public void setFeatureableType(String featureableType) {
                    this.featureableType = featureableType;
                }

                public String getFeature() {
                    return feature;
                }

                public void setFeature(String feature) {
                    this.feature = feature;
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

        public class BusinessRole {

            @SerializedName("id")
            @Expose
            private Integer id;
            @SerializedName("name")
            @Expose
            private String name;
            @SerializedName("guard_name")
            @Expose
            private String guardName;
            @SerializedName("url")
            @Expose
            private String url;
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

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getGuardName() {
                return guardName;
            }

            public void setGuardName(String guardName) {
                this.guardName = guardName;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
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

        public class CustomerRole {

            @SerializedName("id")
            @Expose
            private Integer id;
            @SerializedName("name")
            @Expose
            private String name;
            @SerializedName("guard_name")
            @Expose
            private String guardName;
            @SerializedName("url")
            @Expose
            private String url;
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

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getGuardName() {
                return guardName;
            }

            public void setGuardName(String guardName) {
                this.guardName = guardName;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
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