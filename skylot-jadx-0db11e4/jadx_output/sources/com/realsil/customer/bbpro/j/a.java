package com.realsil.customer.bbpro.j;

import com.realsil.customer.bbpro.model.DeviceStatusInfo;
import java.util.Locale;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/bbpro/j/a.class */
public final class a {
    public byte a;
    public byte b;
    public byte c;
    public byte d;
    public int e;
    public int f;
    public boolean g;
    public int h;

    public a(byte b, byte b2, byte b3, byte b4, int i, int i2, boolean z, int i3) {
        this.a = b;
        this.b = b2;
        this.c = b3;
        this.d = b4;
        this.e = i;
        this.f = i2;
        this.g = z;
        this.h = i3;
    }

    public static a a(byte[] bArr) {
        int length = bArr != null ? bArr.length : 0;
        if (length < 5) {
            return null;
        }
        int i = length;
        byte b = bArr[0];
        byte b2 = bArr[1];
        byte b3 = bArr[2];
        byte b4 = (byte) ((b3 & 240) >> 4);
        byte b5 = (byte) (b3 & 15);
        byte b6 = bArr[3];
        byte b7 = bArr[4];
        boolean z = false;
        byte b8 = 0;
        if (i >= 6) {
            byte b9 = bArr[5];
            b8 = b9;
            z = (b9 & 255) != 255;
        }
        return new a(b, b2, b4, b5, b6, b7, z, b8);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("BudInfoReportEvent{");
        Locale locale = Locale.US;
        sb.append(String.format(locale, "\n\tbudType=%02X, activeBud=%02X", Byte.valueOf(this.a), Byte.valueOf(this.b)));
        sb.append(String.format(locale, "\n\tLCH: channel=%02X, battery=%d(0x%02X) }", Byte.valueOf(this.c), Integer.valueOf(this.e), Integer.valueOf(this.e)));
        sb.append(String.format(locale, "\n\tRCH: channel=%02X, battery=%d(0x%02X) }", Byte.valueOf(this.d), Integer.valueOf(this.f), Integer.valueOf(this.f)));
        if (this.g) {
            sb.append(String.format(locale, "\n\tCASE: battery=%d(0x%02X) }", Integer.valueOf(this.h), Integer.valueOf(this.h)));
        }
        sb.append("\n}");
        return sb.toString();
    }

    public DeviceStatusInfo a() {
        return new DeviceStatusInfo(this.a, this.b, this.c, this.e, this.d, this.f, this.g, this.h);
    }
}
