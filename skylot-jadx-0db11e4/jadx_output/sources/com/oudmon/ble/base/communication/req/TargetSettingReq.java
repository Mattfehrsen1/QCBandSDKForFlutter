package com.oudmon.ble.base.communication.req;

import com.oudmon.ble.base.util.DataTransferUtils;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/oudmon/ble/base/communication/req/TargetSettingReq.class */
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

    public static TargetSettingReq getWriteInstance(final int step, final int calorie, final int distance) {
        return new TargetSettingReq() { // from class: com.oudmon.ble.base.communication.req.TargetSettingReq.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
                this.subData = new byte[10];
                this.subData[0] = 2;
                System.arraycopy(DataTransferUtils.intToBytes(step), 0, this.subData, 1, 3);
                System.arraycopy(DataTransferUtils.intToBytes(calorie), 0, this.subData, 4, 3);
                System.arraycopy(DataTransferUtils.intToBytes(distance), 0, this.subData, 7, 3);
            }
        };
    }

    public static TargetSettingReq getWriteInstance(final int step, final int calorie, final int distance, final int sportMinute, final int sleepMinute) {
        return new TargetSettingReq() { // from class: com.oudmon.ble.base.communication.req.TargetSettingReq.3
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
                this.subData = new byte[14];
                this.subData[0] = 2;
                System.arraycopy(DataTransferUtils.intToBytes(step), 0, this.subData, 1, 3);
                System.arraycopy(DataTransferUtils.intToBytes(calorie), 0, this.subData, 4, 3);
                System.arraycopy(DataTransferUtils.intToBytes(distance), 0, this.subData, 7, 3);
                System.arraycopy(DataTransferUtils.shortToBytes((short) sportMinute), 0, this.subData, 10, 2);
                System.arraycopy(DataTransferUtils.shortToBytes((short) sleepMinute), 0, this.subData, 12, 2);
            }
        };
    }
}
