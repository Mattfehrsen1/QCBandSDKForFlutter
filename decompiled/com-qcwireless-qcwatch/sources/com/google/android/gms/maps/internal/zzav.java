package com.google.android.gms.maps.internal;

import android.os.Parcel;
import android.os.RemoteException;

/* compiled from: com.google.android.gms:play-services-maps@@18.1.0 */
/* loaded from: classes2.dex */
public abstract class zzav extends com.google.android.gms.internal.maps.zzb implements zzaw {
    public zzav() {
        super("com.google.android.gms.maps.internal.IOnMarkerDragListener");
    }

    @Override // com.google.android.gms.internal.maps.zzb
    protected final boolean zza(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (i == 1) {
            com.google.android.gms.internal.maps.zzaa zzaaVarZzb = com.google.android.gms.internal.maps.zzz.zzb(parcel.readStrongBinder());
            com.google.android.gms.internal.maps.zzc.zzc(parcel);
            zzd(zzaaVarZzb);
        } else if (i == 2) {
            com.google.android.gms.internal.maps.zzaa zzaaVarZzb2 = com.google.android.gms.internal.maps.zzz.zzb(parcel.readStrongBinder());
            com.google.android.gms.internal.maps.zzc.zzc(parcel);
            zzb(zzaaVarZzb2);
        } else {
            if (i != 3) {
                return false;
            }
            com.google.android.gms.internal.maps.zzaa zzaaVarZzb3 = com.google.android.gms.internal.maps.zzz.zzb(parcel.readStrongBinder());
            com.google.android.gms.internal.maps.zzc.zzc(parcel);
            zzc(zzaaVarZzb3);
        }
        parcel2.writeNoException();
        return true;
    }
}
