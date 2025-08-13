package com.google.android.gms.internal.fitness;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.fitness.request.StartBleScanRequest;

/* compiled from: com.google.android.gms:play-services-fitness@@21.1.0 */
/* loaded from: classes2.dex */
final class zzcq extends zzk {
    final /* synthetic */ StartBleScanRequest zza;
    final /* synthetic */ com.google.android.gms.fitness.request.zzab zzb;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzcq(zzcy zzcyVar, GoogleApiClient googleApiClient, StartBleScanRequest startBleScanRequest, com.google.android.gms.fitness.request.zzab zzabVar) {
        super(googleApiClient);
        this.zza = startBleScanRequest;
        this.zzb = zzabVar;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    protected final /* bridge */ /* synthetic */ void doExecute(Api.AnyClient anyClient) throws RemoteException {
        ((zzbx) ((zzm) anyClient).getService()).zzf(new StartBleScanRequest(this.zza.getDataTypes(), this.zzb, this.zza.getTimeoutSecs(), new zzes(this)));
    }
}
