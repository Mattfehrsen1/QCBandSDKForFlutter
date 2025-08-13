package com.realsil.customer.bbpro.apt;

import com.realsil.customer.bbpro.core.transportlayer.Command;
import com.realsil.customer.bbpro.profile.AppReq;
import java.util.Locale;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/bbpro/apt/SetAptPowerOnDelayTimeReq.class */
public final class SetAptPowerOnDelayTimeReq extends AppReq {
    public int time;

    /* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/bbpro/apt/SetAptPowerOnDelayTimeReq$Builder.class */
    public static class Builder {
        public int a;

        public Builder(int i) {
            this.a = i;
        }

        public SetAptPowerOnDelayTimeReq build() {
            return new SetAptPowerOnDelayTimeReq(this.a);
        }
    }

    @Override // com.realsil.customer.bbpro.profile.AppReq
    public short getCommandId() {
        return (short) 3130;
    }

    @Override // com.realsil.customer.bbpro.profile.AppReq
    public short getEventId() {
        return (short) 3129;
    }

    @Override // com.realsil.customer.bbpro.profile.AppReq
    public Command encode(int i) {
        return encode();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("SetAptPowerOnDelayTimeReq(0x%04X) {", Short.valueOf(getCommandId())));
        sb.append(String.format(Locale.US, "\n\ttime=0x%02X(%d)", Integer.valueOf(this.time), Integer.valueOf(this.time)));
        sb.append("\n}");
        return sb.toString();
    }

    public SetAptPowerOnDelayTimeReq(int i) {
        this.time = i;
    }

    @Override // com.realsil.customer.bbpro.profile.AppReq
    public Command encode() {
        return new Command.Builder().writeType(2).packet(getCommandId(), new byte[]{(byte) this.time}).eventId(getEventId()).build();
    }
}
