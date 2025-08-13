package com.app.watch.keeping.ext;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Parcelable;
import android.widget.RemoteViews;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import com.app.watch.keeping.entity.Constant;
import com.app.watch.keeping.entity.NotificationConfig;
import com.app.watch.keeping.exception.CactusException;
import com.app.watch.keeping.service.HideForegroundService;
import java.util.Objects;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: NotificationExt.kt */
@Metadata(d1 = {"\u0000*\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\u001a\u0014\u0010\u0005\u001a\u00020\u0006*\u00020\u00072\u0006\u0010\b\u001a\u00020\u0002H\u0000\u001a\u001e\u0010\t\u001a\u00020\n*\u00020\u000b2\u0006\u0010\b\u001a\u00020\u00022\b\b\u0002\u0010\f\u001a\u00020\rH\u0000\"\u0018\u0010\u0000\u001a\u00020\u0001*\u00020\u00028BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004¨\u0006\u000e"}, d2 = {"handleSmallIcon", "", "Lcom/app/watch/keeping/entity/NotificationConfig;", "getHandleSmallIcon", "(Lcom/app/watch/keeping/entity/NotificationConfig;)I", "getNotification", "Landroid/app/Notification;", "Landroid/content/Context;", Constant.CACTUS_NOTIFICATION_CONFIG, "setNotification", "", "Landroid/app/Service;", "isHideService", "", "keeping_release"}, k = 2, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes.dex */
public final class NotificationExtKt {
    private static final int getHandleSmallIcon(NotificationConfig notificationConfig) {
        if (notificationConfig.getHideNotification() && Build.VERSION.SDK_INT != 25) {
            if (Build.VERSION.SDK_INT < 26 || notificationConfig.getHideNotificationAfterO()) {
                return 0;
            }
            return notificationConfig.getSmallIcon();
        }
        return notificationConfig.getSmallIcon();
    }

    public static /* synthetic */ void setNotification$default(Service service, NotificationConfig notificationConfig, boolean z, int i, Object obj) throws CactusException {
        if ((i & 2) != 0) {
            z = false;
        }
        setNotification(service, notificationConfig, z);
    }

    public static final void setNotification(Service service, NotificationConfig notificationConfig, boolean z) throws CactusException {
        Intrinsics.checkNotNullParameter(service, "<this>");
        Intrinsics.checkNotNullParameter(notificationConfig, "notificationConfig");
        Service service2 = service;
        final NotificationManagerCompat notificationManagerCompatFrom = NotificationManagerCompat.from(service2);
        Intrinsics.checkNotNullExpressionValue(notificationManagerCompatFrom, "from(this)");
        final Notification notification = getNotification(service2, notificationConfig);
        notificationManagerCompatFrom.notify(notificationConfig.getServiceId(), notification);
        service.startForeground(notificationConfig.getServiceId(), notification);
        if (notificationConfig.getHideNotification()) {
            if (Build.VERSION.SDK_INT >= 26) {
                if (notificationManagerCompatFrom.getNotificationChannel(notification.getChannelId()) == null || !notificationConfig.getHideNotificationAfterO()) {
                    return;
                }
                CactusExtKt.getSMainHandler().postDelayed(new Runnable() { // from class: com.app.watch.keeping.ext.NotificationExtKt$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        NotificationExtKt.m188setNotification$lambda1$lambda0(notificationManagerCompatFrom, notification);
                    }
                }, 1000L);
                return;
            }
            if (Build.VERSION.SDK_INT >= 25 || z) {
                return;
            }
            Intent intent = new Intent(service2, (Class<?>) HideForegroundService.class);
            intent.putExtra(Constant.CACTUS_NOTIFICATION_CONFIG, notificationConfig);
            CactusExtKt.startInternService(service2, intent);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setNotification$lambda-1$lambda-0, reason: not valid java name */
    public static final void m188setNotification$lambda1$lambda0(NotificationManagerCompat managerCompat, Notification notification) {
        Intrinsics.checkNotNullParameter(managerCompat, "$managerCompat");
        Intrinsics.checkNotNullParameter(notification, "$notification");
        managerCompat.deleteNotificationChannel(notification.getChannelId());
    }

    public static final Notification getNotification(Context context, NotificationConfig notificationConfig) throws CactusException {
        Intrinsics.checkNotNullParameter(context, "<this>");
        Intrinsics.checkNotNullParameter(notificationConfig, "notificationConfig");
        NotificationManagerCompat notificationManagerCompatFrom = NotificationManagerCompat.from(context);
        Intrinsics.checkNotNullExpressionValue(notificationManagerCompatFrom, "from(this@getNotification)");
        Notification getNotification = notificationConfig.getNotification();
        if (getNotification == null) {
            NotificationCompat.Builder smallIcon = new NotificationCompat.Builder(context, notificationConfig.getChannelId()).setContentTitle(notificationConfig.getTitle()).setContentText(notificationConfig.getContent()).setSmallIcon(getHandleSmallIcon(notificationConfig));
            Bitmap largeIconBitmap = notificationConfig.getLargeIconBitmap();
            if (largeIconBitmap == null) {
                if (notificationConfig.getLargeIcon() == 0) {
                    largeIconBitmap = null;
                } else {
                    largeIconBitmap = BitmapFactory.decodeResource(context.getResources(), notificationConfig.getLargeIcon());
                }
            }
            NotificationCompat.Builder priority = smallIcon.setLargeIcon(largeIconBitmap).setContentIntent(notificationConfig.getPendingIntent()).setAutoCancel(true).setVisibility(-1).setPriority(-2);
            RemoteViews remoteViews = notificationConfig.getRemoteViews();
            if (remoteViews != null) {
                priority.setContent(remoteViews);
            }
            RemoteViews bigRemoteViews = notificationConfig.getBigRemoteViews();
            if (bigRemoteViews != null) {
                priority.setCustomBigContentView(bigRemoteViews);
            }
            getNotification = priority.build();
        }
        if (Build.VERSION.SDK_INT >= 26 && notificationManagerCompatFrom.getNotificationChannel(getNotification.getChannelId()) == null) {
            if (notificationConfig.getNotificationChannel() != null && (notificationConfig.getNotificationChannel() instanceof NotificationChannel)) {
                Parcelable notificationChannel = notificationConfig.getNotificationChannel();
                Objects.requireNonNull(notificationChannel, "null cannot be cast to non-null type android.app.NotificationChannel");
                if (!Intrinsics.areEqual(((NotificationChannel) notificationChannel).getId(), getNotification.getChannelId())) {
                    throw new CactusException("保证渠道相同(The id of the NotificationChannel is different from the channel of the Notification.)");
                }
            } else {
                notificationConfig.setNotificationChannel(new NotificationChannel(getNotification.getChannelId(), notificationConfig.getChannelName(), 0));
            }
            Parcelable notificationChannel2 = notificationConfig.getNotificationChannel();
            Objects.requireNonNull(notificationChannel2, "null cannot be cast to non-null type android.app.NotificationChannel");
            notificationManagerCompatFrom.createNotificationChannel((NotificationChannel) notificationChannel2);
        }
        Intrinsics.checkNotNullExpressionValue(getNotification, "getNotification");
        return getNotification;
    }
}
