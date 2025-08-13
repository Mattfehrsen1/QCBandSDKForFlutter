package com.realsil.sdk.bbpro.b;

import com.realsil.sdk.core.logger.ZLogger;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Locale;

/* loaded from: classes3.dex */
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
        return "AptPowerOnDelayTimeEvent {" + String.format(Locale.US, "\n\ttime=0x%02X(%d)", Integer.valueOf(this.a), Integer.valueOf(this.a)) + "\n}";
    }

    public int a() {
        return this.a;
    }
}
