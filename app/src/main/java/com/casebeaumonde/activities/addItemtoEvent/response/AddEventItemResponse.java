package com.casebeaumonde.activities.addItemtoEvent.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddEventItemResponse {

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

        @SerializedName("event_item")
        @Expose
        private EventItem eventItem;

        public EventItem getEventItem() {
            return eventItem;
        }

        public void setEventItem(EventItem eventItem) {
            this.eventItem = eventItem;
        }

        public class EventItem {

            @SerializedName("creator_id")
            @Expose
            private Integer creatorId;
            @SerializedName("title")
            @Expose
            private String title;
            @SerializedName("category_id")
            @Expose
            private String categoryId;
            @SerializedName("brand")
            @Expose
            private Integer brand;
            @SerializedName("color")
            @Expose
            private Integer color;
            @SerializedName("size")
            @Expose
            private Integer size;
            @SerializedName("status")
            @Expose
            private String status;
            @SerializedName("description")
            @Expose
            private String description;
            @SerializedName("price")
            @Expose
            private String price;
            @SerializedName("picture")
            @Expose
            private String picture;
            @SerializedName("fashionable_id")
            @Expose
            private Integer fashionableId;
            @SerializedName("fashionable_type")
            @Expose
            private String fashionableType;
            @SerializedName("updated_at")
            @Expose
            private String updatedAt;
            @SerializedName("created_at")
            @Expose
            private String createdAt;
            @SerializedName("id")
            @Expose
            private Integer id;

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

            public String getCategoryId() {
                return categoryId;
            }

            public void setCategoryId(String categoryId) {
                this.categoryId = categoryId;
            }

            public Integer getBrand() {
                return brand;
            }

            public void setBrand(Integer brand) {
                this.brand = brand;
            }

            public Integer getColor() {
                return color;
            }

            public void setColor(Integer color) {
                this.color = color;
            }

            public Integer getSize() {
                return size;
            }

            public void setSize(Integer size) {
                this.size = size;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getPicture() {
                return picture;
            }

            public void setPicture(String picture) {
                this.picture = picture;
            }

            public Integer getFashionableId() {
                return fashionableId;
            }

            public void setFashionableId(Integer fashionableId) {
                this.fashionableId = fashionableId;
            }

            public String getFashionableType() {
                return fashionableType;
            }

            public void setFashionableType(String fashionableType) {
                this.fashionableType = fashionableType;
            }

            public String getUpdatedAt() {
                return updatedAt;
            }

            public void setUpdatedAt(String updatedAt) {
                this.updatedAt = updatedAt;
            }

            public String getCreatedAt() {
                return createdAt;
            }

            public void setCreatedAt(String createdAt) {
                this.createdAt = createdAt;
            }

            public Integer getId() {
                return id;
            }

            public void setId(Integer id) {
                this.id = id;
            }

        }
    }
}