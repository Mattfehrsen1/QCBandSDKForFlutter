package com.realsil.customer.bbpro.profile;

import com.realsil.customer.bbpro.core.transportlayer.Command;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/bbpro/profile/GetCfgSettingsReq.class */
public final class GetCfgSettingsReq extends AppReq {
    public byte a;

    /* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/bbpro/profile/GetCfgSettingsReq$Builder.class */
    public static class Builder {
        public byte a;

        public Builder(byte b) {
            this.a = b;
        }

        public GetCfgSettingsReq build() {
            return new GetCfgSettingsReq(this.a);
        }
    }

    @Override // com.realsil.customer.bbpro.profile.AppReq
    public short getCommandId() {
        return (short) 23;
    }

    @Override // com.realsil.customer.bbpro.profile.AppReq
    public short getEventId() {
        return (short) 24;
    }

    @Override // com.realsil.customer.bbpro.profile.AppReq
    public Command encode(int i) {
        return encode();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("GetCfgSettingsReq(0x%04X) {", Short.valueOf(getCommandId())));
        sb.append(String.format("\n\ttype=0x%02X", Byte.valueOf(this.a)));
        sb.append("\n}");
        return sb.toString();
    }

    public GetCfgSettingsReq(byte b) {
        this.a = b;
    }

    @Override // com.realsil.customer.bbpro.profile.AppReq
    public Command encode() {
        return new Command.Builder().writeType(2).packet(getCommandId(), new byte[]{this.a}).eventId(getEventId()).build();
    }
}
