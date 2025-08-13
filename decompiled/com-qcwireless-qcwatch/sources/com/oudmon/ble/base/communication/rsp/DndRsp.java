package com.oudmon.ble.base.communication.rsp;

import com.oudmon.ble.base.communication.entity.StartEndTimeEntity;

/* loaded from: classes3.dex */
public class DndRsp extends MixtureRsp {
    private StartEndTimeEntity dndEntity;
    private boolean isEnable;
    private boolean manualDND;

    @Override // com.oudmon.ble.base.communication.rsp.MixtureRsp
    protected void readSubData(byte[] bArr) {
        this.isEnable = bArr[1] == 1;
        this.manualDND = bArr[6] == 1;
        this.dndEntity = new StartEndTimeEntity(bArr[2], bArr[3], bArr[4], bArr[5]);
    }

    public boolean isEnable() {
        return this.isEnable;
    }

    public boolean isManualDND() {
        return this.manualDND;
    }

    public StartEndTimeEntity getDndEntity() {
        return this.dndEntity;
    }

    public void setEnable(boolean z) {
        this.isEnable = z;
    }
}
