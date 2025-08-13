package com.realsil.sdk.core;

/* loaded from: classes3.dex */
public final class RtkConfigure {
    public boolean a;
    public boolean b;
    public String c;
    public int d;

    public static class Builder {
        public RtkConfigure a = new RtkConfigure();

        public RtkConfigure build() {
            return this.a;
        }

        public Builder debugEnabled(boolean z) {
            this.a.setDebugEnabled(z);
            return this;
        }

        public Builder globalLogLevel(int i) {
            this.a.setGlobalLogLevel(i);
            return this;
        }

        public Builder logTag(String str) {
            this.a.setLogTag(str);
            return this;
        }

        public Builder printLog(boolean z) {
            this.a.setPrintLog(z);
            return this;
        }
    }

    public int getGlobalLogLevel() {
        return this.d;
    }

    public String getLogTag() {
        return this.c;
    }

    public boolean isDebugEnabled() {
        return this.a;
    }

    public boolean isPrintLog() {
        return this.b;
    }

    public void setDebugEnabled(boolean z) {
        this.a = z;
    }

    public void setGlobalLogLevel(int i) {
        this.d = i;
    }

    public void setLogTag(String str) {
        this.c = str;
    }

    public void setPrintLog(boolean z) {
        this.b = z;
    }

    public String toString() {
        return "RtkConfigure{" + String.format("\n\tdebugEnabled=%b, printLog=%b, logTag=%s, globalLogLevel=0x%02X", Boolean.valueOf(this.a), Boolean.valueOf(this.b), this.c, Integer.valueOf(this.d)) + "\n}";
    }

    public RtkConfigure() {
        this.a = true;
        this.b = true;
        this.c = "Realtek";
        this.d = 1;
    }
}
