package com.google.android.gms.maps.internal;

import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.RuntimeRemoteException;

/* compiled from: com.google.android.gms:play-services-maps@@18.1.0 */
/* loaded from: classes2.dex */
public final class zzcb {
    private static final String zza = "zzcb";
    private static Context zzb;
    private static zzf zzc;

    public static zzf zza(Context context, MapsInitializer.Renderer renderer) throws GooglePlayServicesNotAvailableException {
        zzf zzeVar;
        Preconditions.checkNotNull(context);
        String str = zza;
        Log.d(str, "preferredRenderer: ".concat(String.valueOf(String.valueOf(renderer))));
        zzf zzfVar = zzc;
        if (zzfVar != null) {
            return zzfVar;
        }
        int iIsGooglePlayServicesAvailable = GooglePlayServicesUtil.isGooglePlayServicesAvailable(context, 13400000);
        if (iIsGooglePlayServicesAvailable != 0) {
            throw new GooglePlayServicesNotAvailableException(iIsGooglePlayServicesAvailable);
        }
        Log.i(str, "Making Creator dynamically");
        try {
            IBinder iBinder = (IBinder) zzd(((ClassLoader) Preconditions.checkNotNull(zzc(context, renderer).getClassLoader())).loadClass("com.google.android.gms.maps.internal.CreatorImpl"));
            if (iBinder == null) {
                zzeVar = null;
            } else {
                IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.maps.internal.ICreator");
                zzeVar = iInterfaceQueryLocalInterface instanceof zzf ? (zzf) iInterfaceQueryLocalInterface : new zze(iBinder);
            }
            zzc = zzeVar;
            try {
                Context contextZzc = zzc(context, renderer);
                contextZzc.getClass();
                zzeVar.zzk(ObjectWrapper.wrap(contextZzc.getResources()), GooglePlayServicesUtil.GOOGLE_PLAY_SERVICES_VERSION_CODE);
                return zzc;
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        } catch (ClassNotFoundException unused) {
            throw new IllegalStateException("Unable to find dynamic class com.google.android.gms.maps.internal.CreatorImpl");
        }
    }

    private static Context zzb(Exception exc, Context context) {
        Log.e(zza, "Failed to load maps module, use pre-Chimera", exc);
        return GooglePlayServicesUtil.getRemoteContext(context);
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x001e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static android.content.Context zzc(android.content.Context r2, com.google.android.gms.maps.MapsInitializer.Renderer r3) {
        /*
            android.content.Context r0 = com.google.android.gms.maps.internal.zzcb.zzb
            if (r0 != 0) goto L50
            r2.getApplicationContext()
            java.lang.String r0 = "com.google.android.gms.maps_dynamite"
            if (r3 == 0) goto L1e
            int r3 = r3.ordinal()
            if (r3 == 0) goto L1a
            r1 = 1
            if (r3 == r1) goto L16
            goto L1e
        L16:
            java.lang.String r3 = "com.google.android.gms.maps_core_dynamite"
            goto L1f
        L1a:
            java.lang.String r3 = "com.google.android.gms.maps_legacy_dynamite"
            goto L1f
        L1e:
            r3 = r0
        L1f:
            com.google.android.gms.dynamite.DynamiteModule$VersionPolicy r1 = com.google.android.gms.dynamite.DynamiteModule.PREFER_REMOTE     // Catch: java.lang.Exception -> L2a
            com.google.android.gms.dynamite.DynamiteModule r1 = com.google.android.gms.dynamite.DynamiteModule.load(r2, r1, r3)     // Catch: java.lang.Exception -> L2a
            android.content.Context r2 = r1.getModuleContext()     // Catch: java.lang.Exception -> L2a
            goto L4d
        L2a:
            r1 = move-exception
            boolean r3 = r3.equals(r0)
            if (r3 != 0) goto L49
            java.lang.String r3 = com.google.android.gms.maps.internal.zzcb.zza     // Catch: java.lang.Exception -> L43
            java.lang.String r1 = "Attempting to load maps_dynamite again."
            android.util.Log.d(r3, r1)     // Catch: java.lang.Exception -> L43
            com.google.android.gms.dynamite.DynamiteModule$VersionPolicy r3 = com.google.android.gms.dynamite.DynamiteModule.PREFER_REMOTE     // Catch: java.lang.Exception -> L43
            com.google.android.gms.dynamite.DynamiteModule r3 = com.google.android.gms.dynamite.DynamiteModule.load(r2, r3, r0)     // Catch: java.lang.Exception -> L43
            android.content.Context r2 = r3.getModuleContext()     // Catch: java.lang.Exception -> L43
            goto L4d
        L43:
            r3 = move-exception
            android.content.Context r2 = zzb(r3, r2)
            goto L4d
        L49:
            android.content.Context r2 = zzb(r1, r2)
        L4d:
            com.google.android.gms.maps.internal.zzcb.zzb = r2
            return r2
        L50:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.maps.internal.zzcb.zzc(android.content.Context, com.google.android.gms.maps.MapsInitializer$Renderer):android.content.Context");
    }

    private static Object zzd(Class cls) {
        try {
            return cls.newInstance();
        } catch (IllegalAccessException unused) {
            throw new IllegalStateException("Unable to call the default constructor of ".concat(String.valueOf(cls.getName())));
        } catch (InstantiationException unused2) {
            throw new IllegalStateException("Unable to instantiate the dynamic class ".concat(String.valueOf(cls.getName())));
        }
    }
}
