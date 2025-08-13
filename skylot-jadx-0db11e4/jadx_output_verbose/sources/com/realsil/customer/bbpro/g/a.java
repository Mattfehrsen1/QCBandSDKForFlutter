package com.realsil.customer.bbpro.g;

import com.realsil.customer.bbpro.core.transportlayer.Command;
import com.realsil.customer.bbpro.profile.AppReq;
import java.util.Locale;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/bbpro/g/a.class */
public final class a extends AppReq {
    public byte a;

    /* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/bbpro/g/a$b.class */
    public static class b {
        public byte a;

        public b(byte b) {
            this.a = b;
        }

        public a a() {
            return new a(this.a);
        }
    }

    @Override // com.realsil.customer.bbpro.profile.AppReq
    public short getCommandId() {
        return (short) 3106;
    }

    @Override // com.realsil.customer.bbpro.profile.AppReq
    public short getEventId() {
        return (short) 3106;
    }

    @Override // com.realsil.customer.bbpro.profile.AppReq
    public Command encode(int i) {
        return encode();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("GetLlAptInfoReq(0x%04X) {", Short.valueOf(getCommandId())));
        sb.append(String.format(Locale.US, "\n\tType=0x%02x", Byte.valueOf(this.a)));
        sb.append("\n}");
        return sb.toString();
    }

    public a(byte b2) {
        this.a = b2;
    }

    @Override // com.realsil.customer.bbpro.profile.AppReq
    public Command encode() {
        return new Command.Builder().writeType(2).packet(getCommandId(), new byte[]{this.a}).eventId(getEventId()).build();
    }
}
