package org.koin.ext;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: StringExt.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\u0010\u000e\n\u0002\b\u0003\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002\u001a\n\u0010\u0003\u001a\u00020\u0001*\u00020\u0002\u001a\n\u0010\u0004\u001a\u00020\u0002*\u00020\u0002¨\u0006\u0005"}, d2 = {"isFloat", "", "", "isInt", "quoted", "koin-core"}, k = 2, mv = {1, 1, 15})
/* loaded from: classes5.dex */
public final class StringExtKt {
    public static final boolean isFloat(String isFloat) {
        Intrinsics.checkParameterIsNotNull(isFloat, "$this$isFloat");
        return StringsKt.toFloatOrNull(isFloat) != null;
    }

    public static final boolean isInt(String isInt) {
        Intrinsics.checkParameterIsNotNull(isInt, "$this$isInt");
        return StringsKt.toIntOrNull(isInt) != null;
    }

    public static final String quoted(String quoted) {
        Intrinsics.checkParameterIsNotNull(quoted, "$this$quoted");
        return StringsKt.replace$default(quoted, "\"", "", false, 4, (Object) null);
    }
}
