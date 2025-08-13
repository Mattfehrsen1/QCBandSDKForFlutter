package com.oudmon.ble.base.communication.req;

import com.oudmon.ble.base.communication.rsp.MixtureRsp;
import com.oudmon.ble.base.communication.utils.ByteUtil;
import java.util.Arrays;

/* loaded from: classes3.dex */
public class TestResp extends MixtureRsp {
    String data;
    StringBuilder sb = new StringBuilder();

    @Override // com.oudmon.ble.base.communication.rsp.MixtureRsp
    protected void readSubData(byte[] bArr) {
        StringBuilder sb = this.sb;
        sb.delete(0, sb.length());
        int iBytesToInt = ByteUtil.bytesToInt(Arrays.copyOfRange(bArr, 0, 2));
        int iBytesToInt2 = ByteUtil.bytesToInt(Arrays.copyOfRange(bArr, 2, 4));
        int iBytesToInt3 = ByteUtil.bytesToInt(Arrays.copyOfRange(bArr, 4, 6));
        int iBytesToInt4 = ByteUtil.bytesToInt(Arrays.copyOfRange(bArr, 6, 8));
        int iBytesToInt5 = ByteUtil.bytesToInt(Arrays.copyOfRange(bArr, 8, 10));
        int iBytesToInt6 = ByteUtil.bytesToInt(Arrays.copyOfRange(bArr, 10, 12));
        StringBuilder sb2 = this.sb;
        sb2.append(iBytesToInt);
        sb2.append(",");
        StringBuilder sb3 = this.sb;
        sb3.append(iBytesToInt2);
        sb3.append(",");
        StringBuilder sb4 = this.sb;
        sb4.append(iBytesToInt3);
        sb4.append(",");
        StringBuilder sb5 = this.sb;
        sb5.append(iBytesToInt4);
        sb5.append(",");
        StringBuilder sb6 = this.sb;
        sb6.append(iBytesToInt5);
        sb6.append(",");
        StringBuilder sb7 = this.sb;
        sb7.append(iBytesToInt6);
        sb7.append("\n");
    }

    public String getData() {
        return this.sb.toString();
    }
}
