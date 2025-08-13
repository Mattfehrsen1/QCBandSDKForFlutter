package com.realsil.sdk.bbpro.keymapping;

import com.realsil.sdk.bbpro.core.transportlayer.Command;
import com.realsil.sdk.bbpro.profile.AppReq;

/* loaded from: classes3.dex */
public final class ResetRwsKeyMmiMapReq extends AppReq {
    public static final byte BUD_TYPE_ALL = 3;
    public static final byte BUD_TYPE_LCH = 1;
    public static final byte BUD_TYPE_RCH = 2;
    public static final byte BUD_TYPE_RESERVE = -1;
    public static final byte BUD_TYPE_STEREO = 0;
    public byte a;

    @Override // com.realsil.sdk.bbpro.profile.AppReq
    public Command encode(int i) {
        return encode();
    }

    @Override // com.realsil.sdk.bbpro.profile.AppReq
    public short getCommandId() {
        return (short) 1802;
    }

    @Override // com.realsil.sdk.bbpro.profile.AppReq
    public short getEventId() {
        return (short) 1800;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("ResetRwsKeyMmiMapReq(0x%04X) {", Short.valueOf(getCommandId())));
        byte b = this.a;
        if (b != -1) {
            sb.append(String.format("\n\tbudType=0x%02X", Byte.valueOf(b)));
        }
        sb.append("\n}");
        return sb.toString();
    }

    public static class Builder {
        public byte a;

        public Builder() {
            this.a = (byte) -1;
        }

        public ResetRwsKeyMmiMapReq build() {
            return new ResetRwsKeyMmiMapReq(this.a);
        }

        public Builder(byte b) {
            this.a = b;
        }
    }

    public ResetRwsKeyMmiMapReq(byte b) {
        this.a = b;
    }

    @Override // com.realsil.sdk.bbpro.profile.AppReq
    public Command encode() {
        byte b = this.a;
        return new Command.Builder().writeType(2).packet(getCommandId(), b != -1 ? new byte[]{b} : null).eventId(getEventId()).build();
    }
}
