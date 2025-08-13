package com.realsil.sdk.core.f;

import android.bluetooth.le.ScanRecord;
import android.os.Build;
import android.os.ParcelUuid;
import android.util.SparseArray;
import com.realsil.sdk.core.utility.DataConverter;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.koin.core.instance.DefinitionInstance;

/* loaded from: classes3.dex */
public final class b {
    public static String a(ScanRecord scanRecord) {
        StringBuilder sb = new StringBuilder();
        sb.append("scanRecord{");
        int i = Build.VERSION.SDK_INT;
        if (i >= 21) {
            Locale locale = Locale.US;
            sb.append(String.format(locale, "\n\tmAdvertiseFlags=0x%04X", Integer.valueOf(scanRecord.getAdvertiseFlags())));
            sb.append(String.format(locale, "\n\tmManufacturerSpecificData=%s", a(scanRecord.getManufacturerSpecificData())));
            sb.append(String.format(locale, "\n\tserviceData=%s", a(scanRecord.getServiceData())));
            sb.append(String.format(locale, "\n\tserviceUuids=%s", a(scanRecord.getServiceUuids())));
            sb.append(String.format(locale, "\n\tmDeviceName=%s", scanRecord.getDeviceName()));
        }
        if (i >= 29) {
            sb.append(String.format(Locale.US, "\n\tserviceSolicitationUuids=%s", a(scanRecord.getServiceSolicitationUuids())));
        }
        sb.append("\n}");
        return sb.toString();
    }

    public static String a(List<ParcelUuid> list) {
        if (list == null || list.size() <= 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("supported features (UUIDs)");
        for (ParcelUuid parcelUuid : list) {
            StringBuilder sbA = com.realsil.sdk.core.a.a.a(DefinitionInstance.ERROR_SEPARATOR);
            sbA.append(parcelUuid.toString());
            sb.append(sbA.toString());
        }
        return sb.toString();
    }

    public static String a(SparseArray<byte[]> sparseArray) {
        if (sparseArray == null) {
            return "null";
        }
        if (sparseArray.size() == 0) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder();
        sb.append('{');
        for (int i = 0; i < sparseArray.size(); i++) {
            byte[] bArrValueAt = sparseArray.valueAt(i);
            int length = bArrValueAt != null ? bArrValueAt.length : 0;
            sb.append(sparseArray.keyAt(i));
            sb.append("=(");
            sb.append(length);
            sb.append(")");
            sb.append(DataConverter.bytes2HexWithSeparate(bArrValueAt));
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
        StringBuilder sb = new StringBuilder();
        sb.append('{');
        Iterator<Map.Entry<T, byte[]>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            T key = it.next().getKey();
            byte[] bArr = map.get(key);
            int length = bArr != null ? bArr.length : 0;
            sb.append(key);
            sb.append("=(");
            sb.append(length);
            sb.append(")");
            sb.append(DataConverter.bytes2HexWithSeparate(bArr));
            if (it.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append('}');
        return sb.toString();
    }
}
