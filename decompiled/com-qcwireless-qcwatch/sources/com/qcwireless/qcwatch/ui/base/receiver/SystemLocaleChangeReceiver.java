package com.qcwireless.qcwatch.ui.base.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Process;
import com.elvishew.xlog.XLog;
import com.oudmon.ble.base.bluetooth.BleBaseControl;
import com.oudmon.ble.base.bluetooth.BleOperateManager;
import com.qcwireless.qcwatch.QJavaApplication;
import com.qcwireless.qcwatch.base.utils.ActivityCollector;
import com.qcwireless.qcwatch.base.utils.QcDateUtil;
import com.qcwireless.qcwatch.ui.base.service.XService;
import com.qcwireless.qcwatch.ui.base.service.YService;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: SystemLocaleChangeReceiver.kt */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016¨\u0006\t"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/receiver/SystemLocaleChangeReceiver;", "Landroid/content/BroadcastReceiver;", "()V", "onReceive", "", "context", "Landroid/content/Context;", "intent", "Landroid/content/Intent;", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class SystemLocaleChangeReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(intent, "intent");
        BleBaseControl.getInstance(QJavaApplication.getInstance().getApplication()).setBluetoothTurnOff(true);
        if (Build.VERSION.SDK_INT >= 26) {
            context.startForegroundService(new Intent(context, (Class<?>) YService.class));
        } else {
            context.startService(new Intent(context, (Class<?>) XService.class));
        }
        XLog.i("系统切换了语言");
        try {
            if (!StringsKt.equals(Build.BRAND, "xiaomi", true) && !StringsKt.equals(Build.BRAND, "redmi", true)) {
                ActivityCollector.INSTANCE.removeAll();
                Process.killProcess(Process.myPid());
                System.exit(0);
                throw new RuntimeException("System.exit returned normally, while it was supposed to halt JVM.");
            }
            if (BleOperateManager.getInstance().isConnected()) {
                BleOperateManager.getInstance().disconnect();
                ActivityCollector.INSTANCE.removeAll();
            }
            QJavaApplication.trySetupNotifyService(QJavaApplication.getInstance().getApplication());
            QcDateUtil.INSTANCE.getGetInstance().initStringArray();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
