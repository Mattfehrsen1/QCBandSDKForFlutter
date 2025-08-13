package com.realsil.customer.bbpro.apt;

import com.realsil.customer.bbpro.core.transportlayer.Command;
import com.realsil.customer.bbpro.profile.AppReq;
import com.realsil.customer.bbpro.profile.GetStatusReq;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/bbpro/apt/GetAptVolumeInfoReq.class */
public final class GetAptVolumeInfoReq extends AppReq {
    public int a;

    @Override // com.realsil.customer.bbpro.profile.AppReq
    public short getCommandId() {
        return (short) 3118;
    }

    @Override // com.realsil.customer.bbpro.profile.AppReq
    public short getEventId() {
        return (short) 3118;
    }

    @Override // com.realsil.customer.bbpro.profile.AppReq
    public Command encode(int i) {
        return encode();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("GetAptVolumeInfoReq(volumeSpecVersion=0x%04X) {", Integer.valueOf(this.a)));
        sb.append("\n}");
        return sb.toString();
    }

    /* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/bbpro/apt/GetAptVolumeInfoReq$Builder.class */
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

    @Override // com.realsil.customer.bbpro.profile.AppReq
    public Command encode() {
        int i = this.a;
        return i == 1 ? new GetStatusReq.Builder((byte) 7).build().encode() : i == 2 ? new Command.Builder().writeType(2).packet((short) 527, null).eventId((short) 517).build() : i == 3 ? new Command.Builder().writeType(2).packet((short) 3118, null).eventId((short) 3118).build() : new Command.Builder().writeType(2).packet((short) 3118, null).eventId((short) 3118).build();
    }
}
