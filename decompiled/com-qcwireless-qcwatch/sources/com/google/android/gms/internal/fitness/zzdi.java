package com.google.android.gms.internal.fitness;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.fitness.data.DataSet;

/* compiled from: com.google.android.gms:play-services-fitness@@21.1.0 */
/* loaded from: classes2.dex */
final class zzdi extends zzah {
    final /* synthetic */ DataSet zza;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzdi(zzds zzdsVar, GoogleApiClient googleApiClient, DataSet dataSet, boolean z) {
        super(googleApiClient);
        this.zza = dataSet;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    protected final /* bridge */ /* synthetic */ void doExecute(Api.AnyClient anyClient) throws RemoteException {
        ((zzca) ((zzaj) anyClient).getService()).zze(new com.google.android.gms.fitness.request.zzk(this.zza, (zzcp) new zzes(this), false));
    }
}
