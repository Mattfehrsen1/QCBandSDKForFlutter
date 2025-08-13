package com.oudmon.ble.base.communication.req;

/* loaded from: classes3.dex */
public class CameraReq extends BaseReqCmd {
    public static final byte ACTION_FINISH = 6;
    public static final byte ACTION_INTO_CAMARA_UI = 4;
    public static final byte ACTION_KEEP_SCREEN_ON = 5;
    private byte action;

    public CameraReq(byte b) {
        super((byte) 2);
        this.action = (byte) 0;
        if (b > 6 || b < 4) {
            throw new IllegalArgumentException("action 范围出错");
        }
        this.action = b;
    }

    @Override // com.oudmon.ble.base.communication.req.BaseReqCmd
    protected byte[] getSubData() {
        return new byte[]{this.action};
    }
}
