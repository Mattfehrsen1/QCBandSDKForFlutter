package com.qcwireless.qcwatch.ui.base.bean.response.cs;

/* loaded from: classes3.dex */
public class WsChatMessage {
    String content;
    String fromUser;
    String id;
    Long sendTime;
    String status;
    String toContactId;
    String type;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getSendTime() {
        return this.sendTime;
    }

    public void setSendTime(Long sendTime) {
        this.sendTime = sendTime;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getToContactId() {
        return this.toContactId;
    }

    public void setToContactId(String toContactId) {
        this.toContactId = toContactId;
    }

    public String getFromUser() {
        return this.fromUser;
    }

    public void setFromUser(String fromUser) {
        this.fromUser = fromUser;
    }
}
