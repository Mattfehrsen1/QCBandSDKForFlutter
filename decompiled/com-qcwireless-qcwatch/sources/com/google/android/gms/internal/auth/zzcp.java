package com.google.android.gms.internal.auth;

/* compiled from: com.google.android.gms:play-services-auth-base@@17.1.4 */
/* loaded from: classes2.dex */
public final class zzcp {
    static volatile zzde<Boolean> zza = zzde.zzc();
    private static final Object zzb = new Object();

    /* JADX WARN: Can't wrap try/catch for region: R(11:17|(1:19)(8:20|(1:22)(1:23)|24|(0)|34|35|36|37)|29|43|30|31|(1:33)|34|35|36|37) */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static boolean zza(android.content.Context r5, android.net.Uri r6) {
        /*
            java.lang.String r6 = r6.getAuthority()
            java.lang.String r0 = "com.google.android.gms.phenotype"
            boolean r0 = r0.equals(r6)
            r1 = 0
            if (r0 != 0) goto L2f
            java.lang.String r5 = java.lang.String.valueOf(r6)
            int r5 = r5.length()
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            int r5 = r5 + 91
            r0.<init>(r5)
            r0.append(r6)
            java.lang.String r5 = " is an unsupported authority. Only com.google.android.gms.phenotype authority is supported."
            r0.append(r5)
            java.lang.String r5 = "PhenotypeClientHelper"
            java.lang.String r6 = r0.toString()
            android.util.Log.e(r5, r6)
            return r1
        L2f:
            com.google.android.gms.internal.auth.zzde<java.lang.Boolean> r6 = com.google.android.gms.internal.auth.zzcp.zza
            boolean r6 = r6.zzb()
            if (r6 == 0) goto L44
            com.google.android.gms.internal.auth.zzde<java.lang.Boolean> r5 = com.google.android.gms.internal.auth.zzcp.zza
            java.lang.Object r5 = r5.zza()
            java.lang.Boolean r5 = (java.lang.Boolean) r5
            boolean r5 = r5.booleanValue()
            return r5
        L44:
            java.lang.Object r6 = com.google.android.gms.internal.auth.zzcp.zzb
            monitor-enter(r6)
            com.google.android.gms.internal.auth.zzde<java.lang.Boolean> r0 = com.google.android.gms.internal.auth.zzcp.zza     // Catch: java.lang.Throwable -> Lb8
            boolean r0 = r0.zzb()     // Catch: java.lang.Throwable -> Lb8
            if (r0 == 0) goto L5d
            com.google.android.gms.internal.auth.zzde<java.lang.Boolean> r5 = com.google.android.gms.internal.auth.zzcp.zza     // Catch: java.lang.Throwable -> Lb8
            java.lang.Object r5 = r5.zza()     // Catch: java.lang.Throwable -> Lb8
            java.lang.Boolean r5 = (java.lang.Boolean) r5     // Catch: java.lang.Throwable -> Lb8
            boolean r5 = r5.booleanValue()     // Catch: java.lang.Throwable -> Lb8
            monitor-exit(r6)     // Catch: java.lang.Throwable -> Lb8
            return r5
        L5d:
            java.lang.String r0 = "com.google.android.gms"
            java.lang.String r2 = r5.getPackageName()     // Catch: java.lang.Throwable -> Lb8
            boolean r0 = r0.equals(r2)     // Catch: java.lang.Throwable -> Lb8
            if (r0 == 0) goto L6b
            goto L8e
        L6b:
            android.content.pm.PackageManager r0 = r5.getPackageManager()     // Catch: java.lang.Throwable -> Lb8
            java.lang.String r2 = "com.google.android.gms.phenotype"
            int r3 = android.os.Build.VERSION.SDK_INT     // Catch: java.lang.Throwable -> Lb8
            r4 = 29
            if (r3 >= r4) goto L7a
            r3 = 0
            goto L7c
        L7a:
            r3 = 268435456(0x10000000, float:2.5243549E-29)
        L7c:
            android.content.pm.ProviderInfo r0 = r0.resolveContentProvider(r2, r3)     // Catch: java.lang.Throwable -> Lb8
            if (r0 == 0) goto La0
            java.lang.String r2 = "com.google.android.gms"
            java.lang.String r0 = r0.packageName     // Catch: java.lang.Throwable -> Lb8
            boolean r0 = r2.equals(r0)     // Catch: java.lang.Throwable -> Lb8
            if (r0 != 0) goto L8e
            goto La0
        L8e:
            android.content.pm.PackageManager r5 = r5.getPackageManager()     // Catch: java.lang.Throwable -> Lb8
            java.lang.String r0 = "com.google.android.gms"
            android.content.pm.ApplicationInfo r5 = r5.getApplicationInfo(r0, r1)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> La0 java.lang.Throwable -> Lb8
            int r5 = r5.flags     // Catch: java.lang.Throwable -> Lb8
            r5 = r5 & 129(0x81, float:1.81E-43)
            if (r5 == 0) goto La0
            r1 = 1
        La0:
            java.lang.Boolean r5 = java.lang.Boolean.valueOf(r1)     // Catch: java.lang.Throwable -> Lb8
            com.google.android.gms.internal.auth.zzde r5 = com.google.android.gms.internal.auth.zzde.zzd(r5)     // Catch: java.lang.Throwable -> Lb8
            com.google.android.gms.internal.auth.zzcp.zza = r5     // Catch: java.lang.Throwable -> Lb8
            monitor-exit(r6)     // Catch: java.lang.Throwable -> Lb8
            com.google.android.gms.internal.auth.zzde<java.lang.Boolean> r5 = com.google.android.gms.internal.auth.zzcp.zza
            java.lang.Object r5 = r5.zza()
            java.lang.Boolean r5 = (java.lang.Boolean) r5
            boolean r5 = r5.booleanValue()
            return r5
        Lb8:
            r5 = move-exception
            monitor-exit(r6)     // Catch: java.lang.Throwable -> Lb8
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.auth.zzcp.zza(android.content.Context, android.net.Uri):boolean");
    }
}
