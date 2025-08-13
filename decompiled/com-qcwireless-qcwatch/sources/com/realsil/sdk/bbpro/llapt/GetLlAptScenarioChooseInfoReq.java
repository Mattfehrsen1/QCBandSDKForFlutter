package com.realsil.sdk.bbpro.llapt;

import com.realsil.sdk.bbpro.core.transportlayer.Command;
import com.realsil.sdk.bbpro.profile.AppReq;

/* loaded from: classes3.dex */
public final class GetLlAptScenarioChooseInfoReq extends AppReq {

    public static class Builder {
        public GetLlAptScenarioChooseInfoReq build() {
            return new GetLlAptScenarioChooseInfoReq();
        }
    }

    @Override // com.realsil.sdk.bbpro.profile.AppReq
    public Command encode(int i) {
        return encode();
    }

    @Override // com.realsil.sdk.bbpro.profile.AppReq
    public short getCommandId() {
        return (short) 3126;
    }

    @Override // com.realsil.sdk.bbpro.profile.AppReq
    public short getEventId() {
        return (short) 3126;
    }

    public String toString() {
        return String.format("GetLlAptBrightnessInfoReq(0x%04X) {", Short.valueOf(getCommandId())) + "\n}";
    }

    public GetLlAptScenarioChooseInfoReq() {
    }

    @Override // com.realsil.sdk.bbpro.profile.AppReq
    public Command encode() {
        return new Command.Builder().writeType(2).packet(getCommandId(), null).eventId(getEventId()).build();
    }
}
