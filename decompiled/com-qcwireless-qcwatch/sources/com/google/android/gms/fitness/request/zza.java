package com.google.android.gms.fitness.request;

import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.fitness.data.BleDevice;

/* compiled from: com.google.android.gms:play-services-fitness@@21.1.0 */
/* loaded from: classes2.dex */
final class zza implements ListenerHolder.Notifier {
    final /* synthetic */ BleDevice zza;

    zza(zze zzeVar, BleDevice bleDevice) {
        this.zza = bleDevice;
    }

    @Override // com.google.android.gms.common.api.internal.ListenerHolder.Notifier
    public final /* synthetic */ void notifyListener(Object obj) {
        ((BleScanCallback) obj).onDeviceFound(this.zza);
    }

    @Override // com.google.android.gms.common.api.internal.ListenerHolder.Notifier
    public final void onNotifyListenerFailed() {
    }
}
