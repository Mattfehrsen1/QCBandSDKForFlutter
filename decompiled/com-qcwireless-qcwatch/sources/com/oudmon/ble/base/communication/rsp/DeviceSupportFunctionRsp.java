package com.oudmon.ble.base.communication.rsp;

/* loaded from: classes3.dex */
public class DeviceSupportFunctionRsp extends BaseRspCmd {
    public boolean deviceNoScreen;
    public boolean notSupportTakePhoto;
    public boolean supportAPPRevision;
    public boolean supportAiAnalyze;
    public boolean supportBlePair;
    public boolean supportMenuWallpaper;
    public boolean supportMoslin;
    public boolean supportTouch;
    public boolean supportWatchTheme;
    public boolean supportWechatPay;

    @Override // com.oudmon.ble.base.communication.rsp.BaseRspCmd
    public boolean acceptData(byte[] bArr) {
        this.supportTouch = (bArr[1] & 1) != 0;
        this.supportMoslin = (bArr[1] & 2) != 0;
        this.supportAPPRevision = (bArr[1] & 4) != 0;
        this.supportBlePair = (bArr[1] & 8) != 0;
        this.supportWatchTheme = (bArr[1] & 16) != 0;
        this.deviceNoScreen = (bArr[1] & 64) != 0;
        this.supportWatchTheme = (bArr[1] & 16) != 0;
        this.supportMenuWallpaper = (bArr[4] & 1) != 0;
        this.supportWechatPay = (bArr[4] & 4) != 0;
        this.supportAiAnalyze = (bArr[3] & 128) != 0;
        this.notSupportTakePhoto = (bArr[6] & 4) != 0;
        return false;
    }
}
