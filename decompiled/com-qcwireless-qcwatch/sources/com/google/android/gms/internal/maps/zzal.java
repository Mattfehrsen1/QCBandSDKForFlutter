package com.google.android.gms.internal.maps;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.maps.model.Tile;

/* compiled from: com.google.android.gms:play-services-maps@@18.1.0 */
/* loaded from: classes2.dex */
public abstract class zzal extends zzb implements zzam {
    public zzal() {
        super("com.google.android.gms.maps.model.internal.ITileProviderDelegate");
    }

    public static zzam zzc(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.maps.model.internal.ITileProviderDelegate");
        return iInterfaceQueryLocalInterface instanceof zzam ? (zzam) iInterfaceQueryLocalInterface : new zzak(iBinder);
    }

    @Override // com.google.android.gms.internal.maps.zzb
    protected final boolean zza(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (i != 1) {
            return false;
        }
        int i3 = parcel.readInt();
        int i4 = parcel.readInt();
        int i5 = parcel.readInt();
        zzc.zzc(parcel);
        Tile tileZzb = zzb(i3, i4, i5);
        parcel2.writeNoException();
        zzc.zzf(parcel2, tileZzb);
        return true;
    }
}
