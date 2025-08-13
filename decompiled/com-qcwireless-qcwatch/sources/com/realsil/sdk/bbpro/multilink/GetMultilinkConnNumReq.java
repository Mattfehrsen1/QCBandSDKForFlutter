package com.realsil.sdk.bbpro.multilink;

import com.realsil.sdk.bbpro.core.transportlayer.Command;
import com.realsil.sdk.bbpro.profile.AppReq;

/* loaded from: classes3.dex */
public final class GetMultilinkConnNumReq extends AppReq {

    public static class Builder {
        public GetMultilinkConnNumReq build() {
            return new GetMultilinkConnNumReq();
        }
    }

    @Override // com.realsil.sdk.bbpro.profile.AppReq
    public Command encode(int i) {
        return encode();
    }

    @Override // com.realsil.sdk.bbpro.profile.AppReq
    public short getCommandId() {
        return (short) 789;
    }

    @Override // com.realsil.sdk.bbpro.profile.AppReq
    public short getEventId() {
        return (short) 789;
    }

    public String toString() {
        return String.format("GetMultilinkConnNumReq(0x%04X) {", Short.valueOf(getCommandId())) + "\n}";
    }

    public GetMultilinkConnNumReq() {
    }

    @Override // com.realsil.sdk.bbpro.profile.AppReq
    public Command encode() {
        return new Command.Builder().writeType(2).packet(getCommandId(), null).eventId(getEventId()).build();
    }
}
