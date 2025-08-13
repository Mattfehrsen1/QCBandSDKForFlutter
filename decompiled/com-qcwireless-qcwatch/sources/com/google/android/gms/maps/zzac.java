package com.google.android.gms.maps;

import android.os.RemoteException;
import com.google.android.gms.maps.internal.IGoogleMapDelegate;

/* compiled from: com.google.android.gms:play-services-maps@@18.1.0 */
/* loaded from: classes2.dex */
final class zzac extends com.google.android.gms.maps.internal.zzar {
    final /* synthetic */ OnMapReadyCallback zza;

    zzac(zzad zzadVar, OnMapReadyCallback onMapReadyCallback) {
        this.zza = onMapReadyCallback;
    }

    @Override // com.google.android.gms.maps.internal.zzas
    public final void zzb(IGoogleMapDelegate iGoogleMapDelegate) throws RemoteException {
        this.zza.onMapReady(new GoogleMap(iGoogleMapDelegate));
    }
}
