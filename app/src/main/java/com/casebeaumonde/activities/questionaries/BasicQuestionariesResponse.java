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
private List<Object> data = null;

public String getCode() {
return code;
}

public void setCode(String code) {
this.code = code;
}


}