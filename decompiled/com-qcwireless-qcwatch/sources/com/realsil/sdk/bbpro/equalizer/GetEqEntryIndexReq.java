package com.realsil.sdk.bbpro.equalizer;

import com.realsil.sdk.bbpro.core.protocol.CommandContract;
import java.util.Locale;

/* loaded from: classes3.dex */
public final class GetEqEntryIndexReq {
    public int a;
    public int b;

    public static class Builder {
        public int a;
        public int b;

        public Builder(int i, int i2) {
            this.a = i;
            this.b = i2;
        }

        public GetEqEntryIndexReq build() {
            return new GetEqEntryIndexReq(this.a, this.b);
        }
    }

    public byte[] encode(int i) {
        int i2 = this.a;
        if (i2 == 0) {
            return i >= 4 ? CommandContract.buildPacket((short) 518, new byte[]{(byte) this.b}) : this.b == 1 ? CommandContract.buildPacket((short) 3587) : CommandContract.buildPacket((short) 518);
        }
        if (i2 == 1) {
            return CommandContract.buildPacket((short) 524);
        }
        return null;
    }

    public int getEqMode() {
        return this.b;
    }

    public int getEqType() {
        return this.a;
    }

    public String toString() {
        return "GetEqEntryIndexReq {" + String.format(Locale.US, "\neqType=%d,eqMode=%d", Integer.valueOf(this.a), Integer.valueOf(this.b)) + "\n}";
    }

    public GetEqEntryIndexReq(int i, int i2) {
        this.a = i;
        this.b = i2;
    }
}
