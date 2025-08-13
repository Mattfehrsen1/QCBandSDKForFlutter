package com.qcwireless.qcwatch.ui.home.gps.util;

import android.content.Context;
import android.os.PowerManager;

/* loaded from: classes3.dex */
public class MyWakeLock {
    private Context context;
    private PowerManager.WakeLock wakeLock;

    public MyWakeLock(Context context) {
        this.context = context;
    }

    public void acquireWakeLock() {
        try {
            if (this.wakeLock == null) {
                PowerManager.WakeLock wakeLockNewWakeLock = ((PowerManager) this.context.getSystemService("power")).newWakeLock(536870913, getClass().getCanonicalName());
                this.wakeLock = wakeLockNewWakeLock;
                if (wakeLockNewWakeLock != null) {
                    wakeLockNewWakeLock.acquire();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void releaseWakeLock() {
        PowerManager.WakeLock wakeLock = this.wakeLock;
        if (wakeLock == null || !wakeLock.isHeld()) {
            return;
        }
        this.wakeLock.release();
        this.wakeLock = null;
    }
}
