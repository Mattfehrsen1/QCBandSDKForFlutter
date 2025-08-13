package com.google.android.gms.internal.auth;

import javax.annotation.CheckForNull;

/* compiled from: com.google.android.gms:play-services-auth-base@@17.1.4 */
/* loaded from: classes2.dex */
final class zzdc<T> extends zzde<T> {
    static final zzdc<Object> zza = new zzdc<>();

    private zzdc() {
    }

    public final boolean equals(@CheckForNull Object obj) {
        return obj == this;
    }

    public final int hashCode() {
        return 2040732332;
    }

    public final String toString() {
        return "Optional.absent()";
    }

    @Override // com.google.android.gms.internal.auth.zzde
    public final T zza() {
        throw new IllegalStateException("Optional.get() cannot be called on an absent value");
    }

    @Override // com.google.android.gms.internal.auth.zzde
    public final boolean zzb() {
        return false;
    }
}
