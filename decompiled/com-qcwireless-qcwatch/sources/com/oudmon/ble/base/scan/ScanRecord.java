package com.oudmon.ble.base.scan;

import android.os.ParcelUuid;
import android.util.SparseArray;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/* loaded from: classes3.dex */
public class ScanRecord {
    private static final int DATA_TYPE_FLAGS = 1;
    private static final int DATA_TYPE_LOCAL_NAME_COMPLETE = 9;
    private static final int DATA_TYPE_LOCAL_NAME_SHORT = 8;
    private static final int DATA_TYPE_MANUFACTURER_SPECIFIC_DATA = 255;
    private static final int DATA_TYPE_SERVICE_DATA = 22;
    private static final int DATA_TYPE_SERVICE_UUIDS_128_BIT_COMPLETE = 7;
    private static final int DATA_TYPE_SERVICE_UUIDS_128_BIT_PARTIAL = 6;
    private static final int DATA_TYPE_SERVICE_UUIDS_16_BIT_COMPLETE = 3;
    private static final int DATA_TYPE_SERVICE_UUIDS_16_BIT_PARTIAL = 2;
    private static final int DATA_TYPE_SERVICE_UUIDS_32_BIT_COMPLETE = 5;
    private static final int DATA_TYPE_SERVICE_UUIDS_32_BIT_PARTIAL = 4;
    private static final int DATA_TYPE_TX_POWER_LEVEL = 10;
    private static final String TAG = "ScanRecord";
    private final int mAdvertiseFlags;
    private final byte[] mBytes;
    private final String mDeviceName;
    private final SparseArray<byte[]> mManufacturerSpecificData;
    private final Map<ParcelUuid, byte[]> mServiceData;
    private final List<ParcelUuid> mServiceUuids;
    private final int mTxPowerLevel;
    static final char[] hexArray = "0123456789ABCDEF".toCharArray();
    public static final ParcelUuid BASE_UUID = ParcelUuid.fromString("00000000-0000-1000-8000-00805F9B34FB");

    static String toString(SparseArray<byte[]> sparseArray) {
        if (sparseArray == null) {
            return "null";
        }
        if (sparseArray.size() == 0) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder();
        sb.append('{');
        for (int i = 0; i < sparseArray.size(); i++) {
            sb.append(sparseArray.keyAt(i));
            sb.append("=");
            sb.append(Arrays.toString(sparseArray.valueAt(i)));
        }
        sb.append('}');
        return sb.toString();
    }

    static <T> String toString(Map<T, byte[]> map) {
        if (map == null) {
            return "null";
        }
        if (map.isEmpty()) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder();
        sb.append('{');
        Iterator<Map.Entry<T, byte[]>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            T key = it.next().getKey();
            sb.append(key);
            sb.append("=");
            sb.append(Arrays.toString(map.get(key)));
            if (it.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append('}');
        return sb.toString();
    }

    public int getAdvertiseFlags() {
        return this.mAdvertiseFlags;
    }

    public List<ParcelUuid> getServiceUuids() {
        return this.mServiceUuids;
    }

    public SparseArray<byte[]> getManufacturerSpecificData() {
        return this.mManufacturerSpecificData;
    }

    public byte[] getManufacturerSpecificData(int i) {
        return this.mManufacturerSpecificData.get(i);
    }

    public Map<ParcelUuid, byte[]> getServiceData() {
        return this.mServiceData;
    }

    public byte[] getServiceData(ParcelUuid parcelUuid) {
        if (parcelUuid == null) {
            return null;
        }
        return this.mServiceData.get(parcelUuid);
    }

    public int getTxPowerLevel() {
        return this.mTxPowerLevel;
    }

    public String getDeviceName() {
        return this.mDeviceName;
    }

    public byte[] getBytes() {
        return this.mBytes;
    }

    private ScanRecord(List<ParcelUuid> list, SparseArray<byte[]> sparseArray, Map<ParcelUuid, byte[]> map, int i, int i2, String str, byte[] bArr) {
        this.mServiceUuids = list;
        this.mManufacturerSpecificData = sparseArray;
        this.mServiceData = map;
        this.mDeviceName = str;
        this.mAdvertiseFlags = i;
        this.mTxPowerLevel = i2;
        this.mBytes = bArr;
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x0090  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0092  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static com.oudmon.ble.base.scan.ScanRecord parseFromBytes(byte[] r14) {
        /*
            r0 = 0
            if (r14 != 0) goto L4
            return r0
        L4:
            r1 = 0
            r2 = -1
            java.util.ArrayList r3 = new java.util.ArrayList
            r3.<init>()
            r4 = -2147483648(0xffffffff80000000, float:-0.0)
            android.util.SparseArray r13 = new android.util.SparseArray
            r13.<init>()
            android.util.ArrayMap r8 = new android.util.ArrayMap
            r8.<init>()
            r11 = r0
            r9 = -1
            r10 = -2147483648(0xffffffff80000000, float:-0.0)
        L1b:
            int r2 = r14.length     // Catch: java.lang.Exception -> L9c
            if (r1 >= r2) goto L8a
            int r2 = r1 + 1
            r1 = r14[r1]     // Catch: java.lang.Exception -> L9c
            r4 = 255(0xff, float:3.57E-43)
            r1 = r1 & r4
            if (r1 != 0) goto L28
            goto L8a
        L28:
            int r1 = r1 + (-1)
            int r5 = r2 + 1
            r2 = r14[r2]     // Catch: java.lang.Exception -> L9c
            r2 = r2 & r4
            r6 = 22
            r7 = 16
            if (r2 == r6) goto L75
            if (r2 == r4) goto L5e
            switch(r2) {
                case 1: goto L59;
                case 2: goto L55;
                case 3: goto L55;
                case 4: goto L4f;
                case 5: goto L4f;
                case 6: goto L49;
                case 7: goto L49;
                case 8: goto L3f;
                case 9: goto L3f;
                case 10: goto L3b;
                default: goto L3a;
            }     // Catch: java.lang.Exception -> L9c
        L3a:
            goto L88
        L3b:
            r2 = r14[r5]     // Catch: java.lang.Exception -> L9c
            r10 = r2
            goto L88
        L3f:
            java.lang.String r11 = new java.lang.String     // Catch: java.lang.Exception -> L9c
            byte[] r2 = extractBytes(r14, r5, r1)     // Catch: java.lang.Exception -> L9c
            r11.<init>(r2)     // Catch: java.lang.Exception -> L9c
            goto L88
        L49:
            r2 = 128(0x80, float:1.794E-43)
            parseServiceUuid(r14, r5, r1, r2, r3)     // Catch: java.lang.Exception -> L9c
            goto L88
        L4f:
            r2 = 32
            parseServiceUuid(r14, r5, r1, r2, r3)     // Catch: java.lang.Exception -> L9c
            goto L88
        L55:
            parseServiceUuid(r14, r5, r1, r7, r3)     // Catch: java.lang.Exception -> L9c
            goto L88
        L59:
            r2 = r14[r5]     // Catch: java.lang.Exception -> L9c
            r2 = r2 & r4
            r9 = r2
            goto L88
        L5e:
            int r2 = r5 + 1
            r2 = r14[r2]     // Catch: java.lang.Exception -> L9c
            r2 = r2 & r4
            int r2 = r2 << 8
            r6 = r14[r5]     // Catch: java.lang.Exception -> L9c
            r4 = r4 & r6
            int r2 = r2 + r4
            int r4 = r5 + 2
            int r6 = r1 + (-2)
            byte[] r4 = extractBytes(r14, r4, r6)     // Catch: java.lang.Exception -> L9c
            r13.put(r2, r4)     // Catch: java.lang.Exception -> L9c
            goto L88
        L75:
            byte[] r2 = extractBytes(r14, r5, r7)     // Catch: java.lang.Exception -> L9c
            android.os.ParcelUuid r2 = parseUuidFrom(r2)     // Catch: java.lang.Exception -> L9c
            int r4 = r5 + 16
            int r6 = r1 + (-16)
            byte[] r4 = extractBytes(r14, r4, r6)     // Catch: java.lang.Exception -> L9c
            r8.put(r2, r4)     // Catch: java.lang.Exception -> L9c
        L88:
            int r1 = r1 + r5
            goto L1b
        L8a:
            boolean r1 = r3.isEmpty()     // Catch: java.lang.Exception -> L9c
            if (r1 == 0) goto L92
            r6 = r0
            goto L93
        L92:
            r6 = r3
        L93:
            com.oudmon.ble.base.scan.ScanRecord r0 = new com.oudmon.ble.base.scan.ScanRecord     // Catch: java.lang.Exception -> L9c
            r5 = r0
            r7 = r13
            r12 = r14
            r5.<init>(r6, r7, r8, r9, r10, r11, r12)     // Catch: java.lang.Exception -> L9c
            return r0
        L9c:
            com.oudmon.ble.base.scan.ScanRecord r0 = new com.oudmon.ble.base.scan.ScanRecord
            r6 = 0
            r8 = 0
            r9 = -1
            r10 = -2147483648(0xffffffff80000000, float:-0.0)
            r11 = 0
            r5 = r0
            r7 = r13
            r12 = r14
            r5.<init>(r6, r7, r8, r9, r10, r11, r12)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oudmon.ble.base.scan.ScanRecord.parseFromBytes(byte[]):com.oudmon.ble.base.scan.ScanRecord");
    }

    public String toString() {
        return "ScanRecord [mAdvertiseFlags=" + this.mAdvertiseFlags + ", mServiceUuids=" + this.mServiceUuids + ", mManufacturerSpecificData=" + toString(this.mManufacturerSpecificData) + ", mServiceData=" + toString(this.mServiceData) + ", mTxPowerLevel=" + this.mTxPowerLevel + ", mDeviceName=" + this.mDeviceName + "]";
    }

    public static String bytesToHex(byte[] bArr) {
        char[] cArr = new char[bArr.length * 2];
        for (int i = 0; i < bArr.length; i++) {
            int i2 = bArr[i] & 255;
            int i3 = i * 2;
            char[] cArr2 = hexArray;
            cArr[i3] = cArr2[i2 >>> 4];
            cArr[i3 + 1] = cArr2[i2 & 15];
        }
        return new String(cArr);
    }

    private static int parseServiceUuid(byte[] bArr, int i, int i2, int i3, List<ParcelUuid> list) {
        while (i2 > 0) {
            list.add(parseUuidFrom(extractBytes(bArr, i, i3)));
            i2 -= i3;
            i += i3;
        }
        return i;
    }

    private static byte[] extractBytes(byte[] bArr, int i, int i2) {
        byte[] bArr2 = new byte[i2];
        System.arraycopy(bArr, i, bArr2, 0, i2);
        return bArr2;
    }

    public static ParcelUuid parseUuidFrom(byte[] bArr) {
        long j;
        if (bArr == null) {
            throw new IllegalArgumentException("uuidBytes cannot be null");
        }
        int length = bArr.length;
        if (length != 16 && length != 32 && length != 128) {
            throw new IllegalArgumentException("uuidBytes length invalid - " + length);
        }
        if (length == 128) {
            ByteBuffer byteBufferOrder = ByteBuffer.wrap(bArr).order(ByteOrder.LITTLE_ENDIAN);
            return new ParcelUuid(new UUID(byteBufferOrder.getLong(8), byteBufferOrder.getLong(0)));
        }
        if (length == 16) {
            j = (bArr[0] & 255) + ((bArr[1] & 255) << 8);
        } else {
            j = (bArr[0] & 255) + ((bArr[1] & 255) << 8) + ((bArr[2] & 255) << 16) + ((bArr[3] & 255) << 24);
        }
        ParcelUuid parcelUuid = BASE_UUID;
        return new ParcelUuid(new UUID(parcelUuid.getUuid().getMostSignificantBits() + (j << 32), parcelUuid.getUuid().getLeastSignificantBits()));
    }
}
