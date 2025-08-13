package com.realsil.sdk.bbpro.profile;

import com.realsil.sdk.bbpro.core.transportlayer.Command;

/* loaded from: classes3.dex */
public final class MmiReq extends AppReq {
    public byte a;

    public static class Builder {
        public byte a;

        public Builder(byte b) {
            this.a = b;
        }

        public MmiReq build() {
            return new MmiReq(this.a);
        }
    }

    @Override // com.realsil.sdk.bbpro.profile.AppReq
    public Command encode(int i) {
        return encode();
    }

    @Override // com.realsil.sdk.bbpro.profile.AppReq
    public short getCommandId() {
        return (short) 4;
    }

    public String toString() {
        return String.format("MmiReq(0x%04X) {", Short.valueOf(getCommandId())) + String.format("\n\taction=0x%02X", Byte.valueOf(this.a)) + "\n}";
    }

    public MmiReq(byte b) {
        this.a = b;
    }

    @Override // com.realsil.sdk.bbpro.profile.AppReq
    public Command encode() {
        return new Command.Builder().writeType(2).packet(getCommandId(), new byte[]{0, this.a}).eventId(getEventId()).build();
    }
}
