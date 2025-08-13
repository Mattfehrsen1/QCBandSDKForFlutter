package com.realsil.customer.core.bluetooth.scanner;

import android.os.ParcelUuid;
import android.util.SparseArray;
import androidx.annotation.Nullable;
import com.realsil.customer.core.e.b;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/core/bluetooth/scanner/SpecScanRecord.class */
public final class SpecScanRecord {
    public final int a;

    @Nullable
    public final List<ParcelUuid> b;
    public final SparseArray<byte[]> c;
    public final Map<ParcelUuid, byte[]> d;
    public final int e;
    public final String f;
    public final byte[] g;

    public SpecScanRecord(ArrayList arrayList, SparseArray sparseArray, Map map, int i, int i2, String str, byte[] bArr) {
        this.b = arrayList;
        this.c = sparseArray;
        this.d = map;
        this.f = str;
        this.a = i;
        this.e = i2;
        this.g = bArr;
    }

    /* JADX WARN: Removed duplicated region for block: B:65:0x01cc  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x0233  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static com.realsil.customer.core.bluetooth.scanner.SpecScanRecord parseFromBytes(byte[] r11) {
        /*
            Method dump skipped, instructions count: 624
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.realsil.customer.core.bluetooth.scanner.SpecScanRecord.parseFromBytes(byte[]):com.realsil.customer.core.bluetooth.scanner.SpecScanRecord");
    }

    public int getAdvertiseFlags() {
        return this.a;
    }

    public List<ParcelUuid> getServiceUuids() {
        return this.b;
    }

    public SparseArray<byte[]> getManufacturerSpecificData() {
        return this.c;
    }

    public Map<ParcelUuid, byte[]> getServiceData() {
        return this.d;
    }

    public int getTxPowerLevel() {
        return this.e;
    }

    @Nullable
    public String getDeviceName() {
        return this.f;
    }

    public byte[] getBytes() {
        return this.g;
    }

    public String toString() {
        return "ScanRecord [mAdvertiseFlags=" + this.a + ", mServiceUuids=" + this.b + "\n, mManufacturerSpecificData=" + b.a(this.c) + ", mServiceData=" + b.a(this.d) + ", mTxPowerLevel=" + this.e + ", mDeviceName=" + this.f + "]";
    }

    @Nullable
    public byte[] getManufacturerSpecificData(int i) {
        SparseArray<byte[]> sparseArray = this.c;
        if (sparseArray != null) {
            return sparseArray.get(i);
        }
        return null;
    }

    @Nullable
    public byte[] getServiceData(ParcelUuid parcelUuid) {
        if (parcelUuid == null) {
            return null;
        }
        return this.d.get(parcelUuid);
    }
}
