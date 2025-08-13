package com.oudmon.ble.base.communication.req;

/* loaded from: classes3.dex */
public class HrvSettingReq extends MixtureReq {
    private HrvSettingReq() {
        super((byte) 56);
        this.subData = new byte[]{1};
    }

    public HrvSettingReq(boolean z) {
        super((byte) 56);
        this.subData = new byte[]{2, z ? (byte) 1 : (byte) 0};
    }

    public static HrvSettingReq getReadInstance() {
        return new HrvSettingReq();
    }
}
