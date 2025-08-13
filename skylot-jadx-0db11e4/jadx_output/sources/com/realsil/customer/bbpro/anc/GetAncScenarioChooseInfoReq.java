package com.realsil.customer.bbpro.anc;

import com.realsil.customer.bbpro.core.transportlayer.Command;
import com.realsil.customer.bbpro.profile.AppReq;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/bbpro/anc/GetAncScenarioChooseInfoReq.class */
public final class GetAncScenarioChooseInfoReq extends AppReq {

    /* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/bbpro/anc/GetAncScenarioChooseInfoReq$Builder.class */
    public static class Builder {
        public GetAncScenarioChooseInfoReq build() {
            return new GetAncScenarioChooseInfoReq();
        }
    }

    @Override // com.realsil.customer.bbpro.profile.AppReq
    public short getCommandId() {
        return (short) 3140;
    }

    @Override // com.realsil.customer.bbpro.profile.AppReq
    public short getEventId() {
        return (short) 3140;
    }

    @Override // com.realsil.customer.bbpro.profile.AppReq
    public Command encode(int i) {
        return encode();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("GetAncScenarioChooseInfoReq(0x%04X) {", Short.valueOf(getCommandId())));
        sb.append("\n}");
        return sb.toString();
    }

    public GetAncScenarioChooseInfoReq() {
    }

    @Override // com.realsil.customer.bbpro.profile.AppReq
    public Command encode() {
        return new Command.Builder().writeType(2).packet(getCommandId(), null).eventId(getEventId()).build();
    }
}
