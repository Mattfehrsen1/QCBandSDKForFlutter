package com.oudmon.ble.base.communication.req;

import com.qcwireless.qc_utils.bytes.DataTransferUtils;

/* loaded from: classes3.dex */
public class TargetSettingReq extends MixtureReq {
    private TargetSettingReq() {
        super((byte) 33);
    }

    public static TargetSettingReq getReadInstance() {
        return new TargetSettingReq() { // from class: com.oudmon.ble.base.communication.req.TargetSettingReq.1
            {
                this.subData = new byte[]{1};
            }
        };
    }

    public static TargetSettingReq getWriteInstance(int i, int i2, int i3) {
        return new TargetSettingReq(i, i2, i3) { // from class: com.oudmon.ble.base.communication.req.TargetSettingReq.2
            final /* synthetic */ int val$calorie;
            final /* synthetic */ int val$distance;
            final /* synthetic */ int val$step;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
                this.val$step = i;
                this.val$calorie = i2;
                this.val$distance = i3;
                this.subData = new byte[10];
                this.subData[0] = 2;
                System.arraycopy(DataTransferUtils.intToBytes(i), 0, this.subData, 1, 3);
                System.arraycopy(DataTransferUtils.intToBytes(i2), 0, this.subData, 4, 3);
                System.arraycopy(DataTransferUtils.intToBytes(i3), 0, this.subData, 7, 3);
            }
        };
    }

    public static TargetSettingReq getWriteInstance(int i, int i2, int i3, int i4, int i5) {
        return new TargetSettingReq(i, i2, i3, i4, i5) { // from class: com.oudmon.ble.base.communication.req.TargetSettingReq.3
            final /* synthetic */ int val$calorie;
            final /* synthetic */ int val$distance;
            final /* synthetic */ int val$sleepMinute;
            final /* synthetic */ int val$sportMinute;
            final /* synthetic */ int val$step;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
                this.val$step = i;
                this.val$calorie = i2;
                this.val$distance = i3;
                this.val$sportMinute = i4;
                this.val$sleepMinute = i5;
                this.subData = new byte[14];
                this.subData[0] = 2;
                System.arraycopy(DataTransferUtils.intToBytes(i), 0, this.subData, 1, 3);
                System.arraycopy(DataTransferUtils.intToBytes(i2), 0, this.subData, 4, 3);
                System.arraycopy(DataTransferUtils.intToBytes(i3), 0, this.subData, 7, 3);
                System.arraycopy(DataTransferUtils.shortToBytes((short) i4), 0, this.subData, 10, 2);
                System.arraycopy(DataTransferUtils.shortToBytes((short) i5), 0, this.subData, 12, 2);
            }
        };
    }
}
