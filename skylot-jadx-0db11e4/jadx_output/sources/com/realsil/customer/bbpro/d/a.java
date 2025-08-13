package com.realsil.customer.bbpro.d;

import com.realsil.customer.audioconnect.localplayback.LocalPlaybackTransferEngine;
import java.util.Locale;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/bbpro/d/a.class */
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
        int length = bArr != null ? bArr.length : 0;
        if (length < 1) {
            return null;
        }
        boolean z = bArr[0] == 1;
        int i = 0;
        int i2 = -1;
        int i3 = -1;
        if (length >= 3) {
            i = ((bArr[2] << 8) | (bArr[1] & 255)) & LocalPlaybackTransferEngine.MAX_SEQUENCE_NUMBER;
        }
        if (length >= 5) {
            i2 = bArr[3] & 255;
            i3 = bArr[4] & 255;
        }
        return new a(z, i, i2, i3);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("LowLatencyInfo{");
        sb.append(String.format(Locale.US, "\n\tenabled=%b, latencyValue=%d, maxLatencyLevel=%d, currentLatencyLevel=%d }", Boolean.valueOf(this.a), Integer.valueOf(this.b), Integer.valueOf(this.c), Integer.valueOf(this.d)));
        sb.append("\n}");
        return sb.toString();
    }
}
