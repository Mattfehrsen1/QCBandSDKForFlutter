package com.oudmon.ble.base.communication.rsp;

/* loaded from: classes3.dex */
public class CameraNotifyRsp extends BaseRspCmd {
    public static final int ACTION_FINISH = 3;
    public static final int ACTION_INTO_CAMERA_UI = 1;
    public static final int ACTION_TAKE_PHOTO = 2;
    private int action = 0;

    @Override // com.oudmon.ble.base.communication.rsp.BaseRspCmd
    public boolean acceptData(byte[] bArr) {
        this.action = bArr[0];
        return false;
    }

    public int getAction() {
        return this.action;
    }
}
