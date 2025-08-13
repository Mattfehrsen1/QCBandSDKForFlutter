package com.oudmon.ble.base.communication.rsp;

import com.elvishew.xlog.XLog;
import com.oudmon.ble.base.communication.utils.ByteUtil;
import java.util.Arrays;

/* loaded from: classes3.dex */
public class TargetSettingRsp extends MixtureRsp {
    private int mCalorie;
    private int mDistance;
    private int mSleep;
    private int mSport;
    private int mStep;

    @Override // com.oudmon.ble.base.communication.rsp.MixtureRsp
    protected void readSubData(byte[] bArr) {
        XLog.i(ByteUtil.byteArrayToString(bArr));
        this.mStep = ByteUtil.bytesToInt(Arrays.copyOfRange(bArr, 1, 4));
        this.mCalorie = ByteUtil.bytesToInt(Arrays.copyOfRange(bArr, 4, 7));
        this.mDistance = ByteUtil.bytesToInt(Arrays.copyOfRange(bArr, 7, 10));
        this.mSport = ByteUtil.bytesToInt(Arrays.copyOfRange(bArr, 10, 12));
        this.mSleep = ByteUtil.bytesToInt(Arrays.copyOfRange(bArr, 12, 14));
    }

    public int getStep() {
        return this.mStep;
    }

    public int getCalorie() {
        return this.mCalorie;
    }

    public int getDistance() {
        return this.mDistance;
    }

    public int getmSport() {
        return this.mSport;
    }

    public int getmSleep() {
        return this.mSleep;
    }
}
