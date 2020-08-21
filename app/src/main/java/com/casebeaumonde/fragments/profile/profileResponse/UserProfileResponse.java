package com.casebeaumonde.fragments.profile.profileResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UserProfileResponse {

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

        @SerializedName("current_following")
        @Expose
        private Boolean currentFollowing;
        @SerializedName("fashionables")
        @Expose
        private List<Object> fashionables = null;
        @SerializedName("user")
        @Expose
        private User user;
        @SerializedName("file_path")
        @Expose
        private String filePath;

        public Boolean getCurrentFollowing() {
            return currentFollowing;
        }

        public void setCurrentFollowing(Boolean currentFollowing) {
            this.currentFollowing = currentFollowing;
        }

        public List<Object> getFashionables() {
            return fashionables;
        }

        public void setFashionables(List<Object> fashionables) {
            this.fashionables = fashionables;
        }

        public User getUser() {
            return user;
        }

        public void setUser(User user) {
            this.user = user;
        }

        public String getFilePath() {
            return filePath;
        }

        public void setFilePath(String filePath) {
            this.filePath = filePath;
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
            @SerializedName("unread_notifications")
            @Expose
            private List<Object> unreadNotifications = null;
            @SerializedName("closets")
            @Expose
            private List<Closet> closets = null;
            @SerializedName("followers")
            @Expose
            private List<Follower> followers = null;
            @SerializedName("following")
            @Expose
            private List<Object> following = null;
            @SerializedName("events")
            @Expose
            private List<Object> events = null;
            @SerializedName("items")
            @Expose
            private List<Object> items = null;
            @SerializedName("profile")
            @Expose
            private Profile profile;
            @SerializedName("customer_profile")
            @Expose
            private Object customerProfile;
            @SerializedName("roles")
            @Expose
            private List<Role> roles = null;
            @SerializedName("customer_subscription")
            @Expose
            private Object customerSubscription;
            @SerializedName("business_subscription")
            @Expose
            private Object businessSubscription;

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

            public List<Object> getUnreadNotifications() {
                return unreadNotifications;
            }

            public void setUnreadNotifications(List<Object> unreadNotifications) {
                this.unreadNotifications = unreadNotifications;
            }

            public List<Closet> getClosets() {
                return closets;
            }

            public void setClosets(List<Closet> closets) {
                this.closets = closets;
            }

            public List<Follower> getFollowers() {
                return followers;
            }

            public void setFollowers(List<Follower> followers) {
                this.followers = followers;
            }

            public List<Object> getFollowing() {
                return following;
            }

            public void setFollowing(List<Object> following) {
                this.following = following;
            }

            public List<Object> getEvents() {
                return events;
            }

            public void setEvents(List<Object> events) {
                this.events = events;
            }

            public List<Object> getItems() {
                return items;
            }

            public void setItems(List<Object> items) {
                this.items = items;
            }

            public Profile getProfile() {
                return profile;
            }

            public void setProfile(Profile profile) {
                this.profile = profile;
            }

            public Object getCustomerProfile() {
                return customerProfile;
            }

            public void setCustomerProfile(Object customerProfile) {
                this.customerProfile = customerProfile;
            }

            public List<Role> getRoles() {
                return roles;
            }

            public void setRoles(List<Role> roles) {
                this.roles = roles;
            }

            public Object getCustomerSubscription() {
                return customerSubscription;
            }

            public void setCustomerSubscription(Object customerSubscription) {
                this.customerSubscription = customerSubscription;
            }

            public Object getBusinessSubscription() {
                return businessSubscription;
            }

            public void setBusinessSubscription(Object businessSubscription) {
                this.businessSubscription = businessSubscription;
            }

            public class Profile {

                @SerializedName("id")
                @Expose
                private Integer id;
                @SerializedName("user_id")
                @Expose
                private Integer userId;
                @SerializedName("about_me")
                @Expose
                private Object aboutMe;
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

                public Integer getUserId() {
                    return userId;
                }

                public void setUserId(Integer userId) {
                    this.userId = userId;
                }

                public Object getAboutMe() {
                    return aboutMe;
                }

                public void setAboutMe(Object aboutMe) {
                    this.aboutMe = aboutMe;
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

            public class Closet {

                @SerializedName("id")
                @Expose
                private Integer id;
                @SerializedName("user_id")
                @Expose
                private Integer userId;
                @SerializedName("creator_id")
                @Expose
                private Integer creatorId;
                @SerializedName("title")
                @Expose
                private String title;
                @SerializedName("description")
                @Expose
                private String description;
                @SerializedName("status")
                @Expose
                private String status;
                @SerializedName("visibility")
                @Expose
                private String visibility;
                @SerializedName("def")
                @Expose
                private Integer def;
                @SerializedName("image")
                @Expose
                private String image;
                @SerializedName("created_at")
                @Expose
                private String createdAt;
                @SerializedName("updated_at")
                @Expose
                private String updatedAt;
                @SerializedName("creator")
                @Expose
                private Creator creator;

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

                public Integer getCreatorId() {
                    return creatorId;
                }

                public void setCreatorId(Integer creatorId) {
                    this.creatorId = creatorId;
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

                public String getStatus() {
                    return status;
                }

                public void setStatus(String status) {
                    this.status = status;
                }

                public String getVisibility() {
                    return visibility;
                }

                public void setVisibility(String visibility) {
                    this.visibility = visibility;
                }

                public Integer getDef() {
                    return def;
                }

                public void setDef(Integer def) {
                    this.def = def;
                }

                public String getImage() {
                    return image;
                }

                public void setImage(String image) {
                    this.image = image;
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

                public Creator getCreator() {
                    return creator;
                }

                public void setCreator(Creator creator) {
                    this.creator = creator;
                }

            }

            public class Follower {

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
                @SerializedName("pivot")
                @Expose
                private Pivot pivot;
                @SerializedName("unread_notifications")
                @Expose
                private List<Object> unreadNotifications = null;

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

                public Pivot getPivot() {
                    return pivot;
                }

                public void setPivot(Pivot pivot) {
                    this.pivot = pivot;
                }

                public List<Object> getUnreadNotifications() {
                    return unreadNotifications;
                }

                public void setUnreadNotifications(List<Object> unreadNotifications) {
                    this.unreadNotifications = unreadNotifications;
                }

                public class Pivot {

                    @SerializedName("leader_id")
                    @Expose
                    private Integer leaderId;
                    @SerializedName("follower_id")
                    @Expose
                    private Integer followerId;
                    @SerializedName("created_at")
                    @Expose
                    private String createdAt;
                    @SerializedName("updated_at")
                    @Expose
                    private String updatedAt;

                    public Integer getLeaderId() {
                        return leaderId;
                    }

                    public void setLeaderId(Integer leaderId) {
                        this.leaderId = leaderId;
                    }

                    public Integer getFollowerId() {
                        return followerId;
                    }

                    public void setFollowerId(Integer followerId) {
                        this.followerId = followerId;
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

            public class Role {

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
                @SerializedName("pivot")
                @Expose
                private Pivot_ pivot;

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

                public Pivot_ getPivot() {
                    return pivot;
                }

                public void setPivot(Pivot_ pivot) {
                    this.pivot = pivot;
                }

                public class Pivot_ {

                    @SerializedName("model_id")
                    @Expose
                    private Integer modelId;
                    @SerializedName("role_id")
                    @Expose
                    private Integer roleId;
                    @SerializedName("model_type")
                    @Expose
                    private String modelType;

                    public Integer getModelId() {
                        return modelId;
                    }

                    public void setModelId(Integer modelId) {
                        this.modelId = modelId;
                    }

                    public Integer getRoleId() {
                        return roleId;
                    }

                    public void setRoleId(Integer roleId) {
                        this.roleId = roleId;
                    }

                    public String getModelType() {
                        return modelType;
                    }

                    public void setModelType(String modelType) {
                        this.modelType = modelType;
                    }

                }

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
                @SerializedName("unread_notifications")
                @Expose
                private List<Object> unreadNotifications = null;

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