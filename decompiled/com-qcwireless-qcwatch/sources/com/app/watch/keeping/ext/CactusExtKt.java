package com.app.watch.keeping.ext;

import android.app.Activity;
import android.app.Application;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.Process;
import android.util.Log;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.ListenableWorker;
import androidx.work.Operation;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;
import com.app.watch.keeping.Cactus;
import com.app.watch.keeping.callback.AppBackgroundCallback;
import com.app.watch.keeping.entity.CactusConfig;
import com.app.watch.keeping.entity.Constant;
import com.app.watch.keeping.entity.DefaultConfig;
import com.app.watch.keeping.pix.OnePixActivity;
import com.app.watch.keeping.receiver.StopReceiver;
import com.app.watch.keeping.service.CactusJobService;
import com.app.watch.keeping.service.LocalService;
import com.app.watch.keeping.service.RemoteService;
import com.app.watch.keeping.workmanager.CactusWorker;
import com.liulishuo.okdownload.core.breakpoint.BreakpointSQLiteKey;
import java.lang.ref.WeakReference;
import java.util.concurrent.TimeUnit;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CactusExt.kt */
@Metadata(d1 = {"\u0000\u0098\u0001\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\b\u0010(\u001a\u00020)H\u0000\u001a\b\u0010*\u001a\u00020)H\u0000\u001a\u0010\u0010+\u001a\u00020)2\u0006\u0010,\u001a\u00020$H\u0000\u001a#\u0010-\u001a\u00020)*\u00020 2\u0017\u0010.\u001a\u0013\u0012\u0004\u0012\u000200\u0012\u0004\u0012\u00020)0/¢\u0006\u0002\b1\u001a\n\u00102\u001a\u00020)*\u00020 \u001a\n\u00103\u001a\u00020)*\u00020 \u001a#\u00104\u001a\u00020)*\u00020 2\u0017\u0010.\u001a\u0013\u0012\u0004\u0012\u000200\u0012\u0004\u0012\u00020)0/¢\u0006\u0002\b1\u001a\u0014\u00105\u001a\u00020)*\u00020 2\u0006\u00106\u001a\u00020\fH\u0002\u001a\u0014\u00107\u001a\u00020)*\u00020 2\u0006\u00106\u001a\u00020\fH\u0000\u001a\u0014\u00108\u001a\u00020)*\u00020 2\u0006\u00106\u001a\u00020\fH\u0000\u001a\u0014\u00109\u001a\u00020)*\u00020 2\u0006\u00106\u001a\u00020\fH\u0000\u001a\u001a\u0010:\u001a\u00020)*\u00020 2\f\u0010.\u001a\b\u0012\u0004\u0012\u00020)0;H\u0000\u001a\f\u0010<\u001a\u00020)*\u00020 H\u0000\u001a\f\u0010=\u001a\u00020)*\u00020 H\u0000\u001a\f\u0010>\u001a\u00020)*\u00020?H\u0000\u001a2\u0010@\u001a\u00020\u0007*\u00020A2\n\u0010B\u001a\u0006\u0012\u0002\b\u00030C2\u0006\u0010D\u001a\u00020E2\u0006\u00106\u001a\u00020\f2\b\b\u0002\u0010F\u001a\u00020\u0007H\u0002\u001a\u0014\u0010G\u001a\u00020)*\u00020 2\u0006\u0010H\u001a\u00020IH\u0000\u001a&\u0010J\u001a\u00020\u0007*\u00020A2\u0006\u0010D\u001a\u00020E2\u0006\u00106\u001a\u00020\f2\b\b\u0002\u0010F\u001a\u00020\u0007H\u0000\u001a\f\u0010K\u001a\u00020)*\u00020 H\u0001\u001a\u001c\u0010L\u001a\u00020\u0007*\u00020A2\u0006\u0010D\u001a\u00020E2\u0006\u00106\u001a\u00020\fH\u0000\u001a\f\u0010M\u001a\u00020)*\u00020AH\u0000\u001a*\u0010N\u001a\u00020)*\u00020O2\n\b\u0002\u0010P\u001a\u0004\u0018\u00010Q2\u0010\b\u0002\u0010.\u001a\n\u0012\u0004\u0012\u00020)\u0018\u00010;H\u0000\u001a\f\u0010R\u001a\u00020)*\u00020 H\u0000\u001a\f\u0010S\u001a\u00020T*\u00020 H\u0000\u001a\u0014\u0010U\u001a\u00020)*\u00020 2\u0006\u00106\u001a\u00020\fH\u0000\"\u0014\u0010\u0000\u001a\u00020\u00018@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0002\u0010\u0003\"\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000\"\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0016\u0010\b\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000\"\u001c\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010\"\u001b\u0010\u0011\u001a\u00020\u00128@X\u0080\u0084\u0002¢\u0006\f\n\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0013\u0010\u0014\"\u000e\u0010\u0017\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000\"\u001a\u0010\u0018\u001a\u00020\u0001X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u0003\"\u0004\b\u001a\u0010\u001b\"\u001a\u0010\u001c\u001a\u00020\u0001X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u0003\"\u0004\b\u001e\u0010\u001b\"\u0015\u0010\u001f\u001a\u00020\u0007*\u00020 8F¢\u0006\u0006\u001a\u0004\b!\u0010\"\"\u0018\u0010#\u001a\u00020$*\u00020$8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b%\u0010&\"\u0018\u0010'\u001a\u00020\u0007*\u00020 8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b'\u0010\"¨\u0006V"}, d2 = {BreakpointSQLiteKey.ID, "", "getId", "()I", "mAppBackgroundCallback", "Lcom/app/watch/keeping/callback/AppBackgroundCallback;", "mIsForeground", "", "mWeakReference", "Ljava/lang/ref/WeakReference;", "Landroid/app/Activity;", "sCactusConfig", "Lcom/app/watch/keeping/entity/CactusConfig;", "getSCactusConfig", "()Lcom/app/watch/keeping/entity/CactusConfig;", "setSCactusConfig", "(Lcom/app/watch/keeping/entity/CactusConfig;)V", "sMainHandler", "Landroid/os/Handler;", "getSMainHandler", "()Landroid/os/Handler;", "sMainHandler$delegate", "Lkotlin/Lazy;", "sRegistered", "sStartTimes", "getSStartTimes", "setSStartTimes", "(I)V", "sTimes", "getSTimes", "setSTimes", "cactusIsRunning", "Landroid/content/Context;", "getCactusIsRunning", "(Landroid/content/Context;)Z", "fieldById", "", "getFieldById", "(Ljava/lang/String;)Ljava/lang/String;", "isCactusRunning", "backBackground", "", "finishOnePix", "log", NotificationCompat.CATEGORY_MESSAGE, "cactus", "block", "Lkotlin/Function1;", "Lcom/app/watch/keeping/Cactus;", "Lkotlin/ExtensionFunctionType;", "cactusRestart", "cactusUnregister", "cactusUpdateNotification", "handleRestartIntent", Constant.CACTUS_CONFIG, "register", "registerCactus", "registerJobCactus", "registerStopReceiver", "Lkotlin/Function0;", "registerWorker", "restart", "setOnePix", "Lcom/app/watch/keeping/pix/OnePixActivity;", "startAndBindService", "Landroid/app/Service;", "cls", "Ljava/lang/Class;", "serviceConnection", "Landroid/content/ServiceConnection;", "isStart", "startInternService", "intent", "Landroid/content/Intent;", "startLocalService", "startOnePixActivity", "startRemoteService", "stopService", "unlinkToDeath", "Landroid/os/IBinder$DeathRecipient;", "iInterface", "Landroid/os/IInterface;", "unregister", "unregisterWorker", "Landroidx/work/Operation;", "updateNotification", "keeping_release"}, k = 2, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes.dex */
public final class CactusExtKt {
    private static AppBackgroundCallback mAppBackgroundCallback;
    private static boolean mIsForeground;
    private static WeakReference<Activity> mWeakReference;
    private static CactusConfig sCactusConfig;
    private static final Lazy sMainHandler$delegate = LazyKt.lazy(new Function0<Handler>() { // from class: com.app.watch.keeping.ext.CactusExtKt$sMainHandler$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final Handler invoke() {
            return new Handler(Looper.getMainLooper());
        }
    });
    private static boolean sRegistered;
    private static int sStartTimes;
    private static int sTimes;

    public static final Handler getSMainHandler() {
        return (Handler) sMainHandler$delegate.getValue();
    }

    public static final int getSTimes() {
        return sTimes;
    }

    public static final void setSTimes(int i) {
        sTimes = i;
    }

    public static final int getSStartTimes() {
        return sStartTimes;
    }

    public static final void setSStartTimes(int i) {
        sStartTimes = i;
    }

    public static final CactusConfig getSCactusConfig() {
        return sCactusConfig;
    }

    public static final void setSCactusConfig(CactusConfig cactusConfig) {
        sCactusConfig = cactusConfig;
    }

    public static final void cactus(Context context, Function1<? super Cactus, Unit> block) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        Intrinsics.checkNotNullParameter(block, "block");
        Cactus companion = Cactus.INSTANCE.getInstance();
        block.invoke(companion);
        companion.register(context);
    }

    public static final void cactusUnregister(Context context) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        Cactus.INSTANCE.getInstance().unregister(context);
    }

    public static final void cactusRestart(Context context) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        Cactus.INSTANCE.getInstance().restart(context);
    }

    public static final void cactusUpdateNotification(Context context, Function1<? super Cactus, Unit> block) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        Intrinsics.checkNotNullParameter(block, "block");
        Cactus companion = Cactus.INSTANCE.getInstance();
        block.invoke(companion);
        companion.updateNotification(context);
    }

    public static final boolean getCactusIsRunning(Context context) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        return Cactus.INSTANCE.getInstance().isRunning(context);
    }

    public static final void registerStopReceiver(Context context, Function0<Unit> block) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        Intrinsics.checkNotNullParameter(block, "block");
        StopReceiver.INSTANCE.newInstance$keeping_release(context).register$keeping_release(block);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final void register(Context context, CactusConfig cactusConfig) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        Intrinsics.checkNotNullParameter(cactusConfig, "cactusConfig");
        if (ManagerExtKt.isMain(context)) {
            try {
                if (sRegistered && isCactusRunning(context)) {
                    log("Cactus is running，Please stop Cactus before registering!!");
                    return;
                }
                sStartTimes++;
                sRegistered = true;
                handleRestartIntent(context, cactusConfig);
                ConfigExtKt.saveConfig(context, cactusConfig);
                if (Build.VERSION.SDK_INT >= 21) {
                    registerJobCactus(context, cactusConfig);
                } else {
                    registerCactus(context, cactusConfig);
                }
                if ((context instanceof Application) && mAppBackgroundCallback == null) {
                    AppBackgroundCallback appBackgroundCallback = new AppBackgroundCallback(context, null, 2, 0 == true ? 1 : 0);
                    mAppBackgroundCallback = appBackgroundCallback;
                    ((Application) context).registerActivityLifecycleCallbacks(appBackgroundCallback);
                }
                AppBackgroundCallback appBackgroundCallback2 = mAppBackgroundCallback;
                if (appBackgroundCallback2 != null) {
                    appBackgroundCallback2.useCallback$keeping_release(true);
                }
            } catch (Exception unused) {
                log("Unable to open cactus service!!");
            }
        }
    }

    public static final void unregister(final Context context) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        try {
            if (isCactusRunning(context) && sRegistered) {
                sRegistered = false;
                CactusConfig cactusConfig = sCactusConfig;
                if (cactusConfig != null && cactusConfig.getDefaultConfig().getWorkerEnabled()) {
                    unregisterWorker(context);
                }
                context.sendBroadcast(new Intent("com.app.QWatchPro.flag.stop." + context.getPackageName()));
                getSMainHandler().postDelayed(new Runnable() { // from class: com.app.watch.keeping.ext.CactusExtKt$$ExternalSyntheticLambda1
                    @Override // java.lang.Runnable
                    public final void run() {
                        CactusExtKt.m186unregister$lambda4(context);
                    }
                }, 1000L);
                return;
            }
            log("Cactus is not running，Please make sure Cactus is running!!");
        } catch (Exception unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: unregister$lambda-4, reason: not valid java name */
    public static final void m186unregister$lambda4(Context this_unregister) {
        Intrinsics.checkNotNullParameter(this_unregister, "$this_unregister");
        AppBackgroundCallback appBackgroundCallback = mAppBackgroundCallback;
        if (appBackgroundCallback != null) {
            appBackgroundCallback.useCallback$keeping_release(false);
            if (this_unregister instanceof Application) {
                ((Application) this_unregister).unregisterActivityLifecycleCallbacks(appBackgroundCallback);
                mAppBackgroundCallback = null;
            }
        }
    }

    public static final void restart(Context context) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        register(context, ConfigExtKt.getConfig(context));
    }

    public static final void updateNotification(Context context, CactusConfig cactusConfig) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        Intrinsics.checkNotNullParameter(cactusConfig, "cactusConfig");
        if (ConfigExtKt.getConfig(context).getNotificationConfig().canUpdate(cactusConfig.getNotificationConfig())) {
            ConfigExtKt.saveConfig(context, cactusConfig);
            NotificationManagerCompat notificationManagerCompatFrom = NotificationManagerCompat.from(context);
            Intrinsics.checkNotNullExpressionValue(notificationManagerCompatFrom, "from(this)");
            notificationManagerCompatFrom.notify(cactusConfig.getNotificationConfig().getServiceId(), NotificationExtKt.getNotification(context, cactusConfig.getNotificationConfig()));
        }
    }

    public static final void registerCactus(final Context context, final CactusConfig cactusConfig) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        Intrinsics.checkNotNullParameter(cactusConfig, "cactusConfig");
        Intent intent = new Intent(context, (Class<?>) LocalService.class);
        intent.putExtra(Constant.CACTUS_CONFIG, cactusConfig);
        startInternService(context, intent);
        getSMainHandler().postDelayed(new Runnable() { // from class: com.app.watch.keeping.ext.CactusExtKt$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                CactusExtKt.m184registerCactus$lambda5(cactusConfig, context);
            }
        }, 5000L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: registerCactus$lambda-5, reason: not valid java name */
    public static final void m184registerCactus$lambda5(CactusConfig cactusConfig, Context this_registerCactus) {
        Intrinsics.checkNotNullParameter(cactusConfig, "$cactusConfig");
        Intrinsics.checkNotNullParameter(this_registerCactus, "$this_registerCactus");
        if (cactusConfig.getDefaultConfig().getWorkerEnabled()) {
            registerWorker(this_registerCactus);
        } else {
            unregisterWorker(this_registerCactus);
        }
    }

    public static final void registerJobCactus(Context context, CactusConfig cactusConfig) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        Intrinsics.checkNotNullParameter(cactusConfig, "cactusConfig");
        Intent intent = new Intent(context, (Class<?>) CactusJobService.class);
        intent.putExtra(Constant.CACTUS_CONFIG, cactusConfig);
        startInternService(context, intent);
    }

    public static final void registerWorker(Context context) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        if (isCactusRunning(context) && sRegistered) {
            try {
                PeriodicWorkRequest periodicWorkRequestBuild = new PeriodicWorkRequest.Builder((Class<? extends ListenableWorker>) CactusWorker.class, 15L, TimeUnit.SECONDS).build();
                Intrinsics.checkNotNullExpressionValue(periodicWorkRequestBuild, "Builder(CactusWorker::cl…                 .build()");
                WorkManager.getInstance(context).enqueueUniquePeriodicWork(CactusWorker.class.getName(), ExistingPeriodicWorkPolicy.REPLACE, periodicWorkRequestBuild);
            } catch (Exception unused) {
                unregisterWorker(context);
                log("WorkManager registration failed");
            }
        }
    }

    public static final Operation unregisterWorker(Context context) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        Operation operationCancelUniqueWork = WorkManager.getInstance(context).cancelUniqueWork(CactusWorker.class.getName());
        Intrinsics.checkNotNullExpressionValue(operationCancelUniqueWork, "getInstance(this).cancel…sWorker::class.java.name)");
        return operationCancelUniqueWork;
    }

    public static final boolean startRemoteService(Service service, ServiceConnection serviceConnection, CactusConfig cactusConfig) {
        Intrinsics.checkNotNullParameter(service, "<this>");
        Intrinsics.checkNotNullParameter(serviceConnection, "serviceConnection");
        Intrinsics.checkNotNullParameter(cactusConfig, "cactusConfig");
        return startAndBindService$default(service, RemoteService.class, serviceConnection, cactusConfig, false, 8, null);
    }

    public static /* synthetic */ boolean startLocalService$default(Service service, ServiceConnection serviceConnection, CactusConfig cactusConfig, boolean z, int i, Object obj) {
        if ((i & 4) != 0) {
            z = true;
        }
        return startLocalService(service, serviceConnection, cactusConfig, z);
    }

    public static final boolean startLocalService(Service service, ServiceConnection serviceConnection, CactusConfig cactusConfig, boolean z) {
        Intrinsics.checkNotNullParameter(service, "<this>");
        Intrinsics.checkNotNullParameter(serviceConnection, "serviceConnection");
        Intrinsics.checkNotNullParameter(cactusConfig, "cactusConfig");
        return startAndBindService(service, LocalService.class, serviceConnection, cactusConfig, z);
    }

    static /* synthetic */ boolean startAndBindService$default(Service service, Class cls, ServiceConnection serviceConnection, CactusConfig cactusConfig, boolean z, int i, Object obj) {
        if ((i & 8) != 0) {
            z = true;
        }
        return startAndBindService(service, cls, serviceConnection, cactusConfig, z);
    }

    private static final boolean startAndBindService(Service service, Class<?> cls, ServiceConnection serviceConnection, CactusConfig cactusConfig, boolean z) {
        Service service2 = service;
        Intent intent = new Intent(service2, cls);
        intent.putExtra(Constant.CACTUS_CONFIG, cactusConfig);
        if (z) {
            startInternService(service2, intent);
        }
        return service.bindService(intent, serviceConnection, 64);
    }

    public static final void startInternService(Context context, Intent intent) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        Intrinsics.checkNotNullParameter(intent, "intent");
        if (Build.VERSION.SDK_INT >= 26) {
            context.startForegroundService(intent);
        } else {
            context.startService(intent);
        }
    }

    public static final void stopService(final Service service) {
        Intrinsics.checkNotNullParameter(service, "<this>");
        getSMainHandler().postDelayed(new Runnable() { // from class: com.app.watch.keeping.ext.CactusExtKt$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                CactusExtKt.m185stopService$lambda7(service);
            }
        }, 1000L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: stopService$lambda-7, reason: not valid java name */
    public static final void m185stopService$lambda7(Service this_stopService) {
        Intrinsics.checkNotNullParameter(this_stopService, "$this_stopService");
        try {
            this_stopService.stopSelf();
        } catch (Exception unused) {
        }
    }

    private static final void handleRestartIntent(Context context, CactusConfig cactusConfig) {
        DefaultConfig defaultConfig = cactusConfig.getDefaultConfig();
        if (defaultConfig.getCrashRestartEnabled()) {
            defaultConfig.setRestartIntent(context.getPackageManager().getLaunchIntentForPackage(context.getPackageName()));
            Intent restartIntent = defaultConfig.getRestartIntent();
            if (restartIntent != null) {
                restartIntent.addFlags(536870912);
                restartIntent.addFlags(268435456);
                return;
            }
            return;
        }
        defaultConfig.setRestartIntent(null);
    }

    public static final void startOnePixActivity(Context context) throws PendingIntent.CanceledException {
        Intrinsics.checkNotNullParameter(context, "<this>");
        if (ManagerExtKt.isScreenOn(context) || Build.VERSION.SDK_INT >= 28) {
            return;
        }
        mIsForeground = ManagerExtKt.isForeground(context);
        log("isForeground:" + mIsForeground);
        Intent intent = new Intent(context, (Class<?>) OnePixActivity.class);
        intent.addFlags(536870912);
        intent.addFlags(268435456);
        try {
            PendingIntent.getActivity(context, 0, intent, 201326592).send();
        } catch (Exception unused) {
        }
    }

    public static final void finishOnePix() {
        WeakReference<Activity> weakReference = mWeakReference;
        if (weakReference != null) {
            Activity activity = weakReference.get();
            if (activity != null) {
                activity.finish();
            }
            mWeakReference = null;
        }
    }

    public static final void setOnePix(OnePixActivity onePixActivity) {
        Intrinsics.checkNotNullParameter(onePixActivity, "<this>");
        if (mWeakReference == null) {
            mWeakReference = new WeakReference<>(onePixActivity);
        }
    }

    public static final void backBackground() {
        Activity activity;
        WeakReference<Activity> weakReference = mWeakReference;
        if (weakReference == null || (activity = weakReference.get()) == null || mIsForeground) {
            return;
        }
        Intrinsics.checkNotNullExpressionValue(activity, "");
        if (ManagerExtKt.isScreenOn(activity)) {
            Intent intent = new Intent("android.intent.action.MAIN");
            intent.setFlags(268435456);
            intent.addCategory("android.intent.category.HOME");
            activity.startActivity(intent);
        }
    }

    public static final boolean isCactusRunning(Context context) throws SecurityException {
        Intrinsics.checkNotNullParameter(context, "<this>");
        String name = LocalService.class.getName();
        Intrinsics.checkNotNullExpressionValue(name, "LocalService::class.java.name");
        return ManagerExtKt.isRunningTaskExist(context, Constant.CACTUS_EMOTE_SERVICE) & ManagerExtKt.isServiceRunning(context, name);
    }

    public static final String getFieldById(String str) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        return "com.app.QWatchPro." + str + '.' + getId();
    }

    public static final int getId() {
        return Process.myUid() <= 0 ? Process.myPid() : Process.myUid();
    }

    public static /* synthetic */ void unlinkToDeath$default(IBinder.DeathRecipient deathRecipient, IInterface iInterface, Function0 function0, int i, Object obj) {
        if ((i & 1) != 0) {
            iInterface = null;
        }
        if ((i & 2) != 0) {
            function0 = null;
        }
        unlinkToDeath(deathRecipient, iInterface, function0);
    }

    public static final void unlinkToDeath(IBinder.DeathRecipient deathRecipient, IInterface iInterface, Function0<Unit> function0) {
        IBinder iBinderAsBinder;
        Intrinsics.checkNotNullParameter(deathRecipient, "<this>");
        if (iInterface != null && (iBinderAsBinder = iInterface.asBinder()) != null) {
            iBinderAsBinder.unlinkToDeath(deathRecipient, 0);
        }
        if (function0 != null) {
            function0.invoke();
        }
    }

    public static final void log(String msg) {
        DefaultConfig defaultConfig;
        Intrinsics.checkNotNullParameter(msg, "msg");
        CactusConfig cactusConfig = sCactusConfig;
        if (cactusConfig != null && (defaultConfig = cactusConfig.getDefaultConfig()) != null) {
            if (defaultConfig.getDebug()) {
                Log.d(Constant.CACTUS_TAG, msg);
                return;
            }
            return;
        }
        Log.v(Constant.CACTUS_TAG, msg);
    }
}
