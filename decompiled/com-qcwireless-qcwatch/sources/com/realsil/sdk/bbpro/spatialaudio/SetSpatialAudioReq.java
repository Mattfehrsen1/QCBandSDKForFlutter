package com.realsil.sdk.bbpro.spatialaudio;

import com.realsil.sdk.bbpro.core.transportlayer.Command;
import com.realsil.sdk.bbpro.profile.AppReq;

/* loaded from: classes3.dex */
public final class SetSpatialAudioReq extends AppReq {
    public static final byte SPATIAL_AUDIO_CLOSE = 0;
    public static final byte SPATIAL_AUDIO_NO_CHANGE = 2;
    public static final byte SPATIAL_AUDIO_OPEN = 1;
    public byte a;

    public static class Builder {
        public byte a;

        public SetSpatialAudioReq build() {
            return new SetSpatialAudioReq(this.a);
        }

        public Builder mode(byte b) {
            this.a = b;
            return this;
        }
    }

    @Override // com.realsil.sdk.bbpro.profile.AppReq
    public Command encode(int i) {
        return encode();
    }

    @Override // com.realsil.sdk.bbpro.profile.AppReq
    public short getCommandId() {
        return (short) 534;
    }

    public String toString() {
        return String.format("SetSpatialAudioReq(0x%04X) {", Short.valueOf(getCommandId())) + String.format("\n\tmode=0x%02X", Byte.valueOf(this.a)) + "\n}";
    }

    public SetSpatialAudioReq(byte b) {
        this.a = b;
    }

    @Override // com.realsil.sdk.bbpro.profile.AppReq
    public Command encode() {
        return new Command.Builder().writeType(2).packet(getCommandId(), new byte[]{this.a}).eventId(getEventId()).build();
    }
}
