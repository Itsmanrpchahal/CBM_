package com.casebeaumonde.activities.MyEvents.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CreateEventResponse {

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

        @SerializedName("event")
        @Expose
        private Event event;

        public Event getEvent() {
            return event;
        }

        public void setEvent(Event event) {
            this.event = event;
        }

        public class Event {

            @SerializedName("creator_id")
            @Expose
            private Integer creatorId;
            @SerializedName("title")
            @Expose
            private String title;
            @SerializedName("type")
            @Expose
            private String type;
            @SerializedName("status")
            @Expose
            private String status;
            @SerializedName("user_id")
            @Expose
            private Object userId;
            @SerializedName("description")
            @Expose
            private String description;
            @SerializedName("from")
            @Expose
            private String from;
            @SerializedName("to")
            @Expose
            private String to;
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
            @SerializedName("creator")
            @Expose
            private Creator creator;

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

            public Object getUserId() {
                return userId;
            }

            public void setUserId(Object userId) {
                this.userId = userId;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
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

            public Creator getCreator() {
                return creator;
            }

            public void setCreator(Creator creator) {
                this.creator = creator;
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
        }
    }
}