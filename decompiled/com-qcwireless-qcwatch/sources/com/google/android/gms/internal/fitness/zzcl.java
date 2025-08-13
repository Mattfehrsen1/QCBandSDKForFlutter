package com.google.android.gms.internal.fitness;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.fitness.result.SessionStopResult;

/* compiled from: com.google.android.gms:play-services-fitness@@21.1.0 */
/* loaded from: classes2.dex */
public abstract class zzcl extends zzb implements zzcm {
    public zzcl() {
        super("com.google.android.gms.fitness.internal.ISessionStopCallback");
    }

    public static zzcm zzb(IBinder iBinder) {
        IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.fitness.internal.ISessionStopCallback");
        return iInterfaceQueryLocalInterface instanceof zzcm ? (zzcm) iInterfaceQueryLocalInterface : new zzck(iBinder);
    }

    @Override // com.google.android.gms.internal.fitness.zzb
    protected final boolean zza(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (i != 1) {
            return false;
        }
        SessionStopResult sessionStopResult = (SessionStopResult) zzc.zza(parcel, SessionStopResult.CREATOR);
        zzc.zzb(parcel);
        zzd(sessionStopResult);
        return true;
    }
}
