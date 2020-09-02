package com.casebeaumonde.createClosets;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CreateClosetResponse {

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
        @SerializedName("file_path")
        @Expose
        private String filePath;

        public Closet getCloset() {
            return closet;
        }

        public void setCloset(Closet closet) {
            this.closet = closet;
        }

        public String getFilePath() {
            return filePath;
        }

        public void setFilePath(String filePath) {
            this.filePath = filePath;
        }

    }

    public class Closet {

        @SerializedName("title")
        @Expose
        private String title;
        @SerializedName("user_id")
        @Expose
        private Integer userId;
        @SerializedName("status")
        @Expose
        private String status;
        @SerializedName("creator_id")
        @Expose
        private Integer creatorId;
        @SerializedName("description")
        @Expose
        private String description;
        @SerializedName("visibility")
        @Expose
        private String visibility;
        @SerializedName("image")
        @Expose
        private String image;
        @SerializedName("updated_at")
        @Expose
        private String updatedAt;
        @SerializedName("created_at")
        @Expose
        private String createdAt;
        @SerializedName("id")
        @Expose
        private Integer id;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public Integer getUserId() {
            return userId;
        }

        public void setUserId(Integer userId) {
            this.userId = userId;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public Integer getCreatorId() {
            return creatorId;
        }

        public void setCreatorId(Integer creatorId) {
            this.creatorId = creatorId;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getVisibility() {
            return visibility;
        }

        public void setVisibility(String visibility) {
            this.visibility = visibility;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
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