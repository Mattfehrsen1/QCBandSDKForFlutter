package com.google.android.gms.internal.auth;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/* compiled from: com.google.android.gms:play-services-auth-base@@17.1.4 */
/* loaded from: classes2.dex */
final class zzfy {
    private static final zzfy zza = new zzfy();
    private final ConcurrentMap<Class<?>, zzgb<?>> zzc = new ConcurrentHashMap();
    private final zzgc zzb = new zzfi();

    private zzfy() {
    }

    public static zzfy zza() {
        return zza;
    }

    public final <T> zzgb<T> zzb(Class<T> cls) {
        zzev.zzf(cls, "messageType");
        zzgb<T> zzgbVarZza = (zzgb) this.zzc.get(cls);
        if (zzgbVarZza == null) {
            zzgbVarZza = this.zzb.zza(cls);
            zzev.zzf(cls, "messageType");
            zzev.zzf(zzgbVarZza, "schema");
            zzgb<T> zzgbVar = (zzgb) this.zzc.putIfAbsent(cls, zzgbVarZza);
            if (zzgbVar != null) {
                return zzgbVar;
            }
        }
        return zzgbVarZza;
    }
}
