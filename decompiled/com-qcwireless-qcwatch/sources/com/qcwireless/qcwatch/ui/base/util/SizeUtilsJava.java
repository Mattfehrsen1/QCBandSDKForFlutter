package com.qcwireless.qcwatch.ui.base.util;

import android.content.Context;

/* loaded from: classes3.dex */
public class SizeUtilsJava {
    public static float px2dp(Context context, float px) {
        return (px / context.getResources().getDisplayMetrics().density) + 0.5f;
    }

    public static float dp2px(Context context, float dp) {
        return (dp * context.getResources().getDisplayMetrics().density) + 0.5f;
    }
}
