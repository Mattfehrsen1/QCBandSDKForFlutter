package com.realsil.sdk.bbpro.equalizer;

import java.util.Locale;

/* loaded from: classes3.dex */
public class EqEntryIndex {
    public int a;
    public int b;
    public int c;
    public int d;

    public EqEntryIndex(int i, int i2, int i3, int i4) {
        this.d = i4;
        this.a = i;
        this.b = i2;
        this.c = i3;
    }

    public int getEqIndex() {
        return this.c;
    }

    public int getEqMode() {
        return this.b;
    }

    public int getEqType() {
        return this.a;
    }

    public int getIndex() {
        return this.c;
    }

    public int getScene() {
        return this.d;
    }

    public boolean isMicEq() {
        return this.a == 1;
    }

    public boolean isSpkEq() {
        return this.a == 0;
    }

    public String toString() {
        return "EqEntryIndex {" + String.format(Locale.US, "\nscene=0x%02X, eqType=%d,eqMode=%d,eqIndex=%d", Integer.valueOf(this.d), Integer.valueOf(this.a), Integer.valueOf(this.b), Integer.valueOf(this.c)) + "\n}";
    }
}
