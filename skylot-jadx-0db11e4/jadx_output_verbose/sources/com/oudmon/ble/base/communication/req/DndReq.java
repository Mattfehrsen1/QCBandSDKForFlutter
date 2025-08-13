package com.oudmon.ble.base.communication.req;

import com.oudmon.ble.base.communication.entity.StartEndTimeEntity;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/oudmon/ble/base/communication/req/DndReq.class */
public class DndReq extends MixtureReq {
    private DndReq() {
        super((byte) 6);
        this.subData = new byte[]{1};
    }

    private DndReq(boolean isEnable, StartEndTimeEntity dndEntity) {
        super((byte) 6);
        byte[] bArr = new byte[6];
        bArr[0] = 2;
        bArr[1] = (byte) (isEnable ? 1 : 2);
        bArr[2] = (byte) (dndEntity.getStartHour() & 255);
        bArr[3] = (byte) (dndEntity.getStartMinute() & 255);
        bArr[4] = (byte) (dndEntity.getEndHour() & 255);
        bArr[5] = (byte) (dndEntity.getEndMinute() & 255);
        this.subData = bArr;
    }

    public static DndReq getReadInstance() {
        return new DndReq();
    }

    public static DndReq getWriteInstance(boolean isEnable, StartEndTimeEntity dndEntity) {
        return new DndReq(isEnable, dndEntity);
    }
}
