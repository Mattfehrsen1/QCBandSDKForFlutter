package com.realsil.sdk.bbpro.apt;

import com.realsil.sdk.bbpro.core.transportlayer.Command;
import com.realsil.sdk.bbpro.profile.AppReq;

/* loaded from: classes3.dex */
public final class GetAptNrOnOffReq extends AppReq {

    public static class Builder {
        public GetAptNrOnOffReq build() {
            return new GetAptNrOnOffReq();
        }
    }

    @Override // com.realsil.sdk.bbpro.profile.AppReq
    public Command encode(int i) {
        return encode();
    }

    @Override // com.realsil.sdk.bbpro.profile.AppReq
    public short getCommandId() {
        return (short) 3125;
    }

    @Override // com.realsil.sdk.bbpro.profile.AppReq
    public short getEventId() {
        return (short) 3124;
    }

    public String toString() {
        return String.format("GetAptNrOnOffReq(0x%04X) {", Short.valueOf(getCommandId())) + "\n}";
    }

    public GetAptNrOnOffReq() {
    }

    @Override // com.realsil.sdk.bbpro.profile.AppReq
    public Command encode() {
        return new Command.Builder().writeType(2).packet(getCommandId(), null).eventId(getEventId()).build();
    }
}
