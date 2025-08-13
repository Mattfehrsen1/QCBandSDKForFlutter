package com.oudmon.ble.base.communication.req;

/* loaded from: classes3.dex */
public class HRVReq extends MixtureReq {
    private byte index;

    public HRVReq(byte b) {
        super((byte) 57);
        this.subData = new byte[]{b};
    }
}
