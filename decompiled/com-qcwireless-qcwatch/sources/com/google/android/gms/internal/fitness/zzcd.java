package com.google.android.gms.internal.fitness;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.fitness.request.SessionInsertRequest;
import com.google.android.gms.fitness.request.SessionReadRequest;

/* compiled from: com.google.android.gms:play-services-fitness@@21.1.0 */
/* loaded from: classes2.dex */
public final class zzcd extends zza implements IInterface {
    zzcd(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.fitness.internal.IGoogleFitSessionsApi");
    }

    public final void zzd(SessionInsertRequest sessionInsertRequest) throws RemoteException {
        Parcel parcelZza = zza();
        zzc.zzc(parcelZza, sessionInsertRequest);
        zzk(3, parcelZza);
    }

    public final void zze(SessionReadRequest sessionReadRequest) throws RemoteException {
        Parcel parcelZza = zza();
        zzc.zzc(parcelZza, sessionReadRequest);
        zzk(4, parcelZza);
    }

    public final void zzf(com.google.android.gms.fitness.request.zzar zzarVar) throws RemoteException {
        Parcel parcelZza = zza();
        zzc.zzc(parcelZza, zzarVar);
        zzk(5, parcelZza);
    }

    public final void zzg(com.google.android.gms.fitness.request.zzat zzatVar) throws RemoteException {
        Parcel parcelZza = zza();
        zzc.zzc(parcelZza, zzatVar);
        zzk(1, parcelZza);
    }

    public final void zzh(com.google.android.gms.fitness.request.zzav zzavVar) throws RemoteException {
        Parcel parcelZza = zza();
        zzc.zzc(parcelZza, zzavVar);
        zzk(2, parcelZza);
    }

    public final void zzi(com.google.android.gms.fitness.request.zzax zzaxVar) throws RemoteException {
        Parcel parcelZza = zza();
        zzc.zzc(parcelZza, zzaxVar);
        zzk(6, parcelZza);
    }
}
