package com.oudmon.ble.base.bluetooth.spp;

import java.util.ArrayDeque;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/oudmon/ble/base/bluetooth/spp/SerialListener.class */
public interface SerialListener {
    void onSerialConnect();

    void onSerialConnectError(Exception exc);

    void onSerialRead(byte[] bArr);

    void onSerialRead(ArrayDeque<byte[]> arrayDeque);

    void onSerialIoError(Exception exc);
}
