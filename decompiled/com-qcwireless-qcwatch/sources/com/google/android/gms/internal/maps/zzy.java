package com.google.android.gms.internal.maps;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.maps.model.LatLng;

/* compiled from: com.google.android.gms:play-services-maps@@18.1.0 */
/* loaded from: classes2.dex */
public final class zzy extends zza implements zzaa {
    zzy(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.maps.model.internal.IMarkerDelegate");
    }

    @Override // com.google.android.gms.internal.maps.zzaa
    public final void zzA(float f) throws RemoteException {
        Parcel parcelZza = zza();
        parcelZza.writeFloat(f);
        zzc(27, parcelZza);
    }

    @Override // com.google.android.gms.internal.maps.zzaa
    public final void zzB() throws RemoteException {
        zzc(11, zza());
    }

    @Override // com.google.android.gms.internal.maps.zzaa
    public final boolean zzC(zzaa zzaaVar) throws RemoteException {
        Parcel parcelZza = zza();
        zzc.zzg(parcelZza, zzaaVar);
        Parcel parcelZzH = zzH(16, parcelZza);
        boolean zZzh = zzc.zzh(parcelZzH);
        parcelZzH.recycle();
        return zZzh;
    }

    @Override // com.google.android.gms.internal.maps.zzaa
    public final boolean zzD() throws RemoteException {
        Parcel parcelZzH = zzH(10, zza());
        boolean zZzh = zzc.zzh(parcelZzH);
        parcelZzH.recycle();
        return zZzh;
    }

    @Override // com.google.android.gms.internal.maps.zzaa
    public final boolean zzE() throws RemoteException {
        Parcel parcelZzH = zzH(21, zza());
        boolean zZzh = zzc.zzh(parcelZzH);
        parcelZzH.recycle();
        return zZzh;
    }

    @Override // com.google.android.gms.internal.maps.zzaa
    public final boolean zzF() throws RemoteException {
        Parcel parcelZzH = zzH(13, zza());
        boolean zZzh = zzc.zzh(parcelZzH);
        parcelZzH.recycle();
        return zZzh;
    }

    @Override // com.google.android.gms.internal.maps.zzaa
    public final boolean zzG() throws RemoteException {
        Parcel parcelZzH = zzH(15, zza());
        boolean zZzh = zzc.zzh(parcelZzH);
        parcelZzH.recycle();
        return zZzh;
    }

    @Override // com.google.android.gms.internal.maps.zzaa
    public final float zzd() throws RemoteException {
        Parcel parcelZzH = zzH(26, zza());
        float f = parcelZzH.readFloat();
        parcelZzH.recycle();
        return f;
    }

    @Override // com.google.android.gms.internal.maps.zzaa
    public final float zze() throws RemoteException {
        Parcel parcelZzH = zzH(23, zza());
        float f = parcelZzH.readFloat();
        parcelZzH.recycle();
        return f;
    }

    @Override // com.google.android.gms.internal.maps.zzaa
    public final float zzf() throws RemoteException {
        Parcel parcelZzH = zzH(28, zza());
        float f = parcelZzH.readFloat();
        parcelZzH.recycle();
        return f;
    }

    @Override // com.google.android.gms.internal.maps.zzaa
    public final int zzg() throws RemoteException {
        Parcel parcelZzH = zzH(17, zza());
        int i = parcelZzH.readInt();
        parcelZzH.recycle();
        return i;
    }

    @Override // com.google.android.gms.internal.maps.zzaa
    public final IObjectWrapper zzh() throws RemoteException {
        Parcel parcelZzH = zzH(30, zza());
        IObjectWrapper iObjectWrapperAsInterface = IObjectWrapper.Stub.asInterface(parcelZzH.readStrongBinder());
        parcelZzH.recycle();
        return iObjectWrapperAsInterface;
    }

    @Override // com.google.android.gms.internal.maps.zzaa
    public final LatLng zzi() throws RemoteException {
        Parcel parcelZzH = zzH(4, zza());
        LatLng latLng = (LatLng) zzc.zza(parcelZzH, LatLng.CREATOR);
        parcelZzH.recycle();
        return latLng;
    }

    @Override // com.google.android.gms.internal.maps.zzaa
    public final String zzj() throws RemoteException {
        Parcel parcelZzH = zzH(2, zza());
        String string = parcelZzH.readString();
        parcelZzH.recycle();
        return string;
    }

    @Override // com.google.android.gms.internal.maps.zzaa
    public final String zzk() throws RemoteException {
        Parcel parcelZzH = zzH(8, zza());
        String string = parcelZzH.readString();
        parcelZzH.recycle();
        return string;
    }

    @Override // com.google.android.gms.internal.maps.zzaa
    public final String zzl() throws RemoteException {
        Parcel parcelZzH = zzH(6, zza());
        String string = parcelZzH.readString();
        parcelZzH.recycle();
        return string;
    }

    @Override // com.google.android.gms.internal.maps.zzaa
    public final void zzm() throws RemoteException {
        zzc(12, zza());
    }

    @Override // com.google.android.gms.internal.maps.zzaa
    public final void zzn() throws RemoteException {
        zzc(1, zza());
    }

    @Override // com.google.android.gms.internal.maps.zzaa
    public final void zzo(float f) throws RemoteException {
        Parcel parcelZza = zza();
        parcelZza.writeFloat(f);
        zzc(25, parcelZza);
    }

    @Override // com.google.android.gms.internal.maps.zzaa
    public final void zzp(float f, float f2) throws RemoteException {
        Parcel parcelZza = zza();
        parcelZza.writeFloat(f);
        parcelZza.writeFloat(f2);
        zzc(19, parcelZza);
    }

    @Override // com.google.android.gms.internal.maps.zzaa
    public final void zzq(boolean z) throws RemoteException {
        Parcel parcelZza = zza();
        zzc.zzd(parcelZza, z);
        zzc(9, parcelZza);
    }

    @Override // com.google.android.gms.internal.maps.zzaa
    public final void zzr(boolean z) throws RemoteException {
        Parcel parcelZza = zza();
        zzc.zzd(parcelZza, z);
        zzc(20, parcelZza);
    }

    @Override // com.google.android.gms.internal.maps.zzaa
    public final void zzs(IObjectWrapper iObjectWrapper) throws RemoteException {
        Parcel parcelZza = zza();
        zzc.zzg(parcelZza, iObjectWrapper);
        zzc(18, parcelZza);
    }

    @Override // com.google.android.gms.internal.maps.zzaa
    public final void zzt(float f, float f2) throws RemoteException {
        Parcel parcelZza = zza();
        parcelZza.writeFloat(f);
        parcelZza.writeFloat(f2);
        zzc(24, parcelZza);
    }

    @Override // com.google.android.gms.internal.maps.zzaa
    public final void zzu(LatLng latLng) throws RemoteException {
        Parcel parcelZza = zza();
        zzc.zze(parcelZza, latLng);
        zzc(3, parcelZza);
    }

    @Override // com.google.android.gms.internal.maps.zzaa
    public final void zzv(float f) throws RemoteException {
        Parcel parcelZza = zza();
        parcelZza.writeFloat(f);
        zzc(22, parcelZza);
    }

    @Override // com.google.android.gms.internal.maps.zzaa
    public final void zzw(String str) throws RemoteException {
        Parcel parcelZza = zza();
        parcelZza.writeString(str);
        zzc(7, parcelZza);
    }

    @Override // com.google.android.gms.internal.maps.zzaa
    public final void zzx(IObjectWrapper iObjectWrapper) throws RemoteException {
        Parcel parcelZza = zza();
        zzc.zzg(parcelZza, iObjectWrapper);
        zzc(29, parcelZza);
    }

    @Override // com.google.android.gms.internal.maps.zzaa
    public final void zzy(String str) throws RemoteException {
        Parcel parcelZza = zza();
        parcelZza.writeString(str);
        zzc(5, parcelZza);
    }

    @Override // com.google.android.gms.internal.maps.zzaa
    public final void zzz(boolean z) throws RemoteException {
        Parcel parcelZza = zza();
        zzc.zzd(parcelZza, z);
        zzc(14, parcelZza);
    }
}
