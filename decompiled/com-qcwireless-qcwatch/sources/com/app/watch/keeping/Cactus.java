package com.app.watch.keeping;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.PendingIntent;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.widget.RemoteViews;
import com.app.watch.keeping.callback.CactusBackgroundCallback;
import com.app.watch.keeping.callback.CactusCallback;
import com.app.watch.keeping.entity.CactusConfig;
import com.app.watch.keeping.entity.DefaultConfig;
import com.app.watch.keeping.entity.NotificationConfig;
import com.app.watch.keeping.ext.CactusExtKt;
import com.app.watch.keeping.ext.ConfigExtKt;
import com.google.android.gms.common.internal.BaseGmsClient;
import com.qcwireless.qcwatch.ui.mine.chat.Constant;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Cactus.kt */
@Metadata(d1 = {"\u0000\u0084\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000f\u0018\u0000 I2\u00020\u0001:\u0001IB\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001a\u0010\u000b\u001a\u00020\u00002\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000e0\rJ\u000e\u0010\u000b\u001a\u00020\u00002\u0006\u0010\u000f\u001a\u00020\u0010J,\u0010\u0011\u001a\u00020\u00002\u0010\b\u0002\u0010\u0012\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\u00132\u0012\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u000e0\rJ\u000e\u0010\u0011\u001a\u00020\u00002\u0006\u0010\u0016\u001a\u00020\u0017J\u000e\u0010\u0018\u001a\u00020\u00002\u0006\u0010\u0019\u001a\u00020\nJ\u000e\u0010\u001a\u001a\u00020\u00002\u0006\u0010\u0019\u001a\u00020\nJ\u000e\u0010\u001b\u001a\u00020\u00002\u0006\u0010\u001b\u001a\u00020\nJ\u000e\u0010\u001c\u001a\u00020\n2\u0006\u0010\u001d\u001a\u00020\u001eJ\u000e\u0010\u001f\u001a\u00020\u000e2\u0006\u0010\u001d\u001a\u00020\u001eJ\u000e\u0010 \u001a\u00020\u000e2\u0006\u0010\u001d\u001a\u00020\u001eJ\u000e\u0010!\u001a\u00020\u00002\u0006\u0010\"\u001a\u00020\nJ\u000e\u0010#\u001a\u00020\u00002\u0006\u0010$\u001a\u00020%J\u000e\u0010&\u001a\u00020\u00002\u0006\u0010'\u001a\u00020(J\u000e\u0010)\u001a\u00020\u00002\u0006\u0010*\u001a\u00020(J\u000e\u0010+\u001a\u00020\u00002\u0006\u0010,\u001a\u00020(J\u000e\u0010-\u001a\u00020\u00002\u0006\u0010\"\u001a\u00020\nJ\u000e\u0010.\u001a\u00020\u00002\u0006\u0010/\u001a\u000200J\u000e\u0010.\u001a\u00020\u00002\u0006\u0010/\u001a\u00020\u0015J\u000e\u00101\u001a\u00020\u00002\u0006\u0010\"\u001a\u00020\nJ\u000e\u00102\u001a\u00020\u00002\u0006\u00103\u001a\u000204J\u0010\u00105\u001a\u00020\u00002\b\u00106\u001a\u0004\u0018\u000107J\u000e\u00108\u001a\u00020\u00002\u0006\u0010\"\u001a\u00020\nJ\u000e\u00109\u001a\u00020\u00002\u0006\u0010:\u001a\u00020;J\u000e\u0010<\u001a\u00020\u00002\u0006\u0010=\u001a\u00020%J\u000e\u0010>\u001a\u00020\u00002\u0006\u0010?\u001a\u00020\u0015J\u000e\u0010@\u001a\u00020\u00002\u0006\u0010A\u001a\u00020\u0015J\u000e\u0010B\u001a\u00020\u00002\u0006\u0010C\u001a\u00020(J\u000e\u0010D\u001a\u00020\u00002\u0006\u0010\"\u001a\u00020\nJ\u000e\u0010E\u001a\u00020\u00002\u0006\u0010\"\u001a\u00020\nJ\u000e\u0010F\u001a\u00020\u000e2\u0006\u0010\u001d\u001a\u00020\u001eJ\u000e\u0010G\u001a\u00020\u000e2\u0006\u0010\u001d\u001a\u00020\u001eJ\u000e\u0010H\u001a\u00020\u00002\u0006\u0010H\u001a\u00020\nR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006J"}, d2 = {"Lcom/app/watch/keeping/Cactus;", "", "()V", "mCactusConfig", "Lcom/app/watch/keeping/entity/CactusConfig;", "mDefaultConfig", "Lcom/app/watch/keeping/entity/DefaultConfig;", "mNotificationConfig", "Lcom/app/watch/keeping/entity/NotificationConfig;", "mUsePreviousConfig", "", "addBackgroundCallback", "block", "Lkotlin/Function1;", "", "cactusBackgroundCallback", "Lcom/app/watch/keeping/callback/CactusBackgroundCallback;", "addCallback", "stop", "Lkotlin/Function0;", "work", "", "cactusCallback", "Lcom/app/watch/keeping/callback/CactusCallback;", "hideNotification", "hide", "hideNotificationAfterO", "isDebug", "isRunning", "context", "Landroid/content/Context;", "register", "restart", "setBackgroundMusicEnabled", "enabled", "setBigRemoteViews", "bigRemoteViews", "Landroid/widget/RemoteViews;", "setChannelId", "channelId", "", "setChannelName", "channelName", "setContent", Constant.MODIFY_ACTIVITY_INTENT_CONTENT, "setCrashRestartUIEnabled", "setLargeIcon", "largeIcon", "Landroid/graphics/Bitmap;", "setMusicEnabled", "setNotification", "notification", "Landroid/app/Notification;", "setNotificationChannel", "notificationChannel", "Landroid/app/NotificationChannel;", "setOnePixEnabled", "setPendingIntent", BaseGmsClient.KEY_PENDING_INTENT, "Landroid/app/PendingIntent;", "setRemoteViews", "remoteViews", "setServiceId", com.app.watch.keeping.entity.Constant.CACTUS_SERVICE_ID, "setSmallIcon", "smallIcon", "setTitle", "title", "setWorkOnMainThread", "setWorkerEnabled", "unregister", "updateNotification", "usePreviousConfig", "Companion", "keeping_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes.dex */
public final class Cactus {
    public static final String CACTUS_TIMES = "times";
    private CactusConfig mCactusConfig;
    private final DefaultConfig mDefaultConfig;
    private NotificationConfig mNotificationConfig;
    private boolean mUsePreviousConfig;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final String CACTUS_WORK = CactusExtKt.getFieldById("work");
    public static final String CACTUS_STOP = CactusExtKt.getFieldById("stop");
    public static final String CACTUS_BACKGROUND = CactusExtKt.getFieldById("background");
    public static final String CACTUS_FOREGROUND = CactusExtKt.getFieldById("foreground");
    private static final Lazy<Cactus> instance$delegate = LazyKt.lazy(new Function0<Cactus>() { // from class: com.app.watch.keeping.Cactus$Companion$instance$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final Cactus invoke() {
            return new Cactus(null);
        }
    });

    public /* synthetic */ Cactus(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    public static final Cactus getInstance() {
        return INSTANCE.getInstance();
    }

    private Cactus() {
        this.mCactusConfig = new CactusConfig(null, null, 3, null);
        this.mNotificationConfig = new NotificationConfig(0, null, null, null, null, 0, 0, null, false, false, null, null, null, null, null, 32767, null);
        this.mDefaultConfig = new DefaultConfig(false, false, false, false, false, false, false, null, 255, null);
    }

    /* compiled from: Cactus.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0010\u0010\u0003\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R!\u0010\t\u001a\u00020\n8FX\u0087\u0084\u0002¢\u0006\u0012\n\u0004\b\u000e\u0010\u000f\u0012\u0004\b\u000b\u0010\u0002\u001a\u0004\b\f\u0010\r¨\u0006\u0010"}, d2 = {"Lcom/app/watch/keeping/Cactus$Companion;", "", "()V", "CACTUS_BACKGROUND", "", "CACTUS_FOREGROUND", "CACTUS_STOP", "CACTUS_TIMES", "CACTUS_WORK", "instance", "Lcom/app/watch/keeping/Cactus;", "getInstance$annotations", "getInstance", "()Lcom/app/watch/keeping/Cactus;", "instance$delegate", "Lkotlin/Lazy;", "keeping_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        public static /* synthetic */ void getInstance$annotations() {
        }

        private Companion() {
        }

        public final Cactus getInstance() {
            return (Cactus) Cactus.instance$delegate.getValue();
        }
    }

    public final Cactus setNotification(Notification notification) {
        Intrinsics.checkNotNullParameter(notification, "notification");
        this.mNotificationConfig.setNotification(notification);
        return this;
    }

    public final Cactus setNotificationChannel(NotificationChannel notificationChannel) {
        if (Build.VERSION.SDK_INT >= 26) {
            this.mNotificationConfig.setNotificationChannel(notificationChannel);
        }
        return this;
    }

    public final Cactus hideNotification(boolean hide) {
        this.mNotificationConfig.setHideNotification(hide);
        return this;
    }

    public final Cactus hideNotificationAfterO(boolean hide) {
        this.mNotificationConfig.setHideNotificationAfterO(hide);
        return this;
    }

    public final Cactus setPendingIntent(PendingIntent pendingIntent) {
        Intrinsics.checkNotNullParameter(pendingIntent, "pendingIntent");
        this.mNotificationConfig.setPendingIntent(pendingIntent);
        return this;
    }

    public final Cactus setServiceId(int serviceId) {
        this.mNotificationConfig.setServiceId(serviceId);
        return this;
    }

    public final Cactus setChannelId(String channelId) {
        Intrinsics.checkNotNullParameter(channelId, "channelId");
        this.mNotificationConfig.setChannelId(channelId);
        return this;
    }

    public final Cactus setChannelName(String channelName) {
        Intrinsics.checkNotNullParameter(channelName, "channelName");
        this.mNotificationConfig.setChannelName(channelName);
        return this;
    }

    public final Cactus setTitle(String title) {
        Intrinsics.checkNotNullParameter(title, "title");
        this.mNotificationConfig.setTitle(title);
        return this;
    }

    public final Cactus setContent(String content) {
        Intrinsics.checkNotNullParameter(content, "content");
        this.mNotificationConfig.setContent(content);
        return this;
    }

    public final Cactus setRemoteViews(RemoteViews remoteViews) {
        Intrinsics.checkNotNullParameter(remoteViews, "remoteViews");
        this.mNotificationConfig.setHideNotification(false);
        this.mNotificationConfig.setRemoteViews(remoteViews);
        return this;
    }

    public final Cactus setBigRemoteViews(RemoteViews bigRemoteViews) {
        Intrinsics.checkNotNullParameter(bigRemoteViews, "bigRemoteViews");
        this.mNotificationConfig.setHideNotification(false);
        this.mNotificationConfig.setBigRemoteViews(bigRemoteViews);
        return this;
    }

    public final Cactus setSmallIcon(int smallIcon) {
        this.mNotificationConfig.setSmallIcon(smallIcon);
        return this;
    }

    public final Cactus setLargeIcon(int largeIcon) {
        this.mNotificationConfig.setLargeIcon(largeIcon);
        return this;
    }

    public final Cactus setLargeIcon(Bitmap largeIcon) {
        Intrinsics.checkNotNullParameter(largeIcon, "largeIcon");
        this.mNotificationConfig.setLargeIconBitmap(largeIcon);
        return this;
    }

    public final Cactus setMusicEnabled(boolean enabled) {
        this.mDefaultConfig.setMusicEnabled(enabled);
        return this;
    }

    public final Cactus setBackgroundMusicEnabled(boolean enabled) {
        this.mDefaultConfig.setBackgroundMusicEnabled(enabled);
        return this;
    }

    public final Cactus setOnePixEnabled(boolean enabled) {
        this.mDefaultConfig.setOnePixEnabled(enabled);
        return this;
    }

    public final Cactus setWorkerEnabled(boolean enabled) {
        this.mDefaultConfig.setWorkerEnabled(enabled);
        return this;
    }

    public final Cactus setCrashRestartUIEnabled(boolean enabled) {
        this.mDefaultConfig.setCrashRestartEnabled(enabled);
        return this;
    }

    public final Cactus setWorkOnMainThread(boolean enabled) {
        this.mDefaultConfig.setWorkOnMainThread(enabled);
        return this;
    }

    public final Cactus usePreviousConfig(boolean usePreviousConfig) {
        this.mUsePreviousConfig = usePreviousConfig;
        return this;
    }

    public final Cactus isDebug(boolean isDebug) {
        this.mDefaultConfig.setDebug(isDebug);
        return this;
    }

    public final Cactus addCallback(CactusCallback cactusCallback) {
        Intrinsics.checkNotNullParameter(cactusCallback, "cactusCallback");
        com.app.watch.keeping.entity.Constant.INSTANCE.getCALLBACKS$keeping_release().add(cactusCallback);
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ Cactus addCallback$default(Cactus cactus, Function0 function0, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            function0 = null;
        }
        return cactus.addCallback(function0, function1);
    }

    public final Cactus addCallback(final Function0<Unit> stop, final Function1<? super Integer, Unit> work) {
        Intrinsics.checkNotNullParameter(work, "work");
        com.app.watch.keeping.entity.Constant.INSTANCE.getCALLBACKS$keeping_release().add(new CactusCallback() { // from class: com.app.watch.keeping.Cactus$addCallback$2$1
            @Override // com.app.watch.keeping.callback.CactusCallback
            public void doWork(int times) {
                work.invoke(Integer.valueOf(times));
            }

            @Override // com.app.watch.keeping.callback.CactusCallback
            public void onStop() {
                Function0<Unit> function0 = stop;
                if (function0 != null) {
                    function0.invoke();
                }
            }
        });
        return this;
    }

    public final Cactus addBackgroundCallback(CactusBackgroundCallback cactusBackgroundCallback) {
        Intrinsics.checkNotNullParameter(cactusBackgroundCallback, "cactusBackgroundCallback");
        com.app.watch.keeping.entity.Constant.INSTANCE.getBACKGROUND_CALLBACKS$keeping_release().add(cactusBackgroundCallback);
        return this;
    }

    public final Cactus addBackgroundCallback(final Function1<? super Boolean, Unit> block) {
        Intrinsics.checkNotNullParameter(block, "block");
        com.app.watch.keeping.entity.Constant.INSTANCE.getBACKGROUND_CALLBACKS$keeping_release().add(new CactusBackgroundCallback() { // from class: com.app.watch.keeping.Cactus$addBackgroundCallback$2$1
            @Override // com.app.watch.keeping.callback.CactusBackgroundCallback
            public void onBackground(boolean background) {
                block.invoke(Boolean.valueOf(background));
            }
        });
        return this;
    }

    public final void register(Context context) {
        CactusConfig previousConfig;
        Intrinsics.checkNotNullParameter(context, "context");
        CactusConfig cactusConfig = new CactusConfig(this.mNotificationConfig, this.mDefaultConfig);
        if (this.mUsePreviousConfig && (previousConfig = ConfigExtKt.getPreviousConfig(context)) != null) {
            cactusConfig = previousConfig;
        }
        this.mCactusConfig = cactusConfig;
        CactusExtKt.register(context, cactusConfig);
    }

    public final void unregister(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        CactusExtKt.unregister(context);
    }

    public final void restart(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        CactusExtKt.restart(context);
    }

    public final void updateNotification(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.mCactusConfig.setNotificationConfig(this.mNotificationConfig);
        CactusExtKt.updateNotification(context, this.mCactusConfig);
    }

    public final boolean isRunning(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return CactusExtKt.isCactusRunning(context);
    }
}
