package com.oudmon.ble.base.communication.req;

import com.oudmon.ble.base.communication.entity.StartEndTimeEntity;

/* loaded from: classes3.dex */
public class DndReq extends MixtureReq {
    private DndReq() {
        super((byte) 6);
        this.subData = new byte[]{1};
    }

    private DndReq(boolean z, StartEndTimeEntity startEndTimeEntity) {
        super((byte) 6);
        byte[] bArr = new byte[6];
        bArr[0] = 2;
        bArr[1] = (byte) (z ? 1 : 2);
        bArr[2] = (byte) (startEndTimeEntity.getStartHour() & 255);
        bArr[3] = (byte) (startEndTimeEntity.getStartMinute() & 255);
        bArr[4] = (byte) (startEndTimeEntity.getEndHour() & 255);
        bArr[5] = (byte) (startEndTimeEntity.getEndMinute() & 255);
        this.subData = bArr;
    }

    public static DndReq getReadInstance() {
        return new DndReq();
    }

    public static DndReq getWriteInstance(boolean z, StartEndTimeEntity startEndTimeEntity) {
        return new DndReq(z, startEndTimeEntity);
    }
}
