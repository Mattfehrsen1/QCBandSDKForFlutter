package com.oudmon.ble.base.communication.rsp;

import com.oudmon.ble.base.communication.entity.StartEndTimeEntity;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/oudmon/ble/base/communication/rsp/DndRsp.class */
public class DndRsp extends MixtureRsp {
    private boolean isEnable;
    private StartEndTimeEntity dndEntity;

    @Override // com.oudmon.ble.base.communication.rsp.MixtureRsp
    protected void readSubData(byte[] subData) {
        this.isEnable = subData[1] == 1;
        this.dndEntity = new StartEndTimeEntity(subData[2], subData[3], subData[4], subData[5]);
    }

    public boolean isEnable() {
        return this.isEnable;
    }

    public StartEndTimeEntity getDndEntity() {
        return this.dndEntity;
    }

    public void setEnable(boolean enable) {
        this.isEnable = enable;
    }
}
