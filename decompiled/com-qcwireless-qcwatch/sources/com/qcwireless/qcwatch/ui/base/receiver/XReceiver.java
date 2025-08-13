package com.qcwireless.qcwatch.ui.base.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import com.qcwireless.qcwatch.ui.base.service.XService;
import com.qcwireless.qcwatch.ui.base.service.YService;

/* loaded from: classes3.dex */
public class XReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (Build.VERSION.SDK_INT >= 26) {
            context.startForegroundService(new Intent(context, (Class<?>) YService.class));
        } else {
            context.startService(new Intent(context, (Class<?>) XService.class));
        }
    }
}
