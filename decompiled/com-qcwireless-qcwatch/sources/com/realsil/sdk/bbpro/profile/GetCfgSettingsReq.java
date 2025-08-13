package com.realsil.sdk.bbpro.profile;

import com.realsil.sdk.bbpro.core.transportlayer.Command;

/* loaded from: classes3.dex */
public final class GetCfgSettingsReq extends AppReq {
    public byte a;

    public static class Builder {
        public byte a;

        public Builder(byte b) {
            this.a = b;
        }

        public GetCfgSettingsReq build() {
            return new GetCfgSettingsReq(this.a);
        }
    }

    @Override // com.realsil.sdk.bbpro.profile.AppReq
    public Command encode(int i) {
        return encode();
    }

    @Override // com.realsil.sdk.bbpro.profile.AppReq
    public short getCommandId() {
        return (short) 23;
    }

    @Override // com.realsil.sdk.bbpro.profile.AppReq
    public short getEventId() {
        return (short) 24;
    }

    public String toString() {
        return String.format("GetCfgSettingsReq(0x%04X) {", Short.valueOf(getCommandId())) + String.format("\n\ttype=0x%02X", Byte.valueOf(this.a)) + "\n}";
    }

    public GetCfgSettingsReq(byte b) {
        this.a = b;
    }

    @Override // com.realsil.sdk.bbpro.profile.AppReq
    public Command encode() {
        return new Command.Builder().writeType(2).packet(getCommandId(), new byte[]{this.a}).eventId(getEventId()).build();
    }
}
