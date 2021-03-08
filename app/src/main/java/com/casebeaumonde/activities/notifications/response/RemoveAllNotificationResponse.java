package com.casebeaumonde.activities.notifications.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RemoveAllNotificationResponse {

@SerializedName("code")
@Expose
private Integer code;
@SerializedName("data")
@Expose
private Data data;

public Integer getCode() {
return code;
}

public void setCode(Integer code) {
this.code = code;
}

public Data getData() {
return data;
}

public void setData(Data data) {
this.data = data;
}

    public class Data {
        @SerializedName("message")
        @Expose
        private String message;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}