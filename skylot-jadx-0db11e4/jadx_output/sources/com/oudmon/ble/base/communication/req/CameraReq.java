package com.oudmon.ble.base.communication.req;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/oudmon/ble/base/communication/req/CameraReq.class */
public class CameraReq extends BaseReqCmd {
    public static final byte ACTION_INTO_CAMARA_UI = 4;
    public static final byte ACTION_KEEP_SCREEN_ON = 5;
    public static final byte ACTION_FINISH = 6;
    private byte action;

    public CameraReq(byte action) {
        super((byte) 2);
        this.action = (byte) 0;
        if (action > 6 || action < 4) {
            throw new IllegalArgumentException("action 范围出错");
        }
        this.action = action;
    }

    @Override // com.oudmon.ble.base.communication.req.BaseReqCmd
    protected byte[] getSubData() {
        return new byte[]{this.action};
    }
}
