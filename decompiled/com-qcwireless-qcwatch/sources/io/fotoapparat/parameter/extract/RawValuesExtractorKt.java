package io.fotoapparat.parameter.extract;

import android.hardware.Camera;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;

/* compiled from: RawValuesExtractor.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a$\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00060\u0003R\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001H\u0000\u001a \u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u0001*\u00060\u0003R\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0002H\u0002¨\u0006\b"}, d2 = {"extractRawCameraValues", "", "", "Landroid/hardware/Camera$Parameters;", "Landroid/hardware/Camera;", "keys", "getValuesForKey", "key", "fotoapparat_release"}, k = 2, mv = {1, 1, 13})
/* loaded from: classes4.dex */
public final class RawValuesExtractorKt {
    public static final List<String> extractRawCameraValues(Camera.Parameters receiver$0, List<String> keys) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(keys, "keys");
        Iterator<T> it = keys.iterator();
        while (it.hasNext()) {
            List<String> valuesForKey = getValuesForKey(receiver$0, (String) it.next());
            if (valuesForKey != null) {
                return valuesForKey;
            }
        }
        return CollectionsKt.emptyList();
    }

    private static final List<String> getValuesForKey(Camera.Parameters parameters, String str) {
        String str2 = parameters.get(str);
        if (str2 == null) {
            return null;
        }
        return new Regex(",").split(str2, 0);
    }
}
