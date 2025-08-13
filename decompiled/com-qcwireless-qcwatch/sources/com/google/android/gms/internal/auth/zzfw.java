package com.google.android.gms.internal.auth;

/* compiled from: com.google.android.gms:play-services-auth-base@@17.1.4 */
/* loaded from: classes2.dex */
final class zzfw {
    private static final zzfv zza;
    private static final zzfv zzb;

    static {
        zzfv zzfvVar;
        try {
            zzfvVar = (zzfv) Class.forName("com.google.protobuf.NewInstanceSchemaFull").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception unused) {
            zzfvVar = null;
        }
        zza = zzfvVar;
        zzb = new zzfv();
    }

    static zzfv zza() {
        return zza;
    }

    static zzfv zzb() {
        return zzb;
    }
}
