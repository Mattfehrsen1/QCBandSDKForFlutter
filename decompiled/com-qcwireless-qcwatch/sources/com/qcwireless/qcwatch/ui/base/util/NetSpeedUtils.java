package com.qcwireless.qcwatch.ui.base.util;

import android.net.TrafficStats;
import com.qcwireless.qcwatch.QCApplication;
import java.util.Timer;
import java.util.TimerTask;
import kotlin.Metadata;

/* compiled from: NetSpeedUtils.kt */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001\u0016B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0010\u001a\u00020\u0011H\u0002J\u0006\u0010\u0012\u001a\u00020\u0004J\u0006\u0010\u0013\u001a\u00020\u0004J\u0006\u0010\u0014\u001a\u00020\u0011J\u0006\u0010\u0015\u001a\u00020\u0011R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/util/NetSpeedUtils;", "", "()V", "lastTotalReceiveBytes", "", "lastTotalTransferBytes", "netSpeedCallback", "Lcom/qcwireless/qcwatch/ui/base/util/NetSpeedUtils$NetSpeedCallback;", "getNetSpeedCallback", "()Lcom/qcwireless/qcwatch/ui/base/util/NetSpeedUtils$NetSpeedCallback;", "setNetSpeedCallback", "(Lcom/qcwireless/qcwatch/ui/base/util/NetSpeedUtils$NetSpeedCallback;)V", "timer", "Ljava/util/Timer;", "timerTask", "Ljava/util/TimerTask;", "calculateNetSpeed", "", "getTotalReceiveBytes", "getTotalTransferBytes", "startMeasuringNetSpeed", "stopMeasuringNetSpeed", "NetSpeedCallback", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class NetSpeedUtils {
    public static final NetSpeedUtils INSTANCE = new NetSpeedUtils();
    private static long lastTotalReceiveBytes;
    private static long lastTotalTransferBytes;
    private static NetSpeedCallback netSpeedCallback;
    private static Timer timer;
    private static TimerTask timerTask;

    /* compiled from: NetSpeedUtils.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H&¨\u0006\u0007"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/util/NetSpeedUtils$NetSpeedCallback;", "", "onNetSpeedChange", "", "downloadSpeed", "", "uploadSpeed", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public interface NetSpeedCallback {
        void onNetSpeedChange(String downloadSpeed, String uploadSpeed);
    }

    private NetSpeedUtils() {
    }

    public final NetSpeedCallback getNetSpeedCallback() {
        return netSpeedCallback;
    }

    public final void setNetSpeedCallback(NetSpeedCallback netSpeedCallback2) {
        netSpeedCallback = netSpeedCallback2;
    }

    public final long getTotalReceiveBytes() {
        long uidRxBytes = TrafficStats.getUidRxBytes(QCApplication.INSTANCE.getCONTEXT().getApplicationInfo().uid);
        if (uidRxBytes == -1) {
            return 0L;
        }
        return uidRxBytes / 1024;
    }

    public final long getTotalTransferBytes() {
        long uidTxBytes = TrafficStats.getUidTxBytes(QCApplication.INSTANCE.getCONTEXT().getApplicationInfo().uid);
        if (uidTxBytes == -1) {
            return 0L;
        }
        return uidTxBytes / 1024;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void calculateNetSpeed() {
        QCApplication.INSTANCE.getCONTEXT();
        NetSpeedUtils netSpeedUtils = INSTANCE;
        long totalReceiveBytes = netSpeedUtils.getTotalReceiveBytes();
        long totalTransferBytes = netSpeedUtils.getTotalTransferBytes();
        long j = totalReceiveBytes - lastTotalReceiveBytes;
        long j2 = totalTransferBytes - lastTotalTransferBytes;
        lastTotalReceiveBytes = totalReceiveBytes;
        lastTotalTransferBytes = totalTransferBytes;
        NetSpeedCallback netSpeedCallback2 = netSpeedCallback;
        if (netSpeedCallback2 != null) {
            netSpeedCallback2.onNetSpeedChange(j + " kb/s", j2 + " kb/s");
        }
    }

    public final void startMeasuringNetSpeed() {
        if (timer == null && timerTask == null) {
            timer = new Timer();
            TimerTask timerTask2 = new TimerTask() { // from class: com.qcwireless.qcwatch.ui.base.util.NetSpeedUtils.startMeasuringNetSpeed.1
                @Override // java.util.TimerTask, java.lang.Runnable
                public void run() {
                    NetSpeedUtils.INSTANCE.calculateNetSpeed();
                }
            };
            timerTask = timerTask2;
            Timer timer2 = timer;
            if (timer2 != null) {
                timer2.schedule(timerTask2, 0L, 1000L);
            }
        }
    }

    public final void stopMeasuringNetSpeed() {
        TimerTask timerTask2 = timerTask;
        if (timerTask2 != null) {
            timerTask2.cancel();
        }
        timerTask = null;
        Timer timer2 = timer;
        if (timer2 != null) {
            timer2.cancel();
        }
        timer = null;
    }
}
