package com.google.android.gms.common.internal;

import java.util.concurrent.ConcurrentHashMap;

/* compiled from: com.google.android.gms:play-services-basement@@18.0.0 */
/* loaded from: classes2.dex */
public class LibraryVersion {
    private static final GmsLogger zza = new GmsLogger("LibraryVersion", "");
    private static LibraryVersion zzb = new LibraryVersion();
    private ConcurrentHashMap<String, String> zzc = new ConcurrentHashMap<>();

    protected LibraryVersion() {
    }

    public static LibraryVersion getInstance() {
        return zzb;
    }

    /* JADX WARN: Removed duplicated region for block: B:34:0x00b8  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.String getVersion(java.lang.String r10) throws java.lang.Throwable {
        /*
            r9 = this;
            java.lang.String r0 = "Failed to get app version for libraryName: "
            java.lang.String r1 = "LibraryVersion"
            java.lang.String r2 = "Please provide a valid libraryName"
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r10, r2)
            java.util.concurrent.ConcurrentHashMap<java.lang.String, java.lang.String> r2 = r9.zzc
            boolean r2 = r2.containsKey(r10)
            if (r2 == 0) goto L1a
            java.util.concurrent.ConcurrentHashMap<java.lang.String, java.lang.String> r0 = r9.zzc
            java.lang.Object r10 = r0.get(r10)
            java.lang.String r10 = (java.lang.String) r10
            return r10
        L1a:
            java.util.Properties r2 = new java.util.Properties
            r2.<init>()
            r3 = 1
            r4 = 0
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch: java.lang.Throwable -> L92 java.io.IOException -> L94
            r5 = 0
            r3[r5] = r10     // Catch: java.lang.Throwable -> L92 java.io.IOException -> L94
            java.lang.Class<com.google.android.gms.common.internal.LibraryVersion> r5 = com.google.android.gms.common.internal.LibraryVersion.class
            java.lang.String r6 = "/%s.properties"
            java.lang.String r3 = java.lang.String.format(r6, r3)     // Catch: java.lang.Throwable -> L92 java.io.IOException -> L94
            java.io.InputStream r3 = r5.getResourceAsStream(r3)     // Catch: java.lang.Throwable -> L92 java.io.IOException -> L94
            if (r3 == 0) goto L6b
            r2.load(r3)     // Catch: java.lang.Throwable -> L8a java.io.IOException -> L8d
            java.lang.String r5 = "version"
            java.lang.String r4 = r2.getProperty(r5, r4)     // Catch: java.lang.Throwable -> L8a java.io.IOException -> L8d
            com.google.android.gms.common.internal.GmsLogger r2 = com.google.android.gms.common.internal.LibraryVersion.zza     // Catch: java.lang.Throwable -> L8a java.io.IOException -> L8d
            java.lang.String r5 = java.lang.String.valueOf(r10)     // Catch: java.lang.Throwable -> L8a java.io.IOException -> L8d
            int r5 = r5.length()     // Catch: java.lang.Throwable -> L8a java.io.IOException -> L8d
            int r5 = r5 + 12
            java.lang.String r6 = java.lang.String.valueOf(r4)     // Catch: java.lang.Throwable -> L8a java.io.IOException -> L8d
            int r6 = r6.length()     // Catch: java.lang.Throwable -> L8a java.io.IOException -> L8d
            int r5 = r5 + r6
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L8a java.io.IOException -> L8d
            r6.<init>(r5)     // Catch: java.lang.Throwable -> L8a java.io.IOException -> L8d
            r6.append(r10)     // Catch: java.lang.Throwable -> L8a java.io.IOException -> L8d
            java.lang.String r5 = " version is "
            r6.append(r5)     // Catch: java.lang.Throwable -> L8a java.io.IOException -> L8d
            r6.append(r4)     // Catch: java.lang.Throwable -> L8a java.io.IOException -> L8d
            java.lang.String r5 = r6.toString()     // Catch: java.lang.Throwable -> L8a java.io.IOException -> L8d
            r2.v(r1, r5)     // Catch: java.lang.Throwable -> L8a java.io.IOException -> L8d
            goto L84
        L6b:
            com.google.android.gms.common.internal.GmsLogger r2 = com.google.android.gms.common.internal.LibraryVersion.zza     // Catch: java.lang.Throwable -> L8a java.io.IOException -> L8d
            java.lang.String r5 = java.lang.String.valueOf(r10)     // Catch: java.lang.Throwable -> L8a java.io.IOException -> L8d
            int r6 = r5.length()     // Catch: java.lang.Throwable -> L8a java.io.IOException -> L8d
            if (r6 == 0) goto L7c
            java.lang.String r5 = r0.concat(r5)     // Catch: java.lang.Throwable -> L8a java.io.IOException -> L8d
            goto L81
        L7c:
            java.lang.String r5 = new java.lang.String     // Catch: java.lang.Throwable -> L8a java.io.IOException -> L8d
            r5.<init>(r0)     // Catch: java.lang.Throwable -> L8a java.io.IOException -> L8d
        L81:
            r2.w(r1, r5)     // Catch: java.lang.Throwable -> L8a java.io.IOException -> L8d
        L84:
            if (r3 == 0) goto Lb6
            com.google.android.gms.common.util.IOUtils.closeQuietly(r3)
            goto Lb6
        L8a:
            r10 = move-exception
            r4 = r3
            goto Lc7
        L8d:
            r2 = move-exception
            r8 = r4
            r4 = r3
            r3 = r8
            goto L96
        L92:
            r10 = move-exception
            goto Lc7
        L94:
            r2 = move-exception
            r3 = r4
        L96:
            com.google.android.gms.common.internal.GmsLogger r5 = com.google.android.gms.common.internal.LibraryVersion.zza     // Catch: java.lang.Throwable -> L92
            java.lang.String r6 = java.lang.String.valueOf(r10)     // Catch: java.lang.Throwable -> L92
            int r7 = r6.length()     // Catch: java.lang.Throwable -> L92
            if (r7 == 0) goto La7
            java.lang.String r0 = r0.concat(r6)     // Catch: java.lang.Throwable -> L92
            goto Lad
        La7:
            java.lang.String r6 = new java.lang.String     // Catch: java.lang.Throwable -> L92
            r6.<init>(r0)     // Catch: java.lang.Throwable -> L92
            r0 = r6
        Lad:
            r5.e(r1, r0, r2)     // Catch: java.lang.Throwable -> L92
            if (r4 == 0) goto Lb5
            com.google.android.gms.common.util.IOUtils.closeQuietly(r4)
        Lb5:
            r4 = r3
        Lb6:
            if (r4 != 0) goto Lc1
            com.google.android.gms.common.internal.GmsLogger r0 = com.google.android.gms.common.internal.LibraryVersion.zza
            java.lang.String r2 = ".properties file is dropped during release process. Failure to read app version is expected during Google internal testing where locally-built libraries are used"
            r0.d(r1, r2)
            java.lang.String r4 = "UNKNOWN"
        Lc1:
            java.util.concurrent.ConcurrentHashMap<java.lang.String, java.lang.String> r0 = r9.zzc
            r0.put(r10, r4)
            return r4
        Lc7:
            if (r4 == 0) goto Lcc
            com.google.android.gms.common.util.IOUtils.closeQuietly(r4)
        Lcc:
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.internal.LibraryVersion.getVersion(java.lang.String):java.lang.String");
    }
}
