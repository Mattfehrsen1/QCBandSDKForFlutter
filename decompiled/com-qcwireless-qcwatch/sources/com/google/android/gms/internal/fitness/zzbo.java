package com.google.android.gms.internal.fitness;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.fitness.result.DataSourcesResult;

/* compiled from: com.google.android.gms:play-services-fitness@@21.1.0 */
/* loaded from: classes2.dex */
public final class zzbo extends zza implements zzbq {
    zzbo(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.fitness.internal.IDataSourcesCallback");
    }

    @Override // com.google.android.gms.internal.fitness.zzbq
    public final void zzb(DataSourcesResult dataSourcesResult) throws RemoteException {
        Parcel parcelZza = zza();
        zzc.zzc(parcelZza, dataSourcesResult);
        zzl(1, parcelZza);
    }
}
