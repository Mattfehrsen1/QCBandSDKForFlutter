package com.google.android.gms.internal.maps;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

/* compiled from: com.google.android.gms:play-services-maps@@18.1.0 */
/* loaded from: classes2.dex */
public final class zzs extends zza implements zzu {
    zzs(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.maps.model.internal.IIndoorLevelDelegate");
    }

    @Override // com.google.android.gms.internal.maps.zzu
    public final int zzd() throws RemoteException {
        Parcel parcelZzH = zzH(5, zza());
        int i = parcelZzH.readInt();
        parcelZzH.recycle();
        return i;
    }

    @Override // com.google.android.gms.internal.maps.zzu
    public final String zze() throws RemoteException {
        Parcel parcelZzH = zzH(1, zza());
        String string = parcelZzH.readString();
        parcelZzH.recycle();
        return string;
    }

    @Override // com.google.android.gms.internal.maps.zzu
    public final String zzf() throws RemoteException {
        Parcel parcelZzH = zzH(2, zza());
        String string = parcelZzH.readString();
        parcelZzH.recycle();
        return string;
    }

    @Override // com.google.android.gms.internal.maps.zzu
    public final void zzg() throws RemoteException {
        zzc(3, zza());
    }

    @Override // com.google.android.gms.internal.maps.zzu
    public final boolean zzh(zzu zzuVar) throws RemoteException {
        Parcel parcelZza = zza();
        zzc.zzg(parcelZza, zzuVar);
        Parcel parcelZzH = zzH(4, parcelZza);
        boolean zZzh = zzc.zzh(parcelZzH);
        parcelZzH.recycle();
        return zZzh;
    }
}
