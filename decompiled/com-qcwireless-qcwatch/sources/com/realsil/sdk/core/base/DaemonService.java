package com.realsil.sdk.core.base;

import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;

/* loaded from: classes3.dex */
public abstract class DaemonService extends BaseService {
    @Override // com.realsil.sdk.core.base.BaseService, android.app.Service
    public void onDestroy() {
        super.onDestroy();
        if (Build.VERSION.SDK_INT < 18) {
            startService(new Intent(getApplicationContext(), (Class<?>) DaemonService.class));
            return;
        }
        NotificationManager notificationManager = (NotificationManager) getSystemService("notification");
        if (notificationManager != null) {
            notificationManager.cancel(this.notificationId);
        }
    }

    @Override // com.realsil.sdk.core.base.BaseService, android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        return 1;
    }
}
