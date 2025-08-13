package com.google.android.gms.internal.fitness;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.fitness.result.DataReadResult;

/* compiled from: com.google.android.gms:play-services-fitness@@21.1.0 */
/* loaded from: classes2.dex */
public abstract class zzbm extends zzb implements zzbn {
    public zzbm() {
        super("com.google.android.gms.fitness.internal.IDataReadCallback");
    }

    public static zzbn zzb(IBinder iBinder) {
        IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.fitness.internal.IDataReadCallback");
        return iInterfaceQueryLocalInterface instanceof zzbn ? (zzbn) iInterfaceQueryLocalInterface : new zzbl(iBinder);
    }

    @Override // com.google.android.gms.internal.fitness.zzb
    protected final boolean zza(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (i != 1) {
            return false;
        }
        DataReadResult dataReadResult = (DataReadResult) zzc.zza(parcel, DataReadResult.CREATOR);
        zzc.zzb(parcel);
        zzd(dataReadResult);
        return true;
    }
}
