package com.google.android.gms.maps;

import android.content.Context;

/* compiled from: com.google.android.gms:play-services-maps@@18.1.0 */
/* loaded from: classes2.dex */
public final class MapsInitializer {
    private static final String zza = "MapsInitializer";
    private static boolean zzb = false;
    private static Renderer zzc = Renderer.LEGACY;

    /* compiled from: com.google.android.gms:play-services-maps@@18.1.0 */
    public enum Renderer {
        LEGACY,
        LATEST
    }

    private MapsInitializer() {
    }

    public static synchronized int initialize(Context context) {
        return initialize(context, null, null);
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x004d  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0054 A[Catch: RemoteException -> 0x0060, all -> 0x0093, TryCatch #0 {RemoteException -> 0x0060, blocks: (B:21:0x004e, B:23:0x0054, B:24:0x0058), top: B:43:0x004e, outer: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0080 A[Catch: all -> 0x0093, TRY_LEAVE, TryCatch #1 {, blocks: (B:4:0x0003, B:7:0x0023, B:10:0x002a, B:11:0x002e, B:13:0x003d, B:15:0x0042, B:21:0x004e, B:23:0x0054, B:24:0x0058, B:28:0x0068, B:30:0x0080, B:27:0x0061, B:34:0x0088, B:35:0x008d, B:37:0x008f), top: B:45:0x0003, inners: #0, #2, #3 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static synchronized int initialize(android.content.Context r5, com.google.android.gms.maps.MapsInitializer.Renderer r6, com.google.android.gms.maps.OnMapsSdkInitializedCallback r7) {
        /*
            java.lang.Class<com.google.android.gms.maps.MapsInitializer> r0 = com.google.android.gms.maps.MapsInitializer.class
            monitor-enter(r0)
            java.lang.String r1 = "Context is null"
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r5, r1)     // Catch: java.lang.Throwable -> L93
            java.lang.String r1 = com.google.android.gms.maps.MapsInitializer.zza     // Catch: java.lang.Throwable -> L93
            java.lang.String r2 = "preferredRenderer: "
            java.lang.String r3 = java.lang.String.valueOf(r6)     // Catch: java.lang.Throwable -> L93
            java.lang.String r3 = java.lang.String.valueOf(r3)     // Catch: java.lang.Throwable -> L93
            java.lang.String r2 = r2.concat(r3)     // Catch: java.lang.Throwable -> L93
            android.util.Log.d(r1, r2)     // Catch: java.lang.Throwable -> L93
            boolean r1 = com.google.android.gms.maps.MapsInitializer.zzb     // Catch: java.lang.Throwable -> L93
            r2 = 0
            if (r1 == 0) goto L2a
            if (r7 == 0) goto L28
            com.google.android.gms.maps.MapsInitializer$Renderer r5 = com.google.android.gms.maps.MapsInitializer.zzc     // Catch: java.lang.Throwable -> L93
            r7.onMapsSdkInitialized(r5)     // Catch: java.lang.Throwable -> L93
        L28:
            monitor-exit(r0)
            return r2
        L2a:
            com.google.android.gms.maps.internal.zzf r1 = com.google.android.gms.maps.internal.zzcb.zza(r5, r6)     // Catch: com.google.android.gms.common.GooglePlayServicesNotAvailableException -> L8e java.lang.Throwable -> L93
            com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate r3 = r1.zze()     // Catch: android.os.RemoteException -> L87 java.lang.Throwable -> L93
            com.google.android.gms.maps.CameraUpdateFactory.zza(r3)     // Catch: android.os.RemoteException -> L87 java.lang.Throwable -> L93
            com.google.android.gms.internal.maps.zzi r3 = r1.zzj()     // Catch: android.os.RemoteException -> L87 java.lang.Throwable -> L93
            com.google.android.gms.maps.model.BitmapDescriptorFactory.zza(r3)     // Catch: android.os.RemoteException -> L87 java.lang.Throwable -> L93
            r3 = 1
            com.google.android.gms.maps.MapsInitializer.zzb = r3     // Catch: java.lang.Throwable -> L93
            r4 = 2
            if (r6 == 0) goto L4d
            int r6 = r6.ordinal()     // Catch: java.lang.Throwable -> L93
            if (r6 == 0) goto L4e
            if (r6 == r3) goto L4b
            goto L4d
        L4b:
            r3 = 2
            goto L4e
        L4d:
            r3 = 0
        L4e:
            int r6 = r1.zzd()     // Catch: android.os.RemoteException -> L60 java.lang.Throwable -> L93
            if (r6 != r4) goto L58
            com.google.android.gms.maps.MapsInitializer$Renderer r6 = com.google.android.gms.maps.MapsInitializer.Renderer.LATEST     // Catch: android.os.RemoteException -> L60 java.lang.Throwable -> L93
            com.google.android.gms.maps.MapsInitializer.zzc = r6     // Catch: android.os.RemoteException -> L60 java.lang.Throwable -> L93
        L58:
            com.google.android.gms.dynamic.IObjectWrapper r5 = com.google.android.gms.dynamic.ObjectWrapper.wrap(r5)     // Catch: android.os.RemoteException -> L60 java.lang.Throwable -> L93
            r1.zzl(r5, r3)     // Catch: android.os.RemoteException -> L60 java.lang.Throwable -> L93
            goto L68
        L60:
            r5 = move-exception
            java.lang.String r6 = com.google.android.gms.maps.MapsInitializer.zza     // Catch: java.lang.Throwable -> L93
            java.lang.String r1 = "Failed to retrieve renderer type or log initialization."
            android.util.Log.e(r6, r1, r5)     // Catch: java.lang.Throwable -> L93
        L68:
            java.lang.String r5 = com.google.android.gms.maps.MapsInitializer.zza     // Catch: java.lang.Throwable -> L93
            java.lang.String r6 = "loadedRenderer: "
            com.google.android.gms.maps.MapsInitializer$Renderer r1 = com.google.android.gms.maps.MapsInitializer.zzc     // Catch: java.lang.Throwable -> L93
            java.lang.String r1 = java.lang.String.valueOf(r1)     // Catch: java.lang.Throwable -> L93
            java.lang.String r1 = java.lang.String.valueOf(r1)     // Catch: java.lang.Throwable -> L93
            java.lang.String r6 = r6.concat(r1)     // Catch: java.lang.Throwable -> L93
            android.util.Log.d(r5, r6)     // Catch: java.lang.Throwable -> L93
            if (r7 == 0) goto L85
            com.google.android.gms.maps.MapsInitializer$Renderer r5 = com.google.android.gms.maps.MapsInitializer.zzc     // Catch: java.lang.Throwable -> L93
            r7.onMapsSdkInitialized(r5)     // Catch: java.lang.Throwable -> L93
        L85:
            monitor-exit(r0)
            return r2
        L87:
            r5 = move-exception
            com.google.android.gms.maps.model.RuntimeRemoteException r6 = new com.google.android.gms.maps.model.RuntimeRemoteException     // Catch: java.lang.Throwable -> L93
            r6.<init>(r5)     // Catch: java.lang.Throwable -> L93
            throw r6     // Catch: java.lang.Throwable -> L93
        L8e:
            r5 = move-exception
            int r5 = r5.errorCode     // Catch: java.lang.Throwable -> L93
            monitor-exit(r0)
            return r5
        L93:
            r5 = move-exception
            monitor-exit(r0)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.maps.MapsInitializer.initialize(android.content.Context, com.google.android.gms.maps.MapsInitializer$Renderer, com.google.android.gms.maps.OnMapsSdkInitializedCallback):int");
    }
}
