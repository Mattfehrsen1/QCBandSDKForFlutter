package com.google.android.gms.internal.auth;

import java.lang.reflect.Field;
import java.nio.Buffer;
import java.security.AccessController;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.misc.Unsafe;

/* compiled from: com.google.android.gms:play-services-auth-base@@17.1.4 */
/* loaded from: classes2.dex */
final class zzgz {
    static final boolean zza;
    private static final Unsafe zzb;
    private static final Class<?> zzc;
    private static final boolean zzd;
    private static final boolean zze;
    private static final zzgy zzf;
    private static final boolean zzg;
    private static final boolean zzh;

    /* JADX WARN: Removed duplicated region for block: B:11:0x0046  */
    static {
        /*
            Method dump skipped, instructions count: 355
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.auth.zzgz.<clinit>():void");
    }

    private zzgz() {
    }

    static double zza(Object obj, long j) {
        return zzf.zza(obj, j);
    }

    static float zzb(Object obj, long j) {
        return zzf.zzb(obj, j);
    }

    static int zzc(Object obj, long j) {
        return zzf.zzi(obj, j);
    }

    static long zzd(Object obj, long j) {
        return zzf.zzj(obj, j);
    }

    static <T> T zze(Class<T> cls) {
        try {
            return (T) zzb.allocateInstance(cls);
        } catch (InstantiationException e) {
            throw new IllegalStateException(e);
        }
    }

    static Object zzf(Object obj, long j) {
        return zzf.zzl(obj, j);
    }

    static Unsafe zzg() {
        try {
            return (Unsafe) AccessController.doPrivileged(new zzgv());
        } catch (Throwable unused) {
            return null;
        }
    }

    static /* synthetic */ void zzh(Throwable th) {
        Logger logger = Logger.getLogger(zzgz.class.getName());
        Level level = Level.WARNING;
        String strValueOf = String.valueOf(th);
        StringBuilder sb = new StringBuilder(String.valueOf(strValueOf).length() + 71);
        sb.append("platform method missing - proto runtime falling back to safer methods: ");
        sb.append(strValueOf);
        logger.logp(level, "com.google.protobuf.UnsafeUtil", "logMissingMethod", sb.toString());
    }

    static /* synthetic */ void zzi(Object obj, long j, boolean z) {
        long j2 = (-4) & j;
        zzgy zzgyVar = zzf;
        int iZzi = zzgyVar.zzi(obj, j2);
        int i = ((~((int) j)) & 3) << 3;
        zzgyVar.zzm(obj, j2, ((z ? 1 : 0) << i) | ((~(255 << i)) & iZzi));
    }

    static /* synthetic */ void zzj(Object obj, long j, boolean z) {
        long j2 = (-4) & j;
        zzgy zzgyVar = zzf;
        int i = (((int) j) & 3) << 3;
        zzgyVar.zzm(obj, j2, ((z ? 1 : 0) << i) | ((~(255 << i)) & zzgyVar.zzi(obj, j2)));
    }

    static void zzk(Object obj, long j, boolean z) {
        zzf.zzc(obj, j, z);
    }

    static void zzl(Object obj, long j, double d) {
        zzf.zzd(obj, j, d);
    }

    static void zzm(Object obj, long j, float f) {
        zzf.zze(obj, j, f);
    }

    static void zzn(Object obj, long j, int i) {
        zzf.zzm(obj, j, i);
    }

    static void zzo(Object obj, long j, long j2) {
        zzf.zzn(obj, j, j2);
    }

    static void zzp(Object obj, long j, Object obj2) {
        zzf.zzo(obj, j, obj2);
    }

    static /* synthetic */ boolean zzq(Object obj, long j) {
        return ((byte) ((zzf.zzi(obj, (-4) & j) >>> ((int) (((~j) & 3) << 3))) & 255)) != 0;
    }

    static /* synthetic */ boolean zzr(Object obj, long j) {
        return ((byte) ((zzf.zzi(obj, (-4) & j) >>> ((int) ((j & 3) << 3))) & 255)) != 0;
    }

    static boolean zzs(Class<?> cls) {
        int i = zzdo.zza;
        try {
            Class<?> cls2 = zzc;
            cls2.getMethod("peekLong", cls, Boolean.TYPE);
            cls2.getMethod("pokeLong", cls, Long.TYPE, Boolean.TYPE);
            cls2.getMethod("pokeInt", cls, Integer.TYPE, Boolean.TYPE);
            cls2.getMethod("peekInt", cls, Boolean.TYPE);
            cls2.getMethod("pokeByte", cls, Byte.TYPE);
            cls2.getMethod("peekByte", cls);
            cls2.getMethod("pokeByteArray", cls, byte[].class, Integer.TYPE, Integer.TYPE);
            cls2.getMethod("peekByteArray", cls, byte[].class, Integer.TYPE, Integer.TYPE);
            return true;
        } catch (Throwable unused) {
            return false;
        }
    }

    static boolean zzt(Object obj, long j) {
        return zzf.zzf(obj, j);
    }

    static boolean zzu() {
        return zzh;
    }

    static boolean zzv() {
        return zzg;
    }

    private static int zzw(Class<?> cls) {
        if (zzh) {
            return zzf.zzg(cls);
        }
        return -1;
    }

    private static int zzx(Class<?> cls) {
        if (zzh) {
            return zzf.zzh(cls);
        }
        return -1;
    }

    private static Field zzy() {
        int i = zzdo.zza;
        Field fieldZzz = zzz(Buffer.class, "effectiveDirectAddress");
        if (fieldZzz != null) {
            return fieldZzz;
        }
        Field fieldZzz2 = zzz(Buffer.class, "address");
        if (fieldZzz2 == null || fieldZzz2.getType() != Long.TYPE) {
            return null;
        }
        return fieldZzz2;
    }

    private static Field zzz(Class<?> cls, String str) {
        try {
            return cls.getDeclaredField(str);
        } catch (Throwable unused) {
            return null;
        }
    }
}
