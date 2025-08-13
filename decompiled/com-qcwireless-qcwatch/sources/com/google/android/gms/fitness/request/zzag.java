package com.google.android.gms.fitness.request;

import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.fitness.data.DataPoint;

/* compiled from: com.google.android.gms:play-services-fitness@@21.1.0 */
/* loaded from: classes2.dex */
final class zzag implements ListenerHolder.Notifier {
    final /* synthetic */ DataPoint zza;

    zzag(zzaj zzajVar, DataPoint dataPoint) {
        this.zza = dataPoint;
    }

    @Override // com.google.android.gms.common.api.internal.ListenerHolder.Notifier
    public final /* bridge */ /* synthetic */ void notifyListener(Object obj) {
        ((OnDataPointListener) obj).onDataPoint(this.zza);
    }

    @Override // com.google.android.gms.common.api.internal.ListenerHolder.Notifier
    public final void onNotifyListenerFailed() {
    }
}
