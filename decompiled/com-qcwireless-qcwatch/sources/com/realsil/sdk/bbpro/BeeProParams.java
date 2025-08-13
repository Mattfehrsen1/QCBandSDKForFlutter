package com.realsil.sdk.bbpro;

import java.util.UUID;

/* loaded from: classes3.dex */
public class BeeProParams {
    public boolean a = false;
    public boolean b = false;
    public boolean c = false;
    public boolean d = false;
    public boolean e = true;
    public boolean f = false;
    public UUID g = ConnectionParameters.g;
    public int h = 0;

    public static class Builder {
        public BeeProParams a = new BeeProParams();

        public Builder autoConnectOnStart(boolean z) {
            return this;
        }

        public Builder bindHfpDisconnection(boolean z) {
            this.a.a(z);
            return this;
        }

        public BeeProParams build() {
            return this.a;
        }

        public Builder connectA2dp(boolean z) {
            this.a.b(z);
            return this;
        }

        public Builder eqModuleEnabled(boolean z) {
            return this;
        }

        public Builder functionModuleEnabled(boolean z) {
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

        public Builder otaModuleEnabled(boolean z) {
            return this;
        }

        public Builder serverEnabled(boolean z) {
            this.a.e(z);
            return this;
        }

        public Builder syncDataWhenConnected(boolean z) {
            this.a.f(z);
            return this;
        }

        public Builder transport(int i) {
            this.a.a(i);
            return this;
        }

        public Builder ttsModuleEnabled(boolean z) {
            return this;
        }

        public Builder uuid(UUID uuid) {
            this.a.a(uuid);
            return this;
        }
    }

    public void d(boolean z) {
        this.c = z;
    }

    public final void e(boolean z) {
        this.a = z;
    }

    public boolean f() {
        return this.a;
    }

    public boolean g() {
        return this.e;
    }

    public String toString() {
        return "BeeProParameters{" + String.format("\n\tserverEnabled=%b,", Boolean.valueOf(this.a)) + String.format("\n\tconnectA2dp=%b,", Boolean.valueOf(this.d)) + String.format("\n\tlistenA2dp=%b, listenHfp=%b\n", Boolean.valueOf(this.b), Boolean.valueOf(this.c)) + String.format("\n\tsyncDataWhenConnected=%b,", Boolean.valueOf(this.e)) + "\n}";
    }

    public void b(boolean z) {
        this.d = z;
    }

    public final void c(boolean z) {
        this.b = z;
    }

    public boolean d() {
        return this.d;
    }

    public boolean e() {
        return this.c;
    }

    public final void f(boolean z) {
        this.e = z;
    }

    public void a(boolean z) {
        this.f = z;
    }

    public UUID b() {
        return this.g;
    }

    public boolean c() {
        return this.f;
    }

    public void a(int i) {
        this.h = i;
    }

    public int a() {
        return this.h;
    }

    public final void a(UUID uuid) {
        this.g = uuid;
    }
}
