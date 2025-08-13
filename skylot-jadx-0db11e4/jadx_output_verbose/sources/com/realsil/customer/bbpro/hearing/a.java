package com.realsil.customer.bbpro.hearing;

import java.util.Locale;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/bbpro/hearing/a.class */
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
        StringBuilder sb = new StringBuilder();
        sb.append("ListeningModeStateReport{");
        sb.append(String.format(Locale.US, "\n\tmode=%02X, activeGroupIndex=%d }", Byte.valueOf(this.a), Integer.valueOf(this.b)));
        sb.append("\n}");
        return sb.toString();
    }
}
