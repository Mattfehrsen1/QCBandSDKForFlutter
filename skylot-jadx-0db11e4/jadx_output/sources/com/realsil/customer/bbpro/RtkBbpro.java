package com.realsil.customer.bbpro;

import android.content.Context;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/bbpro/RtkBbpro.class */
public final class RtkBbpro {
    public static boolean DEBUG_ENABLE = false;

    public static void initizlize(Context context) {
        initialize(context);
    }

    public static void initialize(Context context, boolean z) {
        DEBUG_ENABLE = z;
    }

    public static void initialize(Context context) {
        initialize(context, false);
    }
}
