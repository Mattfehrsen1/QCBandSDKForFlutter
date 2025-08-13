package com.google.android.gms.internal.fitness;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.fitness.data.DataType;

/* compiled from: com.google.android.gms:play-services-fitness@@21.1.0 */
/* loaded from: classes2.dex */
final class zzdw extends zzap {
    final /* synthetic */ DataType zza;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzdw(zzea zzeaVar, GoogleApiClient googleApiClient, DataType dataType) {
        super(googleApiClient);
        this.zza = dataType;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    protected final /* bridge */ /* synthetic */ void doExecute(Api.AnyClient anyClient) throws RemoteException {
        ((zzcb) ((zzar) anyClient).getService()).zzf(new com.google.android.gms.fitness.request.zzbh(this.zza, null, new zzes(this)));
    }
}
