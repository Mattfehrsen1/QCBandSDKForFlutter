package com.realsil.sdk.bbpro.core.transportlayer;

/* loaded from: classes3.dex */
public class Command {
    public static final short INVALID_OPCODE = 0;
    public static final int WRITE_TYPE_DEFAULT = 2;
    public static final int WRITE_TYPE_NO_RESPONSE = 1;
    public static final int WRITE_TYPE_WITH_RESPONSE = 2;
    public int a;
    public short b;
    public short c;
    public byte[] d;
    public int e;

    public static class Builder {
        public byte[] a;
        public short b = 0;
        public short c = 0;
        public int d = 2;
        public int e = 2;

        public Command build() {
            return new Command(this.d, this.b, this.c, this.a, this.e);
        }

        public Builder commandId(short s) {
            this.b = s;
            return this;
        }

        public Builder eventId(short s) {
            this.c = s;
            return this;
        }

        public Builder packet(short s, byte[] bArr) {
            this.b = s;
            this.a = TransportLayerPacket.encodePayload(s, bArr);
            return this;
        }

        public Builder payload(byte[] bArr) {
            this.a = bArr;
            return this;
        }

        public Builder retransCount(int i) {
            this.e = i;
            return this;
        }

        public Builder writeType(int i) {
            this.d = i;
            return this;
        }
    }

    public Command(int i, short s, short s2, byte[] bArr, int i2) {
        this.a = i;
        this.b = s;
        this.c = s2;
        this.d = bArr;
        this.e = i2;
    }

    public byte[] encode(int i) {
        return TransportLayerPacket.encode(i, this.d);
    }

    public short getCommandId() {
        return this.b;
    }

    public short getEventId() {
        return this.c;
    }

    public byte[] getPayload() {
        return this.d;
    }

    public int getPayloadLength() {
        byte[] bArr = this.d;
        if (bArr == null) {
            return 0;
        }
        return bArr.length;
    }

    public int getRetransCount() {
        return this.e;
    }

    public int getWriteType() {
        return this.a;
    }

    public boolean isCommandIdAvailable() {
        return this.b != 0;
    }
}
