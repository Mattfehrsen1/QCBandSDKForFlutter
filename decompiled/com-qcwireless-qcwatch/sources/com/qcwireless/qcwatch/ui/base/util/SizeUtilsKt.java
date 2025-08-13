package com.qcwireless.qcwatch.ui.base.util;

import android.content.Context;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SizeUtils.kt */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a\u0016\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0001\u001a\u0016\u0010\u0005\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0001¨\u0006\u0007"}, d2 = {"dp2px", "", "context", "Landroid/content/Context;", "dp", "px2dp", "px", "app_qwatch_proRelease"}, k = 2, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class SizeUtilsKt {
    public static final float px2dp(Context context, float f) {
        Intrinsics.checkNotNullParameter(context, "context");
        return (f / context.getResources().getDisplayMetrics().density) + 0.5f;
    }

    public static final float dp2px(Context context, float f) {
        Intrinsics.checkNotNullParameter(context, "context");
        return (f * context.getResources().getDisplayMetrics().density) + 0.5f;
    }
}
