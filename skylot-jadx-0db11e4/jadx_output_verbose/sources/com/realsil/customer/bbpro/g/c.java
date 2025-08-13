package com.realsil.customer.bbpro.g;

import com.realsil.customer.bbpro.llapt.LlAptBrightnessStatus;
import com.realsil.customer.core.logger.ZLogger;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Locale;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/bbpro/g/c.class */
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
        byte b = byteBufferWrap.get();
        byte b2 = byteBufferWrap.get();
        short s = byteBufferWrap.getShort();
        byte b3 = byteBufferWrap.get();
        short s2 = byteBufferWrap.getShort();
        boolean z = false;
        if (bArr.length >= 8) {
            z = (byteBufferWrap.get() & 1) == 1;
        }
        return new c(b, b2, s, b3, s2, z);
    }

    public boolean b() {
        return this.a == 0;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("SetLlAptBrightnessRsp {");
        sb.append(String.format(Locale.US, "\n\tresult=0x%02X, L=(%d/%d), R=(%d/%d), rwsSyncEnabled=%b", Byte.valueOf(this.a), Byte.valueOf(this.b), Short.valueOf(this.c), Byte.valueOf(this.d), Short.valueOf(this.e), Boolean.valueOf(this.f)));
        sb.append("\n}");
        return sb.toString();
    }

    public LlAptBrightnessStatus a() {
        return new LlAptBrightnessStatus(this.b, this.d, this.c, this.e, this.f);
    }
}
