package com.realsil.sdk.core.bluetooth.scanner.compat;

import android.bluetooth.BluetoothAdapter;
import android.os.Parcel;
import android.os.ParcelUuid;
import android.os.Parcelable;
import com.realsil.sdk.core.bluetooth.utils.BluetoothHelper;
import com.realsil.sdk.core.e.a;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/* loaded from: classes3.dex */
public final class CompatScanFilter implements Parcelable {
    public final String a;
    public final String b;
    public final int c;
    public final byte[] d;
    public final ParcelUuid e;
    public final ParcelUuid f;
    public final ParcelUuid g;
    public final ParcelUuid h;
    public final ParcelUuid i;
    public final byte[] j;
    public final byte[] k;
    public final int l;
    public final byte[] m;
    public final byte[] n;
    public static final CompatScanFilter EMPTY = new Builder().build();
    public static final Parcelable.Creator<CompatScanFilter> CREATOR = new Parcelable.Creator<CompatScanFilter>() { // from class: com.realsil.sdk.core.bluetooth.scanner.compat.CompatScanFilter.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CompatScanFilter createFromParcel(Parcel parcel) {
            Builder builder = new Builder();
            if (parcel.readInt() == 1) {
                builder.setDeviceName(parcel.readString());
            }
            String string = parcel.readInt() == 1 ? parcel.readString() : null;
            if (parcel.readInt() == 1) {
                ParcelUuid parcelUuid = (ParcelUuid) parcel.readParcelable(ParcelUuid.class.getClassLoader());
                builder.setServiceUuid(parcelUuid);
                if (parcel.readInt() == 1) {
                    builder.setServiceUuid(parcelUuid, (ParcelUuid) parcel.readParcelable(ParcelUuid.class.getClassLoader()));
                }
            }
            if (parcel.readInt() == 1) {
                ParcelUuid parcelUuid2 = (ParcelUuid) parcel.readParcelable(ParcelUuid.class.getClassLoader());
                builder.setServiceSolicitationUuid(parcelUuid2);
                if (parcel.readInt() == 1) {
                    builder.setServiceSolicitationUuid(parcelUuid2, (ParcelUuid) parcel.readParcelable(ParcelUuid.class.getClassLoader()));
                }
            }
            if (parcel.readInt() == 1) {
                ParcelUuid parcelUuid3 = (ParcelUuid) parcel.readParcelable(ParcelUuid.class.getClassLoader());
                if (parcel.readInt() == 1) {
                    byte[] bArr = new byte[parcel.readInt()];
                    parcel.readByteArray(bArr);
                    if (parcel.readInt() == 0) {
                        builder.setServiceData(parcelUuid3, bArr);
                    } else {
                        byte[] bArr2 = new byte[parcel.readInt()];
                        parcel.readByteArray(bArr2);
                        builder.setServiceData(parcelUuid3, bArr, bArr2);
                    }
                }
            }
            int i = parcel.readInt();
            if (parcel.readInt() == 1) {
                byte[] bArr3 = new byte[parcel.readInt()];
                parcel.readByteArray(bArr3);
                if (parcel.readInt() == 0) {
                    builder.setManufacturerData(i, bArr3);
                } else {
                    byte[] bArr4 = new byte[parcel.readInt()];
                    parcel.readByteArray(bArr4);
                    builder.setManufacturerData(i, bArr3, bArr4);
                }
            }
            if (string != null) {
                int i2 = parcel.readInt();
                if (parcel.readInt() == 1) {
                    byte[] bArr5 = new byte[16];
                    parcel.readByteArray(bArr5);
                    builder.a(string, i2, bArr5);
                } else {
                    builder.setDeviceAddress(string, i2);
                }
            }
            return builder.build();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CompatScanFilter[] newArray(int i) {
            return new CompatScanFilter[i];
        }
    };

    public CompatScanFilter(String str, String str2, ParcelUuid parcelUuid, ParcelUuid parcelUuid2, ParcelUuid parcelUuid3, ParcelUuid parcelUuid4, ParcelUuid parcelUuid5, byte[] bArr, byte[] bArr2, int i, byte[] bArr3, byte[] bArr4, int i2, byte[] bArr5) {
        this.a = str;
        this.e = parcelUuid;
        this.f = parcelUuid2;
        this.g = parcelUuid3;
        this.h = parcelUuid4;
        this.b = str2;
        this.i = parcelUuid5;
        this.j = bArr;
        this.k = bArr2;
        this.l = i;
        this.m = bArr3;
        this.n = bArr4;
        this.c = i2;
        this.d = bArr5;
    }

    public static boolean matchesPartialData(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        if (bArr3 == null || bArr3.length < bArr.length) {
            return false;
        }
        if (bArr2 == null) {
            for (int i = 0; i < bArr.length; i++) {
                if (bArr3[i] != bArr[i]) {
                    return false;
                }
            }
            return true;
        }
        for (int i2 = 0; i2 < bArr.length; i2++) {
            if ((bArr2[i2] & bArr3[i2]) != (bArr2[i2] & bArr[i2])) {
                return false;
            }
        }
        return true;
    }

    public static boolean matchesServiceUuids(ParcelUuid parcelUuid, ParcelUuid parcelUuid2, List<ParcelUuid> list) {
        if (parcelUuid == null) {
            return true;
        }
        if (list == null) {
            return false;
        }
        for (ParcelUuid parcelUuid3 : list) {
            if (a.a(parcelUuid3.getUuid(), parcelUuid.getUuid(), parcelUuid2 == null ? null : parcelUuid2.getUuid())) {
                return true;
            }
        }
        return false;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || CompatScanFilter.class != obj.getClass()) {
            return false;
        }
        CompatScanFilter compatScanFilter = (CompatScanFilter) obj;
        return Objects.equals(this.a, compatScanFilter.a) && Objects.equals(this.b, compatScanFilter.b) && this.l == compatScanFilter.l && Objects.deepEquals(this.m, compatScanFilter.m) && Objects.deepEquals(this.n, compatScanFilter.n) && Objects.equals(this.i, compatScanFilter.i) && Objects.deepEquals(this.j, compatScanFilter.j) && Objects.deepEquals(this.k, compatScanFilter.k) && Objects.equals(this.e, compatScanFilter.e) && Objects.equals(this.f, compatScanFilter.f) && Objects.equals(this.g, compatScanFilter.g) && Objects.equals(this.h, compatScanFilter.h);
    }

    public int getAddressType() {
        return this.c;
    }

    public String getDeviceAddress() {
        return this.b;
    }

    public String getDeviceName() {
        return this.a;
    }

    public byte[] getIrk() {
        return this.d;
    }

    public byte[] getManufacturerData() {
        return this.m;
    }

    public byte[] getManufacturerDataMask() {
        return this.n;
    }

    public int getManufacturerId() {
        return this.l;
    }

    public byte[] getServiceData() {
        return this.j;
    }

    public byte[] getServiceDataMask() {
        return this.k;
    }

    public ParcelUuid getServiceDataUuid() {
        return this.i;
    }

    public ParcelUuid getServiceSolicitationUuid() {
        return this.g;
    }

    public ParcelUuid getServiceSolicitationUuidMask() {
        return this.h;
    }

    public ParcelUuid getServiceUuid() {
        return this.e;
    }

    public ParcelUuid getServiceUuidMask() {
        return this.f;
    }

    public int hashCode() {
        return Objects.hash(this.a, this.b, Integer.valueOf(this.l), Integer.valueOf(Arrays.hashCode(this.m)), Integer.valueOf(Arrays.hashCode(this.n)), this.i, Integer.valueOf(Arrays.hashCode(this.j)), Integer.valueOf(Arrays.hashCode(this.k)), this.e, this.f, this.g, this.h);
    }

    public boolean isAllFieldsEmpty() {
        return EMPTY.equals(this);
    }

    /* JADX WARN: Removed duplicated region for block: B:55:0x0096 A[RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean matches(android.bluetooth.le.ScanResult r9) {
        /*
            r8 = this;
            r0 = 0
            if (r9 != 0) goto L4
            return r0
        L4:
            android.bluetooth.BluetoothDevice r1 = r9.getDevice()
            java.lang.String r2 = r8.b
            if (r2 == 0) goto L19
            if (r1 == 0) goto L18
            java.lang.String r1 = r1.getAddress()
            boolean r1 = r2.equals(r1)
            if (r1 != 0) goto L19
        L18:
            return r0
        L19:
            android.bluetooth.le.ScanRecord r9 = r9.getScanRecord()
            if (r9 != 0) goto L34
            java.lang.String r1 = r8.a
            if (r1 != 0) goto L33
            android.os.ParcelUuid r1 = r8.e
            if (r1 != 0) goto L33
            byte[] r1 = r8.m
            if (r1 != 0) goto L33
            byte[] r1 = r8.j
            if (r1 != 0) goto L33
            android.os.ParcelUuid r1 = r8.g
            if (r1 == 0) goto L34
        L33:
            return r0
        L34:
            java.lang.String r1 = r8.a
            if (r1 == 0) goto L43
            java.lang.String r2 = r9.getDeviceName()
            boolean r1 = r1.equals(r2)
            if (r1 != 0) goto L43
            return r0
        L43:
            android.os.ParcelUuid r1 = r8.e
            if (r1 == 0) goto L54
            android.os.ParcelUuid r2 = r8.f
            java.util.List r3 = r9.getServiceUuids()
            boolean r1 = matchesServiceUuids(r1, r2, r3)
            if (r1 != 0) goto L54
            return r0
        L54:
            int r1 = android.os.Build.VERSION.SDK_INT
            r2 = 29
            r3 = 1
            if (r1 < r2) goto L97
            android.os.ParcelUuid r1 = r8.g
            if (r1 == 0) goto L97
            android.os.ParcelUuid r2 = r8.h
            java.util.List r4 = r9.getServiceSolicitationUuids()
            if (r1 != 0) goto L68
            goto L91
        L68:
            if (r4 != 0) goto L6b
            goto L93
        L6b:
            java.util.Iterator r4 = r4.iterator()
        L6f:
            boolean r5 = r4.hasNext()
            if (r5 == 0) goto L93
            java.lang.Object r5 = r4.next()
            android.os.ParcelUuid r5 = (android.os.ParcelUuid) r5
            if (r2 != 0) goto L7f
            r6 = 0
            goto L83
        L7f:
            java.util.UUID r6 = r2.getUuid()
        L83:
            java.util.UUID r7 = r1.getUuid()
            java.util.UUID r5 = r5.getUuid()
            boolean r5 = com.realsil.sdk.core.e.a.a(r5, r7, r6)
            if (r5 == 0) goto L6f
        L91:
            r1 = 1
            goto L94
        L93:
            r1 = 0
        L94:
            if (r1 != 0) goto L97
            return r0
        L97:
            android.os.ParcelUuid r1 = r8.i
            if (r1 == 0) goto Lac
            if (r9 == 0) goto Lac
            byte[] r2 = r8.j
            byte[] r4 = r8.k
            byte[] r1 = r9.getServiceData(r1)
            boolean r1 = matchesPartialData(r2, r4, r1)
            if (r1 != 0) goto Lac
            return r0
        Lac:
            int r1 = r8.l
            if (r1 < 0) goto Lc1
            if (r9 == 0) goto Lc1
            byte[] r2 = r8.m
            byte[] r4 = r8.n
            byte[] r9 = r9.getManufacturerSpecificData(r1)
            boolean r9 = matchesPartialData(r2, r4, r9)
            if (r9 != 0) goto Lc1
            return r0
        Lc1:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.realsil.sdk.core.bluetooth.scanner.compat.CompatScanFilter.matches(android.bluetooth.le.ScanResult):boolean");
    }

    public String toString() {
        StringBuilder sbA = com.realsil.sdk.core.a.a.a("BluetoothLeScanFilter [mDeviceName=");
        sbA.append(this.a);
        sbA.append(", mDeviceAddress=");
        sbA.append(BluetoothHelper.formatAddress(this.b, true));
        sbA.append(", mUuid=");
        sbA.append(this.e);
        sbA.append(", mUuidMask=");
        sbA.append(this.f);
        sbA.append(", mServiceSolicitationUuid=");
        sbA.append(this.g);
        sbA.append(", mServiceSolicitationUuidMask=");
        sbA.append(this.h);
        sbA.append(", mServiceDataUuid=");
        sbA.append(Objects.toString(this.i));
        sbA.append(", mServiceData=");
        sbA.append(Arrays.toString(this.j));
        sbA.append(", mServiceDataMask=");
        sbA.append(Arrays.toString(this.k));
        sbA.append(", mManufacturerId=");
        sbA.append(this.l);
        sbA.append(", mManufacturerData=");
        sbA.append(Arrays.toString(this.m));
        sbA.append(", mManufacturerDataMask=");
        sbA.append(Arrays.toString(this.n));
        sbA.append("]");
        return sbA.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.a == null ? 0 : 1);
        String str = this.a;
        if (str != null) {
            parcel.writeString(str);
        }
        parcel.writeInt(this.b == null ? 0 : 1);
        String str2 = this.b;
        if (str2 != null) {
            parcel.writeString(str2);
        }
        parcel.writeInt(this.e == null ? 0 : 1);
        ParcelUuid parcelUuid = this.e;
        if (parcelUuid != null) {
            parcel.writeParcelable(parcelUuid, i);
            parcel.writeInt(this.f == null ? 0 : 1);
            ParcelUuid parcelUuid2 = this.f;
            if (parcelUuid2 != null) {
                parcel.writeParcelable(parcelUuid2, i);
            }
        }
        parcel.writeInt(this.g == null ? 0 : 1);
        ParcelUuid parcelUuid3 = this.g;
        if (parcelUuid3 != null) {
            parcel.writeParcelable(parcelUuid3, i);
            parcel.writeInt(this.h == null ? 0 : 1);
            ParcelUuid parcelUuid4 = this.h;
            if (parcelUuid4 != null) {
                parcel.writeParcelable(parcelUuid4, i);
            }
        }
        parcel.writeInt(this.i == null ? 0 : 1);
        ParcelUuid parcelUuid5 = this.i;
        if (parcelUuid5 != null) {
            parcel.writeParcelable(parcelUuid5, i);
            parcel.writeInt(this.j == null ? 0 : 1);
            byte[] bArr = this.j;
            if (bArr != null) {
                parcel.writeInt(bArr.length);
                parcel.writeByteArray(this.j);
                parcel.writeInt(this.k == null ? 0 : 1);
                byte[] bArr2 = this.k;
                if (bArr2 != null) {
                    parcel.writeInt(bArr2.length);
                    parcel.writeByteArray(this.k);
                }
            }
        }
        parcel.writeInt(this.l);
        parcel.writeInt(this.m == null ? 0 : 1);
        byte[] bArr3 = this.m;
        if (bArr3 != null) {
            parcel.writeInt(bArr3.length);
            parcel.writeByteArray(this.m);
            parcel.writeInt(this.n == null ? 0 : 1);
            byte[] bArr4 = this.n;
            if (bArr4 != null) {
                parcel.writeInt(bArr4.length);
                parcel.writeByteArray(this.n);
            }
        }
        if (this.b != null) {
            parcel.writeInt(this.c);
            parcel.writeInt(this.d != null ? 1 : 0);
            byte[] bArr5 = this.d;
            if (bArr5 != null) {
                parcel.writeByteArray(bArr5);
            }
        }
    }

    public static final class Builder {
        public static final int LEN_IRK_OCTETS = 16;
        public String a;
        public String b;
        public byte[] d;
        public ParcelUuid e;
        public ParcelUuid f;
        public ParcelUuid g;
        public ParcelUuid h;
        public ParcelUuid i;
        public byte[] j;
        public byte[] k;
        public byte[] m;
        public byte[] n;
        public int c = 0;
        public int l = -1;

        public final Builder a(String str, int i, byte[] bArr) {
            if (!BluetoothAdapter.checkBluetoothAddress(str)) {
                throw new IllegalArgumentException("invalid device address " + str);
            }
            if (i < 0 || i > 1) {
                throw new IllegalArgumentException("'addressType' is invalid!");
            }
            if (i == 1 && bArr != null && !com.realsil.sdk.core.b.a.a(str)) {
                throw new IllegalArgumentException("Invalid combination: IRK requires either a PUBLIC or RANDOM (STATIC) Address");
            }
            this.b = str;
            this.c = i;
            this.d = bArr;
            return this;
        }

        public CompatScanFilter build() {
            return new CompatScanFilter(this.a, this.b, this.e, this.f, this.g, this.h, this.i, this.j, this.k, this.l, this.m, this.n, this.c, this.d);
        }

        public Builder setDeviceAddress(String str) {
            if (str != null) {
                return setDeviceAddress(str, 0);
            }
            this.b = str;
            return this;
        }

        public Builder setDeviceName(String str) {
            this.a = str;
            return this;
        }

        public Builder setManufacturerData(int i, byte[] bArr) {
            if (bArr != null && i < 0) {
                throw new IllegalArgumentException("invalid manufacture id");
            }
            this.l = i;
            this.m = bArr;
            this.n = null;
            return this;
        }

        public Builder setServiceData(ParcelUuid parcelUuid, byte[] bArr) {
            if (parcelUuid == null) {
                throw new IllegalArgumentException("serviceDataUuid is null");
            }
            this.i = parcelUuid;
            this.j = bArr;
            this.k = null;
            return this;
        }

        public Builder setServiceSolicitationUuid(ParcelUuid parcelUuid) {
            this.g = parcelUuid;
            if (parcelUuid == null) {
                this.h = null;
            }
            return this;
        }

        public Builder setServiceUuid(ParcelUuid parcelUuid) {
            this.e = parcelUuid;
            this.f = null;
            return this;
        }

        public Builder setServiceUuid(ParcelUuid parcelUuid, ParcelUuid parcelUuid2) {
            if (this.f != null && this.e == null) {
                throw new IllegalArgumentException("uuid is null while uuidMask is not null!");
            }
            this.e = parcelUuid;
            this.f = parcelUuid2;
            return this;
        }

        public Builder setServiceSolicitationUuid(ParcelUuid parcelUuid, ParcelUuid parcelUuid2) {
            if (parcelUuid2 != null && parcelUuid == null) {
                throw new IllegalArgumentException("SolicitationUuid is null while SolicitationUuidMask is not null!");
            }
            this.g = parcelUuid;
            this.h = parcelUuid2;
            return this;
        }

        public Builder setDeviceAddress(String str, int i) {
            return a(str, i, null);
        }

        public Builder setServiceData(ParcelUuid parcelUuid, byte[] bArr, byte[] bArr2) {
            if (parcelUuid != null) {
                byte[] bArr3 = this.k;
                if (bArr3 != null) {
                    byte[] bArr4 = this.j;
                    if (bArr4 != null) {
                        if (bArr4.length != bArr3.length) {
                            throw new IllegalArgumentException("size mismatch for service data and service data mask");
                        }
                    } else {
                        throw new IllegalArgumentException("serviceData is null while serviceDataMask is not null");
                    }
                }
                this.i = parcelUuid;
                this.j = bArr;
                this.k = bArr2;
                return this;
            }
            throw new IllegalArgumentException("serviceDataUuid is null");
        }

        public Builder setManufacturerData(int i, byte[] bArr, byte[] bArr2) {
            if (bArr != null && i < 0) {
                throw new IllegalArgumentException("invalid manufacture id");
            }
            byte[] bArr3 = this.n;
            if (bArr3 != null) {
                byte[] bArr4 = this.m;
                if (bArr4 != null) {
                    if (bArr4.length != bArr3.length) {
                        throw new IllegalArgumentException("size mismatch for manufacturerData and manufacturerDataMask");
                    }
                } else {
                    throw new IllegalArgumentException("manufacturerData is null while manufacturerDataMask is not null");
                }
            }
            this.l = i;
            this.m = bArr;
            this.n = bArr2;
            return this;
        }
    }
}
