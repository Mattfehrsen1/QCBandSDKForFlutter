package com.google.android.gms.internal.auth;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/* compiled from: com.google.android.gms:play-services-auth-base@@17.1.4 */
/* loaded from: classes2.dex */
public final class zzfk<K, V> extends LinkedHashMap<K, V> {
    private static final zzfk zza;
    private boolean zzb;

    static {
        zzfk zzfkVar = new zzfk();
        zza = zzfkVar;
        zzfkVar.zzb = false;
    }

    private zzfk() {
        this.zzb = true;
    }

    public static <K, V> zzfk<K, V> zza() {
        return zza;
    }

    private static int zzf(Object obj) {
        if (obj instanceof byte[]) {
            return zzev.zzb((byte[]) obj);
        }
        if (obj instanceof zzes) {
            throw new UnsupportedOperationException();
        }
        return obj.hashCode();
    }

    private final void zzg() {
        if (!this.zzb) {
            throw new UnsupportedOperationException();
        }
    }

    @Override // java.util.LinkedHashMap, java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final void clear() {
        zzg();
        super.clear();
    }

    @Override // java.util.LinkedHashMap, java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final Set<Map.Entry<K, V>> entrySet() {
        return isEmpty() ? Collections.emptySet() : super.entrySet();
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final boolean equals(Object obj) {
        if (!(obj instanceof Map)) {
            return false;
        }
        Map map = (Map) obj;
        if (this == map) {
            return true;
        }
        if (size() != map.size()) {
            return false;
        }
        for (Map.Entry<K, V> entry : entrySet()) {
            if (!map.containsKey(entry.getKey())) {
                return false;
            }
            V value = entry.getValue();
            Object obj2 = map.get(entry.getKey());
            if (!(((value instanceof byte[]) && (obj2 instanceof byte[])) ? Arrays.equals((byte[]) value, (byte[]) obj2) : value.equals(obj2))) {
                return false;
            }
        }
        return true;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final int hashCode() {
        int iZzf = 0;
        for (Map.Entry<K, V> entry : entrySet()) {
            iZzf += zzf(entry.getValue()) ^ zzf(entry.getKey());
        }
        return iZzf;
    }

    @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final V put(K k, V v) {
        zzg();
        zzev.zze(k);
        zzev.zze(v);
        return (V) super.put(k, v);
    }

    @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final void putAll(Map<? extends K, ? extends V> map) {
        zzg();
        for (K k : map.keySet()) {
            zzev.zze(k);
            zzev.zze(map.get(k));
        }
        super.putAll(map);
    }

    @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final V remove(Object obj) {
        zzg();
        return (V) super.remove(obj);
    }

    public final zzfk<K, V> zzb() {
        return isEmpty() ? new zzfk<>() : new zzfk<>(this);
    }

    public final void zzc() {
        this.zzb = false;
    }

    public final void zzd(zzfk<K, V> zzfkVar) {
        zzg();
        if (zzfkVar.isEmpty()) {
            return;
        }
        putAll(zzfkVar);
    }

    public final boolean zze() {
        return this.zzb;
    }

    private zzfk(Map<K, V> map) {
        super(map);
        this.zzb = true;
    }
}
