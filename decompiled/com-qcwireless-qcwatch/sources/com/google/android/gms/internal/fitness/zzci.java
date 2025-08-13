package com.google.android.gms.internal.fitness;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.fitness.result.SessionReadResult;

/* compiled from: com.google.android.gms:play-services-fitness@@21.1.0 */
/* loaded from: classes2.dex */
public abstract class zzci extends zzb implements zzcj {
    public zzci() {
        super("com.google.android.gms.fitness.internal.ISessionReadCallback");
    }

    public static zzcj zzb(IBinder iBinder) {
        IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.fitness.internal.ISessionReadCallback");
        return iInterfaceQueryLocalInterface instanceof zzcj ? (zzcj) iInterfaceQueryLocalInterface : new zzch(iBinder);
    }

    @Override // com.google.android.gms.internal.fitness.zzb
    protected final boolean zza(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (i != 1) {
            return false;
        }
        SessionReadResult sessionReadResult = (SessionReadResult) zzc.zza(parcel, SessionReadResult.CREATOR);
        zzc.zzb(parcel);
        zzd(sessionReadResult);
        return true;
    }
}
