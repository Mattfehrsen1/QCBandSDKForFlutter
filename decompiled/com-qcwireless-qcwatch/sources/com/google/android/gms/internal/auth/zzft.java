package com.google.android.gms.internal.auth;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Objects;
import sun.misc.Unsafe;

/* compiled from: com.google.android.gms:play-services-auth-base@@17.1.4 */
/* loaded from: classes2.dex */
final class zzft<T> implements zzgb<T> {
    private static final int[] zza = new int[0];
    private static final Unsafe zzb = zzgz.zzg();
    private final int[] zzc;
    private final Object[] zzd;
    private final int zze;
    private final int zzf;
    private final zzfq zzg;
    private final boolean zzh;
    private final int[] zzi;
    private final int zzj;
    private final int zzk;
    private final zzfe zzl;
    private final zzgp<?, ?> zzm;
    private final zzeh<?> zzn;
    private final zzfv zzo;
    private final zzfl zzp;

    /* JADX WARN: Multi-variable type inference failed */
    private zzft(int[] iArr, int[] iArr2, Object[] objArr, int i, int i2, zzfq zzfqVar, boolean z, boolean z2, int[] iArr3, int i3, int i4, zzfv zzfvVar, zzfe zzfeVar, zzgp<?, ?> zzgpVar, zzeh<?> zzehVar, zzfl zzflVar) {
        this.zzc = iArr;
        this.zzd = iArr2;
        this.zze = objArr;
        this.zzf = i;
        this.zzh = zzfqVar;
        this.zzi = z2;
        this.zzj = iArr3;
        this.zzk = i3;
        this.zzo = i4;
        this.zzl = zzfvVar;
        this.zzm = zzfeVar;
        this.zzn = zzgpVar;
        this.zzg = i2;
        this.zzp = zzehVar;
    }

    private static Field zzA(Class<?> cls, String str) {
        try {
            return cls.getDeclaredField(str);
        } catch (NoSuchFieldException unused) {
            Field[] declaredFields = cls.getDeclaredFields();
            for (Field field : declaredFields) {
                if (str.equals(field.getName())) {
                    return field;
                }
            }
            String name = cls.getName();
            String string = Arrays.toString(declaredFields);
            StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 40 + String.valueOf(name).length() + String.valueOf(string).length());
            sb.append("Field ");
            sb.append(str);
            sb.append(" for ");
            sb.append(name);
            sb.append(" not found. Known fields are ");
            sb.append(string);
            throw new RuntimeException(sb.toString());
        }
    }

    private final void zzB(T t, T t2, int i) {
        long jZzv = zzv(i) & 1048575;
        if (zzG(t2, i)) {
            Object objZzf = zzgz.zzf(t, jZzv);
            Object objZzf2 = zzgz.zzf(t2, jZzv);
            if (objZzf != null && objZzf2 != null) {
                zzgz.zzp(t, jZzv, zzev.zzg(objZzf, objZzf2));
                zzD(t, i);
            } else if (objZzf2 != null) {
                zzgz.zzp(t, jZzv, objZzf2);
                zzD(t, i);
            }
        }
    }

    private final void zzC(T t, T t2, int i) {
        int iZzv = zzv(i);
        int i2 = this.zzc[i];
        long j = iZzv & 1048575;
        if (zzJ(t2, i2, i)) {
            Object objZzf = zzJ(t, i2, i) ? zzgz.zzf(t, j) : null;
            Object objZzf2 = zzgz.zzf(t2, j);
            if (objZzf != null && objZzf2 != null) {
                zzgz.zzp(t, j, zzev.zzg(objZzf, objZzf2));
                zzE(t, i2, i);
            } else if (objZzf2 != null) {
                zzgz.zzp(t, j, objZzf2);
                zzE(t, i2, i);
            }
        }
    }

    private final void zzD(T t, int i) {
        int iZzs = zzs(i);
        long j = 1048575 & iZzs;
        if (j == 1048575) {
            return;
        }
        zzgz.zzn(t, j, (1 << (iZzs >>> 20)) | zzgz.zzc(t, j));
    }

    private final void zzE(T t, int i, int i2) {
        zzgz.zzn(t, zzs(i2) & 1048575, i);
    }

    private final boolean zzF(T t, T t2, int i) {
        return zzG(t, i) == zzG(t2, i);
    }

    private final boolean zzG(T t, int i) {
        int iZzs = zzs(i);
        long j = iZzs & 1048575;
        if (j != 1048575) {
            return (zzgz.zzc(t, j) & (1 << (iZzs >>> 20))) != 0;
        }
        int iZzv = zzv(i);
        long j2 = iZzv & 1048575;
        switch (zzu(iZzv)) {
            case 0:
                return zzgz.zza(t, j2) != 0.0d;
            case 1:
                return zzgz.zzb(t, j2) != 0.0f;
            case 2:
                return zzgz.zzd(t, j2) != 0;
            case 3:
                return zzgz.zzd(t, j2) != 0;
            case 4:
                return zzgz.zzc(t, j2) != 0;
            case 5:
                return zzgz.zzd(t, j2) != 0;
            case 6:
                return zzgz.zzc(t, j2) != 0;
            case 7:
                return zzgz.zzt(t, j2);
            case 8:
                Object objZzf = zzgz.zzf(t, j2);
                if (objZzf instanceof String) {
                    return !((String) objZzf).isEmpty();
                }
                if (objZzf instanceof zzeb) {
                    return !zzeb.zzb.equals(objZzf);
                }
                throw new IllegalArgumentException();
            case 9:
                return zzgz.zzf(t, j2) != null;
            case 10:
                return !zzeb.zzb.equals(zzgz.zzf(t, j2));
            case 11:
                return zzgz.zzc(t, j2) != 0;
            case 12:
                return zzgz.zzc(t, j2) != 0;
            case 13:
                return zzgz.zzc(t, j2) != 0;
            case 14:
                return zzgz.zzd(t, j2) != 0;
            case 15:
                return zzgz.zzc(t, j2) != 0;
            case 16:
                return zzgz.zzd(t, j2) != 0;
            case 17:
                return zzgz.zzf(t, j2) != null;
            default:
                throw new IllegalArgumentException();
        }
    }

    private final boolean zzH(T t, int i, int i2, int i3, int i4) {
        return i2 == 1048575 ? zzG(t, i) : (i3 & i4) != 0;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static boolean zzI(Object obj, int i, zzgb zzgbVar) {
        return zzgbVar.zzi(zzgz.zzf(obj, i & 1048575));
    }

    private final boolean zzJ(T t, int i, int i2) {
        return zzgz.zzc(t, (long) (zzs(i2) & 1048575)) == i;
    }

    static zzgq zzc(Object obj) {
        zzeq zzeqVar = (zzeq) obj;
        zzgq zzgqVar = zzeqVar.zzc;
        if (zzgqVar != zzgq.zza()) {
            return zzgqVar;
        }
        zzgq zzgqVarZzc = zzgq.zzc();
        zzeqVar.zzc = zzgqVarZzc;
        return zzgqVarZzc;
    }

    static <T> zzft<T> zzj(Class<T> cls, zzfn zzfnVar, zzfv zzfvVar, zzfe zzfeVar, zzgp<?, ?> zzgpVar, zzeh<?> zzehVar, zzfl zzflVar) {
        if (zzfnVar instanceof zzga) {
            return zzk((zzga) zzfnVar, zzfvVar, zzfeVar, zzgpVar, zzehVar, zzflVar);
        }
        throw null;
    }

    /* JADX WARN: Removed duplicated region for block: B:123:0x025d  */
    /* JADX WARN: Removed duplicated region for block: B:124:0x0260  */
    /* JADX WARN: Removed duplicated region for block: B:127:0x0278  */
    /* JADX WARN: Removed duplicated region for block: B:128:0x027b  */
    /* JADX WARN: Removed duplicated region for block: B:162:0x032b  */
    /* JADX WARN: Removed duplicated region for block: B:177:0x0378  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static <T> com.google.android.gms.internal.auth.zzft<T> zzk(com.google.android.gms.internal.auth.zzga r34, com.google.android.gms.internal.auth.zzfv r35, com.google.android.gms.internal.auth.zzfe r36, com.google.android.gms.internal.auth.zzgp<?, ?> r37, com.google.android.gms.internal.auth.zzeh<?> r38, com.google.android.gms.internal.auth.zzfl r39) {
        /*
            Method dump skipped, instructions count: 1016
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.auth.zzft.zzk(com.google.android.gms.internal.auth.zzga, com.google.android.gms.internal.auth.zzfv, com.google.android.gms.internal.auth.zzfe, com.google.android.gms.internal.auth.zzgp, com.google.android.gms.internal.auth.zzeh, com.google.android.gms.internal.auth.zzfl):com.google.android.gms.internal.auth.zzft");
    }

    private static <T> int zzl(T t, long j) {
        return ((Integer) zzgz.zzf(t, j)).intValue();
    }

    private final <K, V> int zzm(T t, byte[] bArr, int i, int i2, int i3, long j, zzdp zzdpVar) throws IOException {
        Unsafe unsafe = zzb;
        Object objZzz = zzz(i3);
        Object object = unsafe.getObject(t, j);
        if (!((zzfk) object).zze()) {
            zzfk<K, V> zzfkVarZzb = zzfk.zza().zzb();
            zzfl.zza(zzfkVarZzb, object);
            unsafe.putObject(t, j, zzfkVarZzb);
        }
        throw null;
    }

    private final int zzn(T t, byte[] bArr, int i, int i2, int i3, int i4, int i5, int i6, int i7, long j, int i8, zzdp zzdpVar) throws IOException {
        Unsafe unsafe = zzb;
        long j2 = this.zzc[i8 + 2] & 1048575;
        switch (i7) {
            case 51:
                if (i5 == 1) {
                    unsafe.putObject(t, j, Double.valueOf(Double.longBitsToDouble(zzdq.zzn(bArr, i))));
                    unsafe.putInt(t, j2, i4);
                    return i + 8;
                }
                break;
            case 52:
                if (i5 == 5) {
                    unsafe.putObject(t, j, Float.valueOf(Float.intBitsToFloat(zzdq.zzb(bArr, i))));
                    unsafe.putInt(t, j2, i4);
                    return i + 4;
                }
                break;
            case 53:
            case 54:
                if (i5 == 0) {
                    int iZzm = zzdq.zzm(bArr, i, zzdpVar);
                    unsafe.putObject(t, j, Long.valueOf(zzdpVar.zzb));
                    unsafe.putInt(t, j2, i4);
                    return iZzm;
                }
                break;
            case 55:
            case 62:
                if (i5 == 0) {
                    int iZzj = zzdq.zzj(bArr, i, zzdpVar);
                    unsafe.putObject(t, j, Integer.valueOf(zzdpVar.zza));
                    unsafe.putInt(t, j2, i4);
                    return iZzj;
                }
                break;
            case 56:
            case 65:
                if (i5 == 1) {
                    unsafe.putObject(t, j, Long.valueOf(zzdq.zzn(bArr, i)));
                    unsafe.putInt(t, j2, i4);
                    return i + 8;
                }
                break;
            case 57:
            case 64:
                if (i5 == 5) {
                    unsafe.putObject(t, j, Integer.valueOf(zzdq.zzb(bArr, i)));
                    unsafe.putInt(t, j2, i4);
                    return i + 4;
                }
                break;
            case 58:
                if (i5 == 0) {
                    int iZzm2 = zzdq.zzm(bArr, i, zzdpVar);
                    unsafe.putObject(t, j, Boolean.valueOf(zzdpVar.zzb != 0));
                    unsafe.putInt(t, j2, i4);
                    return iZzm2;
                }
                break;
            case 59:
                if (i5 == 2) {
                    int iZzj2 = zzdq.zzj(bArr, i, zzdpVar);
                    int i9 = zzdpVar.zza;
                    if (i9 == 0) {
                        unsafe.putObject(t, j, "");
                    } else {
                        if ((i6 & 536870912) != 0 && !zzhd.zzd(bArr, iZzj2, iZzj2 + i9)) {
                            throw zzew.zzb();
                        }
                        unsafe.putObject(t, j, new String(bArr, iZzj2, i9, zzev.zza));
                        iZzj2 += i9;
                    }
                    unsafe.putInt(t, j2, i4);
                    return iZzj2;
                }
                break;
            case 60:
                if (i5 == 2) {
                    int iZzd = zzdq.zzd(zzy(i8), bArr, i, i2, zzdpVar);
                    Object object = unsafe.getInt(t, j2) == i4 ? unsafe.getObject(t, j) : null;
                    if (object == null) {
                        unsafe.putObject(t, j, zzdpVar.zzc);
                    } else {
                        unsafe.putObject(t, j, zzev.zzg(object, zzdpVar.zzc));
                    }
                    unsafe.putInt(t, j2, i4);
                    return iZzd;
                }
                break;
            case 61:
                if (i5 == 2) {
                    int iZza = zzdq.zza(bArr, i, zzdpVar);
                    unsafe.putObject(t, j, zzdpVar.zzc);
                    unsafe.putInt(t, j2, i4);
                    return iZza;
                }
                break;
            case 63:
                if (i5 == 0) {
                    int iZzj3 = zzdq.zzj(bArr, i, zzdpVar);
                    int i10 = zzdpVar.zza;
                    zzet zzetVarZzx = zzx(i8);
                    if (zzetVarZzx == null || zzetVarZzx.zza()) {
                        unsafe.putObject(t, j, Integer.valueOf(i10));
                        unsafe.putInt(t, j2, i4);
                    } else {
                        zzc(t).zzf(i3, Long.valueOf(i10));
                    }
                    return iZzj3;
                }
                break;
            case 66:
                if (i5 == 0) {
                    int iZzj4 = zzdq.zzj(bArr, i, zzdpVar);
                    unsafe.putObject(t, j, Integer.valueOf(zzee.zzb(zzdpVar.zza)));
                    unsafe.putInt(t, j2, i4);
                    return iZzj4;
                }
                break;
            case 67:
                if (i5 == 0) {
                    int iZzm3 = zzdq.zzm(bArr, i, zzdpVar);
                    unsafe.putObject(t, j, Long.valueOf(zzee.zzc(zzdpVar.zzb)));
                    unsafe.putInt(t, j2, i4);
                    return iZzm3;
                }
                break;
            case 68:
                if (i5 == 3) {
                    int iZzc = zzdq.zzc(zzy(i8), bArr, i, i2, (i3 & (-8)) | 4, zzdpVar);
                    Object object2 = unsafe.getInt(t, j2) == i4 ? unsafe.getObject(t, j) : null;
                    if (object2 == null) {
                        unsafe.putObject(t, j, zzdpVar.zzc);
                    } else {
                        unsafe.putObject(t, j, zzev.zzg(object2, zzdpVar.zzc));
                    }
                    unsafe.putInt(t, j2, i4);
                    return iZzc;
                }
                break;
        }
        return i;
    }

    /* JADX WARN: Code restructure failed: missing block: B:104:0x02a8, code lost:
    
        if (r0 != r15) goto L105;
     */
    /* JADX WARN: Code restructure failed: missing block: B:105:0x02aa, code lost:
    
        r15 = r30;
        r14 = r31;
        r12 = r32;
        r13 = r34;
        r11 = r35;
        r2 = r19;
        r1 = r20;
        r6 = r24;
        r7 = r25;
     */
    /* JADX WARN: Code restructure failed: missing block: B:106:0x02be, code lost:
    
        r2 = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:112:0x02f1, code lost:
    
        if (r0 != r15) goto L105;
     */
    /* JADX WARN: Code restructure failed: missing block: B:117:0x0314, code lost:
    
        if (r0 != r15) goto L105;
     */
    /* JADX WARN: Failed to find 'out' block for switch in B:28:0x0097. Please report as an issue. */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v10, types: [int] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final int zzo(T r31, byte[] r32, int r33, int r34, com.google.android.gms.internal.auth.zzdp r35) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 896
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.auth.zzft.zzo(java.lang.Object, byte[], int, int, com.google.android.gms.internal.auth.zzdp):int");
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final int zzp(T t, byte[] bArr, int i, int i2, int i3, int i4, int i5, int i6, long j, int i7, long j2, zzdp zzdpVar) throws IOException {
        int i8;
        int i9;
        int i10;
        int i11;
        int iZzj;
        int iZzj2 = i;
        Unsafe unsafe = zzb;
        zzeu zzeuVarZzd = (zzeu) unsafe.getObject(t, j2);
        if (!zzeuVarZzd.zzc()) {
            int size = zzeuVarZzd.size();
            zzeuVarZzd = zzeuVarZzd.zzd(size == 0 ? 10 : size + size);
            unsafe.putObject(t, j2, zzeuVarZzd);
        }
        switch (i7) {
            case 18:
            case 35:
                if (i5 == 2) {
                    zzef zzefVar = (zzef) zzeuVarZzd;
                    int iZzj3 = zzdq.zzj(bArr, iZzj2, zzdpVar);
                    int i12 = zzdpVar.zza + iZzj3;
                    while (iZzj3 < i12) {
                        zzefVar.zze(Double.longBitsToDouble(zzdq.zzn(bArr, iZzj3)));
                        iZzj3 += 8;
                    }
                    if (iZzj3 == i12) {
                        return iZzj3;
                    }
                    throw zzew.zzf();
                }
                if (i5 == 1) {
                    zzef zzefVar2 = (zzef) zzeuVarZzd;
                    zzefVar2.zze(Double.longBitsToDouble(zzdq.zzn(bArr, i)));
                    while (true) {
                        i8 = iZzj2 + 8;
                        if (i8 < i2) {
                            iZzj2 = zzdq.zzj(bArr, i8, zzdpVar);
                            if (i3 == zzdpVar.zza) {
                                zzefVar2.zze(Double.longBitsToDouble(zzdq.zzn(bArr, iZzj2)));
                            }
                        }
                    }
                    return i8;
                }
                return iZzj2;
            case 19:
            case 36:
                if (i5 == 2) {
                    zzem zzemVar = (zzem) zzeuVarZzd;
                    int iZzj4 = zzdq.zzj(bArr, iZzj2, zzdpVar);
                    int i13 = zzdpVar.zza + iZzj4;
                    while (iZzj4 < i13) {
                        zzemVar.zze(Float.intBitsToFloat(zzdq.zzb(bArr, iZzj4)));
                        iZzj4 += 4;
                    }
                    if (iZzj4 == i13) {
                        return iZzj4;
                    }
                    throw zzew.zzf();
                }
                if (i5 == 5) {
                    zzem zzemVar2 = (zzem) zzeuVarZzd;
                    zzemVar2.zze(Float.intBitsToFloat(zzdq.zzb(bArr, i)));
                    while (true) {
                        i9 = iZzj2 + 4;
                        if (i9 < i2) {
                            iZzj2 = zzdq.zzj(bArr, i9, zzdpVar);
                            if (i3 == zzdpVar.zza) {
                                zzemVar2.zze(Float.intBitsToFloat(zzdq.zzb(bArr, iZzj2)));
                            }
                        }
                    }
                    return i9;
                }
                return iZzj2;
            case 20:
            case 21:
            case 37:
            case 38:
                if (i5 == 2) {
                    zzff zzffVar = (zzff) zzeuVarZzd;
                    int iZzj5 = zzdq.zzj(bArr, iZzj2, zzdpVar);
                    int i14 = zzdpVar.zza + iZzj5;
                    while (iZzj5 < i14) {
                        iZzj5 = zzdq.zzm(bArr, iZzj5, zzdpVar);
                        zzffVar.zze(zzdpVar.zzb);
                    }
                    if (iZzj5 == i14) {
                        return iZzj5;
                    }
                    throw zzew.zzf();
                }
                if (i5 == 0) {
                    zzff zzffVar2 = (zzff) zzeuVarZzd;
                    int iZzm = zzdq.zzm(bArr, iZzj2, zzdpVar);
                    zzffVar2.zze(zzdpVar.zzb);
                    while (iZzm < i2) {
                        int iZzj6 = zzdq.zzj(bArr, iZzm, zzdpVar);
                        if (i3 != zzdpVar.zza) {
                            return iZzm;
                        }
                        iZzm = zzdq.zzm(bArr, iZzj6, zzdpVar);
                        zzffVar2.zze(zzdpVar.zzb);
                    }
                    return iZzm;
                }
                return iZzj2;
            case 22:
            case 29:
            case 39:
            case 43:
                if (i5 == 2) {
                    return zzdq.zzf(bArr, iZzj2, zzeuVarZzd, zzdpVar);
                }
                if (i5 == 0) {
                    return zzdq.zzl(i3, bArr, i, i2, zzeuVarZzd, zzdpVar);
                }
                return iZzj2;
            case 23:
            case 32:
            case 40:
            case 46:
                if (i5 == 2) {
                    zzff zzffVar3 = (zzff) zzeuVarZzd;
                    int iZzj7 = zzdq.zzj(bArr, iZzj2, zzdpVar);
                    int i15 = zzdpVar.zza + iZzj7;
                    while (iZzj7 < i15) {
                        zzffVar3.zze(zzdq.zzn(bArr, iZzj7));
                        iZzj7 += 8;
                    }
                    if (iZzj7 == i15) {
                        return iZzj7;
                    }
                    throw zzew.zzf();
                }
                if (i5 == 1) {
                    zzff zzffVar4 = (zzff) zzeuVarZzd;
                    zzffVar4.zze(zzdq.zzn(bArr, i));
                    while (true) {
                        i10 = iZzj2 + 8;
                        if (i10 < i2) {
                            iZzj2 = zzdq.zzj(bArr, i10, zzdpVar);
                            if (i3 == zzdpVar.zza) {
                                zzffVar4.zze(zzdq.zzn(bArr, iZzj2));
                            }
                        }
                    }
                    return i10;
                }
                return iZzj2;
            case 24:
            case 31:
            case 41:
            case 45:
                if (i5 == 2) {
                    zzer zzerVar = (zzer) zzeuVarZzd;
                    int iZzj8 = zzdq.zzj(bArr, iZzj2, zzdpVar);
                    int i16 = zzdpVar.zza + iZzj8;
                    while (iZzj8 < i16) {
                        zzerVar.zze(zzdq.zzb(bArr, iZzj8));
                        iZzj8 += 4;
                    }
                    if (iZzj8 == i16) {
                        return iZzj8;
                    }
                    throw zzew.zzf();
                }
                if (i5 == 5) {
                    zzer zzerVar2 = (zzer) zzeuVarZzd;
                    zzerVar2.zze(zzdq.zzb(bArr, i));
                    while (true) {
                        i11 = iZzj2 + 4;
                        if (i11 < i2) {
                            iZzj2 = zzdq.zzj(bArr, i11, zzdpVar);
                            if (i3 == zzdpVar.zza) {
                                zzerVar2.zze(zzdq.zzb(bArr, iZzj2));
                            }
                        }
                    }
                    return i11;
                }
                return iZzj2;
            case 25:
            case 42:
                if (i5 == 2) {
                    zzdr zzdrVar = (zzdr) zzeuVarZzd;
                    iZzj = zzdq.zzj(bArr, iZzj2, zzdpVar);
                    int i17 = zzdpVar.zza + iZzj;
                    while (iZzj < i17) {
                        iZzj = zzdq.zzm(bArr, iZzj, zzdpVar);
                        zzdrVar.zze(zzdpVar.zzb != 0);
                    }
                    if (iZzj != i17) {
                        throw zzew.zzf();
                    }
                    return iZzj;
                }
                if (i5 == 0) {
                    zzdr zzdrVar2 = (zzdr) zzeuVarZzd;
                    int iZzm2 = zzdq.zzm(bArr, iZzj2, zzdpVar);
                    zzdrVar2.zze(zzdpVar.zzb != 0);
                    while (iZzm2 < i2) {
                        int iZzj9 = zzdq.zzj(bArr, iZzm2, zzdpVar);
                        if (i3 != zzdpVar.zza) {
                            return iZzm2;
                        }
                        iZzm2 = zzdq.zzm(bArr, iZzj9, zzdpVar);
                        zzdrVar2.zze(zzdpVar.zzb != 0);
                    }
                    return iZzm2;
                }
                return iZzj2;
            case 26:
                if (i5 == 2) {
                    if ((j & 536870912) == 0) {
                        iZzj2 = zzdq.zzj(bArr, iZzj2, zzdpVar);
                        int i18 = zzdpVar.zza;
                        if (i18 < 0) {
                            throw zzew.zzc();
                        }
                        if (i18 == 0) {
                            zzeuVarZzd.add("");
                        } else {
                            zzeuVarZzd.add(new String(bArr, iZzj2, i18, zzev.zza));
                            iZzj2 += i18;
                        }
                        while (iZzj2 < i2) {
                            int iZzj10 = zzdq.zzj(bArr, iZzj2, zzdpVar);
                            if (i3 == zzdpVar.zza) {
                                iZzj2 = zzdq.zzj(bArr, iZzj10, zzdpVar);
                                int i19 = zzdpVar.zza;
                                if (i19 < 0) {
                                    throw zzew.zzc();
                                }
                                if (i19 == 0) {
                                    zzeuVarZzd.add("");
                                } else {
                                    zzeuVarZzd.add(new String(bArr, iZzj2, i19, zzev.zza));
                                    iZzj2 += i19;
                                }
                            }
                        }
                    } else {
                        iZzj2 = zzdq.zzj(bArr, iZzj2, zzdpVar);
                        int i20 = zzdpVar.zza;
                        if (i20 < 0) {
                            throw zzew.zzc();
                        }
                        if (i20 == 0) {
                            zzeuVarZzd.add("");
                        } else {
                            int i21 = iZzj2 + i20;
                            if (!zzhd.zzd(bArr, iZzj2, i21)) {
                                throw zzew.zzb();
                            }
                            zzeuVarZzd.add(new String(bArr, iZzj2, i20, zzev.zza));
                            iZzj2 = i21;
                        }
                        while (iZzj2 < i2) {
                            int iZzj11 = zzdq.zzj(bArr, iZzj2, zzdpVar);
                            if (i3 == zzdpVar.zza) {
                                iZzj2 = zzdq.zzj(bArr, iZzj11, zzdpVar);
                                int i22 = zzdpVar.zza;
                                if (i22 < 0) {
                                    throw zzew.zzc();
                                }
                                if (i22 == 0) {
                                    zzeuVarZzd.add("");
                                } else {
                                    int i23 = iZzj2 + i22;
                                    if (!zzhd.zzd(bArr, iZzj2, i23)) {
                                        throw zzew.zzb();
                                    }
                                    zzeuVarZzd.add(new String(bArr, iZzj2, i22, zzev.zza));
                                    iZzj2 = i23;
                                }
                            }
                        }
                    }
                }
                return iZzj2;
            case 27:
                if (i5 == 2) {
                    return zzdq.zze(zzy(i6), i3, bArr, i, i2, zzeuVarZzd, zzdpVar);
                }
                return iZzj2;
            case 28:
                if (i5 == 2) {
                    int iZzj12 = zzdq.zzj(bArr, iZzj2, zzdpVar);
                    int i24 = zzdpVar.zza;
                    if (i24 < 0) {
                        throw zzew.zzc();
                    }
                    if (i24 > bArr.length - iZzj12) {
                        throw zzew.zzf();
                    }
                    if (i24 == 0) {
                        zzeuVarZzd.add(zzeb.zzb);
                    } else {
                        zzeuVarZzd.add(zzeb.zzk(bArr, iZzj12, i24));
                        iZzj12 += i24;
                    }
                    while (iZzj12 < i2) {
                        int iZzj13 = zzdq.zzj(bArr, iZzj12, zzdpVar);
                        if (i3 != zzdpVar.zza) {
                            return iZzj12;
                        }
                        iZzj12 = zzdq.zzj(bArr, iZzj13, zzdpVar);
                        int i25 = zzdpVar.zza;
                        if (i25 < 0) {
                            throw zzew.zzc();
                        }
                        if (i25 > bArr.length - iZzj12) {
                            throw zzew.zzf();
                        }
                        if (i25 == 0) {
                            zzeuVarZzd.add(zzeb.zzb);
                        } else {
                            zzeuVarZzd.add(zzeb.zzk(bArr, iZzj12, i25));
                            iZzj12 += i25;
                        }
                    }
                    return iZzj12;
                }
                return iZzj2;
            case 30:
            case 44:
                if (i5 != 2) {
                    if (i5 == 0) {
                        iZzj = zzdq.zzl(i3, bArr, i, i2, zzeuVarZzd, zzdpVar);
                    }
                    return iZzj2;
                }
                iZzj = zzdq.zzf(bArr, iZzj2, zzeuVarZzd, zzdpVar);
                zzeq zzeqVar = (zzeq) t;
                zzgq zzgqVar = zzeqVar.zzc;
                if (zzgqVar == zzgq.zza()) {
                    zzgqVar = null;
                }
                Object objZzd = zzgd.zzd(i4, zzeuVarZzd, zzx(i6), zzgqVar, this.zzm);
                if (objZzd != null) {
                    zzeqVar.zzc = (zzgq) objZzd;
                    return iZzj;
                }
                return iZzj;
            case 33:
            case 47:
                if (i5 == 2) {
                    zzer zzerVar3 = (zzer) zzeuVarZzd;
                    int iZzj14 = zzdq.zzj(bArr, iZzj2, zzdpVar);
                    int i26 = zzdpVar.zza + iZzj14;
                    while (iZzj14 < i26) {
                        iZzj14 = zzdq.zzj(bArr, iZzj14, zzdpVar);
                        zzerVar3.zze(zzee.zzb(zzdpVar.zza));
                    }
                    if (iZzj14 == i26) {
                        return iZzj14;
                    }
                    throw zzew.zzf();
                }
                if (i5 == 0) {
                    zzer zzerVar4 = (zzer) zzeuVarZzd;
                    int iZzj15 = zzdq.zzj(bArr, iZzj2, zzdpVar);
                    zzerVar4.zze(zzee.zzb(zzdpVar.zza));
                    while (iZzj15 < i2) {
                        int iZzj16 = zzdq.zzj(bArr, iZzj15, zzdpVar);
                        if (i3 != zzdpVar.zza) {
                            return iZzj15;
                        }
                        iZzj15 = zzdq.zzj(bArr, iZzj16, zzdpVar);
                        zzerVar4.zze(zzee.zzb(zzdpVar.zza));
                    }
                    return iZzj15;
                }
                return iZzj2;
            case 34:
            case 48:
                if (i5 == 2) {
                    zzff zzffVar5 = (zzff) zzeuVarZzd;
                    int iZzj17 = zzdq.zzj(bArr, iZzj2, zzdpVar);
                    int i27 = zzdpVar.zza + iZzj17;
                    while (iZzj17 < i27) {
                        iZzj17 = zzdq.zzm(bArr, iZzj17, zzdpVar);
                        zzffVar5.zze(zzee.zzc(zzdpVar.zzb));
                    }
                    if (iZzj17 == i27) {
                        return iZzj17;
                    }
                    throw zzew.zzf();
                }
                if (i5 == 0) {
                    zzff zzffVar6 = (zzff) zzeuVarZzd;
                    int iZzm3 = zzdq.zzm(bArr, iZzj2, zzdpVar);
                    zzffVar6.zze(zzee.zzc(zzdpVar.zzb));
                    while (iZzm3 < i2) {
                        int iZzj18 = zzdq.zzj(bArr, iZzm3, zzdpVar);
                        if (i3 != zzdpVar.zza) {
                            return iZzm3;
                        }
                        iZzm3 = zzdq.zzm(bArr, iZzj18, zzdpVar);
                        zzffVar6.zze(zzee.zzc(zzdpVar.zzb));
                    }
                    return iZzm3;
                }
                return iZzj2;
            default:
                if (i5 == 3) {
                    zzgb zzgbVarZzy = zzy(i6);
                    int i28 = (i3 & (-8)) | 4;
                    int iZzc = zzdq.zzc(zzgbVarZzy, bArr, i, i2, i28, zzdpVar);
                    zzeuVarZzd.add(zzdpVar.zzc);
                    while (iZzc < i2) {
                        int iZzj19 = zzdq.zzj(bArr, iZzc, zzdpVar);
                        if (i3 != zzdpVar.zza) {
                            return iZzc;
                        }
                        iZzc = zzdq.zzc(zzgbVarZzy, bArr, iZzj19, i2, i28, zzdpVar);
                        zzeuVarZzd.add(zzdpVar.zzc);
                    }
                    return iZzc;
                }
                return iZzj2;
        }
    }

    private final int zzq(int i) {
        if (i < this.zze || i > this.zzf) {
            return -1;
        }
        return zzt(i, 0);
    }

    private final int zzr(int i, int i2) {
        if (i < this.zze || i > this.zzf) {
            return -1;
        }
        return zzt(i, i2);
    }

    private final int zzs(int i) {
        return this.zzc[i + 2];
    }

    private final int zzt(int i, int i2) {
        int length = (this.zzc.length / 3) - 1;
        while (i2 <= length) {
            int i3 = (length + i2) >>> 1;
            int i4 = i3 * 3;
            int i5 = this.zzc[i4];
            if (i == i5) {
                return i4;
            }
            if (i < i5) {
                length = i3 - 1;
            } else {
                i2 = i3 + 1;
            }
        }
        return -1;
    }

    private static int zzu(int i) {
        return (i >>> 20) & 255;
    }

    private final int zzv(int i) {
        return this.zzc[i + 1];
    }

    private static <T> long zzw(T t, long j) {
        return ((Long) zzgz.zzf(t, j)).longValue();
    }

    private final zzet zzx(int i) {
        int i2 = i / 3;
        return (zzet) this.zzd[i2 + i2 + 1];
    }

    private final zzgb zzy(int i) {
        int i2 = i / 3;
        int i3 = i2 + i2;
        zzgb zzgbVar = (zzgb) this.zzd[i3];
        if (zzgbVar != null) {
            return zzgbVar;
        }
        zzgb<T> zzgbVarZzb = zzfy.zza().zzb((Class) this.zzd[i3 + 1]);
        this.zzd[i3] = zzgbVarZzb;
        return zzgbVarZzb;
    }

    private final Object zzz(int i) {
        int i2 = i / 3;
        return this.zzd[i2 + i2];
    }

    @Override // com.google.android.gms.internal.auth.zzgb
    public final int zza(T t) {
        int i;
        int iZzc;
        int length = this.zzc.length;
        int i2 = 0;
        for (int i3 = 0; i3 < length; i3 += 3) {
            int iZzv = zzv(i3);
            int i4 = this.zzc[i3];
            long j = 1048575 & iZzv;
            int iHashCode = 37;
            switch (zzu(iZzv)) {
                case 0:
                    i = i2 * 53;
                    iZzc = zzev.zzc(Double.doubleToLongBits(zzgz.zza(t, j)));
                    i2 = i + iZzc;
                    break;
                case 1:
                    i = i2 * 53;
                    iZzc = Float.floatToIntBits(zzgz.zzb(t, j));
                    i2 = i + iZzc;
                    break;
                case 2:
                    i = i2 * 53;
                    iZzc = zzev.zzc(zzgz.zzd(t, j));
                    i2 = i + iZzc;
                    break;
                case 3:
                    i = i2 * 53;
                    iZzc = zzev.zzc(zzgz.zzd(t, j));
                    i2 = i + iZzc;
                    break;
                case 4:
                    i = i2 * 53;
                    iZzc = zzgz.zzc(t, j);
                    i2 = i + iZzc;
                    break;
                case 5:
                    i = i2 * 53;
                    iZzc = zzev.zzc(zzgz.zzd(t, j));
                    i2 = i + iZzc;
                    break;
                case 6:
                    i = i2 * 53;
                    iZzc = zzgz.zzc(t, j);
                    i2 = i + iZzc;
                    break;
                case 7:
                    i = i2 * 53;
                    iZzc = zzev.zza(zzgz.zzt(t, j));
                    i2 = i + iZzc;
                    break;
                case 8:
                    i = i2 * 53;
                    iZzc = ((String) zzgz.zzf(t, j)).hashCode();
                    i2 = i + iZzc;
                    break;
                case 9:
                    Object objZzf = zzgz.zzf(t, j);
                    if (objZzf != null) {
                        iHashCode = objZzf.hashCode();
                    }
                    i2 = (i2 * 53) + iHashCode;
                    break;
                case 10:
                    i = i2 * 53;
                    iZzc = zzgz.zzf(t, j).hashCode();
                    i2 = i + iZzc;
                    break;
                case 11:
                    i = i2 * 53;
                    iZzc = zzgz.zzc(t, j);
                    i2 = i + iZzc;
                    break;
                case 12:
                    i = i2 * 53;
                    iZzc = zzgz.zzc(t, j);
                    i2 = i + iZzc;
                    break;
                case 13:
                    i = i2 * 53;
                    iZzc = zzgz.zzc(t, j);
                    i2 = i + iZzc;
                    break;
                case 14:
                    i = i2 * 53;
                    iZzc = zzev.zzc(zzgz.zzd(t, j));
                    i2 = i + iZzc;
                    break;
                case 15:
                    i = i2 * 53;
                    iZzc = zzgz.zzc(t, j);
                    i2 = i + iZzc;
                    break;
                case 16:
                    i = i2 * 53;
                    iZzc = zzev.zzc(zzgz.zzd(t, j));
                    i2 = i + iZzc;
                    break;
                case 17:
                    Object objZzf2 = zzgz.zzf(t, j);
                    if (objZzf2 != null) {
                        iHashCode = objZzf2.hashCode();
                    }
                    i2 = (i2 * 53) + iHashCode;
                    break;
                case 18:
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                case 24:
                case 25:
                case 26:
                case 27:
                case 28:
                case 29:
                case 30:
                case 31:
                case 32:
                case 33:
                case 34:
                case 35:
                case 36:
                case 37:
                case 38:
                case 39:
                case 40:
                case 41:
                case 42:
                case 43:
                case 44:
                case 45:
                case 46:
                case 47:
                case 48:
                case 49:
                    i = i2 * 53;
                    iZzc = zzgz.zzf(t, j).hashCode();
                    i2 = i + iZzc;
                    break;
                case 50:
                    i = i2 * 53;
                    iZzc = zzgz.zzf(t, j).hashCode();
                    i2 = i + iZzc;
                    break;
                case 51:
                    if (zzJ(t, i4, i3)) {
                        i = i2 * 53;
                        iZzc = zzev.zzc(Double.doubleToLongBits(((Double) zzgz.zzf(t, j)).doubleValue()));
                        i2 = i + iZzc;
                        break;
                    } else {
                        break;
                    }
                case 52:
                    if (zzJ(t, i4, i3)) {
                        i = i2 * 53;
                        iZzc = Float.floatToIntBits(((Float) zzgz.zzf(t, j)).floatValue());
                        i2 = i + iZzc;
                        break;
                    } else {
                        break;
                    }
                case 53:
                    if (zzJ(t, i4, i3)) {
                        i = i2 * 53;
                        iZzc = zzev.zzc(zzw(t, j));
                        i2 = i + iZzc;
                        break;
                    } else {
                        break;
                    }
                case 54:
                    if (zzJ(t, i4, i3)) {
                        i = i2 * 53;
                        iZzc = zzev.zzc(zzw(t, j));
                        i2 = i + iZzc;
                        break;
                    } else {
                        break;
                    }
                case 55:
                    if (zzJ(t, i4, i3)) {
                        i = i2 * 53;
                        iZzc = zzl(t, j);
                        i2 = i + iZzc;
                        break;
                    } else {
                        break;
                    }
                case 56:
                    if (zzJ(t, i4, i3)) {
                        i = i2 * 53;
                        iZzc = zzev.zzc(zzw(t, j));
                        i2 = i + iZzc;
                        break;
                    } else {
                        break;
                    }
                case 57:
                    if (zzJ(t, i4, i3)) {
                        i = i2 * 53;
                        iZzc = zzl(t, j);
                        i2 = i + iZzc;
                        break;
                    } else {
                        break;
                    }
                case 58:
                    if (zzJ(t, i4, i3)) {
                        i = i2 * 53;
                        iZzc = zzev.zza(((Boolean) zzgz.zzf(t, j)).booleanValue());
                        i2 = i + iZzc;
                        break;
                    } else {
                        break;
                    }
                case 59:
                    if (zzJ(t, i4, i3)) {
                        i = i2 * 53;
                        iZzc = ((String) zzgz.zzf(t, j)).hashCode();
                        i2 = i + iZzc;
                        break;
                    } else {
                        break;
                    }
                case 60:
                    if (zzJ(t, i4, i3)) {
                        i = i2 * 53;
                        iZzc = zzgz.zzf(t, j).hashCode();
                        i2 = i + iZzc;
                        break;
                    } else {
                        break;
                    }
                case 61:
                    if (zzJ(t, i4, i3)) {
                        i = i2 * 53;
                        iZzc = zzgz.zzf(t, j).hashCode();
                        i2 = i + iZzc;
                        break;
                    } else {
                        break;
                    }
                case 62:
                    if (zzJ(t, i4, i3)) {
                        i = i2 * 53;
                        iZzc = zzl(t, j);
                        i2 = i + iZzc;
                        break;
                    } else {
                        break;
                    }
                case 63:
                    if (zzJ(t, i4, i3)) {
                        i = i2 * 53;
                        iZzc = zzl(t, j);
                        i2 = i + iZzc;
                        break;
                    } else {
                        break;
                    }
                case 64:
                    if (zzJ(t, i4, i3)) {
                        i = i2 * 53;
                        iZzc = zzl(t, j);
                        i2 = i + iZzc;
                        break;
                    } else {
                        break;
                    }
                case 65:
                    if (zzJ(t, i4, i3)) {
                        i = i2 * 53;
                        iZzc = zzev.zzc(zzw(t, j));
                        i2 = i + iZzc;
                        break;
                    } else {
                        break;
                    }
                case 66:
                    if (zzJ(t, i4, i3)) {
                        i = i2 * 53;
                        iZzc = zzl(t, j);
                        i2 = i + iZzc;
                        break;
                    } else {
                        break;
                    }
                case 67:
                    if (zzJ(t, i4, i3)) {
                        i = i2 * 53;
                        iZzc = zzev.zzc(zzw(t, j));
                        i2 = i + iZzc;
                        break;
                    } else {
                        break;
                    }
                case 68:
                    if (zzJ(t, i4, i3)) {
                        i = i2 * 53;
                        iZzc = zzgz.zzf(t, j).hashCode();
                        i2 = i + iZzc;
                        break;
                    } else {
                        break;
                    }
            }
        }
        return (i2 * 53) + this.zzm.zza(t).hashCode();
    }

    /* JADX WARN: Code restructure failed: missing block: B:110:0x0322, code lost:
    
        if (r0 != r2) goto L111;
     */
    /* JADX WARN: Code restructure failed: missing block: B:111:0x0324, code lost:
    
        r15 = r28;
        r14 = r29;
        r12 = r30;
        r13 = r32;
        r11 = r33;
        r9 = r34;
        r1 = r18;
        r2 = r19;
        r3 = r20;
        r5 = r22;
        r6 = r24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:112:0x033c, code lost:
    
        r7 = r33;
        r2 = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:118:0x0366, code lost:
    
        if (r0 != r15) goto L111;
     */
    /* JADX WARN: Code restructure failed: missing block: B:123:0x0388, code lost:
    
        if (r0 != r15) goto L111;
     */
    /* JADX WARN: Failed to find 'out' block for switch in B:24:0x0089. Please report as an issue. */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    final int zzb(T r29, byte[] r30, int r31, int r32, int r33, com.google.android.gms.internal.auth.zzdp r34) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 1086
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.auth.zzft.zzb(java.lang.Object, byte[], int, int, int, com.google.android.gms.internal.auth.zzdp):int");
    }

    @Override // com.google.android.gms.internal.auth.zzgb
    public final T zzd() {
        return (T) ((zzeq) this.zzg).zzj(4, null, null);
    }

    @Override // com.google.android.gms.internal.auth.zzgb
    public final void zze(T t) {
        int i;
        int i2 = this.zzj;
        while (true) {
            i = this.zzk;
            if (i2 >= i) {
                break;
            }
            long jZzv = zzv(this.zzi[i2]) & 1048575;
            Object objZzf = zzgz.zzf(t, jZzv);
            if (objZzf != null) {
                ((zzfk) objZzf).zzc();
                zzgz.zzp(t, jZzv, objZzf);
            }
            i2++;
        }
        int length = this.zzi.length;
        while (i < length) {
            this.zzl.zza(t, this.zzi[i]);
            i++;
        }
        this.zzm.zze(t);
    }

    @Override // com.google.android.gms.internal.auth.zzgb
    public final void zzg(T t, byte[] bArr, int i, int i2, zzdp zzdpVar) throws IOException {
        if (this.zzh) {
            zzo(t, bArr, i, i2, zzdpVar);
        } else {
            zzb(t, bArr, i, i2, 0, zzdpVar);
        }
    }

    @Override // com.google.android.gms.internal.auth.zzgb
    public final boolean zzh(T t, T t2) {
        boolean zZzh;
        int length = this.zzc.length;
        for (int i = 0; i < length; i += 3) {
            int iZzv = zzv(i);
            long j = iZzv & 1048575;
            switch (zzu(iZzv)) {
                case 0:
                    if (!zzF(t, t2, i) || Double.doubleToLongBits(zzgz.zza(t, j)) != Double.doubleToLongBits(zzgz.zza(t2, j))) {
                        return false;
                    }
                    continue;
                    break;
                case 1:
                    if (!zzF(t, t2, i) || Float.floatToIntBits(zzgz.zzb(t, j)) != Float.floatToIntBits(zzgz.zzb(t2, j))) {
                        return false;
                    }
                    continue;
                    break;
                case 2:
                    if (!zzF(t, t2, i) || zzgz.zzd(t, j) != zzgz.zzd(t2, j)) {
                        return false;
                    }
                    continue;
                    break;
                case 3:
                    if (!zzF(t, t2, i) || zzgz.zzd(t, j) != zzgz.zzd(t2, j)) {
                        return false;
                    }
                    continue;
                    break;
                case 4:
                    if (!zzF(t, t2, i) || zzgz.zzc(t, j) != zzgz.zzc(t2, j)) {
                        return false;
                    }
                    continue;
                    break;
                case 5:
                    if (!zzF(t, t2, i) || zzgz.zzd(t, j) != zzgz.zzd(t2, j)) {
                        return false;
                    }
                    continue;
                    break;
                case 6:
                    if (!zzF(t, t2, i) || zzgz.zzc(t, j) != zzgz.zzc(t2, j)) {
                        return false;
                    }
                    continue;
                    break;
                case 7:
                    if (!zzF(t, t2, i) || zzgz.zzt(t, j) != zzgz.zzt(t2, j)) {
                        return false;
                    }
                    continue;
                    break;
                case 8:
                    if (!zzF(t, t2, i) || !zzgd.zzh(zzgz.zzf(t, j), zzgz.zzf(t2, j))) {
                        return false;
                    }
                    continue;
                    break;
                case 9:
                    if (!zzF(t, t2, i) || !zzgd.zzh(zzgz.zzf(t, j), zzgz.zzf(t2, j))) {
                        return false;
                    }
                    continue;
                    break;
                case 10:
                    if (!zzF(t, t2, i) || !zzgd.zzh(zzgz.zzf(t, j), zzgz.zzf(t2, j))) {
                        return false;
                    }
                    continue;
                    break;
                case 11:
                    if (!zzF(t, t2, i) || zzgz.zzc(t, j) != zzgz.zzc(t2, j)) {
                        return false;
                    }
                    continue;
                    break;
                case 12:
                    if (!zzF(t, t2, i) || zzgz.zzc(t, j) != zzgz.zzc(t2, j)) {
                        return false;
                    }
                    continue;
                    break;
                case 13:
                    if (!zzF(t, t2, i) || zzgz.zzc(t, j) != zzgz.zzc(t2, j)) {
                        return false;
                    }
                    continue;
                    break;
                case 14:
                    if (!zzF(t, t2, i) || zzgz.zzd(t, j) != zzgz.zzd(t2, j)) {
                        return false;
                    }
                    continue;
                    break;
                case 15:
                    if (!zzF(t, t2, i) || zzgz.zzc(t, j) != zzgz.zzc(t2, j)) {
                        return false;
                    }
                    continue;
                    break;
                case 16:
                    if (!zzF(t, t2, i) || zzgz.zzd(t, j) != zzgz.zzd(t2, j)) {
                        return false;
                    }
                    continue;
                    break;
                case 17:
                    if (!zzF(t, t2, i) || !zzgd.zzh(zzgz.zzf(t, j), zzgz.zzf(t2, j))) {
                        return false;
                    }
                    continue;
                    break;
                case 18:
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                case 24:
                case 25:
                case 26:
                case 27:
                case 28:
                case 29:
                case 30:
                case 31:
                case 32:
                case 33:
                case 34:
                case 35:
                case 36:
                case 37:
                case 38:
                case 39:
                case 40:
                case 41:
                case 42:
                case 43:
                case 44:
                case 45:
                case 46:
                case 47:
                case 48:
                case 49:
                    zZzh = zzgd.zzh(zzgz.zzf(t, j), zzgz.zzf(t2, j));
                    break;
                case 50:
                    zZzh = zzgd.zzh(zzgz.zzf(t, j), zzgz.zzf(t2, j));
                    break;
                case 51:
                case 52:
                case 53:
                case 54:
                case 55:
                case 56:
                case 57:
                case 58:
                case 59:
                case 60:
                case 61:
                case 62:
                case 63:
                case 64:
                case 65:
                case 66:
                case 67:
                case 68:
                    long jZzs = zzs(i) & 1048575;
                    if (zzgz.zzc(t, jZzs) != zzgz.zzc(t2, jZzs) || !zzgd.zzh(zzgz.zzf(t, j), zzgz.zzf(t2, j))) {
                        return false;
                    }
                    continue;
                    break;
                default:
            }
            if (!zZzh) {
                return false;
            }
        }
        return this.zzm.zza(t).equals(this.zzm.zza(t2));
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:42:0x009d  */
    @Override // com.google.android.gms.internal.auth.zzgb
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean zzi(T r18) {
        /*
            Method dump skipped, instructions count: 231
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.auth.zzft.zzi(java.lang.Object):boolean");
    }

    @Override // com.google.android.gms.internal.auth.zzgb
    public final void zzf(T t, T t2) {
        Objects.requireNonNull(t2);
        for (int i = 0; i < this.zzc.length; i += 3) {
            int iZzv = zzv(i);
            long j = 1048575 & iZzv;
            int i2 = this.zzc[i];
            switch (zzu(iZzv)) {
                case 0:
                    if (zzG(t2, i)) {
                        zzgz.zzl(t, j, zzgz.zza(t2, j));
                        zzD(t, i);
                        break;
                    } else {
                        break;
                    }
                case 1:
                    if (zzG(t2, i)) {
                        zzgz.zzm(t, j, zzgz.zzb(t2, j));
                        zzD(t, i);
                        break;
                    } else {
                        break;
                    }
                case 2:
                    if (zzG(t2, i)) {
                        zzgz.zzo(t, j, zzgz.zzd(t2, j));
                        zzD(t, i);
                        break;
                    } else {
                        break;
                    }
                case 3:
                    if (zzG(t2, i)) {
                        zzgz.zzo(t, j, zzgz.zzd(t2, j));
                        zzD(t, i);
                        break;
                    } else {
                        break;
                    }
                case 4:
                    if (zzG(t2, i)) {
                        zzgz.zzn(t, j, zzgz.zzc(t2, j));
                        zzD(t, i);
                        break;
                    } else {
                        break;
                    }
                case 5:
                    if (zzG(t2, i)) {
                        zzgz.zzo(t, j, zzgz.zzd(t2, j));
                        zzD(t, i);
                        break;
                    } else {
                        break;
                    }
                case 6:
                    if (zzG(t2, i)) {
                        zzgz.zzn(t, j, zzgz.zzc(t2, j));
                        zzD(t, i);
                        break;
                    } else {
                        break;
                    }
                case 7:
                    if (zzG(t2, i)) {
                        zzgz.zzk(t, j, zzgz.zzt(t2, j));
                        zzD(t, i);
                        break;
                    } else {
                        break;
                    }
                case 8:
                    if (zzG(t2, i)) {
                        zzgz.zzp(t, j, zzgz.zzf(t2, j));
                        zzD(t, i);
                        break;
                    } else {
                        break;
                    }
                case 9:
                    zzB(t, t2, i);
                    break;
                case 10:
                    if (zzG(t2, i)) {
                        zzgz.zzp(t, j, zzgz.zzf(t2, j));
                        zzD(t, i);
                        break;
                    } else {
                        break;
                    }
                case 11:
                    if (zzG(t2, i)) {
                        zzgz.zzn(t, j, zzgz.zzc(t2, j));
                        zzD(t, i);
                        break;
                    } else {
                        break;
                    }
                case 12:
                    if (zzG(t2, i)) {
                        zzgz.zzn(t, j, zzgz.zzc(t2, j));
                        zzD(t, i);
                        break;
                    } else {
                        break;
                    }
                case 13:
                    if (zzG(t2, i)) {
                        zzgz.zzn(t, j, zzgz.zzc(t2, j));
                        zzD(t, i);
                        break;
                    } else {
                        break;
                    }
                case 14:
                    if (zzG(t2, i)) {
                        zzgz.zzo(t, j, zzgz.zzd(t2, j));
                        zzD(t, i);
                        break;
                    } else {
                        break;
                    }
                case 15:
                    if (zzG(t2, i)) {
                        zzgz.zzn(t, j, zzgz.zzc(t2, j));
                        zzD(t, i);
                        break;
                    } else {
                        break;
                    }
                case 16:
                    if (zzG(t2, i)) {
                        zzgz.zzo(t, j, zzgz.zzd(t2, j));
                        zzD(t, i);
                        break;
                    } else {
                        break;
                    }
                case 17:
                    zzB(t, t2, i);
                    break;
                case 18:
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                case 24:
                case 25:
                case 26:
                case 27:
                case 28:
                case 29:
                case 30:
                case 31:
                case 32:
                case 33:
                case 34:
                case 35:
                case 36:
                case 37:
                case 38:
                case 39:
                case 40:
                case 41:
                case 42:
                case 43:
                case 44:
                case 45:
                case 46:
                case 47:
                case 48:
                case 49:
                    this.zzl.zzb(t, t2, j);
                    break;
                case 50:
                    zzgd.zzi(this.zzp, t, t2, j);
                    break;
                case 51:
                case 52:
                case 53:
                case 54:
                case 55:
                case 56:
                case 57:
                case 58:
                case 59:
                    if (zzJ(t2, i2, i)) {
                        zzgz.zzp(t, j, zzgz.zzf(t2, j));
                        zzE(t, i2, i);
                        break;
                    } else {
                        break;
                    }
                case 60:
                    zzC(t, t2, i);
                    break;
                case 61:
                case 62:
                case 63:
                case 64:
                case 65:
                case 66:
                case 67:
                    if (zzJ(t2, i2, i)) {
                        zzgz.zzp(t, j, zzgz.zzf(t2, j));
                        zzE(t, i2, i);
                        break;
                    } else {
                        break;
                    }
                case 68:
                    zzC(t, t2, i);
                    break;
            }
        }
        zzgd.zzf(this.zzm, t, t2);
    }
}
