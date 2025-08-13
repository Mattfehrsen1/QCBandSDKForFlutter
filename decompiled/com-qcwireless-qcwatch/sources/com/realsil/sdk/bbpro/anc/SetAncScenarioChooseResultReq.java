package com.realsil.sdk.bbpro.anc;

import com.realsil.sdk.bbpro.core.transportlayer.Command;
import com.realsil.sdk.bbpro.profile.AppReq;
import java.util.Locale;

/* loaded from: classes3.dex */
public final class SetAncScenarioChooseResultReq extends AppReq {
    public int a;
    public int b;

    public static class Builder {
        public int a;
        public int b;

        public SetAncScenarioChooseResultReq build() {
            return new SetAncScenarioChooseResultReq(this.a, this.b);
        }

        public Builder indicator(int i, int i2) {
            this.a = i;
            this.b = i2;
            return this;
        }
    }

    @Override // com.realsil.sdk.bbpro.profile.AppReq
    public Command encode(int i) {
        return encode();
    }

    @Override // com.realsil.sdk.bbpro.profile.AppReq
    public short getCommandId() {
        return (short) 3142;
    }

    @Override // com.realsil.sdk.bbpro.profile.AppReq
    public short getEventId() {
        return (short) 3142;
    }

    public String toString() {
        return String.format("SetAncScenarioChooseResultReq(0x%04X) {", Short.valueOf(getCommandId())) + String.format(Locale.US, "\n\t L(%d),R(%d)", Integer.valueOf(this.a), Integer.valueOf(this.b)) + "\n}";
    }

    public SetAncScenarioChooseResultReq(int i, int i2) {
        this.a = i;
        this.b = i2;
    }

    @Override // com.realsil.sdk.bbpro.profile.AppReq
    public Command encode() {
        int i = this.a;
        int i2 = this.b;
        return new Command.Builder().writeType(2).packet(getCommandId(), new byte[]{(byte) (i & 255), (byte) ((i >> 8) & 255), (byte) ((i >> 16) & 255), (byte) ((i >> 24) & 255), (byte) (i2 & 255), (byte) ((i2 >> 8) & 255), (byte) ((i2 >> 16) & 255), (byte) ((i2 >> 24) & 255)}).eventId(getEventId()).build();
    }
}
