package com.google.android.gms.internal.auth;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.StrictMode;
import android.util.Log;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import javax.annotation.Nullable;

/* compiled from: com.google.android.gms:play-services-auth-base@@17.1.4 */
/* loaded from: classes2.dex */
public abstract class zzcz<T> {
    public static final /* synthetic */ int zzd = 0;

    @Nullable
    private static volatile zzcy zze = null;
    private static volatile boolean zzf = false;
    final zzcx zzb;
    final String zzc;
    private final T zzj;
    private volatile int zzk = -1;
    private volatile T zzl;
    private final boolean zzm;
    private static final Object zza = new Object();
    private static final AtomicReference<Collection<zzcz<?>>> zzg = new AtomicReference<>();
    private static final zzdb zzh = new zzdb(zzcr.zza, null);
    private static final AtomicInteger zzi = new AtomicInteger();

    /* JADX WARN: Multi-variable type inference failed */
    /* synthetic */ zzcz(zzcx zzcxVar, String str, Object obj, boolean z, zzct zzctVar) {
        if (zzcxVar.zzb == null) {
            throw new IllegalArgumentException("Must pass a valid SharedPreferences file name or ContentProvider URI");
        }
        this.zzb = zzcxVar;
        this.zzc = str;
        this.zzj = obj;
        this.zzm = true;
    }

    static void zzd() {
        zzi.incrementAndGet();
    }

    public static void zze(final Context context) {
        if (zze == null) {
            Object obj = zza;
            synchronized (obj) {
                if (zze == null) {
                    synchronized (obj) {
                        zzcy zzcyVar = zze;
                        Context applicationContext = context.getApplicationContext();
                        if (applicationContext != null) {
                            context = applicationContext;
                        }
                        if (zzcyVar == null || zzcyVar.zza() != context) {
                            zzcg.zzd();
                            zzda.zzc();
                            zzcn.zze();
                            zze = new zzcd(context, zzdk.zza(new zzdg() { // from class: com.google.android.gms.internal.auth.zzcs
                                @Override // com.google.android.gms.internal.auth.zzdg
                                public final Object zza() {
                                    zzde zzdeVarZzc;
                                    zzde zzdeVarZzc2;
                                    Context contextCreateDeviceProtectedStorageContext = context;
                                    int i = zzcz.zzd;
                                    String str = Build.TYPE;
                                    String str2 = Build.TAGS;
                                    if ((!str.equals("eng") && !str.equals("userdebug")) || (!str2.contains("dev-keys") && !str2.contains("test-keys"))) {
                                        return zzde.zzc();
                                    }
                                    if (zzcc.zza() && !contextCreateDeviceProtectedStorageContext.isDeviceProtectedStorage()) {
                                        contextCreateDeviceProtectedStorageContext = contextCreateDeviceProtectedStorageContext.createDeviceProtectedStorageContext();
                                    }
                                    StrictMode.ThreadPolicy threadPolicyAllowThreadDiskReads = StrictMode.allowThreadDiskReads();
                                    try {
                                        StrictMode.allowThreadDiskWrites();
                                        try {
                                            File file = new File(contextCreateDeviceProtectedStorageContext.getDir("phenotype_hermetic", 0), "overrides.txt");
                                            zzdeVarZzc = file.exists() ? zzde.zzd(file) : zzde.zzc();
                                        } catch (RuntimeException e) {
                                            Log.e("HermeticFileOverrides", "no data dir", e);
                                            zzdeVarZzc = zzde.zzc();
                                        }
                                        if (zzdeVarZzc.zzb()) {
                                            File file2 = (File) zzdeVarZzc.zza();
                                            try {
                                                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file2)));
                                                try {
                                                    HashMap map = new HashMap();
                                                    HashMap map2 = new HashMap();
                                                    while (true) {
                                                        String line = bufferedReader.readLine();
                                                        if (line == null) {
                                                            break;
                                                        }
                                                        String[] strArrSplit = line.split(" ", 3);
                                                        if (strArrSplit.length != 3) {
                                                            Log.e("HermeticFileOverrides", line.length() != 0 ? "Invalid: ".concat(line) : new String("Invalid: "));
                                                        } else {
                                                            String str3 = new String(strArrSplit[0]);
                                                            String strDecode = Uri.decode(new String(strArrSplit[1]));
                                                            String strDecode2 = (String) map2.get(strArrSplit[2]);
                                                            if (strDecode2 == null) {
                                                                String str4 = new String(strArrSplit[2]);
                                                                strDecode2 = Uri.decode(str4);
                                                                if (strDecode2.length() < 1024 || strDecode2 == str4) {
                                                                    map2.put(str4, strDecode2);
                                                                }
                                                            }
                                                            if (!map.containsKey(str3)) {
                                                                map.put(str3, new HashMap());
                                                            }
                                                            ((Map) map.get(str3)).put(strDecode, strDecode2);
                                                        }
                                                    }
                                                    String strValueOf = String.valueOf(file2);
                                                    StringBuilder sb = new StringBuilder(String.valueOf(strValueOf).length() + 7);
                                                    sb.append("Parsed ");
                                                    sb.append(strValueOf);
                                                    Log.i("HermeticFileOverrides", sb.toString());
                                                    zzco zzcoVar = new zzco(map);
                                                    bufferedReader.close();
                                                    zzdeVarZzc2 = zzde.zzd(zzcoVar);
                                                } catch (Throwable th) {
                                                    try {
                                                        bufferedReader.close();
                                                    } catch (Throwable th2) {
                                                        th.addSuppressed(th2);
                                                    }
                                                    throw th;
                                                }
                                            } catch (IOException e2) {
                                                throw new RuntimeException(e2);
                                            }
                                        } else {
                                            zzdeVarZzc2 = zzde.zzc();
                                        }
                                        return zzdeVarZzc2;
                                    } finally {
                                        StrictMode.setThreadPolicy(threadPolicyAllowThreadDiskReads);
                                    }
                                }
                            }));
                            zzi.incrementAndGet();
                        }
                    }
                }
            }
        }
    }

    abstract T zza(Object obj);

    public final String zzc() {
        String str = this.zzb.zzd;
        return this.zzc;
    }

    /* JADX WARN: Removed duplicated region for block: B:36:0x00b9  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x00ba A[Catch: all -> 0x0120, TryCatch #0 {, blocks: (B:8:0x0017, B:10:0x001b, B:12:0x0021, B:14:0x0039, B:16:0x0045, B:18:0x004e, B:20:0x0060, B:22:0x006b, B:21:0x0065, B:49:0x00e6, B:51:0x00f6, B:53:0x010c, B:54:0x010f, B:55:0x0113, B:37:0x00ba, B:39:0x00c0, B:43:0x00d6, B:45:0x00dc, B:48:0x00e4, B:42:0x00d2, B:24:0x0070, B:26:0x0076, B:28:0x0084, B:32:0x00a9, B:34:0x00b3, B:30:0x009b, B:56:0x0118, B:57:0x011d, B:58:0x011e), top: B:65:0x0017 }] */
    /* JADX WARN: Removed duplicated region for block: B:46:0x00e1  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x00f6 A[Catch: all -> 0x0120, TryCatch #0 {, blocks: (B:8:0x0017, B:10:0x001b, B:12:0x0021, B:14:0x0039, B:16:0x0045, B:18:0x004e, B:20:0x0060, B:22:0x006b, B:21:0x0065, B:49:0x00e6, B:51:0x00f6, B:53:0x010c, B:54:0x010f, B:55:0x0113, B:37:0x00ba, B:39:0x00c0, B:43:0x00d6, B:45:0x00dc, B:48:0x00e4, B:42:0x00d2, B:24:0x0070, B:26:0x0076, B:28:0x0084, B:32:0x00a9, B:34:0x00b3, B:30:0x009b, B:56:0x0118, B:57:0x011d, B:58:0x011e), top: B:65:0x0017 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final T zzb() {
        /*
            Method dump skipped, instructions count: 294
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.auth.zzcz.zzb():java.lang.Object");
    }
}
