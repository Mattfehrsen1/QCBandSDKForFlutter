package com.realsil.customer.bbpro.equalizer;

import com.realsil.customer.bbpro.core.transportlayer.Command;
import com.realsil.customer.bbpro.profile.AppReq;
import java.util.Locale;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/bbpro/equalizer/f.class */
public final class f extends AppReq {
    public int a;

    /* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/bbpro/equalizer/f$b.class */
    public static class b {
        public int a;

        public b a(int i) {
            this.a = i;
            return this;
        }

        public f a() {
            return new f(this.a);
        }
    }

    @Override // com.realsil.customer.bbpro.profile.AppReq
    public short getCommandId() {
        return (short) 528;
    }

    @Override // com.realsil.customer.bbpro.profile.AppReq
    public short getEventId() {
        return (short) 520;
    }

    @Override // com.realsil.customer.bbpro.profile.AppReq
    public Command encode(int i) {
        return encode();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("GetEqBasicInfo {");
        int i = this.a;
        if ((i & 255) != 255) {
            sb.append(String.format(Locale.US, "\n\tmode=0x%02X", Integer.valueOf(i)));
        }
        sb.append("\n}");
        return sb.toString();
    }

    public f(int i) {
        this.a = i;
    }

    @Override // com.realsil.customer.bbpro.profile.AppReq
    public Command encode() {
        byte[] bArr = null;
        int i = this.a;
        if ((i & 255) != 255) {
            bArr = new byte[]{(byte) (i & 255)};
        }
        return new Command.Builder().writeType(2).packet(getCommandId(), bArr).eventId(getEventId()).build();
    }
}
