package io.fotoapparat.util;

import android.os.Build;
import kotlin.Metadata;

/* compiled from: ApiChecks.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u000b\n\u0000\u001a\b\u0010\u0000\u001a\u00020\u0001H\u0000¨\u0006\u0002"}, d2 = {"isAboveLollipop", "", "fotoapparat_release"}, k = 2, mv = {1, 1, 13})
/* loaded from: classes4.dex */
public final class ApiChecksKt {
    public static final boolean isAboveLollipop() {
        return Build.VERSION.SDK_INT >= 21;
    }
}
