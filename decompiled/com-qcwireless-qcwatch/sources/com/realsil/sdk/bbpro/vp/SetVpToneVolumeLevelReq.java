package com.realsil.sdk.bbpro.vp;

import com.realsil.sdk.bbpro.core.transportlayer.Command;
import com.realsil.sdk.bbpro.profile.AppReq;
import java.util.Locale;

/* loaded from: classes3.dex */
public final class SetVpToneVolumeLevelReq extends AppReq {
    public int a;
    public int b;
    public boolean rwsSyncEnabled;
    public boolean rwsSyncSupported;

    @Override // com.realsil.sdk.bbpro.profile.AppReq
    public Command encode(int i) {
        return encode();
    }

    @Override // com.realsil.sdk.bbpro.profile.AppReq
    public short getCommandId() {
        return (short) 529;
    }

    @Override // com.realsil.sdk.bbpro.profile.AppReq
    public short getEventId() {
        return (short) 522;
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

    public static class Builder {
        public int a;
        public int b;
        public boolean c = false;
        public boolean d = false;

        public SetVpToneVolumeLevelReq build() {
            return new SetVpToneVolumeLevelReq(this.a, this.b, this.c, this.d);
        }

        public Builder leftChannelVolumeLevel(int i) {
            this.a = i;
            return this;
        }

        public Builder rightChannelVolumeLevel(int i) {
            this.b = i;
            return this;
        }

        public Builder rwsSyncEnabled(boolean z) {
            this.c = true;
            this.d = z;
            return this;
        }

        public Builder volumeLevel(int i) {
            this.a = i;
            this.b = i;
            return this;
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

    @Override // com.realsil.sdk.bbpro.profile.AppReq
    public Command encode() {
        return new Command.Builder().writeType(2).packet(getCommandId(), !this.rwsSyncSupported ? new byte[]{(byte) (this.a & 255), (byte) (this.b & 255)} : new byte[]{(byte) (this.a & 255), (byte) (this.b & 255), this.rwsSyncEnabled}).eventId(getEventId()).build();
    }
}
