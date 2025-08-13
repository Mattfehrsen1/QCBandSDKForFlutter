package com.oudmon.ble.base.communication.req;

import android.os.Build;
import com.elvishew.xlog.XLog;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/* loaded from: classes3.dex */
public class BindAncsReq extends BaseReqCmd {
    private byte[] mData;

    public BindAncsReq() {
        super((byte) 4);
        try {
            XLog.i("手机厂商: " + Build.BRAND + "手机型号: " + Build.MODEL + "SDK版本: " + Build.VERSION.SDK_INT + "系统版本: " + Build.VERSION.RELEASE);
            byte[] bytes = Build.MODEL.getBytes(StandardCharsets.UTF_8);
            bytes = bytes.length >= 14 ? Arrays.copyOf(bytes, 13) : bytes;
            byte b = 10;
            if (Build.VERSION.SDK_INT != 29) {
                if (Build.VERSION.SDK_INT == 28) {
                    b = 9;
                } else if (Build.VERSION.SDK_INT == 27 || Build.VERSION.SDK_INT == 26) {
                    b = 8;
                } else if (Build.VERSION.SDK_INT == 25 || Build.VERSION.SDK_INT == 24) {
                    b = 7;
                } else if (Build.VERSION.SDK_INT == 23) {
                    b = 6;
                } else if (Build.VERSION.SDK_INT == 22 || Build.VERSION.SDK_INT == 21) {
                    b = 5;
                }
            }
            byte[] bArr = new byte[bytes.length + 2];
            this.mData = bArr;
            bArr[0] = 2;
            bArr[1] = b;
            System.arraycopy(bytes, 0, bArr, 2, bytes.length);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.oudmon.ble.base.communication.req.BaseReqCmd
    protected byte[] getSubData() {
        byte[] bArr = this.mData;
        return bArr == null ? new byte[]{2} : bArr;
    }
}
