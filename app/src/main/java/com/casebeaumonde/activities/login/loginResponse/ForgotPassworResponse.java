package com.casebeaumonde.activities.login.loginResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ForgotPassworResponse {

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