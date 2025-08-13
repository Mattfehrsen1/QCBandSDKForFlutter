package com.realsil.customer.bbpro.d;

import com.realsil.customer.audioconnect.localplayback.LocalPlaybackTransferEngine;
import java.util.Locale;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/bbpro/d/b.class */
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
        return new b(((bArr[2] << 8) | (bArr[1] & 255)) & LocalPlaybackTransferEngine.MAX_SEQUENCE_NUMBER, bArr[0] & 255);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("LowLatencyLevelReport{");
        sb.append(String.format(Locale.US, "\n\tlatencyValue=%d, currentLatencyLevel=%d }", Integer.valueOf(this.b), Integer.valueOf(this.a)));
        sb.append("\n}");
        return sb.toString();
    }
}
