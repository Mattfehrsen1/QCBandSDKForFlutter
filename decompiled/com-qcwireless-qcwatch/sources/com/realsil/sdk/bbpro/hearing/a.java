package com.realsil.sdk.bbpro.hearing;

import java.util.Locale;

/* loaded from: classes3.dex */
public final class a {
    public byte a;
    public int b;

    public a(byte b, int i) {
        this.a = b;
        this.b = i;
    }

    public static a a(byte[] bArr) {
        if ((bArr != null ? bArr.length : 0) < 2) {
            return null;
        }
        return new a(bArr[0], bArr[1] & 255);
    }

    public String toString() {
        return "ListeningModeStateReport{" + String.format(Locale.US, "\n\tmode=%02X, activeGroupIndex=%d }", Byte.valueOf(this.a), Integer.valueOf(this.b)) + "\n}";
    }
}
