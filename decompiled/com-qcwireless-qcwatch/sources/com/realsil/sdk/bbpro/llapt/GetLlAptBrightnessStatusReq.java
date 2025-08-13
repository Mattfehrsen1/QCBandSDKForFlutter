package com.realsil.sdk.bbpro.llapt;

import com.realsil.sdk.bbpro.core.transportlayer.Command;
import com.realsil.sdk.bbpro.profile.AppReq;

/* loaded from: classes3.dex */
public final class GetLlAptBrightnessStatusReq extends AppReq {

    public static class Builder {
        public GetLlAptBrightnessStatusReq build() {
            return new GetLlAptBrightnessStatusReq();
        }
    }

    @Override // com.realsil.sdk.bbpro.profile.AppReq
    public Command encode(int i) {
        return encode();
    }

    @Override // com.realsil.sdk.bbpro.profile.AppReq
    public short getCommandId() {
        return (short) 3123;
    }

    @Override // com.realsil.sdk.bbpro.profile.AppReq
    public short getEventId() {
        return (short) 3123;
    }

    public String toString() {
        return String.format("GetLlAptBrightnessStatusReq(0x%04X) {", Short.valueOf(getCommandId())) + "\n}";
    }

    public GetLlAptBrightnessStatusReq() {
    }

    @Override // com.realsil.sdk.bbpro.profile.AppReq
    public Command encode() {
        return new Command.Builder().writeType(2).packet(getCommandId(), null).eventId(getEventId()).build();
    }
}
