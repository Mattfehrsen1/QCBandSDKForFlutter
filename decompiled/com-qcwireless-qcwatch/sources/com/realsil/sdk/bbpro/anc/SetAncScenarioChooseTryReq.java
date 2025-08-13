package com.realsil.sdk.bbpro.anc;

import com.realsil.sdk.bbpro.core.transportlayer.Command;
import com.realsil.sdk.bbpro.profile.AppReq;
import java.util.Locale;

/* loaded from: classes3.dex */
public final class SetAncScenarioChooseTryReq extends AppReq {
    public static final byte LL_APT_OFF = 0;
    public static final byte LL_APT_ON = 1;
    public byte a;
    public int b;
    public byte c;
    public int d;

    public static class Builder {
        public byte a;
        public int b;
        public byte c;
        public int d;

        public SetAncScenarioChooseTryReq build() {
            return new SetAncScenarioChooseTryReq(this.a, this.b, this.c, this.d);
        }

        public Builder lch(byte b, int i) {
            this.a = b;
            this.b = i;
            return this;
        }

        public Builder rch(byte b, int i) {
            this.c = b;
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
        return (short) 3141;
    }

    @Override // com.realsil.sdk.bbpro.profile.AppReq
    public short getEventId() {
        return (short) 3141;
    }

    public String toString() {
        return String.format("SetAncScenarioChooseTryReq(0x%04X) {", Short.valueOf(getCommandId())) + String.format(Locale.US, "\n\tL=0x%02x-%d,R=0x%02x-%d)", Byte.valueOf(this.a), Integer.valueOf(this.b), Byte.valueOf(this.c), Integer.valueOf(this.d)) + "\n}";
    }

    public SetAncScenarioChooseTryReq(byte b, int i, byte b2, int i2) {
        this.a = b;
        this.b = i;
        this.c = b2;
        this.d = i2;
    }

    @Override // com.realsil.sdk.bbpro.profile.AppReq
    public Command encode() {
        return new Command.Builder().writeType(2).packet(getCommandId(), new byte[]{this.a, (byte) this.b, this.c, (byte) this.d}).eventId(getEventId()).build();
    }
}
