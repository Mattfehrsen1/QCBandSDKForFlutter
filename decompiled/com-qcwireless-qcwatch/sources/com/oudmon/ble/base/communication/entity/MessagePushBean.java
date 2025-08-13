package com.oudmon.ble.base.communication.entity;

/* loaded from: classes3.dex */
public class MessagePushBean {
    private String message;
    private long time;

    public MessagePushBean(String str, long j) {
        this.message = str;
        this.time = j;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    public long getTime() {
        return this.time;
    }

    public void setTime(long j) {
        this.time = j;
    }
}
