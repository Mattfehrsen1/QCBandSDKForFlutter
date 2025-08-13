package com.realsil.customer.bbpro.spatialaudio;

import com.realsil.customer.bbpro.core.transportlayer.Command;
import com.realsil.customer.bbpro.profile.AppReq;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/bbpro/spatialaudio/SetSpatialAudioReq.class */
public final class SetSpatialAudioReq extends AppReq {
    public static final byte SPATIAL_AUDIO_CLOSE = 0;
    public static final byte SPATIAL_AUDIO_OPEN = 1;
    public static final byte SPATIAL_AUDIO_NO_CHANGE = 2;
    public byte a;

    /* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/bbpro/spatialaudio/SetSpatialAudioReq$Builder.class */
    public static class Builder {
        public byte a;

        public Builder mode(byte b) {
            this.a = b;
            return this;
        }

        public SetSpatialAudioReq build() {
            return new SetSpatialAudioReq(this.a);
        }
    }

    @Override // com.realsil.customer.bbpro.profile.AppReq
    public short getCommandId() {
        return (short) 534;
    }

    @Override // com.realsil.customer.bbpro.profile.AppReq
    public Command encode(int i) {
        return encode();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("SetSpatialAudioReq(0x%04X) {", Short.valueOf(getCommandId())));
        sb.append(String.format("\n\tmode=0x%02X", Byte.valueOf(this.a)));
        sb.append("\n}");
        return sb.toString();
    }

    public SetSpatialAudioReq(byte b) {
        this.a = b;
    }

    @Override // com.realsil.customer.bbpro.profile.AppReq
    public Command encode() {
        return new Command.Builder().writeType(2).packet(getCommandId(), new byte[]{this.a}).eventId(getEventId()).build();
    }
}
