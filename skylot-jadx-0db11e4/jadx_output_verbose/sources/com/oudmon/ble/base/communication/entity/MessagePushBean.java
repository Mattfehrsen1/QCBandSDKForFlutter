package com.oudmon.ble.base.communication.entity;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/oudmon/ble/base/communication/entity/MessagePushBean.class */
public class MessagePushBean {
    private String message;
    private long time;

    public MessagePushBean(String message, long time) {
        this.message = message;
        this.time = time;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getTime() {
        return this.time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
