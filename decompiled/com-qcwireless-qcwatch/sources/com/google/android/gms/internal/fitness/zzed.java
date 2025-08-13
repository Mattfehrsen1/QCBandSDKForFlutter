package com.google.android.gms.internal.fitness;

import android.app.PendingIntent;
import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;

/* compiled from: com.google.android.gms:play-services-fitness@@21.1.0 */
/* loaded from: classes2.dex */
final class zzed extends zzax {
    final /* synthetic */ com.google.android.gms.fitness.data.zzv zza;
    final /* synthetic */ PendingIntent zzb;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzed(zzee zzeeVar, GoogleApiClient googleApiClient, com.google.android.gms.fitness.data.zzv zzvVar, PendingIntent pendingIntent) {
        super(googleApiClient);
        this.zza = zzvVar;
        this.zzb = pendingIntent;
    }

    @Override // com.google.android.gms.internal.fitness.zzax, com.google.android.gms.common.api.internal.BasePendingResult
    protected final /* bridge */ /* synthetic */ Result createFailedResult(Status status) {
        return status;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    protected final /* bridge */ /* synthetic */ void doExecute(Api.AnyClient anyClient) throws RemoteException {
        ((zzcc) ((zzaz) anyClient).getService()).zzf(new com.google.android.gms.fitness.request.zzan(this.zza, this.zzb, new zzes(this)));
    }
}
