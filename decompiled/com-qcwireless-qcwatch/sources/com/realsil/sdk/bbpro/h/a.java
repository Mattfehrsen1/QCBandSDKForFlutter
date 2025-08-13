package com.realsil.sdk.bbpro.h;

import com.realsil.sdk.core.logger.ZLogger;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Locale;

/* loaded from: classes3.dex */
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
        return "GetMultilinkConnNumRsp {" + String.format(Locale.US, "\n\tconnNum=%d", Integer.valueOf(this.a)) + "\n}";
    }
}
