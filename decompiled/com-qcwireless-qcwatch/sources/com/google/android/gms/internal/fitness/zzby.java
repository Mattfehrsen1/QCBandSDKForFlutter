package com.google.android.gms.internal.fitness;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.fitness.request.DataTypeCreateRequest;

/* compiled from: com.google.android.gms:play-services-fitness@@21.1.0 */
/* loaded from: classes2.dex */
public final class zzby extends zza implements IInterface {
    zzby(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.fitness.internal.IGoogleFitConfigApi");
    }

    public final void zzd(DataTypeCreateRequest dataTypeCreateRequest) throws RemoteException {
        Parcel parcelZza = zza();
        zzc.zzc(parcelZza, dataTypeCreateRequest);
        zzk(1, parcelZza);
    }

    public final void zze(com.google.android.gms.fitness.request.zzv zzvVar) throws RemoteException {
        Parcel parcelZza = zza();
        zzc.zzc(parcelZza, zzvVar);
        zzk(22, parcelZza);
    }

    public final void zzf(com.google.android.gms.fitness.request.zzp zzpVar) throws RemoteException {
        Parcel parcelZza = zza();
        zzc.zzc(parcelZza, zzpVar);
        zzk(2, parcelZza);
    }
}
