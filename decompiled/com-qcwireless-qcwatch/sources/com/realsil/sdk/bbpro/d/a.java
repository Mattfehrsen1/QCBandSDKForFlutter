package com.realsil.sdk.bbpro.d;

import java.util.Locale;

/* loaded from: classes3.dex */
public final class a {
    public boolean a;
    public int b;
    public int c;
    public int d;

    public a(boolean z, int i, int i2, int i3) {
        this.a = z;
        this.b = i;
        this.c = i2;
        this.d = i3;
    }

    public static a a(byte[] bArr) {
        int i;
        int length = bArr != null ? bArr.length : 0;
        if (length < 1) {
            return null;
        }
        boolean z = bArr[0] == 1;
        int i2 = length >= 3 ? ((bArr[2] << 8) | (bArr[1] & 255)) & 65535 : 0;
        int i3 = -1;
        if (length >= 5) {
            i3 = bArr[3] & 255;
            i = bArr[4] & 255;
        } else {
            i = -1;
        }
        return new a(z, i2, i3, i);
    }

    public String toString() {
        return "LowLatencyInfo{" + String.format(Locale.US, "\n\tenabled=%b, latencyValue=%d, maxLatencyLevel=%d, currentLatencyLevel=%d }", Boolean.valueOf(this.a), Integer.valueOf(this.b), Integer.valueOf(this.c), Integer.valueOf(this.d)) + "\n}";
    }
}
