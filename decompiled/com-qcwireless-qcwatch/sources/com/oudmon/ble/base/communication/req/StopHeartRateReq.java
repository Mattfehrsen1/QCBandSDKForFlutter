package com.oudmon.ble.base.communication.req;

/* loaded from: classes3.dex */
public class StopHeartRateReq extends BaseReqCmd {
    private byte[] data;

    private StopHeartRateReq(byte b, byte b2, byte b3) {
        super((byte) 106);
        this.data = new byte[]{b, b2, b3};
    }

    public static StopHeartRateReq stopHeartRate(byte b) {
        return new StopHeartRateReq((byte) 1, b, (byte) 0);
    }

    public static StopHeartRateReq stopBloodPressure(byte b, byte b2) {
        return new StopHeartRateReq((byte) 2, b, b2);
    }

    public static StopHeartRateReq stopBloodOxygen(byte b) {
        return new StopHeartRateReq((byte) 3, b, (byte) 0);
    }

    public static StopHeartRateReq stopFatigue(byte b) {
        return new StopHeartRateReq((byte) 4, b, (byte) 0);
    }

    public static StopHeartRateReq stopHealthCheck() {
        return new StopHeartRateReq((byte) 5, (byte) 0, (byte) 0);
    }

    public static StopHeartRateReq stopEcg(int i) {
        return new StopHeartRateReq((byte) 7, (byte) i, (byte) 0);
    }

    @Override // com.oudmon.ble.base.communication.req.BaseReqCmd
    protected byte[] getSubData() {
        return this.data;
    }
}
