package com.realsil.customer.bbpro.b;

import com.realsil.customer.core.logger.ZLogger;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Locale;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/bbpro/b/b.class */
public final class b {
    public int a;

    public b(int i) {
        this.a = i;
    }

    public static b a(byte[] bArr) {
        if (bArr == null || bArr.length < 1) {
            ZLogger.v("invalid packet");
            return null;
        }
        ByteBuffer byteBufferWrap = ByteBuffer.wrap(bArr);
        byteBufferWrap.order(ByteOrder.LITTLE_ENDIAN);
        return new b(byteBufferWrap.get());
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("AptPowerOnDelayTimeEvent {");
        sb.append(String.format(Locale.US, "\n\ttime=0x%02X(%d)", Integer.valueOf(this.a), Integer.valueOf(this.a)));
        sb.append("\n}");
        return sb.toString();
    }

    public int a() {
        return this.a;
    }
}
