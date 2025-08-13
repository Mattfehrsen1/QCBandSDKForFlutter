package com.google.android.gms.internal.fitness;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.fitness.request.DataUpdateRequest;

/* compiled from: com.google.android.gms:play-services-fitness@@21.1.0 */
/* loaded from: classes2.dex */
final class zzdk extends zzah {
    final /* synthetic */ DataUpdateRequest zza;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzdk(zzds zzdsVar, GoogleApiClient googleApiClient, DataUpdateRequest dataUpdateRequest) {
        super(googleApiClient);
        this.zza = dataUpdateRequest;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    protected final /* bridge */ /* synthetic */ void doExecute(Api.AnyClient anyClient) throws RemoteException {
        ((zzca) ((zzaj) anyClient).getService()).zzj(new DataUpdateRequest(this.zza, new zzes(this)));
    }
}
