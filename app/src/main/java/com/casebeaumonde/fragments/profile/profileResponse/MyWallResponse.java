package com.casebeaumonde.fragments.profile.profileResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MyWallResponse {

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

        @SerializedName("fashionables")
        @Expose
        private List<Fashionable> fashionables = null;
        @SerializedName("file_path")
        @Expose
        private String filePath;

        public List<Fashionable> getFashionables() {
            return fashionables;
        }

        public void setFashionables(List<Fashionable> fashionables) {
            this.fashionables = fashionables;
        }

        public String getFilePath() {
            return filePath;
        }

        public void setFilePath(String filePath) {
            this.filePath = filePath;
        }

        public class Fashionable {

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
            @SerializedName("type")
            @Expose
            private String type;
            @SerializedName("creator")
            @Expose
            private Creator creator;
            @SerializedName("from")
            @Expose
            private String from;
            @SerializedName("to")
            @Expose
            private String to;
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

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public Creator getCreator() {
                return creator;
            }

            public void setCreator(Creator creator) {
                this.creator = creator;
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

            public Object getDeletedAt() {
                return deletedAt;
            }

            public void setDeletedAt(Object deletedAt) {
                this.deletedAt = deletedAt;
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
        }
    }
}