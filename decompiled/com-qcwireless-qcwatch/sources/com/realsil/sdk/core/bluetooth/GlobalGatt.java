package com.realsil.sdk.core.bluetooth;

import android.content.Context;
import com.realsil.sdk.core.c.b;

/* loaded from: classes3.dex */
public final class GlobalGatt extends b {
    public static GlobalGatt n;

    public GlobalGatt(Context context) {
        super(context);
    }

    public static GlobalGatt getInstance() {
        return n;
    }

    public static synchronized void initial(Context context) {
        if (n == null) {
            synchronized (GlobalGatt.class) {
                if (n == null) {
                    n = new GlobalGatt(context.getApplicationContext());
                }
            }
        }
    }
}
