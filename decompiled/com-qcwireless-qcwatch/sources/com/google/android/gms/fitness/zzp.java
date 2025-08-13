package com.google.android.gms.fitness;

import android.os.RemoteException;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.fitness.request.SensorRequest;
import com.google.android.gms.fitness.request.zzah;
import com.google.android.gms.fitness.request.zzak;
import com.google.android.gms.internal.fitness.zzaz;
import com.google.android.gms.internal.fitness.zzcc;
import com.google.android.gms.internal.fitness.zzes;
import com.google.android.gms.tasks.TaskCompletionSource;

/* compiled from: com.google.android.gms:play-services-fitness@@21.1.0 */
/* loaded from: classes2.dex */
final class zzp implements RemoteCall {
    final /* synthetic */ ListenerHolder zza;
    final /* synthetic */ SensorRequest zzb;

    zzp(SensorsClient sensorsClient, ListenerHolder listenerHolder, SensorRequest sensorRequest) {
        this.zza = listenerHolder;
        this.zzb = sensorRequest;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.common.api.internal.RemoteCall
    public final /* bridge */ /* synthetic */ void accept(Object obj, Object obj2) throws RemoteException {
        ((zzcc) ((zzaz) obj).getService()).zze(new zzak(this.zzb, zzah.zza().zzb(this.zza), null, zzes.zzc((TaskCompletionSource) obj2)));
    }
}
