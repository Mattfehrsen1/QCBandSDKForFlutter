package com.google.android.gms.internal.auth;

/* compiled from: com.google.android.gms:play-services-auth-base@@17.1.4 */
/* loaded from: classes2.dex */
final class zzen implements zzfo {
    private static final zzen zza = new zzen();

    private zzen() {
    }

    public static zzen zza() {
        return zza;
    }

    @Override // com.google.android.gms.internal.auth.zzfo
    public final zzfn zzb(Class<?> cls) {
        if (!zzeq.class.isAssignableFrom(cls)) {
            String strValueOf = String.valueOf(cls.getName());
            throw new IllegalArgumentException(strValueOf.length() != 0 ? "Unsupported message type: ".concat(strValueOf) : new String("Unsupported message type: "));
        }
        try {
            return (zzfn) zzeq.zza(cls.asSubclass(zzeq.class)).zzj(3, null, null);
        } catch (Exception e) {
            String strValueOf2 = String.valueOf(cls.getName());
            throw new RuntimeException(strValueOf2.length() != 0 ? "Unable to get message info for ".concat(strValueOf2) : new String("Unable to get message info for "), e);
        }
    }

    @Override // com.google.android.gms.internal.auth.zzfo
    public final boolean zzc(Class<?> cls) {
        return zzeq.class.isAssignableFrom(cls);
    }
}
