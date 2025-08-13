package com.oudmon.ble.base.communication.rsp;

import com.oudmon.ble.base.communication.entity.StartEndTimeEntity;
import com.oudmon.ble.base.communication.utils.BLEDataFormatUtils;

/* loaded from: classes3.dex */
public class ReadSitLongRsp extends BaseRspCmd {
    private int cycle;
    private StartEndTimeEntity startEndTimeEntity;
    private byte weekMask;

    @Override // com.oudmon.ble.base.communication.rsp.BaseRspCmd
    public boolean acceptData(byte[] bArr) {
        this.startEndTimeEntity = new StartEndTimeEntity(BLEDataFormatUtils.BCDToDecimal(bArr[0]), BLEDataFormatUtils.BCDToDecimal(bArr[1]), BLEDataFormatUtils.BCDToDecimal(bArr[2]), BLEDataFormatUtils.BCDToDecimal(bArr[3]));
        this.weekMask = bArr[4];
        this.cycle = bArr[5];
        return false;
    }

    public StartEndTimeEntity getStartEndTimeEntity() {
        return this.startEndTimeEntity;
    }

    public byte getWeekMask() {
        return this.weekMask;
    }

    public int getCycle() {
        return this.cycle;
    }

    public boolean isEnable() {
        return this.weekMask != 0;
    }

    public void setEnableAll(boolean z) {
        this.weekMask = (byte) (z ? 127 : 0);
    }

    public ReadSitLongRsp cloneMySelf() {
        ReadSitLongRsp readSitLongRsp = new ReadSitLongRsp();
        readSitLongRsp.weekMask = this.weekMask;
        readSitLongRsp.cycle = this.cycle;
        readSitLongRsp.startEndTimeEntity = new StartEndTimeEntity(this.startEndTimeEntity.getStartHour(), this.startEndTimeEntity.getStartMinute(), this.startEndTimeEntity.getEndHour(), this.startEndTimeEntity.getEndMinute());
        return readSitLongRsp;
    }

    public void enableTheWeek(int i, boolean z) {
        int i2 = 1 << i;
        byte b = (byte) ((~i2) & this.weekMask);
        this.weekMask = b;
        if (z) {
            this.weekMask = (byte) (i2 | b);
        }
    }

    public void setCycle(int i) {
        this.cycle = i;
    }
}
