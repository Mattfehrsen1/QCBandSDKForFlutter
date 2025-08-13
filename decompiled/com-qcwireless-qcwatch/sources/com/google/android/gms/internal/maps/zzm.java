package com.google.android.gms.internal.maps;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

/* compiled from: com.google.android.gms:play-services-maps@@18.1.0 */
/* loaded from: classes2.dex */
public final class zzm extends zza implements zzo {
    zzm(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.maps.model.internal.IGroundOverlayDelegate");
    }

    @Override // com.google.android.gms.internal.maps.zzo
    public final boolean zzA() throws RemoteException {
        Parcel parcelZzH = zzH(23, zza());
        boolean zZzh = zzc.zzh(parcelZzH);
        parcelZzH.recycle();
        return zZzh;
    }

    @Override // com.google.android.gms.internal.maps.zzo
    public final boolean zzB() throws RemoteException {
        Parcel parcelZzH = zzH(16, zza());
        boolean zZzh = zzc.zzh(parcelZzH);
        parcelZzH.recycle();
        return zZzh;
    }

    @Override // com.google.android.gms.internal.maps.zzo
    public final float zzd() throws RemoteException {
        Parcel parcelZzH = zzH(12, zza());
        float f = parcelZzH.readFloat();
        parcelZzH.recycle();
        return f;
    }

    @Override // com.google.android.gms.internal.maps.zzo
    public final float zze() throws RemoteException {
        Parcel parcelZzH = zzH(8, zza());
        float f = parcelZzH.readFloat();
        parcelZzH.recycle();
        return f;
    }

    @Override // com.google.android.gms.internal.maps.zzo
    public final float zzf() throws RemoteException {
        Parcel parcelZzH = zzH(18, zza());
        float f = parcelZzH.readFloat();
        parcelZzH.recycle();
        return f;
    }

    @Override // com.google.android.gms.internal.maps.zzo
    public final float zzg() throws RemoteException {
        Parcel parcelZzH = zzH(7, zza());
        float f = parcelZzH.readFloat();
        parcelZzH.recycle();
        return f;
    }

    @Override // com.google.android.gms.internal.maps.zzo
    public final float zzh() throws RemoteException {
        Parcel parcelZzH = zzH(14, zza());
        float f = parcelZzH.readFloat();
        parcelZzH.recycle();
        return f;
    }

    @Override // com.google.android.gms.internal.maps.zzo
    public final int zzi() throws RemoteException {
        Parcel parcelZzH = zzH(20, zza());
        int i = parcelZzH.readInt();
        parcelZzH.recycle();
        return i;
    }

    @Override // com.google.android.gms.internal.maps.zzo
    public final IObjectWrapper zzj() throws RemoteException {
        Parcel parcelZzH = zzH(25, zza());
        IObjectWrapper iObjectWrapperAsInterface = IObjectWrapper.Stub.asInterface(parcelZzH.readStrongBinder());
        parcelZzH.recycle();
        return iObjectWrapperAsInterface;
    }

    @Override // com.google.android.gms.internal.maps.zzo
    public final LatLng zzk() throws RemoteException {
        Parcel parcelZzH = zzH(4, zza());
        LatLng latLng = (LatLng) zzc.zza(parcelZzH, LatLng.CREATOR);
        parcelZzH.recycle();
        return latLng;
    }

    @Override // com.google.android.gms.internal.maps.zzo
    public final LatLngBounds zzl() throws RemoteException {
        Parcel parcelZzH = zzH(10, zza());
        LatLngBounds latLngBounds = (LatLngBounds) zzc.zza(parcelZzH, LatLngBounds.CREATOR);
        parcelZzH.recycle();
        return latLngBounds;
    }

    @Override // com.google.android.gms.internal.maps.zzo
    public final String zzm() throws RemoteException {
        Parcel parcelZzH = zzH(2, zza());
        String string = parcelZzH.readString();
        parcelZzH.recycle();
        return string;
    }

    @Override // com.google.android.gms.internal.maps.zzo
    public final void zzn() throws RemoteException {
        zzc(1, zza());
    }

    @Override // com.google.android.gms.internal.maps.zzo
    public final void zzo(float f) throws RemoteException {
        Parcel parcelZza = zza();
        parcelZza.writeFloat(f);
        zzc(11, parcelZza);
    }

    @Override // com.google.android.gms.internal.maps.zzo
    public final void zzp(boolean z) throws RemoteException {
        Parcel parcelZza = zza();
        zzc.zzd(parcelZza, z);
        zzc(22, parcelZza);
    }

    @Override // com.google.android.gms.internal.maps.zzo
    public final void zzq(float f) throws RemoteException {
        Parcel parcelZza = zza();
        parcelZza.writeFloat(f);
        zzc(5, parcelZza);
    }

    @Override // com.google.android.gms.internal.maps.zzo
    public final void zzr(float f, float f2) throws RemoteException {
        Parcel parcelZza = zza();
        parcelZza.writeFloat(f);
        parcelZza.writeFloat(f2);
        zzc(6, parcelZza);
    }

    @Override // com.google.android.gms.internal.maps.zzo
    public final void zzs(IObjectWrapper iObjectWrapper) throws RemoteException {
        Parcel parcelZza = zza();
        zzc.zzg(parcelZza, iObjectWrapper);
        zzc(21, parcelZza);
    }

    @Override // com.google.android.gms.internal.maps.zzo
    public final void zzt(LatLng latLng) throws RemoteException {
        Parcel parcelZza = zza();
        zzc.zze(parcelZza, latLng);
        zzc(3, parcelZza);
    }

    @Override // com.google.android.gms.internal.maps.zzo
    public final void zzu(LatLngBounds latLngBounds) throws RemoteException {
        Parcel parcelZza = zza();
        zzc.zze(parcelZza, latLngBounds);
        zzc(9, parcelZza);
    }

    @Override // com.google.android.gms.internal.maps.zzo
    public final void zzv(IObjectWrapper iObjectWrapper) throws RemoteException {
        Parcel parcelZza = zza();
        zzc.zzg(parcelZza, iObjectWrapper);
        zzc(24, parcelZza);
    }

    @Override // com.google.android.gms.internal.maps.zzo
    public final void zzw(float f) throws RemoteException {
        Parcel parcelZza = zza();
        parcelZza.writeFloat(f);
        zzc(17, parcelZza);
    }

    @Override // com.google.android.gms.internal.maps.zzo
    public final void zzx(boolean z) throws RemoteException {
        Parcel parcelZza = zza();
        zzc.zzd(parcelZza, z);
        zzc(15, parcelZza);
    }

    @Override // com.google.android.gms.internal.maps.zzo
    public final void zzy(float f) throws RemoteException {
        Parcel parcelZza = zza();
        parcelZza.writeFloat(f);
        zzc(13, parcelZza);
    }

    @Override // com.google.android.gms.internal.maps.zzo
    public final boolean zzz(zzo zzoVar) throws RemoteException {
        Parcel parcelZza = zza();
        zzc.zzg(parcelZza, zzoVar);
        Parcel parcelZzH = zzH(19, parcelZza);
        boolean zZzh = zzc.zzh(parcelZzH);
        parcelZzH.recycle();
        return zZzh;
    }
}
