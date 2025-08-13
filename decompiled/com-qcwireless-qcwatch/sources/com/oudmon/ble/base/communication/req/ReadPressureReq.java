package com.oudmon.ble.base.communication.req;

import com.oudmon.ble.base.communication.utils.DataParseUtils;

/* loaded from: classes3.dex */
public class ReadPressureReq extends BaseReqCmd {
    private byte[] data;

    public ReadPressureReq(long j) {
        super((byte) 20);
        byte[] bArrIntToByteArray = DataParseUtils.intToByteArray((int) j);
        byte[] bArr = new byte[bArrIntToByteArray.length + 2];
        this.data = bArr;
        System.arraycopy(bArrIntToByteArray, 0, bArr, 0, bArrIntToByteArray.length);
        byte[] bArr2 = this.data;
        bArr2[4] = 0;
        bArr2[5] = 50;
    }

    @Override // com.oudmon.ble.base.communication.req.BaseReqCmd
    protected byte[] getSubData() {
        return this.data;
    }
}
