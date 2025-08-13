package com.google.android.gms.maps.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.StreetViewPanoramaCamera;
import com.google.android.gms.maps.model.StreetViewPanoramaLocation;
import com.google.android.gms.maps.model.StreetViewPanoramaOrientation;
import com.google.android.gms.maps.model.StreetViewSource;

/* compiled from: com.google.android.gms:play-services-maps@@18.1.0 */
/* loaded from: classes2.dex */
public final class zzbw extends com.google.android.gms.internal.maps.zza implements IStreetViewPanoramaDelegate {
    zzbw(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate");
    }

    @Override // com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate
    public final void animateTo(StreetViewPanoramaCamera streetViewPanoramaCamera, long j) throws RemoteException {
        Parcel parcelZza = zza();
        com.google.android.gms.internal.maps.zzc.zze(parcelZza, streetViewPanoramaCamera);
        parcelZza.writeLong(j);
        zzc(9, parcelZza);
    }

    @Override // com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate
    public final void enablePanning(boolean z) throws RemoteException {
        Parcel parcelZza = zza();
        com.google.android.gms.internal.maps.zzc.zzd(parcelZza, z);
        zzc(2, parcelZza);
    }

    @Override // com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate
    public final void enableStreetNames(boolean z) throws RemoteException {
        Parcel parcelZza = zza();
        com.google.android.gms.internal.maps.zzc.zzd(parcelZza, z);
        zzc(4, parcelZza);
    }

    @Override // com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate
    public final void enableUserNavigation(boolean z) throws RemoteException {
        Parcel parcelZza = zza();
        com.google.android.gms.internal.maps.zzc.zzd(parcelZza, z);
        zzc(3, parcelZza);
    }

    @Override // com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate
    public final void enableZoom(boolean z) throws RemoteException {
        Parcel parcelZza = zza();
        com.google.android.gms.internal.maps.zzc.zzd(parcelZza, z);
        zzc(1, parcelZza);
    }

    @Override // com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate
    public final StreetViewPanoramaCamera getPanoramaCamera() throws RemoteException {
        Parcel parcelZzH = zzH(10, zza());
        StreetViewPanoramaCamera streetViewPanoramaCamera = (StreetViewPanoramaCamera) com.google.android.gms.internal.maps.zzc.zza(parcelZzH, StreetViewPanoramaCamera.CREATOR);
        parcelZzH.recycle();
        return streetViewPanoramaCamera;
    }

    @Override // com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate
    public final StreetViewPanoramaLocation getStreetViewPanoramaLocation() throws RemoteException {
        Parcel parcelZzH = zzH(14, zza());
        StreetViewPanoramaLocation streetViewPanoramaLocation = (StreetViewPanoramaLocation) com.google.android.gms.internal.maps.zzc.zza(parcelZzH, StreetViewPanoramaLocation.CREATOR);
        parcelZzH.recycle();
        return streetViewPanoramaLocation;
    }

    @Override // com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate
    public final boolean isPanningGesturesEnabled() throws RemoteException {
        Parcel parcelZzH = zzH(6, zza());
        boolean zZzh = com.google.android.gms.internal.maps.zzc.zzh(parcelZzH);
        parcelZzH.recycle();
        return zZzh;
    }

    @Override // com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate
    public final boolean isStreetNamesEnabled() throws RemoteException {
        Parcel parcelZzH = zzH(8, zza());
        boolean zZzh = com.google.android.gms.internal.maps.zzc.zzh(parcelZzH);
        parcelZzH.recycle();
        return zZzh;
    }

    @Override // com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate
    public final boolean isUserNavigationEnabled() throws RemoteException {
        Parcel parcelZzH = zzH(7, zza());
        boolean zZzh = com.google.android.gms.internal.maps.zzc.zzh(parcelZzH);
        parcelZzH.recycle();
        return zZzh;
    }

    @Override // com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate
    public final boolean isZoomGesturesEnabled() throws RemoteException {
        Parcel parcelZzH = zzH(5, zza());
        boolean zZzh = com.google.android.gms.internal.maps.zzc.zzh(parcelZzH);
        parcelZzH.recycle();
        return zZzh;
    }

    @Override // com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate
    public final IObjectWrapper orientationToPoint(StreetViewPanoramaOrientation streetViewPanoramaOrientation) throws RemoteException {
        Parcel parcelZza = zza();
        com.google.android.gms.internal.maps.zzc.zze(parcelZza, streetViewPanoramaOrientation);
        Parcel parcelZzH = zzH(19, parcelZza);
        IObjectWrapper iObjectWrapperAsInterface = IObjectWrapper.Stub.asInterface(parcelZzH.readStrongBinder());
        parcelZzH.recycle();
        return iObjectWrapperAsInterface;
    }

    @Override // com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate
    public final StreetViewPanoramaOrientation pointToOrientation(IObjectWrapper iObjectWrapper) throws RemoteException {
        Parcel parcelZza = zza();
        com.google.android.gms.internal.maps.zzc.zzg(parcelZza, iObjectWrapper);
        Parcel parcelZzH = zzH(18, parcelZza);
        StreetViewPanoramaOrientation streetViewPanoramaOrientation = (StreetViewPanoramaOrientation) com.google.android.gms.internal.maps.zzc.zza(parcelZzH, StreetViewPanoramaOrientation.CREATOR);
        parcelZzH.recycle();
        return streetViewPanoramaOrientation;
    }

    @Override // com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate
    public final void setOnStreetViewPanoramaCameraChangeListener(zzbk zzbkVar) throws RemoteException {
        Parcel parcelZza = zza();
        com.google.android.gms.internal.maps.zzc.zzg(parcelZza, zzbkVar);
        zzc(16, parcelZza);
    }

    @Override // com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate
    public final void setOnStreetViewPanoramaChangeListener(zzbm zzbmVar) throws RemoteException {
        Parcel parcelZza = zza();
        com.google.android.gms.internal.maps.zzc.zzg(parcelZza, zzbmVar);
        zzc(15, parcelZza);
    }

    @Override // com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate
    public final void setOnStreetViewPanoramaClickListener(zzbo zzboVar) throws RemoteException {
        Parcel parcelZza = zza();
        com.google.android.gms.internal.maps.zzc.zzg(parcelZza, zzboVar);
        zzc(17, parcelZza);
    }

    @Override // com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate
    public final void setOnStreetViewPanoramaLongClickListener(zzbq zzbqVar) throws RemoteException {
        Parcel parcelZza = zza();
        com.google.android.gms.internal.maps.zzc.zzg(parcelZza, zzbqVar);
        zzc(20, parcelZza);
    }

    @Override // com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate
    public final void setPosition(LatLng latLng) throws RemoteException {
        Parcel parcelZza = zza();
        com.google.android.gms.internal.maps.zzc.zze(parcelZza, latLng);
        zzc(12, parcelZza);
    }

    @Override // com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate
    public final void setPositionWithID(String str) throws RemoteException {
        Parcel parcelZza = zza();
        parcelZza.writeString(str);
        zzc(11, parcelZza);
    }

    @Override // com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate
    public final void setPositionWithRadius(LatLng latLng, int i) throws RemoteException {
        Parcel parcelZza = zza();
        com.google.android.gms.internal.maps.zzc.zze(parcelZza, latLng);
        parcelZza.writeInt(i);
        zzc(13, parcelZza);
    }

    @Override // com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate
    public final void setPositionWithRadiusAndSource(LatLng latLng, int i, StreetViewSource streetViewSource) throws RemoteException {
        Parcel parcelZza = zza();
        com.google.android.gms.internal.maps.zzc.zze(parcelZza, latLng);
        parcelZza.writeInt(i);
        com.google.android.gms.internal.maps.zzc.zze(parcelZza, streetViewSource);
        zzc(22, parcelZza);
    }

    @Override // com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate
    public final void setPositionWithSource(LatLng latLng, StreetViewSource streetViewSource) throws RemoteException {
        Parcel parcelZza = zza();
        com.google.android.gms.internal.maps.zzc.zze(parcelZza, latLng);
        com.google.android.gms.internal.maps.zzc.zze(parcelZza, streetViewSource);
        zzc(21, parcelZza);
    }
}
