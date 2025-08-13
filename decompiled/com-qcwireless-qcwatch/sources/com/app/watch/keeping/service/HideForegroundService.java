package com.app.watch.keeping.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import com.app.watch.keeping.entity.Constant;
import com.app.watch.keeping.entity.NotificationConfig;
import com.app.watch.keeping.exception.CactusException;
import com.app.watch.keeping.ext.CactusExtKt;
import com.app.watch.keeping.ext.NotificationExtKt;
import com.qcwireless.qcwatch.ui.home.gps.service.TrackingService;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: HideForegroundService.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\"\u0010\u0007\u001a\u00020\b2\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\bH\u0016¨\u0006\u000b"}, d2 = {"Lcom/app/watch/keeping/service/HideForegroundService;", "Landroid/app/Service;", "()V", "onBind", "Landroid/os/IBinder;", "intent", "Landroid/content/Intent;", "onStartCommand", "", "flags", "startId", "keeping_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes.dex */
public final class HideForegroundService extends Service {
    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        Intrinsics.checkNotNullParameter(intent, "intent");
        return null;
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int flags, int startId) throws CactusException {
        NotificationConfig notificationConfig;
        if (intent != null && (notificationConfig = (NotificationConfig) intent.getParcelableExtra(Constant.CACTUS_NOTIFICATION_CONFIG)) != null) {
            NotificationExtKt.setNotification(this, notificationConfig, true);
        }
        CactusExtKt.getSMainHandler().postDelayed(new Runnable() { // from class: com.app.watch.keeping.service.HideForegroundService$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                HideForegroundService.m190onStartCommand$lambda1(this.f$0);
            }
        }, TrackingService.Constant.FASTEST_UPDATE_INTERVAL);
        return 2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: onStartCommand$lambda-1, reason: not valid java name */
    public static final void m190onStartCommand$lambda1(HideForegroundService this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.stopForeground(true);
        this$0.stopSelf();
    }
}
