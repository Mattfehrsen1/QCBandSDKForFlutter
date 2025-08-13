package com.google.android.gms.internal.fitness;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.fitness.request.DataDeleteRequest;
import com.google.android.gms.fitness.request.DataReadRequest;
import com.google.android.gms.fitness.request.DataUpdateListenerRegistrationRequest;
import com.google.android.gms.fitness.request.DataUpdateRequest;

/* compiled from: com.google.android.gms:play-services-fitness@@21.1.0 */
/* loaded from: classes2.dex */
public final class zzca extends zza implements IInterface {
    zzca(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.fitness.internal.IGoogleFitHistoryApi");
    }

    public final void zzd(DataDeleteRequest dataDeleteRequest) throws RemoteException {
        Parcel parcelZza = zza();
        zzc.zzc(parcelZza, dataDeleteRequest);
        zzk(3, parcelZza);
    }

    public final void zze(com.google.android.gms.fitness.request.zzk zzkVar) throws RemoteException {
        Parcel parcelZza = zza();
        zzc.zzc(parcelZza, zzkVar);
        zzk(2, parcelZza);
    }

    public final void zzf(com.google.android.gms.fitness.request.zzh zzhVar) throws RemoteException {
        Parcel parcelZza = zza();
        zzc.zzc(parcelZza, zzhVar);
        zzk(7, parcelZza);
    }

    public final void zzg(DataReadRequest dataReadRequest) throws RemoteException {
        Parcel parcelZza = zza();
        zzc.zzc(parcelZza, dataReadRequest);
        zzk(1, parcelZza);
    }

    public final void zzh(DataUpdateListenerRegistrationRequest dataUpdateListenerRegistrationRequest) throws RemoteException {
        Parcel parcelZza = zza();
        zzc.zzc(parcelZza, dataUpdateListenerRegistrationRequest);
        zzk(10, parcelZza);
    }

    public final void zzi(com.google.android.gms.fitness.request.zzs zzsVar) throws RemoteException {
        Parcel parcelZza = zza();
        zzc.zzc(parcelZza, zzsVar);
        zzk(11, parcelZza);
    }

    public final void zzj(DataUpdateRequest dataUpdateRequest) throws RemoteException {
        Parcel parcelZza = zza();
        zzc.zzc(parcelZza, dataUpdateRequest);
        zzk(9, parcelZza);
    }
}
