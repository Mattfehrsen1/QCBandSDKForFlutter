package com.google.android.gms.internal.fitness;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.fitness.request.GoalsReadRequest;

/* compiled from: com.google.android.gms:play-services-fitness@@21.1.0 */
/* loaded from: classes2.dex */
public final class zzbz extends zza implements IInterface {
    zzbz(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.fitness.internal.IGoogleFitGoalsApi");
    }

    public final void zzd(GoalsReadRequest goalsReadRequest) throws RemoteException {
        Parcel parcelZza = zza();
        zzc.zzc(parcelZza, goalsReadRequest);
        zzk(1, parcelZza);
    }
}
