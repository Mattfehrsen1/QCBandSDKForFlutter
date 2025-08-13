package com.google.android.gms.internal.fitness;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.fitness.request.DataDeleteRequest;

/* compiled from: com.google.android.gms:play-services-fitness@@21.1.0 */
/* loaded from: classes2.dex */
final class zzdj extends zzah {
    final /* synthetic */ DataDeleteRequest zza;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzdj(zzds zzdsVar, GoogleApiClient googleApiClient, DataDeleteRequest dataDeleteRequest) {
        super(googleApiClient);
        this.zza = dataDeleteRequest;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    protected final /* bridge */ /* synthetic */ void doExecute(Api.AnyClient anyClient) throws RemoteException {
        ((zzca) ((zzaj) anyClient).getService()).zzd(new DataDeleteRequest(this.zza, new zzes(this)));
    }
}
