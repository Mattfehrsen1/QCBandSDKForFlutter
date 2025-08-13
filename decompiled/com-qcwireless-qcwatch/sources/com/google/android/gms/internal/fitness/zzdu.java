package com.google.android.gms.internal.fitness;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.result.ListSubscriptionsResult;

/* compiled from: com.google.android.gms:play-services-fitness@@21.1.0 */
/* loaded from: classes2.dex */
final class zzdu extends zzam {
    final /* synthetic */ DataType zza;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzdu(zzea zzeaVar, GoogleApiClient googleApiClient, DataType dataType) {
        super(googleApiClient);
        this.zza = dataType;
    }

    @Override // com.google.android.gms.common.api.internal.BasePendingResult
    protected final /* synthetic */ Result createFailedResult(Status status) {
        return ListSubscriptionsResult.zza(status);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    protected final /* bridge */ /* synthetic */ void doExecute(Api.AnyClient anyClient) throws RemoteException {
        ((zzcb) ((zzar) anyClient).getService()).zzd(new com.google.android.gms.fitness.request.zzae(this.zza, (zzcg) new zzdz(this, null)));
    }
}
