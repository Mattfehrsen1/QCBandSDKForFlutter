package com.realsil.sdk.bbpro.i;

import java.util.Locale;

/* loaded from: classes3.dex */
public final class c {
    public static final c c = new c(0, 0);
    public int a;
    public int b;

    public c(int i, int i2) {
        this.a = i;
        this.b = i2;
    }

    public int a() {
        return this.a;
    }

    public int b() {
        return this.b;
    }

    public String toString() {
        return String.format(Locale.US, "CmdSetInfo{ cmdSetVersion=0x%04X, eqSpecVersion=0x%04X }", Integer.valueOf(this.a), Integer.valueOf(this.b));
    }

    public static c a(byte[] bArr) {
        int length = bArr.length;
        int i = 1;
        int i2 = 0;
        if (bArr[1] != 0) {
            i = 0;
        } else {
            int i3 = length >= 4 ? ((bArr[2] << 8) | (bArr[3] & 255)) & 65535 : 0;
            if (length >= 6) {
                i = ((bArr[5] & 255) | (bArr[4] << 8)) & 65535;
            } else {
                if (i3 != 0 && i3 != 1) {
                    if (i3 != 256) {
                        if (i3 == 257) {
                            i2 = i3;
                            i = 2;
                        } else if (i3 == 258) {
                            i2 = i3;
                            i = 3;
                        } else if (i3 == 259) {
                            i2 = i3;
                            i = 4;
                        } else if (i3 == 260) {
                            i2 = i3;
                            i = 5;
                        }
                    }
                }
                i2 = i3;
                i = 0;
            }
            i2 = i3;
        }
        return new c(i2, i);
    }
}
