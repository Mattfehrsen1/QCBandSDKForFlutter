package com.realsil.sdk.bbpro.apt;

import com.realsil.sdk.bbpro.core.transportlayer.Command;
import com.realsil.sdk.bbpro.profile.AppReq;
import com.realsil.sdk.bbpro.profile.GetStatusReq;

/* loaded from: classes3.dex */
public final class GetAptVolumeInfoReq extends AppReq {
    public int a;

    @Override // com.realsil.sdk.bbpro.profile.AppReq
    public Command encode(int i) {
        return encode();
    }

    @Override // com.realsil.sdk.bbpro.profile.AppReq
    public short getCommandId() {
        return (short) 3118;
    }

    @Override // com.realsil.sdk.bbpro.profile.AppReq
    public short getEventId() {
        return (short) 3118;
    }

    public String toString() {
        return String.format("GetAptVolumeInfoReq(volumeSpecVersion=0x%04X) {", Integer.valueOf(this.a)) + "\n}";
    }

    public static class Builder {
        public int a;

        public Builder() {
            this.a = 3;
        }

        public GetAptVolumeInfoReq build() {
            return new GetAptVolumeInfoReq(this.a);
        }

        public Builder(int i) {
            this.a = i;
        }
    }

    public GetAptVolumeInfoReq(int i) {
        this.a = i;
    }

    @Override // com.realsil.sdk.bbpro.profile.AppReq
    public Command encode() {
        int i = this.a;
        return i == 1 ? new GetStatusReq.Builder((byte) 7).build().encode() : i == 2 ? new Command.Builder().writeType(2).packet((short) 527, null).eventId((short) 517).build() : i == 3 ? new Command.Builder().writeType(2).packet((short) 3118, null).eventId((short) 3118).build() : new Command.Builder().writeType(2).packet((short) 3118, null).eventId((short) 3118).build();
    }
}
