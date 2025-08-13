package com.realsil.sdk.bbpro.b;

import com.realsil.sdk.bbpro.apt.AptVolumeStatus;
import com.realsil.sdk.core.logger.ZLogger;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Locale;

/* loaded from: classes3.dex */
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
        short s = byteBufferWrap.getShort();
        byte b3 = byteBufferWrap.get();
        short s2 = byteBufferWrap.getShort();
        if (b2 == -1) {
            b2 = 0;
        }
        short s3 = s == -1 ? (short) 0 : s;
        byte b4 = b3 == -1 ? (byte) 0 : b3;
        if (s2 == -1) {
            s2 = 0;
        }
        return new c(b, b2, s3, b4, s2);
    }

    public boolean b() {
        return this.a == 0;
    }

    public String toString() {
        return "SetAptVolumeRsp {" + String.format(Locale.US, "\n\tresult=0x%02X, L=(%d/%d), R=(%d/%d)", Byte.valueOf(this.a), Integer.valueOf(this.b), Integer.valueOf(this.c), Integer.valueOf(this.d), Integer.valueOf(this.e)) + "\n}";
    }

    public AptVolumeStatus a() {
        return new AptVolumeStatus(this.b, this.c, this.d, this.e);
    }
}
