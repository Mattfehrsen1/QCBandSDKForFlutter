package com.realsil.sdk.bbpro;

import android.content.Context;

/* loaded from: classes3.dex */
public final class RtkBbpro {
    public static boolean DEBUG_ENABLE = false;

    public static void initialize(Context context, boolean z) {
        DEBUG_ENABLE = z;
    }

    public static void initizlize(Context context) {
        initialize(context);
    }

    public static void initialize(Context context) {
        initialize(context, false);
    }
}
