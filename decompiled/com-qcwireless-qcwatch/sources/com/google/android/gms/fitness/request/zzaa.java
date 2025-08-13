package com.google.android.gms.fitness.request;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.fitness.data.BleDevice;

/* compiled from: com.google.android.gms:play-services-fitness@@21.1.0 */
/* loaded from: classes2.dex */
public abstract class zzaa extends com.google.android.gms.internal.fitness.zzb implements zzab {
    public zzaa() {
        super("com.google.android.gms.fitness.request.IBleScanCallback");
    }

    @Override // com.google.android.gms.internal.fitness.zzb
    protected final boolean zza(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (i == 1) {
            BleDevice bleDevice = (BleDevice) com.google.android.gms.internal.fitness.zzc.zza(parcel, BleDevice.CREATOR);
            com.google.android.gms.internal.fitness.zzc.zzb(parcel);
            zzb(bleDevice);
        } else {
            if (i != 2) {
                return false;
            }
            zzc();
        }
        return true;
    }
}
