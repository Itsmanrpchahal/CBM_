package com.casebeaumonde.activities.b_questionaries;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SecondQuestionnaireResponse {

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

    }
}