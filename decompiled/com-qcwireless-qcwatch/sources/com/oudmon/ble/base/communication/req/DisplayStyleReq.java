package com.oudmon.ble.base.communication.req;

/* loaded from: classes3.dex */
public class DisplayStyleReq extends MixtureReq {
    private DisplayStyleReq() {
        super((byte) 42);
        this.subData = new byte[]{1};
    }

    private DisplayStyleReq(int i) {
        super((byte) 42);
        this.subData = new byte[]{2, (byte) i};
    }

    public static DisplayStyleReq getReadInstance() {
        return new DisplayStyleReq();
    }

    public static DisplayStyleReq getWriteInstance(int i) {
        return new DisplayStyleReq(i);
    }
}
