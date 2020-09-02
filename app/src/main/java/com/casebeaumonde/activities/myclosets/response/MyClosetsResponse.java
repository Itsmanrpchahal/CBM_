package com.casebeaumonde.activities.myclosets.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MyClosetsResponse {

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

        @SerializedName("closet")
        @Expose
        private List<Closet> closet = null;
        @SerializedName("current_following")
        @Expose
        private Boolean currentFollowing;
        @SerializedName("categories")
        @Expose
        private List<Category> categories = null;
        @SerializedName("brands")
        @Expose
        private List<Brand> brands = null;
        @SerializedName("sizes")
        @Expose
        private List<Size> sizes = null;
        @SerializedName("colors")
        @Expose
        private List<Color> colors = null;

        public List<Closet> getCloset() {
            return closet;
        }

        public void setCloset(List<Closet> closet) {
            this.closet = closet;
        }

        public Boolean getCurrentFollowing() {
            return currentFollowing;
        }

        public void setCurrentFollowing(Boolean currentFollowing) {
            this.currentFollowing = currentFollowing;
        }

        public List<Category> getCategories() {
            return categories;
        }

        public void setCategories(List<Category> categories) {
            this.categories = categories;
        }

        public List<Brand> getBrands() {
            return brands;
        }

        public void setBrands(List<Brand> brands) {
            this.brands = brands;
        }

        public List<Size> getSizes() {
            return sizes;
        }

        public void setSizes(List<Size> sizes) {
            this.sizes = sizes;
        }

        public List<Color> getColors() {
            return colors;
        }

        public void setColors(List<Color> colors) {
            this.colors = colors;
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

        public class Brand {

            @SerializedName("id")
            @Expose
            private Integer id;
            @SerializedName("user_id")
            @Expose
            private Object userId;
            @SerializedName("name")
            @Expose
            private String name;
            @SerializedName("description")
            @Expose
            private Object description;
            @SerializedName("status")
            @Expose
            private String status;
            @SerializedName("logo")
            @Expose
            private Object logo;
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

            public Object getUserId() {
                return userId;
            }

            public void setUserId(Object userId) {
                this.userId = userId;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public Object getDescription() {
                return description;
            }

            public void setDescription(Object description) {
                this.description = description;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public Object getLogo() {
                return logo;
            }

            public void setLogo(Object logo) {
                this.logo = logo;
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

        public class Color {

            @SerializedName("id")
            @Expose
            private Integer id;
            @SerializedName("user_id")
            @Expose
            private Object userId;
            @SerializedName("name")
            @Expose
            private String name;
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

            public Object getUserId() {
                return userId;
            }

            public void setUserId(Object userId) {
                this.userId = userId;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
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

        public class Size {

            @SerializedName("id")
            @Expose
            private Integer id;
            @SerializedName("name")
            @Expose
            private String name;
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

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
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

        public class Category {

            @SerializedName("id")
            @Expose
            private Integer id;
            @SerializedName("parent_id")
            @Expose
            private Integer parentId;
            @SerializedName("name")
            @Expose
            private String name;
            @SerializedName("description")
            @Expose
            private String description;
            @SerializedName("active")
            @Expose
            private Integer active;
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

            public Integer getParentId() {
                return parentId;
            }

            public void setParentId(Integer parentId) {
                this.parentId = parentId;
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

            public Integer getActive() {
                return active;
            }

            public void setActive(Integer active) {
                this.active = active;
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