package com.oudmon.ble.base.bluetooth.spp;

import java.util.ArrayDeque;

/* loaded from: classes3.dex */
public interface SerialListener {
    void onSerialConnect();

    void onSerialConnectError(Exception exc);

    void onSerialIoError(Exception exc);

    void onSerialRead(ArrayDeque<byte[]> arrayDeque);

    void onSerialRead(byte[] bArr);
}
