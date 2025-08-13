package com.google.android.gms.internal.location;

import com.qcwireless.qcwatch.ui.mine.chat.Constant;

/* compiled from: com.google.android.gms:play-services-location@@20.0.0 */
/* loaded from: classes2.dex */
final class zzby extends zzbx {
    static final zzbx zza = new zzby(new Object[0], 0);
    final transient Object[] zzb;
    private final transient int zzc;

    zzby(Object[] objArr, int i) {
        this.zzb = objArr;
        this.zzc = i;
    }

    @Override // java.util.List
    public final Object get(int i) {
        zzbr.zza(i, this.zzc, Constant.MODIFY_ACTIVITY_INTENT_INDEX);
        Object obj = this.zzb[i];
        obj.getClass();
        return obj;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zzc;
    }

    @Override // com.google.android.gms.internal.location.zzbx, com.google.android.gms.internal.location.zzbu
    final int zza(Object[] objArr, int i) {
        System.arraycopy(this.zzb, 0, objArr, 0, this.zzc);
        return this.zzc;
    }

    @Override // com.google.android.gms.internal.location.zzbu
    final int zzb() {
        return this.zzc;
    }

    @Override // com.google.android.gms.internal.location.zzbu
    final int zzc() {
        return 0;
    }

    @Override // com.google.android.gms.internal.location.zzbu
    final boolean zzf() {
        return false;
    }

    @Override // com.google.android.gms.internal.location.zzbu
    final Object[] zzg() {
        return this.zzb;
    }
}
