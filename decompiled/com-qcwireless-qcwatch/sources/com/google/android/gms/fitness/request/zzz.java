package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.gms.fitness.data.BleDevice;

/* compiled from: com.google.android.gms:play-services-fitness@@21.1.0 */
/* loaded from: classes2.dex */
public final class zzz extends com.google.android.gms.internal.fitness.zza implements zzab {
    zzz(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.fitness.request.IBleScanCallback");
    }

    @Override // com.google.android.gms.fitness.request.zzab
    public final void zzb(BleDevice bleDevice) throws RemoteException {
        throw null;
    }

    @Override // com.google.android.gms.fitness.request.zzab
    public final void zzc() throws RemoteException {
        throw null;
    }
}
