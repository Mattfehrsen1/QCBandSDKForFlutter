package com.oudmon.ble.base.communication.rsp;

import com.oudmon.ble.base.communication.utils.ByteUtil;
import java.util.Arrays;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/oudmon/ble/base/communication/rsp/StopHeartRateRsp.class */
public class StopHeartRateRsp extends BaseRspCmd {
    private byte type;
    private byte errCode;
    private int value;
    private int sbp;
    private int dbp;
    private int rri;

    public void setValue(int value) {
        this.value = value;
    }

    public void setSbp(int sbp) {
        this.sbp = sbp;
    }

    public void setDbp(int dbp) {
        this.dbp = dbp;
    }

    @Override // com.oudmon.ble.base.communication.rsp.BaseRspCmd
    public boolean acceptData(byte[] data) {
        this.type = data[0];
        this.errCode = data[1];
        this.value = data[2] & 255;
        if (data.length >= 5) {
            this.sbp = data[3];
            if (this.sbp < 0) {
                this.sbp = data[3] & 255;
            }
            this.dbp = data[4];
            if (this.dbp < 0) {
                this.dbp = data[4] & 255;
            }
        }
        this.rri = ByteUtil.bytesToInt(Arrays.copyOfRange(data, 5, 7));
        return false;
    }

    public void setType(byte type) {
        this.type = type;
    }

    public void setErrCode(byte errCode) {
        this.errCode = errCode;
    }

    public byte getType() {
        return this.type;
    }

    public byte getErrCode() {
        return this.errCode;
    }

    public int getValue() {
        return this.value;
    }

    public int getSbp() {
        return Math.abs(this.sbp);
    }

    public int getDbp() {
        return Math.abs(this.dbp);
    }

    public int getRri() {
        return this.rri;
    }

    public void setRri(int rri) {
        this.rri = rri;
    }
}
