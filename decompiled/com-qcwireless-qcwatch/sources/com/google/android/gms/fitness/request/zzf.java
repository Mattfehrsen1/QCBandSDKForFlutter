package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.fitness.data.BleDevice;
import com.google.android.gms.internal.fitness.zzco;
import com.google.android.gms.internal.fitness.zzcp;

/* compiled from: com.google.android.gms:play-services-fitness@@21.1.0 */
/* loaded from: classes2.dex */
public final class zzf extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzf> CREATOR = new zzg();
    private final String zza;
    private final BleDevice zzb;
    private final zzcp zzc;

    zzf(String str, BleDevice bleDevice, IBinder iBinder) {
        this.zza = str;
        this.zzb = bleDevice;
        this.zzc = zzco.zzb(iBinder);
    }

    public final String toString() {
        return String.format("ClaimBleDeviceRequest{%s %s}", this.zza, this.zzb);
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, this.zza, false);
        SafeParcelWriter.writeParcelable(parcel, 2, this.zzb, i, false);
        zzcp zzcpVar = this.zzc;
        SafeParcelWriter.writeIBinder(parcel, 3, zzcpVar == null ? null : zzcpVar.asBinder(), false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    public zzf(String str, BleDevice bleDevice, zzcp zzcpVar) {
        this.zza = str;
        this.zzb = bleDevice;
        this.zzc = zzcpVar;
    }
}
