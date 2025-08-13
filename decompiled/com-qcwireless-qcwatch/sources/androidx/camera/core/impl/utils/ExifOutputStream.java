package androidx.camera.core.impl.utils;

import androidx.camera.core.impl.utils.ExifData;
import androidx.core.util.Preconditions;
import java.io.BufferedOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes.dex */
public final class ExifOutputStream extends FilterOutputStream {
    private static final short BYTE_ALIGN_II = 18761;
    private static final short BYTE_ALIGN_MM = 19789;
    private static final boolean DEBUG = false;
    private static final byte[] IDENTIFIER_EXIF_APP1 = "Exif\u0000\u0000".getBytes(ExifAttribute.ASCII);
    private static final int IFD_OFFSET = 8;
    private static final byte START_CODE = 42;
    private static final int STATE_FRAME_HEADER = 1;
    private static final int STATE_JPEG_DATA = 2;
    private static final int STATE_SOI = 0;
    private static final int STREAMBUFFER_SIZE = 65536;
    private static final String TAG = "ExifOutputStream";
    private final ByteBuffer mBuffer;
    private int mByteToCopy;
    private int mByteToSkip;
    private final ExifData mExifData;
    private final byte[] mSingleByteArray;
    private int mState;

    public ExifOutputStream(OutputStream ou, ExifData exifData) {
        super(new BufferedOutputStream(ou, 65536));
        this.mSingleByteArray = new byte[1];
        this.mBuffer = ByteBuffer.allocate(4);
        this.mState = 0;
        this.mExifData = exifData;
    }

    private int requestByteToBuffer(int requestByteCount, byte[] buffer, int offset, int length) {
        int iMin = Math.min(length, requestByteCount - this.mBuffer.position());
        this.mBuffer.put(buffer, offset, iMin);
        return iMin;
    }

    /* JADX WARN: Code restructure failed: missing block: B:44:0x0106, code lost:
    
        if (r9 <= 0) goto L60;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x0108, code lost:
    
        r6.out.write(r7, r8, r9);
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x010d, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:?, code lost:
    
        return;
     */
    @Override // java.io.FilterOutputStream, java.io.OutputStream
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void write(byte[] r7, int r8, int r9) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 270
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.camera.core.impl.utils.ExifOutputStream.write(byte[], int, int):void");
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream
    public void write(int oneByte) throws IOException {
        byte[] bArr = this.mSingleByteArray;
        bArr[0] = (byte) (oneByte & 255);
        write(bArr);
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream
    public void write(byte[] buffer) throws IOException {
        write(buffer, 0, buffer.length);
    }

    private void writeExifSegment(ByteOrderedDataOutputStream dataOutputStream) throws IOException {
        int[] iArr = new int[ExifData.EXIF_TAGS.length];
        int[] iArr2 = new int[ExifData.EXIF_TAGS.length];
        for (ExifTag exifTag : ExifData.EXIF_POINTER_TAGS) {
            for (int i = 0; i < ExifData.EXIF_TAGS.length; i++) {
                this.mExifData.getAttributes(i).remove(exifTag.name);
            }
        }
        if (!this.mExifData.getAttributes(1).isEmpty()) {
            this.mExifData.getAttributes(0).put(ExifData.EXIF_POINTER_TAGS[1].name, ExifAttribute.createULong(0L, this.mExifData.getByteOrder()));
        }
        if (!this.mExifData.getAttributes(2).isEmpty()) {
            this.mExifData.getAttributes(0).put(ExifData.EXIF_POINTER_TAGS[2].name, ExifAttribute.createULong(0L, this.mExifData.getByteOrder()));
        }
        if (!this.mExifData.getAttributes(3).isEmpty()) {
            this.mExifData.getAttributes(1).put(ExifData.EXIF_POINTER_TAGS[3].name, ExifAttribute.createULong(0L, this.mExifData.getByteOrder()));
        }
        for (int i2 = 0; i2 < ExifData.EXIF_TAGS.length; i2++) {
            Iterator<Map.Entry<String, ExifAttribute>> it = this.mExifData.getAttributes(i2).entrySet().iterator();
            int i3 = 0;
            while (it.hasNext()) {
                int size = it.next().getValue().size();
                if (size > 4) {
                    i3 += size;
                }
            }
            iArr2[i2] = iArr2[i2] + i3;
        }
        int size2 = 8;
        for (int i4 = 0; i4 < ExifData.EXIF_TAGS.length; i4++) {
            if (!this.mExifData.getAttributes(i4).isEmpty()) {
                iArr[i4] = size2;
                size2 += (this.mExifData.getAttributes(i4).size() * 12) + 2 + 4 + iArr2[i4];
            }
        }
        int i5 = size2 + 8;
        if (!this.mExifData.getAttributes(1).isEmpty()) {
            this.mExifData.getAttributes(0).put(ExifData.EXIF_POINTER_TAGS[1].name, ExifAttribute.createULong(iArr[1], this.mExifData.getByteOrder()));
        }
        if (!this.mExifData.getAttributes(2).isEmpty()) {
            this.mExifData.getAttributes(0).put(ExifData.EXIF_POINTER_TAGS[2].name, ExifAttribute.createULong(iArr[2], this.mExifData.getByteOrder()));
        }
        if (!this.mExifData.getAttributes(3).isEmpty()) {
            this.mExifData.getAttributes(1).put(ExifData.EXIF_POINTER_TAGS[3].name, ExifAttribute.createULong(iArr[3], this.mExifData.getByteOrder()));
        }
        dataOutputStream.writeUnsignedShort(i5);
        dataOutputStream.write(IDENTIFIER_EXIF_APP1);
        dataOutputStream.writeShort(this.mExifData.getByteOrder() == ByteOrder.BIG_ENDIAN ? BYTE_ALIGN_MM : BYTE_ALIGN_II);
        dataOutputStream.setByteOrder(this.mExifData.getByteOrder());
        dataOutputStream.writeUnsignedShort(42);
        dataOutputStream.writeUnsignedInt(8L);
        for (int i6 = 0; i6 < ExifData.EXIF_TAGS.length; i6++) {
            if (!this.mExifData.getAttributes(i6).isEmpty()) {
                dataOutputStream.writeUnsignedShort(this.mExifData.getAttributes(i6).size());
                int size3 = iArr[i6] + 2 + (this.mExifData.getAttributes(i6).size() * 12) + 4;
                for (Map.Entry<String, ExifAttribute> entry : this.mExifData.getAttributes(i6).entrySet()) {
                    int i7 = ((ExifTag) Preconditions.checkNotNull(ExifData.Builder.sExifTagMapsForWriting.get(i6).get(entry.getKey()), "Tag not supported: " + entry.getKey() + ". Tag needs to be ported from ExifInterface to ExifData.")).number;
                    ExifAttribute value = entry.getValue();
                    int size4 = value.size();
                    dataOutputStream.writeUnsignedShort(i7);
                    dataOutputStream.writeUnsignedShort(value.format);
                    dataOutputStream.writeInt(value.numberOfComponents);
                    if (size4 > 4) {
                        dataOutputStream.writeUnsignedInt(size3);
                        size3 += size4;
                    } else {
                        dataOutputStream.write(value.bytes);
                        if (size4 < 4) {
                            while (size4 < 4) {
                                dataOutputStream.writeByte(0);
                                size4++;
                            }
                        }
                    }
                }
                dataOutputStream.writeUnsignedInt(0L);
                Iterator<Map.Entry<String, ExifAttribute>> it2 = this.mExifData.getAttributes(i6).entrySet().iterator();
                while (it2.hasNext()) {
                    ExifAttribute value2 = it2.next().getValue();
                    if (value2.bytes.length > 4) {
                        dataOutputStream.write(value2.bytes, 0, value2.bytes.length);
                    }
                }
            }
        }
        dataOutputStream.setByteOrder(ByteOrder.BIG_ENDIAN);
    }

    static final class JpegHeader {
        public static final short APP1 = -31;
        public static final short DAC = -52;
        public static final short DHT = -60;
        public static final short EOI = -39;
        public static final short JPG = -56;
        public static final short SOF0 = -64;
        public static final short SOF15 = -49;
        public static final short SOI = -40;

        public static boolean isSofMarker(short marker) {
            return (marker < -64 || marker > -49 || marker == -60 || marker == -56 || marker == -52) ? false : true;
        }

        private JpegHeader() {
        }
    }
}
