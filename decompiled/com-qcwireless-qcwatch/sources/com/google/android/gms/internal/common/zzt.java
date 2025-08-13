package com.google.android.gms.internal.common;

import com.qcwireless.qcwatch.ui.mine.chat.Constant;

/* compiled from: com.google.android.gms:play-services-basement@@18.0.0 */
/* loaded from: classes2.dex */
final class zzt extends zzw {
    final /* synthetic */ zzu zza;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzt(zzu zzuVar, zzx zzxVar, CharSequence charSequence) {
        super(zzxVar, charSequence);
        this.zza = zzuVar;
    }

    @Override // com.google.android.gms.internal.common.zzw
    final int zzc(int i) {
        return i + 1;
    }

    @Override // com.google.android.gms.internal.common.zzw
    final int zzd(int i) {
        zzo zzoVar = this.zza.zza;
        CharSequence charSequence = this.zzb;
        int length = charSequence.length();
        zzs.zzb(i, length, Constant.MODIFY_ACTIVITY_INTENT_INDEX);
        while (i < length) {
            if (zzoVar.zza(charSequence.charAt(i))) {
                return i;
            }
            i++;
        }
        return -1;
    }
}
