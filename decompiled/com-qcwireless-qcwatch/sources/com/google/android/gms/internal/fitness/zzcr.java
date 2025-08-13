package com.google.android.gms.internal.fitness;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;

/* compiled from: com.google.android.gms:play-services-fitness@@21.1.0 */
/* loaded from: classes2.dex */
final class zzcr extends zzk {
    final /* synthetic */ com.google.android.gms.fitness.request.zzab zza;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzcr(zzcy zzcyVar, GoogleApiClient googleApiClient, com.google.android.gms.fitness.request.zzab zzabVar) {
        super(googleApiClient);
        this.zza = zzabVar;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    protected final /* bridge */ /* synthetic */ void doExecute(Api.AnyClient anyClient) throws RemoteException {
        ((zzbx) ((zzm) anyClient).getService()).zzg(new com.google.android.gms.fitness.request.zzbb(this.zza, new zzes(this)));
    }
}
