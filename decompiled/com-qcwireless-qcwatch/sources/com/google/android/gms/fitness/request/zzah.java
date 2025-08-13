package com.google.android.gms.fitness.request;

import android.os.Looper;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.api.internal.ListenerHolders;
import com.google.android.gms.common.internal.Preconditions;
import java.util.HashMap;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-fitness@@21.1.0 */
/* loaded from: classes2.dex */
public final class zzah {
    private static final zzah zza = new zzah();
    private final Map zzb = new HashMap();

    private zzah() {
    }

    public static zzah zza() {
        return zza;
    }

    private static ListenerHolder zzf(OnDataPointListener onDataPointListener, Looper looper) {
        return ListenerHolders.createListenerHolder(onDataPointListener, looper, "OnDataPointListener");
    }

    public final zzaj zzb(ListenerHolder listenerHolder) {
        zzaj zzajVar;
        synchronized (this.zzb) {
            ListenerHolder.ListenerKey listenerKey = (ListenerHolder.ListenerKey) Preconditions.checkNotNull(listenerHolder.getListenerKey(), "Key must not be null");
            zzajVar = (zzaj) this.zzb.get(listenerKey);
            if (zzajVar == null) {
                zzajVar = new zzaj(listenerHolder, null);
                this.zzb.put(listenerKey, zzajVar);
            }
        }
        return zzajVar;
    }

    public final zzaj zzc(OnDataPointListener onDataPointListener, Looper looper) {
        return zzb(zzf(onDataPointListener, looper));
    }

    public final zzaj zzd(ListenerHolder listenerHolder) {
        synchronized (this.zzb) {
            ListenerHolder.ListenerKey listenerKey = listenerHolder.getListenerKey();
            if (listenerKey == null) {
                return null;
            }
            zzaj zzajVar = (zzaj) this.zzb.remove(listenerKey);
            if (zzajVar != null) {
                zzajVar.zzc();
            }
            return zzajVar;
        }
    }

    public final zzaj zze(OnDataPointListener onDataPointListener, Looper looper) {
        return zzd(zzf(onDataPointListener, looper));
    }
}
