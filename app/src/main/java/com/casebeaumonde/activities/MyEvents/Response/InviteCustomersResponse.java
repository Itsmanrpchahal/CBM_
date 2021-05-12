package com.casebeaumonde.activities.MyEvents.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class InviteCustomersResponse {

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

        @SerializedName("invitedCustomers")
        @Expose
        private List<InvitedCustomer> invitedCustomers = null;
        @SerializedName("file_path")
        @Expose
        private String filePath;

        public List<InvitedCustomer> getInvitedCustomers() {
            return invitedCustomers;
        }

        public void setInvitedCustomers(List<InvitedCustomer> invitedCustomers) {
            this.invitedCustomers = invitedCustomers;
        }

        public String getFilePath() {
            return filePath;
        }

        public void setFilePath(String filePath) {
            this.filePath = filePath;
        }

        public class InvitedCustomer {

            @SerializedName("id")
            @Expose
            private Integer id;
            @SerializedName("firstname")
            @Expose
            private String firstname;
            @SerializedName("lastname")
            @Expose
            private String lastname;
            @SerializedName("avatar")
            @Expose
            private String avatar;
            @SerializedName("invitedStatus")
            @Expose
            private Integer invitedStatus;
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

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }

            public Integer getInvitedStatus() {
                return invitedStatus;
            }

            public void setInvitedStatus(Integer invitedStatus) {
                this.invitedStatus = invitedStatus;
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