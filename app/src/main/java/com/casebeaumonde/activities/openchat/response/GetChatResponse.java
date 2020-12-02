package com.casebeaumonde.activities.openchat.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetChatResponse {

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

        @SerializedName("messages")
        @Expose
        private List<Message> messages = null;
        @SerializedName("blocked")
        @Expose
        private Integer blocked;
        @SerializedName("checkMessage")
        @Expose
        private Integer checkMessage;

        public List<Message> getMessages() {
            return messages;
        }

        public void setMessages(List<Message> messages) {
            this.messages = messages;
        }

        public Integer getBlocked() {
            return blocked;
        }

        public void setBlocked(Integer blocked) {
            this.blocked = blocked;
        }

        public Integer getCheckMessage() {
            return checkMessage;
        }

        public void setCheckMessage(Integer checkMessage) {
            this.checkMessage = checkMessage;
        }

        public class Message {

            @SerializedName("id")
            @Expose
            private Integer id;
            @SerializedName("from")
            @Expose
            private Integer from;
            @SerializedName("to")
            @Expose
            private Integer to;
            @SerializedName("content")
            @Expose
            private String content;
            @SerializedName("deleted_by")
            @Expose
            private Object deletedBy;
            @SerializedName("status")
            @Expose
            private String status;
            @SerializedName("created_at")
            @Expose
            private String createdAt;
            @SerializedName("updated_at")
            @Expose
            private String updatedAt;

            public Integer getId() {
                return id;
            }

            public void setId(Integer id) {
                this.id = id;
            }

            public Integer getFrom() {
                return from;
            }

            public void setFrom(Integer from) {
                this.from = from;
            }

            public Integer getTo() {
                return to;
            }

            public void setTo(Integer to) {
                this.to = to;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public Object getDeletedBy() {
                return deletedBy;
            }

            public void setDeletedBy(Object deletedBy) {
                this.deletedBy = deletedBy;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getCreatedAt() {
                return createdAt;
            }

            public void setCreatedAt(String createdAt) {
                this.createdAt = createdAt;
            }

            public String getUpdatedAt() {
                return updatedAt;
            }

            public void setUpdatedAt(String updatedAt) {
                this.updatedAt = updatedAt;
            }

        }
    }
}