package com.realsil.customer.core;

import androidx.annotation.NonNull;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/core/RtkConfigure.class */
public final class RtkConfigure {
    public boolean a;
    public boolean b;
    public boolean c;
    public String d;
    public int e;

    /* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/core/RtkConfigure$Builder.class */
    public static class Builder {
        public final RtkConfigure a = new RtkConfigure(0);

        public Builder debugEnabled(boolean z) {
            this.a.setDebugEnabled(z);
            return this;
        }

        public Builder devModeEnabled(boolean z) {
            this.a.setDevModeEnabled(z);
            return this;
        }

        public Builder printLog(boolean z) {
            this.a.setPrintLog(z);
            return this;
        }

        public Builder logTag(@NonNull String str) {
            this.a.setLogTag(str);
            return this;
        }

        public Builder globalLogLevel(int i) {
            this.a.setGlobalLogLevel(i);
            return this;
        }

        public RtkConfigure build() {
            return this.a;
        }
    }

    public /* synthetic */ RtkConfigure(int i) {
        this();
    }

    public boolean isDebugEnabled() {
        return this.b;
    }

    public void setDebugEnabled(boolean z) {
        this.b = z;
    }

    public boolean isDevModeEnabled() {
        return this.a;
    }

    public void setDevModeEnabled(boolean z) {
        this.a = z;
    }

    public String getLogTag() {
        return this.d;
    }

    public void setLogTag(String str) {
        this.d = str;
    }

    public int getGlobalLogLevel() {
        return this.e;
    }

    public void setGlobalLogLevel(int i) {
        this.e = i;
    }

    public boolean isPrintLog() {
        return this.c;
    }

    public void setPrintLog(boolean z) {
        this.c = z;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("RtkConfigure{");
        sb.append(String.format("\n\tdebugEnabled=%b, printLog=%b, logTag=%s, globalLogLevel=0x%02X", Boolean.valueOf(this.b), Boolean.valueOf(this.c), this.d, Integer.valueOf(this.e)));
        sb.append("\n}");
        return sb.toString();
    }

    public RtkConfigure() {
        this.a = true;
        this.b = true;
        this.c = true;
        this.d = "Realtek";
        this.e = 1;
    }
}
