package com.google.android.gms.internal.auth;

/* compiled from: com.google.android.gms:play-services-auth-base@@17.1.4 */
/* loaded from: classes2.dex */
final class zzfm {
    private static final zzfl zza;
    private static final zzfl zzb;

    static {
        zzfl zzflVar;
        try {
            zzflVar = (zzfl) Class.forName("com.google.protobuf.MapFieldSchemaFull").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception unused) {
            zzflVar = null;
        }
        zza = zzflVar;
        zzb = new zzfl();
    }

    static zzfl zza() {
        return zza;
    }

    static zzfl zzb() {
        return zzb;
    }
}
