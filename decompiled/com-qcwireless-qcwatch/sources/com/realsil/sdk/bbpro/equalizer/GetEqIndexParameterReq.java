package com.realsil.sdk.bbpro.equalizer;

import com.realsil.sdk.core.logger.ZLogger;
import java.util.Locale;

/* loaded from: classes3.dex */
public final class GetEqIndexParameterReq {
    public int a;
    public int b;
    public int c;
    public int d;

    public static class Builder {
        public int a = 2;
        public int b;
        public int c;
        public int d;

        public Builder(int i, int i2, int i3) {
            this.b = i;
            this.c = i2;
            this.d = i3;
        }

        public GetEqIndexParameterReq build() {
            return new GetEqIndexParameterReq(this.a, this.b, this.c, this.d);
        }

        public Builder eqBud(int i) {
            this.a = i;
            return this;
        }
    }

    public final byte[] a(int i) {
        return new byte[]{4, 2, (byte) (i & 255)};
    }

    public final byte[] a(int i, int i2) {
        return new byte[]{4, 2, (byte) (i2 & 255), (byte) (i & 255)};
    }

    public final byte[] a(int i, int i2, int i3) {
        return new byte[]{4, 2, (byte) (i3 & 255), (byte) (i & 255), (byte) (i2 & 255)};
    }

    public final byte[] a(int i, int i2, int i3, int i4) {
        return new byte[]{4, 2, (byte) (i4 & 255), (byte) (i & 255), (byte) (i2 & 255), (byte) (i3 & 255)};
    }

    public byte[] encode(int i) {
        if (i == 1 || i == 2 || i == 3) {
            return a(this.d);
        }
        if (i == 4 || i == 5 || i == 256 || i == 257) {
            return a(this.c, this.d);
        }
        if (i == 258) {
            return a(this.b, this.c, this.d);
        }
        if (i >= 512) {
            return a(this.a, this.b, this.c, this.d);
        }
        ZLogger.d(String.format("invalid specVersion:0x%04X", Integer.valueOf(i)));
        return null;
    }

    public int getEqIndex() {
        return this.d;
    }

    public int getEqMode() {
        return this.c;
    }

    public int getEqType() {
        return this.b;
    }

    public String toString() {
        return "GetEqIndexParameterReq {" + String.format(Locale.US, "\neqType=%d,eqMode=%d,eqIndex=%d", Integer.valueOf(this.b), Integer.valueOf(this.c), Integer.valueOf(this.d)) + "\n}";
    }

    public GetEqIndexParameterReq(int i, int i2, int i3, int i4) {
        this.a = i;
        this.b = i2;
        this.c = i3;
        this.d = i4;
    }
}
