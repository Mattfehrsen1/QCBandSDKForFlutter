package com.oudmon.ble.base.communication.rsp;

import android.util.Log;
import com.qcwireless.qc_utils.bytes.DataTransferUtils;

/* loaded from: classes3.dex */
public class WeatherForecastRsp extends MixtureRsp {
    private boolean isSuccess = false;

    @Override // com.oudmon.ble.base.communication.rsp.MixtureRsp
    protected void readSubData(byte[] bArr) {
        Log.i("Jxr35", "WeatherForecastRsp.. readSubData: " + DataTransferUtils.getHexString(bArr));
        this.isSuccess = bArr[0] == 26;
    }

    public boolean isSuccess() {
        return this.isSuccess;
    }

    public String toString() {
        return "WeatherForecastRsp{isSuccess=" + this.isSuccess + ", status=" + this.status + '}';
    }
}
