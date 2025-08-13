package com.google.android.gms.internal.fitness;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.fitness.data.DataSet;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.result.DailyTotalResult;

/* compiled from: com.google.android.gms:play-services-fitness@@21.1.0 */
/* loaded from: classes2.dex */
final class zzdp extends zzae {
    final /* synthetic */ DataType zza;
    final /* synthetic */ boolean zzb;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzdp(zzds zzdsVar, GoogleApiClient googleApiClient, DataType dataType, boolean z) {
        super(googleApiClient);
        this.zza = dataType;
        this.zzb = z;
    }

    @Override // com.google.android.gms.common.api.internal.BasePendingResult
    protected final /* synthetic */ Result createFailedResult(Status status) {
        DataType dataType = this.zza;
        DataSource.Builder builder = new DataSource.Builder();
        builder.setType(1);
        builder.setDataType(dataType);
        return new DailyTotalResult(status, DataSet.builder(builder.build()).build());
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    protected final /* bridge */ /* synthetic */ void doExecute(Api.AnyClient anyClient) throws RemoteException {
        ((zzca) ((zzaj) anyClient).getService()).zzf(new com.google.android.gms.fitness.request.zzh((zzbk) new zzdo(this), this.zza, this.zzb));
    }
}
