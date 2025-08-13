package com.google.android.gms.internal.auth;

/* compiled from: com.google.android.gms:play-services-auth-base@@17.1.4 */
/* loaded from: classes2.dex */
final class zzfi implements zzgc {
    private static final zzfo zza = new zzfg();
    private final zzfo zzb;

    public zzfi() {
        zzfo zzfoVar;
        zzfo[] zzfoVarArr = new zzfo[2];
        zzfoVarArr[0] = zzen.zza();
        try {
            zzfoVar = (zzfo) Class.forName("com.google.protobuf.DescriptorMessageInfoFactory").getDeclaredMethod("getInstance", new Class[0]).invoke(null, new Object[0]);
        } catch (Exception unused) {
            zzfoVar = zza;
        }
        zzfoVarArr[1] = zzfoVar;
        zzfh zzfhVar = new zzfh(zzfoVarArr);
        zzev.zzf(zzfhVar, "messageInfoFactory");
        this.zzb = zzfhVar;
    }

    private static boolean zzb(zzfn zzfnVar) {
        return zzfnVar.zzc() == 1;
    }

    @Override // com.google.android.gms.internal.auth.zzgc
    public final <T> zzgb<T> zza(Class<T> cls) {
        zzgd.zzg(cls);
        zzfn zzfnVarZzb = this.zzb.zzb(cls);
        return zzfnVarZzb.zzb() ? zzeq.class.isAssignableFrom(cls) ? zzfu.zzb(zzgd.zzc(), zzej.zzb(), zzfnVarZzb.zza()) : zzfu.zzb(zzgd.zza(), zzej.zza(), zzfnVarZzb.zza()) : zzeq.class.isAssignableFrom(cls) ? zzb(zzfnVarZzb) ? zzft.zzj(cls, zzfnVarZzb, zzfw.zzb(), zzfe.zzd(), zzgd.zzc(), zzej.zzb(), zzfm.zzb()) : zzft.zzj(cls, zzfnVarZzb, zzfw.zzb(), zzfe.zzd(), zzgd.zzc(), null, zzfm.zzb()) : zzb(zzfnVarZzb) ? zzft.zzj(cls, zzfnVarZzb, zzfw.zza(), zzfe.zzc(), zzgd.zza(), zzej.zza(), zzfm.zza()) : zzft.zzj(cls, zzfnVarZzb, zzfw.zza(), zzfe.zzc(), zzgd.zzb(), null, zzfm.zza());
    }
}
