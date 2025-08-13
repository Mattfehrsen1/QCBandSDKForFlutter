package com.qcwireless.qcwatch.ui.base.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import com.oudmon.ble.base.bluetooth.BleBaseControl;
import com.qcwireless.qcwatch.QJavaApplication;
import com.qcwireless.qcwatch.ui.base.service.XService;
import com.qcwireless.qcwatch.ui.base.service.YService;

/* loaded from: classes3.dex */
public class RebootReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        BleBaseControl.getInstance(QJavaApplication.getInstance().getApplication()).setBluetoothTurnOff(true);
        if (Build.VERSION.SDK_INT >= 26) {
            context.startForegroundService(new Intent(context, (Class<?>) YService.class));
        } else {
            context.startService(new Intent(context, (Class<?>) XService.class));
        }
    }
}
