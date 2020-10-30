package com.casebeaumonde.fragments.profile.profileResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PaymentMethodResponse {

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

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("user_id")
        @Expose
        private Integer userId;
        @SerializedName("payment_processor")
        @Expose
        private String paymentProcessor;
        @SerializedName("customer_profile_id")
        @Expose
        private String customerProfileId;
        @SerializedName("created_at")
        @Expose
        private String createdAt;
        @SerializedName("updated_at")
        @Expose
        private String updatedAt;
        @SerializedName("deleted_at")
        @Expose
        private Object deletedAt;
        @SerializedName("payment_profiles")
        @Expose
        private List<PaymentProfile> paymentProfiles = null;

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

        public String getPaymentProcessor() {
            return paymentProcessor;
        }

        public void setPaymentProcessor(String paymentProcessor) {
            this.paymentProcessor = paymentProcessor;
        }

        public String getCustomerProfileId() {
            return customerProfileId;
        }

        public void setCustomerProfileId(String customerProfileId) {
            this.customerProfileId = customerProfileId;
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

        public List<PaymentProfile> getPaymentProfiles() {
            return paymentProfiles;
        }

        public void setPaymentProfiles(List<PaymentProfile> paymentProfiles) {
            this.paymentProfiles = paymentProfiles;
        }

        public class PaymentProfile {

            @SerializedName("id")
            @Expose
            private Integer id;
            @SerializedName("customer_profile_id")
            @Expose
            private Integer customerProfileId;
            @SerializedName("payment_profile_id")
            @Expose
            private String paymentProfileId;
            @SerializedName("payment_method_type")
            @Expose
            private String paymentMethodType;
            @SerializedName("last_numbers")
            @Expose
            private String lastNumbers;
            @SerializedName("expiration_date")
            @Expose
            private String expirationDate;
            @SerializedName("brand")
            @Expose
            private String brand;
            @SerializedName("default")
            @Expose
            private Integer _default;
            @SerializedName("created_at")
            @Expose
            private String createdAt;
            @SerializedName("updated_at")
            @Expose
            private String updatedAt;
            @SerializedName("deleted_at")
            @Expose
            private Object deletedAt;

            public Integer getId() {
                return id;
            }

            public void setId(Integer id) {
                this.id = id;
            }

            public Integer getCustomerProfileId() {
                return customerProfileId;
            }

            public void setCustomerProfileId(Integer customerProfileId) {
                this.customerProfileId = customerProfileId;
            }

            public String getPaymentProfileId() {
                return paymentProfileId;
            }

            public void setPaymentProfileId(String paymentProfileId) {
                this.paymentProfileId = paymentProfileId;
            }

            public String getPaymentMethodType() {
                return paymentMethodType;
            }

            public void setPaymentMethodType(String paymentMethodType) {
                this.paymentMethodType = paymentMethodType;
            }

            public String getLastNumbers() {
                return lastNumbers;
            }

            public void setLastNumbers(String lastNumbers) {
                this.lastNumbers = lastNumbers;
            }

            public String getExpirationDate() {
                return expirationDate;
            }

            public void setExpirationDate(String expirationDate) {
                this.expirationDate = expirationDate;
            }

            public String getBrand() {
                return brand;
            }

            public void setBrand(String brand) {
                this.brand = brand;
            }

            public Integer getDefault() {
                return _default;
            }

            public void setDefault(Integer _default) {
                this._default = _default;
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

        }
    }
}