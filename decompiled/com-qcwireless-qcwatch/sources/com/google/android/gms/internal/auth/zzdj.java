package com.google.android.gms.internal.auth;

import java.io.Serializable;
import java.util.Arrays;
import javax.annotation.CheckForNull;

/* compiled from: com.google.android.gms:play-services-auth-base@@17.1.4 */
/* loaded from: classes2.dex */
final class zzdj<T> implements Serializable, zzdg {
    final T zza;

    zzdj(T t) {
        this.zza = t;
    }

    public final boolean equals(@CheckForNull Object obj) {
        if (!(obj instanceof zzdj)) {
            return false;
        }
        T t = this.zza;
        T t2 = ((zzdj) obj).zza;
        return t == t2 || t.equals(t2);
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.zza});
    }

    public final String toString() {
        String strValueOf = String.valueOf(this.zza);
        StringBuilder sb = new StringBuilder(String.valueOf(strValueOf).length() + 22);
        sb.append("Suppliers.ofInstance(");
        sb.append(strValueOf);
        sb.append(")");
        return sb.toString();
    }

    @Override // com.google.android.gms.internal.auth.zzdg
    public final T zza() {
        return this.zza;
    }
}
