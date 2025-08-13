package com.google.android.gms.internal.auth;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

/* compiled from: com.google.android.gms:play-services-auth-base@@17.1.4 */
/* loaded from: classes2.dex */
final class zzff extends zzdn<Long> implements RandomAccess, zzeu, zzfx {
    private static final zzff zza;
    private long[] zzb;
    private int zzc;

    static {
        zzff zzffVar = new zzff(new long[0], 0);
        zza = zzffVar;
        zzffVar.zzb();
    }

    zzff() {
        this(new long[10], 0);
    }

    private final String zzf(int i) {
        int i2 = this.zzc;
        StringBuilder sb = new StringBuilder(35);
        sb.append("Index:");
        sb.append(i);
        sb.append(", Size:");
        sb.append(i2);
        return sb.toString();
    }

    private final void zzg(int i) {
        if (i < 0 || i >= this.zzc) {
            throw new IndexOutOfBoundsException(zzf(i));
        }
    }

    @Override // com.google.android.gms.internal.auth.zzdn, java.util.AbstractList, java.util.List
    public final /* bridge */ /* synthetic */ void add(int i, Object obj) {
        int i2;
        long jLongValue = ((Long) obj).longValue();
        zza();
        if (i < 0 || i > (i2 = this.zzc)) {
            throw new IndexOutOfBoundsException(zzf(i));
        }
        long[] jArr = this.zzb;
        if (i2 < jArr.length) {
            System.arraycopy(jArr, i, jArr, i + 1, i2 - i);
        } else {
            long[] jArr2 = new long[((i2 * 3) / 2) + 1];
            System.arraycopy(jArr, 0, jArr2, 0, i);
            System.arraycopy(this.zzb, i, jArr2, i + 1, this.zzc - i);
            this.zzb = jArr2;
        }
        this.zzb[i] = jLongValue;
        this.zzc++;
        this.modCount++;
    }

    @Override // com.google.android.gms.internal.auth.zzdn, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean addAll(Collection<? extends Long> collection) {
        zza();
        zzev.zze(collection);
        if (!(collection instanceof zzff)) {
            return super.addAll(collection);
        }
        zzff zzffVar = (zzff) collection;
        int i = zzffVar.zzc;
        if (i == 0) {
            return false;
        }
        int i2 = this.zzc;
        if (Integer.MAX_VALUE - i2 < i) {
            throw new OutOfMemoryError();
        }
        int i3 = i2 + i;
        long[] jArr = this.zzb;
        if (i3 > jArr.length) {
            this.zzb = Arrays.copyOf(jArr, i3);
        }
        System.arraycopy(zzffVar.zzb, 0, this.zzb, this.zzc, zzffVar.zzc);
        this.zzc = i3;
        this.modCount++;
        return true;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean contains(Object obj) {
        return indexOf(obj) != -1;
    }

    @Override // com.google.android.gms.internal.auth.zzdn, java.util.AbstractList, java.util.Collection, java.util.List
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzff)) {
            return super.equals(obj);
        }
        zzff zzffVar = (zzff) obj;
        if (this.zzc != zzffVar.zzc) {
            return false;
        }
        long[] jArr = zzffVar.zzb;
        for (int i = 0; i < this.zzc; i++) {
            if (this.zzb[i] != jArr[i]) {
                return false;
            }
        }
        return true;
    }

    @Override // java.util.AbstractList, java.util.List
    public final /* bridge */ /* synthetic */ Object get(int i) {
        zzg(i);
        return Long.valueOf(this.zzb[i]);
    }

    @Override // com.google.android.gms.internal.auth.zzdn, java.util.AbstractList, java.util.Collection, java.util.List
    public final int hashCode() {
        int iZzc = 1;
        for (int i = 0; i < this.zzc; i++) {
            iZzc = (iZzc * 31) + zzev.zzc(this.zzb[i]);
        }
        return iZzc;
    }

    @Override // java.util.AbstractList, java.util.List
    public final int indexOf(Object obj) {
        if (!(obj instanceof Long)) {
            return -1;
        }
        long jLongValue = ((Long) obj).longValue();
        int i = this.zzc;
        for (int i2 = 0; i2 < i; i2++) {
            if (this.zzb[i2] == jLongValue) {
                return i2;
            }
        }
        return -1;
    }

    @Override // com.google.android.gms.internal.auth.zzdn, java.util.AbstractList, java.util.List
    public final /* bridge */ /* synthetic */ Object remove(int i) {
        zza();
        zzg(i);
        long[] jArr = this.zzb;
        long j = jArr[i];
        if (i < this.zzc - 1) {
            System.arraycopy(jArr, i + 1, jArr, i, (r3 - i) - 1);
        }
        this.zzc--;
        this.modCount++;
        return Long.valueOf(j);
    }

    @Override // java.util.AbstractList
    protected final void removeRange(int i, int i2) {
        zza();
        if (i2 < i) {
            throw new IndexOutOfBoundsException("toIndex < fromIndex");
        }
        long[] jArr = this.zzb;
        System.arraycopy(jArr, i2, jArr, i, this.zzc - i2);
        this.zzc -= i2 - i;
        this.modCount++;
    }

    @Override // com.google.android.gms.internal.auth.zzdn, java.util.AbstractList, java.util.List
    public final /* bridge */ /* synthetic */ Object set(int i, Object obj) {
        long jLongValue = ((Long) obj).longValue();
        zza();
        zzg(i);
        long[] jArr = this.zzb;
        long j = jArr[i];
        jArr[i] = jLongValue;
        return Long.valueOf(j);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zzc;
    }

    @Override // com.google.android.gms.internal.auth.zzeu
    public final /* bridge */ /* synthetic */ zzeu zzd(int i) {
        if (i >= this.zzc) {
            return new zzff(Arrays.copyOf(this.zzb, i), this.zzc);
        }
        throw new IllegalArgumentException();
    }

    public final void zze(long j) {
        zza();
        int i = this.zzc;
        long[] jArr = this.zzb;
        if (i == jArr.length) {
            long[] jArr2 = new long[((i * 3) / 2) + 1];
            System.arraycopy(jArr, 0, jArr2, 0, i);
            this.zzb = jArr2;
        }
        long[] jArr3 = this.zzb;
        int i2 = this.zzc;
        this.zzc = i2 + 1;
        jArr3[i2] = j;
    }

    private zzff(long[] jArr, int i) {
        this.zzb = jArr;
        this.zzc = i;
    }

    @Override // com.google.android.gms.internal.auth.zzdn, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final /* bridge */ /* synthetic */ boolean add(Object obj) {
        zze(((Long) obj).longValue());
        return true;
    }
}
