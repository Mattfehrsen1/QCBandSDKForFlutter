package com.google.android.gms.maps.internal;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

/* compiled from: com.google.android.gms:play-services-maps@@18.1.0 */
/* loaded from: classes2.dex */
public abstract class zzh extends com.google.android.gms.internal.maps.zzb implements zzi {
    public zzh() {
        super("com.google.android.gms.maps.internal.IInfoWindowAdapter");
    }

    @Override // com.google.android.gms.internal.maps.zzb
    protected final boolean zza(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (i == 1) {
            com.google.android.gms.internal.maps.zzaa zzaaVarZzb = com.google.android.gms.internal.maps.zzz.zzb(parcel.readStrongBinder());
            com.google.android.gms.internal.maps.zzc.zzc(parcel);
            IObjectWrapper iObjectWrapperZzc = zzc(zzaaVarZzb);
            parcel2.writeNoException();
            com.google.android.gms.internal.maps.zzc.zzg(parcel2, iObjectWrapperZzc);
        } else {
            if (i != 2) {
                return false;
            }
            com.google.android.gms.internal.maps.zzaa zzaaVarZzb2 = com.google.android.gms.internal.maps.zzz.zzb(parcel.readStrongBinder());
            com.google.android.gms.internal.maps.zzc.zzc(parcel);
            IObjectWrapper iObjectWrapperZzb = zzb(zzaaVarZzb2);
            parcel2.writeNoException();
            com.google.android.gms.internal.maps.zzc.zzg(parcel2, iObjectWrapperZzb);
        }
        return true;
    }
}
