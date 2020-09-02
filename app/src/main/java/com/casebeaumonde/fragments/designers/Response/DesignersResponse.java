package com.casebeaumonde.fragments.designers.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DesignersResponse {

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

        @SerializedName("users")
        @Expose
        private List<User> users = null;

        public List<User> getUsers() {
            return users;
        }

        public void setUsers(List<User> users) {
            this.users = users;
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
            private String company;
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
//            @SerializedName("unread_notifications")
//            @Expose
//            private List<UnreadNotification> unreadNotifications = null;

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

            public String getCompany() {
                return company;
            }

            public void setCompany(String company) {
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

//            public List<UnreadNotification> getUnreadNotifications() {
//                return unreadNotifications;
//            }
//
//            public void setUnreadNotifications(List<UnreadNotification> unreadNotifications) {
//                this.unreadNotifications = unreadNotifications;
//            }

        }

        public class User_ {

            @SerializedName("id")
            @Expose
            private Integer id;
            @SerializedName("role")
            @Expose
            private String role;
            @SerializedName("email")
            @Expose
            private String email;
            @SerializedName("phone")
            @Expose
            private Object phone;
            @SerializedName("avatar")
            @Expose
            private Object avatar;
            @SerializedName("status")
            @Expose
            private String status;
            @SerializedName("company")
            @Expose
            private Object company;
            @SerializedName("percent")
            @Expose
            private String percent;
            @SerializedName("fullname")
            @Expose
            private Object fullname;
            @SerializedName("lastname")
            @Expose
            private String lastname;
            @SerializedName("firstname")
            @Expose
            private String firstname;
            @SerializedName("allow_hire")
            @Expose
            private Integer allowHire;
            @SerializedName("created_at")
            @Expose
            private String createdAt;
            @SerializedName("deleted_at")
            @Expose
            private Object deletedAt;
            @SerializedName("updated_at")
            @Expose
            private String updatedAt;
            @SerializedName("affiliate_id")
            @Expose
            private Integer affiliateId;
            @SerializedName("pending_balance")
            @Expose
            private String pendingBalance;
            @SerializedName("available_balance")
            @Expose
            private String availableBalance;
            @SerializedName("email_verified_at")
            @Expose
            private String emailVerifiedAt;
            @SerializedName("unread_notifications")
            @Expose
            private List<Object> unreadNotifications = null;

            public Integer getId() {
                return id;
            }

            public void setId(Integer id) {
                this.id = id;
            }

            public String getRole() {
                return role;
            }

            public void setRole(String role) {
                this.role = role;
            }

            public String getEmail() {
                return email;
            }

            public void setEmail(String email) {
                this.email = email;
            }

            public Object getPhone() {
                return phone;
            }

            public void setPhone(Object phone) {
                this.phone = phone;
            }

            public Object getAvatar() {
                return avatar;
            }

            public void setAvatar(Object avatar) {
                this.avatar = avatar;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public Object getCompany() {
                return company;
            }

            public void setCompany(Object company) {
                this.company = company;
            }

            public String getPercent() {
                return percent;
            }

            public void setPercent(String percent) {
                this.percent = percent;
            }

            public Object getFullname() {
                return fullname;
            }

            public void setFullname(Object fullname) {
                this.fullname = fullname;
            }

            public String getLastname() {
                return lastname;
            }

            public void setLastname(String lastname) {
                this.lastname = lastname;
            }

            public String getFirstname() {
                return firstname;
            }

            public void setFirstname(String firstname) {
                this.firstname = firstname;
            }

            public Integer getAllowHire() {
                return allowHire;
            }

            public void setAllowHire(Integer allowHire) {
                this.allowHire = allowHire;
            }

            public String getCreatedAt() {
                return createdAt;
            }

            public void setCreatedAt(String createdAt) {
                this.createdAt = createdAt;
            }

            public Object getDeletedAt() {
                return deletedAt;
            }

            public void setDeletedAt(Object deletedAt) {
                this.deletedAt = deletedAt;
            }

            public String getUpdatedAt() {
                return updatedAt;
            }

            public void setUpdatedAt(String updatedAt) {
                this.updatedAt = updatedAt;
            }

            public Integer getAffiliateId() {
                return affiliateId;
            }

            public void setAffiliateId(Integer affiliateId) {
                this.affiliateId = affiliateId;
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

            public String getEmailVerifiedAt() {
                return emailVerifiedAt;
            }

            public void setEmailVerifiedAt(String emailVerifiedAt) {
                this.emailVerifiedAt = emailVerifiedAt;
            }

            public List<Object> getUnreadNotifications() {
                return unreadNotifications;
            }

            public void setUnreadNotifications(List<Object> unreadNotifications) {
                this.unreadNotifications = unreadNotifications;
            }

        }
    }

}