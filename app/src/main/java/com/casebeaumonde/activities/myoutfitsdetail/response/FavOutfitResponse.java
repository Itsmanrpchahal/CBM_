package com.casebeaumonde.activities.myoutfitsdetail.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FavOutfitResponse {

@SerializedName("code")
@Expose
private String code;
@SerializedName("message")
@Expose
private String message;

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

}