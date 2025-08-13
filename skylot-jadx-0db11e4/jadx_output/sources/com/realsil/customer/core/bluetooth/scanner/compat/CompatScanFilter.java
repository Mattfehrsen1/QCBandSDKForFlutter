package com.realsil.customer.core.bluetooth.scanner.compat;

import android.bluetooth.BluetoothAdapter;
import android.os.Parcel;
import android.os.ParcelUuid;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.realsil.customer.core.bluetooth.utils.BluetoothHelper;
import com.realsil.customer.core.d.a;
import com.realsil.customer.core.utility.StringUtils;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/core/bluetooth/scanner/compat/CompatScanFilter.class */
public final class CompatScanFilter implements Parcelable {
    public static final CompatScanFilter EMPTY = new Builder().build();

    @NonNull
    public static final Parcelable.Creator<CompatScanFilter> CREATOR = new Parcelable.Creator<CompatScanFilter>() { // from class: com.realsil.customer.core.bluetooth.scanner.compat.CompatScanFilter.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CompatScanFilter[] newArray(int i) {
            return new CompatScanFilter[i];
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CompatScanFilter createFromParcel(Parcel parcel) {
            Builder builder = new Builder();
            if (parcel.readInt() == 1) {
                builder.setDeviceName(parcel.readString());
            }
            String string = null;
            if (parcel.readInt() == 1) {
                string = parcel.readString();
            }
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
    };

    @Nullable
    public final String a;

    @Nullable
    public final String b;
    public final int c;

    @Nullable
    public final byte[] d;

    @Nullable
    public final ParcelUuid e;

    @Nullable
    public final ParcelUuid f;

    @Nullable
    public final ParcelUuid g;

    @Nullable
    public final ParcelUuid h;

    @Nullable
    public final ParcelUuid i;

    @Nullable
    public final byte[] j;

    @Nullable
    public final byte[] k;
    public final int l;

    @Nullable
    public final byte[] m;

    @Nullable
    public final byte[] n;

    public CompatScanFilter(String str, String str2, ParcelUuid parcelUuid, ParcelUuid parcelUuid2, ParcelUuid parcelUuid3, ParcelUuid parcelUuid4, ParcelUuid parcelUuid5, byte[] bArr, byte[] bArr2, int i, byte[] bArr3, byte[] bArr4, int i2, @Nullable byte[] bArr5) {
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
            byte b = bArr2[i2];
            if ((b & bArr3[i2]) != (b & bArr[i2])) {
                return false;
            }
        }
        return true;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
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
            parcel.writeInt(this.d == null ? 0 : 1);
            byte[] bArr5 = this.d;
            if (bArr5 != null) {
                parcel.writeByteArray(bArr5);
            }
        }
    }

    @Nullable
    public String getDeviceName() {
        return this.a;
    }

    @Nullable
    public ParcelUuid getServiceUuid() {
        return this.e;
    }

    @Nullable
    public ParcelUuid getServiceUuidMask() {
        return this.f;
    }

    @Nullable
    public ParcelUuid getServiceSolicitationUuid() {
        return this.g;
    }

    @Nullable
    public ParcelUuid getServiceSolicitationUuidMask() {
        return this.h;
    }

    @Nullable
    public String getDeviceAddress() {
        return this.b;
    }

    public int getAddressType() {
        return this.c;
    }

    @Nullable
    public byte[] getIrk() {
        return this.d;
    }

    @Nullable
    public byte[] getServiceData() {
        return this.j;
    }

    @Nullable
    public byte[] getServiceDataMask() {
        return this.k;
    }

    @Nullable
    public ParcelUuid getServiceDataUuid() {
        return this.i;
    }

    public int getManufacturerId() {
        return this.l;
    }

    @Nullable
    public byte[] getManufacturerData() {
        return this.m;
    }

    @Nullable
    public byte[] getManufacturerDataMask() {
        return this.n;
    }

    /* JADX WARN: Removed duplicated region for block: B:65:0x0109 A[RETURN] */
    @androidx.annotation.RequiresApi(api = 21)
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean matches(android.bluetooth.le.ScanResult r6) {
        /*
            Method dump skipped, instructions count: 335
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.realsil.customer.core.bluetooth.scanner.compat.CompatScanFilter.matches(android.bluetooth.le.ScanResult):boolean");
    }

    public String toString() {
        return "BluetoothLeScanFilter [mDeviceName=" + this.a + ", mDeviceAddress=" + BluetoothHelper.formatAddress(this.b, true) + ", mUuid=" + this.e + ", mUuidMask=" + this.f + ", mServiceSolicitationUuid=" + this.g + ", mServiceSolicitationUuidMask=" + this.h + ", mServiceDataUuid=" + Objects.toString(this.i) + ", mServiceData=" + Arrays.toString(this.j) + ", mServiceDataMask=" + Arrays.toString(this.k) + ", mManufacturerId=" + this.l + ", mManufacturerData=" + Arrays.toString(this.m) + ", mManufacturerDataMask=" + Arrays.toString(this.n) + "]";
    }

    public int hashCode() {
        return Objects.hash(this.a, this.b, Integer.valueOf(this.l), Integer.valueOf(Arrays.hashCode(this.m)), Integer.valueOf(Arrays.hashCode(this.n)), this.i, Integer.valueOf(Arrays.hashCode(this.j)), Integer.valueOf(Arrays.hashCode(this.k)), this.e, this.f, this.g, this.h);
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

    public boolean isAllFieldsEmpty() {
        return EMPTY.equals(this);
    }

    /* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/core/bluetooth/scanner/compat/CompatScanFilter$Builder.class */
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

        public Builder setDeviceName(String str) {
            this.a = str;
            return this;
        }

        public Builder setDeviceAddress(String str) {
            if (str != null) {
                return setDeviceAddress(str, 0);
            }
            this.b = str;
            return this;
        }

        public Builder setServiceUuid(ParcelUuid parcelUuid) {
            this.e = parcelUuid;
            this.f = null;
            return this;
        }

        @NonNull
        public Builder setServiceSolicitationUuid(@Nullable ParcelUuid parcelUuid) {
            this.g = parcelUuid;
            if (parcelUuid == null) {
                this.h = null;
            }
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

        public Builder setManufacturerData(int i, byte[] bArr) {
            if (bArr != null && i < 0) {
                throw new IllegalArgumentException("invalid manufacture id");
            }
            this.l = i;
            this.m = bArr;
            this.n = null;
            return this;
        }

        public CompatScanFilter build() {
            return new CompatScanFilter(this.a, this.b, this.e, this.f, this.g, this.h, this.i, this.j, this.k, this.l, this.m, this.n, this.c, this.d);
        }

        @NonNull
        public final Builder a(@NonNull String str, int i, @Nullable byte[] bArr) {
            if (!BluetoothAdapter.checkBluetoothAddress(str)) {
                throw new IllegalArgumentException("invalid device address " + str);
            }
            if (i < 0 || i > 1) {
                throw new IllegalArgumentException("'addressType' is invalid!");
            }
            if (i == 1 && bArr != null && !StringUtils.isEmpty(str) && !com.realsil.customer.core.a.a.a(str)) {
                throw new IllegalArgumentException("Invalid combination: IRK requires either a PUBLIC or RANDOM (STATIC) Address");
            }
            this.b = str;
            this.c = i;
            this.d = bArr;
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

        @NonNull
        public Builder setServiceSolicitationUuid(@Nullable ParcelUuid parcelUuid, @Nullable ParcelUuid parcelUuid2) {
            if (parcelUuid2 != null && parcelUuid == null) {
                throw new IllegalArgumentException("SolicitationUuid is null while SolicitationUuidMask is not null!");
            }
            this.g = parcelUuid;
            this.h = parcelUuid2;
            return this;
        }

        @NonNull
        public Builder setDeviceAddress(@NonNull String str, int i) {
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
