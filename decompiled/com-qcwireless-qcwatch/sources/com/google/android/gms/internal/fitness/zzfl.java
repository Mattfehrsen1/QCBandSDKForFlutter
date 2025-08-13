package com.google.android.gms.internal.fitness;

import com.qcwireless.qcwatch.ui.mine.chat.Constant;
import java.util.List;
import javax.annotation.CheckForNull;

/* compiled from: com.google.android.gms:play-services-fitness@@21.1.0 */
/* loaded from: classes2.dex */
final class zzfl extends zzfm {
    final transient int zza;
    final transient int zzb;
    final /* synthetic */ zzfm zzc;

    zzfl(zzfm zzfmVar, int i, int i2) {
        this.zzc = zzfmVar;
        this.zza = i;
        this.zzb = i2;
    }

    @Override // java.util.List
    public final Object get(int i) {
        zzff.zza(i, this.zzb, Constant.MODIFY_ACTIVITY_INTENT_INDEX);
        return this.zzc.get(i + this.zza);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zzb;
    }

    @Override // com.google.android.gms.internal.fitness.zzfm, java.util.List
    public final /* bridge */ /* synthetic */ List subList(int i, int i2) {
        return subList(i, i2);
    }

    @Override // com.google.android.gms.internal.fitness.zzfj
    final int zzb() {
        return this.zzc.zzc() + this.zza + this.zzb;
    }

    @Override // com.google.android.gms.internal.fitness.zzfj
    final int zzc() {
        return this.zzc.zzc() + this.zza;
    }

    @Override // com.google.android.gms.internal.fitness.zzfj
    @CheckForNull
    final Object[] zze() {
        return this.zzc.zze();
    }

    @Override // com.google.android.gms.internal.fitness.zzfm
    /* renamed from: zzf */
    public final zzfm subList(int i, int i2) {
        zzff.zzc(i, i2, this.zzb);
        zzfm zzfmVar = this.zzc;
        int i3 = this.zza;
        return zzfmVar.subList(i + i3, i2 + i3);
    }
}
