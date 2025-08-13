package com.oudmon.ble.base.communication.req;

/* loaded from: classes3.dex */
public class TimeFormatReq extends MixtureReq {
    private TimeFormatReq() {
        super((byte) 10);
        this.subData = new byte[]{1};
    }

    private TimeFormatReq(boolean z, byte b) {
        super((byte) 10);
        this.subData = new byte[]{2, (byte) (!z ? 1 : 0), b};
    }

    public static TimeFormatReq getReadInstance() {
        return new TimeFormatReq();
    }

    public static TimeFormatReq getWriteInstance(boolean z, byte b) {
        return new TimeFormatReq(z, b);
    }

    public static TimeFormatReq getWriteInstance(boolean z, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        return new TimeFormatReq(z, i, i2, i3, i4, i5, i6, i7, i8) { // from class: com.oudmon.ble.base.communication.req.TimeFormatReq.1
            final /* synthetic */ int val$age;
            final /* synthetic */ int val$dbp;
            final /* synthetic */ int val$height;
            final /* synthetic */ boolean val$is24;
            final /* synthetic */ int val$metric;
            final /* synthetic */ int val$rateWarn;
            final /* synthetic */ int val$sbp;
            final /* synthetic */ int val$sex;
            final /* synthetic */ int val$weight;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
                this.val$is24 = z;
                this.val$metric = i;
                this.val$sex = i2;
                this.val$age = i3;
                this.val$height = i4;
                this.val$weight = i5;
                this.val$sbp = i6;
                this.val$dbp = i7;
                this.val$rateWarn = i8;
                this.subData = new byte[]{2, (byte) (!z ? 1 : 0), (byte) i, (byte) i2, (byte) i3, (byte) i4, (byte) i5, (byte) i6, (byte) i7, (byte) i8};
            }
        };
    }
}
