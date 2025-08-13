package com.realsil.customer.bbpro.apt;

import com.realsil.customer.bbpro.core.transportlayer.Command;
import com.realsil.customer.bbpro.profile.AppReq;
import java.util.Locale;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/bbpro/apt/SetAptNrOnOffReq.class */
public final class SetAptNrOnOffReq extends AppReq {
    public static final byte APT_NR_OFF = 0;
    public static final byte APT_NR_ON = 1;
    public byte a;

    /* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/bbpro/apt/SetAptNrOnOffReq$Builder.class */
    public static class Builder {
        public byte a;

        public Builder(byte b) {
            this.a = b;
        }

        public SetAptNrOnOffReq build() {
            return new SetAptNrOnOffReq(this.a);
        }
    }

    @Override // com.realsil.customer.bbpro.profile.AppReq
    public short getCommandId() {
        return (short) 3124;
    }

    @Override // com.realsil.customer.bbpro.profile.AppReq
    public short getEventId() {
        return (short) 3124;
    }

    @Override // com.realsil.customer.bbpro.profile.AppReq
    public Command encode(int i) {
        return encode();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("SetAptNrOnOffReq(0x%04X) {", Short.valueOf(getCommandId())));
        sb.append(String.format(Locale.US, "\n\ttype=0x%02x", Byte.valueOf(this.a)));
        sb.append("\n}");
        return sb.toString();
    }

    public SetAptNrOnOffReq(byte b) {
        this.a = b;
    }

    @Override // com.realsil.customer.bbpro.profile.AppReq
    public Command encode() {
        return new Command.Builder().writeType(2).packet(getCommandId(), new byte[]{this.a}).eventId(getEventId()).build();
    }
}
