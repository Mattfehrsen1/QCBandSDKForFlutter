package com.realsil.sdk.bbpro.apt;

import com.realsil.sdk.bbpro.core.transportlayer.Command;
import com.realsil.sdk.bbpro.profile.AppReq;
import java.util.Locale;

/* loaded from: classes3.dex */
public final class SetAptPowerOnDelayTimeReq extends AppReq {
    public int time;

    public static class Builder {
        public int a;

        public Builder(int i) {
            this.a = i;
        }

        public SetAptPowerOnDelayTimeReq build() {
            return new SetAptPowerOnDelayTimeReq(this.a);
        }
    }

    @Override // com.realsil.sdk.bbpro.profile.AppReq
    public Command encode(int i) {
        return encode();
    }

    @Override // com.realsil.sdk.bbpro.profile.AppReq
    public short getCommandId() {
        return (short) 3130;
    }

    @Override // com.realsil.sdk.bbpro.profile.AppReq
    public short getEventId() {
        return (short) 3129;
    }

    public String toString() {
        return String.format("SetAptPowerOnDelayTimeReq(0x%04X) {", Short.valueOf(getCommandId())) + String.format(Locale.US, "\n\ttime=0x%02X(%d)", Integer.valueOf(this.time), Integer.valueOf(this.time)) + "\n}";
    }

    public SetAptPowerOnDelayTimeReq(int i) {
        this.time = i;
    }

    @Override // com.realsil.sdk.bbpro.profile.AppReq
    public Command encode() {
        return new Command.Builder().writeType(2).packet(getCommandId(), new byte[]{(byte) this.time}).eventId(getEventId()).build();
    }
}
