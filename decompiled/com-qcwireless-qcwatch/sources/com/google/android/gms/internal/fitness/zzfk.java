package com.google.android.gms.internal.fitness;

/* compiled from: com.google.android.gms:play-services-fitness@@21.1.0 */
/* loaded from: classes2.dex */
final class zzfk extends zzfh {
    private final zzfm zza;

    zzfk(zzfm zzfmVar, int i) {
        super(zzfmVar.size(), i);
        this.zza = zzfmVar;
    }

    @Override // com.google.android.gms.internal.fitness.zzfh
    protected final Object zza(int i) {
        return this.zza.get(i);
    }
}
