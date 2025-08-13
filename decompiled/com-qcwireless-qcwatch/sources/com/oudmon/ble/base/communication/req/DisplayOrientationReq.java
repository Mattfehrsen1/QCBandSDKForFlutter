package com.oudmon.ble.base.communication.req;

/* loaded from: classes3.dex */
public class DisplayOrientationReq extends MixtureReq {
    private DisplayOrientationReq() {
        super((byte) 41);
        this.subData = new byte[]{1};
    }

    private DisplayOrientationReq(boolean z, boolean z2) {
        super((byte) 41);
        byte[] bArr = new byte[3];
        bArr[0] = 2;
        bArr[1] = (byte) (z ? 1 : 2);
        bArr[2] = (byte) (z ? 0 : z2 ? 1 : 2);
        this.subData = bArr;
    }

    public static DisplayOrientationReq getReadInstance() {
        return new DisplayOrientationReq();
    }

    public static DisplayOrientationReq getWriteInstance(boolean z, boolean z2) {
        return new DisplayOrientationReq(z, z2);
    }
}
