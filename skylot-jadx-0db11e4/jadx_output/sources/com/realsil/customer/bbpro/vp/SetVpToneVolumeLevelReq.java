package com.realsil.customer.bbpro.vp;

import com.realsil.customer.bbpro.core.transportlayer.Command;
import com.realsil.customer.bbpro.profile.AppReq;
import java.util.Locale;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/bbpro/vp/SetVpToneVolumeLevelReq.class */
public final class SetVpToneVolumeLevelReq extends AppReq {
    public int a;
    public int b;
    public boolean rwsSyncSupported;
    public boolean rwsSyncEnabled;

    @Override // com.realsil.customer.bbpro.profile.AppReq
    public short getCommandId() {
        return (short) 529;
    }

    @Override // com.realsil.customer.bbpro.profile.AppReq
    public short getEventId() {
        return (short) 522;
    }

    @Override // com.realsil.customer.bbpro.profile.AppReq
    public Command encode(int i) {
        return encode();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("SetVpToneVolumeLevelReq(0x%04X) {", Short.valueOf(getCommandId())));
        if (this.rwsSyncSupported) {
            sb.append(String.format("\n\trwsSyncEnabled=%b", Boolean.valueOf(this.rwsSyncEnabled)));
        } else {
            sb.append(String.format("\n\trwsSyncSupported=%b", Boolean.FALSE));
        }
        sb.append(String.format(Locale.US, "\n\tL=%d,R=%d", Integer.valueOf(this.a), Integer.valueOf(this.b)));
        sb.append("\n}");
        return sb.toString();
    }

    /* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/bbpro/vp/SetVpToneVolumeLevelReq$Builder.class */
    public static class Builder {
        public int a;
        public int b;
        public boolean c = false;
        public boolean d = false;

        public Builder leftChannelVolumeLevel(int i) {
            this.a = i;
            return this;
        }

        public Builder rightChannelVolumeLevel(int i) {
            this.b = i;
            return this;
        }

        public Builder volumeLevel(int i) {
            this.a = i;
            this.b = i;
            return this;
        }

        public Builder rwsSyncEnabled(boolean z) {
            this.c = true;
            this.d = z;
            return this;
        }

        public SetVpToneVolumeLevelReq build() {
            return new SetVpToneVolumeLevelReq(this.a, this.b, this.c, this.d);
        }

        public Builder volumeLevel(int i, int i2) {
            this.a = i;
            this.b = i2;
            return this;
        }
    }

    public SetVpToneVolumeLevelReq(int i, int i2, boolean z, boolean z2) {
        this.a = i;
        this.b = i2;
        this.rwsSyncSupported = z;
        this.rwsSyncEnabled = z2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.realsil.customer.bbpro.profile.AppReq
    public Command encode() {
        byte[] bArr;
        if (this.rwsSyncSupported) {
            bArr = new byte[]{0, 0, this.rwsSyncEnabled};
            bArr[0] = (byte) (this.a & 255);
            bArr[1] = (byte) (this.b & 255);
        } else {
            bArr = new byte[]{0, (byte) (this.b & 255)};
            bArr[0] = (byte) (this.a & 255);
        }
        return new Command.Builder().writeType(2).packet(getCommandId(), bArr).eventId(getEventId()).build();
    }
}
