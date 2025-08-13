package com.realsil.sdk.bbpro.equalizer;

import com.realsil.sdk.bbpro.core.transportlayer.Command;
import com.realsil.sdk.bbpro.profile.AppReq;

/* loaded from: classes3.dex */
public final class b extends AppReq {

    /* renamed from: com.realsil.sdk.bbpro.equalizer.b$b, reason: collision with other inner class name */
    public static class C0231b {
        public b a() {
            return new b();
        }
    }

    @Override // com.realsil.sdk.bbpro.profile.AppReq
    public Command encode(int i) {
        return encode();
    }

    @Override // com.realsil.sdk.bbpro.profile.AppReq
    public short getCommandId() {
        return (short) 513;
    }

    public String toString() {
        return "EnableEqReq {\n}";
    }

    public b() {
    }

    @Override // com.realsil.sdk.bbpro.profile.AppReq
    public Command encode() {
        return new Command.Builder().writeType(2).packet(getCommandId(), null).eventId(getEventId()).build();
    }
}
