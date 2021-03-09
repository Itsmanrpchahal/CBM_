package com.casebeaumonde.activities.questionaries;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BasicQuestionariesResponse {

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

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("user_id")
        @Expose
        private String userId;
        @SerializedName("photo")
        @Expose
        private Object photo;
        @SerializedName("builder_color")
        @Expose
        private Object builderColor;
        @SerializedName("descriptors")
        @Expose
        private Object descriptors;
        @SerializedName("brands")
        @Expose
        private Object brands;
        @SerializedName("updated_at")
        @Expose
        private String updatedAt;
        @SerializedName("created_at")
        @Expose
        private String createdAt;
        @SerializedName("id")
        @Expose
        private Integer id;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public Object getPhoto() {
            return photo;
        }

        public void setPhoto(Object photo) {
            this.photo = photo;
        }

        public Object getBuilderColor() {
            return builderColor;
        }

        public void setBuilderColor(Object builderColor) {
            this.builderColor = builderColor;
        }

        public Object getDescriptors() {
            return descriptors;
        }

        public void setDescriptors(Object descriptors) {
            this.descriptors = descriptors;
        }

        public Object getBrands() {
            return brands;
        }

        public void setBrands(Object brands) {
            this.brands = brands;
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