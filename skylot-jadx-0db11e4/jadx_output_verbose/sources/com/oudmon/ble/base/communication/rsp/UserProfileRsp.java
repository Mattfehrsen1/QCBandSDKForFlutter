package com.oudmon.ble.base.communication.rsp;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/oudmon/ble/base/communication/rsp/UserProfileRsp.class */
public class UserProfileRsp extends MixtureRsp {
    private boolean is24;
    private boolean metric;
    private boolean gender;
    private int age;
    private int height;
    private int weight;
    private int sbp;
    private int dbp;
    private int heartWarming;

    public boolean is24() {
        return this.is24;
    }

    public boolean isMetric() {
        return this.metric;
    }

    public boolean isGender() {
        return this.gender;
    }

    public int getAge() {
        return this.age;
    }

    public int getHeight() {
        return this.height;
    }

    public int getWeight() {
        return this.weight;
    }

    public int getSbp() {
        return this.sbp;
    }

    public int getDbp() {
        return this.dbp;
    }

    public int getHeartWarming() {
        return this.heartWarming;
    }

    public String toString() {
        return "UserProfileRsp{is24=" + this.is24 + ", metric=" + this.metric + ", gender=" + this.gender + ", age=" + this.age + ", height=" + this.height + ", weight=" + this.weight + ", sbp=" + this.sbp + ", dbp=" + this.dbp + ", heartWarming=" + this.heartWarming + '}';
    }

    @Override // com.oudmon.ble.base.communication.rsp.MixtureRsp
    protected void readSubData(byte[] subData) {
        this.is24 = subData[1] == 0;
        this.metric = subData[2] == 0;
        this.gender = subData[3] == 0;
        this.age = subData[4];
        this.height = subData[5];
        this.weight = subData[6];
        this.sbp = subData[7];
        this.dbp = subData[8];
        this.heartWarming = subData[9];
    }
}
