package com.casebeaumonde.activities.eventDetail.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddItemToAnotherCloset {

@SerializedName("code")
@Expose
private String code;
@SerializedName("messsage")
@Expose
private String messsage;

public String getCode() {
return code;
}

public void setCode(String code) {
this.code = code;
}

public String getMesssage() {
return messsage;
}

public void setMesssage(String messsage) {
this.messsage = messsage;
}

}