package com.google.android.gms.auth.api.accounttransfer;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.internal.auth.zzap;
import com.google.android.gms.internal.auth.zzau;
import com.google.android.gms.tasks.TaskCompletionSource;

/* compiled from: com.google.android.gms:play-services-auth-base@@17.1.4 */
/* loaded from: classes2.dex */
abstract class zzk<T> extends TaskApiCall<zzap, T> {
    protected TaskCompletionSource<T> zzb;

    /* synthetic */ zzk(int i, zzb zzbVar) {
        super(null, false, i);
    }

    @Override // com.google.android.gms.common.api.internal.TaskApiCall
    protected final /* bridge */ /* synthetic */ void doExecute(Api.AnyClient anyClient, TaskCompletionSource taskCompletionSource) throws RemoteException {
        this.zzb = taskCompletionSource;
        zza((zzau) ((zzap) anyClient).getService());
    }

    protected abstract void zza(zzau zzauVar) throws RemoteException;
}
