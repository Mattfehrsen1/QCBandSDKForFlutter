package com.realsil.sdk.bbpro.equalizer;

import com.realsil.sdk.bbpro.core.transportlayer.Command;
import com.realsil.sdk.bbpro.profile.AppReq;
import java.util.Locale;

/* loaded from: classes3.dex */
public final class ResetEqDataReq extends AppReq {
    public int a;
    public int b;
    public int c;
    public int d;

    public static class Builder {
        public int a;
        public int b;
        public int c;
        public int d = 2;

        public Builder(int i, int i2, int i3) {
            this.a = i;
            this.b = i2;
            this.c = i3;
        }

        public ResetEqDataReq build() {
            return new ResetEqDataReq(this.a, this.b, this.c, this.d);
        }

        public Builder eqBud(int i) {
            if (this.a == 0) {
                this.d = 2;
                return this;
            }
            this.d = i;
            return this;
        }
    }

    @Override // com.realsil.sdk.bbpro.profile.AppReq
    public Command encode(int i) {
        return encode();
    }

    @Override // com.realsil.sdk.bbpro.profile.AppReq
    public short getCommandId() {
        return (short) 532;
    }

    public int getEqBud() {
        return this.d;
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

    @Override // com.realsil.sdk.bbpro.profile.AppReq
    public short getEventId() {
        return (short) 514;
    }

    public String toString() {
        return "ResetEqDataReq {" + String.format(Locale.US, "\neqType=%d,eqMode=%d,eqIndex=%d，eqBud=%d", Integer.valueOf(this.a), Integer.valueOf(this.b), Integer.valueOf(this.c), Integer.valueOf(this.d)) + "\n}";
    }

    public ResetEqDataReq(int i, int i2, int i3, int i4) {
        this.a = i;
        this.b = i2;
        this.c = i3;
        this.d = i4;
    }

    @Override // com.realsil.sdk.bbpro.profile.AppReq
    public Command encode() {
        return new Command.Builder().writeType(2).packet(getCommandId(), new byte[]{(byte) (this.a & 255), (byte) (this.b & 255), (byte) (this.c & 255), (byte) (this.d & 255)}).eventId(getEventId()).build();
    }
}
