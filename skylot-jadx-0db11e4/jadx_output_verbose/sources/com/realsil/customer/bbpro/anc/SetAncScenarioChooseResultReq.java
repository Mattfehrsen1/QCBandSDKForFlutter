package com.realsil.customer.bbpro.anc;

import com.realsil.customer.bbpro.core.transportlayer.Command;
import com.realsil.customer.bbpro.profile.AppReq;
import java.util.Locale;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/bbpro/anc/SetAncScenarioChooseResultReq.class */
public final class SetAncScenarioChooseResultReq extends AppReq {
    public int a;
    public int b;

    /* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/bbpro/anc/SetAncScenarioChooseResultReq$Builder.class */
    public static class Builder {
        public int a;
        public int b;

        public Builder indicator(int i, int i2) {
            this.a = i;
            this.b = i2;
            return this;
        }

        public SetAncScenarioChooseResultReq build() {
            return new SetAncScenarioChooseResultReq(this.a, this.b);
        }
    }

    @Override // com.realsil.customer.bbpro.profile.AppReq
    public short getCommandId() {
        return (short) 3142;
    }

    @Override // com.realsil.customer.bbpro.profile.AppReq
    public short getEventId() {
        return (short) 3142;
    }

    @Override // com.realsil.customer.bbpro.profile.AppReq
    public Command encode(int i) {
        return encode();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("SetAncScenarioChooseResultReq(0x%04X) {", Short.valueOf(getCommandId())));
        sb.append(String.format(Locale.US, "\n\t L(%d),R(%d)", Integer.valueOf(this.a), Integer.valueOf(this.b)));
        sb.append("\n}");
        return sb.toString();
    }

    public SetAncScenarioChooseResultReq(int i, int i2) {
        this.a = i;
        this.b = i2;
    }

    @Override // com.realsil.customer.bbpro.profile.AppReq
    public Command encode() {
        int i = this.a;
        int i2 = this.b;
        return new Command.Builder().writeType(2).packet(getCommandId(), new byte[]{(byte) (i & 255), (byte) ((i >> 8) & 255), (byte) ((i >> 16) & 255), (byte) ((i >> 24) & 255), (byte) (i2 & 255), (byte) ((i2 >> 8) & 255), (byte) ((i2 >> 16) & 255), (byte) ((i2 >> 24) & 255)}).eventId(getEventId()).build();
    }
}
