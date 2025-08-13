package com.realsil.customer.bbpro.vendor;

import com.realsil.customer.bbpro.core.transportlayer.Command;
import com.realsil.customer.bbpro.profile.AppReq;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/bbpro/vendor/GetFwVersionReq.class */
public final class GetFwVersionReq extends AppReq {
    public static final byte TYPE_PRIMARY_FW_VERSION = 0;
    public static final byte TYPE_SECONDARY_FW_VERSION = 1;
    public static final byte TYPE_OTA_PRIMARY_FW_VERSION = 2;
    public static final byte TYPE_OTA_SECONDARY_FW_VERSION = 3;
    public static final byte TYPE_PRIMARY_UI_OTA_VERSION = 4;
    public static final byte TYPE_SECONDARY_UI_OTA_VERSION = 5;
    public byte a;

    /* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/bbpro/vendor/GetFwVersionReq$Builder.class */
    public static class Builder {
        public byte a;

        public Builder(byte b) {
            this.a = b;
        }

        public GetFwVersionReq build() {
            return new GetFwVersionReq(this.a);
        }
    }

    @Override // com.realsil.customer.bbpro.profile.AppReq
    public short getCommandId() {
        return (short) 776;
    }

    @Override // com.realsil.customer.bbpro.profile.AppReq
    public short getEventId() {
        return (short) 2313;
    }

    @Override // com.realsil.customer.bbpro.profile.AppReq
    public Command encode(int i) {
        return encode();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("GetFwVersionReq(0x%04X) {", Short.valueOf(getCommandId())));
        sb.append(String.format("\n\ttype=0x%02X", Byte.valueOf(this.a)));
        sb.append("\n}");
        return sb.toString();
    }

    public GetFwVersionReq(byte b) {
        this.a = b;
    }

    @Override // com.realsil.customer.bbpro.profile.AppReq
    public Command encode() {
        return new Command.Builder().writeType(2).packet(getCommandId(), new byte[]{this.a}).eventId(getEventId()).build();
    }
}
