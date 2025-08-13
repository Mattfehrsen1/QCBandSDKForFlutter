package com.realsil.sdk.bbpro.profile;

import com.realsil.sdk.bbpro.core.transportlayer.Command;

/* loaded from: classes3.dex */
public final class GetStatusReq extends AppReq {
    public byte a;

    public static class Builder {
        public byte a;

        public Builder(byte b) {
            this.a = b;
        }

        public GetStatusReq build() {
            return new GetStatusReq(this.a);
        }
    }

    @Override // com.realsil.sdk.bbpro.profile.AppReq
    public Command encode(int i) {
        return encode();
    }

    @Override // com.realsil.sdk.bbpro.profile.AppReq
    public short getCommandId() {
        return (short) 24;
    }

    @Override // com.realsil.sdk.bbpro.profile.AppReq
    public short getEventId() {
        return (short) 25;
    }

    public String toString() {
        return String.format("GetStatusReq(0x%04X) {", Short.valueOf(getCommandId())) + String.format("\n\tindex=0x%02X", Byte.valueOf(this.a)) + "\n}";
    }

    public GetStatusReq(byte b) {
        this.a = b;
    }

    @Override // com.realsil.sdk.bbpro.profile.AppReq
    public Command encode() {
        return new Command.Builder().writeType(2).packet(getCommandId(), new byte[]{this.a}).eventId(getEventId()).build();
    }
}
