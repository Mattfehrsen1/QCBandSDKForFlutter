package androidx.startup;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import androidx.tracing.Trace;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/* loaded from: classes.dex */
public final class AppInitializer {
    private static final String SECTION_NAME = "Startup";
    private static volatile AppInitializer sInstance;
    private static final Object sLock = new Object();
    final Context mContext;
    final Set<Class<? extends Initializer<?>>> mDiscovered = new HashSet();
    final Map<Class<?>, Object> mInitialized = new HashMap();

    AppInitializer(Context context) {
        this.mContext = context.getApplicationContext();
    }

    public static AppInitializer getInstance(Context context) {
        if (sInstance == null) {
            synchronized (sLock) {
                if (sInstance == null) {
                    sInstance = new AppInitializer(context);
                }
            }
        }
        return sInstance;
    }

    public <T> T initializeComponent(Class<? extends Initializer<T>> cls) {
        return (T) doInitialize(cls, new HashSet());
    }

    public boolean isEagerlyInitialized(Class<? extends Initializer<?>> cls) {
        return this.mDiscovered.contains(cls);
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x0077 A[Catch: all -> 0x008c, TRY_ENTER, TryCatch #2 {all -> 0x008c, blocks: (B:6:0x0009, B:7:0x0010, B:9:0x0017, B:11:0x001f, B:23:0x0066, B:24:0x006b, B:25:0x006c, B:29:0x0077, B:30:0x008b, B:12:0x0022, B:14:0x003a, B:15:0x003e, B:17:0x0044, B:19:0x0052, B:20:0x0056), top: B:40:0x0009, outer: #0, inners: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0017 A[Catch: all -> 0x008c, TryCatch #2 {all -> 0x008c, blocks: (B:6:0x0009, B:7:0x0010, B:9:0x0017, B:11:0x001f, B:23:0x0066, B:24:0x006b, B:25:0x006c, B:29:0x0077, B:30:0x008b, B:12:0x0022, B:14:0x003a, B:15:0x003e, B:17:0x0044, B:19:0x0052, B:20:0x0056), top: B:40:0x0009, outer: #0, inners: #1 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    <T> T doInitialize(java.lang.Class<? extends androidx.startup.Initializer<?>> r6, java.util.Set<java.lang.Class<?>> r7) {
        /*
            r5 = this;
            java.lang.Object r0 = androidx.startup.AppInitializer.sLock
            monitor-enter(r0)
            boolean r1 = androidx.tracing.Trace.isEnabled()     // Catch: java.lang.Throwable -> L91
            if (r1 == 0) goto L10
            java.lang.String r1 = r6.getSimpleName()     // Catch: java.lang.Throwable -> L8c
            androidx.tracing.Trace.beginSection(r1)     // Catch: java.lang.Throwable -> L8c
        L10:
            boolean r1 = r7.contains(r6)     // Catch: java.lang.Throwable -> L8c
            r2 = 0
            if (r1 != 0) goto L77
            java.util.Map<java.lang.Class<?>, java.lang.Object> r1 = r5.mInitialized     // Catch: java.lang.Throwable -> L8c
            boolean r1 = r1.containsKey(r6)     // Catch: java.lang.Throwable -> L8c
            if (r1 != 0) goto L6c
            r7.add(r6)     // Catch: java.lang.Throwable -> L8c
            java.lang.Class[] r1 = new java.lang.Class[r2]     // Catch: java.lang.Throwable -> L65
            java.lang.reflect.Constructor r1 = r6.getDeclaredConstructor(r1)     // Catch: java.lang.Throwable -> L65
            java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch: java.lang.Throwable -> L65
            java.lang.Object r1 = r1.newInstance(r2)     // Catch: java.lang.Throwable -> L65
            androidx.startup.Initializer r1 = (androidx.startup.Initializer) r1     // Catch: java.lang.Throwable -> L65
            java.util.List r2 = r1.dependencies()     // Catch: java.lang.Throwable -> L65
            boolean r3 = r2.isEmpty()     // Catch: java.lang.Throwable -> L65
            if (r3 != 0) goto L56
            java.util.Iterator r2 = r2.iterator()     // Catch: java.lang.Throwable -> L65
        L3e:
            boolean r3 = r2.hasNext()     // Catch: java.lang.Throwable -> L65
            if (r3 == 0) goto L56
            java.lang.Object r3 = r2.next()     // Catch: java.lang.Throwable -> L65
            java.lang.Class r3 = (java.lang.Class) r3     // Catch: java.lang.Throwable -> L65
            java.util.Map<java.lang.Class<?>, java.lang.Object> r4 = r5.mInitialized     // Catch: java.lang.Throwable -> L65
            boolean r4 = r4.containsKey(r3)     // Catch: java.lang.Throwable -> L65
            if (r4 != 0) goto L3e
            r5.doInitialize(r3, r7)     // Catch: java.lang.Throwable -> L65
            goto L3e
        L56:
            android.content.Context r2 = r5.mContext     // Catch: java.lang.Throwable -> L65
            java.lang.Object r1 = r1.create(r2)     // Catch: java.lang.Throwable -> L65
            r7.remove(r6)     // Catch: java.lang.Throwable -> L65
            java.util.Map<java.lang.Class<?>, java.lang.Object> r7 = r5.mInitialized     // Catch: java.lang.Throwable -> L65
            r7.put(r6, r1)     // Catch: java.lang.Throwable -> L65
            goto L72
        L65:
            r6 = move-exception
            androidx.startup.StartupException r7 = new androidx.startup.StartupException     // Catch: java.lang.Throwable -> L8c
            r7.<init>(r6)     // Catch: java.lang.Throwable -> L8c
            throw r7     // Catch: java.lang.Throwable -> L8c
        L6c:
            java.util.Map<java.lang.Class<?>, java.lang.Object> r7 = r5.mInitialized     // Catch: java.lang.Throwable -> L8c
            java.lang.Object r1 = r7.get(r6)     // Catch: java.lang.Throwable -> L8c
        L72:
            androidx.tracing.Trace.endSection()     // Catch: java.lang.Throwable -> L91
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L91
            return r1
        L77:
            java.lang.String r7 = "Cannot initialize %s. Cycle detected."
            r1 = 1
            java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch: java.lang.Throwable -> L8c
            java.lang.String r6 = r6.getName()     // Catch: java.lang.Throwable -> L8c
            r1[r2] = r6     // Catch: java.lang.Throwable -> L8c
            java.lang.String r6 = java.lang.String.format(r7, r1)     // Catch: java.lang.Throwable -> L8c
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException     // Catch: java.lang.Throwable -> L8c
            r7.<init>(r6)     // Catch: java.lang.Throwable -> L8c
            throw r7     // Catch: java.lang.Throwable -> L8c
        L8c:
            r6 = move-exception
            androidx.tracing.Trace.endSection()     // Catch: java.lang.Throwable -> L91
            throw r6     // Catch: java.lang.Throwable -> L91
        L91:
            r6 = move-exception
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L91
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.startup.AppInitializer.doInitialize(java.lang.Class, java.util.Set):java.lang.Object");
    }

    /* JADX WARN: Multi-variable type inference failed */
    void discoverAndInitialize() {
        try {
            try {
                Trace.beginSection(SECTION_NAME);
                Bundle bundle = this.mContext.getPackageManager().getProviderInfo(new ComponentName(this.mContext.getPackageName(), InitializationProvider.class.getName()), 128).metaData;
                String string = this.mContext.getString(R.string.androidx_startup);
                if (bundle != null) {
                    HashSet hashSet = new HashSet();
                    for (String str : bundle.keySet()) {
                        if (string.equals(bundle.getString(str, null))) {
                            Class<?> cls = Class.forName(str);
                            if (Initializer.class.isAssignableFrom(cls)) {
                                this.mDiscovered.add(cls);
                                doInitialize(cls, hashSet);
                            }
                        }
                    }
                }
            } finally {
                Trace.endSection();
            }
        } catch (PackageManager.NameNotFoundException | ClassNotFoundException e) {
            throw new StartupException(e);
        }
    }
}
