package com.oudmon.ble.base.communication.req;

/* loaded from: classes3.dex */
public class HeartRateSettingReq extends MixtureReq {
    private HeartRateSettingReq() {
        super((byte) 22);
        this.subData = new byte[]{1};
    }

    private HeartRateSettingReq(boolean z) {
        super((byte) 22);
        byte[] bArr = new byte[2];
        bArr[0] = 2;
        bArr[1] = (byte) (z ? 1 : 2);
        this.subData = bArr;
    }

    public static HeartRateSettingReq getReadInstance() {
        return new HeartRateSettingReq();
    }

    public static HeartRateSettingReq getWriteInstance(boolean z) {
        return new HeartRateSettingReq(z);
    }
}
