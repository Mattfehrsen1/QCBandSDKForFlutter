package com.realsil.customer.bbpro.equalizer;

import com.realsil.customer.bbpro.core.transportlayer.Command;
import com.realsil.customer.bbpro.profile.AppReq;
import java.util.Locale;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/bbpro/equalizer/GetEqInfoReq.class */
public final class GetEqInfoReq extends AppReq {
    public static final byte QUERY_TYPE_EQ_STATE = 0;
    public static final byte QUERY_TYPE_EQ_ENTRY_NUMBER = 1;
    public byte a;

    /* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/bbpro/equalizer/GetEqInfoReq$Builder.class */
    public static class Builder {
        public byte a;

        public Builder(byte b) {
            this.a = b;
        }

        public GetEqInfoReq build() {
            return new GetEqInfoReq(this.a);
        }
    }

    public byte getQueryType() {
        return this.a;
    }

    @Override // com.realsil.customer.bbpro.profile.AppReq
    public short getCommandId() {
        return (short) 512;
    }

    @Override // com.realsil.customer.bbpro.profile.AppReq
    public short getEventId() {
        return (short) 512;
    }

    @Override // com.realsil.customer.bbpro.profile.AppReq
    public Command encode(int i) {
        return encode();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("GetEqInfoReq {");
        sb.append(String.format(Locale.US, "\nqueryType=%d", Byte.valueOf(this.a)));
        sb.append("\n}");
        return sb.toString();
    }

    public GetEqInfoReq(byte b) {
        this.a = b;
    }

    @Override // com.realsil.customer.bbpro.profile.AppReq
    public Command encode() {
        return new Command.Builder().writeType(2).packet(getCommandId(), new byte[]{this.a}).eventId(getEventId()).build();
    }
}
