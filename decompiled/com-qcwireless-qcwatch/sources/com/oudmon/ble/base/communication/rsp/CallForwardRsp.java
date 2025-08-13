package com.oudmon.ble.base.communication.rsp;

import com.elvishew.xlog.XLog;
import com.oudmon.ble.base.communication.utils.ByteUtil;
import java.nio.charset.StandardCharsets;

/* loaded from: classes3.dex */
public class CallForwardRsp extends MixtureRsp {
    private byte callForwardType;
    private boolean isEnable;
    private String phoneNumber = "";

    @Override // com.oudmon.ble.base.communication.rsp.MixtureRsp
    protected void readSubData(byte[] bArr) {
        XLog.i(ByteUtil.byteArrayToString(bArr));
        if (bArr[1] > 0) {
            this.isEnable = true;
            this.callForwardType = bArr[1];
        } else {
            this.callForwardType = (byte) 0;
            this.isEnable = false;
        }
        int i = bArr[2];
        byte[] bArr2 = new byte[i];
        System.arraycopy(bArr, 3, bArr2, 0, i);
        XLog.i(bArr2);
        this.phoneNumber = new String(bArr2, StandardCharsets.UTF_8);
    }

    public boolean isEnable() {
        return this.isEnable;
    }

    public void setEnable(boolean z) {
        this.isEnable = z;
    }

    public byte getCallForwardType() {
        return this.callForwardType;
    }

    public void setCallForwardType(byte b) {
        this.callForwardType = b;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String str) {
        this.phoneNumber = str;
    }
}
