package com.realsil.customer.bbpro.core.transportlayer;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/bbpro/core/transportlayer/Command.class */
public class Command {
    public static final int WRITE_TYPE_NO_RESPONSE = 1;
    public static final int WRITE_TYPE_WITH_RESPONSE = 2;
    public static final int WRITE_TYPE_DEFAULT = 2;
    public static final short INVALID_OPCODE = 0;
    public final String a;
    public int b;
    public final int c;
    public final short d;
    public final short e;
    public final byte[] f;
    public final int g;

    /* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/bbpro/core/transportlayer/Command$Builder.class */
    public static class Builder {
        public String a;
        public byte[] b;
        public short c = 0;
        public short d = 0;
        public int e = 2;
        public int f = 2;

        public Builder dst(String str) {
            this.a = str;
            return this;
        }

        public Builder packet(short s, byte[] bArr) {
            this.c = s;
            this.b = TransportLayerPacket.encodePayload(s, bArr);
            return this;
        }

        public Builder commandId(short s) {
            this.c = s;
            return this;
        }

        public Builder payload(byte[] bArr) {
            this.b = bArr;
            return this;
        }

        public Builder eventId(short s) {
            this.d = s;
            return this;
        }

        public Builder writeType(int i) {
            this.e = i;
            return this;
        }

        public Builder retransCount(int i) {
            this.f = i;
            return this;
        }

        public Command build() {
            return new Command(this.a, this.e, this.c, this.d, this.b, this.f);
        }
    }

    public Command(String str, int i, short s, short s2, byte[] bArr, int i2) {
        this.g = 2;
        this.a = str;
        this.c = i;
        this.d = s;
        this.e = s2;
        this.f = bArr;
        this.g = i2;
    }

    public int getPayloadLength() {
        byte[] bArr = this.f;
        if (bArr == null) {
            return 0;
        }
        return bArr.length;
    }

    public byte[] getPayload() {
        return this.f;
    }

    public int getWriteType() {
        return this.c;
    }

    public int getRetransCount() {
        return this.g;
    }

    public boolean isCommandIdAvailable() {
        return this.d != 0;
    }

    public short getCommandId() {
        return this.d;
    }

    public short getEventId() {
        return this.e;
    }

    public String getDst() {
        return this.a;
    }

    public void setSn(int i) {
        this.b = i;
    }

    public byte[] encode() {
        return TransportLayerPacket.encode(this.b, this.f);
    }

    public byte[] encode(int i) {
        return TransportLayerPacket.encode(i, this.f);
    }
}
