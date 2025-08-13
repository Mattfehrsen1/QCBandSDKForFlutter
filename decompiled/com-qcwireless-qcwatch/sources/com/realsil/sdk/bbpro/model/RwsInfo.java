package com.realsil.sdk.bbpro.model;

import java.util.Locale;

/* loaded from: classes3.dex */
public final class RwsInfo {
    public int caseBatteryValue;
    public boolean isRws;
    public int leftBatteryValue;
    public boolean leftConnected;
    public int rightBatteryValue;
    public boolean rightConnected;
    public int activeBud = 0;
    public byte leftActiveChannel = 0;
    public byte rightActiveChannel = 0;
    public boolean caseBatterySupported = false;

    public int getActiveBatteryValue() {
        return this.isRws ? this.activeBud == 0 ? this.leftBatteryValue : this.rightBatteryValue : this.leftBatteryValue;
    }

    public boolean isRwsEngaged() {
        return this.isRws && this.leftConnected && this.rightConnected;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("RwsInfo{");
        boolean z = this.isRws;
        if (z) {
            Locale locale = Locale.US;
            sb.append(String.format(locale, "isRws=%b, activeBud=%d", Boolean.valueOf(z), Integer.valueOf(this.activeBud)));
            sb.append(String.format(locale, "\n\tL: connected=%b, channel=%d, battery=%d\n\tR: connected=%b, channel=%d, battery=%d", Boolean.valueOf(this.leftConnected), Byte.valueOf(this.leftActiveChannel), Integer.valueOf(this.leftBatteryValue), Boolean.valueOf(this.rightConnected), Byte.valueOf(this.rightActiveChannel), Integer.valueOf(this.rightBatteryValue)));
        }
        if (this.caseBatterySupported) {
            sb.append(String.format(Locale.US, "\n\tcaseBatteryValue=%d", Integer.valueOf(this.caseBatteryValue)));
        }
        sb.append("\n}");
        return sb.toString();
    }
}
