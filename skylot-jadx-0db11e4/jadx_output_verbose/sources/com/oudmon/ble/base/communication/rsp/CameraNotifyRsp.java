package com.oudmon.ble.base.communication.rsp;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/oudmon/ble/base/communication/rsp/CameraNotifyRsp.class */
public class CameraNotifyRsp extends BaseRspCmd {
    public static final int ACTION_INTO_CAMERA_UI = 1;
    public static final int ACTION_TAKE_PHOTO = 2;
    public static final int ACTION_FINISH = 3;
    private int action = 0;

    @Override // com.oudmon.ble.base.communication.rsp.BaseRspCmd
    public boolean acceptData(byte[] data) {
        this.action = data[0];
        return false;
    }

    public int getAction() {
        return this.action;
    }
}
