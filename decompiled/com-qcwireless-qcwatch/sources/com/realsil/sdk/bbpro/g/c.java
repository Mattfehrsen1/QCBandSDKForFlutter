package com.realsil.sdk.bbpro.g;

import com.realsil.sdk.bbpro.llapt.LlAptBrightnessStatus;
import com.realsil.sdk.core.logger.ZLogger;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Locale;

/* loaded from: classes3.dex */
public final class c {
    public byte a;
    public byte b;
    public short c;
    public byte d;
    public short e;
    public boolean f;

    public c(byte b, byte b2, short s, byte b3, short s2, boolean z) {
        this.a = b;
        this.b = b2;
        this.c = s;
        this.d = b3;
        this.e = s2;
        this.f = z;
    }

    public static c a(byte[] bArr) {
        if (bArr == null || bArr.length < 7) {
            ZLogger.v("invalid packet");
            return null;
        }
        ByteBuffer byteBufferWrap = ByteBuffer.wrap(bArr);
        byteBufferWrap.order(ByteOrder.LITTLE_ENDIAN);
        return new c(byteBufferWrap.get(), byteBufferWrap.get(), byteBufferWrap.getShort(), byteBufferWrap.get(), byteBufferWrap.getShort(), bArr.length >= 8 && (byteBufferWrap.get() & 1) == 1);
    }

    public boolean b() {
        return this.a == 0;
    }

    public String toString() {
        return "SetLlAptBrightnessRsp {" + String.format(Locale.US, "\n\tresult=0x%02X, L=(%d/%d), R=(%d/%d), rwsSyncEnabled=%b", Byte.valueOf(this.a), Byte.valueOf(this.b), Short.valueOf(this.c), Byte.valueOf(this.d), Short.valueOf(this.e), Boolean.valueOf(this.f)) + "\n}";
    }

    public LlAptBrightnessStatus a() {
        return new LlAptBrightnessStatus(this.b, this.d, this.c, this.e, this.f);
    }
}
