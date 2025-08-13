package com.realsil.sdk.bbpro.d;

import java.util.Locale;

/* loaded from: classes3.dex */
public final class b {
    public int a;
    public int b;

    public b(int i, int i2) {
        this.b = i;
        this.a = i2;
    }

    public static b a(byte[] bArr) {
        if ((bArr != null ? bArr.length : 0) < 3) {
            return null;
        }
        return new b(((bArr[1] & 255) | (bArr[2] << 8)) & 65535, bArr[0] & 255);
    }

    public String toString() {
        return "LowLatencyLevelReport{" + String.format(Locale.US, "\n\tlatencyValue=%d, currentLatencyLevel=%d }", Integer.valueOf(this.b), Integer.valueOf(this.a)) + "\n}";
    }
}
