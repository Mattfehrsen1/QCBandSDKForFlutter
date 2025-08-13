package com.google.android.gms.internal.auth;

import com.google.android.gms.internal.auth.zzeo;
import com.google.android.gms.internal.auth.zzeq;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: com.google.android.gms:play-services-auth-base@@17.1.4 */
/* loaded from: classes2.dex */
public abstract class zzeq<MessageType extends zzeq<MessageType, BuilderType>, BuilderType extends zzeo<MessageType, BuilderType>> extends zzdm<MessageType, BuilderType> {
    private static final Map<Object, zzeq<?, ?>> zzb = new ConcurrentHashMap();
    protected zzgq zzc = zzgq.zza();

    static <T extends zzeq> T zza(Class<T> cls) throws ClassNotFoundException {
        Map<Object, zzeq<?, ?>> map = zzb;
        zzeq<?, ?> zzeqVar = map.get(cls);
        if (zzeqVar == null) {
            try {
                Class.forName(cls.getName(), true, cls.getClassLoader());
                zzeqVar = map.get(cls);
            } catch (ClassNotFoundException e) {
                throw new IllegalStateException("Class initialization cannot fail.", e);
            }
        }
        if (zzeqVar == null) {
            zzeqVar = (zzeq) ((zzeq) zzgz.zze(cls)).zzj(6, null, null);
            if (zzeqVar == null) {
                throw new IllegalStateException();
            }
            map.put(cls, zzeqVar);
        }
        return zzeqVar;
    }

    protected static <T extends zzeq<T, ?>> T zzb(T t, byte[] bArr) throws zzew {
        boolean zZzi = false;
        T t2 = (T) zzc(t, bArr, 0, bArr.length, zzeg.zza());
        if (t2 != null) {
            Boolean bool = Boolean.TRUE;
            byte bByteValue = ((Byte) t2.zzj(1, null, null)).byteValue();
            if (bByteValue == 1) {
                zZzi = true;
            } else if (bByteValue != 0) {
                zZzi = zzfy.zza().zzb(t2.getClass()).zzi(t2);
                t2.zzj(2, true != zZzi ? null : t2, null);
            }
            if (!zZzi) {
                zzew zzewVar = new zzew(new zzgo(t2).getMessage());
                zzewVar.zze(t2);
                throw zzewVar;
            }
        }
        return t2;
    }

    static <T extends zzeq<T, ?>> T zzc(T t, byte[] bArr, int i, int i2, zzeg zzegVar) throws zzew {
        T t2 = (T) t.zzj(4, null, null);
        try {
            zzgb zzgbVarZzb = zzfy.zza().zzb(t2.getClass());
            zzgbVarZzb.zzg(t2, bArr, 0, i2, new zzdp(zzegVar));
            zzgbVarZzb.zze(t2);
            if (t2.zza == 0) {
                return t2;
            }
            throw new RuntimeException();
        } catch (zzew e) {
            e.zze(t2);
            throw e;
        } catch (IOException e2) {
            if (e2.getCause() instanceof zzew) {
                throw ((zzew) e2.getCause());
            }
            zzew zzewVar = new zzew(e2);
            zzewVar.zze(t2);
            throw zzewVar;
        } catch (IndexOutOfBoundsException unused) {
            zzew zzewVarZzf = zzew.zzf();
            zzewVarZzf.zze(t2);
            throw zzewVarZzf;
        }
    }

    protected static <E> zzeu<E> zzd() {
        return zzfz.zze();
    }

    static Object zzf(Method method, Object obj, Object... objArr) {
        try {
            return method.invoke(obj, objArr);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Couldn't use Java reflection to implement protocol message reflection.", e);
        } catch (InvocationTargetException e2) {
            Throwable cause = e2.getCause();
            if (cause instanceof RuntimeException) {
                throw ((RuntimeException) cause);
            }
            if (cause instanceof Error) {
                throw ((Error) cause);
            }
            throw new RuntimeException("Unexpected exception thrown by generated accessor method.", cause);
        }
    }

    protected static Object zzg(zzfq zzfqVar, String str, Object[] objArr) {
        return new zzga(zzfqVar, "\u0001\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0001\u0000\u0001\u001a", objArr);
    }

    protected static <T extends zzeq> void zzi(Class<T> cls, T t) {
        zzb.put(cls, t);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            return zzfy.zza().zzb(getClass()).zzh(this, (zzeq) obj);
        }
        return false;
    }

    public final int hashCode() {
        int i = this.zza;
        if (i != 0) {
            return i;
        }
        int iZza = zzfy.zza().zzb(getClass()).zza(this);
        this.zza = iZza;
        return iZza;
    }

    public final String toString() {
        return zzfs.zza(this, super.toString());
    }

    @Override // com.google.android.gms.internal.auth.zzfq
    public final /* bridge */ /* synthetic */ zzfp zze() {
        zzeo zzeoVar = (zzeo) zzj(5, null, null);
        zzeoVar.zze(this);
        return zzeoVar;
    }

    @Override // com.google.android.gms.internal.auth.zzfr
    public final /* bridge */ /* synthetic */ zzfq zzh() {
        return (zzeq) zzj(6, null, null);
    }

    protected abstract Object zzj(int i, Object obj, Object obj2);
}
