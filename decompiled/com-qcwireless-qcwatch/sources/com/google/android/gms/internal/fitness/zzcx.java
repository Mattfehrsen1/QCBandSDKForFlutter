package com.google.android.gms.internal.fitness;

import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.fitness.result.BleDevicesResult;

/* compiled from: com.google.android.gms:play-services-fitness@@21.1.0 */
/* loaded from: classes2.dex */
final class zzcx extends zzev {
    private final BaseImplementation.ResultHolder zza;

    @Override // com.google.android.gms.internal.fitness.zzew
    public final void zzb(BleDevicesResult bleDevicesResult) {
        this.zza.setResult(bleDevicesResult);
    }
}
