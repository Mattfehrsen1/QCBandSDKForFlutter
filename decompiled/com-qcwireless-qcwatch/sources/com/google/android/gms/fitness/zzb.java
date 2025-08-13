package com.google.android.gms.fitness;

import android.os.RemoteException;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.fitness.request.zzab;
import com.google.android.gms.fitness.request.zzbb;
import com.google.android.gms.internal.fitness.zzbx;
import com.google.android.gms.internal.fitness.zzcp;
import com.google.android.gms.internal.fitness.zzes;
import com.google.android.gms.tasks.TaskCompletionSource;

/* compiled from: com.google.android.gms:play-services-fitness@@21.1.0 */
/* loaded from: classes2.dex */
final class zzb implements RemoteCall {
    final /* synthetic */ ListenerHolder zza;

    zzb(BleClient bleClient, ListenerHolder listenerHolder) {
        this.zza = listenerHolder;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.common.api.internal.RemoteCall
    public final /* bridge */ /* synthetic */ void accept(Object obj, Object obj2) throws RemoteException {
        com.google.android.gms.internal.fitness.zzm zzmVar = (com.google.android.gms.internal.fitness.zzm) obj;
        TaskCompletionSource taskCompletionSource = (TaskCompletionSource) obj2;
        com.google.android.gms.fitness.request.zze zzeVarZzd = com.google.android.gms.fitness.request.zzc.zza().zzd(this.zza);
        if (zzeVarZzd == null) {
            taskCompletionSource.setResult(false);
        } else {
            ((zzbx) zzmVar.getService()).zzg(new zzbb((zzab) zzeVarZzd, (zzcp) zzes.zze(taskCompletionSource)));
        }
    }
}
