package com.casebeaumonde.activities.myGigs.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddGigResponse {

@SerializedName("code")
@Expose
private String code;
@SerializedName("message")
@Expose
private String message;
@SerializedName("data")
@Expose
private Data data;

public String getCode() {
return code;
}

public void setCode(String code) {
this.code = code;
}

public String getMessage() {
return message;
}

public void setMessage(String message) {
this.message = message;
}

public Data getData() {
return data;
}

public void setData(Data data) {
this.data = data;
}

    public class Data {

        @SerializedName("gig")
        @Expose
        private Gig gig;

        public Gig getGig() {
            return gig;
        }

        public void setGig(Gig gig) {
            this.gig = gig;
        }

        public class Gig {

            @SerializedName("user_id")
            @Expose
            private Integer userId;
            @SerializedName("title")
            @Expose
            private String title;
            @SerializedName("rate_type")
            @Expose
            private String rateType;
            @SerializedName("hours")
            @Expose
            private String hours;
            @SerializedName("rate")
            @Expose
            private String rate;
            @SerializedName("status")
            @Expose
            private String status;
            @SerializedName("description")
            @Expose
            private String description;
            @SerializedName("updated_at")
            @Expose
            private String updatedAt;
            @SerializedName("created_at")
            @Expose
            private String createdAt;
            @SerializedName("id")
            @Expose
            private Integer id;

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

            public String getRateType() {
                return rateType;
            }

            public void setRateType(String rateType) {
                this.rateType = rateType;
            }

            public String getHours() {
                return hours;
            }

            public void setHours(String hours) {
                this.hours = hours;
            }

            public String getRate() {
                return rate;
            }

            public void setRate(String rate) {
                this.rate = rate;
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