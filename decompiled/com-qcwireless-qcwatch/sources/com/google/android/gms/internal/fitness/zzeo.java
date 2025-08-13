package com.google.android.gms.internal.fitness;

import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.fitness.result.SessionStopResult;

/* compiled from: com.google.android.gms:play-services-fitness@@21.1.0 */
/* loaded from: classes2.dex */
final class zzeo extends zzcl {
    private final BaseImplementation.ResultHolder zza;

    @Override // com.google.android.gms.internal.fitness.zzcm
    public final void zzd(SessionStopResult sessionStopResult) {
        this.zza.setResult(sessionStopResult);
    }
}
