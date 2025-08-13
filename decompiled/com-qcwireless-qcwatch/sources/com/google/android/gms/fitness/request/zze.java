package com.google.android.gms.fitness.request;

import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.fitness.data.BleDevice;

/* compiled from: com.google.android.gms:play-services-fitness@@21.1.0 */
/* loaded from: classes2.dex */
public final class zze extends zzaa {
    private final ListenerHolder zza;

    @Override // com.google.android.gms.fitness.request.zzab
    public final void zzb(BleDevice bleDevice) {
        this.zza.notifyListener(new zza(this, bleDevice));
    }

    @Override // com.google.android.gms.fitness.request.zzab
    public final void zzc() {
        this.zza.notifyListener(new zzb(this));
    }

    public final void zzd() {
        this.zza.clear();
    }
}
