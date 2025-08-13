package com.google.android.gms.internal.fitness;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.fitness.request.SessionInsertRequest;

/* compiled from: com.google.android.gms:play-services-fitness@@21.1.0 */
/* loaded from: classes2.dex */
final class zzeh extends zzbf {
    final /* synthetic */ SessionInsertRequest zza;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzeh(zzep zzepVar, GoogleApiClient googleApiClient, SessionInsertRequest sessionInsertRequest) {
        super(googleApiClient);
        this.zza = sessionInsertRequest;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    protected final /* bridge */ /* synthetic */ void doExecute(Api.AnyClient anyClient) throws RemoteException {
        ((zzcd) ((zzbh) anyClient).getService()).zzd(new SessionInsertRequest(this.zza, new zzes(this)));
    }
}
