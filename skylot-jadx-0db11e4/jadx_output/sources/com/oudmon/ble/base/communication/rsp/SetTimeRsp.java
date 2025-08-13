package com.oudmon.ble.base.communication.rsp;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/oudmon/ble/base/communication/rsp/SetTimeRsp.class */
public class SetTimeRsp extends BaseRspCmd {
    public boolean mSupportTemperature;
    public boolean mSupportPlate;
    public boolean mSupportMenstruation;
    public boolean mSupportCustomWallpaper;
    public boolean mSupportBloodOxygen;
    public boolean mSupportBloodPressure;
    public boolean mSupportFeature;
    public boolean mSupportOneKeyCheck;
    public boolean mSupportWeather;
    public boolean mNewSleepProtocol;
    public int mMaxWatchFace;
    public boolean mSupportContact;
    public boolean mSupportManualHeart;
    public boolean mSupportECard;
    public boolean mSupportLocation;
    public int mMaxContacts;
    public boolean mMusicSupport;
    public boolean rtkMcu;
    public boolean mEbookSupport;
    public boolean mSupportWeChat;
    public boolean mSupportHrv;

    @Override // com.oudmon.ble.base.communication.rsp.BaseRspCmd
    public boolean acceptData(byte[] data) {
        this.mSupportTemperature = data[0] == 1;
        this.mSupportPlate = data[1] == 1;
        this.mSupportMenstruation = data[2] == 1;
        this.mSupportCustomWallpaper = (data[3] & 1) != 0;
        this.mSupportBloodOxygen = (data[3] & 2) != 0;
        this.mSupportBloodPressure = (data[3] & 4) != 0;
        this.mSupportFeature = (data[3] & 8) != 0;
        this.mSupportOneKeyCheck = (data[3] & 16) != 0;
        this.mSupportWeather = (data[3] & 32) != 0;
        this.mSupportWeChat = (data[3] & 64) == 0;
        this.mNewSleepProtocol = data[8] == 1;
        this.mMaxWatchFace = data[9];
        this.mSupportContact = data[10] == 1;
        this.mSupportManualHeart = (data[11] & 1) != 0;
        this.mSupportECard = (data[11] & 2) != 0;
        this.mSupportLocation = (data[11] & 4) != 0;
        this.mMusicSupport = (data[11] & 16) != 0;
        this.rtkMcu = (data[11] & 32) != 0;
        this.mEbookSupport = (data[11] & 64) != 0;
        if (data[12] == 0) {
            this.mMaxContacts = 20;
        } else {
            this.mMaxContacts = data[12] * 10;
        }
        this.mSupportHrv = (data[13] & 32) != 0;
        return false;
    }
}
