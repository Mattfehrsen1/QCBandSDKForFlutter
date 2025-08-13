package com.realsil.customer.bbpro.g;

import com.realsil.customer.core.logger.ZLogger;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Locale;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/bbpro/g/d.class */
public final class d {
    public byte a;

    public d(byte b) {
        this.a = b;
    }

    public static d a(byte[] bArr) {
        if (bArr == null || bArr.length < 1) {
            ZLogger.v("invalid packet");
            return null;
        }
        ByteBuffer byteBufferWrap = ByteBuffer.wrap(bArr);
        byteBufferWrap.order(ByteOrder.LITTLE_ENDIAN);
        return new d(byteBufferWrap.get());
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("SetLlAptScenarioChooseResultRsp {");
        Locale locale = Locale.US;
        Object[] objArr = new Object[1];
        objArr[0] = Boolean.valueOf(this.a == 0);
        sb.append(String.format(locale, "\n\tresult=%b", objArr));
        sb.append("\n}");
        return sb.toString();
    }

    public boolean a() {
        return this.a == 0;
    }
}
