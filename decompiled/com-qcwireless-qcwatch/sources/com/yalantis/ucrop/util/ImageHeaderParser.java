package com.yalantis.ucrop.util;

import android.content.Context;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.text.TextUtils;
import android.util.Log;
import androidx.core.view.MotionEventCompat;
import androidx.exifinterface.media.ExifInterface;
import com.bumptech.glide.load.Key;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;

/* loaded from: classes4.dex */
public class ImageHeaderParser {
    private static final int EXIF_MAGIC_NUMBER = 65496;
    private static final int EXIF_SEGMENT_TYPE = 225;
    private static final int INTEL_TIFF_MAGIC_NUMBER = 18761;
    private static final int MARKER_EOI = 217;
    private static final int MOTOROLA_TIFF_MAGIC_NUMBER = 19789;
    private static final int ORIENTATION_TAG_TYPE = 274;
    private static final int SEGMENT_SOS = 218;
    private static final int SEGMENT_START_ID = 255;
    private static final String TAG = "ImageHeaderParser";
    public static final int UNKNOWN_ORIENTATION = -1;
    private final Reader reader;
    private static final String JPEG_EXIF_SEGMENT_PREAMBLE = "Exif\u0000\u0000";
    private static final byte[] JPEG_EXIF_SEGMENT_PREAMBLE_BYTES = JPEG_EXIF_SEGMENT_PREAMBLE.getBytes(Charset.forName(Key.STRING_CHARSET_NAME));
    private static final int[] BYTES_PER_FORMAT = {0, 1, 1, 2, 4, 8, 1, 1, 2, 4, 8, 4, 8};

    private interface Reader {
        int getUInt16() throws IOException;

        short getUInt8() throws IOException;

        int read(byte[] bArr, int i) throws IOException;

        long skip(long j) throws IOException;
    }

    private static int calcTagOffset(int i, int i2) {
        return i + 2 + (i2 * 12);
    }

    private static boolean handles(int i) {
        return (i & EXIF_MAGIC_NUMBER) == EXIF_MAGIC_NUMBER || i == MOTOROLA_TIFF_MAGIC_NUMBER || i == INTEL_TIFF_MAGIC_NUMBER;
    }

    public ImageHeaderParser(InputStream inputStream) {
        this.reader = new StreamReader(inputStream);
    }

    public int getOrientation() throws IOException {
        int uInt16 = this.reader.getUInt16();
        if (!handles(uInt16)) {
            if (Log.isLoggable(TAG, 3)) {
                Log.d(TAG, "Parser doesn't handle magic number: " + uInt16);
            }
            return -1;
        }
        int iMoveToExifSegmentAndGetLength = moveToExifSegmentAndGetLength();
        if (iMoveToExifSegmentAndGetLength == -1) {
            if (Log.isLoggable(TAG, 3)) {
                Log.d(TAG, "Failed to parse exif segment length, or exif segment not found");
            }
            return -1;
        }
        return parseExifSegment(new byte[iMoveToExifSegmentAndGetLength], iMoveToExifSegmentAndGetLength);
    }

    private int parseExifSegment(byte[] bArr, int i) throws IOException {
        int i2 = this.reader.read(bArr, i);
        if (i2 != i) {
            if (Log.isLoggable(TAG, 3)) {
                Log.d(TAG, "Unable to read exif segment data, length: " + i + ", actually read: " + i2);
            }
            return -1;
        }
        if (hasJpegExifPreamble(bArr, i)) {
            return parseExifSegment(new RandomAccessReader(bArr, i));
        }
        if (Log.isLoggable(TAG, 3)) {
            Log.d(TAG, "Missing jpeg exif preamble");
        }
        return -1;
    }

    private boolean hasJpegExifPreamble(byte[] bArr, int i) {
        boolean z = bArr != null && i > JPEG_EXIF_SEGMENT_PREAMBLE_BYTES.length;
        if (z) {
            int i2 = 0;
            while (true) {
                byte[] bArr2 = JPEG_EXIF_SEGMENT_PREAMBLE_BYTES;
                if (i2 >= bArr2.length) {
                    break;
                }
                if (bArr[i2] != bArr2[i2]) {
                    return false;
                }
                i2++;
            }
        }
        return z;
    }

    private int moveToExifSegmentAndGetLength() throws IOException {
        short uInt8;
        int uInt16;
        long j;
        long jSkip;
        do {
            short uInt82 = this.reader.getUInt8();
            if (uInt82 != 255) {
                if (Log.isLoggable(TAG, 3)) {
                    Log.d(TAG, "Unknown segmentId=" + ((int) uInt82));
                }
                return -1;
            }
            uInt8 = this.reader.getUInt8();
            if (uInt8 == SEGMENT_SOS) {
                return -1;
            }
            if (uInt8 == MARKER_EOI) {
                if (Log.isLoggable(TAG, 3)) {
                    Log.d(TAG, "Found MARKER_EOI in exif segment");
                }
                return -1;
            }
            uInt16 = this.reader.getUInt16() - 2;
            if (uInt8 == EXIF_SEGMENT_TYPE) {
                return uInt16;
            }
            j = uInt16;
            jSkip = this.reader.skip(j);
        } while (jSkip == j);
        if (Log.isLoggable(TAG, 3)) {
            Log.d(TAG, "Unable to skip enough data, type: " + ((int) uInt8) + ", wanted to skip: " + uInt16 + ", but actually skipped: " + jSkip);
        }
        return -1;
    }

    private static int parseExifSegment(RandomAccessReader randomAccessReader) {
        ByteOrder byteOrder;
        short int16 = randomAccessReader.getInt16(6);
        if (int16 == MOTOROLA_TIFF_MAGIC_NUMBER) {
            byteOrder = ByteOrder.BIG_ENDIAN;
        } else if (int16 == INTEL_TIFF_MAGIC_NUMBER) {
            byteOrder = ByteOrder.LITTLE_ENDIAN;
        } else {
            if (Log.isLoggable(TAG, 3)) {
                Log.d(TAG, "Unknown endianness = " + ((int) int16));
            }
            byteOrder = ByteOrder.BIG_ENDIAN;
        }
        randomAccessReader.order(byteOrder);
        int int32 = randomAccessReader.getInt32(10) + 6;
        short int162 = randomAccessReader.getInt16(int32);
        for (int i = 0; i < int162; i++) {
            int iCalcTagOffset = calcTagOffset(int32, i);
            short int163 = randomAccessReader.getInt16(iCalcTagOffset);
            if (int163 == ORIENTATION_TAG_TYPE) {
                short int164 = randomAccessReader.getInt16(iCalcTagOffset + 2);
                if (int164 < 1 || int164 > 12) {
                    if (Log.isLoggable(TAG, 3)) {
                        Log.d(TAG, "Got invalid format code = " + ((int) int164));
                    }
                } else {
                    int int322 = randomAccessReader.getInt32(iCalcTagOffset + 4);
                    if (int322 < 0) {
                        if (Log.isLoggable(TAG, 3)) {
                            Log.d(TAG, "Negative tiff component count");
                        }
                    } else {
                        if (Log.isLoggable(TAG, 3)) {
                            Log.d(TAG, "Got tagIndex=" + i + " tagType=" + ((int) int163) + " formatCode=" + ((int) int164) + " componentCount=" + int322);
                        }
                        int i2 = int322 + BYTES_PER_FORMAT[int164];
                        if (i2 > 4) {
                            if (Log.isLoggable(TAG, 3)) {
                                Log.d(TAG, "Got byte count > 4, not orientation, continuing, formatCode=" + ((int) int164));
                            }
                        } else {
                            int i3 = iCalcTagOffset + 8;
                            if (i3 < 0 || i3 > randomAccessReader.length()) {
                                if (Log.isLoggable(TAG, 3)) {
                                    Log.d(TAG, "Illegal tagValueOffset=" + i3 + " tagType=" + ((int) int163));
                                }
                            } else if (i2 < 0 || i2 + i3 > randomAccessReader.length()) {
                                if (Log.isLoggable(TAG, 3)) {
                                    Log.d(TAG, "Illegal number of bytes for TI tag data tagType=" + ((int) int163));
                                }
                            } else {
                                return randomAccessReader.getInt16(i3);
                            }
                        }
                    }
                }
            }
        }
        return -1;
    }

    private static class RandomAccessReader {
        private final ByteBuffer data;

        public RandomAccessReader(byte[] bArr, int i) {
            this.data = (ByteBuffer) ByteBuffer.wrap(bArr).order(ByteOrder.BIG_ENDIAN).limit(i);
        }

        public void order(ByteOrder byteOrder) {
            this.data.order(byteOrder);
        }

        public int length() {
            return this.data.remaining();
        }

        public int getInt32(int i) {
            return this.data.getInt(i);
        }

        public short getInt16(int i) {
            return this.data.getShort(i);
        }
    }

    private static class StreamReader implements Reader {
        private final InputStream is;

        public StreamReader(InputStream inputStream) {
            this.is = inputStream;
        }

        @Override // com.yalantis.ucrop.util.ImageHeaderParser.Reader
        public int getUInt16() throws IOException {
            return ((this.is.read() << 8) & MotionEventCompat.ACTION_POINTER_INDEX_MASK) | (this.is.read() & 255);
        }

        @Override // com.yalantis.ucrop.util.ImageHeaderParser.Reader
        public short getUInt8() throws IOException {
            return (short) (this.is.read() & 255);
        }

        @Override // com.yalantis.ucrop.util.ImageHeaderParser.Reader
        public long skip(long j) throws IOException {
            if (j < 0) {
                return 0L;
            }
            long j2 = j;
            while (j2 > 0) {
                long jSkip = this.is.skip(j2);
                if (jSkip <= 0) {
                    if (this.is.read() == -1) {
                        break;
                    }
                    jSkip = 1;
                }
                j2 -= jSkip;
            }
            return j - j2;
        }

        @Override // com.yalantis.ucrop.util.ImageHeaderParser.Reader
        public int read(byte[] bArr, int i) throws IOException {
            int i2 = i;
            while (i2 > 0) {
                int i3 = this.is.read(bArr, i - i2, i2);
                if (i3 == -1) {
                    break;
                }
                i2 -= i3;
            }
            return i - i2;
        }
    }

    public static void copyExif(ExifInterface exifInterface, int i, int i2, String str) throws Throwable {
        try {
            copyExifAttributes(exifInterface, new ExifInterface(str), i, i2);
        } catch (IOException e) {
            Log.d(TAG, e.getMessage());
        }
    }

    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:19:0x0038 -> B:29:0x003f). Please report as a decompilation issue!!! */
    public static void copyExif(Context context, int i, int i2, Uri uri, String str) throws IOException {
        if (context == null) {
            Log.d(TAG, "context is null");
            return;
        }
        InputStream inputStreamOpenInputStream = null;
        try {
            try {
                try {
                    inputStreamOpenInputStream = context.getContentResolver().openInputStream(uri);
                    copyExifAttributes(new ExifInterface(inputStreamOpenInputStream), new ExifInterface(str), i, i2);
                    if (inputStreamOpenInputStream != null) {
                        inputStreamOpenInputStream.close();
                    }
                } catch (IOException e) {
                    Log.d(TAG, e.getMessage(), e);
                    if (inputStreamOpenInputStream == null) {
                    } else {
                        inputStreamOpenInputStream.close();
                    }
                }
            } catch (IOException e2) {
                Log.d(TAG, e2.getMessage(), e2);
            }
        } catch (Throwable th) {
            if (inputStreamOpenInputStream != null) {
                try {
                    inputStreamOpenInputStream.close();
                } catch (IOException e3) {
                    Log.d(TAG, e3.getMessage(), e3);
                }
            }
            throw th;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:49:0x0086 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:57:0x0078 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:68:? A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void copyExif(android.content.Context r4, int r5, int r6, android.net.Uri r7, android.net.Uri r8) throws java.lang.Throwable {
        /*
            java.lang.String r0 = "ImageHeaderParser"
            if (r4 != 0) goto Lb
            java.lang.String r4 = "context is null"
            android.util.Log.d(r0, r4)
            return
        Lb:
            r1 = 0
            android.content.ContentResolver r2 = r4.getContentResolver()     // Catch: java.lang.Throwable -> L4c java.io.IOException -> L4f
            java.io.InputStream r7 = r2.openInputStream(r7)     // Catch: java.lang.Throwable -> L4c java.io.IOException -> L4f
            androidx.exifinterface.media.ExifInterface r2 = new androidx.exifinterface.media.ExifInterface     // Catch: java.lang.Throwable -> L44 java.io.IOException -> L48
            r2.<init>(r7)     // Catch: java.lang.Throwable -> L44 java.io.IOException -> L48
            android.content.ContentResolver r4 = r4.getContentResolver()     // Catch: java.lang.Throwable -> L44 java.io.IOException -> L48
            java.lang.String r3 = "rw"
            android.os.ParcelFileDescriptor r1 = r4.openFileDescriptor(r8, r3)     // Catch: java.lang.Throwable -> L44 java.io.IOException -> L48
            androidx.exifinterface.media.ExifInterface r4 = new androidx.exifinterface.media.ExifInterface     // Catch: java.lang.Throwable -> L44 java.io.IOException -> L48
            java.io.FileDescriptor r8 = r1.getFileDescriptor()     // Catch: java.lang.Throwable -> L44 java.io.IOException -> L48
            r4.<init>(r8)     // Catch: java.lang.Throwable -> L44 java.io.IOException -> L48
            copyExifAttributes(r2, r4, r5, r6)     // Catch: java.lang.Throwable -> L44 java.io.IOException -> L48
            if (r7 == 0) goto L3e
            r7.close()     // Catch: java.io.IOException -> L36
            goto L3e
        L36:
            r4 = move-exception
            java.lang.String r5 = r4.getMessage()
            android.util.Log.d(r0, r5, r4)
        L3e:
            if (r1 == 0) goto L74
            r1.close()     // Catch: java.io.IOException -> L6c
            goto L74
        L44:
            r4 = move-exception
            r5 = r1
            r1 = r7
            goto L76
        L48:
            r4 = move-exception
            r5 = r1
            r1 = r7
            goto L51
        L4c:
            r4 = move-exception
            r5 = r1
            goto L76
        L4f:
            r4 = move-exception
            r5 = r1
        L51:
            java.lang.String r6 = r4.getMessage()     // Catch: java.lang.Throwable -> L75
            android.util.Log.d(r0, r6, r4)     // Catch: java.lang.Throwable -> L75
            if (r1 == 0) goto L66
            r1.close()     // Catch: java.io.IOException -> L5e
            goto L66
        L5e:
            r4 = move-exception
            java.lang.String r6 = r4.getMessage()
            android.util.Log.d(r0, r6, r4)
        L66:
            if (r5 == 0) goto L74
            r5.close()     // Catch: java.io.IOException -> L6c
            goto L74
        L6c:
            r4 = move-exception
            java.lang.String r5 = r4.getMessage()
            android.util.Log.d(r0, r5, r4)
        L74:
            return
        L75:
            r4 = move-exception
        L76:
            if (r1 == 0) goto L84
            r1.close()     // Catch: java.io.IOException -> L7c
            goto L84
        L7c:
            r6 = move-exception
            java.lang.String r7 = r6.getMessage()
            android.util.Log.d(r0, r7, r6)
        L84:
            if (r5 == 0) goto L92
            r5.close()     // Catch: java.io.IOException -> L8a
            goto L92
        L8a:
            r5 = move-exception
            java.lang.String r6 = r5.getMessage()
            android.util.Log.d(r0, r6, r5)
        L92:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yalantis.ucrop.util.ImageHeaderParser.copyExif(android.content.Context, int, int, android.net.Uri, android.net.Uri):void");
    }

    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:19:0x003a -> B:27:0x0041). Please report as a decompilation issue!!! */
    public static void copyExif(Context context, ExifInterface exifInterface, int i, int i2, Uri uri) throws IOException {
        if (context == null) {
            Log.d(TAG, "context is null");
            return;
        }
        ParcelFileDescriptor parcelFileDescriptorOpenFileDescriptor = null;
        try {
            try {
                try {
                    parcelFileDescriptorOpenFileDescriptor = context.getContentResolver().openFileDescriptor(uri, "rw");
                    copyExifAttributes(exifInterface, new ExifInterface(parcelFileDescriptorOpenFileDescriptor.getFileDescriptor()), i, i2);
                    if (parcelFileDescriptorOpenFileDescriptor != null) {
                        parcelFileDescriptorOpenFileDescriptor.close();
                    }
                } catch (IOException e) {
                    Log.d(TAG, e.getMessage(), e);
                }
            } catch (IOException e2) {
                Log.d(TAG, e2.getMessage());
                if (parcelFileDescriptorOpenFileDescriptor != null) {
                    parcelFileDescriptorOpenFileDescriptor.close();
                }
            }
        } catch (Throwable th) {
            if (parcelFileDescriptorOpenFileDescriptor != null) {
                try {
                    parcelFileDescriptorOpenFileDescriptor.close();
                } catch (IOException e3) {
                    Log.d(TAG, e3.getMessage(), e3);
                }
            }
            throw th;
        }
    }

    private static void copyExifAttributes(ExifInterface exifInterface, ExifInterface exifInterface2, int i, int i2) throws Throwable {
        String[] strArr = {ExifInterface.TAG_F_NUMBER, ExifInterface.TAG_DATETIME, ExifInterface.TAG_DATETIME_DIGITIZED, ExifInterface.TAG_EXPOSURE_TIME, ExifInterface.TAG_FLASH, ExifInterface.TAG_FOCAL_LENGTH, ExifInterface.TAG_GPS_ALTITUDE, ExifInterface.TAG_GPS_ALTITUDE_REF, ExifInterface.TAG_GPS_DATESTAMP, ExifInterface.TAG_GPS_LATITUDE, ExifInterface.TAG_GPS_LATITUDE_REF, ExifInterface.TAG_GPS_LONGITUDE, ExifInterface.TAG_GPS_LONGITUDE_REF, ExifInterface.TAG_GPS_PROCESSING_METHOD, ExifInterface.TAG_GPS_TIMESTAMP, ExifInterface.TAG_PHOTOGRAPHIC_SENSITIVITY, ExifInterface.TAG_MAKE, ExifInterface.TAG_MODEL, ExifInterface.TAG_SUBSEC_TIME, ExifInterface.TAG_SUBSEC_TIME_DIGITIZED, ExifInterface.TAG_SUBSEC_TIME_ORIGINAL, ExifInterface.TAG_WHITE_BALANCE};
        for (int i3 = 0; i3 < 22; i3++) {
            String str = strArr[i3];
            String attribute = exifInterface.getAttribute(str);
            if (!TextUtils.isEmpty(attribute)) {
                exifInterface2.setAttribute(str, attribute);
            }
        }
        exifInterface2.setAttribute(ExifInterface.TAG_IMAGE_WIDTH, String.valueOf(i));
        exifInterface2.setAttribute(ExifInterface.TAG_IMAGE_LENGTH, String.valueOf(i2));
        exifInterface2.setAttribute(ExifInterface.TAG_ORIENTATION, "0");
        exifInterface2.saveAttributes();
    }
}
