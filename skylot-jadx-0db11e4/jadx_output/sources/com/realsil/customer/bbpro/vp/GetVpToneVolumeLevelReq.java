package com.realsil.customer.bbpro.vp;

import com.realsil.customer.bbpro.core.transportlayer.Command;
import com.realsil.customer.bbpro.profile.AppReq;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/bbpro/vp/GetVpToneVolumeLevelReq.class */
public final class GetVpToneVolumeLevelReq extends AppReq {

    /* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/bbpro/vp/GetVpToneVolumeLevelReq$Builder.class */
    public static class Builder {
        public GetVpToneVolumeLevelReq build() {
            return new GetVpToneVolumeLevelReq();
        }
    }

    @Override // com.realsil.customer.bbpro.profile.AppReq
    public short getCommandId() {
        return (short) 530;
    }

    @Override // com.realsil.customer.bbpro.profile.AppReq
    public short getEventId() {
        return (short) 521;
    }

    @Override // com.realsil.customer.bbpro.profile.AppReq
    public Command encode(int i) {
        return encode();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("GetVpToneVolumeLevelReq(0x%04X) {", Short.valueOf(getCommandId())));
        sb.append("\n}");
        return sb.toString();
    }

    public GetVpToneVolumeLevelReq() {
    }

    @Override // com.realsil.customer.bbpro.profile.AppReq
    public Command encode() {
        return new Command.Builder().writeType(2).packet(getCommandId(), null).eventId(getEventId()).build();
    }
}
