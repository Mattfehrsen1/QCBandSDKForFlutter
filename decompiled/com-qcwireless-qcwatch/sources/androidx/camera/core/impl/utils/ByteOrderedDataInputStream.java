package androidx.camera.core.impl.utils;

import java.io.ByteArrayInputStream;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteOrder;

/* loaded from: classes.dex */
final class ByteOrderedDataInputStream extends InputStream implements DataInput {
    private ByteOrder mByteOrder;
    private final DataInputStream mDataInputStream;
    final int mLength;
    int mPosition;
    private static final ByteOrder LITTLE_ENDIAN = ByteOrder.LITTLE_ENDIAN;
    private static final ByteOrder BIG_ENDIAN = ByteOrder.BIG_ENDIAN;

    ByteOrderedDataInputStream(InputStream in) throws IOException {
        this(in, ByteOrder.BIG_ENDIAN);
    }

    ByteOrderedDataInputStream(InputStream in, ByteOrder byteOrder) throws IOException {
        this.mByteOrder = ByteOrder.BIG_ENDIAN;
        DataInputStream dataInputStream = new DataInputStream(in);
        this.mDataInputStream = dataInputStream;
        int iAvailable = dataInputStream.available();
        this.mLength = iAvailable;
        this.mPosition = 0;
        dataInputStream.mark(iAvailable);
        this.mByteOrder = byteOrder;
    }

    ByteOrderedDataInputStream(byte[] bytes) throws IOException {
        this(new ByteArrayInputStream(bytes));
    }

    public void setByteOrder(ByteOrder byteOrder) {
        this.mByteOrder = byteOrder;
    }

    public void seek(long byteCount) throws IOException {
        int i = this.mPosition;
        if (i > byteCount) {
            this.mPosition = 0;
            this.mDataInputStream.reset();
            this.mDataInputStream.mark(this.mLength);
        } else {
            byteCount -= i;
        }
        int i2 = (int) byteCount;
        if (skipBytes(i2) != i2) {
            throw new IOException("Couldn't seek up to the byteCount");
        }
    }

    public int peek() {
        return this.mPosition;
    }

    @Override // java.io.InputStream
    public int available() throws IOException {
        return this.mDataInputStream.available();
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        this.mPosition++;
        return this.mDataInputStream.read();
    }

    @Override // java.io.InputStream
    public int read(byte[] b, int off, int len) throws IOException {
        int i = this.mDataInputStream.read(b, off, len);
        this.mPosition += i;
        return i;
    }

    @Override // java.io.DataInput
    public int readUnsignedByte() throws IOException {
        this.mPosition++;
        return this.mDataInputStream.readUnsignedByte();
    }

    @Override // java.io.DataInput
    public String readLine() {
        throw new UnsupportedOperationException("readLine() not implemented.");
    }

    @Override // java.io.DataInput
    public boolean readBoolean() throws IOException {
        this.mPosition++;
        return this.mDataInputStream.readBoolean();
    }

    @Override // java.io.DataInput
    public char readChar() throws IOException {
        this.mPosition += 2;
        return this.mDataInputStream.readChar();
    }

    @Override // java.io.DataInput
    public String readUTF() throws IOException {
        this.mPosition += 2;
        return this.mDataInputStream.readUTF();
    }

    @Override // java.io.DataInput
    public void readFully(byte[] buffer, int offset, int length) throws IOException {
        int i = this.mPosition + length;
        this.mPosition = i;
        if (i > this.mLength) {
            throw new EOFException();
        }
        if (this.mDataInputStream.read(buffer, offset, length) != length) {
            throw new IOException("Couldn't read up to the length of buffer");
        }
    }

    @Override // java.io.DataInput
    public void readFully(byte[] buffer) throws IOException {
        int length = this.mPosition + buffer.length;
        this.mPosition = length;
        if (length > this.mLength) {
            throw new EOFException();
        }
        if (this.mDataInputStream.read(buffer, 0, buffer.length) != buffer.length) {
            throw new IOException("Couldn't read up to the length of buffer");
        }
    }

    @Override // java.io.DataInput
    public byte readByte() throws IOException {
        int i = this.mPosition + 1;
        this.mPosition = i;
        if (i > this.mLength) {
            throw new EOFException();
        }
        int i2 = this.mDataInputStream.read();
        if (i2 >= 0) {
            return (byte) i2;
        }
        throw new EOFException();
    }

    @Override // java.io.DataInput
    public short readShort() throws IOException {
        int i = this.mPosition + 2;
        this.mPosition = i;
        if (i > this.mLength) {
            throw new EOFException();
        }
        int i2 = this.mDataInputStream.read();
        int i3 = this.mDataInputStream.read();
        if ((i2 | i3) < 0) {
            throw new EOFException();
        }
        ByteOrder byteOrder = this.mByteOrder;
        if (byteOrder == LITTLE_ENDIAN) {
            return (short) ((i3 << 8) + i2);
        }
        if (byteOrder == BIG_ENDIAN) {
            return (short) ((i2 << 8) + i3);
        }
        throw new IOException("Invalid byte order: " + this.mByteOrder);
    }

    @Override // java.io.DataInput
    public int readInt() throws IOException {
        int i = this.mPosition + 4;
        this.mPosition = i;
        if (i > this.mLength) {
            throw new EOFException();
        }
        int i2 = this.mDataInputStream.read();
        int i3 = this.mDataInputStream.read();
        int i4 = this.mDataInputStream.read();
        int i5 = this.mDataInputStream.read();
        if ((i2 | i3 | i4 | i5) < 0) {
            throw new EOFException();
        }
        ByteOrder byteOrder = this.mByteOrder;
        if (byteOrder == LITTLE_ENDIAN) {
            return (i5 << 24) + (i4 << 16) + (i3 << 8) + i2;
        }
        if (byteOrder == BIG_ENDIAN) {
            return (i2 << 24) + (i3 << 16) + (i4 << 8) + i5;
        }
        throw new IOException("Invalid byte order: " + this.mByteOrder);
    }

    @Override // java.io.DataInput
    public int skipBytes(int byteCount) throws IOException {
        int iMin = Math.min(byteCount, this.mLength - this.mPosition);
        int iSkipBytes = 0;
        while (iSkipBytes < iMin) {
            iSkipBytes += this.mDataInputStream.skipBytes(iMin - iSkipBytes);
        }
        this.mPosition += iSkipBytes;
        return iSkipBytes;
    }

    @Override // java.io.DataInput
    public int readUnsignedShort() throws IOException {
        int i = this.mPosition + 2;
        this.mPosition = i;
        if (i > this.mLength) {
            throw new EOFException();
        }
        int i2 = this.mDataInputStream.read();
        int i3 = this.mDataInputStream.read();
        if ((i2 | i3) < 0) {
            throw new EOFException();
        }
        ByteOrder byteOrder = this.mByteOrder;
        if (byteOrder == LITTLE_ENDIAN) {
            return (i3 << 8) + i2;
        }
        if (byteOrder == BIG_ENDIAN) {
            return (i2 << 8) + i3;
        }
        throw new IOException("Invalid byte order: " + this.mByteOrder);
    }

    public long readUnsignedInt() throws IOException {
        return readInt() & 4294967295L;
    }

    @Override // java.io.DataInput
    public long readLong() throws IOException {
        int i = this.mPosition + 8;
        this.mPosition = i;
        if (i > this.mLength) {
            throw new EOFException();
        }
        int i2 = this.mDataInputStream.read();
        int i3 = this.mDataInputStream.read();
        int i4 = this.mDataInputStream.read();
        int i5 = this.mDataInputStream.read();
        int i6 = this.mDataInputStream.read();
        int i7 = this.mDataInputStream.read();
        int i8 = this.mDataInputStream.read();
        int i9 = this.mDataInputStream.read();
        if ((i2 | i3 | i4 | i5 | i6 | i7 | i8 | i9) < 0) {
            throw new EOFException();
        }
        ByteOrder byteOrder = this.mByteOrder;
        if (byteOrder == LITTLE_ENDIAN) {
            return (i9 << 56) + (i8 << 48) + (i7 << 40) + (i6 << 32) + (i5 << 24) + (i4 << 16) + (i3 << 8) + i2;
        }
        if (byteOrder == BIG_ENDIAN) {
            return (i2 << 56) + (i3 << 48) + (i4 << 40) + (i5 << 32) + (i6 << 24) + (i7 << 16) + (i8 << 8) + i9;
        }
        throw new IOException("Invalid byte order: " + this.mByteOrder);
    }

    @Override // java.io.DataInput
    public float readFloat() throws IOException {
        return Float.intBitsToFloat(readInt());
    }

    @Override // java.io.DataInput
    public double readDouble() throws IOException {
        return Double.longBitsToDouble(readLong());
    }

    @Override // java.io.InputStream
    public void mark(int readlimit) {
        synchronized (this.mDataInputStream) {
            this.mDataInputStream.mark(readlimit);
        }
    }

    public int getLength() {
        return this.mLength;
    }
}
