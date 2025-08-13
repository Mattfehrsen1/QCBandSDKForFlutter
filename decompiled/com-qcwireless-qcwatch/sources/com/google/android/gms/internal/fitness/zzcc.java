package com.google.android.gms.internal.fitness;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.fitness.request.DataSourcesRequest;

/* compiled from: com.google.android.gms:play-services-fitness@@21.1.0 */
/* loaded from: classes2.dex */
public final class zzcc extends zza implements IInterface {
    zzcc(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.fitness.internal.IGoogleFitSensorsApi");
    }

    public final void zzd(DataSourcesRequest dataSourcesRequest) throws RemoteException {
        Parcel parcelZza = zza();
        zzc.zzc(parcelZza, dataSourcesRequest);
        zzk(1, parcelZza);
    }

    public final void zze(com.google.android.gms.fitness.request.zzak zzakVar) throws RemoteException {
        Parcel parcelZza = zza();
        zzc.zzc(parcelZza, zzakVar);
        zzk(2, parcelZza);
    }

    public final void zzf(com.google.android.gms.fitness.request.zzan zzanVar) throws RemoteException {
        Parcel parcelZza = zza();
        zzc.zzc(parcelZza, zzanVar);
        zzk(3, parcelZza);
    }
}
