package com.google.android.gms.internal.auth;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.auth.api.accounttransfer.DeviceMetaData;
import com.google.android.gms.common.api.Status;

/* compiled from: com.google.android.gms:play-services-auth-base@@17.1.4 */
/* loaded from: classes2.dex */
public abstract class zzas extends zzb implements zzat {
    public zzas() {
        super("com.google.android.gms.auth.api.accounttransfer.internal.IAccountTransferCallbacks");
    }

    @Override // com.google.android.gms.internal.auth.zzb
    protected final boolean zza(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        switch (i) {
            case 1:
                zzh((Status) zzc.zza(parcel, Status.CREATOR));
                return true;
            case 2:
                zzf((Status) zzc.zza(parcel, Status.CREATOR), (com.google.android.gms.auth.api.accounttransfer.zzv) zzc.zza(parcel, com.google.android.gms.auth.api.accounttransfer.zzv.CREATOR));
                return true;
            case 3:
                zzg((Status) zzc.zza(parcel, Status.CREATOR), (com.google.android.gms.auth.api.accounttransfer.zzn) zzc.zza(parcel, com.google.android.gms.auth.api.accounttransfer.zzn.CREATOR));
                return true;
            case 4:
                zze();
                return true;
            case 5:
                zzd((Status) zzc.zza(parcel, Status.CREATOR));
                return true;
            case 6:
                zzb(parcel.createByteArray());
                return true;
            case 7:
                zzc((DeviceMetaData) zzc.zza(parcel, DeviceMetaData.CREATOR));
                return true;
            default:
                return false;
        }
    }
}
