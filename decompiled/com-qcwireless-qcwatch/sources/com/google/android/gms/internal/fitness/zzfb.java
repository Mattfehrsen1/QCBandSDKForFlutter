package com.google.android.gms.internal.fitness;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.fitness.service.FitnessSensorServiceRequest;

/* compiled from: com.google.android.gms:play-services-fitness@@21.1.0 */
/* loaded from: classes2.dex */
public abstract class zzfb extends zzb implements zzfc {
    public zzfb() {
        super("com.google.android.gms.fitness.internal.service.IFitnessSensorService");
    }

    @Override // com.google.android.gms.internal.fitness.zzb
    protected final boolean zza(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (i == 1) {
            zzex zzexVar = (zzex) zzc.zza(parcel, zzex.CREATOR);
            zzbq zzbqVarZzc = zzbp.zzc(parcel.readStrongBinder());
            zzc.zzb(parcel);
            zzb(zzexVar, zzbqVarZzc);
        } else if (i == 2) {
            FitnessSensorServiceRequest fitnessSensorServiceRequest = (FitnessSensorServiceRequest) zzc.zza(parcel, FitnessSensorServiceRequest.CREATOR);
            zzcp zzcpVarZzb = zzco.zzb(parcel.readStrongBinder());
            zzc.zzb(parcel);
            zzc(fitnessSensorServiceRequest, zzcpVarZzb);
        } else {
            if (i != 3) {
                return false;
            }
            zzez zzezVar = (zzez) zzc.zza(parcel, zzez.CREATOR);
            zzcp zzcpVarZzb2 = zzco.zzb(parcel.readStrongBinder());
            zzc.zzb(parcel);
            zzd(zzezVar, zzcpVarZzb2);
        }
        return true;
    }
}
