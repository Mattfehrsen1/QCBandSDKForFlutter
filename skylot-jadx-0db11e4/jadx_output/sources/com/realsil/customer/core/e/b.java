package com.realsil.customer.core.e;

import android.bluetooth.le.ScanRecord;
import android.os.Build;
import android.os.ParcelUuid;
import android.util.SparseArray;
import com.realsil.customer.core.utility.DataConverter;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/core/e/b.class */
public final class b {
    public static String a(ScanRecord scanRecord) {
        String string;
        String string2;
        StringBuilder sb = new StringBuilder("scanRecord{");
        int i = Build.VERSION.SDK_INT;
        if (i >= 21) {
            Locale locale = Locale.US;
            sb.append(String.format(locale, "\n\tmAdvertiseFlags=0x%04X", Integer.valueOf(scanRecord.getAdvertiseFlags())));
            sb.append(String.format(locale, "\n\tmManufacturerSpecificData=%s", a(scanRecord.getManufacturerSpecificData())));
            sb.append(String.format(locale, "\n\tserviceData=%s", a(scanRecord.getServiceData())));
            Object[] objArr = new Object[1];
            List<ParcelUuid> serviceUuids = scanRecord.getServiceUuids();
            if (serviceUuids == null || serviceUuids.size() <= 0) {
                string2 = "";
            } else {
                StringBuilder sb2 = new StringBuilder("supported features (UUIDs)");
                Iterator<ParcelUuid> it = serviceUuids.iterator();
                while (it.hasNext()) {
                    sb2.append("\n\t" + it.next().toString());
                }
                string2 = sb2.toString();
            }
            objArr[0] = string2;
            sb.append(String.format(locale, "\n\tserviceUuids=%s", objArr));
            sb.append(String.format(locale, "\n\tmDeviceName=%s", scanRecord.getDeviceName()));
        }
        if (i >= 29) {
            Locale locale2 = Locale.US;
            Object[] objArr2 = new Object[1];
            List<ParcelUuid> serviceSolicitationUuids = scanRecord.getServiceSolicitationUuids();
            if (serviceSolicitationUuids == null || serviceSolicitationUuids.size() <= 0) {
                string = "";
            } else {
                StringBuilder sb3 = new StringBuilder("supported features (UUIDs)");
                Iterator<ParcelUuid> it2 = serviceSolicitationUuids.iterator();
                while (it2.hasNext()) {
                    sb3.append("\n\t" + it2.next().toString());
                }
                string = sb3.toString();
            }
            objArr2[0] = string;
            sb.append(String.format(locale2, "\n\tserviceSolicitationUuids=%s", objArr2));
        }
        sb.append("\n}");
        return sb.toString();
    }

    public static String a(SparseArray<byte[]> sparseArray) {
        if (sparseArray == null) {
            return "null";
        }
        if (sparseArray.size() == 0) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder("{");
        for (int i = 0; i < sparseArray.size(); i++) {
            byte[] bArrValueAt = sparseArray.valueAt(i);
            int length = 0;
            if (bArrValueAt != null) {
                length = bArrValueAt.length;
            }
            sb.append(sparseArray.keyAt(i)).append("=(").append(length).append(")").append(DataConverter.bytes2HexWithSeparate(bArrValueAt));
        }
        sb.append('}');
        return sb.toString();
    }

    public static <T> String a(Map<T, byte[]> map) {
        if (map == null) {
            return "null";
        }
        if (map.isEmpty()) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder("{");
        Iterator<Map.Entry<T, byte[]>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            T key = it.next().getKey();
            byte[] bArr = map.get(key);
            int length = 0;
            if (bArr != null) {
                length = bArr.length;
            }
            sb.append(key).append("=(").append(length).append(")").append(DataConverter.bytes2HexWithSeparate(bArr));
            if (it.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append('}');
        return sb.toString();
    }
}
