package com.oudmon.ble.base.communication.req;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/oudmon/ble/base/communication/req/TouchControlReq.class */
public class TouchControlReq extends MixtureReq {
    boolean touch;

    public static TouchControlReq getReadInstance(boolean touch) {
        return new TouchControlReq(touch);
    }

    public TouchControlReq() {
        super((byte) 59);
        this.touch = false;
        this.subData = new byte[]{1};
    }

    public TouchControlReq(boolean touch) {
        super((byte) 59);
        this.touch = false;
        if (touch) {
            this.subData = new byte[]{1, 0};
        } else {
            this.subData = new byte[]{1, 1};
        }
    }

    private TouchControlReq(int type, boolean touch, int strength) {
        super((byte) 59);
        this.touch = false;
        if (touch) {
            this.subData = new byte[]{2, 0, (byte) type};
        } else {
            this.subData = new byte[]{2, 1, (byte) type, (byte) strength};
        }
    }

    public static TouchControlReq getWriteInstance(int type, boolean touch, int strength) {
        return new TouchControlReq(type, touch, strength);
    }
}
