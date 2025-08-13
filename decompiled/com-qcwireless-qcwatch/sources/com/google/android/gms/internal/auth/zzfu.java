package com.google.android.gms.internal.auth;

import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-auth-base@@17.1.4 */
/* loaded from: classes2.dex */
final class zzfu<T> implements zzgb<T> {
    private final zzfq zza;
    private final zzgp<?, ?> zzb;
    private final zzeh<?> zzc;

    private zzfu(zzgp<?, ?> zzgpVar, zzeh<?> zzehVar, zzfq zzfqVar) {
        this.zzb = zzgpVar;
        zzehVar.zzc(zzfqVar);
        this.zzc = zzehVar;
        this.zza = zzfqVar;
    }

    static <T> zzfu<T> zzb(zzgp<?, ?> zzgpVar, zzeh<?> zzehVar, zzfq zzfqVar) {
        return new zzfu<>(zzgpVar, zzehVar, zzfqVar);
    }

    @Override // com.google.android.gms.internal.auth.zzgb
    public final int zza(T t) {
        return this.zzb.zza(t).hashCode();
    }

    @Override // com.google.android.gms.internal.auth.zzgb
    public final T zzd() {
        return (T) ((zzeo) ((zzeq) this.zza).zzj(5, null, null)).zzg();
    }

    @Override // com.google.android.gms.internal.auth.zzgb
    public final void zze(T t) {
        this.zzb.zze(t);
        this.zzc.zzb(t);
    }

    @Override // com.google.android.gms.internal.auth.zzgb
    public final void zzf(T t, T t2) {
        zzgd.zzf(this.zzb, t, t2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.internal.auth.zzgb
    public final void zzg(T t, byte[] bArr, int i, int i2, zzdp zzdpVar) throws IOException {
        zzeq zzeqVar = (zzeq) t;
        if (zzeqVar.zzc == zzgq.zza()) {
            zzeqVar.zzc = zzgq.zzc();
        }
        throw null;
    }

    @Override // com.google.android.gms.internal.auth.zzgb
    public final boolean zzh(T t, T t2) {
        return this.zzb.zza(t).equals(this.zzb.zza(t2));
    }

    @Override // com.google.android.gms.internal.auth.zzgb
    public final boolean zzi(T t) {
        this.zzc.zza(t);
        throw null;
    }
}
