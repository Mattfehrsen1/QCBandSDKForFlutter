package com.realsil.customer.bbpro.keymapping;

import com.realsil.customer.bbpro.core.transportlayer.Command;
import com.realsil.customer.bbpro.profile.AppReq;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/bbpro/keymapping/ResetRwsKeyMmiMapReq.class */
public final class ResetRwsKeyMmiMapReq extends AppReq {
    public static final byte BUD_TYPE_STEREO = 0;
    public static final byte BUD_TYPE_LCH = 1;
    public static final byte BUD_TYPE_RCH = 2;
    public static final byte BUD_TYPE_ALL = 3;
    public static final byte BUD_TYPE_RESERVE = -1;
    public byte a;

    @Override // com.realsil.customer.bbpro.profile.AppReq
    public short getCommandId() {
        return (short) 1802;
    }

    @Override // com.realsil.customer.bbpro.profile.AppReq
    public short getEventId() {
        return (short) 1800;
    }

    @Override // com.realsil.customer.bbpro.profile.AppReq
    public Command encode(int i) {
        return encode();
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

    /* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/bbpro/keymapping/ResetRwsKeyMmiMapReq$Builder.class */
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

    @Override // com.realsil.customer.bbpro.profile.AppReq
    public Command encode() {
        byte[] bArr = null;
        byte b = this.a;
        if (b != -1) {
            bArr = new byte[]{b};
        }
        return new Command.Builder().writeType(2).packet(getCommandId(), bArr).eventId(getEventId()).build();
    }
}
