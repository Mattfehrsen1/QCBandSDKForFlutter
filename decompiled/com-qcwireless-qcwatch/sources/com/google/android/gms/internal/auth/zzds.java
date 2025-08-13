package com.google.android.gms.internal.auth;

import java.util.NoSuchElementException;

/* compiled from: com.google.android.gms:play-services-auth-base@@17.1.4 */
/* loaded from: classes2.dex */
final class zzds extends zzdv {
    final /* synthetic */ zzeb zza;
    private int zzb = 0;
    private final int zzc;

    zzds(zzeb zzebVar) {
        this.zza = zzebVar;
        this.zzc = zzebVar.zzd();
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.zzb < this.zzc;
    }

    @Override // com.google.android.gms.internal.auth.zzdx
    public final byte zza() {
        int i = this.zzb;
        if (i >= this.zzc) {
            throw new NoSuchElementException();
        }
        this.zzb = i + 1;
        return this.zza.zzb(i);
    }
}
