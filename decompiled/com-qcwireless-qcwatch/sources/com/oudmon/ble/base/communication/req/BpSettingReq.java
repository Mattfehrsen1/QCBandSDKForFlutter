package com.oudmon.ble.base.communication.req;

import com.oudmon.ble.base.communication.entity.StartEndTimeEntity;

/* loaded from: classes3.dex */
public class BpSettingReq extends MixtureReq {
    private BpSettingReq() {
        super((byte) 12);
        this.subData = new byte[]{1};
    }

    private BpSettingReq(boolean z, StartEndTimeEntity startEndTimeEntity, int i) {
        super((byte) 12);
        this.subData = new byte[]{2, z ? (byte) 1 : (byte) 0, (byte) (startEndTimeEntity.getStartHour() & 255), (byte) (startEndTimeEntity.getStartMinute() & 255), (byte) (startEndTimeEntity.getEndHour() & 255), (byte) (startEndTimeEntity.getEndMinute() & 255), (byte) (i & 255)};
    }

    public static BpSettingReq getReadInstance() {
        return new BpSettingReq();
    }

    public static BpSettingReq getWriteInstance(boolean z, StartEndTimeEntity startEndTimeEntity, int i) {
        return new BpSettingReq(z, startEndTimeEntity, i);
    }
}
