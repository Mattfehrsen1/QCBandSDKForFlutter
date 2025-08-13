package com.oudmon.ble.base.communication.rsp;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/oudmon/ble/base/communication/rsp/DeviceSupportFunctionRsp.class */
public class DeviceSupportFunctionRsp extends BaseRspCmd {
    public boolean supportTouch;
    public boolean supportMoslin;
    public boolean supportAPPRevision;
    public boolean supportBlePair;
    public boolean supportGesture;
    public boolean supportRingMusic;
    public boolean supportRingVideo;
    public boolean supportRingEbook;
    public boolean supportRingCamera;
    public boolean supportRingPhoneCall;
    public boolean supportRingGame;

    @Override // com.oudmon.ble.base.communication.rsp.BaseRspCmd
    public boolean acceptData(byte[] data) {
        this.supportTouch = (data[1] & 1) != 0;
        this.supportMoslin = (data[1] & 2) != 0;
        this.supportAPPRevision = (data[1] & 4) != 0;
        this.supportBlePair = (data[1] & 8) != 0;
        this.supportGesture = (data[1] & 128) != 0;
        this.supportRingMusic = (data[2] & 1) != 0;
        this.supportRingVideo = (data[2] & 2) != 0;
        this.supportRingEbook = (data[2] & 4) != 0;
        this.supportRingCamera = (data[2] & 8) != 0;
        this.supportRingPhoneCall = (data[2] & 16) != 0;
        this.supportRingGame = (data[2] & 32) != 0;
        return false;
    }
}
