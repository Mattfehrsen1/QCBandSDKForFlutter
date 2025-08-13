package com.realsil.customer.core.bluetooth.connection.le;

import android.annotation.TargetApi;
import android.content.Context;
import com.realsil.customer.core.b.a;

@TargetApi(18)
/* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/core/bluetooth/connection/le/BluetoothGattClientManagerImpl.class */
public final class BluetoothGattClientManagerImpl extends a {
    public static BluetoothGattClientManagerImpl n;

    public BluetoothGattClientManagerImpl(Context context) {
        super(context);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [java.lang.Class<com.realsil.customer.core.bluetooth.connection.le.BluetoothGattClientManagerImpl>] */
    /* JADX WARN: Type inference failed for: r0v2, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v4 */
    public static synchronized void initial(Context context) {
        if (n == null) {
            ?? r0 = BluetoothGattClientManagerImpl.class;
            synchronized (r0) {
                if (n == null) {
                    n = new BluetoothGattClientManagerImpl(context.getApplicationContext());
                }
                r0 = r0;
            }
        }
    }

    public static BluetoothGattClientManagerImpl getInstance() {
        return n;
    }
}
