package com.realsil.sdk.bbpro.core;

import android.os.ParcelUuid;
import com.realsil.sdk.core.bluetooth.utils.BluetoothUuid;
import com.realsil.sdk.core.logger.ZLogger;
import com.realsil.sdk.core.utility.DataConverter;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.UUID;

/* loaded from: classes3.dex */
public class Utils {
    public static ParcelUuid byteArrayToUuid(byte[] bArr) {
        if (bArr == null || bArr.length < 16) {
            return null;
        }
        ByteBuffer byteBufferWrap = ByteBuffer.wrap(bArr);
        byteBufferWrap.order(ByteOrder.BIG_ENDIAN);
        return new ParcelUuid(new UUID(byteBufferWrap.getLong(0), byteBufferWrap.getLong(8)));
    }

    public static ParcelUuid[] byteArrayToUuids(byte[] bArr) {
        int length = bArr.length / 16;
        ParcelUuid[] parcelUuidArr = new ParcelUuid[length];
        ByteBuffer byteBufferWrap = ByteBuffer.wrap(bArr);
        byteBufferWrap.order(ByteOrder.BIG_ENDIAN);
        int i = 0;
        for (int i2 = 0; i2 < length; i2++) {
            parcelUuidArr[i2] = new ParcelUuid(new UUID(byteBufferWrap.getLong(i), byteBufferWrap.getLong(i + 8)));
            i += 16;
        }
        return parcelUuidArr;
    }

    public static boolean checkUuid(ParcelUuid[] parcelUuidArr, UUID uuid, boolean z) {
        ParcelUuid parcelUuidByteArrayToUuid;
        if (uuid != null && parcelUuidArr != null && parcelUuidArr.length > 0) {
            for (ParcelUuid parcelUuid : parcelUuidArr) {
                if (parcelUuid != null) {
                    if (uuid.equals(parcelUuid.getUuid())) {
                        return true;
                    }
                    if (!BluetoothUuid.is16BitUuid(parcelUuid) && !BluetoothUuid.is32BitUuid(parcelUuid) && z && (parcelUuidByteArrayToUuid = byteArrayToUuid(DataConverter.reverse(uuidToByteArray(parcelUuid)))) != null && uuid.equals(parcelUuidByteArrayToUuid.getUuid())) {
                        ZLogger.v("match reverse uuid:" + parcelUuid.toString());
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static ParcelUuid getUuid(ParcelUuid[] parcelUuidArr, UUID uuid, boolean z) {
        ParcelUuid parcelUuidByteArrayToUuid;
        if (uuid != null && parcelUuidArr != null && parcelUuidArr.length > 0) {
            for (ParcelUuid parcelUuid : parcelUuidArr) {
                if (parcelUuid != null) {
                    if (uuid.equals(parcelUuid.getUuid())) {
                        return parcelUuid;
                    }
                    if (!BluetoothUuid.is16BitUuid(parcelUuid) && !BluetoothUuid.is32BitUuid(parcelUuid) && z && (parcelUuidByteArrayToUuid = byteArrayToUuid(DataConverter.reverse(uuidToByteArray(parcelUuid)))) != null && uuid.equals(parcelUuidByteArrayToUuid.getUuid())) {
                        ZLogger.v("match reverse uuid:" + parcelUuidByteArrayToUuid.toString());
                        return parcelUuidByteArrayToUuid;
                    }
                }
            }
        }
        return null;
    }

    public static byte[] uuidToByteArray(ParcelUuid parcelUuid) {
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(16);
        byteBufferAllocate.order(ByteOrder.BIG_ENDIAN);
        UUID uuid = parcelUuid.getUuid();
        long mostSignificantBits = uuid.getMostSignificantBits();
        long leastSignificantBits = uuid.getLeastSignificantBits();
        byteBufferAllocate.putLong(mostSignificantBits);
        byteBufferAllocate.putLong(8, leastSignificantBits);
        return byteBufferAllocate.array();
    }
}
