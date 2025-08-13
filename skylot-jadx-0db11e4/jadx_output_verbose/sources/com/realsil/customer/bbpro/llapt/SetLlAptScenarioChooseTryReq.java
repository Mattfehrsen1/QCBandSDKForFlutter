package com.realsil.customer.bbpro.llapt;

import com.realsil.customer.bbpro.core.transportlayer.Command;
import com.realsil.customer.bbpro.profile.AppReq;
import java.util.Locale;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/bbpro/llapt/SetLlAptScenarioChooseTryReq.class */
public final class SetLlAptScenarioChooseTryReq extends AppReq {
    public static final byte LL_APT_OFF = 0;
    public static final byte LL_APT_ON = 1;
    public byte a;
    public int b;
    public byte c;
    public int d;

    /* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/bbpro/llapt/SetLlAptScenarioChooseTryReq$Builder.class */
    public static class Builder {
        public byte a;
        public int b;
        public byte c;
        public int d;

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

        public SetLlAptScenarioChooseTryReq build() {
            return new SetLlAptScenarioChooseTryReq(this.a, this.b, this.c, this.d);
        }
    }

    @Override // com.realsil.customer.bbpro.profile.AppReq
    public short getCommandId() {
        return (short) 3127;
    }

    @Override // com.realsil.customer.bbpro.profile.AppReq
    public short getEventId() {
        return (short) 3127;
    }

    @Override // com.realsil.customer.bbpro.profile.AppReq
    public Command encode(int i) {
        return encode();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("SetLlAptScenarioChooseTryReq(0x%04X) {", Short.valueOf(getCommandId())));
        sb.append(String.format(Locale.US, "\n\tL=0x%02x-%d,R=0x%02x-%d)", Byte.valueOf(this.a), Integer.valueOf(this.b), Byte.valueOf(this.c), Integer.valueOf(this.d)));
        sb.append("\n}");
        return sb.toString();
    }

    public SetLlAptScenarioChooseTryReq(byte b, int i, byte b2, int i2) {
        this.a = b;
        this.b = i;
        this.c = b2;
        this.d = i2;
    }

    @Override // com.realsil.customer.bbpro.profile.AppReq
    public Command encode() {
        return new Command.Builder().writeType(2).packet(getCommandId(), new byte[]{this.a, (byte) this.b, this.c, (byte) this.d}).eventId(getEventId()).build();
    }
}
