package com.google.android.gms.internal.fitness;

import java.util.List;

/* compiled from: com.google.android.gms:play-services-fitness@@21.1.0 */
/* loaded from: classes2.dex */
public final class zzd {
    public static int zza(Object obj, List list) {
        if (obj == null) {
            return -1;
        }
        int iIndexOf = list.indexOf(obj);
        if (iIndexOf >= 0) {
            return iIndexOf;
        }
        list.add(obj);
        return list.size() - 1;
    }
}
