package com.baidu.location.indoor;

import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanResult;
import android.os.Build;
import android.os.SystemClock;
import android.text.TextUtils;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes.dex */
class g extends ScanCallback {
    final /* synthetic */ f a;

    g(f fVar) {
        this.a = fVar;
    }

    @Override // android.bluetooth.le.ScanCallback
    public void onScanResult(int i, ScanResult scanResult) {
        long jElapsedRealtimeNanos;
        if (this.a.e == null || scanResult.getDevice() == null || TextUtils.isEmpty(scanResult.getDevice().getAddress())) {
            return;
        }
        this.a.b = System.currentTimeMillis();
        this.a.e.put(scanResult.getDevice().getAddress(), scanResult);
        long jCurrentTimeMillis = System.currentTimeMillis() - this.a.s;
        if (jCurrentTimeMillis > 800 || jCurrentTimeMillis < -100) {
            this.a.e.values().size();
            this.a.s = System.currentTimeMillis();
            long timestampNanos = scanResult.getTimestampNanos() / 1000000;
            if (Build.VERSION.SDK_INT >= 17) {
                try {
                    jElapsedRealtimeNanos = SystemClock.elapsedRealtimeNanos() / 1000000;
                } catch (Error unused) {
                }
            } else {
                jElapsedRealtimeNanos = 0;
            }
            Iterator it = this.a.e.entrySet().iterator();
            while (it.hasNext()) {
                ScanResult scanResult2 = (ScanResult) ((Map.Entry) it.next()).getValue();
                boolean z = false;
                if (timestampNanos - (scanResult2.getTimestampNanos() / 1000000) > 2500) {
                    it.remove();
                    z = true;
                }
                if (jElapsedRealtimeNanos != 0 && !z && jElapsedRealtimeNanos - (scanResult2.getTimestampNanos() / 1000000) > 2500) {
                    it.remove();
                }
            }
            this.a.e.values().size();
            this.a.j();
        }
    }
}
