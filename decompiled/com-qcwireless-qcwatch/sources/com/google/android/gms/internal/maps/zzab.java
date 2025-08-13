package com.google.android.gms.internal.maps;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PatternItem;
import java.util.ArrayList;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-maps@@18.1.0 */
/* loaded from: classes2.dex */
public final class zzab extends zza implements zzad {
    zzab(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.maps.model.internal.IPolygonDelegate");
    }

    @Override // com.google.android.gms.internal.maps.zzad
    public final void zzA(float f) throws RemoteException {
        Parcel parcelZza = zza();
        parcelZza.writeFloat(f);
        zzc(13, parcelZza);
    }

    @Override // com.google.android.gms.internal.maps.zzad
    public final boolean zzB(zzad zzadVar) throws RemoteException {
        Parcel parcelZza = zza();
        zzc.zzg(parcelZza, zzadVar);
        Parcel parcelZzH = zzH(19, parcelZza);
        boolean zZzh = zzc.zzh(parcelZzH);
        parcelZzH.recycle();
        return zZzh;
    }

    @Override // com.google.android.gms.internal.maps.zzad
    public final boolean zzC() throws RemoteException {
        Parcel parcelZzH = zzH(22, zza());
        boolean zZzh = zzc.zzh(parcelZzH);
        parcelZzH.recycle();
        return zZzh;
    }

    @Override // com.google.android.gms.internal.maps.zzad
    public final boolean zzD() throws RemoteException {
        Parcel parcelZzH = zzH(18, zza());
        boolean zZzh = zzc.zzh(parcelZzH);
        parcelZzH.recycle();
        return zZzh;
    }

    @Override // com.google.android.gms.internal.maps.zzad
    public final boolean zzE() throws RemoteException {
        Parcel parcelZzH = zzH(16, zza());
        boolean zZzh = zzc.zzh(parcelZzH);
        parcelZzH.recycle();
        return zZzh;
    }

    @Override // com.google.android.gms.internal.maps.zzad
    public final float zzd() throws RemoteException {
        Parcel parcelZzH = zzH(8, zza());
        float f = parcelZzH.readFloat();
        parcelZzH.recycle();
        return f;
    }

    @Override // com.google.android.gms.internal.maps.zzad
    public final float zze() throws RemoteException {
        Parcel parcelZzH = zzH(14, zza());
        float f = parcelZzH.readFloat();
        parcelZzH.recycle();
        return f;
    }

    @Override // com.google.android.gms.internal.maps.zzad
    public final int zzf() throws RemoteException {
        Parcel parcelZzH = zzH(12, zza());
        int i = parcelZzH.readInt();
        parcelZzH.recycle();
        return i;
    }

    @Override // com.google.android.gms.internal.maps.zzad
    public final int zzg() throws RemoteException {
        Parcel parcelZzH = zzH(10, zza());
        int i = parcelZzH.readInt();
        parcelZzH.recycle();
        return i;
    }

    @Override // com.google.android.gms.internal.maps.zzad
    public final int zzh() throws RemoteException {
        Parcel parcelZzH = zzH(24, zza());
        int i = parcelZzH.readInt();
        parcelZzH.recycle();
        return i;
    }

    @Override // com.google.android.gms.internal.maps.zzad
    public final int zzi() throws RemoteException {
        Parcel parcelZzH = zzH(20, zza());
        int i = parcelZzH.readInt();
        parcelZzH.recycle();
        return i;
    }

    @Override // com.google.android.gms.internal.maps.zzad
    public final IObjectWrapper zzj() throws RemoteException {
        Parcel parcelZzH = zzH(28, zza());
        IObjectWrapper iObjectWrapperAsInterface = IObjectWrapper.Stub.asInterface(parcelZzH.readStrongBinder());
        parcelZzH.recycle();
        return iObjectWrapperAsInterface;
    }

    @Override // com.google.android.gms.internal.maps.zzad
    public final String zzk() throws RemoteException {
        Parcel parcelZzH = zzH(2, zza());
        String string = parcelZzH.readString();
        parcelZzH.recycle();
        return string;
    }

    @Override // com.google.android.gms.internal.maps.zzad
    public final List zzl() throws RemoteException {
        Parcel parcelZzH = zzH(6, zza());
        ArrayList arrayListZzb = zzc.zzb(parcelZzH);
        parcelZzH.recycle();
        return arrayListZzb;
    }

    @Override // com.google.android.gms.internal.maps.zzad
    public final List zzm() throws RemoteException {
        Parcel parcelZzH = zzH(4, zza());
        ArrayList arrayListCreateTypedArrayList = parcelZzH.createTypedArrayList(LatLng.CREATOR);
        parcelZzH.recycle();
        return arrayListCreateTypedArrayList;
    }

    @Override // com.google.android.gms.internal.maps.zzad
    public final List zzn() throws RemoteException {
        Parcel parcelZzH = zzH(26, zza());
        ArrayList arrayListCreateTypedArrayList = parcelZzH.createTypedArrayList(PatternItem.CREATOR);
        parcelZzH.recycle();
        return arrayListCreateTypedArrayList;
    }

    @Override // com.google.android.gms.internal.maps.zzad
    public final void zzo() throws RemoteException {
        zzc(1, zza());
    }

    @Override // com.google.android.gms.internal.maps.zzad
    public final void zzp(boolean z) throws RemoteException {
        Parcel parcelZza = zza();
        zzc.zzd(parcelZza, z);
        zzc(21, parcelZza);
    }

    @Override // com.google.android.gms.internal.maps.zzad
    public final void zzq(int i) throws RemoteException {
        Parcel parcelZza = zza();
        parcelZza.writeInt(i);
        zzc(11, parcelZza);
    }

    @Override // com.google.android.gms.internal.maps.zzad
    public final void zzr(boolean z) throws RemoteException {
        Parcel parcelZza = zza();
        zzc.zzd(parcelZza, z);
        zzc(17, parcelZza);
    }

    @Override // com.google.android.gms.internal.maps.zzad
    public final void zzs(List list) throws RemoteException {
        Parcel parcelZza = zza();
        parcelZza.writeList(list);
        zzc(5, parcelZza);
    }

    @Override // com.google.android.gms.internal.maps.zzad
    public final void zzt(List list) throws RemoteException {
        Parcel parcelZza = zza();
        parcelZza.writeTypedList(list);
        zzc(3, parcelZza);
    }

    @Override // com.google.android.gms.internal.maps.zzad
    public final void zzu(int i) throws RemoteException {
        Parcel parcelZza = zza();
        parcelZza.writeInt(i);
        zzc(9, parcelZza);
    }

    @Override // com.google.android.gms.internal.maps.zzad
    public final void zzv(int i) throws RemoteException {
        Parcel parcelZza = zza();
        parcelZza.writeInt(i);
        zzc(23, parcelZza);
    }

    @Override // com.google.android.gms.internal.maps.zzad
    public final void zzw(List list) throws RemoteException {
        Parcel parcelZza = zza();
        parcelZza.writeTypedList(list);
        zzc(25, parcelZza);
    }

    @Override // com.google.android.gms.internal.maps.zzad
    public final void zzx(float f) throws RemoteException {
        Parcel parcelZza = zza();
        parcelZza.writeFloat(f);
        zzc(7, parcelZza);
    }

    @Override // com.google.android.gms.internal.maps.zzad
    public final void zzy(IObjectWrapper iObjectWrapper) throws RemoteException {
        Parcel parcelZza = zza();
        zzc.zzg(parcelZza, iObjectWrapper);
        zzc(27, parcelZza);
    }

    @Override // com.google.android.gms.internal.maps.zzad
    public final void zzz(boolean z) throws RemoteException {
        Parcel parcelZza = zza();
        zzc.zzd(parcelZza, z);
        zzc(15, parcelZza);
    }
}
