package com.realsil.customer.bbpro;

import androidx.annotation.NonNull;
import java.util.UUID;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/bbpro/BeeProParams.class */
public class BeeProParams {
    public boolean a = false;
    public boolean b = false;
    public boolean c = false;
    public boolean d = false;
    public boolean e = true;
    public boolean f = false;
    public UUID g = ConnectionParameters.g;
    public int h = 0;

    /* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/bbpro/BeeProParams$Builder.class */
    public static class Builder {
        public BeeProParams a = new BeeProParams();

        public Builder autoConnectOnStart(boolean z) {
            return this;
        }

        public Builder serverEnabled(boolean z) {
            this.a.e(z);
            return this;
        }

        public Builder listenA2dp(boolean z) {
            this.a.c(z);
            return this;
        }

        public Builder listenHfp(boolean z) {
            this.a.d(z);
            return this;
        }

        public Builder bindHfpDisconnection(boolean z) {
            this.a.a(z);
            return this;
        }

        public Builder connectA2dp(boolean z) {
            this.a.b(z);
            return this;
        }

        public Builder ttsModuleEnabled(boolean z) {
            return this;
        }

        public Builder otaModuleEnabled(boolean z) {
            return this;
        }

        public Builder eqModuleEnabled(boolean z) {
            return this;
        }

        public Builder functionModuleEnabled(boolean z) {
            return this;
        }

        public Builder syncDataWhenConnected(boolean z) {
            this.a.f(z);
            return this;
        }

        public Builder uuid(UUID uuid) {
            this.a.a(uuid);
            return this;
        }

        public Builder transport(int i) {
            this.a.a(i);
            return this;
        }

        public BeeProParams build() {
            return this.a;
        }
    }

    public boolean f() {
        return this.a;
    }

    public void d(boolean z) {
        this.c = z;
    }

    public boolean g() {
        return this.e;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("BeeProParameters{");
        sb.append(String.format("\n\tserverEnabled=%b,", Boolean.valueOf(this.a)));
        sb.append(String.format("\n\tconnectA2dp=%b,", Boolean.valueOf(this.d)));
        sb.append(String.format("\n\tlistenA2dp=%b, listenHfp=%b\n", Boolean.valueOf(this.b), Boolean.valueOf(this.c)));
        sb.append(String.format("\n\tsyncDataWhenConnected=%b,", Boolean.valueOf(this.e)));
        sb.append("\n}");
        return sb.toString();
    }

    public final void e(boolean z) {
        this.a = z;
    }

    public boolean e() {
        return this.c;
    }

    public boolean d() {
        return this.d;
    }

    public void b(boolean z) {
        this.d = z;
    }

    public final void c(boolean z) {
        this.b = z;
    }

    public final void f(boolean z) {
        this.e = z;
    }

    public boolean c() {
        return this.f;
    }

    public void a(boolean z) {
        this.f = z;
    }

    public UUID b() {
        return this.g;
    }

    public void a(int i) {
        this.h = i;
    }

    public int a() {
        return this.h;
    }

    public final void a(@NonNull UUID uuid) {
        this.g = uuid;
    }
}
