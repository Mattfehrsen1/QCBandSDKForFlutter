package com.google.android.gms.internal.fitness;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.fitness.result.GoalsResult;

/* compiled from: com.google.android.gms:play-services-fitness@@21.1.0 */
/* loaded from: classes2.dex */
public abstract class zzbv extends zzb implements zzbw {
    public zzbv() {
        super("com.google.android.gms.fitness.internal.IGoalsReadCallback");
    }

    public static zzbw zzb(IBinder iBinder) {
        IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.fitness.internal.IGoalsReadCallback");
        return iInterfaceQueryLocalInterface instanceof zzbw ? (zzbw) iInterfaceQueryLocalInterface : new zzbu(iBinder);
    }

    @Override // com.google.android.gms.internal.fitness.zzb
    protected final boolean zza(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (i != 1) {
            return false;
        }
        GoalsResult goalsResult = (GoalsResult) zzc.zza(parcel, GoalsResult.CREATOR);
        zzc.zzb(parcel);
        zzd(goalsResult);
        return true;
    }
}
