package com.google.android.gms.fitness.data;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* compiled from: com.google.android.gms:play-services-fitness@@21.1.0 */
/* loaded from: classes2.dex */
public abstract class zzu extends com.google.android.gms.internal.fitness.zzb implements zzv {
    public zzu() {
        super("com.google.android.gms.fitness.data.IDataSourceListener");
    }

    public static zzv zzb(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.fitness.data.IDataSourceListener");
        return iInterfaceQueryLocalInterface instanceof zzv ? (zzv) iInterfaceQueryLocalInterface : new zzt(iBinder);
    }

    @Override // com.google.android.gms.internal.fitness.zzb
    protected final boolean zza(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (i != 1) {
            return false;
        }
        DataPoint dataPoint = (DataPoint) com.google.android.gms.internal.fitness.zzc.zza(parcel, DataPoint.CREATOR);
        com.google.android.gms.internal.fitness.zzc.zzb(parcel);
        zzd(dataPoint);
        return true;
    }
}
