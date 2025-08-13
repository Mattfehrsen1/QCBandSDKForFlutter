package com.google.android.gms.internal.auth;

import android.os.Binder;

/* compiled from: com.google.android.gms:play-services-auth-base@@17.1.4 */
/* loaded from: classes2.dex */
public final /* synthetic */ class zzci {
    public static <V> V zza(zzcj<V> zzcjVar) {
        try {
            return zzcjVar.zza();
        } catch (SecurityException unused) {
            long jClearCallingIdentity = Binder.clearCallingIdentity();
            try {
                return zzcjVar.zza();
            } finally {
                Binder.restoreCallingIdentity(jClearCallingIdentity);
            }
        }
    }
}
