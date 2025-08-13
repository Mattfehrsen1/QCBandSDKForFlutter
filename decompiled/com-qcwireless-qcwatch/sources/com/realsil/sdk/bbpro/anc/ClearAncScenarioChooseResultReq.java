package com.realsil.sdk.bbpro.anc;

import com.realsil.sdk.bbpro.core.transportlayer.Command;
import com.realsil.sdk.bbpro.profile.AppReq;

/* loaded from: classes3.dex */
public final class ClearAncScenarioChooseResultReq extends AppReq {

    public static class Builder {
        public ClearAncScenarioChooseResultReq build() {
            return new ClearAncScenarioChooseResultReq();
        }
    }

    @Override // com.realsil.sdk.bbpro.profile.AppReq
    public Command encode(int i) {
        return encode();
    }

    @Override // com.realsil.sdk.bbpro.profile.AppReq
    public short getCommandId() {
        return (short) 3142;
    }

    @Override // com.realsil.sdk.bbpro.profile.AppReq
    public short getEventId() {
        return (short) 3142;
    }

    public String toString() {
        return String.format("ClearAncScenarioChooseResultReq(0x%04X) {", Short.valueOf(getCommandId())) + "\n}";
    }

    public ClearAncScenarioChooseResultReq() {
    }

    @Override // com.realsil.sdk.bbpro.profile.AppReq
    public Command encode() {
        return new Command.Builder().writeType(2).packet(getCommandId(), null).eventId(getEventId()).build();
    }
}
