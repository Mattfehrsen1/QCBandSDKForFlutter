package com.oudmon.ble.base.communication.req;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/oudmon/ble/base/communication/req/MenstruationReq.class */
public class MenstruationReq extends MixtureReq {
    public MenstruationReq(byte key) {
        super((byte) 43);
        this.subData = new byte[]{1};
    }

    public MenstruationReq(boolean isEnable, int during, int cycle, int lastStart, int lastEnd, boolean alarmEnable, int mAlert, int oAlert, int hour, int min) {
        super((byte) 43);
        byte[] bArr = new byte[11];
        bArr[0] = 2;
        bArr[1] = (byte) (isEnable ? 1 : 0);
        bArr[2] = (byte) (during & 255);
        bArr[3] = (byte) (cycle & 255);
        bArr[4] = (byte) (lastStart & 255);
        bArr[5] = (byte) (lastEnd & 255);
        bArr[6] = (byte) (alarmEnable ? 1 : 0);
        bArr[7] = (byte) (mAlert & 255);
        bArr[8] = (byte) (oAlert & 255);
        bArr[9] = (byte) (hour & 255);
        bArr[10] = (byte) (min & 255);
        this.subData = bArr;
    }
}
