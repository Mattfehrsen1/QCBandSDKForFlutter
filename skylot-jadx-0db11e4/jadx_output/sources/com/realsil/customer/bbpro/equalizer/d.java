package com.realsil.customer.bbpro.equalizer;

import com.realsil.customer.core.logger.ZLogger;
import java.util.Locale;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/bbpro/equalizer/d.class */
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

    public static d b(byte[] bArr) {
        if (bArr == null || bArr.length < 1) {
            ZLogger.v("invalid params");
            return null;
        }
        int length = bArr.length;
        byte b = 254;
        byte b2 = 0;
        byte b3 = bArr[0];
        if (length >= 2) {
            b2 = bArr[1];
        }
        if (length >= 3) {
            b = bArr[2];
        }
        return new d(0, b2, b3, b);
    }

    public static d a(byte[] bArr) {
        if (bArr == null || bArr.length < 1) {
            ZLogger.v("invalid params");
            return null;
        }
        int length = bArr.length;
        byte b = 254;
        byte b2 = bArr[0];
        if (length >= 2) {
            b = bArr[1];
        }
        return new d(1, 0, b2, b);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("EqIndexEvent {");
        sb.append(String.format(Locale.US, "\n\teqType=0x%02X, eqMode=0x%02X, eqIndex=%d, scene=%d", Integer.valueOf(this.a), Integer.valueOf(this.b), Integer.valueOf(this.c), Integer.valueOf(this.d)));
        sb.append("\n}");
        return sb.toString();
    }

    public EqEntryIndex a() {
        return new EqEntryIndex(this.a, this.b, this.c, this.d);
    }
}
