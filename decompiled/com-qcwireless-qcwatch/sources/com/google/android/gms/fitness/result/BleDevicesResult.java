package com.google.android.gms.fitness.result;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.core.app.NotificationCompat;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.fitness.data.BleDevice;
import com.google.android.gms.fitness.data.DataType;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-fitness@@21.1.0 */
@Deprecated
/* loaded from: classes2.dex */
public class BleDevicesResult extends AbstractSafeParcelable implements Result {
    public static final Parcelable.Creator<BleDevicesResult> CREATOR = new zza();
    private final List zza;
    private final Status zzb;

    public BleDevicesResult(List list, Status status) {
        this.zza = Collections.unmodifiableList(list);
        this.zzb = status;
    }

    public static BleDevicesResult zza(Status status) {
        return new BleDevicesResult(Collections.emptyList(), status);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof BleDevicesResult)) {
            return false;
        }
        BleDevicesResult bleDevicesResult = (BleDevicesResult) obj;
        return this.zzb.equals(bleDevicesResult.zzb) && Objects.equal(this.zza, bleDevicesResult.zza);
    }

    public List<BleDevice> getClaimedBleDevices() {
        return this.zza;
    }

    public List<BleDevice> getClaimedBleDevices(DataType dataType) {
        ArrayList arrayList = new ArrayList();
        for (BleDevice bleDevice : this.zza) {
            if (bleDevice.getDataTypes().contains(dataType)) {
                arrayList.add(bleDevice);
            }
        }
        return Collections.unmodifiableList(arrayList);
    }

    @Override // com.google.android.gms.common.api.Result
    public Status getStatus() {
        return this.zzb;
    }

    public int hashCode() {
        return Objects.hashCode(this.zzb, this.zza);
    }

    public String toString() {
        return Objects.toStringHelper(this).add(NotificationCompat.CATEGORY_STATUS, this.zzb).add("bleDevices", this.zza).toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeTypedList(parcel, 1, getClaimedBleDevices(), false);
        SafeParcelWriter.writeParcelable(parcel, 2, getStatus(), i, false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }
}
