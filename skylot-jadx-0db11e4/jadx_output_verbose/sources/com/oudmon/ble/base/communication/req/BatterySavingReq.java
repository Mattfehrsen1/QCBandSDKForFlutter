package com.oudmon.ble.base.communication.req;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/oudmon/ble/base/communication/req/BatterySavingReq.class */
public class BatterySavingReq extends MixtureReq {
    public BatterySavingReq() {
        super((byte) 118);
        this.subData = new byte[]{0};
    }

    private BatterySavingReq(boolean open) {
        super((byte) 118);
        byte[] bArr = new byte[2];
        bArr[0] = 1;
        bArr[1] = (byte) (open ? 1 : 0);
        this.subData = bArr;
    }

    public static BatterySavingReq getReadInstance() {
        return new BatterySavingReq();
    }

    public static BatterySavingReq getWriteInstance(boolean open) {
        return new BatterySavingReq(open);
    }
}
