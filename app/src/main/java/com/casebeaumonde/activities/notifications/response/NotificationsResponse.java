package com.casebeaumonde.activities.notifications.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NotificationsResponse {

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

        @SerializedName("notification")
        @Expose
        private List<Notification> notification = null;

        public List<Notification> getNotification() {
            return notification;
        }

        public void setNotification(List<Notification> notification) {
            this.notification = notification;
        }

        public class Notification {

            @SerializedName("id")
            @Expose
            private String id;
            @SerializedName("type")
            @Expose
            private String type;
            @SerializedName("notifiable_type")
            @Expose
            private String notifiableType;
            @SerializedName("notifiable_id")
            @Expose
            private Integer notifiableId;
            @SerializedName("data")
            @Expose
            private Data_ data;
            @SerializedName("read_at")
            @Expose
            private String readAt;
            @SerializedName("opened_at")
            @Expose
            private Object openedAt;
            @SerializedName("created_at")
            @Expose
            private String createdAt;
            @SerializedName("updated_at")
            @Expose
            private String updatedAt;
            @SerializedName("message")
            @Expose
            private String message;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getNotifiableType() {
                return notifiableType;
            }

            public void setNotifiableType(String notifiableType) {
                this.notifiableType = notifiableType;
            }

            public Integer getNotifiableId() {
                return notifiableId;
            }

            public void setNotifiableId(Integer notifiableId) {
                this.notifiableId = notifiableId;
            }

            public Data_ getData() {
                return data;
            }

            public void setData(Data_ data) {
                this.data = data;
            }

            public String getReadAt() {
                return readAt;
            }

            public void setReadAt(String readAt) {
                this.readAt = readAt;
            }

            public Object getOpenedAt() {
                return openedAt;
            }

            public void setOpenedAt(Object openedAt) {
                this.openedAt = openedAt;
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

            public String getMessage() {
                return message;
            }

            public void setMessage(String message) {
                this.message = message;
            }

            public class Data_ {

                @SerializedName("type")
                @Expose
                private String type;
                @SerializedName("user")
                @Expose
                private User user;
                @SerializedName("created_at")
                @Expose
                private String createdAt;
                @SerializedName("open_in_chat")
                @Expose
                private String openInChat;

                public String getType() {
                    return type;
                }

                public void setType(String type) {
                    this.type = type;
                }

                public User getUser() {
                    return user;
                }

                public void setUser(User user) {
                    this.user = user;
                }

                public String getCreatedAt() {
                    return createdAt;
                }

                public void setCreatedAt(String createdAt) {
                    this.createdAt = createdAt;
                }

                public String getOpenInChat() {
                    return openInChat;
                }

                public void setOpenInChat(String openInChat) {
                    this.openInChat = openInChat;
                }

                public class User {

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
                    private String phone;
                    @SerializedName("avatar")
                    @Expose
                    private String avatar;
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
                    private String fullname;
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

                    public String getPhone() {
                        return phone;
                    }

                    public void setPhone(String phone) {
                        this.phone = phone;
                    }

                    public String getAvatar() {
                        return avatar;
                    }

                    public void setAvatar(String avatar) {
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

                    public String getFullname() {
                        return fullname;
                    }

                    public void setFullname(String fullname) {
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
    }

}