package com.oudmon.ble.base.communication.req;

/* loaded from: classes3.dex */
public class PalmScreenReq extends MixtureReq {
    private PalmScreenReq() {
        super((byte) 5);
        this.subData = new byte[]{1};
    }

    private PalmScreenReq(boolean z, boolean z2, boolean z3) {
        super((byte) 5);
        byte[] bArr = new byte[3];
        bArr[0] = 2;
        bArr[1] = (byte) (z ? 1 : 2);
        bArr[2] = (byte) ((z2 ? 1 : 2) | (z3 ? 4 : 0));
        this.subData = bArr;
    }

    public static PalmScreenReq getReadInstance() {
        return new PalmScreenReq();
    }

    public static PalmScreenReq getWriteInstance(boolean z, boolean z2) {
        return new PalmScreenReq(z, z2, true);
    }
}
