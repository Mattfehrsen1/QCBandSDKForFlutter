package com.google.android.gms.internal.fitness;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* compiled from: com.google.android.gms:play-services-fitness@@21.1.0 */
/* loaded from: classes2.dex */
public final class zzcb extends zza implements IInterface {
    zzcb(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.fitness.internal.IGoogleFitRecordingApi");
    }

    public final void zzd(com.google.android.gms.fitness.request.zzae zzaeVar) throws RemoteException {
        Parcel parcelZza = zza();
        zzc.zzc(parcelZza, zzaeVar);
        zzk(3, parcelZza);
    }

    public final void zze(com.google.android.gms.fitness.request.zzbd zzbdVar) throws RemoteException {
        Parcel parcelZza = zza();
        zzc.zzc(parcelZza, zzbdVar);
        zzk(1, parcelZza);
    }

    public final void zzf(com.google.android.gms.fitness.request.zzbh zzbhVar) throws RemoteException {
        Parcel parcelZza = zza();
        zzc.zzc(parcelZza, zzbhVar);
        zzk(2, parcelZza);
    }
}
