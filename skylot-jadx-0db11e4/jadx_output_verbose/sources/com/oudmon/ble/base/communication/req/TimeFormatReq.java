package com.oudmon.ble.base.communication.req;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/oudmon/ble/base/communication/req/TimeFormatReq.class */
public class TimeFormatReq extends MixtureReq {
    private TimeFormatReq() {
        super((byte) 10);
        this.subData = new byte[]{1};
    }

    private TimeFormatReq(boolean is24, byte metric) {
        super((byte) 10);
        byte[] bArr = new byte[3];
        bArr[0] = 2;
        bArr[1] = (byte) (is24 ? 0 : 1);
        bArr[2] = metric;
        this.subData = bArr;
    }

    public static TimeFormatReq getReadInstance() {
        return new TimeFormatReq();
    }

    public static TimeFormatReq getWriteInstance(boolean is24, byte metric) {
        return new TimeFormatReq(is24, metric);
    }

    public static TimeFormatReq getWriteInstance(final boolean is24, final int metric, final int sex, final int age, final int height, final int weight, final int sbp, final int dbp, final int rateWarn) {
        return new TimeFormatReq() { // from class: com.oudmon.ble.base.communication.req.TimeFormatReq.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
                byte[] bArr = new byte[10];
                bArr[0] = 2;
                bArr[1] = (byte) (is24 ? 0 : 1);
                bArr[2] = (byte) metric;
                bArr[3] = (byte) sex;
                bArr[4] = (byte) age;
                bArr[5] = (byte) height;
                bArr[6] = (byte) weight;
                bArr[7] = (byte) sbp;
                bArr[8] = (byte) dbp;
                bArr[9] = (byte) rateWarn;
                this.subData = bArr;
            }
        };
    }
}
