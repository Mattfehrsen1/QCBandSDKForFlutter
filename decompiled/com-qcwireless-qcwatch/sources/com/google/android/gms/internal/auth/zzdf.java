package com.google.android.gms.internal.auth;

import javax.annotation.CheckForNull;

/* compiled from: com.google.android.gms:play-services-auth-base@@17.1.4 */
/* loaded from: classes2.dex */
final class zzdf<T> extends zzde<T> {
    private final T zza;

    zzdf(T t) {
        this.zza = t;
    }

    public final boolean equals(@CheckForNull Object obj) {
        if (obj instanceof zzdf) {
            return this.zza.equals(((zzdf) obj).zza);
        }
        return false;
    }

    public final int hashCode() {
        return this.zza.hashCode() + 1502476572;
    }

    public final String toString() {
        String strValueOf = String.valueOf(this.zza);
        StringBuilder sb = new StringBuilder(String.valueOf(strValueOf).length() + 13);
        sb.append("Optional.of(");
        sb.append(strValueOf);
        sb.append(")");
        return sb.toString();
    }

    @Override // com.google.android.gms.internal.auth.zzde
    public final T zza() {
        return this.zza;
    }

    @Override // com.google.android.gms.internal.auth.zzde
    public final boolean zzb() {
        return true;
    }
}
