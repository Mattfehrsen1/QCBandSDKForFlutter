package com.realsil.customer.bbpro.profile;

import com.realsil.customer.bbpro.core.transportlayer.Command;
import com.realsil.customer.core.utility.DataConverter;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/bbpro/profile/SetCfgSettingsReq.class */
public final class SetCfgSettingsReq extends AppReq {
    public byte a;
    public byte[] b;
    public boolean c;

    /* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/bbpro/profile/SetCfgSettingsReq$Builder.class */
    public static class Builder {
        public byte a;
        public byte[] b;
        public boolean c = false;

        public Builder(byte b) {
            this.a = b;
        }

        public Builder cfgData(byte[] bArr) {
            this.b = bArr;
            return this;
        }

        public Builder assembleDataLength(boolean z) {
            this.c = z;
            return this;
        }

        public SetCfgSettingsReq build() {
            return new SetCfgSettingsReq(this.a, this.b, this.c);
        }
    }

    public static byte[] wrapperDeviceId(String str) {
        byte[] bytes = str.getBytes();
        byte[] bArr = new byte[12];
        if (bytes.length <= 12) {
            System.arraycopy(bytes, 0, bArr, 0, bytes.length);
        }
        return bArr;
    }

    public static byte[] combineSingleDeviceId(String str, String str2) {
        byte[] bytes = str.getBytes();
        byte[] bytes2 = str2.getBytes();
        byte[] bArr = new byte[24];
        if (bytes.length <= 12) {
            System.arraycopy(bytes, 0, bArr, 0, bytes.length);
        }
        if (bytes2.length <= 12) {
            System.arraycopy(bytes2, 0, bArr, 12, bytes2.length);
        }
        return bArr;
    }

    @Override // com.realsil.customer.bbpro.profile.AppReq
    public short getCommandId() {
        return (short) 18;
    }

    @Override // com.realsil.customer.bbpro.profile.AppReq
    public Command encode(int i) {
        return encode();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("SetCfgSettingsReq(0x%04X) {", Short.valueOf(getCommandId())));
        sb.append(String.format("\n\ttype=0x%02X", Byte.valueOf(this.a)));
        sb.append(String.format("\n\tdata=%s", DataConverter.bytes2Hex(this.b)));
        sb.append("\n}");
        return sb.toString();
    }

    public SetCfgSettingsReq(byte b, byte[] bArr, boolean z) {
        this.a = b;
        this.b = bArr;
        this.c = z;
    }

    @Override // com.realsil.customer.bbpro.profile.AppReq
    public Command encode() {
        byte[] bArr;
        int length = 0;
        byte[] bArr2 = this.b;
        if (bArr2 != null) {
            length = bArr2.length;
        }
        if (this.c) {
            int i = length;
            byte[] bArr3 = new byte[i + 2];
            bArr = bArr3;
            bArr[0] = this.a;
            bArr3[1] = (byte) (length & 255);
            if (i > 0) {
                System.arraycopy(bArr2, 0, bArr, 2, length);
            }
        } else {
            int i2 = length;
            byte[] bArr4 = new byte[i2 + 1];
            bArr = bArr4;
            bArr4[0] = this.a;
            if (i2 > 0) {
                System.arraycopy(bArr2, 0, bArr, 1, length);
            }
        }
        return new Command.Builder().writeType(2).packet(getCommandId(), bArr).eventId(getEventId()).build();
    }
}
