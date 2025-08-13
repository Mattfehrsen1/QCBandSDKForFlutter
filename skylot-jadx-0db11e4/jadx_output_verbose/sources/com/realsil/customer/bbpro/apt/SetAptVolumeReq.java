package com.realsil.customer.bbpro.apt;

import com.realsil.customer.bbpro.core.transportlayer.Command;
import com.realsil.customer.bbpro.profile.AppReq;
import java.util.Locale;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/bbpro/apt/SetAptVolumeReq.class */
public final class SetAptVolumeReq extends AppReq {
    public static final byte VOLUME_PARAMETER_TYPE_MAIN = 0;
    public static final byte VOLUME_PARAMETER_TYPE_SUB = 1;
    public static final byte VOLUME_TYPE_LEVEL = 0;
    public static final byte VOLUME_TYPE_STEPS = 1;
    public int a;
    public byte b;
    public int c;
    public int d;
    public boolean e;
    public boolean f;

    @Override // com.realsil.customer.bbpro.profile.AppReq
    public short getCommandId() {
        return (short) 3119;
    }

    @Override // com.realsil.customer.bbpro.profile.AppReq
    public short getEventId() {
        return (short) 3119;
    }

    @Override // com.realsil.customer.bbpro.profile.AppReq
    public Command encode(int i) {
        return encode();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("SetAptVolumeReq(0x%04X) {", Short.valueOf(getCommandId())));
        sb.append(String.format(Locale.US, "\n\tvolumeSpecVersion==0x%02X,volumeType=0x%02X, volumeLevel:(%d,%d)", Integer.valueOf(this.a), Byte.valueOf(this.b), Integer.valueOf(this.c), Integer.valueOf(this.d)));
        sb.append("\n}");
        return sb.toString();
    }

    /* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/bbpro/apt/SetAptVolumeReq$Builder.class */
    public static class Builder {
        public int a;
        public byte b;
        public int c;
        public int d;
        public boolean e;
        public boolean f;

        public Builder(int i, byte b) {
            this.a = i;
            this.b = b;
        }

        public Builder volumeLevel(int i) {
            this.c = i;
            this.d = i;
            return this;
        }

        public Builder save2Flash(boolean z) {
            this.f = z;
            this.e = true;
            return this;
        }

        public SetAptVolumeReq build() {
            return new SetAptVolumeReq(this.a, this.b, this.c, this.d, this.e, this.f);
        }

        public Builder volumeLevel(int i, int i2) {
            this.c = i;
            this.d = i2;
            return this;
        }

        public Builder(byte b) {
            this.b = b;
            this.a = 3;
        }
    }

    public SetAptVolumeReq(int i, byte b, int i2, int i3, boolean z, boolean z2) {
        this.a = i;
        this.b = b;
        this.c = i2;
        this.d = i3;
        this.e = z;
        this.f = z2;
    }

    @Override // com.realsil.customer.bbpro.profile.AppReq
    public Command encode() {
        byte[] bArr;
        int i = this.a;
        if (i == 1) {
            return new Command.Builder().writeType(2).packet((short) 521, new byte[]{(byte) (this.c & 255)}).build();
        }
        if (i == 2) {
            if (this.e) {
                bArr = new byte[]{(byte) (this.c & 255), 0};
                if (this.f) {
                    bArr[1] = 1;
                }
            } else {
                bArr = new byte[]{(byte) (this.c & 255)};
            }
            return new Command.Builder().writeType(2).packet((short) 526, bArr).build();
        }
        if (i == 3) {
            int i2 = this.c;
            int i3 = this.d;
            return new Command.Builder().writeType(2).packet((short) 3119, new byte[]{this.b, (byte) (i2 & 255), (byte) ((i2 >> 8) & 255), (byte) (i3 & 255), (byte) ((i3 >> 8) & 255)}).eventId((short) 3119).build();
        }
        int i4 = this.c;
        int i5 = this.d;
        return new Command.Builder().writeType(2).packet((short) 3119, new byte[]{this.b, (byte) (i4 & 255), (byte) ((i4 >> 8) & 255), (byte) (i5 & 255), (byte) ((i5 >> 8) & 255)}).eventId((short) 3119).build();
    }
}
