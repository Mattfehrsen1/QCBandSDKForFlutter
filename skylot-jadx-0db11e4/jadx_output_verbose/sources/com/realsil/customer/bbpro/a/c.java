package com.realsil.customer.bbpro.a;

import com.realsil.customer.core.logger.ZLogger;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Locale;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/bbpro/a/c.class */
public final class c {
    public byte a;

    public c(byte b) {
        this.a = b;
    }

    public static c a(byte[] bArr) {
        if (bArr == null || bArr.length < 1) {
            ZLogger.v("invalid packet");
            return null;
        }
        ByteBuffer byteBufferWrap = ByteBuffer.wrap(bArr);
        byteBufferWrap.order(ByteOrder.LITTLE_ENDIAN);
        return new c(byteBufferWrap.get());
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("SetAncScenarioChooseTryRsp {");
        Locale locale = Locale.US;
        Object[] objArr = new Object[1];
        objArr[0] = Boolean.valueOf(this.a == 0);
        sb.append(String.format(locale, "\n\tsuccess=%b", objArr));
        sb.append("\n}");
        return sb.toString();
    }

    public boolean a() {
        return this.a == 0;
    }
}
