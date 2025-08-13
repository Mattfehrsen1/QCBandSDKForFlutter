package com.oudmon.ble.base.communication.rsp;

import com.oudmon.ble.base.communication.utils.ByteUtil;
import java.util.Arrays;

/* loaded from: classes3.dex */
public class SetTimeRsp extends BaseRspCmd {
    public boolean bpSettingSupport;
    public int height;
    public boolean mEbookSupport;
    public int mMaxContacts;
    public int mMaxWatchFace;
    public boolean mMusicSupport;
    public boolean mNewSleepProtocol;
    public boolean mSupport4G;
    public boolean mSupportAlbum;
    public boolean mSupportAppInstall;
    public boolean mSupportAppMeasure;
    public boolean mSupportAvatar;
    public boolean mSupportBloodLipids;
    public boolean mSupportBloodOxygen;
    public boolean mSupportBloodPressure;
    public boolean mSupportBloodSugar;
    public boolean mSupportBloodSugarCheck;
    public boolean mSupportContact;
    public boolean mSupportCustomWallpaper;
    public boolean mSupportDeviceLayout;
    public boolean mSupportECard;
    public boolean mSupportFeature;
    public boolean mSupportGPS;
    public boolean mSupportHRV;
    public boolean mSupportHeart;
    public boolean mSupportHrv;
    public boolean mSupportJieLiMusic;
    public boolean mSupportLocation;
    public boolean mSupportLyrics;
    public boolean mSupportManualBloodOxygen;
    public boolean mSupportManualHeart;
    public boolean mSupportMenstruation;
    public boolean mSupportNavPicture;
    public boolean mSupportOneKeyCheck;
    public boolean mSupportPlate;
    public boolean mSupportPressure;
    public boolean mSupportQuick;
    public boolean mSupportRecord;
    public boolean mSupportSleep;
    public boolean mSupportStock;
    public boolean mSupportStress;
    public boolean mSupportTemperature;
    public boolean mSupportTranslate;
    public boolean mSupportUricAcid;
    public boolean mSupportWeChat;
    public boolean mSupportWeather;
    public boolean mYaWeiSupport;
    public boolean rtkMcu;
    public int width;

    @Override // com.oudmon.ble.base.communication.rsp.BaseRspCmd
    public boolean acceptData(byte[] bArr) {
        this.mSupportTemperature = bArr[0] == 1;
        this.mSupportPlate = (bArr[1] & 1) != 0;
        this.mSupportBloodSugarCheck = (bArr[1] & 2) != 0;
        this.mSupportStock = (bArr[1] & 4) != 0;
        this.mSupportAppInstall = (bArr[1] & 8) != 0;
        this.mSupportQuick = (bArr[1] & 16) != 0;
        this.mSupportDeviceLayout = (bArr[1] & 32) != 0;
        this.mSupportHeart = (bArr[1] & 64) != 0;
        this.mSupportSleep = (bArr[1] & 128) != 0;
        this.mSupportMenstruation = (bArr[2] & 1) != 0;
        this.mSupportCustomWallpaper = (bArr[3] & 1) != 0;
        this.mSupportBloodOxygen = (bArr[3] & 2) != 0;
        this.mSupportBloodPressure = (bArr[3] & 4) != 0;
        this.mSupportFeature = (bArr[3] & 8) != 0;
        this.mSupportOneKeyCheck = (bArr[3] & 16) != 0;
        this.mSupportWeather = (bArr[3] & 32) != 0;
        this.mSupportWeChat = (bArr[3] & 64) == 0;
        this.mSupportAvatar = (bArr[3] & 128) != 0;
        this.width = ByteUtil.bytesToInt(Arrays.copyOfRange(bArr, 4, 6));
        this.height = ByteUtil.bytesToInt(Arrays.copyOfRange(bArr, 6, 8));
        this.mNewSleepProtocol = bArr[8] == 1;
        this.mMaxWatchFace = bArr[9];
        this.mSupportContact = (bArr[10] & 1) != 0;
        this.mSupportLyrics = (bArr[10] & 2) != 0;
        this.mSupportAlbum = (bArr[10] & 4) != 0;
        this.mSupportGPS = (bArr[10] & 8) != 0;
        this.mSupportJieLiMusic = (bArr[10] & 16) != 0;
        this.mSupportManualHeart = (bArr[11] & 1) != 0;
        this.mSupportECard = (bArr[11] & 2) != 0;
        this.mSupportLocation = (bArr[11] & 4) != 0;
        this.mMusicSupport = (bArr[11] & 16) != 0;
        this.rtkMcu = (bArr[11] & 32) != 0;
        this.mEbookSupport = (bArr[11] & 64) != 0;
        this.mSupportBloodSugar = (bArr[11] & 128) != 0;
        if (bArr[12] == 0) {
            this.mMaxContacts = 20;
        } else {
            this.mMaxContacts = bArr[12] * 10;
        }
        this.mSupportNavPicture = (8 & bArr[13]) != 0;
        this.mSupportStress = (bArr[13] & 16) != 0;
        this.mSupportHRV = (bArr[13] & 32) != 0;
        this.mSupportBloodLipids = (bArr[13] & 64) != 0;
        this.mSupportUricAcid = (bArr[13] & 128) != 0;
        this.bpSettingSupport = (2 & bArr[13]) != 0;
        this.mSupport4G = (bArr[13] & 4) != 0;
        this.mSupportPressure = (bArr[13] & 16) != 0;
        this.mSupportHrv = (bArr[13] & 32) != 0;
        this.mSupportRecord = (bArr[13] & 1) != 0;
        return false;
    }
}
