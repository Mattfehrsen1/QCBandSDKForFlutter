package com.realsil.sdk.bbpro.equalizer;

import com.realsil.sdk.bbpro.core.protocol.CommandContract;
import com.realsil.sdk.core.logger.ZLogger;
import java.util.Locale;

/* loaded from: classes3.dex */
public final class SetEqEntryIndexReq {
    public int a;
    public int b;
    public int c;

    public static class Builder {
        public int a;
        public int b;
        public int c;

        public Builder(int i, int i2, int i3) {
            this.a = i;
            this.b = i2;
            this.c = i3;
        }

        public SetEqEntryIndexReq build() {
            return new SetEqEntryIndexReq(this.a, this.b, this.c);
        }
    }

    public byte[] encode(int i) {
        int i2 = this.a;
        if (i2 != 0) {
            if (i2 == 1) {
                return CommandContract.buildPacket((short) 523, new byte[]{(byte) (this.c & 255)});
            }
            return null;
        }
        if (i >= 4) {
            return CommandContract.buildPacket((short) 517, new byte[]{(byte) (this.c & 255), (byte) this.b});
        }
        if (i < 1) {
            int i3 = this.c;
            return CommandContract.buildPacket((short) 520, new byte[]{(byte) (i3 & 255), (byte) ((i3 >> 8) & 255)});
        }
        if (this.b != 1) {
            return CommandContract.buildPacket((short) 517, new byte[]{(byte) (this.c & 255)});
        }
        ZLogger.v("gaming mode eq entry index can not be change");
        return null;
    }

    public int getEqIndex() {
        return this.c;
    }

    public int getEqMode() {
        return this.b;
    }

    public int getEqType() {
        return this.a;
    }

    public String toString() {
        return "SetEqEntryIndexReq {" + String.format(Locale.US, "\n\teqType=%d,eqMode=%d,eqIndex=%d", Integer.valueOf(this.a), Integer.valueOf(this.b), Integer.valueOf(this.c)) + "\n}";
    }

    public SetEqEntryIndexReq(int i, int i2, int i3) {
        this.a = i;
        this.b = i2;
        this.c = i3;
    }
}
