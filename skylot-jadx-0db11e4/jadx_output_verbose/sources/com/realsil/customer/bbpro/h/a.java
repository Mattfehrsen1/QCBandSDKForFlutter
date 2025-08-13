package com.realsil.customer.bbpro.h;

import com.realsil.customer.core.logger.ZLogger;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Locale;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/bbpro/h/a.class */
public final class a {
    public int a;

    public a(int i) {
        this.a = i;
    }

    public static a a(byte[] bArr) {
        if (bArr == null || bArr.length < 1) {
            ZLogger.v("invalid packet");
            return null;
        }
        ByteBuffer byteBufferWrap = ByteBuffer.wrap(bArr);
        byteBufferWrap.order(ByteOrder.LITTLE_ENDIAN);
        return new a(byteBufferWrap.get() & 255);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("GetMultilinkConnNumRsp {");
        sb.append(String.format(Locale.US, "\n\tconnNum=%d", Integer.valueOf(this.a)));
        sb.append("\n}");
        return sb.toString();
    }
}
