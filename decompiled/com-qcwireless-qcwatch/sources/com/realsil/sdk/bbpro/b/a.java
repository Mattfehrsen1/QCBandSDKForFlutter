package com.realsil.sdk.bbpro.b;

import com.realsil.sdk.core.logger.ZLogger;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Locale;

/* loaded from: classes3.dex */
public final class a {
    public byte a;
    public byte b;

    public a(byte b, byte b2) {
        this.a = b;
        this.b = b2;
    }

    public static a a(byte[] bArr) {
        if (bArr == null || bArr.length < 2) {
            ZLogger.v("invalid packet");
            return null;
        }
        ByteBuffer byteBufferWrap = ByteBuffer.wrap(bArr);
        byteBufferWrap.order(ByteOrder.LITTLE_ENDIAN);
        return new a(byteBufferWrap.get(), byteBufferWrap.get());
    }

    public String toString() {
        return "AptNrOnOffEvent {" + String.format(Locale.US, "\n\tstatus=0x%02X, state=0x%02X", Byte.valueOf(this.a), Byte.valueOf(this.b)) + "\n}";
    }

    public boolean a() {
        return this.b == 1;
    }
}
