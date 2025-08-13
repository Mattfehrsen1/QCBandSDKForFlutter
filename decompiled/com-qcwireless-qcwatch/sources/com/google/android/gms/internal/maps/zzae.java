package com.google.android.gms.internal.maps;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.maps.model.Cap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PatternItem;
import com.google.android.gms.maps.model.StyleSpan;
import java.util.ArrayList;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-maps@@18.1.0 */
/* loaded from: classes2.dex */
public final class zzae extends zza implements zzag {
    zzae(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.maps.model.internal.IPolylineDelegate");
    }

    @Override // com.google.android.gms.internal.maps.zzag
    public final void zzA(boolean z) throws RemoteException {
        Parcel parcelZza = zza();
        zzc.zzd(parcelZza, z);
        zzc(11, parcelZza);
    }

    @Override // com.google.android.gms.internal.maps.zzag
    public final void zzB(float f) throws RemoteException {
        Parcel parcelZza = zza();
        parcelZza.writeFloat(f);
        zzc(5, parcelZza);
    }

    @Override // com.google.android.gms.internal.maps.zzag
    public final void zzC(float f) throws RemoteException {
        Parcel parcelZza = zza();
        parcelZza.writeFloat(f);
        zzc(9, parcelZza);
    }

    @Override // com.google.android.gms.internal.maps.zzag
    public final boolean zzD(zzag zzagVar) throws RemoteException {
        Parcel parcelZza = zza();
        zzc.zzg(parcelZza, zzagVar);
        Parcel parcelZzH = zzH(15, parcelZza);
        boolean zZzh = zzc.zzh(parcelZzH);
        parcelZzH.recycle();
        return zZzh;
    }

    @Override // com.google.android.gms.internal.maps.zzag
    public final boolean zzE() throws RemoteException {
        Parcel parcelZzH = zzH(18, zza());
        boolean zZzh = zzc.zzh(parcelZzH);
        parcelZzH.recycle();
        return zZzh;
    }

    @Override // com.google.android.gms.internal.maps.zzag
    public final boolean zzF() throws RemoteException {
        Parcel parcelZzH = zzH(14, zza());
        boolean zZzh = zzc.zzh(parcelZzH);
        parcelZzH.recycle();
        return zZzh;
    }

    @Override // com.google.android.gms.internal.maps.zzag
    public final boolean zzG() throws RemoteException {
        Parcel parcelZzH = zzH(12, zza());
        boolean zZzh = zzc.zzh(parcelZzH);
        parcelZzH.recycle();
        return zZzh;
    }

    @Override // com.google.android.gms.internal.maps.zzag
    public final float zzd() throws RemoteException {
        Parcel parcelZzH = zzH(6, zza());
        float f = parcelZzH.readFloat();
        parcelZzH.recycle();
        return f;
    }

    @Override // com.google.android.gms.internal.maps.zzag
    public final float zze() throws RemoteException {
        Parcel parcelZzH = zzH(10, zza());
        float f = parcelZzH.readFloat();
        parcelZzH.recycle();
        return f;
    }

    @Override // com.google.android.gms.internal.maps.zzag
    public final int zzf() throws RemoteException {
        Parcel parcelZzH = zzH(8, zza());
        int i = parcelZzH.readInt();
        parcelZzH.recycle();
        return i;
    }

    @Override // com.google.android.gms.internal.maps.zzag
    public final int zzg() throws RemoteException {
        Parcel parcelZzH = zzH(24, zza());
        int i = parcelZzH.readInt();
        parcelZzH.recycle();
        return i;
    }

    @Override // com.google.android.gms.internal.maps.zzag
    public final int zzh() throws RemoteException {
        Parcel parcelZzH = zzH(16, zza());
        int i = parcelZzH.readInt();
        parcelZzH.recycle();
        return i;
    }

    @Override // com.google.android.gms.internal.maps.zzag
    public final IObjectWrapper zzi() throws RemoteException {
        Parcel parcelZzH = zzH(28, zza());
        IObjectWrapper iObjectWrapperAsInterface = IObjectWrapper.Stub.asInterface(parcelZzH.readStrongBinder());
        parcelZzH.recycle();
        return iObjectWrapperAsInterface;
    }

    @Override // com.google.android.gms.internal.maps.zzag
    public final Cap zzj() throws RemoteException {
        Parcel parcelZzH = zzH(22, zza());
        Cap cap = (Cap) zzc.zza(parcelZzH, Cap.CREATOR);
        parcelZzH.recycle();
        return cap;
    }

    @Override // com.google.android.gms.internal.maps.zzag
    public final Cap zzk() throws RemoteException {
        Parcel parcelZzH = zzH(20, zza());
        Cap cap = (Cap) zzc.zza(parcelZzH, Cap.CREATOR);
        parcelZzH.recycle();
        return cap;
    }

    @Override // com.google.android.gms.internal.maps.zzag
    public final String zzl() throws RemoteException {
        Parcel parcelZzH = zzH(2, zza());
        String string = parcelZzH.readString();
        parcelZzH.recycle();
        return string;
    }

    @Override // com.google.android.gms.internal.maps.zzag
    public final List zzm() throws RemoteException {
        Parcel parcelZzH = zzH(26, zza());
        ArrayList arrayListCreateTypedArrayList = parcelZzH.createTypedArrayList(PatternItem.CREATOR);
        parcelZzH.recycle();
        return arrayListCreateTypedArrayList;
    }

    @Override // com.google.android.gms.internal.maps.zzag
    public final List zzn() throws RemoteException {
        Parcel parcelZzH = zzH(4, zza());
        ArrayList arrayListCreateTypedArrayList = parcelZzH.createTypedArrayList(LatLng.CREATOR);
        parcelZzH.recycle();
        return arrayListCreateTypedArrayList;
    }

    @Override // com.google.android.gms.internal.maps.zzag
    public final List zzo() throws RemoteException {
        Parcel parcelZzH = zzH(30, zza());
        ArrayList arrayListCreateTypedArrayList = parcelZzH.createTypedArrayList(StyleSpan.CREATOR);
        parcelZzH.recycle();
        return arrayListCreateTypedArrayList;
    }

    @Override // com.google.android.gms.internal.maps.zzag
    public final void zzp() throws RemoteException {
        zzc(1, zza());
    }

    @Override // com.google.android.gms.internal.maps.zzag
    public final void zzq(boolean z) throws RemoteException {
        Parcel parcelZza = zza();
        zzc.zzd(parcelZza, z);
        zzc(17, parcelZza);
    }

    @Override // com.google.android.gms.internal.maps.zzag
    public final void zzr(int i) throws RemoteException {
        Parcel parcelZza = zza();
        parcelZza.writeInt(i);
        zzc(7, parcelZza);
    }

    @Override // com.google.android.gms.internal.maps.zzag
    public final void zzs(Cap cap) throws RemoteException {
        Parcel parcelZza = zza();
        zzc.zze(parcelZza, cap);
        zzc(21, parcelZza);
    }

    @Override // com.google.android.gms.internal.maps.zzag
    public final void zzt(boolean z) throws RemoteException {
        Parcel parcelZza = zza();
        zzc.zzd(parcelZza, z);
        zzc(13, parcelZza);
    }

    @Override // com.google.android.gms.internal.maps.zzag
    public final void zzu(int i) throws RemoteException {
        Parcel parcelZza = zza();
        parcelZza.writeInt(i);
        zzc(23, parcelZza);
    }

    @Override // com.google.android.gms.internal.maps.zzag
    public final void zzv(List list) throws RemoteException {
        Parcel parcelZza = zza();
        parcelZza.writeTypedList(list);
        zzc(25, parcelZza);
    }

    @Override // com.google.android.gms.internal.maps.zzag
    public final void zzw(List list) throws RemoteException {
        Parcel parcelZza = zza();
        parcelZza.writeTypedList(list);
        zzc(3, parcelZza);
    }

    @Override // com.google.android.gms.internal.maps.zzag
    public final void zzx(List list) throws RemoteException {
        Parcel parcelZza = zza();
        parcelZza.writeTypedList(list);
        zzc(29, parcelZza);
    }

    @Override // com.google.android.gms.internal.maps.zzag
    public final void zzy(Cap cap) throws RemoteException {
        Parcel parcelZza = zza();
        zzc.zze(parcelZza, cap);
        zzc(19, parcelZza);
    }

    @Override // com.google.android.gms.internal.maps.zzag
    public final void zzz(IObjectWrapper iObjectWrapper) throws RemoteException {
        Parcel parcelZza = zza();
        zzc.zzg(parcelZza, iObjectWrapper);
        zzc(27, parcelZza);
    }
}
