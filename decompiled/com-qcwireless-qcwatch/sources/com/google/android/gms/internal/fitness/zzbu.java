package com.google.android.gms.internal.fitness;

import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.gms.fitness.result.GoalsResult;

/* compiled from: com.google.android.gms:play-services-fitness@@21.1.0 */
/* loaded from: classes2.dex */
public final class zzbu extends zza implements zzbw {
    zzbu(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.fitness.internal.IGoalsReadCallback");
    }

    @Override // com.google.android.gms.internal.fitness.zzbw
    public final void zzd(GoalsResult goalsResult) throws RemoteException {
        throw null;
    }
}
