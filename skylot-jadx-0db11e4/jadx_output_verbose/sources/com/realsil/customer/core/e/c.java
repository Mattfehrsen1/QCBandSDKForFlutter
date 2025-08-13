package com.realsil.customer.core.e;

import android.content.Context;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/core/e/c.class */
public final class c {
    public final a a;

    public c(Context context, int i) {
        this.a = a(context, i);
    }

    public static a a(Context context, int i) {
        if (i >= 21) {
            return new e(context);
        }
        if (i >= 18) {
            return new d(context);
        }
        return null;
    }
}
