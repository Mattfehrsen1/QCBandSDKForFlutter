package com.oudmon.ble.base.communication.rsp;

import com.oudmon.ble.base.communication.entity.StartEndTimeEntity;

/* loaded from: classes3.dex */
public class BpSettingRsp extends MixtureRsp {
    private boolean isEnable;
    private int multiple;
    private StartEndTimeEntity startEndTimeEntity;

    @Override // com.oudmon.ble.base.communication.rsp.MixtureRsp
    protected void readSubData(byte[] bArr) {
        this.isEnable = bArr[1] == 1;
        this.startEndTimeEntity = new StartEndTimeEntity(bArr[2], bArr[3], bArr[4], bArr[5]);
        this.multiple = bArr[6];
    }

    public boolean isEnable() {
        return this.isEnable;
    }

    public StartEndTimeEntity getStartEndTimeEntity() {
        return this.startEndTimeEntity;
    }

    public int getMultiple() {
        return this.multiple;
    }

    public void setEnable(boolean z) {
        this.isEnable = z;
    }
}
