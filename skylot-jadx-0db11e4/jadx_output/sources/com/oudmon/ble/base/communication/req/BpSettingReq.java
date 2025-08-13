package com.oudmon.ble.base.communication.req;

import com.oudmon.ble.base.communication.entity.StartEndTimeEntity;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/oudmon/ble/base/communication/req/BpSettingReq.class */
public class BpSettingReq extends MixtureReq {
    private BpSettingReq() {
        super((byte) 12);
        this.subData = new byte[]{1};
    }

    private BpSettingReq(boolean isEnable, StartEndTimeEntity startEndTimeEntity, int multiple) {
        super((byte) 12);
        byte[] bArr = new byte[7];
        bArr[0] = 2;
        bArr[1] = (byte) (isEnable ? 1 : 0);
        bArr[2] = (byte) (startEndTimeEntity.getStartHour() & 255);
        bArr[3] = (byte) (startEndTimeEntity.getStartMinute() & 255);
        bArr[4] = (byte) (startEndTimeEntity.getEndHour() & 255);
        bArr[5] = (byte) (startEndTimeEntity.getEndMinute() & 255);
        bArr[6] = (byte) (multiple & 255);
        this.subData = bArr;
    }

    public static BpSettingReq getReadInstance() {
        return new BpSettingReq();
    }

    public static BpSettingReq getWriteInstance(boolean isEnable, StartEndTimeEntity startEndTimeEntity, int multiple) {
        return new BpSettingReq(isEnable, startEndTimeEntity, multiple);
    }
}
