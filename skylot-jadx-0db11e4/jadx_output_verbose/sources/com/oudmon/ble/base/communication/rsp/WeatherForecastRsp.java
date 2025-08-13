package com.oudmon.ble.base.communication.rsp;

import android.util.Log;
import com.oudmon.ble.base.util.DataTransferUtils;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/oudmon/ble/base/communication/rsp/WeatherForecastRsp.class */
public class WeatherForecastRsp extends MixtureRsp {
    private boolean isSuccess = false;

    @Override // com.oudmon.ble.base.communication.rsp.MixtureRsp
    protected void readSubData(byte[] subData) {
        Log.i("Jxr35", "WeatherForecastRsp.. readSubData: " + DataTransferUtils.getHexString(subData));
        this.isSuccess = subData[0] == 26;
    }

    public boolean isSuccess() {
        return this.isSuccess;
    }

    public String toString() {
        return "WeatherForecastRsp{isSuccess=" + this.isSuccess + ", status=" + this.status + '}';
    }
}
