package com.google.android.gms.internal.auth;

import android.content.ContentResolver;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.os.StrictMode;
import android.util.Log;
import androidx.collection.ArrayMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/* compiled from: com.google.android.gms:play-services-auth-base@@17.1.4 */
/* loaded from: classes2.dex */
public final class zzcg implements zzck {
    private final ContentResolver zzc;
    private final Uri zzd;
    private final ContentObserver zze;
    private final Object zzf;
    private volatile Map<String, String> zzg;
    private final List<zzch> zzh;
    private static final Map<Uri, zzcg> zzb = new ArrayMap();
    public static final String[] zza = {"key", "value"};

    private zzcg(ContentResolver contentResolver, Uri uri) {
        zzcf zzcfVar = new zzcf(this, null);
        this.zze = zzcfVar;
        this.zzf = new Object();
        this.zzh = new ArrayList();
        Objects.requireNonNull(contentResolver);
        Objects.requireNonNull(uri);
        this.zzc = contentResolver;
        this.zzd = uri;
        contentResolver.registerContentObserver(uri, false, zzcfVar);
    }

    public static zzcg zza(ContentResolver contentResolver, Uri uri) {
        zzcg zzcgVar;
        synchronized (zzcg.class) {
            Map<Uri, zzcg> map = zzb;
            zzcgVar = map.get(uri);
            if (zzcgVar == null) {
                try {
                    zzcg zzcgVar2 = new zzcg(contentResolver, uri);
                    try {
                        map.put(uri, zzcgVar2);
                    } catch (SecurityException unused) {
                    }
                    zzcgVar = zzcgVar2;
                } catch (SecurityException unused2) {
                }
            }
        }
        return zzcgVar;
    }

    static synchronized void zzd() {
        for (zzcg zzcgVar : zzb.values()) {
            zzcgVar.zzc.unregisterContentObserver(zzcgVar.zze);
        }
        zzb.clear();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.internal.auth.zzck
    public final /* bridge */ /* synthetic */ Object zzb(String str) {
        Map<String, String> map;
        Map<String, String> map2;
        Map<String, String> map3 = this.zzg;
        Map<String, String> mapEmptyMap = map3;
        if (map3 == null) {
            synchronized (this.zzf) {
                Map<String, String> map4 = this.zzg;
                map = map4;
                if (map4 == null) {
                    StrictMode.ThreadPolicy threadPolicyAllowThreadDiskReads = StrictMode.allowThreadDiskReads();
                    try {
                        try {
                            map2 = (Map) zzci.zza(new zzcj() { // from class: com.google.android.gms.internal.auth.zzce
                                @Override // com.google.android.gms.internal.auth.zzcj
                                public final Object zza() {
                                    return this.zza.zzc();
                                }
                            });
                        } finally {
                            StrictMode.setThreadPolicy(threadPolicyAllowThreadDiskReads);
                        }
                    } catch (SQLiteException | IllegalStateException | SecurityException unused) {
                        Log.e("ConfigurationContentLoader", "PhenotypeFlag unable to load ContentProvider, using default values");
                        StrictMode.setThreadPolicy(threadPolicyAllowThreadDiskReads);
                        map2 = null;
                    }
                    this.zzg = map2;
                    threadPolicyAllowThreadDiskReads = map2;
                    map = threadPolicyAllowThreadDiskReads;
                }
            }
            mapEmptyMap = map;
        }
        if (mapEmptyMap == null) {
            mapEmptyMap = Collections.emptyMap();
        }
        return mapEmptyMap.get(str);
    }

    public final /* synthetic */ Map zzc() {
        Cursor cursorQuery = this.zzc.query(this.zzd, zza, null, null, null);
        if (cursorQuery == null) {
            return Collections.emptyMap();
        }
        try {
            int count = cursorQuery.getCount();
            if (count == 0) {
                return Collections.emptyMap();
            }
            Map arrayMap = count <= 256 ? new ArrayMap(count) : new HashMap(count, 1.0f);
            while (cursorQuery.moveToNext()) {
                arrayMap.put(cursorQuery.getString(0), cursorQuery.getString(1));
            }
            return arrayMap;
        } finally {
            cursorQuery.close();
        }
    }

    public final void zze() {
        synchronized (this.zzf) {
            this.zzg = null;
            zzcz.zzd();
        }
        synchronized (this) {
            Iterator<zzch> it = this.zzh.iterator();
            while (it.hasNext()) {
                it.next().zza();
            }
        }
    }
}
