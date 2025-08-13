package com.baidu.location.b;

import com.baidu.location.b.k;
import java.util.Comparator;

/* loaded from: classes.dex */
class l implements Comparator<k.c> {
    final /* synthetic */ k a;

    l(k kVar) {
        this.a = kVar;
    }

    @Override // java.util.Comparator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compare(k.c cVar, k.c cVar2) {
        if (cVar.b > cVar2.b) {
            return -1;
        }
        return cVar.b == cVar2.b ? 0 : 1;
    }
}
