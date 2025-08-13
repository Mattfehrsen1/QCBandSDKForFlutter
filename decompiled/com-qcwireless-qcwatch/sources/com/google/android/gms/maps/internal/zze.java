package com.google.android.gms.maps.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.StreetViewPanoramaOptions;

/* compiled from: com.google.android.gms:play-services-maps@@18.1.0 */
/* loaded from: classes2.dex */
public final class zze extends com.google.android.gms.internal.maps.zza implements zzf {
    zze(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.maps.internal.ICreator");
    }

    @Override // com.google.android.gms.maps.internal.zzf
    public final int zzd() throws RemoteException {
        Parcel parcelZzH = zzH(9, zza());
        int i = parcelZzH.readInt();
        parcelZzH.recycle();
        return i;
    }

    @Override // com.google.android.gms.maps.internal.zzf
    public final ICameraUpdateFactoryDelegate zze() throws RemoteException {
        ICameraUpdateFactoryDelegate zzbVar;
        Parcel parcelZzH = zzH(4, zza());
        IBinder strongBinder = parcelZzH.readStrongBinder();
        if (strongBinder == null) {
            zzbVar = null;
        } else {
            IInterface iInterfaceQueryLocalInterface = strongBinder.queryLocalInterface("com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate");
            zzbVar = iInterfaceQueryLocalInterface instanceof ICameraUpdateFactoryDelegate ? (ICameraUpdateFactoryDelegate) iInterfaceQueryLocalInterface : new zzb(strongBinder);
        }
        parcelZzH.recycle();
        return zzbVar;
    }

    @Override // com.google.android.gms.maps.internal.zzf
    public final IMapFragmentDelegate zzf(IObjectWrapper iObjectWrapper) throws RemoteException {
        IMapFragmentDelegate zzkVar;
        Parcel parcelZza = zza();
        com.google.android.gms.internal.maps.zzc.zzg(parcelZza, iObjectWrapper);
        Parcel parcelZzH = zzH(2, parcelZza);
        IBinder strongBinder = parcelZzH.readStrongBinder();
        if (strongBinder == null) {
            zzkVar = null;
        } else {
            IInterface iInterfaceQueryLocalInterface = strongBinder.queryLocalInterface("com.google.android.gms.maps.internal.IMapFragmentDelegate");
            zzkVar = iInterfaceQueryLocalInterface instanceof IMapFragmentDelegate ? (IMapFragmentDelegate) iInterfaceQueryLocalInterface : new zzk(strongBinder);
        }
        parcelZzH.recycle();
        return zzkVar;
    }

    @Override // com.google.android.gms.maps.internal.zzf
    public final IMapViewDelegate zzg(IObjectWrapper iObjectWrapper, GoogleMapOptions googleMapOptions) throws RemoteException {
        IMapViewDelegate zzlVar;
        Parcel parcelZza = zza();
        com.google.android.gms.internal.maps.zzc.zzg(parcelZza, iObjectWrapper);
        com.google.android.gms.internal.maps.zzc.zze(parcelZza, googleMapOptions);
        Parcel parcelZzH = zzH(3, parcelZza);
        IBinder strongBinder = parcelZzH.readStrongBinder();
        if (strongBinder == null) {
            zzlVar = null;
        } else {
            IInterface iInterfaceQueryLocalInterface = strongBinder.queryLocalInterface("com.google.android.gms.maps.internal.IMapViewDelegate");
            zzlVar = iInterfaceQueryLocalInterface instanceof IMapViewDelegate ? (IMapViewDelegate) iInterfaceQueryLocalInterface : new zzl(strongBinder);
        }
        parcelZzH.recycle();
        return zzlVar;
    }

    @Override // com.google.android.gms.maps.internal.zzf
    public final IStreetViewPanoramaFragmentDelegate zzh(IObjectWrapper iObjectWrapper) throws RemoteException {
        IStreetViewPanoramaFragmentDelegate zzbxVar;
        Parcel parcelZza = zza();
        com.google.android.gms.internal.maps.zzc.zzg(parcelZza, iObjectWrapper);
        Parcel parcelZzH = zzH(8, parcelZza);
        IBinder strongBinder = parcelZzH.readStrongBinder();
        if (strongBinder == null) {
            zzbxVar = null;
        } else {
            IInterface iInterfaceQueryLocalInterface = strongBinder.queryLocalInterface("com.google.android.gms.maps.internal.IStreetViewPanoramaFragmentDelegate");
            zzbxVar = iInterfaceQueryLocalInterface instanceof IStreetViewPanoramaFragmentDelegate ? (IStreetViewPanoramaFragmentDelegate) iInterfaceQueryLocalInterface : new zzbx(strongBinder);
        }
        parcelZzH.recycle();
        return zzbxVar;
    }

    @Override // com.google.android.gms.maps.internal.zzf
    public final IStreetViewPanoramaViewDelegate zzi(IObjectWrapper iObjectWrapper, StreetViewPanoramaOptions streetViewPanoramaOptions) throws RemoteException {
        IStreetViewPanoramaViewDelegate zzbyVar;
        Parcel parcelZza = zza();
        com.google.android.gms.internal.maps.zzc.zzg(parcelZza, iObjectWrapper);
        com.google.android.gms.internal.maps.zzc.zze(parcelZza, streetViewPanoramaOptions);
        Parcel parcelZzH = zzH(7, parcelZza);
        IBinder strongBinder = parcelZzH.readStrongBinder();
        if (strongBinder == null) {
            zzbyVar = null;
        } else {
            IInterface iInterfaceQueryLocalInterface = strongBinder.queryLocalInterface("com.google.android.gms.maps.internal.IStreetViewPanoramaViewDelegate");
            zzbyVar = iInterfaceQueryLocalInterface instanceof IStreetViewPanoramaViewDelegate ? (IStreetViewPanoramaViewDelegate) iInterfaceQueryLocalInterface : new zzby(strongBinder);
        }
        parcelZzH.recycle();
        return zzbyVar;
    }

    @Override // com.google.android.gms.maps.internal.zzf
    public final com.google.android.gms.internal.maps.zzi zzj() throws RemoteException {
        Parcel parcelZzH = zzH(5, zza());
        com.google.android.gms.internal.maps.zzi zziVarZzb = com.google.android.gms.internal.maps.zzh.zzb(parcelZzH.readStrongBinder());
        parcelZzH.recycle();
        return zziVarZzb;
    }

    @Override // com.google.android.gms.maps.internal.zzf
    public final void zzk(IObjectWrapper iObjectWrapper, int i) throws RemoteException {
        Parcel parcelZza = zza();
        com.google.android.gms.internal.maps.zzc.zzg(parcelZza, iObjectWrapper);
        parcelZza.writeInt(i);
        zzc(6, parcelZza);
    }

    @Override // com.google.android.gms.maps.internal.zzf
    public final void zzl(IObjectWrapper iObjectWrapper, int i) throws RemoteException {
        Parcel parcelZza = zza();
        com.google.android.gms.internal.maps.zzc.zzg(parcelZza, iObjectWrapper);
        parcelZza.writeInt(i);
        zzc(10, parcelZza);
    }
}
