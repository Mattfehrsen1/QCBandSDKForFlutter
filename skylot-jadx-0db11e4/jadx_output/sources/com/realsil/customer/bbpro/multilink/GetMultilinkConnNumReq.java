package com.realsil.customer.bbpro.multilink;

import com.realsil.customer.bbpro.core.transportlayer.Command;
import com.realsil.customer.bbpro.profile.AppReq;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/bbpro/multilink/GetMultilinkConnNumReq.class */
public final class GetMultilinkConnNumReq extends AppReq {

    /* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/bbpro/multilink/GetMultilinkConnNumReq$Builder.class */
    public static class Builder {
        public GetMultilinkConnNumReq build() {
            return new GetMultilinkConnNumReq();
        }
    }

    @Override // com.realsil.customer.bbpro.profile.AppReq
    public short getCommandId() {
        return (short) 789;
    }

    @Override // com.realsil.customer.bbpro.profile.AppReq
    public short getEventId() {
        return (short) 789;
    }

    @Override // com.realsil.customer.bbpro.profile.AppReq
    public Command encode(int i) {
        return encode();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("GetMultilinkConnNumReq(0x%04X) {", Short.valueOf(getCommandId())));
        sb.append("\n}");
        return sb.toString();
    }

    public GetMultilinkConnNumReq() {
    }

    @Override // com.realsil.customer.bbpro.profile.AppReq
    public Command encode() {
        return new Command.Builder().writeType(2).packet(getCommandId(), null).eventId(getEventId()).build();
    }
}
