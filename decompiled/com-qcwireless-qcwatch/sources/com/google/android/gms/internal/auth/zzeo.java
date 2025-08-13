package com.google.android.gms.internal.auth;

import com.google.android.gms.internal.auth.zzeo;
import com.google.android.gms.internal.auth.zzeq;

/* compiled from: com.google.android.gms:play-services-auth-base@@17.1.4 */
/* loaded from: classes2.dex */
public class zzeo<MessageType extends zzeq<MessageType, BuilderType>, BuilderType extends zzeo<MessageType, BuilderType>> extends zzdl<MessageType, BuilderType> {
    protected MessageType zza;
    protected boolean zzb = false;
    private final MessageType zzc;

    protected zzeo(MessageType messagetype) {
        this.zzc = messagetype;
        this.zza = (MessageType) messagetype.zzj(4, null, null);
    }

    private static final void zzj(MessageType messagetype, MessageType messagetype2) {
        zzfy.zza().zzb(messagetype.getClass()).zzf(messagetype, messagetype2);
    }

    @Override // com.google.android.gms.internal.auth.zzdl
    protected final /* bridge */ /* synthetic */ zzdl zzb(zzdm zzdmVar) {
        zze((zzeq) zzdmVar);
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.internal.auth.zzdl
    /* renamed from: zzd, reason: merged with bridge method [inline-methods] */
    public final BuilderType clone() {
        BuilderType buildertype = (BuilderType) this.zzc.zzj(5, null, null);
        buildertype.zze(zzg());
        return buildertype;
    }

    public final BuilderType zze(MessageType messagetype) {
        if (this.zzb) {
            zzi();
            this.zzb = false;
        }
        zzj(this.zza, messagetype);
        return this;
    }

    @Override // com.google.android.gms.internal.auth.zzfp
    /* renamed from: zzf, reason: merged with bridge method [inline-methods] */
    public MessageType zzg() {
        if (this.zzb) {
            return this.zza;
        }
        MessageType messagetype = this.zza;
        zzfy.zza().zzb(messagetype.getClass()).zze(messagetype);
        this.zzb = true;
        return this.zza;
    }

    @Override // com.google.android.gms.internal.auth.zzfr
    public final /* bridge */ /* synthetic */ zzfq zzh() {
        return this.zzc;
    }

    protected void zzi() {
        MessageType messagetype = (MessageType) this.zza.zzj(4, null, null);
        zzj(messagetype, this.zza);
        this.zza = messagetype;
    }
}
