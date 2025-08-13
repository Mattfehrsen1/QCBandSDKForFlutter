package com.google.android.gms.fitness.data;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

/* compiled from: com.google.android.gms:play-services-fitness@@21.1.0 */
/* loaded from: classes2.dex */
public final class zzt extends com.google.android.gms.internal.fitness.zza implements zzv {
    zzt(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.fitness.data.IDataSourceListener");
    }

    @Override // com.google.android.gms.fitness.data.zzv
    public final void zzd(DataPoint dataPoint) throws RemoteException {
        Parcel parcelZza = zza();
        com.google.android.gms.internal.fitness.zzc.zzc(parcelZza, dataPoint);
        zzl(1, parcelZza);
    }
}
