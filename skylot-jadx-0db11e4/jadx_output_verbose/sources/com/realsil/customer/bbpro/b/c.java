package com.realsil.customer.bbpro.b;

import com.realsil.customer.bbpro.apt.AptVolumeStatus;
import com.realsil.customer.core.logger.ZLogger;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Locale;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/bbpro/b/c.class */
public final class c {
    public byte a;
    public int b;
    public int c;
    public int d;
    public int e;

    public c(byte b, int i, int i2, int i3, int i4) {
        this.a = b;
        this.b = i;
        this.c = i2;
        this.d = i3;
        this.e = i4;
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
        byte b3 = b2;
        short s = byteBufferWrap.getShort();
        byte b4 = byteBufferWrap.get();
        short s2 = byteBufferWrap.getShort();
        if (b2 == -1) {
            b3 = 0;
        }
        if (s == -1) {
            s = 0;
        }
        if (b4 == -1) {
            b4 = 0;
        }
        if (s2 == -1) {
            s2 = 0;
        }
        return new c(b, b3, s, b4, s2);
    }

    public boolean b() {
        return this.a == 0;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("SetAptVolumeRsp {");
        sb.append(String.format(Locale.US, "\n\tresult=0x%02X, L=(%d/%d), R=(%d/%d)", Byte.valueOf(this.a), Integer.valueOf(this.b), Integer.valueOf(this.c), Integer.valueOf(this.d), Integer.valueOf(this.e)));
        sb.append("\n}");
        return sb.toString();
    }

    public AptVolumeStatus a() {
        return new AptVolumeStatus(this.b, this.c, this.d, this.e);
    }
}
