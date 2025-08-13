package com.google.android.gms.fitness.request;

import com.google.android.gms.fitness.data.BleDevice;

/* compiled from: com.google.android.gms:play-services-fitness@@21.1.0 */
@Deprecated
/* loaded from: classes2.dex */
public abstract class BleScanCallback {
    public abstract void onDeviceFound(BleDevice bleDevice);

    public abstract void onScanStopped();
}
