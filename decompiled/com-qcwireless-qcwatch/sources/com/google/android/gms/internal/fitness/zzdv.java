package com.google.android.gms.internal.fitness;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.fitness.data.Subscription;

/* compiled from: com.google.android.gms:play-services-fitness@@21.1.0 */
/* loaded from: classes2.dex */
final class zzdv extends zzap {
    final /* synthetic */ Subscription zza;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzdv(zzea zzeaVar, GoogleApiClient googleApiClient, Subscription subscription) {
        super(googleApiClient);
        this.zza = subscription;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    protected final /* bridge */ /* synthetic */ void doExecute(Api.AnyClient anyClient) throws RemoteException {
        ((zzcb) ((zzar) anyClient).getService()).zze(new com.google.android.gms.fitness.request.zzbd(this.zza, false, (zzcp) new zzes(this)));
    }
}
