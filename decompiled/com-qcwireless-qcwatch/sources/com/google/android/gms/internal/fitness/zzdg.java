package com.google.android.gms.internal.fitness;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.fitness.request.GoalsReadRequest;
import com.google.android.gms.fitness.result.GoalsResult;
import java.util.Collections;

/* compiled from: com.google.android.gms:play-services-fitness@@21.1.0 */
/* loaded from: classes2.dex */
final class zzdg extends zzx {
    final /* synthetic */ GoalsReadRequest zza;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzdg(zzdh zzdhVar, GoogleApiClient googleApiClient, GoalsReadRequest goalsReadRequest) {
        super(googleApiClient);
        this.zza = goalsReadRequest;
    }

    @Override // com.google.android.gms.common.api.internal.BasePendingResult
    protected final /* synthetic */ Result createFailedResult(Status status) {
        return new GoalsResult(status, Collections.emptyList());
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    protected final /* bridge */ /* synthetic */ void doExecute(Api.AnyClient anyClient) throws RemoteException {
        ((zzbz) ((zzab) anyClient).getService()).zzd(new GoalsReadRequest(this.zza, new zzdf(this)));
    }
}
