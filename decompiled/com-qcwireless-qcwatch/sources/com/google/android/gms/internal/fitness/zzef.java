package com.google.android.gms.internal.fitness;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.fitness.data.Session;

/* compiled from: com.google.android.gms:play-services-fitness@@21.1.0 */
/* loaded from: classes2.dex */
final class zzef extends zzbf {
    final /* synthetic */ Session zza;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzef(zzep zzepVar, GoogleApiClient googleApiClient, Session session) {
        super(googleApiClient);
        this.zza = session;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    protected final /* bridge */ /* synthetic */ void doExecute(Api.AnyClient anyClient) throws RemoteException {
        ((zzcd) ((zzbh) anyClient).getService()).zzg(new com.google.android.gms.fitness.request.zzat(this.zza, (zzcp) new zzes(this)));
    }
}
