package com.casebeaumonde;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UpdateProfilePicResponse {

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

        @SerializedName("image")
        @Expose
        private String image;

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

    }
}