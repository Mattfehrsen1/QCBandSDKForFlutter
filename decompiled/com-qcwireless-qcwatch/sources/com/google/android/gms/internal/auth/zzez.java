package com.google.android.gms.internal.auth;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.RandomAccess;

/* compiled from: com.google.android.gms:play-services-auth-base@@17.1.4 */
/* loaded from: classes2.dex */
public final class zzez extends zzdn<String> implements RandomAccess, zzfa {
    public static final zzfa zza;
    private static final zzez zzb;
    private final List<Object> zzc;

    static {
        zzez zzezVar = new zzez(10);
        zzb = zzezVar;
        zzezVar.zzb();
        zza = zzezVar;
    }

    public zzez() {
        this(10);
    }

    private static String zzh(Object obj) {
        return obj instanceof String ? (String) obj : obj instanceof zzeb ? ((zzeb) obj).zzm(zzev.zza) : zzev.zzh((byte[]) obj);
    }

    @Override // com.google.android.gms.internal.auth.zzdn, java.util.AbstractList, java.util.List
    public final /* bridge */ /* synthetic */ void add(int i, Object obj) {
        zza();
        this.zzc.add(i, (String) obj);
        this.modCount++;
    }

    @Override // com.google.android.gms.internal.auth.zzdn, java.util.AbstractList, java.util.List
    public final boolean addAll(int i, Collection<? extends String> collection) {
        zza();
        if (collection instanceof zzfa) {
            collection = ((zzfa) collection).zzg();
        }
        boolean zAddAll = this.zzc.addAll(i, collection);
        this.modCount++;
        return zAddAll;
    }

    @Override // com.google.android.gms.internal.auth.zzdn, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final void clear() {
        zza();
        this.zzc.clear();
        this.modCount++;
    }

    @Override // com.google.android.gms.internal.auth.zzdn, java.util.AbstractList, java.util.List
    public final /* bridge */ /* synthetic */ Object remove(int i) {
        zza();
        Object objRemove = this.zzc.remove(i);
        this.modCount++;
        return zzh(objRemove);
    }

    @Override // com.google.android.gms.internal.auth.zzdn, java.util.AbstractList, java.util.List
    public final /* bridge */ /* synthetic */ Object set(int i, Object obj) {
        zza();
        return zzh(this.zzc.set(i, (String) obj));
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zzc.size();
    }

    @Override // com.google.android.gms.internal.auth.zzeu
    public final /* bridge */ /* synthetic */ zzeu zzd(int i) {
        if (i < size()) {
            throw new IllegalArgumentException();
        }
        ArrayList arrayList = new ArrayList(i);
        arrayList.addAll(this.zzc);
        return new zzez((ArrayList<Object>) arrayList);
    }

    @Override // com.google.android.gms.internal.auth.zzfa
    public final zzfa zze() {
        return zzc() ? new zzgu(this) : this;
    }

    @Override // java.util.AbstractList, java.util.List
    /* renamed from: zzf, reason: merged with bridge method [inline-methods] */
    public final String get(int i) {
        Object obj = this.zzc.get(i);
        if (obj instanceof String) {
            return (String) obj;
        }
        if (obj instanceof zzeb) {
            zzeb zzebVar = (zzeb) obj;
            String strZzm = zzebVar.zzm(zzev.zza);
            if (zzebVar.zzh()) {
                this.zzc.set(i, strZzm);
            }
            return strZzm;
        }
        byte[] bArr = (byte[]) obj;
        String strZzh = zzev.zzh(bArr);
        if (zzev.zzi(bArr)) {
            this.zzc.set(i, strZzh);
        }
        return strZzh;
    }

    @Override // com.google.android.gms.internal.auth.zzfa
    public final List<?> zzg() {
        return Collections.unmodifiableList(this.zzc);
    }

    public zzez(int i) {
        this.zzc = new ArrayList(i);
    }

    private zzez(ArrayList<Object> arrayList) {
        this.zzc = arrayList;
    }

    @Override // com.google.android.gms.internal.auth.zzdn, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean addAll(Collection<? extends String> collection) {
        return addAll(size(), collection);
    }
}
