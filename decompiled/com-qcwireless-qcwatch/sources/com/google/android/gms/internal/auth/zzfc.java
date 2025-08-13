package com.google.android.gms.internal.auth;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-auth-base@@17.1.4 */
/* loaded from: classes2.dex */
final class zzfc extends zzfe {
    private static final Class<?> zza = Collections.unmodifiableList(Collections.emptyList()).getClass();

    private zzfc() {
        super(null);
    }

    /* synthetic */ zzfc(zzfb zzfbVar) {
        super(null);
    }

    @Override // com.google.android.gms.internal.auth.zzfe
    final void zza(Object obj, long j) {
        Object objUnmodifiableList;
        List list = (List) zzgz.zzf(obj, j);
        if (list instanceof zzfa) {
            objUnmodifiableList = ((zzfa) list).zze();
        } else {
            if (zza.isAssignableFrom(list.getClass())) {
                return;
            }
            if ((list instanceof zzfx) && (list instanceof zzeu)) {
                zzeu zzeuVar = (zzeu) list;
                if (zzeuVar.zzc()) {
                    zzeuVar.zzb();
                    return;
                }
                return;
            }
            objUnmodifiableList = Collections.unmodifiableList(list);
        }
        zzgz.zzp(obj, j, objUnmodifiableList);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.internal.auth.zzfe
    final <E> void zzb(Object obj, Object obj2, long j) {
        zzez zzezVar;
        List list = (List) zzgz.zzf(obj2, j);
        int size = list.size();
        List listZzd = (List) zzgz.zzf(obj, j);
        if (listZzd.isEmpty()) {
            listZzd = listZzd instanceof zzfa ? new zzez(size) : ((listZzd instanceof zzfx) && (listZzd instanceof zzeu)) ? ((zzeu) listZzd).zzd(size) : new ArrayList(size);
            zzgz.zzp(obj, j, listZzd);
        } else {
            if (zza.isAssignableFrom(listZzd.getClass())) {
                ArrayList arrayList = new ArrayList(listZzd.size() + size);
                arrayList.addAll(listZzd);
                zzgz.zzp(obj, j, arrayList);
                zzezVar = arrayList;
            } else if (listZzd instanceof zzgu) {
                zzez zzezVar2 = new zzez(listZzd.size() + size);
                zzezVar2.addAll(zzezVar2.size(), (zzgu) listZzd);
                zzgz.zzp(obj, j, zzezVar2);
                zzezVar = zzezVar2;
            } else if ((listZzd instanceof zzfx) && (listZzd instanceof zzeu)) {
                zzeu zzeuVar = (zzeu) listZzd;
                if (!zzeuVar.zzc()) {
                    listZzd = zzeuVar.zzd(listZzd.size() + size);
                    zzgz.zzp(obj, j, listZzd);
                }
            }
            listZzd = zzezVar;
        }
        int size2 = listZzd.size();
        int size3 = list.size();
        if (size2 > 0 && size3 > 0) {
            listZzd.addAll(list);
        }
        if (size2 > 0) {
            list = listZzd;
        }
        zzgz.zzp(obj, j, list);
    }
}
