package com.google.android.gms.fitness.request;

import com.google.android.gms.common.api.internal.ListenerHolder;

/* compiled from: com.google.android.gms:play-services-fitness@@21.1.0 */
/* loaded from: classes2.dex */
final class zzb implements ListenerHolder.Notifier {
    zzb(zze zzeVar) {
    }

    @Override // com.google.android.gms.common.api.internal.ListenerHolder.Notifier
    public final /* synthetic */ void notifyListener(Object obj) {
        ((BleScanCallback) obj).onScanStopped();
    }

    @Override // com.google.android.gms.common.api.internal.ListenerHolder.Notifier
    public final void onNotifyListenerFailed() {
    }
}
