package com.google.android.gms.internal.auth;

import java.io.Serializable;
import java.util.Objects;
import javax.annotation.CheckForNull;

/* compiled from: com.google.android.gms:play-services-auth-base@@17.1.4 */
/* loaded from: classes2.dex */
final class zzdh<T> implements Serializable, zzdg {
    final zzdg<T> zza;
    volatile transient boolean zzb;

    @CheckForNull
    transient T zzc;

    zzdh(zzdg<T> zzdgVar) {
        Objects.requireNonNull(zzdgVar);
        this.zza = zzdgVar;
    }

    public final String toString() {
        Object string;
        if (this.zzb) {
            String strValueOf = String.valueOf(this.zzc);
            StringBuilder sb = new StringBuilder(String.valueOf(strValueOf).length() + 25);
            sb.append("<supplier that returned ");
            sb.append(strValueOf);
            sb.append(">");
            string = sb.toString();
        } else {
            string = this.zza;
        }
        String strValueOf2 = String.valueOf(string);
        StringBuilder sb2 = new StringBuilder(String.valueOf(strValueOf2).length() + 19);
        sb2.append("Suppliers.memoize(");
        sb2.append(strValueOf2);
        sb2.append(")");
        return sb2.toString();
    }

    @Override // com.google.android.gms.internal.auth.zzdg
    public final T zza() {
        if (!this.zzb) {
            synchronized (this) {
                if (!this.zzb) {
                    T tZza = this.zza.zza();
                    this.zzc = tZza;
                    this.zzb = true;
                    return tZza;
                }
            }
        }
        return this.zzc;
    }
}
