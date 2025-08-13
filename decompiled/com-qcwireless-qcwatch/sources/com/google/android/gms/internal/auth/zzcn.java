package com.google.android.gms.internal.auth;

import android.content.Context;
import android.database.ContentObserver;
import android.util.Log;
import androidx.core.content.PermissionChecker;
import javax.annotation.Nullable;

/* compiled from: com.google.android.gms:play-services-auth-base@@17.1.4 */
/* loaded from: classes2.dex */
final class zzcn implements zzck {
    private static zzcn zza;

    @Nullable
    private final Context zzb;

    @Nullable
    private final ContentObserver zzc;

    private zzcn() {
        this.zzb = null;
        this.zzc = null;
    }

    private zzcn(Context context) {
        this.zzb = context;
        zzcm zzcmVar = new zzcm(this, null);
        this.zzc = zzcmVar;
        context.getContentResolver().registerContentObserver(zzcb.zza, true, zzcmVar);
    }

    static zzcn zza(Context context) {
        zzcn zzcnVar;
        synchronized (zzcn.class) {
            if (zza == null) {
                zza = PermissionChecker.checkSelfPermission(context, "com.google.android.providers.gsf.permission.READ_GSERVICES") == 0 ? new zzcn(context) : new zzcn();
            }
            zzcnVar = zza;
        }
        return zzcnVar;
    }

    static synchronized void zze() {
        Context context;
        zzcn zzcnVar = zza;
        if (zzcnVar != null && (context = zzcnVar.zzb) != null && zzcnVar.zzc != null) {
            context.getContentResolver().unregisterContentObserver(zza.zzc);
        }
        zza = null;
    }

    @Override // com.google.android.gms.internal.auth.zzck
    /* renamed from: zzc, reason: merged with bridge method [inline-methods] */
    public final String zzb(final String str) {
        if (this.zzb == null) {
            return null;
        }
        try {
            return (String) zzci.zza(new zzcj() { // from class: com.google.android.gms.internal.auth.zzcl
                @Override // com.google.android.gms.internal.auth.zzcj
                public final Object zza() {
                    return this.zza.zzd(str);
                }
            });
        } catch (IllegalStateException | SecurityException e) {
            String strValueOf = String.valueOf(str);
            Log.e("GservicesLoader", strValueOf.length() != 0 ? "Unable to read GServices for: ".concat(strValueOf) : new String("Unable to read GServices for: "), e);
            return null;
        }
    }

    public final /* synthetic */ String zzd(String str) {
        return zzcb.zza(this.zzb.getContentResolver(), str, null);
    }
}
