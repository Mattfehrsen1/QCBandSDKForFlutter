package com.realsil.sdk.bbpro.profile;

import com.realsil.sdk.bbpro.core.transportlayer.Command;
import com.realsil.sdk.core.utility.DataConverter;

/* loaded from: classes3.dex */
public final class SetCfgSettingsReq extends AppReq {
    public byte a;
    public byte[] b;
    public boolean c;

    public static class Builder {
        public byte a;
        public byte[] b;
        public boolean c = false;

        public Builder(byte b) {
            this.a = b;
        }

        public Builder assembleDataLength(boolean z) {
            this.c = z;
            return this;
        }

        public SetCfgSettingsReq build() {
            return new SetCfgSettingsReq(this.a, this.b, this.c);
        }

        public Builder cfgData(byte[] bArr) {
            this.b = bArr;
            return this;
        }
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

    public static byte[] wrapperDeviceId(String str) {
        byte[] bytes = str.getBytes();
        byte[] bArr = new byte[12];
        if (bytes.length <= 12) {
            System.arraycopy(bytes, 0, bArr, 0, bytes.length);
        }
        return bArr;
    }

    @Override // com.realsil.sdk.bbpro.profile.AppReq
    public Command encode(int i) {
        return encode();
    }

    @Override // com.realsil.sdk.bbpro.profile.AppReq
    public short getCommandId() {
        return (short) 18;
    }

    public String toString() {
        return String.format("SetCfgSettingsReq(0x%04X) {", Short.valueOf(getCommandId())) + String.format("\n\ttype=0x%02X", Byte.valueOf(this.a)) + String.format("\n\tdata=%s", DataConverter.bytes2Hex(this.b)) + "\n}";
    }

    public SetCfgSettingsReq(byte b, byte[] bArr, boolean z) {
        this.a = b;
        this.b = bArr;
        this.c = z;
    }

    @Override // com.realsil.sdk.bbpro.profile.AppReq
    public Command encode() {
        byte[] bArr;
        byte[] bArr2 = this.b;
        int length = bArr2 != null ? bArr2.length : 0;
        if (this.c) {
            bArr = new byte[length + 2];
            bArr[0] = this.a;
            bArr[1] = (byte) (length & 255);
            if (length > 0) {
                System.arraycopy(bArr2, 0, bArr, 2, length);
            }
        } else {
            bArr = new byte[length + 1];
            bArr[0] = this.a;
            if (length > 0) {
                System.arraycopy(bArr2, 0, bArr, 1, length);
            }
        }
        return new Command.Builder().writeType(2).packet(getCommandId(), bArr).eventId(getEventId()).build();
    }
}
