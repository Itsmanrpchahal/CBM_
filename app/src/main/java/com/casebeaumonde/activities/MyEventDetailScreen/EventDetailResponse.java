package com.casebeaumonde.activities.MyEventDetailScreen;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class EventDetailResponse {

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
        @SerializedName("closet")
        @Expose
        private List<Closet> closet = null;

        public Events getEvents() {
            return events;
        }

        public void setEvents(Events events) {
            this.events = events;
        }

        public List<Closet> getCloset() {
            return closet;
        }

        public void setCloset(List<Closet> closet) {
            this.closet = closet;
        }

        public class Closet {

            @SerializedName("id")
            @Expose
            private Integer id;
            @SerializedName("title")
            @Expose
            private String title;
            @SerializedName("creator")
            @Expose
            private Object creator;

            public Integer getId() {
                return id;
            }

            public void setId(Integer id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public Object getCreator() {
                return creator;
            }

            public void setCreator(Object creator) {
                this.creator = creator;
            }

        }

        public class Events {

            @SerializedName("id")
            @Expose
            private Integer id;
            @SerializedName("creator_id")
            @Expose
            private Integer creatorId;
            @SerializedName("user_id")
            @Expose
            private Object userId;
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
            @SerializedName("items")
            @Expose
            private List<Item> items = null;
            @SerializedName("creator")
            @Expose
            private Creator__1 creator;
            @SerializedName("user")
            @Expose
            private Object user;

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

            public Object getUserId() {
                return userId;
            }

            public void setUserId(Object userId) {
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

            public List<Item> getItems() {
                return items;
            }

            public void setItems(List<Item> items) {
                this.items = items;
            }

            public Creator__1 getCreator() {
                return creator;
            }

            public void setCreator(Creator__1 creator) {
                this.creator = creator;
            }

            public Object getUser() {
                return user;
            }

            public void setUser(Object user) {
                this.user = user;
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
                @SerializedName("item_id")
                @Expose
                private Integer itemId;
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

                public Integer getItemId() {
                    return itemId;
                }

                public void setItemId(Integer itemId) {
                    this.itemId = itemId;
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
                    @SerializedName("avatar")
                    @Expose
                    private String avatar;
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
            }

            public class Creator__1 {

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
                @SerializedName("avatar")
                @Expose
                private String avatar;
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

            }

        }
    }
}