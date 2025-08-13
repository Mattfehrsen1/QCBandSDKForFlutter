package com.realsil.customer.core.bluetooth.connection.le;

import com.realsil.customer.core.bluetooth.connection.BluetoothClientCallback;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/core/bluetooth/connection/le/BluetoothGattClientCallback.class */
public abstract class BluetoothGattClientCallback extends BluetoothClientCallback {
    public static final int CONNECT_ERROR = 1;
    public static final int DISCOVERY_SERVICE_FAILED = 2;
    public static final int DISCOVERY_SERVICE_TIMEOUT = 3;
    public static final int ENABLE_CCCD_FAILED = 4;
    public static final int ENABLE_CCCD_EXCEPTION = 5;
    public static final int ENABLE_CCCD_TIMEOUT = 6;
}
