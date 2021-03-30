package com.casebeaumonde.fragments.contracts;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ContractCountResponse {

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

        @SerializedName("activeContract")
        @Expose
        private Integer activeContract;
        @SerializedName("completedContract")
        @Expose
        private Integer completedContract;
        @SerializedName("gigs")
        @Expose
        private Integer gigs;
        @SerializedName("workInvitation")
        @Expose
        private Integer workInvitation;
        @SerializedName("receivedOffers")
        @Expose
        private Integer receivedOffers;
        @SerializedName("invitesToColaborate")
        @Expose
        private Integer invitesToColaborate;

        public Integer getActiveContract() {
            return activeContract;
        }

        public void setActiveContract(Integer activeContract) {
            this.activeContract = activeContract;
        }

        public Integer getCompletedContract() {
            return completedContract;
        }

        public void setCompletedContract(Integer completedContract) {
            this.completedContract = completedContract;
        }

        public Integer getGigs() {
            return gigs;
        }

        public void setGigs(Integer gigs) {
            this.gigs = gigs;
        }

        public Integer getWorkInvitation() {
            return workInvitation;
        }

        public void setWorkInvitation(Integer workInvitation) {
            this.workInvitation = workInvitation;
        }

        public Integer getReceivedOffers() {
            return receivedOffers;
        }

        public void setReceivedOffers(Integer receivedOffers) {
            this.receivedOffers = receivedOffers;
        }

        public Integer getInvitesToColaborate() {
            return invitesToColaborate;
        }

        public void setInvitesToColaborate(Integer invitesToColaborate) {
            this.invitesToColaborate = invitesToColaborate;
        }

    }
}