package com.realsil.sdk.core.f;

import android.content.Context;
import android.os.Build;

/* loaded from: classes3.dex */
public final class c {
    public a a;

    public c(Context context) {
        int i = Build.VERSION.SDK_INT;
        this.a = a(context);
    }

    public final a a(Context context) {
        int i = Build.VERSION.SDK_INT;
        if (i >= 21) {
            return new e(context);
        }
        if (i >= 18) {
            return new d(context);
        }
        return null;
    }
}
