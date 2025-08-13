package com.google.android.gms.internal.fitness;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.fitness.request.SessionReadRequest;
import com.google.android.gms.fitness.result.SessionReadResult;
import java.util.ArrayList;

/* compiled from: com.google.android.gms:play-services-fitness@@21.1.0 */
/* loaded from: classes2.dex */
final class zzei extends zzbc {
    final /* synthetic */ SessionReadRequest zza;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzei(zzep zzepVar, GoogleApiClient googleApiClient, SessionReadRequest sessionReadRequest) {
        super(googleApiClient);
        this.zza = sessionReadRequest;
    }

    @Override // com.google.android.gms.common.api.internal.BasePendingResult
    protected final /* synthetic */ Result createFailedResult(Status status) {
        return new SessionReadResult(new ArrayList(), new ArrayList(), status);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    protected final /* bridge */ /* synthetic */ void doExecute(Api.AnyClient anyClient) throws RemoteException {
        ((zzcd) ((zzbh) anyClient).getService()).zze(new SessionReadRequest(this.zza, new zzem(this, null)));
    }
}
