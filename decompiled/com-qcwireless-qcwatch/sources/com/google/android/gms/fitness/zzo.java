package com.google.android.gms.fitness;

import android.app.PendingIntent;
import android.os.RemoteException;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.fitness.data.zzv;
import com.google.android.gms.fitness.request.zzah;
import com.google.android.gms.fitness.request.zzaj;
import com.google.android.gms.fitness.request.zzan;
import com.google.android.gms.internal.fitness.zzaz;
import com.google.android.gms.internal.fitness.zzcc;
import com.google.android.gms.internal.fitness.zzcp;
import com.google.android.gms.internal.fitness.zzes;
import com.google.android.gms.tasks.TaskCompletionSource;

/* compiled from: com.google.android.gms:play-services-fitness@@21.1.0 */
/* loaded from: classes2.dex */
final class zzo implements RemoteCall {
    final /* synthetic */ ListenerHolder zza;

    zzo(SensorsClient sensorsClient, ListenerHolder listenerHolder) {
        this.zza = listenerHolder;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.common.api.internal.RemoteCall
    public final /* bridge */ /* synthetic */ void accept(Object obj, Object obj2) throws RemoteException {
        zzaz zzazVar = (zzaz) obj;
        TaskCompletionSource taskCompletionSource = (TaskCompletionSource) obj2;
        zzaj zzajVarZzd = zzah.zza().zzd(this.zza);
        if (zzajVarZzd == null) {
            taskCompletionSource.setResult(false);
        } else {
            ((zzcc) zzazVar.getService()).zzf(new zzan((zzv) zzajVarZzd, (PendingIntent) null, (zzcp) zzes.zze(taskCompletionSource)));
        }
    }
}
