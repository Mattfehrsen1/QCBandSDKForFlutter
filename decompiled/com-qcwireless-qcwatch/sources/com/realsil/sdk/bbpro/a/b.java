package com.realsil.sdk.bbpro.a;

import com.realsil.sdk.core.logger.ZLogger;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Locale;

/* loaded from: classes3.dex */
public final class b {
    public byte a;

    public b(byte b) {
        this.a = b;
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
        sb.append("SetAncScenarioChooseResultRsp {");
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
