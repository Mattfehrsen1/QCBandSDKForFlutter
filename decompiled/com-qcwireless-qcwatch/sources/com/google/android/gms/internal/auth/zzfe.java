package com.google.android.gms.internal.auth;

/* compiled from: com.google.android.gms:play-services-auth-base@@17.1.4 */
/* loaded from: classes2.dex */
abstract class zzfe {
    private static final zzfe zza;
    private static final zzfe zzb;

    static {
        zzfb zzfbVar = null;
        zza = new zzfc(zzfbVar);
        zzb = new zzfd(zzfbVar);
    }

    /* synthetic */ zzfe(zzfb zzfbVar) {
    }

    static zzfe zzc() {
        return zza;
    }

    static zzfe zzd() {
        return zzb;
    }

    abstract void zza(Object obj, long j);

    abstract <L> void zzb(Object obj, Object obj2, long j);
}
