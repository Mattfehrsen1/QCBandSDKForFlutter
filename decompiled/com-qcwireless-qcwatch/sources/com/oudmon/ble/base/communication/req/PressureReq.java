package com.oudmon.ble.base.communication.req;

/* loaded from: classes3.dex */
public class PressureReq extends MixtureReq {
    private byte index;

    public PressureReq(byte b) {
        super((byte) 55);
        this.subData = new byte[]{b};
    }
}
