package com.google.android.gms.internal.maps;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.maps.model.Tile;

/* compiled from: com.google.android.gms:play-services-maps@@18.1.0 */
/* loaded from: classes2.dex */
public final class zzak extends zza implements zzam {
    zzak(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.maps.model.internal.ITileProviderDelegate");
    }

    @Override // com.google.android.gms.internal.maps.zzam
    public final Tile zzb(int i, int i2, int i3) throws RemoteException {
        Parcel parcelZza = zza();
        parcelZza.writeInt(i);
        parcelZza.writeInt(i2);
        parcelZza.writeInt(i3);
        Parcel parcelZzH = zzH(1, parcelZza);
        Tile tile = (Tile) zzc.zza(parcelZzH, Tile.CREATOR);
        parcelZzH.recycle();
        return tile;
    }
}
