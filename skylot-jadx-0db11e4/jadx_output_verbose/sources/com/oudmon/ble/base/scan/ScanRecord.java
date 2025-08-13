package com.oudmon.ble.base.scan;

import android.os.ParcelUuid;
import android.util.SparseArray;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RequiresApi(api = 21)
/* loaded from: qc_sdk_20250409.aar:classes.jar:com/oudmon/ble/base/scan/ScanRecord.class */
public class ScanRecord {
    private static final String TAG = "ScanRecord";
    private static final int DATA_TYPE_FLAGS = 1;
    private static final int DATA_TYPE_SERVICE_UUIDS_16_BIT_PARTIAL = 2;
    private static final int DATA_TYPE_SERVICE_UUIDS_16_BIT_COMPLETE = 3;
    private static final int DATA_TYPE_SERVICE_UUIDS_32_BIT_PARTIAL = 4;
    private static final int DATA_TYPE_SERVICE_UUIDS_32_BIT_COMPLETE = 5;
    private static final int DATA_TYPE_SERVICE_UUIDS_128_BIT_PARTIAL = 6;
    private static final int DATA_TYPE_SERVICE_UUIDS_128_BIT_COMPLETE = 7;
    private static final int DATA_TYPE_LOCAL_NAME_SHORT = 8;
    private static final int DATA_TYPE_LOCAL_NAME_COMPLETE = 9;
    private static final int DATA_TYPE_TX_POWER_LEVEL = 10;
    private static final int DATA_TYPE_SERVICE_DATA = 22;
    private static final int DATA_TYPE_MANUFACTURER_SPECIFIC_DATA = 255;
    private final int mAdvertiseFlags;

    @Nullable
    private final List<ParcelUuid> mServiceUuids;
    private final SparseArray<byte[]> mManufacturerSpecificData;
    private final Map<ParcelUuid, byte[]> mServiceData;
    private final int mTxPowerLevel;
    private final String mDeviceName;
    private final byte[] mBytes;
    static final char[] hexArray = "0123456789ABCDEF".toCharArray();
    public static final ParcelUuid BASE_UUID = ParcelUuid.fromString("00000000-0000-1000-8000-00805F9B34FB");

    static String toString(SparseArray<byte[]> array) {
        if (array == null) {
            return "null";
        }
        if (array.size() == 0) {
            return "{}";
        }
        StringBuilder buffer = new StringBuilder();
        buffer.append('{');
        for (int i = 0; i < array.size(); i++) {
            buffer.append(array.keyAt(i)).append("=").append(Arrays.toString(array.valueAt(i)));
        }
        buffer.append('}');
        return buffer.toString();
    }

    static <T> String toString(Map<T, byte[]> map) {
        if (map == null) {
            return "null";
        }
        if (map.isEmpty()) {
            return "{}";
        }
        StringBuilder buffer = new StringBuilder();
        buffer.append('{');
        Iterator<Map.Entry<T, byte[]>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<T, byte[]> entry = it.next();
            Object key = entry.getKey();
            buffer.append(key).append("=").append(Arrays.toString(map.get(key)));
            if (it.hasNext()) {
                buffer.append(", ");
            }
        }
        buffer.append('}');
        return buffer.toString();
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

    @Nullable
    public byte[] getManufacturerSpecificData(int manufacturerId) {
        return this.mManufacturerSpecificData.get(manufacturerId);
    }

    public Map<ParcelUuid, byte[]> getServiceData() {
        return this.mServiceData;
    }

    @Nullable
    public byte[] getServiceData(ParcelUuid serviceDataUuid) {
        if (serviceDataUuid == null) {
            return null;
        }
        return this.mServiceData.get(serviceDataUuid);
    }

    public int getTxPowerLevel() {
        return this.mTxPowerLevel;
    }

    @Nullable
    public String getDeviceName() {
        return this.mDeviceName;
    }

    public byte[] getBytes() {
        return this.mBytes;
    }

    private ScanRecord(List<ParcelUuid> serviceUuids, SparseArray<byte[]> manufacturerData, Map<ParcelUuid, byte[]> serviceData, int advertiseFlags, int txPowerLevel, String localName, byte[] bytes) {
        this.mServiceUuids = serviceUuids;
        this.mManufacturerSpecificData = manufacturerData;
        this.mServiceData = serviceData;
        this.mDeviceName = localName;
        this.mAdvertiseFlags = advertiseFlags;
        this.mTxPowerLevel = txPowerLevel;
        this.mBytes = bytes;
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x0187  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static com.oudmon.ble.base.scan.ScanRecord parseFromBytes(byte[] r10) {
        /*
            Method dump skipped, instructions count: 431
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oudmon.ble.base.scan.ScanRecord.parseFromBytes(byte[]):com.oudmon.ble.base.scan.ScanRecord");
    }

    public String toString() {
        return "ScanRecord [mAdvertiseFlags=" + this.mAdvertiseFlags + ", mServiceUuids=" + this.mServiceUuids + ", mManufacturerSpecificData=" + toString(this.mManufacturerSpecificData) + ", mServiceData=" + toString(this.mServiceData) + ", mTxPowerLevel=" + this.mTxPowerLevel + ", mDeviceName=" + this.mDeviceName + "]";
    }

    public static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 255;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[(j * 2) + 1] = hexArray[v & 15];
        }
        return new String(hexChars);
    }

    private static int parseServiceUuid(byte[] scanRecord, int currentPos, int dataLength, int uuidLength, List<ParcelUuid> serviceUuids) {
        while (dataLength > 0) {
            byte[] uuidBytes = extractBytes(scanRecord, currentPos, uuidLength);
            serviceUuids.add(parseUuidFrom(uuidBytes));
            dataLength -= uuidLength;
            currentPos += uuidLength;
        }
        return currentPos;
    }

    private static byte[] extractBytes(byte[] scanRecord, int start, int length) {
        byte[] bytes = new byte[length];
        System.arraycopy(scanRecord, start, bytes, 0, length);
        return bytes;
    }

    public static ParcelUuid parseUuidFrom(byte[] uuidBytes) {
        long shortUuid;
        if (uuidBytes == null) {
            throw new IllegalArgumentException("uuidBytes cannot be null");
        }
        int length = uuidBytes.length;
        if (length != 16 && length != 32 && length != 128) {
            throw new IllegalArgumentException("uuidBytes length invalid - " + length);
        }
        if (length == 128) {
            ByteBuffer buf = ByteBuffer.wrap(uuidBytes).order(ByteOrder.LITTLE_ENDIAN);
            long msb = buf.getLong(8);
            long lsb = buf.getLong(0);
            return new ParcelUuid(new UUID(msb, lsb));
        }
        if (length == 16) {
            long shortUuid2 = uuidBytes[0] & 255;
            shortUuid = shortUuid2 + ((uuidBytes[1] & 255) << 8);
        } else {
            long shortUuid3 = uuidBytes[0] & 255;
            shortUuid = shortUuid3 + ((uuidBytes[1] & 255) << 8) + ((uuidBytes[2] & 255) << 16) + ((uuidBytes[3] & 255) << 24);
        }
        long msb2 = BASE_UUID.getUuid().getMostSignificantBits() + (shortUuid << 32);
        long lsb2 = BASE_UUID.getUuid().getLeastSignificantBits();
        return new ParcelUuid(new UUID(msb2, lsb2));
    }
}
