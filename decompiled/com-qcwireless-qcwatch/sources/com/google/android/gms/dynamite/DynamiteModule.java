package com.google.android.gms.dynamite;

import android.content.Context;
import android.database.Cursor;
import android.os.IBinder;
import android.os.IInterface;
import android.util.Log;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/* compiled from: com.google.android.gms:play-services-basement@@18.0.0 */
/* loaded from: classes2.dex */
public final class DynamiteModule {
    private static Boolean zzb = null;
    private static String zzc = null;
    private static boolean zzd = false;
    private static int zze = -1;
    private static zzq zzj;
    private static zzr zzk;
    private final Context zzi;
    private static final ThreadLocal<zzn> zzf = new ThreadLocal<>();
    private static final ThreadLocal<Long> zzg = new zzd();
    private static final VersionPolicy.IVersions zzh = new zze();
    public static final VersionPolicy PREFER_REMOTE = new zzf();
    public static final VersionPolicy PREFER_LOCAL = new zzg();
    public static final VersionPolicy PREFER_REMOTE_VERSION_NO_FORCE_STAGING = new zzh();
    public static final VersionPolicy PREFER_HIGHEST_OR_LOCAL_VERSION = new zzi();
    public static final VersionPolicy PREFER_HIGHEST_OR_LOCAL_VERSION_NO_FORCE_STAGING = new zzj();
    public static final VersionPolicy PREFER_HIGHEST_OR_REMOTE_VERSION = new zzk();
    public static final VersionPolicy zza = new zzl();

    /* compiled from: com.google.android.gms:play-services-basement@@18.0.0 */
    public static class DynamiteLoaderClassLoader {
        public static ClassLoader sClassLoader;
    }

    /* compiled from: com.google.android.gms:play-services-basement@@18.0.0 */
    public static class LoadingException extends Exception {
        /* synthetic */ LoadingException(String str, zzp zzpVar) {
            super(str);
        }

        /* synthetic */ LoadingException(String str, Throwable th, zzp zzpVar) {
            super(str, th);
        }
    }

    /* compiled from: com.google.android.gms:play-services-basement@@18.0.0 */
    public interface VersionPolicy {

        /* compiled from: com.google.android.gms:play-services-basement@@18.0.0 */
        public interface IVersions {
            int zza(Context context, String str);

            int zzb(Context context, String str, boolean z) throws LoadingException;
        }

        /* compiled from: com.google.android.gms:play-services-basement@@18.0.0 */
        public static class SelectionResult {
            public int localVersion = 0;
            public int remoteVersion = 0;
            public int selection = 0;
        }

        SelectionResult selectModule(Context context, String str, IVersions iVersions) throws LoadingException;
    }

    private DynamiteModule(Context context) {
        Preconditions.checkNotNull(context);
        this.zzi = context;
    }

    public static int getLocalVersion(Context context, String str) throws NoSuchFieldException, ClassNotFoundException {
        try {
            ClassLoader classLoader = context.getApplicationContext().getClassLoader();
            StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 61);
            sb.append("com.google.android.gms.dynamite.descriptors.");
            sb.append(str);
            sb.append(".");
            sb.append("ModuleDescriptor");
            Class<?> clsLoadClass = classLoader.loadClass(sb.toString());
            Field declaredField = clsLoadClass.getDeclaredField("MODULE_ID");
            Field declaredField2 = clsLoadClass.getDeclaredField("MODULE_VERSION");
            if (Objects.equal(declaredField.get(null), str)) {
                return declaredField2.getInt(null);
            }
            String strValueOf = String.valueOf(declaredField.get(null));
            StringBuilder sb2 = new StringBuilder(String.valueOf(strValueOf).length() + 51 + String.valueOf(str).length());
            sb2.append("Module descriptor id '");
            sb2.append(strValueOf);
            sb2.append("' didn't match expected id '");
            sb2.append(str);
            sb2.append("'");
            Log.e("DynamiteModule", sb2.toString());
            return 0;
        } catch (ClassNotFoundException unused) {
            StringBuilder sb3 = new StringBuilder(String.valueOf(str).length() + 45);
            sb3.append("Local module descriptor class for ");
            sb3.append(str);
            sb3.append(" not found.");
            Log.w("DynamiteModule", sb3.toString());
            return 0;
        } catch (Exception e) {
            String strValueOf2 = String.valueOf(e.getMessage());
            Log.e("DynamiteModule", strValueOf2.length() != 0 ? "Failed to load module descriptor class: ".concat(strValueOf2) : new String("Failed to load module descriptor class: "));
            return 0;
        }
    }

    public static int getRemoteVersion(Context context, String str) {
        return zza(context, str, false);
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0091 A[Catch: all -> 0x0305, TRY_LEAVE, TryCatch #3 {all -> 0x0305, blocks: (B:3:0x0025, B:7:0x0083, B:12:0x008b, B:15:0x0091, B:26:0x00b3, B:103:0x0233, B:104:0x023e, B:106:0x0240, B:108:0x0242, B:109:0x024a, B:131:0x02b0, B:132:0x02c9, B:111:0x024c, B:113:0x025e, B:115:0x0269, B:117:0x0270, B:119:0x0281, B:129:0x02a7, B:130:0x02af, B:114:0x0263, B:133:0x02ca, B:134:0x0304), top: B:146:0x0025, inners: #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:25:0x00b1  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static com.google.android.gms.dynamite.DynamiteModule load(android.content.Context r19, com.google.android.gms.dynamite.DynamiteModule.VersionPolicy r20, java.lang.String r21) throws com.google.android.gms.dynamite.DynamiteModule.LoadingException {
        /*
            Method dump skipped, instructions count: 808
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.dynamite.DynamiteModule.load(android.content.Context, com.google.android.gms.dynamite.DynamiteModule$VersionPolicy, java.lang.String):com.google.android.gms.dynamite.DynamiteModule");
    }

    /* JADX WARN: Removed duplicated region for block: B:94:0x0169 A[Catch: all -> 0x01ca, TRY_ENTER, TRY_LEAVE, TryCatch #5 {all -> 0x01ca, blocks: (B:3:0x0002, B:56:0x00d6, B:59:0x00dd, B:68:0x0103, B:90:0x015b, B:94:0x0169, B:119:0x01c3, B:120:0x01c6, B:114:0x01bb, B:62:0x00e3, B:64:0x00f5, B:66:0x00ff, B:65:0x00fa, B:123:0x01c9, B:4:0x0003, B:7:0x0008, B:8:0x0025, B:54:0x00d3, B:36:0x008f, B:39:0x0092, B:47:0x00ab, B:55:0x00d5, B:53:0x00b1), top: B:134:0x0002, inners: #1, #3 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static int zza(android.content.Context r9, java.lang.String r10, boolean r11) {
        /*
            Method dump skipped, instructions count: 463
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.dynamite.DynamiteModule.zza(android.content.Context, java.lang.String, boolean):int");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:54:0x00ce  */
    /* JADX WARN: Type inference failed for: r0v0 */
    /* JADX WARN: Type inference failed for: r0v1, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r0v2 */
    /* JADX WARN: Type inference failed for: r0v3 */
    /* JADX WARN: Type inference failed for: r0v4 */
    /* JADX WARN: Type inference failed for: r0v5 */
    /* JADX WARN: Type inference failed for: r0v6 */
    /* JADX WARN: Type inference failed for: r0v7 */
    /* JADX WARN: Type inference failed for: r0v8 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static int zzb(android.content.Context r10, java.lang.String r11, boolean r12) throws java.lang.Throwable {
        /*
            r0 = 0
            java.lang.ThreadLocal<java.lang.Long> r1 = com.google.android.gms.dynamite.DynamiteModule.zzg     // Catch: java.lang.Throwable -> Lb7 java.lang.Exception -> Lba
            java.lang.Object r1 = r1.get()     // Catch: java.lang.Throwable -> Lb7 java.lang.Exception -> Lba
            java.lang.Long r1 = (java.lang.Long) r1     // Catch: java.lang.Throwable -> Lb7 java.lang.Exception -> Lba
            long r1 = r1.longValue()     // Catch: java.lang.Throwable -> Lb7 java.lang.Exception -> Lba
            android.content.ContentResolver r3 = r10.getContentResolver()     // Catch: java.lang.Throwable -> Lb7 java.lang.Exception -> Lba
            java.lang.String r10 = "api_force_staging"
            java.lang.String r4 = "api"
            r9 = 1
            if (r9 == r12) goto L1b
            r10 = r4
        L1b:
            android.net.Uri$Builder r12 = new android.net.Uri$Builder     // Catch: java.lang.Throwable -> Lb7 java.lang.Exception -> Lba
            r12.<init>()     // Catch: java.lang.Throwable -> Lb7 java.lang.Exception -> Lba
            java.lang.String r4 = "content"
            android.net.Uri$Builder r12 = r12.scheme(r4)     // Catch: java.lang.Throwable -> Lb7 java.lang.Exception -> Lba
            java.lang.String r4 = "com.google.android.gms.chimera"
            android.net.Uri$Builder r12 = r12.authority(r4)     // Catch: java.lang.Throwable -> Lb7 java.lang.Exception -> Lba
            android.net.Uri$Builder r10 = r12.path(r10)     // Catch: java.lang.Throwable -> Lb7 java.lang.Exception -> Lba
            android.net.Uri$Builder r10 = r10.appendPath(r11)     // Catch: java.lang.Throwable -> Lb7 java.lang.Exception -> Lba
            java.lang.String r11 = "requestStartTime"
            java.lang.String r12 = java.lang.String.valueOf(r1)     // Catch: java.lang.Throwable -> Lb7 java.lang.Exception -> Lba
            android.net.Uri$Builder r10 = r10.appendQueryParameter(r11, r12)     // Catch: java.lang.Throwable -> Lb7 java.lang.Exception -> Lba
            android.net.Uri r4 = r10.build()     // Catch: java.lang.Throwable -> Lb7 java.lang.Exception -> Lba
            r5 = 0
            r6 = 0
            r7 = 0
            r8 = 0
            android.database.Cursor r10 = r3.query(r4, r5, r6, r7, r8)     // Catch: java.lang.Throwable -> Lb7 java.lang.Exception -> Lba
            if (r10 == 0) goto La8
            boolean r11 = r10.moveToFirst()     // Catch: java.lang.Exception -> La6 java.lang.Throwable -> Lca
            if (r11 == 0) goto La8
            r11 = 0
            int r12 = r10.getInt(r11)     // Catch: java.lang.Exception -> La6 java.lang.Throwable -> Lca
            if (r12 <= 0) goto L95
            java.lang.Class<com.google.android.gms.dynamite.DynamiteModule> r1 = com.google.android.gms.dynamite.DynamiteModule.class
            monitor-enter(r1)     // Catch: java.lang.Exception -> La6 java.lang.Throwable -> Lca
            r2 = 2
            java.lang.String r2 = r10.getString(r2)     // Catch: java.lang.Throwable -> L92
            com.google.android.gms.dynamite.DynamiteModule.zzc = r2     // Catch: java.lang.Throwable -> L92
            java.lang.String r2 = "loaderVersion"
            int r2 = r10.getColumnIndex(r2)     // Catch: java.lang.Throwable -> L92
            if (r2 < 0) goto L75
            int r2 = r10.getInt(r2)     // Catch: java.lang.Throwable -> L92
            com.google.android.gms.dynamite.DynamiteModule.zze = r2     // Catch: java.lang.Throwable -> L92
        L75:
            java.lang.String r2 = "disableStandaloneDynamiteLoader"
            int r2 = r10.getColumnIndex(r2)     // Catch: java.lang.Throwable -> L92
            if (r2 < 0) goto L89
            int r2 = r10.getInt(r2)     // Catch: java.lang.Throwable -> L92
            if (r2 == 0) goto L85
            goto L86
        L85:
            r9 = 0
        L86:
            com.google.android.gms.dynamite.DynamiteModule.zzd = r9     // Catch: java.lang.Throwable -> L92
            r11 = r9
        L89:
            monitor-exit(r1)     // Catch: java.lang.Throwable -> L92
            boolean r1 = zze(r10)     // Catch: java.lang.Exception -> La6 java.lang.Throwable -> Lca
            if (r1 == 0) goto L95
            r10 = r0
            goto L95
        L92:
            r11 = move-exception
            monitor-exit(r1)     // Catch: java.lang.Throwable -> L92
            throw r11     // Catch: java.lang.Exception -> La6 java.lang.Throwable -> Lca
        L95:
            if (r11 != 0) goto L9d
            if (r10 == 0) goto L9c
            r10.close()
        L9c:
            return r12
        L9d:
            com.google.android.gms.dynamite.DynamiteModule$LoadingException r11 = new com.google.android.gms.dynamite.DynamiteModule$LoadingException     // Catch: java.lang.Exception -> La6 java.lang.Throwable -> Lca
            java.lang.String r12 = "forcing fallback to container DynamiteLoader impl"
            r11.<init>(r12, r0)     // Catch: java.lang.Exception -> La6 java.lang.Throwable -> Lca
            throw r11     // Catch: java.lang.Exception -> La6 java.lang.Throwable -> Lca
        La6:
            r11 = move-exception
            goto Lbd
        La8:
            java.lang.String r11 = "DynamiteModule"
            java.lang.String r12 = "Failed to retrieve remote module version."
            android.util.Log.w(r11, r12)     // Catch: java.lang.Exception -> La6 java.lang.Throwable -> Lca
            com.google.android.gms.dynamite.DynamiteModule$LoadingException r11 = new com.google.android.gms.dynamite.DynamiteModule$LoadingException     // Catch: java.lang.Exception -> La6 java.lang.Throwable -> Lca
            java.lang.String r12 = "Failed to connect to dynamite module ContentResolver."
            r11.<init>(r12, r0)     // Catch: java.lang.Exception -> La6 java.lang.Throwable -> Lca
            throw r11     // Catch: java.lang.Exception -> La6 java.lang.Throwable -> Lca
        Lb7:
            r10 = move-exception
            r11 = r10
            goto Lcc
        Lba:
            r10 = move-exception
            r11 = r10
            r10 = r0
        Lbd:
            boolean r12 = r11 instanceof com.google.android.gms.dynamite.DynamiteModule.LoadingException     // Catch: java.lang.Throwable -> Lca
            if (r12 == 0) goto Lc2
            throw r11     // Catch: java.lang.Throwable -> Lca
        Lc2:
            com.google.android.gms.dynamite.DynamiteModule$LoadingException r12 = new com.google.android.gms.dynamite.DynamiteModule$LoadingException     // Catch: java.lang.Throwable -> Lca
            java.lang.String r1 = "V2 version check failed"
            r12.<init>(r1, r11, r0)     // Catch: java.lang.Throwable -> Lca
            throw r12     // Catch: java.lang.Throwable -> Lca
        Lca:
            r11 = move-exception
            r0 = r10
        Lcc:
            if (r0 == 0) goto Ld1
            r0.close()
        Ld1:
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.dynamite.DynamiteModule.zzb(android.content.Context, java.lang.String, boolean):int");
    }

    private static DynamiteModule zzc(Context context, String str) {
        String strValueOf = String.valueOf(str);
        Log.i("DynamiteModule", strValueOf.length() != 0 ? "Selected local version of ".concat(strValueOf) : new String("Selected local version of "));
        return new DynamiteModule(context.getApplicationContext());
    }

    private static void zzd(ClassLoader classLoader) throws LoadingException {
        zzr zzrVar;
        zzp zzpVar = null;
        try {
            IBinder iBinder = (IBinder) classLoader.loadClass("com.google.android.gms.dynamiteloader.DynamiteLoaderV2").getConstructor(new Class[0]).newInstance(new Object[0]);
            if (iBinder == null) {
                zzrVar = null;
            } else {
                IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.dynamite.IDynamiteLoaderV2");
                zzrVar = iInterfaceQueryLocalInterface instanceof zzr ? (zzr) iInterfaceQueryLocalInterface : new zzr(iBinder);
            }
            zzk = zzrVar;
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
            throw new LoadingException("Failed to instantiate dynamite loader", e, zzpVar);
        }
    }

    private static boolean zze(Cursor cursor) {
        zzn zznVar = zzf.get();
        if (zznVar == null || zznVar.zza != null) {
            return false;
        }
        zznVar.zza = cursor;
        return true;
    }

    private static zzq zzf(Context context) {
        zzq zzqVar;
        synchronized (DynamiteModule.class) {
            zzq zzqVar2 = zzj;
            if (zzqVar2 != null) {
                return zzqVar2;
            }
            try {
                IBinder iBinder = (IBinder) context.createPackageContext("com.google.android.gms", 3).getClassLoader().loadClass("com.google.android.gms.chimera.container.DynamiteLoaderImpl").newInstance();
                if (iBinder == null) {
                    zzqVar = null;
                } else {
                    IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.dynamite.IDynamiteLoader");
                    zzqVar = iInterfaceQueryLocalInterface instanceof zzq ? (zzq) iInterfaceQueryLocalInterface : new zzq(iBinder);
                }
                if (zzqVar != null) {
                    zzj = zzqVar;
                    return zzqVar;
                }
            } catch (Exception e) {
                String strValueOf = String.valueOf(e.getMessage());
                Log.e("DynamiteModule", strValueOf.length() != 0 ? "Failed to load IDynamiteLoader from GmsCore: ".concat(strValueOf) : new String("Failed to load IDynamiteLoader from GmsCore: "));
            }
            return null;
        }
    }

    public Context getModuleContext() {
        return this.zzi;
    }

    public IBinder instantiate(String str) throws LoadingException {
        try {
            return (IBinder) this.zzi.getClassLoader().loadClass(str).newInstance();
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            String strValueOf = String.valueOf(str);
            throw new LoadingException(strValueOf.length() != 0 ? "Failed to instantiate module class: ".concat(strValueOf) : new String("Failed to instantiate module class: "), e, null);
        }
    }
}
