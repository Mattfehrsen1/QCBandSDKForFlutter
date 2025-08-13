package com.google.android.gms.internal.auth;

import android.content.Context;
import java.util.Objects;
import javax.annotation.Nullable;

/* compiled from: com.google.android.gms:play-services-auth-base@@17.1.4 */
/* loaded from: classes2.dex */
final class zzcd extends zzcy {
    private final Context zza;
    private final zzdg<zzde<zzco>> zzb;

    zzcd(Context context, @Nullable zzdg<zzde<zzco>> zzdgVar) {
        Objects.requireNonNull(context, "Null context");
        this.zza = context;
        this.zzb = zzdgVar;
    }

    public final boolean equals(Object obj) {
        zzdg<zzde<zzco>> zzdgVar;
        if (obj == this) {
            return true;
        }
        if (obj instanceof zzcy) {
            zzcy zzcyVar = (zzcy) obj;
            if (this.zza.equals(zzcyVar.zza()) && ((zzdgVar = this.zzb) != null ? zzdgVar.equals(zzcyVar.zzb()) : zzcyVar.zzb() == null)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        int iHashCode = (this.zza.hashCode() ^ 1000003) * 1000003;
        zzdg<zzde<zzco>> zzdgVar = this.zzb;
        return iHashCode ^ (zzdgVar == null ? 0 : zzdgVar.hashCode());
    }

    public final String toString() {
        String strValueOf = String.valueOf(this.zza);
        String strValueOf2 = String.valueOf(this.zzb);
        StringBuilder sb = new StringBuilder(String.valueOf(strValueOf).length() + 46 + String.valueOf(strValueOf2).length());
        sb.append("FlagsContext{context=");
        sb.append(strValueOf);
        sb.append(", hermeticFileOverrides=");
        sb.append(strValueOf2);
        sb.append("}");
        return sb.toString();
    }

    @Override // com.google.android.gms.internal.auth.zzcy
    final Context zza() {
        return this.zza;
    }

    @Override // com.google.android.gms.internal.auth.zzcy
    @Nullable
    final zzdg<zzde<zzco>> zzb() {
        return this.zzb;
    }
}
