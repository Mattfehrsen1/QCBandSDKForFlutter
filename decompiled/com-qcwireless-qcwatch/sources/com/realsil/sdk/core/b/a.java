package com.realsil.sdk.core.b;

import android.bluetooth.BluetoothAdapter;
import java.util.Objects;

/* loaded from: classes3.dex */
public final class a {
    public static boolean a(String str) {
        Objects.requireNonNull(str);
        return BluetoothAdapter.checkBluetoothAddress(str) && (Integer.parseInt(str.split(":")[0], 16) & 192) == 192;
    }
}
