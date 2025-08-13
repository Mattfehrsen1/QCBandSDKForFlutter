package com.realsil.customer.bbpro.apt;

import com.realsil.customer.bbpro.core.transportlayer.Command;
import com.realsil.customer.bbpro.profile.AppReq;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/bbpro/apt/GetAptPowerOnDelayTimeReq.class */
public final class GetAptPowerOnDelayTimeReq extends AppReq {

    /* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/bbpro/apt/GetAptPowerOnDelayTimeReq$Builder.class */
    public static class Builder {
        public GetAptPowerOnDelayTimeReq build() {
            return new GetAptPowerOnDelayTimeReq();
        }
    }

    @Override // com.realsil.customer.bbpro.profile.AppReq
    public short getCommandId() {
        return (short) 3129;
    }

    @Override // com.realsil.customer.bbpro.profile.AppReq
    public short getEventId() {
        return (short) 3129;
    }

    @Override // com.realsil.customer.bbpro.profile.AppReq
    public Command encode(int i) {
        return encode();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("GetAptPowerOnDelayTimeReq(0x%04X) {", Short.valueOf(getCommandId())));
        sb.append("\n}");
        return sb.toString();
    }

    public GetAptPowerOnDelayTimeReq() {
    }

    @Override // com.realsil.customer.bbpro.profile.AppReq
    public Command encode() {
        return new Command.Builder().writeType(2).packet(getCommandId(), null).eventId(getEventId()).build();
    }
}
