package com.google.android.gms.internal.fitness;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.fitness.result.DailyTotalResult;

/* compiled from: com.google.android.gms:play-services-fitness@@21.1.0 */
/* loaded from: classes2.dex */
public abstract class zzbj extends zzb implements zzbk {
    public zzbj() {
        super("com.google.android.gms.fitness.internal.IDailyTotalCallback");
    }

    public static zzbk zzb(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.fitness.internal.IDailyTotalCallback");
        return iInterfaceQueryLocalInterface instanceof zzbk ? (zzbk) iInterfaceQueryLocalInterface : new zzbi(iBinder);
    }

    @Override // com.google.android.gms.internal.fitness.zzb
    protected final boolean zza(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (i != 1) {
            return false;
        }
        DailyTotalResult dailyTotalResult = (DailyTotalResult) zzc.zza(parcel, DailyTotalResult.CREATOR);
        zzc.zzb(parcel);
        zzd(dailyTotalResult);
        return true;
    }
}
