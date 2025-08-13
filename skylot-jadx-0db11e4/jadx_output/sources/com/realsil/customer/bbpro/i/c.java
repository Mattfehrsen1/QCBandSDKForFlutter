package com.realsil.customer.bbpro.i;

import androidx.annotation.NonNull;
import com.realsil.customer.audioconnect.localplayback.LocalPlaybackTransferEngine;
import java.util.Locale;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/bbpro/i/c.class */
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

    public static c a(@NonNull byte[] bArr) {
        int length = bArr.length;
        int i = 0;
        int i2 = 0;
        if (bArr[1] == 0) {
            if (length >= 4) {
                i = ((bArr[2] << 8) | (bArr[3] & 255)) & LocalPlaybackTransferEngine.MAX_SEQUENCE_NUMBER;
            }
            if (length >= 6) {
                i2 = ((bArr[4] << 8) | (bArr[5] & 255)) & LocalPlaybackTransferEngine.MAX_SEQUENCE_NUMBER;
            } else if (i == 0 || i == 1) {
                i2 = 0;
            } else if (i == 256) {
                i2 = 1;
            } else if (i == 257) {
                i2 = 2;
            } else if (i == 258) {
                i2 = 3;
            } else if (i == 259) {
                i2 = 4;
            } else if (i == 260) {
                i2 = 5;
            }
        }
        return new c(i, i2);
    }
}
