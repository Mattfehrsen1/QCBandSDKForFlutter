package com.google.android.gms.internal.fitness;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.fitness.request.DataTypeCreateRequest;
import com.google.android.gms.fitness.result.DataTypeResult;

/* compiled from: com.google.android.gms:play-services-fitness@@21.1.0 */
/* loaded from: classes2.dex */
final class zzcz extends zzp {
    final /* synthetic */ DataTypeCreateRequest zza;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzcz(zzde zzdeVar, GoogleApiClient googleApiClient, DataTypeCreateRequest dataTypeCreateRequest) {
        super(googleApiClient);
        this.zza = dataTypeCreateRequest;
    }

    @Override // com.google.android.gms.common.api.internal.BasePendingResult
    protected final /* synthetic */ Result createFailedResult(Status status) {
        return new DataTypeResult(status, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    protected final /* bridge */ /* synthetic */ void doExecute(Api.AnyClient anyClient) throws RemoteException {
        ((zzby) ((zzu) anyClient).getService()).zzd(new DataTypeCreateRequest(this.zza, new zzdd(this, null)));
    }
}
