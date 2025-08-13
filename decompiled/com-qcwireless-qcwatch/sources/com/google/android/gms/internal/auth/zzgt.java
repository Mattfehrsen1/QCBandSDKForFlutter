package com.google.android.gms.internal.auth;

import java.util.Iterator;

/* compiled from: com.google.android.gms:play-services-auth-base@@17.1.4 */
/* loaded from: classes2.dex */
final class zzgt implements Iterator<String> {
    final Iterator<String> zza;
    final /* synthetic */ zzgu zzb;

    zzgt(zzgu zzguVar) {
        this.zzb = zzguVar;
        this.zza = zzguVar.zza.iterator();
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.zza.hasNext();
    }

    @Override // java.util.Iterator
    public final /* bridge */ /* synthetic */ String next() {
        return this.zza.next();
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException();
    }
}
