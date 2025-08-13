package com.oudmon.ble.base.communication.req;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/oudmon/ble/base/communication/req/StopHeartRateReq.class */
public class StopHeartRateReq extends BaseReqCmd {
    private byte[] data;

    private StopHeartRateReq(byte type, byte bb, byte cc) {
        super((byte) 106);
        this.data = new byte[]{type, bb, cc};
    }

    public static StopHeartRateReq stopHeartRate(byte hrValue) {
        return new StopHeartRateReq((byte) 1, hrValue, (byte) 0);
    }

    public static StopHeartRateReq stopBloodPressure(byte sbp, byte dbp) {
        return new StopHeartRateReq((byte) 2, sbp, dbp);
    }

    public static StopHeartRateReq stopBloodOxygen(byte oxyValue) {
        return new StopHeartRateReq((byte) 3, oxyValue, (byte) 0);
    }

    public static StopHeartRateReq stopPressure(byte value) {
        return new StopHeartRateReq((byte) 8, value, (byte) 0);
    }

    public static StopHeartRateReq stopHrv(byte value) {
        return new StopHeartRateReq((byte) 10, value, (byte) 0);
    }

    public static StopHeartRateReq stopFatigue(byte fatigueScore) {
        return new StopHeartRateReq((byte) 4, fatigueScore, (byte) 0);
    }

    public static StopHeartRateReq stopHealthCheck() {
        return new StopHeartRateReq((byte) 5, (byte) 0, (byte) 0);
    }

    public static StopHeartRateReq stopTemperatureCheck() {
        return new StopHeartRateReq((byte) 11, (byte) 0, (byte) 0);
    }

    public static StopHeartRateReq stopEcg(int ecgType) {
        return new StopHeartRateReq((byte) 7, (byte) ecgType, (byte) 0);
    }

    @Override // com.oudmon.ble.base.communication.req.BaseReqCmd
    protected byte[] getSubData() {
        return this.data;
    }
}
