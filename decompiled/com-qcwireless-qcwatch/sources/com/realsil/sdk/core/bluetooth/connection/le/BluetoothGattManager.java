package com.realsil.sdk.core.bluetooth.connection.le;

import android.content.Context;
import com.realsil.sdk.core.c.b;

/* loaded from: classes3.dex */
public final class BluetoothGattManager extends b {
    public static BluetoothGattManager n;

    public BluetoothGattManager(Context context) {
        super(context);
    }

    public static BluetoothGattManager getInstance() {
        return n;
    }

    public static synchronized void initial(Context context) {
        if (n == null) {
            synchronized (BluetoothGattManager.class) {
                if (n == null) {
                    n = new BluetoothGattManager(context.getApplicationContext());
                }
            }
        }
    }
}
