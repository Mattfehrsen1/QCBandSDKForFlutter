package com.google.android.gms.internal.common;

import java.util.Iterator;
import java.util.NoSuchElementException;
import javax.annotation.CheckForNull;

/* compiled from: com.google.android.gms:play-services-basement@@18.0.0 */
/* loaded from: classes2.dex */
abstract class zzj<T> implements Iterator<T> {

    @CheckForNull
    private T zza;
    private int zzb = 2;

    protected zzj() {
    }

    @Override // java.util.Iterator
    public final T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        this.zzb = 2;
        T t = this.zza;
        this.zza = null;
        return t;
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException();
    }

    @CheckForNull
    protected abstract T zza();

    @CheckForNull
    protected final T zzb() {
        this.zzb = 3;
        return null;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        int i = this.zzb;
        if (i == 4) {
            throw new IllegalStateException();
        }
        int i2 = i - 1;
        if (i == 0) {
            throw null;
        }
        if (i2 == 0) {
            return true;
        }
        if (i2 != 2) {
            this.zzb = 4;
            this.zza = zza();
            if (this.zzb != 3) {
                this.zzb = 1;
                return true;
            }
        }
        return false;
    }
}
