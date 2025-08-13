package com.google.android.gms.auth.api.accounttransfer;

/* compiled from: com.google.android.gms:play-services-auth-base@@17.1.4 */
/* loaded from: classes2.dex */
final class zzf extends zzj<DeviceMetaData> {
    final /* synthetic */ zzg zza;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzf(zzg zzgVar, zzk zzkVar) {
        super(zzkVar);
        this.zza = zzgVar;
    }

    @Override // com.google.android.gms.internal.auth.zzan, com.google.android.gms.internal.auth.zzat
    public final void zzc(DeviceMetaData deviceMetaData) {
        this.zza.zzb.setResult(deviceMetaData);
    }
}
