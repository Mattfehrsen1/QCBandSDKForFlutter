package com.oudmon.ble.base.communication.req;

import com.oudmon.ble.base.communication.utils.BLEDataFormatUtils;

/* loaded from: classes3.dex */
public class StartHeartRateReq extends BaseReqCmd {
    public static final byte ACTION_CONTINUE = 3;
    public static final byte ACTION_PAUSE = 2;
    public static final byte ACTION_START = 1;
    public static final byte ACTION_STOP = 4;
    public static final byte TYPE_BLOODOXYGEN = 3;
    public static final byte TYPE_BLOODPRESSURE = 2;
    public static final byte TYPE_ECG = 7;
    public static final byte TYPE_FATIGUE = 4;
    public static final byte TYPE_HEALTHCHECK = 5;
    public static final byte TYPE_HEARTRATE = 1;
    public static final byte TYPE_REALTIMEHEARTRATE = 6;
    private byte sub;
    private byte type;

    private StartHeartRateReq(byte b, byte b2) {
        super((byte) 105);
        this.type = b;
        this.sub = b2;
    }

    public static StartHeartRateReq getSimpleReq(byte b) {
        return new StartHeartRateReq(b, b < 3 ? (byte) 0 : BLEDataFormatUtils.decimalToBCD(25));
    }

    public static StartHeartRateReq getRealtimeHeartRate(byte b) {
        return new StartHeartRateReq((byte) 6, b);
    }

    @Override // com.oudmon.ble.base.communication.req.BaseReqCmd
    protected byte[] getSubData() {
        return new byte[]{this.type, this.sub};
    }
}
