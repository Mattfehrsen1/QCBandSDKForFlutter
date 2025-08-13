package com.realsil.sdk.bbpro.apt;

import com.realsil.sdk.bbpro.core.transportlayer.Command;
import com.realsil.sdk.bbpro.profile.AppReq;
import java.util.Locale;

/* loaded from: classes3.dex */
public final class SetAptNrOnOffReq extends AppReq {
    public static final byte APT_NR_OFF = 0;
    public static final byte APT_NR_ON = 1;
    public byte a;

    public static class Builder {
        public byte a;

        public Builder(byte b) {
            this.a = b;
        }

        public SetAptNrOnOffReq build() {
            return new SetAptNrOnOffReq(this.a);
        }
    }

    @Override // com.realsil.sdk.bbpro.profile.AppReq
    public Command encode(int i) {
        return encode();
    }

    @Override // com.realsil.sdk.bbpro.profile.AppReq
    public short getCommandId() {
        return (short) 3124;
    }

    @Override // com.realsil.sdk.bbpro.profile.AppReq
    public short getEventId() {
        return (short) 3124;
    }

    public String toString() {
        return String.format("SetAptNrOnOffReq(0x%04X) {", Short.valueOf(getCommandId())) + String.format(Locale.US, "\n\ttype=0x%02x", Byte.valueOf(this.a)) + "\n}";
    }

    public SetAptNrOnOffReq(byte b) {
        this.a = b;
    }

    @Override // com.realsil.sdk.bbpro.profile.AppReq
    public Command encode() {
        return new Command.Builder().writeType(2).packet(getCommandId(), new byte[]{this.a}).eventId(getEventId()).build();
    }
}
