package com.google.android.gms.internal.auth;

import java.util.Iterator;
import java.util.List;
import java.util.RandomAccess;

/* compiled from: com.google.android.gms:play-services-auth-base@@17.1.4 */
/* loaded from: classes2.dex */
final class zzgd {
    private static final Class<?> zza;
    private static final zzgp<?, ?> zzb;
    private static final zzgp<?, ?> zzc;
    private static final zzgp<?, ?> zzd;

    static {
        Class<?> cls;
        try {
            cls = Class.forName("com.google.protobuf.GeneratedMessage");
        } catch (Throwable unused) {
            cls = null;
        }
        zza = cls;
        zzb = zzj(false);
        zzc = zzj(true);
        zzd = new zzgr();
    }

    public static zzgp<?, ?> zza() {
        return zzb;
    }

    public static zzgp<?, ?> zzb() {
        return zzc;
    }

    public static zzgp<?, ?> zzc() {
        return zzd;
    }

    static <UT, UB> UB zzd(int i, List<Integer> list, zzet zzetVar, UB ub, zzgp<UT, UB> zzgpVar) {
        if (zzetVar == null) {
            return ub;
        }
        if (list instanceof RandomAccess) {
            int size = list.size();
            int i2 = 0;
            for (int i3 = 0; i3 < size; i3++) {
                int iIntValue = list.get(i3).intValue();
                if (zzetVar.zza()) {
                    if (i3 != i2) {
                        list.set(i2, Integer.valueOf(iIntValue));
                    }
                    i2++;
                } else {
                    ub = (UB) zze(i, iIntValue, ub, zzgpVar);
                }
            }
            if (i2 != size) {
                list.subList(i2, size).clear();
                return ub;
            }
        } else {
            Iterator<Integer> it = list.iterator();
            while (it.hasNext()) {
                int iIntValue2 = it.next().intValue();
                if (!zzetVar.zza()) {
                    ub = (UB) zze(i, iIntValue2, ub, zzgpVar);
                    it.remove();
                }
            }
        }
        return ub;
    }

    static <UT, UB> UB zze(int i, int i2, UB ub, zzgp<UT, UB> zzgpVar) {
        if (ub == null) {
            ub = zzgpVar.zzc();
        }
        zzgpVar.zzd(ub, i, i2);
        return ub;
    }

    static <T, UT, UB> void zzf(zzgp<UT, UB> zzgpVar, T t, T t2) {
        zzgpVar.zzf(t, zzgpVar.zzb(zzgpVar.zza(t), zzgpVar.zza(t2)));
    }

    public static void zzg(Class<?> cls) {
        Class<?> cls2;
        if (!zzeq.class.isAssignableFrom(cls) && (cls2 = zza) != null && !cls2.isAssignableFrom(cls)) {
            throw new IllegalArgumentException("Message classes must extend GeneratedMessage or GeneratedMessageLite");
        }
    }

    static boolean zzh(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    static <T> void zzi(zzfl zzflVar, T t, T t2, long j) {
        zzgz.zzp(t, j, zzfl.zza(zzgz.zzf(t, j), zzgz.zzf(t2, j)));
    }

    private static zzgp<?, ?> zzj(boolean z) {
        Class<?> cls;
        try {
            cls = Class.forName("com.google.protobuf.UnknownFieldSetSchema");
        } catch (Throwable unused) {
            cls = null;
        }
        if (cls == null) {
            return null;
        }
        try {
            return (zzgp) cls.getConstructor(Boolean.TYPE).newInstance(Boolean.valueOf(z));
        } catch (Throwable unused2) {
            return null;
        }
    }
}
