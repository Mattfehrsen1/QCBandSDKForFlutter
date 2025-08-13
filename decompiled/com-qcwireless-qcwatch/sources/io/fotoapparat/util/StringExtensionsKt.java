package io.fotoapparat.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: StringExtensions.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0002\u0010\"\n\u0000\u001a\u000e\u0010\u0005\u001a\u00020\u0001*\u0004\u0018\u00010\u0006H\u0000\u001a\u0012\u0010\u0005\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00060\u0007H\u0000\"\u001c\u0010\u0000\u001a\n \u0002*\u0004\u0018\u00010\u00010\u0001X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0003\u0010\u0004¨\u0006\b"}, d2 = {"lineSeparator", "", "kotlin.jvm.PlatformType", "getLineSeparator", "()Ljava/lang/String;", "wrap", "", "", "fotoapparat_release"}, k = 2, mv = {1, 1, 13})
/* loaded from: classes4.dex */
public final class StringExtensionsKt {
    private static final String lineSeparator = System.getProperty("line.separator");

    public static final String getLineSeparator() {
        return lineSeparator;
    }

    public static final String wrap(Set<? extends Object> receiver$0) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        StringBuilder sb = new StringBuilder();
        Set<? extends Object> set = receiver$0;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(set, 10));
        Iterator<T> it = set.iterator();
        while (it.hasNext()) {
            arrayList.add(lineSeparator + "\t\t" + it.next());
        }
        sb.append(arrayList);
        sb.append(lineSeparator);
        return sb.toString();
    }

    public static final String wrap(Object obj) {
        if (obj == null) {
            obj = "null";
        }
        return "\t\t" + obj + lineSeparator;
    }
}
