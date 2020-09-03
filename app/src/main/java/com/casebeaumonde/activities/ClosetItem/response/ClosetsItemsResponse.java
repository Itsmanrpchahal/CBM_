package com.casebeaumonde.activities.ClosetItem.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ClosetsItemsResponse {

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
        private Closet closet;

        public Closet getCloset() {
            return closet;
        }

        public void setCloset(Closet closet) {
            this.closet = closet;
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
            @SerializedName("items")
            @Expose
            private List<Item> items = null;
            @SerializedName("creator")
            @Expose
            private Creator_ creator;

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

            public List<Item> getItems() {
                return items;
            }

            public void setItems(List<Item> items) {
                this.items = items;
            }

            public Creator_ getCreator() {
                return creator;
            }

            public void setCreator(Creator_ creator) {
                this.creator = creator;
            }

            public class Item {

                @SerializedName("id")
                @Expose
                private Integer id;
                @SerializedName("parent_id")
                @Expose
                private Integer parentId;
                @SerializedName("creator_id")
                @Expose
                private Integer creatorId;
                @SerializedName("fashionable_type")
                @Expose
                private String fashionableType;
                @SerializedName("fashionable_id")
                @Expose
                private Integer fashionableId;
                @SerializedName("title")
                @Expose
                private String title;
                @SerializedName("description")
                @Expose
                private String description;
                @SerializedName("category_id")
                @Expose
                private Integer categoryId;
                @SerializedName("size")
                @Expose
                private Size size;
                @SerializedName("color")
                @Expose
                private Color color;
                @SerializedName("brand")
                @Expose
                private Brand brand;
                @SerializedName("price")
                @Expose
                private Integer price;
                @SerializedName("picture")
                @Expose
                private String picture;
                @SerializedName("external_url")
                @Expose
                private Object externalUrl;
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
                @SerializedName("category")
                @Expose
                private Category category;
                @SerializedName("hearts")
                @Expose
                private List<Heart> hearts = null;

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

                public Integer getCreatorId() {
                    return creatorId;
                }

                public void setCreatorId(Integer creatorId) {
                    this.creatorId = creatorId;
                }

                public String getFashionableType() {
                    return fashionableType;
                }

                public void setFashionableType(String fashionableType) {
                    this.fashionableType = fashionableType;
                }

                public Integer getFashionableId() {
                    return fashionableId;
                }

                public void setFashionableId(Integer fashionableId) {
                    this.fashionableId = fashionableId;
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

                public Integer getCategoryId() {
                    return categoryId;
                }

                public void setCategoryId(Integer categoryId) {
                    this.categoryId = categoryId;
                }

                public Size getSize() {
                    return size;
                }

                public void setSize(Size size) {
                    this.size = size;
                }

                public Color getColor() {
                    return color;
                }

                public void setColor(Color color) {
                    this.color = color;
                }

                public Brand getBrand() {
                    return brand;
                }

                public void setBrand(Brand brand) {
                    this.brand = brand;
                }

                public Integer getPrice() {
                    return price;
                }

                public void setPrice(Integer price) {
                    this.price = price;
                }

                public String getPicture() {
                    return picture;
                }

                public void setPicture(String picture) {
                    this.picture = picture;
                }

                public Object getExternalUrl() {
                    return externalUrl;
                }

                public void setExternalUrl(Object externalUrl) {
                    this.externalUrl = externalUrl;
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

                public Category getCategory() {
                    return category;
                }

                public void setCategory(Category category) {
                    this.category = category;
                }

                public List<Heart> getHearts() {
                    return hearts;
                }

                public void setHearts(List<Heart> hearts) {
                    this.hearts = hearts;
                }

                public class Heart {

                    @SerializedName("id")
                    @Expose
                    private Integer id;
                    @SerializedName("user_id")
                    @Expose
                    private Integer userId;
                    @SerializedName("heartable_type")
                    @Expose
                    private String heartableType;
                    @SerializedName("heartable_id")
                    @Expose
                    private Integer heartableId;
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

                    public Integer getUserId() {
                        return userId;
                    }

                    public void setUserId(Integer userId) {
                        this.userId = userId;
                    }

                    public String getHeartableType() {
                        return heartableType;
                    }

                    public void setHeartableType(String heartableType) {
                        this.heartableType = heartableType;
                    }

                    public Integer getHeartableId() {
                        return heartableId;
                    }

                    public void setHeartableId(Integer heartableId) {
                        this.heartableId = heartableId;
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

            public class Creator_ {

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
                @SerializedName("avatar")
                @Expose
                private String avatar;
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

                public String getAvatar() {
                    return avatar;
                }

                public void setAvatar(String avatar) {
                    this.avatar = avatar;
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
        }
    }
}