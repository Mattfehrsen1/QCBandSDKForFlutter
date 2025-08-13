package com.realsil.customer.core.bluetooth;

import android.annotation.TargetApi;
import android.content.Context;
import com.realsil.customer.core.b.a;

@TargetApi(18)
/* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/core/bluetooth/GlobalGatt.class */
public final class GlobalGatt extends a {
    public static GlobalGatt n;

    public GlobalGatt(Context context) {
        super(context);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [java.lang.Class<com.realsil.customer.core.bluetooth.GlobalGatt>] */
    /* JADX WARN: Type inference failed for: r0v2, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v4 */
    public static synchronized void initial(Context context) {
        if (n == null) {
            ?? r0 = GlobalGatt.class;
            synchronized (r0) {
                if (n == null) {
                    n = new GlobalGatt(context.getApplicationContext());
                }
                r0 = r0;
            }
        }
    }

    public static GlobalGatt getInstance() {
        return n;
    }
}
