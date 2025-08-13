package com.realsil.sdk.bbpro.equalizer;

import com.realsil.sdk.core.logger.ZLogger;
import java.util.Locale;

/* loaded from: classes3.dex */
public final class d {
    public int a;
    public int b;
    public int c;
    public int d;

    public d(int i, int i2, int i3, int i4) {
        this.a = i;
        this.b = i2;
        this.c = i3;
        this.d = i4;
    }

    public static d a(byte[] bArr) {
        if (bArr == null || bArr.length < 1) {
            ZLogger.v("invalid params");
            return null;
        }
        return new d(1, 0, bArr[0], bArr.length >= 2 ? bArr[1] : (byte) 254);
    }

    public static d b(byte[] bArr) {
        if (bArr == null || bArr.length < 1) {
            ZLogger.v("invalid params");
            return null;
        }
        int length = bArr.length;
        return new d(0, length >= 2 ? bArr[1] : (byte) 0, bArr[0], length >= 3 ? bArr[2] : (byte) 254);
    }

    public String toString() {
        return "EqIndexEvent {" + String.format(Locale.US, "\n\teqType=0x%02X, eqMode=0x%02X, eqIndex=%d, scene=%d", Integer.valueOf(this.a), Integer.valueOf(this.b), Integer.valueOf(this.c), Integer.valueOf(this.d)) + "\n}";
    }

    public EqEntryIndex a() {
        return new EqEntryIndex(this.a, this.b, this.c, this.d);
    }
}
