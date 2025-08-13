package com.google.android.gms.internal.fitness;

import com.qcwireless.qcwatch.ui.mine.chat.Constant;

/* compiled from: com.google.android.gms:play-services-fitness@@21.1.0 */
/* loaded from: classes2.dex */
final class zzfq extends zzfm {
    static final zzfm zza = new zzfq(new Object[0], 0);
    final transient Object[] zzb;
    private final transient int zzc;

    zzfq(Object[] objArr, int i) {
        this.zzb = objArr;
        this.zzc = i;
    }

    @Override // java.util.List
    public final Object get(int i) {
        zzff.zza(i, this.zzc, Constant.MODIFY_ACTIVITY_INTENT_INDEX);
        Object obj = this.zzb[i];
        obj.getClass();
        return obj;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zzc;
    }

    @Override // com.google.android.gms.internal.fitness.zzfm, com.google.android.gms.internal.fitness.zzfj
    final int zza(Object[] objArr, int i) {
        System.arraycopy(this.zzb, 0, objArr, 0, this.zzc);
        return this.zzc;
    }

    @Override // com.google.android.gms.internal.fitness.zzfj
    final int zzb() {
        return this.zzc;
    }

    @Override // com.google.android.gms.internal.fitness.zzfj
    final int zzc() {
        return 0;
    }

    @Override // com.google.android.gms.internal.fitness.zzfj
    final Object[] zze() {
        return this.zzb;
    }
}
