package com.realsil.customer.core.bluetooth.connection.le;

import com.realsil.customer.core.bluetooth.utils.BluetoothHelper;
import java.util.Locale;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/core/bluetooth/connection/le/GattConnParams.class */
public final class GattConnParams {
    public static final int MAX_RECONNECT_TIMES = 2;
    public final String a;
    public final boolean b;
    public final boolean c;
    public final int d;
    public final int e;
    public final boolean f;

    /* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/core/bluetooth/connection/le/GattConnParams$Builder.class */
    public static class Builder {
        public String a;
        public boolean b;
        public boolean c;
        public int d = 1;
        public int e = 2;
        public boolean f;

        public Builder address(String str) {
            this.a = str;
            return this;
        }

        public Builder hid(boolean z) {
            this.c = z;
            return this;
        }

        public Builder reconnectTimes(int i) {
            this.d = i;
            return this;
        }

        public Builder refreshCache(boolean z) {
            this.f = z;
            return this;
        }

        public Builder transport(int i) {
            this.e = i;
            return this;
        }

        public Builder createBond(boolean z) {
            this.b = z;
            return this;
        }

        public GattConnParams build() {
            return new GattConnParams(this.a, this.b, this.c, this.d, this.e, this.f);
        }
    }

    public GattConnParams(String str, boolean z, boolean z2, int i, int i2, boolean z3) {
        this.d = 1;
        this.e = 2;
        this.a = str;
        this.b = z;
        this.c = z2;
        this.d = i;
        this.e = i2;
        this.f = z3;
    }

    public String getAddress() {
        return this.a;
    }

    public boolean isHid() {
        return this.c;
    }

    public int getTransport() {
        return this.e;
    }

    public int getReconnectTimes() {
        return this.d;
    }

    public boolean isRefreshCache() {
        return this.f;
    }

    public boolean isCreateBond() {
        return this.b;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("ConnectParams{\n");
        sb.append(String.format("\n\taddress=%s， isHid=%b", BluetoothHelper.formatAddress(this.a, true), Boolean.valueOf(this.c)));
        sb.append(String.format("\n\tcreateBond=%b\n", Boolean.valueOf(this.b)));
        Locale locale = Locale.US;
        sb.append(String.format(locale, "\n\ttransport=%d", Integer.valueOf(this.e)));
        sb.append(String.format("\n\trefreshCache=%b\n", Boolean.valueOf(this.f)));
        sb.append(String.format(locale, "\n\treconnectTimes=%d", Integer.valueOf(this.d)));
        sb.append("\n}");
        return sb.toString();
    }
}
