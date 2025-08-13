package com.realsil.sdk.bbpro.g;

import com.realsil.sdk.bbpro.core.transportlayer.Command;
import com.realsil.sdk.bbpro.profile.AppReq;
import java.util.Locale;

/* loaded from: classes3.dex */
public final class a extends AppReq {
    public byte a;

    public static class b {
        public byte a;

        public b(byte b) {
            this.a = b;
        }

        public a a() {
            return new a(this.a);
        }
    }

    @Override // com.realsil.sdk.bbpro.profile.AppReq
    public Command encode(int i) {
        return encode();
    }

    @Override // com.realsil.sdk.bbpro.profile.AppReq
    public short getCommandId() {
        return (short) 3106;
    }

    @Override // com.realsil.sdk.bbpro.profile.AppReq
    public short getEventId() {
        return (short) 3106;
    }

    public String toString() {
        return String.format("GetLlAptInfoReq(0x%04X) {", Short.valueOf(getCommandId())) + String.format(Locale.US, "\n\tType=0x%02x", Byte.valueOf(this.a)) + "\n}";
    }

    public a(byte b2) {
        this.a = b2;
    }

    @Override // com.realsil.sdk.bbpro.profile.AppReq
    public Command encode() {
        return new Command.Builder().writeType(2).packet(getCommandId(), new byte[]{this.a}).eventId(getEventId()).build();
    }
}
