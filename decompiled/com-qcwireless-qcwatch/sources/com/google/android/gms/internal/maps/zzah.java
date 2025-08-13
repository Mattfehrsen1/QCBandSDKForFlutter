package com.google.android.gms.internal.maps;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

/* compiled from: com.google.android.gms:play-services-maps@@18.1.0 */
/* loaded from: classes2.dex */
public final class zzah extends zza implements zzaj {
    zzah(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.maps.model.internal.ITileOverlayDelegate");
    }

    @Override // com.google.android.gms.internal.maps.zzaj
    public final float zzd() throws RemoteException {
        Parcel parcelZzH = zzH(13, zza());
        float f = parcelZzH.readFloat();
        parcelZzH.recycle();
        return f;
    }

    @Override // com.google.android.gms.internal.maps.zzaj
    public final float zze() throws RemoteException {
        Parcel parcelZzH = zzH(5, zza());
        float f = parcelZzH.readFloat();
        parcelZzH.recycle();
        return f;
    }

    @Override // com.google.android.gms.internal.maps.zzaj
    public final int zzf() throws RemoteException {
        Parcel parcelZzH = zzH(9, zza());
        int i = parcelZzH.readInt();
        parcelZzH.recycle();
        return i;
    }

    @Override // com.google.android.gms.internal.maps.zzaj
    public final String zzg() throws RemoteException {
        Parcel parcelZzH = zzH(3, zza());
        String string = parcelZzH.readString();
        parcelZzH.recycle();
        return string;
    }

    @Override // com.google.android.gms.internal.maps.zzaj
    public final void zzh() throws RemoteException {
        zzc(2, zza());
    }

    @Override // com.google.android.gms.internal.maps.zzaj
    public final void zzi() throws RemoteException {
        zzc(1, zza());
    }

    @Override // com.google.android.gms.internal.maps.zzaj
    public final void zzj(boolean z) throws RemoteException {
        Parcel parcelZza = zza();
        zzc.zzd(parcelZza, z);
        zzc(10, parcelZza);
    }

    @Override // com.google.android.gms.internal.maps.zzaj
    public final void zzk(float f) throws RemoteException {
        Parcel parcelZza = zza();
        parcelZza.writeFloat(f);
        zzc(12, parcelZza);
    }

    @Override // com.google.android.gms.internal.maps.zzaj
    public final void zzl(boolean z) throws RemoteException {
        Parcel parcelZza = zza();
        zzc.zzd(parcelZza, z);
        zzc(6, parcelZza);
    }

    @Override // com.google.android.gms.internal.maps.zzaj
    public final void zzm(float f) throws RemoteException {
        Parcel parcelZza = zza();
        parcelZza.writeFloat(f);
        zzc(4, parcelZza);
    }

    @Override // com.google.android.gms.internal.maps.zzaj
    public final boolean zzn(zzaj zzajVar) throws RemoteException {
        Parcel parcelZza = zza();
        zzc.zzg(parcelZza, zzajVar);
        Parcel parcelZzH = zzH(8, parcelZza);
        boolean zZzh = zzc.zzh(parcelZzH);
        parcelZzH.recycle();
        return zZzh;
    }

    @Override // com.google.android.gms.internal.maps.zzaj
    public final boolean zzo() throws RemoteException {
        Parcel parcelZzH = zzH(11, zza());
        boolean zZzh = zzc.zzh(parcelZzH);
        parcelZzH.recycle();
        return zZzh;
    }

    @Override // com.google.android.gms.internal.maps.zzaj
    public final boolean zzp() throws RemoteException {
        Parcel parcelZzH = zzH(7, zza());
        boolean zZzh = zzc.zzh(parcelZzH);
        parcelZzH.recycle();
        return zZzh;
    }
}
