package com.realsil.customer.bbpro.profile;

import com.realsil.customer.bbpro.core.transportlayer.Command;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/bbpro/profile/MmiReq.class */
public final class MmiReq extends AppReq {
    public byte a;

    /* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/bbpro/profile/MmiReq$Builder.class */
    public static class Builder {
        public byte a;

        public Builder(byte b) {
            this.a = b;
        }

        public MmiReq build() {
            return new MmiReq(this.a);
        }
    }

    @Override // com.realsil.customer.bbpro.profile.AppReq
    public short getCommandId() {
        return (short) 4;
    }

    @Override // com.realsil.customer.bbpro.profile.AppReq
    public Command encode(int i) {
        return encode();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("MmiReq(0x%04X) {", Short.valueOf(getCommandId())));
        sb.append(String.format("\n\taction=0x%02X", Byte.valueOf(this.a)));
        sb.append("\n}");
        return sb.toString();
    }

    public MmiReq(byte b) {
        this.a = b;
    }

    @Override // com.realsil.customer.bbpro.profile.AppReq
    public Command encode() {
        return new Command.Builder().writeType(2).packet(getCommandId(), new byte[]{0, this.a}).eventId(getEventId()).build();
    }
}
