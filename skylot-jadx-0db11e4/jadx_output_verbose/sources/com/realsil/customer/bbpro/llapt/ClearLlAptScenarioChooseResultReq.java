package com.realsil.customer.bbpro.llapt;

import com.realsil.customer.bbpro.core.transportlayer.Command;
import com.realsil.customer.bbpro.profile.AppReq;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/bbpro/llapt/ClearLlAptScenarioChooseResultReq.class */
public final class ClearLlAptScenarioChooseResultReq extends AppReq {

    /* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/bbpro/llapt/ClearLlAptScenarioChooseResultReq$Builder.class */
    public static class Builder {
        public ClearLlAptScenarioChooseResultReq build() {
            return new ClearLlAptScenarioChooseResultReq();
        }
    }

    @Override // com.realsil.customer.bbpro.profile.AppReq
    public short getCommandId() {
        return (short) 3128;
    }

    @Override // com.realsil.customer.bbpro.profile.AppReq
    public short getEventId() {
        return (short) 3128;
    }

    @Override // com.realsil.customer.bbpro.profile.AppReq
    public Command encode(int i) {
        return encode();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("ClearLlAptScenarioChooseResultReq(0x%04X) {", Short.valueOf(getCommandId())));
        sb.append("\n}");
        return sb.toString();
    }

    public ClearLlAptScenarioChooseResultReq() {
    }

    @Override // com.realsil.customer.bbpro.profile.AppReq
    public Command encode() {
        return new Command.Builder().writeType(2).packet(getCommandId(), null).eventId(getEventId()).build();
    }
}
