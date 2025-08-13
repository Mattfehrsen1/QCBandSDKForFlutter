package com.google.android.gms.internal.auth;

/* compiled from: com.google.android.gms:play-services-auth-base@@17.1.4 */
/* loaded from: classes2.dex */
final class zzfd extends zzfe {
    private zzfd() {
        super(null);
    }

    /* synthetic */ zzfd(zzfb zzfbVar) {
        super(null);
    }

    @Override // com.google.android.gms.internal.auth.zzfe
    final void zza(Object obj, long j) {
        ((zzeu) zzgz.zzf(obj, j)).zzb();
    }

    @Override // com.google.android.gms.internal.auth.zzfe
    final <E> void zzb(Object obj, Object obj2, long j) {
        zzeu zzeuVarZzd = (zzeu) zzgz.zzf(obj, j);
        zzeu zzeuVar = (zzeu) zzgz.zzf(obj2, j);
        int size = zzeuVarZzd.size();
        int size2 = zzeuVar.size();
        if (size > 0 && size2 > 0) {
            if (!zzeuVarZzd.zzc()) {
                zzeuVarZzd = zzeuVarZzd.zzd(size2 + size);
            }
            zzeuVarZzd.addAll(zzeuVar);
        }
        if (size > 0) {
            zzeuVar = zzeuVarZzd;
        }
        zzgz.zzp(obj, j, zzeuVar);
    }
}
