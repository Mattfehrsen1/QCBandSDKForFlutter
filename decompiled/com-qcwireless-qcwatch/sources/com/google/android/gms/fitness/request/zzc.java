package com.google.android.gms.fitness.request;

import android.os.Looper;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.api.internal.ListenerHolders;
import com.google.android.gms.common.internal.Preconditions;
import java.util.HashMap;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-fitness@@21.1.0 */
/* loaded from: classes2.dex */
public final class zzc {
    private static final zzc zza = new zzc();
    private final Map zzb = new HashMap();

    private zzc() {
    }

    public static zzc zza() {
        return zza;
    }

    private static ListenerHolder zzf(BleScanCallback bleScanCallback, Looper looper) {
        return ListenerHolders.createListenerHolder(bleScanCallback, looper, "BleScanCallback");
    }

    public final zze zzb(ListenerHolder listenerHolder) {
        zze zzeVar;
        synchronized (this.zzb) {
            ListenerHolder.ListenerKey listenerKey = (ListenerHolder.ListenerKey) Preconditions.checkNotNull(listenerHolder.getListenerKey(), "Key must not be null");
            zzeVar = (zze) this.zzb.get(listenerKey);
            if (zzeVar == null) {
                zzeVar = new zze(listenerHolder, null);
                this.zzb.put(listenerKey, zzeVar);
            }
        }
        return zzeVar;
    }

    public final zze zzc(BleScanCallback bleScanCallback, Looper looper) {
        return zzb(zzf(bleScanCallback, looper));
    }

    public final zze zzd(ListenerHolder listenerHolder) {
        synchronized (this.zzb) {
            ListenerHolder.ListenerKey listenerKey = listenerHolder.getListenerKey();
            if (listenerKey == null) {
                return null;
            }
            zze zzeVar = (zze) this.zzb.get(listenerKey);
            if (zzeVar != null) {
                zzeVar.zzd();
            }
            return zzeVar;
        }
    }

    public final zze zze(BleScanCallback bleScanCallback, Looper looper) {
        return zzd(zzf(bleScanCallback, looper));
    }
}
