package com.realsil.sdk.core.bluetooth.scanner;

import android.os.ParcelUuid;
import android.util.SparseArray;
import com.realsil.sdk.core.a.a;
import com.realsil.sdk.core.bluetooth.utils.BluetoothUuid;
import com.realsil.sdk.core.f.b;
import java.util.List;
import java.util.Map;

/* loaded from: classes3.dex */
public final class SpecScanRecord {
    public final int a;
    public final List<ParcelUuid> b;
    public final SparseArray<byte[]> c;
    public final Map<ParcelUuid, byte[]> d;
    public final int e;
    public final String f;
    public final byte[] g;

    public SpecScanRecord(List<ParcelUuid> list, SparseArray<byte[]> sparseArray, Map<ParcelUuid, byte[]> map, int i, int i2, String str, byte[] bArr) {
        this.b = list;
        this.c = sparseArray;
        this.d = map;
        this.f = str;
        this.a = i;
        this.e = i2;
        this.g = bArr;
    }

    public static int a(byte[] bArr, int i, int i2, int i3, List<ParcelUuid> list) {
        while (i2 > 0) {
            list.add(BluetoothUuid.parseUuidFrom(a(bArr, i, i3)));
            i2 -= i3;
            i += i3;
        }
        return i;
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x007d  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x00a2  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00a4  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static com.realsil.sdk.core.bluetooth.scanner.SpecScanRecord parseFromBytes(byte[] r16) {
        /*
            Method dump skipped, instructions count: 240
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.realsil.sdk.core.bluetooth.scanner.SpecScanRecord.parseFromBytes(byte[]):com.realsil.sdk.core.bluetooth.scanner.SpecScanRecord");
    }

    public int getAdvertiseFlags() {
        return this.a;
    }

    public byte[] getBytes() {
        return this.g;
    }

    public String getDeviceName() {
        return this.f;
    }

    public SparseArray<byte[]> getManufacturerSpecificData() {
        return this.c;
    }

    public Map<ParcelUuid, byte[]> getServiceData() {
        return this.d;
    }

    public List<ParcelUuid> getServiceUuids() {
        return this.b;
    }

    public int getTxPowerLevel() {
        return this.e;
    }

    public String toString() {
        StringBuilder sbA = a.a("ScanRecord [mAdvertiseFlags=");
        sbA.append(this.a);
        sbA.append(", mServiceUuids=");
        sbA.append(this.b);
        sbA.append("\n, mManufacturerSpecificData=");
        sbA.append(b.a(this.c));
        sbA.append(", mServiceData=");
        sbA.append(b.a(this.d));
        sbA.append(", mTxPowerLevel=");
        sbA.append(this.e);
        sbA.append(", mDeviceName=");
        sbA.append(this.f);
        sbA.append("]");
        return sbA.toString();
    }

    public byte[] getManufacturerSpecificData(int i) {
        SparseArray<byte[]> sparseArray = this.c;
        if (sparseArray != null) {
            return sparseArray.get(i);
        }
        return null;
    }

    public byte[] getServiceData(ParcelUuid parcelUuid) {
        if (parcelUuid == null) {
            return null;
        }
        return this.d.get(parcelUuid);
    }

    public static byte[] a(byte[] bArr, int i, int i2) {
        byte[] bArr2 = new byte[i2];
        System.arraycopy(bArr, i, bArr2, 0, i2);
        return bArr2;
    }
}
