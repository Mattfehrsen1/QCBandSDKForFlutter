package com.realsil.customer.bbpro.llapt;

import com.realsil.customer.bbpro.core.transportlayer.Command;
import com.realsil.customer.bbpro.profile.AppReq;
import java.util.Locale;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/bbpro/llapt/SetLlAptBrightnessReq.class */
public final class SetLlAptBrightnessReq extends AppReq {
    public static final byte PARAMETER_TYPE_MAIN = 0;
    public static final byte PARAMETER_TYPE_SUB = 1;
    public byte a;
    public int b;
    public int c;
    public boolean rwsSyncSupported;
    public boolean rwsSyncEnabled;

    /* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/bbpro/llapt/SetLlAptBrightnessReq$Builder.class */
    public static class Builder {
        public byte a;
        public int b;
        public int c;
        public boolean d = false;
        public boolean e = false;

        public Builder(byte b) {
            this.a = b;
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

        public SetLlAptBrightnessReq build() {
            return new SetLlAptBrightnessReq(this.a, this.b, this.c, this.d, this.e);
        }
    }

    @Override // com.realsil.customer.bbpro.profile.AppReq
    public short getCommandId() {
        return (short) 3122;
    }

    @Override // com.realsil.customer.bbpro.profile.AppReq
    public short getEventId() {
        return (short) 3122;
    }

    @Override // com.realsil.customer.bbpro.profile.AppReq
    public Command encode(int i) {
        return encode();
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

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.realsil.customer.bbpro.profile.AppReq
    public Command encode() {
        byte[] bArr;
        if (this.rwsSyncSupported) {
            byte[] bArr2 = new byte[6];
            bArr = bArr2;
            bArr[0] = this.a;
            int i = this.b;
            bArr[1] = (byte) (i & 255);
            bArr[2] = (byte) ((i >> 8) & 255);
            int i2 = this.c;
            bArr[3] = (byte) (i2 & 255);
            bArr[4] = (byte) ((i2 >> 8) & 255);
            bArr2[5] = this.rwsSyncEnabled;
        } else {
            byte[] bArr3 = new byte[5];
            bArr = bArr3;
            bArr[0] = this.a;
            int i3 = this.b;
            bArr[1] = (byte) (i3 & 255);
            bArr[2] = (byte) ((i3 >> 8) & 255);
            int i4 = this.c;
            bArr[3] = (byte) (i4 & 255);
            bArr3[4] = (byte) ((i4 >> 8) & 255);
        }
        return new Command.Builder().writeType(2).packet(getCommandId(), bArr).eventId(getEventId()).build();
    }
}
