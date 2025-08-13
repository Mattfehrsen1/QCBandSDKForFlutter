package com.google.android.gms.internal.auth;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-auth-base@@17.1.4 */
/* loaded from: classes2.dex */
public final class zzeg {
    static final zzeg zza = new zzeg(true);
    private static volatile boolean zzb = false;
    private static volatile zzeg zzc;
    private final Map zzd;

    zzeg() {
        this.zzd = new HashMap();
    }

    public static zzeg zza() {
        zzeg zzegVar = zzc;
        if (zzegVar == null) {
            synchronized (zzeg.class) {
                zzegVar = zzc;
                if (zzegVar == null) {
                    zzegVar = zza;
                    zzc = zzegVar;
                }
            }
        }
        return zzegVar;
    }

    zzeg(boolean z) {
        this.zzd = Collections.emptyMap();
    }
}
