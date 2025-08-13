package com.oudmon.ble.base.communication.req;

import android.os.Build;
import android.util.Log;
import com.oudmon.ble.base.communication.Constants;
import com.oudmon.ble.base.util.DataTransferUtils;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/oudmon/ble/base/communication/req/BindAncsReq.class */
public class BindAncsReq extends BaseReqCmd {
    private byte[] mData;

    public BindAncsReq() {
        super((byte) 4);
        try {
            Log.i(Constants.TAG, "手机厂商: " + Build.BRAND + "手机型号: " + Build.MODEL + "SDK版本: " + Build.VERSION.SDK_INT + "系统版本: " + Build.VERSION.RELEASE);
            byte[] model = Build.MODEL.getBytes(StandardCharsets.UTF_8);
            model = model.length >= 14 ? Arrays.copyOf(model, 13) : model;
            byte androidVersion = 10;
            if (Build.VERSION.SDK_INT == 29) {
                androidVersion = 10;
            } else if (Build.VERSION.SDK_INT == 28) {
                androidVersion = 9;
            } else if (Build.VERSION.SDK_INT == 27 || Build.VERSION.SDK_INT == 26) {
                androidVersion = 8;
            } else if (Build.VERSION.SDK_INT == 25 || Build.VERSION.SDK_INT == 24) {
                androidVersion = 7;
            } else if (Build.VERSION.SDK_INT == 23) {
                androidVersion = 6;
            } else if (Build.VERSION.SDK_INT == 22 || Build.VERSION.SDK_INT == 21) {
                androidVersion = 5;
            }
            this.mData = new byte[model.length + 2];
            this.mData[0] = 2;
            this.mData[1] = androidVersion;
            System.arraycopy(model, 0, this.mData, 2, model.length);
            Log.i("Jxr35", "mData: " + DataTransferUtils.getHexString(this.mData));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.oudmon.ble.base.communication.req.BaseReqCmd
    protected byte[] getSubData() {
        return this.mData == null ? new byte[]{2} : this.mData;
    }
}
