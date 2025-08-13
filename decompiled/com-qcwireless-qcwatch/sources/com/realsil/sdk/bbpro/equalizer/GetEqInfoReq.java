package com.realsil.sdk.bbpro.equalizer;

import com.realsil.sdk.bbpro.core.transportlayer.Command;
import com.realsil.sdk.bbpro.profile.AppReq;
import java.util.Locale;

/* loaded from: classes3.dex */
public final class GetEqInfoReq extends AppReq {
    public static final byte QUERY_TYPE_EQ_ENTRY_NUMBER = 1;
    public static final byte QUERY_TYPE_EQ_STATE = 0;
    public byte a;

    public static class Builder {
        public byte a;

        public Builder(byte b) {
            this.a = b;
        }

        public GetEqInfoReq build() {
            return new GetEqInfoReq(this.a);
        }
    }

    @Override // com.realsil.sdk.bbpro.profile.AppReq
    public Command encode(int i) {
        return encode();
    }

    @Override // com.realsil.sdk.bbpro.profile.AppReq
    public short getCommandId() {
        return (short) 512;
    }

    @Override // com.realsil.sdk.bbpro.profile.AppReq
    public short getEventId() {
        return (short) 512;
    }

    public byte getQueryType() {
        return this.a;
    }

    public String toString() {
        return "GetEqInfoReq {" + String.format(Locale.US, "\nqueryType=%d", Byte.valueOf(this.a)) + "\n}";
    }

    public GetEqInfoReq(byte b) {
        this.a = b;
    }

    @Override // com.realsil.sdk.bbpro.profile.AppReq
    public Command encode() {
        return new Command.Builder().writeType(2).packet(getCommandId(), new byte[]{this.a}).eventId(getEventId()).build();
    }
}
