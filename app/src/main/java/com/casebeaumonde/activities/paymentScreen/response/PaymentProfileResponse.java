package com.casebeaumonde.activities.paymentScreen.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PaymentProfileResponse {

    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("data")
    @Expose
    private List<Datum> data = null;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }

    public class Datum {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("payable_type")
        @Expose
        private String payableType;
        @SerializedName("payable_id")
        @Expose
        private Integer payableId;
        @SerializedName("type")
        @Expose
        private String type;
        @SerializedName("user_id")
        @Expose
        private String userId;
        @SerializedName("customer_email")
        @Expose
        private Object customerEmail;
        @SerializedName("last_4")
        @Expose
        private String last4;
        @SerializedName("card_brand")
        @Expose
        private String cardBrand;
        @SerializedName("operation")
        @Expose
        private String operation;
        @SerializedName("amount")
        @Expose
        private String amount;
        @SerializedName("fee")
        @Expose
        private Object fee;
        @SerializedName("refund_amount")
        @Expose
        private String refundAmount;
        @SerializedName("account_type")
        @Expose
        private Object accountType;
        @SerializedName("account_id")
        @Expose
        private Object accountId;
        @SerializedName("description")
        @Expose
        private Object description;
        @SerializedName("transaction_id")
        @Expose
        private String transactionId;
        @SerializedName("ref_transaction_id")
        @Expose
        private Object refTransactionId;
        @SerializedName("auth_code")
        @Expose
        private Object authCode;
        @SerializedName("transaction_status")
        @Expose
        private String transactionStatus;
        @SerializedName("provider")
        @Expose
        private String provider;
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

        public String getPayableType() {
            return payableType;
        }

        public void setPayableType(String payableType) {
            this.payableType = payableType;
        }

        public Integer getPayableId() {
            return payableId;
        }

        public void setPayableId(Integer payableId) {
            this.payableId = payableId;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public Object getCustomerEmail() {
            return customerEmail;
        }

        public void setCustomerEmail(Object customerEmail) {
            this.customerEmail = customerEmail;
        }

        public String getLast4() {
            return last4;
        }

        public void setLast4(String last4) {
            this.last4 = last4;
        }

        public String getCardBrand() {
            return cardBrand;
        }

        public void setCardBrand(String cardBrand) {
            this.cardBrand = cardBrand;
        }

        public String getOperation() {
            return operation;
        }

        public void setOperation(String operation) {
            this.operation = operation;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public Object getFee() {
            return fee;
        }

        public void setFee(Object fee) {
            this.fee = fee;
        }

        public String getRefundAmount() {
            return refundAmount;
        }

        public void setRefundAmount(String refundAmount) {
            this.refundAmount = refundAmount;
        }

        public Object getAccountType() {
            return accountType;
        }

        public void setAccountType(Object accountType) {
            this.accountType = accountType;
        }

        public Object getAccountId() {
            return accountId;
        }

        public void setAccountId(Object accountId) {
            this.accountId = accountId;
        }

        public Object getDescription() {
            return description;
        }

        public void setDescription(Object description) {
            this.description = description;
        }

        public String getTransactionId() {
            return transactionId;
        }

        public void setTransactionId(String transactionId) {
            this.transactionId = transactionId;
        }

        public Object getRefTransactionId() {
            return refTransactionId;
        }

        public void setRefTransactionId(Object refTransactionId) {
            this.refTransactionId = refTransactionId;
        }

        public Object getAuthCode() {
            return authCode;
        }

        public void setAuthCode(Object authCode) {
            this.authCode = authCode;
        }

        public String getTransactionStatus() {
            return transactionStatus;
        }

        public void setTransactionStatus(String transactionStatus) {
            this.transactionStatus = transactionStatus;
        }

        public String getProvider() {
            return provider;
        }

        public void setProvider(String provider) {
            this.provider = provider;
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