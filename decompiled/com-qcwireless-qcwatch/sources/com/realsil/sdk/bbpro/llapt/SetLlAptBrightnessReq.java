package com.realsil.sdk.bbpro.llapt;

import com.realsil.sdk.bbpro.core.transportlayer.Command;
import com.realsil.sdk.bbpro.profile.AppReq;
import java.util.Locale;

/* loaded from: classes3.dex */
public final class SetLlAptBrightnessReq extends AppReq {
    public static final byte PARAMETER_TYPE_MAIN = 0;
    public static final byte PARAMETER_TYPE_SUB = 1;
    public byte a;
    public int b;
    public int c;
    public boolean rwsSyncEnabled;
    public boolean rwsSyncSupported;

    public static class Builder {
        public byte a;
        public int b;
        public int c;
        public boolean d = false;
        public boolean e = false;

        public Builder(byte b) {
            this.a = b;
        }

        public SetLlAptBrightnessReq build() {
            return new SetLlAptBrightnessReq(this.a, this.b, this.c, this.d, this.e);
        }

        public Builder level(int i, int i2) {
            this.b = i;
            this.c = i2;
            return this;
        }

        public Builder rwsSyncEnabled(boolean z) {
            this.d = true;
            this.e = z;
            return this;
        }
    }

    @Override // com.realsil.sdk.bbpro.profile.AppReq
    public Command encode(int i) {
        return encode();
    }

    @Override // com.realsil.sdk.bbpro.profile.AppReq
    public short getCommandId() {
        return (short) 3122;
    }

    @Override // com.realsil.sdk.bbpro.profile.AppReq
    public short getEventId() {
        return (short) 3122;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("SetLlAptBrightnessReq(0x%04X) {", Short.valueOf(getCommandId())));
        if (this.rwsSyncSupported) {
            sb.append(String.format("\n\trwsSyncEnabled=%b", Boolean.valueOf(this.rwsSyncEnabled)));
        } else {
            sb.append(String.format("\n\trwsSyncSupported=%b", Boolean.FALSE));
        }
        sb.append(String.format(Locale.US, "\n\tType=0x%02x, Level:(%d,%d)", Byte.valueOf(this.a), Integer.valueOf(this.b), Integer.valueOf(this.c)));
        sb.append("\n}");
        return sb.toString();
    }

    public SetLlAptBrightnessReq(byte b, int i, int i2, boolean z, boolean z2) {
        this.a = b;
        this.b = i;
        this.c = i2;
        this.rwsSyncSupported = z;
        this.rwsSyncEnabled = z2;
    }

    @Override // com.realsil.sdk.bbpro.profile.AppReq
    public Command encode() {
        byte[] bArr;
        if (this.rwsSyncSupported) {
            int i = this.b;
            int i2 = this.c;
            bArr = new byte[]{this.a, (byte) (i & 255), (byte) ((i >> 8) & 255), (byte) (i2 & 255), (byte) ((i2 >> 8) & 255), this.rwsSyncEnabled};
        } else {
            int i3 = this.b;
            int i4 = this.c;
            bArr = new byte[]{this.a, (byte) (i3 & 255), (byte) ((i3 >> 8) & 255), (byte) (i4 & 255), (byte) ((i4 >> 8) & 255)};
        }
        return new Command.Builder().writeType(2).packet(getCommandId(), bArr).eventId(getEventId()).build();
    }
}
