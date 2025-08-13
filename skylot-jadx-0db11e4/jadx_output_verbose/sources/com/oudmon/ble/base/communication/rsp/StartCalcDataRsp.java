package com.oudmon.ble.base.communication.rsp;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/oudmon/ble/base/communication/rsp/StartCalcDataRsp.class */
public class StartCalcDataRsp extends BaseRspCmd {
    private byte type;
    private byte errCode;
    private byte heart;
    private byte sbp;
    private byte dbp;
    private int fatigue;
    private int spo2;
    private int score;

    @Override // com.oudmon.ble.base.communication.rsp.BaseRspCmd
    public boolean acceptData(byte[] data) {
        this.type = data[0];
        this.errCode = data[1];
        this.heart = data[2];
        if (data.length >= 5) {
            this.sbp = data[3];
            this.dbp = data[4];
            return false;
        }
        return false;
    }

    public byte getType() {
        return this.type;
    }

    public void setType(byte type) {
        this.type = type;
    }

    public byte getErrCode() {
        return this.errCode;
    }

    public void setErrCode(byte errCode) {
        this.errCode = errCode;
    }

    public byte getHeart() {
        return this.heart;
    }

    public void setHeart(byte heart) {
        this.heart = heart;
    }

    public byte getSbp() {
        return this.sbp;
    }

    public void setSbp(byte sbp) {
        this.sbp = sbp;
    }

    public byte getDbp() {
        return this.dbp;
    }

    public void setDbp(byte dbp) {
        this.dbp = dbp;
    }

    public int getFatigue() {
        return this.fatigue;
    }

    public void setFatigue(int fatigue) {
        this.fatigue = fatigue;
    }

    public int getSpo2() {
        return this.spo2;
    }

    public void setSpo2(int spo2) {
        this.spo2 = spo2;
    }

    public int getScore() {
        return this.score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
