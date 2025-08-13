package com.qcwireless.qcwatch.ui.base.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;
import com.oudmon.ble.base.bluetooth.BleOperateManager;
import com.qcwireless.qcwatch.ui.base.thread.ThreadManager;
import java.util.Objects;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: NetWorkStateReceiver.kt */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001a\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0016¨\u0006\t"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/receiver/NetWorkStateReceiver;", "Landroid/content/BroadcastReceiver;", "()V", "onReceive", "", "context", "Landroid/content/Context;", "intent", "Landroid/content/Intent;", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class NetWorkStateReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        Intrinsics.checkNotNullParameter(context, "context");
        Object systemService = context.getSystemService("connectivity");
        Objects.requireNonNull(systemService, "null cannot be cast to non-null type android.net.ConnectivityManager");
        ConnectivityManager connectivityManager = (ConnectivityManager) systemService;
        if (Build.VERSION.SDK_INT < 23) {
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            if (activeNetworkInfo != null && activeNetworkInfo.isAvailable()) {
                if (activeNetworkInfo.getType() == 1) {
                    if (BleOperateManager.getInstance().isConnected()) {
                        return;
                    }
                    ThreadManager.getInstance().wakeUp();
                    return;
                } else {
                    if (activeNetworkInfo.getType() != 0 || BleOperateManager.getInstance().isConnected()) {
                        return;
                    }
                    ThreadManager.getInstance().wakeUp();
                    return;
                }
            }
            if (BleOperateManager.getInstance().isConnected()) {
                return;
            }
            ThreadManager.getInstance().wakeUp();
            return;
        }
        Network activeNetwork = connectivityManager.getActiveNetwork();
        if (activeNetwork != null) {
            NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(activeNetwork);
            if (networkCapabilities != null) {
                if (networkCapabilities.hasTransport(1) || !networkCapabilities.hasTransport(0) || BleOperateManager.getInstance().isConnected()) {
                    return;
                }
                ThreadManager.getInstance().wakeUp();
                return;
            }
            if (BleOperateManager.getInstance().isConnected()) {
                return;
            }
            ThreadManager.getInstance().wakeUp();
            return;
        }
        if (BleOperateManager.getInstance().isConnected()) {
            return;
        }
        ThreadManager.getInstance().wakeUp();
    }
}
