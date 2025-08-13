package com.oudmon.ble.base.communication.req;

import com.oudmon.ble.base.communication.rsp.BaseRspCmd;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/oudmon/ble/base/communication/req/AppRevisionResp.class */
public class AppRevisionResp extends BaseRspCmd {
    private int dataType;
    private int result;
    private int success;

    public AppRevisionResp() {
    }

    public AppRevisionResp(int success) {
        this.success = success;
    }

    @Override // com.oudmon.ble.base.communication.rsp.BaseRspCmd
    public boolean acceptData(byte[] data) {
        this.dataType = data[0];
        this.result = data[9];
        return false;
    }

    public int getDataType() {
        return this.dataType;
    }

    public int getResult() {
        return this.result;
    }

    public int getSuccess() {
        return this.success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }
}
