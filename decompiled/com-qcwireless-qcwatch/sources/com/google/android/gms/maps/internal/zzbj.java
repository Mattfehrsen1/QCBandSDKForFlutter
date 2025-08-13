package com.google.android.gms.maps.internal;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.maps.model.StreetViewPanoramaCamera;

/* compiled from: com.google.android.gms:play-services-maps@@18.1.0 */
/* loaded from: classes2.dex */
public abstract class zzbj extends com.google.android.gms.internal.maps.zzb implements zzbk {
    public zzbj() {
        super("com.google.android.gms.maps.internal.IOnStreetViewPanoramaCameraChangeListener");
    }

    @Override // com.google.android.gms.internal.maps.zzb
    protected final boolean zza(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (i != 1) {
            return false;
        }
        StreetViewPanoramaCamera streetViewPanoramaCamera = (StreetViewPanoramaCamera) com.google.android.gms.internal.maps.zzc.zza(parcel, StreetViewPanoramaCamera.CREATOR);
        com.google.android.gms.internal.maps.zzc.zzc(parcel);
        zzb(streetViewPanoramaCamera);
        parcel2.writeNoException();
        return true;
    }
}
