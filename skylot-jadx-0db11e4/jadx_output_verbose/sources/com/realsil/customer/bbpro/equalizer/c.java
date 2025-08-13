package com.realsil.customer.bbpro.equalizer;

import com.realsil.customer.core.logger.ZLogger;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Locale;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/bbpro/equalizer/c.class */
public class c {
    public int a;
    public int b;
    public boolean c;
    public byte d;
    public int e;
    public int f;
    public int g;
    public int h;
    public int i;
    public int j;
    public int k;
    public int l;
    public int m;
    public int n;
    public int o;
    public int p;
    public int q;
    public int r;
    public int s;
    public int t;
    public int u;

    public c(int i, boolean z, int i2, int i3) {
        this.r = 0;
        this.g = i;
        this.c = z;
        this.a = i2;
        this.b = i3;
    }

    public static c a(int i, byte[] bArr) {
        return i >= 512 ? b(bArr) : a(bArr);
    }

    public static c b(byte[] bArr) {
        if (bArr == null || bArr.length < 4) {
            ZLogger.v("invalid packet");
            return null;
        }
        ByteBuffer byteBufferWrap = ByteBuffer.wrap(bArr);
        byteBufferWrap.order(ByteOrder.LITTLE_ENDIAN);
        byte b = byteBufferWrap.get();
        int i = byteBufferWrap.get() & 255;
        int i2 = byteBufferWrap.get() & 255;
        int i3 = byteBufferWrap.getShort();
        int i4 = byteBufferWrap.getShort();
        int i5 = byteBufferWrap.getShort();
        int i6 = byteBufferWrap.getShort();
        int i7 = 0;
        int i8 = 0;
        int i9 = 0;
        int i10 = 0;
        for (int i11 = 0; i11 < 16; i11++) {
            if (((i3 >> i11) & 1) == 1) {
                i7++;
            }
            if (((i4 >> i11) & 1) == 1) {
                i8++;
            }
            if (((i5 >> i11) & 1) == 1) {
                i9++;
            }
            if (((i6 >> i11) & 1) == 1) {
                i10++;
            }
        }
        return new c(b == 1, byteBufferWrap.get(), byteBufferWrap.getShort(), i, byteBufferWrap.get() & 255, i7, i3, byteBufferWrap.get() & 255, i8, i4, byteBufferWrap.get() & 255, i10, i6, byteBufferWrap.get() & 255, i2, byteBufferWrap.get() & 255, i9, i5, byteBufferWrap.get() & 255);
    }

    public String toString() {
        return String.format(Locale.US, "EqBasicInfo{spkActiveMode=%d, state=%b, entryNumber=%d, activeIndex=%d}", Integer.valueOf(this.g), Boolean.valueOf(this.c), Integer.valueOf(this.a), Integer.valueOf(this.b));
    }

    public static c a(byte[] bArr) {
        if (bArr != null && bArr.length >= 4) {
            int i = bArr[0] & 255;
            byte b = bArr[1];
            return new c(i, b == 1, bArr[2] & 255, bArr[3] & 255);
        }
        ZLogger.v("invalid packet");
        return null;
    }

    public c(boolean z, byte b, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11, int i12, int i13, int i14, int i15, int i16, int i17) {
        this.c = z;
        this.d = b;
        this.e = i;
        this.f = i2;
        this.g = i3;
        this.h = i4;
        this.i = i5;
        this.j = i6;
        this.k = i7;
        this.l = i8;
        this.m = i9;
        this.n = i10;
        this.o = i11;
        this.p = i12;
        this.q = i13;
        this.r = i14;
        this.s = i15;
        this.t = i16;
        this.u = i17;
    }
}
