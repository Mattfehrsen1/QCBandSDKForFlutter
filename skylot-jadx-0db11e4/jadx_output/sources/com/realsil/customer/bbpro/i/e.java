package com.realsil.customer.bbpro.i;

import com.realsil.customer.bbpro.core.transportlayer.Command;
import com.realsil.customer.bbpro.profile.AppReq;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/bbpro/i/e.class */
public final class e extends AppReq {
    public int a;
    public int b;

    /* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/bbpro/i/e$b.class */
    public static class b {
        public int a;
        public int b;

        public b a(int i) {
            this.a = i;
            return this;
        }

        public b b(int i) {
            this.b = i;
            return this;
        }

        public e a() {
            return new e(this.a, this.b);
        }
    }

    @Override // com.realsil.customer.bbpro.profile.AppReq
    public short getCommandId() {
        return (short) 12;
    }

    @Override // com.realsil.customer.bbpro.profile.AppReq
    public short getEventId() {
        return (short) 17;
    }

    @Override // com.realsil.customer.bbpro.profile.AppReq
    public Command encode(int i) {
        return encode();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("SyncCmdSetVersionReq(0x%04X) {", Short.valueOf(getCommandId())));
        sb.append(String.format("\n\tcmdSetVersion=0x%04X, eqSpecVersion=0x%04X", Integer.valueOf(this.a), Integer.valueOf(this.b)));
        sb.append("\n}");
        return sb.toString();
    }

    public e(int i, int i2) {
        this.a = i;
        this.b = i2;
    }

    @Override // com.realsil.customer.bbpro.profile.AppReq
    public Command encode() {
        int i = this.a;
        int i2 = this.b;
        return new Command.Builder().writeType(2).packet(getCommandId(), new byte[]{0, (byte) ((i >> 8) & 255), (byte) (i & 255), (byte) ((i2 >> 8) & 255), (byte) (i2 & 255)}).eventId(getEventId()).build();
    }
}
