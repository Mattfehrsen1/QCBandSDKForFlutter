package com.realsil.customer.bbpro.keymapping;

import com.realsil.customer.bbpro.core.transportlayer.Command;
import com.realsil.customer.bbpro.model.KeyMmiSettings;
import com.realsil.customer.bbpro.profile.AppReq;
import java.util.ArrayList;
import java.util.List;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/bbpro/keymapping/ResetKeyMmiMapReq.class */
public final class ResetKeyMmiMapReq extends AppReq {
    public static final byte BUD_TYPE_STEREO = 0;
    public static final byte BUD_TYPE_LCH = 1;
    public static final byte BUD_TYPE_RCH = 2;
    public static final byte BUD_TYPE_ALL = 3;
    public static final byte BUD_TYPE_RESERVE = -1;
    public byte a;

    public static List<KeyMmiSettings> decodeKeyMmiSettings(byte[] bArr) {
        ArrayList arrayList = new ArrayList();
        if (bArr != null && bArr.length > 0) {
            int i = bArr[0] & 255;
            if (bArr.length >= (i * 4) + 1) {
                for (int i2 = 0; i2 < i; i2++) {
                    KeyMmiSettings keyMmiSettings = new KeyMmiSettings();
                    int i3 = i2 * 4;
                    keyMmiSettings.setBud(bArr[i3 + 1]);
                    keyMmiSettings.setScenario(bArr[i3 + 2]);
                    keyMmiSettings.setClickType(bArr[i3 + 3]);
                    keyMmiSettings.setMmiIndex(bArr[i3 + 4]);
                    arrayList.add(keyMmiSettings);
                }
            }
        }
        return arrayList;
    }

    @Override // com.realsil.customer.bbpro.profile.AppReq
    public short getCommandId() {
        return (short) 1799;
    }

    @Override // com.realsil.customer.bbpro.profile.AppReq
    public short getEventId() {
        return (short) 1795;
    }

    @Override // com.realsil.customer.bbpro.profile.AppReq
    public Command encode(int i) {
        return encode();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("ResetKeyMmiMapReq(0x%04X) {", Short.valueOf(getCommandId())));
        byte b = this.a;
        if (b != -1) {
            sb.append(String.format("\n\tbudType=0x%02X", Byte.valueOf(b)));
        }
        sb.append("\n}");
        return sb.toString();
    }

    /* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/bbpro/keymapping/ResetKeyMmiMapReq$Builder.class */
    public static class Builder {
        public byte a;

        public Builder() {
            this.a = (byte) -1;
        }

        public ResetKeyMmiMapReq build() {
            return new ResetKeyMmiMapReq(this.a);
        }

        public Builder(byte b) {
            this.a = b;
        }
    }

    public ResetKeyMmiMapReq(byte b) {
        this.a = b;
    }

    @Override // com.realsil.customer.bbpro.profile.AppReq
    public Command encode() {
        byte[] bArr = null;
        byte b = this.a;
        if (b != -1) {
            bArr = new byte[]{b};
        }
        return new Command.Builder().writeType(2).packet(getCommandId(), bArr).eventId(getEventId()).build();
    }
}
