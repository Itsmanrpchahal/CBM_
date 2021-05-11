package com.casebeaumonde.activities.MyEvents.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MyEventsResponse {

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

        @SerializedName("events")
        @Expose
        private Events events;

        public Events getEvents() {
            return events;
        }

        public void setEvents(Events events) {
            this.events = events;
        }

        public class Events {

            @SerializedName("current_page")
            @Expose
            private Integer currentPage;
            @SerializedName("data")
            @Expose
            private List<Datum> data = null;
            @SerializedName("first_page_url")
            @Expose
            private String firstPageUrl;
            @SerializedName("from")
            @Expose
            private Integer from;
            @SerializedName("last_page")
            @Expose
            private Integer lastPage;
            @SerializedName("last_page_url")
            @Expose
            private String lastPageUrl;
            @SerializedName("next_page_url")
            @Expose
            private Object nextPageUrl;
            @SerializedName("path")
            @Expose
            private String path;
            @SerializedName("per_page")
            @Expose
            private Integer perPage;
            @SerializedName("prev_page_url")
            @Expose
            private Object prevPageUrl;
            @SerializedName("to")
            @Expose
            private Integer to;
            @SerializedName("total")
            @Expose
            private Integer total;



            public Integer getCurrentPage() {
                return currentPage;
            }

            public void setCurrentPage(Integer currentPage) {
                this.currentPage = currentPage;
            }

            public List<Datum> getData() {
                return data;
            }

            public void setData(List<Datum> data) {
                this.data = data;
            }

            public String getFirstPageUrl() {
                return firstPageUrl;
            }

            public void setFirstPageUrl(String firstPageUrl) {
                this.firstPageUrl = firstPageUrl;
            }

            public Integer getFrom() {
                return from;
            }

            public void setFrom(Integer from) {
                this.from = from;
            }

            public Integer getLastPage() {
                return lastPage;
            }

            public void setLastPage(Integer lastPage) {
                this.lastPage = lastPage;
            }

            public String getLastPageUrl() {
                return lastPageUrl;
            }

            public void setLastPageUrl(String lastPageUrl) {
                this.lastPageUrl = lastPageUrl;
            }

            public Object getNextPageUrl() {
                return nextPageUrl;
            }

            public void setNextPageUrl(Object nextPageUrl) {
                this.nextPageUrl = nextPageUrl;
            }

            public String getPath() {
                return path;
            }

            public void setPath(String path) {
                this.path = path;
            }

            public Integer getPerPage() {
                return perPage;
            }

            public void setPerPage(Integer perPage) {
                this.perPage = perPage;
            }

            public Object getPrevPageUrl() {
                return prevPageUrl;
            }

            public void setPrevPageUrl(Object prevPageUrl) {
                this.prevPageUrl = prevPageUrl;
            }

            public Integer getTo() {
                return to;
            }

            public void setTo(Integer to) {
                this.to = to;
            }

            public Integer getTotal() {
                return total;
            }

            public void setTotal(Integer total) {
                this.total = total;
            }

            public class Datum {

                @SerializedName("id")
                @Expose
                private Integer id;
                @SerializedName("creator_id")
                @Expose
                private Integer creatorId;
                @SerializedName("user_id")
                @Expose
                private Integer userId;
                @SerializedName("title")
                @Expose
                private String title;
                @SerializedName("description")
                @Expose
                private String description;
                @SerializedName("image")
                @Expose
                private String image;
                @SerializedName("from")
                @Expose
                private String from;
                @SerializedName("to")
                @Expose
                private String to;
                @SerializedName("type")
                @Expose
                private String type;
                @SerializedName("status")
                @Expose
                private String status;
                @SerializedName("created_at")
                @Expose
                private String createdAt;
                @SerializedName("updated_at")
                @Expose
                private String updatedAt;
                @SerializedName("deleted_at")
                @Expose
                private Object deletedAt;
                @SerializedName("creator")
                @Expose
                private Creator creator;
                @SerializedName("user")
                @Expose
                private User user;

                public Integer getId() {
                    return id;
                }

                public void setId(Integer id) {
                    this.id = id;
                }

                public Integer getCreatorId() {
                    return creatorId;
                }

                public void setCreatorId(Integer creatorId) {
                    this.creatorId = creatorId;
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

                public String getImage() {
                    return image;
                }

                public void setImage(String image) {
                    this.image = image;
                }

                public String getFrom() {
                    return from;
                }

                public void setFrom(String from) {
                    this.from = from;
                }

                public String getTo() {
                    return to;
                }

                public void setTo(String to) {
                    this.to = to;
                }

                public String getType() {
                    return type;
                }

                public void setType(String type) {
                    this.type = type;
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

                public Object getDeletedAt() {
                    return deletedAt;
                }

                public void setDeletedAt(Object deletedAt) {
                    this.deletedAt = deletedAt;
                }

                public Creator getCreator() {
                    return creator;
                }

                public void setCreator(Creator creator) {
                    this.creator = creator;
                }

                public User getUser() {
                    return user;
                }

                public void setUser(User user) {
                    this.user = user;
                }

                public class Creator {

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
                    @SerializedName("chat_invitation")
                    @Expose
                    private String chatInvitation;
                    @SerializedName("avatar")
                    @Expose
                    private String avatar;
                    @SerializedName("email_verified_at")
                    @Expose
                    private String emailVerifiedAt;
                    @SerializedName("paypal_email")
                    @Expose
                    private String paypalEmail;
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

                    public String getChatInvitation() {
                        return chatInvitation;
                    }

                    public void setChatInvitation(String chatInvitation) {
                        this.chatInvitation = chatInvitation;
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

                    public String getPaypalEmail() {
                        return paypalEmail;
                    }

                    public void setPaypalEmail(String paypalEmail) {
                        this.paypalEmail = paypalEmail;
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
                    @SerializedName("chat_invitation")
                    @Expose
                    private String chatInvitation;
                    @SerializedName("avatar")
                    @Expose
                    private String avatar;
                    @SerializedName("email_verified_at")
                    @Expose
                    private String emailVerifiedAt;
                    @SerializedName("paypal_email")
                    @Expose
                    private Object paypalEmail;
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

                    public String getChatInvitation() {
                        return chatInvitation;
                    }

                    public void setChatInvitation(String chatInvitation) {
                        this.chatInvitation = chatInvitation;
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

                    public Object getPaypalEmail() {
                        return paypalEmail;
                    }

                    public void setPaypalEmail(Object paypalEmail) {
                        this.paypalEmail = paypalEmail;
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

            }
        }
    }
}